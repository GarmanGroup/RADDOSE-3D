package se.raddo.raddose3D;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The Monte Carlo's exposed region (issue #17): the voxels the beam crosses,
 * with the crystal where the wedge puts it, and the volume they stand for.
 * A 4 x 6 x 8 um cuboid at 2 voxels per um, in a top-hat beam 2 um wide and
 * 6 um tall: the beam covers the crystal's height and half its width.
 */
public class MonteCarloExposedRegionTest {

  private static final List<String> NONE = new ArrayList<String>();
  private static final List<Double> NO_AMOUNTS = new ArrayList<Double>();

  private static MC mc(final boolean xfel) throws Exception {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Crystal.CRYSTAL_DIM_X, 4d);
    p.put(Crystal.CRYSTAL_DIM_Y, 6d);
    p.put(Crystal.CRYSTAL_DIM_Z, 8d);
    p.put(Crystal.CRYSTAL_RESOLUTION, 2d);
    p.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    p.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    p.put(Crystal.CRYSTAL_PROGRAM, xfel ? "XFEL" : "MONTECARLO");
    p.put(Crystal.CRYSTAL_COEFCALC, new CoefCalcFromParams(78.02, 78.02,
        78.02, 90.0, 90.0, 90.0, 24, 51, 0, 0, Arrays.asList("ZN", "S"),
        Arrays.asList(0.333, 6.0), Arrays.asList("P"), Arrays.asList(425.0),
        NONE, NO_AMOUNTS, 0.64, null, null, 0, NONE, NO_AMOUNTS, 0, 100));
    CrystalPolyhedron c = new CrystalCuboid(p);
    return new MC(c.vertices, c.indices,
        (double[][][][]) field(c, "crystCoord"), c.crystalPixPerUM,
        (int[]) field(c, "crystSizeVoxels"), c.crystOcc, 1, false, xfel,
        xfel, new double[3], false);
  }

  private static Object field(final Object owner, final String name)
      throws Exception {
    Field f = CrystalPolyhedron.class.getDeclaredField(name);
    f.setAccessible(true);
    return f.get(owner);
  }

  /** A top-hat beam with a rectangular aperture, um. */
  private static Beam aperture(final double width, final double height) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_COLL_H, width);
    p.put(Beam.BEAM_COLL_V, height);
    p.put(Beam.BEAM_FLUX, 2e12);
    p.put(Beam.BEAM_ENERGY, 12.1);
    return new BeamTophat(p);
  }

  /** A wedge from start to end, degrees, the crystal offset along x, um. */
  private static Wedge wedge(final double start, final double end,
      final double offsetX) {
    return new Wedge(2d, start, end, 10d, offsetX, 0d, 0d, null, null, null,
        null, null);
  }

  /** The voxel at x, y, z um in the crystal's own frame. */
  private static boolean at(final boolean[][][] exposed, final double x,
      final double y, final double z) {
    return exposed[(int) Math.round((x + 2) * 2)][(int) Math.round((y + 3) * 2)]
        [(int) Math.round((z + 4) * 2)];
  }

  /**
   * Centred and unturned, the exposed region is the voxels the old test
   * chose, the column |x| <= 1 um, and the exposed volume is unscaled: such
   * a crystal's results are unchanged.
   */
  @Test
  public void aCentredUnturnedCrystalsRegionIsAsItWas() throws Exception {
    MC mc = mc(false);
    Wedge still = wedge(0, 0, 0);
    double[] angles = mc.exposedAngles(still);
    boolean[][][] exposed = mc.exposedVoxels(aperture(2, 6), still, angles);
    for (int a = 0; a < exposed.length; a++) {
      for (int b = 0; b < exposed[a].length; b++) {
        for (int c = 0; c < exposed[a][b].length; c++) {
          assertEquals(Math.abs(a / 2.0 - 2) <= 1, exposed[a][b][c]);
        }
      }
    }
    assertEquals(1.0, mc.exposedShare(aperture(2, 6), still, angles));
  }

  /**
   * A StartOffset of 2 um along x moves the beam to x from -3 to -1 um in
   * the crystal: the region follows it, and only half the beam is on the
   * crystal, so the exposed volume halves.
   */
  @Test
  public void anOffsetMovesTheRegionAndHangsTheBeamOffTheEdge()
      throws Exception {
    MC mc = mc(false);
    Wedge offset = wedge(0, 0, 2);
    double[] angles = mc.exposedAngles(offset);
    boolean[][][] exposed = mc.exposedVoxels(aperture(2, 6), offset, angles);
    assertTrue(at(exposed, -2, 0, 0));
    assertTrue(at(exposed, -1, 0, 0));
    assertFalse(at(exposed, -0.5, 0, 0));
    assertFalse(at(exposed, 0, 0, 0));
    assertEquals(0.5, mc.exposedShare(aperture(2, 6), offset, angles));
  }

  /**
   * At 60 degrees the beam crosses the slab |x cos 60 + z sin 60| <= 1: 4 x
   * 2.31 um of the crystal's x-z section, against 2 x 8 centred, a share of
   * 0.577. Measured at the slices' midpoints, within 1%.
   */
  @Test
  public void aTurnedStillsRegionIsTheSlabTheBeamCrosses() throws Exception {
    MC mc = mc(false);
    Wedge turned = wedge(60, 60, 0);
    double[] angles = mc.exposedAngles(turned);
    assertArrayEquals(new double[] {Math.toRadians(60)}, angles);
    boolean[][][] exposed = mc.exposedVoxels(aperture(2, 6), turned, angles);
    assertTrue(at(exposed, 2, 0, -2));
    assertFalse(at(exposed, 2, 0, 0.5));
    assertTrue(at(exposed, -1, 0, 1.5));
    assertFalse(at(exposed, 0, 0, 2));
    double share = 4 * (2 / Math.sin(Math.toRadians(60))) / (2 * 8);
    assertEquals(share, mc.exposedShare(aperture(2, 6), turned, angles),
        0.01 * share);
  }

  /**
   * Turning from 0 to 90 degrees the beam sweeps every voxel it crosses on
   * the way, at every 2 degrees: the corner at x = 2, z = 0 comes into the
   * beam as the crystal turns, and the one at x = 2, z = 4 never does.
   */
  @Test
  public void aRotationsRegionIsEveryVoxelTheBeamCrosses() throws Exception {
    MC mc = mc(false);
    Wedge rotation = wedge(0, 90, 0);
    double[] angles = mc.exposedAngles(rotation);
    assertEquals(0, angles[0]);
    assertEquals(Math.toRadians(90), angles[angles.length - 1], 1e-15);
    assertTrue(angles.length >= 46);
    boolean[][][] exposed = mc.exposedVoxels(aperture(2, 6), rotation,
        angles);
    assertTrue(at(exposed, 2, 0, 0));
    assertFalse(at(exposed, 2, 0, 4));
    assertTrue(mc.exposedShare(aperture(2, 6), rotation, angles) > 1);
  }

  /** An XFEL pulse stands at its wedge's start, as its photons do. */
  @Test
  public void anXfelPulseStandsAtItsStart() throws Exception {
    assertArrayEquals(new double[] {Math.toRadians(30)},
        mc(true).exposedAngles(wedge(30, 90, 0)));
  }
}
