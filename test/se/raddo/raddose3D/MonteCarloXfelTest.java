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

  /**
   * A simple Monte Carlo run's outputXFEL.CSV gives the Monte Carlo's own
   * doses, as outputMC.CSV does: they held only the binding energies of
   * atoms that do not relax and the energy of electrons freed with under
   * 0.05 keV. And outputIonsPerAtom.CSV lists the elements by atomic
   * number, not in hash order.
   */
  @Test
  public void aMonteCarloRunWritesItsOwnDosesAndElementsInOrder(
      @TempDir final Path dir) throws IOException {
    Map<String, String> files = run(cuboid("MONTECARLO",
        insulin(Arrays.asList("ZN", "S", "SE"), Arrays.asList(0.333, 6.0, 4.0),
            20000)), gaussian(13.0, null, false), still(10), dir, 5);
    String[] mc = files.get("outputMC.CSV").split("\\n")[1].split(",");
    String[] xfel = files.get("outputXFEL.CSV").split("\\n")[1].split(",");
    assertEquals(Double.parseDouble(mc[3]), Double.parseDouble(xfel[3]), 1e-6);
    assertEquals(Double.parseDouble(mc[4]), Double.parseDouble(xfel[4]), 1e-6);
    List<Integer> order = new ArrayList<Integer>();
    for (String line : files.get("outputIonsPerAtom.CSV").split("\\n")) {
      if (!line.startsWith("Atomic")) {
        order.add(Integer.parseInt(line.split(",")[0].trim()));
      }
    }
    List<Integer> sorted = new ArrayList<Integer>(order);
    java.util.Collections.sort(sorted);
    assertEquals(sorted, order);
    assertTrue(order.size() >= 5, order.toString());
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
   * The distant collisions' integral, as it was: at each of its thousand
   * steps, getpDisW found the distribution's ends again.
   */
  private static double distantStepByStep(final CoefCalcCompute coefCalc,
      final double energy, final int n, final int i, final Element e,
      final double a) {
    double uk = coefCalc.getShellBindingSubshell(i, e) * 1000;
    double w = coefCalc.getShellBindingSubshell(i, e) * Beam.KEVTOJOULES;
    double wk = coefCalc.getWkMolecule(a, e, i, false);
    double wdis = 3 * coefCalc.getWak(energy, wk, uk) - 2 * uk;
    wdis = (wdis / 1000) * Beam.KEVTOJOULES;
    double step = wdis / coefCalc.Wbins;
    double previous = 0;
    double sum = 0;
    int count = 0;
    while (w <= wdis) {
      double y = w == 0 ? 0
          : Math.pow(w, n - 1) * coefCalc.getpDisW(energy, w, a, e, i, false);
      if (count > 0) {
        sum += step * ((y + previous) / 2);
      }
      count += 1;
      w += step;
      previous = y;
    }
    return sum;
  }

  /**
   * The distant collisions' integral finds its distribution's ends once,
   * not at every step, where they were nearly all of the GOS model's time.
   * Every subshell's integral is the same to the last bit.
   */
  @Test
  public void theDistantIntegralIsTheSumItWas() {
    CoefCalcCompute coefCalc = insulin(Arrays.asList("ZN", "S", "SE"),
        Arrays.asList(0.333, 6.0, 4.0), 100);
    double a = coefCalc.getSturnheimera(12.1, false);
    int compared = 0;
    for (String name : Arrays.asList("H", "C", "N", "O", "P", "S", "ZN",
        "SE")) {
      Element e = coefCalc.getParser().getElement(name);
      int valence = coefCalc.getNumValenceElectronsSubshells(e)[1];
      for (int i = 0; i <= valence; i++) {
        double uk = coefCalc.getShellBindingSubshell(i, e) * 1000;
        for (double energy : new double[] {0.06, 0.5, 2.0, 12.1, 30.0}) {
          for (int n = 0; n <= 1; n++) {
            double expected = distantStepByStep(coefCalc, energy, n, i, e, a);
            assertEquals(expected,
                coefCalc.integrateDist(energy, uk, n, i, e, a, false), 0.0,
                name + " subshell " + i + " at " + energy + " keV, n = " + n);
            compared += expected != 0 ? 1 : 0;
          }
        }
      }
    }
    assertTrue(compared > 100, "only " + compared + " integrals were not 0");
  }

  /**
   * A collision with a valence subshell takes its binding energy from the
   * table the GOS model's cross-sections are built with. Phosphorus's and
   * selenium's valence subshells have no photoelectric edge, and took 0:
   * the recoil was 0, a longitudinal deflection NaN, and the electron lost.
   */
  @Test
  public void aValenceCollisionHasItsSubshellsBindingEnergy() {
    CoefCalcCompute coefCalc = insulin(Arrays.asList("ZN", "S", "SE"),
        Arrays.asList(0.333, 6.0, 4.0), 100);
    double a = coefCalc.getSturnheimera(12.1, false);
    for (String name : Arrays.asList("P", "SE", "S", "C")) {
      Element e = coefCalc.getParser().getElement(name);
      int valence = coefCalc.getNumValenceElectronsSubshells(e)[1];
      double uk = MC.collisionBindingEnergy(coefCalc, e, valence, true);
      assertEquals(coefCalc.getShellBindingSubshell(valence, e), uk, 0.0, name);
      assertTrue(uk > 0, name);
      // At 12.1 keV the recoil's limit is Uk itself, and Wak is Wk.
      double wk = coefCalc.getWkMolecule(a, e, valence, false);
      double recoil = coefCalc.getRecoilEnergyDistant(12.1, wk, uk * 1000);
      assertTrue(recoil > 0 && !Double.isInfinite(recoil),
          name + ": recoil " + recoil);
    }
    // An inner shell, which Bethe's cross-sections choose, keeps its edge.
    Element p = coefCalc.getParser().getElement("P");
    assertEquals(p.getKEdge(), MC.collisionBindingEnergy(coefCalc, p, 0, false),
        0.0);
  }

  /**
   * The GOS model gives each element as many electrons as its atomic
   * number: its inner subshells' and its valence subshell's. Arsenic and
   * selenium had two more, their 4s counted in M5 and again as valence.
   */
  @Test
  public void everyElementHasAsManyElectronsAsItsAtomicNumber() {
    CoefCalcCompute coefCalc = insulin(100);
    ElementDatabase elements = ElementDatabase.getInstance();
    for (int z = 1; z <= 100; z++) {
      Element e = elements.getElement(z);
      if (e == null) {
        continue;
      }
      int[] electrons = coefCalc.getNumValenceElectronsSubshells(e);
      int total = electrons[0];
      for (int i = 0; i < electrons[1]; i++) {
        total += CoefCalcCompute.SUBSHELL_ELECTRONS[i];
      }
      assertEquals(z, total, e.getElementName());
    }
  }

  /**
   * From bromine on, an element has the K to M5 subshells inner and every
   * other electron in a valence subshell, bound more weakly than M5. Every
   * electron was valence, bound by the K edge, so below it no collision
   * with one could happen.
   */
  @Test
  public void heavyAtomsHaveInnerSubshellsAndAValenceSubshell() {
    CoefCalcCompute coefCalc = insulin(100);
    ElementDatabase elements = ElementDatabase.getInstance();
    for (int z = 35; z <= 100; z++) {
      Element e = elements.getElement(z);
      if (e == null) {
        continue;
      }
      assertArrayEquals(new int[] {z - 28, 9},
          coefCalc.getNumValenceElectronsSubshells(e), e.getElementName());
      for (int i = 0; i < 9; i++) {
        assertTrue(coefCalc.getShellBindingSubshell(i, e)
            > coefCalc.getShellBindingSubshell(i + 1, e),
            e.getElementName() + " subshell " + i);
      }
    }
    // Iodine's valence electrons, 4s to 5p, are bound by 193 to 10 eV.
    double iodine = coefCalc.getShellBindingSubshell(9,
        elements.getElement("I"));
    assertTrue(iodine > 0.010 && iodine < 0.193, "iodine " + iodine);
  }

  /**
   * A heavy atom leaves the Sternheimer factor, which matches the model's
   * mean excitation energy to the tables', to the rest of the composition.
   * Iodide at 1 M, its 53 electrons bound by its K edge, took it from 1.65
   * to 1.2, and so every resonance energy in the crystal down with it; and
   * iodine had no collisions with electrons below 33 keV.
   */
  @Test
  public void aHeavyAtomLeavesTheSternheimerFactorToTheComposition() {
    CoefCalcCompute iodide = new CoefCalcFromParams(78.02, 78.02, 78.02, 90.0,
        90.0, 90.0, 24, 51, 0, 0, Arrays.asList("ZN", "S"),
        Arrays.asList(0.333, 6.0), Arrays.asList("P", "I"),
        Arrays.asList(425.0, 1000.0), NONE, NO_AMOUNTS, 0.64, null, null, 0,
        NONE, NO_AMOUNTS, 0, 100);
    double a = insulin(100).getSturnheimera(10, false);
    assertEquals(a, iodide.getSturnheimera(10, false), 0.02);
    iodide.getGOSInel(false, 10);
    Element iodine = iodide.getParser().getElement("I");
    assertTrue(iodide.GOSinelastic.get(iodine)[9][3] > 0);
  }

  /**
   * Xenon and every heavier element but gadolinium had no L1 or M1 binding
   * energy, so a photoelectron from platinum's L1 shell stopped any Monte
   * Carlo run with a NullPointerException.
   */
  @Test
  public void aPlatinumCrystalRuns(@TempDir final Path dir) throws IOException {
    CoefCalcCompute coefCalc = insulin(Arrays.asList("ZN", "S", "PT"),
        Arrays.asList(0.333, 6.0, 20.0), 20000);
    Map<String, String> files = run(cuboid("MONTECARLO", coefCalc),
        gaussian(15.0, null, false), still(10), dir, 3);
    assertTrue(files.containsKey("outputMC.CSV"), files.keySet().toString());
  }

  /**
   * GOS mode deposits no more energy than the crystal absorbs: the
   * RADDOSE-3D-style dose, every absorbed photon's whole energy. A
   * relaxing vacancy deposited the binding energy less the Auger
   * electron's, and the two vacancies it left then deposited theirs again;
   * a fluorescing one deposited the photon's energy too. In a selenium
   * crystal at 13 keV that came to more than the photons brought.
   */
  @Test
  public void gosDepositsNoMoreThanIsAbsorbed(@TempDir final Path dir)
      throws IOException {
    CoefCalcCompute coefCalc = insulin(Arrays.asList("ZN", "S", "SE"),
        Arrays.asList(0.333, 6.0, 10.0), 10000);
    Map<String, String> files = run(cuboid("GOS", coefCalc),
        gaussian(13.0, null, false), still(10), dir, 11);
    String[] row = files.get("outputXFEL.CSV").split("\n")[1].split(",");
    double raddoseStyle = Double.parseDouble(row[1].trim());
    double gos = Double.parseDouble(row[8].trim());
    assertTrue(gos > 0 && gos < raddoseStyle,
        "GOS " + gos + " MGy against RADDOSE-3D-style " + raddoseStyle);
  }

  /**
   * The GOS model's inelastic mean free path is the outer subshells' and the
   * inner shells' together, and where no inner shell is open, the outer
   * subshells' alone: a length, where its inverse was taken. The chance an
   * inelastic collision is with an inner shell is then 0, not what it was.
   */
  @Test
  public void theGosInelasticMeanFreePathIsALength() {
    double both = MC.gosInelasticLambda(3.0, 1000.0);
    assertEquals(1 / (1 / 3.0 + 1 / 1000.0), both, 1e-15);
    assertEquals(3.0, MC.gosInelasticLambda(3.0, 0), 0.0);
    assertEquals(both / 1000.0, MC.innerShellChance(both, 1000.0), 0.0);
    assertEquals(0.0, MC.innerShellChance(3.0, 0), 0.0);
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
   * A 1 fs pulse through a 60 um crystal, about 20,000 time bins of 0.01 fs.
   * They were allocated at 15,300 whatever the pulse, so a deposit deeper
   * than about 46 um threw ArrayIndexOutOfBoundsException; now a deposit is
   * sorted by its bin as it is made, and none is kept.
   */
  @Test
  public void xfelTimeBinsFollowThePulse(@TempDir final Path dir)
      throws IOException {
    Map<String, String> files = run(cuboid("XFEL", insulin(100), 10, 10, 60,
        0.2), gaussian(12.1, 1.0, false), still(1), dir, 3);
    assertTrue(files.containsKey("outputXFEL.CSV"), files.keySet().toString());
  }

  /**
   * An XFEL pulse lasts femtoseconds, so the crystal stands still through it,
   * at the wedge's start: a wedge that rotates gives what a still at its
   * start does. XFEL turned the voxels the dose is tallied in, but not the
   * crystal, by whole radians of the wedge.
   */
  @Test
  public void anXfelPulseIsAStillAtTheWedgesStart(@TempDir final Path dir)
      throws IOException {
    Map<String, String> turned = run(cuboid("XFEL", insulin(2000)),
        gaussian(12.1, 1.0, false), new Wedge(2d, 30d, 120d, 10d, null, null,
            null, null, null, null, null, null),
        Files.createDirectory(dir.resolve("turned")), 3);
    Map<String, String> still = run(cuboid("XFEL", insulin(2000)),
        gaussian(12.1, 1.0, false), new Wedge(2d, 30d, 30d, 10d, null, null,
            null, null, null, null, null, null),
        Files.createDirectory(dir.resolve("still")), 3);
    assertEquals(still.get("outputXFEL.CSV"), turned.get("outputXFEL.CSV"));
  }

  /**
   * A pulse's length is as given: 0.2 fs and 0.4 fs are different pulses.
   * It was rounded to whole femtoseconds, so both had none, and gave the
   * same files.
   */
  @Test
  public void anXfelPulseIsAsLongAsGiven(@TempDir final Path dir)
      throws IOException {
    Map<String, String> shorter = run(cuboid("XFEL", insulin(20000)),
        gaussian(12.1, 1.0, false), still(0.2),
        Files.createDirectory(dir.resolve("shorter")), 3);
    Map<String, String> longer = run(cuboid("XFEL", insulin(20000)),
        gaussian(12.1, 1.0, false), still(0.4),
        Files.createDirectory(dir.resolve("longer")), 3);
    assertFalse(shorter.get("outputXFEL.CSV").equals(longer.get("outputXFEL.CSV")));
  }

  /**
   * XFEL runs in a surrounding as the Monte Carlo does: XFEL had a model of
   * its own, a box around the crystal's bounding dimensions that ignored
   * SurroundingThickness.
   */
  @Test
  public void anXfelPulseRunsInASurrounding(@TempDir final Path dir)
      throws IOException {
    Map<String, String> files = run(cuboid("XFEL", insulinIn(
        Arrays.asList("NA", "CL"), Arrays.asList(500.0, 500.0), 20000)),
        gaussian(12.1, 1.0, false), still(10), dir, 3);
    String[] row = files.get("outputXFEL.CSV").split("\\n")[1].split(",");
    assertTrue(Double.parseDouble(row[3].trim()) > 0, files.get("outputXFEL.CSV"));
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

  // ------------------------------------------------------------- geometry

  /** An MC run's geometry, for a 4 x 6 x 8 um cuboid at 2 voxels per um. */
  /**
   * An XFEL pulse's pink photons come in no order of energy: a photon's
   * place in the run is its time in the pulse, and in ascending order the
   * softest came first. The Monte Carlo keeps the ascending order. Both
   * have the sampler's energies.
   */
  @Test
  public void anXfelPulsesPinkPhotonsComeInNoOrderOfEnergy() throws Exception {
    double[][] energies = new double[2][];
    for (int k = 0; k < 2; k++) {
      boolean xfel = k == 1;
      Crystal cuboid = cuboid("MONTECARLO", insulin(100), 4, 6, 8, 2);
      CrystalPolyhedron c = (CrystalPolyhedron) cuboid;
      MC mc = new MC(c.vertices, c.indices,
          (double[][][][]) field(c, "crystCoord"), c.crystalPixPerUM,
          (int[]) field(c, "crystSizeVoxels"), c.crystOcc, 1, false, xfel,
          xfel, new double[3], false);
      setMcField(mc, "meanEnergy", 12.1);
      setMcField(mc, "energyFWHM", 0.5);
      setMcField(mc, "NUM_PHOTONS", 100000L);
      RandomSource.setSeed(5);
      energies[k] = (double[]) call(mc, "photonEnergies", new Class<?>[0]);
    }
    double[] sorted = energies[1].clone();
    Arrays.sort(sorted);
    assertArrayEquals(energies[0], sorted, 0.0);
    for (int i = 1; i < energies[0].length; i++) {
      assertTrue(energies[0][i] >= energies[0][i - 1]);
    }
    assertEquals(0, rankCorrelationWithIndex(energies[1]), 0.01);
  }

  /** Spearman's correlation between values and their places. */
  private static double rankCorrelationWithIndex(final double[] values) {
    int n = values.length;
    Integer[] order = new Integer[n];
    for (int i = 0; i < n; i++) {
      order[i] = i;
    }
    Arrays.sort(order, (a, b) -> Double.compare(values[a], values[b]));
    double sum = 0;
    for (int rank = 0; rank < n; rank++) {
      double d = rank - order[rank];
      sum += d * d;
    }
    return 1 - 6 * sum / ((double) n * ((double) n * n - 1));
  }

  private static void setMcField(final MC mc, final String name,
      final Object value) throws Exception {
    Field f = MC.class.getDeclaredField(name);
    f.setAccessible(true);
    f.set(mc, value);
  }

  private static MC cuboidMC() throws Exception {
    Crystal cuboid = cuboid("MONTECARLO", insulin(100), 4, 6, 8, 2);
    CrystalPolyhedron c = (CrystalPolyhedron) cuboid;
    return new MC(c.vertices, c.indices,
        (double[][][][]) field(c, "crystCoord"), c.crystalPixPerUM,
        (int[]) field(c, "crystSizeVoxels"), c.crystOcc, 1, false, false,
        false, new double[3], false);
  }

  private static Object call(final MC mc, final String name,
      final Class<?>[] types, final Object... args) throws Exception {
    java.lang.reflect.Method m = MC.class.getDeclaredMethod(name, types);
    m.setAccessible(true);
    return m.invoke(mc, args);
  }

  private static final Class<?>[] SIX_DOUBLES = {double.class, double.class,
      double.class, double.class, double.class, double.class};

  /**
   * From the centre of a 4 x 6 x 8 um crystal rotated through 90 degrees,
   * along x: 4 um to its surface, half its 8 um z, now along x. The
   * unrotated crystal's planes gave 2 um.
   */
  @Test
  public void escapeDistancesAreToTheRotatedCrystal() throws Exception {
    MC mc = cuboidMC();
    call(mc, "setUpRotatedVertices", new Class<?>[] {double.class, Wedge.class},
        Math.PI / 2, still(10));
    double distance = (Double) call(mc, "getIntersectionDistance", SIX_DOUBLES,
        0.0, 0.0, 0.0, 1.0, 0.0, 0.0);
    assertEquals(4.0, distance, 1e-9);
  }

  /**
   * From outside, past a corner: the nearest plane ahead, y = 3, is crossed
   * beyond the crystal, at x = -3; the face the ray enters by is x = -2.
   */
  @Test
  public void entryDistancesAreToTheFaceCrossed() throws Exception {
    MC mc = cuboidMC();
    call(mc, "setUpRotatedVertices", new Class<?>[] {double.class, Wedge.class},
        0.0, still(10));
    double norm = Math.sqrt(1.25);
    double distance = (Double) call(mc, "getIntersectionDistance", SIX_DOUBLES,
        -5000.0, 4000.0, 0.0, 1 / norm, -0.5 / norm, 0.0);
    assertEquals(3 * norm, distance, 1e-9);
  }

  /**
   * A point just inside a corner of a crystal offset by 0.5 um and rotated
   * through 90 degrees is in the corner voxel. Translating back before
   * rotating back put it a voxel past the grid, and the run failed with an
   * ArrayIndexOutOfBoundsException.
   */
  @Test
  public void anOffsetRotatedCrystalsPointsFindTheirVoxels() throws Exception {
    MC mc = cuboidMC();
    Wedge offset = new Wedge(2d, 0d, 90d, 10d, 0.5, 0d, 0d, null, null, null,
        null, null);
    double angle = Math.PI / 2;
    // The corner (1.99, 2.99, 3.99) um, offset, then rotated: x' = x cos a
    // + z sin a, z' = -x sin a + z cos a.
    double[] corner = {1.99 + 0.5, 2.99, 3.99};
    double x = corner[0] * Math.cos(angle) + corner[2] * Math.sin(angle);
    double z = -corner[0] * Math.sin(angle) + corner[2] * Math.cos(angle);
    int[] voxel = (int[]) call(mc, "convertToPixelCoordinates",
        new Class<?>[] {double.class, double.class, double.class, double.class,
            Wedge.class}, x * 1000, corner[1] * 1000, z * 1000, angle, offset);
    assertArrayEquals(new int[] {8, 12, 16}, voxel);
  }

  /** And a run of one no longer fails. */
  @Test
  public void anOffsetRotatedCrystalRuns(@TempDir final Path dir)
      throws IOException {
    Map<String, String> files = run(cuboid("MONTECARLO", insulin(20000), 4, 6,
        8, 2), gaussian(12.1, null, false), new Wedge(2d, 0d, 90d, 10d, 0.5, 0d,
        0d, null, null, null, null, null), dir, 3);
    assertTrue(files.containsKey("outputMC.CSV"));
  }

  // ---------------------------------------------------------------- beams

  private static Beam circular(final String type, final double fwhm,
      final double aperture) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_ENERGY, 12.1);
    p.put(Beam.BEAM_FLUX, 2e12);
    p.put(Beam.BEAM_COLL_H, aperture);
    p.put(Beam.BEAM_COLL_V, aperture);
    p.put(Beam.BEAM_CIRCULAR, "TRUE");
    if ("Gaussian".equals(type)) {
      p.put(Beam.BEAM_FWHM_X, fwhm);
      p.put(Beam.BEAM_FWHM_Y, fwhm);
      return new BeamGaussian(p);
    }
    return new BeamTophat(p);
  }

  private static double[][] positions(final Beam beam, final int n)
      throws Exception {
    MC mc = cuboidMC();
    RandomSource.setSeed(11);
    double[][] xy = new double[n][];
    for (int i = 0; i < n; i++) {
      xy[i] = (double[]) call(mc, "getPhotonBeamXYPos",
          new Class<?>[] {Beam.class, Wedge.class}, beam, still(10));
    }
    return xy;
  }

  /** A 5 um circle's area is pi 2.5^2 um^2, not pi 5^2. */
  @Test
  public void aCircularExposedAreaIsTheEllipses() throws Exception {
    assertEquals(Math.PI * 2500 * 2500,
        cuboidMC().getExposedArea(circular("Tophat", 0, 5)), 1e-3);
  }

  /**
   * A circular top-hat fills its circle: half its photons have |y| > |x|.
   * It filled a bow tie, |y| <= |x|.
   */
  @Test
  public void aCircularTophatFillsItsCircle() throws Exception {
    double[][] xy = positions(circular("Tophat", 0, 5), 20000);
    int steep = 0;
    for (double[] p : xy) {
      assertTrue(MC.insideEllipse(p, 5, 5), Arrays.toString(p));
      if (Math.abs(p[1]) > Math.abs(p[0])) {
        steep++;
      }
    }
    assertEquals(0.5, steep / 20000.0, 0.02);
  }

  /**
   * A circular Gaussian's radii follow the two-dimensional normal: 1 -
   * exp(-1/2) = 39.3% within one sigma, through an aperture far wider than
   * the beam. Its radius was drawn from a one-dimensional normal, which put
   * 68% there.
   */
  @Test
  public void aCircularGaussiansRadiiAreTwoDimensional() throws Exception {
    double sigma = 20 / (2 * Math.sqrt(2 * Math.log(2)));
    double[][] xy = positions(circular("Gaussian", 20, 200), 20000);
    int within = 0;
    for (double[] p : xy) {
      if (Math.hypot(p[0], p[1]) / 1000 < sigma) {
        within++;
      }
    }
    assertEquals(1 - Math.exp(-0.5), within / 20000.0, 0.015);
  }

  // --------------------------------------------------------- elastic angles

  /**
   * Scattering the same per unit solid angle in every direction: a quarter
   * of deflections are under 60 degrees, where (1 - cos theta) / 2 = 1/4.
   * Taking the table's values as the chances themselves put a quarter under
   * 45.
   */
  @Test
  public void elasticAnglesAreWeightedBySinTheta() {
    double[] degrees = new double[181];
    double[] flat = new double[181];
    for (int k = 0; k <= 180; k++) {
      degrees[k] = k;
      flat[k] = 1;
    }
    double quarter = Math.toDegrees(MC.deflectionAngle(flat, degrees, 0.25));
    assertEquals(60, quarter, 1);
  }

  /**
   * Above 20 keV, column 146 is 1.1 degrees, as the table's header says.
   * The angle was worked out from the index, which gave 1.0 from there on.
   */
  @Test
  public void elasticAnglesAreTheTablesOwn() throws Exception {
    java.io.BufferedReader header = new java.io.BufferedReader(
        new java.io.FileReader("constants/above_20000/6.csv"));
    String[] columns = header.readLine().split(",");
    header.close();
    double[] degrees = new double[columns.length - 1];
    for (int k = 1; k < columns.length; k++) {
      degrees[k - 1] = Double.parseDouble(columns[k]);
    }
    double[] one = new double[degrees.length];
    one[146] = 1;
    assertEquals(1.1, Math.toDegrees(MC.deflectionAngle(one, degrees, 0.5)),
        1e-12);
  }

  // ------------------------------------------------------------ directions

  /** Uniform over the sphere: cos theta uniform, so its square averages 1/3.
   * The polar angle was uniform on [0, 2 pi): 1/2. */
  @Test
  public void isotropicDirectionsAreUniformOverTheSphere() {
    RandomSource.setSeed(3);
    int n = 200000;
    double[] mean = new double[3];
    double[] meanSquare = new double[3];
    for (int i = 0; i < n; i++) {
      double[] d = MC.isotropicDirection();
      assertEquals(1, d[0] * d[0] + d[1] * d[1] + d[2] * d[2], 1e-12);
      for (int k = 0; k < 3; k++) {
        mean[k] += d[k] / n;
        meanSquare[k] += d[k] * d[k] / n;
      }
    }
    for (int k = 0; k < 3; k++) {
      assertEquals(0, mean[k], 0.005);
      assertEquals(1.0 / 3, meanSquare[k], 0.005);
    }
  }

  /** At the angle to the axis, uniformly around it: the other components'
   * squares average half of what the axis leaves. One was a uniform
   * fraction of it, and averaged a third. */
  @Test
  public void polarisedDirectionsAreUniformAroundTheAxis() {
    RandomSource.setSeed(5);
    int n = 200000;
    double cos = 0.6;
    for (int axis = 0; axis < 2; axis++) {
      double[] meanSquare = new double[3];
      for (int i = 0; i < n; i++) {
        double[] d = MC.aboutAxis(cos, axis);
        assertEquals(cos, d[axis], 0);
        assertEquals(1, d[0] * d[0] + d[1] * d[1] + d[2] * d[2], 1e-12);
        for (int k = 0; k < 3; k++) {
          meanSquare[k] += d[k] * d[k] / n;
        }
      }
      for (int k = 0; k < 3; k++) {
        if (k != axis) {
          assertEquals((1 - cos * cos) / 2, meanSquare[k], 0.003);
        }
      }
    }
  }

  /** The K-shell photoelectron's angle to the polarisation, from the dipole
   * distribution 1 + beta P2(cos) with beta = 2: cos squared per solid
   * angle, so the cosine's square averages 3/5. Without the sin of the
   * angle, cos squared per unit angle: 3/4. */
  @Test
  public void photoelectronAnglesFollowTheDipoleDistribution()
      throws Exception {
    MC mc = cuboidMC();
    call(mc, "populateAngularEmissionProbs", new Class<?>[0]);
    double[] table = (double[]) mcField(mc, "angularEmissionProbs");
    assertEquals(1, table[table.length - 1], 1e-12);
    RandomSource.setSeed(7);
    int n = 200000;
    double meanSquare = 0;
    for (int i = 0; i < n; i++) {
      double cos = (Double) call(mc, "getCosAngleToX", new Class<?>[0]);
      meanSquare += cos * cos / n;
    }
    assertEquals(0.6, meanSquare, 0.005);
  }

  /**
   * Every K-shell photoelectron leaves along the dipole distribution about
   * the polarisation, so its component along it squares to 3/5 on average;
   * three in four did, and the rest uniformly, which gives 8/15. Any other
   * shell's leaves uniformly: a third along each axis.
   */
  @Test
  public void everyKShellPhotoelectronFollowsThePolarisation()
      throws Exception {
    MC mc = cuboidMC();
    call(mc, "populateAngularEmissionProbs", new Class<?>[0]);
    RandomSource.setSeed(13);
    int n = 200000;
    double[][] meanSquare = new double[2][3];
    for (int shell = 0; shell < 2; shell++) {
      for (int i = 0; i < n; i++) {
        double[] d = (double[]) call(mc, "photoelectronDirection",
            new Class<?>[] {int.class}, shell);
        for (int k = 0; k < 3; k++) {
          meanSquare[shell][k] += d[k] * d[k] / n;
        }
      }
    }
    double along = Math.max(meanSquare[0][0], meanSquare[0][1]);
    assertEquals(0.6, along, 0.005);
    for (int k = 0; k < 3; k++) {
      assertEquals(1.0 / 3, meanSquare[1][k], 0.005);
    }
  }

  /** Klein and Nishina's cross-section: Thomson's 1 + cos^2 at low energy,
   * where cos^2 averages 2/5, and forward at high, where cos averages what
   * the cross-section gives. Compton angles were uniform from 0 to pi. */
  @Test
  public void comptonAnglesFollowKleinAndNishina() {
    RandomSource.setSeed(9);
    int n = 200000;
    double meanSquare = 0;
    for (int i = 0; i < n; i++) {
      double mu = MC.kleinNishinaCosine(1e-9);
      meanSquare += mu * mu / n;
    }
    assertEquals(0.4, meanSquare, 0.004);

    double k = 1;
    double weighted = 0;
    double total = 0;
    int steps = 20000;
    for (int i = 0; i <= steps; i++) {
      double mu = -1 + 2.0 * i / steps;
      double p = 1 / (1 + k * (1 - mu));
      double f = p * p * (p + 1 / p - (1 - mu * mu));
      double w = (i == 0 || i == steps) ? 0.5 : 1;
      weighted += w * mu * f;
      total += w * f;
    }
    double mean = 0;
    for (int i = 0; i < n; i++) {
      mean += MC.kleinNishinaCosine(k) / n;
    }
    assertEquals(weighted / total, mean, 0.005);
  }

  /** A deflection turns a direction through the angle, around it. The
   * formula used divided by y -- so a direction in the xz plane became NaN
   * -- and missed the angle: from directions uniform over the sphere, 30
   * degrees came out anywhere from 0 to 180. */
  @Test
  public void deflectionsTurnThroughTheirAngle() throws Exception {
    MC mc = cuboidMC();
    Class<?>[] five = {double.class, double.class, double.class,
        double.class, double.class};
    double[][] directions = {{0.36, 0.48, 0.8}, {0.6, 0, 0.8}, {1, 0, 0},
        {0, 0, 1}, {0, 0, -1}, {0, 0.6, -0.8}, {-0.48, -0.36, 0.8}};
    double theta = Math.toRadians(30);
    for (double[] d : directions) {
      for (double phi = 0; phi < 2 * Math.PI; phi += 0.7) {
        double[] v = (double[]) call(mc, "getNewDirectionVector", five,
            d[0], d[1], d[2], theta, phi);
        double[] w = (double[]) call(mc, "getNewDirectionVector", five,
            d[0], d[1], d[2], theta, phi + Math.PI);
        assertEquals(Math.cos(theta), v[0] * d[0] + v[1] * d[1] + v[2] * d[2],
            1e-9, Arrays.toString(d));
        for (int k = 0; k < 3; k++) {
          // Opposite sides of the cone: their sum is along the direction.
          assertEquals(2 * Math.cos(theta) * d[k], v[k] + w[k], 1e-9,
              Arrays.toString(d));
        }
      }
    }
  }

  // ----------------------------------------------------------- bookkeeping

  private static final Class<?>[] AUGER = {CoefCalc.class, double.class,
      double.class, Element.class, double.class, double.class, double.class,
      boolean.class, Beam.class, double.class, Wedge.class};

  /** An MC with its relaxation tables read. */
  private static MC relaxingMC() throws Exception {
    MC mc = cuboidMC();
    call(mc, "populateAugerLinewidths", new Class<?>[0]);
    call(mc, "populateFluorescenceLinewidths", new Class<?>[0]);
    return mc;
  }

  /**
   * Phosphorus has relaxation tables, but was left out of the list of
   * elements that use them, so in simple mode its K holes deposited their
   * whole binding energy: none ever fluoresced. About 6% do, and their
   * photons take most of it away.
   */
  @Test
  public void phosphorusRelaxes() throws Exception {
    MC mc = relaxingMC();
    Wedge wedge = still(30);
    call(mc, "setUpRotatedVertices", new Class<?>[] {double.class, Wedge.class},
        0.0, wedge);
    Element phosphorus = ElementDatabase.getInstance().getElement("P");
    RandomSource.setSeed(13);
    int n = 4000;
    for (int i = 0; i < n; i++) {
      call(mc, "produceAugerElectron", AUGER, null, 0.0, 0.0, phosphorus,
          0.0, 0.0, 0.0, false, null, 0.0, wedge);
    }
    double perHole = (Double) mcField(mc, "dose") / n;
    double kEdge = phosphorus.getKEdge();
    assertTrue(perHole < 0.97 * kEdge, perHole + " of " + kEdge);
    assertTrue(perHole > 0.9 * kEdge, perHole + " of " + kEdge);
  }

  /**
   * A fluorescence transition is drawn from the fluorescence table. MC
   * drew it from the Auger table, and read the fluorescence energy at the
   * Auger transition's index.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void fluorescenceTransitionsAreTheFluorescenceTables()
      throws Exception {
    MC mc = relaxingMC();
    double[] cumulative = ((Map<Integer, Map<Integer, double[]>>) mcField(mc,
        "flCumulativeTransitionProbabilities")).get(16).get(0);
    Class<?>[] types = {int.class, int.class, boolean.class};
    RandomSource.setSeed(17);
    int n = 20000;
    double[] share = new double[cumulative.length];
    for (int i = 0; i < n; i++) {
      int index = (Integer) call(mc, "getTransitionIndex", types, 16, 0, false);
      share[index] += 1.0 / n;
    }
    // The table is padded with zeros after its last transition.
    double last = 0;
    for (int k = 0; k < cumulative.length; k++) {
      double chance = Math.max(0, cumulative[k] - last);
      assertEquals(chance, share[k], 0.01, "transition " + k);
      last = Math.max(last, cumulative[k]);
    }
  }

  /** A still at its start angle; a rotation from its start to its end. */
  @Test
  public void wedgesTurnFromTheirStartToTheirEnd() {
    Wedge still = new Wedge(2d, 30d, 30d, 10d, null, null, null, null, null,
        null, null, null);
    assertEquals(30, Math.toDegrees(MC.photonAngle(still, 0.5)), 1e-9);
    Wedge down = new Wedge(2d, 90d, 0d, 10d, null, null, null, null, null,
        null, null, null);
    assertEquals(90, Math.toDegrees(MC.photonAngle(down, 0)), 1e-9);
    assertEquals(67.5, Math.toDegrees(MC.photonAngle(down, 0.25)), 1e-9);
    Wedge up = new Wedge(2d, 10d, 50d, 10d, null, null, null, null, null,
        null, null, null);
    assertEquals(20, Math.toDegrees(MC.photonAngle(up, 0.25)), 1e-9);
  }

  /** Elastic, photoelectric and Compton 2 : 1 : 1: half the interactions
   * are elastic. It was 2 / (1 + 1), every one. */
  @Test
  public void theElasticChanceIsOfEveryInteraction() {
    assertEquals(0.5, MC.elasticChance(1, 1, 2), 0);
  }

  /**
   * A crystal offset so its front face is at z = 0 is crossed at z = 0.
   * That was the value for a miss, so every photon missed it.
   */
  @Test
  public void aFaceAtZeroIsNotAMiss() throws Exception {
    MC mc = cuboidMC();
    Wedge offset = new Wedge(2d, 0d, 0d, 10d, 0d, 0d, 4d, null, null, null,
        null, null);
    call(mc, "setUpRotatedVertices", new Class<?>[] {double.class, Wedge.class},
        0.0, offset);
    Class<?>[] types = {double.class, Wedge.class, double[].class,
        boolean.class};
    double front = (Double) call(mc, "getStartingZ", types, 0.0, offset,
        new double[] {0, 0, 0}, true);
    assertEquals(0, front, 1e-9);
    double back = (Double) call(mc, "getStartingZ", types, 0.0, offset,
        new double[] {0, 0, 0}, false);
    assertEquals(8000, back, 1e-6);
    double miss = (Double) call(mc, "getStartingZ", types, 0.0, offset,
        new double[] {5, 0, 0}, true);
    assertTrue(Double.isNaN(miss));
  }

  // ----------------------------------------------------------- surrounding

  /** Insulin in a surrounding with the given solutes, mM (CalcSurrounding). */
  private static CoefCalcCompute insulinIn(final List<String> solutes,
      final List<Double> concentrations, final long photons) {
    return new CoefCalcFromParams(78.02, 78.02, 78.02, 90.0, 90.0, 90.0, 24,
        51, 0, 0, Arrays.asList("ZN", "S"), Arrays.asList(0.333, 6.0),
        Arrays.asList("P"), Arrays.asList(425.0), solutes, concentrations,
        0.64, null, "TRUE", 0, NONE, NO_AMOUNTS, 0, photons);
  }

  /** An MC run's geometry for a crystal, with a surrounding of the thickness given, um. */
  private static MC mcFor(final CrystalPolyhedron c, final double[][] vertices,
      final double[] thickness) throws Exception {
    return new MC(vertices, c.indices,
        (double[][][][]) field(c, "crystCoord"), c.crystalPixPerUM,
        (int[]) field(c, "crystSizeVoxels"), c.crystOcc, 1, false, false,
        false, thickness, false);
  }

  /**
   * A cylinder 5 um across in a surrounding 1 um thick: its side is 1 um
   * thick all round, as are its ends. Each vertex was pushed out 1 um along
   * each axis, which made the side 1.14 to 1.41 um thick.
   */
  @Test
  public void aCylindersSurroundingIsAsThickAllRound() throws Exception {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Crystal.CRYSTAL_DIM_X, 5d);
    p.put(Crystal.CRYSTAL_DIM_Y, 5d);
    p.put(Crystal.CRYSTAL_DIM_Z, 1d);
    p.put(Crystal.CRYSTAL_RESOLUTION, 1d);
    p.put(Crystal.CRYSTAL_ANGLE_P, 0d);
    p.put(Crystal.CRYSTAL_ANGLE_L, 0d);
    CrystalCylinder cylinder = new CrystalCylinder(p);
    MC mc = mcFor(cylinder, cylinder.vertices, new double[] {1, 1, 1});
    call(mc, "setupSurroundingVolume", new Class<?>[0]);
    for (int i = 0; i < cylinder.vertices.length; i++) {
      double[] v = cylinder.vertices[i];
      double[] w = mc.verticesSurrounding[i];
      assertEquals(Math.hypot(v[0], v[2]) * 3.5 / 2.5, Math.hypot(w[0], w[2]),
          1e-9, "vertex " + i);
      assertEquals(Math.signum(v[1]) * 3.5, w[1], 1e-9, "vertex " + i);
    }
  }

  /**
   * A crystal not centred on the origin is enclosed: a 4 um cube whose
   * corners all have x > 0, in a surrounding 1 um thick, has it from x = 7
   * to 13. Pushed away from the origin, every vertex moved to larger x, and
   * the surrounding began at 9, inside the crystal.
   */
  @Test
  public void aCrystalOffTheOriginIsEnclosed() throws Exception {
    CrystalPolyhedron cube = (CrystalPolyhedron) cuboid("MONTECARLO",
        insulin(100), 4, 4, 4, 1);
    double[][] moved = new double[cube.vertices.length][];
    for (int i = 0; i < moved.length; i++) {
      moved[i] = new double[] {cube.vertices[i][0] + 10, cube.vertices[i][1],
          cube.vertices[i][2]};
    }
    MC mc = mcFor(cube, moved, new double[] {1, 1, 1});
    call(mc, "setupSurroundingVolume", new Class<?>[0]);
    double[] x = mc.minMaxVertices(0, mc.verticesSurrounding);
    assertEquals(7, x[0], 1e-9);
    assertEquals(13, x[1], 1e-9);
  }

  /**
   * The thickness is capped once, by the reach of the most energetic
   * photon's photoelectron; 0 takes the cap. It was capped photon by photon,
   * by overwriting it, so a pink beam, whose photons come in order of
   * energy, left it at the first, least energetic photon's reach.
   */
  @Test
  public void theSurroundingIsCappedByTheMostEnergeticPhoton() throws Exception {
    CoefCalcCompute salt = insulinIn(Arrays.asList("NA", "CL"),
        Arrays.asList(500.0, 500.0), 100);
    MC mc = mcFor((CrystalPolyhedron) cuboidCrystal(), cuboidCrystal().vertices,
        new double[] {0, 0.1, 100});
    mc.capSurroundingThickness(salt, new double[] {8, 10, 12.1});
    double reach = 12.1 / salt.getStoppingPower(12.1, true);
    assertTrue(reach > 100 && reach < 100000, "reach " + reach);
    Field f = MC.class.getDeclaredField("surroundingThickness");
    f.setAccessible(true);
    assertArrayEquals(new double[] {reach, 100, reach}, (double[]) f.get(mc),
        1e-9);
  }

  private static CrystalPolyhedron cuboidCrystal() {
    return (CrystalPolyhedron) cuboid("MONTECARLO", insulin(100), 4, 4, 4, 1);
  }

  /**
   * As many photons are absorbed in the surrounding behind the crystal as
   * in front: a 2 um slab barely attenuates the beam. One that crossed the
   * crystal was given its chance behind it only if the distance drawn for
   * the crystal happened to end in the surrounding, so almost none were.
   */
  @Test
  public void photonsReachTheSurroundingBehindTheCrystal(@TempDir final Path dir)
      throws Exception {
    CoefCalcCompute iodide = insulinIn(Arrays.asList("I"),
        Arrays.asList(2000.0), 200000);
    CrystalPolyhedron slab = (CrystalPolyhedron) cuboid("MONTECARLO", iodide,
        4, 4, 2, 1);
    MC mc = mcFor(slab, slab.vertices, new double[] {0, 0, 0});
    mc.setOutputDirectory(dir.toFile());
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Beam.BEAM_ENERGY, 12.1);
    p.put(Beam.BEAM_FLUX, 2e12);
    p.put(Beam.BEAM_COLL_H, 4.0);
    p.put(Beam.BEAM_COLL_V, 4.0);
    RandomSource.setSeed(19);
    mc.CalculateXFEL(new BeamTophat(p), still(10), iodide);
    long front = mc.photonsInSurroundingInFront;
    long behind = mc.photonsInSurroundingBehind;
    assertTrue(front > 500, "front " + front);
    assertEquals(1, (double) behind / front, 0.15, behind + " behind, " + front + " in front");
  }

  private static Object mcField(final MC mc, final String name)
      throws Exception {
    Field f = MC.class.getDeclaredField(name);
    f.setAccessible(true);
    return f.get(mc);
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
