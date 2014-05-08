package se.raddo.raddose3D.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.BeamGaussian;
import se.raddo.raddose3D.BeamTophat;
import se.raddo.raddose3D.ClassFactory;

/**
 * Tests of the different ClassFactory functions.
 */
public class ClassFactoryTest {

  private static final Double BEAMENERGYMARKER = 12.533743110d;

  @Test
  public void testBeamFactoryTopHat() {
    ClassFactory bf = new ClassFactory();

    Beam b = bf.createObject(Beam.class, "Tophat", defaultProperties());
    Assert.assertTrue(b instanceof BeamTophat);
    Assertion.equals(b.getPhotonEnergy(), BEAMENERGYMARKER,
        "Beam Energy");

    System.out.println("@Test - testClassFactoryBeamTopHat");
  }

  @Test
  public void testBeamFactoryGaussian() {
    ClassFactory bf = new ClassFactory();

    Beam b = bf.createObject(Beam.class, "Gaussian", defaultProperties());
    Assert.assertTrue(b instanceof BeamGaussian);
    Assertion.equals(b.getPhotonEnergy(), BEAMENERGYMARKER,
        "Beam Energy");

    System.out.println("@Test - testClassFactoryBeamGaussian");
  }

  @Test
  public void testBeamFactoryInvalid() {
    ClassFactory bf = new ClassFactory();

    try {
      bf.createObject(Beam.class, "invalid", defaultProperties());
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("@Test - testClassFactoryBeamInvalid");
      return;
    }

    Assert.fail("BeamFactory accepted invalid input (should fail)");
  }

  @Test
  public void testBeamFactoryEmpty() {
    ClassFactory bf = new ClassFactory();

    try {
      bf.createObject(Beam.class, "", defaultProperties());
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      System.out.println("@Test - testClassFactoryBeamEmpty");
      return;
    }

    Assert.fail("BeamFactory accepted empty input (should fail)");
  }

  @Test
  public void testBeamFactoryNull() {
    ClassFactory bf = new ClassFactory();

    try {
      bf.createObject(Beam.class, null, defaultProperties());
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      System.out.println("@Test - testClassFactoryBeamNull");
      return;
    }

    Assert.fail("BeamFactory accepted null input (should fail)");
  }

  @Test
  public void testBeamFactoryDummy() {
    ClassFactory bf = new ClassFactory();

    Beam b = bf.createObject(Beam.class, "se.raddo.raddose3D.tests.BeamDummy",
        defaultProperties());
    Assert.assertTrue(b instanceof BeamDummy);

    System.out.println("@Test - testClassFactoryBeamDummy");
  }

  @Test
  public void testClassFactoryNull() {
    ClassFactory cf = new ClassFactory();

    try {
      cf.createObject(null, "se.raddo.raddose3D.Crystal", defaultProperties());
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      System.out.println("@Test - testClassFactoryNull");
      return;
    }
  }

  private HashMap<Object, Object> defaultProperties() {
    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Beam.BEAM_ENERGY, BEAMENERGYMARKER);
    properties.put(Beam.BEAM_FLUX, 10d);
    properties.put(Beam.BEAM_COLL_H, 1d);
    properties.put(Beam.BEAM_COLL_V, 1d);
    properties.put(Beam.BEAM_FWHM_X, 1d);
    properties.put(Beam.BEAM_FWHM_Y, 1d);
    properties.put(Beam.BEAM_PIXSIZE_X, 1d);
    properties.put(Beam.BEAM_PIXSIZE_Y, 1d);
    return properties;
  }
}
