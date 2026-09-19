package se.raddo.raddose3D;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Checks how the file readers behave on input they cannot fully understand.
 * <p>
 * Each case here used to end in a {@code NullPointerException}, a silently
 * truncated structure, or a terminated JVM. None of them are exotic: a
 * missing file, a sequence written on Windows, a PDB from a tool that does
 * not pad to 80 columns.
 */
public class MalformedInputTest {

  /** Empty lists, which several constructors here require. */
  private static final List<String> NO_NAMES = new ArrayList<String>();
  /** Empty amounts. */
  private static final List<Double> NO_AMOUNTS = new ArrayList<Double>();

  /**
   * Writes a file and returns its path.
   *
   * @param path where to write
   * @param text what to write
   * @return the path written
   * @throws IOException if writing fails
   */
  private static Path write(final Path path, final String text)
      throws IOException {
    Files.createDirectories(path.getParent());
    Files.write(path, text.getBytes(StandardCharsets.UTF_8));
    return path;
  }

  // ------------------------------------------------------------------ CIF

  /**
   * A CIF path that does not exist is reported, not dereferenced.
   * <p>
   * {@code getCIFFile} caught the {@code IOException}, printed "Cannot read
   * from specified path", and then wrapped the null reader on the next line.
   * The user saw a stack trace for a NullPointerException, with the message
   * that actually explained the problem scrolled above it.
   *
   * @param dir temporary directory
   */
  @Test
  public void missingCifFileIsReported(@TempDir final Path dir) {
    Path missing = dir.resolve("does-not-exist.cif");

    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new CoefCalcFromCIF(missing.toString()));

    assertTrue(e.getMessage().contains("Cannot read the CIF file"),
        "the message should say what went wrong: " + e.getMessage());
    assertTrue(e.getMessage().contains("does-not-exist.cif"),
        "and which file: " + e.getMessage());
  }

  // ------------------------------------------------------------------ PDB

  /** A minimal PDB with a cell, one symmetry operator and a short sequence. */
  private static String minimalPdb() {
    return "CRYST1   78.270   78.270   78.270  90.00  90.00  90.00 P 1\n"
        + "REMARK 290   SMTRY1   1  1.000000  0.000000  0.000000        0.00000\n"
        + "SEQRES   1 A    3  ALA GLY SER\n"
        + "END\n";
  }

  /**
   * A line too short for the fixed-column slices costs that line, not the
   * rest of the file.
   * <p>
   * The {@code IndexOutOfBoundsException} catch sat outside the read loop, so
   * the first short line abandoned everything after it and a composition was
   * built from however much had been read -- with nothing to say most of the
   * structure was missing.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void shortLinesDoNotTruncateThePdb(@TempDir final Path dir)
      throws IOException {
    Path complete = write(dir.resolve("a/complete.pdb"), minimalPdb());
    // "REMARK 290" alone is 10 characters; the symmetry check reads [13,19).
    Path withShortLine = write(dir.resolve("b/short.pdb"),
        "REMARK 290\n" + minimalPdb());

    CoefCalcFromPDB reference = new CoefCalcFromPDB(complete.toString(),
        NO_NAMES, NO_AMOUNTS, null, null, NO_NAMES, NO_AMOUNTS, 0.0, 0L);
    CoefCalcFromPDB truncated = new CoefCalcFromPDB(withShortLine.toString(),
        NO_NAMES, NO_AMOUNTS, null, null, NO_NAMES, NO_AMOUNTS, 0.0, 0L);

    assertEquals(reference.getNumAminoAcids(), truncated.getNumAminoAcids(),
        "a short line early in the file must not cost the SEQRES records "
            + "that follow it");
    assertEquals(reference.getDensity(), truncated.getDensity(), 1e-12,
        "and so must not change the composition");
  }

  // ------------------------------------------------------------- sequence

  /**
   * Builds a sequence-based calculator from a FASTA string.
   *
   * @param dir temporary directory
   * @param name file name
   * @param fasta file contents
   * @return the constructed calculator
   * @throws IOException if the fixture cannot be written
   */
  private static CoefCalcFromSequence fromFasta(final Path dir,
      final String name, final String fasta) throws IOException {
    Path path = write(dir.resolve(name), fasta);
    return new CoefCalcFromSequence(78.27, 78.27, 78.27, 90.0, 90.0, 90.0, 1,
        NO_NAMES, NO_AMOUNTS, NO_NAMES, NO_AMOUNTS, null, path.toString(),
        NO_NAMES, NO_AMOUNTS, null, null, 0, NO_NAMES, NO_AMOUNTS, 0.0);
  }

  /**
   * A sequence file written on Windows parses.
   * <p>
   * Lines reach {@code parseSequenceLine} untrimmed, so every carriage return
   * was looked up as a residue code, returned null, and threw on the next
   * statement. Nothing in the message mentioned line endings.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void carriageReturnsInASequenceAreIgnored(@TempDir final Path dir)
      throws IOException {
    CoefCalcFromSequence unix = fromFasta(dir, "unix.fasta",
        ">test\nACDEFGHIK\n");
    CoefCalcFromSequence windows = fromFasta(dir, "windows.fasta",
        ">test\r\nACDEFGHIK\r\n");

    assertEquals(unix.getNumAminoAcids(), windows.getNumAminoAcids(),
        "line endings must not change the number of residues read");
    assertEquals(9.0, windows.getNumAminoAcids(), 1e-12,
        "all nine residues should be counted");
  }

  /**
   * Spaces inside a sequence line are ignored rather than looked up.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void whitespaceInASequenceIsIgnored(@TempDir final Path dir)
      throws IOException {
    assertEquals(9.0,
        fromFasta(dir, "spaced.fasta", ">test\nACD EFG HIK\n")
            .getNumAminoAcids(),
        1e-12, "internal spaces should not be read as residues");
  }

  /**
   * A character that is genuinely not a residue code names itself.
   * <p>
   * Stopping is deliberate: silently dropping it would give a protein one
   * residue short and a dose for a molecule the user did not describe. 'O'
   * is pyrrolysine, one of only two letters with no protein residue behind
   * it.
   *
   * @param dir temporary directory
   */
  @Test
  public void unknownResidueCodeNamesItself(@TempDir final Path dir) {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> fromFasta(dir, "bad.fasta", ">test\nACDOFGHIK\n"));

    assertTrue(e.getMessage().contains("'O'"),
        "the offending character should be named: " + e.getMessage());
    assertTrue(e.getMessage().contains("position 4"),
        "and located: " + e.getMessage());
    assertTrue(e.getMessage().contains("protein"),
        "and the sequence type given, since the same letter is valid in DNA: "
            + e.getMessage());
  }

  // ------------------------------------------------------------- cylinder

  /**
   * A cylinder given three dimensions says which two it used.
   * <p>
   * A cylinder needs a diameter and a length. The third value was read and
   * discarded in silence, so {@code Dimensions 40 40 20} gave a 40 um long
   * cylinder rather than the 20 um the input suggests -- twice the volume,
   * with nothing on screen to say so. The behaviour is unchanged; only the
   * silence is.
   */
  @Test
  public void cylinderWithThreeDimensionsWarns() {
    java.io.ByteArrayOutputStream captured =
        new java.io.ByteArrayOutputStream();
    java.io.PrintStream original = System.out;
    try {
      System.setOut(new java.io.PrintStream(captured, true));
      java.util.Map<Object, Object> properties =
          new java.util.HashMap<Object, Object>();
      properties.put(Crystal.CRYSTAL_DIM_X, 40.0);
      properties.put(Crystal.CRYSTAL_DIM_Y, 40.0);
      properties.put(Crystal.CRYSTAL_DIM_Z, 20.0);
      properties.put(Crystal.CRYSTAL_RESOLUTION, 0.5);
      properties.put(Crystal.CRYSTAL_ANGLE_P, 0.0);
      properties.put(Crystal.CRYSTAL_ANGLE_L, 0.0);
      properties.put(Crystal.CRYSTAL_COEFCALC, new CoefCalcAverage());
      Crystal c = new CrystalCylinder(properties);
      assertNotNull(c, "the cylinder should still be built");
    } finally {
      System.setOut(original);
    }

    String output = captured.toString();
    assertTrue(output.contains("a cylinder is described by two dimensions"),
        "a discarded third dimension should be reported: " + output);
    assertTrue(output.contains("20.00"),
        "the ignored value should be named: " + output);
    assertTrue(output.contains("40.00"),
        "and the values actually used: " + output);
  }
}
