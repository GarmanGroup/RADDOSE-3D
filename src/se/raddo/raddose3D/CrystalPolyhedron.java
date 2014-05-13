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
  private final double        crystalPixPerUM;

  /**
   * Initial orientation of the crystal in the plane of the loop (right handed
   * rotation about z) and of the loop (right handed rotation about x) in
   * radians.
   */
  private final double        p, l;

  /**
   * 3 element array defining dimensions of
   * bounding box of crystal in um.
   */
  private final double[]      crystSizeUM;

  /** 3 element array defining dimensions of bounding box in voxels. */
  private final int[]         crystSizeVoxels;

  /**
   * Dose and fluence arrays holding the scalar
   * fields for these values at voxel i,j,k.
   */
  private double[][][]        dose, fluence, elastic;

  /**
   * A boolean (int for extensibility to deeper segmentation) array. 0 = empty,
   * 1 = crystal.
   */
  private final boolean[][][] crystOcc;

  /**
   * 4d array where the 4th dimension is a 3 element array with the coordinates
   * of the voxel i,j,k in the starting position.
   */
  private double[][][][]      crystCoord;

  /**
   * Vertex array containing a variable number of 3-dimension vertices.
   * Currently set to a default approx. tetrahedron for testing purposes.
   */
  private final double[][]    vertices = {
                                       { 0, 0, 0 },
                                       { 2, 0, 0 },
                                       { 1, 2, 0 },
                                       { 1, 1, 2 }
                                       };

  /**
   * Index array displaying connectivity of vertex array.
   * These indices must go clockwise to ensure correct calculation
   * of normal vectors.
   * In groups of 3 - triangles only please, no octagon nonsense.
   */
  private final int[][]       indices  = {
                                       { 2, 1, 3 },
                                       { 1, 3, 4 },
                                       { 1, 4, 2 },
                                       { 4, 3, 2 }
                                       };

  /**
   * Normal array holding normalised direction vectors for
   * each triangle specified by the index array.
   * Contains an i, j, k vector per triangle.
   * Should have same no. of entries as the indices array.
   */
  private double[][]          normals;

  /**
   * Distances from origin for each of the triangle planes.
   * Should have same no. of entries as the indices array.
   */
  private double[]            originDistances;

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
    static public double vectorMagnitude(final double[] vector)
    {
      double distance = Math.pow(vector[0], 2) + Math.pow(vector[1], 2)
          + Math.pow(vector[2], 2);

      distance = Math.sqrt(distance);

      return distance;
    }

    /**
     * returns 3D vector between FROM and TO points.
     * 
     * @param from from point
     * @param to to point
     * @return vector between points.
     */
    static public double[] vectorBetweenPoints(final double[] from,
        final double[] to)
    {
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
    static public double[] crossProduct(final double[] vector1,
        final double[] vector2)
    {
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
    static public double[] normalisedCrossProduct(final double[] vector1,
        final double[] vector2)
    {
      double[] newVector = crossProduct(vector1, vector2);
      double magnitude = vectorMagnitude(newVector);

      for (int i = 0; i < 3; i++) {
        newVector[i] /= magnitude;
      }

      return newVector;
    }

    /**
     * returns dot product between two 3D vectors
     * 
     * @param vector1 vector1
     * @param vector2 vector2
     * @return dot product
     */
    static public double dotProduct(final double[] vector1,
        final double[] vector2)
    {
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
    static public double[] rayTraceToPoint(final double[] normalUnitVector,
        final double[] directionVector, final double[] origin,
        final double planeDistance)
    {
      double t = rayTraceDistance(normalUnitVector, directionVector, origin,
          planeDistance);

      double[] point = new double[3];

      for (int i = 0; i < 3; i++)
        point[i] = origin[i] + t * directionVector[i];

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
    static public double rayTraceDistance(final double[] normalUnitVector,
        final double[] directionVector, final double[] origin,
        final double planeDistance) {
      double d = normalUnitVector[0] * origin[0] + normalUnitVector[1]
          * origin[1] + normalUnitVector[2] * origin[2] + planeDistance;

      double originNormalDotProduct = dotProduct(origin, normalUnitVector);
      double directionNormalDotProduct = dotProduct(directionVector,
          normalUnitVector);

      double t = -(originNormalDotProduct + d) / directionNormalDotProduct;

      return t;
    }

    /**
     * Original C code
     * http://www.ecse.rpi.edu/~wrf/Research/Short_Notes/pnpoly.html
     * Takes an array of vertices of a polygon and determines whether a point
     * is contained within the polygon or not. Ignores the z axis at the
     * moment.
     * @param vertices array of 3D vertices
     * @param point point to test inclusion - must be in same plane
     *        as vertices
     * @return boolean value - in polygon or not in polygon.
     */
    public boolean polygonInclusionTest(double[][] vertices, double[] point) {
      int i, j = 0;
      boolean c = false;
      
      for (i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
        if ( ((vertices[i][1] > point[1]) != (vertices[j][1] > point[1])) &&
            (point[0] < (vertices[j][0] - vertices[i][0]) * (point[1] - vertices[i][1]) /
                (vertices[j][1] - vertices[i][1]) + vertices[i][0])) {
          
          c = !c;
        }
      }
      
      return c;
    }
  }

  /**
   * Code shamefully copied from @link CrystalCuboid class
   * but frankly, CrystalCuboid is doomed once this class is implemented.
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

  /**
   * Calculates normal array from index and vertex arrays.
   * Also calculates signed distances of each triangle
   * from the origin.
   */
  public void calculateNormals()
  {
    normals = new double[indices.length][3];

    for (int i = 0; i < indices.length; i++)
    {
      // get the three vertices which this triangle corresponds to.
      double[] point1 = vertices[indices[i][0] - 1];
      double[] point2 = vertices[indices[i][0] - 1];
      double[] point3 = vertices[indices[i][0] - 1];

      // get two vectors which can be used to define our plane.

      double[] vector1 = Vector.vectorBetweenPoints(point1, point2);
      double[] vector2 = Vector.vectorBetweenPoints(point1, point3);

      // get the normal vector between these two vectors.

      double[] normalVector = Vector.normalisedCrossProduct(vector1, vector2);

      // copy this vector into the normals array at the given point.      
      System.arraycopy(normalVector, 0, normals[i], 0, 3);

      double distanceFromOrigin = -(normalVector[0] * point1[0]
          + normalVector[1] * point1[1] + normalVector[2] * point1[2]);

      originDistances[i] = distanceFromOrigin;
    }

  }

  /**
   * Calculates crystal occupancy at i, j, k, returns value
   * and sets crystOcc at a given i, j, k
   * 
   * @param i i
   * @param j j
   * @param k k
   * @return crystal occupancy flag
   */
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
