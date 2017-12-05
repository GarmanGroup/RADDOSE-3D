package se.raddo.raddose3D;

import java.util.Map;

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
  
  public abstract boolean getIsEM();
  
 // public abstract double getSA();
  
  public abstract double getElectronElastic(Beam beam);
  
  public abstract double getElectronInelastic(Beam beam, double exposedVolume);
  
  public abstract double getEMConc();
  
  public abstract double getEMSolventFraction();
  
  public abstract double getElectronInelasticSolvent(Beam beam);
  
 //  public abstract double numberOfWaters(double exposedVolume);
  
  public abstract void calculateSolventWaterEM(double solventFraction, double exposedVolume);
  
  public abstract void calculateDensityEM(double exposedVolume);
  
  public abstract Map<String, Double> getFractionElementEM();
  
}

