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
 * All five were corrected on 2026-09-17. These tests now assert that the terms
 * contribute, so a regression back to integer division would be caught, and
 * {@link #javaIntegerDivisionTruncatesTowardsZero()} keeps the underlying
 * language hazard documented.
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
  public void wkMoleculeIncludesThePlasmonTerm() {
    CoefCalcCompute c = coefCalc();
    Element sulphur = c.getParser().getElement("S");
    assertTrue(sulphur != null, "test needs sulphur in the element database (symbol lookup)");

    double a = 1.0;
    for (int shell = 0; shell < 4; shell++) {
      double bindingOnly = Math.abs(a * c.getShellBindingSubshell(shell, sulphur) * 1000);
      assertTrue(c.getWkMolecule(a, sulphur, shell, false) > bindingOnly,
          "Wk at shell " + shell + " should exceed |a * binding * 1000| ("
              + bindingOnly + "), because the plasmon term is no longer "
              + "multiplied by an integer-divided zero");
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
  public void wkIsSubLinearInAOnceThePlasmonTermContributes() {
    CoefCalcCompute c = coefCalc();
    Element sulphur = c.getParser().getElement("S");

    double single = c.getWkMolecule(1.0, sulphur, 0, false);
    double triple = c.getWkMolecule(3.0, sulphur, 0, false);

    assertTrue(triple < 3 * single,
        "with the plasmon term restored Wk is sub-linear in a: "
            + "sqrt(9x^2 + p) < 3*sqrt(x^2 + p)");
    assertTrue(triple > single, "Wk should still increase with a");
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

    // Kept as a demonstration of the hazard: MicroED.java:240 now uses 4.0/3.0
    // and no longer loses the third.
    assertEquals(correct * 0.75, asCoded, 1e-6,
        "integer (4/3) is 1, i.e. three quarters of the correct 4/3");
  }
}
