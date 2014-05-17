package se.raddo.raddose3D;

/**
 * This class returns the value of the Relative Diffraction Efficiency
 * for a voxel with a given dose.
 * Although this class is called DDMSimple the Dose Decay Model (DDM)
 * for this class assumes that there is no decay in diffracting efficiency
 * at all. Therefore the RDE = 1 for all dose values.
 */
public class DDMSimple implements DDM {

  @Override
  public String toString() {
    return "Simple DDM.";
  }

  @Override
  public double calcDecay(final double dose) {
    return 1; // The simple model assumes no intensity decay with dose!
  }

}
