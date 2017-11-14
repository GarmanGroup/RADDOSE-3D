package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

/**
 * This class describes a spherical crystal, i.e. that is invariant under
 * rotation. It is most useful for theoretical considerations of radiation
 * damage and dose distribution.
 */
public class CrystalSpherical extends Crystal {
  /** Resolution of crystal in 1/um. */
  private final double         crystalPixPerUM;

  /**
   * 3 element array defining dimensions of bounding box of crystal in um. Here
   * x = y = z.
   */
  private final double[]       crystSizeUM;

  /** 3 element array defining dimensions of bounding box in voxels. */
  private final int[]          crystSizeVoxels;

  /**
   * 4d array where the 4th dimension is a 3 element array with the coordinates
   * of the voxel i,j,k.
   */
  private final double[][][][] crystCoord;

  /**
   * Dose and fluence arrays holding the scalar fields for these values at voxel
   * i,j,k.
   */
  private double[][][]         dose, fluence, elastic;

  /**
   * A boolean (int for extensibility to deeper segmentation) array. 0 = empty,
   * 1 = crystal.
   */
  private final boolean[][][]  crystOcc;

  /**
   * Generic property constructor for spherical crystals. Extracts all required
   * information from a Map data structure.
   * *
   * Used properties:
   * CRYSTAL_DIM_X
   * CRYSTAL_RESOLUTION (optional. Default: 0.5)
   * 
   * @param properties
   *          Map of type <Object, Object> that contains all crystal properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Crystal} class.
   */
  public CrystalSpherical(final Map<Object, Object> properties) {
    // Pass properties to Crystal()-constructor
    super(properties);

    // Check if optional values are initialized, otherwise set to defaults.
    Map<Object, Object> mergedProperties = new HashMap<Object, Object>();
    mergedProperties.put(Crystal.CRYSTAL_RESOLUTION, CRYSTAL_RESOLUTION_DEF);
    mergedProperties.putAll(properties);

    // Check for valid parameters
    Assertions a = new Assertions("Could not create spherical crystal: ");
    a.checkIsClass(mergedProperties.get(Crystal.CRYSTAL_DIM_X), Double.class,
        "no crystal diameter specified");
    a.checkIsClass(mergedProperties.get(Crystal.CRYSTAL_RESOLUTION),
        Double.class, "no crystal resolution specified");

    crystalPixPerUM = (Double) mergedProperties.get(Crystal.CRYSTAL_RESOLUTION);

    Double diameter = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_X);

    double[] tempDiameterArray = { diameter, diameter, diameter };
    crystSizeUM = tempDiameterArray;

    // Define how many voxels are needed for the bounding box.
    int nVox = (int) StrictMath.round(diameter * crystalPixPerUM) + 1;

    int[] tempSizeArray = { nVox, nVox, nVox };
    crystSizeVoxels = tempSizeArray;

    dose = new double[nVox][nVox][nVox];
    fluence = new double[nVox][nVox][nVox];
    elastic = new double[nVox][nVox][nVox];

    /*
     * initialise an array to store the voxel coordinates and occupancies before
     * setting the final crystCoord.
     */
    double[][][][] tempCrystCoords = new double[nVox][nVox][nVox][3];
    boolean[][][] tempCrystOcc = new boolean[nVox][nVox][nVox];

    // Loop over all voxels to define their centre position and occupancy
    for (int i = 0; i < nVox; i++) {
      for (int j = 0; j < nVox; j++) {
        for (int k = 0; k < nVox; k++) {

          /*
           * Coordinates should be far side of box (-diameter/2) + how far along
           * we are (i/crystRes).
           */
          tempCrystCoords[i][j][k][0] = -diameter / 2 + i / crystalPixPerUM;
          tempCrystCoords[i][j][k][1] = -diameter / 2 + j / crystalPixPerUM;
          tempCrystCoords[i][j][k][2] = -diameter / 2 + k / crystalPixPerUM;

          /*
           * Occupancy (=1 if they are in the crystal)finds distance to origin,
           * and tests if it's smaller than the radius. Occ = 1 if so, 0
           * otherwise.
           */
          double distanceFromOrigin = Math.sqrt(tempCrystCoords[i][j][k][0]
              * tempCrystCoords[i][j][k][0]
              + tempCrystCoords[i][j][k][1] * tempCrystCoords[i][j][k][1]
              + tempCrystCoords[i][j][k][2] * tempCrystCoords[i][j][k][2]);
          if (distanceFromOrigin <= (diameter / 2)) {
            tempCrystOcc[i][j][k] = true;
          } else {
            tempCrystOcc[i][j][k] = false;
          }
        }
      }
    }

    crystCoord = tempCrystCoords;
    crystOcc = tempCrystOcc;
  }

  @Override
  public void setupDepthFinding(final double angle, final Wedge wedge) {
    // Spherical crystals do not need a specific depth finding setup.
  }

  @Override
  public double findDepth(final double[] voxCoord, final double deltaPhi,
      final Wedge myWedge) {

    /*
     * Define a new origin, allowing for the crystal having been translated. No
     * need to define z, since it cannot be translated along this axis.
     */
    double[] newOrigin = new double[2];
    newOrigin[0] = myWedge.getStartX() + myWedge.getTransX(deltaPhi);
    newOrigin[1] = myWedge.getStartY() + myWedge.getTransY(deltaPhi);

    // Map voxCoord back to origin so that we can find depth from simple lin-alg
    double[] mappedCoords = new double[3];
    mappedCoords[0] = voxCoord[0] - newOrigin[0];
    mappedCoords[1] = voxCoord[1] - newOrigin[1];
    mappedCoords[2] = voxCoord[2];

    // Find distance from mappedCoords to surface of sphere:
    // Let point mappedCoords be P = (a, b, c). Surface point along
    // vector (0, 0, -1) form this is S = (x, y, z)
    //            _______
    //           /       \   Since we are only translating
    //          /S----P   \  along (0, 0, -1), S = (a, b, z).
    //         |          |  Also, S is on the surface of the sphere,
    //         |    O     |  so r^2 = x^2 + y^2 + z^2
    //          \        /   Solving these equation simultaneously gives us
    //           \______/    z^2 = r^2 - a^2 - b^2
    // And the depth is defined as depth = z - c

    double surfaceZ = Math.sqrt(Math.pow(crystSizeUM[0] / 2, 2)
        - Math.pow(mappedCoords[0], 2) - Math.pow(mappedCoords[1], 2));
    return surfaceZ - mappedCoords[2];
  }

  @Override
  public double[] getCrystCoord(final int i, final int j, final int k) {
    return crystCoord[i][j][k];
  }

  @Override
  public boolean isCrystalAt(final int i, final int j, final int k) {
    return crystOcc[i][j][k];
  }

  @Override
  public void addDose(final int i, final int j, final int k,
      final double doseVox) {
    dose[i][j][k] += doseVox;
  }

  @Override
  public void addFluence(final int i, final int j, final int k,
      final double fluenceVox) {
    fluence[i][j][k] += fluenceVox;
  }

  @Override
  public void addElastic(final int i, final int j, final int k,
      final double elasticVox) {
    elastic[i][j][k] += elasticVox;
  }

  @Override
  public double getDose(final int i, final int j, final int k) {
    return dose[i][j][k];
  }

  @Override
  public double getElastic(final int i, final int j, final int k) {
    return elastic[i][j][k];
  }

  @Override
  public double getFluence(final int i, final int j, final int k) {
    return fluence[i][j][k];
  }

  @Override
  public String crystalInfo() {
    return String
        .format(
            "Spherical crystal of diameter %.0f um, "
                + "at a resolution of %.2f um per voxel edge.",
            crystSizeUM[0], 1 / crystalPixPerUM);
  }

  @Override
  public int[] getCrystSizeVoxels() {
    int[] csv = new int[crystSizeVoxels.length];
    System.arraycopy(crystSizeVoxels, 0, csv, 0, crystSizeVoxels.length);
    return csv;
  }

  @Override
  public double getCrystalPixPerUM() {
    return crystalPixPerUM;
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
  public double addDoseAfterPE(int i, int j, int k, double doseIncreasePE, double doseIncreaseFL) {
    return 0;
  }

  @Override
  public void setPEparamsForCurrentBeam(double beamEnergy, double feFactors[][]) {    
  }
  
  @Override
  public void setFLparamsForCurrentBeam(final double[][] feFactors) {
  }

  @Override
  public double addDoseAfterFL(int i, int j, int k, double doseIncrease) {
    return 0;
  }
}
