package se.raddo.raddose3D;

public abstract class CoefCalc {
  public abstract double getDensity();

  @Override
  public abstract String toString();

  /**
   * Calculate cross-sections for the new Wedge/Beam scenario.
   * 
   * @param w
   *          Wedge object
   *          TODO: Can the coefficients ever be dependent on the wedge?
   * @param b
   *          Beam object
   */
  public abstract void updateCoefficients(Wedge w, Beam b);

  /**
   * Returns the current absorption coefficient (photoelectric effect).
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
}
