package se.raddo.raddose3D.tests;

/**
 * Performs basic tests on crystal classes. Includes:
 * - AddDose increments dose correctly
 * - AddFluence increments fluence correctly
 * - Resolution correct
 * - Correct x,y,z range of voxels (Bounding box size)
 * - Voxels initialised correctly
 * Depth Must be tested in individual crystal classes.
 * 
 * @author Oliver Zeldin
 */
import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.*;
import org.testng.annotations.*;

import se.raddo.raddose3D.CoefCalc;
import se.raddo.raddose3D.CoefCalcAverage;
import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.CrystalCuboid;
import se.raddo.raddose3D.CrystalSpherical;

public class CrystalBasicTests {
  private static final Double   res  = 0.78;
  private static final Double   dim1 = 100d;
  private static final Double   dim2 = 125d;
  private static final Double   dim3 = 150d;
  private static final CoefCalc coef = new CoefCalcAverage();

  // Instances of all crystal subClasses should be put here, so that they can be tested. 
  private ArrayList<Crystal> generateCrystals() {
    ArrayList<Crystal> crystArrayList = new ArrayList<Crystal>();
    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, dim1);
    properties.put(Crystal.CRYSTAL_DIM_Y, dim2);
    properties.put(Crystal.CRYSTAL_DIM_Z, dim3);
    properties.put(Crystal.CRYSTAL_RESOLUTION, res);
    properties.put(Crystal.CRYSTAL_COEFCALC, coef);

    crystArrayList.add(new CrystalSpherical(properties));
    crystArrayList.add(new CrystalCuboid(properties));
    return crystArrayList;
  }

  /* *************************************************
   * 
   * General Crystal Classes tests:
   * 
   * ************************************************
   */

  @Test(groups = { "advanced" })
  /** Test that the resolution in the crystal object is the one specified in the constructor*/
  public void testResolution() {
    ArrayList<Crystal> cl = generateCrystals();
    for (Crystal c : cl) {
      assertEquals(c.getCrystalPixPerUM(), res, c.crystalInfo());
    }
  }

  @Test(groups = { "advanced" })
  /** Checks that both initialising and incrementing the dose array works correctly.*/
  public void testAddDose() {
    ArrayList<Crystal> cl = generateCrystals();
    for (Crystal c : cl) {
      c.addDose(1, 2, 3, 0.1);
      assertEquals(c.getDose(1, 2, 3), 0.1, c.crystalInfo()
          + "fails to initialise Dose");

      c.addDose(1, 2, 3, 0.9);
      assertEquals(c.getDose(1, 2, 3), 1.0, c.crystalInfo()
          + "fails to initialise Dose");
    }
  }

  @Test(groups = { "advanced" })
  /** 
   * Checks that both initializing and incrementing the fluence array works
   * correctly.
   */
  public void testAddFluence() {
    ArrayList<Crystal> cl = generateCrystals();
    for (Crystal c : cl) {
      c.addFluence(1, 2, 3, 0.1);
      assertEquals(c.getFluence(1, 2, 3), 0.1, c.crystalInfo()
          + "fails to initialise Fluence.");

      c.addFluence(1, 2, 3, 0.9);
      assertEquals(c.getFluence(1, 2, 3), 1.0, c.crystalInfo()
          + "fails to increment Fluence.");
    }
  }

  @Test(groups = { "advanced" })
  /**
   * Checks all the voxels to confirm that they span the whole crystal and
   * that none of them lie outside the bounding box.
   */
  public void testBoundingBoxCorrectSize() {
    ArrayList<Crystal> cl = generateCrystals();
    for (Crystal c : cl) {
      double minX = java.lang.Double.POSITIVE_INFINITY;
      double minY = java.lang.Double.POSITIVE_INFINITY;
      double minZ = java.lang.Double.POSITIVE_INFINITY;

      double maxX = -1 * java.lang.Double.POSITIVE_INFINITY;
      double maxY = -1 * java.lang.Double.POSITIVE_INFINITY;
      double maxZ = -1 * java.lang.Double.POSITIVE_INFINITY;

      //Check that none of the voxels are outside the box
      for (int i = 0; i < c.getCrystSizeVoxels()[0]; i++) {
        for (int j = 0; j < c.getCrystSizeVoxels()[1]; j++) {
          for (int k = 0; k < c.getCrystSizeVoxels()[2]; k++) {
            if (c.getCrystCoord(i, j, k)[0] < minX)
              minX = c.getCrystCoord(i, j, k)[0];
            if (c.getCrystCoord(i, j, k)[1] < minY)
              minY = c.getCrystCoord(i, j, k)[1];
            if (c.getCrystCoord(i, j, k)[2] < minZ)
              minZ = c.getCrystCoord(i, j, k)[2];

            if (c.getCrystCoord(i, j, k)[0] > maxX)
              maxX = c.getCrystCoord(i, j, k)[0];
            if (c.getCrystCoord(i, j, k)[1] > maxY)
              maxY = c.getCrystCoord(i, j, k)[1];
            if (c.getCrystCoord(i, j, k)[2] > maxZ)
              maxZ = c.getCrystCoord(i, j, k)[2];
          }
        }
      }

      assertTrue(
          Math.abs(minX + (c.getCrystSizeUM()[0] / 2)) < 1 / c
              .getCrystalPixPerUM(),
          c.crystalInfo()
              + "minX  value more than 1 pixRes unit away from specified size.");
      assertTrue(
          Math.abs(minY + (c.getCrystSizeUM()[1] / 2)) < 1 / c
              .getCrystalPixPerUM(),
          c.crystalInfo()
              + "minY  value more than 1 pixRes unit away from specified size.");
      assertTrue(
          Math.abs(minZ + (c.getCrystSizeUM()[2] / 2)) < 1 / c
              .getCrystalPixPerUM(),
          c.crystalInfo()
              + "minZ  value more than 1 pixRes unit away from specified size.");

      assertTrue(
          Math.abs(maxX - (c.getCrystSizeUM()[0] / 2)) < 1 / c
              .getCrystalPixPerUM(),
          c.crystalInfo()
              + "maxX  value more than 1 pixRes unit away from specified size.");
      assertTrue(
          Math.abs(maxY - (c.getCrystSizeUM()[1] / 2)) < 1 / c
              .getCrystalPixPerUM(),
          c.crystalInfo()
              + "maxY  value more than 1 pixRes unit away from specified size.");
      assertTrue(
          Math.abs(maxZ - (c.getCrystSizeUM()[2] / 2)) < 1 / c
              .getCrystalPixPerUM(),
          c.crystalInfo()
              + "maxZ  value more than 1 pixRes unit away from specified size.");
    }
  }

  @Test(groups = { "advanced" })
  /** Checks that all the voxels in the voxel array are initialised and not null. */
  public void allVoxelsInitialised() {
    ArrayList<Crystal> cl = generateCrystals();
    for (Crystal c : cl) {
      //Count the voxels, check that noone of the coordinates are null
      for (int i = 0; i < c.getCrystSizeVoxels()[0]; i++) {
        for (int j = 0; j < c.getCrystSizeVoxels()[1]; j++) {
          for (int k = 0; k < c.getCrystSizeVoxels()[2]; k++) {
            assertNotNull(c.getCrystCoord(i, j, k), c.crystalInfo()
                + "crystCoord has null voxels at i,j,k = " + i + ", " + j
                + ", " + k);
          }
        }
      }
    }
  }
}
