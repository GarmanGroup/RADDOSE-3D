package se.raddo.raddose3D;

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
