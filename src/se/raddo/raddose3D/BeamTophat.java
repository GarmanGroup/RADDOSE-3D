package se.raddo.raddose3D;

import java.util.Map;

/**
 * Top Hat beam class that returns a constant value within the beam area, and
 * zero outside.
 */

public class BeamTophat implements Beam {

  /** Horizontal and vertical extent of the beam in micrometres. */
  private final Double beamXum, beamYum;

  /** Beam flux. */
  private final Double photonsPerSec;

  /** Beam energy. */
  private final Double photonEnergy;

  /** Attenuated beam flux.  */
  private double attenuatedPhotonsPerSec;

  /**
   * Generic property constructor for Top Hat beams. Extracts all required
   * information from a Map data structure.
   * *
   * Used properties:
   * BEAM_COLL_H - horizontal extent of the beam in micrometres.
   * BEAM_COLL_V - vertical extent of the beam in micrometres.
   * BEAM_FLUX - flux of the beam in photons per second.
   * BEAM_ENERGY - photon energy.
   *
   * @param properties
   *          Map of type <Object, Object> that contains all beam properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Beam} class.
   */
  public BeamTophat(final Map<Object, Object> properties) {
    // Check for valid parameters
    Assertions a = new Assertions("Could not create TopHat beam: ");
    a.checkIsClass(properties.get(Beam.BEAM_COLL_H), Double.class,
        "no horizontal beam collimation specified");
    a.checkIsClass(properties.get(Beam.BEAM_COLL_V), Double.class,
        "no vertical beam collimation specified");
    a.checkIsClass(properties.get(Beam.BEAM_FLUX), Double.class,
        "no beam flux specified");
    a.checkIsClass(properties.get(Beam.BEAM_ENERGY), Double.class,
        "no beam energy specified");

    // Set the final variables for the object
    beamXum = (Double) properties.get(Beam.BEAM_COLL_H);
    beamYum = (Double) properties.get(Beam.BEAM_COLL_V);
    photonsPerSec = (Double) properties.get(Beam.BEAM_FLUX);
    photonEnergy = (Double) properties.get(Beam.BEAM_ENERGY);
  }

  @Override
  // returns Joules/sec*um^2
  public double beamIntensity(final double coordX, final double coordY,
      final double offAxisUM) {

    // Test to see if it's in the beam, if so assign even fluence, if not, no
    // fluence.
    if (Math.abs(coordX - offAxisUM) <= beamXum / 2
        && Math.abs(coordY) <= beamYum / 2) {

      return KEVTOJOULES * attenuatedPhotonsPerSec * photonEnergy
          / (beamXum * beamYum);
    } else {
      return 0d;
    }
  }

  @Override
  public String getDescription() {
    return "Top hat beam, " + beamXum + " by " + beamYum
        + " (x by y) um with " + photonsPerSec + " photons per second at "
        + photonEnergy + "keV.\n";
  }

  @Override
  public double getPhotonsPerSec() {
    return photonsPerSec;
  }

  @Override
  public double getPhotonEnergy() {
    return photonEnergy;
  }
  
  @Override
  public void generateBeamArray() {};

  @Override
  public void applyContainerAttenuation(Container sampleContainer){
    attenuatedPhotonsPerSec = photonsPerSec
        * (1 - sampleContainer.getContainerAttenuationFraction());

    if (sampleContainer.getContainerMaterial() != null) {
      String s = String.format("Beam photons per second after container "
          + "attenuation is %.2e photons per second", attenuatedPhotonsPerSec);

      System.out.println(s);
    }
  }
  //for EM
  @Override
  public double getBeamX() {
    return beamXum;
  }
  
  @Override
  public double getBeamY() {
    return beamYum;
  }
}
