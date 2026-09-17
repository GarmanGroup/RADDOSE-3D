package se.raddo.raddose3D;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * End-to-end characterisation tests: run a complete experiment and compare the
 * summary output against a committed reference file.
 * <p>
 * This is the only practical way to cover {@link Crystal#expose}, which is
 * roughly 670 lines and the single largest untested surface on the MX path. It
 * is also the only thing guarding the {@code energyPerFluence} half of commit
 * c0d0c3f, which fixed a shared field being overwritten with the cryo value
 * partway through the exposure.
 * <p>
 * These tests were impossible before runs became reproducible: the same input
 * previously gave a different answer every time, with average diffraction
 * weighted dose spanning about 0.4%. They pin a fixed seed through
 * {@link RandomSource}.
 * <p>
 * <strong>They assert that behaviour has not changed, not that it is right.</strong>
 * A deliberate change will fail them; regenerate with {@link #main(String[])}
 * and review the diff.
 * <p>
 * Values are compared with a relative tolerance rather than exactly. The
 * calculation uses {@link Math#pow}, {@link Math#exp} and {@link Math#log},
 * none of which are required to be bit-reproducible across JVM
 * implementations -- only {@link StrictMath} is. The same seed and input give
 * an average DWD of 12.614139 on macOS/JDK 25, 12.614146 on Linux/JDK 8 and
 * 12.614135 on Linux/JDK 21: agreement to about 1 part in 10^6. The tolerance
 * below accommodates that while still being five orders of magnitude tighter
 * than any physics regression these tests exist to catch -- reverting c0d0c3f,
 * for instance, moves a field by a factor of four.
 */
public class GoldenRunTest {

  /** Fixed so the Monte Carlo escape calculations replay identically. */
  private static final long SEED = 20260917L;

  private static final String GOLDEN_DIR = "test/resources/";

  /**
   * A small MX experiment exercising the interesting paths: photoelectron
   * escape, fluorescence escape and a surrounding medium. Kept deliberately
   * small so the test runs in seconds.
   */
  private static final String MX_WITH_ESCAPE =
        "Crystal\n"
      + "Type Cuboid\n"
      + "Dimensions 12 10 8\n"
      + "PixelsPerMicron 0.5\n"
      + "AbsCoefCalc RD3D\n"
      + "UnitCell 78.02 78.02 78.02\n"
      + "NumMonomers 24\n"
      + "NumResidues 51\n"
      + "ProteinHeavyAtoms Zn 0.333 S 6\n"
      + "SolventHeavyConc P 425\n"
      + "SolventFraction 0.641\n"
      + "CALCULATEPEESCAPE TRUE\n"
      + "CALCULATEFLESCAPE TRUE\n"
      + "CALCSURROUNDING TRUE\n"
      + "\nBeam\n"
      + "Type Gaussian\n"
      + "Flux 2e12\n"
      + "FWHM 20 20\n"
      + "Energy 12.1\n"
      + "Collimation Rectangular 30 30\n"
      + "\nWedge 0 90\n"
      + "ExposureTime 10\n"
      + "AngularResolution 30\n";

  /** The same experiment with no escape modelling, i.e. the plain dose path. */
  private static final String MX_PLAIN =
      MX_WITH_ESCAPE.replace("CALCULATEPEESCAPE TRUE\n", "")
                    .replace("CALCULATEFLESCAPE TRUE\n", "")
                    .replace("CALCSURROUNDING TRUE\n", "");

  /**
   * Runs one experiment to completion and returns the summary CSV.
   * Stdout is captured so the progress chatter does not pollute test output.
   */
  private static String runSummary(final String input) throws Exception {
    RandomSource.setSeed(SEED);

    WriterString writer = new WriterString();
    Map<Object, Object> outProps = new HashMap<Object, Object>();
    outProps.put(Output.OUTPUT_WRITER, writer);

    Experiment experiment = new Experiment();
    experiment.addObserver(new OutputSummaryCSV(outProps));

    PrintStream realOut = System.out;
    System.setOut(new PrintStream(new ByteArrayOutputStream(), true, "UTF-8"));
    try {
      experiment.process(new InputParserString(input));
      experiment.close();
    } finally {
      System.setOut(realOut);
    }
    return writer.getDataString();
  }

  /** @return the field as a double, or null if it is not numeric. */
  private static Double asDouble(final String field) {
    try {
      return Double.valueOf(field);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private static String readGolden(final String name) throws Exception {
    File f = new File(GOLDEN_DIR + name);
    assertTrue(f.isFile(),
        "missing reference file " + f.getPath()
            + " -- run GoldenRunTest.regenerate() to create it");
    return new String(Files.readAllBytes(f.toPath()), Charset.forName("UTF-8"));
  }

  /**
   * Relative tolerance for numeric fields. See the class comment: this
   * accommodates cross-JVM floating point variation in Math.pow/exp/log, not
   * any variation in the physics.
   */
  private static final double RELATIVE_TOLERANCE = 1e-5;

  /**
   * Compares field by field so a failure names the column that moved rather
   * than dumping two long lines. Numeric fields are compared with a relative
   * tolerance; anything non-numeric must match exactly.
   */
  private static void assertMatchesGolden(final String goldenName,
      final String actual) throws Exception {
    String[] expectedLines = readGolden(goldenName).split("\n");
    String[] actualLines = actual.split("\n");

    assertEquals(expectedLines.length, actualLines.length,
        "row count changed in " + goldenName);

    String[] columns = expectedLines[0].split(",");
    for (int row = 1; row < expectedLines.length; row++) {
      String[] expectedFields = expectedLines[row].split(",");
      String[] actualFields = actualLines[row].split(",");
      assertEquals(expectedFields.length, actualFields.length,
          "field count changed on row " + row + " of " + goldenName);

      for (int col = 0; col < expectedFields.length; col++) {
        String label = (col < columns.length ? columns[col].trim()
                                             : "column " + col);
        String expectedField = expectedFields[col].trim();
        String actualField = actualFields[col].trim();
        String where = goldenName + " row " + row + ": '" + label + "'";

        Double expectedValue = asDouble(expectedField);
        Double actualValue = asDouble(actualField);

        if (expectedValue == null || actualValue == null) {
          assertEquals(expectedField, actualField, where + " changed");
          continue;
        }

        double tolerance =
            Math.max(Math.abs(expectedValue) * RELATIVE_TOLERANCE, 1e-12);
        assertEquals(expectedValue.doubleValue(), actualValue.doubleValue(),
            tolerance,
            where + " changed beyond the " + RELATIVE_TOLERANCE
                + " relative tolerance (expected " + expectedField + ", got "
                + actualField + ")");
      }
    }
  }

  @Test
  @Tag("slow")
  public void mxRunWithEscapeMatchesReference() throws Exception {
    assertMatchesGolden("golden-mx-escape-summary.csv",
        runSummary(MX_WITH_ESCAPE));
  }

  @Test
  @Tag("slow")
  public void mxRunWithoutEscapeMatchesReference() throws Exception {
    assertMatchesGolden("golden-mx-plain-summary.csv", runSummary(MX_PLAIN));
  }

  /**
   * The seed must actually determine the result, otherwise the references
   * above are pinning noise and would fail intermittently.
   */
  @Test
  @Tag("slow")
  public void theSameSeedReproducesTheSameRun() throws Exception {
    // Exact equality is correct here: this is the same JVM, so the only thing
    // being tested is that the seed determines the result.
    assertEquals(runSummary(MX_WITH_ESCAPE), runSummary(MX_WITH_ESCAPE),
        "a fixed seed must give the same summary twice");
  }

  /**
   * Escape modelling must change the answer, otherwise the escape reference is
   * silently testing the same path as the plain one.
   */
  @Test
  @Tag("slow")
  public void escapeModellingChangesTheResult() throws Exception {
    assertTrue(!runSummary(MX_WITH_ESCAPE).equals(runSummary(MX_PLAIN)),
        "enabling PE/FL escape and a surrounding medium should change the "
            + "summary; if not, those inputs are being ignored");
  }

  /**
   * Regenerates the reference files. Not a test -- run deliberately after an
   * intended change, then review the diff before committing:
   * {@code java -cp bin:bin-test se.raddo.raddose3D.GoldenRunTest}
   */
  public static void main(final String[] args) throws Exception {
    write("golden-mx-escape-summary.csv", runSummary(MX_WITH_ESCAPE));
    write("golden-mx-plain-summary.csv", runSummary(MX_PLAIN));
  }

  private static void write(final String name, final String content)
      throws Exception {
    File f = new File(GOLDEN_DIR + name);
    Files.write(f.toPath(), content.getBytes(Charset.forName("UTF-8")));
    System.out.println("wrote " + f.getPath());
  }
}
