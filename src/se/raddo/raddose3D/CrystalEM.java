package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

public class CrystalEM extends Crystal {

  /**
   * 3 element array defining dimensions of bounding box of crystal in um. 
   */
  private final double[]       crystSizeUM = null;
  
  public double crystalSurfaceArea, XDim, YDim;
  
  public double sampleThickness;
  
  public double crystalVolume;
  
  /**
   * Dose and fluence arrays holding the scalar fields for these values at voxel
   * i,j,k.
   */
  private double         dose, fluence;
  
  public CrystalEM(Map<Object, Object> properties) {
    super(properties);
    // Check if optional values are initialized, otherwise set to defaults.
    Map<Object, Object> mergedProperties = new HashMap<Object, Object>();
    mergedProperties.put(Crystal.CRYSTAL_RESOLUTION, CRYSTAL_RESOLUTION_DEF);
    //add more optional values in for EM
    mergedProperties.putAll(properties);
    
    XDim = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_X); // in um
    YDim = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_Y); // in um
    
    crystalSurfaceArea = XDim * YDim * 1E08; //convert from um^2 to A^2
    sampleThickness = ((Double) mergedProperties.get(Crystal.CRYSTAL_DIM_Z)) * 1000; //convert um to nm
    
    crystalVolume = (crystalSurfaceArea * (sampleThickness * 10) * 1E-27);    //A^3 to dm^3
  }
  
  private double EMLETWay(Beam beam, Wedge wedge, CoefCalc coefCalc) {
//    double surfaceArea = coefCalc.getSA(); //Change to crystal coord when move 
    double electronNumber = beam.getPhotonsPerSec() * wedge.getTotSec();
    
    //check if the beam is bigger or smaller than the sample - need to check in x and in y (x = horizontal, y = vertical)
    double exposedAreaY = getExposedY(beam);
    double exposedAreaX = getExposedX(beam);
    double totExposedArea;
    totExposedArea = (exposedAreaX * exposedAreaY) * 1E08; //convert  um^2 to A^2
    
    double exposure = electronNumber/totExposedArea;  //exposure in e/A^2
    double beamEnergy = beam.getPhotonEnergy();
    double baseDose = 0;
    double theDose = 0;
    //set case exposure = 1
  if (beamEnergy == 100) {
    baseDose = 6.6;
  }
  else if (beamEnergy == 200) {
    baseDose = 4.5;
  }
  else if (beamEnergy == 300) {
    baseDose = 3.7;
  }
  theDose = baseDose * exposure;
  return theDose;
  }
  
  private double EMEquationWay(Beam beam, Wedge wedge, CoefCalc coefCalc) {
    double energyPerEvent = 0.02; //in keV
    double electronNumber = beam.getPhotonsPerSec() * wedge.getTotSec();
    double exposedVolume = (getExposedX(beam) * getExposedY(beam))  * (sampleThickness/1000) * 1E-15; //exposed volume in dm^3
    //Testing
    double elasticProbOverT = coefCalc.getElectronElastic(beam);
    double elasticProb = elasticProbOverT * sampleThickness;
    
    
    double inelasticProbOverT = coefCalc.getElectronInelastic(beam);
    double inelasticProb = inelasticProbOverT * sampleThickness;
    double numberInelasticEvents = inelasticProb * electronNumber;
    double energyDeposited = (energyPerEvent * numberInelasticEvents) * Beam.KEVTOJOULES; //in J
    double exposedMass = (coefCalc.getEMConc() * exposedVolume) / 1000;  //in Kg 
    double dose = (energyDeposited/exposedMass) / 1E06; //dose in MGy
    return dose;
  }
  
  /**
   * Returns the exposed area in the x dimensions of the sample in um
   * 
   * @param beam
   * @return exposedAreaX
   */
  private double getExposedX(Beam beam) {
    double exposedAreaX;
    double beamX = beam.getBeamX();
    if (XDim > beamX) {
      exposedAreaX = beamX;
    }
    else {
      exposedAreaX = XDim;
    }
    return exposedAreaX;
  }
  
  /**
   * Returns the exposed area in the y dimensions of the sample in um
   * 
   * @param beam
   * @return exposedAreaY
   */
  private double getExposedY(Beam beam) {
    double exposedAreaY;
    double beamY = beam.getBeamY();

    if (YDim > beamY) {
      exposedAreaY = beamY;
    }
    else {
      exposedAreaY = YDim;
    }
    return exposedAreaY;
  }
  
  
  @Override
  public void CalculateEM(Beam beam, Wedge wedge, CoefCalc coefCalc) {
    double dose1 = EMLETWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area: %.2e", dose1));
    System.out.println(" MGy\n");
 
  
    double dose2 = EMEquationWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area: %.2e", dose2));
    System.out.println(" MGy\n");
  }
  
  @Override
  public void setupDepthFinding(final double angle, final Wedge wedge) {
    // Spherical crystals do not need a specific depth finding setup.
  }
  
  @Override
  public double findDepth(final double[] voxCoord, final double deltaPhi,
      final Wedge myWedge) {
  return 0;
  }

  @Override
  public double[] getCrystCoord(final int i, final int j, final int k) {
    double[] crash = null;
    return crash;
  }

  @Override
  public boolean isCrystalAt(final int i, final int j, final int k) {
    return true;
  }

  @Override
  public void addDose(final int i, final int j, final int k,
      final double doseVox) {
    
  }

  @Override
  public void addFluence(final int i, final int j, final int k,
      final double fluenceVox) {
  
  }

  @Override
  public void addElastic(final int i, final int j, final int k,
      final double elasticVox) {
    
  }

  @Override
  public double getDose(final int i, final int j, final int k) {
    return dose;
  }

  @Override
  public double getElastic(final int i, final int j, final int k) {
    return 0;
  }

  @Override
  public double getFluence(final int i, final int j, final int k) {
    return fluence;
  }

  @Override
  public String crystalInfo() {
    return null;
  }

  @Override
  public int[] getCrystSizeVoxels() {
int[] crash = null;
    return crash;
  }

  @Override
  public double getCrystalPixPerUM() {
    return 0;
  }

  @Override
  public double[] getCrystSizeUM() {
    double[] cs = new double[crystSizeUM.length];
    System.arraycopy(crystSizeUM, 0, cs, 0, crystSizeUM.length);
    return cs;
  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#getEscapeFactor(int, int, int)
   * 
   * This class does has no photoelectron escape implementation
   * so this method has only been added to prevent error warnings. 
   */
  @Override
  public double getEscapeFactor(final int i, final int j, final int k) {
    return 1.0;
  }

  @Override
  public double addDoseAfterPE(int i, int j, int k, double doseIncreasePEL) {
    return 0;
  }

  @Override
  public void setPEparamsForCurrentBeam(double beamEnergy) {    
  }
  
  @Override
  public void setFLparamsForCurrentBeam(final double[][] feFactors) {
  }

  @Override
  public double addDoseAfterFL(int i, int j, int k, double doseIncreaseFL) {
    return 0;
  }
}
