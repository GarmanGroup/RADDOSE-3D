package se.raddo.raddose3D;

/**
 * Classes implementing this interface can subscribe to {@link Experiment} and
 * receive events such as {@link Crystal} and {@link Beam} initialisation and
 * {@link Wedge} exposure.
 * 
 * @author Markus Gerstel
 */

public interface Output {

  /** Constant for data fields in Map constructors: Primary Writer object. */
  public static final String OUTPUT_WRITER            = "OUT_WRITER";

  /**
   * Constant for data fields in Map constructors: Secondary Writer object.
   * Only relevant for output modules that produce more than one file,
   * eg. OutputDoseStatePOV
   */
  public static final String OUTPUT_WRITER_EXTFILE    = "OUT_WRITER_EXT";

  /** Constant for data fields in Map constructors: Command line options. */
  // not yet used
  // public static final String OUTPUT_COMMANDLINE       = "OUT_COMMANDLINE";

  /** Constant for data fields in Map constructors: Free text. */
  public static final String OUTPUT_TEXT              = "OUT_TEXT";

  /** Constant for data fields in Map constructors: Number of histogram bins. */
  public static final String OUTPUT_HISTBINS          = "OUT_HBINS";
  /** Constant for data fields in Map constructors: Histogram minimum. */
  public static final String OUTPUT_HISTMIN           = "OUT_HMIN";
  /** Constant for data fields in Map constructors: Histogram maximum. */
  public static final String OUTPUT_HISTMAX           = "OUT_HMAX";

  /** Constant for data fields in Map constructors: TAD. */
  public static final String OUTPUT_ABS_ENERGY_THRESH = "OUT_AET";

  /**
   * A new {@link Crystal} has been registered.
   * 
   * @param c
   *          Crystal object
   */
  public void publishCrystal(Crystal c);

  /**
   * A new {@link Beam} has been set up.
   * 
   * @param b
   *          Beam before exposure
   */
  public void publishBeam(Beam b);

  /**
   * A new {@link Wedge} of the crystal has been exposed.
   * 
   * @param w
   *          Wedge after exposure
   */
  public void publishWedge(Wedge w);

  /**
   * Last call a subscriber will receive. Implementing classes are expected to
   * clean up and close files. They should also drop references to stored
   * objects at this point.
   */
  public void close();
}
