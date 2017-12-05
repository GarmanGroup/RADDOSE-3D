package se.raddo.raddose3D;

import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.util.HashMap;
import java.util.Map;

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
//import java.math.*;

public class CrystalEM extends Crystal {

  /**
   * 3 element array defining dimensions of bounding box of crystal in um. 
   */
  private final double[]       crystSizeUM = null;
  
  public double crystalSurfaceArea, XDim, YDim;
  
  public double sampleThickness;
  
  public double crystalVolume;
  
  public double stoppingPower;
  
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
    
    //Reduce electron number if beam bigger than the sample
    
    if (totExposedArea < (beam.getBeamX()*beam.getBeamY() * 1E08)) {
      double fractionFlux = totExposedArea / (beam.getBeamX()*beam.getBeamY() * 1E08);
      electronNumber = electronNumber * fractionFlux;
    }
    
    
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
 
    //will need to edit when I add in circular
    double exposedArea = getExposedX(beam) * getExposedY(beam);
    double exposedVolume = exposedArea  * (sampleThickness/1000) * 1E-15; //exposed volume in dm^3
    
    double electronNumber = getElectronNumber(beam, wedge, exposedArea);
    
    
    double solventFraction = coefCalc.getEMSolventFraction();
    
    //now I need to calcWaters here as don't have access to crystal properties in coefCalcEM 
   
    //way 1 - density

    //way 2 = their way
    coefCalc.calculateSolventWaterEM(solventFraction, exposedVolume);
    //density
    coefCalc.calculateDensityEM(exposedVolume);
    System.out.println(String.format("\nDensity: %.2e", coefCalc.getDensity()));
    
    //Testing
    double elasticProbOverT = coefCalc.getElectronElastic(beam);
    double elasticProb = elasticProbOverT * sampleThickness;
    
    
    double inelasticProbOverT = coefCalc.getElectronInelastic(beam, exposedVolume);
    double inelasticProb = inelasticProbOverT * sampleThickness;
    //same for solvent
    double solventInelasticProbOverT = coefCalc.getElectronInelasticSolvent(beam);
    double solventInelasticProb = solventInelasticProbOverT * sampleThickness;
    
    //TO TEST
  //  solventInelasticProb = 0;
    

    double numberInelasticEvents = (inelasticProb * electronNumber) + (solventInelasticProb * electronNumber);
    double energyDeposited = (energyPerEvent * numberInelasticEvents) * Beam.KEVTOJOULES; //in J
    double exposedMass = ((coefCalc.getEMConc() * exposedVolume) / 1000) + (((930 * solventFraction) * exposedVolume) / 1000);  //in Kg 
    double dose = (energyDeposited/exposedMass) / 1E06; //dose in MGy //thickness isn't making a difference on dose as mass increases with it
    
    return dose;
  }
  
  private double EMStoppingPowerWay(Beam beam, Wedge wedge, CoefCalc coefCalc) {
    int numSlices = 1;
    double avgElectronEnergy = beam.getPhotonEnergy();
    double dose = 0; 
    double exposedArea = getExposedX(beam) * getExposedY(beam);
    double exposedVolume = exposedArea  * (sampleThickness/1000) * 1E-15; //exposed volume in dm^3
    double solventFraction = coefCalc.getEMSolventFraction();
    
    for (int i = 0; i < numSlices; i++) {
      accessESTAR(coefCalc, avgElectronEnergy);
      // stopping power = MeV cm2/g
      double mevPerCm = stoppingPower * coefCalc.getDensity();
      double kevPerElectron = (mevPerCm * ((sampleThickness / 1E07)/numSlices)) *1000;
      avgElectronEnergy -= kevPerElectron;
      double electronNumber = getElectronNumber(beam, wedge, exposedArea);
      double energyDeposited = (kevPerElectron * electronNumber) * Beam.KEVTOJOULES; //in J
      double exposedMass = ((coefCalc.getEMConc() * exposedVolume) / 1000) + (((930 * solventFraction) * exposedVolume) / 1000);  //in Kg 
      dose += (energyDeposited/exposedMass) / 1E06; //dose in MGy
    }
    return dose;
  }
  
  private double getElectronNumber(Beam beam, Wedge wedge, double exposedArea) {
    double electronNumber = beam.getPhotonsPerSec() * wedge.getTotSec();
 //   double exposedArea = getExposedX(beam) * getExposedY(beam);
    //Remove electrons that go around edge of sample if beam bigger than sample
    if (exposedArea < (beam.getBeamX()*beam.getBeamY())) {
      double fractionFlux = exposedArea / (beam.getBeamX()*beam.getBeamY());
      electronNumber = electronNumber * fractionFlux;
    }
    return electronNumber;
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
    
    stoppingPower = Double.parseDouble(stoppingPowerString);
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

  
  @Override
  public void CalculateEM(Beam beam, Wedge wedge, CoefCalc coefCalc) {
    double dose1 = EMLETWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by LET: %.8e", dose1));
    System.out.println(" MGy\n");
 
  
    double dose2 = EMEquationWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by equation: %.8e", dose2));
    System.out.println(" MGy\n");
    
    
    double dose3 = EMStoppingPowerWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by stopping power: %.8e", dose3));
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
