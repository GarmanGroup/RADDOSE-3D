/**
 *
 */
package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

/**
 * @author magd3052
 */
public class CrystalPolyhedron extends Crystal {

  /** Resolution of crystal in 1/um. */
  private final double     crystalPixPerUM;

  /**
   * Initial orientation of the crystal in the plane of the loop (right handed
   * rotation about z) and of the loop (right handed rotation about x) in
   * radians.
   */
  private final double     p, l;

  /**
   * 3 element array defining dimensions of
   * bounding box of crystal in um.
   */
  private final double[]   crystSizeUM;

  /** 3 element array defining dimensions of bounding box in voxels. */
  private final int[]      crystSizeVoxels;

  /**
   * Dose and fluence arrays holding the scalar
   * fields for these values at voxel i,j,k.
   */
  private double[][][]     dose, fluence, elastic;

  /**
   * A boolean (int for extensibility to deeper segmentation) array. 0 = empty,
   * 1 = crystal.
   */
  private final boolean[][][]  crystOcc;

  /**
   * 4d array where the 4th dimension is a 3 element array with the coordinates
   * of the voxel i,j,k in the starting position.
   */
  private double[][][][]   crystCoord;

  /**
   * Vertex array containing a variable number of 3-dimension vertices.
   * Currently set to a default approx. tetrahedron for testing purposes.
   */
  private final double[][] vertices     = {
                                           { 0, 0, 0 },
                                           { 2, 0, 0 },
                                           { 1, 2, 0 },
                                           { 1, 1, 2 }
                                           };

  /**
   * Index array displaying connectivity of vertex array.
   * Please try to make sure these indices are going clockwise
   * as this will help anyone who plans to draw the crystal
   * in OpenGL.
   * In groups of 3 - triangles only please, no octagon nonsense.
   */
  private final int[][]    indices      = {
                                           { 2, 1, 3 },
                                           { 1, 3, 4 },
                                           { 1, 4, 2 },
                                           { 4, 3, 2 }
                                           };

  
  /**
   * Code shamefully copied from @link CrystalCuboid class
   * but frankly, CrystalCuboid is doomed once this class is implemented.
   * 
   * Generic property constructor for cuboid crystals. Extracts all required
   * information from a Map data structure.
   * *
   * Used properties:
   * CRYSTAL_DIM_X
   * CRYSTAL_DIM_Y
   * CRYSTAL_DIM_Z
   * CRYSTAL_RESOLUTION (optional. Default: use getDefaultLimitedResolution)
   * CRYSTAL_ANGLE_P (optional. Default: 0)
   * CRYSTAL_ANGLE_L (optional. Default: 0)
   * 
   * @param properties
   *          Map of type <Object, Object> that contains all crystal properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Crystal} class.
   */
  public CrystalPolyhedron(Map<Object, Object> properties) {
    super(properties);
    // Pass properties to Crystal()-constructor

    // Check if optional values are initialized, otherwise set to defaults.
    Map<Object, Object> mergedProperties = new HashMap<Object, Object>();
    mergedProperties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    mergedProperties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    mergedProperties.putAll(properties);

    // Check for valid parameters
    Assertions a = new Assertions("Could not create cuboid crystal: ");
    a.checkIsClass(mergedProperties.get(Crystal.CRYSTAL_DIM_X), Double.class,
        "no X dimension specified");
    a.checkIsClass(mergedProperties.get(Crystal.CRYSTAL_DIM_Y), Double.class,
        "no Y dimension specified");
    a.checkIsClass(mergedProperties.get(Crystal.CRYSTAL_DIM_Z), Double.class,
        "no Z dimension specified");
    a.checkIsClass(mergedProperties.get(Crystal.CRYSTAL_ANGLE_P), Double.class,
        "no P angle specified");
    a.checkIsClass(mergedProperties.get(Crystal.CRYSTAL_ANGLE_L), Double.class,
        "no L angle specified");

    Double xdim = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_X);
    Double ydim = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_Y);
    Double zdim = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_Z);

    if ((mergedProperties.get(Crystal.CRYSTAL_RESOLUTION) == null)
        || (!Double.class.isAssignableFrom(
            mergedProperties.get(Crystal.CRYSTAL_RESOLUTION).getClass()))) {
      mergedProperties.put(Crystal.CRYSTAL_RESOLUTION,
          getDefaultLimitedResolution(xdim, ydim, zdim));
    }

    // Assign the final variables that can be set directly from the constructor
    crystalPixPerUM = (Double) mergedProperties.get(Crystal.CRYSTAL_RESOLUTION);
    p = Math.toRadians((Double) mergedProperties.get(Crystal.CRYSTAL_ANGLE_P));
    l = Math.toRadians((Double) mergedProperties.get(Crystal.CRYSTAL_ANGLE_L));

    double[] tempCrystDim = { xdim, ydim, zdim };
    crystSizeUM = tempCrystDim; // Final Value

    // Set number of voxels to match crystal dimensions
    int nx = (int) StrictMath.round(xdim * crystalPixPerUM) + 1;
    int ny = (int) StrictMath.round(ydim * crystalPixPerUM) + 1;
    int nz = (int) StrictMath.round(zdim * crystalPixPerUM) + 1;
    int[] tempCrystSize = { nx, ny, nz };
    crystSizeVoxels = tempCrystSize; // Final Value

    // Initialise dose, elastic, and fluence to the correct size
    dose = new double[nx][ny][nz];
    fluence = new double[nx][ny][nz];
    elastic = new double[nx][ny][nz];
    crystOcc = new boolean[nx][ny][nz];
    
    /*
     * Calculate Crystal Coordinates, and assign them:
     */

    double[][][][] tempCrystCoords = new double[nx][ny][nz][3];

    for (int i = 0; i < nx; i++) {
      for (int j = 0; j < ny; j++) {
        for (int k = 0; k < nz; k++) {

          /*
           * Set original coordinate. Temporary variables needed since we use
           * all of the previous xyz's to set each of the new ones.
           */
          double x = -xdim / 2 + i / crystalPixPerUM;
          double y = -ydim / 2 + j / crystalPixPerUM;
          double z = -zdim / 2 + k / crystalPixPerUM;

          /*
           * rotation in plane about [0 0 1] (P) Temporary variables needed
           * since we use all of the previous xyz's to set each of the new ones.
           */
          double x2 = x * Math.cos(p) + y * Math.sin(p);
          double y2 = -1 * x * Math.sin(p) + y * Math.cos(p);
          double z2 = z;

          /*
           * rotation loop about [1 0 0] (L)
           */
          tempCrystCoords[i][j][k][0] = x2;
          tempCrystCoords[i][j][k][1] = y2 * Math.cos(l) + z2 * Math.sin(l);
          tempCrystCoords[i][j][k][2] = -1 * y2 * Math.sin(l) + z2
              * Math.cos(l);
        }
      }
    }

    crystCoord = tempCrystCoords; // Final value

  }
  
  public boolean calculateCrystalOccupancy(int i, int j, int k)
  {
    // TODO: calculate crystal occupancy
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#setupDepthFinding(double,
   * se.raddo.raddose3D.Wedge)
   */
  @Override
  public void setupDepthFinding(double angrad, Wedge wedge) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#findDepth(double[], double,
   * se.raddo.raddose3D.Wedge)
   */
  @Override
  public double findDepth(double[] voxCoord, double deltaPhi, Wedge myWedge) {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#getCrystCoord(int, int, int)
   */
  @Override
  public double[] getCrystCoord(int i, int j, int k) {
    return crystCoord[i][j][k];
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#isCrystalAt(int, int, int)
   */
  @Override
  public boolean isCrystalAt(int i, int j, int k) {
    return crystOcc[i][j][k];
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#addDose(int, int, int, double)
   */
  @Override
  public void addDose(int i, int j, int k, double doseIncrease) {
     dose[i][j][k] += doseIncrease;
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#addFluence(int, int, int, double)
   */
  @Override
  public void addFluence(int i, int j, int k, double fluenceIncrease) {
    fluence[i][j][k] += fluenceIncrease;

  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#addElastic(int, int, int, double)
   */
  @Override
  public void addElastic(int i, int j, int k, double elasticIncrease) {
    elastic[i][j][k] += elasticIncrease;

  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#crystalInfo()
   */
  @Override
  public String crystalInfo() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#getCrystSizeVoxels()
   */
  @Override
  public int[] getCrystSizeVoxels() {
    int[] csv = new int[crystSizeVoxels.length];
    System.arraycopy(crystSizeVoxels, 0, csv, 0, crystSizeVoxels.length);
    return csv;
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#getCrystSizeUM()
   */
  @Override
  public double[] getCrystSizeUM() {
    double[] cs = new double[crystSizeUM.length];
    System.arraycopy(crystSizeUM, 0, cs, 0, crystSizeUM.length);
    return cs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#getDose(int, int, int)
   */
  @Override
  public double getDose(int i, int j, int k) {
    return dose[i][j][k];
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#getFluence(int, int, int)
   */
  @Override
  public double getFluence(int i, int j, int k) {
    return fluence[i][j][k];
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#getElastic(int, int, int)
   */
  @Override
  public double getElastic(int i, int j, int k) {
    return elastic[i][j][k];
  }

  /*
   * (non-Javadoc)
   * 
   * @see se.raddo.raddose3D.Crystal#getCrystalPixPerUM()
   */
  @Override
  public double getCrystalPixPerUM() {
    return crystalPixPerUM;
  }

}
