package se.raddo.raddose3D.tests;

import java.util.HashMap;

import org.testng.annotations.Test;

import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.CrystalCuboid;
import se.raddo.raddose3D.CrystalPolyhedron;
import se.raddo.raddose3D.Wedge;

public class CrystalPolyhedronTests {
  @Test
  public static void testFindDepthSimple()
  {
    double xdim = 60, ydim = 20, zdim = 40; // just like in the model file.

    Double resolution = 0.5d;
    String modelFile = "models/cuboid-30-20-10.obj";
    String modelType = "obj";

    HashMap<Object, Object> properties = new HashMap<Object, Object>();

    properties.put(Crystal.CRYSTAL_RESOLUTION, resolution);
    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    properties.put(CrystalPolyhedron.CRYSTAL_WIREFRAME_FILE, modelFile);
    properties.put(CrystalPolyhedron.CRYSTAL_WIREFRAME_TYPE, modelType);

    Crystal c = new CrystalPolyhedron(properties);

    Wedge w = new Wedge(0d, 0d, 0d, 100d, 0d, 0d, 0d, 0d, 0d, 0d, 0d);

    double[] crystCoords;
    // this coordinate is in voxel coordinates.
    // this translates to bottom left corner of the crystal
    // in crystCoords (-45, -37, -20)
    // and should therefore be first to intercept the beam and have
    // a depth of 0.
    // taking voxels which are definitely within the crystal (starting
    // from 1, 1, 1 to width - 1, height - 1, depth - 1)
    // due to rounding errors because I still don't know how
    // to deal with those. -- Helen

    for (double angle = 0; angle <= 90; angle += 90)
    {
      for (int x = 1; x < xdim * resolution - 1; x++) {
        for (int y = 1; y < ydim * resolution - 1; y++) {
          for (int z = 1; z < zdim * resolution - 1; z++) {
            crystCoords = c.getCrystCoord(x, y, z);
            Assertion.equals(crystCoords[0], -(xdim / 2) + (x / resolution),
                "crystal coordinate x axis for voxel (" + x + ", " + y + ", "
                    + z + ")", 0.01);
            Assertion.equals(crystCoords[1], -(ydim / 2) + (y / resolution),
                "crystal coordinate y axis for voxel (" + x + ", " + y + ", "
                    + z + ")", 0.01);
            Assertion.equals(crystCoords[2], -(zdim / 2) + (z / resolution),
                "crystal coordinate z axis for voxel (" + x + ", " + y + ", "
                    + z + ")", 0.01);

            double radians = Math.toRadians(angle);
            
            c.setupDepthFinding(radians, w);

            double depth = c.findDepth(crystCoords, 0, w);

            // Because the crystal has not been rotated,
            // the depth should just be z / resolution
            double trueDepth = (angle == 0) ? z : x;
            trueDepth /= resolution;
            
            String axis = (angle == 0) ? "z" : "x";
            
            Assertion.equals(depth, trueDepth, "depth at " + axis + " = " + trueDepth
                + " for crystCoord (" + crystCoords[0] + ", " + crystCoords[1]
                + ", " + crystCoords[2] + ")", 2.0);
          }
        }
      }
    }
  }

  // Test to work out whether concave crystals omit depths where
  // there is missing crystal space.
  // Object file has a horseshoe shape (like a magnet) which
  // is hit from the side, which may omit the middle bit
  // depending on the tested voxel.
  @Test
  public static void testFindDepthConcave()
  {
    Double resolution = 0.5d;
    String modelFile = "models/concave_cuboid-30-20-10.obj";
    String modelType = "obj";

    HashMap<Object, Object> properties = new HashMap<Object, Object>();

    properties.put(Crystal.CRYSTAL_RESOLUTION, resolution);
    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    properties.put(CrystalPolyhedron.CRYSTAL_WIREFRAME_FILE, modelFile);
    properties.put(CrystalPolyhedron.CRYSTAL_WIREFRAME_TYPE, modelType);

    Crystal c = new CrystalPolyhedron(properties);

    Wedge w = new Wedge(0d, 0d, 0d, 100d, 0d, 0d, 0d, 0d, 0d, 0d, 0d);

    // this is where the beam should be going through the thickest
    // part of the crystal (pre-determined)
    double[] crystCoordThick = { 3.5, -8.65, 29.9 };

    // this coordinate should miss out the middle section of
    // the horseshoe if hit by the beam.

    double[] crystCoordThin = { 5, 6, 30.0 };

    c.setupDepthFinding(0, w);

    double thickDepth = c.findDepth(crystCoordThick, 0, w);
    Assertion
        .equals(thickDepth, 60.0, "Thick part of crystal about 30 um", 1.0);

    double thinDepth = c.findDepth(crystCoordThin, 0, w);
    Assertion.equals(thinDepth, 40.0, "Thin part of crystal about 20 um", 1.0);

  }
}
