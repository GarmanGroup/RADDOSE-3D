package se.raddo.raddose3D.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.*;

import se.raddo.raddose3D.CoefCalcAverage;
import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.CrystalCuboid;
import se.raddo.raddose3D.Wedge;

/**
 * Tests for the Cuboid crystal class.
 * 
 * @author Oliver Zeldin
 */

public class CrystalCuboidTest {
  final static double dblRoundingTolerance = 1e-13;

  /**
   * Tests value against target. Includes testing for null and nice error
   * messages
   */
  private void EqualsAssertion(Double value, Double target, String name) {
    Assert.assertNotNull(value, name + " is null");
    Assert.assertTrue(Math.abs(value - target) < dblRoundingTolerance,
        name + " set incorrectly (" + value + ")");
  }

  @Test(groups = { "advanced" })
  /** Checks that a full 360 rotation in P or L makes the crystal invariant,
   *  and that you get correct negatives under 180deg rotation.
   **/
  public void testCuboidCrystalPandL() {
    final Double ang360 = 360d;
    final Double ang180 = 180d;

    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, 100d);
    properties.put(Crystal.CRYSTAL_DIM_Y, 100d);
    properties.put(Crystal.CRYSTAL_DIM_Z, 100d);
    properties.put(Crystal.CRYSTAL_RESOLUTION, 0.5d);
    properties.put(Crystal.CRYSTAL_COEFCALC, new CoefCalcAverage());

    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    Crystal c = new CrystalCuboid(properties);

    properties.put(Crystal.CRYSTAL_ANGLE_P, ang360);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    Crystal cEquivalentP360 = new CrystalCuboid(properties);
    // Should be the same as c

    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, ang360);
    Crystal cEquivalentL360 = new CrystalCuboid(properties);
    // Should be the same as c

    properties.put(Crystal.CRYSTAL_ANGLE_P, ang360);
    properties.put(Crystal.CRYSTAL_ANGLE_L, ang360);
    Crystal cEquivalentPL360 = new CrystalCuboid(properties);
    // Should be the same as c

    properties.put(Crystal.CRYSTAL_ANGLE_P, ang180);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    Crystal cP180 = new CrystalCuboid(properties);
    // (i,j,k) should = c(-i, -j,  k)

    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, ang180);
    Crystal cL180 = new CrystalCuboid(properties);
    // (i,j,k) should = c(i , -j, -k)

    int x = c.getCrystSizeVoxels()[0];
    int y = c.getCrystSizeVoxels()[1];
    int z = c.getCrystSizeVoxels()[2];

    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        for (int k = 0; k < z; k++) {
          double id[] = c.getCrystCoord(i, j, k);

          EqualsAssertion(cEquivalentP360.getCrystCoord(i, j, k)[0], id[0],
              "P360-x");
          EqualsAssertion(cEquivalentP360.getCrystCoord(i, j, k)[1], id[1],
              "P360-y");
          EqualsAssertion(cEquivalentP360.getCrystCoord(i, j, k)[2], id[2],
              "P360-z");

          EqualsAssertion(cEquivalentL360.getCrystCoord(i, j, k)[0], id[0],
              "L360-x");
          EqualsAssertion(cEquivalentL360.getCrystCoord(i, j, k)[1], id[1],
              "L360-y");
          EqualsAssertion(cEquivalentL360.getCrystCoord(i, j, k)[2], id[2],
              "L360-z");

          EqualsAssertion(cEquivalentPL360.getCrystCoord(i, j, k)[0], id[0],
              "PL360-x");
          EqualsAssertion(cEquivalentPL360.getCrystCoord(i, j, k)[1], id[1],
              "PL360-y");
          EqualsAssertion(cEquivalentPL360.getCrystCoord(i, j, k)[2], id[2],
              "PL360-z");

          EqualsAssertion(-1 * cP180.getCrystCoord(i, j, k)[0], id[0], "P180-x");
          EqualsAssertion(-1 * cP180.getCrystCoord(i, j, k)[1], id[1], "P180-y");
          EqualsAssertion(cP180.getCrystCoord(i, j, k)[2], id[2], "P180-z");

          EqualsAssertion(cL180.getCrystCoord(i, j, k)[0], id[0], "L180-x");
          EqualsAssertion(-1 * cL180.getCrystCoord(i, j, k)[1], id[1], "L180-y");
          EqualsAssertion(-1 * cL180.getCrystCoord(i, j, k)[2], id[2], "L180-z");
        }
      }

    }
    System.out.println("@Test - testCuboidCrystalPandL");
  }

  //This should work now... Am going to tart up Wedge and have another go.
  @Test(groups = { "advanced" })
  public static void testFindDepth() {

    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, 100d);
    properties.put(Crystal.CRYSTAL_DIM_Y, 100d);
    properties.put(Crystal.CRYSTAL_DIM_Z, 100d);
    properties.put(Crystal.CRYSTAL_RESOLUTION, 1d);
    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    properties.put(Crystal.CRYSTAL_COEFCALC, new CoefCalcAverage());
    Crystal c = new CrystalCuboid(properties);

    Wedge w = new Wedge(2d, 0d, 90d, 100d, 0d, 0d, 0d, 0d, 0d, 0d, 0d);

    /* Some random test coordinates to work on */
    double[] testCoords = { 0, 0, 0 };//{ 12.23, 21.56, -44.32}; 
    double[] testInvCoords = { 0, 0, 0 };//{-12.23, 21.56,  44.32}; 

    for (double angles = 0; angles < Math.toRadians(500); angles += Math
        .toRadians(18.8)) {
      // Loop over y as well, to make it more thorough

      //System.out.println(String.format("%n%n angle is %g", angles));
      /* Rotating crystal into position */
      double[] tempCoords = new double[3];
      double[] tempInvCoords = new double[3];
      //Debug System.out.println(i+j+k);
      tempCoords[0] = testCoords[0] * Math.cos(angles) - testCoords[2]
          * Math.sin(angles); //Rotate X
      tempCoords[1] = testCoords[1];
      tempCoords[2] = testCoords[0] * Math.sin(angles) + testCoords[2]
          * Math.cos(angles); //Rotate Z

      /* Symmetry related pair of tempCoords */
      tempInvCoords[0] = testInvCoords[0] * Math.cos(angles) - testInvCoords[2]
          * Math.sin(angles); //Rotate X
      tempInvCoords[1] = testInvCoords[1];
      tempInvCoords[2] = testInvCoords[0] * Math.sin(angles) + testInvCoords[2]
          * Math.cos(angles); //Rotate Z

      Assert.assertTrue(Math.abs(tempCoords[1] - tempInvCoords[1]) <= 1e-10,
          "y does not match under inversion");
      if (Math.abs(tempCoords[1] - tempInvCoords[1]) <= 1e-10)
        System.out.println("y coords match");

      //						System.out.println("tempcoords = "    + tempCoords[0]      + ", " + tempCoords[1]     + ", " + tempCoords[2]);
      //                        System.out.println("tempInvCoords = " + tempInvCoords[0]   + ", " + tempInvCoords[1]  + ", " + tempInvCoords[2]);                				    
      //                        
      //                        System.out.println("depth tempCoords @ theta = 0: "     + c.findDepth(tempCoords, angles, w));
      //                        System.out.println("depth tempInvCoords @ theta = 0: "  + c.findDepth(tempInvCoords, angles, w));						
      //                        System.out.println("depth tempCoords @ theta = 180: "   + c.findDepth(tempCoords, angles + Math.PI, w));
      //                        System.out.println("depth tempInvCoords @ theta = 180: "+ c.findDepth(tempInvCoords, angles + Math.PI, w));     

      /* um of depths should be constant under 180Deg rotation */
      c.setupDepthFinding(angles, w);
      double sumdepths1 = c.findDepth(tempCoords, angles, w)
          + c.findDepth(tempInvCoords, angles, w);

      c.setupDepthFinding(angles + Math.PI, w);
      double sumdepths2 = c.findDepth(tempCoords, angles + Math.PI, w)
          + c.findDepth(tempInvCoords, angles + Math.PI, w);

      //						System.out.println("sumdepths1 = " + sumdepths1);
      //						System.out.println("sumdepths2 = " + sumdepths2);

      double depthDelta = sumdepths1 - sumdepths2;
      System.out.println("depthdelta = " + depthDelta);
      Assert.assertTrue(Math.abs(sumdepths1 - sumdepths2) <= 1e-10,
          "depths are not matched under symmetry");
    }
  }

  @Test(groups = { "advanced" })
  public static void secondDepthTest() {

    // make a new map for a Cuboid Crystal, dimensions 90 x 74 x 40 um,
    // 0.5 voxels per um, no starting rotation.
    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, 90d);
    properties.put(Crystal.CRYSTAL_DIM_Y, 74d);
    properties.put(Crystal.CRYSTAL_DIM_Z, 40d);
    properties.put(Crystal.CRYSTAL_RESOLUTION, 0.5d);
    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    properties.put(Crystal.CRYSTAL_COEFCALC, new CoefCalcAverage());
    Crystal c = new CrystalCuboid(properties);

    // create a new wedge with no rotation at 100 seconds' exposure
    // (doesn't matter)
    Wedge w = new Wedge(0d, 0d, 0d, 100d, 0d, 0d, 0d, 0d, 0d, 0d, 0d);

    // beam is along z axis. So when the crystal is not rotated, the 
    // maximum depth along the z axis should be 40 um (length of crystal).

    double[] crystCoords = new double[3];
    // this coordinate is in voxel coordinates.
    // this translates to bottom left corner of the crystal
    // in crystCoords (-45, -37, -20)
    // and should therefore be first to intercept the beam and have
    // a depth of 0.
    crystCoords = c.getCrystCoord(0, 0, 0);

    Assertion.equals(crystCoords[2], -20, "crystal coordinate z axis = -20");
    
    c.setupDepthFinding(0, w);
    double depth = c.findDepth(crystCoords, 0, w);

    Assertion.equals(depth, 0, "depth = 0 at front edge of crystal");

  }
}
