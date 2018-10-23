package se.raddo.raddose3D;

import java.util.Map;
import java.util.Set;

/**
 * Uses average values, assuming 50% solvent content.
 * Absorption and Attenuation coefficients are based on Holton 2010 (Absorption
 * coefficient: 0.237/mm, Attenuation coefficient: 0.281/mm), which uses average
 * composition from a survey of the PDB (Berman 2002).
 * Density is taken from Fischer et al., 2004: 1.41 g/ml for large proteins,
 * 50% solvent (solvent mass ~1g/lml).
 */
public class CoefCalcAverage extends CoefCalc {

  /**
   * Absorption Coefficient in um^-1.
   * From Holton 2010 using average composition from Berman 2002 (50/50
   * solvent/protein).
   */
  private static final double ABSORPTION_COEFFICIENT  = 0.000237;

  /**
   * Attenuation Coefficient in um^-1.
   * Based on Holton 2010 using average composition from Berman 2002 (50/50
   * solvent/protein).
   */
  private static final double ATTENUATION_COEFFICIENT = 0.000281;

  /**
   * Elastic Coefficient in um^-1.
   * Based on Holton 2010 using average composition from Berman 2002 (50/50
   * solvent/protein).
   */
  private static final double ELASTIC_COEFFICIENT     = 0.00001799;

  /**
   * Density in g/ml.
   * Average of density given in Fischer & Polikarpov 2004 (1.41 g/ml for large
   * proteins) and 50% solvent (~1g/ml).
   */
  private static final double DENSITY                 = 1.2;
  
  /**
   * Number of X-ray Fluorescent escape factors
   */
  private static final int NUM_FLUOR_ESCAPE_FACTORS  = 27;

  @Override
  public double getAbsorptionCoefficient() {
    return ABSORPTION_COEFFICIENT;
  }

  @Override
  public double getAttenuationCoefficient() {
    return ATTENUATION_COEFFICIENT;
  }

  @Override
  public double getElasticCoefficient() {
    return ELASTIC_COEFFICIENT;
  }

  @Override
  public double getDensity() {
    return DENSITY;
  }

  @Override
  public String toString() {
    return String.format(
        "Dummy crystal coefficients used.%n"
            + "Absorption coefficient: %.2e /um.%n"
            + "Attenuation coefficient: %.2e /um.%n"
            + "Elastic coefficient: %.2e /um.%n"
            + "Density: %.2f g/ml.%n",
        ABSORPTION_COEFFICIENT, ATTENUATION_COEFFICIENT, ELASTIC_COEFFICIENT,
        DENSITY);
  }

  @Override
  public void updateCoefficients(final Beam b) {
    // Does nothing
  }
  
  @Override
  public void updateCryoCoefficients(final Beam b) {
    // Does nothing
  }

  @Override
  public double[][] getFluorescentEscapeFactors(Beam beam) {
    System.out.println("********** WARNING **********");
    System.out.println("No X-ray Fluorescent escape correction is implemented " +
    "for the 'Average/Dummy' calculated crystal composition.");
    System.out.println("No X-ray Fluorescent escape correction is being applied.");
    double[][] fluorEscapeFactors = new double[1][NUM_FLUOR_ESCAPE_FACTORS];
    for (int i = 0; i < NUM_FLUOR_ESCAPE_FACTORS; i++){
      fluorEscapeFactors[0][i] = 0.0;
    }
    return fluorEscapeFactors;
  }

  @Override
  public double getInelasticCoefficient() {
/*    Does nothing, needed to prevent error in this class when Compton was added, 
       Compton is already included in ABSORPTION_COEFFICIENT in this class;*/
    return 0;
  }
  
  @Override
  public double getCryoAbsorptionCoefficient() {
    return 0;
  }
  
  @Override
  public double getCryoDensity() {
    return 0;
  }
  
  @Override
  public boolean isCryo() {
    return false;
  }
  
  @Override
  public double[][] getCryoFluorescentEscapeFactors(Beam beam) {
    double[][] fluorEscapeFactors = new double[1][NUM_FLUOR_ESCAPE_FACTORS];
    for (int i = 0; i < NUM_FLUOR_ESCAPE_FACTORS; i++){
      fluorEscapeFactors[0][i] = 0.0;
    }
    return fluorEscapeFactors;
  }
  
  @Override
  public Set<Element> getPresentElements(boolean cryo){
    return null;
  }

  @Override
  public double getElectronElastic(double avgEnergy) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getElectronInelastic(double avgEnergy, double exposedVolume) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getStoppingPower(double avgEnergy) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getEta() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Map<String, Double> getFractionElementEM() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double getElectronElasticMFPL(double electronEnergy) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getRutherfordScreening(double electronEnergy) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getFSELambda(double FSExSection) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double betheIonisationxSection(double electronEnergy) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Map<Element, Double> getInnerShellProbs() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double getEMFlAbsCoef(double flEnergy) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Map<Element, Double> getElasticProbs() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void calculateSterheimerFactor() {
    // TODO Auto-generated method stub
    
  }
  
}
