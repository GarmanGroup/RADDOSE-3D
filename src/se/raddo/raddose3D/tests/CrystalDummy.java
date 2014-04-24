package se.raddo.raddose3D.tests;

import java.util.HashMap;
import java.util.Map;

import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.Wedge;

/**
 * A minimal implementation of the Crystal class, which does... nothing.
 * This class is only for test purposes.
 * 
 * @author Markus Gerstel
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
  }

  @Override
  public void addFluence(int i, int j, int k, double fluenceIncrease) {
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
  }

  @Override
  public void addElastic(int i, int j, int k, double elasticIncrease) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public double getElastic(int i, int j, int k) {
    // TODO Auto-generated method stub
    return 0;
  }
}
