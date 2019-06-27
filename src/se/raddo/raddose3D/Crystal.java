package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Crystal abstract class.
 */
public abstract class Crystal {

  /** Constant for data fields in Map constructors: X dimension in um. */
  public static final String     CRYSTAL_DIM_X                 = "DIM_X";
  /** Constant for data fields in Map constructors: Y dimension in um. */
  public static final String     CRYSTAL_DIM_Y                 = "DIM_Y";
  /** Constant for data fields in Map constructors: Z dimension in um. */
  public static final String     CRYSTAL_DIM_Z                 = "DIM_Z";
  /** Constant for data fields in Map constructors: Crystal resolution. */
  public static final String     CRYSTAL_RESOLUTION            = "RES";
  /** Constant for data fields in Map constructors: P angle (in degrees!). */
  public static final String     CRYSTAL_ANGLE_P               = "P";
  /** Constant for data fields in Map constructors: L angle (in degrees!). */
  public static final String     CRYSTAL_ANGLE_L               = "L";
  /** Constant for data fields in Map constructors: Coefficient Model. */
  public static final String     CRYSTAL_COEFCALC              = "COEFCALC";
  /** Constant for data fields in Map constructors: Dose Decay Model. */
  public static final String     CRYSTAL_DDM                   = "DECAYMODEL";
  /** Constant for data fields in Map constructors: Wireframe type. */
  public static final String     CRYSTAL_WIREFRAME_TYPE        = "WIREFRAME_TYPE";
  /** Constant for data fields in Map constructors: Wireframe file. */
  public static final String     CRYSTAL_WIREFRAME_FILE        = "WIREFRAME_FILE";
  /** Constant for data fields in Map constructors: Photoelectron escape. */
  public static final String     CRYSTAL_ELECTRON_ESCAPE       = "PHESCAPE";
  /** Constant for data fields in Map constructors: Fluorescent escape. */
  public static final String     CRYSTAL_FLUORESCENT_ESCAPE       = "FLESCAPE";
  /** Constant for data fields in Map constructors: Goniometer Axis. */
  public static final String     CRYSTAL_GONIOMETER_AXIS       = "GONIOMETER";
  /** Constant for data fields in Map constructors: Electrons. */
  public static final String     CRYSTAL_PROGRAM       = "PROGRAM";

  /** Constant for data fields in Map constructors: Photoelectron resolution. */
  
  public static final String     CRYSTAL_PHOTOELECTRON_RESOLUTION       = "PERES";
  /** Constant for data fields in Map constructors: Fluorescent resolution. */
  public static final String     CRYSTAL_FLUORESCENT_RESOLUTION       = "FLRES";
  
  /** Constant for data fields in Map constructors: Container Type. */
  public static final String     CRYSTAL_CONTAINER             = "CONTAINER";

  /** Default recommended voxel resolution in voxels/micrometre. */
  protected static  Double  CRYSTAL_RESOLUTION_DEF        = 0.5d;

  /** Number of exposure-steps when crystal is exposed without rotation. */
  public static final int        STATICEXPOSURE                = 100;

  /** Conversion factor from Gy to MGy. */
  private static final double        GY_TO_MGY                 = 1e-6;

  /** Unit conversion to get voxel mass in kg. */
  private static final double        UNIT_CONVERSION           = 1e-15;

  /**
   * Upper voxel limit for default resolution.
   * Resolution will be reduced if voxel number would otherwise exceed this.
   */
  private static final Long      CRYSTAL_RESOLUTION_DEF_VOXLIM = 1000000L;

  /** The dose decay model used by this Crystal instance. */
  private final DDM              ddm;
  /** The CoefCalc method being employed to generate crystal coefficients. */
  private final CoefCalc         coefCalc;
  /** The container encasing the irradiated sample*/
  private final Container        sampleContainer;
  
  public String crystalType;
  
  

  /**
   * Cumulative dose lost from crystal
   */
  private double totalEscapedDose                               = 0;  //Think this is just to test unless want to output
  
  /**
   * Cumulative PE dose lost from crystal
   */
  private double totalEscapedDosePE = 0;
  
  /**
   * Cumulative PE dose lost from crystal
   */
  private double totalEscapedDoseFL = 0;
  
  //This is just here to test
  private double totalAugerEnergyToRelease = 0;
  private double totalPEEnergyToRelease = 0;
  private double totalFlEnergyToRelease   = 0;
  private double totalDoseFromSurrounding = 0;
  
  /**
   * crystal minimum and maximum dimensions
   */
  public double[] xMinAndMax;
  public double[] yMinAndMax;
  public double[] zMinAndMax;
  public double[] minimumDimensions;
  public double[] maximumDimensions;
  
  /**
   * Cumulative dose both remaining in crystal and lost through photoelectron escape
   */
  private double totalCrystalDose                               = 0;
  
  /** The mass of each voxel in the crystal */
  private double voxelMass                                      = 0;

  /**
   * whether photoelectron escape should be included
   */
  public final boolean photoElectronEscape; 
  
  /**
   * whether fluorescent escape should be included
   */
  public final boolean fluorescentEscape;
  
  /**
   * whether electrons are used
   */
 // public final boolean useElectrons;
  public final String subprogram;
  
  /**
   * Goniometer Orientation
   */
  public final boolean verticalGoniometer; 
  
  /**
   * Average photoelectron binding energy to subtract from beam energy to get average photoelectron energy
   */
  public double EnergyToSubtractFromPE; 
  public double cryoEnergyToSubtractFromPE;
  
  public double XDim;
  public double YDim;
  public double ZDim;
  
  private final int numberAngularEmissionBins = 10;
  
  
  /**
   * The energy of each fluorescent event
   */
 // public double[][] fluorescenceEnergyPerEvent;
  public double[] fluorescenceEnergyPerEvent;
  /**
   * The proportion that each fluorescent wavelength contributes to the total fluorescent energy
   */
 // public double[][] fluorescenceProportionEvent;
  public double[] fluorescenceProportionEvent;
  /**
   * List of registered exposureObservers. Registered objects will be notified
   * of individual voxel exposure events and can also inspect the Crystal object
   * after each image and and wedge.
   */
  private final List<ExposeObserver> exposureObservers
                = new ArrayList<ExposeObserver>();

  /**
   * An single, common ExposureSummary object to which a reference can be
   * obtained via getExposureSummary().
   */
  private ExposureSummary        exposureSummaryObserver;

  /**
   * Generic property constructor for crystal classes.
   * Sets the DDM object if defined, a reasonable default otherwise.
   * Sets the CoefCalc object if defined, a reasonable default otherwise.
   * Sets the Container object if defined, a reasonable default otherwise.
   *
   * @param properties
   *          Map of type <Object, Object> that contains all crystal properties.
   *          The keys of the Map are defined by the constants in the
   *          Crystal class.
   */
  public Crystal(final Map<Object, Object> properties) {
    if (properties.get(Crystal.CRYSTAL_DDM) == null) {
      ddm = new DDMSimple();
 //     ddm = new DDMLinear();
 //       ddm = new DDMLeal(null,null,null);
    } else {
      ddm = (DDM) properties.get(Crystal.CRYSTAL_DDM);
    }

    if (properties.get(Crystal.CRYSTAL_COEFCALC) == null) {
      coefCalc = new CoefCalcAverage();
    } else {
      coefCalc = (CoefCalc) properties.get(Crystal.CRYSTAL_COEFCALC);
    }

    if (properties.get(Crystal.CRYSTAL_CONTAINER) == null) {
      sampleContainer = new ContainerTransparent();
    } else {
      sampleContainer = (Container) properties.get(Crystal.CRYSTAL_CONTAINER);
    }
// Get whether to calculate the escapes
    String pEE = (String) properties.get(CRYSTAL_ELECTRON_ESCAPE);
    if (pEE != null) {
      pEE = pEE.toUpperCase();
    }
    photoElectronEscape = ("TRUE".equals(pEE));
    
    String fE = (String) properties.get(CRYSTAL_FLUORESCENT_ESCAPE);
    if (fE != null) {
      fE = fE.toUpperCase();
    }
    fluorescentEscape = ("TRUE".equals(fE));
    
    //Get the goniometer axis
    String goniometer =  String.valueOf(properties.get(CRYSTAL_GONIOMETER_AXIS));
    verticalGoniometer = ("90.0".equals(goniometer)); //so horizontal is default 
    
    String program = (String) properties.get(CRYSTAL_PROGRAM);
    if (program != null) {
      program = program.toUpperCase().trim();
      subprogram = program;
    }
    else {
      subprogram = "RD3D";
    }
    

    //Check that ppm is sensible
    if ((properties.get(CRYSTAL_RESOLUTION) != null) && (properties.get(CRYSTAL_DIM_X) != null)) {
      isPPMSensible(properties); 
    }
    else { //set default resolution
      if ((properties.get(CRYSTAL_RESOLUTION) == null) && (properties.get(CRYSTAL_DIM_X) != null)) {
        CRYSTAL_RESOLUTION_DEF = 10 / ((double) properties.get(CRYSTAL_DIM_X));
        }  
    }
    
    crystalType = "CUBOID";
    XDim = (double) properties.get(Crystal.CRYSTAL_DIM_X); // in um
    try {
      YDim = (double) properties.get(Crystal.CRYSTAL_DIM_Y); // in um
    }
    catch (NullPointerException e){
      //then the crystal is spherical
      crystalType = "SPHERICAL";
      YDim = XDim;
      ZDim = XDim;
    }
    try {
      ZDim = (double) properties.get(Crystal.CRYSTAL_DIM_Z); // in um
    }
    catch (NullPointerException e){
      if (crystalType == "CUBOID") { //then it's a cylinder
        crystalType = "CYLINDER";
        ZDim = YDim;
        YDim = XDim;
      }
    }
  }
  
  /**
   * Warns the user if the pixels per micron is set too low
   * @param properties
   */
  public void isPPMSensible(final Map<Object, Object> properties) {
    double pixelSize = 1 / ((double) properties.get(CRYSTAL_RESOLUTION)); 
    double numberOfPixels = (((double) properties.get(CRYSTAL_DIM_X)) / pixelSize); 
    if (numberOfPixels < 8) {  //test this number properly it is just arbitrary for now
      System.out.println("WARNING: The pixels per micron is set too low so the output dose my not be accurate");
    }
  }

  public abstract void setupDepthFinding(double angrad, Wedge wedge);

  /**
   * This should take a set of xyz coordinates (a voxel coordinate), the current
   * orientation of the crystal, and the wedge we are exposing and return the
   * depth of the coordinate in micrometres from the surface of the crystal
   * along the [0 0 1] unit vector.
   *
   * @param voxCoord coordinates of voxel
   * @param deltaPhi change in phi
   * @param myWedge Wedge object
   * @return depth
   */
  public abstract double findDepth(double[] voxCoord, double deltaPhi,
      Wedge myWedge);

  // TODO change the deltaphi from absolute to how far along
  // the wedge, since this is more general.

  /**
   * Return the coordinates, in micrometres from the origin (centre of
   * rotation and beam intercept) of voxel ijk.
   *
   * @param i i coord
   * @param j j coord
   * @param k k coord
   * @return crystal coordinates
   */
  public abstract double[] getCrystCoord(int i, int j, int k);
  public abstract double[] getCryoCrystCoord(int i, int j, int k);

  /**
   * returns TRUE if there is a crystal at the coordinates i, j, k.
   *
   * @param i i coord
   * @param j j coord
   * @param k k coord
   * @return TRUE if crystal present at coords
   */
  public abstract boolean isCrystalAt(int i, int j, int k);

  /**
   * Get the escape factor for a voxel in the crystal
   *
   * @param i i coord
   * @param j j coord
   * @param k k coord
   * @return escapeFactor at crystal coordinates x, y, z
   */
  public abstract double getEscapeFactor(int i, int j, int k);

  /**
   * Should increment the dose array element ijk by doseVox.
   *
   * @param i i coord
   * @param j j coord
   * @param k k coord
   * @param doseIncrease
   */
  public abstract void addDose(int i, int j, int k, double doseIncrease);

  /**
   * Should increment the dose array element ijk by doseVox.
   * This accounts for PE energy transfer to nearby voxels.
   *
   * @param i i coord
   * @param j j coord
   * @param k k coord
   * @param doseIncreasePE
   * @return voxel dose lost from crystal by PE escape
   */
  public abstract double addDoseAfterPE(int i, int j, int k, double doseIncreasePE);
  public abstract double addDoseAfterPECryo(double i, double j, double k, double doseIncreasePE, double energyToDoseFactor);
  
  public abstract double trackPhotoelectron(int i, int j, int k, double doseIncreasePE, CoefCalc coefCalc, 
                                            Map<Element, Double> elementAbsorptionProbs, Map<Element, double[]> ionisationProbs, double[] angularEmissionProbs,
                                            Beam beam, boolean surrounding);
  
  /**
   * This accounts for FL energy transfer to nearby voxels and caluclates release
   * 
   * @param i
   * @param j
   * @param k
   * @param doseIncreaseFL
   * @return voxel dose lost from crystal by fluorescence escape
   */
  public abstract double addDoseAfterFL(int i, int j, int k, double doseIncreaseFL);

  /**
   * set new photoelectron trajectory parameters for current beam
   *
   * @param beamEnergy
   */
  public abstract void setPEparamsForCurrentBeam(double beamEnergy, CoefCalc coefCalc, double[][] feFactors);
  public abstract void setFLparamsForCurrentBeam(final double[][] feFactors);
  public abstract void setCryoPEparamsForCurrentBeam(Beam beam, CoefCalc coefCalc, double[][] feFactors);
  
  public abstract void startMicroED(double XDim, double YDim, double ZDim, Beam beam, Wedge wedge, CoefCalc coefCalc, String crystalType);
  public abstract void startXFEL(double XDim, double YDim, double ZDim, Beam beam, Wedge wedge, CoefCalc coefCalc);

  
  /**
   * finds the voxels that the bins on the tracks are in
   * @param cryo
   * @param coefCalc
   * @param energy
   * @param feFactors
   * @param angle
   */
  
  public abstract void findVoxelsReachedByPE(boolean cryo, CoefCalc coefCalc, final double energy, double[][] feFactors, final double angle);
  /**
   * Should increment the fluence array element ijk by fluenceVox.
   *
   * @param i i coord
   * @param j j coord
   * @param k k coord
   * @param fluenceIncrease
   */
  public abstract void addFluence(int i, int j, int k, double fluenceIncrease);

  /**
   * Should increment the elastic yield array element ijk by fluenceVox.
   *
   * @param i i coord
   * @param j j coord
   * @param k k coord
   * @param elasticIncrease
   */
  public abstract void addElastic(int i, int j, int k, double elasticIncrease);

  /**
   * Return a description of at least crystal size, initial orientation, and the
   * voxel resolution.
   */
  public abstract String crystalInfo();

  /**
   * Return the size of the bounding box of the crystal in voxels.
   */
  public abstract int[] getCrystSizeVoxels();
  public abstract int[] getCryoCrystSizeVoxels();

  /**
   * Return the size of the bounding box of the crystal in um.
   */
  public abstract double[] getCrystSizeUM();
  
  public abstract int getCryoExtraVoxels();

  /**
   * Return the dose at voxel ijk in MGy.
   *
   * @param i i coord
   * @param j j coord
   * @param k k coord
   */
  public abstract double getDose(int i, int j, int k);

  /**
   * Return the fluence at voxel ijk.
   *
   * @param i i coord
   * @param j j coord
   * @param k k coord
   */
  public abstract double getFluence(int i, int j, int k);

  /**
   * Return the total elastic scattering from voxel ijk.
   *
   * @param i i coord
   * @param j j coord
   * @param k k coord
   */
  public abstract double getElastic(int i, int j, int k);

  /**
   * Return the resolution of the crystal in 1/um.
   * @return resolution of crystal in 1 / um.
   */
  public abstract double getCrystalPixPerUM();
  
  public abstract double getCryoCrystalPixPerUM();
  
  public abstract int getExtraVoxels(int maxPEDistance, double pixelsPerMicron);

  /**
   * Return the coefCalc object that is being used to calculate coefficients.
   *
   * @return
   *         CoefCalc object.
   */
  public final CoefCalc getCoefCalc() {
    return coefCalc;
  }

  /**
   * Retrieve the DoseDecayModel object of the crystal.
   *
   * @return
   *         DoseDecayModel object.
   */
  public final DDM getDDM() {
    return ddm;
  }

  /**
   * Register an observer for crystal exposures.
   * If the observer has already been registered it will not be registered
   * again.
   *
   * @param e
   *          The observer class to be registered
   */
  public void addObserver(final ExposeObserver e) {
    if (!exposureObservers.contains(e)) {
      exposureObservers.add(e);
      e.register(this);
    }
  }
  
  /**
   * Calculates the general Auger energy at each angle that is later applied to each voxel using number of photons
   * 
   * @param fluorescentEscapeFactors
   * @return auger energy
   */
  public double getAugerEnergy(double[][] fluorescentEscapeFactors) {
    //Similar to the fluorescence energy below. Copy and pasting a bit so can make it tidier
    //Gets the Auger energy without number of photons
    double augerEnergy = 0;
    for (int i = 0; i < fluorescentEscapeFactors.length; i++){   //loops over each atom type
     double muratio = fluorescentEscapeFactors[i][0]; // uj/upe
     double K1 = fluorescentEscapeFactors[i][1] * fluorescentEscapeFactors[i][2] * (1-fluorescentEscapeFactors[i][3]); 
     /*
     double L1 = fluorescentEscapeFactors[i][5] * fluorescentEscapeFactors[i][6] * (1-fluorescentEscapeFactors[i][7]);
     double L2 = fluorescentEscapeFactors[i][9] * fluorescentEscapeFactors[i][10] * (1-fluorescentEscapeFactors[i][11]);
     double L3 = fluorescentEscapeFactors[i][13] * fluorescentEscapeFactors[i][14] * (1-fluorescentEscapeFactors[i][15]);
     */
  //   augerEnergy += (K1 + L1 + L2 + L3) * muratio;
     augerEnergy += K1 * muratio;
    }
    augerEnergy = augerEnergy * Beam.KEVTOJOULES;
    return augerEnergy;
  }
  
  /**
   * Gets the energy of each fluorescent event at a given angle
   * 
   * @param fluorescentEscapeFactors
   */
  public void getFluorescenceEnergyPerEvent(double[][] fluorescentEscapeFactors) {
    int length = fluorescentEscapeFactors.length;
  // fluorescenceEnergyPerEvent = new double[length][4];
   fluorescenceEnergyPerEvent = new double[length];
  for (int i = 0; i < length; i++){    //loops over each atom type
    double muratio = fluorescentEscapeFactors[i][0]; // uj/upe
  //j-shell ionization x j-shell fluorescence yield x j-edge energy
   double K1 = fluorescentEscapeFactors[i][1] * fluorescentEscapeFactors[i][2]*fluorescentEscapeFactors[i][3];
   /*
   double L1 = fluorescentEscapeFactors[i][5] * fluorescentEscapeFactors[i][6]*fluorescentEscapeFactors[i][7];
   double L2 = fluorescentEscapeFactors[i][9] * fluorescentEscapeFactors[i][10]*fluorescentEscapeFactors[i][11];
   double L3 = fluorescentEscapeFactors[i][13] * fluorescentEscapeFactors[i][14]*fluorescentEscapeFactors[i][15];
   */
    //calculate energy per event
   /*
    fluorescenceEnergyPerEvent[i][0] = K1 * muratio * Beam.KEVTOJOULES;
    fluorescenceEnergyPerEvent[i][1] = L1 * muratio * Beam.KEVTOJOULES;
    fluorescenceEnergyPerEvent[i][2] = L2 * muratio * Beam.KEVTOJOULES;
    fluorescenceEnergyPerEvent[i][3] = L3 * muratio * Beam.KEVTOJOULES;
    */
   fluorescenceEnergyPerEvent[i] = K1 * muratio * Beam.KEVTOJOULES;
  }
  
  }
  /**
   * Calculates the energy term for fluorescent energy that must be sent out. The number of photons is included when
   * individual voxels are looked at
   * 
   * @param beam
   * @param fluorescentEscapeFactors
   * @return the total fluorescent energy term
   */
// 
  public double calcFluorescence(final Beam beam, double[][] fluorescentEscapeFactors) {
    //Could probably do this in CofCalcsCompute to avoid looping again
   // double[][] fluorescentEscapeFactors = coefCalc.getFluorescentEscapeFactors(beam);
    double length = fluorescentEscapeFactors.length;
    double fluorescentEnergyToRelease = 0;
  for (int i = 0; i < length; i++){    //loops over each atom type
    double muratio = fluorescentEscapeFactors[i][0]; // uj/upe
    //j-shell ionization x j-shell fluorescence yield x j-edge energy
    double K1 = fluorescentEscapeFactors[i][1]*fluorescentEscapeFactors[i][2]*fluorescentEscapeFactors[i][3]; 
  /*  
    double L1 = fluorescentEscapeFactors[i][5]*fluorescentEscapeFactors[i][6]*fluorescentEscapeFactors[i][7];
    double L2 = fluorescentEscapeFactors[i][9]*fluorescentEscapeFactors[i][10]*fluorescentEscapeFactors[i][11];
    double L3 = fluorescentEscapeFactors[i][13]*fluorescentEscapeFactors[i][14]*fluorescentEscapeFactors[i][15];
    */
    
   // fluorescentEnergyToRelease = fluorescentEnergyToRelease + ((K1 + L1 + L2 + L3) * muratio);     //the units are kEV
    fluorescentEnergyToRelease = fluorescentEnergyToRelease + (K1 * muratio);     //the units are kEV
    
    }
  //Convert keV to Joules
  fluorescentEnergyToRelease = fluorescentEnergyToRelease * Beam.KEVTOJOULES; 
  return fluorescentEnergyToRelease;
  }

  /**
   * Calculates the average amount of photoelectron binding energy so this can be subtracted from their energy
   * @param feFactors
   */
  public void calculatePEEnergySubtraction(double[][] feFactors, boolean cryo) {
    double totK = 0, totL1 = 0, totL2 = 0, totL3 = 0;
    double totM1 = 0, totM2 = 0, totM3 = 0, totM4 = 0, totM5 = 0;
    
    for (int i = 0; i < feFactors.length; i++) { // for every element 
      totK += feFactors[i][0] * feFactors[i][1] * feFactors[i][2];
      totL1 += feFactors[i][0] * feFactors[i][5] * feFactors[i][6];
      totL2 += feFactors[i][0] * feFactors[i][9] * feFactors[i][10];
      totL3 += feFactors[i][0] * feFactors[i][13] * feFactors[i][14];
      
      //Add the Ms here for uranium
      
      totM1 += feFactors[i][0] * feFactors[i][17] * feFactors[i][18];
      totM2 += feFactors[i][0] * feFactors[i][19] * feFactors[i][20];
      totM3 += feFactors[i][0] * feFactors[i][21] * feFactors[i][22];
      totM4 += feFactors[i][0] * feFactors[i][23] * feFactors[i][24];
      totM5 += feFactors[i][0] * feFactors[i][25] * feFactors[i][26];
      
    }

    if (cryo == false) { //is this being called for the crystal or the cryo-solution
      EnergyToSubtractFromPE = totK + totL1 + totL2 + totL3 + totM1 + totM2 + totM3 + totM4 + totM5;
      
    }
    else {
      cryoEnergyToSubtractFromPE = totK + totL1 + totL2 + totL3 + totM1 + totM2 + totM3 + totM4 + totM5;
  //    System.out.print("test");
    }
  }
  
  /**
   * Set up the photoelectrons produced in the surrounding
   * @param beam
   * @param cryoFeFactors
   */
  public void calculateCryoSolutionParameters(final Beam beam, double[][] cryoFeFactors) {
    calculatePEEnergySubtraction(cryoFeFactors, true);
    setCryoPEparamsForCurrentBeam(beam, coefCalc, cryoFeFactors); 
  }
  
  /**
   * Expose this crystal to a given beam according to a strategy.
   *
   * @param beam
   *          Beam object describing the used beam.
   * @param wedge
   *          Wedge object describing the exposure strategy including
   *          translational and rotational information.
   */
  public void expose(final Beam beam, final Wedge wedge) {
    //start XFEL here, just comment and uncomment for now
    coefCalc.updateCoefficients(beam);
    
    if (subprogram.equals("XFEL")) {
      startXFEL(XDim, YDim, ZDim, beam, wedge, coefCalc);
    }
    else if (subprogram.equals("EMSP") || subprogram.equals("EMED")){
      startMicroED(XDim, YDim, ZDim, beam, wedge, coefCalc, crystalType);
    }
    else {
  
    double fluorescenceEnergyRelease = 0;
    double augerEnergy = 0;   
    double cryoAugerEnergy = 0;
    double cryoFluorescenceEnergyRelease = 0;
    double[][] cryoFeFactors = null;
    // Update coefficients in case the beam energy has changed.
    coefCalc.updateCoefficients(beam);

    //Set up PE and FE - no need to do this is PE false
    double[][] feFactors = coefCalc.getFluorescentEscapeFactors(beam); 
    Map<Element, Double> elementAbsorptionProbs = null;
    Map<Element, double[]> ionisationProbs = null;
    double[] angularEmissionProbs = null;
    if (photoElectronEscape) {
      //Calculate PE electron binding energy subtraction
      
    calculatePEEnergySubtraction(feFactors, false); 
    setPEparamsForCurrentBeam(beam.getPhotonEnergy(), coefCalc, feFactors); 
    
    //change this stuff for the Monte carlo methos to element absorption probs and angular distribution
    //populate the relative element cross sections here 
    elementAbsorptionProbs = coefCalc.getPhotoElectricProbsElement(beam.getPhotonEnergy());
    //populate the relative shell cross sections
    ionisationProbs = getRelativeShellProbs(elementAbsorptionProbs, beam.getPhotonEnergy());
    //populate the angular emission probs
    angularEmissionProbs = getAngularEmissionProbs();
    //elastic electron angle setup
    coefCalc.populateCrossSectionCoefficients();
    
    //Calc Auger
    augerEnergy = getAugerEnergy(feFactors);

    }
   if (fluorescentEscape) {
    //Calc % energy contribution of each event
 
    getFluorescenceEnergyPerEvent(feFactors);
    fluorescenceEnergyRelease = calcFluorescence(beam, feFactors);
   // fluorescenceProportionEvent = new double[feFactors.length][4];
    fluorescenceProportionEvent = new double[feFactors.length];
    for (int m = 0; m < feFactors.length; m++) {
     
    //  fluorescenceProportionEvent[m][0] = fluorescenceEnergyPerEvent[m][0] / fluorescenceEnergyRelease; //K
    //  fluorescenceProportionEvent[m][1] = fluorescenceEnergyPerEvent[m][1] / fluorescenceEnergyRelease; //L1
    //  fluorescenceProportionEvent[m][2] = fluorescenceEnergyPerEvent[m][2] / fluorescenceEnergyRelease; //L2
    //  fluorescenceProportionEvent[m][3] = fluorescenceEnergyPerEvent[m][3] / fluorescenceEnergyRelease; //L3
      
      fluorescenceProportionEvent[m] = fluorescenceEnergyPerEvent[m] / fluorescenceEnergyRelease; //K
    }

    setFLparamsForCurrentBeam(feFactors);
    
    }

   if (coefCalc.isCryo() == true && photoElectronEscape == true){
     coefCalc.updateCryoCoefficients(beam);
     cryoFeFactors = coefCalc.getCryoFluorescentEscapeFactors(beam);
     calculateCryoSolutionParameters(beam, cryoFeFactors); //crystal and everhting set here 
     cryoAugerEnergy = getAugerEnergy(cryoFeFactors);
       if (fluorescentEscape) {
         cryoFluorescenceEnergyRelease = calcFluorescence(beam, cryoFeFactors);
       }
   }
   
    //Calculate the attenuation due to the sample container
    sampleContainer.calculateContainerAttenuation(beam);
    //Print information about the attenuation to the console.
    sampleContainer.containerInformation();
    
    //Apply the attenuation of the container to the beam
    beam.applyContainerAttenuation(sampleContainer);

    //Generate beam array.
    //NOTE: this only does anything for the experimental beam class.
    //The beam implementation should change so that an array is an instance property
    //for each type of beam.
    beam.generateBeamArray();

    // Set up angles to iterate over.
    double[] angles;
    if (Math.abs(wedge.getStartAng() - wedge.getEndAng()) < wedge.getAngRes()) {
      angles = new double[STATICEXPOSURE]; // TODO: something clever
      for (int i = 0; i < angles.length; i++) {
        angles[i] = wedge.getStartAng();
      }
    } else {
      Integer sign = 1;
      if (wedge.getEndAng() < wedge.getStartAng()) {
        sign = -1;
      }
      angles = new double[sign
          * (int) ((wedge.getEndAng() - wedge.getStartAng())
              / wedge.getAngRes() + 1)];
      for (int i = 0; i < angles.length; i++) {
        angles[i] = wedge.getStartAng() + sign * i * wedge.getAngRes();
      }
    }

    for (ExposeObserver eo : exposureObservers) {
      eo.exposureStart(angles.length);
    }

    // The main meat of it:
    for (int n = 0; n < angles.length; n++) {
      // Expose one angle
      exposeAngle(angles[n], beam, wedge, n, angles.length, fluorescenceEnergyRelease, 
                  augerEnergy, cryoAugerEnergy, cryoFluorescenceEnergyRelease, feFactors, cryoFeFactors,
                  elementAbsorptionProbs, ionisationProbs, angularEmissionProbs);

      for (ExposeObserver eo : exposureObservers) {
        eo.imageComplete(n, angles[n]);
      }

    } // end of looping over angles

    double fractionEscapedDose = (totalEscapedDose - totalDoseFromSurrounding)/totalCrystalDose; //Just to test escaped dose
    
    final double voxelMassKg =  (1e-15 * (Math.pow(getCrystalPixPerUM(), -3) * coefCalc
        .getDensity()));
    
    for (int i = 0; i < getCrystSizeVoxels()[0]; i++) {
      for (int j = 0; j < getCrystSizeVoxels()[1]; j++) {
        for (int k = 0; k < getCrystSizeVoxels()[2]; k++) {
          if (isCrystalAt(i, j, k)) {
            for (ExposeObserver eo : exposureObservers) {
              eo.summaryObservation(i, j, k, getDose(i, j, k), voxelMassKg);
            }
          }
        }
      }
    }

    for (ExposeObserver eo : exposureObservers) {
      eo.exposureComplete();
    }
    
    if (photoElectronEscape) {
      System.out.print(String.format("\nEnergy that may escape by Photoelectron Escape: %.2e", totalEscapedDosePE));
      System.out.println(" J.\n");
    }
    if (fluorescentEscape) {
      System.out.print(String.format("Total energy that may escape by Fluorescent Escape: %.2e", totalEscapedDoseFL));
      System.out.println(" J.\n");
    }
    
    
  } // this the the end of the if not electrons statement 

    ///////////////////////////////////////////////////////
    // END OF EXPOSE METHOD
    ///////////////////////////////////////////////////////
  }

  private void exposeAngle(final double angle, final Beam beam,
      final Wedge wedge, final int anglenum, final int anglecount,  
      double fluorescenceEnergyRelease, double augerEnergy, double cryoAugerEnergy,
      double cryoFluorescenceEnergyRelease, double[][] feFactors, double[][] cryoFeFactors,
      Map<Element, Double> elementAbsorptionProbs, Map<Element, double[]> ionisationProbs, double[] angularEmissionProbs) {
    
    final int[] crystalSize = getCrystSizeVoxels();

    final Double[] wedgeStart = wedge.getStartVector();
    final Double[] wedgeTranslation = wedge.getTranslationVector(angle);

    final double anglecos = Math.cos(angle);
    final double anglesin = Math.sin(angle);
    setupDepthFinding(angle, wedge);
    
    
    //Set up tracks in polarised direction for photoelectrons
    if (photoElectronEscape) {
      
      findVoxelsReachedByPE(false, coefCalc, beam.getPhotonEnergy(), feFactors, angle);
      if (coefCalc.isCryo() == true) {
        findVoxelsReachedByPE(true, coefCalc, beam.getPhotonEnergy(), cryoFeFactors, angle);
      }
      
    }
    
    double energyPerFluence =
        1 - Math.exp(-1 * coefCalc.getAbsorptionCoefficient()
            / getCrystalPixPerUM());
    // absorption of the beam by a voxel
    
        final double fluenceToDoseFactorCompton = -1
            * Math.expm1(-1 * coefCalc.getInelasticCoefficient()
                / getCrystalPixPerUM())
            // exposure for the Voxel (J) * fraction absorbed by voxel
            / (1e-15 * (Math.pow(getCrystalPixPerUM(), -3) * coefCalc
                .getDensity()))
            // Voxel mass: 1um^3/1m/ml
            // (= 1e-18/1e3) / [volume (um^-3) *density (g/ml)]
            * 1e-6; // MGy
            //

     double fluenceToDoseFactor = -1
        * Math.expm1(-1 * coefCalc.getAbsorptionCoefficient()
            / getCrystalPixPerUM())
        // exposure for the Voxel (J) * fraction absorbed by voxel
        / (1e-15 * (Math.pow(getCrystalPixPerUM(), -3) * coefCalc
            .getDensity()))
        // Voxel mass: 1um^3/1m/ml
        // (= 1e-18/1e3) / [volume (um^-3) *density (g/ml)]
        * 1e-6; // MGy

    final double fluenceToElasticFactor = -1
        * Math.expm1(-1 * coefCalc.getElasticCoefficient()
        / getCrystalPixPerUM())
        // exposure for the Voxel (J) * fraction scattered by the voxel
        //   = J scattered
        / (beam.getPhotonEnergy() * Beam.KEVTOJOULES);
        // J scattered / [(keV/photon) / (J/keV)] = photons scattered

    double beamAttenuationFactor = Math.pow(getCrystalPixPerUM(), -2)
        * wedge.getTotSec() / anglecount;
    // Area in um^2 of a voxel * time per angular step

    final double beamAttenuationExpFactor = -coefCalc     
        .getAttenuationCoefficient();
    
    double[] crystCoords;
    double[] translateRotateCoords = new double[3];
    
    double[][][] voxImageFluence = new double[crystalSize[0]][crystalSize[1]][crystalSize[2]];
    double[][][] voxImageDose = new double[crystalSize[0]][crystalSize[1]][crystalSize[2]];
    double[][][] absorbedEnergy = new double[crystalSize[0]][crystalSize[1]][crystalSize[2]];
    double[][][] voxElasticYield = new double[crystalSize[0]][crystalSize[1]][crystalSize[2]];
    double[][][] voxImageComptonFluence = new double[crystalSize[0]][crystalSize[1]][crystalSize[2]];
    
    for (int i = 0; i < crystalSize[0]; i++) {
      for (int j = 0; j < crystalSize[1]; j++) {
        for (int k = 0; k < crystalSize[2]; k++) {
          if (isCrystalAt(i, j, k)) {
            // Rotate crystal into position
            crystCoords = getCrystCoord(i, j, k);
            translateRotateCoords = translateCrystalToPosition(crystCoords, wedgeStart, wedgeTranslation,
                                                               anglecos, anglesin) ;
            
            
            //here is where microED and Synchrotron really need to diverge. It will be easy to treat
            //as one voxel to start but may need to change this at a later date - look at spread in CASINO 
            //for now the rotation just needs to look at the surface area of the crystal then 
            //so yeah stick with TopHat for now 
            
            //Could update stopping power with pixel depth
            //My planned additional fudge factor would simply need to be applied to every voxel
            
            
            /* Unattenuated beam intensity (J/um^2/s) */
            double unattenuatedBeamIntensity = beam.beamIntensity(
                translateRotateCoords[0], translateRotateCoords[1],
                wedge.getOffAxisUm());

            if (unattenuatedBeamIntensity > 0d) {
              double depth = findDepth(translateRotateCoords, angle, wedge);

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
              double beamenergy = (beam.getPhotonEnergy() * Beam.KEVTOJOULES);
              double mcsquared = electronweight * csquared;
              double voxImageElectronEnergyDose = mcsquared / (2*beamenergy + mcsquared);
              voxImageElectronEnergyDose = (beamenergy * (1 - (Math.pow(voxImageElectronEnergyDose, 0.5)))); //Compton electron energy in joules
              double numberofphotons = voxImageFluence[i][j][k] / beamenergy; //This gives I0 in equation 9 in Karthik 2010, dividing by beam energy leaves photons per um^2/s
              voxImageComptonFluence[i][j][k] = numberofphotons * voxImageElectronEnergyDose; //Re-calculate voxImageFluence using Compton electron energy
              double voxImageDoseCompton = fluenceToDoseFactorCompton * voxImageComptonFluence[i][j][k];
              
              //elastic yield
              voxElasticYield[i][j][k] = fluenceToElasticFactor *
                  voxImageFluence[i][j][k]; //* beamEnergy;
              //Dose absorbed by photoelectric effect
              voxImageDose[i][j][k] = fluenceToDoseFactor * voxImageFluence[i][j][k];

              if (voxImageDose[i][j][k] > 0) {
                totalCrystalDose += voxImageDose[i][j][k];

                addFluence(i, j, k, voxImageFluence[i][j][k]);
                
                if (photoElectronEscape == true && fluorescentEscape == true) {
                 //Fl part
                //Energy to be released by voxel
                  double totFluorescenceEnergyRelease = fluorescenceEnergyRelease * numberofphotons;
                //convert this to a dose to be released
                  double voxImageFlDoseRelease = fluenceToDoseFactor * totFluorescenceEnergyRelease;
                                  
                  //auger part
                  //Dose in voxel
                  double totAugerDose = augerEnergy * numberofphotons * fluenceToDoseFactor;
                  
                  //Do PE
             //     double dosePE = voxImageDose[i][j][k] - voxImageFlDoseRelease - totAugerDose;
                  double dosePE = voxImageDose[i][j][k] - (EnergyToSubtractFromPE/beam.getPhotonEnergy())*voxImageDose[i][j][k];
                   
                  double doseLostFromCrystalPE = addDoseAfterPE(i, j, k, dosePE); //to run with new photoelectron escape
                  
                  double doseLostFromCrytsalFL = 0;
                  if (voxImageFlDoseRelease > 0) { //necessary to prevent error when 0
                  doseLostFromCrytsalFL = addDoseAfterFL(i, j, k, voxImageFlDoseRelease);
                  }
                  totalEscapedDosePE +=  doseLostFromCrystalPE;
                  totalEscapedDoseFL += doseLostFromCrytsalFL;
                  totalEscapedDose += doseLostFromCrystalPE + doseLostFromCrytsalFL;
                  
                  //These to test
                  totalFlEnergyToRelease += voxImageFlDoseRelease;
                  totalPEEnergyToRelease += dosePE;
                  totalAugerEnergyToRelease += totAugerDose;
                  
                  //add Auger to Voxel
                  if(totAugerDose > 0) {
                  addDose(i, j, k, totAugerDose);
                  }
                } 
                else if (photoElectronEscape == true && fluorescentEscape == false) { //only do PE escape
                  //Dose in voxel
                  double totAugerDose = augerEnergy * numberofphotons * fluenceToDoseFactor;
                  //Do PE
                //  double dosePE = voxImageDose[i][j][k] - totAugerDose; //change this to binding energy fraction
                  double dosePE = voxImageDose[i][j][k] - (EnergyToSubtractFromPE/beam.getPhotonEnergy())*voxImageDose[i][j][k];
                  double doseLostFromCrystalPE = addDoseAfterPE(i, j, k, dosePE);
                  
            //      double doseLostFromCrystalPE = trackPhotoelectron(i, j, k, dosePE, coefCalc, elementAbsorptionProbs, ionisationProbs, angularEmissionProbs, beam, false);
                  
                  totalEscapedDosePE +=  doseLostFromCrystalPE;
                  totalEscapedDose += doseLostFromCrystalPE;
                  //add Auger to Voxel
                  if(totAugerDose > 0) {
                  addDose(i, j, k, totAugerDose);
                  }
                }
                else if (photoElectronEscape == false && fluorescentEscape == true) { //only do Fluorescent escape
                  //Energy to be released by voxel
                  double totFluorescenceEnergyRelease = fluorescenceEnergyRelease * numberofphotons;
                //convert this to a dose to be released
                  double voxImageFlDoseRelease = fluenceToDoseFactor * totFluorescenceEnergyRelease;
                  double doseLeft = voxImageDose[i][j][k] - voxImageFlDoseRelease;
                  double doseLostFromCrytsalFL = 0;
                  if (voxImageFlDoseRelease > 0) {
                  doseLostFromCrytsalFL = addDoseAfterFL(i, j, k, voxImageFlDoseRelease);
                  }
                  addDose(i, j, k, doseLeft);
                  totalEscapedDoseFL += doseLostFromCrytsalFL;
                  totalEscapedDose +=  doseLostFromCrytsalFL;
                  
                }
                else { // no escape
                  addDose(i, j, k, voxImageDose[i][j][k]);
                }

                addDose(i, j, k, voxImageDoseCompton);
                addElastic(i, j, k, voxElasticYield[i][j][k]);

              } else if (voxImageDose[i][j][k] < 0) {
                throw new ArithmeticException(
                    "negative dose encountered - this should never happen");
              }
            } // IF inbeam
          } // IF crystOcc
        } //i
      } // j
    } // k : end of looping over crystal voxels
    
  
  //Now I am exposing the surrounding solution   
  boolean aSurface = coefCalc.isCryo(); //iscryo should change
  if (aSurface) {
    if (photoElectronEscape) {
      //loop through all the bigger crystal voxels
      //if it is close to surface in this big crystal (i.e outside the normal crystal) then expose it

      //change to reflect cryo solution    
      fluenceToDoseFactor = -1
          * Math.expm1(-1 * coefCalc.getCryoAbsorptionCoefficient()
              / getCryoCrystalPixPerUM())
          // exposure for the Voxel (J) * fraction absorbed by voxel
          / (1e-15 * (Math.pow(getCrystalPixPerUM(), -3) * coefCalc     //energy absorbed by cryo voxel converted to the dose of a crystal voxel
              .getDensity()))
          // Voxel mass: 1um^3/1m/ml
          // (= 1e-18/1e3) / [volume (um^-3) *density (g/ml)]
          * 1e-6; // MGy

      energyPerFluence =
          1 - Math.exp(-1 * coefCalc.getCryoAbsorptionCoefficient()
              / getCryoCrystalPixPerUM());
      // absorption of the beam by a voxel
      
      double energyToDoseFactor = (1e-15 * (Math.pow(getCrystalPixPerUM(), -3) * coefCalc     //energy absorbed by cryo voxel to dose in crystal voxel
          .getDensity()));
      
      beamAttenuationFactor = Math.pow(getCryoCrystalPixPerUM(), -2)
          * wedge.getTotSec() / anglecount;
      
      
      final int[] cryoCrystalSize = getCryoCrystSizeVoxels();
 //     final int extraVoxels = getExtraVoxels(int maxPEDistance);
      double[] cryoCrystCoord;
      double[] depthCoords = new double[3];
      double ppmRatio = (getCrystalPixPerUM() / getCryoCrystalPixPerUM());
      int extraVoxels = getCryoExtraVoxels();
      for (int i = 0; i < cryoCrystalSize[0]; i++) {
        for (int j = 0; j < cryoCrystalSize[1]; j++) {
          for (int k = 0; k < cryoCrystalSize[2]; k++) {
            //if this is an extra voxel
            
            double iCryst = (i - extraVoxels) * ppmRatio; 
            double jCryst = (j - extraVoxels) * ppmRatio;
            double kCryst = (k - extraVoxels) * ppmRatio;
            
            int iconverted = (int) StrictMath.round(iCryst); 
            int jconverted = (int) StrictMath.round(jCryst);
            int kconverted = (int) StrictMath.round(kCryst);
                    
            if (isCrystalAt(iconverted, jconverted, kconverted) == false) { // if this voxel is not in the original crystal
              cryoCrystCoord = getCryoCrystCoord(i, j, k);
              
              translateRotateCoords = translateCrystalToPosition(cryoCrystCoord, wedgeStart, wedgeTranslation,
                  anglecos, anglesin) ;

              // Unattenuated beam intensity (J/um^2/s) 
              double unattenuatedBeamIntensity = beam.beamIntensity(
                  translateRotateCoords[0], translateRotateCoords[1],
                  wedge.getOffAxisUm());
              
              if (unattenuatedBeamIntensity > 0d) {
                //Set the depth coordinates based on crystal depth
                for(int m = 0; m < 3; m++) {
                  if (translateRotateCoords[m] < minimumDimensions[m]) {
                    depthCoords[m] = minimumDimensions[m];
                  }
                  else if (translateRotateCoords[m] > maximumDimensions[m]) {
                    depthCoords[m] = maximumDimensions[m];
                  }
                  else {
                    depthCoords[m] = translateRotateCoords[m];
                  }
                }
                
                double depth = findDepth(depthCoords, angle, wedge);
                
                double cryoVoxImageFluence =     // Attenuates the beam for absorption in joules 
                    unattenuatedBeamIntensity * beamAttenuationFactor
                        * Math.exp(depth * beamAttenuationExpFactor); 
                //For Auger
                double beamEnergy = (beam.getPhotonEnergy() * Beam.KEVTOJOULES);
                double numberOfPhotons = cryoVoxImageFluence / beamEnergy;
                
                double cryoVoxImageEnergy = energyPerFluence * cryoVoxImageFluence; 
                double cryoVoxImageDose= fluenceToDoseFactor * cryoVoxImageFluence;
                                                                             
                if (cryoVoxImageEnergy > 0) {
            //    if (cryoVoxImageDose > 0) { 
                  double energyPE = 0;
                  double dosePE = 0;
                  double totCryoAugerEnergy = cryoAugerEnergy * numberOfPhotons * energyPerFluence;
                  double totCryoAugerDose = cryoAugerEnergy * numberOfPhotons * fluenceToDoseFactor;
                  if (fluorescentEscape == false) {
                    energyPE = cryoVoxImageEnergy - totCryoAugerEnergy;
                    dosePE = cryoVoxImageDose - totCryoAugerDose;
                  }
                  else {
                    double totCryoFluorescenceEnergyRelease = cryoFluorescenceEnergyRelease * numberOfPhotons;
                    //convert this to a dose to be released
                    double voxImageFlEnergyRelease = energyPerFluence * totCryoFluorescenceEnergyRelease;
                    double voxImageFlDoseRelease = fluenceToDoseFactor * totCryoFluorescenceEnergyRelease;
              //      energyPE = cryoVoxImageEnergy - totCryoAugerEnergy - voxImageFlEnergyRelease;
                    energyPE = cryoVoxImageEnergy - (cryoEnergyToSubtractFromPE/beam.getPhotonEnergy())*cryoVoxImageEnergy;
                  //  dosePE = cryoVoxImageDose - totCryoAugerDose - voxImageFlDoseRelease;
                    dosePE = cryoVoxImageDose - (cryoEnergyToSubtractFromPE/beam.getPhotonEnergy())*cryoVoxImageDose;
                  }
                  double doseAddedBack = addDoseAfterPECryo(iCryst, jCryst, kCryst, energyPE, energyToDoseFactor);
              //    double doseAddedBack = addDoseAfterPECryo(iconverted, jconverted, kconverted, dosePE, energyToDoseFactor);
                  totalDoseFromSurrounding += doseAddedBack;
                } // end if voximage dose > 0
              } // end if unattenuated beam intensity > 0
            } // end if crystal not at
          } //end k
        } // end j
      }  //end i
    } // end if pe true
  }//end if there is a surface
  
  //loop through all again for DWD - needs to go after cryo as well!!!
  for (int i = 0; i < crystalSize[0]; i++) {
    for (int j = 0; j < crystalSize[1]; j++) {
      for (int k = 0; k < crystalSize[2]; k++) {
        if (isCrystalAt(i, j, k)) {
          if (voxImageFluence[i][j][k] > 0) {
            double totalVoxelDose = getDose(i, j, k); //how can this be done before the whole crystal???
            //This may need to change - ask what this is
            double interpolatedVoxelDose = totalVoxelDose + voxImageDose[i][j][k] / 2; // this needs to change for PE escape
            double relativeDiffractionEfficiency =
                getDDM().calcDecay(interpolatedVoxelDose);
            
            // Fluence times the fraction of the beam absorbed by the voxel
            
//may need to pass in different things or pass in more and change in observer

            absorbedEnergy[i][j][k] = voxImageFluence[i][j][k] * energyPerFluence;
            double comptonabsorbedEnergy = voxImageComptonFluence[i][j][k] * energyPerFluence;
            
            absorbedEnergy[i][j][k] = absorbedEnergy[i][j][k] + comptonabsorbedEnergy;
 
         //   relativeDiffractionEfficiency = 1;
            for (ExposeObserver eo : exposureObservers) {
              eo.exposureObservation(anglenum, i, j, k, voxImageDose[i][j][k],   //voxImageDose should be added dose (doesn't do Compton or escape)
                  totalVoxelDose, voxImageFluence[i][j][k],
                  relativeDiffractionEfficiency, absorbedEnergy[i][j][k],
                  voxElasticYield[i][j][k], anglecount);
            }
          }
        }
      }
    }
  }
  }

  private double[] translateCrystalToPosition(double[] crystCoords, Double[] wedgeStart, Double[] wedgeTranslation,
                                               double anglecos, double anglesin) {
    double[] translateRotateCoords = new double[3];
    // Translate Y
    translateRotateCoords[1] = crystCoords[1]
        + wedgeStart[1] + wedgeTranslation[1];
    // Translate X
    double translateCoordX = crystCoords[0]
        + wedgeStart[0] + wedgeTranslation[0];
    // Translate Z
    double translateCoordZ = crystCoords[2]
        + wedgeStart[2] + wedgeTranslation[2];
    /* Rotate clockwise when y axis points away from observer */
    // Rotate X
    translateRotateCoords[0] = translateCoordX * anglecos
        + translateCoordZ * anglesin;
    // Rotate Z
    translateRotateCoords[2] = -1 * translateCoordX * anglesin
        + translateCoordZ * anglecos;
    
    return translateRotateCoords;
  }

  @SuppressWarnings("unused")
  @Deprecated
  private double fluenceToDose(final double voxFluence,
      final double crystalPixPerUM) {
    return voxFluence
        * -1
        * Math
            .expm1(-1 * coefCalc.getAbsorptionCoefficient() / crystalPixPerUM)
        // exposure for the Voxel (J) * fraction absorbed by voxel
        / (1e-15 * (Math.pow(crystalPixPerUM, -3) * coefCalc.getDensity()))
        // Voxel mass: 1um^3/1m/ml
        // (= 1e-18/1e3) / [volume (um^-3) *density (g/ml)]
        * 1e-6; // MGy
  }

  /**
   * Returns a common ExposureSummary object registered to this crystal.
   *
   * @return
   *         An ExposureSummary object that keeps a list of automatically
   *         generated metrics regarding exposures of this crystal.
   */
  public synchronized ExposureSummary getExposureSummary() {
    if (exposureSummaryObserver == null) {
      exposureSummaryObserver = new ExposureSummary();
      addObserver(exposureSummaryObserver);
    }
    return exposureSummaryObserver;
  }

  /**
   * This function recommends an experimental resolution (in voxels per
   * micrometres) for the cases where the user did not explicitly specify one.
   * A built-in default resolution is considered, but the total number of voxels
   * is limited.
   *
   * @param x
   *          crystal length in micrometres.
   * @param y
   *          crystal width in micrometres.
   * @param z
   *          crystal depth in micrometres.
   * @return
   *         Recommended resolution in voxels per micrometre.
   */
  public Double getDefaultLimitedResolution(final Double x, final Double y,
      final Double z) {

    if ((x * CRYSTAL_RESOLUTION_DEF)
        * (y * CRYSTAL_RESOLUTION_DEF)
        * (z * CRYSTAL_RESOLUTION_DEF)
        <= CRYSTAL_RESOLUTION_DEF_VOXLIM) {
      return CRYSTAL_RESOLUTION_DEF;
    }

    Double reductionFactor = CRYSTAL_RESOLUTION_DEF_VOXLIM / (x * y * z);
    reductionFactor = Math.pow(reductionFactor, 1d / 3d);
    return reductionFactor;
  }
  
  public void setMinMaxCrystalDimensions(double[] xMinMax, double[] yMinMax, double[] zMinMax) {
    xMinAndMax = xMinMax;
    yMinAndMax = yMinMax;
    zMinAndMax = zMinMax;
    minimumDimensions = new double[3];
    maximumDimensions = new double[3];
    minimumDimensions[0] = xMinMax[0];
    minimumDimensions[1] = yMinMax[0];
    minimumDimensions[2]=  zMinMax[0];
    maximumDimensions[0] = xMinMax[1];
    maximumDimensions[1] = yMinMax[1];
    maximumDimensions[2]=  zMinMax[1];
  }
  
  public boolean getPEEscapeBool() {
    return photoElectronEscape;
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
  
  private double[] getAngularEmissionProbs() {
    double[] angularEmissionProbs = new double[numberAngularEmissionBins];
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
    return angularEmissionProbs;
  }
  
  private double solvePolarisationEquationForAngle(double phi, double photoElectric, double beta) {
    double height = (photoElectric / (4*Math.PI)) * (1+(beta*0.5*(3*Math.pow(Math.cos(phi), 2) - 1)));
    return height;
  }
}
