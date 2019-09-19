package se.raddo.raddose3D.tests;

/**
 * Performs basic tests on Experimental Beam classes.
 */

import static org.testng.Assert.*;
import org.testng.annotations.*;

import se.raddo.raddose3D.BeamExperimental;

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

  @Test
  /** Checks that the halfway points between nodes are correct */
  public void testHorizontal() {
    BeamExperimental evenVert = new BeamExperimental(evenBeamVerticalChange,
        defaultFlux, defaultE, defaultHorizSize, defaultVertSize, defaultP, defaultFWHM);

    // Intensity profile should be constant across rows, 
    // change from 0 to 4 across columns
    evenVert.generateBeamArray();
    double normF = defaultFlux
        / (sumHoriz * defaultHorizSize * defaultVertSize);

    // Check along X
    assertTrue(evenVert.beamIntensity(0, 0, 0) - 2.5 * normF < tolerance);
    assertTrue(evenVert.beamIntensity(5, 8, 0) - 3.5 * normF < tolerance);
    assertTrue(evenVert.beamIntensity(0, -10, 0) - 1.25 * normF < tolerance);
    // Check along Y
    assertTrue(evenVert.beamIntensity(5, 0, 0) - 3.5 * normF < tolerance);
    assertTrue(evenVert.beamIntensity(-2.5, 5, 0) - 2 * normF < tolerance);
  }

  @Test
  /** Checks that the halfway points between nodes are correct */
  public void testVertical() {
    BeamExperimental evenHoriz = new BeamExperimental(evenBeamHorizontalChange,
        defaultFlux, defaultE, defaultHorizSize, defaultVertSize, defaultP, defaultFWHM);
    // Intensity profile should be constant across rows, 
    // change from 0 to 4 across columns
    evenHoriz.generateBeamArray();
    double normF = defaultFlux / (sumVert * defaultHorizSize * defaultVertSize);
    // Check along X
    //assertTrue(evenHoriz.beamIntensity(0, 0, 0) - 2.5 * normF < tolerance);
    assertTrue(evenHoriz.beamIntensity(8, 5, 0) - 3.5 * normF < tolerance);
    assertTrue(evenHoriz.beamIntensity(-10, 0, 0) - 1.25 * normF < tolerance);
    // Check along Y
    assertTrue(evenHoriz.beamIntensity(0, 5, 0) - 3.5 * normF < tolerance);
    assertTrue(evenHoriz.beamIntensity(5, -2.5, 0) - 2 * normF < tolerance);
  }

  @Test
  /** Checks that the halfway points between nodes are correct */
  public void testBoth() {
    BeamExperimental evenBoth = new BeamExperimental(evenBeamBothChange,
        defaultFlux, defaultE, defaultHorizSize, defaultVertSize, defaultP, defaultFWHM); // Intensity profile should be constant across rows, 
    evenBoth.generateBeamArray();
    double normF = defaultFlux / (sumBoth * defaultHorizSize * defaultVertSize);

    // Check along X
    assertTrue(evenBoth.beamIntensity(0, 0, 0) - 5 * normF < tolerance);
    assertTrue(evenBoth.beamIntensity(5, 0, 0) - 6 * normF < tolerance);
    assertTrue(evenBoth.beamIntensity(2.5, 0, 0) - 5.5 * normF < tolerance);

    // Check along Y
    assertTrue(evenBoth.beamIntensity(0, -5, 0) - 4 * normF < tolerance);
    assertTrue(evenBoth.beamIntensity(0, -2.5, 0) - 4.5 * normF < tolerance);

    // Check along both
    assertTrue(evenBoth.beamIntensity(5, -5, 0) - 5.5 * normF < tolerance);
    assertTrue(evenBoth.beamIntensity(7.5, -2.5, 0) - 6 * normF < tolerance);

    System.out.print("ok");
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
    Assertion.equals(BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0, 0),
        0., "Bilinear Interpolation at x=0, y=0", tolerance);
    Assertion.equals(BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 1, 0),
        1., "Bilinear Interpolation at x=1, y=0", tolerance);
    Assertion.equals(BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0, 1),
        2., "Bilinear Interpolation at x=0, y=1", tolerance);
    Assertion.equals(BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 1, 1),
        3., "Bilinear Interpolation at x=1, y=1", tolerance);

    Assertion.equals(
        BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0.3, 0.5),
        1.3, "Bilinear Interpolation at x=0.3, y=0.5", tolerance);
    Assertion.equals(
        BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0.7, 0.5),
        1.7, "Bilinear Interpolation at x=0.7, y=0.5", tolerance);
    Assertion.equals(
        BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0.7, 0.9),
        2.5, "Bilinear Interpolation at x=0.7, y=0.9", tolerance);
    Assertion.equals(
        BeamExperimental.bilinearInterpolate(0, 1, 2, 3, 0.9, 0.9),
        2.7, "Bilinear Interpolation at x=0.9, y=0.9", tolerance);
  }
}
