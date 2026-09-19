package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Checks the protein density used to estimate a crystal's solvent fraction.
 * <p>
 * {@code calculateSolventFractionFromNums} converts each kind of residue to a
 * mass, divides by an assumed density to get the volume it occupies, and
 * calls the remainder solvent. The protein density was a flat 1.35 g/cm^3,
 * sitting directly above a commented-out copy of the Fischer et al. (2004)
 * formula and a comment saying to use it.
 * <p>
 * Fischer et al. (2004), Protein Science <b>13</b>, 2825-2828, give
 * rho = 1.410 + 0.145 exp(-M/13), with M the molecular weight of one molecule
 * in kDa. Small proteins pack less efficiently and are correspondingly
 * denser, which one constant cannot express.
 * <p>
 * Nothing covered this path before: the golden-file test specifies
 * {@code SolventFraction} explicitly and so never reaches it.
 */
public class ProteinDensityTest {

  /** Empty heavy-atom lists, which the constructor requires. */
  private static final List<String> NO_NAMES = new ArrayList<String>();
  /** Empty heavy-atom amounts. */
  private static final List<Double> NO_AMOUNTS = new ArrayList<Double>();

  /** Atomic mass unit in grams, as CoefCalcCompute spells it. */
  private static final double ATOMIC_MASS_UNIT = 1.66E-24;
  /** Average residue mass in daltons. */
  private static final double AMINO_ACID_AVE_MASS = 110.0;
  /** Cubic Angstroms to millilitres. */
  private static final double ANGSTROMS_TO_ML = 1E-24;

  /**
   * Builds a calculator with no solvent fraction given, so it computes one.
   *
   * @param edge cubic cell edge in Angstroms
   * @param monomers copies per unit cell
   * @param residues amino acids per monomer
   * @return the constructed calculator
   */
  private static CoefCalcFromParams protein(final double edge,
      final int monomers, final int residues) {
    return new CoefCalcFromParams(edge, edge, edge, 90.0, 90.0, 90.0,
        monomers, residues, 0, 0, NO_NAMES, NO_AMOUNTS, NO_NAMES, NO_AMOUNTS,
        NO_NAMES, NO_AMOUNTS, null, null, null, 0, NO_NAMES, NO_AMOUNTS, 0.0,
        0L);
  }

  /**
   * The Fischer density for a protein of the given size, computed here
   * rather than read from the class under test.
   *
   * @param residues amino acids in one molecule
   * @return density in g/cm^3
   */
  private static double fischerDensity(final int residues) {
    double molecularWeightKDa = AMINO_ACID_AVE_MASS * residues / 1000.0;
    return 1.410 + 0.145 * Math.exp(-molecularWeightKDa / 13.0);
  }

  /**
   * The solvent fraction that follows from a given protein density.
   *
   * @param edge cubic cell edge in Angstroms
   * @param monomers copies per unit cell
   * @param residues amino acids per monomer
   * @param density protein density in g/cm^3
   * @return expected solvent fraction
   */
  private static double solventFractionFor(final double edge,
      final int monomers, final int residues, final double density) {
    double cellVolume = edge * edge * edge;
    double proteinMass =
        ATOMIC_MASS_UNIT * AMINO_ACID_AVE_MASS * residues * monomers;
    return 1 - proteinMass / (cellVolume * density * ANGSTROMS_TO_ML);
  }

  /**
   * The solvent fraction follows from the Fischer density, checked against an
   * expectation computed independently of the class under test.
   */
  @Test
  public void solventFractionUsesTheFischerDensity() {
    double expected = solventFractionFor(78.27, 8, 129, fischerDensity(129));

    assertEquals(expected, protein(78.27, 8, 129).getSolventFraction(), 1e-12,
        "solvent fraction should follow from rho = 1.410 + 0.145 exp(-M/13)");
  }

  /**
   * The value that changed, recorded so the size of the change is on the
   * record rather than inferred.
   */
  @Test
  public void theFlatDensityGaveADifferentAnswer() {
    double withFischer = protein(78.27, 8, 129).getSolventFraction();
    double withFlat = solventFractionFor(78.27, 8, 129, 1.35);

    assertEquals(0.73057648459110820, withFischer, 1e-12,
        "Fischer density for a 129-residue protein in a 78.27 A cube");
    assertEquals(0.70888760279645570, withFlat, 1e-12,
        "the superseded flat 1.35 g/cm^3 value");
    assertTrue(withFischer > withFlat,
        "a denser protein occupies less of the cell, so more of it is "
            + "solvent");
  }

  /**
   * The point of the formula: a small protein is denser than a large one. A
   * single constant cannot express this, which is why the constant was wrong
   * for everything except one particular size.
   */
  @Test
  public void smallerProteinsAreDenser() {
    assertTrue(fischerDensity(50) > fischerDensity(500),
        "the Fischer density decreases with molecular weight");

    // Same cell and same total residue count, so the only difference is how
    // that mass is divided into molecules -- and therefore the density.
    double manySmall = protein(78.27, 16, 50).getSolventFraction();
    double fewLarge = protein(78.27, 2, 400).getSolventFraction();
    double sameMassSmall = solventFractionFor(78.27, 16, 50,
        fischerDensity(50));
    double sameMassLarge = solventFractionFor(78.27, 2, 400,
        fischerDensity(400));

    assertEquals(sameMassSmall, manySmall, 1e-12, "many small molecules");
    assertEquals(sameMassLarge, fewLarge, 1e-12, "a few large ones");
  }

  /** A very large protein approaches the asymptote, 1.410 g/cm^3. */
  @Test
  public void largeProteinsApproachTheLimitingDensity() {
    assertEquals(1.410, fischerDensity(100000), 1e-6,
        "the exponential term vanishes for a large molecule");
  }

  /**
   * An empty cell is all solvent whatever the density formula says, so the
   * change cannot have introduced a discontinuity at zero.
   */
  @Test
  public void anEmptyCellIsAllSolvent() {
    assertEquals(1.0, protein(78.27, 0, 0).getSolventFraction(), 1e-12,
        "no protein means no protein volume");
  }
}
