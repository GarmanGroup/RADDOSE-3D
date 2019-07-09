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
  
  private final Double energyFWHM;

  /** Attenuated beam flux.  */
  private double attenuatedPhotonsPerSec;
  
  private boolean isCircular;

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
    energyFWHM = (Double) properties.get(Beam.ENERGY_FWHM);
    
    if (properties.get(Beam.BEAM_CIRCULAR) == "TRUE") {
      isCircular = true;
    }
    else {
      isCircular = false;
    }
   
  }

  @Override
  // returns Joules/sec*um^2
  public double beamIntensity(final double coordX, final double coordY,
      final double offAxisUM) {

    // Test to see if it's in the beam, if so assign even fluence, if not, no
    // fluence.
    if (isCircular == false) {
      if (Math.abs(coordX - offAxisUM) <= beamXum / 2
        && Math.abs(coordY) <= beamYum / 2) {
        
      return KEVTOJOULES * attenuatedPhotonsPerSec * photonEnergy
          / (beamXum * beamYum);
      } else {
        return 0d;
      }
    }
    else {
      if (((Math.pow(coordX - offAxisUM, 2)/Math.pow(beamXum/2, 2)) + 
          (Math.pow(coordY, 2)/Math.pow(beamYum/2, 2))) <= 1) {  //check if inside the ellipse
        return KEVTOJOULES * attenuatedPhotonsPerSec * photonEnergy
            / (Math.PI * (beamXum/2) * (beamYum/2));
      }
      else {
      return 0d;
      }
     
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
  public Double getEnergyFWHM() {
    return energyFWHM;
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

  @Override
  public double beamMinumumDimension() {
    return Math.min(beamXum, beamYum);
  }

  @Override
  public double getBeamArea() {
    double beamArea = 0;
    if (isCircular == true) {
      beamArea = Math.PI * (beamXum/2) * (beamYum/2);
    }
    else {
      beamArea = beamXum * beamYum;
    }
    return beamArea;
  }
}
