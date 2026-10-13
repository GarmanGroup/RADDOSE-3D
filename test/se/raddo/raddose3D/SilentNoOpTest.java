package se.raddo.raddose3D;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Checks input that RADDOSE-3D used to accept and then quietly disregard.
 * <p>
 * Each of these took a value from the user, validated it, stored it, and
 * never read it. None of them failed; they simply did nothing, which is the
 * hardest kind of fault to notice from the outside.
 */
public class SilentNoOpTest {

  /** Empty lists, which several constructors here require. */
  private static final List<String> NO_NAMES = new ArrayList<String>();
  /** Empty amounts. */
  private static final List<Double> NO_AMOUNTS = new ArrayList<Double>();

  /**
   * Builds a small-molecule calculator.
   *
   * @param solventNames solvent species, or an empty list
   * @param solventAmounts their concentrations in mmol/l
   * @return the constructed calculator
   */
  private static CoefCalcSmallMolecules smallMolecule(
      final List<String> solventNames, final List<Double> solventAmounts) {
    return new CoefCalcSmallMolecules(10.0, 12.0, 14.0, 90.0, 90.0, 90.0, 4,
        Arrays.asList("C", "H", "N", "O"), Arrays.asList(6.0, 12.0, 2.0, 3.0),
        solventNames, solventAmounts, null, NO_NAMES, NO_AMOUNTS, null, null,
        NO_NAMES, NO_AMOUNTS, 0.0, 0L);
  }

  /**
   * SolventHeavyConc under SmallMole is refused, not absorbed.
   * <p>
   * It used to reach {@code solventConcentration}, whose only reader is
   * {@code calculateSolventWater} -- guarded by a hardcoded
   * {@code boolean fillRestWithWater = false}. Not one atom of the solute
   * ever reached the cell, and nothing said so.
   */
  @Test
  public void smallMoleculeRefusesSolventItCannotModel() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> smallMolecule(Arrays.asList("Na"), Arrays.asList(500.0)));

    assertTrue(e.getMessage().contains("not modelled"),
        "the message should say the input is not supported: "
            + e.getMessage());
    assertTrue(e.getMessage().contains("Na"),
        "and name what was ignored: " + e.getMessage());
  }

  /** A small molecule with no solvent specified still works. */
  @Test
  public void smallMoleculeWithoutSolventIsUnaffected() {
    CoefCalcSmallMolecules cc = smallMolecule(NO_NAMES, NO_AMOUNTS);
    Element carbon = cc.getParser().getElement("C");

    assertEquals(24.0, cc.getMacromolecularOccurrence(carbon), 1e-12,
        "six carbons per monomer, four monomers");
  }

  /**
   * Carbohydrates now count towards the solvent fraction on the sequence
   * path.
   * <p>
   * The count was recorded by {@code addCarbs}, which ran <em>after</em>
   * {@code calculateSolventFractionFromNums} had already used it, so the
   * estimate always saw zero however many were specified.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void carbohydratesReduceTheSolventFraction(@TempDir final Path dir)
      throws IOException {
    Path fasta = dir.resolve("seq.fasta");
    Files.write(fasta, ">test\nACDEFGHIKLMNPQRSTVWY\n"
        .getBytes(StandardCharsets.UTF_8));

    double withoutCarbs = sequenceSolventFraction(fasta, 0);
    double withCarbs = sequenceSolventFraction(fasta, 50);

    assertNotEquals(withoutCarbs, withCarbs,
        "50 carbohydrate residues occupy some of the cell, so they must "
            + "change the solvent fraction");
    assertTrue(withCarbs < withoutCarbs,
        "adding anything to the cell leaves less room for solvent: "
            + withCarbs + " against " + withoutCarbs);
  }

  /**
   * Builds a sequence-based calculator and reports its solvent fraction.
   *
   * @param fasta path to the sequence file
   * @param numCarb carbohydrate residues per monomer
   * @return the computed solvent fraction
   */
  private static double sequenceSolventFraction(final Path fasta,
      final int numCarb) {
    return new CoefCalcFromSequence(78.27, 78.27, 78.27, 90.0, 90.0, 90.0, 8,
        NO_NAMES, NO_AMOUNTS, NO_NAMES, NO_AMOUNTS, null, fasta.toString(),
        NO_NAMES, NO_AMOUNTS, null, null, numCarb, NO_NAMES, NO_AMOUNTS, 0.0)
            .getSolventFraction();
  }

  /**
   * Copper counts as a light atom, as the constant that gates it says it
   * should.
   * <p>
   * {@code LIGHT_ATOM_MAX_NUM} is documented "Light/heavy element threshold,
   * 29 is treated as light atom", but the comparison was {@code <}, which
   * excluded copper. The only other use of the constant, commented out in
   * {@code Element}, is {@code <=}. A {@code TODO: Is this &lt; or &lt;= ?!}
   * stood against it.
   */
  @Test
  public void copperCountsAsALightAtom() {
    assertEquals(29, Element.LIGHT_ATOM_MAX_NUM,
        "the threshold itself should not move");

    // The heteroatom mass that feeds the solvent estimate includes light
    // atoms only, so a cell containing copper must differ from one without.
    CoefCalcCompute withCopper = new CoefCalcFromParams();
    withCopper.cellVolume(78.27, 78.27, 78.27, 90.0, 90.0, 90.0);
    withCopper.setNumMonomers(1);
    withCopper.setNumAminoAcids(129);
    withCopper.setHetatmOccurrence(
        withCopper.getParser().getElement("CU"), 100.0);

    CoefCalcCompute withZinc = new CoefCalcFromParams();
    withZinc.cellVolume(78.27, 78.27, 78.27, 90.0, 90.0, 90.0);
    withZinc.setNumMonomers(1);
    withZinc.setNumAminoAcids(129);
    withZinc.setHetatmOccurrence(
        withZinc.getParser().getElement("ZN"), 100.0);

    double copper = withCopper.calculateSolventFractionFromNums();
    double zinc = withZinc.calculateSolventFractionFromNums();

    assertTrue(copper < zinc,
        "copper (Z=29) is inside the threshold and so occupies space in the "
            + "estimate, while zinc (Z=30) is excluded: " + copper
            + " should be below " + zinc);
  }

  /**
   * Builds a 20 um cube crystal.
   *
   * @param coefCalc its composition
   * @param escapeKey the escape property to switch on
   * @return the crystal
   */
  private static Crystal cube(final CoefCalc coefCalc, final String escapeKey) {
    Map<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, 20.0);
    properties.put(Crystal.CRYSTAL_DIM_Y, 20.0);
    properties.put(Crystal.CRYSTAL_DIM_Z, 20.0);
    properties.put(Crystal.CRYSTAL_RESOLUTION, 0.5);
    properties.put(Crystal.CRYSTAL_ANGLE_P, 0.0);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0.0);
    properties.put(Crystal.CRYSTAL_COEFCALC, coefCalc);
    properties.put(escapeKey, "TRUE");
    return new CrystalCuboid(properties);
  }

  /**
   * Escape modelling with AbsCoefCalc Average is refused.
   * <p>
   * Average has no elements. Photoelectron escape asked for them and failed
   * with a NullPointerException; fluorescence escape printed a warning and
   * modelled nothing.
   */
  @Test
  public void escapeWithTheAverageCompositionIsRefused() {
    for (String key : new String[] {Crystal.CRYSTAL_ELECTRON_ESCAPE,
        Crystal.CRYSTAL_FLUORESCENT_ESCAPE}) {
      IllegalArgumentException e = assertThrows(
          IllegalArgumentException.class,
          () -> cube(new CoefCalcAverage(), key), key);
      assertTrue(e.getMessage().contains("Average"), e.getMessage());
    }
  }

  /**
   * Builds a SAXS-from-sequence calculator for a 1000 A box at 10 g/l.
   *
   * @param fasta the sequence file
   * @param numCarb carbohydrate residues per monomer
   * @return the calculator
   */
  private static CoefCalcFromSequenceSAXS saxsFromSequence(final Path fasta,
      final int numCarb) {
    return new CoefCalcFromSequenceSAXS(1000.0, 1000.0, 1000.0, 90.0, 90.0,
        90.0, NO_NAMES, NO_AMOUNTS, NO_NAMES, NO_AMOUNTS, null, 10.0,
        fasta.toString(), NO_NAMES, NO_AMOUNTS, null, null, numCarb, NO_NAMES,
        NO_AMOUNTS, 0.0);
  }

  /**
   * NumCarb counts under AbsCoefCalc SAXSSEQ.
   * <p>
   * addCarbs adds getNumCarb() residues, and nothing on this path set it, so
   * the carbohydrate was read and then ignored. It now adds its atoms, and
   * its mass to the monomer -- so a given concentration is fewer monomers.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void saxsFromSequenceCountsCarbohydrate(@TempDir final Path dir)
      throws IOException {
    Path fasta = dir.resolve("insulin.fasta");
    Files.write(fasta, (">insulin A\nGIVEQCCTSICSLYQLENYCN\n"
        + ">insulin B\nFVNQHLCGSHLVEALYLVCGERGFFYTPKT\n")
        .getBytes(StandardCharsets.UTF_8));

    CoefCalcFromSequenceSAXS plain = saxsFromSequence(fasta, 0);
    CoefCalcFromSequenceSAXS sugary = saxsFromSequence(fasta, 40);
    Element carbon = plain.getParser().getElement("C");

    double plainCarbon = plain.getMacromolecularOccurrence(carbon)
        / plain.getNumMonomers();
    double sugaryCarbon = sugary.getMacromolecularOccurrence(carbon)
        / sugary.getNumMonomers();
    assertEquals(6.0 * 40, sugaryCarbon - plainCarbon, 1e-9,
        "six carbons per carbohydrate residue, per monomer");
    assertTrue(sugary.getNumMonomers() < plain.getNumMonomers(),
        "a heavier monomer at the same concentration is fewer monomers: "
            + sugary.getNumMonomers() + " against " + plain.getNumMonomers());
  }

  /**
   * Residues before any header are protein.
   * <p>
   * The residue type started at 0, which names no type, so such a file
   * failed on its first residue, calling it "not a recognised protein residue
   * code" whatever it was.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void aSequenceWithoutAHeaderIsProtein(@TempDir final Path dir)
      throws IOException {
    Path fasta = dir.resolve("plain.fasta");
    Files.write(fasta, "GIVEQCCTSICSLYQLENYCN\n"
        .getBytes(StandardCharsets.UTF_8));

    CoefCalcFromSequence cc = new CoefCalcFromSequence(78.27, 78.27, 78.27,
        90.0, 90.0, 90.0, 8, NO_NAMES, NO_AMOUNTS, NO_NAMES, NO_AMOUNTS, null,
        fasta.toString(), NO_NAMES, NO_AMOUNTS, null, null, 0, NO_NAMES,
        NO_AMOUNTS, 0.0);

    assertEquals(21.0, cc.getNumAminoAcids(), 1e-12);
  }

  /**
   * A chain read as protein but made only of nucleotide codes is reported.
   * <p>
   * Every nucleotide code is an amino acid code too, so a DNA chain whose
   * header lacks ISDNA was read as protein without a word.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void aNucleotideLookalikeChainIsReported(@TempDir final Path dir)
      throws IOException {
    Path fasta = dir.resolve("unmarked.fasta");
    Files.write(fasta, ">chain B, DNA\nACGTACGTACGTACGT\n>marked ISDNA\nACGTACGTACGT\n"
        .getBytes(StandardCharsets.UTF_8));

    PrintStream console = System.out;
    ByteArrayOutputStream captured = new ByteArrayOutputStream();
    System.setOut(new PrintStream(captured, true, "UTF-8"));
    try {
      new CoefCalcFromSequence(78.27, 78.27, 78.27, 90.0, 90.0, 90.0, 8,
          NO_NAMES, NO_AMOUNTS, NO_NAMES, NO_AMOUNTS, null, fasta.toString(),
          NO_NAMES, NO_AMOUNTS, null, null, 0, NO_NAMES, NO_AMOUNTS, 0.0);
    } finally {
      System.setOut(console);
    }
    String output = captured.toString("UTF-8");

    assertTrue(output.contains("A chain of 16 residues was read as protein"),
        "the unmarked chain should be reported: " + output);
    assertEquals(1, output.split("was read as protein", -1).length - 1,
        "and only it, not the chain marked ISDNA: " + output);
  }

  /**
   * A sequence file that cannot be read is an error.
   * <p>
   * It printed "Could not read sequence file." and carried on with no
   * residues, giving a dose for a crystal of solvent alone.
   *
   * @param dir temporary directory
   */
  @Test
  public void aMissingSequenceFileIsAnError(@TempDir final Path dir) {
    String missing = dir.resolve("absent.fasta").toString();
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new CoefCalcFromSequence(78.27, 78.27, 78.27, 90.0, 90.0, 90.0, 8,
            NO_NAMES, NO_AMOUNTS, NO_NAMES, NO_AMOUNTS, null, missing,
            NO_NAMES, NO_AMOUNTS, null, null, 0, NO_NAMES, NO_AMOUNTS, 0.0));
    assertTrue(e.getMessage().contains("absent.fasta"), e.getMessage());
  }
}
