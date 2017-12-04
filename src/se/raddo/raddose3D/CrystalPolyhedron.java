/**
 *
 */
package se.raddo.raddose3D;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
  
  protected  int flRes;
  
  protected  int peRes;

  /**
   * 3 element array defining dimensions of
   * bounding box of crystal in um.
   */
  protected final double[]      crystSizeUM;

  /** 3 element array defining dimensions of bounding box in voxels. */
  private final int[]           crystSizeVoxels;

  /**
   * Dose and fluence arrays holding the scalar
   * fields for these values at voxel i,j,k.
   */
  private final double[][][]    dose, fluence, elastic;

  /**
   * Escape factor (% of photoelectrons which remain within the crystal)
   * for each voxel coordinate i, j, k.
   */
  private final double[][][]    escapeFactor;

  /**
   * Boolean to determine if escapeFactors have been calculated yet or not.
   */
  private boolean               calculatedEscapeFactors  = false;

  /**
   * Average mean distance travelled by electron.
   */
  private static final double   MEAN_ELECTRON_TRAVEL_DISTANCE = 3.;

  /**
   * Constants for calculation of Gumbel distribution mu and beta parameters.
   */
  private static final double[] GUMBEL_DISTN_CALC_PARAMS = {0.0094,0.0575,0.002,0.0096};
  
  /**
   * Distance bins travelled by a photoelectron.
   */
//  private static final double[]     PE_DISTANCES_TRAVELLED = {0, 1., 1.5, 2., 2.5, //normal
//      3., 3.5, 4., 5., 6., 7., 8. };
 
  private double[]     PE_DISTANCES_TRAVELLED;
  
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
   * length of distance bins travelled by fluorescence.
   */
  private int flDistBins;
  
  /**
   * Max angle for photoelectron direction vectors.
   */
  private static final double   PE_ANGLE_LIMIT = 0.99*2*Math.PI;
  
  /**
   * Number of divisions to split the photoelectron direction vectors.
   */
  private static final int   PE_ANGLE_RESOLUTION = 6;
  
  /**
   * 3d array for voxels where photoelectrons can reach
   */
  private  double[][][] relativeVoxXYZ;

  /**
   * 5d array for voxels where fluorescence can reach
   * the first two dimensions ar element and shell
   */
  //private double[][][][][] flRelativeVoxXYZ;
  private double[][][][] flRelativeVoxXYZ;
  /**
   * Proportion of voxel dose deposited at each distance
   * from voxel due to photoelectron escape
   */
  private  double[]   propnDoseDepositedAtDist;
  
  
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
  private final boolean[][][][] crystOcc;

  /**
   * 4d array where the 4th dimension is a 3 element array with the coordinates
   * of the voxel i,j,k in the starting position.
   */
  private final double[][][][]  crystCoord;

  /**
   * Vertex array containing a variable number of 3-dimension vertices.
   */
  protected double[][]          vertices;

  /* vertices for cuboid of size 90 x 74 x 40 */
  /*
   * = {
   * { -45, -37, 20 },
   * { -45, -37, -20 },
   * { -45, 37, -20 },
   * { -45, 37, 20 },
   * { 45, -37, 20 },
   * { 45, -37, -20 },
   * { 45, 37, -20 },
   * { 45, 37, 20 }
   * };
   */

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
  private int[][]               indices;

  /**
   * Similar in style to the index array, except each index is replaced
   * by the corresponding rotatedVertex.
   */
  private double[][][]          expandedRotatedVertices;

  /* Indices for cuboid */
  /*
   * = {
   * { 1, 3, 2 },
   * { 4, 3, 1 },
   * { 3, 6, 2 },
   * { 7, 6, 3 },
   * { 2, 5, 1 },
   * { 2, 6, 5 },
   * { 4, 8, 3 },
   * { 8, 7, 3 },
   * { 4, 1, 8 },
   * { 1, 5, 8 },
   * { 8, 5, 7 },
   * { 7, 5, 6 }
   * };
   */

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

      // assuming direction vector is always (0, 0, 1)

      // double directionNormalDotProduct = directionVector[2] * normalUnitVector[2];

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
        flDistBins = 4;
      }
    }
   if (photoElectronEscape) {
    peRes = (int) mergedProperties.get(Crystal.CRYSTAL_PHOTOELECTRON_RESOLUTION);
   }
    /*
    // Adjusting PE max distance based on beam energy
    int maxPEDistance = 8;
 //   double meanPEDistance = 
    
    
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
      }
     }
 // Get PE distances
    PE_DISTANCES_TRAVELLED = new double[peDistBins];
    double binInterval = (double) maxPEDistance / (peDistBins - 1);
    for (int i = 0; i < peDistBins; i++) {
      PE_DISTANCES_TRAVELLED[i] = i * binInterval;
    } 
    }
   propnDoseDepositedAtDist = new double[peDistBins];
   relativeVoxXYZ = new double[peDistBins][PE_ANGLE_RESOLUTION * PE_ANGLE_RESOLUTION][3];
   */
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
      
      gumbelDistribution[i] = Math.exp(-(z + Math.exp(-z))) / beta;
    }
  }
  
  /**
   * Gets the distance distribution for a photoelectron
   * 
   * @param beamEnergy
   * @return
   */ //Should beam energy be used??? In Sanishvili 2011 they took off 0.7keV as the average energy of K shell electron binding. Is this better?
  private double[] getGumbelParamsForBeamEnergy(final double beamEnergy) {
    // Gumbel distribution for mean photoelectron path lengths depend on 
    // beam energy. Derived from Ben Gayther's 2016 summer project work
    double energyCorrection = EnergyToSubtractFromPE;
    
    double[] gumbParams = new double[2];
    gumbParams[0] = GUMBEL_DISTN_CALC_PARAMS[0]*Math.pow(beamEnergy - energyCorrection,2) 
        + GUMBEL_DISTN_CALC_PARAMS[1]*(beamEnergy - energyCorrection); //mu = mean path length of a PE
    gumbParams[1] = GUMBEL_DISTN_CALC_PARAMS[2]*Math.pow(beamEnergy - energyCorrection,2) 
        + GUMBEL_DISTN_CALC_PARAMS[3]*(beamEnergy - energyCorrection); //beta - breaks down after 20keV
    
    //Try and better approximate beta for 22-30keV
    if (beamEnergy - energyCorrection >= 22 && beamEnergy - energyCorrection <= 30) {
      gumbParams[1] = 1.4; // beta = 1.4keV
    }
    
    return gumbParams;
  }
  
  /**
   * Calculates the fluorescence distribution for each wavelength emitted
   * 
   * @param feFactors
   */
  private void calcFluorescenceDistribution(final double[][] feFactors) {

    //This will look horrible do now and clean up later
    //It is also currently just dropping energy at each point not losing it gradually along the way so need to check this is right
    //may be doing for every voxel so check
    
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
        
        //To test
      //   maxDistanceFl = 40;
        
        
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
  private  void setMaxPEDistance(final double beamEnergy) { //needs to be dynamic as it depends on beam energy
    double averagePEDistance = GUMBEL_DISTN_CALC_PARAMS[0]*Math.pow(beamEnergy - EnergyToSubtractFromPE,2) 
        + GUMBEL_DISTN_CALC_PARAMS[1]*(beamEnergy - EnergyToSubtractFromPE);
    int maxPEDistance = (int) Math.ceil(averagePEDistance + 6);
    
    //TO TEST against old
   // maxPEDistance = 8;
    
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
      }
     }
 // Get PE distances
    PE_DISTANCES_TRAVELLED = new double[peDistBins];
    double binInterval = (double) maxPEDistance / (peDistBins - 1);
    for (int i = 0; i < peDistBins; i++) {
      PE_DISTANCES_TRAVELLED[i] = i * binInterval;
    } 
    
   propnDoseDepositedAtDist = new double[peDistBins];
   relativeVoxXYZ = new double[peDistBins][PE_ANGLE_RESOLUTION * PE_ANGLE_RESOLUTION][3];
  }
  
  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#setPEparamsForCurrentBeam(double)
   */
  @Override
  public void setPEparamsForCurrentBeam(final double beamEnergy) {
    // Initialise crystal photolectron escape properties here for current beam
    
    //first of all need to get PE distances 
    setMaxPEDistance(beamEnergy);
    findVoxelsReachedByPE();
    calcProportionVoxDoseDepositedByDist(beamEnergy);  
  }
  
  @Override
  public void setFLparamsForCurrentBeam(final double[][] feFactors) {
    // Initialise crystal fl escape properties here for current beam
    //find voxels is done with pe
    calcFluorescenceDistribution(feFactors);  
    findVoxelsReachedByFL(feFactors);
  }
  
  private void calcProportionVoxDoseDepositedByDist(final double beamEnergy) {
    // calculate the fraction of energy deposited by PE up to each 
    // distance, assuming PE distances follow a given distribution

    // Set up a mean path length distribution
    double[] pathLengthDistn = new double[peDistBins];
        
    double[] distnParams = getGumbelParamsForBeamEnergy(beamEnergy);
    calculateGumbelDistribution(PE_DISTANCES_TRAVELLED, pathLengthDistn, distnParams[0],
        distnParams[1], peDistBins);
    
    // find total area under specified distribution
    double distnIntegral = 0;
    for (int l = 0; l < peDistBins-1; l++) {
      double width = PE_DISTANCES_TRAVELLED[l + 1] - PE_DISTANCES_TRAVELLED[l];
      double height = (pathLengthDistn[l + 1] + pathLengthDistn[l]) / 2;
      distnIntegral += width * height;
    }  
    
    /*
     * The following code calculates the proportion of dose deposited along
     * each track by the travelling PE
     */  
    for (int l = peDistBins-1; l > 0; l--) {
      double width = PE_DISTANCES_TRAVELLED[l] - PE_DISTANCES_TRAVELLED[l-1];
      double height = (pathLengthDistn[l] + pathLengthDistn[l-1]) / 2;

      if (l == peDistBins-1) 
      {
        // the proportion of energy deposited at the end of the PE track. The division
        // by l is since the energy carried by PEs is deposited linearly at
        // l divisions along the track up to this point
        propnDoseDepositedAtDist[l] = width * height / (l * distnIntegral);
      } else {
        // the amount of energy deposited by PEs stopping at this distance (divided
        // by l to account for energy being deposited linearly up to this point),
        // plus the energy deposited by PEs travelling further than this stopping 
        // distance that also deposit a fraction of their energy at this distance
        propnDoseDepositedAtDist[l] = propnDoseDepositedAtDist[l+1] 
            + width * height / (l * distnIntegral);
      }
  
    }
  }
  
  /**
   * finds voxels that lie along the PE tracks
   */
  private void findVoxelsReachedByPE() {
    double step = PE_ANGLE_LIMIT / PE_ANGLE_RESOLUTION;  
    
    int counter = -1;
    for (double theta = 0; theta < PE_ANGLE_LIMIT; theta += step) {
      for (double phi = 0; phi < PE_ANGLE_LIMIT; phi += step) {
        // calculate x, y, z coordinates of voxel[i][j][k]
        // plus the polar coordinates for r, theta, phi
        counter += 1;
        double xNorm = Math.sin(theta) * Math.cos(phi);
        double yNorm = Math.sin(theta) * Math.sin(phi);
        double zNorm = Math.cos(theta);
        
        for (int m = 0; m < peDistBins; m++) {
          // calculate r in voxel coordinates rather than pixels
          double r = PE_DISTANCES_TRAVELLED[m] * this.crystalPixPerUM; 
          relativeVoxXYZ[m][counter][0] = r * xNorm;
          relativeVoxXYZ[m][counter][1] = r * yNorm;
          relativeVoxXYZ[m][counter][2] = r * zNorm;
        }

      }
    }
  }
  /**
   * Finds voxels that lie along fluorescence tracks
   * 
   * @param feFactors
   */
  private void findVoxelsReachedByFL(final double feFactors[][]) {
double step = PE_ANGLE_LIMIT / PE_ANGLE_RESOLUTION;  
//flRelativeVoxXYZ = new double[feFactors.length][4][flDistBins][PE_ANGLE_RESOLUTION * PE_ANGLE_RESOLUTION][3];
flRelativeVoxXYZ = new double[feFactors.length][flDistBins][PE_ANGLE_RESOLUTION * PE_ANGLE_RESOLUTION][3];
    int counter = -1;
    for (double theta = 0; theta < PE_ANGLE_LIMIT; theta += step) {
      for (double phi = 0; phi < PE_ANGLE_LIMIT; phi += step) {
        // calculate x, y, z coordinates of voxel[i][j][k]
        // plus the polar coordinates for r, theta, phi
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
  

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#addDoseAfterPE(int, int, int, double, double)
   */
  @Override
  public double addDoseAfterPE(final int i, final int j, final int k,
      final double doseIncreasePE) {
       
    double doseLostFromCrystalPE = 0;
 
    /* 
     * NOTE: currently the code will run the PE escape code for every voxel
     * in the crystal. For large crystals or when the pixels per micron value
     * is large, this will take a very large time to run. The boolean below
     * instead allows the PE escape code to run only for voxels determined
     * to be close to the voxel boundaries. This is not set by default, since
     * it appears that the pixels per micron value can significantly change
     * which voxels are assigned as close to surface (thus significantly changing
     * the reported dose). 
     * TODO: find a rigorous way to assign voxels close to surface
    */ 
    
    boolean findCloseToSurface = false; // this works if the pixel size is smaller than 8 
    boolean closeToSurface = false;    
    double pixelSize = 1/crystalPixPerUM;
    // This will ensure that close to surface is only used when valid
    if (pixelSize > PE_DISTANCES_TRAVELLED[peDistBins - 1]) { //if pixel size more than max distance of a PE
      findCloseToSurface = false;
    }
      else {
        findCloseToSurface = true;
    }
    
    //TO TEST
//    findCloseToSurface = false;
    
    if (findCloseToSurface) {
      // calculate whether this voxel classified as close to surface and  
      // PE escape should be considered
      for (int n = 0; n < indices.length; n++) {
        double[] voxCoord = getCrystCoord(i, j, k);
        double distanceToPlane = Vector.rayTraceDistance(normals[n],
            normals[n], voxCoord, originDistances[n]);
        if (distanceToPlane < PE_DISTANCES_TRAVELLED[peDistBins - 1]) {
          closeToSurface = true;
          break;
        } 
      }
    } else {
      closeToSurface = true;
    }
    
    if (!closeToSurface) {
      // if not close to surface, add all dose to voxel 
      dose[i][j][k] += doseIncreasePE;
      
    } else {
      // if voxel is within the specified min distance to surface, take a grid 
      // of r, theta and phi and calculate for every r within 0.5 um and 
      // 5 um, what proportion exit the crystal.
               
      for (int m = 0; m < peDistBins; m++) {   
        for (int q = 0; q < PE_ANGLE_RESOLUTION*PE_ANGLE_RESOLUTION; q++) { 
          double x = relativeVoxXYZ[m][q][0];
          double y = relativeVoxXYZ[m][q][1];
          double z = relativeVoxXYZ[m][q][2];

          // get dose transferred to these located voxels 
          // at the distance r away (due to PE movement)
          double partialDose = doseIncreasePE * propnDoseDepositedAtDist[m]
              / Math.pow(PE_ANGLE_RESOLUTION,2);
          
          // add counts to total & total within crystal in order to
          // calculate the proportion for a given r.       
          if (isCrystalAt((int) (i + x), (int) (j + y),
              (int) (k + z))) {              
            // get dose transferred to this new voxel (due to PE movement)
            addDose((int) (i + x), (int) (j + y),
            (int) (k + z), partialDose);
          } else {
            doseLostFromCrystalPE += partialDose;
            peRelease += partialDose;
          }
        }    
      }
    }
    return doseLostFromCrystalPE;
  } 
  
  @Override
  public double addDoseAfterFL(final int i, final int j, final int k, //!!!!!!!!!Needs lots of changing!!
      final double doseIncreaseFL) {
   double doseLostFromCrystalFL = 0;
   //for every energy distribution
    for (int n = 0; n < fluorescenceProportionEvent.length; n++) { 
   //   for (int l = 0; l < 4; l++) { // 0 = K, 1 = L1, 2 = L2, 3 = L3
    for (int m = 0; m < flDistBins; m++) {   
      for (int q = 0; q < PE_ANGLE_RESOLUTION*PE_ANGLE_RESOLUTION; q++) { 

        double flPartialDose = 0;
        // get dose transferred to these located voxels 
        // at the distance r away (due to PE movement)
        
     //     if(fluorescenceProportionEvent[n][l] != 0) {
            if(fluorescenceProportionEvent[n] != 0) {
   //     flPartialDose = doseIncreaseFL  * fluorescenceProportionEvent[n][l] * flDistanceDistribution[n][l][m] 
   //         / Math.pow(PE_ANGLE_RESOLUTION,2);
          flPartialDose = doseIncreaseFL  * fluorescenceProportionEvent[n] * flDistanceDistribution[n][m] 
              / Math.pow(PE_ANGLE_RESOLUTION,2);
              
        //TO TEST
         fldose += flPartialDose;
      /*  
        double x = flRelativeVoxXYZ[n][l][m][q][0];
        double y = flRelativeVoxXYZ[n][l][m][q][1];
        double z = flRelativeVoxXYZ[n][l][m][q][2];
        */
         
         double x = flRelativeVoxXYZ[n][m][q][0];
         double y = flRelativeVoxXYZ[n][m][q][1];
         double z = flRelativeVoxXYZ[n][m][q][2];
         
        // add counts to total & total within crystal in order to
        // calculate the proportion for a given r.     
        if (isCrystalAt((int) (i + x), (int) (j + y),
            (int) (k + z))) {              
          // get dose transferred to this new voxel (due to FL movement)
          addDose((int) (i + x), (int) (j + y),
          (int) (k + z), flPartialDose);
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
  
  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#getCrystCoord(int, int, int)
   */
  @Override
  public double[] getCrystCoord(final int i, final int j, final int k) {
    return crystCoord[i][j][k];
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
}
