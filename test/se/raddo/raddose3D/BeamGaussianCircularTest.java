package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the circular/elliptical collimation path in {@link BeamGaussian},
 * rewritten in commit d2fbcf1 ("circular beam now uses more accurate cartesian
 * approach").
 * <p>
 * That commit replaced a 100-step polar trapezoid with a 1000-step midpoint
 * rule in x. The result becomes {@code normFactor}, which divides into
 * {@code scaleFactor} and therefore scales <em>every</em> value
 * {@code beamIntensity} returns, so an error here silently rescales all doses.
 * <p>
 * {@code normFactor} and {@code bivariateGaussianVolume} are both private, but
 * normFactor is exactly recoverable through the public API. At the origin the
 * profile {@code gX.value(0) * gY.value(0)} is a product of two normalised
 * commons-math3 Gaussians, i.e. {@code 1 / (2 pi sigmaX sigmaY)}, so
 * {@code beamIntensity(0,0,0) == KEVTOJOULES * energy * flux
 * / (normFactor * 2 pi sigmaX sigmaY)}.
 * <p>
 * No golden numbers are needed: for a circular aperture of radius r with
 * sigmaX == sigmaY == sigma, the enclosed volume of a normalised bivariate
 * Gaussian has the closed form 1 - exp(-r^2 / 2 sigma^2) (the Rayleigh CDF).
 * <p>
 * <strong>These tests pass against the implementation d2fbcf1 replaced, so
 * they are a correctness pin rather than a regression guard for it.</strong>
 * Measuring both against the closed form (and, for elliptical apertures,
 * against a 2,000,001-point Simpson reference) shows the rewrite made the
 * result <em>less</em> accurate, not more, contrary to the commit message:
 * <pre>
 *                        circular (sx==sy)   elliptical (20x60, ap 25x70)
 *   old polar trapezoid   ~1e-16             ~5e-11
 *   new cartesian midpoint ~3.6e-6           ~3.3e-6
 * </pre>
 * The old code integrated the radial direction analytically, so it was near
 * exact; the new code is a 1000-step quadrature. It is also no longer
 * symmetric under swapping the x and y axes (~3.5e-7 apart, hence the 1e-5
 * tolerance in {@link #ellipticalIntegrationIsSymmetricInXAndY()}).
 * <p>
 * The absolute error is small -- roughly 7e-6 relative, i.e. under 0.001% on
 * dose -- so this is a precision regression, not a correctness one. Recorded
 * in TEST-TRIAGE.md #5. The tolerances below are set to today's behaviour so
 * that any further degradation shows up.
 */
public class BeamGaussianCircularTest {

  private static final double SIGMA_TO_FWHM = 2 * Math.sqrt(2 * Math.log(2));
  private static final double ENERGY = 12.1;
  private static final double FLUX = 2e12;

  /**
   * @param sigmaX  beam sigma in x, um
   * @param sigmaY  beam sigma in y, um
   * @param collX   full collimation width in x, um (= 2 * semi-axis)
   * @param collY   full collimation width in y, um
   * @param circular whether to request circular collimation
   */
  private static BeamGaussian beam(double sigmaX, double sigmaY,
      double collX, double collY, boolean circular) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_FWHM_X, sigmaX * SIGMA_TO_FWHM);
    p.put(Beam.BEAM_FWHM_Y, sigmaY * SIGMA_TO_FWHM);
    p.put(Beam.BEAM_ENERGY, ENERGY);
    p.put(Beam.BEAM_FLUX, FLUX);
    p.put(Beam.BEAM_COLL_H, collX);
    p.put(Beam.BEAM_COLL_V, collY);
    if (circular) {
      // NOTE: BeamGaussian compares this with ==, not equals(), so it must be
      // the interned literal. See circularFlagIsComparedByReference below.
      p.put(Beam.BEAM_CIRCULAR, "TRUE");
    }
    BeamGaussian b = new BeamGaussian(p);
    // Required: attenuatedPhotonsPerSec is not initialised by the constructor,
    // so a freshly built beam reports zero intensity everywhere until this is
    // called. Crystal.expose() does exactly this at Crystal.java:869.
    b.applyContainerAttenuation(new ContainerTransparent());
    return b;
  }

  /** Recovers the private normFactor through the public API. */
  private static double normFactor(BeamGaussian b, double sigmaX, double sigmaY) {
    double peakProfile = 1.0 / (2 * Math.PI * sigmaX * sigmaY);
    return Beam.KEVTOJOULES * ENERGY * FLUX * peakProfile
        / b.beamIntensity(0, 0, 0);
  }

  /** Builds a circular/rectangular beam and returns its normFactor. */
  private static double normFactor(double sigmaX, double sigmaY,
      double collX, double collY, boolean circular) {
    return normFactor(beam(sigmaX, sigmaY, collX, collY, circular),
        sigmaX, sigmaY);
  }

  /**
   * The headline property of the rewritten integrator: for a circular aperture
   * on a radially symmetric Gaussian the answer is known in closed form.
   */
  @ParameterizedTest
  @ValueSource(doubles = {0.25, 0.5, 1.0, 1.5, 2.0, 3.0, 5.0})
  public void circularNormFactorMatchesTheRayleighClosedForm(double rOverSigma) {
    double sigma = 20.0;
    double r = rOverSigma * sigma;

    double expected = 1 - Math.exp(-(r * r) / (2 * sigma * sigma));
    double actual = normFactor(sigma, sigma, 2 * r, 2 * r, true);

    // 1e-5: the integrator is a 1000-step midpoint rule, whose error against
    // the exact Rayleigh CDF peaks around 3.6e-6 near r = 1.5 sigma. Tight
    // enough to catch a wrong integrator, loose enough not to pin quadrature
    // noise.
    assertEquals(expected, actual, 1e-5,
        "circular normFactor at r = " + rOverSigma + " sigma");
  }

  /** An aperture much wider than the beam captures essentially all of it. */
  @Test
  public void aWideApertureCapturesTheWholeBeam() {
    double sigma = 20.0;
    assertEquals(1.0, normFactor(sigma, sigma, 20 * sigma, 20 * sigma, true),
        1e-6, "a 10-sigma-radius aperture should capture the whole beam");
  }

  /** A vanishingly small aperture captures essentially none of it. */
  @Test
  public void aTinyApertureCapturesAlmostNothing() {
    double sigma = 20.0;
    double n = normFactor(sigma, sigma, 0.02 * sigma, 0.02 * sigma, true);
    assertTrue(n > 0, "normFactor must stay positive, was " + n);
    assertTrue(n < 1e-3, "a 0.01-sigma aperture should capture ~nothing, was " + n);
  }

  /**
   * An ellipse is strictly contained in the rectangle with the same half-axes,
   * so it must enclose strictly less of the beam. This holds regardless of the
   * quadrature scheme, so it is a genuine invariant of the rewrite.
   */
  @ParameterizedTest
  @ValueSource(doubles = {0.5, 1.0, 2.0, 4.0})
  public void circularCapturesLessThanRectangular(double rOverSigma) {
    double sigma = 20.0;
    double coll = 2 * rOverSigma * sigma;

    double circular = normFactor(sigma, sigma, coll, coll, true);
    double rectangular = normFactor(sigma, sigma, coll, coll, false);

    assertTrue(circular < rectangular,
        "ellipse should enclose less than its bounding rectangle "
            + "(circular=" + circular + ", rectangular=" + rectangular + ")");
  }

  /** The same must hold for a genuinely elliptical (sx != sy) aperture. */
  @Test
  public void ellipticalCapturesLessThanRectangularWhenAxesDiffer() {
    double circular = normFactor(20, 60, 50, 140, true);
    double rectangular = normFactor(20, 60, 50, 140, false);
    assertTrue(circular < rectangular,
        "circular=" + circular + ", rectangular=" + rectangular);
  }

  /**
   * An elliptical aperture with equal semi-axes and equal sigmas must give the
   * same answer whichever axis is nominally "larger" -- i.e. the integrator is
   * symmetric in x and y.
   */
  @Test
  public void ellipticalIntegrationIsSymmetricInXAndY() {
    // 1e-5 rather than exact: the integrator is a 1000-step midpoint rule in
    // x, so swapping the axes changes which direction is sampled discretely.
    assertEquals(normFactor(20, 40, 60, 100, true),
                 normFactor(40, 20, 100, 60, true),
                 1e-5, "swapping x and y should not change the enclosed volume");
  }

  /**
   * Enclosed volume must grow with aperture size, and saturate at 1 rather
   * than overshooting it.
   */
  @Test
  public void normFactorIncreasesWithApertureRadius() {
    double sigma = 20.0;
    double previous = 0;
    for (double r = 5; r <= 200; r += 5) {
      double n = normFactor(sigma, sigma, 2 * r, 2 * r, true);

      // Strictly increasing while there is still beam left to capture; once
      // it has saturated at 1 only quadrature noise remains, so require
      // non-decreasing within that noise.
      if (previous < 0.999) {
        assertTrue(n > previous,
            "normFactor decreased between r=" + (r - 5) + " and r=" + r
                + " (" + previous + " -> " + n + ")");
      } else {
        assertTrue(n >= previous - 1e-7,
            "normFactor dropped after saturating between r=" + (r - 5)
                + " and r=" + r + " (" + previous + " -> " + n + ")");
      }
      assertTrue(n <= 1.0 + 1e-6,
          "normFactor exceeded 1 at r=" + r + " (" + n + ")");
      previous = n;
    }
    assertEquals(1.0, previous, 1e-6, "should have saturated at 1");
  }

  /** The elliptical clip in beamIntensity: zero outside, positive inside. */
  @Test
  public void beamIntensityIsClippedToTheEllipse() {
    double sigma = 20.0;
    BeamGaussian b = beam(sigma, sigma, 80, 40, true); // semi-axes 40 x 20

    assertTrue(b.beamIntensity(0, 0, 0) > 0, "centre should be illuminated");
    assertTrue(b.beamIntensity(39.9, 0, 0) > 0, "just inside along x");
    assertTrue(b.beamIntensity(0, 19.9, 0) > 0, "just inside along y");

    assertEquals(0.0, b.beamIntensity(40.1, 0, 0), 0.0, "just outside along x");
    assertEquals(0.0, b.beamIntensity(0, 20.1, 0), 0.0, "just outside along y");
    // A corner of the bounding rectangle lies outside the inscribed ellipse.
    assertEquals(0.0, b.beamIntensity(39, 19, 0), 0.0,
        "rectangle corner should be outside the ellipse");
  }

  /** A rectangular beam illuminates that same corner, an ellipse does not. */
  @Test
  public void rectangularCollimationDoesNotClipTheCorners() {
    double sigma = 20.0;
    assertTrue(beam(sigma, sigma, 80, 40, false).beamIntensity(39, 19, 0) > 0,
        "a rectangular beam should illuminate the corner");
  }

  /** The off-axis offset shifts the ellipse along x. */
  @Test
  public void offAxisOffsetShiftsTheAperture() {
    double sigma = 20.0;
    BeamGaussian b = beam(sigma, sigma, 80, 40, true);
    // x = 50 is outside the unshifted ellipse but inside one centred at x = 30
    assertEquals(0.0, b.beamIntensity(50, 0, 0), 0.0);
    assertTrue(b.beamIntensity(50, 0, 30) > 0,
        "point should fall inside the aperture once it is offset");
  }

  /**
   * A beam straight from the constructor delivers zero intensity: the field
   * attenuatedPhotonsPerSec starts at 0 and is only populated by
   * applyContainerAttenuation (BeamGaussian.java:269) or setPhotonsPerfs
   * (:347). The production path is safe -- Crystal.expose() calls the former
   * at Crystal.java:869 -- but the ordering is implicit, so pin it.
   */
  @Test
  public void beamIsInertUntilContainerAttenuationIsApplied() {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_FWHM_X, 20 * SIGMA_TO_FWHM);
    p.put(Beam.BEAM_FWHM_Y, 20 * SIGMA_TO_FWHM);
    p.put(Beam.BEAM_ENERGY, ENERGY);
    p.put(Beam.BEAM_FLUX, FLUX);
    p.put(Beam.BEAM_COLL_H, 80.);
    p.put(Beam.BEAM_COLL_V, 80.);

    BeamGaussian fresh = new BeamGaussian(p);
    assertEquals(0.0, fresh.beamIntensity(0, 0, 0), 0.0,
        "a beam with no container applied yet has no intensity");

    fresh.applyContainerAttenuation(new ContainerTransparent());
    assertTrue(fresh.beamIntensity(0, 0, 0) > 0,
        "applying a transparent container should activate the beam");
  }

  /**
   * The circular flag is compared by value, not by reference.
   * <p>
   * It used to read {@code properties.get(BEAM_CIRCULAR) == "TRUE"}, which
   * worked only because the ANTLR parser stores the interned literal
   * (InputfileParser.java:4845). Any caller building the property map
   * programmatically with an equal but non-identical string silently got a
   * rectangular beam, a different normFactor and different doses throughout.
   * BeamTophat and BeamExperimentalpgm shared the pattern and are fixed too.
   */
  @Test
  public void circularFlagIsComparedByValue() {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_FWHM_X, 20 * SIGMA_TO_FWHM);
    p.put(Beam.BEAM_FWHM_Y, 20 * SIGMA_TO_FWHM);
    p.put(Beam.BEAM_ENERGY, ENERGY);
    p.put(Beam.BEAM_FLUX, FLUX);
    p.put(Beam.BEAM_COLL_H, 80.);
    p.put(Beam.BEAM_COLL_V, 80.);

    p.put(Beam.BEAM_CIRCULAR, "TRUE");                 // interned literal
    assertTrue(new BeamGaussian(p).getIsCircular(),
        "the interned literal is recognised");

    p.put(Beam.BEAM_CIRCULAR, new String("TRUE"));     // equal, not identical
    assertTrue(new BeamGaussian(p).getIsCircular(),
        "an equal-but-not-identical \"TRUE\" must also be recognised");
  }
}
