package se.raddo.raddose3D;

public abstract class ContainerSemiTransparent implements Container{
  
  /**
   * Conversion from microns to centimeters
   */
  private static final double MICRONS_TO_CENTIMETERS = 1e-4;
  
  /**
   * Conversion of beam energy from MeV to KeV
   */
  protected static final double MEV_TO_KEV             = 1e3;
  
  /**
   * The thickness of the container in microns
   */
  protected final double        thickness;
  
  /**
   * The density of the material in grams per centimetre cubed
   */
  protected final double        density;
  
  
  /**
   * The mass thickness of the sample. On the NIST website the mass
   * thickness is defined as the mass per unit area.
   */
  protected double              massThickness;
  
  /**
   * The total fraction attenuation of the X-ray beam due to the container
   */
  protected double              containerAttenuationFraction;
  
  /**
   * The mass attenuation coefficient of the sample
   */
  protected double              massAttenuationCoefficient;

  /**
   * The material that the container is made from
   */
  protected String              material;
  
  public ContainerSemiTransparent(Double conThickness, Double conDensity, 
      String conMaterial) {
    /**
     * Initialise the instance variables
     */
    if (conThickness == null && conDensity == null && 
        (conMaterial == null || conMaterial == "")) {
      this.thickness = 0;
      this.density = 0;
      this.material = null;

    } else if (conThickness == null || conDensity == null || 
        conMaterial == null || conMaterial == "") {
      System.out.print("Not all of the container fields have been defined. ");
      System.out.println("Assuming no container around sample.");
      this.thickness = 0;
      this.density = 0;
      this.material = null;

    } else {
      this.thickness = conThickness;
      this.density = conDensity;
      this.material = conMaterial;
    }
  }
  
  /**
   * This method updates the mass attenuation coefficient value used
   * to determine the overall beam attenuation.
   *
   * @param beam
   *          beam object describing the beam used.
   */
  abstract void extractMassAttenuationCoef(Beam beam);
  
  /**
   * Calculate the mass thickness of the container. The mass thickness is
   * defined as the mass per unit area.
   */
  protected void calculateMassThickness() {
    //Convert container thickness units from microns to centimeters.
    double thicknessInCentimeters = MICRONS_TO_CENTIMETERS * this.thickness;
    this.massThickness = this.density * thicknessInCentimeters;
  }

  /**
   * Calculate the fraction by which the beam is attenuated by the container
   *
   * @param beam
   *          The beam object that is used to irradiate the sample
   */
  @Override
  public void calculateContainerAttenuation(Beam beam) {
    extractMassAttenuationCoef(beam);
    calculateMassThickness();

    this.containerAttenuationFraction = 1 - Math
        .exp(-this.massAttenuationCoefficient
            * this.massThickness);
  }

  /**
   * Construct a string that prints details about the sample container.
   */
  @Override
  public void containerInformation() {
    if (this.material != null) {
      String s = String.format(
          "The mass attenuation coefficient of the %s container "
              + "is %.2f centimetres^2 per gram.%n"
              + "The attenuation fraction of the beam due to the sample"
              + " container of thickness %.2f microns is: %.2f.%n"
          , this.material, this.massAttenuationCoefficient
          , this.thickness, this.containerAttenuationFraction);

      System.out.printf(s);
    }
  }
  
  /**
   * Return the material from which the container is made
   *
   * @return
   *        Container material
   */
  @Override
  public String getContainerMaterial() {
    return this.material;
  }

  /**
   * Return the attenuation factor of the container
   *
   * @return
   *         Attenuation factor
   */
  @Override
  public double getContainerAttenuationFraction() {
    return this.containerAttenuationFraction;
  }
  
  /**
   * Return the mass attenuation coefficient of the container
   *
   * @return
   *         Mass attenuation coefficient
   */
  public double getMassAttenuationCoefficient() {
    return this.massAttenuationCoefficient;
  }
  
  /**
   * Return the mass thickness of the container
   *
   * @return
   *         Mass thickness of the sample
   */
  public double getMassThickness() {
    return this.massThickness;
  }

}
