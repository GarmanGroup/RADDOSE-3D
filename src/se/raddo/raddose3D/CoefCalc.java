package se.raddo.raddose3D;

public abstract class CoefCalc {
  /**
   * Calculate cross-sections for the new Beam.
   * 
   * @param b
   *          Beam object
   */
  public abstract void updateCoefficients(Beam b);

  /**
   * Returns the current absorption coefficient. (Photoelectric and Compton)
   * 
   * @return
   *         absorption coefficient in units m^-1 (tbc)
   */
  public abstract double getAbsorptionCoefficient();

  /**
   * Returns the current attenuation coefficient (photoelectric effect +
   * inelastic/compton scattering + elastic/rayleigh scattering).
   * 
   * @return
   *         attenuation coefficient in units m^-1 (tbc)
   */
  public abstract double getAttenuationCoefficient();

  /**
   * Returns the current elastic X-ray (rayleigh, coherent) scattering
   * coefficient.
   * 
   * @return
   *         elastic scattering coefficient in units m^-1 (tbc)
   */
  public abstract double getElasticCoefficient();

  /**
   * Returns the current inelastic X-ray (compton, incoherent) scattering
   * coefficient.
   * 
   * @return
   *         inelastic scattering coefficient in units m^-1 (tbc)
   */
  public abstract double getInelasticCoefficient();
  
  /**
   * Returns the density of the crystal.
   * 
   * @return
   *         crystal density in g/ml.
   */ 
  
  public abstract double getDensity();
  
  /**
   * Calculates the absorbed energy within the crystal corrected for
   * X-ray Fluorescence.
   * 
   * @param beam
   *          Beam object
   * @return
   *         absorbed energy.
   */
  public abstract double[][] getFluorescentEscapeFactors(Beam beam);
  
  /**
   * Calculate cryo-cross-sections for the new Beam.
   * 
   * @param b
   *          Beam object
   */
  public abstract void updateCryoCoefficients(Beam b);
  
  /**
   * Returns the current cryo absorption coefficient. (Photoelectric and Compton)
   * 
   * @return
   *         absorption coefficient in units m^-1 (tbc)
   */
  public abstract double getCryoAbsorptionCoefficient();
  
  /**
   * Returns the density of the cryo-solution.
   * 
   * @return
   *         cryo-solution density in g/ml.
   */ 
  
  public abstract double getCryoDensity();
  
  /**
   * @return
   *        if cryo solution has been entered or not
   */
  public abstract boolean isCryo();
}
