package se.raddo.raddose3D;

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
  public double getInelasticCoefficient() {
    // Added to prevent error
    return 0;
  }
}
