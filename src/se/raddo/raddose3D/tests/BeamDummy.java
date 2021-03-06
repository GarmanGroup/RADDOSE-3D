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
  public Double getBeamX() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Double getBeamY() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean getIsCircular() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public double getSemiAngle() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getApertureRadius() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getImageX() {
    // TODO Auto-generated method stub
    return 0;
  }
  @Override
  public double getImageY() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getPulseEnergy() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setPhotonsPerfs(double photonsPerfs) {
    // TODO Auto-generated method stub
    
  }


  @Override
  public String getType() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double getSx() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getSy() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Double getEnergyFWHM() {
    // TODO Auto-generated method stub
    return null;
  }
}
