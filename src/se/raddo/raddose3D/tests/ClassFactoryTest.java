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
    ClassFactory<Beam> bf = new ClassFactory<Beam>(Beam.class);

    Beam b = bf.createObject("Tophat", defaultProperties());
    Assert.assertTrue(b instanceof BeamTophat);
    Assertion.equals(b.getPhotonEnergy(), BEAMENERGYMARKER,
        "Beam Energy");

    System.out.println("@Test - testClassFactoryBeamTopHat");
  }

  @Test
  public void testBeamFactoryGaussian() {
    ClassFactory<Beam> bf = new ClassFactory<Beam>(Beam.class);

    Beam b = bf.createObject("Gaussian", defaultProperties());
    Assert.assertTrue(b instanceof BeamGaussian);
    Assertion.equals(b.getPhotonEnergy(), BEAMENERGYMARKER,
        "Beam Energy");

    System.out.println("@Test - testClassFactoryBeamGaussian");
  }

  @Test
  public void testBeamFactoryInvalid() {
    ClassFactory<Beam> bf = new ClassFactory<Beam>(Beam.class);

    try {
      bf.createObject("invalid", defaultProperties());
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("@Test - testClassFactoryBeamInvalid");
      return;
    }

    Assert.fail("BeamFactory accepted invalid input (should fail)");
  }

  @Test
  public void testBeamFactoryEmpty() {
    ClassFactory<Beam> bf = new ClassFactory<Beam>(Beam.class);

    try {
      bf.createObject("", defaultProperties());
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      System.out.println("@Test - testClassFactoryBeamEmpty");
      return;
    }

    Assert.fail("BeamFactory accepted empty input (should fail)");
  }

  @Test
  public void testBeamFactoryNull() {
    ClassFactory<Beam> bf = new ClassFactory<Beam>(Beam.class);

    try {
      bf.createObject(null, defaultProperties());
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      System.out.println("@Test - testClassFactoryBeamNull");
      return;
    }

    Assert.fail("BeamFactory accepted null input (should fail)");
  }

  @Test
  public void testBeamFactoryDummy() {
    ClassFactory<Beam> bf = new ClassFactory<Beam>(Beam.class);

    Beam b = bf.createObject("se.raddo.raddose3D.tests.BeamDummy",
        defaultProperties());

    Assert.assertTrue(b instanceof BeamDummy);

    System.out.println("@Test - testClassFactoryBeamDummy");
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
