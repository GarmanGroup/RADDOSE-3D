package se.raddo.raddose3D;

public abstract class Container {

  /**
   * Calculate the fraction by which the beam is attenuated by the container
   *
   * @param beam
   *          The beam object that is used to irradiate the sample
   */
  public abstract void calculateContainerAttenuation(Beam beam);

  /**
   * Construct a string that prints details about the sample container.
   */
  public abstract void containerInformation();

  /**
   * Return the attenuation factor of the container
   *
   * @return
   *         Attenuation factor
   */
  public abstract double getContainerAttenuationFraction();

  /**
   * Return the material from which the container is made
   *
   * @return
   *        Container material
   */
  public abstract String getContainerMaterial();

}
