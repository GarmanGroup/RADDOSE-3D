package se.raddo.raddose3D;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Checks that a CIF's contents are scaled by the number of formula units in
 * the unit cell.
 * <p>
 * {@code _chemical_formula_sum} states the contents of one formula unit, not
 * of the cell. {@code _cell_formula_units_Z} says how many of them the cell
 * holds. Until Z was read, the cell was filled with a single formula unit
 * however many it actually held, so the density -- and with it every
 * absorption coefficient and every dose computed through
 * {@code AbsCoefCalc EXPSM} -- came out low by exactly that factor. Z is
 * typically 2 to 8 for small molecules.
 * <p>
 * The interesting tests here compare against the crystal density stated in
 * the CIF itself, which RADDOSE-3D does not read. That makes them evidence
 * that the calculation is right, rather than merely self-consistent.
 */
public class CoefCalcCifFormulaUnitsTest {

  /** Tolerance on density, in g/cm^3, against published values. */
  private static final double DENSITY_TOLERANCE = 0.02;

  /**
   * Writes a minimal CIF containing only the items the parser reads.
   *
   * @param dir temporary directory to write into
   * @param formula the {@code _chemical_formula_sum} value
   * @param cellVolume cell volume in cubic Angstroms
   * @param zLine a complete {@code _cell_formula_units_Z} line, or null to
   *          leave the item out entirely
   * @return path to the written file
   * @throws IOException if the file cannot be written
   */
  private static Path writeCif(final Path dir, final String formula,
      final double cellVolume, final String zLine) throws IOException {
    StringBuilder text = new StringBuilder("data_test\n");
    text.append("_chemical_formula_sum            '").append(formula)
        .append("'\n");
    text.append("_cell_volume                     ").append(cellVolume)
        .append("\n");
    if (zLine != null) {
      text.append(zLine).append("\n");
    }
    Files.createDirectories(dir);
    Path path = dir.resolve("test.cif");
    Files.write(path, text.toString().getBytes(StandardCharsets.UTF_8));
    return path;
  }

  /**
   * Atoms of one element in the cell.
   *
   * @param coefCalc the calculator to inspect
   * @param symbol chemical symbol
   * @return occurrence per unit cell
   */
  private static double occurrenceOf(final CoefCalcFromCIF coefCalc,
      final String symbol) {
    Element element = coefCalc.getParser().getElement(symbol.toUpperCase());
    assertTrue(element != null, "unknown element symbol in test: " + symbol);
    return coefCalc.getMacromolecularOccurrence(element);
  }

  /**
   * Z multiplies every count taken from the formula.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void formulaUnitsMultiplyTheCellContents(@TempDir final Path dir)
      throws IOException {
    Path cif = writeCif(dir, "C H4 N2 O", 145.9,
        "_cell_formula_units_Z            2");
    CoefCalcFromCIF coefCalc = new CoefCalcFromCIF(cif.toString());

    assertEquals(2.0, occurrenceOf(coefCalc, "C"), 1e-12, "carbon");
    assertEquals(8.0, occurrenceOf(coefCalc, "H"), 1e-12, "hydrogen");
    assertEquals(4.0, occurrenceOf(coefCalc, "N"), 1e-12, "nitrogen");
    assertEquals(2.0, occurrenceOf(coefCalc, "O"), 1e-12, "oxygen");
  }

  /**
   * Urea's own CIF states a diffraction density of 1.367 g/cm^3. With Z = 2
   * the calculation reproduces it; without, it gives half.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void ureaDensityMatchesTheValueInItsCif(@TempDir final Path dir)
      throws IOException {
    // COD entry 1008775.
    Path cif = writeCif(dir, "C H4 N2 O", 145.9,
        "_cell_formula_units_Z            2");
    double density = new CoefCalcFromCIF(cif.toString()).getDensity();

    assertEquals(1.367, density, DENSITY_TOLERANCE,
        "urea density should match _exptl_crystal_density_diffrn");
  }

  /**
   * A second structure, with a different Z and a heavy element, so the check
   * is not a coincidence of one formula.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void antimonateDensityMatchesTheValueInItsCif(@TempDir final Path dir)
      throws IOException {
    // COD entry 1008123, _exptl_crystal_density_meas 2.9.
    Path cif = writeCif(dir, "C2 H8 F4 N4 O3 Sb2", 2076.9,
        "_cell_formula_units_Z            8");
    double density = new CoefCalcFromCIF(cif.toString()).getDensity();

    assertEquals(2.9, density, 0.05,
        "density should match _exptl_crystal_density_meas");
  }

  /**
   * Without Z the density is low by exactly that factor. Stated as a test so
   * that the size of the old error is on the record, and so that anyone
   * tempted to drop the multiplication sees what it costs.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void omittingFormulaUnitsUnderstatesDensityByThatFactor(
      @TempDir final Path dir) throws IOException {
    Path withZ = writeCif(dir, "C H4 N2 O", 145.9,
        "_cell_formula_units_Z            2");
    double withZDensity = new CoefCalcFromCIF(withZ.toString()).getDensity();

    Path withoutZ = writeCif(dir.resolve("sub"), "C H4 N2 O", 145.9, null);
    double withoutZDensity =
        new CoefCalcFromCIF(withoutZ.toString()).getDensity();

    assertEquals(2.0, withZDensity / withoutZDensity, 1e-12,
        "stating Z = 2 should exactly double the density");
  }

  /**
   * A CIF with no Z keeps the old behaviour of one formula unit per cell,
   * which is also the correct reading when the item is genuinely absent.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void absentFormulaUnitsMeansOne(@TempDir final Path dir)
      throws IOException {
    Path cif = writeCif(dir, "C H4 N2 O", 145.9, null);
    CoefCalcFromCIF coefCalc = new CoefCalcFromCIF(cif.toString());

    assertEquals(1.0, occurrenceOf(coefCalc, "C"), 1e-12,
        "one formula unit when Z is not stated");
  }

  /**
   * CIF items may appear in any order, and Z is commonly written above the
   * formula. Applying it as each formula token is parsed would have made the
   * result depend on that order.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void formulaUnitsMayPrecedeTheFormula(@TempDir final Path dir)
      throws IOException {
    String text = "data_test\n"
        + "_cell_formula_units_Z            4\n"
        + "_chemical_formula_sum            'C H4 N2 O'\n"
        + "_cell_volume                     145.9\n";
    Path path = dir.resolve("z_first.cif");
    Files.write(path, text.getBytes(StandardCharsets.UTF_8));

    assertEquals(4.0, occurrenceOf(new CoefCalcFromCIF(path.toString()), "C"),
        1e-12, "Z should apply whichever side of the formula it is stated");
  }

  /**
   * Z is defined as an integer. A value that is not a positive whole number
   * is refused and the cell falls back to one formula unit, rather than
   * silently scaling by a fraction.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void nonsensicalFormulaUnitsFallBackToOne(@TempDir final Path dir)
      throws IOException {
    String[] bad = {"0", "-2", "4.5", "many"};

    for (int i = 0; i < bad.length; i++) {
      Path cif = writeCif(dir.resolve("bad" + i), "C H4 N2 O", 145.9,
          "_cell_formula_units_Z            " + bad[i]);
      assertEquals(1.0, occurrenceOf(new CoefCalcFromCIF(cif.toString()), "C"),
          1e-12, "Z of '" + bad[i] + "' should fall back to one formula unit");
    }
  }

  /**
   * A Z written as a whole number with a decimal point is still a whole
   * number of formula units.
   *
   * @param dir temporary directory
   * @throws IOException if the fixture cannot be written
   */
  @Test
  public void formulaUnitsMayBeWrittenWithADecimalPoint(
      @TempDir final Path dir) throws IOException {
    Path cif = writeCif(dir, "C H4 N2 O", 145.9,
        "_cell_formula_units_Z            4.0");
    assertEquals(4.0, occurrenceOf(new CoefCalcFromCIF(cif.toString()), "C"),
        1e-12, "4.0 is four formula units");
  }
}
