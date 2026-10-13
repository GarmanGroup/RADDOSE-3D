package se.raddo.raddose3D;

/**
 * Performs basic tests on Experimental Beam classes.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


public class BeamExperimentalTest {

  private double     tolerance                = 1e-10;

  private Double[][] evenBeamVerticalChange   = new Double[4][4];
  private Double[][] evenBeamHorizontalChange = new Double[4][4];
  private Double[][] evenBeamBothChange       = new Double[4][4];

  private Double     sumVert                  = 0.0;
  private Double     sumHoriz                 = 0.0;
  private Double     sumBoth                  = 0.0;

  private Double     defaultE                 = 10.0;
  private Double     defaultFlux              = 1e12;
  private Double     defaultVertSize          = 5.0;
  private Double     defaultHorizSize         = 5.0;

  private Double     defaultP                 = 1.0;

  private Double     defaultFWHM              = null;

  public BeamExperimentalTest() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        evenBeamVerticalChange[i][j] = (double) i + 1;
        sumVert += (double) i + 1;
        evenBeamHorizontalChange[i][j] = (double) j + 1;
        sumHoriz += (double) j + 1;
        evenBeamBothChange[i][j] = (double) i + j + 2;
        sumBoth += (double) i + j + 2;
      }
    }
  }

  /**
   * Builds a beam from a profile, as a run builds one: a transparent
   * container applied first, so the beam carries its flux.
   * <p>
   * The tests here used to skip that step. The attenuated flux then stayed 0,
   * every intensity was 0, and assertions of the form
   * {@code intensity - expected < tolerance} held whatever the code did.
   *
   * @param profile relative intensities, [y][x]
   * @return the beam, ready to be asked for intensities
   */
  private BeamExperimental beam(final Double[][] profile) {
    BeamExperimental beam = new BeamExperimental(profile, defaultFlux,
        defaultE, defaultHorizSize, defaultVertSize, defaultP, defaultFWHM);
    beam.applyContainerAttenuation(new ContainerTransparent());
    beam.generateBeamArray();
    return beam;
  }

  /**
   * One pixel's energy flux, as generateBeamArray scales it.
   *
   * @param value the pixel's relative intensity
   * @param sum the profile's total
   * @return J/um^2/s
   */
  private double perPixel(final double value, final double sum) {
    return value * Beam.KEVTOJOULES * defaultE * defaultFlux
        / (sum * defaultHorizSize * defaultVertSize);
  }

  /**
   * A 4 by 4 profile of 5 um pixels, with a border of zeros one pixel wide,
   * is centred on the beam axis: pixel [i][j] is centred at x = 5j - 7.5,
   * y = 5i - 7.5. This one varies along y only.
   */
  @Test
  public void aProfileVaryingAlongY() {
    BeamExperimental b = beam(evenBeamVerticalChange);

    assertEquals(perPixel(1, sumVert), b.beamIntensity(0, -7.5, 0),
        tolerance * perPixel(1, sumVert));
    assertEquals(perPixel(3, sumVert), b.beamIntensity(0, 2.5, 0),
        tolerance * perPixel(3, sumVert));
    assertEquals(perPixel(4, sumVert), b.beamIntensity(-7.5, 7.5, 0),
        tolerance * perPixel(4, sumVert));
    // Halfway between the rows of 2 and of 3.
    assertEquals(perPixel(2.5, sumVert), b.beamIntensity(0, 0, 0),
        tolerance * perPixel(2.5, sumVert));
  }

  /** The same, varying along x only. */
  @Test
  public void aProfileVaryingAlongX() {
    BeamExperimental b = beam(evenBeamHorizontalChange);

    assertEquals(perPixel(1, sumHoriz), b.beamIntensity(-7.5, 0, 0),
        tolerance * perPixel(1, sumHoriz));
    assertEquals(perPixel(4, sumHoriz), b.beamIntensity(7.5, -7.5, 0),
        tolerance * perPixel(4, sumHoriz));
    assertEquals(perPixel(2.5, sumHoriz), b.beamIntensity(0, 0, 0),
        tolerance * perPixel(2.5, sumHoriz));
  }

  /** Varying along both, read at centres, midpoints and the corners. */
  @Test
  public void aProfileVaryingAlongBoth() {
    BeamExperimental b = beam(evenBeamBothChange);

    assertEquals(perPixel(2, sumBoth), b.beamIntensity(-7.5, -7.5, 0),
        tolerance * perPixel(2, sumBoth));
    assertEquals(perPixel(8, sumBoth), b.beamIntensity(7.5, 7.5, 0),
        tolerance * perPixel(8, sumBoth));
    assertEquals(perPixel(5, sumBoth), b.beamIntensity(0, 0, 0),
        tolerance * perPixel(5, sumBoth));
    assertEquals(perPixel(5, sumBoth), b.beamIntensity(2.5, -2.5, 0),
        tolerance * perPixel(5, sumBoth));
    // The middle of four pixels, of 4, 5, 5 and 6.
    assertEquals(perPixel(5, sumBoth), b.beamIntensity(5, -5, 0),
        tolerance * perPixel(5, sumBoth));
  }

  /**
   * The profile fades into its border of zeros, and stops one pixel short of
   * the image's edge.
   */
  @Test
  public void theProfileFadesIntoItsBorder() {
    BeamExperimental b = beam(evenBeamBothChange);

    // Halfway from the corner pixel's centre to the border's.
    assertEquals(perPixel(4, sumBoth), b.beamIntensity(10, 7.5, 0),
        tolerance * perPixel(4, sumBoth));
    assertEquals(0.0, b.beamIntensity(10.001, 0, 0));
    assertEquals(0.0, b.beamIntensity(0, -10.001, 0));
  }

  /** Summed over the pixel centres, the profile carries the beam's power. */
  @Test
  public void theProfileCarriesTheBeamsPower() {
    BeamExperimental b = beam(evenBeamBothChange);
    double power = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        power += b.beamIntensity(5 * j - 7.5, 5 * i - 7.5, 0)
            * defaultHorizSize * defaultVertSize;
      }
    }
    double expected = Beam.KEVTOJOULES * defaultE * defaultFlux;
    assertEquals(expected, power, tolerance * expected);
  }

  /**
   * Explicitly test the bilinear interpolation routine.
   * Values obtained from
   * *
   * http://www.ajdesigner.com/phpinterpolation/
   * bilinear_interpolation_equation.php
   */
  @Test
  public void testInterpolation() {
    Tolerance.equals(BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0, 0),
        0., "Bilinear Interpolation at x=0, y=0", tolerance);
    Tolerance.equals(BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 1, 0),
        1., "Bilinear Interpolation at x=1, y=0", tolerance);
    Tolerance.equals(BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0, 1),
        2., "Bilinear Interpolation at x=0, y=1", tolerance);
    Tolerance.equals(BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 1, 1),
        3., "Bilinear Interpolation at x=1, y=1", tolerance);

    Tolerance.equals(
        BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0.3, 0.5),
        1.3, "Bilinear Interpolation at x=0.3, y=0.5", tolerance);
    Tolerance.equals(
        BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0.7, 0.5),
        1.7, "Bilinear Interpolation at x=0.7, y=0.5", tolerance);
    Tolerance.equals(
        BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0.7, 0.9),
        2.5, "Bilinear Interpolation at x=0.7, y=0.9", tolerance);
    Tolerance.equals(
        BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0.9, 0.9),
        2.7, "Bilinear Interpolation at x=0.9, y=0.9", tolerance);
  }
}
