package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link CrystalCylinder}, a user-selectable crystal shape that had
 * no coverage at all.
 * <p>
 * The dimension mapping is not the obvious one, and is pinned here because
 * nothing else documents it. {@code CrystalCylinder.loadVertices} reads
 * <pre>
 *   radius = CRYSTAL_DIM_X / 2
 *   height = CRYSTAL_DIM_Y
 * </pre>
 * and ignores {@code CRYSTAL_DIM_Z} entirely. The prism is built along x and
 * then rotated 90 degrees about z, so the finished cylinder's axis lies along
 * <em>y</em>. For {@code Dimensions D H anything} the bounding box is
 * therefore {@code D x H x D}.
 * <p>
 * Geometry is checked against the analytic shape rather than recorded numbers:
 * the mesh is a 32-segment prism, which encloses
 * {@code (n/2)*sin(2*pi/n)*r^2*h}, about 99.4% of the true cylinder.
 */
public class CrystalCylinderTest {

  /** Number of segments in the prism approximating the circle. */
  private static final int SEGMENTS = 32;

  /**
   * @param diameter the circular cross-section, CRYSTAL_DIM_X
   * @param height   the axial length, CRYSTAL_DIM_Y
   */
  private static CrystalCylinder cylinder(double diameter, double height) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Crystal.CRYSTAL_DIM_X, diameter);
    p.put(Crystal.CRYSTAL_DIM_Y, height);
    p.put(Crystal.CRYSTAL_DIM_Z, 1d);   // ignored -- see dimZIsIgnored
    p.put(Crystal.CRYSTAL_RESOLUTION, 1.0d);
    p.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    p.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    return new CrystalCylinder(p);
  }

  private static int occupancy(Crystal c) {
    int[] n = c.getCrystSizeVoxels();
    int count = 0;
    for (int i = 0; i < n[0]; i++) {
      for (int j = 0; j < n[1]; j++) {
        for (int k = 0; k < n[2]; k++) {
          if (c.isCrystalAt(i, j, k)) {
            count++;
          }
        }
      }
    }
    return count;
  }

  /** Volume of the 32-segment prism the mesh actually represents. */
  private static double prismVolume(double diameter, double height) {
    double r = diameter / 2;
    return (SEGMENTS / 2.0) * Math.sin(2 * Math.PI / SEGMENTS) * r * r * height;
  }

  @Test
  public void boundingBoxIsDiameterByHeightByDiameter() {
    double[] um = cylinder(40, 20).getCrystSizeUM();

    assertEquals(40.0, um[0], 1.0, "x should span the diameter");
    assertEquals(20.0, um[1], 1.0, "y should span the height -- the axis");
    assertEquals(40.0, um[2], 1.0, "z should span the diameter");
  }

  /**
   * CRYSTAL_DIM_Z is never read by loadVertices. Pinned because a user writing
   * "Dimensions 40 40 20" expecting a 20 um tall cylinder would silently get a
   * 40 um one.
   */
  @Test
  public void dimZIsIgnored() {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Crystal.CRYSTAL_DIM_X, 40d);
    p.put(Crystal.CRYSTAL_DIM_Y, 20d);
    p.put(Crystal.CRYSTAL_RESOLUTION, 1.0d);
    p.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    p.put(Crystal.CRYSTAL_ANGLE_L, 0d);

    p.put(Crystal.CRYSTAL_DIM_Z, 5d);
    double[] withFive = new CrystalCylinder(p).getCrystSizeUM();

    p.put(Crystal.CRYSTAL_DIM_Z, 500d);
    double[] withFiveHundred = new CrystalCylinder(p).getCrystSizeUM();

    assertEquals(withFive[0], withFiveHundred[0], 1e-9);
    assertEquals(withFive[1], withFiveHundred[1], 1e-9);
    assertEquals(withFive[2], withFiveHundred[2], 1e-9,
        "changing CRYSTAL_DIM_Z by a factor of 100 changed the crystal, so it "
            + "is no longer ignored -- update this test and the class comment");
  }

  @Test
  public void describesItself() {
    String info = cylinder(40, 20).crystalInfo();
    assertTrue(info != null && info.length() > 0, "crystalInfo was empty");
    assertTrue(info.toLowerCase().contains("cylinder"),
        "crystalInfo should name the shape: " + info);
  }

  @ParameterizedTest
  @CsvSource({"40, 20", "30, 30", "60, 10", "20, 40"})
  public void occupancyApproximatesThePrismVolume(double diameter,
      double height) {
    double expected = prismVolume(diameter, height);   // 1 voxel per um^3

    assertEquals(expected, occupancy(cylinder(diameter, height)),
        expected * 0.15,
        "occupied voxels should approximate the prism volume for a "
            + diameter + " um diameter, " + height + " um tall cylinder");
  }

  @Test
  public void cornersOfTheBoundingBoxAreOutsideTheCylinder() {
    CrystalCylinder c = cylinder(40, 20);
    int[] n = c.getCrystSizeVoxels();

    assertTrue(occupancy(c) < (long) n[0] * n[1] * n[2],
        "a cylinder should not fill its bounding box");
    assertTrue(c.isCrystalAt(n[0] / 2, n[1] / 2, n[2] / 2),
        "the centre of the cylinder should be occupied");
    assertTrue(!c.isCrystalAt(0, n[1] / 2, 0),
        "a corner of the circular cross-section should be outside");
  }

  @Test
  public void occupancyScalesWithHeight() {
    int shortOne = occupancy(cylinder(40, 10));
    int tallOne = occupancy(cylinder(40, 20));

    assertTrue(tallOne > shortOne, "a taller cylinder should contain more");
    assertEquals(2.0, (double) tallOne / shortOne, 0.3,
        "doubling the height should roughly double the volume");
  }

  @Test
  public void occupancyScalesWithTheSquareOfTheRadius() {
    int small = occupancy(cylinder(20, 20));
    int large = occupancy(cylinder(40, 20));

    assertEquals(4.0, (double) large / small, 0.6,
        "doubling the diameter should roughly quadruple the volume");
  }

  /** Depth finding must work on a cylinder, not only on cuboids and meshes. */
  @Test
  public void depthFindingWorksOnACylinder() {
    CrystalCylinder c = cylinder(40, 20);
    Wedge w = new Wedge(0d, 0d, 0d, 100d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 2d);
    c.setupDepthFinding(0, w);

    int[] n = c.getCrystSizeVoxels();
    // The beam runs along z, so depth at the back of the crystal should
    // approach the full chord through the circular cross-section.
    double depthAtBack = c.findDepth(
        c.getCrystCoord(n[0] / 2, n[1] / 2, n[2] - 1), 0, w);

    assertTrue(depthAtBack > 0, "a ray through the axis should find crystal");
    assertEquals(40.0, depthAtBack, 4.0,
        "depth through the centre should approach the diameter");
  }
}
