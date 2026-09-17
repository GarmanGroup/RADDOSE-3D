package se.raddo.raddose3D;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Invariant tests for {@link CrystalPolyhedron#findDepth}.
 * <p>
 * <strong>These are not a regression guard for commit 9a75719</strong>
 * ("fix findDepth deduplication"), although that is what they were written to
 * be. That commit corrected a duplicate filter which compared boxed
 * {@code Double} references instead of values (so duplicates were never
 * removed) and which also advanced its index past an element after removing
 * one. Both are real defects in the code as written.
 * <p>
 * However, no input could be found for which the fix changes the result.
 * 98,051 sampled voxels across three meshes (a 100um cube, the convex .obj and
 * the concave .obj fixtures) at seven rotation settings, plus rays aimed
 * exactly at vertices, edge midpoints and face centres and a sub-voxel sweep
 * along a face diagonal, all produce byte-identical depths before and after
 * the fix. The reason appears to be {@code Vector.polygonInclusionTest}: the
 * pnpoly crossing-number algorithm uses a half-open edge rule
 * ({@code (v[i].y > p.y) != (v[j].y > p.y)}), which by construction assigns a
 * point lying on an edge shared by two triangles to exactly one of them. The
 * duplicate distances the filter exists to remove therefore do not arise.
 * <p>
 * See TEST-TRIAGE.md #3. What follows are genuine invariants of findDepth
 * that were previously untested and are worth keeping regardless.
 */
public class FindDepthRegressionTest {

  private static final double DIM = 100d;
  private static final double RESOLUTION = 0.5d;

  private static Crystal cube() {
    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, DIM);
    properties.put(Crystal.CRYSTAL_DIM_Y, DIM);
    properties.put(Crystal.CRYSTAL_DIM_Z, DIM);
    properties.put(Crystal.CRYSTAL_RESOLUTION, RESOLUTION);
    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    return new CrystalCuboid(properties);
  }

  private static Wedge wedge() {
    return new Wedge(0d, 0d, 0d, 100d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 2d);
  }

  /**
   * Voxels lying on the diagonal shared by the two triangles of the front face
   * must report a non-zero depth. This is the shape the duplicate-distance bug
   * would take if it were reachable.
   */
  @Test
  public void voxelsOnAFaceDiagonalHaveNonZeroDepth() {
    Crystal c = cube();
    Wedge w = wedge();
    c.setupDepthFinding(0, w);

    int voxels = (int) (DIM * RESOLUTION);
    int checked = 0;
    // i == j puts the voxel exactly under the diagonal shared by the two
    // triangles making up the +z face.
    for (int i = 1; i < voxels; i++) {
      for (int k = 1; k < voxels; k++) {
        double[] coord = c.getCrystCoord(i, i, k);
        assertEquals(coord[0], coord[1], 1e-12,
            "test precondition: voxel should be on the x == y diagonal");

        double depth = c.findDepth(coord, 0, w);
        assertTrue(depth > 0,
            "depth collapsed to 0 on the face diagonal at voxel ("
                + i + ", " + i + ", " + k + "), crystal coordinate ("
                + coord[0] + ", " + coord[1] + ", " + coord[2] + ")");
        checked++;
      }
    }
    assertTrue(checked > 0, "no diagonal voxels were examined");
  }

  /**
   * For an unrotated cuboid the beam travels along z, so the depth of a voxel
   * is its distance from the front face. This must hold on the diagonal too.
   */
  @Test
  public void depthEqualsDistanceFromFrontFace() {
    Crystal c = cube();
    Wedge w = wedge();
    c.setupDepthFinding(0, w);

    int voxels = (int) (DIM * RESOLUTION);
    for (int k = 0; k < voxels; k++) {
      double expected = k / RESOLUTION;

      // on the diagonal (the regression case)
      double onDiagonal = c.findDepth(c.getCrystCoord(10, 10, k), 0, w);
      assertEquals(expected, onDiagonal, 1e-9,
          "depth on the face diagonal at k=" + k);

      // and off it (the ordinary case)
      double offDiagonal = c.findDepth(c.getCrystCoord(10, 17, k), 0, w);
      assertEquals(expected, offDiagonal, 1e-9,
          "depth off the face diagonal at k=" + k);
    }
  }

  /**
   * Depth must not depend on whether the ray happens to strike a shared edge:
   * a diagonal voxel and its neighbour at the same z see the same thickness.
   */
  @Test
  public void depthIsUniformAcrossASliceOfAnAxisAlignedCuboid() {
    Crystal c = cube();
    Wedge w = wedge();
    c.setupDepthFinding(0, w);

    int voxels = (int) (DIM * RESOLUTION);
    int k = voxels / 2;
    double reference = c.findDepth(c.getCrystCoord(3, 11, k), 0, w);
    assertTrue(reference > 0, "reference voxel should be inside the crystal");

    for (int i = 1; i < voxels - 1; i++) {
      for (int j = 1; j < voxels - 1; j++) {
        assertEquals(reference, c.findDepth(c.getCrystCoord(i, j, k), 0, w),
            1e-9, "depth varies across a slice at voxel (" + i + ", " + j + ")");
      }
    }
  }

  /** A point well outside the mesh intersects nothing and has zero depth. */
  @Test
  public void voxelsOutsideTheCrystalHaveZeroDepth() {
    Crystal c = cube();
    Wedge w = wedge();
    c.setupDepthFinding(0, w);

    assertEquals(0.0, c.findDepth(new double[] {500, 500, 0}, 0, w), 0.0);
    assertEquals(0.0, c.findDepth(new double[] {-500, 0, 0}, 0, w), 0.0);
    assertEquals(0.0, c.findDepth(new double[] {0, -500, 0}, 0, w), 0.0);
  }

  /**
   * The concave fixture has a horseshoe cross-section, so a ray through the
   * open part crosses the mesh four times. This exercises the branch of
   * findDepth that sums alternating entry/exit segments.
   */
  @Test
  public void concaveMeshSumsSeparateSegments() {
    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_RESOLUTION, 0.5d);
    properties.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    properties.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    properties.put(CrystalPolyhedron.CRYSTAL_WIREFRAME_FILE,
        "test/resources/CrystalPolyhedron-concave_cuboid-30-20-10.obj");
    properties.put(CrystalPolyhedron.CRYSTAL_WIREFRAME_TYPE, "obj");

    Crystal c = new CrystalPolyhedron(properties);
    Wedge w = wedge();
    c.setupDepthFinding(0, w);

    // Through the solid back of the horseshoe.
    double thick = c.findDepth(new double[] {3.5, -8.65, 29.9}, 0, w);
    // Through the open part, which skips the middle section.
    double thin = c.findDepth(new double[] {5, 6, 30.0}, 0, w);

    assertTrue(thick > 0, "ray through the thick part found no crystal");
    assertTrue(thin > 0, "ray through the thin part found no crystal");
    assertTrue(thick > thin,
        "the solid part of the horseshoe should be thicker than the open part "
            + "(thick=" + thick + ", thin=" + thin + ")");
  }
}
