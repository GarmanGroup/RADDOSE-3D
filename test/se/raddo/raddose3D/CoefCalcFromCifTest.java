package se.raddo.raddose3D;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for reading a small-molecule CIF.
 * <p>
 * This class previously had no coverage, and
 * {@code parseChemicalFormula} crashed on a large fraction of real CIFs: it
 * indexed {@code elements.charAt(1)} unconditionally when deciding whether an
 * element symbol is one or two letters, so any formula whose final token is a
 * bare single-letter symbol threw ArrayIndexOutOfBounds. Urea,
 * {@code 'C H4 N2 O'}, is the textbook small-molecule example and was affected.
 * <p>
 * Only the final token can be that short -- every earlier one is followed by a
 * space, which is not a letter and so takes the one-letter branch.
 * <p>
 * Note the fixtures here always supply {@code _chemical_formula_sum}: without
 * it {@code readCIFFile} calls {@code System.exit(0)}, which would take the
 * test runner down with it.
 */
public class CoefCalcFromCifTest {

  /** Writes a minimal CIF containing just what the parser reads. */
  private static Path writeCif(final Path dir, final String formula,
      final double cellVolume) throws IOException {
    String text = "data_test\n"
        + "_chemical_formula_sum            '" + formula + "'\n"
        + "_cell_volume                     " + cellVolume + "\n";
    Path path = dir.resolve("test.cif");
    Files.write(path, text.getBytes(StandardCharsets.UTF_8));
    return path;
  }

  private static double occurrenceOf(final CoefCalcFromCIF coefCalc,
      final String symbol) {
    Element element = coefCalc.getParser().getElement(symbol.toUpperCase());
    assertTrue(element != null, "unknown element symbol in test: " + symbol);
    return coefCalc.getMacromolecularOccurrence(element);
  }

  /**
   * A formula ending in a bare single-letter symbol must parse.
   * This is the regression: every one of these used to throw.
   */
  @ParameterizedTest
  @CsvSource({
      "C H4 N2 O,   O, 1",
      "C4 H16 Ca N8 O8 S, S, 1",
      "H2 O,        O, 1",
      "Na Cl,       Cl, 1",
      "C6 H12 O6,   O, 6",
  })
  public void formulaEndingInASingleLetterSymbolParses(final String formula,
      final String lastSymbol, final double expected, @TempDir final Path dir)
      throws IOException {
    CoefCalcFromCIF coefCalc =
        new CoefCalcFromCIF(writeCif(dir, formula, 1000.0).toString());

    assertEquals(expected, occurrenceOf(coefCalc, lastSymbol), 1e-12,
        "wrong count for the final element of '" + formula + "'");
  }

  /** Urea, element by element. */
  @Test
  public void ureaIsParsedCorrectly(@TempDir final Path dir) throws IOException {
    CoefCalcFromCIF coefCalc =
        new CoefCalcFromCIF(writeCif(dir, "C H4 N2 O", 145.9).toString());

    assertEquals(1.0, occurrenceOf(coefCalc, "C"), 1e-12, "carbon");
    assertEquals(4.0, occurrenceOf(coefCalc, "H"), 1e-12, "hydrogen");
    assertEquals(2.0, occurrenceOf(coefCalc, "N"), 1e-12, "nitrogen");
    assertEquals(1.0, occurrenceOf(coefCalc, "O"), 1e-12, "oxygen");
  }

  /** Two-letter symbols must not be split, wherever they appear. */
  @Test
  public void twoLetterSymbolsAreRecognised(@TempDir final Path dir)
      throws IOException {
    CoefCalcFromCIF coefCalc = new CoefCalcFromCIF(
        writeCif(dir, "C2 H8 F4 N4 O3 Sb2", 2076.9).toString());

    assertEquals(2.0, occurrenceOf(coefCalc, "Sb"), 1e-12, "antimony");
    assertEquals(4.0, occurrenceOf(coefCalc, "F"), 1e-12, "fluorine");
    assertEquals(2.0, occurrenceOf(coefCalc, "C"), 1e-12, "carbon");
  }

  /** A two-letter symbol as the final token, the case that always worked. */
  @Test
  public void twoLetterSymbolAtTheEndParses(@TempDir final Path dir)
      throws IOException {
    CoefCalcFromCIF coefCalc =
        new CoefCalcFromCIF(writeCif(dir, "H2 Ca", 500.0).toString());

    assertEquals(1.0, occurrenceOf(coefCalc, "Ca"), 1e-12);
    assertEquals(2.0, occurrenceOf(coefCalc, "H"), 1e-12);
  }

  /** A bare symbol with no count means one atom, not zero. */
  @Test
  public void aBareSymbolMeansOneAtom(@TempDir final Path dir)
      throws IOException {
    CoefCalcFromCIF coefCalc =
        new CoefCalcFromCIF(writeCif(dir, "C H4 N2 O", 145.9).toString());

    assertEquals(1.0, occurrenceOf(coefCalc, "C"), 1e-12,
        "a symbol written without a number means one atom");
  }

  /** The cell volume is read, including the standard-uncertainty form. */
  @Test
  public void cellVolumeIsRead(@TempDir final Path dir) throws IOException {
    CoefCalcFromCIF coefCalc =
        new CoefCalcFromCIF(writeCif(dir, "H2 O", 145.9).toString());
    assertTrue(coefCalc.chemicalSum, "the formula sum should have been seen");
  }

  /** A single element on its own is still a valid formula. */
  @Test
  public void aSingleElementFormulaParses(@TempDir final Path dir)
      throws IOException {
    CoefCalcFromCIF coefCalc =
        new CoefCalcFromCIF(writeCif(dir, "C", 100.0).toString());
    assertEquals(1.0, occurrenceOf(coefCalc, "C"), 1e-12);
  }
}
