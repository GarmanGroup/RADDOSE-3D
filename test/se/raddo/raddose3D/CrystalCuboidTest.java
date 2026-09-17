package se.raddo.raddose3D;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


/**
 * Tests for the Cuboid crystal class.
 */

public class CrystalCuboidTest {
  /**
   * A full 360 degree rotation in P or L must leave the crystal exactly as it
   * was, coordinate for coordinate.
   * <p>
   * This replaces an assertion that a 180 degree rotation negates
   * getCrystCoord, which was simply wrong about the convention: AngleP and
   * AngleL rotate the crystal <em>mesh</em>, and the voxel grid is then rebuilt
   * over the bounding box of that rotated mesh. getCrystCoord therefore returns
   * bounding-box-relative coordinates of a new grid, not rotated coordinates of
   * a fixed one, and a cube rotated 180 degrees has the same bounding box -- so
   * voxel (0,0,0) is the same corner either way and the negation could never
   * hold. See TEST-TRIAGE.md #2.
   * <p>
   * The invariants that do hold are covered here (360 degrees is the identity)
   * and in CrystalRotationFrameTest (the grid spans the rotated bounding box,
   * occupancy is rotation-invariant, and the surrounding encloses the crystal).
   */
  @Test
  @Tag("slow")
  public void aFullThreeSixtyRotationLeavesTheCrystalUnchanged() {
    Crystal plain = cuboid(0d, 0d);
    Crystal spunP = cuboid(360d, 0d);
    Crystal spunL = cuboid(0d, 360d);
    Crystal spunPL = cuboid(360d, 360d);

    int[] size = plain.getCrystSizeVoxels();
    assertArrayEquals(size, spunP.getCrystSizeVoxels(), "AngleP 360 changed the grid size");
    assertArrayEquals(size, spunL.getCrystSizeVoxels(), "AngleL 360 changed the grid size");

    for (int i = 0; i < size[0]; i += 7) {
      for (int j = 0; j < size[1]; j += 7) {
        for (int k = 0; k < size[2]; k += 7) {
          double[] id = plain.getCrystCoord(i, j, k);
          for (Crystal spun : new Crystal[] {spunP, spunL, spunPL}) {
            double[] got = spun.getCrystCoord(i, j, k);
            for (int axis = 0; axis < 3; axis++) {
              assertEquals(id[axis], got[axis], 1e-9,
                  "360 degree rotation moved voxel (" + i + "," + j + "," + k
                      + ") on axis " + axis);
            }
          }
        }
      }
    }
  }

  /** A 100 um cube at 0.5 voxels/um with the given rotation. */
  private static Crystal cuboid(final Double angleP, final Double angleL) {
    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, 100d);
    properties.put(Crystal.CRYSTAL_DIM_Y, 100d);
    properties.put(Crystal.CRYSTAL_DIM_Z, 100d);
    properties.put(Crystal.CRYSTAL_RESOLUTION, 0.5d);
    properties.put(Crystal.CRYSTAL_ANGLE_P, angleP);
    properties.put(Crystal.CRYSTAL_ANGLE_L, angleL);
    return new CrystalCuboid(properties);
  }

  @Test
  @Tag("slow")
  public void testFindDepthSymmetry() {

    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, 100d);
    properties.put(Crystal.CRYSTAL_DIM_Y, 100d);
    properties.put(Crystal.CRYSTAL_DIM_Z, 100d);
    properties.put(Crystal.CRYSTAL_RESOLUTION, 1d);
    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    Crystal c = new CrystalCuboid(properties);

    Wedge w = new Wedge(2d, 0d, 90d, 100d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 2d);

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

      assertTrue(Math.abs(tempCoords[1] - tempInvCoords[1]) <= 1e-10,
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
      assertTrue(Math.abs(sumdepths1 - sumdepths2) <= 1e-10,
          "depths are not matched under symmetry");
    }
  }

  @Test
  public void testFindDepth() {
    int xdim = 90;
    int ydim = 74;
    int zdim = 40;
    Double resolution = 0.5d;

    // make a new map for a Cuboid Crystal, dimensions 90 x 74 x 40 um,
    // 0.5 voxels per um, no starting rotation.
    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, Double.valueOf(xdim));
    properties.put(Crystal.CRYSTAL_DIM_Y, Double.valueOf(ydim));
    properties.put(Crystal.CRYSTAL_DIM_Z, Double.valueOf(zdim));
    properties.put(Crystal.CRYSTAL_RESOLUTION, resolution);
    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    Crystal c = new CrystalCuboid(properties);

    // create a new wedge with no rotation at 100 seconds' exposure
    // (doesn't matter)
    Wedge w = new Wedge(0d, 0d, 0d, 100d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 2d);

    // beam is along z axis. So when the crystal is not rotated, the 
    // maximum depth along the z axis should be zdim um (length of crystal).

    double[] crystCoords;
    // this coordinate is in voxel coordinates.
    // this translates to bottom left corner of the crystal
    // in crystCoords (-45, -37, -20)
    // and should therefore be first to intercept the beam and have
    // a depth of 0.

    for (int x = 0; x < xdim * resolution; x++) {
      for (int y = 0; y < ydim * resolution; y++) {
        for (int z = 0; z < zdim * resolution; z++) {
          crystCoords = c.getCrystCoord(x, y, z);
          Tolerance.equals(crystCoords[0], -(xdim / 2) + (x / resolution),
              "crystal coordinate x axis");
          Tolerance.equals(crystCoords[1], -(ydim / 2) + (y / resolution),
              "crystal coordinate y axis");
          Tolerance.equals(crystCoords[2], -(zdim / 2) + (z / resolution),
              "crystal coordinate z axis");

          c.setupDepthFinding(0, w);

          double depth = c.findDepth(crystCoords, 0, w);
          // The depth finding overestimates by 10/resolution :(
          // depth -= (10 / resolution);

          // Because the crystal has not been rotated,
          // the depth should just be z / resolution
          Tolerance.equals(depth, z / resolution, "depth at z=" + z);
        }
      }
    }
  }
}
