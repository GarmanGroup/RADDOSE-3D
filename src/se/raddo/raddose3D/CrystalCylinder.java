package se.raddo.raddose3D;

import java.util.Map;

public class CrystalCylinder extends CrystalPolyhedron {

  /**
   * Conversion factor from microns to millimeters
   */
  private static final double CONVERSION_MICRONS_TO_MILLIMETERS = 1e-3;

  @Override
  public void loadVertices(final Map<Object, Object> mergedProperties) {
    int[][] tempIndices = {
        { 2, 4, 3},
        { 3, 4, 6},
        { 6, 8, 7},
        { 8, 10, 9},
        { 10, 12, 11},
        { 12, 14, 13},
        { 14, 16, 15},
        { 15, 16, 18},
        { 18, 20, 19},
        { 20, 22, 21},
        { 21, 22, 24},
        { 24, 26, 25},
        { 26, 28, 27},
        { 27, 28, 30},
        { 30, 32, 31},
        { 32, 34, 33},
        { 34, 36, 35},
        { 35, 36, 38},
        { 38, 40, 39},
        { 39, 40, 42},
        { 42, 44, 43},
        { 44, 46, 45},
        { 45, 46, 48},
        { 48, 50, 49},
        { 49, 50, 52},
        { 52, 54, 53},
        { 53, 54, 56},
        { 56, 58, 57},
        { 58, 60, 59},
        { 59, 60, 62},
        { 62, 38, 22},
        { 64, 2, 1},
        { 62, 64, 63},
        { 53, 55, 63},
        { 1, 2, 3},
        { 5, 3, 6},
        { 5, 6, 7},
        { 7, 8, 9},
        { 9, 10, 11},
        { 11, 12, 13},
        { 13, 14, 15},
        { 17, 15, 18},
        { 17, 18, 19},
        { 19, 20, 21},
        { 23, 21, 24},
        { 23, 24, 25},
        { 25, 26, 27},
        { 29, 27, 30},
        { 29, 30, 31},
        { 31, 32, 33},
        { 33, 34, 35},
        { 37, 35, 38},
        { 37, 38, 39},
        { 41, 39, 42},
        { 41, 42, 43},
        { 43, 44, 45},
        { 47, 45, 48},
        { 47, 48, 49},
        { 51, 49, 52},
        { 51, 52, 53},
        { 55, 53, 56},
        { 55, 56, 57},
        { 57, 58, 59},
        { 61, 59, 62},
        { 2, 64, 4},
        { 64, 62, 14},
        { 58, 54, 60},
        { 58, 56, 54},
        { 54, 52, 50},
        { 48, 46, 44},
        { 42, 48, 44},
        { 42, 40, 38},
        { 34, 32, 36},
        { 30, 22, 32},
        { 26, 22, 28},
        { 26, 24, 22},
        { 22, 20, 18},
        { 14, 22, 16},
        { 10, 8, 12},
        { 54, 62, 60},
        { 64, 6, 4},
        { 8, 14, 12},
        { 22, 30, 28},
        { 38, 48, 42},
        { 38, 36, 22},
        { 6, 64, 8},
        { 61, 62, 63},
        { 36, 32, 22},
        { 54, 38, 62},
        { 38, 50, 48},
        { 38, 54, 50},
        { 8, 64, 14},
        { 63, 64, 1},
        { 22, 18, 16},
        { 62, 22, 14},
        { 63, 1, 3},
        { 3, 5, 7},
        { 15, 9, 13},
        { 15, 17, 19},
        { 21, 23, 31},
        { 23, 25, 27},
        { 27, 29, 31},
        { 31, 33, 35},
        { 35, 37, 39},
        { 39, 41, 43},
        { 47, 51, 45},
        { 47, 49, 51},
        { 53, 39, 43},
        { 59, 63, 57},
        { 59, 61, 63},
        { 7, 31, 3},
        { 9, 11, 13},
        { 63, 55, 57},
        { 23, 27, 31},
        { 39, 63, 35},
        { 45, 51, 53},
        { 15, 7, 9},
        { 15, 19, 21},
        { 7, 21, 31},
        { 15, 21, 7},
        { 39, 53, 63},
        { 31, 63, 3},
        { 63, 31, 35},
        { 43, 45, 53}
    };

    Double radius = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_X) / 2;
    Double height = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_Y);

    /*
     * A cylinder needs two numbers, not three: the diameter of its circular
     * cross-section and its axial length. Those are taken from the first two
     * values of Dimensions, so a third is meaningless here.
     *
     * It used to be read and silently discarded, so "Dimensions 40 40 20"
     * gave a 40 um tall cylinder rather than the 20 um the third value
     * suggests -- twice the volume, with nothing on screen to say so. Saying
     * which value was used costs nothing and removes the trap.
     */
    Double unusedDepth = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_Z);
    if (unusedDepth != null && !unusedDepth.equals(height)) {
      System.out.println(String.format(
          "Warning: a cylinder is described by two dimensions, so the third "
              + "value of Dimensions (%.2f um) is ignored. The cylinder is "
              + "%.2f um across and %.2f um long, taken from the first two.",
          unusedDepth, radius * 2, height));
    }

    double[][] tempVertices = createCylinderVertices(radius, height);

    setIndices(tempIndices);

    vertices = new double[tempVertices.length][3];

    for (int i = 0; i < tempVertices.length; i++) {
      System.arraycopy(tempVertices[i], 0, vertices[i], 0, 3);
    }
  }

  /**
   * Creates the vertices required for the cylinder object
   * @param radius of the circular cross-section of the cylinder
   * @param height - This is the axial length of the cylinder
   * @return an nx3 array (where n is the number of vertices) containing
   * the x,y,z coordinates of each vertex
   */
  private double[][] createCylinderVertices(Double radius, Double height) {

    // Set the default number of half the amount of vertices for the cylinder
    int numOfVertices = 32;

    //Create y Coordinates for the base and the top of the cylinder
    double midPoint = height / 2;
    double yCoordBase = -midPoint;
    double yCoordTop = midPoint;

    // Calculate angular step around circle.
    // The negative sign is used to go anti clockwise around the circle to be
    // consistent with the output from BLENDER software.
    double angleToVertex = -2 * Math.PI/numOfVertices;

    //Create variable to store the x,y,z coordinates of each vertex.
    //Note that there are two circles for the cylinder: one at the base and one
    //at the top. Hence the total number of vertices is 2 * numOfVertices
    double[][] vertices = new double[2 * numOfVertices][3];

    /*
     * The circle is laid out directly in the xz plane, with the axis along y.
     *
     * It used to be built about x and then turned a quarter turn about z.
     * That turn is exact in algebra and not in arithmetic: Math.cos of
     * Math.toRadians(90) is 6.12e-17, not zero, so every vertex picked up a
     * y displacement of that times its distance from the axis. The two flat
     * caps came out tilted by 6e-17 radians instead of lying flat.
     *
     * A tilt that small sounds harmless and is not. calculateCrystalOccupancy
     * traces a ray along z and skips any face the ray runs parallel to; a cap
     * that is tilted is no longer parallel, so it stops being skipped and
     * starts returning an intersection distance computed as one rounding
     * error divided by another. The voxels whose centres sit exactly on a cap
     * -- a whole plane of them whenever the height is a round multiple of the
     * voxel size -- then get an arbitrary extra crossing, and an arbitrary
     * half of them fall out of the crystal.
     *
     * For "Type Cylinder / Dimensions 20 16" at 0.5 voxels/um that was 42 of
     * 518 voxels, 8% of the crystal, and 6% on the absorbed energy and the
     * elastic yield. Worse, Math.cos and Math.sin are not required to be
     * bit-reproducible across JVMs, so which voxels were lost varied between
     * platforms. Laying the circle out in xz directly makes the caps exactly
     * flat, and the ray parallel to them again.
     */
    for (int vertex = 0; vertex < numOfVertices; vertex++){
      //Calculate points around the circle
      double xCoord = -radius * Math.cos(vertex * angleToVertex);
      double zCoord = radius * Math.sin(vertex * angleToVertex);

      //Add points to the vertices array for the Base
      vertices[2 * vertex][0] = xCoord;
      vertices[2 * vertex][1] = yCoordBase;
      vertices[2 * vertex][2] = zCoord;

      //Add points to the vertices array for the Top
      vertices[2 * vertex + 1][0] = xCoord;
      vertices[2 * vertex + 1][1] = yCoordTop;
      vertices[2 * vertex + 1][2] = zCoord;
    }

    return vertices;
  }
  

  public CrystalCylinder(final Map<Object, Object> properties) {
    super(properties);
  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#crystalInfo()
   */
  @Override
  public String crystalInfo() {
    String s = String
        .format(
            "Cylinder (Polyhedron) crystal of "
                + "diameter %.2f mm and height %.2f mm at a "
                + "resolution of %.2f microns per voxel edge.",
                crystSizeUM[0] * CONVERSION_MICRONS_TO_MILLIMETERS,
                crystSizeUM[1] * CONVERSION_MICRONS_TO_MILLIMETERS,
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
  @Override
  public void runPEsim(int i, int j, int k, double numAbsorbedPhotons,
      boolean addBindingEn, CoefCalc coefCalc, double photonEnergy,
      double angle, boolean surrounding) {
    // TODO Auto-generated method stub
    
  }
  */
}
