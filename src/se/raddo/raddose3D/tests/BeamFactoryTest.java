package se.raddo.raddose3D.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.*;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.BeamFactory;
import se.raddo.raddose3D.BeamGaussian;
import se.raddo.raddose3D.BeamTophat;

public class BeamFactoryTest {

  private static final Double BEAMENERGYMARKER = 12.533743110d;

  @Test
  public void testBeamFactoryTopHat() {
    BeamFactory bf = new BeamFactory();

    Beam b = bf.createBeam("tophat", defaultProperties());
    Assert.assertTrue(b instanceof BeamTophat);
    Assertion.equals(b.getPhotonEnergy(), BEAMENERGYMARKER,
        "Beam Energy");

    System.out.println("@Test - testBeamFactoryTopHat");
  }

  @Test
  public void testBeamFactoryGaussian() {
    BeamFactory bf = new BeamFactory();

    Beam b = bf.createBeam("gaussian", defaultProperties());
    Assert.assertTrue(b instanceof BeamGaussian);
    Assertion.equals(b.getPhotonEnergy(), BEAMENERGYMARKER,
        "Beam Energy");

    System.out.println("@Test - testBeamFactoryGaussian");
  }

  @Test
  public void testBeamFactoryInvalid() {
    BeamFactory bf = new BeamFactory();

    try {
      bf.createBeam("invalid", defaultProperties());
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("@Test - testBeamFactoryInvalid");
      return;
    }

    Assert.fail("BeamFactory accepted invalid input (should fail)");
  }

  @Test
  public void testBeamFactoryEmpty() {
    BeamFactory bf = new BeamFactory();

    try {
      bf.createBeam("", defaultProperties());
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("@Test - testBeamFactoryEmpty");
      return;
    }

    Assert.fail("BeamFactory accepted empty input (should fail)");
  }

  @Test
  public void testBeamFactoryFactory() {
    BeamFactory bf = new BeamFactory();

    try {
      bf.createBeam("", defaultProperties());
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("@Test - testBeamFactoryFactory");
      return;
    }

    Assert.fail("BeamFactory accepted input 'factory' (should fail)");
  }

  @Test
  public void testBeamFactoryDummy() {
    BeamFactory bf = new BeamFactory();

    Beam b = bf.createBeam("se.raddo.raddose3D.tests.BeamDummy",
        defaultProperties());

    Assert.assertTrue(b instanceof BeamDummy);

    System.out.println("@Test - testBeamFactoryDummy");
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
