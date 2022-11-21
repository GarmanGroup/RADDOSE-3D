package se.raddo.raddose3D;

/**
 * This is a Dose Decay Model class that calculates the Relative
 * Diffraction Efficiency (RDE) according to the model from the
 * Leal et al. (2012) paper. The paper describes the loss of
 * scattering power of a crystal as a product of the expected
 * intensity, the Debye-waller factor and an empirically derived
 * scale factor.
 */
public class DDMBfactor implements DDM {

  /**
   * Decay parameters used in Leal et al. (2012) (eqn 4).
   */

  /**
   * Decay Parameter beta.
   */
  private final double            beta;

  /**
   * Decay Parameter b0.
   */
  private final double            b0;

  /**
   * Decay Parameter gamma.
   */
  private final double            gamma;
  
  
  public DDMBfactor(final Double gamma, final Double b0, final Double beta) {

    //If not all decay parameters have been given in the input file then set their values to zeros
    //Otherwise set them to the values given in the input file.
    if (gamma == null || b0 == null || beta == null) {
      this.beta = 9;
      this.b0 = 9;
      this.gamma = 9;
      System.out.print("No decay parameter values given. ");
      System.out.println("All decay parameters set to 9.");
    } else {
      this.beta = beta;
      this.b0 = b0;
      this.gamma = gamma;

    }
  }
  
  @Override
  public String toString() {
    return "Bfactor DDM.";
  }


;
  @Override
  public double calcDecay(final double dose) {
      double frequency = 1/gamma;
      double Bfactor = b0;
      double dosePerExposure = beta; //MGy per e-/A2
      double weight = Math.exp((-(dose*Bfactor/dosePerExposure)/2)*Math.pow(frequency, 2));
      return weight;
  
  }


}
