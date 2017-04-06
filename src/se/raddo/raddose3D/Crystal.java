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
  public static final String     CRYSTAL_ELECTRON_ESCAPE = "PHESCAPE";

  /** Default recommended voxel resolution in voxels/micrometre. */
  protected static final Double  CRYSTAL_RESOLUTION_DEF        = 0.5d;

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

  /**
   * Cumulative dose lost from crystal due to photoelectron escape
   */
  private double totalEscapedDose                               = 0;
  
  private double fluorescentEscapedDose                         = 0;

  /**
   * Cumulative dose both remaining in crystal and lost through photoelectron escape
   */
  private double totalCrystalDose                               = 0;
  
  /** The mass of each voxel in the crystal */
  private double voxelMass                                      = 0;

  /**
   * whether photoelectron escape should be included
   */
  private final boolean photoElectronEscape; 
  
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
    
    String pEE = (String) properties.get(CRYSTAL_ELECTRON_ESCAPE);
    photoElectronEscape = ("TRUE".equals(pEE));
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
   * @param doseIncrease
   * @return voxel dose lost from crystal
   */
  public abstract double addDoseAfterPE(int i, int j, int k, double doseIncrease);
  
  public abstract double addDoseAfterFL(int i, int j, int k, double doseIncrease);

  /**
   * set new photoelectron trajectory parameters for current beam
   *
   * @param beamEnergy
   */
  public abstract void setPEparamsForCurrentBeam(double beamEnergy);

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
    setPEparamsForCurrentBeam(beam.getPhotonEnergy());

    double[][] fluorEscapeFactors = coefCalc.getFluorescentEscapeFactors(beam);
    
    
   //Takes the fluorescent escape factors and calculates the energy that can escape by fluorescence
    double[][] fluorescentescapefactors = coefCalc.getFluorescentEscapeFactors(beam);
    double energy = beam.getPhotonEnergy();
    double length = fluorescentescapefactors.length;
    for (int i = 0; i < length; i++){    //loops over each atom type
      double muratio = fluorescentescapefactors[i][0]; // uj/upe
      double K1, L1, L2, L3;
      double K1px = fluorescentescapefactors[i][2]*fluorescentescapefactors[i][3]*fluorescentescapefactors[i][4]; //K-shell ionization x K-shell fluorescence yield x fluorescentX-ray escape probability
      double L1px = fluorescentescapefactors[i][6]*fluorescentescapefactors[i][7]*fluorescentescapefactors[i][8];
      double L2px = fluorescentescapefactors[i][10]*fluorescentescapefactors[i][11]*fluorescentescapefactors[i][12];
      double L3px = fluorescentescapefactors[i][14]*fluorescentescapefactors[i][15]*fluorescentescapefactors[i][16];
      K1 = fluorescentescapefactors[i][1] * K1px; // K1px is multiplied by the K1 edge energy
      L1 = fluorescentescapefactors[i][5] * L1px;
      L2 = fluorescentescapefactors[i][9] * L2px;
      L3 = fluorescentescapefactors[i][13] * L3px;
      energy = (energy - K1 - L1 - L2 - L3) * muratio; // beam energy minus K1, L1, L2 and L3 multipled my uj/upe
      fluorescentEscapedDose = fluorescentEscapedDose + energy; // Adds energy that can escape for each atom type
      }

    double beamEnergyInJoules = beam.getPhotonEnergy()
        * Beam.KEVTOJOULES;
    
    fluorescentEscapedDose = fluorescentEscapedDose * Beam.KEVTOJOULES;
    
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
      exposeAngle(angles[n], beam, wedge, n, angles.length,
          beamEnergyInJoules, fluorEscapeFactors);

      for (ExposeObserver eo : exposureObservers) {
        eo.imageComplete(n, angles[n]);
      }

    } // end of looping over angles

    double fractionEscapedDose = totalEscapedDose/totalCrystalDose; //!!!!!!!!!Whats this? This towards the end?
    
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
      System.out.println(String.format("\nEnergy that may escape by Photoelectron Escape: %.2e", fractionEscapedDose));
      System.out.print(String.format("Total energy that may escape by Fluorescent Escape: %.2e", fluorescentEscapedDose));
      System.out.println(" J.\n");
    }

    ///////////////////////////////////////////////////////
    // END OF EXPOSE METHOD
    ///////////////////////////////////////////////////////
  }

  private void exposeAngle(final double angle, final Beam beam,
      final Wedge wedge, final int anglenum, final int anglecount,
      final double beamEnergy, final double[][] fluorEscapeFactors) {

    final int[] crystalSize = getCrystSizeVoxels();

    final Double[] wedgeStart = wedge.getStartVector();
    final Double[] wedgeTranslation = wedge.getTranslationVector(angle);

    final double anglecos = Math.cos(angle);
    final double anglesin = Math.sin(angle);
    setupDepthFinding(angle, wedge);

    final double absorptionFraction =
        1 - Math.exp(-1 * coefCalc.getAbsorptionCoefficient()
            / getCrystalPixPerUM());
    // absorption fraction of the beam by a voxel
    
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

        // Calculate voxel mass (kg) = voxelVolume (um^3) * density (g/cm^3) *
        //                             unitConversionFactor
        voxelMass = UNIT_CONVERSION * (Math.pow(getCrystalPixPerUM(), -3) *
            coefCalc.getDensity());
                
    final double absorptionFractionPerKg =
        absorptionFraction / voxelMass * GY_TO_MGY;   

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
              double voxImageFluence =
                  (unattenuatedBeamIntensity) * beamAttenuationFactor
                      * Math.exp(depth * beamAttenuationExpFactor)
                      * (beamEnergy);// - fluorescentEscapedDose);
              // Attenuates the beam for absorption

              /*
               * I think that we need to reduce the voxImageEnergy due to 
               * X-ray fluorescence escape in the first line below this 
               * comment!
               * 
               * One other consideration is whether you put the code for
               * Energy reduction by X-ray fluorescence before the code to 
               * reduce the dose due to photoelectron escape (PE). This is 
               * because the inner shell electron has to be ejected before 
               * X-ray fluorescence takes place. Hence there's an argument
               * for doing PE first
               */
              
              double electronweight = 9.10938356E-31;
              double csquared = 3E8*3E8;
              double beamenergy = (beam.getPhotonEnergy() * Beam.KEVTOJOULES);
              double mcsquared = electronweight * csquared;
              double voxImageElectronEnergyDose = mcsquared / (2*beamenergy + mcsquared);
              voxImageElectronEnergyDose = (beamenergy * (1 - (Math.pow(voxImageElectronEnergyDose, 0.5)))); //Compton electron energy in joules
              
              double numberofphotons = voxImageFluence / beamenergy; //This gives I0 in equation 9 in Karthik 2010, dividing by beam energy leaves photons per um^2/s
              double voxImageEnergy = voxImageFluence;
              double voxImageComptonFluence = numberofphotons * voxImageElectronEnergyDose; //Re-calculate voxImageFluence using Compton electron energy
              double voxImageDoseCompton = fluenceToDoseFactorCompton * voxImageComptonFluence;
              
              double voxImageDose = absorptionFractionPerKg * voxImageEnergy;
              
              // MGy
              double voxElasticYield = fluenceToElasticFactor *
                  voxImageFluence; //* beamEnergy;
              
              if (voxImageDose > 0) {

                addFluence(i, j, k, voxImageFluence);
                
                if (photoElectronEscape) {  //This currently neglects energy lost due to binding energy - add this later
                  double doseLostFromCrystal = addDoseAfterPE(i, j, k, voxImageDose); //to run with new photoelectron escape
                  totalEscapedDose += doseLostFromCrystal;
                  totalCrystalDose += voxImageDose;
                } else {
                  addDose(i, j, k, voxImageDose);
                }
                addDose(i, j, k, voxImageDoseCompton);
                addElastic(i, j, k, voxElasticYield);
              } else if (voxImageDose < 0) {
                throw new ArithmeticException(
                    "negative dose encountered - this should never happen");
              }

              double totalVoxelDose = getDose(i, j, k);
              double interpolatedVoxelDose = totalVoxelDose + voxImageDose / 2;
              double relativeDiffractionEfficiency =
                  getDDM().calcDecay(interpolatedVoxelDose);

              // Fluence times the fraction of the beam absorbed by the voxel
              double absorbedEnergy = voxImageEnergy * absorptionFraction;

              for (ExposeObserver eo : exposureObservers) {
                eo.exposureObservation(anglenum, i, j, k, voxImageDose,
                    totalVoxelDose, voxImageEnergy,
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
