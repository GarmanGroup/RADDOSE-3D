package se.raddo.raddose3D;

/**
 * Dose Decay Model classes. They contain a method that calculates the
 * diffraction efficiency decay as a function of dose.
 */
public interface DDM {
  /**
   * Calculates the decay of diffraction efficiency as a function of dose.
   * 
   * @param dose
   *          the dose absorbed within a crystal voxel
   * @return
   *         The Relative Diffraction Efficiency (RDE)
   */
  public double calcDecay(double dose);
}
