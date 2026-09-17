package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Guards commit ac63bc6, "fix: Throw error in SAXS coefCalc if Mw &lt;= 0".
 * <p>
 * The monomer molecular weight is built from the residue counts
 * ({@code 110*res + 327*DNA + 339.5*RNA + 180*carb}). If every count is zero
 * the weight is zero and the subsequent {@code concentration / weight} divides
 * by zero, silently producing an infinite molarity. The guards at
 * CoefCalcSAXS.java:155 and CoefCalcFromSequenceSAXS.java:170 turn that into an
 * explicit IllegalArgumentException.
 */
public class CoefCalcSaxsGuardTest {

  private static final List<String> NO_NAMES = new ArrayList<String>();
  private static final List<Double> NO_NUMS = new ArrayList<Double>();

  /**
   * @param numResidues protein residues per monomer
   * @param numRNA      RNA residues
   * @param numDNA      DNA residues
   * @param numCarb     carbohydrate residues
   */
  private static CoefCalcSAXS saxs(int numResidues, int numRNA, int numDNA,
      int numCarb) {
    return new CoefCalcSAXS(
        100.0, 100.0, 100.0, 90.0, 90.0, 90.0,
        numResidues, numRNA, numDNA,
        NO_NAMES, NO_NUMS, NO_NAMES, NO_NUMS,
        0.5,        // solvent fraction
        10.0,       // protein concentration, g/L
        null, null, null, null,
        numCarb,
        NO_NAMES, NO_NUMS, 0.0);
  }

  @Test
  public void zeroMolecularWeightIsRejected() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> saxs(0, 0, 0, 0),
        "a monomer with no residues at all has zero molecular weight and must "
            + "not be accepted");
    assertTrue(e.getMessage().contains("positive monomer molecular weight"),
        "unexpected message: " + e.getMessage());
  }

  @Test
  public void proteinResiduesAloneAreEnough() {
    assertDoesNotThrow(() -> saxs(100, 0, 0, 0));
  }

  @Test
  public void rnaAloneIsEnough() {
    assertDoesNotThrow(() -> saxs(0, 50, 0, 0));
  }

  @Test
  public void dnaAloneIsEnough() {
    assertDoesNotThrow(() -> saxs(0, 0, 50, 0));
  }

  @Test
  public void carbohydrateAloneIsEnough() {
    assertDoesNotThrow(() -> saxs(0, 0, 0, 50));
  }
}
