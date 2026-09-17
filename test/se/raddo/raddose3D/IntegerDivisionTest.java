package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Characterisation tests for integer division appearing inside floating point
 * physics expressions. See TEST-TRIAGE.md #6.
 * <p>
 * Java evaluates {@code (2/3)} as integer division, giving 0, so any term it
 * multiplies vanishes. Six such expressions exist:
 * <pre>
 *   CoefCalcCompute.java:2384   (1/16) * ((gamma-1)/gamma)^2
 *   CoefCalcCompute.java:2563   (1/8)  * (1 - sqrt(1-beta^2))
 *   CoefCalcCompute.java:3759   (2/3)  * (shells[i]/Z) * plasmaEnergy^2
 *   CoefCalcCompute.java:3877   (2/3)  * ((fk*totNum)/sumZ) * plasmaEnergy^2
 *   MicroED.java:240            (4/3)  * PI * a * b * c
 * </pre>
 * All are in the electron/GOS code, so a standard MX run is unaffected. These
 * tests pin the current behaviour so the change is visible if the constants
 * are corrected to {@code 2.0/3.0} and so on.
 * <p>
 * The tests assert what the code does today. They are deliberately <em>not</em>
 * assertions that the behaviour is right.
 */
public class IntegerDivisionTest {

  /** Insulin-like cell, enough to populate presentElements. */
  private static CoefCalcCompute coefCalc() {
    List<String> protNames = new ArrayList<String>(Arrays.asList("S", "Zn"));
    List<Double> protNums = new ArrayList<Double>(Arrays.asList(6.0, 0.333));
    List<String> solNames = new ArrayList<String>(Arrays.asList("P"));
    List<Double> solNums = new ArrayList<Double>(Arrays.asList(425.));
    List<String> none = new ArrayList<String>();
    List<Double> noneD = new ArrayList<Double>();

    CoefCalcFromParams c = new CoefCalcFromParams(
        78.02, 78.02, 78.02, 90.0, 90.0, 90.0, 24, 51, 0, 0,
        protNames, protNums, solNames, solNums, none, noneD,
        0., null, null, 0, none, noneD, 0, 0);
    c.updateCoefficients(12.1);
    return c;
  }

  /**
   * {@code getWkMolecule} is
   * {@code sqrt((a*binding*1000)^2 + (2/3)*(fk*totNum/sumZ)*plasma^2)}.
   * Because {@code (2/3)} is 0, the plasmon term drops out entirely and the
   * result collapses to {@code a * binding * 1000}.
   * <p>
   * This is why commit 8d37c7a, which widened {@code sumZ} from int to double
   * to avoid a truncation, cannot change any result: the quantity it feeds is
   * multiplied by zero.
   */
  @Test
  public void wkMoleculeCollapsesToTheBindingTermBecauseTwoOverThreeIsZero() {
    CoefCalcCompute c = coefCalc();
    Element sulphur = c.getParser().getElement("S");
    assertTrue(sulphur != null, "test needs sulphur in the element database (symbol lookup)");

    double a = 1.0;
    for (int shell = 0; shell < 4; shell++) {
      double expected = a * c.getShellBindingSubshell(shell, sulphur) * 1000;
      assertEquals(Math.abs(expected),
          c.getWkMolecule(a, sulphur, shell, false), 1e-9,
          "Wk should equal |a * binding * 1000| at shell " + shell
              + "; any difference would mean the plasmon term is contributing, "
              + "i.e. that (2/3) had been corrected to 2.0/3.0");
    }
  }

  /** The plasmon energy really is non-zero, so the term is dropped, not absent. */
  @Test
  public void thePlasmonTermIsNonZeroAndIsBeingDiscarded() {
    CoefCalcCompute c = coefCalc();
    assertTrue(c.getPlasmaEnergyAll(false) > 0,
        "plasma energy should be positive, so zeroing (2/3) really does "
            + "discard a physically meaningful term");
  }

  /** Scaling a scales Wk exactly, which only holds while the other term is 0. */
  @Test
  public void wkScalesLinearlyWithA() {
    CoefCalcCompute c = coefCalc();
    Element sulphur = c.getParser().getElement("S");

    double single = c.getWkMolecule(1.0, sulphur, 0, false);
    double triple = c.getWkMolecule(3.0, sulphur, 0, false);

    assertEquals(3 * single, triple, 1e-9,
        "exact linear scaling in a is only possible because the additive "
            + "plasmon term is zero");
  }

  /** The language-level facts the above depend on. */
  @Test
  public void javaIntegerDivisionTruncatesTowardsZero() {
    assertEquals(0, 2 / 3, "(2/3) is integer division");
    assertEquals(0, 4 / 3 - 1, "(4/3) is 1, so (4/3)*x loses the 1/3");
    assertEquals(0, 1 / 8, "(1/8) is integer division");
    assertEquals(0, 1 / 16, "(1/16) is integer division");
  }

  /**
   * MicroED.java:240 computes a spherical crystal volume as
   * {@code (4/3) * PI * (X/2) * (Y/2) * (Z/2)}. {@code (4/3)} is 1, not 1.333,
   * so the volume is 25% low -- and it is then used to clamp exposedVolume at
   * MicroED.java:1825-1826.
   */
  @Test
  public void sphericalVolumeFormulaLosesTheOneThird() {
    double x = 100, y = 100, z = 100;
    double asCoded = ((4 / 3) * Math.PI * (x / 2) * (y / 2) * (z / 2));
    double correct = ((4 / 3.0) * Math.PI * (x / 2) * (y / 2) * (z / 2));

    assertEquals(correct * 0.75, asCoded, 1e-6,
        "as coded the sphere volume is three quarters of the correct value");
    assertTrue(asCoded < correct, "as-coded volume should be the smaller");
  }
}
