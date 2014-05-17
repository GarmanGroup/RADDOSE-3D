package se.raddo.raddose3D;

/**
 * This is a Dose Decay Model (DDM) class that calculates the Relative
 * Diffraction Efficiency (RDE) for a voxel with a given dose. The DDM
 * is linear decrease in the RDE based on data from the Owen et al. (2006)
 * paper. Once the decay reaches a value given by DDM_FACTOR_OWEN_2006
 * the Decay has reached maximum and the RDE is 0.
 */
public class DDMLinear implements DDM {

  /** from Owen et al. 2006. */
  private static final Double DDM_FACTOR_OWEN_2006 = 2d * 43d;

  @Override
  public String toString() {
    return "Linear Dose Decay Model used. "
        + "Diffraction intensity decreases linearly with dose "
        + "according to ddmFactor = 1 - dose/(2*43) (Owen et al. 2006)";
  }

  @Override
  public double calcDecay(final double dose) {

    double ddmFactor;
    if (dose < DDM_FACTOR_OWEN_2006) {
      ddmFactor = 1 - dose / DDM_FACTOR_OWEN_2006;
    } else {
      ddmFactor = 0;
    }
    return ddmFactor;
  }

}
