package se.raddo.raddose3D;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link DDMBfactor}, added in commit 85a2a0a (2022) and never
 * covered. The other three diffraction decay models each have tests; this one
 * had none, so these mirror the patterns in {@link DDMTests}.
 * <p>
 * The model is
 * {@code weight = exp(-(dose * b0 / beta) / 2 * (1 / gamma)^2)}.
 */
public class DDMBfactorTest {

  private static final double TOL = 1e-12;

  private static DDM ddm(double gamma, double b0, double beta) {
    return new DDMBfactor(gamma, b0, beta);
  }

  /** Reference implementation of the documented formula. */
  private static double expected(double dose, double gamma, double b0,
      double beta) {
    return Math.exp((-(dose * b0 / beta) / 2) * Math.pow(1 / gamma, 2));
  }

  @Test
  public void zeroDoseGivesFullDiffractionPower() {
    assertEquals(1.0, ddm(2, 3, 4).calcDecay(0), TOL,
        "an unexposed crystal should retain all diffraction power");
  }

  @ParameterizedTest
  @ValueSource(doubles = {0.001, 0.1, 1, 5, 20, 100, 1000})
  public void decayStaysWithinZeroAndOne(double dose) {
    double decay = ddm(2, 3, 4).calcDecay(dose);
    assertTrue(decay >= 0, "decay went negative at dose " + dose + ": " + decay);
    assertTrue(decay <= 1, "decay exceeded 1 at dose " + dose + ": " + decay);
  }

  @Test
  public void decayDecreasesMonotonicallyWithDose() {
    DDM d = ddm(2, 3, 4);
    double previous = d.calcDecay(0);
    for (double dose = 0.5; dose <= 200; dose += 0.5) {
      double decay = d.calcDecay(dose);
      assertTrue(decay < previous,
          "decay did not decrease at dose " + dose
              + " (" + previous + " -> " + decay + ")");
      previous = decay;
    }
  }

  @Test
  public void decayTendsToZeroAtVeryHighDose() {
    assertEquals(0.0, ddm(2, 3, 4).calcDecay(1e9), 1e-12);
  }

  @ParameterizedTest
  @ValueSource(doubles = {0.5, 1, 2, 10})
  public void matchesTheDocumentedFormula(double dose) {
    assertEquals(expected(dose, 2, 3, 4), ddm(2, 3, 4).calcDecay(dose), TOL);
  }

  /** A larger B0 means faster decay; a larger beta or gamma means slower. */
  @Test
  public void parametersActInTheExpectedDirection() {
    double dose = 5;
    assertTrue(ddm(2, 6, 4).calcDecay(dose) < ddm(2, 3, 4).calcDecay(dose),
        "doubling b0 should decay faster");
    assertTrue(ddm(2, 3, 8).calcDecay(dose) > ddm(2, 3, 4).calcDecay(dose),
        "doubling beta should decay more slowly");
    assertTrue(ddm(4, 3, 4).calcDecay(dose) > ddm(2, 3, 4).calcDecay(dose),
        "doubling gamma should decay more slowly");
  }

  /**
   * The constructor's fallback: if ANY of the three parameters is null, all
   * three are set to 9 -- including the two that were supplied. That is what
   * the code does (DDMBfactor.java:37-43); pinning it here so a change is
   * visible.
   */
  @Test
  public void aSingleNullParameterResetsAllThreeToNine() {
    DDM allNine = ddm(9, 9, 9);
    double dose = 3.5;

    assertEquals(allNine.calcDecay(dose),
        new DDMBfactor(null, 3.0, 4.0).calcDecay(dose), TOL,
        "null gamma should reset b0 and beta to 9 as well");
    assertEquals(allNine.calcDecay(dose),
        new DDMBfactor(2.0, null, 4.0).calcDecay(dose), TOL,
        "null b0 should reset gamma and beta to 9 as well");
    assertEquals(allNine.calcDecay(dose),
        new DDMBfactor(2.0, 3.0, null).calcDecay(dose), TOL,
        "null beta should reset gamma and b0 to 9 as well");
  }

  @Test
  public void describesItself() {
    assertEquals("Bfactor DDM.", ddm(2, 3, 4).toString());
  }
}
