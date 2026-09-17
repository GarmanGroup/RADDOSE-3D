package se.raddo.raddose3D;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Covers three small pieces of plumbing that had no tests: the file and
 * fan-out {@link Writer} implementations, {@link OutputFactory}'s name
 * resolution, and {@link ElementEM}'s interpolation.
 */
public class WriterAndFactoryTest {

  // ----------------------------------------------------------- WriterFile

  @Test
  public void writerFileWritesWhatItIsGiven(@TempDir File dir) throws Exception {
    File target = new File(dir, "out.txt");
    Writer w = new WriterFile(target.getPath());

    w.write("hello");
    w.write(new StringBuffer(" world"));
    w.close();

    assertEquals("hello world",
        new String(Files.readAllBytes(target.toPath()),
                   Charset.forName("UTF-8")));
  }

  @Test
  public void writerFileFlushesWithoutClosing(@TempDir File dir)
      throws Exception {
    File target = new File(dir, "flush.txt");
    Writer w = new WriterFile(target.getPath());

    w.write("before flush");
    w.flush();
    assertTrue(target.length() > 0, "flush should have reached the file");

    w.write(" and after");
    w.close();
    assertTrue(new String(Files.readAllBytes(target.toPath()),
        Charset.forName("UTF-8")).contains("and after"),
        "writing after a flush should still work");
  }

  @Test
  public void writerFileRejectsAnUnwritablePath() {
    assertThrows(Exception.class,
        () -> new WriterFile("/this/path/does/not/exist/out.txt"),
        "an unwritable path should fail loudly");
  }

  // ------------------------------------------------------- WriterMultiple

  @Test
  public void writerMultipleFansOutToEveryWriter() {
    WriterString a = new WriterString();
    WriterString b = new WriterString();
    List<Writer> both = new ArrayList<Writer>();
    both.add(a);
    both.add(b);

    Writer w = new WriterMultiple(both);
    w.write("shared");
    w.write(new StringBuffer(" text"));

    assertEquals("shared text", a.getDataString());
    assertEquals("shared text", b.getDataString(),
        "both writers should receive identical content");
  }

  @Test
  public void writerMultipleWithNoTargetsIsHarmless() {
    Writer w = new WriterMultiple(new ArrayList<Writer>());
    w.write("goes nowhere");
    w.write(new StringBuffer("also nowhere"));
  }

  // -------------------------------------------------------- OutputFactory

  /** Every documented output name must resolve to the right class. */
  @ParameterizedTest
  @CsvSource({
      "finaldosestatecsv,       OutputFinalDoseStateCSV",
      "FinalDoseStateCSV,       OutputFinalDoseStateCSV",
      "FINALDOSESTATECSV,       OutputFinalDoseStateCSV",
      "finaldosestater,         OutputFinalDoseStateR",
      "fluenceperdosehistcsv,   OutputFluencePerDoseHistCSV",
      "summarycsv,              OutputSummaryCSV",
      "summarytext,             OutputSummaryText",
  })
  public void outputFactoryResolvesNamesCaseInsensitively(String name,
      String expectedClass) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Output.OUTPUT_WRITER, new WriterString());
    p.put(Output.OUTPUT_HISTBINS, 10);
    p.put(Output.OUTPUT_HISTMIN, 0.0);
    p.put(Output.OUTPUT_HISTMAX, 10.0);

    Output out = new OutputFactory().createOutput(name, p);
    assertNotNull(out, name + " resolved to null");
    assertEquals("se.raddo.raddose3D." + expectedClass,
        out.getClass().getName(), "wrong class for output name '" + name + "'");
  }

  @ParameterizedTest
  @ValueSource(strings = {"nosuchoutput", "", "OutputSummaryCSV "})
  public void outputFactoryRejectsUnknownNames(String name) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Output.OUTPUT_WRITER, new WriterString());

    assertThrows(RuntimeException.class,
        () -> new OutputFactory().createOutput(name, p),
        "'" + name + "' should not resolve to an output");
  }

  // ------------------------------------------------------------ ElementEM

  /** A two-point table, so interpolation is easy to reason about. */
  private static ElementEM twoPointElement() {
    TreeMap<Double, Double> data = new TreeMap<Double, Double>();
    data.put(1.0, 10.0);
    data.put(3.0, 30.0);
    return new ElementEM("TESTIUM", 99, 123.4, data);
  }

  @Test
  public void elementEmInterpolatesLinearlyBetweenTabulatedPoints() {
    ElementEM e = twoPointElement();

    assertEquals(10.0, e.getElasticCoefficient(1.0), 1e-12, "at the lower key");
    assertEquals(30.0, e.getElasticCoefficient(3.0), 1e-12, "at the upper key");
    assertEquals(20.0, e.getElasticCoefficient(2.0), 1e-12, "midway");
    assertEquals(15.0, e.getElasticCoefficient(1.5), 1e-12, "quarter way");
  }

  /**
   * Outside 0.05 to 300 keV the method returns 0 rather than extrapolating.
   */
  @ParameterizedTest
  @ValueSource(doubles = {0.0, 0.04, 300.1, 1000.0, -5.0})
  public void elementEmReturnsZeroOutsideItsEnergyRange(double energy) {
    assertEquals(0.0, twoPointElement().getElasticCoefficient(energy), 0.0,
        "energy " + energy + " keV is outside [0.05, 300] and should give 0");
  }

  @Test
  public void elementEmKeepsItsIdentity() {
    ElementEM e = twoPointElement();
    assertEquals("TESTIUM", e.getElementName());
    assertEquals(99, e.getAtomicNumber());
  }

  /** The constructor must copy its map, not alias the caller's. */
  @Test
  public void elementEmCopiesTheTableItIsGiven() {
    TreeMap<Double, Double> data = new TreeMap<Double, Double>();
    data.put(1.0, 10.0);
    data.put(3.0, 30.0);
    ElementEM e = new ElementEM("TESTIUM", 99, 123.4, data);

    data.put(2.0, 999.0);   // would break interpolation if aliased

    assertEquals(20.0, e.getElasticCoefficient(2.0), 1e-12,
        "mutating the caller's map changed the element's data");
  }
}
