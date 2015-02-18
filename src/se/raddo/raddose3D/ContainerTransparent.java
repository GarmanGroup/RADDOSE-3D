package se.raddo.raddose3D;

public class ContainerTransparent extends Container {

  /**
   * The material that the container is made from
   */
  private final String        material;

  /**
   * The total fraction attenuation of the X-ray beam due to the container
   */
  private final double        containerAttenuationFraction;

  /**
   * Constructor for the ContainerTransparent class.
   * Since the container effectively acts as completely "transparent"
   * it is the same as having no material and no attenuation.
   */
  public ContainerTransparent() {
    this.material = null;
    this.containerAttenuationFraction = 0;
  }

  @Override
  public void calculateContainerAttenuation(Beam beam) {
    }

  /**
   * Construct a string that prints details about the sample container.
   */
  @Override
  public void containerInformation() {
      String s = String.format(
          "No container has been specified.\n");

      System.out.printf(s);

  }

  /**
   * Return the attenuation factor of the container.
   * Since the container is transparent the attenuation fraction
   * is 0.
   *
   * @return
   *         Attenuation factor
   */
  @Override
  public double getContainerAttenuationFraction() {
    return this.containerAttenuationFraction;
  }

  /**
   * Return the material from which the container is made.
   * In this class the container is completely "transparent"
   * so it has no material. Hence it will return null.
   *
   * @return
   *        Container material
   */
  @Override
  public String getContainerMaterial() {
    return this.material;
  }

}
