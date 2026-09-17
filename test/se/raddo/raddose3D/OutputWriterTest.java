package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the {@code Output*} family by injecting a {@link WriterString} and
 * inspecting what they emit.
 * <p>
 * Twelve of these classes had no coverage at all, despite being the only thing
 * standing between a correct calculation and a correct results file. They all
 * implement the same {@code publishCrystal / publishBeam / publishWedge /
 * close} contract and write through a {@link Writer}, so one fixture covers
 * the family.
 */
public class OutputWriterTest {

  /**
   * A crystal reporting an ExposureSummary that has been through the start of
   * an exposure, which is what initialises its accumulators.
   * <p>
   * A freshly constructed ExposureSummary leaves them null, so calling
   * publishWedge on one throws NullPointerException. The Output classes assume
   * the full observer lifecycle has run; this fixture reproduces the minimum
   * of it.
   */
  private static class SummaryCrystal extends CrystalDummy {
    private final ExposureSummary summary = new ExposureSummary();

    SummaryCrystal() {
      // The Output classes need a summary that has been all the way through
      // an exposure: exposureStart initialises the accumulators, and only
      // exposureComplete populates the averages the outputs read. That in turn
      // needs at least one completed image -- see
      // exposureCompleteThrowsIfNoImagesRan.
      summary.exposureStart(1, wedge(), new int[] {2, 2, 2});
      summary.summaryObservation(0, 0, 0, 1.0, 1.0);
      summary.imageComplete(0, 0.0, 0.0, 1.0);
      summary.exposureComplete();
    }

    @Override
    public synchronized ExposureSummary getExposureSummary() {
      return summary;
    }
  }

  private static Map<Object, Object> props(final Writer w) {
    Map<Object, Object> p = new HashMap<Object, Object>();
    p.put(Output.OUTPUT_WRITER, w);
    return p;
  }

  private static Wedge wedge() {
    return new Wedge(0d, 90d, 0d, 10d, null, null, null, null,
                     null, null, null, null);
  }

  // ---------------------------------------------------------------- headers

  /** Every CSV output announces its columns when constructed. */
  @Test
  public void summaryCsvWritesItsHeaderOnConstruction() {
    WriterString w = new WriterString();
    new OutputSummaryCSV(props(w));

    String header = w.getDataString();
    assertTrue(header.startsWith("Wedge Number"),
        "header should start with the wedge column: " + header);
    for (String column : new String[] {"Average DWD", "Last DWD", "Max Dose",
                                       "Dose Contrast", "Used Volume"}) {
      assertTrue(header.contains(column),
          "header is missing the '" + column + "' column: " + header);
    }
  }

  /**
   * The header must declare exactly as many columns as publishWedge emits
   * fields. A mismatch silently shifts every value into the wrong column --
   * the kind of defect that makes a results file quietly wrong rather than
   * obviously broken.
   */
  @Test
  public void summaryCsvHeaderColumnCountMatchesTheRowItWrites() {
    WriterString w = new WriterString();
    OutputSummaryCSV out = new OutputSummaryCSV(props(w));
    out.publishCrystal(new SummaryCrystal());
    out.publishWedge(wedge());

    String[] lines = w.getDataString().split("\n");
    assertEquals(2, lines.length, "expected a header and one data row");

    int headerColumns = lines[0].split(",").length;
    int rowColumns = lines[1].split(",").length;
    assertEquals(headerColumns, rowColumns,
        "header declares " + headerColumns + " columns but the row has "
            + rowColumns + "; values would be attributed to the wrong column");
  }

  @Test
  public void rdeCsvWritesAHeader() {
    WriterString w = new WriterString();
    new OutputRDECSV(props(w));
    assertFalse(w.getDataString().isEmpty(), "RDE output wrote no header");
  }

  @Test
  public void dwdsWritesAHeader() {
    WriterString w = new WriterString();
    new OutputDWDs(props(w));
    assertFalse(w.getDataString().isEmpty(), "DWDs output wrote no header");
  }

  // ------------------------------------------------------------ wedge rows

  /** Each wedge adds exactly one row, numbered from 1. */
  @Test
  public void summaryCsvNumbersWedgesSequentially() {
    WriterString w = new WriterString();
    OutputSummaryCSV out = new OutputSummaryCSV(props(w));
    out.publishCrystal(new SummaryCrystal());

    for (int i = 0; i < 3; i++) {
      out.publishWedge(wedge());
    }

    String[] lines = w.getDataString().split("\n");
    assertEquals(4, lines.length, "expected a header plus three rows");
    for (int i = 1; i <= 3; i++) {
      assertTrue(lines[i].startsWith(i + ","),
          "row " + i + " should start with wedge number " + i + ": " + lines[i]);
    }
  }

  /** The row must be parseable as numbers, not NaN or a format failure. */
  @Test
  public void summaryCsvRowIsNumeric() {
    WriterString w = new WriterString();
    OutputSummaryCSV out = new OutputSummaryCSV(props(w));
    out.publishCrystal(new SummaryCrystal());
    out.publishWedge(wedge());

    String row = w.getDataString().split("\n")[1];
    for (String field : row.split(",")) {
      String v = field.trim();
      assertFalse(v.isEmpty(), "empty field in row: " + row);
      try {
        Double.parseDouble(v);
      } catch (NumberFormatException e) {
        throw new AssertionError("non-numeric field '" + v + "' in row: " + row);
      }
    }
  }

  // ------------------------------------------------- construction contract

  /** Every output rejects a missing writer rather than failing later. */
  @ParameterizedTest
  @ValueSource(strings = {"OutputSummaryCSV", "OutputRDECSV", "OutputDWDs",
                          "OutputSummaryText", "OutputFinalDoseStateCSV",
                          "OutputVoxelDose", "OutputVoxelFluences"})
  public void everyOutputRejectsAMissingWriter(String className)
      throws Exception {
    final Class<?> type = Class.forName("se.raddo.raddose3D." + className);
    final Map<Object, Object> empty = new HashMap<Object, Object>();

    assertThrows(Exception.class,
        () -> type.getConstructor(Map.class).newInstance(empty),
        className + " accepted a property map with no writer");
  }

  /** Every output can be built with a writer and closed without error. */
  @ParameterizedTest
  @ValueSource(strings = {"OutputSummaryCSV", "OutputRDECSV", "OutputDWDs",
                          "OutputSummaryText", "OutputFinalDoseStateCSV",
                          "OutputVoxelDose", "OutputVoxelFluences"})
  public void everyOutputBuildsAndClosesCleanly(String className)
      throws Exception {
    Class<?> type = Class.forName("se.raddo.raddose3D." + className);
    WriterString w = new WriterString();

    Output out = (Output) type.getConstructor(Map.class).newInstance(props(w));
    out.publishBeam(new BeamDummy());
    out.close();
  }

  /** close() must reach the writer, so buffered output is not lost. */
  @Test
  public void closingTheOutputClosesTheWriter() {
    WriterString w = new WriterString();
    OutputSummaryCSV out = new OutputSummaryCSV(props(w));
    out.close();

    assertThrows(RuntimeException.class, () -> w.write("after close"),
        "the writer should have been closed by Output.close()");
  }

  /**
   * ExposureSummary.exposureComplete() throws if no image ever completed: it
   * indexes the per-image arrays at [count - 1], which is [-1] when the count
   * is zero. An exposure whose angular resolution exceeds its wedge span would
   * reach this. Pinned rather than fixed -- see TEST-TRIAGE.md #7.
   */
  @Test
  public void exposureCompleteThrowsIfNoImagesRan() {
    ExposureSummary summary = new ExposureSummary();
    summary.exposureStart(1, wedge(), new int[] {2, 2, 2});

    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> summary.exposureComplete(),
        "expected the [-1] index; if this now passes, the guard was added");
  }

  /** publishBeam is a no-op for the summary outputs and must not emit a row. */
  @Test
  public void publishBeamDoesNotEmitARow() {
    WriterString w = new WriterString();
    OutputSummaryCSV out = new OutputSummaryCSV(props(w));
    String afterHeader = w.getDataString();

    out.publishBeam(new BeamDummy());

    assertEquals(afterHeader, w.getDataString(),
        "publishBeam should not write anything to the summary");
  }
}
