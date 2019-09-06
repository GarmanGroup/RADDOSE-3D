package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

import se.raddo.raddose3D.CrystalPolyhedron.Vector;


public class MC {
  //polyhderon variables
  public double[][] verticesXFEL;
  public double[][] rotatedVerticesXFEL;
  public double[][][]          expandedRotatedVerticesXFEL;
  
  public int[][] indicesXFEL;
  public double[][][][] crystCoordXFEL;
  public double crystalPixPerUMXFEL;
  public int[] crystalSizeVoxelsXFEL;
  public boolean[][][][] crystOccXFEL;
  public int runNumber;
  /**
   * Normal array holding normalised direction vectors for
   * each triangle specified by the index array.
   * Contains an i, j, k vector per triangle.
   * Should have same no. of entries as the indices array.
   */
  private double[][]            normals, rotatedNormals;
  //test
  /**
   * Distances from origin for each of the triangle planes.
   * Should have same no. of entries as the indices array.
   */
  private double[]              originDistances, rotatedOriginDistances;
  
  
  public double[][] verticesSurrounding;
  public double[][] rotatedVerticesSurrounding;
  public double[][][] expandedRotatedVerticesSurrounding;
  public int[][] indicesSurrounding;
  private double[][] normalsSurrounding, rotatedNormalsSurrounding;
  private double[] originDistancesSurrounding, rotatedOriginDistancesSurrounding;
  
  
  public double XDimension; //nm
  public double YDimension;
  public double ZDimension;
  
  private double[] maxDims;
  private double[] minDims;
  
  public double dose;
  public double photonDose;
  public double electronDose;
  public double gosElectronDose;
  public double photonDosevResolved;
  public double gosElectronDosevResolved;
  
  public double electronDoseSurrounding;
  public double raddoseStyleDose;
  public double raddoseStyleDoseCompton;
  public double escapedEnergy;
  
  private double lastTime;
  private double lastTimeVox[];
  
  private double[] angularEmissionProbs;
  private final int numberAngularEmissionBins = 50;
  
  private TreeMap<Double, double[]>[]  lowEnergyAngles;
  private TreeMap<Double, double[]>[]  highEnergyAngles;
  
  private HashMap<Integer, HashMap<Integer, double[]>> augerTransitionLinewidths;
  private HashMap<Integer, HashMap<Integer, double[]>> augerTransitionProbabilities;
  private HashMap<Integer, HashMap<Integer, double[]>> cumulativeTransitionProbabilities;
  private HashMap<Integer, HashMap<Integer, double[]>> augerTransitionEnergies;
  private HashMap<Integer, HashMap<Integer, double[]>> augerExitIndex;
  private HashMap<Integer, HashMap<Integer, double[]>> augerDropIndex;
  private int[] augerElements = {6, 7, 8, 11, 12, 14, 15, 16, 17, 19, 20, 25, 26, 27, 28, 29, 30, 33, 34};
  private HashMap<Integer, Double> totKAugerProb;
  
  private HashMap<Integer, HashMap<Integer, double[]>> flTransitionLinewidths;
  private HashMap<Integer, HashMap<Integer, double[]>> flTransitionProbabilities;
  private HashMap<Integer, HashMap<Integer, double[]>> flCumulativeTransitionProbabilities;
  private HashMap<Integer, HashMap<Integer, double[]>> flTransitionEnergies;
  private HashMap<Integer, HashMap<Integer, double[]>> flDropIndex;
    
  //ionisationStuff
  private long totalIonisationEvents;
  private double totalIonisationEventsPerAtom;
  private double totalIonisationEventsPerNonHAtom;
  private long totalIonisationEventsvResolved;
  private double totalIonisationEventsPerAtomvResolved;
  private double totalIonisationEventsPerNonHAtomvResolved;
  
  
  private int ionisationsPerPhotoelectron;
  private double totalShellBindingEnergy;
  private double ionisationsOld;
  private HashMap<Element, Long> atomicIonisations;
  private HashMap<Element, Double> atomicIonisationsPerAtom;
  private HashMap<Element, Long> atomicIonisationsExposed;
  private HashMap<Element, Double> atomicIonisationsPerAtomExposed;
  private long lowEnergyIonisations;
  private double lowEnergyIonisationsPerAtom;
  
  private TreeMap<Double, Double> energyPerInel;
  private TreeMap<Double, Double> energyPerInelSurrounding;
  private final int numInelEnBins = 100;
  private TreeMap<Double, Double> stragglingPerInel;
  private TreeMap<Double, Double> stragglingPerInelSurrounding;
  
  public final double Wcc = 0.0;
  private double avgW;
  private int  avgWNum;
  private double avgUk;
  private int avgUkNum;
  
  private boolean verticalGoni;
  
  private double RD3D_ADWC;
  private double RD3D_ADER;
  
  private double[][][] voxelEnergy;
  private double[][][] voxelEnergyvResolved;
  private double[][][] doseSimple;
  private double[][][] voxelIonisationsvResolved;
  public double[][][] voxelElastic;
  private double totElastic;
  
  
  private final boolean silicon = false;
  
  private boolean simpleMC = false; 
  private boolean doXFEL = true;
  
  
  private double numFluxPhotons;
  protected long NUM_PHOTONS = 100000;
  protected  long PULSE_LENGTH = 30; //length in fs
  protected double PULSE_BIN_LENGTH = 0.1; //length in fs
  protected double PULSE_ENERGY = 1.4E-3; //energy in J
  protected static final double c = 299792458; //m/s
  protected static final double m = 9.10938356E-31; // in Kg
  protected static final double h = 6.62607004E-34; //J.s
  
  
  // Initialising pink beam parameters - 
  private double meanEnergy;
  private Double energyFWHM;
  private double[] photonEnergyArray;
//private double totalNumberOfPhotons = PULSE_ENERGY / meanEnergy; // need to check where this is computed elsewhere and change 
  
  private double[] surroundingThickness;
  
  public MC(double vertices[][], int[][] indices, double[][][][] crystCoord, 
      double crystalPixPerUM, int[] crystSizeVoxels, boolean[][][][] crystOcc, int runNum, boolean verticalGoniometer,
      boolean xfel, boolean gos, double[] surrThickness) {
    verticesXFEL = vertices;
    indicesXFEL = indices;
    crystCoordXFEL = crystCoord;
    crystalPixPerUMXFEL = crystalPixPerUM;
    crystalSizeVoxelsXFEL = crystSizeVoxels;
    crystOccXFEL = crystOcc;
   // surroundingThickness = surrThickness;
    surroundingThickness = new double[3];
    for (int i = 0; i < 3; i++) {
      surroundingThickness[i] = 1000*surrThickness[i];
    }
    
    //set up cryocystal
    verticesSurrounding = new double[verticesXFEL.length][3];
    indicesSurrounding = new int[indicesXFEL.length][3];
    
    verticalGoni = verticalGoniometer;
    doXFEL = xfel;
    simpleMC = !gos;
    
    double[] xMinMax = this.minMaxVertices(0, vertices);
    double[] yMinMax = this.minMaxVertices(1, vertices);
    double[] zMinMax = this.minMaxVertices(2, vertices);
    XDimension = 1000 * (xMinMax[1] - xMinMax[0]);
    YDimension = 1000 * (yMinMax[1] - yMinMax[0]);
    ZDimension = 1000 * (zMinMax[1] - zMinMax[0]);
    
    
    int[] maxVoxel = getMaxPixelCoordinates();

    voxelEnergyvResolved = new double[maxVoxel[0]][maxVoxel[1]][maxVoxel[2]];
    voxelIonisationsvResolved = new double[maxVoxel[0]][maxVoxel[1]][maxVoxel[2]];
    voxelElastic = new double[maxVoxel[0]][maxVoxel[1]][maxVoxel[2]];
    lastTimeVox = new double[maxVoxel[2]];
    doseSimple = new double[maxVoxel[0]][maxVoxel[1]][maxVoxel[2]];
    
    atomicIonisations = new HashMap<Element, Long>();
    atomicIonisationsPerAtom = new HashMap<Element, Double>();
    atomicIonisationsExposed = new HashMap<Element, Long>();
    atomicIonisationsPerAtomExposed = new HashMap<Element, Double>();
    
    lowEnergyAngles = new TreeMap[95];
    highEnergyAngles = new TreeMap[95];
    
    augerTransitionLinewidths = new HashMap<Integer, HashMap<Integer, double[]>>();
    augerTransitionProbabilities = new HashMap<Integer, HashMap<Integer, double[]>>();
    augerTransitionEnergies = new HashMap<Integer, HashMap<Integer, double[]>>();
    totKAugerProb = new HashMap<Integer, Double>();
    cumulativeTransitionProbabilities = new HashMap<Integer, HashMap<Integer, double[]>>();
    augerExitIndex = new HashMap<Integer, HashMap<Integer, double[]>>();
    augerDropIndex = new HashMap<Integer, HashMap<Integer, double[]>>();
    
    flTransitionLinewidths = new HashMap<Integer, HashMap<Integer, double[]>>();
    flTransitionProbabilities = new HashMap<Integer, HashMap<Integer, double[]>>();
    flTransitionEnergies = new HashMap<Integer, HashMap<Integer, double[]>>();
    flCumulativeTransitionProbabilities = new HashMap<Integer, HashMap<Integer, double[]>>();
    flDropIndex = new HashMap<Integer, HashMap<Integer, double[]>>();
    
    energyPerInel = new TreeMap<Double, Double>();
    energyPerInelSurrounding = new TreeMap<Double, Double>();
    stragglingPerInel = new TreeMap<Double, Double>();
    stragglingPerInelSurrounding = new TreeMap<Double, Double>();
    
    runNumber = runNum;
    
    maxDims = new double[3];
    minDims = new double[3];
    
    
    
    
  }
  
  private void setupSurroundingVolume() {
    
    for (int i = 0; i < verticesXFEL.length; i++) {
      for (int j = 0; j < 3; j++) {
        if (verticesXFEL[i][j] < 0) {
          verticesSurrounding[i][j] = verticesXFEL[i][j] - surroundingThickness[j]/1000;
        }
        else {
          verticesSurrounding[i][j] = verticesXFEL[i][j] + surroundingThickness[j]/1000;
        }
      }
    }
    
    indicesSurrounding = indicesXFEL;
  }
  
  
  public void CalculateXFEL(Beam beam, Wedge wedge, CoefCalc coefCalc) {

    //set pulse length and num photons from input
    
    //put in a flux if doXFEL not true
    
    if (doXFEL == true) {
      PULSE_ENERGY = beam.getPulseEnergy() / 1E3;
    }
    else {
      double flux = beam.getPhotonsPerSec();
      double numPhotons = flux * wedge.getTotSec();
      PULSE_ENERGY = numPhotons * (beam.getPhotonEnergy()*Beam.KEVTOJOULES);
    }
     
    PULSE_LENGTH = StrictMath.round(wedge.getTotSec()); //fs
    if (PULSE_LENGTH <= 1) {
      PULSE_BIN_LENGTH = 0.01;
    }
    else if (PULSE_LENGTH <= 50) {
      PULSE_BIN_LENGTH = 0.1;
    }
    else {
      PULSE_BIN_LENGTH = 1;
    }
    
    NUM_PHOTONS = coefCalc.getNumberSimulatedElectrons();
    if (NUM_PHOTONS == 0) {
      NUM_PHOTONS = 1000000;
    }
    
    meanEnergy = beam.getPhotonEnergy();
    energyFWHM = beam.getEnergyFWHM();
    if(energyFWHM == null) {double[] array = new double[(int)NUM_PHOTONS]; Arrays.fill(array, meanEnergy); photonEnergyArray = array;}
    else {
    SampleNormalEnergyDistribution SamplePhotonEnergies = new SampleNormalEnergyDistribution(meanEnergy, energyFWHM, (NUM_PHOTONS)); //eventually need to not reply on assumption it is normal   
    photonEnergyArray = SamplePhotonEnergies.getSampledEnergies();}                     
    
    // for testing
    lastTime = ((1/c) * (ZDimension/1E9) * 1E15) + PULSE_LENGTH;
   // numFluxPhotons = beam.getPhotonsPerSec() * wedge.getTotSec();
    //get the time at which the last photon exits the last voxel
    for (int i = 0; i < lastTimeVox.length; i++) {
      lastTimeVox[i] = ((1/c) * ((ZDimension/1E9)/lastTimeVox.length)*(i+1) * 1E15) + PULSE_LENGTH;
    }
    /*
    double energyPerPhoton = beam.getPhotonEnergy()*Beam.KEVTOJOULES; // would need to move this into for loop if decide to reinstate
    double numberOfPhotons = PULSE_ENERGY/energyPerPhoton;
    double fractionel = getFractionElasticallyScattered(coefCalc);
    double numberElastic = numberOfPhotons * fractionel;
    */
    long start = System.nanoTime();
    startMonteCarloXFEL(beam, wedge, coefCalc);
    processDose(beam, coefCalc);
    RD3Dcheck(beam, wedge, coefCalc);
    long runtime = System.nanoTime() - start;
    System.out.println(String.format("RADDOSE-XFEL simulation complete, runtime in seconds was: %.8e", runtime/1E9));
    

  }
  
  /**
   * @param beam
   * @param wedge
   * @param coefCalc
   */
  public void startMonteCarloXFEL(Beam beam, Wedge wedge, CoefCalc coefCalc) {
    
    
    //populate augerLinewidths
      try {
        populateAugerLinewidths();
        populateFluorescenceLinewidths();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
    //get how much the electron deposits per inelastic interaction at several energies in this material
  //  populateEnergyPerInel(beam, coefCalc);
    //get the straggling for all of these
  //  populateStraggling(beam, coefCalc);
    
    //populate the angular emission probs
    populateAngularEmissionProbs();                   // DONT MOVE IN
    
    //elastic electron angle setup
    coefCalc.populateCrossSectionCoefficients();     // DONT MOVE IN
    
    double angle = 0;
    
    //Decide a starting time stamp for the photons
    double photonDivisions =  NUM_PHOTONS / (PULSE_LENGTH/PULSE_BIN_LENGTH);
     
    double xn = 0, yn = 0, zn = 0, progress = 0, lastProgress = 0;
    for (int i = 0; i < NUM_PHOTONS; i++) { //for every photon to simulate
      double energyOfPhoton = photonEnergyArray[i];
      progress = ((double)i)/NUM_PHOTONS;
      if (progress - lastProgress >= 0.05) {
        lastProgress = progress;
        System.out.print((int)(progress*100) + "% ");
      }
      
      //need to get the angle if there is any rotation 
      if ((Math.abs(wedge.getStartAng() - wedge.getEndAng()) < wedge.getAngRes()) || (wedge.getEndAng() == 0)) {
        angle = 0;
      }
      else {
        //choose angle dependent on angular resolution and how far in the photon is
        Integer sign = 1;
        if (wedge.getEndAng() < wedge.getStartAng()) {
          sign = -1;
        }
        angle = wedge.getStartAng() + (progress * (sign * (int) (wedge.getEndAng() - wedge.getStartAng())));
      }
      //convert to 0-2pi
      int timesOver = (int) (angle/(2*Math.PI));
      angle = angle - (timesOver * 2 *Math.PI);
      
      //need to update the vertices and stuff 
      setUpRotatedVertices(angle, wedge);
      
      
      // SECTION FOR STUFF MOVED INTO FOR LOOP
      //get absorption coefficient
      coefCalc.updateCoefficients(energyOfPhoton);                                                                       // CHANGE AS EXPLAINED!

      double absCoef = coefCalc.getAbsorptionCoefficient(); //um-1
      double comptonCoef = coefCalc.getInelasticCoefficient(); //um-1
      double elasticCoef = coefCalc.getElasticCoefficient(); //um^-1
      
//    double photonMFPL = (1/absCoef)*1000; //just photoelectric absorption for now can put in Compton later
      double photonMFPL = (1/(absCoef + comptonCoef))*1000; //including Compton
      double probCompton = 1 - (photonMFPL/((1/absCoef)*1000));
      double totalMFPL = (1/(absCoef + comptonCoef + elasticCoef))*1000;
      double elasticProb = elasticCoef / (absCoef + comptonCoef);
      
      //populate the relative element cross sections here 
      Map<Element, Double> elementAbsorptionProbs = coefCalc.getPhotoElectricProbsElement(energyOfPhoton);
      Map<Element, Double> elementComptonProbs = coefCalc.getComptonProbsElement(energyOfPhoton);
      //populate the relative shell cross sections
      Map<Element, double[]> ionisationProbs = getRelativeShellProbs(elementAbsorptionProbs, energyOfPhoton);

      
      // AND SOME MORE STUFF MOVED IN
      
      //set up the surrounding stuff if there is one
      double absCoefSurrounding = 0, comptonCoefSurrounding = 0, photonMFPLSurrounding = 0, probComptonSurrounding = 0, distanceNM = 0, wickTest = 0;
      Map<Element, Double> elementAbsorptionProbsSurrounding = null;
      Map<Element, double[]> ionisationProbsSurrounding = null;
      if (coefCalc.isCryo() == true) { //user wants to simulate a surrounding
        coefCalc.updateCryoCoefficients(energyOfPhoton);                                                                       // CHANGE AS DISCUSSED
        absCoefSurrounding = coefCalc.getCryoAbsorptionCoefficient();
        comptonCoefSurrounding = coefCalc.getCryoInelasticCoefficient();
        photonMFPLSurrounding = (1/(absCoefSurrounding + comptonCoefSurrounding))*1000;
        probComptonSurrounding = 1 - (photonMFPLSurrounding/((1/absCoefSurrounding)*1000));
        elementAbsorptionProbsSurrounding = coefCalc.getPhotoElectricProbsElementSurrounding(energyOfPhoton);
        ionisationProbsSurrounding = getRelativeShellProbs(elementAbsorptionProbsSurrounding, energyOfPhoton);
        //just use the same angular emission probs
        
        //get the maximum photoelectron travel distance (based on photon energy) for tracking purposes   
        //take the max using the CSDA without integration so a little bit of an overestimate 
        double stoppingPower = coefCalc.getStoppingPower(energyOfPhoton, true);
        distanceNM = (energyOfPhoton/stoppingPower);      
        wickTest = distanceNM;
        for (int test = 0; test < 3; test++) {
          if (surroundingThickness[test] <= 0.0 || surroundingThickness[test] > wickTest) {
            surroundingThickness[test] = wickTest;
          }
        }
        
        setupSurroundingVolume();
        //rotateSurrounding
        setUpRotatedVerticesSurrounding(angle, wedge);
        //space here to change the wickTest distance
        //wickTest = 1000;
       // wickTest = beam.getPulseEnergy() * 1000;
        
        
        
        //set up my cryo crystal bigger than the normal one using this distance, similar to PE escape stuff
        
      }
      
      
      
      
      // AND CONTINUE
      
      boolean exited = false;
      double timeStamp = ((int) (i/ photonDivisions)) * PULSE_BIN_LENGTH;
      //firstly I need to get a position of the beam on the sample. the direction will simply be 0 0 1
      
      
      //will need to change these based on the angle 
      double xNorm = 0.0000, yNorm = 0.0000, zNorm = 1.0; //direction cosine are such that just going down in one
      double theta = 0, phi = 0, previousTheta = 0, previousPhi = 0, thisTheta = 0;
      
      
      double previousZ = -ZDimension/2;  //dodgy if specimen not flat - change for concave holes
      
     
      
      //need to add start offset and translation to the xy position as well
      
      //position
      
      double[] xyPos = getPhotonBeamXYPos(beam, wedge);
      double previousX = xyPos[0];
      double previousY = xyPos[1];
      //bring the z to the start of the surrounding volume
      boolean track = true;
      double tempZ = 0;
      double tempZNorm = -1.0;
      
      
      
      //this doesn't quite work as surrounding could be in front of z = 0 if rod in front;
      
      //or could be interacting twice with the plane if rod behind
      //if it interacts twice I need to take the longer distance - but then could interact far away even more times...
      //my fudge doesn't even always work
      
      
      //defo needs sorting
      
      
      
      Double intersectionDistance = 0.0;
      boolean surrounding = false, hitting = true;
      double[] coord = {previousX/1000, previousY/1000, tempZ/1000};
      double crystalZ = 0;
      if (coefCalc.isCryo()) {
        //place photon Z at start of surrrounding
        previousZ = getStartingZSurrounding(angle, wedge, coord); 
        crystalZ = getStartingZ(angle, wedge, coord, true); 
        if (crystalZ == previousZ) {
          //then there is no surrounding here
          surrounding = false;
        }
        else {
          surrounding = true;
        }
        if (crystalZ == 0.0) { // this is a photon that I want to track but will never hit the crystal
          hitting = false;
        }
      }
      else{
        //Place photon Z at start of crystal 
        previousZ = getStartingZ(angle, wedge, coord, true); 
        surrounding = false;
      }
      if (previousZ == 0.0) {
        track = false; //outside tracked area
        exited = true;
      }
      
      
      double s = 0;
      //the next step will be to get s
      
      
      //first deal with photons that first see surrounding
      if (track == true) {
        if (surrounding == true) {
          s = -photonMFPLSurrounding*Math.log(Math.random());
          if (hitting == true) { //need to deal with photons that could interact before the crystal as MFPL changes on hitting xtal
            double frontThickness = Math.abs(crystalZ-previousZ);
            if (s < frontThickness) { // has interacted before the crystal
              xn = previousX + s * xNorm;
              yn = previousY + s * yNorm;
              zn = previousZ + s * zNorm;
              double RNDcompton = Math.random();
              int doseTime = 0;
              if (RNDcompton < probComptonSurrounding) {
                //produce a compton electron
                produceCompton(beam, coefCalc, timeStamp, xn, yn, zn, surrounding, energyOfPhoton, elementComptonProbs, angle, wedge);
              }
              else {
                //produce a photoelectron
                producePhotoElectron(beam, coefCalc, elementAbsorptionProbsSurrounding, ionisationProbsSurrounding, timeStamp, doseTime, xn, yn, zn, surrounding, energyOfPhoton, angle, wedge);
              }           
              // set exited to true so this photon is no longer tracked 
              exited = true;
            }
          }
        }
      }
      
      //now set s for the photons that didn't interact before crystal
      if (hitting == true) {
        s = -totalMFPL*Math.log(Math.random());
      }
      //get interaction position
      xn = previousX + s * xNorm;
      yn = previousY + s * yNorm;
      zn = previousZ + s * zNorm;
      
      //now start the simulation
      
      while (exited == false) {
        if (isMicrocrystalAt(xn, yn, zn, angle, wedge) == true) { //if it hit the crystal
          surrounding = false;
          // if the microcrystal is here a photoelectron will need to be produced
          //determine the time at which this happened = startingTimeStamp + time to this point
          double timeToPoint = (1/c) * (s/1E9); //in seconds
          timeStamp += timeToPoint * 1E15; //time from start of pulse that this happened
          int doseTime = (int) (timeStamp/PULSE_BIN_LENGTH); //rounding down so 0 = 0-0.99999, 1 - 1-1.99999 etc 
          
          //determine if elastic or inel
          double RNDel = Math.random();
          if (RNDel < elasticProb) {
            //do elastic
            int[] pixelCoord = convertToPixelCoordinates(xn, yn, zn, angle, wedge);
            voxelElastic[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += 1;
            totElastic += 1;
          }
          else {
          //determine if this was Compton scattering or photoelectric absorption
          double RNDcompton = Math.random();
          if (RNDcompton < probCompton) {
            ionisationsOld += 1;
            produceCompton(beam, coefCalc, timeStamp, xn, yn, zn, surrounding, energyOfPhoton, elementComptonProbs, angle, wedge);
          }
          else {
            //this was a photoelectric absorption
            ionisationsOld += 1;
            producePhotoElectron(beam, coefCalc, elementAbsorptionProbs, ionisationProbs, timeStamp, doseTime, xn, yn, zn, surrounding, energyOfPhoton, angle, wedge);
          }
          }
          //photon is absorbed so don't need to keep track of it after this and update stuff
        }
        else { // this photon didn't hit the crystal
          if (coefCalc.isCryo()) {
            //first thing to check if it interacted in the tracked area
            if (calculateCrystalOccupancySurrounding(xn, yn, zn) == true) {
              surrounding = true;
              if (hitting == false) {//it's gone through the edge
                  double timeToPoint = ((1/c) * (s/1E9)); //in seconds
                  timeStamp += timeToPoint * 1E15; //time from start of pulse that this happened
                  int doseTime = (int) (timeStamp/PULSE_BIN_LENGTH); //rounding down so 0 = 0-0.99999, 1 - 1-1.99999 etc 
                  double RNDcompton = Math.random();
                  if (RNDcompton < probComptonSurrounding) {
                    //produce a compton electron
                    produceCompton(beam, coefCalc, timeStamp, xn, yn, zn, surrounding, energyOfPhoton, elementComptonProbs, angle, wedge);
                  }
                  else {
                    //produce a photoelectron
                    producePhotoElectron(beam, coefCalc, elementAbsorptionProbsSurrounding, ionisationProbsSurrounding, timeStamp, doseTime, xn, yn, zn, surrounding, energyOfPhoton, angle, wedge);
                  }           
                }
              else {
                //it's gone through the crystal and come out the back - check if it will interact with the behind surrounding
                surrounding = true;
                //update timeStamp
                double timeToPoint = (1/c) * (ZDimension/1E9);
                timeStamp += timeToPoint * 1E15;
                
                //would need to make changes here if silicon is going at the back 
                
                if (silicon == true) {
                  Map<String, Double> siCoeffs = coefCalc.calculateCoefficientsSilicon(energyOfPhoton);
                  double siMFPL = (1/(siCoeffs.get("Photoelectric") + siCoeffs.get("Compton Attenuation")))*1000;
                  s = -siMFPL*Math.log(Math.random());
                }
                else {
                  s = -photonMFPLSurrounding*Math.log(Math.random());
                }
                
                //set the Z to the point at the back of the crystal!!!
              //  previousZ = ZDimension/2;
                
                previousZ = getStartingZ(angle, wedge, coord, false);
                
                
                
                xn = previousX + s * xNorm;
                yn = previousY + s * yNorm;
                zn = previousZ + s * zNorm;
                //if (zn < wickTest + ZDimension/2) {
                if(calculateCrystalOccupancySurrounding(xn, yn, zn) == true) {
                  //then it has interacted with the surrounding behind the crystal
                  timeToPoint = (1/c) * (s/1E9);
                  timeStamp += timeToPoint * 1E15;
                  int doseTime = (int) (timeStamp/PULSE_BIN_LENGTH);
                  double RNDcompton = Math.random();
                  if (RNDcompton < probComptonSurrounding) {
                    //produce a compton electron
                    produceCompton(beam, coefCalc, timeStamp, xn, yn, zn, surrounding, energyOfPhoton, elementComptonProbs, angle, wedge);
                  }
                  else {
                    //produce a photoelectron
                    producePhotoElectron(beam, coefCalc, elementAbsorptionProbsSurrounding, ionisationProbsSurrounding, timeStamp, doseTime, xn, yn, zn, surrounding, energyOfPhoton, angle, wedge);
                  }           
                }
              }
            }
            
            
            

          }

          

        }
        exited = true; // because the photon is absorbed, also not tracking compton electrons after they scatter as unlikely to scatter again
      }
    }
    
    
// END OF FOR LOOP (ITERATING THROUGH PHOTONS)
    
    //get time at which last photon exits the sample
    lastTime = ((1/c) * (ZDimension/1E9) * 1E15) + PULSE_LENGTH;
    //get the time at which the last photon exits the last voxel
    for (int i = 0; i < lastTimeVox.length; i++) {
      lastTimeVox[i] = ((1/c) * ((ZDimension/1E9)/lastTimeVox.length)*(i+1) * 1E15) + PULSE_LENGTH;
    }
  }

// END OF MONTE CARLO
// processDose now does the work iterating through in 4D (no major changes needed)
  
  private void processDose(Beam beam, CoefCalc coefCalc) {
    //just take the whole sample, assuming it is bathed totally
    //and also just take a whole cube for now
    double voxLength = Math.pow(crystalPixPerUMXFEL, -1) * 1000; //nm
    double voxLengthRatio = ZDimension/voxLength;
    double voxelVolume = Math.pow(crystalPixPerUMXFEL, -3) * 1E-12; // cm^3
    double voxelMass = ((coefCalc.getDensity() * voxelVolume) / 1000);  //in Kg 
    double sampleVolume = XDimension * YDimension * ZDimension * 1E-21; //cm^3
    double sampleMass = ((coefCalc.getDensity() * sampleVolume) / 1000);  //in Kg 
    double totalAtoms = coefCalc.getTotalAtomsInCrystal(sampleVolume);
    double exposedVolume = getExposedArea(beam) * ZDimension * 1E-21;
    if (exposedVolume > sampleVolume) {
      exposedVolume = sampleVolume;
    }
    double volFraction = exposedVolume/sampleVolume;
    
    double meanEnergyJoules = meanEnergy*Beam.KEVTOJOULES;
    double numberOfPhotons = PULSE_ENERGY/meanEnergyJoules;
    totElastic = totElastic * (numberOfPhotons/NUM_PHOTONS);
    double testSumxyEl = 0;
    
//    numberOfPhotons = numFluxPhotons;
    int[] maxVoxel = getMaxPixelCoordinates();
    int voxelCount = 0, voxelCountExposed = 0;
    double voxDoseNoCutoff = 0, voxDose = 0, voxDoseExposed = 0, voxDoseExposedNoCutoff = 0;
    double voxDosevResolved = 0, voxDoseNoCutoffvResolved = 0, DWD = 0, DWDNoCutoff = 0, DWDcoarse = 0, DWDcoarseNoCutoff = 0;
    long ionisationsCutoff = 0, lowIonisationsCutOff = 0;
    int countCutoff = 0;
    double sumDose = 0, sumElectronDose = 0, sumPhotonDose = 0, sumElectronDoseSurrounding = 0, sumGOSDose = 0, gosTot = 0, gosTotNoCutoff = 0;
    double sumDoseNoCutOff = 0, sumElectronDoseNoCutOff = 0, sumPhotonDoseNoCutOff = 0, sumElectronDoseSurroundingNoCutOff = 0, sumGOSDoseNoCutOff = 0;
    double sumDoseNoCutOffExposed = 0, sumMCDose = 0;
    double sumGOSvResolved = 0, sumGOSvResolvedNoCutoff = 0;
    double totIonsvResolved = 0, totIonsvResolvedExposed = 0;
    for (int i = 0; i < 1; i++) {
      dose = ((dose * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES) / sampleMass) /1E6; //in MGy
      electronDose = ((electronDose * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES) / sampleMass) /1E6; //in MGy
      gosElectronDose = ((gosElectronDose * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES) / sampleMass) /1E6; //in MGy
      photonDose = ((photonDose * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES) / sampleMass) /1E6; //in MGy
      gosElectronDosevResolved = ((gosElectronDosevResolved * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES) / sampleMass) /1E6; //in MGy
      
      electronDoseSurrounding = ((electronDoseSurrounding * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES) / sampleMass) /1E6; //in MGy
      totalIonisationEvents = StrictMath.round(totalIonisationEvents * (numberOfPhotons/NUM_PHOTONS));
      totalIonisationEventsvResolved = StrictMath.round(totalIonisationEventsvResolved * (numberOfPhotons/NUM_PHOTONS));
      lowEnergyIonisations = StrictMath.round(lowEnergyIonisations * (numberOfPhotons/NUM_PHOTONS));
      if (i > 0) {
        totalIonisationEvents += totalIonisationEvents; //make it cumulative
        lowEnergyIonisations += lowEnergyIonisations;
        totalIonisationEventsvResolved += totalIonisationEventsvResolved; 
        if (i*PULSE_BIN_LENGTH < lastTime-(1*PULSE_BIN_LENGTH)) {
          ionisationsCutoff += totalIonisationEvents;
          lowIonisationsCutOff += lowEnergyIonisations;
          countCutoff += 1;
        }
      }
      totalIonisationEventsPerAtom = totalIonisationEvents/totalAtoms;
      lowEnergyIonisationsPerAtom = lowEnergyIonisations/totalAtoms;
      totalIonisationEventsPerAtomvResolved = totalIonisationEventsvResolved/totalAtoms;
      //sums
      if (i*PULSE_BIN_LENGTH < lastTime-(1*PULSE_BIN_LENGTH)) {
        sumDose += dose;
        sumElectronDose += electronDose;
        sumGOSDose += gosElectronDose;
        sumPhotonDose += photonDose;
        sumElectronDoseSurrounding += electronDoseSurrounding;
        sumGOSvResolved += gosElectronDosevResolved;
      }
      sumDoseNoCutOff += dose;

      sumElectronDoseNoCutOff += electronDose;
      sumGOSDoseNoCutOff += gosElectronDose;
      sumPhotonDoseNoCutOff += photonDose;
      sumElectronDoseSurroundingNoCutOff += electronDoseSurrounding;
      sumGOSvResolvedNoCutoff += gosElectronDosevResolved;
      
      //now process the voxel dose
      
      for (int a = 0; a < maxVoxel[0]; a++) {
        for (int b = 0; b < maxVoxel[1]; b++) {
          double xyElastic = 0;
          double energySumResolved = 0, energySum = 0;
          for (int c = 0; c < maxVoxel[2]; c++) {
            double[] cartesian = convertToCartesianCoordinates(a,b,c);
            double flux = beam.beamIntensity(cartesian[0]/1000, cartesian[1]/1000, 0);
            sumMCDose += ((doseSimple[a][b][c] * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES));
            if (testIfInsideExposedArea(cartesian[0], cartesian[1], beam) == true) { 
              sumDoseNoCutOffExposed += ((doseSimple[a][b][c] * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES));
            }
            
         //   voxelEnergy[a][b][c][i] = ((voxelEnergy[a][b][c][i] * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES));// / voxelMass) /1E6; //in MGy
            voxelEnergyvResolved[a][b][c] = ((voxelEnergyvResolved[a][b][c] * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES));
            voxelIonisationsvResolved[a][b][c] = ((voxelIonisationsvResolved[a][b][c] * (numberOfPhotons/NUM_PHOTONS)));
            if (i == 0) {
              voxelCount += 1;
              voxelElastic[a][b][c] = voxelElastic[a][b][c] * (numberOfPhotons/NUM_PHOTONS);
               
            }
            xyElastic += voxelElastic[a][b][c];
            
            
            if (i == 0) {
              testSumxyEl += xyElastic;
            }
            
            
            if (i*PULSE_BIN_LENGTH < lastTimeVox[c]-(1*PULSE_BIN_LENGTH)) {
        //      voxDose += voxelEnergy[a][b][c][i];
              energySumResolved += voxelEnergyvResolved[a][b][c];
              voxDosevResolved += voxelEnergyvResolved[a][b][c];
              totIonsvResolved += voxelIonisationsvResolved[a][b][c];
              if ( voxelElastic[a][b][c]> 0) { //change later to wedge.getoffAxisUM
               // voxDoseExposed += voxelEnergyvResolved[a][b][c][i];
                DWD += voxelEnergyvResolved[a][b][c] * (voxelElastic[a][b][c]/totElastic);

              }
              if (xyElastic > 0 && energySumResolved > 0) {
                if (c == maxVoxel[2] - 1) {
                  DWDcoarse += (energySumResolved) * (xyElastic/totElastic);
                }
              }
              if(testIfInsideExposedArea(cartesian[0], cartesian[1], beam) == true) { 
                voxDoseExposed += voxelEnergyvResolved[a][b][c];
                totIonsvResolvedExposed += voxelIonisationsvResolved[a][b][c];
              }
            }
        //    voxDoseNoCutoff += voxelEnergy[a][b][c][i];
            voxDoseNoCutoffvResolved += voxelEnergyvResolved[a][b][c];
            energySum += voxelEnergyvResolved[a][b][c];
            if (voxelElastic[a][b][c] > 0) { //change later to wedge.getoffAxisUM
              //  voxDoseExposedNoCutoff += voxelEnergyvResolved[a][b][c][i];
                DWDNoCutoff += voxelEnergyvResolved[a][b][c] * (voxelElastic[a][b][c]/totElastic);
              if (i == 0) {
                voxelCountExposed += 1;
              }
            }
            if (xyElastic > 0 && energySum > 0) {
              if (c == maxVoxel[2] - 1) {
                DWDcoarseNoCutoff += (energySum) * (xyElastic/totElastic);
              }
            }
            if (testIfInsideExposedArea(cartesian[0], cartesian[1], beam) == true) { 
              voxDoseExposedNoCutoff += voxelEnergyvResolved[a][b][c];
            }
          }
        }
      }
    }
    
    voxDoseNoCutoff = (voxDoseNoCutoff / sampleMass)/1E6;
    voxDose = (voxDose / sampleMass)/1E6;
    voxDoseNoCutoffvResolved = (voxDoseNoCutoffvResolved / sampleMass)/1E6;
    voxDosevResolved = (voxDosevResolved / sampleMass)/1E6;
    
    sumMCDose = (sumMCDose / sampleMass)/1E6;
    
    double fractionLow = (double)lowIonisationsCutOff / (double)ionisationsCutoff;
    double totalNonHAtoms = 0, totalHAtoms = 0, totalHAtomsExposed = 0, totalNonHAtomsExposed = 0; 
    double sumNonHIonisations = 0, sumHIonisations = 0, sumNonHIonisationsExposed = 0, sumHIonisationsExposed = 0;
    double[] ions = new double[99];
    for (Element e: atomicIonisations.keySet()) {
      if (e != null) {
      long temp = StrictMath.round(atomicIonisations.get(e) * (numberOfPhotons/NUM_PHOTONS));
      long tempExposed = StrictMath.round(atomicIonisationsExposed.get(e) * (numberOfPhotons/NUM_PHOTONS));
    //  temp += temp*fractionLow;     
      double totalAtomsElem = coefCalc.getTotalAtomsInCrystalElement(sampleVolume, e);
      double totalAtomsElemExposed = coefCalc.getTotalAtomsInCrystalElement(sampleVolume, e)*volFraction;
      double ionsPerAtom = (double)temp / totalAtomsElem;
      double ionsPerAtomExposed = (double)tempExposed / (totalAtomsElem*volFraction);
      atomicIonisationsPerAtom.put(e, ionsPerAtom);
      atomicIonisationsPerAtomExposed.put(e, ionsPerAtomExposed);
      ions[e.getAtomicNumber()] = ionsPerAtom;
      if (e.getAtomicNumber() != 1) {
        totalNonHAtoms += totalAtomsElem;
        sumNonHIonisations += temp;
        totalNonHAtomsExposed += totalAtomsElemExposed;
        sumNonHIonisationsExposed += tempExposed;
      }
      else {
        totalHAtoms += totalAtomsElem;
        sumHIonisations += temp;
        totalHAtomsExposed += totalAtomsElemExposed;
        sumHIonisationsExposed += tempExposed;
      }
      }
    }
    double ionisationsPerNonH = sumNonHIonisations/totalNonHAtoms;
    double ionisationsPerNonHExposed = sumNonHIonisationsExposed/totalNonHAtomsExposed;
    
    
    
    gosTot = sumGOSDose + sumPhotonDose;
    gosTotNoCutoff = sumGOSDoseNoCutOff + sumPhotonDoseNoCutOff;
    double ionisationsPerAtomCutoff = totalIonisationEventsPerAtom;
    double lowEnIonisationsPerAtomCutoff = lowEnergyIonisationsPerAtom;
    double ionsiationPerAtomAll = totalIonisationEventsPerAtom;
    double ionisationsPerAtomvResolved = totalIonisationEventsPerAtomvResolved;
    double ionisationsPerAtomvResolvedExposed = totalIonisationEventsPerAtomvResolved/volFraction;
    
    double totIonsperAtom = totIonsvResolved / totalAtoms;
    double totIonsperNonHAtom = (totIonsvResolved-sumHIonisations) / totalNonHAtoms;
    double totIonsperAtomExposed = totIonsvResolved / (totalAtoms*volFraction);
    double totIonsperNonHAtomExposed = (totIonsvResolved-sumHIonisationsExposed) / (totalNonHAtoms*volFraction);
    
    

    //exposed volume and mass
    double exposedMass = voxelCountExposed * voxelMass;
    
    exposedMass = ((coefCalc.getDensity() * exposedVolume) / 1000);  //in Kg
    voxDoseExposed = (voxDoseExposed / exposedMass)/1E6;
    voxDoseExposedNoCutoff = (voxDoseExposedNoCutoff / exposedMass)/1E6;
    sumDoseNoCutOffExposed = (sumDoseNoCutOffExposed / exposedMass)/1E6;
    
    DWD = (DWD / voxelMass)/1E6;
    DWDNoCutoff = (DWDNoCutoff / voxelMass)/1E6;
    DWDcoarse = (DWDcoarse / (voxelMass*voxLengthRatio))/1E6;
    DWDcoarseNoCutoff = (DWDcoarseNoCutoff / (voxelMass*voxLengthRatio))/1E6;
    
    raddoseStyleDose = ((raddoseStyleDose * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES) / sampleMass) /1E6; //in MGy
    raddoseStyleDoseCompton = ((raddoseStyleDoseCompton * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES) / sampleMass) /1E6; //in MGy)
    escapedEnergy = ((escapedEnergy * (numberOfPhotons/NUM_PHOTONS) * Beam.KEVTOJOULES) / sampleMass) /1E6; //in MGy
    ionisationsOld = ionisationsOld * (numberOfPhotons/NUM_PHOTONS) / totalAtoms;
    double totRADDOSEdose = raddoseStyleDose + raddoseStyleDoseCompton;
    double rdExposed = (totRADDOSEdose * sampleMass)/exposedMass;
    raddoseStyleDose = rdExposed;
    //get diffraction efficiency
    double fractionElastic = getFractionElasticallyScattered(coefCalc);
   // double numberElastic = numberOfPhotons * fractionElastic;
    double numberElastic = totElastic;
    double numberInelastic = numberOfPhotons * getFractionComptonScattered(coefCalc);
    
    double elasticMolecule = numberOfPhotons * getFractionElasticallyScatteredMacro(coefCalc);
    double elasticSolvent = numberElastic - elasticMolecule;
    
    double diffractionEfficiency = numberElastic / voxDoseExposed;
    double DEFull = numberElastic / sumDoseNoCutOff;
    
    
    
    //print
    System.out.println(" ");
    System.out.println(String.format("RADDOSE-3D style average dose whole crystal: %.3f", totRADDOSEdose)); 
    System.out.println(String.format("RADDOSE-3D style average dose exposed region: %.3f", rdExposed)); 
    
    //this should just be if XFEL
    if (doXFEL == true) {
      System.out.println(String.format("RADDOSE-XFEL average dose whole crystal (ADWC): %.3f", voxDosevResolved)); 
      System.out.println(String.format("RADDOSE-XFEL average dose exposed region (ADER): %.3f", voxDoseExposed)); 
    }
    else {
      //if it isn't XFEL but still GOS then the dose with no cutoff should be shown 
      if (simpleMC == false) {
        System.out.println(String.format("RADDOSE-GOS average dose whole crystal (ADWC): %.3f", voxDoseNoCutoffvResolved)); 
        System.out.println(String.format("RADDOSE-GOS average dose exposed region (ADER): %.3f", voxDoseExposedNoCutoff)); 
      }
      else {
        System.out.println(String.format("RADDOSE-MC average dose whole crystal (ADWC): %.3f", sumMCDose)); 
        System.out.println(String.format("RADDOSE-MC average dose exposed region (ADER): %.3f", sumDoseNoCutOffExposed)); 
      }
      //unless just simpleMC in which case show diff dose
    }
   
    
    
 //   System.out.println(String.format("RADDOSE-XFEL diffraction weighted dose (DWD): %.3f", DWDcoarse)); 
    if (voxDoseExposed > 400) { //change to exposed
      System.out.println("Warning, damage may begin to be seen at these doses");
    }
    System.out.println(String.format("Diffraction Efficiency (number elastic/ADER): %.3f", diffractionEfficiency)); 
    System.out.println(" ");
    System.out.println(String.format("Average ionisations per atom exposed region: %.3f", totIonsperAtomExposed)); 
    System.out.println(String.format("Average ionisations per non-hydrogen atom exposed region: %.3f", ionisationsPerNonHExposed)); 
    System.out.println(" ");
    
    avgW = 1000*(avgW/avgWNum);
    avgUk = avgUk/avgUkNum;
    
  //  raddoseStyleDose = (raddoseStyleDose * sampleMass)/exposedMass;
    
    
    //write for RADDOSE-MC
    /*
    try {
      WriterFile("outputMC.CSV", sumDoseNoCutOff, DEFull, sumElectronDoseSurroundingNoCutOff);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    */
    double voxFrac = voxDosevResolved/gosTot;
    //write output to csv
    try {
      WriterFile("outputXFEL.CSV", totRADDOSEdose, rdExposed, voxDosevResolved, voxDoseExposed, diffractionEfficiency, ionisationsPerAtomvResolvedExposed, ionisationsPerNonHExposed, voxFrac, voxDoseNoCutoffvResolved, DWDcoarse, DWD);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    //write a csv for elements as well
    try {
      WriteElements("outputIonsPerAtom.CSV");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    //write stuff for simple MC
    if (simpleMC == true) {
      //write output to csv
      try {
        WriterFileMCsimple("outputMC.CSV", totRADDOSEdose, rdExposed, sumMCDose, sumDoseNoCutOffExposed, numberElastic);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
  }
  
  private void WriterFile(final String filename, final double totRADDOSEdose, final double rdExposed, final double voxDosevResolved, final double voxDoseExposed
                          ,final double diffractionEfficiency, final double ionisationsPerAtomvResolvedExposed, final double ionisationsPerNonHExposed, final double voxFrac,
                          final double vResolvedNoCut, final double DWDcoarse, final double DWD) throws IOException {

    BufferedWriter outFile;
    if (runNumber == 1) {
    outFile = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(filename), "UTF-8"));
    }
    else {
      outFile = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(filename, true), "UTF-8"));
    }
    try {
      if (runNumber == 1) {
        outFile.write("Run Number, RD3D-ADWC,RD3D-ADER,XFEL-ADWC,XFEL-ADER,Diffraction efficiency,ions per atom, ions per non-H atom, vResolvedNoCut, DWDcoarse, DWD\n");
      }
      outFile.write(String.format(
          " %d, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f%n", runNumber, totRADDOSEdose, rdExposed, voxDosevResolved,voxDoseExposed,diffractionEfficiency,ionisationsPerAtomvResolvedExposed, ionisationsPerNonHExposed, vResolvedNoCut, DWDcoarse, DWD));
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
  
  private void WriteElements(final String filename)throws IOException {
    BufferedWriter outFile;
    outFile = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(filename), "UTF-8"));
    try {
      outFile.write("Atomic number,ionisationsPerAtom\n");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("WriterFile: Could not write to file " + filename);
    }
    
    for (Element e: atomicIonisationsPerAtomExposed.keySet()) {
      try {
      outFile.write(String.format(
          " %d, %f%n", e.getAtomicNumber(), atomicIonisationsPerAtomExposed.get(e)));
      }catch (IOException e1) {
        e1.printStackTrace();
        System.err.println("WriterFile: Could not write to file " + filename);
      }
    }

    try {
      outFile.close();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("WriterFile: Could not close file " + filename);
    }
  }
  
  private void WriterFileMCsimple(final String filename, final double totRADDOSEdose, final double rdExposed, final double MCdose, final double MCdoseExposed,
      final double numberEl) throws IOException {
    BufferedWriter outFile;
    if (runNumber == 1) {
      outFile = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(filename), "UTF-8"));
    }
    else {
      outFile = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(filename, true), "UTF-8"));
    }
    try {
    if (runNumber == 1) {
    outFile.write("Run Number, RD3D-ADWC,RD3D-ADER,MC-ADWC,MC-ADER,numEl\n");
    }
    outFile.write(String.format(
    " %d, %f, %f, %f, %f, %f%n", runNumber, totRADDOSEdose, rdExposed, MCdose, MCdoseExposed, numberEl));
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
  
  private double getFractionElasticallyScattered(CoefCalc coefCalc) {
    double elasticCoef = coefCalc.getElasticCoefficient(); //per um
    double fractionElastic = 1-Math.exp(-elasticCoef * (ZDimension/1000));
    return fractionElastic;
  }
  private double getFractionElasticallyScatteredMacro(CoefCalc coefCalc) {
    double elasticCoef = coefCalc.getElasticCoefficientMacro(); //per um
    double fractionElastic = 1-Math.exp(-elasticCoef * (ZDimension/1000));
    return fractionElastic;
  }
  
  private double getFractionComptonScattered(CoefCalc coefCalc) {
    double comptonCoef = coefCalc.getInelasticCoefficient(); //per um
    double fractionElastic = 1-Math.exp(-comptonCoef * (ZDimension/1000));
    return fractionElastic;
  }
  
  private double getTimeToDistance(double electronEnergy, double s) {
    double csquared = Math.pow(c, 2);
    double Vo = electronEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double v = Math.pow(betaSquared*csquared, 0.5) * 1E9 / 1E15; //nm/fs
    double timeTos = (1/v) * s;
    return timeTos;
  }
  
  private Element getIonisedElement(Map<Element, Double> elementAbsorptionProbs) {
    double elementRND = Math.random();
    Element ionisedElement = null;
    for (Element e : elementAbsorptionProbs.keySet()) {
      double elementProb =  elementAbsorptionProbs.get(e);
      if (elementProb > elementRND) {
        ionisedElement = e;
        break;
      }
    }
    return ionisedElement;
  }
  
  private int getIonisedShell(Element ionisedElement, Map<Element, double[]> ionisationProbs) {
    double[] shellProbs = ionisationProbs.get(ionisedElement);
    double shellRND = Math.random();
    int shellIndex = 0;
    for (int j = 0; j < shellProbs.length; j++) {
      if (shellProbs[j] > shellRND) {
        shellIndex = j;
        break;
      }
    }
    return shellIndex;
  }
  
  private void producePhotoElectron(Beam beam, CoefCalc coefCalc, Map<Element, Double> elementAbsorptionProbs, Map<Element, double[]> ionisationProbs,
                                    double timeStamp, int doseTime, double xn, double yn, double zn, boolean surrounding, double photonEnergy,
                                    double angle, Wedge wedge) {
  //work out the element that has been absorbed with and hence the shell binding energy and photoelectron energy
    //element
    int[] pixelCoord = convertToPixelCoordinates(xn, yn, zn, angle, wedge);
    Element ionisedElement = getIonisedElement(elementAbsorptionProbs);
   // if (timeStamp <lastTime-(1*PULSE_BIN_LENGTH) && surrounding == false) {
    int ionisationTime = (int) (timeStamp/PULSE_BIN_LENGTH);
    if (ionisationTime < 0) {
      ionisationTime = 0;
    }
    if (surrounding == false) {
      totalIonisationEvents += 1;
      addIonisation(timeStamp, pixelCoord, ionisedElement, xn, yn, beam, 1);
    }
    //shell
    int shellIndex = getIonisedShell(ionisedElement, ionisationProbs);
    //get the shell binding energy
    double shellBindingEnergy = getShellBindingEnergy(ionisedElement, shellIndex);
    double photoelectronEnergy = photonEnergy - shellBindingEnergy;
    
    if (doseTime < 0) {
      doseTime = 0;
    }
    
    //Add the dose (shell binding energy) to the appropriate time
    if (surrounding == false) {
  //    dose += shellBindingEnergy;
  //    doseSimple[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += shellBindingEnergy;
      photonDose += shellBindingEnergy;
      raddoseStyleDose += photonEnergy;
      //add to voxel
  //    int[] pixelCoord = convertToPixelCoordinates(xn, yn, zn);
    //  voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += shellBindingEnergy;
    }
    //change this here to do the relaxation first and then 
    
    //send out the photoelectron in with the same timestamp of the photon - I think I should have this timestamp as a double
    
    //get direction and angles assuming 100% polarisation in the X axis
    //if there is rootation need to add this in as well
    double thisAngle = 2*Math.PI - angle;
    double polarised = Math.random();
    double xNorminit = 0, zNorminit = 0, xNorm = 0, yNorm = 0, zNorm = 0, phi = 0, theta = 0;
    if (shellIndex == 0 && polarised > 0.25) { //then I want to send out in a biased direction
      if (verticalGoni == true) {
        xNorminit = getCosAngleToX();
        //get yNorm and zNorm
        yNorm = PosOrNeg() * Math.random() * Math.pow(1-Math.pow(xNorminit, 2), 0.5);
      }
      else {
        yNorm = getCosAngleToX();
        //get yNorm and zNorm
        xNorminit = PosOrNeg() * Math.random() * Math.pow(1-Math.pow(yNorm, 2), 0.5);
      }
      zNorminit = PosOrNeg() * Math.pow(1 - Math.pow(xNorminit, 2) - Math.pow(yNorm, 2), 0.5);
      
      //now apply the rotation matrix
      xNorm = xNorminit * Math.cos(thisAngle) + zNorminit * Math.sin(thisAngle);
      zNorm = -1 * xNorminit * Math.sin(thisAngle) + zNorminit * Math.cos(thisAngle);

      theta = Math.acos(zNorm);
      phi = Math.acos(xNorm / Math.sin(theta));
      /*
      xNorm = getCosAngleToX();
      //get yNorm and zNorm
      yNorm = PosOrNeg() * Math.random() * Math.pow(1-Math.pow(xNorm, 2), 0.5);
      zNorm = PosOrNeg() * Math.pow(1 - Math.pow(xNorm, 2) - Math.pow(yNorm, 2), 0.5);
      //get theta and phi
      theta = Math.acos(zNorm);
      phi = Math.acos(xNorm / Math.sin(theta));
      */
    }
    else { // send it out in a random direction
      theta = Math.random() * 2 * Math.PI;
      phi = Math.random() * 2 * Math.PI;
      xNorm = Math.sin(theta) * Math.cos(phi);
      yNorm = Math.sin(theta) * Math.sin(phi);
      zNorm = Math.cos(theta);
    }
    
    trackPhotoelectron(coefCalc, timeStamp, photoelectronEnergy, xn, yn, zn, xNorm, yNorm, zNorm, theta, phi, surrounding, true, beam, angle, wedge);
    if (surrounding == false) {
      ionisationsOld += (int) ((photoelectronEnergy*1000) / 21.0);
    }
    /*
    if (ionisationsPerPhotoelectron > 0) {
      System.out.println(ionisationsPerPhotoelectron + " " + totalShellBindingEnergy);
    }
    */
    //relax the atom and see if an auger electron was produced
  //  if (simpleMC == false){
      if (surrounding == false) { //only want to track Auger if in the crystal for now
        produceAugerElectron(coefCalc, timeStamp, shellIndex, ionisedElement, xn, yn, zn, surrounding, beam, angle, wedge);
      }
  //  }
    
  }
  
  private void produceAugerElectron(CoefCalc coefCalc, double timeStamp, double shellIndex, Element ionisedElement,
                                    double xn, double yn, double zn, boolean surrounding, Beam beam, double angle, Wedge wedge) {
    //only do if from a K shell for now
    double shellBindingEnergy = getShellBindingEnergy(ionisedElement, (int)shellIndex);
    int doseTime = (int) (timeStamp/PULSE_BIN_LENGTH);
    int[] pixelCoord = convertToPixelCoordinates(xn, yn, zn, angle, wedge);
    int shell = (int) shellIndex;
    int Z = ionisedElement.getAtomicNumber();
    if (Z == 6 || Z == 7 || Z == 8 || Z == 11 || Z == 12 || Z == 14 || Z == 16 || Z == 17 || Z == 19 || Z == 20 || Z == 25 || Z == 26 || Z == 27 || Z == 28 || Z == 29 || Z == 30 || Z == 33 || Z == 34) {
    if (shellIndex == 0 || (shellIndex < 2 && Z==11) || (shellIndex < 3 && Z==12) || (shellIndex < 4 && Z<=20 && Z>12) || (shellIndex < 7 && Z>=25 && Z<31) || (shellIndex < 9 && Z>32 && Z < 35)) {
      //only do for elements that are possible right now - C N O S
        double shellFluorescenceYield = getShellFluorescenceYield(ionisedElement, shellIndex);
        double fluoresenceYieldRND = Math.random(); 

        if (fluoresenceYieldRND > shellFluorescenceYield) { //then this will emit an Auger electron 
          // determine which transition happened in the usual way from cumulative probs
          if (simpleMC == false) {
          double[] linewidths = augerTransitionLinewidths.get(Z).get(shell);
          double[] energies = augerTransitionEnergies.get(Z).get(shell);
          double[] exitIndexes = augerExitIndex.get(Z).get(shell);
          double[] dropIndexes = augerDropIndex.get(Z).get(shell);
          int transitionIndex = getTransitionIndex(Z, shell, true);
          //could actually get a proper energy from this... Also get a linewidth and therefore lifetime 
          double augerEnergy = energies[transitionIndex];
          //can add the energy difference to the dose here
          gosElectronDosevResolved += shellBindingEnergy - augerEnergy; 
          voxelEnergyvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += shellBindingEnergy - augerEnergy;
          
          double augerLinewidth = linewidths[transitionIndex];
          double augerLifetime = 1E15*((h/(2*Math.PI)) / ((augerLinewidth/1000)*Beam.KEVTOJOULES));
          timeStamp += augerLifetime;
          int exitIndex = (int) exitIndexes[transitionIndex];
          int dropIndex = (int) dropIndexes[transitionIndex];
          //add a charge 
          
          //account for this transition so cross sections can be adjusted
          
          //send out the Auger
          //choose a random direction
          double theta = Math.random() * 2 * Math.PI;
          double phi = Math.random() * 2 * Math.PI;
          double xNorm = Math.sin(theta) * Math.cos(phi);
          double yNorm = Math.sin(theta) * Math.sin(phi);
          double zNorm = Math.cos(theta);
          int ionisationTime = (int) (timeStamp/PULSE_BIN_LENGTH);
          
          
      //    if (timeStamp <lastTime-(1*PULSE_BIN_LENGTH) && surrounding == false) {
          if (surrounding == false) {
            addIonisation(timeStamp, pixelCoord, ionisedElement, xn, yn, beam, 1);
          }
          trackPhotoelectron(coefCalc, timeStamp, augerEnergy, xn, yn, zn, xNorm, yNorm, zNorm, theta, phi, surrounding, false, beam, angle, wedge);
          if (surrounding == false) {
            ionisationsOld += 1;
          }
          //produce another Auger from the leftover hole - only will happen if possible
          produceAugerElectron(coefCalc, timeStamp, exitIndex, ionisedElement, xn, yn, zn, surrounding, beam, angle, wedge);
          produceAugerElectron(coefCalc, timeStamp, dropIndex, ionisedElement, xn, yn, zn, surrounding, beam, angle, wedge);
          }
          else { //simpleMC == true
            dose += shellBindingEnergy;
            doseSimple[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += shellBindingEnergy;
          }
        }
        else {
          //do fluorescence
          //i.e send out the photons just as I did before 
          //shell binding energy is assuming they remain 
          double[] linewidths = flTransitionLinewidths.get(Z).get(shell);
          double[] energies = flTransitionEnergies.get(Z).get(shell);
          int transitionIndex = getTransitionIndex(Z, shell, false);
          double flEnergy = energies[transitionIndex];
          if (simpleMC == false) {
          double[] dropIndexes = flDropIndex.get(Z).get(shell);
          int dropIndex = (int) dropIndexes[transitionIndex];
          gosElectronDosevResolved += shellBindingEnergy;
          voxelEnergyvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += shellBindingEnergy;
          
          double flLinewidth = linewidths[transitionIndex];
          double flLifetime = 1E15*((h/(2*Math.PI)) / ((flLinewidth/1000)*Beam.KEVTOJOULES));
          timeStamp += flLifetime;
          produceAugerElectron(coefCalc, timeStamp, dropIndex, ionisedElement, xn, yn, zn, surrounding, beam, angle, wedge);
          }
          else {
            dose += shellBindingEnergy - flEnergy;
            doseSimple[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += shellBindingEnergy - flEnergy;
          }
        }
      }
    else {
      gosElectronDosevResolved += shellBindingEnergy;
      voxelEnergyvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += shellBindingEnergy;
      dose += shellBindingEnergy;
      doseSimple[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += shellBindingEnergy;
    }
    /*
      else if (Z == 26) {
        double augerEnergy = 6.5;
        double augerLifetime = 1E15*((h/(2*Math.PI)) / ((0.55/1000)*Beam.KEVTOJOULES));
        timeStamp += augerLifetime;
        double theta = Math.random() * 2 * Math.PI;
        double phi = Math.random() * 2 * Math.PI;
        double xNorm = Math.sin(theta) * Math.cos(phi);
        double yNorm = Math.sin(theta) * Math.sin(phi);
        double zNorm = Math.cos(theta);
        if (timeStamp <lastTime-(1*PULSE_BIN_LENGTH) & surrounding == false) {
          if (atomicIonisations.containsKey(ionisedElement)) {
            atomicIonisations.put(ionisedElement, atomicIonisations.get(ionisedElement)+1);
          }
          else {
            atomicIonisations.put(ionisedElement, (long)1);
          }
        }
        trackPhotoelectron(coefCalc, timeStamp, augerEnergy, xn, yn, zn, xNorm, yNorm, zNorm, theta, phi, surrounding, false);
        if (surrounding == false) {
          ionisationsOld += 1;
        }
      }
      */
    }
    else {
      gosElectronDosevResolved += shellBindingEnergy;
      voxelEnergyvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += shellBindingEnergy;
      dose += shellBindingEnergy;
      doseSimple[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += shellBindingEnergy;
    }
  }
  
  private int getTransitionIndex(int Z, int shell, boolean auger) {
    double[] transitionProbs = cumulativeTransitionProbabilities.get(Z).get(shell);
    int transitionIndex = 0;
    double transitionRND = Math.random();
    for (int i = 0; i < transitionProbs.length; i++) {
      if (transitionRND < transitionProbs[i]) { // then it's this transition
        transitionIndex = i;
        break;
      }
    }
    return transitionIndex;
  }
  
  
  private double[] getTranstionProbs(int Z, int shell, boolean auger) {
    if (auger == true) {
      return cumulativeTransitionProbabilities.get(Z).get(shell);
    }
    else {
      return flCumulativeTransitionProbabilities.get(Z).get(shell);
    }
  }
  
  private void addIonisation(double timeStamp, int[] pixelCoord, Element ionisedElement, double xn, double yn, Beam beam, int numIonisation) {
    totalIonisationEvents += numIonisation; 
      totalIonisationEventsvResolved += numIonisation;
      voxelIonisationsvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]]+= numIonisation;
      if (atomicIonisations.containsKey(ionisedElement)) {
        atomicIonisations.put(ionisedElement, atomicIonisations.get(ionisedElement)+numIonisation);
      }
      else {
        atomicIonisations.put(ionisedElement, (long)numIonisation);
      }
      if(testIfInsideExposedArea(xn, yn, beam)) {
        if (atomicIonisationsExposed.containsKey(ionisedElement)) {
          atomicIonisationsExposed.put(ionisedElement, atomicIonisationsExposed.get(ionisedElement)+numIonisation);
        }
        else {
          atomicIonisationsExposed.put(ionisedElement, (long)numIonisation);
        }
      }
    
  }
  
  
  private void produceCompton(Beam beam, CoefCalc coefCalc, double timeStamp, double xn, double yn, double zn, boolean surrounding, 
                              double photonEnergy, Map<Element, Double> elementComptonProbs, double angle, Wedge wedge) {
    //then the photon scattered by the compton effect
    //pick an angle theta
    int[] pixelCoord = convertToPixelCoordinates(xn, yn, zn, angle, wedge);
    double photonTheta = Math.PI * Math.random();
    //now get the energy of the compton electron
    double mcSquared = m * Math.pow(c, 2);
    double incidentEnergy = photonEnergy * Beam.KEVTOJOULES;
    double Ecomp = ((Math.pow(incidentEnergy, 2) * (1-Math.cos(photonTheta))) / 
                   (mcSquared * (1+((incidentEnergy/mcSquared)*(1-Math.cos(photonTheta))))))
                    /Beam.KEVTOJOULES; //in keV
    if (surrounding == false) {
      raddoseStyleDoseCompton += Ecomp;
    }
    //now get phi - this is the angle to Z
    double electronPhi = Math.atan((1/Math.tan(photonTheta/2)) / (1 + (incidentEnergy/mcSquared)));
    //now get the angles and direction
    double zNorm = Math.cos(electronPhi);
    double xNorm = PosOrNeg() * Math.random() * Math.pow(1-Math.pow(zNorm, 2), 0.5);
    double yNorm = PosOrNeg() * Math.pow(1 - Math.pow(xNorm, 2) - Math.pow(zNorm, 2), 0.5);
    double theta = Math.acos(zNorm);
    double phi = Math.acos(xNorm / Math.sin(theta));

    int ionisationTime = (int) (timeStamp/PULSE_BIN_LENGTH);
    if (ionisationTime < 0) {
      ionisationTime = 0;
    }
    if (surrounding == false) {
        Element ionisedElement = getIonisedElement(elementComptonProbs);
        addIonisation(timeStamp, pixelCoord, ionisedElement, xn, yn, beam, 1);
     
      
      //need to add a random element in here 
    }
    
    trackPhotoelectron(coefCalc, timeStamp, Ecomp, xn, yn, zn, xNorm, yNorm, zNorm, theta, phi, surrounding, true, beam, angle, wedge);
    if (surrounding == false) {
      ionisationsOld += (int) ((Ecomp*1000) / 21.0);
    }
    
    //i'm not doing the atomic ionisations here and really need to sort out what's going on. - at least need to do what element it's from even if not shell. 
    
  }

  private double getShellFluorescenceYield(Element ionisedElement, double shellIndex) {
    double flYield = 0;
    int intIndex = (int) shellIndex;
    switch (intIndex) {
      case 0: flYield = ionisedElement.getKShellFluorescenceYield();
              break;
      case 1: flYield = ionisedElement.getL1ShellFluorescenceYield();
              break;
      case 2: flYield = ionisedElement.getL2ShellFluorescenceYield();
              break;
      case 3: flYield = ionisedElement.getL3ShellFluorescenceYield();
              break;
      case 4: flYield = ionisedElement.getM1ShellFluorescenceYield();
              break;
      case 5: flYield = ionisedElement.getM2ShellFluorescenceYield();
              break;
      case 6: flYield = ionisedElement.getM3ShellFluorescenceYield();
              break;
      case 7: flYield = ionisedElement.getM4ShellFluorescenceYield();
              break;
      case 8: flYield = ionisedElement.getM5ShellFluorescenceYield();
              break;
    }
    return flYield;
  }
  
  private void trackPhotoelectron(CoefCalc coefCalc, double startingTimeStamp, double startingEnergy,
                                  double previousX, double previousY, double previousZ,
                                  double xNorm, double yNorm, double zNorm, double theta, double phi, boolean surrounding,
                                  boolean primaryElectron, Beam beam, double angle, Wedge wedge) {
    //do full Monte Carlo simulation the same way as in MicroED, but with a time stamp and adding dose every step
    //just do stopping power for now dw about surrounding and aUger and fluorescence and stuff
    
  //  double energyLossToUpdate = 0.2; //keV
    double energyLossToUpdate = 0.0; //keV
    double lossSinceLastUpdate = 0;
    
    
    int ionisationTime = (int) (startingTimeStamp/PULSE_BIN_LENGTH);
    if (ionisationTime < 0) {
      ionisationTime = 0;
    }
    if (surrounding == false) {
  //    totalIonisationEvents[ionisationTime] += 1;
      
      if (startingEnergy > 10) { //saying this is an actual PE
        ionisationsPerPhotoelectron = 0;
        totalShellBindingEnergy = 0;
      }
    }

    double energyLost = 0;
    double W = 0;
    double electronEnergy = startingEnergy;
    double timeStamp = startingTimeStamp;
    double startingStoppingPower = coefCalc.getStoppingPower(startingEnergy, surrounding);
    double stoppingPower = startingStoppingPower;
    
    double startingLambda_el = coefCalc.getElectronElasticMFPL(startingEnergy, surrounding);
    Map<ElementEM, Double> elasticProbs = coefCalc.getElasticProbs(surrounding);
    
    
    //the FSE stuff 
    double startingFSExSection = getFSEXSection(startingEnergy);
    double startingFSELambda = coefCalc.getFSELambda(startingFSExSection, surrounding); //should be surrounding not false
    
    
    //inelastic lamdaFromGOS to go in here
   // double gosInelasticLambda = coefCalc.getGOSInel(surrounding, electronEnergy);
   // Map<Element, double[]> gosIonisationProbs = coefCalc.getGOSShellProbs(surrounding, gosInelasticLambda);
    
    
    //Inner shell ionisation x section
    coefCalc.populateCrossSectionCoefficients();
    double startingInnerShellLambda = coefCalc.betheIonisationxSection(startingEnergy, surrounding);
    Map<Element, double[]> ionisationProbs = coefCalc.getAllShellProbs(surrounding); //Really need to make sure that these are in the same order
    
    double gosInelasticLambda = 0, gosInnerLambda = 0, gosOuterLambda = 0;
    Map<Element, double[]> gosIonisationProbs = null;
    Map<Element, Double> gosOuterIonisationProbs = null;
    if (simpleMC == false) {
    if (surrounding == false) {
      gosInelasticLambda = coefCalc.getGOSInel(surrounding, electronEnergy);
      gosInnerLambda = coefCalc.getGOSInnerLambda(surrounding);
      gosOuterLambda = coefCalc.getGOSOuterLambda(surrounding);
      gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(surrounding, gosOuterLambda); //note atm this does not work with plasma in this way
      gosIonisationProbs = coefCalc.getGOSShellProbs(surrounding, gosInelasticLambda);
      if (startingInnerShellLambda > 0) {
        gosInelasticLambda = 1/(1/gosOuterLambda + 1/startingInnerShellLambda);
      }
      else {
        gosInelasticLambda = 1/gosOuterLambda;
      }
     
    }
    
    //some sort of GOS ionisation probs
    
    double startingPlasmonLambda = coefCalc.getPlasmaMFPL(startingEnergy, surrounding);
    double plasmaEnergy = coefCalc.getPlasmaFrequency(surrounding)/1000.0; //in keV
    }
    double lambdaT = 0;
    double Pinner = 0;
 //   lambdaT = startingLambda_el;
 //   lambdaT = 1/ (1/startingLambda_el + 1/startingFSELambda);
    
    if (gosInelasticLambda > 0) {
      lambdaT = 1 / (1/startingLambda_el + 1/gosInelasticLambda);
    }
    else {
      lambdaT = startingLambda_el;
    }
    if (startingInnerShellLambda > 0) {
      Pinner = (gosInelasticLambda/startingInnerShellLambda);
    }
    
    double testRND = Math.random();
    double s = -lambdaT*Math.log(testRND);
    double Pinel = 1 - (lambdaT / startingLambda_el);
    
     
    
   // Pinel = 0; //quick way so it never activates the slow code
    
    double xn = previousX + s * xNorm;
    double yn = previousY + s * yNorm;
    double zn = previousZ + s * zNorm;
    
    double lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, surrounding);
    double FSELambda = 0, FSExSection = 0, innerShellLamda = 0;;
    
    
    boolean exited = false;
    boolean entered = false;
    
    
    double previousTheta = 0, previousPhi = 0;
    
    if (startingEnergy < 0.05) {
      int[] pixelCoord = convertToPixelCoordinates(previousX, previousY, previousZ, angle, wedge);
      exited = true;
      //I need to add to the dose here
      if (isMicrocrystalAt(previousX, previousY, previousZ, angle, wedge) == true) { 
          int doseTime = (int) (timeStamp/PULSE_BIN_LENGTH);
        if (primaryElectron == true) { //only doing dose from the primary
          dose += startingEnergy;
          electronDose += startingEnergy;
          gosElectronDose += startingEnergy;
          gosElectronDosevResolved += startingEnergy;
        //add to voxel
          doseSimple[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += startingEnergy;
      //    voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += startingEnergy;
          voxelEnergyvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]]+= startingEnergy;
          
        }
        //get avg energy and add on ionisation
        double avgEnergy = coefCalc.getAvgInelasticEnergy(startingEnergy);
        avgEnergy = coefCalc.getPlasmaEnergy(surrounding)/1000;
        if (avgEnergy > 0) {
          if (simpleMC == false) {
          int numIonisation = (int) (startingEnergy/avgEnergy);
          if (numIonisation > 0 && surrounding == false) {
            lowEnergyIonisations+= numIonisation;
            //decide what elements to add this to
            Element hitElem = chooseLowEnElement(coefCalc, Pinner, gosOuterIonisationProbs, ionisationProbs);
            addIonisation(timeStamp, pixelCoord, hitElem, xn, yn, beam, numIonisation);
          }
        }
        }
      }
    }
    while (exited == false) {
      if (isMicrocrystalAt(xn, yn, zn, angle, wedge) == true) { //photoelectron still in the crystal
        //here need to check if it has crossed a boundary to enter
        if (surrounding == true) {
          entered = true;
          surrounding = false;
          //need to check where it intersects
          double intersectionDistance = 1000*getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm, true); //nm 
          double[] intersectionPoint = getIntersectionPoint(intersectionDistance, previousX, previousY, previousZ, xNorm, yNorm, zNorm); 
          //set this to previous positions
          previousX = 1000*intersectionPoint[0];
          previousY = 1000*intersectionPoint[1];
          previousZ = 1000*intersectionPoint[2];
          //update timestamp
          timeStamp += getTimeToDistance(electronEnergy, intersectionDistance);
          //update energy to this point and coefficients
          if (simpleMC == true) {
            electronEnergy -= intersectionDistance * stoppingPower;
            //might need to sort it out if less than 0.05 here
            if (electronEnergy < 0.05) {
              break; 
            }
          }
          stoppingPower = coefCalc.getStoppingPower(electronEnergy, surrounding);
          double newFSExSection = getFSEXSection(startingEnergy);
          double newFSELambda = coefCalc.getFSELambda(startingFSExSection, false);
          if (simpleMC == false) {
          startingInnerShellLambda = coefCalc.betheIonisationxSection(startingEnergy, surrounding);
          ionisationProbs = coefCalc.getAllShellProbs(surrounding);
          gosInelasticLambda = coefCalc.getGOSInel(surrounding, electronEnergy);
          gosInnerLambda = coefCalc.getGOSInnerLambda(surrounding);
          gosOuterLambda = coefCalc.getGOSOuterLambda(surrounding);
          gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(surrounding, gosOuterLambda); //note atm this does not work with plasma in this way
          if (startingInnerShellLambda > 0) {
            gosInelasticLambda = 1/(1/gosOuterLambda + 1/startingInnerShellLambda);
          }
          else {
            gosInelasticLambda = 1/gosOuterLambda;
          }
          gosIonisationProbs = coefCalc.getGOSShellProbs(surrounding, gosInelasticLambda);
          if (startingInnerShellLambda > 0) {
            Pinner = (gosInelasticLambda/startingInnerShellLambda);
          }
          else {
            Pinner = 0;
          }
          }
        //  lambdaT = 1/(1/coefCalc.getElectronElasticMFPL(electronEnergy, surrounding) + 1/newFSELambda);
          startingLambda_el = coefCalc.getElectronElasticMFPL(electronEnergy, surrounding);
          if (gosInelasticLambda > 0) {
            lambdaT = 1 / (1/startingLambda_el + 1/gosInelasticLambda);
          }
          else {
            lambdaT = startingLambda_el;
          }
          elasticProbs = coefCalc.getElasticProbs(surrounding);

          Pinel = 1 - (lambdaT / startingLambda_el);

          //get a new s and xn, yn, zn
          s = -lambdaT*Math.log(Math.random());
          xn = previousX + s*xNorm;
          yn = previousY + s*yNorm;
          zn = previousZ + s*zNorm;
        }
      }
      //Will need a second if microcrystal is at = to true here so that I can check the surrounding one again
      if (isMicrocrystalAt(xn, yn, zn, angle, wedge) == true) {
        energyLost = s * stoppingPower;
        if (simpleMC == true) {
          lossSinceLastUpdate += energyLost;
        }
        //work out how long it took to travel this far 
        double timeToDistance = getTimeToDistance(electronEnergy, s);
        int doseTime = (int) ((timeStamp + (timeToDistance/2))/PULSE_BIN_LENGTH);
        timeStamp += timeToDistance;
        double energyToAdd = energyLost;
        if (doseTime < 0) {
          doseTime = 0;
        }
        /*
        if (energyToAdd < 0) {
          System.out.print("Test");
        }
        */
        if (primaryElectron == true) { //only doing dose from the primary
          int[] pixelCoord = convertToPixelCoordinates(xn, yn, zn, angle, wedge);
          dose += energyToAdd;  //still just adding keV
          
          //the 
          
          doseSimple[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]]+= energyToAdd;
          if (entered == true) {
            electronDoseSurrounding += energyToAdd;
          }
          else {
            electronDose += energyToAdd;
          }
          //add to voxel
        //  int[] pixelCoord = convertToPixelCoordinates(xn, yn, zn);
        //  voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += energyToAdd;
        }
        
        //update position and angle
        //update position and angle
        previousTheta = theta;
        previousPhi = phi;
        previousX = xn;
        previousY = yn;
        previousZ = zn;

        //here would be where I check if elastic or inelastic collision
        double RNDinelastic = Math.random();
        if (RNDinelastic  < Pinel) {
          
          //generate a charge if possible //this below is the FSE model
          /*
          //determine which element was hit
          double shellBindingEnergy = 0;
          Element collidedElement = null;
          int collidedShell = -1;
          double elementRND = Math.random();
          for (Element e : ionisationProbs.keySet()) {
            collidedShell = findIfElementIonised(e, ionisationProbs, elementRND);
            if (collidedShell >= 0) {
              collidedElement = e;
              break;
            }
          } 
          shellBindingEnergy = getShellBindingEnergy(collidedElement, collidedShell);
          double epsilon = getFSEEnergy(electronEnergy, shellBindingEnergy);
          double FSEEnergy = epsilon * electronEnergy - shellBindingEnergy;
          double sinSquaredAlpha = 0, sinSquaredGamma = 0;
          double FSEtheta = 0, FSEphi = 0, FSEpreviousTheta = 0, FSEpreviousPhi = 0, FSExNorm = 0, FSEyNorm = 0, FSEzNorm = 0;
          FSEpreviousTheta = previousTheta;
          FSEpreviousPhi = previousPhi;
          double minTrackEnergy = 0.05;  //this needs to be tested 
      //    double minTrackEnergy = 0.00;  //this needs to be tested 
          if (FSEEnergy > 0) {
            ionisationsPerPhotoelectron += 1;
            totalIonisationEvents[doseTime] += 1;
            totalShellBindingEnergy += shellBindingEnergy;
            if (FSEEnergy > minTrackEnergy) { // track a secondary electron if sufficient energy, this full cascade will be expensive but necessary for charge!!!!
              double tPrimary = (electronEnergy-FSEEnergy)/511; //t is in rest mass units. Need to change to stopping power en
              double tFSE = FSEEnergy/511;
              //alpha = angle of primary electron
              sinSquaredAlpha = (2 * epsilon) / (2 + tPrimary - tPrimary*epsilon);
              //gamma - angle of secondary electron
              sinSquaredGamma = 2*(1-epsilon) / (2 + tFSE*epsilon); 
            
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
              
              
              //recursive so slow!!!!!!!!!!!
              trackPhotoelectron(coefCalc, timeStamp, FSEEnergy, xn, yn, zn, FSExNorm, FSEyNorm, FSEzNorm, FSEtheta, FSEphi, surrounding);
              
           //   if (surrounding == false) {
           //     if (entered == true) {
           //       electronDoseSurrounding[doseTime] += FSEEnergy;
           //     }
           //     else {
           //       electronDose[doseTime] += FSEEnergy;
           //     }
           //   }
              
              
              
            
            }
            else { //0 to cutoff
            //  totalIonisationEvents[doseTime] += 1;
            }
            //produce an Auger electron as well - only if it was a K shell for now. 
            produceAugerElectron(coefCalc, timeStamp, collidedShell, collidedElement, xn, yn, zn, surrounding);
          }

          //update the angle deflection of the primary
          theta = Math.asin(Math.pow(sinSquaredAlpha, 0.5));
          theta = previousTheta + theta;
          if (theta >= (2 * Math.PI)) {
            theta -= 2*Math.PI;
          }
          
          */
         // theta = previousTheta;
          
          
          //here I am going to do the GOS model
          

          
          //determine which element and shell was hit
          int doseTimeGOS = (int) (timeStamp/PULSE_BIN_LENGTH);
          if (doseTimeGOS < 0) {
            doseTimeGOS = 0;
          }
          double shellBindingEnergy = 0;
          boolean plasmon = false;
          Element collidedElement = null;
          int collidedShell = -1;
          
          //I want to check if it's an inner shell ionisation and if it is use the proper inner shell cross sections
          //determine if the interaction was inner shell or outer shell
          double RNDInner = Math.random();
          double elementRND = Math.random();
          if (RNDInner < Pinner) {
            //then this hit an inner shell
            for (Element e : ionisationProbs.keySet()) {
              collidedShell = findIfElementIonised(e, ionisationProbs, elementRND);
              if (collidedShell >= 0) {
                collidedElement = e;
                break;
              }
            } 
          }
          else {
            //hit an outer shell
            for (Element e : gosOuterIonisationProbs.keySet()) {
              int[] electrons = coefCalc.getNumValenceElectronsSubshells(e);
              int numInnerShells = electrons[1];
              collidedShell = numInnerShells;
              if (findIfOuterShellIonised(e, gosOuterIonisationProbs, elementRND) == true){
                collidedElement = e;
                break;
              }
            } 
          }
          
          /*
          for (Element e : gosIonisationProbs.keySet()) {
            collidedShell = findIfElementIonised(e, gosIonisationProbs, elementRND); //will need to adjust this for if plasmon
            if (collidedShell >= 0) {
              collidedElement = e;
              break;
            }
          } 
          */
          
          if (collidedShell == -1) {
            //then this is a collision with the conduction band 
            plasmon = true;
          }
          else {
            //shellBindingEnergy = getShellBindingEnergyGOS(collidedElement, collidedShell);
            shellBindingEnergy = getShellBindingEnergy(collidedElement, collidedShell);
          }
          
          
          //get the type of collision
          int type = 0;
          if (plasmon == false) {
            type = getGOSInelasticType(coefCalc.getGOSVariable(surrounding).get(collidedElement), collidedShell);
          }
          else {
            type = getGOSInelasticTypePlasmon(coefCalc.getPlasmonVariable(surrounding));
          }
          
          //get energy loss
          double a = coefCalc.returnAdjustment();
          double Uk = 0;
         // double Uk = shellBindingEnergy*1000;
          double Wk = 0, Qak = 0, Q = 0;
          if (plasmon == false) {
            Uk = shellBindingEnergy*1000;
            Wk = coefCalc.getWkMolecule(a, collidedElement, collidedShell, surrounding);
            Qak = getQak(electronEnergy, Wk, Uk);
          }
          else {
            Uk = 0;
            Wk = coefCalc.getWcbAll(surrounding);
            Qak = Wk;
          }
          double Wak = WkToWak(electronEnergy, Wk, Uk); //eV
          double Wdis = 3*Wak - 2*Uk;
          if (type == 0 || type == 1) {
            //then this was a distant collision
             
            //get recoil energy
            if (plasmon == true) {
              W = Wk/1000;
            }
            else {
              W = getEnergyLossDistant(Wdis, Uk)/1000; //in keV  
            }
            Q = coefCalc.getRecoilEnergyDistant(electronEnergy, Wak, Qak); //J
            //get theta (new to add on to previous)
            if (type == 1) {
              //transverse
              theta = previousTheta;
            }
            else {
              //longitudinal
             theta = getGOSPrimaryThetaLong(electronEnergy, Q, Wak, previousTheta);
            }
          }
          else {
            //a close collision
            if (plasmon == true) {
              W = Wk/1000;
            }
            else {
         //     double k = samplek(electronEnergy, Qak);
              double k = samplek(electronEnergy, Uk);
              W = k*(electronEnergy+Uk/1000); //keV
            }
            theta = getGOSPrimaryThetaClose(electronEnergy, W, previousTheta);
          }
          //now I need to send out the secondary electron
          //get an angle and energy then recursively call ofc
          double minTrackEnergy = 0.05;
          double SEPreviousTheta = previousTheta;
          double SEPreviousPhi = previousPhi;
          double SEEnergy = W - Uk/1000;
          double SETheta = 0, SEPhi = 0, SExNorm = 0, SEyNorm = 0, SEzNorm = 0;
          int[] pixelCoord = convertToPixelCoordinates(xn, yn, zn, angle, wedge);
          if (SEEnergy > 0) {
      //      if (timeStamp <lastTime-(1*PULSE_BIN_LENGTH) & surrounding == false) {
            if (surrounding == false) {
              addIonisation(timeStamp, pixelCoord, collidedElement, xn, yn, beam, 1);
            }
            ionisationsPerPhotoelectron += 1;
            avgUk += Uk;
            avgUkNum += 1;
            if (plasmon == true) {
              avgUk += W*1000;
            }
          //  totalIonisationEvents[doseTime] += 1; //is this right??? need to sort this as well!!!
            //don't do this above if calling recursively as done at the top
            totalShellBindingEnergy += shellBindingEnergy;
            if (SEEnergy > minTrackEnergy) {
           //   gosElectronDose[doseTime] += Uk/1000;
              //get theta
              if (type == 0 || type == 1) { //distant
                SETheta = secondaryThetaDistant(electronEnergy, Wak, Q, previousTheta);
              }
              else { //close
                SETheta = secondaryThetaClose(electronEnergy, W, SEPreviousTheta);
              }
              //get phi
              SEPhi = 2 * Math.PI * Math.random();
              SEPhi = SEPreviousPhi + SEPhi;
              if (SEPhi >= (2 * Math.PI)) {
                SEPhi -= 2*Math.PI;
              }
              //now get normals
              SExNorm = Math.sin(SETheta) * Math.cos(SEPhi);
              SEyNorm = Math.sin(SETheta) * Math.sin(SEPhi);
              
              SEzNorm = Math.cos(SETheta);
              //send it out with the correct timestamp
              
              trackPhotoelectron(coefCalc, timeStamp, SEEnergy, xn, yn, zn, SExNorm, SEyNorm, SEzNorm, SETheta, SEPhi, surrounding, false, beam, angle, wedge);
              if (primaryElectron == true) { //only doing dose from the primary
                gosElectronDose += W;
                avgW += W;
                avgWNum += 1;
                //add to voxel
           //     voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTimeGOS] += W;
              }
              
            }
            else { //too low energy to track - work out what exactly I'm doing with dose! - need an SP way and a W way
              if (primaryElectron == true) { //only doing dose from the primary
                gosElectronDose += W;
                //add to voxel
         //       voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTimeGOS] += W;
              }
              gosElectronDosevResolved += SEEnergy;
              voxelEnergyvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]]+= SEEnergy;
           //   totalIonisationEvents[doseTimeGOS] += 1;
              //sort out how many extra ionisations this will cause
              double avgEnergy = coefCalc.getAvgInelasticEnergy(SEEnergy);
              avgEnergy = coefCalc.getPlasmaEnergy(surrounding)/1000;
              if (avgEnergy > 0 && surrounding == false) {
                int numIonisation = (int) (SEEnergy/avgEnergy);
                if (numIonisation > 0) {
                  lowEnergyIonisations += numIonisation;
                  //decide what elements to add this to
                  Element hitElem = chooseLowEnElement(coefCalc, Pinner, gosOuterIonisationProbs, ionisationProbs);
                  addIonisation(timeStamp, pixelCoord, hitElem, xn, yn, beam, numIonisation);
                }
              }
            }
          //  electronEnergy -= W; 
            lossSinceLastUpdate += W;
            //produce Auger electon should only be if it is from an inner shell of an element more than 2
            produceAugerElectron(coefCalc, timeStamp, collidedShell, collidedElement, xn, yn, zn, surrounding, beam, angle, wedge);
          }
            
          //I still need to add a cutoff in here and do dose time and ionisations
        }
        else {
          //do elastic
          theta = getElectronElasticTheta(electronEnergy, elasticProbs, previousTheta);
          
          
          
        //  theta = previousTheta;
          
        }

        //update angle and stuff - for now it is always an elastic interaction

        phi = getElectronElasticPhi(previousPhi);
        
   //     phi = previousPhi;
      //now further update the primary
        
        xNorm = Math.sin(theta) * Math.cos(phi);
        yNorm = Math.sin(theta) * Math.sin(phi);
        zNorm = Math.cos(theta);
        
        //update the energy and stopping Power and stuff
     //   electronEnergy -= energyLost; 
     //   lossSinceLastUpdate += energyLost;
        
        //GOS lambda
        if (electronEnergy > 0.5) {  //this will make it not quite behave right at edges :/
          //need to change the 0.5 to if it crosses an ionisation boundary  or just higher than highest binding energy shell
          //put in a get maxUk thing in coefCalc
          if (lossSinceLastUpdate > energyLossToUpdate) {
            electronEnergy -= lossSinceLastUpdate;
            if (simpleMC == false) {
            gosInelasticLambda = coefCalc.getGOSInel(false, electronEnergy);
            innerShellLamda = coefCalc.betheIonisationxSection(electronEnergy, false);
            gosOuterLambda = coefCalc.getGOSOuterLambda(surrounding);
            gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(surrounding, gosOuterLambda);
            gosIonisationProbs = coefCalc.getGOSShellProbs(false, gosInelasticLambda);
            if (startingInnerShellLambda > 0) {
              gosInelasticLambda = 1/(1/gosOuterLambda + 1/startingInnerShellLambda);
            }
            else {
              gosInelasticLambda = 1/gosOuterLambda;
            }
            }
            lossSinceLastUpdate = 0;
          }
        }
        else {
          electronEnergy -= lossSinceLastUpdate;
          if (simpleMC == false) {
          gosInelasticLambda = coefCalc.getGOSInel(false, electronEnergy);
          innerShellLamda = coefCalc.betheIonisationxSection(electronEnergy, false);
          gosOuterLambda = coefCalc.getGOSOuterLambda(surrounding);
          gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(surrounding, gosOuterLambda);
          gosIonisationProbs = coefCalc.getGOSShellProbs(false, gosInelasticLambda);
          if (startingInnerShellLambda > 0) {
            gosInelasticLambda = 1/(1/gosOuterLambda + 1/startingInnerShellLambda);
          }
          else {
            gosInelasticLambda = gosOuterLambda;
          }
          }
          lossSinceLastUpdate = 0;
        }
        
        stoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
        //get new lambdaT
        lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, false);
        FSExSection = getFSEXSection(electronEnergy);
        FSELambda = coefCalc.getFSELambda(FSExSection, false);
        

        
      //  lambdaT = 1/(1/lambdaEl + 1/FSELambda);
        if (gosInelasticLambda > 0) {
          lambdaT = 1/(1/lambdaEl + 1/gosInelasticLambda);
        }
        else {
          lambdaT = 1/(1/lambdaEl);
        }
        s = -lambdaT*Math.log(Math.random());
        elasticProbs = coefCalc.getElasticProbs(false);
        ionisationProbs = coefCalc.getAllShellProbs(false);
        //GOS ionisation probs
        Pinel = 1 - (lambdaT / lambdaEl);
        if (innerShellLamda > 0) {
          Pinner = gosInelasticLambda / innerShellLamda;
        }
        //update to new position
        xn = previousX + s * xNorm;
        yn = previousY + s * yNorm;
        zn = previousZ + s * zNorm;
      }
      else { //it's left the crystal if surrounding = false
        if (surrounding == false) {
          surrounding = true;  //will need to try and see what happens when it comes back in the crystal in both models
          
          double escapeDistance = 1000 * getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm, true); //nm
          
          
          //testing no re-entry
          //exited = true;
          
          
          if (coefCalc.isCryo() == false) {
            exited = true;
            //deposit the energy though
            if (simpleMC == true) { 
              energyLost = escapeDistance * stoppingPower;
              electronEnergy -= energyLost;
              dose += energyLost;
              
              if (isMicrocrystalAt(previousX, previousY, previousZ, angle, wedge) == true) {
                int[] pixelCoord = convertToPixelCoordinates(previousX, previousY, previousZ, angle, wedge);
                doseSimple[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += energyLost;
              }
            }
          }
          else {
            
          //i think for stopping power just take it to the edge as well and deposit in the middle
          //if energy drops below 0.05 keV than drop the rest in the same place
          
          //set new position just outside
            
            
            //this escape distance will need to change as I don't think it does rotation
            
            
          

          if (simpleMC == true) { 
            //update energy
            energyLost = escapeDistance * stoppingPower;
            electronEnergy -= energyLost;
            //work out how long it took to travel this far 
            double timeToDistance = getTimeToDistance(electronEnergy, s);
            int doseTime = (int) ((timeStamp + (timeToDistance/2))/PULSE_BIN_LENGTH);
            timeStamp += timeToDistance;
            double energyToAdd = energyLost;
            if (doseTime < 0) {
              doseTime = 0;
            }
            //add dose
            dose+= energyLost;
            
            if (isMicrocrystalAt(previousX, previousY, previousZ, angle, wedge) == true) {
              int[] pixelCoord = convertToPixelCoordinates(previousX, previousY, previousZ, angle, wedge);
              doseSimple[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += energyLost;
            }
            else {

            }
            
            //if drops below 0.05 keV deposit it all
            if (electronEnergy < 0.05) {
              dose += electronEnergy;
              // doseSimple[0][0][0][doseTime] += energyLost;
              electronEnergy = 0;
              exited = true;
            }
          }
          
          previousX = previousX + (escapeDistance + (escapeDistance/1000)) * xNorm;
          previousY = previousY + (escapeDistance + (escapeDistance/1000)) * yNorm;
          previousZ = previousZ + (escapeDistance + (escapeDistance/1000)) * zNorm;

          //update cross section and probs and stuff for the surrounding
          if (electronEnergy > 0.05) {
          if (simpleMC == false) {
            gosInelasticLambda = coefCalc.getGOSInel(surrounding, electronEnergy);
            innerShellLamda = coefCalc.betheIonisationxSection(electronEnergy, surrounding);
            gosOuterLambda = coefCalc.getGOSOuterLambda(surrounding);
            gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(surrounding, gosOuterLambda);
            gosIonisationProbs = coefCalc.getGOSShellProbs(surrounding, gosInelasticLambda);
            if (startingInnerShellLambda > 0) {
              gosInelasticLambda = 1/(1/gosOuterLambda + 1/startingInnerShellLambda);
            }
            else {
              gosInelasticLambda = 1/gosOuterLambda;
            }
          }
          
          stoppingPower = coefCalc.getStoppingPower(electronEnergy, surrounding);
          //get new lambdaT
          lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, surrounding);
          
          if (gosInelasticLambda > 0) {
            lambdaT = 1/(1/lambdaEl + 1/gosInelasticLambda);
          }
          else {
            lambdaT = 1/(1/lambdaEl);
          }
          s = -lambdaT*Math.log(Math.random());
          
          elasticProbs = coefCalc.getElasticProbs(surrounding);
          ionisationProbs = coefCalc.getAllShellProbs(false);
          //GOS ionisation probs
          Pinel = 1 - (lambdaT / lambdaEl);
          if (innerShellLamda > 0) {
            Pinner = gosInelasticLambda / innerShellLamda;
          }
          
          //update to new position
          xn = previousX + s * xNorm;
          yn = previousY + s * yNorm;
          zn = previousZ + s * zNorm;
          
          }
          }

          /*
          if (simpleMC == true) {
          //get the energy deposited before it left the crystal. - when I slice need to also do timestamps 
          double escapeDist = 1000 * getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm); //nm
          double FSEStoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
          double energyToEdge = FSEStoppingPower * escapeDist;
          if (energyToEdge < electronEnergy){ //the FSE has escaped
            double energyLostStep = 0, totFSEenLostLastStep = 0;
            double newEnergy = electronEnergy;
            for (int j = 0; j < 10; j++) { //I will need to play around with the amount of slicing when I am writing up
              energyLostStep = (escapeDist/10) * FSEStoppingPower;
              //add dose to timeStamp
              double timeToDistance = getTimeToDistance(newEnergy, escapeDist/10);
              int doseTime = (int) ((timeStamp + (timeToDistance/2))/PULSE_BIN_LENGTH); // over 2 as adding it half way
              timeStamp += timeToDistance;
              if (doseTime < 0) {
                doseTime = 0;
              }
              if (energyLostStep < 0) {
                System.out.print("Test");
              }
              dose[doseTime] += energyLostStep;  //still just adding keV
              if (entered == false) {
              electronDose[doseTime] += energyLostStep;
              
              }
              else {
                electronDoseSurrounding[doseTime] += energyLostStep;
              }
              newEnergy -= energyLostStep;
              FSEStoppingPower = coefCalc.getStoppingPower(newEnergy, false);
              if (newEnergy < 0.05) {
                exited = true;
                if (newEnergy > 0) {
                  dose[doseTime] += newEnergy;
                  if (entered == false) {
                    electronDose[doseTime] += newEnergy;
                    
                    }
                    else {
                      electronDoseSurrounding[doseTime] += newEnergy;
                    }
                }
                break;
              }
            } 
            //calc escaped energy
            if (entered == false) {
              escapedEnergy += newEnergy;
            }
            
          }
          else {
            //didn't quite escape, add the electron energy to the dose
            double timeToDistance = getTimeToDistance(electronEnergy, s);
            int doseTime = (int) ((timeStamp + (timeToDistance/2))/PULSE_BIN_LENGTH);
            timeStamp += timeToDistance;
            if (doseTime < 0) {
              doseTime = 0;
            }
            if (electronEnergy < 0) {
              System.out.print("Test");
            }
            dose[doseTime] += electronEnergy;  //still just adding keV
            if (entered == false) {
            electronDose[doseTime] += electronEnergy;
            
            }
            else {
              electronDoseSurrounding[doseTime] += electronEnergy;
            }
          }
          }
          else { //full GOS sim
            // pretty much same as before 
            //I want to pull the electron back to the point where it exited. Track it with same direction vector
            //But the envitonemnt is now the surrounding
          }
          */
        }
        else { //it's one I'm tracking from the surrounding
          //check if it's still worth tracking it form the surrounding anymore - i.e still in track range
          double maxDistanceNM = (electronEnergy/coefCalc.getStoppingPower(electronEnergy, surrounding)); 
          
          //this bit here will also need to change for rotation
          
       //   if (Math.abs(xn) > maxDistanceNM + XDimension/2 || Math.abs(yn) > maxDistanceNM + YDimension/2 || Math.abs(zn) > maxDistanceNM + ZDimension/2) {
          if (calculateCrystalOccupancySurrounding(xn, yn, zn) == false){
            exited = true;
          }
          else {
             //if it is still worth tracking I need to do everything exactly the same as if it was in the crystal...
            //find energy lost
            energyLost = s * stoppingPower;
            //update timeStamp
            double timeToDistance = getTimeToDistance(electronEnergy, s);
            timeStamp += timeToDistance;
          //update position and angle
            //update position and angle
            previousTheta = theta;
            previousPhi = phi;
            previousX = xn;
            previousY = yn;
            previousZ = zn;
            
            //update angle and stuff - for now it is always an elastic interaction
            theta = getElectronElasticTheta(electronEnergy, elasticProbs, previousTheta);
            phi = getElectronElasticPhi(previousPhi);
          //now further update the primary
            
            xNorm = Math.sin(theta) * Math.cos(phi);
            yNorm = Math.sin(theta) * Math.sin(phi);
            zNorm = Math.cos(theta);
            
            //update the energy and stopping Power and stuff
            electronEnergy -= energyLost; 
            stoppingPower = coefCalc.getStoppingPower(electronEnergy, surrounding);
            //get new lambdaT
            lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, surrounding);
            
            lambdaT = lambdaEl;
            s = -lambdaT*Math.log(Math.random());
            elasticProbs = coefCalc.getElasticProbs(surrounding);
            
            //update to new position
            xn = previousX + s * xNorm;
            yn = previousY + s * yNorm;
            zn = previousZ + s * zNorm;
          }
        }
      }
      if (electronEnergy < 0.05) {
        exited = true;
        //add the dose if died in the crystal
        if (isMicrocrystalAt(previousX, previousY, previousZ, angle, wedge) == true) {
          int doseTime = (int) (timeStamp/PULSE_BIN_LENGTH);
          if (doseTime < 0) {
            doseTime = 0;
          }
          if (primaryElectron == true) { //only doing dose from the primary
            dose += electronEnergy;
            int[] pixelCoord = convertToPixelCoordinates(previousX, previousY, previousZ, angle, wedge);
            doseSimple[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += electronEnergy;
            gosElectronDose += electronEnergy;
            if (entered == false) {
              electronDose += electronEnergy;
            }
            else {
              electronDoseSurrounding += electronEnergy;
            }
            //add to voxel
            
        //    voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += electronEnergy;
          }
          if (simpleMC == false) {
          gosElectronDosevResolved += electronEnergy;
          int[] pixelCoord = convertToPixelCoordinates(previousX, previousY, previousZ, angle, wedge);
          voxelEnergyvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]] += electronEnergy;
          double avgEnergy = coefCalc.getAvgInelasticEnergy(electronEnergy); //can make this plasmon energy
          avgEnergy = coefCalc.getPlasmaEnergy(surrounding)/1000;
          if (avgEnergy > 0) {
            int numIonisation = (int) (electronEnergy/avgEnergy);
            if (numIonisation > 0) {
              lowEnergyIonisations += numIonisation;
              //decide what elements to add this to
              Element hitElem = chooseLowEnElement(coefCalc, Pinner, gosOuterIonisationProbs, ionisationProbs);
              addIonisation(timeStamp, pixelCoord, hitElem, xn, yn, beam, numIonisation);
              
            } 
          }
        }
        }
      }
    }
    
  }
  
  private double getElectronElasticTheta(double electronEnergy, Map<ElementEM, Double> elasticProbs, double previousTheta) {
    double elasticElementRND = Math.random();
    ElementEM elasticElement = null;
    for (ElementEM e : elasticProbs.keySet()) {
      if (elasticProbs.get(e) > elasticElementRND) { //Then this element is the one that was ionised
        elasticElement = e;
        break;
      }
    }
    //get the angles
    
    if (elasticElement == null) {
      double test = 0;
      test += 1;
    }
    //ELSEPA stuff
    double theta = getPrimaryElasticScatteringAngle(electronEnergy, elasticElement.getAtomicNumber());
    theta = previousTheta + theta;
    if (theta >= (2 * Math.PI)) {
      theta -= 2*Math.PI;
    }
    return theta;
  }
  
  private double getElectronElasticPhi(double previousPhi) {
    double phi = 2 * Math.PI * Math.random();
    phi = previousPhi + phi;
    if (phi >= (2 * Math.PI)) {
      phi -= 2*Math.PI;
    }
    return phi;
  }
  
  private double getFSEXSection(double electronEnergy) {
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double m = 9.10938356E-28; // in g
    double c = 29979245800.0;  //in cm

    double csquared = Math.pow(c/100, 2);
    double Vo = electronEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow((m/1000)*csquared/(Vo + (m/1000)*csquared), 2);
    double vsquared = (betaSquared * csquared)*10000;

    double constant = (2* Math.PI * Math.pow(elementaryCharge, 4)) / (m*vsquared * (Vo*1000*10000));

    //numerical integral of this
    double energyCutOff;
    energyCutOff = (14.0/1000.0)/electronEnergy; //corresponds to a 14eV cut off, the hydrogen K shell energy
    
    double restMassEnergy = 511; //keV
    double tau = electronEnergy/restMassEnergy;
    double crossSection = (((2*tau+1)/Math.pow(tau+1, 2))*(Math.log((1/0.5)-1)) + Math.pow(tau/(tau+1), 2) - (1/0.5) - (1/(0.5-1))) -
                          (((2*tau+1)/Math.pow(tau+1, 2))*(Math.log((1/energyCutOff)-1)) + Math.pow(tau/(tau+1), 2) - (1/energyCutOff) - (1/(energyCutOff-1))); 
                          
    crossSection*= constant;

    return crossSection; 
  }
  
  private int findIfElementIonised(Element e, Map<Element, double[]> ionisationProbs, double elementRND) {
    double[] elementShellProbs = ionisationProbs.get(e);
    int shell = -1;
    for (int k = 0; k < elementShellProbs.length; k++) {
      if (elementShellProbs[k] > elementRND) { //Then this element is the one that was ionised
        shell = k;
        break;
      }
    }
    return shell;
  }
  
  private boolean findIfOuterShellIonised(Element e, Map<Element, Double> ionisationProbs, double elementRND) {
    boolean hit = false;
    double elementShellProbs = ionisationProbs.get(e);
      if (elementShellProbs > elementRND) { //Then this element is the one that was ionised
        hit = true;
      }
    return hit;
  }
  
  private double getFSEEnergy(double electronEnergy, double shellBindingEnergy) {
    double RNDFSEEnergy = Math.random();
    double energyCutOff = (14.0/1000.0)/electronEnergy;
    
    double tau = electronEnergy/511;
    double alphaParam = Math.pow(tau/(tau+1), 2);
    double betaParam = (2*tau + 1)/Math.pow(tau+1, 2);
    double gammaParam = (1/energyCutOff)-(1/(1-energyCutOff))-(alphaParam*energyCutOff)-(betaParam*Math.log((1-energyCutOff)/((electronEnergy*energyCutOff)/511)));
    double omegaParam = RNDFSEEnergy*(gammaParam + (alphaParam/2)) - gammaParam;
    double epsilon = (omegaParam-2-betaParam+Math.pow(Math.pow(omegaParam-2-betaParam, 2) + 4*(omegaParam+alphaParam-2*betaParam), 0.5)) /
                      (2*(omegaParam+alphaParam-2*betaParam));
    
    double omega = 1 / ((1/energyCutOff) - ((1/energyCutOff)-2)*RNDFSEEnergy);
//      double omega = 1 / (100 - 98*Math.random());
    return epsilon;
  }
  

  private double getCosAngleToX() {
    double RNDangle = Math.random();
    double lastProb = 0;
    double angle = 0;
    for (int i = 0; i < numberAngularEmissionBins; i++) {
      if (RNDangle < angularEmissionProbs[i]) { //then it's in this angle range
        //interpolate angle
        double angleStart = i * Math.PI/numberAngularEmissionBins;
        double angleEnd = (i+1) * (Math.PI/numberAngularEmissionBins);
        double proportionAlong = (RNDangle - lastProb) / (angularEmissionProbs[i] - lastProb);
        angle = angleStart + (proportionAlong * (angleEnd - angleStart));
        break;
      }
      lastProb = angularEmissionProbs[i];
    }
    return Math.cos(angle);
  }
  private double PosOrNeg() {
    double RND = Math.random();
    if (RND < 0.5) {
      return 1;
    }
    else {
      return -1;
    }
  }

  
public double getExposedArea(Beam beam) { // returns area in nm^2
  double area = 0.0;
  
  if(beam.getType() == "Tophat") {
    
    // Get collimation in um
    double xCollimation = beam.getBeamX();
    double yCollimation = beam.getBeamY();
    
    if(beam.getIsCircular() == false) { // rectangular collimation
      area = (xCollimation*1000)*(yCollimation*1000);
    }
    
    else if(beam.getIsCircular() == true) { // elliptical collimation
      area = Math.PI*(xCollimation*1000)*(yCollimation*1000);
      }
  }
  
  else if(beam.getType() == "Gaussian") {
    
    // Get collimation in um, or else set it to defaults
    Double xCollimation = beam.getBeamX();
    Double yCollimation = beam.getBeamY();
    final double SIGMA_TO_FWHM = 2 * Math.sqrt(2 * Math.log(2));
    if(xCollimation == null) {xCollimation = beam.getSx()*SIGMA_TO_FWHM*3;} // default collimation at three FWHM; TODO check if it would be better to move this bit into the beam classes
    if(yCollimation == null) {yCollimation = beam.getSy()*SIGMA_TO_FWHM*3;}

    if(beam.getIsCircular() == false) {
      area = (xCollimation*1000)*(yCollimation*1000);
    }
    
    else if(beam.getIsCircular() == true) {
      area = Math.PI*(xCollimation*1000)*(yCollimation*1000);
    }
  }
  
  else {System.out.println("Beam type not supported");}
  
  return area;
}
  
private boolean testIfInsideExposedArea(double xPos, double yPos, Beam beam) { // take positions in nm; returns true if inside exposed area
  
  boolean result = false;
  
  if(beam.getType() == "Tophat") {
    
    // Get collimation in um
    double xCollimation = beam.getBeamX();
    double yCollimation = beam.getBeamY();
    
    if(beam.getIsCircular() == false) { // rectangular collimation
    
      if ((Math.abs(xPos/1000) > (xCollimation/2))) {result = false;} // no need to subtract wedge.getOffAxisUm() as XFEL assumes it hits centre
      else if ((Math.abs(yPos/1000) > (yCollimation/2))) {result = false;}
      else {result = true;};
    }
    
    else if(beam.getIsCircular() == true) { // elliptical collimation
      if (((Math.pow((xPos/1000), 2)/Math.pow(xCollimation/2, 2)) + (Math.pow((yPos/1000), 2)/Math.pow(yCollimation/2, 2))) > 1) {result = false;} 
      else {result = true;}
      }
  
  }
  
  else if(beam.getType() == "Gaussian") {
    
    // Get collimation in um, or else set it to defaults
    Double xCollimation = beam.getBeamX();
    Double yCollimation = beam.getBeamY();
    final double SIGMA_TO_FWHM = 2 * Math.sqrt(2 * Math.log(2));
    if(xCollimation == null) {xCollimation = beam.getSx()*SIGMA_TO_FWHM*3;} // default collimation at three FWHM; TODO check if it would be better to move this bit into the beam classes
    if(yCollimation == null) {yCollimation = beam.getSy()*SIGMA_TO_FWHM*3;}

    if(beam.getIsCircular() == false) {
      if ((Math.abs(xPos/1000) > (xCollimation/2))) {result = false;} // no need to subtract wedge.getOffAxisUm() as XFEL assumes it hits centre
      else if ((Math.abs(yPos/1000) > (yCollimation/2))) {result = false;}
      else {result = true;};
    }
    
    else if(beam.getIsCircular() == true) {
      if (((Math.pow((xPos/1000), 2)/Math.pow(xCollimation/2, 2)) + (Math.pow((yPos/1000), 2)/Math.pow(yCollimation/2, 2))) > 1) {result = false;} 
      else {result = true;}
    }
  }
  else {System.out.println("Beam type not supported");}
  
 return result; 
  
}




/**
 * This method places a photon randomly in the beam area.
 * Works for Tophat and Gaussian (although case where FWHMs are not equal may take longer to run, especially if collimation is narrow), for both rectangular and elliptical/circular collimation. 
 * @param beam
 * @param wedge
 * @return the initial xy position of the photon
 */
  private double[] getPhotonBeamXYPos(Beam beam, Wedge wedge) { //TODO need to change some more here; getPhotonBeamXYPos method
    double[] xyPos = new double[2];
    
    if(beam.getType() == "Tophat") {
    
      double RND1 = Math.random();
      double RND2 = Math.random();
      
      double xCollimation = beam.getBeamX(); // fetch x collimation in um
      xyPos[0] = 1000*xCollimation*(RND1 - 0.5); // x position in nm
    
      double yCollimation = beam.getBeamY();
      if (beam.getIsCircular()) {   //reduce Y limits so you can't put it out of the circle / ellipse
        xyPos[1] = 1000*RND2*Math.sqrt(Math.pow(yCollimation/2, 2)*(Math.pow((xyPos[0]/1000), 2)/Math.pow(xCollimation/2, 2)));
        xyPos[1] *= PosOrNeg();
      }
      else {
        xyPos[1] = 1000*((RND2 * yCollimation) - (yCollimation/2));
      }
    }
    
    else if(beam.getType() == "Gaussian") {
      
        // Get collimation in um, or else set it to defaults
        Double xCollimation = beam.getBeamX();
        Double yCollimation = beam.getBeamY();
        final double SIGMA_TO_FWHM = 2 * Math.sqrt(2 * Math.log(2));
        if(xCollimation == null) {xCollimation = beam.getSx()*SIGMA_TO_FWHM*3;} // default collimation at three FWHM; TODO check if it would be better to move this bit into the beam classes
        if(yCollimation == null) {yCollimation = beam.getSy()*SIGMA_TO_FWHM*3;}
      
        if(beam.getIsCircular() == false) { // square 2D Gaussian
          
      //    double sx = beam.getSx()*1000; // beam.getSx() is in um, we want the positions in nanometers
      //    double sy = beam.getSy()*1000;
          double sx = beam.getSx(); // beam.getSx() is in um - same as collimation
          double sy = beam.getSy();
          NormalDistribution gx = new NormalDistribution(0, sx); // have assumed no offset so do not need to use wedge.getOffAxisUm(); units are nm
          NormalDistribution gy = new NormalDistribution(0, sy);
          
          // Find cumulative probabilities at collimation limits
          double lowerCumulativeProbX = gx.cumulativeProbability(-xCollimation/2);
          double upperCumulativeProbX = gx.cumulativeProbability(xCollimation/2);
          double lowerCumulativeProbY = gy.cumulativeProbability(-yCollimation/2);
          double upperCumulativeProbY = gy.cumulativeProbability(yCollimation/2);
          
          // Randomly select cumulative probabilities between those limits
          double RCPx = lowerCumulativeProbX + Math.random()*(upperCumulativeProbX - lowerCumulativeProbX); // gets a random cumulative probability
          double RCPy = lowerCumulativeProbY + Math.random()*(upperCumulativeProbY - lowerCumulativeProbY);
          
          // Generate the coordinates, units nm
          xyPos[0] = gx.inverseCumulativeProbability(RCPx)*1000;
          xyPos[1] = gy.inverseCumulativeProbability(RCPy)*1000;
        }
        
        else if(beam.getIsCircular() == true) { // elliptical 2D Gaussian
          
          double RND1 = Math.random();
          double RND2 = Math.random();  
 
// First test if can use circular Gaussian because it is quicker to run (do not have to keep placing until you get a photon within the exposed area, as always places in exposed area)
          if(beam.getSx() == beam.getSy()) {
            double[] rtPos = new double[2]; //Polar coordinates
         //   double sr = beam.getSx()*1000; // noting sx == sy
            double sr = beam.getSx(); // noting sx == sy
            NormalDistribution gr = new NormalDistribution(0, sr); //units of nm
          
            double lowerCumulativeProbR = gr.cumulativeProbability(-xCollimation/2);
            double upperCumulativeProbR = gr.cumulativeProbability(xCollimation/2);
            double RCPr = lowerCumulativeProbR + Math.random()*(upperCumulativeProbR - lowerCumulativeProbR); // gets a random cumulative probability
          
            rtPos[0] = gr.inverseCumulativeProbability(RCPr)*1000; // value of r
            rtPos[1] = 2*(Math.PI)*RND2; // angle anticlockwise from x-axis, using convention 0 <= theta < 2*pi, in radians
          
            xyPos[0] = rtPos[0]*Math.cos(rtPos[1]);
            xyPos[1] = rtPos[0]*Math.sin(rtPos[1]);
          }

// Else use full equation for bivariate Gaussian in Cartesian coordinates (slow to run if FWHMs >> collimations)
          else if(beam.getSx() != beam.getSy()) {
            double[] means = {0.0, 0.0};
            double[][] covarianceMatrix = new double[2][2];
          
       //     double sx = beam.getSx()*1000;
       //     double sy = beam.getSy()*1000;
            double sx = beam.getSx();
            double sy = beam.getSy();
            double rho = 0; // we want rho == 0; note that when sx == sy this case simplifies to the above 
            covarianceMatrix[0][0] = Math.pow(sx, 2);
            covarianceMatrix[1][1] = Math.pow(sy, 2);
            covarianceMatrix[0][1] = rho*sx*sy;
            covarianceMatrix[1][0] = rho*sx*sy;
          
            MultivariateNormalDistribution gr = new MultivariateNormalDistribution(means, covarianceMatrix);
          
            do {
              xyPos = gr.sample();
              xyPos[0] *= 1000;
              xyPos[1] *= 1000;
            } while(((Math.pow((xyPos[0]/1000), 2)/Math.pow(xCollimation/2, 2)) + (Math.pow((xyPos[1]/1000), 2)/Math.pow(yCollimation/2, 2))) > 1);
          }
        }
    }
    else {System.out.println("Beam type not supported");}
    
    //System.out.println(Arrays.toString(xyPos));
    return xyPos;
  }
  
  
  private Map<Element, double[]> getRelativeShellProbs(Map<Element, Double> elementAbsorptionProbs, double beamEnergy){
    Map<Element, double[]> ionisationProbs = new HashMap<Element, double[]>();
    for (Element e : elementAbsorptionProbs.keySet()) {
      e.EdgeRatio();
      double runningSumProb = 0;
      double kshellProb = 0, L1shellProb = 0, L2shellProb = 0, L3shellProb = 0, M1shellProb = 0, M2shellProb = 0, M3shellProb = 0, M4shellProb = 0, M5shellProb = 0;
      double[] shellProbs = new double[9];
  //    double shellProb = 0;
      if (beamEnergy > e.getKEdge() ) {
        kshellProb = e.getKShellIonisationProb();
        runningSumProb += kshellProb;
        shellProbs[0] = runningSumProb;
      }
      if (beamEnergy > e.getL1Edge() && e.getAtomicNumber() >= 12) {
        L1shellProb = e.getL1ShellIonisationProb() * (1-kshellProb);
        runningSumProb += L1shellProb;
        shellProbs[1] = runningSumProb;
      }
      if (beamEnergy > e.getL2Edge() && e.getAtomicNumber() >= 12) {
        L2shellProb = e.getL2ShellIonisationProb() * (1-kshellProb-L1shellProb);
        runningSumProb += L2shellProb;
        shellProbs[2] = runningSumProb;
      }
      if (beamEnergy > e.getL3Edge() && e.getAtomicNumber() >= 12) {
        L3shellProb = e.getL3ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb);
        runningSumProb += L3shellProb;
        shellProbs[3] = runningSumProb;
      }
      if (beamEnergy > e.getM1Edge() && e.getAtomicNumber() >= 73) { 
        M1shellProb = e.getM1ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb-L3shellProb);
        runningSumProb += M1shellProb;
        shellProbs[4] = runningSumProb;
      }
      if (beamEnergy > e.getM2Edge() && e.getAtomicNumber() >= 73) { 
        M2shellProb = e.getM2ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb-L3shellProb-M1shellProb);
        runningSumProb += M2shellProb;
        shellProbs[5] = runningSumProb;
      }
      if (beamEnergy > e.getM3Edge() && e.getAtomicNumber() >= 73) { 
        M3shellProb = e.getM3ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb-L3shellProb-M1shellProb-M2shellProb);
        runningSumProb += M3shellProb;
        shellProbs[6] = runningSumProb;
      }
      if (beamEnergy > e.getM4Edge() && e.getAtomicNumber() >= 73) { 
        M4shellProb = e.getM4ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb-L3shellProb-M1shellProb-M2shellProb-M3shellProb);
        runningSumProb += M4shellProb;
        shellProbs[7] = runningSumProb;
      }
      if (beamEnergy > e.getM5Edge() && e.getAtomicNumber() >= 73) { 
        M4shellProb = e.getM5ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb-L3shellProb-M1shellProb-M2shellProb-M3shellProb-M4shellProb);
        runningSumProb += M4shellProb;
        shellProbs[8] = runningSumProb;
      }
      ionisationProbs.put(e, shellProbs);
    }
    return ionisationProbs;
  }
  
  private void populateAugerLinewidths() throws IOException {
    for (int i = 0; i < augerElements.length; i++) {
      int shell = getNumAugerShells(i);
      HashMap<Integer, double[]> shellLinewidths = new HashMap<Integer, double[]>();
      HashMap<Integer, double[]> shellTransitionProbs = new HashMap<Integer, double[]>();
      HashMap<Integer, double[]> shellTransitionEnergies = new HashMap<Integer, double[]>();
      HashMap<Integer, double[]> shellCumulativeProbs = new HashMap<Integer, double[]>();
      HashMap<Integer, double[]> leftShellIndexes = new HashMap<Integer, double[]>();
      HashMap<Integer, double[]> dropShellIndexes = new HashMap<Integer, double[]>();

      for (int j =0; j <= shell; j++) {
    //  if (augerElements[i] != 26) {
      double[] transitionProbs = new double[65];
      double[] leftShellIndex = new double[65];
      double[] dropShellIndex = new double[65];
      double[] cumulativeTransitionProbs = new double[65];
      double sumProb = 0;
      double[] transitionLinewidths = new double[65];
      double[] transitionEnergies = new double[65];
      String elementNum = String.valueOf(augerElements[i]) + "-" + j + ".csv";
      String filePath = "constants/auger_linewidths/" + elementNum;
      InputStreamReader isr = locateFile(filePath);
      BufferedReader br = new BufferedReader(isr);
      String line;
      String[] components;
      int count = -1;
      while ((line = br.readLine()) != null) {
        count += 1;
        components = line.split(",");
        transitionLinewidths[count] = Double.parseDouble(components[1]);
        transitionProbs[count] = Double.parseDouble(components[2]);
        transitionEnergies[count] = Double.parseDouble(components[3]);
        leftShellIndex[count] = Double.parseDouble(components[4]);
        dropShellIndex[count] = Double.parseDouble(components[5]);
        sumProb += transitionProbs[count];
        cumulativeTransitionProbs[count] = sumProb;
      }
      //scale cumulative probs to one 
      for (int k = 0; k < cumulativeTransitionProbs.length; k++) {
        cumulativeTransitionProbs[k] = cumulativeTransitionProbs[k] * (1/sumProb);
      }
      if (j == 0) {
        totKAugerProb.put(augerElements[i], sumProb);
      }
      shellLinewidths.put(j, transitionLinewidths);
      shellTransitionProbs.put(j, transitionProbs);
      shellTransitionEnergies.put(j, transitionEnergies);
      shellCumulativeProbs.put(j, cumulativeTransitionProbs);
      leftShellIndexes.put(j,  leftShellIndex);
      dropShellIndexes.put(j,  dropShellIndex);
      }

      
      augerTransitionLinewidths.put(augerElements[i], shellLinewidths);
      augerTransitionProbabilities.put(augerElements[i], shellTransitionProbs);
      augerTransitionEnergies.put(augerElements[i], shellTransitionEnergies);
      augerExitIndex.put(augerElements[i], leftShellIndexes);
      augerDropIndex.put(augerElements[i], dropShellIndexes);
      cumulativeTransitionProbabilities.put(augerElements[i], shellCumulativeProbs);
    //  }
    //  else {
        
     // }
    }
  }
  
  private void populateFluorescenceLinewidths() throws IOException {
    for (int i = 0; i < augerElements.length; i++) {
      int shell = getNumAugerShells(i);
      HashMap<Integer, double[]> flLinewidths = new HashMap<Integer, double[]>();
      HashMap<Integer, double[]> flTransitionProbs = new HashMap<Integer, double[]>();
      HashMap<Integer, double[]> transitionEnergiesfl = new HashMap<Integer, double[]>();
      HashMap<Integer, double[]> flCumulativeProbs = new HashMap<Integer, double[]>();
      HashMap<Integer, double[]> dropShellIndexes = new HashMap<Integer, double[]>();
      
      for (int j =0; j <= shell; j++) {
        //  if (augerElements[i] != 26) {
          double[] transitionProbs = new double[65];
          double[] cumulativeTransitionProbs = new double[65];
          double[] dropShellIndex = new double[65];
          double sumProb = 0;
          double[] transitionLinewidths = new double[65];
          double[] transitionEnergies = new double[65];
          String elementNum = String.valueOf(augerElements[i]) + "-" + j + ".csv";
          String filePath = "constants/fl_linewidths/" + elementNum;
          InputStreamReader isr = locateFile(filePath);
          BufferedReader br = new BufferedReader(isr);
          String line;
          String[] components;
          int count = -1;
          while ((line = br.readLine()) != null) {
            count += 1;
            components = line.split(",");
            transitionLinewidths[count] = Double.parseDouble(components[1]);
            transitionProbs[count] = Double.parseDouble(components[2]);
            transitionEnergies[count] = Double.parseDouble(components[3]);
            dropShellIndex[count] = Double.parseDouble(components[4]);
            sumProb += transitionProbs[count];
            cumulativeTransitionProbs[count] = sumProb;
          }
          //scale cumulative probs to one 
          for (int k = 0; k < cumulativeTransitionProbs.length; k++) {
            cumulativeTransitionProbs[k] = cumulativeTransitionProbs[k] * (1/sumProb);
          }
          if (j == 0) {
            totKAugerProb.put(augerElements[i], sumProb);
          }
          flLinewidths.put(j, transitionLinewidths);
          flTransitionProbs.put(j, transitionProbs);
          transitionEnergiesfl.put(j, transitionEnergies);
          flCumulativeProbs.put(j, cumulativeTransitionProbs);
          dropShellIndexes.put(j,  dropShellIndex);

          }
          
          flTransitionLinewidths.put(augerElements[i], flLinewidths);
          flTransitionProbabilities.put(augerElements[i], flTransitionProbs);
          flTransitionEnergies.put(augerElements[i], transitionEnergiesfl);
          flCumulativeTransitionProbabilities.put(augerElements[i], flCumulativeProbs);
          flDropIndex.put(augerElements[i], dropShellIndexes);
    }
  }
  
  private int getNumAugerShells(int i) {
    int shell = 0;
    if (augerElements[i] == 11) {
      shell = 1;
    }
    if (augerElements[i] == 12) {
      shell = 2;
    }
    if (augerElements[i] <= 20 && augerElements[i] > 12) {
      shell = 3;
    }
    if (augerElements[i] >= 25 && augerElements[i] <= 30) {
      shell = 6;
    }
    if (augerElements[i] >= 33 && augerElements[i] <= 34) {
      shell = 8;
    }
    return shell;
  }
  
  private void populateEnergyPerInel(Beam beam, CoefCalc coefCalc, double photonEnergy) {
    double maxEnergy = photonEnergy;
    for (int i = 1; i <= numInelEnBins; i++ ){
      double thisEnergy = i* (maxEnergy / numInelEnBins);
      //need to get elastic for inel to work
      double elasticMFPL = coefCalc.getElectronElasticMFPL(thisEnergy, false);
      double stoppingPower = coefCalc.getStoppingPower(thisEnergy, false);
      double inelMFPL = coefCalc.getElectronInelasticMFPL(thisEnergy, false);
      double keVPerInteraction = inelMFPL*stoppingPower;
      energyPerInel.put(thisEnergy, keVPerInteraction);
      if (coefCalc.isCryo()) {
        elasticMFPL = coefCalc.getElectronElasticMFPL(thisEnergy, true);
        stoppingPower = coefCalc.getStoppingPower(thisEnergy, true);
        inelMFPL = coefCalc.getElectronInelasticMFPL(thisEnergy, true);
        keVPerInteraction = inelMFPL*stoppingPower;
        energyPerInelSurrounding.put(thisEnergy, keVPerInteraction);
      }
    }
  }
  
  private void  populateStraggling(Beam beam, CoefCalc coefCalc, double photonEnergy) {
    /*
    //way 1 - non-relativistic but takes in thickness so probably better for lower energy
    
    
    //way 2 - relativistic but assuming thin so probably better for higher energy
  //  double m = 9.10938356E-31; // in Kg
    double csquared = c*c;  // (m/s)^2   //update this to be precise
    double maxEnergy = photonEnergy;
    for (int i = 1; i <= numInelEnBins; i++ ){
      double thisEnergy = i* (maxEnergy / numInelEnBins);
      double Vo =  thisEnergy * Beam.KEVTOJOULES;
      double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2); 
      double Emax = (2*m*csquared*betaSquared / (1-betaSquared)) /Beam.KEVTOJOULES; 
      double averageE = energyPerInel.get(thisEnergy);
    }
    */
    
  }
  
  private void populateAngularEmissionProbs() {
    angularEmissionProbs = new double[numberAngularEmissionBins];
  //    double photoelectric = coefCalc.getElementAbsorptionCoef(beam.getPhotonEnergy(), e);
      //integrate under the whole curve
      double lastHeight = 0;
      double totalArea = 0;
      for (int i = 0; i <= 100; i++) {
        double angle = ((Math.PI)/100)*i;
        double height = solvePolarisationEquationForAngle(angle, 1, 2);
        if (i > 0) {
          double area = ((lastHeight + height)/2) * ((Math.PI)/100);
          totalArea += area;
        }
        lastHeight = height;
      }
      //now get the proportion of some of these
   //   double[] emissionProbs = new double[numberAngularEmissionBins];
      lastHeight = 0;
      double cumulativeProb = 0;
      for (int i = 0; i <= numberAngularEmissionBins; i++) {
        double angle = ((Math.PI)/numberAngularEmissionBins)*i;
        double height = solvePolarisationEquationForAngle(angle, 1, 2);
        if (i > 0) {
          double area = ((lastHeight + height)/2) * ((Math.PI)/numberAngularEmissionBins);
          cumulativeProb += area / totalArea;
          angularEmissionProbs[i-1] = cumulativeProb;
        }
        lastHeight = height;
      }
    //  angularEmissionProbs.put(e, emissionProbs);
  
  }
  
  private double solvePolarisationEquationForAngle(double phi, double photoElectric, double beta) {
    double height = (photoElectric / (4*Math.PI)) * (1+(beta*0.5*(3*Math.pow(Math.cos(phi), 2) - 1)));
    return height;
  }
  
  private double getShellBindingEnergy(Element collidedElement, int collidedShell) {
    double shellBindingEnergy = 0;
    switch (collidedShell) {
      case 0: shellBindingEnergy = collidedElement.getKEdge();
              break;
      case 1: shellBindingEnergy = collidedElement.getL1Binding();
              break;
      case 2: shellBindingEnergy = collidedElement.getL2Edge();
              break;
      case 3: shellBindingEnergy = collidedElement.getL3Edge();
              break;
      case 4: shellBindingEnergy = collidedElement.getM1Edge();
              break;
      case 5: shellBindingEnergy = collidedElement.getM2Edge();
              break;
      case 6: shellBindingEnergy = collidedElement.getM3Edge();
              break;
      case 7: shellBindingEnergy = collidedElement.getM4Edge();
              break;
      case 8: shellBindingEnergy = collidedElement.getM5Edge();
              break;
    }
    return shellBindingEnergy;
  }
  
  private double getShellBindingEnergyGOS(Element collidedElement, int collidedShell) {
    //I just haven't put all the shells in properly yet
    double shellBindingEnergy = 0;
    switch (collidedShell) {
      case 0: shellBindingEnergy = collidedElement.getKEdge();
              break;
      case 1: shellBindingEnergy = collidedElement.getL1Edge();
              break;
      case 2: shellBindingEnergy = collidedElement.getM1Edge();
              break;
    }
    return shellBindingEnergy;
  }
  
  
  
  private double getPrimaryElasticScatteringAngle(double electronEnergy, int atomicNumber){
    boolean highEnergy = false;
    if (electronEnergy > 20) {
      highEnergy = true;
    }
   
    //determine if need to get data from file or it's already loaded
    boolean getFile = mapPopulated(highEnergy, atomicNumber);
    
    //get the right file if I need to
    if (getFile == true) {
      
      TreeMap<Double, double[]> elementData = new TreeMap<Double, double[]>();
      try {
        elementData =  getAngleFileData(highEnergy, atomicNumber);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } 
      //now add the file data to the global array
      if (highEnergy == true) {
        highEnergyAngles[atomicNumber] = elementData;
      }
      else {
        lowEnergyAngles[atomicNumber] = elementData;
      }
    }
    
    //Now use the data in the global array to work out the angle
    //get nearest energy
    Double energyKey = returnNearestEnergy(highEnergy, atomicNumber, electronEnergy);
    
    //should probably interpolate the values here tbh.... will do at some point
    
    //get the differential cross sections for that energy of the element
    double[] energyAngleProbs = null;
    if (highEnergy == true) {
      energyAngleProbs = highEnergyAngles[atomicNumber].get(energyKey);
    }
    else {
      energyAngleProbs = lowEnergyAngles[atomicNumber].get(energyKey);
    }
    //get the angle from this 
    double deflectionAngle = returnDeflectionAngle(highEnergy, energyAngleProbs);
    
    if (Double.isNaN(deflectionAngle)){
      System.out.println("test");
    }
    return deflectionAngle;
  }
  
  private InputStreamReader locateFile(String filePath) 
      throws UnsupportedEncodingException, FileNotFoundException{
    InputStream is = getClass().getResourceAsStream("/" + filePath);

    if (is == null) {
      is = new FileInputStream(filePath);
    }

    return new InputStreamReader(is, "US-ASCII");
  }

  private boolean mapPopulated(boolean highEnergy, int atomicNumber) {
    if (highEnergy == true) {
      if (highEnergyAngles[atomicNumber] == null) {
        return true;
      }
      else {
        return false;
      }
    }
    else {
      if (lowEnergyAngles[atomicNumber] == null) {
        return true;
      }
      else {
       return false;
      }
    }
  }

//--put it in here when I have copy and paste back
private TreeMap<Double, double[]> getAngleFileData(boolean highEnergy, int atomicNum) throws IOException{
String elementNum = String.valueOf(atomicNum) + ".csv";
String filePath = "";
if (highEnergy == true) {
filePath = "constants/above_20000/" + elementNum;
}
else {
filePath = "constants/below_20000/" + elementNum;
}

InputStreamReader isr = locateFile(filePath);
BufferedReader br = new BufferedReader(isr);
TreeMap<Double, double[]> elementData = new TreeMap<Double, double[]>();
String line;
String[] components;
int count = -1;
while ((line = br.readLine()) != null) {
count +=1 ;
components = line.split(",");
if (count > 0) { //if this is not the first line
  Double energy = Double.valueOf(components[0]);
  String[] angleProbsString = Arrays.copyOfRange(components, 1, components.length);
  double[] angleProbs = new double[angleProbsString.length];
  for (int i = 0; i < angleProbsString.length; i++) {
    angleProbs[i] = Double.parseDouble(angleProbsString[i]);
  }
  //Now add this to the local treemap
  elementData.put(energy, angleProbs);
}
}
return elementData;
}


private Double returnNearestEnergy(boolean highEnergy, int atomicNumber, double electronEnergy) {
Double nearestEnergy = 0.;
if (electronEnergy >= 0.05 && electronEnergy <= 300) {
Double beforeKey = 0.;
Double afterKey = 0.;
if (highEnergy == true) {
  beforeKey = highEnergyAngles[atomicNumber].floorKey(electronEnergy);
  afterKey = highEnergyAngles[atomicNumber].ceilingKey(electronEnergy);
  
}
else {
  beforeKey = lowEnergyAngles[atomicNumber].floorKey(electronEnergy);
  afterKey = lowEnergyAngles[atomicNumber].ceilingKey(electronEnergy);
}
if (beforeKey == null) {
  beforeKey = 0.;
}
if (afterKey == null) {
  afterKey = 0.;
}
beforeKey = (beforeKey == 0.) ? afterKey: beforeKey;
afterKey = (afterKey == 0.) ? beforeKey: afterKey;
if (Math.abs(electronEnergy - beforeKey) <= Math.abs(electronEnergy-afterKey)) {
  nearestEnergy = beforeKey;
}
else {
  nearestEnergy = afterKey;
}

}
return nearestEnergy;
}

private double returnDeflectionAngle(boolean highEnergy, double[] energyAngleProbs) {
double totalProb = 0;
for (int i = 0; i < energyAngleProbs.length; i++) {
totalProb += energyAngleProbs[i];
}
double[] probPerAngle = new double[energyAngleProbs.length];
double sumProb = 0;
for (int j = 0; j < energyAngleProbs.length; j++) {
sumProb += energyAngleProbs[j];
probPerAngle[j] = sumProb/totalProb;
}

double RND = Math.random();
double index = 0;
for (int k = 0; k < probPerAngle.length; k++) {
if (probPerAngle[k] >= RND) {
  index = k;
  break;
}
}
//convert the index to an angle
double angleDegrees = 0;
if (highEnergy == true) {
double startFactor = 0.;
int factor = 0;
double divideFactor = 4;
double minusFactor = 0;
double modFactor = 0;
if (index >=1 && index < 146) {
  minusFactor = 1;
  modFactor = 36;
  factor = (int) ((int) (index - minusFactor)/modFactor);
  startFactor = Math.pow(10, factor) * 0.0001;
  divideFactor = 4;
}
else if (index >= 146 && index < 236) {
//   factor = (int) (index-146)/100;
  startFactor = 1;
  divideFactor = 10;
  minusFactor = 146;
  modFactor = 90;
}
else if (index >= 236 && index <= 296) {
  startFactor = 10;  //go until 25
  divideFactor = 40;
  minusFactor = 236;
  modFactor = 60;
}
else if (index > 296) {
  startFactor = 25;
  divideFactor = 50;
  minusFactor = 296;
  modFactor = 1000000; //just anything super high as all but first one
}
angleDegrees = startFactor + (((index-minusFactor)%modFactor)*(startFactor/divideFactor));
if (Double.isNaN(angleDegrees)){
//   System.out.println("test");
  angleDegrees = 0;
}
}
else {
angleDegrees = 1.0 * index;
}
double angleRadians = angleDegrees * Math.PI/180;
/*
if (index > 296 && highEnergy == true) {
System.out.println("test");
}
*/

return angleRadians;
}
  
private Element chooseLowEnElement(CoefCalc coefCalc, double Pinner, Map<Element, Double> gosOuterIonisationProbs, Map<Element, double[]> ionisationProbs) {
  Element collidedElement = null;
  int collidedShell = -1;
  double RNDInner = Math.random();
  double elementRND = Math.random();
  if (RNDInner < Pinner) {
    //then this hit an inner shell
    for (Element e : ionisationProbs.keySet()) {
      collidedShell = findIfElementIonised(e, ionisationProbs, elementRND);
      if (collidedShell >= 0) {
        collidedElement = e;
        break;
      }
    } 
  }
  else {
    //hit an outer shell
    for (Element e : gosOuterIonisationProbs.keySet()) {
      int[] electrons = coefCalc.getNumValenceElectronsSubshells(e);
      int numInnerShells = electrons[1];
      collidedShell = numInnerShells;
      if (findIfOuterShellIonised(e, gosOuterIonisationProbs, elementRND) == true){
        collidedElement = e;
        break;
      }
    } 
  }
  return collidedElement;
}

  private int getGOSInelasticType(double[][] shellProbs, int shellIndex) {
    double runningSum = 0;
    double RND = Math.random();
    int type = 0;
    for (int i = 0; i < 3; i++) {
      runningSum += shellProbs[shellIndex][i]/shellProbs[shellIndex][3];
      if (runningSum > RND) { //then this type of collision
        type = i;
        break;
      }
    }
    return type;
  }
  
  private int getGOSInelasticTypePlasmon(double[] plasmonProbs) {
    double runningSum = 0;
    double RND = Math.random();
    int type = 0;
    for (int i = 0; i < 3; i++) {
      runningSum += plasmonProbs[i]/plasmonProbs[3];
      if (runningSum > RND) { //then this type of collision
        type = i;
        break;
      }
    }
    return type;
  }
  
  public double WkToWak(double E, double Wk, double Uk) {
    if (E*1000 > 3*Wk - 2*Uk) {
      return Wk;
    }
    else {
      return (E*1000+2*Uk)/3;
    }
  }
  
  public double getQak(double E, double Wk, double Uk) {
    if (E*1000 > 3*Wk - 2*Uk) {
      return Uk;
    }
    else {
      return Uk * (E*1000/(3*Wk-2*Uk));
    }
  }
  
  public double getEnergyLossDistant(double Wdis, double Uk){ 
    double RND = Math.random();
    double W = Wdis - Math.pow(RND*Math.pow(Wdis-Uk, 2), 0.5);
    return W; //returning eV
  }
  
  public double getClosea(double E) { // E in keV
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = (E) * Beam.KEVTOJOULES;
    double a = Math.pow(Vo/(Vo+m*csquared), 2);
    return a;
  }
  
  public double getGOSPrimaryThetaLong(double EkeV, double Q, double WakeV, double previousTheta) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double theta = 0;
    //again make sure I sort out units in here
    double E = EkeV * Beam.KEVTOJOULES;
    double Wak = (WakeV/1000)*Beam.KEVTOJOULES;
    double numerator = E*(E+2*m*csquared) + (E-Wak)*(E-Wak+2*m*csquared) - Q*(Q+2*m*csquared);
    double denominator = 2*Math.pow(E*(E+2*m*csquared)*(E-Wak)*(E-Wak+2*m*csquared), 0.5);
    double cosTheta = numerator/denominator;
    theta = Math.acos(cosTheta);
    theta = previousTheta + theta;
    if (theta >= (2 * Math.PI)) {
      theta -= 2*Math.PI;
    }
    return theta;
  }
  
  public double getRandomk(double E, double Qk) { //E in keV and Qk in eV
    double kc = Math.max(Qk, Wcc) / (E*1000 + Qk);  //get units right ofc
    double k = 0;
    double a = getClosea(E);
    double RND = Math.random();
    double zeta = RND * (1.0+5.0*a*kc/2.0);
    if (zeta < 1) {
      k = kc / (1-zeta*(1-2*kc));
    }
    else {
      k = kc + (zeta-1)*(1-2*kc)/(5*a*kc);
    }
    return k; //dimensionless
  }
  
  public double getPDFk(double E, double k, double Qk) {
    double kc = Math.max(Qk, Wcc) / (E*1000 + Qk);  //get units right ofc
    double a = getClosea(E);   //assume this is the gamma one not sturnheimer one
    double PDF = (Math.pow(k, -2) + Math.pow(1-k, -2) - 1/(k*(1-k)) + a*(1+1/(k*(1-k))))
                  * heavisideStepFunction(k-kc) * heavisideStepFunction(0.5-k);
    return PDF;
  }
  
//now do the rejection algorithm
  public double samplek(double E, double Qk) {
    double a = getClosea(E);
    boolean exit = false;
    double k = 0;
    int count = 0;
    while (exit == false) {
      k = getRandomk(E, Qk);
      double RND = Math.random();
      double LHS = RND * (1 + 5*a*Math.pow(k, 2));
      double RHS = Math.pow(k, 2) * getPDFk(E, k, Qk);
      if (LHS < RHS) {
        exit = true;
      }
      // testing clause
      count += 1;
      if (count > 10000) {

        System.out.println("the random sampling of k is always being rejected");
        break;
      }
    }
    return k;
  }
  
  private int heavisideStepFunction(double x) {
    if (x >= 0) {
      return 1;
    }
    else {
      return 0;
    }
  }
  
  private double getGOSPrimaryThetaClose(double EkeV, double WkeV, double previousTheta) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double E = EkeV*Beam.KEVTOJOULES;
    double W = WkeV*Beam.KEVTOJOULES;
    
    double cosTheta = Math.pow(((E-W)/E) * ((E+ 2*m*csquared)/ (E-W+2*m*csquared)),0.5);
    double theta = Math.acos(cosTheta);
    theta = previousTheta + theta;
    if (theta >= (2 * Math.PI)) {
      theta -= 2*Math.PI;
    }
    return theta;
  }
  
  public double secondaryThetaClose(double EkeV, double WkeV, double previousTheta) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double W = WkeV * Beam.KEVTOJOULES;
    double E = EkeV * Beam.KEVTOJOULES;
    double cosTheta = Math.pow((W/E)*((E+2*m*csquared)/(W+2*m*csquared)), 0.5);
    double theta = Math.acos(cosTheta);
    theta = previousTheta + theta;
    if (theta >= (2 * Math.PI)) {
      theta -= 2*Math.PI;
    }
    return theta;
  }
  
  public double secondaryThetaDistant(double E, double WakeV, double Q, double previousTheta) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = E * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    
    double Wak = (WakeV/1000)*Beam.KEVTOJOULES;
    double cosTheta = Math.pow(((Math.pow(Wak, 2)/betaSquared)/(Q*(Q+2*m*csquared)))
                      *Math.pow(1+(Q*(Q+2*m*csquared)-Math.pow(Wak, 2))/(2*Wak*(Vo+m*csquared)), 2),0.5);
    double theta = Math.acos(cosTheta);
    theta = previousTheta + theta;
    if (theta >= (2 * Math.PI)) {
      theta -= 2*Math.PI;
    }
    return theta;
  }
  
  //Do a RD3D check of the values to make sure the RD3D dose is not ridiculous
  public void RD3Dcheck(Beam beam, Wedge wedge, CoefCalc coefCalc) {
    //firstly need to set the photons per fs in beam
    double meanEnergyJoules = meanEnergy*Beam.KEVTOJOULES;
    coefCalc.updateCoefficients(meanEnergy);
    double numberOfPhotons = PULSE_ENERGY/meanEnergyJoules;
    double photonsPerfs = numberOfPhotons/PULSE_LENGTH;
    beam.setPhotonsPerfs(photonsPerfs);
    
    double[] crystCoords;
    int[] crystalSize = getMaxPixelCoordinates();
    double[][][] voxImageFluence = new double[crystalSize[0]][crystalSize[1]][crystalSize[2]];
    double[][][] voxImageDose = new double[crystalSize[0]][crystalSize[1]][crystalSize[2]];
    double[][][] absorbedEnergy = new double[crystalSize[0]][crystalSize[1]][crystalSize[2]];
    double[][][] voxElasticYield = new double[crystalSize[0]][crystalSize[1]][crystalSize[2]];
    double[][][] voxImageComptonFluence = new double[crystalSize[0]][crystalSize[1]][crystalSize[2]];
    
    final double fluenceToDoseFactorCompton = -1
        * Math.expm1(-1 * coefCalc.getInelasticCoefficient()
            / crystalPixPerUMXFEL)
        // exposure for the Voxel (J) * fraction absorbed by voxel
        / (1e-15 * (Math.pow(crystalPixPerUMXFEL, -3) * coefCalc
            .getDensity()))
        // Voxel mass: 1um^3/1m/ml
        // (= 1e-18/1e3) / [volume (um^-3) *density (g/ml)]
        * 1e-6; // MGy
        //

 double fluenceToDoseFactor = -1
    * Math.expm1(-1 * coefCalc.getAbsorptionCoefficient()
        / crystalPixPerUMXFEL)
    // exposure for the Voxel (J) * fraction absorbed by voxel
    / (1e-15 * (Math.pow(crystalPixPerUMXFEL, -3) * coefCalc
        .getDensity()))
    // Voxel mass: 1um^3/1m/ml
    // (= 1e-18/1e3) / [volume (um^-3) *density (g/ml)]
    * 1e-6; // MGy
    
    double beamAttenuationFactor = Math.pow(crystalPixPerUMXFEL, -2)
        * wedge.getTotSec();
    // Area in um^2 of a voxel * time per angular step

    final double beamAttenuationExpFactor = -coefCalc     
        .getAttenuationCoefficient();
    
    long numVoxels = 0;
    long numExposedVoxels = 0;
    double sumDose = 0;
    for (int i = 0; i < crystalSize[0]; i++) {
      for (int j = 0; j < crystalSize[1]; j++) {
        for (int k = 0; k < crystalSize[2]; k++) {
          if (isMicrocrystalAt(i, j, k, 0, wedge)) {
            numVoxels += 1;
            // Rotate crystal into position
            crystCoords = convertToCartesianCoordinates(i, j, k);
            for (int l = 0; l < 3; l++) {
              crystCoords[l] = crystCoords[l]/1000;
            }
            
            /* Unattenuated beam intensity (J/um^2/s) */
            double unattenuatedBeamIntensity = beam.beamIntensity(
                crystCoords[0], crystCoords[1],
                0);

            if (unattenuatedBeamIntensity > 0d) {
              numExposedVoxels += 1;
              double depth = crystCoords[2];

              /*
               * Assigning exposure (joules incident) and dose (J/kg absorbed)
               * to the voxel.
               */
          
              voxImageFluence[i][j][k] =     // Attenuates the beam for absorption in joules 
                  unattenuatedBeamIntensity * beamAttenuationFactor // beam attenuation factor includes voxel size
                      * Math.exp(depth * beamAttenuationExpFactor);   
              
              //calculate compton effect
              double electronweight = 9.10938356E-31;
              double csquared = 3E8*3E8;
              double energyOfPhotonJoules = (meanEnergy * Beam.KEVTOJOULES); // Might change to loop through energies, after seen effect in RD3D
              double mcsquared = electronweight * csquared;
              double voxImageElectronEnergyDose = mcsquared / (2*energyOfPhotonJoules + mcsquared);
              voxImageElectronEnergyDose = (energyOfPhotonJoules * (1 - (Math.pow(voxImageElectronEnergyDose, 0.5)))); //Compton electron energy in joules
              double numberofphotons = voxImageFluence[i][j][k] / energyOfPhotonJoules; //This gives I0 in equation 9 in Karthik 2010, dividing by beam energy leaves photons per um^2/s
              voxImageComptonFluence[i][j][k] = numberofphotons * voxImageElectronEnergyDose; //Re-calculate voxImageFluence using Compton electron energy
              double voxImageDoseCompton = fluenceToDoseFactorCompton * voxImageComptonFluence[i][j][k];
              
              //elastic yield
              //Dose absorbed by photoelectric effect
              voxImageDose[i][j][k] = fluenceToDoseFactor * voxImageFluence[i][j][k] + voxImageDoseCompton;
              sumDose += voxImageDose[i][j][k];
            }
          }
        }
      }
    }
    
    RD3D_ADWC = sumDose/ numVoxels;
    RD3D_ADER = sumDose/ numExposedVoxels;
    boolean tooLowRes = false;
    if ((Math.abs(RD3D_ADER - raddoseStyleDose) / raddoseStyleDose) > 0.20) {
      System.out.println("Consider simulating more photons for a more reliable result");
    }
  }
  
  
  
  private boolean isMicrocrystalAt(final double x, final double y, final double z, double angle, Wedge wedge) {
    //Note that this is absolutely only right for a cuboid at the moment
    //This can stay as a quick test
    //this quick test actually messes with the program and it's imperfect placing of pixels
    
    
    //will need to change this quick test when I start considering crytal rotation
    boolean outside = false;
    if ((x > XDimension/2) || (x < -XDimension/2)) {
     // return false;
     // outside = true;
    }
    if ((y > YDimension/2) || (y < -YDimension/2)) {
    //  return false;
    //  outside = true;
    }
    if ((z > ZDimension/2) || (z < -ZDimension/2)) {
    //  return false;
    //  outside = true;
    }
     
    //now do the crystal occupancy stuff
    //convert xyz to ijk
    
    //do the reverse translate rotate to get the inital crsystal xyz that now occupies the 
    //xyz of this interaction
    
    
    //double[] rotatedCoords = translateInteractionToPosition(x, y, z, wedge.getStartVector(), wedge.getTranslationVector(angle), angle);
    
    int[] pixelCoords = convertToPixelCoordinates(x, y, z, angle, wedge); 
    
    //if the pixel coords are less than 0 or more than max then need to return false
    int[] maxVoxel = getMaxPixelCoordinates();
    if (pixelCoords[0] < 0 || pixelCoords[0] >= maxVoxel[0]) {
      //return false;
      outside = true;
    }
    if (pixelCoords[1] < 0 || pixelCoords[1] >= maxVoxel[1]) {
      //return false;
      outside = true;
    }
    if (pixelCoords[2] < 0 || pixelCoords[2] >= maxVoxel[2]) {
      //return false;
      outside = true;
    }
    
    //this is where I should change it!!!
    //should just test the occupnacy every time!
    /*
    boolean[] occ = crystOccXFEL[pixelCoords[0]][pixelCoords[1]][pixelCoords[2]];  //This means that if has already been done don't do it again
                                          // Really needed to speed up Monte Carlo

    if (!occ[0]) {
      occ[1] = calculateCrystalOccupancy(x, y, z);
      occ[0] = true;
    }


    */
    boolean occ = calculateCrystalOccupancy(x, y, z);
    
    if (occ == true) {
      //set a max and min in each dimension
      setMaxMin(x, y, z);
    }
    /*
    if (occ == true && outside == true) {
      System.out.println("wrong");
    }
    */
    return occ;
  }
  
  private void setMaxMin(double x, double y, double z) {
    double[] dims = {x, y, z};
    for (int i = 0; i < 3; i++) {
      if (dims[i] > maxDims[i]) {
        maxDims[i] = dims[i];
      }
      if (dims[i] < minDims[i]) {
        minDims[i] = dims[i];
      }
    }
  }
  
  private int[] convertToPixelCoordinates(final double x, final double y, final double z, final double angle, Wedge wedge) {
    double[] rotatedCoords = translateInteractionToPosition(x, y, z, wedge.getStartVector(), wedge.getTranslationVector(angle), angle);
    double[] xMinMax = this.minMaxVertices(0, verticesXFEL);
    double[] yMinMax = this.minMaxVertices(1, verticesXFEL);
    double[] zMinMax = this.minMaxVertices(2, verticesXFEL);
    int i = (int) StrictMath.round(((rotatedCoords[0]/1000) - xMinMax[0]) * crystalPixPerUMXFEL);
    int j = (int) StrictMath.round(((rotatedCoords[1]/1000) - yMinMax[0]) * crystalPixPerUMXFEL);
    int k = (int) StrictMath.round(((rotatedCoords[2]/1000) - zMinMax[0]) * crystalPixPerUMXFEL);
    int[] pixelCoords = {i, j, k};
    
    
    //if there is a rotation and translation I will need to apply it here
    //This involves applying the opposite translation and rotation than that applied to the crystal
    
    
    return pixelCoords;
  }
  
  private double[] convertToCartesianCoordinates(final int i, final int j, final int k) {
    double[] xMinMax = this.minMaxVertices(0, verticesXFEL);
    double[] yMinMax = this.minMaxVertices(1, verticesXFEL);
    double[] zMinMax = this.minMaxVertices(2, verticesXFEL);
    double x = ((i/crystalPixPerUMXFEL) + xMinMax[0])*1000;
    double y = ((j/crystalPixPerUMXFEL) + yMinMax[0])*1000;
    double z = ((k/crystalPixPerUMXFEL) + zMinMax[0])*1000;
    double[] cartesianCoords = {x, y, z};
    return cartesianCoords;
  }
  
  private double[] translateInteractionToPosition(double x, double y, double z, Double[] wedgeStart, Double[] wedgeTranslation,
      double angle) {
    double[] translateRotateCoords = new double[3];
 // Translate Y
    translateRotateCoords[1] = y
    - 1000*wedgeStart[1] - 1000*wedgeTranslation[1];
    // Translate X
    double translateCoordX = x
    - 1000*wedgeStart[0] - 1000*wedgeTranslation[0];
    // Translate Z
    double translateCoordZ = z
    - 1000*wedgeStart[2] - 1000*wedgeTranslation[2];
    /* Rotate clockwise when y axis points away from observer */
    // Rotate X
    
    //now do the reverse translation 
    //this is the same but anglecos and anglesin are 2pi minus
    double anglecos = Math.cos(2*Math.PI - angle);
    double anglesin = Math.sin(2*Math.PI - angle);
    translateRotateCoords[0] = translateCoordX * anglecos
    + translateCoordZ * anglesin;
    // Rotate Z
    translateRotateCoords[2] = -1 * translateCoordX * anglesin
    + translateCoordZ * anglecos;

    return translateRotateCoords;
}
  
  public boolean calculateCrystalOccupancy(final double x, final double y, final double z)
  {
    
    //i need to change this to take the rotated values if there has been rotation
    //rotated vertices, rotated originDistances
    
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

    for (int l = 0; l < indicesXFEL.length; l++) {
      double intersectionDistance = Vector.rayTraceDistance(rotatedNormals[l],
          directionVector, origin, rotatedOriginDistances[l]);

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
        System.arraycopy(rotatedVerticesXFEL[indicesXFEL[l][m] - 1], 0, triangleVertices[m],
            0, 3);
      }

  //    boolean crosses = Vector.polygonInclusionTest(triangleVertices,
  //        intersectionPoint);
      
      boolean crosses = Vector.polygonInclusionTest(expandedRotatedVerticesXFEL[l],
          intersectionPoint);

      if (crosses) {
        inside = !inside;
      }
    }
    
    //my quick test
    /*
    boolean outside = false;
    double[] point = {x/1000, y/1000, z/1000};
    for (int k = 0; k < 3; k++) {
   //   point[k] = Math.round(point[k] *1000.0)/1000.0;
      if (point[k] > 1.05 || point[k] < -1.05) {
        outside = true;
      }
    }
    
    if(inside == outside) {
      System.out.println("no");
    }
    */
    return inside;
  }
  
  public boolean calculateCrystalOccupancySurrounding(final double x, final double y, final double z)
  {
    
    //i need to change this to take the rotated values if there has been rotation
    //rotated vertices, rotated originDistances
    
    if (normalsSurrounding == null) {
      calculateNormalsSurrounding(false);
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

    for (int l = 0; l < indicesSurrounding.length; l++) {
      double intersectionDistance = Vector.rayTraceDistance(rotatedNormalsSurrounding[l],
          directionVector, origin, rotatedOriginDistancesSurrounding[l]);

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
        System.arraycopy(rotatedVerticesSurrounding[indicesSurrounding[l][m] - 1], 0, triangleVertices[m],
            0, 3);
      }

  //    boolean crosses = Vector.polygonInclusionTest(triangleVertices,
  //        intersectionPoint);
      
      boolean crosses = Vector.polygonInclusionTest(expandedRotatedVerticesSurrounding[l],
          intersectionPoint);

      if (crosses) {
        inside = !inside;
      }
    }

    return inside;
  }
  
  private int[] getMaxPixelCoordinates() {
    double[] xMinMax = this.minMaxVertices(0, verticesXFEL);
    double[] yMinMax = this.minMaxVertices(1, verticesXFEL);
    double[] zMinMax = this.minMaxVertices(2, verticesXFEL);
    Double xdim = xMinMax[1] - xMinMax[0];
    Double ydim = yMinMax[1] - yMinMax[0];
    Double zdim = zMinMax[1] - zMinMax[0];
    int nx = (int) StrictMath.round(xdim * crystalPixPerUMXFEL) + 1;
    int ny = (int) StrictMath.round(ydim * crystalPixPerUMXFEL) + 1;
    int nz = (int) StrictMath.round(zdim * crystalPixPerUMXFEL) + 1;
    int[] maxCoord = {nx, ny, nz};
    return maxCoord;
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
  
  private double getIntersectionDistance(double x, double y, double z, double ca, double cb, double cc, boolean minVal) {
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
    for (int l = 0; l < indicesXFEL.length; l++) {
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
          double max = Math.max(minIntersect, intersectionDistance);
          if (minVal == true) {
            minIntersect = min;
          }
          else {
            minIntersect = max;
          }
        }
      }

    }
    return minIntersect;
  }
  
  private double getIntersectionDistanceSurrounding(double x, double y, double z, double ca, double cb, double cc, boolean minVal) {
    if (normalsSurrounding == null) {
      calculateNormalsSurrounding(false);
    }

    double[] directionVector = {ca, cb, cc}; //the actual direction vector
    double minIntersect = 0;
    double[] origin = new double[3];
    origin[0] = x/1000;
    origin[1] = y/1000;
    origin[2] = z/1000;
    
    double intersectionDistance = 0;
    for (int l = 0; l < indicesSurrounding.length; l++) {
      intersectionDistance = Vector.rayTraceDistance(normalsSurrounding[l],
          directionVector, origin, originDistancesSurrounding[l]);

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
          double max = Math.max(minIntersect, intersectionDistance);
          if (minVal == true) {
            minIntersect = min;
          }
          else {
            minIntersect = max;
          }
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
   * Calculates normal array from index and vertex arrays.
   * Also calculates signed distances of each triangle
   * from the origin.
   */
  public void calculateNormals(final boolean rotated) {

    double[][] verticesUsed = verticesXFEL;
    double[] originDistancesUsed = new double[verticesXFEL.length];
    double[][] normalsUsed = new double[verticesXFEL.length][3];
    
    if (rotated) {
      verticesUsed = rotatedVerticesXFEL;
    }

    normalsUsed = new double[indicesXFEL.length][3];
    originDistancesUsed = new double[indicesXFEL.length];

    for (int i = 0; i < indicesXFEL.length; i++) {
      // get the three vertices which this triangle corresponds to.
      double[] point1 = verticesUsed[indicesXFEL[i][0] - 1];
      double[] point2 = verticesUsed[indicesXFEL[i][1] - 1];
      double[] point3 = verticesUsed[indicesXFEL[i][2] - 1];

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

    
    if (rotated) {
      rotatedOriginDistances = new double[indicesXFEL.length];
      rotatedNormals = new double[indicesXFEL.length][3];

      for (int i = 0; i < normalsUsed.length; i++) {
        System.arraycopy(normalsUsed[i], 0, rotatedNormals[i], 0, 3);
      }

      System.arraycopy(originDistancesUsed, 0, rotatedOriginDistances, 0,
          indicesXFEL.length);
    } else {
      originDistances = new double[indicesXFEL.length];
      normals = new double[indicesXFEL.length][3];

      for (int i = 0; i < normalsUsed.length; i++) {
        System.arraycopy(normalsUsed[i], 0, normals[i], 0, 3);
      }

      System.arraycopy(originDistancesUsed, 0, originDistances, 0,
          indicesXFEL.length);
    }
    
  }
  
  public void calculateNormalsSurrounding(final boolean rotated) {

    double[][] verticesUsed = verticesSurrounding;
    double[] originDistancesUsed = new double[verticesSurrounding.length];
    double[][] normalsUsed = new double[verticesSurrounding.length][3];
    
    if (rotated) {
      verticesUsed = rotatedVerticesSurrounding;
    }

    normalsUsed = new double[indicesSurrounding.length][3];
    originDistancesUsed = new double[indicesSurrounding.length];

    for (int i = 0; i < indicesSurrounding.length; i++) {
      // get the three vertices which this triangle corresponds to.
      double[] point1 = verticesUsed[indicesSurrounding[i][0] - 1];
      double[] point2 = verticesUsed[indicesSurrounding[i][1] - 1];
      double[] point3 = verticesUsed[indicesSurrounding[i][2] - 1];

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

    
    if (rotated) {
      rotatedOriginDistancesSurrounding = new double[indicesSurrounding.length];
      rotatedNormalsSurrounding = new double[indicesSurrounding.length][3];

      for (int i = 0; i < normalsUsed.length; i++) {
        System.arraycopy(normalsUsed[i], 0, rotatedNormalsSurrounding[i], 0, 3);
      }

      System.arraycopy(originDistancesUsed, 0, rotatedOriginDistancesSurrounding, 0,
          indicesSurrounding.length);
    } else {
      originDistancesSurrounding = new double[indicesSurrounding.length];
      normalsSurrounding = new double[indicesSurrounding.length][3];

      for (int i = 0; i < normalsUsed.length; i++) {
        System.arraycopy(normalsUsed[i], 0, normalsSurrounding[i], 0, 3);
      }

      System.arraycopy(originDistancesUsed, 0, originDistancesSurrounding, 0,
          indicesSurrounding.length);
    }
    
  }
  
  private void setUpRotatedVertices(final double angrad, final Wedge wedge) {
    rotatedVerticesXFEL = new double[verticesXFEL.length][3];

    // Rotate and translate the vertices of the crystal
    // to the position defined by angrad (= deltaphi)

    for (int vertInd = 0; vertInd < verticesXFEL.length; vertInd++) {
      // Translate Y
      rotatedVerticesXFEL[vertInd][1] = verticesXFEL[vertInd][1]
          + wedge.getStartY()
          + wedge.getTransY(angrad);
      // Translate X
      double transX = verticesXFEL[vertInd][0]
          + wedge.getStartX()
          + wedge.getTransX(angrad);
      // Translate Z
      double transZ = verticesXFEL[vertInd][2]
          + wedge.getStartZ()
          + wedge.getTransZ(angrad);

      // Rotate X
      rotatedVerticesXFEL[vertInd][0] = transX * Math.cos(angrad)
          + transZ * Math.sin(angrad);
      // Rotate Z
      rotatedVerticesXFEL[vertInd][2] = -1 * transX * Math.sin(angrad)
          + transZ * Math.cos(angrad);
    }

    calculateNormals(true);

    /*
     * Now we populate the expandedRotatedVertex array.
     */

    expandedRotatedVerticesXFEL = new double[indicesXFEL.length][3][3];

    for (int i = 0; i < indicesXFEL.length; i++) {
      for (int j = 0; j < 3; j++) {
        System.arraycopy(rotatedVerticesXFEL[indicesXFEL[i][j] - 1], 0,
            expandedRotatedVerticesXFEL[i][j], 0, 3);
      }
    }
  }
  
  private void setUpRotatedVerticesSurrounding(final double angrad, final Wedge wedge) {
    rotatedVerticesSurrounding = new double[verticesSurrounding.length][3];

    // Rotate and translate the vertices of the crystal
    // to the position defined by angrad (= deltaphi)

    for (int vertInd = 0; vertInd < verticesSurrounding.length; vertInd++) {
      // Translate Y
      rotatedVerticesSurrounding[vertInd][1] = verticesSurrounding[vertInd][1]
          + wedge.getStartY()
          + wedge.getTransY(angrad);
      // Translate X
      double transX = verticesSurrounding[vertInd][0]
          + wedge.getStartX()
          + wedge.getTransX(angrad);
      // Translate Z
      double transZ = verticesSurrounding[vertInd][2]
          + wedge.getStartZ()
          + wedge.getTransZ(angrad);

      // Rotate X
      rotatedVerticesSurrounding[vertInd][0] = transX * Math.cos(angrad)
          + transZ * Math.sin(angrad);
      // Rotate Z
      rotatedVerticesSurrounding[vertInd][2] = -1 * transX * Math.sin(angrad)
          + transZ * Math.cos(angrad);
    }

    calculateNormalsSurrounding(true);

    /*
     * Now we populate the expandedRotatedVertex array.
     */

    expandedRotatedVerticesSurrounding = new double[indicesSurrounding.length][3][3];

    for (int i = 0; i < indicesSurrounding.length; i++) {
      for (int j = 0; j < 3; j++) {
        System.arraycopy(rotatedVerticesSurrounding[indicesSurrounding[i][j] - 1], 0,
            expandedRotatedVerticesSurrounding[i][j], 0, 3);
      }
    }
  }
  
  private double getStartingZSurrounding(double angle, Wedge wedge, double[] coord) {
    double[] zAxis = { 0, 0, 1 };

    List<Double> distancesFound = new ArrayList<Double>();

    for (int i = 0; i < indicesSurrounding.length; i++) {
      double intersectionDistance = 
          Vector.rayTraceDistance(rotatedNormalsSurrounding[i],
              zAxis, coord, rotatedOriginDistancesSurrounding[i]);

      Double distanceObject = Double.valueOf(intersectionDistance);

      if ( distanceObject.isNaN()
          || distanceObject.isInfinite()) {
        continue;
      }

      double[] intersectionPoint = Vector.rayTraceToPointWithDistance(
          zAxis, coord, intersectionDistance);

      boolean crosses = Vector.polygonInclusionTest(expandedRotatedVerticesSurrounding[i],
          intersectionPoint);

      if (crosses) {
        distancesFound.add(Double.valueOf(intersectionDistance));
      }
    }

    Collections.sort(distancesFound);
    if (distancesFound.isEmpty()) {
      return 0.0;
    }
    else {
      //then it does cross - return the most negative value
      return 1000*distancesFound.get(0).doubleValue();
    }
  }
  
  private double getStartingZ(double angle, Wedge wedge, double[] coord, boolean front) {
    double[] zAxis = { 0, 0, 1 };

    List<Double> distancesFound = new ArrayList<Double>();

    for (int i = 0; i < indicesXFEL.length; i++) {
      double intersectionDistance = 
          Vector.rayTraceDistance(rotatedNormals[i],
              zAxis, coord, rotatedOriginDistances[i]);

      Double distanceObject = Double.valueOf(intersectionDistance);

      if ( distanceObject.isNaN()
          || distanceObject.isInfinite()) {
        continue;
      }

      double[] intersectionPoint = Vector.rayTraceToPointWithDistance(
          zAxis, coord, intersectionDistance);

      boolean crosses = Vector.polygonInclusionTest(expandedRotatedVerticesXFEL[i],
          intersectionPoint);

      if (crosses) {
        distancesFound.add(Double.valueOf(intersectionDistance));
      }
    }

    Collections.sort(distancesFound);
    
    //get front or back
    int index = 0;
    if (front == false) {
      index = 1;
    }
    if (distancesFound.isEmpty()) {
      return 0.0;
    }
    else {
      //then it does cross - return the most negative value
      return 1000*distancesFound.get(index).doubleValue();
    }
  }
  
  
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
    
    public static double[] normaliseVector(final double[] vector) {
      double[] newVector = new double[3];
      double magnitude = vectorMagnitude(vector);
      
      for (int i = 0; i < 3; i++) {
        newVector[i] = vector[i]/magnitude;
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
         double[] point) {
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
  /*
  private Element getRandomElement(CoefCalc coefCalc) { //weight this by number of electrons in the crystal.
    double sampleVolume = XDimension * YDimension * ZDimension * 1E-21; //cm^3
    Set<Element> presentElements = coefCalc.getPresentElements(false);
    long sumElectrons = 0;
    int size = presentElements.size();
    HashMap<Element, Double> numElectrons = new HashMap<Element, Double>(size);
    for (Element e: presentElements) {
      double electrons = e.getAtomicNumber() * coefCalc.getTotalAtomsInCrystalElement(sampleVolume, e);
      if (electrons < 0.0) {
        numElectrons.put(e, electrons);
      }
      sumElectrons += electrons;
    }
    HashMap<Element, Double> elementProb = new HashMap<Element, Double>(size);
    double sumProb = 0;
    for (Element e: numElectrons.keySet()) {
      sumProb += numElectrons.get(e)/sumElectrons;
      elementProb.put(e, sumProb);
    }
    Element toReturn = null;
    double RND = Math.random();
    for (Element e: numElectrons.keySet()) {
      if (RND <= elementProb.get(e)) {
        toReturn = e;
        break;
      }
    }
    return toReturn;
  }
  
  private Element getComptonElement(CoefCalc coefCalc, double comptonCoef, double energy) { //weight this by number of electrons in the crystal.
    Set<Element> presentElements = coefCalc.getPresentElements(false);
    int size = presentElements.size();
    HashMap<Element, Double> elementProb = new HashMap<Element, Double>(size);
    double sumProb = 0;
    for (Element e: presentElements) {
      Map<String, Double> absCoeffsElement = new HashMap<String, Double>();
      absCoeffsElement = coefCalc.calculateCoefficientsElement(energy, e);
      double comptonEl = absCoeffsElement.get("Compton Attenuation");
      sumProb += comptonEl/comptonCoef;
      elementProb.put(e, sumProb);
    }
    Element toReturn = null;
    double RND = Math.random();
    for (Element e: presentElements) {
      if (RND <= elementProb.get(e)) {
        toReturn = e;
        break;
      }
    }
    return toReturn;
  }
  */
}

