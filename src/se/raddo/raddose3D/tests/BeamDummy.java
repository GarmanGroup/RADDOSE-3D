package se.raddo.raddose3D.tests;

import java.util.Map;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.Container;

/**
 * A minimal implementation of the Beam interface, which does... nothing.
 * This class is only for test purposes.
 *
 * @author Markus Gerstel
 */
public class BeamDummy implements Beam {
  public BeamDummy() {
  }

  public BeamDummy(Map<Object, Object> properties) {
  }

  @Override
  public double beamIntensity(double coordX, double coordY, double offAxisUM) {
    return 0;
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public double getPhotonsPerSec() {
    return 0;
  }

  @Override
  public double getPhotonEnergy() {
    return 0;
  }

  @Override
  public void applyContainerAttenuation(Container sampleContainer){

  }

  @Override
  public void generateBeamArray() {    
  }

  @Override
  public double beamMinumumDimension() {
    return 0;
  }

  @Override
  public double getBeamArea() {
    return 0;
  }

  @Override
  public double getExposure() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getBeamX() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getBeamY() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean getIsCircular() {
    // TODO Auto-generated method stub
    return false;
  }
}
