package se.raddo.raddose3D;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Tests of the different ClassFactory functions.
 */
public class ClassFactoryTest {

  private static final Double BEAMENERGYMARKER = 12.533743110d;

  @Test
  public void testBeamFactoryTopHat() {
    ClassFactory bf = new ClassFactory();

    Beam b = bf.createObject(Beam.class, "Tophat", defaultProperties());
    assertTrue(b instanceof BeamTophat);
    Tolerance.equals(b.getPhotonEnergy(), BEAMENERGYMARKER,
        "Beam Energy");

    System.out.println("@Test - testClassFactoryBeamTopHat");
  }

  @Test
  public void testBeamFactoryGaussian() {
    ClassFactory bf = new ClassFactory();

    Beam b = bf.createObject(Beam.class, "Gaussian", defaultProperties());
    assertTrue(b instanceof BeamGaussian);
    Tolerance.equals(b.getPhotonEnergy(), BEAMENERGYMARKER,
        "Beam Energy");

    System.out.println("@Test - testClassFactoryBeamGaussian");
  }

  @Test
  public void testClassFactoryShouldFailOnInvalidInput() {
    assertThrows(RuntimeException.class, () -> {
      ClassFactory cf = new ClassFactory();
      cf.createObject(Beam.class, "invalid", defaultProperties());
    });
  }

  @Test
  public void testClassFactoryShouldFailOnEmptyInput() {
    assertThrows(IllegalArgumentException.class, () -> {
      ClassFactory cf = new ClassFactory();
      cf.createObject(Beam.class, "", defaultProperties());
    });
  }

  @Test
  public void testClassFactoryShouldFailOnNullInput() {
    assertThrows(IllegalArgumentException.class, () -> {
      ClassFactory cf = new ClassFactory();
      cf.createObject(Beam.class, null, defaultProperties());
    });
  }

  @Test
  public void testClassFactoryDummy() {
    ClassFactory cf = new ClassFactory();

    Beam b = cf.createObject(Beam.class, "se.raddo.raddose3D.BeamDummy",
        defaultProperties());
    assertTrue(b instanceof BeamDummy);

    System.out.println("@Test - testClassFactoryBeamDummy");
  }

  @Test
  public void testClassFactoryShouldFailOnNullClasses() {
    assertThrows(IllegalArgumentException.class, () -> {
      ClassFactory cf = new ClassFactory();
      cf.createObject(null, "se.raddo.raddose3D.Crystal", defaultProperties());
    });
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
