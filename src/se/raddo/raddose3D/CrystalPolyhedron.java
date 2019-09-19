/**
 *
 */
package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author magd3052
 */
public class CrystalPolyhedron extends Crystal {  
  /** Resolution of crystal in 1/um. */
  protected final double        crystalPixPerUM;

  /**
   * Initial orientation of the crystal in the plane of the loop (right handed
   * rotation about z) and of the loop (right handed rotation about x) in
   * radians.
   */
  protected final double        p;

  protected final double        l;
  
  private boolean MCSim = false;
  
  /**
   * Number of bins used along the fluorescence escape tracks and photoelectron escape tracks
   */
  protected  int flRes;
  
  protected  int peRes;

  /**
   * 3 element array defining dimensions of
   * bounding box of crystal in um.
   */
  protected final double[]      crystSizeUM;

  /** 3 element array defining dimensions of bounding box in voxels. */
  private final int[]           crystSizeVoxels;
  private int[]           cryoCrystSizeVoxels;

  /**
   * Dose and fluence arrays holding the scalar
   * fields for these values at voxel i,j,k.
   */
  public double[][][]    dose, fluence, elastic;
  
  

  private final int numberAngularEmissionBins = 50;

  /**
   * Escape factor (% of photoelectrons which remain within the crystal)
   * for each voxel coordinate i, j, k.
   */
  private final double[][][]    escapeFactor;
  
  private Map<Object, Object> totalProperties = new HashMap<Object, Object>();

  /**
   * Constants for calculation of Gumbel distribution mu and beta parameters.
   */
  private static  double[] GUMBEL_DISTN_CALC_LOC = {0.0121, 0.0405}; //initially set as 1.17, <20keV
  private static  double[] GUMBEL_DISTN_CALC_SCALE = {0.002, 0.0076}; //initially set as 1.17, <20keV
  private static  double[] CRYO_GUMBEL_DISTN_CALC_LOC = {0.0121, 0.0405}; //initially set as 1.17, <20keV
  private static  double[] CRYO_GUMBEL_DISTN_CALC_SCALE = {0.002, 0.0076}; //initially set as 1.17, <20keV

  /**
   * Constants for calculation of JohnsonSU distribution gamma and eta and loc/scale parameters.
   */
  private static  double JSU_DISTN_CALC_LOC; //initially set as 1.17, <20keV
  private static  double JSU_DISTN_CALC_SCALE; //initially set as 1.17, <20keV
  private static  double JSU_DISTN_CALC_GAMMA; //initially set as 1.17, <20keV
  private static  double JSU_DISTN_CALC_ETA; //initially set as 1.17, <20keV
  private static  double CRYO_JSU_DISTN_CALC_LOC; //initially set as 1.17, <20keV
  private static  double CRYO_JSU_DISTN_CALC_SCALE; //initially set as 1.17, <20keV
  private static  double CRYO_JSU_DISTN_CALC_GAMMA; //initially set as 1.17, <20keV
  private static  double CRYO_JSU_DISTN_CALC_ETA; //initially set as 1.17, <20keV
  
  /**
   * Distance bins travelled by a photoelectron.
   */ 
  private double[]     PE_DISTANCES_TRAVELLED;
  private double[] CRYO_PE_DISTANCES_TRAVELLED;
  
  /**
   * Distance bins travelled by fluorescence.
   */
 // private double[][][] flDistancesTravelled;
  private double[][] flDistancesTravelled;
  
  /**
   * length of distance bins travelled by photoelectron.
   */
  private int peDistBins;
  
  //to test
  private double fldose = 0;
  private double flRelease = 0;
  private double peRelease = 0;
  
  /**
   * Stores the percentage of crystal and cryo density to weight for track length calculation
   */
  public double cryoAndCrystalDensity;
  
  /**
   * length of distance bins travelled by fluorescence.
   */
  private int flDistBins;
  
  /**
   * Max angle for photoelectron direction vectors.
   */
  private static final double   PE_ANGLE_LIMIT = 1*2*Math.PI;
  
  /**
   * The resolution limit is the number of divisions to split the photoelectron direction vectors. 
   * This limit will be 10*10 so 100 tracks
   * The angle resolution is the number of tracks to send out the dose along per voxel.
   * This is 1*1 and is randomly chosen for each voxel
   */
  private static final int   PE_ANGLE_RESOLUTION = 1;
  private static final int   PE_ANGLE_RES_LIMIT = 100;
  
  private static final int   FL_ANGLE_RESOLUTION = 1;
  private static final int   FL_ANGLE_RES_LIMIT = 16;

//  private final int numberAngularEmissionBins = 10;
  public TreeMap<Double, double[]>[]  lowEnergyAngles;
  public TreeMap<Double, double[]>[]  highEnergyAngles;
  
  /**
   * Stores the number of tracks used for fluorescence 
   */
  private int numberOfTracksFL;
  
  /**
   * Stores the indexes of the tracks, biased so a more likely tracks is stored proportionally more times
   */
  private int [] trackNumberBias;
  
  /**
   * 3d array for voxels where photoelectrons can reach
   */
  private  double[][][] relativeVoxXYZ;
  private  double[][][] relativeVoxXYZCryoCrystal;
  
  /**
   * Has the photoelectron angular distribution based on the material
   */
  private double[] angularDistribution;
  private double[] cryoAngularDistribution;
  
  /**
   * Pixels per micron of the surrounding material
   */
  private double cryoPPM;
  /**
   * Used to convert surrounding coordinates to crystal coordinates in terms of voxel ijk
   */
  public int cryoCoordinateShift;
  
  /**
   * 5d array for voxels where fluorescence can reach
   * the first two dimensions are element and shell
   */
  //private double[][][][][] flRelativeVoxXYZ;
  private double[][][][] flRelativeVoxXYZ;
  /**
   * Proportion of voxel dose deposited at each distance
   * from voxel due to photoelectron escape
   */
  private  double[]   propnDoseDepositedAtDist;
  private  double[]   propnDoseDepositedAtDistCryo;
  
  /**
   * Proportion of voxel dose deposited at each distance
   * from voxel for each shell energy of each element
   */
 // private double[][][] flDistanceDistribution; 
  private double[][] flDistanceDistribution;
  /**
   * A boolean (int for extensibility to deeper segmentation) array.
   * Fourth dimension is a two element array, first element
   * is a flag (calculated/not calculated) and second element is
   * a boolean (crystal/not crystal).
   */
  public final boolean[][][][] crystOcc;

  /**
   * 4d array where the 4th dimension is a 3 element array with the coordinates
   * of the voxel i,j,k in the starting position.
   */
  private final double[][][][]  crystCoord;
  private double[][][][]  cryoCrystCoord;

  /**
   * Vertex array containing a variable number of 3-dimension vertices.
   */
  protected double[][]          vertices;

  /**
   * Vertices which have been rotated for a given wedge angle.
   */
  private double[][]            rotatedVertices;

  /**
   * Index array displaying connectivity of vertex array.
   * These indices must go clockwise to ensure correct calculation
   * of normal vectors.
   * In groups of 3 - triangles only please, no octagon nonsense.
   */
  public int[][]               indices;

  /**
   * Similar in style to the index array, except each index is replaced
   * by the corresponding rotatedVertex.
   */
  public double[][][]          expandedRotatedVertices;

  /**
   * Normal array holding normalised direction vectors for
   * each triangle specified by the index array.
   * Contains an i, j, k vector per triangle.
   * Should have same no. of entries as the indices array.
   */
  public double[][]            normals, rotatedNormals;

  /**
   * Distances from origin for each of the triangle planes.
   * Should have same no. of entries as the indices array.
   */
  public double[]              originDistances, rotatedOriginDistances;

  /**
   * Vector class containing magical vector methods
   * like cross products and magnitudes.
   *
   * @author magd3052
   */
  public static class Vector {
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
   * Load vertices from wireframe file or any subclass implementation.
   *
   * @param mergedProperties Map of type <Object, Object> that contains all
   *          crystal properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Crystal} class.
   */
  public void loadVertices(final Map<Object, Object> mergedProperties) {
    // Assign wireframe type and wireframe file
    String wireframeType = (String) mergedProperties
        .get(CRYSTAL_WIREFRAME_TYPE);
    String wireframeFile = (String) mergedProperties
        .get(CRYSTAL_WIREFRAME_FILE);

    // TODO: turn into something a bit more sensible later
    // like an ImportWireframeFactory.

    if ("obj".equalsIgnoreCase(wireframeType)) {
      ImportWireframeObj importer = new ImportWireframeObj(wireframeFile);
      vertices = importer.getVertices();
      indices = importer.getIndices();
    } else {
      System.out.println("Wireframe model type not set.");
      indices = new int[1][3];
    }

  }

  /**
   * Generic property constructor for polyhedron crystals. Extracts all required
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
  public CrystalPolyhedron(final Map<Object, Object> properties) {
    super(properties);
    // Pass properties to Crystal()-constructor

    // Check if optional values are initialized, otherwise set to defaults.
    Map<Object, Object> mergedProperties = new HashMap<Object, Object>();
    mergedProperties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    mergedProperties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    mergedProperties.put(Crystal.CRYSTAL_FLUORESCENT_RESOLUTION, 0);
    mergedProperties.put(Crystal.CRYSTAL_PHOTOELECTRON_RESOLUTION, 0);
    mergedProperties.putAll(properties);
    
    totalProperties = properties;
    // Check for valid parameters
    Assertions a = new Assertions("Could not create polyhedral crystal: ");
    a.checkIsClass(mergedProperties.get(Crystal.CRYSTAL_ANGLE_P), Double.class,
        "no P angle specified");
    a.checkIsClass(mergedProperties.get(Crystal.CRYSTAL_ANGLE_L), Double.class,
        "no L angle specified");

    // Assign the rotation variables that can be set directly from the constructor
    p = Math.toRadians((Double) mergedProperties.get(Crystal.CRYSTAL_ANGLE_P));
    l = Math.toRadians((Double) mergedProperties.get(Crystal.CRYSTAL_ANGLE_L));

    loadVertices(mergedProperties);

    double[] xMinMax = this.minMaxVertices(0, vertices);
    double[] yMinMax = this.minMaxVertices(1, vertices);
    double[] zMinMax = this.minMaxVertices(2, vertices);
    super.setMinMaxCrystalDimensions(xMinMax, yMinMax, zMinMax);
    
    for (int i = 0; i < vertices.length; i++) {

      double x = vertices[i][0];
      double y = vertices[i][1];
      double z = vertices[i][2];

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
      vertices[i][0] = x2;
      vertices[i][1] = y2 * Math.cos(l) + z2 * Math.sin(l);
      vertices[i][2] = -1 * y2 * Math.sin(l) + z2
          * Math.cos(l);
    }
    
    
    //fudged for microED
    xMinMax = this.minMaxVertices(0, vertices);
    yMinMax = this.minMaxVertices(1, vertices);
    zMinMax = this.minMaxVertices(2, vertices);

    Double xshift = -xMinMax[0];
    Double yshift = -yMinMax[0];
    Double zshift = -zMinMax[0];

    Double xdim = xMinMax[1] - xMinMax[0];
    Double ydim = yMinMax[1] - yMinMax[0];
    Double zdim = zMinMax[1] - zMinMax[0];

    // Assign the resolution from the constructor or from calculated value
    if ((mergedProperties.get(Crystal.CRYSTAL_RESOLUTION) == null)
        || (!Double.class.isAssignableFrom(
            mergedProperties.get(Crystal.CRYSTAL_RESOLUTION).getClass()))) {
      mergedProperties.put(Crystal.CRYSTAL_RESOLUTION,
          getDefaultLimitedResolution(xdim, ydim, zdim));
    }
    crystalPixPerUM = (Double) mergedProperties.get(Crystal.CRYSTAL_RESOLUTION);

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

    // Initialise crystal occupancy to correct size
    crystOcc = new boolean[nx][ny][nz][2];

    /*
     * Calculate Crystal Coordinates, and assign them:
     * (This needs to be turned into a rotation-based subroutine!)
     */

    double[][][][] tempCrystCoords = new double[nx][ny][nz][3];

    for (int i = 0; i < nx; i++) {
      for (int j = 0; j < ny; j++) {
        for (int k = 0; k < nz; k++) {

          /*
           * Set original coordinate. Temporary variables needed since we use
           * all of the previous xyz's to set each of the new ones.
           */
          double x = -xshift + i / crystalPixPerUM;
          double y = -yshift + j / crystalPixPerUM;
          double z = -zshift + k / crystalPixPerUM;
          
          
          //need to remember to add this back in for the main RADDOSE-3D
          
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
          
          tempCrystCoords[i][j][k][0] = x;
          tempCrystCoords[i][j][k][1] = y;
          tempCrystCoords[i][j][k][2] = z;
          
        }
      }
    }

    crystCoord = tempCrystCoords; // Final value

    escapeFactor = new double[nx][ny][nz];
    
    // Initialise beam-independent crystal photoelectron escape properties
    //Get fl bins  
    flRes = 0;
    peRes = 0; 
    if (fluorescentEscape) {
      flRes = (int) mergedProperties.get(Crystal.CRYSTAL_FLUORESCENT_RESOLUTION);
      if (flRes >= 2) { //if user defined and sensible
        flDistBins = flRes;
      }
      else { //default
        flDistBins = 8;
      }
    }
   if (photoElectronEscape) {
    peRes = (int) mergedProperties.get(Crystal.CRYSTAL_PHOTOELECTRON_RESOLUTION);
   }
   lowEnergyAngles = new TreeMap[95];
   highEnergyAngles = new TreeMap[95];
  }
  
  /**
   * Constructs the surrounding solution the same way as the crystal, but extended off all edges in all dimensions 
   * by the maximum photoelectron travel distance. The pixels per micron of the surrounding is chosen so the pixels 
   * match up with the original crystal
   * @param maxPEDistance
   */
  public void produceCryoSolutionCrystal(int maxPEDistance, final Beam beam) {
    
    double[] xMinMax = this.minMaxVertices(0, vertices);
    double[] yMinMax = this.minMaxVertices(1, vertices);
    double[] zMinMax = this.minMaxVertices(2, vertices);
    
    double xCryst = xMinMax[1] - xMinMax[0];
    double yCryst = yMinMax[1] - yMinMax[0];
    double zCryst = zMinMax[1] - zMinMax[0];
    
    //set ppm of cryo - switch to a function 
    double pixelsPerMicron = setCryoPPM(beam, maxPEDistance, xCryst, yCryst, zCryst);

    //test
//    pixelsPerMicron = 20;
    
    int extraVoxels = getExtraVoxels(maxPEDistance, pixelsPerMicron); // the extra voxels to add on each end
    
    //test
 //   extraVoxels = 8;
    
    
    //Correct dims for bigger size
    Double xdim = xCryst + ((extraVoxels / pixelsPerMicron) * 2); 
    Double ydim = yCryst + ((extraVoxels / pixelsPerMicron) * 2);
    Double zdim = zCryst + ((extraVoxels / pixelsPerMicron) * 2); 
    int nx = (int) StrictMath.round(xdim * pixelsPerMicron) + 1;
    int ny = (int) StrictMath.round(ydim * pixelsPerMicron) + 1;
    int nz = (int) StrictMath.round(zdim * pixelsPerMicron) + 1;
    
    cryoPPM = pixelsPerMicron;
    cryoCoordinateShift = extraVoxels;
    
    int[] tempCrystSize = {nx, ny, nz};
    cryoCrystSizeVoxels = tempCrystSize; // Final Value
    
    Double xshift = -xMinMax[0] + (extraVoxels/pixelsPerMicron);
    Double yshift = -yMinMax[0] + (extraVoxels/pixelsPerMicron);
    Double zshift = -zMinMax[0] + (extraVoxels/pixelsPerMicron);
    
    double[][][][] tempCrystCoords = new double[nx][ny][nz][3];
    
    for (int i = 0; i < nx ; i++) {
      for (int j = 0; j < ny ; j++) { 
        for (int k = 0; k < nz ; k++) { //loop through voxels for this crystal
          /*
           * Set original coordinate. Temporary variables needed since we use
           * all of the previous xyz's to set each of the new ones.
           */
          
            double x = -xshift + (i / pixelsPerMicron);
            double y = -yshift + (j / pixelsPerMicron);
            double z = -zshift + (k / pixelsPerMicron);

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
    cryoCrystCoord = tempCrystCoords;
  }
  
  private double setCryoPPM(final Beam beam, final int maxPEDistance,
                            final double xCryst, final double yCryst, final double zCryst) {
    double minDimCryst = Math.min(zCryst, Math.min(xCryst, yCryst)); // incorporste my beam into min dim
    double minDimBeam = beam.beamMinumumDimension();
 //   double minDim = Math.min(minDimBeam,  minDimCryst);
    
 //   double crystalVolume = xCryst * yCryst * zCryst;
 //   double beamVolume = maxPEDistance * beam.getBeamArea();
    
    double pixelsPerMicron =  (1/((double)maxPEDistance)) * 20;
    
    int multiplyFactor = 20;
    if (minDimCryst >= minDimBeam) {
      multiplyFactor *= 1.5;
    }
    
 //   if ((crystalVolume > beamVolume) & (minDimCryst >= minDimBeam)
    double idealPPM = 0.0;
    if (MCSim == false) {
      idealPPM = ((1/((double)maxPEDistance)) * 5) + ((1/((double)maxPEDistance)) * multiplyFactor * (1/minDimCryst)) ; 
    }
    else {
      idealPPM = ((1/((double)maxPEDistance)) * 5) ;
    }
    
    if (idealPPM >= crystalPixPerUM) { // set up a ppm so the crsytals can superimpose 
      pixelsPerMicron = Math.ceil(idealPPM / crystalPixPerUM) * crystalPixPerUM;
    } 
    else {
      pixelsPerMicron = crystalPixPerUM / ((int) (crystalPixPerUM / idealPPM));
    }
    

    return pixelsPerMicron;
  }
  
  @Override
  public int getExtraVoxels(int maxPEDistance, double pixelsPerMicron) {
    //New pixels per micron to be sensible for the maxPEDistance
    int extraVoxels = (int) (maxPEDistance/ (1/pixelsPerMicron));
    return extraVoxels;
  }
  

  /**
   * Calculates normal array from index and vertex arrays.
   * Also calculates signed distances of each triangle
   * from the origin.
   */
  public void calculateNormals(final boolean rotated) {

    double[][] verticesUsed = vertices;
    double[] originDistancesUsed = new double[vertices.length];
    double[][] normalsUsed = new double[vertices.length][3];

    if (rotated) {
      verticesUsed = rotatedVertices;
    }

    normalsUsed = new double[indices.length][3];
    originDistancesUsed = new double[indices.length];

    for (int i = 0; i < indices.length; i++) {
      // get the three vertices which this triangle corresponds to.
      double[] point1 = verticesUsed[indices[i][0] - 1];
      double[] point2 = verticesUsed[indices[i][1] - 1];
      double[] point3 = verticesUsed[indices[i][2] - 1];

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
      rotatedOriginDistances = new double[indices.length];
      rotatedNormals = new double[indices.length][3];

      for (int i = 0; i < normalsUsed.length; i++) {
        System.arraycopy(normalsUsed[i], 0, rotatedNormals[i], 0, 3);
      }

      System.arraycopy(originDistancesUsed, 0, rotatedOriginDistances, 0,
          indices.length);
    } else {
      originDistances = new double[indices.length];
      normals = new double[indices.length][3];

      for (int i = 0; i < normalsUsed.length; i++) {
        System.arraycopy(normalsUsed[i], 0, normals[i], 0, 3);
      }

      System.arraycopy(originDistancesUsed, 0, originDistances, 0,
          indices.length);
    }
  }

  /**
   * Calculates crystal occupancy at i, j, k, returns value
   * and sets crystOcc at a given i, j, k.
   *
   * @param i i
   * @param j j
   * @param k k
   * @return crystal occupancy flag
   */
  public boolean calculateCrystalOccupancy(final int i, final int j, final int k)
  {
    if (normals == null) {
      calculateNormals(false);
    }

    boolean inside = false;

    double[] directionVector = { 0, 0, 1 };
    double[] origin = crystCoord[i][j][k];

    for (int l = 0; l < indices.length; l++) {
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
        System.arraycopy(vertices[indices[l][m] - 1], 0, triangleVertices[m],
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

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#setupDepthFinding(double,
   * se.raddo.raddose3D.Wedge)
   */
  @Override
  public void setupDepthFinding(final double angrad, final Wedge wedge) {

    rotatedVertices = new double[vertices.length][3];

    // Rotate and translate the vertices of the crystal
    // to the position defined by angrad (= deltaphi)

    for (int vertInd = 0; vertInd < vertices.length; vertInd++) {
      // Translate Y
      rotatedVertices[vertInd][1] = vertices[vertInd][1]
          + wedge.getStartY()
          + wedge.getTransY(angrad);
      // Translate X
      double transX = vertices[vertInd][0]
          + wedge.getStartX()
          + wedge.getTransX(angrad);
      // Translate Z
      double transZ = vertices[vertInd][2]
          + wedge.getStartZ()
          + wedge.getTransZ(angrad);

      // Rotate X
      rotatedVertices[vertInd][0] = transX * Math.cos(angrad)
          + transZ * Math.sin(angrad);
      // Rotate Z
      rotatedVertices[vertInd][2] = -1 * transX * Math.sin(angrad)
          + transZ * Math.cos(angrad);
    }

    calculateNormals(true);

    /*
     * Now we populate the expandedRotatedVertex array.
     */

    expandedRotatedVertices = new double[indices.length][3][3];

    for (int i = 0; i < indices.length; i++) {
      for (int j = 0; j < 3; j++) {
        System.arraycopy(rotatedVertices[indices[i][j] - 1], 0,
            expandedRotatedVertices[i][j], 0, 3);
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#findDepth(double[], double,
   * se.raddo.raddose3D.Wedge)
   */
  @Override
  public double findDepth(final double[] voxCoord, final double deltaPhi,
      final Wedge myWedge) {
    double[] zAxis = { 0, 0, 1 };

    List<Double> distancesFound = new ArrayList<Double>();

    for (int i = 0; i < indices.length; i++) {
      double intersectionDistance = (-1)
          * Vector.rayTraceDistance(rotatedNormals[i],
              zAxis, voxCoord, rotatedOriginDistances[i]);

      Double distanceObject = Double.valueOf(intersectionDistance);

      if (intersectionDistance <= 0 || distanceObject.isNaN()
          || distanceObject.isInfinite()) {
        continue;
      }

      double[] intersectionPoint = Vector.rayTraceToPointWithDistance(
          zAxis, voxCoord, intersectionDistance);

      boolean crosses = Vector.polygonInclusionTest(expandedRotatedVertices[i],
          intersectionPoint);

      if (crosses) {
        distancesFound.add(Double.valueOf(intersectionDistance));
      }
    }

    Collections.sort(distancesFound);

    for (int i = 0; i < distancesFound.size() - 1; i++) {
      if (distancesFound.get(i + 1) == distancesFound.get(i)) {
        distancesFound.remove(i + 1);
      }
    }

    // sanity check that point is within crystal
    if (distancesFound.isEmpty() || distancesFound.size() % 2 == 0) {
      return 0;
    }

    double depth = distancesFound.get(0).doubleValue();

    for (int i = 1; i < distancesFound.size(); i += 2) {
      Double addition = distancesFound.get(i + 1) - distancesFound.get(i);

      depth += addition.doubleValue();
    }
    /*
     * if (deltaPhi == 0) {
     * System.out.println(voxCoord[0] + "\t" + voxCoord[1] + "\t" + voxCoord[2]
     * + "\t" + depth);
     * }
     */
    return depth;
  }
  
  /**
   * Calculates the distance distribution for a photoelectron
   * 
   * @param distancesTravelled
   * @param gumbelDistribution
   * @param mu
   * @param beta
   * @param bins
   */
  private void calculateGumbelDistribution(final double[] distancesTravelled,
      double[] gumbelDistribution, final double mu, final double beta,
      final int bins) {
    for (int i = 0; i < bins; i++) {
      double x = distancesTravelled[i];
      double z = (x - mu)/beta;
      
    //  gumbelDistribution[i] = Math.exp(-(z + Math.exp(-z))) / beta; //this is incorrect as this is gumbel_r
        gumbelDistribution[i] = Math.exp(z - Math.exp(z)) / beta;  //this is gumbel_l as it should be
    }
  }
  
  private void calculateJohnsonSUDistribution(final double[] distancesTravelled,
      double[] johnsonDistribution, final double gamma, final double eta, final double loc, final double scale,
      final int bins) {
    for (int i = 0; i < bins; i++) {
      double x = distancesTravelled[i];
      double hypSinh = asinh((x-loc)/scale);
      johnsonDistribution[i] = (eta / (Math.pow((Math.pow((x-loc), 2) + Math.pow(scale, 2)), 0.5)  
                               * Math.pow(2*Math.PI, 0.5))) * 
                               (Math.exp(-0.5*Math.pow((gamma + eta*hypSinh), 2)));
    }
    
  }
  
  private double asinh(double x)
  {
    return Math.log(x + Math.sqrt(x*x + 1.0));
  }
  
  private double calculateGumbelPDF(final double x, final double mu, final double beta) {
    double z = (x-mu)/beta;
    double PDF = Math.exp(z - Math.exp(z)) / beta;
    return PDF;
  }
  
  private double calculateJohnsonPDF(final double x, final double gamma, final double eta, 
      final double loc, final double scale) {
    double hypSinh = asinh((x-loc)/scale);
    double PDF = (eta / (Math.pow((Math.pow((x-loc), 2) + Math.pow(scale, 2)), 0.5)  
        * Math.pow(2*Math.PI, 0.5))) * 
        (Math.exp(-0.5*Math.pow((gamma + eta*hypSinh), 2)));
    return PDF;
  }
  
  /**
   * Calculate the energy deposition distribution along photoelectron paths for the given energy 
   * 
   * @param distancesTravelled
   * @param bins
   * @param peEnergy
   * @return
   */
  private double[] calculateEnergyDistn(final double[] distancesTravelled, final int bins, final double peEnergy) {
    ReadEnergyCSV rdCSV = new ReadEnergyCSV();
    double[] lowCoefficients = rdCSV.openCSV("constants/EnergyCoefsLow.txt", peEnergy);
    double[] mediumCoefficients = rdCSV.openCSV("constants/EnergyCoefsMed.txt", peEnergy);
    double[] highCoefficients = rdCSV.openCSV("constants/EnergyCoefsHigh.txt", peEnergy);
    
    double[] energyDeposited = new double[bins];
    
    for (int i = 0; i < bins; i++) {
      double x = distancesTravelled[i]/distancesTravelled[bins-1]; //proportion along track
      if (x < 2/peEnergy){ //low
        if (x == 0) {
          energyDeposited[i] = 0;
        }
        else {
        energyDeposited[i] =  lowCoefficients[6]*Math.pow(x, 6) + lowCoefficients[5]*Math.pow(x,5) + 
                              lowCoefficients[4]*Math.pow(x, 4) + lowCoefficients[3]*Math.pow(x,3) + 
                              lowCoefficients[2]*Math.pow(x, 2) + lowCoefficients[1]*x + lowCoefficients[0];
        }
      }
      else if (x > 1-(2/peEnergy)){ //high
        energyDeposited[i] =  highCoefficients[6]*Math.pow(x, 6) + highCoefficients[5]*Math.pow(x,5) + 
                              highCoefficients[4]*Math.pow(x, 4) + highCoefficients[3]*Math.pow(x,3) + 
                              highCoefficients[2]*Math.pow(x, 2) + highCoefficients[1]*x + highCoefficients[0];        
      }
      else{ //medium
        energyDeposited[i] =  mediumCoefficients[6]*Math.pow(x, 6) + mediumCoefficients[5]*Math.pow(x,5) + 
                              mediumCoefficients[4]*Math.pow(x, 4) + mediumCoefficients[3]*Math.pow(x,3) + 
                              mediumCoefficients[2]*Math.pow(x, 2) + mediumCoefficients[1]*x + mediumCoefficients[0];          
      }
    }
    return energyDeposited;
  }
  
  /**
   * Gets the distance distribution for a photoelectron
   * 
   * @param beamEnergy
   * @return
   */ 
  private double[] getGumbelParamsForBeamEnergy(final double beamEnergy, final boolean cryo) {
    // Gumbel distribution for mean photoelectron path lengths depend on 
    // beam energy. Derived from Josh Dickerson's project
    double energyCorrection = EnergyToSubtractFromPE;  // this isn't the right energy correction for the cryo
    
    if (cryo == true) {
      energyCorrection = cryoEnergyToSubtractFromPE;
    }
    
    double[] gumbParams = new double[2]; //0 = location parameter, 1 = scale parameter
     
    double peEnergy = beamEnergy - energyCorrection;

    if (cryo == false) {
      gumbParams[0] = GUMBEL_DISTN_CALC_LOC[0]*Math.pow(peEnergy,2) 
          + GUMBEL_DISTN_CALC_LOC[1]*(peEnergy);
      gumbParams[1] = GUMBEL_DISTN_CALC_SCALE[0]*Math.pow(peEnergy,2) 
          + GUMBEL_DISTN_CALC_SCALE[1]*(peEnergy);
    }
    else {
      gumbParams[0] = CRYO_GUMBEL_DISTN_CALC_LOC[0]*Math.pow(peEnergy,2) 
          + CRYO_GUMBEL_DISTN_CALC_LOC[1]*(peEnergy);
      gumbParams[1] = CRYO_GUMBEL_DISTN_CALC_SCALE[0]*Math.pow(peEnergy,2) 
          + CRYO_GUMBEL_DISTN_CALC_SCALE[1]*(peEnergy);
    }
    
  //  gumbParams[0] += 1;
   // gumbParams[1] = 0.256487;
    
    return gumbParams;
  }
  
  /**
   * Gets the distance distribution for a photoelectron
   * 
   * @param beamEnergy
   * @return
   */ 
  private double[] getJohnsonParamsForBeamEnergy(final double beamEnergy, final boolean cryo) {
    // Gumbel distribution for mean photoelectron path lengths depend on 
    // beam energy. Derived from Josh Dickerson's project
    double energyCorrection = EnergyToSubtractFromPE;  // this isn't the right energy correction for the cryo
    
    if (cryo == true) {
      energyCorrection = cryoEnergyToSubtractFromPE;
    }
    
    double[] johnsonParams = new double[4]; //0 = location parameter, 1 = scale parameter, 2 = gamma, 3 = eta
     
    double peEnergy = beamEnergy - energyCorrection;

    if (cryo == false) {
      johnsonParams[0] = JSU_DISTN_CALC_LOC;
      johnsonParams[1] = JSU_DISTN_CALC_SCALE;
      johnsonParams[2] = JSU_DISTN_CALC_GAMMA;
      johnsonParams[3] = JSU_DISTN_CALC_ETA;
    }
    else {
      johnsonParams[0] = CRYO_JSU_DISTN_CALC_LOC;
      johnsonParams[1] = CRYO_JSU_DISTN_CALC_SCALE;
      johnsonParams[2] = CRYO_JSU_DISTN_CALC_GAMMA;
      johnsonParams[3] = CRYO_JSU_DISTN_CALC_ETA;
    }
    
    return johnsonParams;
  }
  
  /**
   * Calculates the fluorescence distribution for each wavelength emitted
   * 
   * @param feFactors
   */
  private void calcFluorescenceDistribution(final double[][] feFactors) {
    int distanceResolution = flDistBins -1;
 //   flDistanceDistribution = new double[feFactors.length][4][flDistBins];
    flDistanceDistribution = new double[feFactors.length][flDistBins];
//    flDistancesTravelled = new double[feFactors.length][4][flDistBins];
    flDistancesTravelled = new double[feFactors.length][flDistBins];
    double runningEscapeTotal;
    for (int i = 0; i < feFactors.length; i++) { //for every element
   //   for (int j = 0; j < 4; j++) { //for each shell
        runningEscapeTotal = 0;
  //    if (fluorescenceProportionEvent[i][j] > 0) { //If j shell fluorescence possible
        if (fluorescenceProportionEvent[i] > 0) {
  //      int muabsIndex = (4* j) + 4;
          int muabsIndex = 4;
        //Calculate distance at which escape probability = 5%
        double maxDistanceFl = -1 * (Math.log(0.05)/feFactors[i][muabsIndex]);
        double crystalMaxDistance = Math.pow(Math.pow(crystSizeUM[0], 2) + 
            Math.pow(crystSizeUM[1], 2) + Math.pow(crystSizeUM[2], 2), 0.5 );
        //If it's more than max furthest distance in crystal - using simple now maybe do with vectors later
        if (maxDistanceFl > crystalMaxDistance) {
          maxDistanceFl = crystalMaxDistance;
        }
          //populate fldistances with crystalMaxDistance as the last
        for (int q = 0; q <= distanceResolution; q++) {
       //   flDistancesTravelled[i][j][q] = (maxDistanceFl/distanceResolution) * q;
          flDistancesTravelled[i][q] = (maxDistanceFl/distanceResolution) * q;
        }

        for (int l = flDistBins-1; l >= 0; l--) { 
          //the likelihood of escape at this distance is exp(-muabs*x/2)
          //so starting from the edge, calculate what is released past this. 

          //the first run is the escape prob, the rest will be the prob of it landing there
          //Might be better to land it halfway between the points instead of at the end of each point
          
          if(l == flDistBins-1) { //prob of escape
        //    flDistanceDistribution[i][j][l] = Math.exp(-feFactors[i][muabsIndex] * flDistancesTravelled[i][j][l]);
            flDistanceDistribution[i][l] = Math.exp(-feFactors[i][muabsIndex] * flDistancesTravelled[i][l]);
          }
            else { //prob it stops at this distance
        //      flDistanceDistribution[i][j][l] = Math.exp(-feFactors[i][muabsIndex] * flDistancesTravelled[i][j][l]) - runningEscapeTotal;
              flDistanceDistribution[i][l] = Math.exp(-feFactors[i][muabsIndex] * flDistancesTravelled[i][l]) - runningEscapeTotal;
            }
        //  runningEscapeTotal += flDistanceDistribution[i][j][l];
          runningEscapeTotal += flDistanceDistribution[i][l];
        }

      }
     // }

    }
  }
  
  /**
   * Sets the maximum distance of a photoelectron from the crystal for the given photoelectron energy
   * 
   * @param beamEnergy
   */
  private  void setMaxPEDistance(final double beamEnergy) { //needs to be dynamic as it depends on beam energy
    double peEnergy = beamEnergy - EnergyToSubtractFromPE;
    int maxPEDistance = (int) Math.ceil(getMaxPEDistance(peEnergy, false));
  //  int maxPEDistance = (int) Math.ceil(getMaxPEDistanceJohnson(peEnergy, false));
    
 //   int maxPEDistance = 3;

      //Get PE bins
    if (peRes >= 2) { //if user defined and sensible
      peDistBins = peRes;
    }
    else { //default
      //set the pixel size in um
      double pixelSize = 1/crystalPixPerUM;
      if (pixelSize >= maxPEDistance) {
        peDistBins = 2;  // 0 and 8
      }
      else {
        //number of bins = 1 + roundup(8/pixelSize).
        peDistBins = 1 + (int) Math.ceil(maxPEDistance/pixelSize);
        //So I need to improve it a bit at the lower range
        if (peDistBins < maxPEDistance){
          peDistBins = maxPEDistance;
        }
        //erring on side of caution
        peDistBins += 1;
        // ramping it up now it is quicker

        peDistBins += 50;

      }
     }
 // Get PE distances
    PE_DISTANCES_TRAVELLED = new double[peDistBins];
    double binInterval = (double) maxPEDistance / (peDistBins - 1);
    for (int i = 0; i < peDistBins; i++) {
      PE_DISTANCES_TRAVELLED[i] = i * binInterval;
    } 
    
   propnDoseDepositedAtDist = new double[peDistBins];
   relativeVoxXYZ = new double[peDistBins][PE_ANGLE_RES_LIMIT * PE_ANGLE_RES_LIMIT][3];
  }
  
  /**
   * Sets the maximum distance of a photoelectron from the surrounding for the given photoelectron energy
   * @param beamEnergy
   */
  private  void setMaxPEDistanceCryo(final double beamEnergy, final Beam beam) { //needs to be dynamic as it depends on beam energy
    double peEnergy = beamEnergy - cryoEnergyToSubtractFromPE;

    //Need to redo max   
    int maxPEDistance = (int) Math.ceil(getMaxPEDistance(peEnergy, true)); //need to change for cryo......... 
    

    
    //find max side
    double averageSide = (crystSizeUM[0] + crystSizeUM[1] + crystSizeUM[2])/3;

    if (averageSide >= maxPEDistance) {
      cryoAndCrystalDensity = 0.5;
    }else {
      cryoAndCrystalDensity = maxPEDistance / (maxPEDistance + averageSide);
    }
    
    // to test
  //  maxPEDistance += 1;
    
    //set up the surrounding environment
    produceCryoSolutionCrystal(maxPEDistance, beam);
    
    CRYO_PE_DISTANCES_TRAVELLED = new double[peDistBins];
    double binInterval = (double) maxPEDistance / (peDistBins - 1);
    for (int i = 0; i < peDistBins; i++) {
      CRYO_PE_DISTANCES_TRAVELLED[i] = i * binInterval;
    } 
    
    propnDoseDepositedAtDistCryo = new double[peDistBins];
    relativeVoxXYZCryoCrystal = new double[peDistBins][PE_ANGLE_RES_LIMIT * PE_ANGLE_RES_LIMIT][3];
  }
  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#setPEparamsForCurrentBeam(double)
   */
  
  /**
   * Takes the averaage photoelectron energy and calculates the maximum distance one can travel
   * 
   * @param peEnergy
   * @return
   */
  private double getMaxPEDistance(final double peEnergy, final boolean cryo) {
    int energyRange = 0;
    double locationParam = 0;
    double scaleParam = 0;
    if (cryo == false) {
    locationParam = GUMBEL_DISTN_CALC_LOC[energyRange]*Math.pow(peEnergy,2) 
        + GUMBEL_DISTN_CALC_LOC[energyRange+1]*(peEnergy); 
    scaleParam = GUMBEL_DISTN_CALC_SCALE[energyRange]*Math.pow(peEnergy,2) 
         + GUMBEL_DISTN_CALC_SCALE[energyRange+1]*(peEnergy);
    }//need to change for cryo
    else {
      locationParam = CRYO_GUMBEL_DISTN_CALC_LOC[energyRange]*Math.pow(peEnergy,2) 
          + CRYO_GUMBEL_DISTN_CALC_LOC[energyRange+1]*(peEnergy); 
      scaleParam = CRYO_GUMBEL_DISTN_CALC_SCALE[energyRange]*Math.pow(peEnergy,2) 
           + CRYO_GUMBEL_DISTN_CALC_SCALE[energyRange+1]*(peEnergy);
    }
    

    double modalHeight = calculateGumbelPDF(locationParam, locationParam, scaleParam);
    double targetHeight = modalHeight * 0.001;
    double calcHeight = modalHeight;
    double step = peEnergy/100;
    if (peEnergy < 10) {
    step /= 10;
    }
    double maxDistance = locationParam;
    while (calcHeight >= targetHeight) {
      maxDistance += step;
      calcHeight = calculateGumbelPDF(maxDistance, locationParam, scaleParam);
      //escape clause in case there is an issue
      if (maxDistance >= locationParam * 2) {
        break;
      }
    }

    maxDistance += 1;
    
    return maxDistance;
  }
  
  /**
   * Takes the averaage photoelectron energy and calculates the maximum distance one can travel
   * 
   * @param peEnergy
   * @return
   */
  private double getMaxPEDistanceJohnson(final double peEnergy, final boolean cryo) {
    int energyRange = 0;
    double locationParam = 0;
    double scaleParam = 0;
    double gamma = 0;
    double eta = 0;
    if (cryo == false) {
    locationParam = JSU_DISTN_CALC_LOC;
    scaleParam = JSU_DISTN_CALC_SCALE;
    gamma = JSU_DISTN_CALC_GAMMA;
    eta = JSU_DISTN_CALC_ETA;
    }//need to change for cryo
    else {
      locationParam = CRYO_JSU_DISTN_CALC_LOC;
      scaleParam = CRYO_JSU_DISTN_CALC_SCALE;
      gamma = CRYO_JSU_DISTN_CALC_GAMMA;
      eta = CRYO_JSU_DISTN_CALC_ETA;
    }
    

    double modalHeight = calculateJohnsonPDF(locationParam, gamma, eta, locationParam, scaleParam);
    double targetHeight = modalHeight * 0.001;
    double calcHeight = modalHeight;
    double step = peEnergy/100;
    if (peEnergy < 10) {
    step /= 10;
    }
    double maxDistance = locationParam;
    while (calcHeight >= targetHeight) {
      maxDistance += step;
      calcHeight = calculateJohnsonPDF(maxDistance, gamma, eta, locationParam, scaleParam);
      //escape clause in case there is an issue
      if (maxDistance >= locationParam * 2) {
        break;
      }
    }

    return maxDistance;
  }
  
  @Override
  public void setPEparamsForCurrentBeam(final double beamEnergy, CoefCalc coefCalc, double[][] feFactors) {
    // Initialise crystal photolectron escape properties here for current beam
    //set Gumbel values based on density
    double density = coefCalc.getDensity();
  //  density = 1.17;
    double peEnergy = beamEnergy - EnergyToSubtractFromPE;
    GUMBEL_DISTN_CALC_LOC = setGumbelLoc(density, peEnergy);
    GUMBEL_DISTN_CALC_SCALE = setGumbelScale(density, peEnergy);
    
    double jsuParams[] = setJohnsonParams(density, peEnergy);
    JSU_DISTN_CALC_LOC = jsuParams[0];
    JSU_DISTN_CALC_SCALE = jsuParams[1];
    JSU_DISTN_CALC_GAMMA = jsuParams[2];
    JSU_DISTN_CALC_ETA = jsuParams[3];
    
    //first of all need to get PE distances 
    setMaxPEDistance(beamEnergy);
    angularDistribution = setUpPEPolarisation(coefCalc, beamEnergy, feFactors, false);
 //   findVoxelsReachedByPE(false, coefCalc, beamEnergy, feFactors, 0);
    calcProportionVoxDoseDepositedByDist(beamEnergy);  
    
  }
  
  @Override
  public void setCryoPEparamsForCurrentBeam(final Beam beam, CoefCalc coefCalc, double[][] feFactors) {
    // Initialise crystal photolectron escape properties here for current beam
    //set Gumbel values based on density
    double density = coefCalc.getCryoDensity();
    double beamEnergy = beam.getPhotonEnergy();
  //  double density = coefCalc.getDensity();
    double peEnergy = beamEnergy - cryoEnergyToSubtractFromPE;
    CRYO_GUMBEL_DISTN_CALC_LOC = setGumbelLoc(density, peEnergy);
    CRYO_GUMBEL_DISTN_CALC_SCALE = setGumbelScale(density, peEnergy);
    //first of all need to get PE distances 
    setMaxPEDistanceCryo(beamEnergy, beam);
    
    //could recalculate cryo gumbel based on new density
    density = (coefCalc.getCryoDensity() * cryoAndCrystalDensity) + (coefCalc.getDensity() * (1-cryoAndCrystalDensity));
    // this density is pretty bad so I would need to think of something a bit more clever 
    CRYO_GUMBEL_DISTN_CALC_LOC = setGumbelLoc(density, peEnergy);
    CRYO_GUMBEL_DISTN_CALC_SCALE = setGumbelScale(density, peEnergy);
    //I'm not recalculating the max distance though :/ 
    
    
    
  //  findVoxelsReachedByPE(true, coefCalc, beamEnergy, feFactors);
    cryoAngularDistribution = setUpPEPolarisation(coefCalc, beamEnergy, feFactors, true);
    calcProportionVoxDoseDepositedByDistCryo(beamEnergy);  
  }
  
  
  @Override
  public void setFLparamsForCurrentBeam(final double[][] feFactors) {
    // Initialise crystal fl escape properties here for current beam
    //find voxels is done with pe
    calcFluorescenceDistribution(feFactors);  
    findVoxelsReachedByFL(feFactors);
  }
  
  /**Sets the Location parameters for the Gumbel distribution based on density
   * 
   * 
   * @param density
   * @param peEnergy
   * @return
   */
  private double[] setGumbelLoc(final double density, final double peEnergy) {
    double[] gumbelParams = new double[2];
    if (peEnergy <= 20) {
      gumbelParams[0] = 0.0105*Math.pow(density, 2) - 0.0351*density + 0.0387;
      gumbelParams[1] = 0.0245*Math.pow(density, 2) - 0.0943*density + 0.1171;  
    }
    else if (peEnergy > 20 && peEnergy <= 50) {
      gumbelParams[0] = 0.009*Math.pow(density, 2) - 0.0293*density + 0.0318;
      gumbelParams[1] = 0.0459*Math.pow(density, 2) - 0.1942*density + 0.2582;  
    }
    else {
      gumbelParams[0] = 0.0072*Math.pow(density, 2) - 0.024*density + 0.0262;
      gumbelParams[1] = 0.16925*Math.pow(density, 2) - 0.05562*density + 0.6075;  
    }
    return gumbelParams;
  }
  
  /**
   * Sets the scale parameters for the Gumbel distribution based on density
   * 
   * @param density
   * @param peEnergy
   * @return
   */
  private double[] setGumbelScale(final double density, final double peEnergy) {
    double[] gumbelParams = new double[6];
    if (peEnergy <= 20) {
      gumbelParams[0] = 0.0029*Math.pow(density, 2) - 0.0081*density + 0.0076;
      gumbelParams[1] = 0*Math.pow(density, 2) - 0.0085*density + 0.01751;  
    }
    else if (peEnergy > 20 && peEnergy <= 50) {
      gumbelParams[0] = 0.0018*Math.pow(density, 2) - 0.0053*density + 0.0051;
      gumbelParams[1] = 0*Math.pow(density, 2) - 0.0238*density + 0.0536;  
    }
    else {
      gumbelParams[0] = 0*Math.pow(density, 2) - 0.0006*density + 0.0017;
      gumbelParams[1] = 0*Math.pow(density, 2) - 0.0569*density + 0.1068;  
    }
    return gumbelParams;
  }
  
  /**Sets the Location parameters for the Gumbel distribution based on density
   * 
   * 
   * @param density
   * @param peEnergy
   * @return
   */
  private double[] setJohnsonParams(final double density, final double peEnergy) {
    double[] johnsonParams = new double[4];
    //for 11.25keV
    johnsonParams[0] = 3.884704185; //loc
    johnsonParams[1] = 0.991818699; //scale
    johnsonParams[2] = 9.542206584; //gamma
    johnsonParams[3] = 6.608091735; //eta
    return johnsonParams;
  }
  
  
  
  /**
   * calculate the fraction of energy deposited by PE up to each 
   * distance, assuming PE distances follow a given distribution and convoluting with the energy
   * deposition distribution
   * @param beamEnergy
   */
  private void calcProportionVoxDoseDepositedByDist(final double beamEnergy) { 
    double peEnergy = beamEnergy - EnergyToSubtractFromPE;
    // Set up a mean path length distribution
    double[] pathLengthDistn = new double[peDistBins];

    
    double[] distnParams = getGumbelParamsForBeamEnergy(beamEnergy, false);
    calculateGumbelDistribution(PE_DISTANCES_TRAVELLED, pathLengthDistn, distnParams[0],
        distnParams[1], peDistBins);
    
    
    /*
    double[] distnParams = getJohnsonParamsForBeamEnergy(beamEnergy, false);
    calculateJohnsonSUDistribution(PE_DISTANCES_TRAVELLED, pathLengthDistn, distnParams[2], distnParams[3], distnParams[0],
        distnParams[1], peDistBins);
    */
    
    
    
    
    //I'm applying the energy distribution to each PE distance travelled so it can be combined with the distance distribution 
    double[][] totalEnergyDistn = new double[peDistBins][peDistBins];
    for (int i = 0; i < peDistBins; i++) {
      double[] energyDistn = calculateEnergyDistn(PE_DISTANCES_TRAVELLED, peDistBins - i, peEnergy);
      int length = energyDistn.length;
      for (int j = (length - 1); j > 0  ; j--) {
      totalEnergyDistn[i][j] = energyDistn[j];  //i = 0 is longest distance, a high j is top energy
      }
    }
    
    // find total area under specified distribution
    double distnIntegral = 0;
    double[] totEnergyIntegral = new double[peDistBins]; 
    for (int l = 0; l < peDistBins-1; l++) {
      double width = PE_DISTANCES_TRAVELLED[l + 1] - PE_DISTANCES_TRAVELLED[l];
      double height = (pathLengthDistn[l + 1] + pathLengthDistn[l]) / 2;
      distnIntegral += width * height;
      
      //do the same for the energy distribution 
      for (int i = 0; i < peDistBins; i++) { //for every PE Distance travelled
        if (totalEnergyDistn[i][l + 1] != 0) { //prevent values off edge of distribution changing it
          double energyHeight = (totalEnergyDistn[i][l + 1] + totalEnergyDistn[i][l]) / 2;
          totEnergyIntegral[i] += width * energyHeight; 
        }
      }
    } 

    
    
     // The following code calculates the proportion of dose deposited along
     // each track by the travelling PE
      
    
   double[] distanceWidths = new double[peDistBins];
   double[] distanceHeights = new double[peDistBins];
   int pathCount = -1;
   for (int l = peDistBins-1; l > 0; l--) {  
     pathCount += 1;
     distanceWidths[pathCount] = PE_DISTANCES_TRAVELLED[l] - PE_DISTANCES_TRAVELLED[l-1]; //width of this electron length subset
     distanceHeights[pathCount] = (pathLengthDistn[l] + pathLengthDistn[l-1]) / 2;
   }

   // a way to get the mean
   int thisCount = -1;
   double sumWidth = 0, mean = 0;
   for (int l = peDistBins-1; l > 0; l--) {  
  // for (int l = 0; l < peDistBins; l++) {    
     thisCount += 1;
     sumWidth += distanceWidths[l];
     mean += (sumWidth) * ((distanceWidths[l]*distanceHeights[l])/distnIntegral);
   } 
   
   /*
    * Starting at the last bin, the population of photoelectron that stop here is calculated based
    * on the path length distribution. The energy deposition distribution then calculates where to deposit their energy
    * along the path length. 
    * Then move back one to second last bin and do the same and keep looping for all bins
    */

    for (int l = peDistBins-1; l > 0; l--) { //for every bin 
      for (int i = 0; i < peDistBins; i++) { //for every subset of electrons that stop at this path length
        double energyHeight = 0.;
        if (totalEnergyDistn[i][l] != 0) {
          energyHeight = (totalEnergyDistn[i][l] + totalEnergyDistn[i][l-1]) / 2;
        }
        
        //replace l with the proportion in the energy distribution
        if (totEnergyIntegral[i] != 0) {
          propnDoseDepositedAtDist[l] += (distanceWidths[i] * distanceHeights[i] / (distnIntegral)) * (distanceWidths[i] * energyHeight / (totEnergyIntegral[i])); 
        }
      }
    }

    
    /*
    // get the energy propertion dist
    double[][] totalEnergyDistn = new double[peDistBins-1][peDistBins-1];
    double width = PE_DISTANCES_TRAVELLED[1] - PE_DISTANCES_TRAVELLED[0];
    for (int i = 0; i < peDistBins-1; i++) {
      double[] energyDistn = calculateEnergyDistn(PE_DISTANCES_TRAVELLED, peDistBins - i, peEnergy);
      double[] energyPropDistn = new double[peDistBins - 1];
      double tot = 0;
      for (int l = 1; l < energyDistn.length; l++) {
        tot += width * ((energyDistn[l] + energyDistn[l-1])/2);
      }
      for (int l = 1; l < energyDistn.length; l++) {
        energyPropDistn[l-1] = (width * ((energyDistn[l] + energyDistn[l-1])/2))/tot;   //convert to a proportion
      }
      int length = energyPropDistn.length;
      for (int j = (length - 1); j >= 0  ; j--) {
      totalEnergyDistn[i][j] = energyPropDistn[j];  //i = 0 is longest distance, a high j is top energy
      }
    }
    System.out.println("test");
    
    //Now get path lengths
    double distnIntegral = 0;
    //start with total area under curve
    for (int l = 0; l < peDistBins-1; l++) {
      double height = (pathLengthDistn[l + 1] + pathLengthDistn[l]) / 2;
      distnIntegral += width * height;
    }
    double[] pathLengthPropDist = new double[peDistBins-1];
    for (int l = 0; l < peDistBins-1; l++) {
      double height = (pathLengthDistn[l + 1] + pathLengthDistn[l]) / 2;
      pathLengthPropDist[l] = (width * height)/distnIntegral;
    }
    System.out.println("test");
    
    //now combine
    for (int l = peDistBins-1; l > 0; l--) { //for every bin 
      //get proportion that stop here
      double proportionStop = pathLengthPropDist[l-1];
      for (int i = 0; i < l; i++) { //for all the bins within this path length
        propnDoseDepositedAtDist[i] += proportionStop * totalEnergyDistn[peDistBins-l-1][i];
      }
    }
    
    //test
    double test = 0;
    for(int i = 0; i < propnDoseDepositedAtDist.length; i++) {
      test += propnDoseDepositedAtDist[i];
    }
    System.out.println(test);
    */
    /*
    //just CSDA
    
    // get the energy propertion dist
    double width = PE_DISTANCES_TRAVELLED[1] - PE_DISTANCES_TRAVELLED[0];
 
      double[] energyDistn = calculateEnergyDistn(PE_DISTANCES_TRAVELLED, peDistBins, peEnergy);
      double[] energyPropDistn = new double[peDistBins - 1];
      double tot = 0;
      for (int l = 1; l < energyDistn.length; l++) {
        tot += width * ((energyDistn[l] + energyDistn[l-1])/2);
      }
      for (int l = 1; l < energyDistn.length; l++) {
        energyPropDistn[l-1] = (width * ((energyDistn[l] + energyDistn[l-1])/2))/tot;   //convert to a proportion
      }
    //now combine
      for (int i = 0; i < l; i++) { //for all the bins within this path length
        propnDoseDepositedAtDist[i] += energyPropDistn[i];
      }
    */
  }
  
  /**
   * calculate the fraction of energy deposited by PE from the surrounding up to each 
   * distance, assuming PE distances follow a given distribution and convoluting with the energy
   * deposition distribution
   * @param beamEnergy
   */
  private void calcProportionVoxDoseDepositedByDistCryo(final double beamEnergy) {
    // calculate the fraction of energy deposited by PE up to each 
    // distance, assuming PE distances follow a given distribution
    double peEnergy = beamEnergy - cryoEnergyToSubtractFromPE;
    // Set up a mean path length distribution
    double[] pathLengthDistn = new double[peDistBins];
    
    double[] distnParams = getGumbelParamsForBeamEnergy(beamEnergy, true);
    calculateGumbelDistribution(CRYO_PE_DISTANCES_TRAVELLED, pathLengthDistn, distnParams[0],
        distnParams[1], peDistBins);
    
    /*
    double[] distnParams = getJohnsonParamsForBeamEnergy(beamEnergy, false);
    calculateJohnsonSUDistribution(CRYO_PE_DISTANCES_TRAVELLED, pathLengthDistn, distnParams[2], distnParams[3], distnParams[0],
        distnParams[1], peDistBins);
    */
    
    //I'm applying the energy distribution to each PE distance travelled so it can be combined with the distance distribution 
    double[][] totalEnergyDistn = new double[peDistBins][peDistBins];
    for (int i = 0; i < peDistBins; i++) {
      double[] energyDistn = calculateEnergyDistn(CRYO_PE_DISTANCES_TRAVELLED, peDistBins - i, peEnergy);
      int length = energyDistn.length;
      for (int j = (length - 1); j > 0  ; j--) {
      totalEnergyDistn[i][j] = energyDistn[j];  //i = 0 is longest distance, a high j is top energy
      }
    }
    
    // find total area under specified distribution
    double distnIntegral = 0;
    double[] totEnergyIntegral = new double[peDistBins]; 
    for (int l = 0; l < peDistBins-1; l++) {
      double width = CRYO_PE_DISTANCES_TRAVELLED[l + 1] - CRYO_PE_DISTANCES_TRAVELLED[l];
      double height = (pathLengthDistn[l + 1] + pathLengthDistn[l]) / 2;
      distnIntegral += width * height;
      
      //do the same for the energy distribution 
      for (int i = 0; i < peDistBins; i++) { //for every PE Distance travelled
        if (totalEnergyDistn[i][l + 1] != 0) { //prevent values off edge of distribution changing it
          double energyHeight = (totalEnergyDistn[i][l + 1] + totalEnergyDistn[i][l]) / 2;
          totEnergyIntegral[i] += width * energyHeight; 
        }
      }
    }  
    /*
     * The following code calculates the proportion of dose deposited along
     * each track by the travelling PE
     */  
    double[] distanceWidths = new double[peDistBins];
    double[] distanceHeights = new double[peDistBins];
    int pathCount = -1;
    for (int l = peDistBins-1; l > 0; l--) {  
      pathCount += 1;
      distanceWidths[pathCount] = CRYO_PE_DISTANCES_TRAVELLED[l] - CRYO_PE_DISTANCES_TRAVELLED[l-1]; //width of this electron length subset
      distanceHeights[pathCount] = (pathLengthDistn[l] + pathLengthDistn[l-1]) / 2;
    }
    
     for (int l = peDistBins-1; l > 0; l--) { //for every bin 
       for (int i = 0; i < peDistBins; i++) { //for every subset of electrons that stop at this path length
         double energyHeight = 0.;
         if (totalEnergyDistn[i][l] != 0) {
           energyHeight = (totalEnergyDistn[i][l] + totalEnergyDistn[i][l-1]) / 2;
         }
         
         //replace l with the proportion in the energy distribution
         if (totEnergyIntegral[i] != 0) {
           propnDoseDepositedAtDistCryo[l] += (distanceWidths[i] * distanceHeights[i] / (distnIntegral)) * (distanceWidths[i] * energyHeight / (totEnergyIntegral[i])); 
         }
       }
     }
  }

  /**
   * finds voxels that lie along the PE tracks
   */
  @Override
  public void findVoxelsReachedByPE(boolean cryo, CoefCalc coefCalc, final double energy, double[][] feFactors, final double angle) {
    //way 2
    //Convert angle to less than 360 if it is more
    int timesOver = (int) (angle/(2*Math.PI));
    double thisAngle = angle - (timesOver * 2 *Math.PI);
    
    //flip the angle to be opposite direction
    thisAngle = 2*Math.PI - thisAngle;
    
    double[] distribution = null;
    if (cryo == false) {
      distribution = angularDistribution;
    }
    else {
      distribution = cryoAngularDistribution;
    }
    //sum
    int numbersInArray = 0;
    for (int i = 0; i < distribution.length; i++){
      numbersInArray += distribution[i];
    }
    numbersInArray *= PE_ANGLE_RES_LIMIT;
    
    numbersInArray *= 1.5;  // This is a messy fix to index out of bounds change properly later
    
    double step = 2*Math.PI / PE_ANGLE_RES_LIMIT;
    int counter = -1;
    int bigArrayIndex = 0;
    int[] theBigArray = new int[numbersInArray];
    
    for (double theta = 0*Math.PI; theta < 2*Math.PI; theta += step) {
      for (double phi = 0; phi <= PE_ANGLE_LIMIT/2 ; phi += step) {
        //Check if this track has already been assigned       
        boolean replicateTrack = false;
        
        if (theta == 0 || theta == (PE_ANGLE_LIMIT / 2)) {
          if (phi == 0) {
            replicateTrack = false;
          }
          else {
            replicateTrack = true;
          }
        }
        
        if (replicateTrack == false) {
          counter += 1;

          double xNorm = Math.sin(theta) * Math.cos(phi);
          double yNorm = Math.sin(theta) * Math.sin(phi);
          double zNorm = Math.cos(theta);

          //weight track in bigarray
          int runningCheck = 0;
          
          //calculate the angle to the x axis (or y axis if horizontal)
          //using cartesian vectors, cos(theta) = dot product / multiple of magnitudes
          //x axis vector = (1, 0, 0) so dot product is just xnorm, just yNorm if horizontal
          double dotProduct = 0;
          if (verticalGoniometer == true) {
            dotProduct = xNorm;
          }else {
            dotProduct = yNorm;
          } 
          double magnitude = Math.sqrt(Math.pow(xNorm, 2) + Math.pow(yNorm, 2) + Math.pow(zNorm, 2)); //always 1
          double cosAngleToX = dotProduct / magnitude; 
          double angleToX = Math.acos(cosAngleToX);
          //find where angle is in distribution
          int place = (int) Math.rint((angleToX * PE_ANGLE_RES_LIMIT)/PE_ANGLE_LIMIT);
          
          if (distribution[place] > 0) {
            while (runningCheck < distribution[place]) {
              theBigArray[bigArrayIndex] = counter;
              runningCheck += 1;
              bigArrayIndex += 1;
            }
          }
          
          //Need to apply the rotation matrix here so I shift the x and z axes - shift opposite way to rotation 
          double xNormrot = xNorm * Math.cos(thisAngle) + zNorm * Math.sin(thisAngle);
          double zNormrot = -1 * xNorm * Math.sin(thisAngle) + zNorm * Math.cos(thisAngle);
          
          for (int m = 0; m < peDistBins; m++) {
            // calculate r in voxel coordinates rather than pixels
            double r = 0;
            if (cryo == false)  {  
              r = PE_DISTANCES_TRAVELLED[m] * this.crystalPixPerUM; 
              relativeVoxXYZ[m][counter][0] = r * xNormrot;
              relativeVoxXYZ[m][counter][1] = r * yNorm;
              relativeVoxXYZ[m][counter][2] = r * zNormrot;
            }
            else {
              //the r here is for crystal ppm, this is old way
              r = CRYO_PE_DISTANCES_TRAVELLED[m] * this.crystalPixPerUM;
              relativeVoxXYZCryoCrystal[m][counter][0] = r * xNormrot;
              relativeVoxXYZCryoCrystal[m][counter][1] = r * yNorm;
              relativeVoxXYZCryoCrystal[m][counter][2] = r * zNormrot;
            }
          }
        }
      }
    }
    
    //count numbers in array, not zeroes at the end - a stupid way of doing it so clean up later
    boolean pastFirst = false;
    int actualNumber = 0;
    for (int j = 0; j < theBigArray.length; j++) {
        if (pastFirst == true) {
          if (theBigArray[j] == 0) { //if it goes back to nought this is junk at the end
            actualNumber = j;
            break;
          }
        }        
        if (theBigArray[j] > 0) {
          pastFirst = true;
        }
    }
    int[] actualTrackBias = new int[actualNumber];
    for (int i = 0; i < actualNumber; i++) {
      actualTrackBias[i] = theBigArray[i];
    }
    
    trackNumberBias = actualTrackBias;
  }

  /**
   * Calculate how much to bias the tracks based on the beam polarisation vector
   * @param coefCalc
   * @param energy
   * @param feFactors
   * @param cryo
   * @return
   */
  private double[] setUpPEPolarisation(CoefCalc coefCalc, final double energy, double[][] feFactors, boolean cryo) {
    double beta = 2; //this is only true for s shells but so far I haven't calculated it for other shells
 //   double beta = 0;
    double angle = 0;
    int elementCounter = 0;
    double weight = 0;
    double degreeOfPolarisation = 0.75; // also need to weight as only 75% polarised 
    double point = 0;
    double[] weightedAveragePoint = new double[(PE_ANGLE_RES_LIMIT/2)+1];
    double sumPoint = 0;
    
    elementCounter = -1;
    for (Element e : coefCalc.getPresentElements(cryo)) {
      elementCounter += 1;
      weight += feFactors[elementCounter][0] * feFactors[elementCounter][2];
    }
    
    for (int i = 0; i <= PE_ANGLE_RES_LIMIT/2; i++) { //for each track to be polarised
      angle = i * (PE_ANGLE_LIMIT/PE_ANGLE_RES_LIMIT);
      point = solvePolarisationEquationForAngle(angle, 1, beta) / solvePolarisationEquationForAngle(0, 1, beta);
      sumPoint = point * weight * degreeOfPolarisation;
      weightedAveragePoint[i] = Math.round((1000 * sumPoint)) + (1000 * (1-(weight*degreeOfPolarisation))); //second part is the non-polarised parts
      
   //   weightedAveragePoint[i] = 1000;
      
    }
     return weightedAveragePoint;
   }
  
  /**
   * Solve the polarisation equation for given input
   * @param phi
   * @param photoElectric
   * @param beta
   * @return
   */
  private double solvePolarisationEquationForAngle(double phi, double photoElectric, double beta) {
    double height = (photoElectric / (4*Math.PI)) * (1+(beta*0.5*(3*Math.pow(Math.cos(phi), 2) - 1)));
    return height;
  }

  /**
   * Finds voxels that lie along fluorescence tracks
   * 
   * @param feFactors
   */
  private void findVoxelsReachedByFL(final double feFactors[][]) {
    double step = PE_ANGLE_LIMIT / FL_ANGLE_RES_LIMIT;  
    //flRelativeVoxXYZ = new double[feFactors.length][4][flDistBins][FL_ANGLE_RESOLUTION * FL_ANGLE_RESOLUTION][3];
    flRelativeVoxXYZ = new double[feFactors.length][flDistBins][FL_ANGLE_RES_LIMIT * FL_ANGLE_RES_LIMIT][3];
    int counter = -1;
    for (double phi = 0; phi < PE_ANGLE_LIMIT; phi += step) {
      for (double theta = 0; theta <= PE_ANGLE_LIMIT / 2; theta += step) {
        // calculate x, y, z coordinates of voxel[i][j][k]
        // plus the polar coordinates for r, theta, phi
        
        boolean replicateTrack = false;
        if (theta == 0 || theta == (PE_ANGLE_LIMIT / 2)) {
          if (phi == 0) {
            replicateTrack = false;
          }
          else {
            replicateTrack = true;
          }
        }
        if (replicateTrack == false) {
        counter += 1;
        double xNorm = Math.sin(theta) * Math.cos(phi);
        double yNorm = Math.sin(theta) * Math.sin(phi);
        double zNorm = Math.cos(theta);
        
       
        for (int i = 0; i < feFactors.length; i++) { //for every element
 //         for (int j = 0; j < 4; j++) { //for each shell
            for (int m = 0; m < flDistBins; m++) { 
          // calculate r in voxel coordinates rather than pixels
         /*     
          double r = flDistancesTravelled[i][j][m] * this.crystalPixPerUM; 
          flRelativeVoxXYZ[i][j][m][counter][0] = r * xNorm;
          flRelativeVoxXYZ[i][j][m][counter][1] = r * yNorm;
          flRelativeVoxXYZ[i][j][m][counter][2] = r * zNorm;
          */
              
              double r = flDistancesTravelled[i][m] * this.crystalPixPerUM; 
              flRelativeVoxXYZ[i][m][counter][0] = r * xNorm;
              flRelativeVoxXYZ[i][m][counter][1] = r * yNorm;
              flRelativeVoxXYZ[i][m][counter][2] = r * zNorm;
              
            }
          }
        }
   //   }
      }
    } 
    numberOfTracksFL = counter + 1;
  }
  
  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#addDoseAfterPE(int, int, int, double, double)
   */
  @Override
  public double addDoseAfterPE(final int i, final int j, final int k,
      final double doseIncreasePE) {
       
    double doseLostFromCrystalPE = 0;  
      for (int q = 0; q < PE_ANGLE_RESOLUTION*PE_ANGLE_RESOLUTION; q++) { //for every tracks i'm choosing
        int randomIndex = ThreadLocalRandom.current().nextInt(0, trackNumberBias.length);
        int randomTrack = trackNumberBias[randomIndex];
 
        for (int m = 0; m < peDistBins; m++) {   
          double x = relativeVoxXYZ[m][randomTrack][0];
          double y = relativeVoxXYZ[m][randomTrack][1];
          double z = relativeVoxXYZ[m][randomTrack][2];

          // get dose transferred to these located voxels 
          // at the distance r away (due to PE movement)
          double partialDose = doseIncreasePE * propnDoseDepositedAtDist[m]
              / Math.pow(PE_ANGLE_RESOLUTION,2);
          
          // add counts to total & total within crystal in order to
          // calculate the proportion for a given r.       
          if (isCrystalAt((int) StrictMath.round(i + x), (int) StrictMath.round(j + y),
              (int) StrictMath.round(k + z))) {              
            // get dose transferred to this new voxel (due to PE movement)
            addDose((int) StrictMath.round(i + x), (int) StrictMath.round(j + y),
            (int) StrictMath.round(k + z), partialDose);
          } else {
            doseLostFromCrystalPE += partialDose;
            peRelease += partialDose;
          }
        }    
      }
    return doseLostFromCrystalPE;
  } 
  
  @Override
  public double addDoseAfterFL(final int i, final int j, final int k, 
      final double doseIncreaseFL) {
   double doseLostFromCrystalFL = 0;
   //choose a random track
   int randomTrack = ThreadLocalRandom.current().nextInt(0, numberOfTracksFL);
   
   //for every energy distribution
    for (int n = 0; n < fluorescenceProportionEvent.length; n++) { 
   //   for (int l = 0; l < 4; l++) { // 0 = K, 1 = L1, 2 = L2, 3 = L3
    for (int m = 0; m < flDistBins; m++) {   
      for (int q = 0; q < FL_ANGLE_RESOLUTION*FL_ANGLE_RESOLUTION; q++) { //One loop for now

        double flPartialDose = 0;
        // get dose transferred to these located voxels 
        // at the distance r away (due to PE movement)
        
     //     if(fluorescenceProportionEvent[n][l] != 0) {
            if(fluorescenceProportionEvent[n] != 0) {
   //     flPartialDose = doseIncreaseFL  * fluorescenceProportionEvent[n][l] * flDistanceDistribution[n][l][m] 
   //         / Math.pow(FL_ANGLE_RESOLUTION,2);
          flPartialDose = doseIncreaseFL  * fluorescenceProportionEvent[n] * flDistanceDistribution[n][m] 
              / Math.pow(FL_ANGLE_RESOLUTION,2);
              
        //TO TEST
         fldose += flPartialDose;
      /*  
        double x = flRelativeVoxXYZ[n][l][m][q][0];
        double y = flRelativeVoxXYZ[n][l][m][q][1];
        double z = flRelativeVoxXYZ[n][l][m][q][2];
        */
         
         //change q for a random number between 0 and 35. 
         double x = flRelativeVoxXYZ[n][m][randomTrack][0];
         double y = flRelativeVoxXYZ[n][m][randomTrack][1];  
         double z = flRelativeVoxXYZ[n][m][randomTrack][2];
         
        // add counts to total & total within crystal in order to
        // calculate the proportion for a given r.     
        if (isCrystalAt((int) StrictMath.round(i + x), (int) StrictMath.round(j + y),
            (int) StrictMath.round(k + z))) {              
          // get dose transferred to this new voxel (due to FL movement)
          addDose((int) StrictMath.round(i + x), (int) StrictMath.round(j + y),
          (int) StrictMath.round(k + z), flPartialDose);
        } else {
          doseLostFromCrystalFL += flPartialDose;
          flRelease += flPartialDose;
        }
          }
        }
      }
  //  }
    }
    return doseLostFromCrystalFL;
  }  
  
  @Override
  public double addDoseAfterPECryo(final double i, final double j, final double k,
      final double energyIncreasePE, final double energyToDoseFactor) {
    double doseBackInCrystalPE = 0;
    
    for (int q = 0; q < PE_ANGLE_RESOLUTION*PE_ANGLE_RESOLUTION; q++) { //for every tracks i'm choosing
    //     int randomTrack = ThreadLocalRandom.current().nextInt(0, numberOfTracksPE); //choose one at random
      int randomIndex = ThreadLocalRandom.current().nextInt(0, trackNumberBias.length);
      int randomTrack = trackNumberBias[randomIndex];

      for (int m = 0; m < peDistBins; m++) { 
     
        double x = relativeVoxXYZCryoCrystal[m][randomTrack][0];
        double y = relativeVoxXYZCryoCrystal[m][randomTrack][1];
        double z = relativeVoxXYZCryoCrystal[m][randomTrack][2];      

        // get dose transferred to these located voxels 
        // at the distance r away (due to PE movement)
        double partialDose = energyIncreasePE * propnDoseDepositedAtDistCryo[m]
            / Math.pow(PE_ANGLE_RESOLUTION,2);
        partialDose = (partialDose / energyToDoseFactor) * 1E-06; //Energy to Dose in MGy
        
        // add counts to total & total within crystal in order to
        // calculate the proportion for a given r.       
        if (isCrystalAt((int) StrictMath.round(i + x), (int) StrictMath.round(j + y),
            (int) StrictMath.round(k + z))) {              
          // get dose transferred to this new voxel (due to PE movement)
          
          addDose((int) StrictMath.round(i + x), (int) StrictMath.round(j + y),
          (int) StrictMath.round(k + z), partialDose);
          doseBackInCrystalPE += partialDose;
        } 
      }    
    }
    return doseBackInCrystalPE;
  }
  
  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#getCrystCoord(int, int, int)
   */
  @Override
  public double[] getCrystCoord(final int i, final int j, final int k) {
    return crystCoord[i][j][k];
  }
  
  @Override
  public double[] getCryoCrystCoord(final int i, final int j, final int k) {
    return cryoCrystCoord[i][j][k];
  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#isCrystalAt(int, int, int)
   */
  @Override
  public boolean isCrystalAt(final int i, final int j, final int k) {
    final int[] crystalSize = getCrystSizeVoxels();

    if (i < 0 || i >= crystalSize[0]) {
      return false;
    }
    if (j < 0 || j >= crystalSize[1]) {
      return false;
    }
    if (k < 0 || k >= crystalSize[2]) {
      return false;
    }

    boolean[] occ = crystOcc[i][j][k];

    if (!occ[0]) {
      occ[1] = calculateCrystalOccupancy(i, j, k);
      occ[0] = true;
    }

    return occ[1];
  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#addDose(int, int, int, double)
   */
  @Override
  public void addDose(final int i, final int j, final int k,
      final double doseIncrease) {
    dose[i][j][k] += doseIncrease; //* escapeFactor[i][j][k];
  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#addFluence(int, int, int, double)
   */
  @Override
  public void addFluence(final int i, final int j, final int k,
      final double fluenceIncrease) {
    fluence[i][j][k] += fluenceIncrease;

  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#addElastic(int, int, int, double)
   */
  @Override
  public void addElastic(final int i, final int j, final int k,
      final double elasticIncrease) {
    elastic[i][j][k] += elasticIncrease;

  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#crystalInfo()
   */
  @Override
  public String crystalInfo() {
    String s = String.format(
        "Polyhedron crystal of bounding size "
            + "[%.0f, %.0f, %.0f] um [x, y, z] at a "
            + "resolution of %.2f microns per voxel edge.",
        crystSizeUM[0],
        crystSizeUM[1],
        crystSizeUM[2],
        1 / crystalPixPerUM);
    if (l == 0 && p == 0) {
      return s;
    } else {
      return s + String.format(
          "%nRotated by %.1f deg in the plane of the loop and the loop is "
              + "bent by %.1f relative to the rotation axis at phi = 0.",
          Math.toDegrees(p), Math.toDegrees(l));
    }
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
  
  @Override
  public int[] getCryoCrystSizeVoxels() {
    int[] csv = new int[cryoCrystSizeVoxels.length];
    System.arraycopy(cryoCrystSizeVoxels, 0, csv, 0, cryoCrystSizeVoxels.length);
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
  public double getDose(final int i, final int j, final int k) {
    return dose[i][j][k];
  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#getFluence(int, int, int)
   */
  @Override
  public double getFluence(final int i, final int j, final int k) {
    return fluence[i][j][k];
  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#getElastic(int, int, int)
   */
  @Override
  public double getElastic(final int i, final int j, final int k) {
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
  
  @Override
  public double getCryoCrystalPixPerUM() {
    return cryoPPM;
  }
  
  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#getEscapeFactor(int, int, int)
   */
  @Override
  public double getEscapeFactor(final int i, final int j, final int k) {
    return escapeFactor[i][j][k];
  }

  /**
   * Subclasses should set indices using this method.
   *
   * @param tempIndices new indices
   */
  protected void setIndices(final int[][] tempIndices) {
    indices = new int[tempIndices.length][3];
    for (int i = 0; i < tempIndices.length; i++) {
      System.arraycopy(tempIndices[i], 0, indices[i], 0, 3);
    }
  }
  
  @Override
  public int getCryoExtraVoxels() {
    return cryoCoordinateShift;
  }

  @Override
  public double getNumImages(Wedge wedge) {
    if (Math.abs(wedge.getStartAng() - wedge.getEndAng()) < wedge.getAngRes()) {
      return Crystal.STATICEXPOSURE;
    }
    else {
      Integer sign = 1;
      if (wedge.getEndAng() < wedge.getStartAng()) {
        sign = -1;
      }
      return (sign
          * (int) ((wedge.getEndAng() - wedge.getStartAng())
              / wedge.getAngRes() + 1));
    }
  }

  public void startMicroED(double XDim, double YDim, double ZDim, Beam beam,
      Wedge wedge, CoefCalc coefCalc, String crystalType) {
    //also need indices, vertices, crystalCoords, numberVoxelsm, PPM (may not need all of these but just 
    //to be safe for now
    MicroED microED = new MicroED(vertices, indices, crystCoord, 
                                  crystalPixPerUM, crystSizeVoxels, crystOcc, crystalType);
    microED.CalculateEM(beam, wedge, coefCalc);
  }
  @Override
  public void startXFEL(double XDim, double YDim, double ZDim, Beam beam,
      Wedge wedge, CoefCalc coefCalc, int runNum, boolean verticalGoniometer, boolean xfelTrue, boolean gos) {
    //also need indices, vertices, crystalCoords, numberVoxelsm, PPM (may not need all of these but just 
    //to be safe for now
    XFEL xfel = new XFEL(vertices, indices, crystCoord, 
                                  crystalPixPerUM, crystSizeVoxels, crystOcc, runNum, verticalGoniometer, xfelTrue, gos);
    xfel.CalculateXFEL(beam, wedge, coefCalc);
  }
  
  @Override
  public void startMC(double XDim, double YDim, double ZDim, Beam beam,
      Wedge wedge, CoefCalc coefCalc, int runNum, boolean verticalGoniometer, boolean xfelTrue, boolean gos, double[] surrThickness) {
    //also need indices, vertices, crystalCoords, numberVoxelsm, PPM (may not need all of these but just 
    //to be safe for now
    MC mc = new MC(vertices, indices, crystCoord, 
                                  crystalPixPerUM, crystSizeVoxels, crystOcc, runNum, verticalGoniometer, xfelTrue, gos, surrThickness);
    mc.CalculateXFEL(beam, wedge, coefCalc);
  }
  
  
  
  //photoelectron Monte Carlo stuff starts below
  
  
  
  
  @Override
  public double trackPhotoelectron(int i, int j, int k, double doseIncreasePE, CoefCalc coefCalc, 
                    Map<Element, Double> elementAbsorptionProbs, Map<Element, double[]> ionisationProbs, double[] angularEmissionProbs,
                    Beam beam, boolean surrounding) {
    double energyEscapedPE = 0;
    //determine which element absorbed this photoelectron and which shell it came from
    Element ionisedElement = getIonisedElement(elementAbsorptionProbs);
    int shellIndex = getIonisedShell(ionisationProbs, ionisedElement);
    double shellBindingEnergy = getShellBindingEnergy(ionisedElement, shellIndex);
    double photoelectronEnergy = beam.getPhotonEnergy() - shellBindingEnergy;
    //get the dose scaling factor
    double voxelVolume = Math.pow(((1/crystalPixPerUM)/1E4),3); //in cm^3
    double voxelMass = (coefCalc.getDensity() *  voxelVolume) / 1000; //in Kg
    double startingDose = ((photoelectronEnergy * Beam.KEVTOJOULES) / voxelMass)/1E6; //in  MGy
    double doseScale = doseIncreasePE / startingDose;
    
    //convert pixel coordinates to cartesian to get starting point
    double[] startingCoordinates = convertToCartesianCoordinates(i, j, k); //um
    double previousX = startingCoordinates[0];
    double previousY = startingCoordinates[1];
    double previousZ = startingCoordinates[2];
    //choose an initial direction
    double theta = 0, phi = 0, xNorm = 0, yNorm = 0, zNorm = 0;
    if (shellIndex == 0) { //then I want to send out in a biased direction
      xNorm = getCosAngleToX(angularEmissionProbs);
      //get yNorm and zNorm
      yNorm = PosOrNeg() * Math.random() * Math.pow(1-Math.pow(xNorm, 2), 0.5);
      zNorm = PosOrNeg() * Math.pow(1 - Math.pow(xNorm, 2) - Math.pow(yNorm, 2), 0.5);
      //get theta and phi
      theta = Math.acos(zNorm);
      phi = Math.acos(xNorm / Math.sin(theta));
    }
    else { // send it out in a random direction
      theta = Math.random() * 2 * Math.PI;
      phi = Math.random() * 2 * Math.PI;
      xNorm = Math.sin(theta) * Math.cos(phi);
      yNorm = Math.sin(theta) * Math.sin(phi);
      zNorm = Math.cos(theta);
    }
    
    //now set up the energy and MFPL stuff for the Monte Carlo simulation
    double electronEnergy = photoelectronEnergy;
    double energyLost = 0;
    surrounding = false;   //I'm just going to focus on the crystal for now and see how long it takes to run, then add in surrounding 
    double stoppingPower = coefCalc.getStoppingPower(photoelectronEnergy, surrounding); //in keV/nm
    
    double startingLambda_el = coefCalc.getElectronElasticMFPL(photoelectronEnergy, surrounding);
    Map<ElementEM, Double> elasticProbs = coefCalc.getElasticProbs(surrounding);
    
    double lambdaT = 0;
    lambdaT = startingLambda_el;
    
    //first distance
    double testRND = Math.random();
    double s = -lambdaT*Math.log(testRND) / 1000; //um
 //   double Pinel = 1 - (lambdaT / startingLambda_el);
    double xn = previousX + s * xNorm;
    double yn = previousY + s * yNorm;
    double zn = previousZ + s * zNorm;
    
    
    boolean exited = false;
    double previousTheta = 0, previousPhi = 0;
    if (photoelectronEnergy < 0.05) {
      exited = true;
    }
    while (exited == false) {
      //convert coordinate to voxel
      int[] pixelCoords = convertToPixelCoordinates(xn, yn, zn);
      if (isCrystalAt(pixelCoords[0], pixelCoords[1], pixelCoords[2]) == true) { //photoelectron still in the crystal
        energyLost = s*1000 * stoppingPower;
        //convert this energy to dose and add it to the middle voxel in the track
        double middleX = previousX + (s/2) * xNorm;
        double middleY = previousY + (s/2) * yNorm;
        double middleZ = previousZ + (s/2) * zNorm;
        addMonteCarloDoseToPixels(energyLost, middleX, middleY, middleZ, doseScale, voxelMass);

        //update position and angle
        //update position and angle
        previousTheta = theta;
        previousPhi = phi;
        previousX = xn;
        previousY = yn;
        previousZ = zn;
        
        //update angle and stuff - for now it is always an elastic interaction
        double elasticElementRND = Math.random();
        ElementEM elasticElement = null;
        for (ElementEM e : elasticProbs.keySet()) {
          if (elasticProbs.get(e) > elasticElementRND) { //Then this element is the one that was ionised
            elasticElement = e;
            break;
          }
        }
        
        //get the angles
        //ELSEPA stuff

        theta = getPrimaryElasticScatteringAngle(electronEnergy, elasticElement.getAtomicNumber());

        //to test a straight line
      //  theta = 0;
        
        theta = previousTheta + theta;
        if (theta >= (2 * Math.PI)) {
          theta -= 2*Math.PI;
        }
        phi = 2 * Math.PI * Math.random();
        
        //testing a straight line
     //   phi = 0;
        
        phi = previousPhi + phi;
        if (phi >= (2 * Math.PI)) {
          phi -= 2*Math.PI;
        }
      //now further update the primary
        
        xNorm = Math.sin(theta) * Math.cos(phi);
        yNorm = Math.sin(theta) * Math.sin(phi);
        zNorm = Math.cos(theta);
        
        //update the energy and stopping Power and stuff
        electronEnergy -= energyLost; 
        stoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
        //get new lambdaT
        double lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, false);
        lambdaT = lambdaEl;
        s = -lambdaT*Math.log(Math.random()) /1000;
        elasticProbs = coefCalc.getElasticProbs(false);
        
        //update to new position
        xn = previousX + s * xNorm;
        yn = previousY + s * yNorm;
        zn = previousZ + s * zNorm;
      }
      else { //it's left the crystal
        exited = true;
        //get the energy deposited before it left the crystal. - when I slice need to also do timestamps 
        double escapeDist = getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm); //nm
        double FSEStoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
        double energyToEdge = FSEStoppingPower * escapeDist*1000;
        if (energyToEdge < electronEnergy){ //the FSE has escaped
          double energyLostStep = 0, totFSEenLostLastStep = 0;
          energyLost = escapeDist * 1000 * stoppingPower;
          double middleX = previousX + (escapeDist/2) * xNorm;
          double middleY = previousY + (escapeDist/2) * yNorm;
          double middleZ = previousZ + (escapeDist/2) * zNorm;
          addMonteCarloDoseToPixels(energyLost, middleX, middleY, middleZ, doseScale, voxelMass);
          double newEnergy = electronEnergy - energyLost;
          /*
          double newEnergy = electronEnergy;
          for (int m = 0; m < 10; m++) { //I will need to play around with the amount of slicing when I am writing up
            energyLostStep = (escapeDist/10)*1000 * FSEStoppingPower;
            //add dose to the pixel in the centre of the slice
            double middleX = previousX + (m*(escapeDist/10) + escapeDist/20) * xNorm;
            double middleY = previousY + (m*(escapeDist/10) + escapeDist/20) * yNorm;
            double middleZ = previousZ + (m*(escapeDist/10) + escapeDist/20) * zNorm;
            addMonteCarloDoseToPixels(energyLostStep, middleX, middleY, middleZ, doseScale, voxelMass);
            
            newEnergy -= energyLostStep;
            FSEStoppingPower = coefCalc.getStoppingPower(newEnergy, false);
            if (newEnergy < 0) {
              break;
            }
          } 
          */
          if (newEnergy > 0) {
            energyEscapedPE += newEnergy;
          }
        }
        else {
          //didn't quite escape, add all to middle pixel
          double middleX = previousX + (escapeDist/2) * xNorm;
          double middleY = previousY + (escapeDist/2) * yNorm;
          double middleZ = previousZ + (escapeDist/2) * zNorm;
          addMonteCarloDoseToPixels(electronEnergy, middleX, middleY, middleZ, doseScale, voxelMass);
        }
      }
      if (electronEnergy < 0.05) {
        exited = true;
      }
    }
    energyEscapedPE = doseScale*(((energyEscapedPE * Beam.KEVTOJOULES) / voxelMass)/1E6);
    return energyEscapedPE;
  }
  
  private void addMonteCarloDoseToPixels(double energyLost, double middleX, double middleY, double middleZ, 
                                        double doseScale, double voxelMass) {
    int[] middlePixelCoords = convertToPixelCoordinates(middleX, middleY, middleZ);
    if (isCrystalAt(middlePixelCoords[0], middlePixelCoords[1], middlePixelCoords[2]) == true) { //needed as could go out and in again
      //then convert energy to dose
      double doseLost = ((energyLost * Beam.KEVTOJOULES) / voxelMass)/1E6; //in  MGy
      //scale this dose up to be representative of all
      double doseToAdd = doseLost * doseScale;
      //add this dose to the pixel
      addDose(middlePixelCoords[0], middlePixelCoords[1], middlePixelCoords[2], doseToAdd);
    }
  }
  
  private double[] convertToCartesianCoordinates(final int i, final int j, final int k) {
    double[] xMinMax = this.minMaxVertices(0, vertices);
    double[] yMinMax = this.minMaxVertices(1, vertices);
    double[] zMinMax = this.minMaxVertices(2, vertices);
    double x = ((i/crystalPixPerUM) + xMinMax[0]);
    double y = ((j/crystalPixPerUM) + yMinMax[0]);
    double z = ((k/crystalPixPerUM) + zMinMax[0]);
    double[] cartesianCoords = {x, y, z};
    return cartesianCoords; // in um
  }
  
  private int[] convertToPixelCoordinates(final double x, final double y, final double z) {
    double[] xMinMax = this.minMaxVertices(0, vertices);
    double[] yMinMax = this.minMaxVertices(1, vertices);
    double[] zMinMax = this.minMaxVertices(2, vertices);
    int i = (int) StrictMath.round(((x) - xMinMax[0]) * crystalPixPerUM);
    int j = (int) StrictMath.round(((y) - yMinMax[0]) * crystalPixPerUM);
    int k = (int) StrictMath.round(((z) - zMinMax[0]) * crystalPixPerUM);
    int[] pixelCoords = {i, j, k};
    return pixelCoords;
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
  
  private int getIonisedShell(Map<Element, double[]> ionisationProbs, Element ionisedElement) {
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
  
  private double getShellBindingEnergy(Element collidedElement, int collidedShell) {
    double shellBindingEnergy = 0;
    switch (collidedShell) {
      case 0: shellBindingEnergy = collidedElement.getKEdge();
              break;
      case 1: shellBindingEnergy = collidedElement.getL1Edge();
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
  
  private double getCosAngleToX( double[] angularEmissionProbs) {
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
  
  public double getPrimaryElasticScatteringAngle(double electronEnergy, int atomicNumber){
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
  
  public InputStreamReader locateFile(String filePath) 
      throws UnsupportedEncodingException, FileNotFoundException{
    InputStream is = getClass().getResourceAsStream("/" + filePath);

    if (is == null) {
      is = new FileInputStream(filePath);
    }

    return new InputStreamReader(is, "US-ASCII");
  }

  public boolean mapPopulated(boolean highEnergy, int atomicNumber) {
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
public TreeMap<Double, double[]> getAngleFileData(boolean highEnergy, int atomicNum) throws IOException{
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


public Double returnNearestEnergy(boolean highEnergy, int atomicNumber, double electronEnergy) {
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

public double returnDeflectionAngle(boolean highEnergy, double[] energyAngleProbs) {
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

private double getIntersectionDistance(double x, double y, double z, double ca, double cb, double cc) {
  if (normals == null) {
    calculateNormals(false);
  }

  double[] directionVector = {ca, cb, cc}; //the actual direction vector
  double minIntersect = 0;
  double[] origin = new double[3];
  origin[0] = x;
  origin[1] = y;
  origin[2] = z;
  
  double intersectionDistance = 0;
  for (int l = 0; l < indices.length; l++) {
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

@Override
public void setELSEPA(CoefCalc coefCalc) {
  Set<Element> presentElements = coefCalc.getPresentElements(false);
  Set<Element> presentElementsSurr = coefCalc.getPresentElements(true);
  if (presentElements != null) {
    for (Element e: presentElements) {
      readELSEPA(e);
    }
  }
  if (presentElementsSurr != null) {
    for (Element e: presentElementsSurr) {
      readELSEPA(e);
    }
  }
 }
  private void readELSEPA(Element e) {
    int atomicNumber = e.getAtomicNumber();
    boolean getFile = mapPopulated(true, atomicNumber);
    
    //get the right file if I need to

    TreeMap<Double, double[]> elementData = new TreeMap<Double, double[]>();
    if (getFile == true) {
    try {
      elementData =  getAngleFileData(true, atomicNumber);
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } 
    highEnergyAngles[atomicNumber] = elementData;
    }
    getFile = mapPopulated(false, atomicNumber);
    if (getFile == true) {
    try {
      elementData =  getAngleFileData(false, atomicNumber);
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    lowEnergyAngles[atomicNumber] = elementData;
    }
  }

  /*
  @Override
  public void simElectron(double i, double j, double k, double numAbsorbedPhotons,
      boolean addBindingEn, CoefCalc coefCalc, double photonEnergy, double angle, boolean surrounding) {
 
    CrystalPolyhedron obj = new PEsim(totalProperties, dose);
    obj.runPEsim(i, j, k, numAbsorbedPhotons, addBindingEn, coefCalc, photonEnergy, angle, surrounding);
    //return dose object
    dose = obj.getThisDose();
  }
*/
  /*
  public abstract void runPEsim(double i, double j, double k, double numAbsorbedPhotons,
      boolean addBindingEn, CoefCalc coefCalc, double photonEnergy, double angle, boolean surrounding);
  
  
  public abstract double[][][] getThisDose();
  */
}

