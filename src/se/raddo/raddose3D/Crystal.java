package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.Arrays;
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
  
  public double EnergyToSubtractFromPE; 
  
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

    //Check that ppm is sensible
    if ((properties.get(CRYSTAL_RESOLUTION) != null) && (properties.get(CRYSTAL_DIM_X) != null)) {
      isPPMSensible(properties); 
    }
    else { //set default resolution
      if ((properties.get(CRYSTAL_RESOLUTION) == null) && (properties.get(CRYSTAL_DIM_X) != null)) {
        CRYSTAL_RESOLUTION_DEF = 10 / ((double) properties.get(CRYSTAL_DIM_X));
        }  
    }
  }
  
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
  
  /**
   * This accounts for FE energy transfer to nearby voxels and caluclates release
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
  public abstract void setPEparamsForCurrentBeam(double beamEnergy);
  public abstract void setFLparamsForCurrentBeam(final double[][] feFactors);
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

  /**
   * Return the size of the bounding box of the crystal in um.
   */
  public abstract double[] getCrystSizeUM();

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
   * Calculates the general Auger energy at eahc angle that is later applied to each voxel using number of photons
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

  public void calculatePEEnergySubtraction(double[][] feFactors) {
    double totK = 0, totL1 = 0, totL2 = 0, totL3 = 0;
    double totM1 = 0, totM2 = 0, totM3 = 0, totM4 = 0, totM5 = 0;
    
    for (int i = 0; i < feFactors.length; i++) {
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
    
  //  totK = 0; //to test
  //  totL1 = 0;
  //  totL2 = 0;
  //  totL3 = 0;
    /*
    System.out.println(totK); // to test
    System.out.println(totL1); // to test
    System.out.println(totL2); // to test
    System.out.println(totL3); // to test
    
    System.out.println(totM1); // to test
    System.out.println(totM2); // to test
    System.out.println(totM3); // to test
    System.out.println(totM4); // to test
    System.out.println(totM5); // to test
    */
    EnergyToSubtractFromPE = totK + totL1 + totL2 + totL3 + totM1 + totM2 + totM3 + totM4 + totM5;
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

    // Update coefficients in case the beam energy has changed.
    coefCalc.updateCoefficients(beam);
    double fluorescenceEnergyRelease = 0;
    double augerEnergy = 0;
    //Set up PE and FE - no need to do this is PE false
    double[][] feFactors = coefCalc.getFluorescentEscapeFactors(beam); 
    //Calculate PE electron binding energy subtraction
    calculatePEEnergySubtraction(feFactors); 
    
    if (photoElectronEscape) {
    setPEparamsForCurrentBeam(beam.getPhotonEnergy()); 
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
    /* 
      fluorescenceProportionEvent[m][0] = fluorescenceEnergyPerEvent[m][0] / fluorescenceEnergyRelease; //K
      fluorescenceProportionEvent[m][1] = fluorescenceEnergyPerEvent[m][1] / fluorescenceEnergyRelease; //L1
      fluorescenceProportionEvent[m][2] = fluorescenceEnergyPerEvent[m][2] / fluorescenceEnergyRelease; //L2
      fluorescenceProportionEvent[m][3] = fluorescenceEnergyPerEvent[m][3] / fluorescenceEnergyRelease; //L3
      */
      fluorescenceProportionEvent[m] = fluorescenceEnergyPerEvent[m] / fluorescenceEnergyRelease; //K
    }
    setFLparamsForCurrentBeam(feFactors);
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
      exposeAngle(angles[n], beam, wedge, n, angles.length, fluorescenceEnergyRelease, augerEnergy);

      for (ExposeObserver eo : exposureObservers) {
        eo.imageComplete(n, angles[n]);
      }

    } // end of looping over angles

    double fractionEscapedDose = totalEscapedDose/totalCrystalDose; //Just to test escaped dose
    
    for (int i = 0; i < getCrystSizeVoxels()[0]; i++) {
      for (int j = 0; j < getCrystSizeVoxels()[1]; j++) {
        for (int k = 0; k < getCrystSizeVoxels()[2]; k++) {
          if (isCrystalAt(i, j, k)) {
            for (ExposeObserver eo : exposureObservers) {
              eo.summaryObservation(i, j, k, getDose(i, j, k));
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

    ///////////////////////////////////////////////////////
    // END OF EXPOSE METHOD
    ///////////////////////////////////////////////////////
  }

  private void exposeAngle(final double angle, final Beam beam,
      final Wedge wedge, final int anglenum, final int anglecount,  double fluorescenceEnergyRelease, double augerEnergy) {

    final int[] crystalSize = getCrystSizeVoxels();

    final Double[] wedgeStart = wedge.getStartVector();
    final Double[] wedgeTranslation = wedge.getTranslationVector(angle);

    final double anglecos = Math.cos(angle);
    final double anglesin = Math.sin(angle);
    setupDepthFinding(angle, wedge);


    final double energyPerFluence =
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

    final double fluenceToDoseFactor = -1
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

    final double beamAttenuationFactor = Math.pow(getCrystalPixPerUM(), -2)
        * wedge.getTotSec() / anglecount;
    // Area in um^2 of a voxel * time per angular step

    final double beamAttenuationExpFactor = -coefCalc     
        .getAttenuationCoefficient();

    double[] crystCoords;
    double[] translateRotateCoords = new double[3];
    for (int i = 0; i < crystalSize[0]; i++) {
      for (int j = 0; j < crystalSize[1]; j++) {
        for (int k = 0; k < crystalSize[2]; k++) {
          if (isCrystalAt(i, j, k)) {
            // Rotate crystal into position
            crystCoords = getCrystCoord(i, j, k);

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


              double voxImageFluence =     // Attenuates the beam for absorption in joules 
                  unattenuatedBeamIntensity * beamAttenuationFactor
                      * Math.exp(depth * beamAttenuationExpFactor);              

              
              double electronweight = 9.10938356E-31;
              double csquared = 3E8*3E8;
              double beamenergy = (beam.getPhotonEnergy() * Beam.KEVTOJOULES);
              double mcsquared = electronweight * csquared;
              double voxImageElectronEnergyDose = mcsquared / (2*beamenergy + mcsquared);

              voxImageElectronEnergyDose = (beamenergy * (1 - (Math.pow(voxImageElectronEnergyDose, 0.5)))); //Compton electron energy in joules
              double numberofphotons = voxImageFluence / beamenergy; //This gives I0 in equation 9 in Karthik 2010, dividing by beam energy leaves photons per um^2/s
              double voxImageComptonFluence = numberofphotons * voxImageElectronEnergyDose; //Re-calculate voxImageFluence using Compton electron energy
              double voxImageDoseCompton = fluenceToDoseFactorCompton * voxImageComptonFluence;
              
              // MGy
              double voxElasticYield = fluenceToElasticFactor *
                  voxImageFluence; //* beamEnergy;

              double voxImageDose = fluenceToDoseFactor * voxImageFluence;

              if (voxImageDose > 0) {
                totalCrystalDose += voxImageDose;

                addFluence(i, j, k, voxImageFluence);
                
                if (photoElectronEscape == true && fluorescentEscape == true) {
                 //Fl part
                //Energy to be released by voxel
                  double totFluorescenceEnergyRelease = fluorescenceEnergyRelease * numberofphotons;
                //convert this to a dose to be released
                  double voxImageFlDoseRelease = fluenceToDoseFactor * totFluorescenceEnergyRelease;
                  
                  //TOTEST
                 //  voxImageFlDoseRelease = 0;
                  
                  //auger part
                  //Dose in voxel
                  double totAugerDose = augerEnergy * numberofphotons * fluenceToDoseFactor;
                  
                  //Do PE
                  double dosePE = voxImageDose - voxImageFlDoseRelease - totAugerDose;
                //  dosePE = 0;
                //TOTEST - Fl escape prob = 1
                  // voxImageFlDoseRelease = 0;
                   
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
                  //addFluorescence - this is old code used to test
              //    addDose(i, j, k, voxImageFlDoseRelease); //comment out as now sending along tracks
                  // add PE To test
                //  addDose(i, j, k, voxImageDose - voxImageFlDoseRelease);
                  
                } 
                else if (photoElectronEscape == true && fluorescentEscape == false) { //only do PE escape
                  //Dose in voxel
                  double totAugerDose = augerEnergy * numberofphotons * fluenceToDoseFactor;
                  //Do PE
                  double dosePE = voxImageDose - totAugerDose;
                  double doseLostFromCrystalPE = addDoseAfterPE(i, j, k, dosePE);
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
                  double doseLeft = voxImageDose - voxImageFlDoseRelease;
                  double doseLostFromCrytsalFL = 0;
                  if (voxImageFlDoseRelease > 0) {
                  doseLostFromCrytsalFL = addDoseAfterFL(i, j, k, voxImageFlDoseRelease);
                  }
                  addDose(i, j, k, doseLeft);
                  totalEscapedDoseFL += doseLostFromCrytsalFL;
                  totalEscapedDose +=  doseLostFromCrytsalFL;
                  
                }
                else { // no escape
                  addDose(i, j, k, voxImageDose);
                }

                addDose(i, j, k, voxImageDoseCompton);
                addElastic(i, j, k, voxElasticYield);

              } else if (voxImageDose < 0) {
                throw new ArithmeticException(
                    "negative dose encountered - this should never happen");
              }
//I MAY HAVE TO CHANGE SUMMIN HERE AS WELL????????? - WON'T SOLVE ISSUE BUT MAY NEED TO HAPPEN
              double totalVoxelDose = getDose(i, j, k); //how can this be done before the whole crystal???
              //This may need to change - ask what this is
              double interpolatedVoxelDose = totalVoxelDose + voxImageDose / 2;
              double relativeDiffractionEfficiency =
                  getDDM().calcDecay(interpolatedVoxelDose);

              // Fluence times the fraction of the beam absorbed by the voxel

//may need to pass in different things or pass in more and change in observer

              double absorbedEnergy = voxImageFluence * energyPerFluence;
              double comptonabsorbedEnergy = voxImageComptonFluence * energyPerFluence;
              
              absorbedEnergy = absorbedEnergy + comptonabsorbedEnergy;

              for (ExposeObserver eo : exposureObservers) {
                eo.exposureObservation(anglenum, i, j, k, voxImageDose,
                    totalVoxelDose, voxImageFluence,
                    relativeDiffractionEfficiency, absorbedEnergy,
                    voxElasticYield);
              }
            } // IF inbeam
          } // IF crystOcc
        } //i
      } // j
    } // k : end of looping over crystal voxels
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


}
