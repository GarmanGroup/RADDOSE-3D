package se.raddo.raddose3D;

/**
 * Beam interface for an MX beam, that can interact with expose and output
 * methods.
 */

public interface Beam {

  /** Constant for data fields in Map constructors: Horizontal collimation. */
  public static final String BEAM_COLL_H      = "COLL_H";
  /** Constant for data fields in Map constructors: Vertical collimation. */
  public static final String BEAM_COLL_V      = "COLL_V";
  /** Constant for data fields in Map constructors: Beam energy. */
  public static final String BEAM_ENERGY      = "ENERGY";
  /** Constant for data fields in Map constructors: External filename. */
  public static final String BEAM_EXTFILE     = "EXTFILE";
  /** Constant for data fields in Map constructors: FWHM X. */
  public static final String BEAM_FWHM_X      = "FHWM_X";
  /** Constant for data fields in Map constructors: FWHM Y. */
  public static final String BEAM_FWHM_Y      = "FHWM_Y";
  /** Constant for data fields in Map constructors: Beam flux. */
  public static final String BEAM_FLUX        = "FLUX";
  /** Constant for data fields in Map constructors: Pixel size X. */
  public static final String BEAM_PIXSIZE_X   = "PIXSIZE_X";
  /** Constant for data fields in Map constructors: Pixel size Y. */
  public static final String BEAM_PIXSIZE_Y   = "PIXSIZE_Y";

  public static final String BEAM_CIRCULAR   = "CIRCULAR";
  
  /** Constant for data fields in Map constructors: Beam exposure. */
  public static final String BEAM_EXPOSURE        = "EXPOSURE";
  
  public static final String BEAM_SEMIANGLE = "SEMIANGLE";
  public static final String BEAM_APERTURERADIUS = "APERTURERADIUS";
  
  public static final String IMAGE_X = "IMAGE_X";
  public static final String IMAGE_Y = "IMAGE_Y";
  
  /** The elementary charge, 1.6*10^-19 coulombs. */
  public static final Double ELEMENTARYCHARGE = 1.602176565e-19;

  /** Conversion factor from keV to Joules. */
  public static final Double KEVTOJOULES      = 1000 * ELEMENTARYCHARGE;

  
  /**
   * Returns the mean intensity at position X, Y in joules/um^2/s.
   *
   * @param coordX
   * @param coordY
   * @param offAxisUM
   * @return Beam intensity at position X, Y in joules/um^2/s
   */
  public double beamIntensity(double coordX, double coordY, double offAxisUM);

  /**
   * Returns a short description of the beam, ideally one line.
   * It should contain the beam type, flux, and energy.
   *
   * @return
   *         Description of the Beam.
   */
  public String getDescription();

  /**
   * Returns flux of the beam.
   *
   * @return
   *         Beam flux in photons per second.
   */
  public double getPhotonsPerSec();

  /**
   * Returns photon energy of the beam.
   *
   * @return
   *         Photon energy in keV.
   */
  public double getPhotonEnergy();
  
  /**
   * Generates a beam array
   */
  public void generateBeamArray();
  
  /**
   * Applies flux attenuation due to the sample container.
   */
  public void applyContainerAttenuation(Container sampleContainer);
  
/**
 * gets the minuimumn beam dimension
 * @return
 *          beam min dim
 */
  public double beamMinumumDimension();
  
  public double getBeamArea();
  
  /**
   * Returns exposure of the beam.
   *
   * @return
   *         Beam exposure in electrons per Angstrom squared.
   */
  public double getExposure();
  
  public double getBeamX();
  
  public double getBeamY();
  
  /**
   * Returns true if the beam is circularly collimated
   * @return
   *        circular coll or not
   */
  public boolean getIsCircular();
  
  public double getSemiAngle();
  public double getApertureRadius();
  
  public double getImageX();
  public double getImageY();
  
}
