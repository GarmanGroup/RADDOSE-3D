package se.raddo.raddose3D;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests that AngleP / AngleL put the crystal grid and the surrounding (cryo)
 * grid in the same frame. See TEST-TRIAGE.md #2.
 * <p>
 * Both grids are built over the bounding box of the <em>already rotated</em>
 * mesh -- {@code CrystalPolyhedron.java:589} for the crystal and {@code :708}
 * for the surrounding -- so both are already in the lab frame and neither
 * should have the rotation applied a second time.
 * <p>
 * The crystal grid gets this right: it computes the rotated coordinate at
 * {@code :655-665} and then discards it by overwriting with the unrotated one
 * at {@code :667-669}. The dead lines are confusing and should be deleted, but
 * the behaviour is correct. The surrounding grid at {@code :764-774} keeps its
 * rotation, which double-rotates it.
 */
public class CrystalRotationFrameTest {

  private static CrystalPolyhedron crystal(double dx, double dy, double dz,
      double angleP, double angleL) {
    HashMap<Object, Object> m = new HashMap<Object, Object>();
    m.put(Crystal.CRYSTAL_DIM_X, dx);
    m.put(Crystal.CRYSTAL_DIM_Y, dy);
    m.put(Crystal.CRYSTAL_DIM_Z, dz);
    m.put(Crystal.CRYSTAL_RESOLUTION, 0.5d);
    m.put(Crystal.CRYSTAL_ANGLE_P, angleP);
    m.put(Crystal.CRYSTAL_ANGLE_L, angleL);
    return new CrystalCuboid(m);
  }

  private static Beam beam() {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_FWHM_X, 50.);
    p.put(Beam.BEAM_FWHM_Y, 50.);
    p.put(Beam.BEAM_ENERGY, 12.1);
    p.put(Beam.BEAM_FLUX, 2e12);
    p.put(Beam.BEAM_COLL_H, 100.);
    p.put(Beam.BEAM_COLL_V, 100.);
    BeamGaussian b = new BeamGaussian(p);
    b.applyContainerAttenuation(new ContainerTransparent());
    return b;
  }

  /** Min and max of one component over a whole voxel grid. */
  private static double[] range(double[][][][] grid, int component, int[] n) {
    double lo = Double.MAX_VALUE, hi = -Double.MAX_VALUE;
    for (int i = 0; i < n[0]; i++) {
      for (int j = 0; j < n[1]; j++) {
        for (int k = 0; k < n[2]; k++) {
          double v = grid[i][j][k][component];
          lo = Math.min(lo, v);
          hi = Math.max(hi, v);
        }
      }
    }
    return new double[] {lo, hi};
  }

  private static double[] crystalRange(CrystalPolyhedron c, int component) {
    int[] n = c.getCrystSizeVoxels();
    double lo = Double.MAX_VALUE, hi = -Double.MAX_VALUE;
    for (int i = 0; i < n[0]; i++) {
      for (int j = 0; j < n[1]; j++) {
        for (int k = 0; k < n[2]; k++) {
          double v = c.getCrystCoord(i, j, k)[component];
          lo = Math.min(lo, v);
          hi = Math.max(hi, v);
        }
      }
    }
    return new double[] {lo, hi};
  }

  /**
   * The crystal grid must span the rotated bounding box. A 100 x 50 crystal
   * turned 90 degrees in P is 50 wide in x and 100 in y.
   */
  @ParameterizedTest
  @CsvSource({
      //  angleP, angleL, expected x half-width, expected y half-width
      "    0,   0,   50,   25",
      "   90,   0,   25,   50",
      "  180,   0,   50,   25",
  })
  public void crystalGridSpansTheRotatedBoundingBox(double angleP,
      double angleL, double halfX, double halfY) {
    CrystalPolyhedron c = crystal(100, 50, 20, angleP, angleL);

    double[] x = crystalRange(c, 0);
    double[] y = crystalRange(c, 1);

    assertEquals(-halfX, x[0], 0.51, "crystal grid x lower bound");
    assertEquals(halfX, x[1], 0.51, "crystal grid x upper bound");
    assertEquals(-halfY, y[0], 0.51, "crystal grid y lower bound");
    assertEquals(halfY, y[1], 0.51, "crystal grid y upper bound");
  }

  /**
   * PARKED -- see TEST-TRIAGE.md #2. Fails at AngleP = 90.
   * <p>
   * The surrounding medium exists to catch photoelectrons leaving the crystal,
   * so it must enclose the crystal. Both grids feed the same transform in the
   * same exposure loop (Crystal.java:1087 and :1295), so they must share a
   * frame. At AngleP = 90 the cryo grid comes out transposed -- x spans
   * [-60, 60] where the crystal spans [-25, 25], and y spans [-35, 35] where
   * the crystal spans [-50, 50] -- so the crystal protrudes from its own
   * surrounding by 15 um.
   */
  @ParameterizedTest
  @CsvSource({"0, 0", "45, 0", "90, 0", "0, 90", "0, 45"})
  @Tag("pending")
  public void surroundingEnclosesTheCrystalAtEveryRotation(double angleP,
      double angleL) throws Exception {
    CrystalPolyhedron c = crystal(100, 50, 20, angleP, angleL);
    c.produceCryoSolutionCrystal(10, beam());

    Field f = CrystalPolyhedron.class.getDeclaredField("cryoCrystCoord");
    f.setAccessible(true);
    double[][][][] cryo = (double[][][][]) f.get(c);
    int[] cryoN = c.getCryoCrystSizeVoxels();

    for (int component = 0; component < 3; component++) {
      double[] inner = crystalRange(c, component);
      double[] outer = range(cryo, component, cryoN);

      assertTrue(outer[0] <= inner[0] + 1e-6,
          "surrounding does not reach the crystal's lower bound in axis "
              + component + " (crystal " + inner[0] + ", surrounding "
              + outer[0] + ") at AngleP=" + angleP + " AngleL=" + angleL);
      assertTrue(outer[1] >= inner[1] - 1e-6,
          "surrounding does not reach the crystal's upper bound in axis "
              + component + " (crystal " + inner[1] + ", surrounding "
              + outer[1] + ") at AngleP=" + angleP + " AngleL=" + angleL);
    }
  }

  /**
   * With no rotation the two grids must agree exactly, which is the sanity
   * check that the enclosure test above is measuring the right thing.
   */
  @Test
  public void withoutRotationBothGridsShareAnOrigin() throws Exception {
    CrystalPolyhedron c = crystal(100, 50, 20, 0, 0);
    c.produceCryoSolutionCrystal(10, beam());

    Field f = CrystalPolyhedron.class.getDeclaredField("cryoCrystCoord");
    f.setAccessible(true);
    double[][][][] cryo = (double[][][][]) f.get(c);
    int[] cryoN = c.getCryoCrystSizeVoxels();

    for (int component = 0; component < 3; component++) {
      double[] inner = crystalRange(c, component);
      double[] outer = range(cryo, component, cryoN);
      assertTrue(outer[0] <= inner[0] + 1e-6 && outer[1] >= inner[1] - 1e-6,
          "unrotated surrounding should enclose the crystal in axis "
              + component);
    }
  }

  /**
   * Occupancy is a rotation invariant: turning the crystal cannot change how
   * much crystal there is, beyond discretisation. This is what breaks if the
   * rotation is "restored" for crystCoord -- at AngleP = 90 occupancy halves.
   */
  @ParameterizedTest
  @CsvSource({"0, 0", "45, 0", "90, 0", "180, 0", "0, 90"})
  public void occupancyIsPreservedUnderRotation(double angleP, double angleL) {
    CrystalPolyhedron c = crystal(100, 50, 20, angleP, angleL);
    int[] n = c.getCrystSizeVoxels();

    int occupied = 0;
    for (int i = 0; i < n[0]; i++) {
      for (int j = 0; j < n[1]; j++) {
        for (int k = 0; k < n[2]; k++) {
          if (c.isCrystalAt(i, j, k)) {
            occupied++;
          }
        }
      }
    }

    // 100 x 50 x 20 um at 0.5 voxels/um -> 50 x 25 x 10 um of voxels = 12500.
    assertEquals(12500, occupied, 12500 * 0.05,
        "occupied voxel count changed by more than 5% at AngleP=" + angleP
            + " AngleL=" + angleL + " (got " + occupied + ")");
  }
}
