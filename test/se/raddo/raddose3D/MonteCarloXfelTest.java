package se.raddo.raddose3D;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The Monte Carlo (Subprogram MONTECARLO or GOS) and XFEL subprograms:
 * reproducible from a seed, and refusing what they cannot run.
 */
public class MonteCarloXfelTest {

  private static final List<String> NONE = new ArrayList<String>();
  private static final List<Double> NO_AMOUNTS = new ArrayList<Double>();

  /** Insulin, with the given heavy atoms per monomer. */
  private static CoefCalcCompute insulin(final List<String> heavyAtoms,
      final List<Double> amounts, final long photons) {
    return new CoefCalcFromParams(78.02, 78.02, 78.02, 90.0, 90.0, 90.0, 24,
        51, 0, 0, heavyAtoms, amounts, Arrays.asList("P"),
        Arrays.asList(425.0), NONE, NO_AMOUNTS, 0.64, null, null, 0, NONE,
        NO_AMOUNTS, 0, photons);
  }

  private static CoefCalcCompute insulin(final long photons) {
    return insulin(Arrays.asList("ZN", "S"), Arrays.asList(0.333, 6.0),
        photons);
  }

  private static Crystal cuboid(final String program, final CoefCalc coefCalc,
      final double x, final double y, final double z, final double ppm) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Crystal.CRYSTAL_DIM_X, x);
    p.put(Crystal.CRYSTAL_DIM_Y, y);
    p.put(Crystal.CRYSTAL_DIM_Z, z);
    p.put(Crystal.CRYSTAL_RESOLUTION, ppm);
    p.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    p.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    p.put(Crystal.CRYSTAL_PROGRAM, program);
    if (coefCalc != null) {
      p.put(Crystal.CRYSTAL_COEFCALC, coefCalc);
    }
    return new CrystalCuboid(p);
  }

  private static Crystal cuboid(final String program, final CoefCalc coefCalc) {
    return cuboid(program, coefCalc, 20, 20, 20, 0.5);
  }

  /** A Gaussian beam; circular with unequal FWHM samples a bivariate normal. */
  private static Beam gaussian(final double energy, final Double pulseEnergy,
      final boolean circular) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_FWHM_X, 20.0);
    p.put(Beam.BEAM_FWHM_Y, circular ? 12.0 : 20.0);
    p.put(Beam.BEAM_ENERGY, energy);
    p.put(Beam.BEAM_FLUX, 2e12);
    p.put(Beam.BEAM_COLL_H, 30.0);
    p.put(Beam.BEAM_COLL_V, 30.0);
    if (circular) {
      p.put(Beam.BEAM_CIRCULAR, "TRUE");
    }
    if (pulseEnergy != null) {
      p.put(Beam.PULSE_ENERGY, pulseEnergy);
    }
    return new BeamGaussian(p);
  }

  /** A wedge at one angle, exposed for the given time (fs for XFEL). */
  private static Wedge still(final double exposure) {
    return new Wedge(2d, 0d, 0d, exposure, null, null, null, null, null,
        null, null, null);
  }

  /** Runs the crystal from a seed, returning each file it wrote. */
  private static Map<String, String> run(final Crystal crystal,
      final Beam beam, final Wedge wedge, final Path dir, final long seed)
      throws IOException {
    crystal.setSubprogramOutputDirectory(dir.toFile());
    RandomSource.setSeed(seed);
    crystal.expose(beam, wedge);
    Map<String, String> files = new TreeMap<String, String>();
    for (File f : dir.toFile().listFiles()) {
      files.put(f.getName(), new String(Files.readAllBytes(f.toPath()),
          StandardCharsets.UTF_8));
    }
    return files;
  }

  private static Map<String, String> runTwice(final String program,
      final long photons, final Beam beam, final Wedge wedge, final Path dir)
      throws IOException {
    Map<String, String> first = run(cuboid(program, insulin(photons)), beam,
        wedge, Files.createDirectory(dir.resolve("first")), 7);
    Map<String, String> second = run(cuboid(program, insulin(photons)), beam,
        wedge, Files.createDirectory(dir.resolve("second")), 7);
    assertFalse(first.isEmpty());
    assertEquals(first, second);
    return first;
  }

  // ---------------------------------------------------------- reproducible

  /**
   * Elements, and the probabilities an element is picked from, iterate by
   * atomic number. They iterated in identity-hash order, which could change
   * between JVMs, so the same seed could pick a different element.
   */
  @Test
  public void elementsIterateByAtomicNumber() {
    CoefCalcCompute coefCalc = insulin(Arrays.asList("SE", "ZN", "S"),
        Arrays.asList(1.0, 0.333, 6.0), 100);
    coefCalc.updateCoefficients(12.1);
    assertEquals(Arrays.asList(1, 6, 7, 8, 15, 16, 30, 34),
        atomicNumbers(coefCalc.getPresentElements(false)));
    assertEquals(Arrays.asList(1, 6, 7, 8, 15, 16, 30, 34),
        atomicNumbers(coefCalc.getPhotoElectricProbsElement(12.1).keySet()));
  }

  private static List<Integer> atomicNumbers(final Iterable<Element> elements) {
    List<Integer> z = new ArrayList<Integer>();
    for (Element e : elements) {
      z.add(e.getAtomicNumber());
    }
    return z;
  }

  @Test
  public void elementsHashByAtomicNumber() {
    assertEquals(30, ElementDatabase.getInstance().getElement("ZN").hashCode());
    assertEquals(30,
        ElementDatabaseEM.getInstance().getElement("ZN").hashCode());
  }

  /**
   * The same seed gives the same files. The circular Gaussian beam with
   * unequal FWHM drew its positions from a generator seeded by the clock.
   */
  @Test
  public void aMonteCarloRunIsReproducible(@TempDir final Path dir)
      throws IOException {
    Map<String, String> files = runTwice("MONTECARLO", 2000,
        gaussian(12.1, null, true), still(30), dir);
    assertTrue(files.containsKey("outputMC.CSV"), files.keySet().toString());
  }

  @Test
  public void aGosRunIsReproducible(@TempDir final Path dir)
      throws IOException {
    runTwice("GOS", 100, gaussian(12.1, null, true), still(30), dir);
  }

  @Test
  public void anXfelRunIsReproducible(@TempDir final Path dir)
      throws IOException {
    Map<String, String> files = runTwice("XFEL", 100,
        gaussian(12.1, 1.0, true), still(30), dir);
    assertTrue(files.containsKey("outputXFEL.CSV"), files.keySet().toString());
  }

  // ---------------------------------------------------------------- input

  /** Collects what the parser makes of an input. */
  private static class Collector implements Initializer {
    private final List<Crystal> crystals = new ArrayList<Crystal>();

    @Override
    public void setCrystal(final Crystal c) {
      crystals.add(c);
    }

    @Override
    public void setBeam(final Beam b) {
    }

    @Override
    public void exposeWedge(final Wedge w) {
    }

    @Override
    public void raiseWarning(final String warning) {
    }

    @Override
    public void addReference(final String reference) {
    }
  }

  private static Crystal parse(final String simPhotons, final String runs)
      throws InputException {
    Collector collector = new Collector();
    new InputParserString("Crystal\nType Cuboid\nDimensions 20 20 20\n"
        + "AbsCoefCalc RD3D\nUnitCell 78 78 78\nNumMonomers 24\n"
        + "NumResidues 51\nSubprogram MonteCarlo\nSimPhotons " + simPhotons
        + "\nRuns " + runs + "\n").sendData(collector);
    return collector.crystals.get(0);
  }

  /** Long.parseLong refused 1e6 and 1000000.0, and the run crashed. */
  @Test
  public void simPhotonsAndRunsAcceptWholeNumbersInAnyNotation()
      throws InputException {
    Crystal crystal = parse("1e3", "2.0");
    assertEquals(1000, crystal.getCoefCalc().getNumberSimulatedElectrons());
    assertEquals(2, crystal.runs);
    assertEquals(1000000,
        parse("1000000.0", "1").getCoefCalc().getNumberSimulatedElectrons());
  }

  @Test
  public void aFractionOfAPhotonIsRefused() {
    InputException e = assertThrows(InputException.class,
        () -> parse("1.5", "1"));
    assertTrue(e.toString().contains("SimPhotons needs a whole number"),
        e.toString());
    e = assertThrows(InputException.class, () -> parse("1000", "0"));
    assertTrue(e.toString().contains("Runs needs a whole number"),
        e.toString());
  }

  // ---------------------------------------------------------------- caches

  /** They were read from disk again for every electron. */
  @Test
  public void theOvervoltageTablesAreReadOnce() throws Exception {
    CoefCalcCompute coefCalc = insulin(100);
    Field low = CoefCalcCompute.class.getDeclaredField("lowOvervoltages");
    low.setAccessible(true);
    coefCalc.populateCrossSectionCoefficients();
    Object first = low.get(coefCalc);
    coefCalc.populateCrossSectionCoefficients();
    assertSame(first, low.get(coefCalc));
  }

  /** It does not depend on the energy, and was searched for every event. */
  @Test
  public void theSternheimerFactorDependsOnTheCompositionAlone() {
    CoefCalcCompute coefCalc = insulin(100);
    double a = coefCalc.getSturnheimera(0.1, false);
    assertEquals(a, coefCalc.getSturnheimera(300, false), 0.0);
    assertEquals(a, insulin(100).getSturnheimera(12, false), 0.0);
  }

  /**
   * The stopping power's total charge was a long, into which each element's
   * Z times its atom count was truncated: a count one ulp below a whole
   * number lost a whole electron, and the stopping power moved by about
   * 1e-6, by an amount that depended on the order the elements were summed.
   */
  @Test
  public void theStoppingPowerHasNoKnifeEdgeAtWholeNumbers() {
    CoefCalcCompute coefCalc = insulin(100);
    Element carbon = coefCalc.getParser().getElement("C");
    double whole = Math.rint(coefCalc.getMacromolecularOccurrence(carbon));
    coefCalc.setMacromolecularOccurrence(carbon, whole);
    double atWhole = coefCalc.getStoppingPower(300, false);
    coefCalc.setMacromolecularOccurrence(carbon, Math.nextDown(whole));
    double justBelow = coefCalc.getStoppingPower(300, false);
    assertEquals(atWhole, justBelow, atWhole * 1e-12);
  }

  // ------------------------------------------------------------------ XFEL

  /**
   * A 1 fs pulse through a 60 um crystal needs about 20,000 time bins of
   * 0.01 fs. They were allocated at 15,300 whatever the pulse, so a deposit
   * deeper than about 46 um threw ArrayIndexOutOfBoundsException.
   */
  @Test
  public void xfelTimeBinsFollowThePulse(@TempDir final Path dir)
      throws IOException {
    Map<String, String> files = run(cuboid("XFEL", insulin(100), 10, 10, 60,
        0.2), gaussian(12.1, 1.0, false), still(1), dir, 3);
    assertTrue(files.containsKey("outputXFEL.CSV"), files.keySet().toString());
  }

  /**
   * XFEL tests whether a point is in the crystal at the point itself. It
   * used to keep the answer for the first point tested in each voxel, so in
   * a voxel the surface cuts through, a point inside the crystal was outside
   * if a point outside had been tested there first.
   */
  @Test
  public void xfelTestsOccupancyAtEachPoint() throws Exception {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Crystal.CRYSTAL_DIM_X, 5d);
    p.put(Crystal.CRYSTAL_DIM_Y, 5d);
    p.put(Crystal.CRYSTAL_RESOLUTION, 1d);
    p.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    p.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    p.put(Crystal.CRYSTAL_PROGRAM, "XFEL");
    p.put(Crystal.CRYSTAL_COEFCALC, insulin(100));
    CrystalCylinder cylinder = new CrystalCylinder(p);
    Wedge wedge = still(30);
    XFEL xfel = new XFEL(cylinder.vertices, cylinder.indices,
        (double[][][][]) field(cylinder, "crystCoord"),
        cylinder.crystalPixPerUM, (int[]) field(cylinder, "crystSizeVoxels"),
        cylinder.crystOcc, 1, false, true, true, wedge, false);
    java.lang.reflect.Method inside = XFEL.class.getDeclaredMethod(
        "isMicrocrystalAt", double.class, double.class, double.class,
        double.class, Wedge.class);
    inside.setAccessible(true);
    // The circle of radius 2500 nm lies in x and z. Both points are in the
    // voxel from 1000 to 2000 nm along x and z.
    assertFalse((Boolean) inside.invoke(xfel, 1900.0, 0.0, 1900.0, 0.0, wedge));
    assertTrue((Boolean) inside.invoke(xfel, 1100.0, 0.0, 1100.0, 0.0, wedge));
  }

  // ----------------------------------------------------- photoelectric shell

  /** An MC run's shell model, for a 5 um insulin cuboid. */
  @SuppressWarnings("unchecked")
  private static double[] shellChances(final Element element,
      final double energy) throws Exception {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Crystal.CRYSTAL_DIM_X, 5d);
    p.put(Crystal.CRYSTAL_DIM_Y, 5d);
    p.put(Crystal.CRYSTAL_DIM_Z, 5d);
    p.put(Crystal.CRYSTAL_RESOLUTION, 1d);
    p.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    p.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    p.put(Crystal.CRYSTAL_PROGRAM, "MONTECARLO");
    p.put(Crystal.CRYSTAL_COEFCALC, insulin(100));
    CrystalCuboid cuboid = new CrystalCuboid(p);
    MC mc = new MC(cuboid.vertices, cuboid.indices,
        (double[][][][]) field(cuboid, "crystCoord"), cuboid.crystalPixPerUM,
        (int[]) field(cuboid, "crystSizeVoxels"), cuboid.crystOcc, 1, false,
        false, false, new double[3], false);
    java.lang.reflect.Method shells = MC.class.getDeclaredMethod(
        "getRelativeShellProbs", Map.class, double.class);
    shells.setAccessible(true);
    Map<Element, Double> absorbing = new HashMap<Element, Double>();
    absorbing.put(element, 1.0);
    return ((Map<Element, double[]>) shells.invoke(mc, absorbing, energy))
        .get(element);
  }

  /**
   * Iodine in a 12.1 keV beam, below its 33.2 keV K edge. Its L shells take
   * 78% of its absorption, and M shells are modelled only from tantalum up.
   * A draw past the L shells took the K shell, and freed a photoelectron of
   * 12.1 - 33.2 = -21 keV. It now ionises an outer shell: no binding energy,
   * and a photoelectron with the photon's whole energy.
   */
  @Test
  public void belowTheKEdgeAPhotoelectronNeverTakesTheKShell()
      throws Exception {
    Element iodine = ElementDatabase.getInstance().getElement("I");
    double[] chances = shellChances(iodine, 12.1);
    assertEquals(0.0, chances[0]);
    double modelled = chances[3];
    assertEquals(0.78, modelled, 0.01, Arrays.toString(chances));
    for (double draw : new double[] {0.0, modelled * 0.5, modelled, 0.999999}) {
      assertTrue(MC.ionisedShell(chances, draw) != 0, "draw " + draw);
    }
    assertEquals(1, MC.ionisedShell(chances, 0.0));
    assertEquals(MC.OUTER_SHELL, MC.ionisedShell(chances, modelled));
    assertEquals(MC.OUTER_SHELL, MC.ionisedShell(chances, 0.999999));
  }

  /**
   * Sodium, whose L shells the model leaves out (it has them only from
   * magnesium up): 8.4% of its absorption, which took the K shell's binding
   * energy, is by its outer shells.
   */
  @Test
  public void aDrawPastEveryShellIonisesAnOuterShell() throws Exception {
    Element sodium = ElementDatabase.getInstance().getElement("NA");
    double[] chances = shellChances(sodium, 12.1);
    assertEquals(1 - 1 / 11.9, chances[0], 1e-12, Arrays.toString(chances));
    assertEquals(0, MC.ionisedShell(chances, chances[0] * 0.99));
    assertEquals(MC.OUTER_SHELL, MC.ionisedShell(chances, chances[0]));
  }

  /**
   * Where the last shell modelled has an edge ratio of 0 -- carbon's K,
   * zinc's L3 -- it takes the rest, and no draw is past it.
   */
  @Test
  public void mostElementsHaveAShellForEveryDraw() throws Exception {
    ElementDatabase elements = ElementDatabase.getInstance();
    for (String symbol : new String[] {"H", "C", "N", "O", "S", "P", "ZN"}) {
      double[] chances = shellChances(elements.getElement(symbol), 12.1);
      double last = 0;
      for (double chance : chances) {
        last = Math.max(last, chance);
      }
      assertEquals(1.0, last, 1e-12, symbol + " " + Arrays.toString(chances));
    }
  }

  private static Object field(final Object owner, final String name)
      throws Exception {
    Field f = CrystalPolyhedron.class.getDeclaredField(name);
    f.setAccessible(true);
    return f.get(owner);
  }

  // -------------------------------------------------------------- refusals

  @Test
  public void anExperimentalBeamIsRefused(@TempDir final Path dir) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_ENERGY, 12.1);
    p.put(Beam.BEAM_FLUX, 2e12);
    p.put(Beam.BEAM_COLL_H, 30.0);
    p.put(Beam.BEAM_COLL_V, 30.0);
    Beam experimental = new BeamTophat(p) {
      @Override
      public String getType() {
        return "Experimental";
      }
    };
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> run(cuboid("MONTECARLO", insulin(100)), experimental, still(30),
            dir, 1));
    assertTrue(e.getMessage().contains("TopHat or Gaussian"), e.getMessage());
  }

  /** ELSEPA's tables above 20 keV stop at Z = 52; a heavier atom crashed. */
  @Test
  public void aHeavyAtomAbove20KeVIsRefused(@TempDir final Path dir) {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> run(cuboid("MONTECARLO", insulin(Arrays.asList("I"),
            Arrays.asList(1.0), 100)), gaussian(25, null, false), still(30),
            dir, 1));
    assertTrue(e.getMessage().contains("Z = 53"), e.getMessage());
  }

  @Test
  public void anXfelBeamWithoutAPulseEnergyIsRefused(@TempDir final Path dir) {
    IllegalStateException e = assertThrows(IllegalStateException.class,
        () -> run(cuboid("XFEL", insulin(100)), gaussian(12.1, null, false),
            still(30), dir, 1));
    assertTrue(e.getMessage().contains("PulseEnergy"), e.getMessage());
  }

  @Test
  public void theAverageCompositionIsRefused(@TempDir final Path dir) {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> run(cuboid("MONTECARLO", null), gaussian(12.1, null, false),
            still(30), dir, 1));
    assertTrue(e.getMessage().contains("AVERAGE"), e.getMessage());
  }

  // ---------------------------------------------------------------- output

  /** The files used the default locale: a German one wrote 57,305000. */
  @Test
  public void theFilesWriteDecimalPoints(@TempDir final Path dir)
      throws IOException {
    Locale before = Locale.getDefault();
    Locale.setDefault(Locale.GERMANY);
    try {
      Map<String, String> files = run(cuboid("MONTECARLO", insulin(500)),
          gaussian(12.1, null, false), still(30), dir, 5);
      String[] header = files.get("outputMC.CSV").split("\n")[0].split(",");
      String[] row = files.get("outputMC.CSV").split("\n")[1].split(",");
      assertEquals(header.length, row.length, files.get("outputMC.CSV"));
      for (String field : row) {
        Double.parseDouble(field.trim());
      }
    } finally {
      Locale.setDefault(before);
    }
  }

  @Test
  public void theFilesGoWhereTheyAreSent(@TempDir final Path dir)
      throws IOException {
    Map<String, String> files = run(cuboid("MONTECARLO", insulin(200)),
        gaussian(12.1, null, false), still(30), dir, 5);
    assertArrayEquals(new String[] {"outputIonsPerAtom.CSV", "outputMC.CSV",
        "outputXFEL.CSV"}, files.keySet().toArray(new String[0]));
  }
}
