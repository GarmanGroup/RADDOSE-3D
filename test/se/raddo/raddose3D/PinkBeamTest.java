package se.raddo.raddose3D;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks that a pink beam shares each image's exposure between the energies
 * it is sampled at, in everything it reports.
 * <p>
 * A pink beam is exposed once per sampled energy, 200 times an image, each
 * with a 200th of the exposure. Three things did not allow for that: the
 * surrounding had its whole exposure at every energy, the exposed volume in
 * DWDs.csv counted every voxel once per energy, and VoxelFluences.csv kept
 * only the last energy's fluence. A pink beam with a negligible spread must
 * therefore report what a monochromatic beam does.
 */
public class PinkBeamTest {

  /** Fixed so the escape directions replay identically. */
  private static final long SEED = 20260917L;

  /** A small protein crystal; ESCAPE and PINK are filled in per test. */
  private static final String INPUT =
        "Crystal\n"
      + "Type Cuboid\n"
      + "Dimensions 20 16 12\n"
      + "PixelsPerMicron 0.5\n"
      + "AbsCoefCalc RD3D\n"
      + "UnitCell 78.02 78.02 78.02\n"
      + "NumMonomers 24\n"
      + "NumResidues 51\n"
      + "ProteinHeavyAtoms Zn 0.333 S 6\n"
      + "SolventHeavyConc P 425\n"
      + "SolventFraction 0.64\n"
      + "ESCAPE"
      + "\nBeam\n"
      + "Type Gaussian\n"
      + "Flux 2e12\n"
      + "FWHM 20 20\n"
      + "Energy 12.1\n"
      + "PINK"
      + "Collimation Rectangular 40 40\n"
      + "\nWedge 0 WEDGE_END\n"
      + "ExposureTime 10\n";

  /** The outputs of one run. */
  private static final class Run {
    /** Summary.csv, last row, as numbers. */
    private final double[] summary;
    /** DWDs.csv rows after the header, as numbers. */
    private final List<double[]> dwds;
    /** VoxelFluences.csv rows after the header, as numbers. */
    private final List<double[]> fluences;

    Run(final double[] summary, final List<double[]> dwds,
        final List<double[]> fluences) {
      this.summary = summary;
      this.dwds = dwds;
      this.fluences = fluences;
    }
  }

  /**
   * Splits a CSV line into numbers.
   *
   * @param line the line
   * @return its fields
   */
  private static double[] numbers(final String line) {
    String[] fields = line.split(",");
    double[] values = new double[fields.length];
    for (int i = 0; i < fields.length; i++) {
      values[i] = Double.parseDouble(fields[i].trim());
    }
    return values;
  }

  /**
   * The rows of a CSV after its header.
   *
   * @param text the CSV
   * @return the rows, as numbers
   */
  private static List<double[]> rows(final String text) {
    List<double[]> out = new ArrayList<double[]>();
    String[] lines = text.trim().split("\n");
    for (int i = 1; i < lines.length; i++) {
      if (!lines[i].trim().isEmpty()) {
        out.add(numbers(lines[i]));
      }
    }
    return out;
  }

  /**
   * Runs the input and collects its outputs.
   *
   * @param input the input file
   * @return the outputs
   * @throws Exception if the run fails
   */
  private static Run run(final String input) throws Exception {
    RandomSource.setSeed(SEED);
    WriterString summary = new WriterString();
    WriterString dwds = new WriterString();
    WriterString fluences = new WriterString();
    Experiment experiment = new Experiment();
    experiment.addObserver(new OutputSummaryCSV(writer(summary)));
    experiment.addObserver(new OutputDWDs(writer(dwds)));
    experiment.addObserver(new OutputVoxelFluences(writer(fluences)));

    PrintStream console = System.out;
    System.setOut(new PrintStream(new ByteArrayOutputStream(), true, "UTF-8"));
    try {
      experiment.process(new InputParserString(input));
      experiment.close();
    } finally {
      System.setOut(console);
    }
    List<double[]> summaryRows = rows(summary.getDataString());
    return new Run(summaryRows.get(summaryRows.size() - 1),
        rows(dwds.getDataString()), rows(fluences.getDataString()));
  }

  /**
   * Properties naming one writer.
   *
   * @param w the writer
   * @return the properties
   */
  private static Map<Object, Object> writer(final Writer w) {
    Map<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Output.OUTPUT_WRITER, w);
    return properties;
  }

  /**
   * Builds an input.
   *
   * @param escape escape directives, or empty
   * @param pink an EnergyFWHM line, or empty
   * @param wedgeEnd the wedge's end angle
   * @return the input file
   */
  private static String input(final String escape, final String pink,
      final String wedgeEnd) {
    return INPUT.replace("ESCAPE", escape).replace("PINK", pink)
        .replace("WEDGE_END", wedgeEnd);
  }

  /**
   * Compares two tables of numbers.
   *
   * @param what which output, for messages
   * @param expected the monochromatic beam's
   * @param actual the pink beam's
   */
  private static void assertClose(final String what,
      final List<double[]> expected, final List<double[]> actual) {
    assertEquals(expected.size(), actual.size(), what + " rows");
    for (int r = 0; r < expected.size(); r++) {
      double[] e = expected.get(r);
      double[] a = actual.get(r);
      assertEquals(e.length, a.length, what + " row " + r + " columns");
      for (int c = 0; c < e.length; c++) {
        assertEquals(e[c], a[c], 1e-5 * Math.abs(e[c]) + 1e-12,
            what + " row " + r + " column " + c);
      }
    }
  }

  /**
   * Without escape, a pink beam with a negligible spread reports what a
   * monochromatic beam does: the summary, DWDs.csv -- its volume included --
   * and VoxelFluences.csv.
   */
  @Test
  public void aNarrowPinkBeamReportsAsAMonochromaticOne() throws Exception {
    Run mono = run(input("", "", "30"));
    Run pink = run(input("", "EnergyFWHM 0.000001\n", "30"));

    List<double[]> monoSummary = new ArrayList<double[]>();
    monoSummary.add(mono.summary);
    List<double[]> pinkSummary = new ArrayList<double[]>();
    pinkSummary.add(pink.summary);
    assertClose("Summary.csv", monoSummary, pinkSummary);
    assertClose("DWDs.csv", mono.dwds, pink.dwds);
    assertClose("VoxelFluences.csv", mono.fluences, pink.fluences);
  }

  /**
   * With a surrounding, a narrow pink beam's dose matches a monochromatic
   * beam's to within the noise of the escape draws, which differ because a
   * pink beam takes 200 times as many. The surrounding used to contribute
   * 200 times its share, making the dose many times too large.
   */
  @Test
  @Tag("slow")
  public void aNarrowPinkBeamGetsTheSurroundingsShareOnce() throws Exception {
    String escape = "CalculatePEEscape TRUE\nCalcSurrounding TRUE\n";
    Run mono = run(input(escape, "", "2"));
    Run pink = run(input(escape, "EnergyFWHM 0.000001\n", "2"));

    int averageDoseWholeCrystal = 5;
    assertEquals(mono.summary[averageDoseWholeCrystal],
        pink.summary[averageDoseWholeCrystal],
        0.02 * mono.summary[averageDoseWholeCrystal],
        "whole-crystal average dose");
  }
}
