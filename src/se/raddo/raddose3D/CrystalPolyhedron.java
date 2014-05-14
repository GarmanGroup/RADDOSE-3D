/**
 *
 */
package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author magd3052
 */
public class CrystalPolyhedron extends Crystal {

  /** Resolution of crystal in 1/um. */
  private final double          crystalPixPerUM;

  /**
   * Initial orientation of the crystal in the plane of the loop (right handed
   * rotation about z) and of the loop (right handed rotation about x) in
   * radians.
   */
  private final double          p, l;

  /**
   * 3 element array defining dimensions of
   * bounding box of crystal in um.
   */
  private final double[]        crystSizeUM;

  /** 3 element array defining dimensions of bounding box in voxels. */
  private final int[]           crystSizeVoxels;

  /**
   * Dose and fluence arrays holding the scalar
   * fields for these values at voxel i,j,k.
   */
  private double[][][]          dose, fluence, elastic;

  /**
   * A boolean (int for extensibility to deeper segmentation) array.
   * Fourth dimension is a two dimensional array, first element
   * is a flag (calculated/not calculated) and second element is
   * a boolean (crystal/not crystal).
   */
  private final boolean[][][][] crystOcc;

  /**
   * 4d array where the 4th dimension is a 3 element array with the coordinates
   * of the voxel i,j,k in the starting position.
   */
  private double[][][][]        crystCoord;

  /**
   * Vertex array containing a variable number of 3-dimension vertices.
   * Currently set to a default approx. tetrahedron for testing purposes.
   */
  private final double[][]      vertices = {
                                         { -45, -37, 20 },
                                         { -45, -37, -20 },
                                         { -45, 37, -20 },
                                         { -45, 37, 20 },
                                         { 45, -37, 20 },
                                         { 45, -37, -20 },
                                         { 45, 37, -20 },
                                         { 45, 37, 20 }
                                         };

  private double[][]            rotatedVertices;

  /**
   * Index array displaying connectivity of vertex array.
   * These indices must go clockwise to ensure correct calculation
   * of normal vectors.
   * In groups of 3 - triangles only please, no octagon nonsense.
   */
  private final int[][]         indices  = {
                                         { 1, 3, 2 },
                                         { 4, 3, 1 },
                                         { 3, 6, 2 },
                                         { 7, 6, 3 },
                                         { 2, 5, 1 },
                                         { 2, 6, 5 },
                                         { 4, 8, 3 },
                                         { 8, 7, 3 },
                                         { 4, 1, 8 },
                                         { 1, 5, 8 },
                                         { 8, 5, 7 },
                                         { 7, 5, 6 }
                                         };

  /**
   * Normal array holding normalised direction vectors for
   * each triangle specified by the index array.
   * Contains an i, j, k vector per triangle.
   * Should have same no. of entries as the indices array.
   */
  private double[][]            normals  = null, rotatedNormals = null;

  /**
   * Distances from origin for each of the triangle planes.
   * Should have same no. of entries as the indices array.
   */
  private double[]              originDistances, rotatedOriginDistances = null;

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
      //     double d = Math.abs(normalUnitVector[0] * origin[0] + normalUnitVector[1]
      //         * origin[1] + normalUnitVector[2] * origin[2] + planeDistance);

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
            &&
            (point[0] < (vertices[j][0] - vertices[i][0])
                * (point[1] - vertices[i][1]) /
                (vertices[j][1] - vertices[i][1]) + vertices[i][0])) {
          c = !c;
        }
      }

      // however if it's on the edge we want it to be a YES...
/*
      if (!c) {
        for (int i = 0, j = 1; i < 3; j = (i++ % 3))
        {
          double[] edge, edge2 = new double[3];
          edge = vectorBetweenPoints(vertices[i], vertices[j]);
          edge2 = vectorBetweenPoints(vertices[i], point);

          double scale0 = edge2[0] / edge[0];
          double scale1 = edge2[1] / edge[1];

          if (scale0 != scale1) {
            continue;
          }

          double scale2 = edge[2] / point[2];

          if (scale1 != scale2) {
            continue;
          } else {
            return true;
          }
        }
      }*/

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
  public CrystalPolyhedron(final Map<Object, Object> properties) {
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

    // Initialise crystal occupancy to correct size
    crystOcc = new boolean[nx][ny][nz][2];

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
  public void calculateNormals(boolean rotated) {

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

    if (!rotated) {
      originDistances = new double[indices.length];
      normals = new double[indices.length][3];

      for (int i = 0; i < normalsUsed.length; i++) {
        System.arraycopy(normalsUsed[i], 0, normals[i], 0, 3);
      }

      System.arraycopy(originDistancesUsed, 0, originDistances, 0,
          indices.length);
    } else {
      rotatedOriginDistances = new double[indices.length];
      rotatedNormals = new double[indices.length][3];

      for (int i = 0; i < normalsUsed.length; i++) {
        System.arraycopy(normalsUsed[i], 0, rotatedNormals[i], 0, 3);
      }

      System.arraycopy(originDistancesUsed, 0, rotatedOriginDistances, 0,
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
    if (normals == null)
      calculateNormals(false);

    boolean inside = false;

    double[] directionVector = { 0, 0, 1 };
    double[] origin = crystCoord[i][j][k];

    for (int l = 0; l < indices.length; l++) {
      double intersectionDistance = Vector.rayTraceDistance(normals[l],
          directionVector, origin, originDistances[l]);

      Double distanceObject = Double.valueOf(intersectionDistance);

      if (intersectionDistance < 0 || distanceObject.isNaN()
          || distanceObject.isInfinite())
        continue;

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

    if (inside) {
      //     System.out.println("Inside is true for " + i + ", " + j + ", " + k);
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

    for (int i = 0; i < indices.length; i++)
    {
      double intersectionDistance = (-1)
          * Vector.rayTraceDistance(rotatedNormals[i],
              zAxis, voxCoord, rotatedOriginDistances[i]);

      Double distanceObject = Double.valueOf(intersectionDistance);

      if (intersectionDistance <= 0 || distanceObject.isNaN()
          || distanceObject.isInfinite())
        continue;

      double[] intersectionPoint = Vector.rayTraceToPointWithDistance(
          zAxis, voxCoord, intersectionDistance);

      double[][] triangleVertices = new double[3][3];

      // copy vertices referenced by indices into single array for
      // passing onto the polygon inclusion test.
      for (int m = 0; m < 3; m++) {
        System.arraycopy(vertices[indices[i][m] - 1], 0, triangleVertices[m],
            0, 3);
      }

      boolean crosses = Vector.polygonInclusionTest(triangleVertices,
          intersectionPoint);

      if (crosses) {
        distancesFound.add(Double.valueOf(intersectionDistance));
      }
    }

    Collections.sort(distancesFound);

    for (int i = 0; i < distancesFound.size() - 1; i++)
    {
      if (distancesFound.get(i + 1) == distancesFound.get(i))
        distancesFound.remove(i + 1);
    }

    // sanity check that point is within crystal
    if (distancesFound.size() == 0 || distancesFound.size() % 2 == 0) {
      return 0;
    }

    double depth = distancesFound.get(0).doubleValue();

    for (int i = 1; i < distancesFound.size(); i += 2) {
      Double addition = distancesFound.get(i + 1) - distancesFound.get(i);

      depth += addition.doubleValue();
    }
/*
    if (deltaPhi == 0) {
      System.out.println(voxCoord[0] + "\t" + voxCoord[1] + "\t" + voxCoord[2]
          + "\t" + depth);
    }
*/
    return depth;
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
    dose[i][j][k] += doseIncrease;
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
    // TODO Auto-generated method stub
    return "Insert info";
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

}
