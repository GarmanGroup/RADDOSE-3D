package se.raddo.raddose3D;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * MicroED (Subprogram EMED), run the way CrystalPolyhedron.startMicroED runs
 * it, writing its two files into a temporary directory.
 */
public class MicroEDTest {

  private static final List<String> NONE = new ArrayList<String>();
  private static final List<Double> NO_AMOUNTS = new ArrayList<Double>();

  /** Insulin, as in the MicroED example input, with extra heavy atoms. */
  private static CoefCalcCompute insulin(final List<String> heavyAtoms,
      final List<Double> amounts) {
    return new CoefCalcFromParams(78.02, 78.02, 78.02, 90.0, 90.0, 90.0, 24,
        51, 0, 0, heavyAtoms, amounts, Arrays.asList("P"),
        Arrays.asList(425.0), NONE, NO_AMOUNTS, 0.64, null, null, 0, NONE,
        NO_AMOUNTS, 0, 0);
  }

  private static CoefCalcCompute insulin() {
    return insulin(Arrays.asList("ZN", "S"), Arrays.asList(0.333, 6.0));
  }

  private static Beam tophat(final Double exposure) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_ENERGY, 300.0);
    p.put(Beam.BEAM_COLL_H, 4.0);
    p.put(Beam.BEAM_COLL_V, 4.0);
    if (exposure != null) {
      p.put(Beam.BEAM_EXPOSURE, exposure);
    }
    else {
      p.put(Beam.BEAM_FLUX, 1e12);
    }
    return new BeamTophat(p);
  }

  /** A cuboid's vertices, in micrometres, as CrystalCuboid has them. */
  private static double[][] cuboid(final double x, final double y,
      final double z) {
    double[][] vertices = new double[8][];
    int n = 0;
    for (double i : new double[] {-x / 2, x / 2}) {
      for (double j : new double[] {-y / 2, y / 2}) {
        for (double k : new double[] {-z / 2, z / 2}) {
          vertices[n++] = new double[] {i, j, k};
        }
      }
    }
    return vertices;
  }

  /** The values of outputMicroED.CSV, keyed by its header. */
  private static Map<String, Double> run(final double thicknessUm,
      final CoefCalc coefCalc, final Beam beam, final Path dir)
      throws IOException {
    MicroED microED = new MicroED(cuboid(2.0, 2.0, thicknessUm), null, null,
        0, null, null, "CUBOID");
    microED.setOutputDirectory(dir.toFile());
    coefCalc.updateCoefficients(beam);
    microED.CalculateEM(beam, new Wedge(0d, 0d, 0d, 1d, null, null, null,
        null, null, null, null, null), coefCalc);

    List<String> lines = Files.readAllLines(dir.resolve("outputMicroED.CSV"),
        StandardCharsets.UTF_8);
    String[] names = lines.get(0).split(",");
    String[] values = lines.get(1).split(",");
    Map<String, Double> row = new HashMap<String, Double>();
    for (int i = 0; i < names.length; i++) {
      row.put(names[i].trim(), Double.parseDouble(values[i].trim()));
    }
    return row;
  }

  private static List<String[]> slices(final Path dir) throws IOException {
    List<String> lines = Files.readAllLines(
        dir.resolve("outputMicroED_slices.csv"), StandardCharsets.UTF_8);
    List<String[]> rows = new ArrayList<String[]>();
    for (String line : lines.subList(1, lines.size())) {
      rows.add(line.split(","));
    }
    return rows;
  }

  /**
   * The distant-collision integral for subshell i now reads subshell i's
   * binding energy, as the rest of the inelastic model does. It used to read
   * getShellBinding(i), which counts shells (K, L1, M1): for an L2 subshell
   * it started the integral at the M1 edge. This is the inelastic mean free
   * path the fix gives for the baseline; before it, 234.1956 nm.
   */
  @Test
  public void inelasticMeanFreePath() {
    CoefCalcCompute coefCalc = insulin();
    assertEquals(253.15032, coefCalc.getGOSInel(false, 300.0), 1e-4);
  }

  /**
   * A crystal thinner than a 5 nm slice used to have no slices at all, and
   * its dose came out as 0/0.
   */
  @Test
  public void aCrystalThinnerThanASliceHasOne(@TempDir final Path dir)
      throws IOException {
    Map<String, Double> row = run(0.004, insulin(), tophat(1.0), dir);

    double dose = row.get("Dose");
    assertTrue(dose > 0 && !Double.isInfinite(dose), "dose " + dose);
    List<String[]> slices = slices(dir);
    assertEquals(1, slices.size());
    assertEquals(0.0, Double.parseDouble(slices.get(0)[1]), 0.0);
    assertEquals(dose, Double.parseDouble(slices.get(0)[2]), dose * 1e-7);
  }

  /** Depths step by the slices' real width, here 299 nm / 59. */
  @Test
  public void sliceDepthsUseTheSliceWidth(@TempDir final Path dir)
      throws IOException {
    run(0.299, insulin(), tophat(1.0), dir);

    List<String[]> slices = slices(dir);
    assertEquals(59, slices.size());
    for (int i = 0; i < slices.size(); i++) {
      assertEquals(i * 299.0 / 59, Double.parseDouble(slices.get(i)[1]), 1e-4,
          "slice " + (i + 1));
    }
  }

  @Test
  public void elementsLackingBindingEnergiesAreListedInOrder() {
    assertTrue(insulin().getElementsLackingBindingEnergies().isEmpty());
    assertEquals(Arrays.asList("NA", "AL", "CA"),
        insulin(Arrays.asList("CA", "ZN", "AL", "S", "NA"),
            Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0))
            .getElementsLackingBindingEnergies());
  }

  /**
   * Aluminium's M1 binding energy is blank in the tables, and MicroED threw
   * NullPointerException reading it. Sodium's L2 is zero, which gave NaN
   * for the inelastic path -- and the grid searches, which stop where the
   * information coefficient falls, never stopped, and reported the last
   * point of their grids (1990 kV, 1995 nm). Both now report NaN for
   * everything the inelastic model feeds, and the dose as before.
   */
  @Test
  public void elementsLackingBindingEnergiesGiveNaN(@TempDir final Path dir)
      throws IOException {
    for (String element : new String[] {"AL", "NA"}) {
      Path out = Files.createDirectory(dir.resolve(element));
      Map<String, Double> row = run(0.3, insulin(Arrays.asList(element),
          Arrays.asList(1.0)), tophat(1.0), out);

      assertTrue(row.get("Dose") > 0, element);
      assertTrue(row.get("Elastic") > 0, element);
      assertTrue(row.get("Single_elastic") > 0, element);
      for (String name : new String[] {"Inelastic", "Productive", "Info_coef",
          "Best_en", "Best_t"}) {
        assertTrue(Double.isNaN(row.get(name)), element + " " + name);
      }
    }
  }

  @Test
  public void theBaselineSearchesForTheBestVoltageAndThickness(
      @TempDir final Path dir) throws IOException {
    Map<String, Double> row = run(0.3, insulin(), tophat(1.0), dir);
    for (String name : row.keySet()) {
      assertFalse(Double.isNaN(row.get(name)), name);
    }
  }

  /**
   * MicroED reads an exposure that only a TopHat beam carries. A Gaussian
   * beam reported zero, so the dose was zero and the information
   * coefficient NaN.
   */
  @Test
  public void aGaussianBeamIsRefused(@TempDir final Path dir) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_FWHM_X, 2.0);
    p.put(Beam.BEAM_FWHM_Y, 2.0);
    p.put(Beam.BEAM_ENERGY, 300.0);
    p.put(Beam.BEAM_FLUX, 1e12);
    p.put(Beam.BEAM_COLL_H, 4.0);
    p.put(Beam.BEAM_COLL_V, 4.0);
    Beam gaussian = new BeamGaussian(p);

    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> run(0.3, insulin(), gaussian, dir));
    assertTrue(e.getMessage().contains("TopHat"), e.getMessage());
  }

  /** A TopHat beam given a flux but no exposure threw NullPointerException. */
  @Test
  public void aTophatBeamWithoutAnExposureIsRefused(@TempDir final Path dir) {
    IllegalStateException e = assertThrows(IllegalStateException.class,
        () -> run(0.3, insulin(), tophat(null), dir));
    assertTrue(e.getMessage().contains("Exposure"), e.getMessage());
  }
}
