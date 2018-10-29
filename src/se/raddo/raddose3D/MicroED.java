package se.raddo.raddose3D;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.util.HashMap;
import java.util.Map;


public class MicroED {
  
  //polyhderon variables
  public double[][] verticesEM;
  public int[][] indicesEM;
  public double[][][][] crystCoordEM;
  public double crystalPixPerUMEM;
  public int[] crystalSizeVoxelsEM;
  public boolean[][][][] crystOccEM;
  /**
   * Normal array holding normalised direction vectors for
   * each triangle specified by the index array.
   * Contains an i, j, k vector per triangle.
   * Should have same no. of entries as the indices array.
   */
  private double[][]            normals, rotatedNormals;

  /**
   * Distances from origin for each of the triangle planes.
   * Should have same no. of entries as the indices array.
   */
  private double[]              originDistances, rotatedOriginDistances;
  
  
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
  private double stoppingPowerESTAR;
  //to see if multislice is necessary at all
  private final int numberSlices = 1;
  
  private double MonteCarloDose;
  private double MonteCarloTotElasticCount;
  private double MonteCarloSingleElasticCount;
  private double MonteCarloFSEEscape;
  private double MonteCarloFlEscape;
  private double MonteCarloAugerEscape;
  private double extraFlEscape;
  private double extraAugerEscape;
  private double newMonteCarloFSEEscape;
  
  private double elasticEnergyTot;
  private double displacementEnergy;
 
  private double totFSEEnergy;
  private double totAugerEnergy;
  
  protected static final double NUM_MONTE_CARLO_ELECTRONS = 100000;
  
  
  
  public MicroED(double vertices[][], int[][] indices, double[][][][] crystCoord, 
                  double crystalPixPerUM, int[] crystSizeVoxels, boolean[][][][] crystOcc) {
    verticesEM = vertices;
    indicesEM = indices;
    crystCoordEM = crystCoord;
    crystalPixPerUMEM = crystalPixPerUM;
    crystalSizeVoxelsEM = crystSizeVoxels;
    crystOccEM = crystOcc;
    
    double[] xMinMax = this.minMaxVertices(0, vertices);
    double[] yMinMax = this.minMaxVertices(1, vertices);
    double[] zMinMax = this.minMaxVertices(2, vertices);
    XDimension = 1000 * (xMinMax[1] - xMinMax[0]);
    YDimension = 1000 * (yMinMax[1] - yMinMax[0]);
    ZDimension = 1000 * (zMinMax[1] - zMinMax[0]);
    
    
    crystalSurfaceArea = XDimension * YDimension * 1E02; //convert from nm^2 to A^2
    sampleThickness = ZDimension; //nm
    crystalVolume = (crystalSurfaceArea * (sampleThickness * 10) * 1E-27);    //A^3 to dm^3
    //note the volume would need to be updated for a polyhedron!!! - currently just a cube or cylinder 
    //although it isn't used
    
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
    
    //calculate Sternheimer adjustment factor
    
    double dose3 = EMStoppingPowerWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by stopping power: %.8e", dose3));
    System.out.println(" MGy\n");
    
    //start the Monte carlo stuff
    long start = System.nanoTime();
    startMonteCarlo(coefCalc, beam); 
    double dose4 = processMonteCarloDose(beam, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by Monte Carlo: %.8e", dose4));
    System.out.println(" MGy\n");
    long runtime = System.nanoTime() - start;
    System.out.println(String.format("The Monte Carlo runtime in seconds was: %.8e", runtime/1E9));
    
    /*
    accessESTAR(coefCalc, beam.getPhotonEnergy());
    double dose4 = getESTARDose(coefCalc, beam);
    System.out.print(String.format("\nThe Dose in the exposed area by ESTAR: %.8e", dose4));
    System.out.println(" MGy\n");
    */
    
    System.out.println("\nNumber elastic events: " + numberElastic);
    System.out.println("Number single elastic events: " + numberSingleElastic);
    System.out.println("Number productive events: " + numberProductive);
    
    System.out.println("Number elastic events Monte Carlo: " + MonteCarloTotElasticCount);
    System.out.println("Number single elastic events Monte Carlo: " + MonteCarloSingleElasticCount);
    
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
 // double energyPerEvent = 0.02; //in keV
//  double energyPerEvent = (7 * coefCalc.getZav())/1000; //in keV  //Change this to 7* Zav
  double energyPerEvent = 0.042;

  //will need to edit when I add in circular
  double exposedArea = 0;
  if (beam.getIsCircular() == false) {
    exposedArea = (getExposedX(beam) * getExposedY(beam)); //um^2
  }
  else {
    exposedArea = Math.PI * ((getExposedX(beam)/2) * (getExposedY(beam)/2)); //um^2
  }
  double electronNumber = exposure * (exposedArea * 1E08);
  
  double exposedVolume = exposedArea  * (sampleThickness/1000) * 1E-15; //exposed volume in dm^3
  
//  double electronNumber = getElectronNumber(beam, wedge, exposedArea);

  
//  double solventFraction = coefCalc.getEMSolventFraction();
  
  //now I need to calcWaters here as don't have access to crystal properties in coefCalcEM 
 
  //way 1 - density

  //way 2 = their way
//  coefCalc.calculateSolventWaterEM(solventFraction, exposedVolume);
  //density
//  coefCalc.calculateDensityEM(exposedVolume);
//  System.out.println(String.format("\nDensity: %.2e", coefCalc.getDensity()));
  
  //Elastic collisions
  // put in multislice here as well
  double elasticProb = 0;
  double avgEnergy = beam.getPhotonEnergy();
  for (int i = 1; i <= numberSlices; i++) {
    double elasticProbOverT = coefCalc.getElectronElastic(avgEnergy);
    elasticProb += elasticProbOverT * (sampleThickness/numberSlices); 
    //I need to update the electron energy, will do this with the stopping power for consistency
    double stoppingPower = coefCalc.getStoppingPower(avgEnergy); //send it electron energy
    double energyPerEl =  stoppingPower * (sampleThickness/numberSlices);
    avgEnergy -= energyPerEl;
  }

  
  numberElastic = elasticProb * electronNumber;
  numberSingleElastic = electronNumber * 
                        Math.exp(-elasticProb) * (Math.pow(elasticProb, 1) / 1); //Poisson distribution
  
  //inelastic 
  double inelasticProbOverT = 0;
  double inelasticProb = 0;
  avgEnergy = beam.getPhotonEnergy();
  if (useInelEqu == true) {
    for (int i = 1; i <= numberSlices; i++) {
      inelasticProbOverT = coefCalc.getElectronInelastic(avgEnergy, exposedVolume);
      inelasticProb += inelasticProbOverT * (sampleThickness/numberSlices);
      //I need to update the electron energy, will do this with the stopping power for consistency
      double stoppingPower = coefCalc.getStoppingPower(avgEnergy); //send it electron energy
      double energyPerEl =  stoppingPower * (sampleThickness/numberSlices);
      avgEnergy -= energyPerEl;
    }
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
  double exposure = beam.getExposure();
  if (beam.getIsCircular() == false) {
    exposedArea = (getExposedX(beam) * getExposedY(beam)); //um^2
  }
  else {
    exposedArea = Math.PI * ((getExposedX(beam)/2) * (getExposedY(beam)/2)); //um^2
  }
  double electronNumber = exposure * (exposedArea * 1E08);
  
  double exposedVolume = exposedArea  * ((sampleThickness/1000)) * 1E-15; //exposed volume in dm^3
  double exposedMass = (((coefCalc.getDensity()*1000) * exposedVolume) / 1000);  //in Kg 
  double stoppingPower = 0, energyDeposited = 0, dose = 0;
  double avgEnergy = beam.getPhotonEnergy();
  for (int i = 1; i <= numberSlices; i++) {
    // need to get the stopping power from coefcalc
    stoppingPower = coefCalc.getStoppingPower(avgEnergy); //send it electron energy

 
    double energyPerEl =  stoppingPower * (sampleThickness/numberSlices);
    avgEnergy -= energyPerEl; 
    energyDeposited = electronNumber * energyPerEl * Beam.KEVTOJOULES;  //in J, currently per electron
    dose += (energyDeposited/exposedMass) / 1E06; //dose in MGy 
  }
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

public void accessESTAR(CoefCalc coefCalc, double avgElectronEnergy) {
  String exePath = "lib\\selenium\\chromedriver.exe";
  System.setProperty("webdriver.chrome.driver", exePath);
// Create a new instance of the Firefox driver
  WebDriver driver = new ChromeDriver();
  //Launch the Website
  driver.get("https://physics.nist.gov/PhysRefData/Star/Text/ESTAR-u.html");
  
  //Enter material name
  WebElement name = driver.findElement(By.name("Name"));
  name.sendKeys("Protein");
  
  //Enter density
  double densityNum = coefCalc.getDensity();
  String densityString = Double.toString(densityNum);
  WebElement density = driver.findElement(By.name("Density"));
  density.sendKeys(densityString);
  
  //Enter element fractions
  Map<String, Double> fractionElementEM = new HashMap<String, Double>();
  fractionElementEM = coefCalc.getFractionElementEM();
  WebElement fractions = driver.findElement(By.name("Formulae"));
  NumberFormat formatNoSF = new DecimalFormat();
  formatNoSF = new DecimalFormat("0.000000"); //will break if in standard form
  
  for (String elementName : fractionElementEM.keySet()) {
    String fractionElement = formatNoSF.format(fractionElementEM.get(elementName));
    String toSend = (elementName + " " + fractionElement); 
    //Write this in the textbox
    fractions.sendKeys(toSend);
    fractions.sendKeys(Keys.RETURN);
  }
  
  //submit this information
  WebElement submit = driver.findElement(By.cssSelector("input[value='Submit']"));
  submit.click();
  
  
  //enter the beam energy
  String beamMeV = Double.toString((avgElectronEnergy / 1000));
  WebElement energy = driver.findElement(By.name("Energies"));
  energy.sendKeys(beamMeV);
  //uncheck default energies
  WebElement checkBox = driver.findElement(By.cssSelector("input[value='on']"));
  checkBox.click();
  //remove the graph as unnecessary
  WebElement radioButton = driver.findElement(By.cssSelector("input[value='None']"));
  radioButton.click();
  //submit this page
  submit = driver.findElement(By.cssSelector("input[value='Submit']"));
  submit.click();
  
  //select to output total stopping power
  checkBox = driver.findElement(By.name("total"));
  checkBox.click();
  //Download data
  submit = driver.findElement(By.cssSelector("input[value='Download data']"));
  submit.click();
  
  //copy and paste whole page
  Actions action = new Actions(driver); 
  action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0061')).perform();
  action.keyUp(Keys.CONTROL).perform();
  action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0063')).perform();
  String wholeTable = getSysClipboardText();

  //get beam energy in a string
  double MeV = avgElectronEnergy/1000;
  NumberFormat formatter = new DecimalFormat();
  formatter = new DecimalFormat("0.000E00");
  String beamEnergy = formatter.format(MeV); 
  // search using beam energy
  int beamEnergyIndex = wholeTable.indexOf(beamEnergy);
  String numbers = wholeTable.substring(beamEnergyIndex);
  //find stopping power by the space
  int spaceIndex = numbers.indexOf(" ");
  String stoppingPowerString = numbers.substring(spaceIndex + 1);
  stoppingPowerString = stoppingPowerString.trim();
  
  stoppingPowerESTAR = Double.parseDouble(stoppingPowerString);
  driver.quit(); // close all windows opened by selenium
   
}

/**
* get string from Clipboard
*/
public static String getSysClipboardText() {
   String ret = "";
   Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();

   Transferable clipTf = sysClip.getContents(null);

   if (clipTf != null) {

       if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
           try {
               ret = (String) clipTf
                       .getTransferData(DataFlavor.stringFlavor);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }

   return ret;
}

private double getESTARDose(CoefCalc coefCalc, Beam beam) {
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
  
  double stoppingPower = (stoppingPowerESTAR * coefCalc.getDensity() * 1000) / 1E7; //keV/nm
  
  double energyDeposited = electronNumber * stoppingPower * sampleThickness * Beam.KEVTOJOULES;  //in J, currently per electron
  
  double exposedMass = (((coefCalc.getDensity()*1000) * exposedVolume) / 1000);  //in Kg 
  double dose = (energyDeposited/exposedMass) / 1E06; //dose in MGy 
  
  return dose;
}

// Everything below will be the Monte Carlo section of the code
private void startMonteCarlo(CoefCalc coefCalc, Beam beam) {
int triggered = 0; //testing
int thisTriggered = 0; //testing
  
  //set up for one electron to start with and then test how many needed to get little deviation and then scale up
  int numberBackscattered = 0;
  //Start stuff to make quicker
  double startingEnergy = beam.getPhotonEnergy();
  double startingStoppingPower = coefCalc.getStoppingPower(startingEnergy);
  double startingLambda_el = coefCalc.getElectronElasticMFPL(startingEnergy);
  Map<ElementEM, Double> elasticProbs = coefCalc.getElasticProbs();
  
  //the FSE stuff 
  
  double startingFSExSection = getFSEXSection(beam.getPhotonEnergy());
  double startingFSELambda = coefCalc.getFSELambda(startingFSExSection);
  
  //Inner shell ionisation x section
  coefCalc.populateCrossSectionCoefficients();

  double startingInnerShellLambda = coefCalc.betheIonisationxSection(startingEnergy);
 // Map<Element, Double> ionisationProbs = coefCalc.getInnerShellProbs(); //Really need to make sure that these are in the same order
  Map<Element, double[]> ionisationProbs = coefCalc.getAllShellProbs(); //Really need to make sure that these are in the same order
  
  
  
  //not going to change the direction of the program yet going to write it separately and then
  //incorporate it in -remember that lambda_el now needs to always be lambda_t!!!!!!!!!
  
  //test ELSEPA
 // startingLambda = 236;
  
  for (int i = 0; i < NUM_MONTE_CARLO_ELECTRONS; i++) { //for every electron to simulate
    
  
  double electronEnergy = startingEnergy;
  double stoppingPower = startingStoppingPower;
  double energyLost = 0;
 // double lambdaT = startingLambda_el; //lambda in nm  -//Just elastic
  double lambdaT = 1 / (1/startingLambda_el + 1/startingFSELambda); //FSE one
 // double lambdaT = 1 / (1/startingLambda_el + 1/startingInnerShellLambda + 1/startingFSELambda);
  double PEL = lambdaT / startingLambda_el;
  double Pinel = 1 - (lambdaT / startingLambda_el);
  double PinnerShell = startingFSELambda/(startingInnerShellLambda + startingFSELambda);
  double testRND = Math.random();
  double s = -lambdaT*Math.log(testRND);
  //now I'm going to go through the coordinates
  
  
  //Need to change these to a uniform beam
  double previousX = 0, previousY = 0; //atm starting going straight 
  double xNorm = 0.0000, yNorm = 0.0000, zNorm = 1.0; //direction cosine are such that just going down in one
  double theta = 0, phi = 0, previousTheta = 0, previousPhi = 0, thisTheta = 0;
  double previousZ = -ZDimension/2;  //dodgy if specimen not flat - change for concave holes
  
  //position
  double RNDx = Math.random();
  previousX = (RNDx * XDimension) - (XDimension/2);
  double RNDy = Math.random();
  previousY = (RNDy * YDimension) - (YDimension/2);
  
  //direction 
  double[] directionVector = getElectronStartingDirection(beam, previousX, previousY, previousZ);
  xNorm = directionVector[0];
  yNorm = directionVector[1];
  zNorm = directionVector[2];
  
  //need to update theta and phi for these direction vectors 
  
  // theta angle between 0 0 1 and vector, phi angle between 1 0 0 and vector
  double[] zaxis = {0, 0, 1};
  theta = Math.acos(Vector.dotProduct(zaxis, directionVector));
  double[]xaxis = {1, 0, 0};
  double[] phiVector = {xNorm, yNorm, 0};
  //test
//  double[] phiVector = {0, -1, 0};
  double phiVectorMag = Vector.vectorMagnitude(phiVector);
  for (int m = 0; m <= 2; m++) {
    phiVector[m] /= phiVectorMag;
  }
  phi = Math.acos(Vector.dotProduct(xaxis, phiVector));
  if (yNorm < 0) {
    phi = 2*Math.PI - phi;  //so phi can be between 0 and 2pi not just pi
  }
  
  
  /*
  //direction
  RNDx = Math.random();
  double changex = 1, changey = 1;
  if (RNDx < 0.5){
    changex = -1;
  }
  cx = cx * changex;
  RNDy = Math.random();
  if (RNDy < 0.5){
    changey = -1;
  }
  cy = cy * changey;
  */
  
//  double ca = cx;
//  double cb = cy;
//  double cc = cz;
  double xn = previousX + s * xNorm;
  double yn = previousY + s * yNorm;
  double zn = previousZ + s * zNorm;
  boolean exited = false;
  boolean scattered = false;
  
  
  int timesScattered = 0;
  //check if the electron has left the sample, if it has just do the dose of Z
  //if it has not left move onto the loop
  while (exited == false) {
  if (isMicrocrystalAt(xn, yn, zn) == true) {
    ElementEM elasticElement = null;
    scattered = true;
    thisTriggered += 1;
    //reset
    previousTheta = theta;
    previousPhi = phi;
    previousX = xn;
    previousY = yn;
    previousZ = zn;
    //update dose and energy and stoppingPower
    energyLost = s * stoppingPower;
    MonteCarloDose += energyLost;   //keV
     
    //add an elastic collision

    //Determining if the scattering event was inelastic or elastic 
    double RNDscatter = Math.random();
//    RNDscatter = 0; // test
 //   double phi = 0, cosPhi = 1, psi = 0, AN = 0, AM = 0, V1 = 0, V2 = 0, V3 = 0, V4 = 0;
    
    if (RNDscatter < Pinel) {
      //Do inelastic
      triggered += 1;
      boolean innerShell = false;
      double shellBindingEnergy = 0;
      Element collidedElement = null;
      int collidedShell = 0;
      //did this come from an inner shell?
      double RNDinnerShell = Math.random();
      if (RNDinnerShell < PinnerShell) {
        //Then this secondary electron came from an inner shell
        innerShell = true;
        //determine which elemental shell it came from
        double elementRND = Math.random();
        for (Element e : ionisationProbs.keySet()) {
          double[] elementShellProbs = ionisationProbs.get(e);
          for (int k = 0; k < elementShellProbs.length; k++) {
            if (elementShellProbs[k] > elementRND) { //Then this element is the one that was ionised
              collidedElement = e;
              collidedShell = k;
              break;
            }
          }
        }
        
        double shellFluorescenceYield = 0;
        double flauEnergy = 0;
        if (collidedShell == 0) {
          shellBindingEnergy = collidedElement.getKEdge();
          shellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
          flauEnergy = collidedElement.getKFluorescenceAverage();
        }
        else if (collidedShell == 1) {
          shellBindingEnergy = collidedElement.getL1Edge();
          shellFluorescenceYield = collidedElement.getL1ShellFluorescenceYield();
          flauEnergy = collidedElement.getLFluorescenceAverage();
        }
        else if (collidedShell == 2) {
          shellBindingEnergy = collidedElement.getL2Edge();
          shellFluorescenceYield = collidedElement.getL2ShellFluorescenceYield();
          flauEnergy = collidedElement.getLFluorescenceAverage();
        }
        else if (collidedShell == 3){
          shellBindingEnergy = collidedElement.getL3Edge();
          shellFluorescenceYield = collidedElement.getL3ShellFluorescenceYield();
          flauEnergy = collidedElement.getLFluorescenceAverage();
        }
        
        //Do Fl or Auger
      //RND for FL or Auger given it was that element
        double fluoresenceYieldKRND = Math.random();
      //  double KshellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
        if (fluoresenceYieldKRND <= shellFluorescenceYield) { 
        //then it's fluorescence
          // get the absorption coefficient of the crystal
      //    double flEnergy = collidedElement.getKFluorescenceAverage();
          double absCoef = coefCalc.getEMFlAbsCoef(flauEnergy); //units um^-1
          //get a random direction vector
          double SExNorm = Math.random();
          double SEyNorm = Math.random();
          double SEzNorm = Math.random();
          //Draw the vector to the edge
          double flEscapeDist = getIntersectionDistance(previousX, previousY, previousZ, SExNorm, SEyNorm, SEzNorm); //um
          double escapeFraction = Math.exp(-absCoef * flEscapeDist);
          MonteCarloFlEscape += escapeFraction * flauEnergy;
          
        }
        else {
          //need to do Auger electrons
          //Auger electron energy equals flEnergy - shell binding energy of Auger electron
          //for now ignore the shell binding energy so overestimating their significance
       //   double augerEnergy = collidedElement.getKFluorescenceAverage();
          totAugerEnergy += flauEnergy;
          //get a random direction vector
          double SExNorm = Math.random();
          double SEyNorm = Math.random();
          double SEzNorm = Math.random();
          //Draw the vector to the edge
          double augerEscapeDist = 1000* getIntersectionDistance(previousX, previousY, previousZ, SExNorm, SEyNorm, SEzNorm); //um
          double augerStoppingPower = coefCalc.getStoppingPower(flauEnergy);
          double augerEnergyToEdge = augerStoppingPower * augerEscapeDist;
          if (augerEnergyToEdge < flauEnergy){
            MonteCarloAugerEscape += flauEnergy - augerEnergyToEdge;
          }
        }
        theta = 0;
      }
      else {
      //Track the secondary electron
      double FSEtheta = 0, FSEphi = 0, FSEpreviousTheta = 0, FSEpreviousPhi = 0, FSExNorm = 0, FSEyNorm = 0, FSEzNorm = 0;
      //firstly calculate the FSE energy
      double omega = 1 / (10000 - 9998*Math.random());
  //    double omega = 1 / (100 - 98*Math.random());
      double FSEEnergy = omega * electronEnergy; // - shellBindingEnergy;
      //I'm going to take t as the energy of that particular electron
      // This could be two values for the primary, with stopping power or with inel removal
      double sinSquaredAlpha = 0;
      double sinSquaredGamma = 0;
      double escapeDist = 0, maxDist = 0;
      if (FSEEnergy > electronEnergy/10000) { // so I only care about the FSE if it is more than x 
   //   if (FSEEnergy > 0) { // so I only care about the FSE if it is more than x 
        // determine the angles of the FSE and the primary electron
        double tPrimary = (electronEnergy-FSEEnergy)/511; //t is in rest mass units. Need to change to stopping power en
        double tFSE = FSEEnergy/511;
        //alpha = angle of primary electron
        sinSquaredAlpha = (2 * omega) / (2 + tPrimary - tPrimary*omega);
        //gamma - angle of secondary electron
        sinSquaredGamma = 2*(1-omega) / (2 + tFSE*omega); 
      
      //need to sort out when I'm tracking and when I'm not properly
      /*
      //then track the secondary electron and see how much dose escapes
      phi = Math.asin(Math.pow(sinSquaredGamma, 0.5));
      cosPhi = Math.cos(phi);
      psi = 2 * Math.PI * Math.random();
      
      //x and y are the same as in Joy, so x will be the rotation axis
      AN = -(cx/cz); // will need to catch an error here if = 0
      AM = 1 / (Math.pow(1 + AN*AN, 0.5));
      V1 = AN * Math.sin(phi);
      V2 = AN*AM*Math.sin(phi);
      V3 = Math.cos(psi);
      V4 = Math.sin(psi);
      
      ca = (cx*cosPhi) + (V1*V3) + (cy*V2*V4);
      cb = (cy*cosPhi) + (V4*(cz*V1 - cx*V2));
      cc = (cz*cosPhi) + (V2*V3) - (cy*V1*V4);
      */
      
      FSEtheta = Math.asin(Math.pow(sinSquaredGamma, 0.5));
      FSEphi = 2 * Math.PI * Math.random();
      
      
      FSEtheta = FSEpreviousTheta + FSEtheta;
      if (FSEtheta >= (2 * Math.PI)) {
        FSEtheta -= 2*Math.PI;
      }
      FSEphi = FSEpreviousPhi + FSEphi;
      if (FSEphi >= (2 * Math.PI)) {
        FSEphi -= 2*Math.PI;
      }
        
      FSExNorm = Math.sin(FSEtheta) * Math.cos(FSEphi);
      FSEyNorm = Math.sin(FSEtheta) * Math.sin(FSEphi);
      FSEzNorm = Math.cos(FSEtheta);
      
            //now I have a vector need to find where it will intersect point and the distance
      //If doing Monte Carlo of FSE would start tracking it here
      MonteCarloSecondaryElastic(coefCalc, FSEEnergy, previousX, previousY, previousZ, FSEtheta, FSEphi);
      
      /*
      escapeDist = 1000 * getIntersectionDistance(previousX, previousY, previousZ, FSExNorm, FSEyNorm, FSEzNorm); //nm
      double FSEStoppingPower = coefCalc.getStoppingPower(FSEEnergy);
      double energyToEdge = FSEStoppingPower * escapeDist;
      maxDist = FSEEnergy / FSEStoppingPower;
      totFSEEnergy += FSEEnergy;
      if (energyToEdge < FSEEnergy){
      //  MonteCarloFSEEscape += FSEEnergy - energyToEdge;
        double test = FSEEnergy - energyToEdge;
        //I'm thinking about multislicing this secondary electron and seeing if that makes a difference!!!
        //I think it will and it should reduce escape so increase dose - and it did
        
        //I should possibly also think about actually Monte Carlo tracking these at these low energies as they
        //will scatter and change direction
        double energyLostStep = 0;
        double newEnergy = FSEEnergy;
        for (int j = 0; j < 10; j++) { //I will need to play around with the amount of slicing when I am writing up
          energyLostStep = (escapeDist/10) * FSEStoppingPower;
          newEnergy -= energyLostStep;
          FSEStoppingPower = coefCalc.getStoppingPower(newEnergy);
        }
        if (newEnergy > 0) {
          MonteCarloFSEEscape += newEnergy;
        }
        
      }
      */
      }
      /*
      //Track Fl and Auger from this secondary electron
      //Need to think about the distance it is going and stuff like that though before I plough on
      double distTravelled = Math.min(maxDist, escapeDist);
      MonteCarloSecondaryElectronInnerShell(coefCalc, FSEEnergy, previousX, previousY, previousZ, FSExNorm, FSEyNorm, FSEzNorm, distTravelled);
      */
      //track the primary electron 
      /*
      phi = Math.asin(Math.pow(sinSquaredAlpha, 0.5));
      cosPhi = Math.cos(phi);
      */
      theta = Math.asin(Math.pow(sinSquaredAlpha, 0.5));
      /* not needed as done later
      phi = 2 * Math.PI * Math.random();
      
      
      theta = previousTheta + theta;
      if (theta >= (2 * Math.PI)) {
        theta -= 2*Math.PI;
      }
      phi = previousPhi + phi;
      if (phi >= (2 * Math.PI)) {
        phi -= 2*Math.PI;
      }
      */
    } 
    }
    else { //else it stays false and the collision will be elastic
      
      timesScattered += 1;
      MonteCarloTotElasticCount += 1;

      //now start the loop - clean up the first iteration into this later 
      //Determine what element elastically scattered the electron so can choose an alpha correctly
      
      double elasticElementRND = Math.random();
      
      for (ElementEM e : elasticProbs.keySet()) {
        if (elasticProbs.get(e) > elasticElementRND) { //Then this element is the one that was ionised
          elasticElement = e;
          break;
        }
      }
      
      //get the angles
      double alpha = getRutherfordScreeningElement(elasticElement, electronEnergy);
      double RND = Math.random();
      /*
      cosPhi = 1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND));
      phi = Math.acos(cosPhi);
      */
      theta = Math.acos(1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND)));
      thisTheta = theta;
      
      //Impart elastic knock-on energy???
      
      double Emax = electronEnergy * ((1 + electronEnergy)/1022)/(456*elasticElement.getAtomicWeight());
      double m = 9.10938356E-28; //kg
      double u = 1.660539040E-27; //kg/(g/mol)
      double restEnergy = 511;
      Emax = (2/elasticElement.getAtomicWeight())*(m/u)*electronEnergy*((2+electronEnergy)/(restEnergy));
      double Ed = 35;
      /*
      double nuclearMass = elasticElement.getAtomicWeight() * 1.660539040E-27;
      double c = 299792458;
      double csquared = c*c;
      double Mcsquared = nuclearMass * csquared;
      double KinE = electronEnergy * Beam.KEVTOJOULES;
      double sintheta = Math.pow(Math.sin(theta/2), 2);
      double energyTransmitted = (Math.pow(KinE, 2) / (Mcsquared))
                                  * ((2 * sintheta) / (1 + sintheta *(2*(KinE)/Mcsquared) ));
      energyTransmitted /= Beam.KEVTOJOULES;
      elasticEnergyTot += energyTransmitted;
      */
      
      double sintheta = Math.pow(Math.sin(theta/2), 2);
      double en = (Emax/1000) * sintheta;
      elasticEnergyTot += en;
      if (Emax > Ed) {
        displacementEnergy += en;
      }
    }
    //now further update the primary
    //  psi = 2 * Math.PI * Math.random();
    phi =  2 * Math.PI * Math.random();
    theta = previousTheta + theta;
    if (theta >= (2 * Math.PI)) {
      theta -= 2*Math.PI;
    }
    phi = previousPhi + phi;
    if (phi >= (2 * Math.PI)) {
      phi -= 2*Math.PI;
    }
      
    /*
      //x and y are the same as in Joy, so x will be the rotation axis
      AN = -(cx/cz); // will need to catch an error here if = 0
      AM = 1 / (Math.pow(1 + AN*AN, 0.5));
      V1 = AN * Math.sin(phi);
      V2 = AN*AM*Math.sin(phi);
      V3 = Math.cos(psi);
      V4 = Math.sin(psi);
      
      ca = (cx*cosPhi) + (V1*V3) + (cy*V2*V4);
      cb = (cy*cosPhi) + (V4*(cz*V1 - cx*V2));
      cc = (cz*cosPhi) + (V2*V3) - (cy*V1*V4);
      */
   
    xNorm = Math.sin(theta) * Math.cos(phi);
    yNorm = Math.sin(theta) * Math.sin(phi);
    zNorm = Math.cos(theta);
    
    
      //update stopping powers
      //get new stoppingPower
      electronEnergy -= energyLost; 
      stoppingPower = coefCalc.getStoppingPower(electronEnergy);
      //get new lambdaT
      double FSExSection = getFSEXSection(electronEnergy);
      double FSELambda = coefCalc.getFSELambda(FSExSection);
      double lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy);
      double innerShellLambda = coefCalc.betheIonisationxSection(electronEnergy);
   //   lambdaT =  1 / (1/lambdaEl + 1/FSELambda);
      lambdaT =  1 / (1/lambdaEl + 1/innerShellLambda + 1/FSELambda);
 //     lambdaT =  1 / (1/lambdaEl);
      s = -lambdaT*Math.log(Math.random());
      PEL = lambdaT / lambdaEl;
      Pinel = 1 - (lambdaT / lambdaEl);
      PinnerShell = FSELambda/(innerShellLambda + FSELambda);
      
      ionisationProbs = coefCalc.getAllShellProbs();
      elasticProbs = coefCalc.getElasticProbs();
      
      //update to new position
      xn = previousX + s * xNorm;
      yn = previousY + s * yNorm;
      zn = previousZ + s * zNorm;
      
  }
  else {
    exited = true;
    if (scattered == false) { // so it has just gone straight through with no elastic
      //deposit the dose - for now just the z dimension
      energyLost = ZDimension * stoppingPower;
      MonteCarloDose += energyLost;   //keV
      //end
    }
    else { //it has scattered at least  once but has now exited 
      //find the plane it is crossing somehow
      s = 1000 * getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm);
      //I'm going to get the point as well for now as it may be useful when doing apertures and stuff
      //It's also useful for backscattering!!!!
      double[] intersectionPoint = getIntersectionPoint(s, previousX, previousY, previousZ, xNorm, yNorm, zNorm);
      energyLost = s * stoppingPower;
      MonteCarloDose += energyLost;   //keV
      if (intersectionPoint[2] == -125) {
        numberBackscattered += 1;
      }
    }
  }
  }
  if (timesScattered == 1) {
    MonteCarloSingleElasticCount += 1;
  }
  
  } //end looping through electrons
  
  //Will need to do something about exiting the correct plane here

  //Will also need to add in inel scattering here for productive (and then FSE stuff)

   MonteCarloDose -= MonteCarloFSEEscape;
   MonteCarloDose -= newMonteCarloFSEEscape;
   MonteCarloDose -= MonteCarloAugerEscape;
   MonteCarloDose -= MonteCarloFlEscape;
}

private double processMonteCarloDose(Beam beam, CoefCalc coefCalc) {
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
  
  //do the elastic stuff
  MonteCarloTotElasticCount = MonteCarloTotElasticCount * (electronNumber / NUM_MONTE_CARLO_ELECTRONS);
  MonteCarloSingleElasticCount = MonteCarloSingleElasticCount * (electronNumber / NUM_MONTE_CARLO_ELECTRONS);
  
  MonteCarloDose = (MonteCarloDose * (electronNumber / NUM_MONTE_CARLO_ELECTRONS)) * Beam.KEVTOJOULES;
  
  double exposedMass = (((coefCalc.getDensity()*1000) * exposedVolume) / 1000);  //in Kg 
  double dose = (MonteCarloDose/exposedMass) / 1E06; //dose in MGy 
  
  return dose;
}

private double getFSEXSection(double electronEnergy) {
  double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
  double m = 9.10938356E-28; // in g
  double c = 29979245800.0;  //in cm
  //classical for now
  //find the electron velocity in cm/s
//  double vsquared = ((electronEnergy*Beam.KEVTOJOULES * 2) / (m/1000)) * 10000; //(cm/s)^2
  
  double csquared = Math.pow(c/100, 2);
  double Vo = electronEnergy * Beam.KEVTOJOULES;
  double betaSquared = 1- Math.pow((m/1000)*csquared/(Vo + (m/1000)*csquared), 2);
  double vsquared = (betaSquared * csquared)*10000;
  
  //the v sqaured being relativistic or not is what makes the difference
  
  //integrate equation - currently this isn't right... Do it numerically
//  double constant = (Math.PI * Math.pow(Beam.ELEMENTARYCHARGE, 4)) / Math.pow(electronEnergy*Beam.KEVTOJOULES, 2);  //maybe go with Murata
//  double constant = (6.21E-20 / Math.pow(electronEnergy, 2)); // *1E14;  //cm^2/electron? ???
//  double constant = (4* Math.PI * Math.pow(elementaryCharge, 4)) / (Math.pow(m*vsquared, 2));
  double constant = (2* Math.PI * Math.pow(elementaryCharge, 4)) / (m*vsquared * (Vo*1000*10000));
  
  //So the equation in Murata is cross section per electron (i assume cm^2/electron). So need to
  //1) Work out electrons per unit volume
  //2) multiply to get cross section in cm^-1 and convert to nm^-1
  
 // double crossSection = 0; //so this is in cm^2
  
  //equ integrates t 1/1-x -1/x + C
  
// double  crossSection = (1/(1-0.5) - 1/0.5) - ((1/(1-0.001) - 1/0.001));
// crossSection *= constant;  // I think this is now in cm^2 per electron
  
/*
  double crossSection = 0;
  for (double i = 1.1; i <= 500; i+=0.1) {
    double omega = i /1000;
    double omegaMinusOne = (i-0.1) / 1000;
    double width = (i /1000) - ((i-0.1)/1000);
    double height = ((constant * ((1/Math.pow(omega, 2)) + (1/Math.pow(1-omega, 2))))
                    + (constant * ((1/Math.pow(omegaMinusOne, 2)) + (1/Math.pow(1-omegaMinusOne, 2))))) 
                    / 2;
    crossSection += width * height;
  }
 */
 
 
 //try the relativistic cross section from Murata et al - with times
  /*
  double restMassEnergy = 511; //keV
  double tau = electronEnergy/restMassEnergy;
  double crossSection = 0;
  for (double i = 1.1; i <= 500; i+=0.1) {
    double omega = i /1000;
    double omegaMinusOne = (i-0.1) / 1000;
    double width = (i /1000) - ((i-0.1)/1000);
    double height = ((constant * ((1/Math.pow(omega, 2)) + (1/Math.pow(1-omega, 2)) + Math.pow(tau/(tau+1), 2) 
                  - ((2*tau+1)/Math.pow(tau+1, 2)) * (1/(omega*(1-omega)))))
        
                    + (constant * ((1/Math.pow(omegaMinusOne, 2)) + (1/Math.pow(1-omegaMinusOne, 2)) + Math.pow(tau/(tau+1), 2) 
                    - ((2*tau+1)/Math.pow(tau+1, 2)) * (1/(omegaMinusOne*(1-omegaMinusOne)))))) 
                    / 2;
    crossSection += width * height;
  }
  */
  
  //numerical integral of this
  double restMassEnergy = 511; //keV
  double tau = electronEnergy/restMassEnergy;
  double crossSection = (((2*tau+1)/Math.pow(tau+1, 2))*(Math.log((1/0.5)-1)) + Math.pow(tau/(tau+1), 2) - (1/0.5) - (1/(0.5-1))) -
                        (((2*tau+1)/Math.pow(tau+1, 2))*(Math.log((1/0.0001)-1)) + Math.pow(tau/(tau+1), 2) - (1/0.0001) - (1/(0.0001-1))); 
                        
  crossSection*= constant;

 
 //Book classical
  //constant is same as above
  /*
  double T = electronEnergy * Beam.KEVTOJOULES;
  double crossSection = 0;
  for (double i = 2*(T/1000); i <= T/2; i+= T/1000) {
    double Q = i;
    double QMinusOne = i - (T/1000);
    double width = T/1000;
    double height = ((constant * ((1/Math.pow(Q, 2)) * Math.pow(T/(T-Q), 2) * (1-2*(Q/T) + 2*Math.pow(Q/T, 2))))
                    + (constant * ((1/Math.pow(QMinusOne, 2)) * Math.pow(T/(T-QMinusOne), 2) * (1-2*(QMinusOne/T) + 2*Math.pow(QMinusOne/T, 2)))))
                    / 2;
    crossSection += width * height;
  }
  */
/*
  //book relativistic
  //constant is same as above
  double T = electronEnergy * Beam.KEVTOJOULES;
  double m = 9.10938356E-31; // in Kg
  double c = 299792458;
  double csquared = c*c;
  double crossSection = 0;
  for (double i = 2*(T/1000); i <= T/2; i+= T/1000) {
    double Q = i;
    double QMinusOne = i - (T/1000);
    double width = T/1000;
    double height = ((constant * ((1/Math.pow(Q, 2)) * Math.pow(T/(T-Q), 2) * 
                    (1-(3-Math.pow(T/(T+m*csquared), 2))*(Q/T)*(1-(Q/T))+(Math.pow(Q/(T+m*csquared), 2) * Math.pow(1-(Q/T), 2)))))
        
                    + (constant * ((1/Math.pow(QMinusOne, 2)) * Math.pow(T/(T-QMinusOne), 2) * 
                        (1-(3-Math.pow(T/(T+m*csquared), 2))*(QMinusOne/T)*(1-(QMinusOne/T))+(Math.pow(QMinusOne/(T+m*csquared), 2) * Math.pow(1-(QMinusOne/T), 2))))))
                    / 2;
    crossSection += width * height;
  }
  */
  //book non-rel
  
  //book very rel

 
  return crossSection; //cm^2/atom //nm^2 per atom??? //Really not sure about units here
}

private double getRutherfordScreeningElement(ElementEM e, double electronEnergy) {
  double alpha = 0;
  alpha = 3.4E-3 * (Math.pow(e.getAtomicNumber(), 0.67)/electronEnergy);
  return alpha;
}

private void inelasticFSEProduced(double electronEnergy) {

  //next stage is to track the FSE
  // 1) Use vector stuff to draw vector to point and determine energy by stopping power
          // make sure that I don't go to a negative energy
  // 2) Track the FSE by Monte Carlo
  
  
  
  
  //Use two methods to incorporate this
  // Method 1 - Only update the primary electron energy using the stopping power and subtract escape energy from the end
  //Method 2 - try incorporating the direct energy losses from this
              // - would expect these energy losses to be much lower but need to quantify

}

private void MonteCarloSecondaryElectronInnerShell(CoefCalc coefCalc, double FSEenergy, double previousX, double previousY, double previousZ, 
                                          double ca, double cb, double cc, double distTravelled) {
  //need to be careful here as they will change Bethe global variables in coefCalc so this void must be called at the end of a collision
  double electronEnergy = FSEenergy;
  double innerShellLambda = coefCalc.betheIonisationxSection(electronEnergy);
  Map<Element, double[]> ionisationProbs = coefCalc.getAllShellProbs();
  double testRND = Math.random();
  double s = -innerShellLambda*Math.log(testRND);
  double runningDist = s;
  double xn = previousX + s * ca;
  double yn = previousY + s * cb;
  double zn = previousZ + s * cc;
  boolean exited = false;
  while (exited == false) {
    if ((runningDist > distTravelled) || (innerShellLambda <= 0)) {
      exited = true;
    }
    if ((isMicrocrystalAt(xn, yn, zn) == true) && (exited == false)) {
      previousX = xn;
      previousY = yn;
      previousZ = zn;
      //determine which element has been ionized
      double shellBindingEnergy = 0;
      Element collidedElement = null;
      double collidedShell = 0;
      
      double elementRND = Math.random();
      for (Element e : ionisationProbs.keySet()) {
        double[] elementShellProbs = ionisationProbs.get(e);
        for (int k = 0; k < elementShellProbs.length; k++) {
          if (elementShellProbs[k] > elementRND) { //Then this element is the one that was ionised
            collidedElement = e;
            collidedShell = k;
            break;
          }
        }
      }
      
      double shellFluorescenceYield = 0;
      double flauEnergy = 0;
      if (collidedShell == 0) {
        shellBindingEnergy = collidedElement.getKEdge();
        shellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
        flauEnergy = collidedElement.getKFluorescenceAverage();
      }
      else if (collidedShell == 1) {
        shellBindingEnergy = collidedElement.getL1Edge();
        shellFluorescenceYield = collidedElement.getL1ShellFluorescenceYield();
        flauEnergy = collidedElement.getLFluorescenceAverage();
      }
      else if (collidedShell == 2) {
        shellBindingEnergy = collidedElement.getL2Edge();
        shellFluorescenceYield = collidedElement.getL2ShellFluorescenceYield();
        flauEnergy = collidedElement.getLFluorescenceAverage();
      }
      else if (collidedShell == 3){
        shellBindingEnergy = collidedElement.getL3Edge();
        shellFluorescenceYield = collidedElement.getL3ShellFluorescenceYield();
        flauEnergy = collidedElement.getLFluorescenceAverage();
      }
      
      
      
      if (electronEnergy > shellBindingEnergy) { //only a collision if it is physically possible
        //Do Fl or Auger
      //RND for FL or Auger given it was that element
        double fluoresenceYieldKRND = Math.random();
  //      double KshellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
        if (fluoresenceYieldKRND <= shellFluorescenceYield) { 
        //then it's fluorescence
          // get the absorption coefficient of the crystal
       //   double flEnergy = collidedElement.getKFluorescenceAverage();
          double absCoef = coefCalc.getEMFlAbsCoef(flauEnergy); //units um^-1
          //get a random direction vector
          double xNorm = Math.random();
          double yNorm = Math.random();
          double zNorm = Math.random();
          //Draw the vector to the edge
          double flEscapeDist = getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm); //um
          double escapeFraction = Math.exp(-absCoef * flEscapeDist);
          MonteCarloFlEscape += escapeFraction * flauEnergy;
          extraFlEscape += escapeFraction * flauEnergy;
        }
        else {
          //need to do Auger electrons
          //Auger electron energy equals flEnergy - shell binding energy of Auger electron
          //for now ignore the shell binding energy so overestimating their significance
     //     double augerEnergy = collidedElement.getKFluorescenceAverage();
          totAugerEnergy += flauEnergy;
          //get a random direction vector
          double xNorm = Math.random();
          double yNorm = Math.random();
          double zNorm = Math.random();
          //Draw the vector to the edge
          double augerEscapeDist = 1000 * getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm); //um
          double augerStoppingPower = coefCalc.getStoppingPower(flauEnergy);
          double augerEnergyToEdge = augerStoppingPower * augerEscapeDist;
          if (augerEnergyToEdge < flauEnergy){
            MonteCarloAugerEscape += flauEnergy - augerEnergyToEdge;
            extraAugerEscape += flauEnergy - augerEnergyToEdge;
          }
        }
        //update new energy
        electronEnergy -= shellBindingEnergy;
        innerShellLambda = coefCalc.betheIonisationxSection(electronEnergy);
        ionisationProbs = coefCalc.getAllShellProbs();
      }  
      //update stuff for next collision
      s = -innerShellLambda*Math.log(Math.random());
      runningDist += s;
      xn = previousX + s * ca;
      yn = previousY + s * cb;
      zn = previousZ + s * cc;
    }
    else {
      exited = true;
    }
    if (electronEnergy < 0.1) {
      exited = true; //exit the loop if the electron is below a certain energy, set here at 100eV
    }
  }
  
}

private void MonteCarloSecondaryElastic(CoefCalc coefCalc, double FSEenergy, double previousX, double previousY, double previousZ, 
    double FSEtheta, double FSEphi) { //Will need to combine this with the inner shell stuff as well - means re-updating the inner shell x sections after I mess with them
  double energyLost = 0;
  double theta = FSEtheta;
  double phi = FSEphi;
  double electronEnergy = FSEenergy;
  double startingEnergy = FSEenergy;
  double startingStoppingPower = coefCalc.getStoppingPower(startingEnergy);
  double stoppingPower = startingStoppingPower;
  
  double startingLambda_el = coefCalc.getElectronElasticMFPL(startingEnergy);
  Map<ElementEM, Double> elasticProbs = coefCalc.getElasticProbs();
  
  double startingInnerShellLambda = coefCalc.betheIonisationxSection(startingEnergy);
 // Map<Element, Double> ionisationProbs = coefCalc.getInnerShellProbs(); 
  Map<Element, double[]> ionisationProbs = coefCalc.getAllShellProbs(); 

  //Just do elastic for now and then incorporate inner shell
//  double lambdaT = startingLambda_el;
  double lambdaT = 0;
  if (startingInnerShellLambda > 0) {
    lambdaT = 1/((1/startingLambda_el) + (1/startingInnerShellLambda));
  }
  else{
    lambdaT = startingLambda_el;
  }
  double testRND = Math.random();
  double s = -lambdaT*Math.log(testRND);
  double Pinel = 1 - (lambdaT / startingLambda_el);
  double xNorm = Math.sin(theta) * Math.cos(phi);
  double yNorm = Math.sin(theta) * Math.sin(phi);
  double zNorm = Math.cos(theta);
  double xn = previousX + s * xNorm;
  double yn = previousY + s * yNorm;
  double zn = previousZ + s * zNorm;
  boolean exited = false, scattered = false;
  double previousTheta = 0, previousPhi = 0;
  
  if (electronEnergy < 0.05) {
    exited = true;
  }
  while (exited == false) {
    if (isMicrocrystalAt(xn, yn, zn) == true) {
      //reset
      scattered = true;
      previousTheta = theta;
      previousPhi = phi;
      previousX = xn;
      previousY = yn;
      previousZ = zn;
      //update dose and energy and stoppingPower
      energyLost = s * stoppingPower;
      
      
    double RNDscatter = Math.random();
    if (RNDscatter < Pinel) { // If the scatter was an inner shell ionisation 
      double shellBindingEnergy = 0;
      Element collidedElement = null;
      double collidedShell = 0;
      
      double elementRND = Math.random();
      for (Element e : ionisationProbs.keySet()) {
        double[] elementShellProbs = ionisationProbs.get(e);
        for (int k = 0; k < elementShellProbs.length; k++) {
          if (elementShellProbs[k] > elementRND) { //Then this element is the one that was ionised
            collidedElement = e;
            collidedShell = k;
            break;
          }
        }
      }
      
      double shellFluorescenceYield = 0;
      double flauEnergy = 0;
      if (collidedShell == 0) {
        shellBindingEnergy = collidedElement.getKEdge();
        shellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
        flauEnergy = collidedElement.getKFluorescenceAverage();
      }
      else if (collidedShell == 1) {
        shellBindingEnergy = collidedElement.getL1Edge();
        shellFluorescenceYield = collidedElement.getL1ShellFluorescenceYield();
        flauEnergy = collidedElement.getLFluorescenceAverage();
      }
      else if (collidedShell == 2) {
        shellBindingEnergy = collidedElement.getL2Edge();
        shellFluorescenceYield = collidedElement.getL2ShellFluorescenceYield();
        flauEnergy = collidedElement.getLFluorescenceAverage();
      }
      else if (collidedShell == 3){
        shellBindingEnergy = collidedElement.getL3Edge();
        shellFluorescenceYield = collidedElement.getL3ShellFluorescenceYield();
        flauEnergy = collidedElement.getLFluorescenceAverage();
      }
      if (electronEnergy > shellBindingEnergy) { //only a collision if it is physically possible
        //Do Fl or Auger
      //RND for FL or Auger given it was that element
        double fluoresenceYieldKRND = Math.random();
    //    double KshellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
        if (fluoresenceYieldKRND <= shellFluorescenceYield) { 
        //then it's fluorescence
          // get the absorption coefficient of the crystal
      //    double flEnergy = collidedElement.getKFluorescenceAverage();
          double absCoef = coefCalc.getEMFlAbsCoef(flauEnergy); //units um^-1
          //get a random direction vector
          double SExNorm = Math.random();
          double SEyNorm = Math.random();
          double SEzNorm = Math.random();
          //Draw the vector to the edge
          double flEscapeDist = getIntersectionDistance(previousX, previousY, previousZ, SExNorm, SEyNorm, SEzNorm); //um
          double escapeFraction = Math.exp(-absCoef * flEscapeDist);
          MonteCarloFlEscape += escapeFraction * flauEnergy;
          extraFlEscape += escapeFraction * flauEnergy;
        }
        else {
          //need to do Auger electrons
          //Auger electron energy equals flEnergy - shell binding energy of Auger electron
          //for now ignore the shell binding energy so overestimating their significance
       //   double augerEnergy = collidedElement.getKFluorescenceAverage();
          totAugerEnergy += flauEnergy;
          //get a random direction vector
          double SExNorm = Math.random();
          double SEyNorm = Math.random();
          double SEzNorm = Math.random();
          //Draw the vector to the edge
          double augerEscapeDist = 1000 * getIntersectionDistance(previousX, previousY, previousZ, SExNorm, SEyNorm, SEzNorm); //um
          double augerStoppingPower = coefCalc.getStoppingPower(flauEnergy);
          double augerEnergyToEdge = augerStoppingPower * augerEscapeDist;
          if (augerEnergyToEdge < flauEnergy){
            MonteCarloAugerEscape += flauEnergy - augerEnergyToEdge;
            extraAugerEscape += flauEnergy - augerEnergyToEdge;
          }
        }
      }  
    }
    else {
      double elasticElementRND = Math.random();
      ElementEM elasticElement = null;
      for (ElementEM e : elasticProbs.keySet()) {
        if (elasticProbs.get(e) > elasticElementRND) { //Then this element is the one that was ionised
          elasticElement = e;
          break;
        }
      }
      
      //get the angles
 //     double phi = 0, cosPhi = 1, psi = 0, AN = 0, AM = 0, V1 = 0, V2 = 0, V3 = 0, V4 = 0;
      double alpha = getRutherfordScreeningElement(elasticElement, electronEnergy);
      double RND = Math.random();
      /*
      cosPhi = 1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND));
      phi = Math.acos(cosPhi);
    */
      theta = Math.acos(1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND)));
      theta = previousTheta + theta;
      if (theta >= (2 * Math.PI)) {
        theta -= 2*Math.PI;
      }
      phi = 2 * Math.PI * Math.random();
      phi = previousPhi + phi;
      if (phi >= (2 * Math.PI)) {
        phi -= 2*Math.PI;
      }
    //now further update the primary
//      psi = 2 * Math.PI * Math.random();

      /*
      //x and y are the same as in Joy, so x will be the rotation axis
      AN = -(cx/cz); // will need to catch an error here if = 0
      AM = 1 / (Math.pow(1 + AN*AN, 0.5));
      V1 = AN * Math.sin(phi);
      V2 = AN*AM*Math.sin(phi);
      V3 = Math.cos(psi);
      V4 = Math.sin(psi);
      
      ca = (cx*cosPhi) + (V1*V3) + (cy*V2*V4);
      cb = (cy*cosPhi) + (V4*(cz*V1 - cx*V2));
      cc = (cz*cosPhi) + (V2*V3) - (cy*V1*V4);
      */
      
      xNorm = Math.sin(theta) * Math.cos(phi);
      yNorm = Math.sin(theta) * Math.sin(phi);
      zNorm = Math.cos(theta);
    }
      //update stopping powers
      //get new stoppingPower
      electronEnergy -= energyLost; 
      stoppingPower = coefCalc.getStoppingPower(electronEnergy);
      //get new lambdaT
      double lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy);
      double innerShellLambda = coefCalc.betheIonisationxSection(electronEnergy);
      if (innerShellLambda > 0) {
        lambdaT =  1 / ((1/lambdaEl)+(1/innerShellLambda));
      }
      else {
        lambdaT = lambdaEl;
      }
      s = -lambdaT*Math.log(Math.random());
      elasticProbs = coefCalc.getElasticProbs();
      ionisationProbs = coefCalc.getAllShellProbs();
      //update to new position
      xn = previousX + s * xNorm;
      yn = previousY + s * yNorm;
      zn = previousZ + s * zNorm;
    }
    else {
      exited = true;
          //I need to add the distance bit here - multislice 
        double escapeDist = 1000 * getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm); //nm
        double FSEStoppingPower = coefCalc.getStoppingPower(electronEnergy);
        double energyToEdge = FSEStoppingPower * escapeDist;
        if (energyToEdge < electronEnergy){
          double energyLostStep = 0;
          double newEnergy = electronEnergy;
          for (int j = 0; j < 10; j++) { //I will need to play around with the amount of slicing when I am writing up
            energyLostStep = (escapeDist/10) * FSEStoppingPower;
            newEnergy -= energyLostStep;
            FSEStoppingPower = coefCalc.getStoppingPower(newEnergy);
          }
          if (newEnergy > 0) {
           // MonteCarloFSEEscape += newEnergy;
            newMonteCarloFSEEscape += newEnergy;
          }
        }
      
    }
    if (electronEnergy < 0.05) { // play with this and maybe graph it
      exited = true;
    }
  }
}

private double[] getElectronStartingDirection(Beam beam, double previousX, double previousY, double previousZ) {
  double beamSemiAngle = 10;  //in mrad
  double beamApertureRadius = 1.2;  // how many times bigger the aperture is than the beam
  double beamRadius = Math.pow(Math.pow((beam.getBeamX()/2),2) + Math.pow(beam.getBeamY()/2, 2), 0.5);
  double extraLength = beamRadius * (beamApertureRadius - 1);
  //now get the aperture point
  double signX = (previousX >= 0) ? 1 : -1;
  double signY = (previousY >= 0) ? 1 : -1;
  double apertureX = signX*(Math.random() * beamApertureRadius) + previousX;
  double apertureY = signY*(Math.random() * beamApertureRadius) + previousY;
  double apertureZ = previousZ - (extraLength/Math.tan(beamSemiAngle/1000));
  double[] coordinateFrom = {apertureX, apertureY, apertureZ};
  double[] coordinateTo = {previousX, previousY, previousZ};
  double[] directionVector = Vector.vectorBetweenPoints(coordinateFrom, coordinateTo);
  double magnitude = Vector.vectorMagnitude(directionVector);
  for (int v = 0; v <= 2; v ++) {
    directionVector[v] /= magnitude;   //this is to normalise it 
  }
  return directionVector;
}


private boolean isMicrocrystalAt(final double x, final double y, final double z) {
  //Note that this is absolutely only right for a cuboid at the moment
  //This can stay as a quick test
  if ((x > XDimension/2) || (x < -XDimension/2)) {
    return false;
  }
  if ((y > YDimension/2) || (y < -YDimension/2)) {
    return false;
  }
  if ((z > ZDimension/2) || (z < -ZDimension/2)) {
    return false;
  }

  //now do the crystal occupancy stuff
  //convert xyz to ijk
  double[] xMinMax = this.minMaxVertices(0, verticesEM);
  double[] yMinMax = this.minMaxVertices(1, verticesEM);
  double[] zMinMax = this.minMaxVertices(2, verticesEM);
  int i = (int) StrictMath.round(((x/1000) - xMinMax[0]) * crystalPixPerUMEM);
  int j = (int) StrictMath.round(((y/1000) - yMinMax[0]) * crystalPixPerUMEM);
  int k = (int) StrictMath.round(((z/1000) - zMinMax[0]) * crystalPixPerUMEM);
  
  boolean[] occ = crystOccEM[i][j][k];  //This means that if has already been done don't do it again
                                        // Really needed to speed up Monte Carlo

  if (!occ[0]) {
    occ[1] = calculateCrystalOccupancy(x, y, z);
    occ[0] = true;
  }

  return occ[1];
}

private double getIntersectionDistance(double x, double y, double z, double ca, double cb, double cc) {
  if (normals == null) {
    calculateNormals(false);
  }

  double[] directionVector = {ca, cb, cc}; //the actual direction vector
  double minIntersect = 0;
  double[] origin = new double[3];
  origin[0] = x/1000;
  origin[1] = y/1000;
  origin[2] = z/1000;
  
  double intersectionDistance = 0;
  for (int l = 0; l < indicesEM.length; l++) {
    intersectionDistance = Vector.rayTraceDistance(normals[l],
        directionVector, origin, originDistances[l]);

    Double distanceObject = Double.valueOf(intersectionDistance);

    if (intersectionDistance < 0 || distanceObject.isNaN()
        || distanceObject.isInfinite()) {
        //do nothing
    }
    else {
  //    break; //maybe should just be closest, or an issue with the rayTRace
      if (minIntersect == 0) {
        minIntersect = intersectionDistance;
      }
      else {
        double min = Math.min(minIntersect, intersectionDistance);
        minIntersect = min;
      }
    }

  }
  return minIntersect;
}

private double[] getIntersectionPoint(double intersectionDistance, double x, double y, double z,
                                    double ca, double cb, double cc) {
  double[] directionVector = {ca, cb, cc}; //the actual direction vector
  double[] origin = new double[3];
  origin[0] = x/1000;
  origin[1] = y/1000;
  origin[2] = z/1000;
  double distance = intersectionDistance / 1000;
  double[] intersectionPoint = Vector.rayTraceToPointWithDistance(
      directionVector, origin, distance);
  return intersectionPoint;
}


/**
 * Returns the minimum and maximum values of a vertex array
 * given chosen dimension (0 = x, 1 = y, 2 = z).
 *
 * @param dimension 0 = x, 1 = y, 2 = z
 * @param vertices vertices to be examined
 * @return double array, first element minimum, second element maximum
 */
public double[] minMaxVertices(final int dimension, final double[][] vertices) {

  double min = java.lang.Double.POSITIVE_INFINITY;
  double max = java.lang.Double.NEGATIVE_INFINITY;

  for (int i = 0; i < vertices.length; i++) {
    if (vertices[i][dimension] < min) {
      min = vertices[i][dimension];
    }

    if (vertices[i][dimension] > max) {
      max = vertices[i][dimension];
    }
  }

  double[] result = { min, max };

  return result;
}

/**
 * Calculates normal array from index and vertex arrays.
 * Also calculates signed distances of each triangle
 * from the origin.
 */
public void calculateNormals(final boolean rotated) {

  double[][] verticesUsed = verticesEM;
  double[] originDistancesUsed = new double[verticesEM.length];
  double[][] normalsUsed = new double[verticesEM.length][3];

  normalsUsed = new double[indicesEM.length][3];
  originDistancesUsed = new double[indicesEM.length];

  for (int i = 0; i < indicesEM.length; i++) {
    // get the three vertices which this triangle corresponds to.
    double[] point1 = verticesUsed[indicesEM[i][0] - 1];
    double[] point2 = verticesUsed[indicesEM[i][1] - 1];
    double[] point3 = verticesUsed[indicesEM[i][2] - 1];

    // get two vectors which can be used to define our plane.

    double[] vector1 = Vector.vectorBetweenPoints(point1, point2);
    double[] vector2 = Vector.vectorBetweenPoints(point1, point3);

    // get the normal vector between these two vectors.

    double[] normalVector = Vector.normalisedCrossProduct(vector1, vector2);

    // copy this vector into the normals array at the given point.
    System.arraycopy(normalVector, 0, normalsUsed[i], 0, 3);

    double distanceFromOrigin = -(normalVector[0] * point1[0]
        + normalVector[1] * point1[1] + normalVector[2] * point1[2]);

    originDistancesUsed[i] = distanceFromOrigin;
  }

    originDistances = new double[indicesEM.length];
    normals = new double[indicesEM.length][3];

    for (int i = 0; i < normalsUsed.length; i++) {
      System.arraycopy(normalsUsed[i], 0, normals[i], 0, 3);
    }

    System.arraycopy(originDistancesUsed, 0, originDistances, 0,
        indicesEM.length);
  
}


public boolean calculateCrystalOccupancy(final double x, final double y, final double z)
{
  if (normals == null) {
    calculateNormals(false);
  }

  boolean inside = false;

  double[] directionVector = { 0, 0, 1 };
  double[] origin = new double[3];
  origin[0] = x/1000;
  origin[1] = y/1000;
  origin[2] = z/1000;
  //It doesn't work if x = y so need a fudge here... this is horrible.
  if (origin[0] == origin[1]) {
    origin[0] += 0.00001;
  }

  for (int l = 0; l < indicesEM.length; l++) {
    double intersectionDistance = Vector.rayTraceDistance(normals[l],
        directionVector, origin, originDistances[l]);

    Double distanceObject = Double.valueOf(intersectionDistance);

    if (intersectionDistance < 0 || distanceObject.isNaN()
        || distanceObject.isInfinite()) {
      continue;
    }

    double[] intersectionPoint = Vector.rayTraceToPointWithDistance(
        directionVector, origin, intersectionDistance);

    double[][] triangleVertices = new double[3][3];

    // copy vertices referenced by indices into single array for
    // passing onto the polygon inclusion test.
    for (int m = 0; m < 3; m++) {
      System.arraycopy(verticesEM[indicesEM[l][m] - 1], 0, triangleVertices[m],
          0, 3);
    }

    boolean crosses = Vector.polygonInclusionTest(triangleVertices,
        intersectionPoint);

    if (crosses) {
      inside = !inside;
    }
  }

  return inside;
}

/**
 * Vector class containing magical vector methods
 * like cross products and magnitudes.
 *
 * @author magd3052
 */
private static class Vector {
  /**
   * Returns magnitude of 3D vector.
   *
   * @param vector 3d coordinates of vector
   * @return magnitude scalar.
   */
  public static double vectorMagnitude(final double[] vector) {
    double squaredDistance = Math.pow(vector[0], 2) + Math.pow(vector[1], 2)
        + Math.pow(vector[2], 2);

    double distance = Math.sqrt(squaredDistance);

    return distance;
  }

  /**
   * returns 3D vector between FROM and TO points.
   *
   * @param from from point
   * @param to to point
   * @return vector between points.
   */
  public static double[] vectorBetweenPoints(final double[] from,
      final double[] to) {
    double[] newVector = new double[3];

    for (int i = 0; i < 3; i++) {
      newVector[i] = to[i] - from[i];
    }

    return newVector;
  }

  /**
   * returns 3D cross-product between two vectors.
   *
   * @param vector1 vector1
   * @param vector2 vector2
   * @return cross product
   */
  public static double[] crossProduct(final double[] vector1,
      final double[] vector2) {
    double[] newVector = new double[3];

    newVector[0] = vector1[1] * vector2[2] - vector1[2] * vector2[1];
    newVector[1] = vector1[2] * vector2[0] - vector1[0] * vector2[2];
    newVector[2] = vector1[0] * vector2[1] - vector1[1] * vector2[0];

    return newVector;
  }

  /**
   * returns 3D cross product with magnitude set to 1 between
   * two vectors.
   *
   * @param vector1 vector1
   * @param vector2 vector2
   * @return normalised cross product
   */
  public static double[] normalisedCrossProduct(final double[] vector1,
      final double[] vector2) {
    double[] newVector = crossProduct(vector1, vector2);
    double magnitude = vectorMagnitude(newVector);

    for (int i = 0; i < 3; i++) {
      newVector[i] /= magnitude;
    }

    return newVector;
  }

  /**
   * returns dot product between two 3D vectors.
   *
   * @param vector1 vector1
   * @param vector2 vector2
   * @return dot product
   */
  public static double dotProduct(final double[] vector1,
      final double[] vector2) {
    double dotProduct = 0;

    for (int i = 0; i < 3; i++) {
      dotProduct += vector1[i] * vector2[i];
    }

    return dotProduct;
  }

  /**
   * Ray trace from a point to a plane via a direction vector,
   * find the intersection between the direction vector and the
   * plane and return this point.
   *
   * @param normalUnitVector normal vector with magnitude 1
   * @param directionVector direction vector of any magnitude
   * @param origin point from which ray is traced (i.e. voxel coordinate)
   * @param planeDistance distance of plane from true origin (0, 0, 0)
   * @return intersection point between plane and direction vector
   */
  @SuppressWarnings("unused")
  public static double[] rayTraceToPoint(final double[] normalUnitVector,
      final double[] directionVector, final double[] origin,
      final double planeDistance) {
    double t = rayTraceDistance(normalUnitVector, directionVector, origin,
        planeDistance);

    double[] point = new double[3];

    for (int i = 0; i < 3; i++) {
      point[i] = origin[i] + t * directionVector[i];
    }

    return point;
  }

  /**
   * Ray trace - find intersection of direction vector from point
   * with plane from already-known distance t.
   *
   * @param directionVector direction vector
   * @param origin point from which ray is traced
   * @param t distance of origin to plane along direction vector
   * @return point of intersection
   */
  public static double[] rayTraceToPointWithDistance(
      final double[] directionVector,
      final double[] origin,
      final double t) {
    double[] point = new double[3];

    for (int i = 0; i < 3; i++) {
      point[i] = origin[i] + t * directionVector[i];
    }

    return point;
  }

  /**
   * Ray trace from a point to a plane via a direction vector,
   * find the signed distance between the direction vector and
   * the plane and return this point.
   *
   * @param normalUnitVector normal vector with magnitude 1
   * @param directionVector direction vector of any magnitude
   * @param origin point from which ray is traced (i.e. voxel coordinate)
   * @param planeDistance distance of plane from true origin (0, 0, 0)
   * @return signed distance between direction vector and plane
   */
  public static double rayTraceDistance(final double[] normalUnitVector,
      final double[] directionVector, final double[] origin,
      final double planeDistance) {

    double originNormalDotProduct = dotProduct(origin, normalUnitVector);
    double directionNormalDotProduct = dotProduct(directionVector,
        normalUnitVector);

    double t = -(originNormalDotProduct + planeDistance)
        / directionNormalDotProduct;

    return t;
  }

  /**
   * Original C code
   * http://www.ecse.rpi.edu/~wrf/Research/Short_Notes/pnpoly.html
   * Takes an array of vertices of a polygon and determines whether a point
   * is contained within the polygon or not. Ignores the z axis at the
   * moment.
   *
   * @param vertices array of 3D vertices
   * @param point point to test inclusion - must be in same plane
   *          as vertices
   * @return boolean value - in polygon or not in polygon.
   */
  public static boolean polygonInclusionTest(final double[][] vertices,
      final double[] point) {
    boolean c = false;

    for (int i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
      if (((vertices[i][1] > point[1]) != (vertices[j][1] > point[1]))
          && (point[0] < (vertices[j][0] - vertices[i][0])
              * (point[1] - vertices[i][1])
              / (vertices[j][1] - vertices[i][1]) + vertices[i][0])) {
        c = !c;
      }
    }

    return c;
  }
}

}
