package se.raddo.raddose3D;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Regression test for the per-shell fluorescence escape fix in commit c0d0c3f.
 * <p>
 * {@code CrystalPolyhedron.calcFluorescenceDistribution} computes, for each
 * element and each of the four shells (K, L1, L2, L3), the distance at which
 * the fluorescent photon's escape probability has fallen to 5%:
 * {@code -ln(0.05) / mu_abs}. The photoelectric mu_abs for each shell lives in
 * a different column of {@code feFactors} -- K=4, L1=8, L2=12, L3=16 -- but the
 * code used {@code muabsIndex = 4} unconditionally, i.e. it applied the K-shell
 * coefficient to all four shells. The fix is {@code muabsIndex = (4 * j) + 4}.
 * <p>
 * Both the method and the {@code flDistancesTravelled} result are private and
 * there is no getter, so this reaches them reflectively rather than widening
 * production code purely to make it testable.
 */
public class FluorescenceEscapeTest {

  /** Escape probability threshold hard-coded in the production method. */
  private static final double ESCAPE_THRESHOLD = 0.05;

  private static final double DIM = 100d;

  /** Number of distance bins; must be >= 2 for the production default path. */
  private static final int BINS = 16;

  private static CrystalPolyhedron cube() {
    HashMap<Object, Object> p = new HashMap<Object, Object>();
    p.put(Crystal.CRYSTAL_DIM_X, DIM);
    p.put(Crystal.CRYSTAL_DIM_Y, DIM);
    p.put(Crystal.CRYSTAL_DIM_Z, DIM);
    p.put(Crystal.CRYSTAL_RESOLUTION, 0.5d);
    p.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    p.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    // Fluorescent escape must be switched on, otherwise flDistBins stays 0 and
    // calcFluorescenceDistribution allocates zero-length bin arrays.
    p.put(Crystal.CRYSTAL_FLUORESCENT_ESCAPE, "TRUE");
    p.put(Crystal.CRYSTAL_FLUORESCENT_RESOLUTION, BINS);
    // Any composition but Average, for which escape is refused. The tests
    // supply their own escape factors, so which one does not matter.
    p.put(Crystal.CRYSTAL_COEFCALC, new CoefCalcFromParams());
    return new CrystalCuboid(p);
  }

  /**
   * Runs calcFluorescenceDistribution for one element whose four shells have
   * the given mu_abs values, and returns the furthest distance recorded per
   * shell.
   */
  private static double[] maxEscapeDistancePerShell(CrystalPolyhedron c,
      double[] muPerShell) throws Exception {
    // One element, 20 columns: shells occupy 4, 8, 12, 16.
    double[][] feFactors = new double[1][20];
    for (int shell = 0; shell < 4; shell++) {
      feFactors[0][(4 * shell) + 4] = muPerShell[shell];
    }

    // Mark every shell as capable of fluorescing, else the loop skips it.
    c.fluorescenceProportionEvent = new double[1][4];
    for (int shell = 0; shell < 4; shell++) {
      c.fluorescenceProportionEvent[0][shell] = 1.0;
    }

    Method m = CrystalPolyhedron.class.getDeclaredMethod(
        "calcFluorescenceDistribution", double[][].class);
    m.setAccessible(true);
    m.invoke(c, (Object) feFactors);

    Field f = CrystalPolyhedron.class.getDeclaredField("flDistancesTravelled");
    f.setAccessible(true);
    double[][][] distances = (double[][][]) f.get(c);

    double[] maxima = new double[4];
    for (int shell = 0; shell < 4; shell++) {
      double[] bins = distances[0][shell];
      maxima[shell] = bins[bins.length - 1];
    }
    return maxima;
  }

  /** The body diagonal, which the production code clamps distances to. */
  private static double crystalDiagonal() {
    return Math.sqrt(3 * DIM * DIM);
  }

  /**
   * The heart of the fix: four shells with four different absorption
   * coefficients must yield four different escape distances.
   */
  @Test
  public void eachShellUsesItsOwnAbsorptionCoefficient() throws Exception {
    double[] mu = {0.1, 0.2, 0.4, 0.8};
    double[] maxima = maxEscapeDistancePerShell(cube(), mu);

    for (int shell = 0; shell < 4; shell++) {
      double expected = -Math.log(ESCAPE_THRESHOLD) / mu[shell];
      assertTrue(expected < crystalDiagonal(),
          "test precondition: shell " + shell + " should not be clamped");
      assertEquals(expected, maxima[shell], 1e-9,
          "escape distance for shell " + shell + " (mu = " + mu[shell] + ")");
    }
  }

  /**
   * Stated as a difference rather than absolute values: with the pre-c0d0c3f
   * code every shell shared the K-shell coefficient, so all four distances
   * were identical.
   */
  @Test
  public void shellsWithDistinctCoefficientsDoNotShareADistance() throws Exception {
    double[] maxima = maxEscapeDistancePerShell(cube(), new double[] {0.1, 0.2, 0.4, 0.8});

    for (int shell = 1; shell < 4; shell++) {
      assertTrue(Math.abs(maxima[shell] - maxima[0]) > 1e-6,
          "shell " + shell + " has the same escape distance as the K shell ("
              + maxima[shell] + "), which is what using muabsIndex = 4 for "
              + "every shell would produce");
    }
    // Larger mu means the photon is reabsorbed sooner.
    for (int shell = 1; shell < 4; shell++) {
      assertTrue(maxima[shell] < maxima[shell - 1],
          "escape distance should shrink as mu grows (shell " + shell + ")");
    }
  }

  /** Distances longer than the crystal body diagonal are clamped to it. */
  @Test
  public void escapeDistanceIsClampedToTheCrystalDiagonal() throws Exception {
    // mu = 0.001 gives -ln(0.05)/mu ~= 2996 um, far beyond a 100 um cube.
    double[] maxima = maxEscapeDistancePerShell(cube(), new double[] {0.001, 0.001, 0.001, 0.001});

    for (int shell = 0; shell < 4; shell++) {
      assertEquals(crystalDiagonal(), maxima[shell], 1e-9,
          "shell " + shell + " should be clamped to the body diagonal");
    }
  }

  /** Bins run linearly from 0 to the maximum escape distance. */
  @Test
  public void distanceBinsAreLinearlySpacedFromZero() throws Exception {
    CrystalPolyhedron c = cube();
    maxEscapeDistancePerShell(c, new double[] {0.1, 0.2, 0.4, 0.8});

    Field f = CrystalPolyhedron.class.getDeclaredField("flDistancesTravelled");
    f.setAccessible(true);
    double[] bins = ((double[][][]) f.get(c))[0][0];

    assertEquals(0.0, bins[0], 0.0, "first bin should be at zero distance");
    double step = bins[bins.length - 1] / (bins.length - 1);
    for (int q = 0; q < bins.length; q++) {
      assertEquals(step * q, bins[q], 1e-9, "bin " + q + " is not evenly spaced");
    }
  }

  /** A shell that cannot fluoresce is left untouched. */
  @Test
  public void shellsThatCannotFluoresceAreSkipped() throws Exception {
    CrystalPolyhedron c = cube();
    double[][] feFactors = new double[1][20];
    for (int shell = 0; shell < 4; shell++) {
      feFactors[0][(4 * shell) + 4] = 0.1 * (shell + 1);
    }
    // Only the K shell can fluoresce.
    c.fluorescenceProportionEvent = new double[1][4];
    c.fluorescenceProportionEvent[0][0] = 1.0;

    Method m = CrystalPolyhedron.class.getDeclaredMethod(
        "calcFluorescenceDistribution", double[][].class);
    m.setAccessible(true);
    m.invoke(c, (Object) feFactors);

    Field f = CrystalPolyhedron.class.getDeclaredField("flDistancesTravelled");
    f.setAccessible(true);
    double[][][] distances = (double[][][]) f.get(c);

    assertTrue(distances[0][0][distances[0][0].length - 1] > 0,
        "the K shell should have been populated");
    for (int shell = 1; shell < 4; shell++) {
      assertEquals(0.0, distances[0][shell][distances[0][shell].length - 1], 0.0,
          "shell " + shell + " cannot fluoresce and should stay zero");
    }
  }

  /**
   * Elements without a tabulated L emission energy give no NaN factors.
   * <p>
   * Mg to Co have L fluorescence yields but an L emission energy of 0 keV,
   * where the absorption coefficient is NaN. The escape distances built from
   * it were NaN, the fluorescence went to voxel (0, 0, 0), outside the
   * crystal, and the escape totals printed NaN. Such a shell now has no
   * fluorescence yield for escape, so it relaxes locally.
   */
  @Test
  public void untabulatedLShellFluorescenceIsNotNaN() {
    List<String> atoms = Arrays.asList("FE", "CA", "S");
    List<Double> numbers = Arrays.asList(2.0, 2.0, 6.0);
    List<String> none = new ArrayList<String>();
    List<Double> noNumbers = new ArrayList<Double>();
    CoefCalcFromParams coefCalc = new CoefCalcFromParams(78.02, 78.02, 78.02,
        90.0, 90.0, 90.0, 24, 51, 0, 0, atoms, numbers, none, noNumbers, none,
        noNumbers, 0.64, null, null, 0, none, noNumbers, 0, 0);

    HashMap<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_ENERGY, 12.1);
    beamProperties.put(Beam.BEAM_FLUX, 2e12);
    beamProperties.put(Beam.BEAM_FWHM_X, 20d);
    beamProperties.put(Beam.BEAM_FWHM_Y, 20d);
    Beam beam = new BeamGaussian(beamProperties);
    // As Crystal.expose does before asking for the factors.
    coefCalc.updateCoefficients(beam);
    double[][] factors = coefCalc.getFluorescentEscapeFactors(beam);

    for (double[] row : factors) {
      for (int column = 0; column < row.length; column++) {
        assertFalse(Double.isNaN(row[column]),
            "column " + column + " of " + Arrays.toString(row));
      }
    }
  }

  /**
   * Fluorescence directions cover the sphere evenly.
   * <p>
   * The directions used to be a grid uniform in polar and azimuthal angle,
   * crowded at the poles, of which only the first -- straight along +z -- was
   * ever used. They are now a Fibonacci lattice: unit vectors whose mean is
   * close to zero and of which half point up.
   */
  @Test
  public void fluorescenceDirectionsCoverTheSphereEvenly() throws Exception {
    CrystalPolyhedron c = cube();
    maxEscapeDistancePerShell(c, new double[] {0.01, 0.01, 0.01, 0.01});

    Method m = CrystalPolyhedron.class.getDeclaredMethod(
        "findVoxelsReachedByFL", double[][].class);
    m.setAccessible(true);
    m.invoke(c, (Object) new double[1][20]);

    Field f = CrystalPolyhedron.class.getDeclaredField("flRelativeVoxXYZ");
    f.setAccessible(true);
    double[][][][][] offsets = (double[][][][][]) f.get(c);
    double[][] tracks = offsets[0][0][BINS - 1];

    double[] mean = new double[3];
    int up = 0;
    double length = Math.sqrt(tracks[0][0] * tracks[0][0]
        + tracks[0][1] * tracks[0][1] + tracks[0][2] * tracks[0][2]);
    for (double[] t : tracks) {
      double norm = Math.sqrt(t[0] * t[0] + t[1] * t[1] + t[2] * t[2]);
      assertEquals(length, norm, 1e-9 * length, "every track is as long");
      for (int axis = 0; axis < 3; axis++) {
        mean[axis] += t[axis] / norm / tracks.length;
      }
      if (t[2] > 0) {
        up++;
      }
    }
    assertEquals(256, tracks.length);
    assertEquals(tracks.length / 2, up, "half the directions point up");
    for (int axis = 0; axis < 3; axis++) {
      assertEquals(0.0, mean[axis], 1e-2, "mean direction, axis " + axis);
    }
  }
}
