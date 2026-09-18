package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Checks that a SAXS monomer count weighs each kind of residue with its own
 * average mass.
 * <p>
 * {@code CoefCalcSAXS.calculateNumMonomers} was declared
 * {@code (residues, DNA, RNA)} and called {@code (residues, RNA, DNA)}, so
 * each nucleic acid was multiplied by the other's average residue mass -- RNA
 * by 327.0 and DNA by 339.5, when the constants say the opposite. The monomer
 * count derived from it fixes the whole composition, so every quantity
 * downstream was wrong for any SAXS sample containing nucleic acid.
 * <p>
 * These tests are written against the public behaviour ({@code getNumMonomers}
 * after construction) rather than the private method, so they keep working if
 * the internals are rearranged.
 */
public class CoefCalcSaxsMonomerTest {

  /** Empty heavy-atom lists, which every constructor here needs. */
  private static final List<String> NO_NAMES  = new ArrayList<String>();
  /** Empty heavy-atom amounts. */
  private static final List<Double> NO_AMOUNTS = new ArrayList<Double>();

  /** Protein concentration in g/l, the same for every case here. */
  private static final double CONCENTRATION = 10.0;

  /** Residues of whichever polymer a case uses. */
  private static final int RESIDUES = 60;

  /** Average masses, duplicated from CoefCalcSAXS, which keeps them private. */
  private static final double AVG_RNA_MASS = 339.5;
  /** @see #AVG_RNA_MASS */
  private static final double AVG_DNA_MASS = 327.0;

  /** Avogadro's number, as CoefCalcCompute spells it. */
  private static final double AVOGADRO = 6.022e23;

  /** Cubic Angstroms to litres. */
  private static final double ANGSTROM_CUBED_TO_LITRE = 1e-27;

  /**
   * Builds a SAXS coefficient calculator with the default 1000 Angstrom cell.
   *
   * @param numResidues amino acid residues per monomer
   * @param numRNA RNA residues per monomer
   * @param numDNA DNA residues per monomer
   * @return the constructed calculator
   */
  private static CoefCalcSAXS saxs(final int numResidues, final int numRNA,
      final int numDNA) {
    return new CoefCalcSAXS(null, null, null, null, null, null,
        numResidues, numRNA, numDNA,
        NO_NAMES, NO_AMOUNTS, NO_NAMES, NO_AMOUNTS,
        null, CONCENTRATION, NO_NAMES, NO_AMOUNTS,
        null, null, 0, NO_NAMES, NO_AMOUNTS, 0.0);
  }

  /**
   * The expected monomer count for a monomer of the given molecular weight,
   * computed independently of CoefCalcSAXS.
   *
   * @param molecularWeight monomer mass in daltons
   * @return monomers in the default 1000 Angstrom cubed cell
   */
  private static int expectedMonomers(final double molecularWeight) {
    double cellVolume = 1000.0 * 1000.0 * 1000.0;
    double molarity = CONCENTRATION / molecularWeight;
    double volumeLitres = ANGSTROM_CUBED_TO_LITRE * cellVolume;
    return (int) Math.round(molarity * volumeLitres * AVOGADRO);
  }

  /**
   * An RNA sample must be weighed with the RNA mass.
   * <p>
   * Before the fix this gave 307, the count for the DNA mass.
   */
  @Test
  public void rnaUsesTheRnaMass() {
    assertEquals(expectedMonomers(AVG_RNA_MASS * RESIDUES),
        saxs(0, RESIDUES, 0).getNumMonomers(),
        "RNA monomer count should follow from the RNA residue mass");
  }

  /**
   * A DNA sample must be weighed with the DNA mass.
   * <p>
   * Before the fix this gave 296, the count for the RNA mass.
   */
  @Test
  public void dnaUsesTheDnaMass() {
    assertEquals(expectedMonomers(AVG_DNA_MASS * RESIDUES),
        saxs(0, 0, RESIDUES).getNumMonomers(),
        "DNA monomer count should follow from the DNA residue mass");
  }

  /**
   * The symptom that made the swap visible without knowing either constant:
   * RNA is the heavier residue, so a gram of it contains fewer molecules.
   * Before the fix the inequality ran the other way.
   */
  @Test
  public void theHeavierResidueGivesFewerMonomers() {
    int rnaMonomers = saxs(0, RESIDUES, 0).getNumMonomers();
    int dnaMonomers = saxs(0, 0, RESIDUES).getNumMonomers();

    assertTrue(AVG_RNA_MASS > AVG_DNA_MASS,
        "this test assumes RNA is the heavier residue");
    assertTrue(rnaMonomers < dnaMonomers,
        "RNA is heavier than DNA, so the same mass of it must contain fewer "
            + "molecules -- got " + rnaMonomers + " RNA against " + dnaMonomers
            + " DNA");
  }

  /**
   * Protein is unaffected either way, so it pins the rest of the calculation
   * while the nucleic acid tests move.
   */
  @Test
  public void proteinIsUnaffected() {
    assertEquals(expectedMonomers(110.0 * 129),
        saxs(129, 0, 0).getNumMonomers(),
        "protein monomer count should follow from the amino acid mass");
  }

  /**
   * Mixing the two adds both masses rather than double-counting either.
   */
  @Test
  public void mixedSampleAddsBothMasses() {
    assertEquals(
        expectedMonomers(AVG_RNA_MASS * RESIDUES + AVG_DNA_MASS * RESIDUES),
        saxs(0, RESIDUES, RESIDUES).getNumMonomers(),
        "a mixed sample should weigh each polymer with its own mass");
  }
}
