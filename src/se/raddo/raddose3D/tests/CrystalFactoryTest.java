package se.raddo.raddose3D.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.*;

import se.raddo.raddose3D.CoefCalcAverage;
import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.CrystalCuboid;
import se.raddo.raddose3D.CrystalFactory;
import se.raddo.raddose3D.CrystalSpherical;
import se.raddo.raddose3D.DDMSimple;

public class CrystalFactoryTest {
  private static final Double CRYSTALRESOLUTIONMARKER = 0.533743110d;

  @Test
  public void testCrystalFactoryCuboid() {
    CrystalFactory cf = new CrystalFactory();

    Crystal c = cf.createCrystal("cuboid", defaultProperties());
    Assert.assertTrue(c instanceof CrystalCuboid);
    Assertion.equals(c.getCrystalPixPerUM(), CRYSTALRESOLUTIONMARKER,
        "Resolution");

    System.out.println("@Test - testCrystalFactoryCuboid");
  }

  @Test
  public void testCrystalFactorySpherical() {
    CrystalFactory cf = new CrystalFactory();

    Crystal c = cf.createCrystal("spherical", defaultProperties());
    Assert.assertTrue(c instanceof CrystalSpherical);
    Assertion.equals(c.getCrystalPixPerUM(), CRYSTALRESOLUTIONMARKER,
        "Resolution");

    System.out.println("@Test - testCrystalFactorySpherical");
  }

  @Test
  public void testCrystalFactoryInvalid() {
    CrystalFactory cf = new CrystalFactory();

    try {
      cf.createCrystal("invalid", defaultProperties());
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("@Test - testCrystalFactoryInvalid");
      return;
    }

    Assert.fail("CrystalFactory accepted invalid input (should fail)");
  }

  @Test
  public void testCrystalFactoryEmpty() {
    CrystalFactory cf = new CrystalFactory();

    try {
      cf.createCrystal("", defaultProperties());
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("@Test - testCrystalFactoryEmpty");
      return;
    }

    Assert.fail("CrystalFactory accepted empty input (should fail)");
  }

  @Test
  public void testCrystalFactoryFactory() {
    CrystalFactory cf = new CrystalFactory();

    try {
      cf.createCrystal("factory", defaultProperties());
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("@Test - testCrystalFactoryFactory");
      return;
    }

    Assert.fail("CrystalFactory accepted input 'factory' (should fail)");
  }

  @Test
  public void testCrystalFactoryDummy() {
    CrystalFactory cf = new CrystalFactory();

    Crystal c = cf.createCrystal("se.raddo.raddose3D.tests.CrystalDummy",
        defaultProperties());
    Assert.assertTrue(c instanceof CrystalDummy);

    System.out.println("@Test - testCrystalFactoryDummy");
  }

  private HashMap<Object, Object> defaultProperties() {
    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_COEFCALC, new CoefCalcAverage());
    properties.put(Crystal.CRYSTAL_DDM, new DDMSimple());
    properties.put(Crystal.CRYSTAL_RESOLUTION, CRYSTALRESOLUTIONMARKER);
    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    properties.put(Crystal.CRYSTAL_DIM_X, 10d);
    properties.put(Crystal.CRYSTAL_DIM_Y, 10d);
    properties.put(Crystal.CRYSTAL_DIM_Z, 10d);
    return properties;
  }
}
