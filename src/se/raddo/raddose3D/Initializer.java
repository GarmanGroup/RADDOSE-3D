package se.raddo.raddose3D;

/**
 * Interface to which a stream of experimental objects can be sent.
 */
public interface Initializer extends ExperimentNotices {

  /**
   * Set a new Crystal object for following exposures.
   * 
   * @param c
   *          Crystal object to be used. It is the responsibility of the
   *          implementing class to check against null values.
   */
  public void setCrystal(Crystal c);

  /**
   * Set a new Beam object for following exposures.
   * 
   * @param b
   *          Beam object to be used. It is the responsibility of the
   *          implementing class to check against null values.
   */
  public void setBeam(Beam b);

  /**
   * Expose the previously given Crystal object using the
   * previously given Beam and Expose objects according
   * to the given Wedge object.
   * 
   * @param w
   *          Wedge object to be used for exposure. It is the responsibility of
   *          the implementing class to check against null values.
   */
  public void exposeWedge(Wedge w);
}
