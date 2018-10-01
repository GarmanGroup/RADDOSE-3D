package se.raddo.raddose3D;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class MicroED {
  
  
  public double crystalSurfaceArea;  //A^2

  public double sampleThickness; //nm

  public double crystalVolume;  //dm^3
  
  public double XDimension; //um
  public double YDimension;
  public double ZDimension;
  
  private double numberElastic;
  private double numberSingleElastic;
  private double numberNotInelasticEqu;
  private double numberNotInelasticRatio;
  private double numberProductive;
  
  
  
  public MicroED(double XDim, double YDim, double ZDim) {
    XDimension = XDim;
    YDimension = YDim;
    ZDimension = ZDim;
    crystalSurfaceArea = XDim * YDim * 1E08; //convert from um^2 to A^2
    sampleThickness = ZDim * 1000; //convert um to nm
    crystalVolume = (crystalSurfaceArea * (sampleThickness * 10) * 1E-27);    //A^3 to dm^3
    
  }
  
  public void CalculateEM(Beam beam, Wedge wedge, CoefCalc coefCalc) { // also pass in crystal dimensions
    // Just to be clear these are all dose of the exposed volume
    double dose1 = EMLETWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by LET: %.8e", dose1));
    System.out.println(" MGy\n");
 
  
    double dose2 = EMEquationWay(beam, wedge, coefCalc, true);
    System.out.print(String.format("\nThe Dose in the exposed area by equation: %.8e", dose2));
    System.out.println(" MGy\n");
    
    dose2 = EMEquationWay(beam, wedge, coefCalc, false);
    System.out.print(String.format("\nThe Dose in the exposed area by 3:1: %.8e", dose2));
    System.out.println(" MGy\n");
    
    double dose3 = EMStoppingPowerWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by stopping power: %.8e", dose3));
    System.out.println(" MGy\n");
    
    System.out.println(" Number elastic events: " + numberElastic);
    System.out.println(" Number single elastic events: " + numberSingleElastic);
    System.out.println(" Number productive events: " + numberProductive);
    
    try {
      WriterFile("outputMicroED.CSV", dose3);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  private double EMLETWay(Beam beam, Wedge wedge, CoefCalc coefCalc) {
//  double electronNumber = beam.getPhotonsPerSec() * wedge.getTotSec(); // total incident electrons
  double exposure = beam.getExposure();
  
  //check if the beam is bigger or smaller than the sample - need to check in x and in y (x = horizontal, y = vertical)
  double exposedAreaY = getExposedY(beam);
  double exposedAreaX = getExposedX(beam);
  double totExposedArea = 0;
  if (beam.getIsCircular() == false) {
    totExposedArea = (exposedAreaX * exposedAreaY) * 1E08; //convert  um^2 to A^2
  }
  else {
    totExposedArea = Math.PI * ((exposedAreaX/2) * (exposedAreaY/2)) * 1E08; //convert  um^2 to A^2
  }
  
//  double electronNumber = exposure * totExposedArea;
  
  //Reduce electron number if beam bigger than the sample
  /*
  if (totExposedArea < (beam.getBeamX()*beam.getBeamY() * 1E08)) { 
    double fractionFlux = totExposedArea / (beam.getBeamX()*beam.getBeamY() * 1E08);
    electronNumber = electronNumber * fractionFlux; //convert total electron number to electron incident on the sample
  }
  */
  
//  double exposure = electronNumber/totExposedArea;  //exposure in e/A^2
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

private double EMEquationWay(Beam beam, Wedge wedge, CoefCalc coefCalc, boolean useInelEqu) {
  double exposure = beam.getExposure();
  double energyPerEvent = 0.02; //in keV

  //will need to edit when I add in circular
  double exposedArea = 0;
  if (beam.getIsCircular() == false) {
    exposedArea = (getExposedX(beam) * getExposedY(beam)); //um^2
  }
  else {
    exposedArea = Math.PI * ((getExposedX(beam)/2) * (getExposedY(beam)/2)); //um^2
  }
  
  double exposedVolume = exposedArea  * (sampleThickness/1000) * 1E-15; //exposed volume in dm^3
  
//  double electronNumber = getElectronNumber(beam, wedge, exposedArea);
  double electronNumber = exposure * (exposedArea * 1E08);
  
//  double solventFraction = coefCalc.getEMSolventFraction();
  
  //now I need to calcWaters here as don't have access to crystal properties in coefCalcEM 
 
  //way 1 - density

  //way 2 = their way
//  coefCalc.calculateSolventWaterEM(solventFraction, exposedVolume);
  //density
//  coefCalc.calculateDensityEM(exposedVolume);
//  System.out.println(String.format("\nDensity: %.2e", coefCalc.getDensity()));
  
  //Elastic collisions
  double elasticProbOverT = coefCalc.getElectronElastic(beam);
  double elasticProb = elasticProbOverT * sampleThickness;
  
  numberElastic = elasticProb * electronNumber;
  numberSingleElastic = electronNumber * 
                        Math.exp(-elasticProb) * (Math.pow(elasticProb, 1) / 1); //Poisson distribution
  
  //inelastic 
  double inelasticProbOverT = 0;
  double inelasticProb = 0;
  if (useInelEqu == true) {
    inelasticProbOverT = coefCalc.getElectronInelastic(beam, exposedVolume);
    inelasticProb = inelasticProbOverT * sampleThickness;
    numberNotInelasticEqu = Math.exp(-inelasticProb) * electronNumber;
  }
  else {
    inelasticProb = elasticProb * 3;
    numberNotInelasticRatio = Math.exp(-inelasticProb) * electronNumber;
  }
  
  numberProductive = numberSingleElastic* numberNotInelasticEqu / electronNumber;

  //calculate backscattering coefficient - Use Heinrichs equation as a start
  double eta = coefCalc.getEta();
  double numberBackscattered = electronNumber * (eta / 100);
  //how I deal with backscattering in terms of dose and productive is really not trivial!!!! 
  //If I take them off at the start, they don't contribute to dose so that is understimated
  //If I take them off just the productive, I'm overestimating dose a little
  //How do I take off - I need to take it off single elastic via a probability
  //Indep of beam energy also scares me a bit
  
  //I should defo do % that were elastically scattered within the specified angle from the
  //objective aperture as this is much better!!!!!!!!!!!!!!!!!
  
  
//Am I doing the mass right???? What is dose it is energy per mass of all right not just protein....
  double numberInelasticEvents = (inelasticProb * electronNumber);
  double energyDeposited = (energyPerEvent * numberInelasticEvents) * Beam.KEVTOJOULES; //in J
  double exposedMass = (((coefCalc.getDensity()*1000) * exposedVolume) / 1000);  //in Kg 
  double dose = (energyDeposited/exposedMass) / 1E06; //dose in MGy //thickness isn't making a difference on dose as mass increases with it
  
  return dose;
}

private double EMStoppingPowerWay(Beam beam, Wedge wedge, CoefCalc coefCalc) {
  double exposedArea = 0;
  if (beam.getIsCircular() == false) {
    exposedArea = (getExposedX(beam) * getExposedY(beam)); //um^2
  }
  else {
    exposedArea = Math.PI * ((getExposedX(beam)/2) * (getExposedY(beam)/2)); //um^2
  }
  
  double exposedVolume = exposedArea  * (sampleThickness/1000) * 1E-15; //exposed volume in dm^3
  double exposure = beam.getExposure();
  double electronNumber = exposure * (exposedArea * 1E08);
  
  // need to get the stopping power from coefcalc
  double stoppingPower = coefCalc.getStoppingPower(beam);
  
  
  double energyDeposited = electronNumber * stoppingPower * sampleThickness * Beam.KEVTOJOULES;  //in J, currently per electron
  
  double exposedMass = (((coefCalc.getDensity()*1000) * exposedVolume) / 1000);  //in Kg 
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
  if (XDimension > beamX) {
    exposedAreaX = beamX;
  }
  else {
    exposedAreaX = XDimension;
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

  if (YDimension > beamY) {
    exposedAreaY = beamY;
  }
  else {
    exposedAreaY = YDimension;
  }
  return exposedAreaY;
}

private void WriterFile(final String filename, final double dose3) throws IOException {
  BufferedWriter outFile;
  outFile = new BufferedWriter(new OutputStreamWriter(
      new FileOutputStream(filename), "UTF-8"));
  try {
    outFile.write("dose, total_el, single_el, productive_el\n");
    outFile.write(String.format(
        " %f, %f, %f, %f%n", dose3, numberElastic, numberSingleElastic, numberProductive));
  } catch (IOException e) {
    e.printStackTrace();
    System.err.println("WriterFile: Could not write to file " + filename);
  }
  
  try {
    outFile.close();
  } catch (IOException e) {
    e.printStackTrace();
    System.err.println("WriterFile: Could not close file " + filename);
  }
}
  
}
