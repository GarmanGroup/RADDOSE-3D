package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

/**
 * The CrystalCuboid class is for a cuboid-shaped crystal with a given start
 * orientation and position defined by:
 * - x,y,z sizes in um
 * - crystres in 1/um
 * - starting angles P and L.
 * - A coefCalc object
 */

public class CrystalCuboidOld extends Crystal {

  /** Resolution of crystal in 1/um. */
  private final double         crystalPixPerUM;

  /**
   * Initial orientation of the crystal in the plane of the loop (right handed
   * rotation about z) and of the loop (right handed rotation about x) in
   * radians.
   */
  private final double         p, l;

  /**
   * 3 element array defining dimensions of
   * bounding box of crystal in um.
   */
  private final double[]       crystSizeUM;

  /** 3 element array defining dimensions of bounding box in voxels. */
  private final int[]          crystSizeVoxels;

  /**
   * Dose and fluence arrays holding the scalar
   * fields for these values at voxel i,j,k.
   */
  private double[][][]         dose, fluence, elastic;

  /**
   * 4d array where the 4th dimension is a 3 element array with the coordinates
   * of the voxel i,j,k in the starting position.
   */
  private final double[][][][] crystCoord;

  /**
   * 8 by 3 double array that defines the xyz coordinates of the crystal in its
   * starting position.
   */
  private final double[][]     vertices;

  private double[][]           verticesRotatedNormals;
  private double[][]           verticesRotatedv1s;
  private double[]             verticesRotatedDenominators;

  /**
   * 6 by 4 integer array that defines the faces of the crystal, each 4 element
   * row represents the vertices that make up that face.
   */
  private final int[][]        face = {
                                    { 5, 8, 7, 6 },
                                    { 1, 2, 3, 4 },
                                    { 4, 3, 7, 8 },
                                    { 5, 6, 2, 1 },
                                    { 1, 4, 8, 5 },
                                    { 6, 7, 3, 2 }
                                    };

  /**
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
  public CrystalCuboidOld(final Map<Object, Object> properties) {
    // Pass properties to Crystal()-constructor
    super(properties);

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

    /*
     * Set initial vertex positions. A constant 'delta' is added to each vertex so that the voxels
     * on the surface do not appear beyond the faces defined by the vertices. Basically, the box 
     * bounded by the vertices is slightly bigger than the box bounded by the voxels, and so when we 
     * rotate everything, the voxels never end up outside the 'vertex' box due to rounding errors.
     */

    // make sure rounding errors don't put voxels outside the surface. Should be small relative to voxel size.
    double delta = 0.5 * crystalPixPerUM;

    // Set initial positions of vertices
    double[][] makeVertices = {
        { xdim / 2 + delta, ydim / 2 + delta, zdim / 2 + delta },
        { -xdim / 2 - delta, ydim / 2 + delta, zdim / 2 + delta },
        { -xdim / 2 - delta, -ydim / 2 - delta, zdim / 2 + delta },
        { xdim / 2 + delta, -ydim / 2 - delta, zdim / 2 + delta },
        { xdim / 2 + delta, ydim / 2 + delta, -zdim / 2 - delta },
        { -xdim / 2 - delta, ydim / 2 + delta, -zdim / 2 - delta },
        { -xdim / 2 - delta, -ydim / 2 - delta, -zdim / 2 - delta },
        { xdim / 2 + delta, -ydim / 2 - delta, -zdim / 2 - delta }
    };

    int i = 0;
    for (double[] indVertex : makeVertices) {

      /*
       * rotation in plane about [0 0 1] (P) Temporary variables needed since we
       * use all of the previous xyz's to set each of the new ones
       */
      double x2 = indVertex[0] * Math.cos(p) + indVertex[1] * Math.sin(p);
      double y2 = -1 * indVertex[0] * Math.sin(p) + indVertex[1] * Math.cos(p);
      double z2 = indVertex[2];

      /*
       * rotation loop about [1 0 0] (L) Temporary variables needed since we use
       * all of the previous xyz's to set each of the new ones
       */
      makeVertices[i][0] = x2;
      makeVertices[i][1] = y2 * Math.cos(l) + z2 * Math.sin(l);
      makeVertices[i][2] = -1 * y2 * Math.sin(l) + z2 * Math.cos(l);
      i++;
    }

    vertices = makeVertices; // Final value

  }

  @Override
  public String crystalInfo() {
    String s = String
        .format(
            "Cuboid Crystal of size [%.0f, %.0f, %.0f] um [x, y, z] at a "
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

  @Override
  public void setupDepthFinding(final double angrad, final Wedge wedge) {
    double[][] verticesRotated = new double[vertices.length][3];

    // Rotate and translate the vertices of the crystal
    // to the position defined by angrad (= deltaphi)

    for (int vertInd = 0; vertInd < vertices.length; vertInd++) {

      // Translate Y
      verticesRotated[vertInd][1] = vertices[vertInd][1]
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
      verticesRotated[vertInd][0] = transX * Math.cos(angrad)
          + transZ * Math.sin(angrad);
      // Rotate Z
      verticesRotated[vertInd][2] = -1 * transX * Math.sin(angrad)
          + transZ * Math.cos(angrad);
    }

    // Define the z axis for use later.
    final double[] zAxis = { 0, 0, 1 };

    verticesRotatedDenominators = new double[face.length];
    verticesRotatedNormals = new double[face.length][3];
    verticesRotatedv1s = new double[face.length][3];

    /*
     * Loop through each face of the crystal and find the distance along the z
     * axis from the point to the face
     */
    for (int facePlane = 0; facePlane < face.length; facePlane++) {

      // Three points on face define a plane
      // -1 to go from vertex number to index
      double[] v1 = verticesRotated[face[facePlane][0] - 1];
      double[] v2 = verticesRotated[face[facePlane][1] - 1];
      double[] v3 = verticesRotated[face[facePlane][2] - 1];

      //find normal to the face
      double[] normal = cross3(minus(v1, v2), minus(v3, v2));

      /*
       * Line is defined by pointOnLine = voxCoord + d*zAxis,
       * Plane is (P-v1) dot normal = 0.
       * Rearranging gives d = (v1 - voxCoord)dot normal / zAxis dot normal
       * We now find the denominator and numerator separately
       */
      double denominator = dot3(zAxis, normal); // = 0 if they are parallel

      // Cache values
      verticesRotatedNormals[facePlane] = normal;
      verticesRotatedv1s[facePlane] = v1;
      verticesRotatedDenominators[facePlane] = denominator;
    }
  }

  @Override
  /*
   * This method uses simple linear algebra to find the distance from a point to
   * a plane along a defined unit vector.
   * It calculates the distance to all the planes defined by the vertices and
   * faces arrays together, then takes the smallest positive value as the
   * minimum.
   * 
   * @param deltaPhi in Radians
   */
  public double findDepth(final double[] voxCoord, final double phi,
      final Wedge wedge) {

    // Found minimal distance to plane
    Double minDist = Double.POSITIVE_INFINITY;

    /*
     * Loop through each face of the crystal and find the distance along the z
     * axis from the point to the face
     */
    for (int facePlane = 0; facePlane < face.length; facePlane++) {

      // if verticesRotatedDenominators[] == 0 they are parallel and will
      // not intersect
      if (verticesRotatedDenominators[facePlane] != 0) {
        // numerator = (v1-voxP) dot normal
        double numerator = dot3(minus(verticesRotatedv1s[facePlane], voxCoord),
            verticesRotatedNormals[facePlane]);
        // Distance of the voxel from any given plane.
        double dist = -1 * numerator / verticesRotatedDenominators[facePlane];
        // factor of -1 because we want surface to point, not point to surface.

        // Check if this is the smallest non-zero distance
        if ((dist > 0) && (dist < minDist)) {
          minDist = dist;
        }
      }
    }

    /*
     * Check that the mindist has been changed, ie that there was at least one
     * non-zero positive distance along z axis to a plane.
     */
    if (minDist.isInfinite()) {
      throw (new RuntimeErrorException(
          null,
          String
              .format(
                  "%n Depth finding did not work. minDist was never changed "
                      + "from initialised value at phi = %g degrees. %n "
                      + "voxel coodinates: %n X: %g %n Y: %g %n Z : %g %n",
                  Math.toDegrees(phi), voxCoord[0], voxCoord[1], voxCoord[2])));
    }

    return minDist;
  }

  @Override
  public double[] getCrystCoord(final int i, final int j, final int k) {
    return crystCoord[i][j][k];
  }

  @Override
  public boolean isCrystalAt(final int i, final int j, final int k) {
    return true;
  }

  @Override
  public void addDose(final int i, final int j, final int k,
      final double doseVox) {
    dose[i][j][k] += doseVox;
  }

  @Override
  public void addElastic(final int i, final int j, final int k,
      final double elasticVox) {
    elastic[i][j][k] += elasticVox;
  }

  @Override
  public void addFluence(final int i, final int j, final int k,
      final double fluenceVox) {
    fluence[i][j][k] += fluenceVox;
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

  /**
   * Difference of two three-element vectors.
   * 
   * @param v1
   *          Vector A.
   * @param v2
   *          Vector B.
   * @return
   *         Vector A - B.
   */
  private static double[] minus(final double[] v1, final double[] v2) {
    double[] min = new double[3];
    min[0] = v1[0] - v2[0];
    min[1] = v1[1] - v2[1];
    min[2] = v1[2] - v2[2];

    return min;
  }

  /**
   * Dot product of two three-element vectors.
   * 
   * @param v1
   *          Vector A.
   * @param v2
   *          Vector B.
   * @return
   *         Vector A dot B.
   */
  private static double dot3(final double[] v1, final double[] v2) {

    double[] dot = new double[3];
    dot[0] = v1[0] * v2[0];
    dot[1] = v1[1] * v2[1];
    dot[2] = v1[2] * v2[2];

    return dot[0] + dot[1] + dot[2];
  }

  /**
   * Cross product of two three-element vectors.
   * 
   * @param v1
   *          Vector A.
   * @param v2
   *          Vector B.
   * @return
   *         Vector A x B.
   */
  private static double[] cross3(final double[] v1, final double[] v2) {
    double[] cross = new double[3];

    cross[0] = v1[1] * v2[2] - v1[2] * v2[1];
    cross[1] = v1[2] * v2[0] - v1[0] * v2[2];
    cross[2] = v1[0] * v2[1] - v1[1] * v2[0];
    return cross;
  }

  /*
   * (non-Javadoc)
   *
   * @see se.raddo.raddose3D.Crystal#getEscapeFactor(int, int, int)
   * 
   * This class is no longer used so this method has only been added
   * to prevent error warnings.
   */
  @Override
  public double getEscapeFactor(final int i, final int j, final int k) {
    return 1.0;
  }
  // Commented out because not needed at the moment, but it's worth having
  // this for future dev.
  //  private static double[] add3(double[] a, double[] b) {
  //    double[] ans = new double[3];
  //
  //    ans[0] = a[0] + b[0];
  //    ans[1] = a[1] + b[1];
  //    ans[2] = a[2] + b[2];
  //
  //    return ans;
  //  }
  //
  //  private static double[] elementWiseTimes(double d, double[] zAxis) {
  //    double[] ans = new double[3];
  //
  //    ans[0] = zAxis[0] * d;
  //    ans[1] = zAxis[1] * d;
  //    ans[2] = zAxis[2] * d;
  //
  //    return ans;
  //  }

  @Override
  public double addDoseAfterPE(int i, int j, int k, double doseIncreasePE) {
    return 0;
  }

  @Override
  public void setPEparamsForCurrentBeam(double beamEnergy, CoefCalc coefCalc, double[][] feFactors) {    
  }
  
  @Override
  public void setFLparamsForCurrentBeam(final double[][] feFactors){
  }

  @Override
  public double addDoseAfterFL(int i, int j, int k, double doseIncreaseFL) {
    // 
    return 0;
  }

  @Override
  public double[] getCryoCrystCoord(int i, int j, int k) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int[] getCryoCrystSizeVoxels() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getExtraVoxels(int maxPEDistance, double pixelsPerMicron) {
    // TODO Auto-generated method stub
    return 0;
  }
  
  @Override
  public void setCryoPEparamsForCurrentBeam(final Beam beam, CoefCalc coefCalc, double[][] feFactors) {    
  }
  
  @Override
  public double addDoseAfterPECryo(final double i, final double j, final double k, double doseIncreasePE, double energyToDoseFactor) {
    return 0;
  }
  
  @Override
  public void findVoxelsReachedByPE(boolean cryo, CoefCalc coefCalc, final double energy, double[][] feFactors, final double angle) {
    
  }
  
  @Override
  public int getCryoExtraVoxels() {
   
    return 0;
  }

  @Override
  public double getCryoCrystalPixPerUM() {
    
    return 0;
  }

  @Override
  public void startMicroED(double XDim, double YDim, double ZDim, Beam beam,
      Wedge wedge, CoefCalc coefCalc, String crystalType) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void startXFEL(double XDim, double YDim, double ZDim, Beam beam,
      Wedge wedge, CoefCalc coefCalc, int runNum) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public double trackPhotoelectron(int i, int j, int k, double doseIncreasePE,
      CoefCalc coefCalc, Map<Element, Double> elementAbsorptionProbs, Map<Element, double[]> ionisationProbs, double[] angularEmissionProbs,
      Beam beam, boolean surrounding) {
    // TODO Auto-generated method stub
    return 0;
  }
}
