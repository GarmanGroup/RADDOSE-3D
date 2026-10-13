package se.raddo.raddose3D;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks that the crystal and the surrounding draw photoelectron directions
 * from their own tables.
 * <p>
 * Each table lists the directions, each repeated by its weight under the
 * beam's polarisation, which depends on the material. Both used to be
 * written to one field, the surrounding's last, so with a surrounding the
 * crystal's photoelectrons were drawn with the surrounding's weights.
 */
public class PhotoelectronTrackTest {

  /** {@code PE_ANGLE_RES_LIMIT}: angular bins over a full turn. */
  private static final int ANGLE_BINS = 100;

  /**
   * Builds a 20 um cube with photoelectron escape on.
   *
   * @return the crystal
   */
  private static CrystalPolyhedron cube() {
    HashMap<Object, Object> p = new HashMap<Object, Object>();
    p.put(Crystal.CRYSTAL_DIM_X, 20d);
    p.put(Crystal.CRYSTAL_DIM_Y, 20d);
    p.put(Crystal.CRYSTAL_DIM_Z, 20d);
    p.put(Crystal.CRYSTAL_RESOLUTION, 0.5d);
    p.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    p.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    p.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, "TRUE");
    p.put(Crystal.CRYSTAL_COEFCALC, new CoefCalcFromParams());
    return new CrystalCuboid(p);
  }

  /**
   * Sets a private field of the crystal.
   *
   * @param c the crystal
   * @param name the field
   * @param value its new value
   * @throws Exception if reflection fails
   */
  private static void set(final CrystalPolyhedron c, final String name,
      final Object value) throws Exception {
    Field f = CrystalPolyhedron.class.getDeclaredField(name);
    f.setAccessible(true);
    f.set(c, value);
  }

  /**
   * Reads a private table of the crystal.
   *
   * @param c the crystal
   * @param name the field
   * @return its value
   * @throws Exception if reflection fails
   */
  private static int[] table(final CrystalPolyhedron c, final String name)
      throws Exception {
    Field f = CrystalPolyhedron.class.getDeclaredField(name);
    f.setAccessible(true);
    return (int[]) f.get(c);
  }

  /** Each material keeps the table built from its own weights. */
  @Test
  public void crystalAndSurroundingKeepTheirOwnTables() throws Exception {
    CrystalPolyhedron c = cube();
    double[] once = new double[ANGLE_BINS / 2 + 1];
    double[] twice = new double[ANGLE_BINS / 2 + 1];
    Arrays.fill(once, 1);
    Arrays.fill(twice, 2);
    set(c, "angularDistribution", once);
    set(c, "cryoAngularDistribution", twice);
    // No distance bins: only the tables are wanted here.
    set(c, "peDistBins", 0);

    Method m = CrystalPolyhedron.class.getDeclaredMethod("findVoxelsReachedByPE",
        boolean.class, CoefCalc.class, double.class, double[][].class,
        double.class);
    m.setAccessible(true);
    m.invoke(c, false, null, 12.1, null, 0.0);
    int[] crystal = table(c, "trackNumberBias").clone();
    m.invoke(c, true, null, 12.1, null, 0.0);

    assertEquals(crystal.length, table(c, "trackNumberBias").length,
        "building the surrounding's table must leave the crystal's alone");
    assertEquals(2 * crystal.length, table(c, "cryoTrackNumberBias").length,
        "every direction twice, as the surrounding's weights say");
  }
}
