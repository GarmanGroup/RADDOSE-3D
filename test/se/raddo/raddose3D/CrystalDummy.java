package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;


/**
 * A minimal implementation of the Crystal class, which does... nothing.
 * This class is only for test purposes.
 */
public class CrystalDummy extends Crystal {
  /**
   * The no-argument constructor must still supply crystal dimensions.
   * <p>
   * {@code Crystal}'s constructor reads CRYSTAL_DIM_X with an unguarded cast
   * ({@code XDim = (double) properties.get(Crystal.CRYSTAL_DIM_X)}), so an
   * empty property map unboxes null and throws NullPointerException. Note the
   * CRYSTAL_DIM_Y and CRYSTAL_DIM_Z lines immediately following it in
   * Crystal.java <em>are</em> wrapped in try/catch, so the asymmetry looks
   * unintentional -- see TEST-TRIAGE.md. This is worked around here rather than
   * in production code, because a test should not change behaviour to pass.
   */
  public CrystalDummy() {
    super(dummyProperties());
  }

  public CrystalDummy(Map<Object, Object> properties) {
    super(properties);
  }

  private static Map<Object, Object> dummyProperties() {
    Map<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, 0d);
    properties.put(Crystal.CRYSTAL_DIM_Y, 0d);
    properties.put(Crystal.CRYSTAL_DIM_Z, 0d);
    return properties;
  }

  @Override
  public double findDepth(double[] voxCoord, double deltaPhi, Wedge myWedge) {
    return 0;
  }

  @Override
  public double[] getCrystCoord(int i, int j, int k) {
    return null;
  }

  @Override
  public boolean isCrystalAt(int i, int j, int k) {
    return false;
  }

  @Override
  public void addDose(int i, int j, int k, double doseIncrease) {
    // No implementation required.
  }

  @Override
  public void addFluence(int i, int j, int k, double fluenceIncrease) {
    // No implementation required.
  }

  @Override
  public String crystalInfo() {
    return null;
  }

  @Override
  public int[] getCrystSizeVoxels() {
    return null;
  }

  @Override
  public double[] getCrystSizeUM() {
    return null;
  }

  @Override
  public double getDose(int i, int j, int k) {
    return 0;
  }

  @Override
  public double getFluence(int i, int j, int k) {
    return 0;
  }

  @Override
  public double getCrystalPixPerUM() {
    return 0;
  }

  @Override
  public void setupDepthFinding(double angle, Wedge wedge) {
    // No implementation required.
  }

  @Override
  public void addElastic(int i, int j, int k, double elasticIncrease) {
    // No implementation required.
  }

  @Override
  public double getElastic(int i, int j, int k) {
    return 0;
  }

  @Override
  public double getEscapeFactor(int i, int j, int k) {
    return 1.0;
  }

  @Override
  public double addDoseAfterPE(int i, int j, int k, double doseIncreasePE) {
    return 0;
  }

  @Override
  public void setPEparamsForCurrentBeam(double beamEnergy, CoefCalc coefCalc, double[][] feFactors) {    
  }
  
  @Override
  public void setFLparamsForCurrentBeam(final double[][] feFactors) {
  }

  @Override
  public double addDoseAfterFL(int i, int j, int k, double doseIncreaseFL) {
    return 0;
  }

  @Override
  public double[] getCryoCrystCoord(int i, int j, int k) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int[] getCryoCrystSizeVoxels() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getExtraVoxels(int maxPEDistance, double pixelsPerMicron) {
    // TODO Auto-generated method stub
    return 0;
  }
  
  @Override
  public void setCryoPEparamsForCurrentBeam(final Beam beam, CoefCalc coefCalc, double[][] feFactors) {    
  }
  
  @Override
  public double addDoseAfterPECryo(final double i, final double j, final double k, double doseIncreasePE, double energyToDoseFactor) {
    return 0;
  }
  
  @Override
  public void findVoxelsReachedByPE(boolean cryo, CoefCalc coefCalc, final double energy, double[][] feFactors, final double angle) {
    
  }
  
  @Override
  public int getCryoExtraVoxels() {
   
    return 0;
  }
  
  @Override
  public double getCryoCrystalPixPerUM() {
    
    return 0;
  }

  @Override
  public double getNumImages(Wedge wedge) {
    // TODO Auto-generated method stub
    return 0;
  }
  
  @Override
  public void startMicroED(double XDim, double YDim, double ZDim, Beam beam,
      Wedge wedge, CoefCalc coefCalc, String crystalType) {
    
  }

  @Override
  public void startXFEL(double XDim, double YDim, double ZDim, Beam beam,
      Wedge wedge, CoefCalc coefCalc, int runNum, boolean verticalGoniometer, boolean xfelTrue, boolean gos, boolean verticalPolarisation) {
    // TODO Auto-generated method stub
    
  }

/*
  @Override
  public void simElectron(int i, int j, int k, double numAbsorbedPhotons,
      boolean addBindingEn, CoefCalc coefCalc, double photonEnergy,
      double angle, boolean surrounding) {
    // TODO Auto-generated method stub
    
  }
*/
  @Override
  public void setELSEPA(CoefCalc coefCalc) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void startMC(double XDim, double YDim, double ZDim, Beam beam,
      Wedge wedge, CoefCalc coefCalc, int runNum, boolean verticalGoniometer,
      boolean xfel, boolean gos, double[] surrThickness, boolean verticalPolarisation) {
    // TODO Auto-generated method stub
    
  }
}
