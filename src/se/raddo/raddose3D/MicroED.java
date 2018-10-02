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
    
    /*
    accessESTAR(coefCalc, beam.getPhotonEnergy());
    double dose4 = getESTARDose(coefCalc, beam);
    System.out.print(String.format("\nThe Dose in the exposed area by ESTAR: %.8e", dose4));
    System.out.println(" MGy\n");
    */
    
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
  
}
