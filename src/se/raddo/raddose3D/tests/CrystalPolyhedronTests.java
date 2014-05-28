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

    for (int x = 0; x < xdim * resolution; x++) {
      for (int y = 0; y < ydim * resolution; y++) {
        for (int z = 0; z < zdim * resolution; z++) {
          crystCoords = c.getCrystCoord(x, y, z);
          Assertion.equals(crystCoords[0], -(xdim / 2) + (x / resolution),
              "crystal coordinate x axis for voxel (" + x + ", " + y + ", " + z + ")", 0.01);
          Assertion.equals(crystCoords[1], -(ydim / 2) + (y / resolution),
              "crystal coordinate y axis for voxel (" + x + ", " + y + ", " + z + ")", 0.01);
          Assertion.equals(crystCoords[2], -(zdim / 2) + (z / resolution),
              "crystal coordinate z axis for voxel (" + x + ", " + y + ", " + z + ")", 0.01);
          
          c.setupDepthFinding(0, w);

          double depth = c.findDepth(crystCoords, 0, w);
          // The depth finding overestimates by 10/resolution :(
          // depth -= (10 / resolution);

          // Because the crystal has not been rotated,
          // the depth should just be z / resolution
          Assertion.equals(depth, z / resolution, "depth at z=" + z, 2.0);
        }
      }
    }
  }

  @Test
  public static void testFindDepthConcave()
  {
    double xdim = 30, ydim = 20, zdim = 10; // just like in the model file.
    
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
    // part of the crystal
    double [] crystCoordThin = {3, 30.28, 8.4};

    // thinnest part of the crystal
    
    double [] crystCoordThick = {3.5, 29.9, -8.65};
    
    c.setupDepthFinding(0, w);

    double thickDepth = c.findDepth(crystCoordThick, 0, w);
    Assertion.equals(thickDepth, 30.0, "Thick part of crystal about 30 um", 1.0);
    
    double thinDepth = c.findDepth(crystCoordThin, 0, w);
    Assertion.equals(thickDepth, 20.0, "Thin part of crystal about 20 um", 3.0);
    
  }
}
