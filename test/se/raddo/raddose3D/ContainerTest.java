package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the sample container attenuation model.
 * <p>
 * The predecessor of this file, {@code ContainerTests.java}, carried <em>no</em>
 * TestNG annotations at all, so neither of its two methods had ever run. It
 * also reached out to physics.nist.gov on every call, which is why the
 * attenuation arithmetic is exercised here through a stub subclass instead:
 * {@link ContainerSemiTransparent} does all the real work
 * ({@code calculateMassThickness}, {@code calculateContainerAttenuation}) and
 * delegates only the table lookup to the abstract
 * {@code extractMassAttenuationCoef}.
 * <p>
 * The two tests that genuinely need NIST are kept, tagged {@code network} and
 * excluded from the default run.
 */
public class ContainerTest {

  /** A container whose mass attenuation coefficient is supplied directly. */
  private static class StubContainer extends ContainerSemiTransparent {
    private final double coefficient;

    StubContainer(double thickness, double density, double coefficient) {
      super(thickness, density, "stub");
      this.coefficient = coefficient;
    }

    @Override
    void extractMassAttenuationCoef(Beam beam) {
      this.massAttenuationCoefficient = coefficient;
    }
  }

  private static Beam beam(double energyKeV) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_COLL_H, 80.);
    p.put(Beam.BEAM_COLL_V, 80.);
    p.put(Beam.BEAM_FLUX, 9.281e8);
    p.put(Beam.BEAM_ENERGY, energyKeV);
    return new BeamTophat(p);
  }

  /** Mass thickness is density times thickness, converted um -> cm. */
  @ParameterizedTest
  @ValueSource(doubles = {0, 1, 50, 100, 1000})
  public void massThicknessIsDensityTimesThicknessInCentimetres(double thickness) {
    double density = 2.23;
    StubContainer c = new StubContainer(thickness, density, 0.5);
    c.calculateContainerAttenuation(beam(8.05));

    assertEquals(density * thickness * 1e-4, c.getMassThickness(), 1e-15,
        "mass thickness for a " + thickness + " um wall");
  }

  /** The Beer-Lambert form: 1 - exp(-(mu/rho) * mass thickness). */
  @ParameterizedTest
  @ValueSource(doubles = {0.1, 1.0, 10.0, 100.0})
  public void attenuationFollowsBeerLambert(double coefficient) {
    double thickness = 50, density = 1.424;
    StubContainer c = new StubContainer(thickness, density, coefficient);
    c.calculateContainerAttenuation(beam(8.05));

    double massThickness = density * thickness * 1e-4;
    assertEquals(1 - Math.exp(-coefficient * massThickness),
        c.getContainerAttenuationFraction(), 1e-15);
  }

  /**
   * The property the original {@code testTransparency} was reaching for: a
   * zero-thickness wall attenuates nothing, whatever it is made of.
   */
  @ParameterizedTest
  @ValueSource(doubles = {0.5, 5, 50, 500})
  public void zeroThicknessAttenuatesNothing(double coefficient) {
    StubContainer c = new StubContainer(0.0, 2.23, coefficient);
    c.calculateContainerAttenuation(beam(8.05));

    assertEquals(0.0, c.getMassThickness(), 0.0);
    assertEquals(0.0, c.getContainerAttenuationFraction(), 1e-15,
        "a zero-thickness container must not attenuate the beam");
  }

  /** Zero density is equally transparent. */
  @Test
  public void zeroDensityAttenuatesNothing() {
    StubContainer c = new StubContainer(100.0, 0.0, 5.0);
    c.calculateContainerAttenuation(beam(8.05));
    assertEquals(0.0, c.getContainerAttenuationFraction(), 1e-15);
  }

  /** Attenuation rises with wall thickness and saturates below 1. */
  @Test
  public void attenuationIncreasesWithThicknessAndStaysBelowOne() {
    double previous = -1;
    for (double thickness = 0; thickness <= 5000; thickness += 100) {
      StubContainer c = new StubContainer(thickness, 2.23, 10.0);
      c.calculateContainerAttenuation(beam(8.05));
      double f = c.getContainerAttenuationFraction();

      assertTrue(f > previous, "attenuation fell at thickness " + thickness);
      assertTrue(f >= 0 && f < 1,
          "attenuation fraction out of range at thickness " + thickness
              + ": " + f);
      previous = f;
    }
  }

  /** A fully-null specification means "no container at all". */
  @Test
  public void aFullyUnspecifiedContainerIsTransparent() {
    StubContainer c = new StubContainer(0.0, 0.0, 0.0) {
    };
    c.calculateContainerAttenuation(beam(8.05));
    assertEquals(0.0, c.getContainerAttenuationFraction(), 0.0);
  }

  /** ContainerTransparent short-circuits the whole model. */
  @Test
  public void containerTransparentHasNoMaterialAndNoAttenuation() {
    ContainerTransparent c = new ContainerTransparent();
    c.calculateContainerAttenuation(beam(8.05));
    assertNull(c.getContainerMaterial(), "transparent container has no material");
    assertEquals(0.0, c.getContainerAttenuationFraction(), 0.0);
  }

  // NOTE: ContainerElemental.getNISTURL(int) and ContainerMixture.getNISTURL()
  // are pure string formatting and would be good test targets, but both are
  // private rather than package-private, so they are not reachable from here.
  // Widening them is a production change and is deliberately left alone.

  /**
   * The original intent of {@code testTransparency}: a real pyrex/elemental
   * container of zero thickness attenuates nothing. Needs NIST.
   */
  @Test
  @Tag("network")
  public void zeroThicknessRealContainersAreTransparent() {
    ContainerMixture mix = new ContainerMixture(0.0, 2.23, "pyrex");

    List<String> names = new ArrayList<String>(Arrays.asList("S", "O"));
    List<Double> nums = new ArrayList<Double>(Arrays.asList(1.0, 2.0));
    ContainerElemental elem = new ContainerElemental(0.0, 2.65, names, nums);

    Beam b = beam(8.05);
    mix.calculateContainerAttenuation(b);
    elem.calculateContainerAttenuation(b);

    assertTrue(mix.getContainerAttenuationFraction() < 1e-6,
        "zero mixture thickness attenuated the beam");
    assertTrue(elem.getContainerAttenuationFraction() < 1e-6,
        "zero elemental thickness attenuated the beam");
  }

  /**
   * The original {@code testElementalMatchesMixture}: alanine's mass
   * attenuation coefficient should agree whether taken from the NIST compound
   * table or summed from its elements. Needs NIST.
   * <p>
   * Note the original comparison was one-sided ({@code a - b < 1e-2}, which
   * passes for any sufficiently negative difference); this uses Math.abs.
   */
  @Test
  @Tag("network")
  public void elementalCompositionMatchesMixtureForAlanine() {
    ContainerMixture mix = new ContainerMixture(50.0, 1.424, "alanine");

    List<String> names = new ArrayList<String>(Arrays.asList("H", "C", "N", "O"));
    List<Double> nums = new ArrayList<Double>(Arrays.asList(7.0, 3.0, 1.0, 2.0));
    ContainerElemental elem = new ContainerElemental(50.0, 1.424, names, nums);

    Beam b = beam(8.05);
    mix.calculateContainerAttenuation(b);
    elem.calculateContainerAttenuation(b);

    assertEquals(mix.getMassAttenuationCoefficient(),
        elem.getMassAttenuationCoefficient(), 1e-2,
        "mixture and elemental mass attenuation coefficients disagree");
  }
}
