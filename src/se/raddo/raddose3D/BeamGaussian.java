package se.raddo.raddose3D;

/**
 * Perfect Gaussian model of a beam. Can be collimated or uncollimated.
 * Flux is defined as total flux within collimated region (defined by beamsize).
 */

import java.util.Map;

import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.distribution.NormalDistribution;

/** An X-ray beam class with a 2d Gaussian beam profile. */
public class BeamGaussian implements Beam {
  /** Horizontal full width half maximum of the beam. */
  private final Double        fwhmX;

  /** Vertical full width half maximum of the beam. */
  private final Double        fwhmY;

  /** Beam flux. */
  private final Double        photonsPerSec;

  /** Beam energy. */
  private final Double        photonEnergy;

  /** Horizontal/Vertical collimation. No collimation if set to null. */
  private final Double        collXum, collYum;

  private final Double        normFactor;

  private double              scaleFactor;

  /** Horizontal/Vertical Gaussian distribution of the beam. */
  private final Gaussian      gX, gY;

  /**
   * Conversion factor between standard deviation and full-width at
   * half-maximum.
   */
  private static final double SIGMA_TO_FWHM = 2 * Math.sqrt(2 * Math.log(2));

  /** Attenuated beam flux  */
  private double attenuatedPhotonsPerSec;
  
  private boolean isCircular;

  /**
   * Generic property constructor for Gaussian beams. Extracts all required
   * information from a Map data structure.
   * *
   * Used properties:
   * BEAM_COLL_H - horizontal collimation of the beam in micrometres. (optional)
   * BEAM_COLL_V - vertical collimation of the beam in micrometres. (optional)
   * BEAM_FWHM_X - horizontal full-width half-maximum.
   * BEAM_FWHM_Y - vertical full-width half-maximum.
   * BEAM_FLUX - flux of the beam in photons per second.
   * BEAM_ENERGY - photon energy.
   *
   * @param properties
   *          Map of type <Object, Object> that contains all beam properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Beam} class.
   */
  public BeamGaussian(final Map<Object, Object> properties) {
    // Check for valid parameters
    Assertions a = new Assertions("Could not create Gaussian beam: ");
    a.checkIsClass(properties.get(Beam.BEAM_FWHM_X), Double.class,
        "no horizontal FWHM specified");
    a.checkIsClass(properties.get(Beam.BEAM_FWHM_Y), Double.class,
        "no vertical FWHM specified");
    a.checkIsClass(properties.get(Beam.BEAM_FLUX), Double.class,
        "no beam flux specified");
    a.checkIsClass(properties.get(Beam.BEAM_ENERGY), Double.class,
        "no beam energy specified");

    photonsPerSec = (Double) properties.get(Beam.BEAM_FLUX);
    photonEnergy = (Double) properties.get(Beam.BEAM_ENERGY);
    fwhmX = (Double) properties.get(Beam.BEAM_FWHM_X);
    fwhmY = (Double) properties.get(Beam.BEAM_FWHM_Y);

    Double sigmaX = fwhmX / SIGMA_TO_FWHM; // Convert to sigma
    Double sigmaY = fwhmY / SIGMA_TO_FWHM; // Convert to sigma

    if ((properties.get(Beam.BEAM_COLL_H) == null)
        && (properties.get(Beam.BEAM_COLL_V) == null)) {
      collXum = null;
      collYum = null;
      normFactor = 1d;
    } else {
      // at the moment only allow no collimation or rectangular collimation
      a.checkIsClass(properties.get(Beam.BEAM_COLL_H), Double.class,
          "no horizontal beam collimation specified");
      a.checkIsClass(properties.get(Beam.BEAM_COLL_V), Double.class,
          "no vertical beam collimation specified");
      collXum = (Double) properties.get(Beam.BEAM_COLL_H);
      collYum = (Double) properties.get(Beam.BEAM_COLL_V);

      /*
       * normfactor is the integral of a normalised Gaussian within the
       * collimated region. It is needed to calculate fluxes.
       */
      if (properties.get(Beam.BEAM_CIRCULAR) == "TRUE") {
        isCircular = true;
      }
      else {
        isCircular = false;
      }
         
      normFactor = bivariateGaussianVolume(-collXum / 2, collXum / 2,
          -collYum / 2, collYum / 2, sigmaX, sigmaY);
    }

    gX = new Gaussian(0, sigmaX);
    gY = new Gaussian(0, sigmaY);
    


  }
  /**
   * Calculate the scale factor for the beam.
   */
  private void calculateScaleFactor(){
    scaleFactor = KEVTOJOULES * photonEnergy * attenuatedPhotonsPerSec
        / normFactor;
  }

  /**
   * Returns the value at a position of the 2d Gaussian.
   * @param x x-position
   * @param y y-position
   * @return 2d Gaussian function value.
   */
  private double gaussianIntensity(final double x, final double y) {
    return gX.value(x) * gY.value(y); 
  }

  /**
   * Find the volume under a bivariate Gaussian using cumulative density
   * functions.
   *
   * @param x1
   *          The lower bound of x.
   * @param x2
   *          The upper bound of x.
   * @param y1
   *          The lower bound of y.
   * @param y2
   *          The upper bound of y.
   * @param sx
   *          standard deviation (sigma) in X direction.
   * @param sy
   *          standard deviation (sigma) in Y direction.
   * @return
   *         The volume under f(x, y).
   */
  private double bivariateGaussianVolume(final double x1, final double x2,
      final double y1, final double y2, final double sx, final double sy) {

    NormalDistribution gx = new NormalDistribution(0, sx);
    NormalDistribution gy = new NormalDistribution(0, sy);
// this needs to change for circular
    double cdf;
//    double test;
 //   double Px = gx.cumulativeProbability(x2);
 //   double Py = gy.cumulativeProbability(y2);
    if (isCircular == false) {
      cdf = gx.cumulativeProbability(x2) * gy.cumulativeProbability(y2);
      cdf -= gx.cumulativeProbability(x1) * gy.cumulativeProbability(y2);  
      cdf -= gx.cumulativeProbability(x2) * gy.cumulativeProbability(y1);
      cdf += gx.cumulativeProbability(x1) * gy.cumulativeProbability(y1);

//      test = (Px - (1-Px)) * (Py - (1 - Py));   // This makes so much more sense to me than what is above!!!
    }
    else {
      //try and integrate it properly
      double stepTheta = (2* Math.PI) / 100;
      double A = 1 / (2* Math.PI * sx * sy);
      double lastHeldHeight = 0, lastHeldTheta = 0;
      double overallSum = 0;
      for (double theta = 0; theta <= 2*Math.PI; theta += stepTheta) {
        double r = (x2 * y2) / (Math.pow(Math.pow(y2 * Math.cos(theta), 2) + Math.pow(x2 * Math.sin(theta), 2), 0.5));
        double cosSquared = Math.pow(Math.cos(theta), 2);
        double sinSquared = Math.pow(Math.sin(theta), 2);
        double cosSPlusSinSTerm = (cosSquared / (2*Math.pow(sx, 2))) + (sinSquared / (2*Math.pow(sy, 2)));
        double oneOver = A / (2*cosSPlusSinSTerm);
        double otherTerm = 1 - Math.exp(-Math.pow(r, 2) * cosSPlusSinSTerm);
        double overallTerm = oneOver * otherTerm; 
        
        if (theta != 0) {
          double area = (theta - lastHeldTheta) * ((lastHeldHeight + overallTerm) / 2);
          overallSum += area;
        }
        lastHeldTheta = theta;
        lastHeldHeight = overallTerm;

      }
      cdf = overallSum;
      
    }
    return cdf;
  }

  @Override
  public String getDescription() {
    String collimation = "";
    if (collXum != null) {
      collimation = String.format("%.1fx%.1f um ", collXum, collYum);
    }

    return String
        .format(
            "Gaussian beam, %swith %.2f by %.2f FWHM "
                + "(x by y) and %.1e photons per second at %.2f keV.%n",
            collimation,
            fwhmX, fwhmY, photonsPerSec, photonEnergy);
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
  public double beamIntensity(final double coordX, final double coordY,
      final double offAxisUM) {
    //Calculate the scale factor
    calculateScaleFactor();

    // Test if beam coordinate is outside collimated area,
    if (isCircular == false) {
      if ((collXum != null) && (Math.abs(coordX - offAxisUM) > collXum / 2)) {
        return 0;
      }
      if ((collYum != null) && (Math.abs(coordY) > collYum / 2)) {
        return 0;
      }
    }
    else {
      if (((Math.pow(coordX - offAxisUM, 2)/Math.pow(collXum/2, 2)) + 
          (Math.pow(coordY, 2)/Math.pow(collYum/2, 2))) > 1) {
        return 0;
      }
    }
    
    // Return normalisedGaussian * scale factor
    return gaussianIntensity((coordX - offAxisUM), coordY) * scaleFactor;
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
    double minCol = Math.min(collXum,  collYum);
    double minFWHM = Math.min(fwhmX, fwhmY);
    return Math.min(minCol,  minFWHM);
  }
  @Override
  public double getBeamArea() {
    // TODO Auto-generated method stub
    return 0;
  }
  @Override
  public double getExposure() {
    // TODO Auto-generated method stub
    return 0;
  }
}
