package se.raddo.raddose3D;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


public class CrystalFactoryTest {
  private static final Double CRYSTALRESOLUTIONMARKER = 0.533743110d;

  @Test
  public void testCrystalFactoryCuboid() {
    CrystalFactory cf = new CrystalFactory();

    Crystal c = cf.createCrystal("cuboid", defaultProperties());
    assertTrue(c instanceof CrystalCuboid);
    Tolerance.equals(c.getCrystalPixPerUM(), CRYSTALRESOLUTIONMARKER,
        "Resolution");

    System.out.println("@Test - testCrystalFactoryCuboid");
  }

  @Test
  public void testCrystalFactorySpherical() {
    CrystalFactory cf = new CrystalFactory();

    Crystal c = cf.createCrystal("spherical", defaultProperties());
    assertTrue(c instanceof CrystalSphericalNew);
    Tolerance.equals(c.getCrystalPixPerUM(), CRYSTALRESOLUTIONMARKER,
        "Resolution");

    System.out.println("@Test - testCrystalFactorySpherical");
  }

  @Test
  public void testCrystalFactoryShouldFailOnInvalidInput() {
    assertThrows(RuntimeException.class, () -> {
      CrystalFactory cf = new CrystalFactory();
      cf.createCrystal("invalid", defaultProperties());
    });
  }

  @Test
  public void testCrystalFactoryShouldFailOnEmptyInput() {
    assertThrows(RuntimeException.class, () -> {
      CrystalFactory cf = new CrystalFactory();
      cf.createCrystal("", defaultProperties());
    });
  }

  @Test
  public void testCrystalFactoryDummy() {
    CrystalFactory cf = new CrystalFactory();

    Crystal c = cf.createCrystal("se.raddo.raddose3D.CrystalDummy",
        defaultProperties());
    assertTrue(c instanceof CrystalDummy);

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
