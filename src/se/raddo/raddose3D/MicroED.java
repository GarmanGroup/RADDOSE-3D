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
    
    double dose3 = EMStoppingPowerWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by stopping power: %.8e", dose3));
    System.out.println(" MGy\n");
    
    //start the Monte carlo stuff
    startMonteCarlo(coefCalc, beam); 
    double dose4 = processMonteCarloDose(beam, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by Monte Carlo: %.8e", dose4));
    System.out.println(" MGy\n");
    
    
    /*
    accessESTAR(coefCalc, beam.getPhotonEnergy());
    double dose4 = getESTARDose(coefCalc, beam);
    System.out.print(String.format("\nThe Dose in the exposed area by ESTAR: %.8e", dose4));
    System.out.println(" MGy\n");
    */
    
    System.out.println(" Number elastic events: " + numberElastic);
    System.out.println(" Number single elastic events: " + numberSingleElastic);
    System.out.println(" Number productive events: " + numberProductive);
    
    System.out.println(" Number elastic events Monte Carlo: " + MonteCarloTotElasticCount);
    System.out.println(" Number single elastic events Monte Carlo: " + MonteCarloSingleElasticCount);
    
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
  double energyPerEvent = 0.037; //in keV

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

  //set up for one electron to start with and then test how many needed to get little deviation and then scale up
  int numberBackscattered = 0;
  //Start stuff to make quicker
  double startingEnergy = beam.getPhotonEnergy();
  double startingStoppingPower = coefCalc.getStoppingPower(startingEnergy);
  double startingLambda_el = coefCalc.getElectronElasticMFPL(startingEnergy);
  
  //the FSE stuff 
  
  double startingFSExSection = getFSEXSection(beam.getPhotonEnergy());
  double startingFSELambda = coefCalc.getFSELambda(startingFSExSection);
  
  //Inner shell ionisation x section
  double startingInnerShellLambda = coefCalc.betheIonisationxSection(startingEnergy);
  Map<Element, Double> ionisationProbs = coefCalc.getInnerShellProbs(); //Really need to make sure that these are in the same order
  
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
 // double lambdaT = 1 / (1/startingLambda_el + 1/startingInnerShellLambda);
  double PEL = lambdaT / startingLambda_el;
  double testRND = Math.random();
  double s = -lambdaT*Math.log(testRND);
  //now I'm going to go through the coordinates
  
  
  //Need to change these to a uniform beam
  double previousX = 0, previousY = 0; //atm starting going straight 
  double cx = 0.0001, cy = 0.0001, cz = 0.9998; //direction cosine are such that just going down in one
  //position
  double RNDx = Math.random();
  previousX = (RNDx * XDimension) - (XDimension/2);
  double RNDy = Math.random();
  previousY = (RNDy * YDimension) - (YDimension/2);
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
  
  double ca = cx;
  double cb = cy;
  double cc = cz;
  double previousZ = -ZDimension/2; //this may be dodgy if the top of the specimen is not flat...
  double xn = previousX + s * ca;
  double yn = previousY + s * cb;
  double zn = previousZ + s * cc;
  boolean exited = false;
  boolean scattered = false;
  
  
  int timesScattered = 0;
  //check if the electron has left the sample, if it has just do the dose of Z
  //if it has not left move onto the loop
  while (exited == false) {
  if (isMicrocrystalAt(xn, yn, zn) == true) {
    scattered = true;
    //reset
    cx = ca;
    cy = cb;
    cz = cc;
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
    double phi = 0, cosPhi = 1, psi = 0, AN = 0, AM = 0, V1 = 0, V2 = 0, V3 = 0, V4 = 0;
    
    if (RNDscatter > PEL) {
      //Do inelastic
 
      //firstly calculate the FSE energy
      double omega = 1 / (1000 - 998*Math.random());
      double FSEEnergy = omega * electronEnergy;
      //I'm going to take t as the energy of that particular electron
      // This could be two values for the primary, with stopping power or with inel removal
      double sinSquaredAlpha = 0;
      double sinSquaredGamma = 0;
      if (FSEEnergy > 0.5) { // so I only care about the FSE if it is more than 500eV or it probably won't escape
        // determine the angles of the FSE and the primary electron
        double tPrimary = (electronEnergy-FSEEnergy)/511; //t is in rest mass units. Need to change to stopping power en
        double tFSE = FSEEnergy/511;
        //alpha = angle of primary electron
        sinSquaredAlpha = (2 * omega) / (2 + tPrimary - tPrimary*omega);
        //gamma - angle of secondary electron
        sinSquaredGamma = 2*(1-omega) / (2 + tFSE*omega); 
      }
      //need to sort out when I'm tracking and when I'm not properly
      
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
      
      //now I have a vector need to find where it will intersect point and the distance
      double escapeDist = 1000 * getIntersectionDistance(previousX, previousY, previousZ, ca, cb, cc); //nm
      double FSEStoppingPower = coefCalc.getStoppingPower(FSEEnergy);
      double energyToEdge = FSEStoppingPower * escapeDist;
      totFSEEnergy += FSEEnergy;
      if (energyToEdge < FSEEnergy){
        MonteCarloFSEEscape += FSEEnergy - energyToEdge;
      }
      
      
      
      //track the primary electron 
      phi = Math.asin(Math.pow(sinSquaredAlpha, 0.5));
      cosPhi = Math.cos(phi);
      
      
      /*
      //so now I'm saying the collision was an inner shell ionisation event
      
      //RND to determine what element that came from - which element should be added into the elastic as well
      double elementRND = Math.random();
      Element collidedElement = null;
      for (Element e : ionisationProbs.keySet()) {
        if (ionisationProbs.get(e) > elementRND) { //Then this element is the one that was ionised
          collidedElement = e;
          break;
        }
      }
      
    //RND for FL or Auger given it was that element
      double fluoresenceYieldKRND = Math.random();
      double KshellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
      if (fluoresenceYieldKRND <= KshellFluorescenceYield) { 
      //then it's fluorescence
        // get the absorption coefficient of the crystal
        double flEnergy = collidedElement.getKFluorescenceAverage();
        double absCoef = coefCalc.getEMFlAbsCoef(flEnergy); //units um^-1
        //get a random direction vector
        double xNorm = Math.random();
        double yNorm = Math.random();
        double zNorm = Math.random();
        //Draw the vector to the edge
        double flEscapeDist = getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm); //um
        double escapeFraction = Math.exp(-absCoef * flEscapeDist);
        MonteCarloFlEscape += escapeFraction * flEnergy;
        
      }
      else {
        //need to do Auger electrons
        //Auger electron energy equals flEnergy - shell binding energy of Auger electron
        //for now ignore the shell binding energy so overestimating their significance
        double augerEnergy = collidedElement.getKFluorescenceAverage();
        totAugerEnergy += augerEnergy;
        //get a random direction vector
        double xNorm = Math.random();
        double yNorm = Math.random();
        double zNorm = Math.random();
        //Draw the vector to the edge
        double augerEscapeDist = 1000* getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm); //um
        double augerStoppingPower = coefCalc.getStoppingPower(augerEnergy);
        double augerEnergyToEdge = augerStoppingPower * augerEscapeDist;
        if (augerEnergyToEdge < augerEnergy){
          MonteCarloAugerEscape += augerEnergy - augerEnergyToEdge;
        }
      }
      */
      
    } 
    else { //else it stays false and the collision will be elastic
      
      timesScattered += 1;
      MonteCarloTotElasticCount += 1;

      //now start the loop - clean up the first iteration into this later 
      
      //get the angles
      double alpha = coefCalc.getRutherfordScreening(electronEnergy);
      double RND = Math.random();
      cosPhi = 1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND));
      phi = Math.acos(cosPhi);
    }
    //now further update the primary
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
      

   
      //update stopping powers
      //get new stoppingPower
      electronEnergy -= energyLost; 
      stoppingPower = coefCalc.getStoppingPower(electronEnergy);
      //get new lambdaT
      double FSExSection = getFSEXSection(electronEnergy * 1000);
      double FSELambda = coefCalc.getFSELambda(FSExSection);
      double lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy);
      double innerShellLambda = coefCalc.betheIonisationxSection(electronEnergy);
    //  lambdaT =  1 / (1/coefCalc.getElectronElasticMFPL(electronEnergy) + 1/FSELambda);
    lambdaT =  1 / (1/coefCalc.getElectronElasticMFPL(electronEnergy) + 1/innerShellLambda);
 //     lambdaT =  1 / (1/lambdaEl);
      s = -lambdaT*Math.log(Math.random());
      PEL = lambdaT / lambdaEl;
      ionisationProbs = coefCalc.getInnerShellProbs();
      
      //update to new position
      xn = previousX + s * ca;
      yn = previousY + s * cb;
      zn = previousZ + s * cc;
      
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
      s = 1000 * getIntersectionDistance(previousX, previousY, previousZ, ca, cb, cc);
      //I'm going to get the point as well for now as it may be useful when doing apertures and stuff
      //It's also useful for backscattering!!!!
      double[] intersectionPoint = getIntersectionPoint(s, previousX, previousY, previousZ, ca, cb, cc);
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

 //  MonteCarloDose -= MonteCarloFSEEscape;
 //  MonteCarloDose -= MonteCarloAugerEscape;
 //  MonteCarloDose -= MonteCarloFlEscape;
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
  //integrate equation - currently this isn't right... Do it numerically
//  double constant = (Math.PI * Math.pow(Beam.ELEMENTARYCHARGE, 4)) / Math.pow(electronEnergy*Beam.KEVTOJOULES, 2);  //maybe go with Murata
//  double constant = (Math.PI * Math.exp(4)) / Math.pow(electronEnergy*Beam.KEVTOJOULES, 2);  
  double constant = (6.21E-20 / Math.pow(electronEnergy, 2)); // *1E14;  //nm^2 ???
  
  //So the equation in Murata is cross section per electron (i assume cm^2/electron). So need to
  //1) Work out electrons per unit volume
  //2) multiply to get cross section in cm^-1 and convert to nm^-1
  
 // double crossSection = 0; //so this is in cm^2
  
  //equ integrates t 1/1-x -1/x + C
 double  crossSection = (1/(1-0.5) - 1/0.5) - ((1/(1-0.001) - 1/0.001));
  
  
  /*
  for (int i = 2; i <= 50; i++) {
    double omega = (double) i /100;
    double omegaMinusOne = ((double)i-1) / 100;
    double width = ((double)i /100) - (((double)i-1)/100);
    double height = ((constant * ((1/Math.pow(omega, 2)) + (1/Math.pow(1-omega, 2))))
                    + (constant * ((1/Math.pow(omegaMinusOne, 2)) + (1/Math.pow(1-omegaMinusOne, 2))))) 
                    / 2;
    crossSection += width * height;
  }
  */
 
 crossSection *= constant;  // I think this is now in cm^2 per electron
 
  return crossSection; //cm^2/atom //nm^2 per atom??? //Really not sure about units here
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
