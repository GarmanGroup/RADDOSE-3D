package se.raddo.raddose3D.tests;

import java.util.HashMap;
import java.util.Map;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.CoefCalc;
import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.Wedge;

/**
 * A minimal implementation of the Crystal class, which does... nothing.
 * This class is only for test purposes.
 */
public class CrystalDummy extends Crystal {
  public CrystalDummy() {
    super(new HashMap<Object, Object>());
  }
  
  public CrystalDummy(Map<Object, Object> properties) {
    super(properties);
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
}
