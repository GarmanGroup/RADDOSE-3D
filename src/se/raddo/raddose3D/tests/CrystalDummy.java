package se.raddo.raddose3D.tests;

import java.util.HashMap;
import java.util.Map;

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
  public double addDoseAfterPE(int i, int j, int k, double doseIncrease) {
    return 0;
  }

  @Override
  public void setPEparamsForCurrentBeam(double beamEnergy) {    
  }

  @Override
  public double addDoseAfterFL(int i, int j, int k, double doseIncrease) {
    return 0;
  }
}
