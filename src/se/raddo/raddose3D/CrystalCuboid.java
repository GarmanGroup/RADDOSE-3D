package se.raddo.raddose3D;

import java.util.Map;

public class CrystalCuboid extends CrystalPolyhedron {

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

  @Override
  public void loadVertices(final Map<Object, Object> mergedProperties)
  {
    int[][] tempIndices = {
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


    Double xdim = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_X) / 2;
    Double ydim = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_Y) / 2;
    Double zdim = (Double) mergedProperties.get(Crystal.CRYSTAL_DIM_Z) / 2;

    double[][] tempVertices = { { -xdim, -ydim, zdim },
       { -xdim, -ydim, -zdim },
       { -xdim, ydim, -zdim },
       { -xdim, ydim, zdim },
       { xdim, -ydim, zdim },
       { xdim, -ydim, -zdim },
       { xdim, ydim, -zdim },
       { xdim, ydim, zdim }
       };

    setIndices(tempIndices);

    vertices = new double[tempVertices.length][3];

    for (int i = 0; i < tempVertices.length; i++) {
      System.arraycopy(tempVertices[i], 0, vertices[i], 0, 3);
    }

  }

  public CrystalCuboid(final Map<Object, Object> properties) {
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
            "Cuboid (Polyhedron) crystal of size "
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
}
