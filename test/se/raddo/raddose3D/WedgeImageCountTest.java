package se.raddo.raddose3D;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks how many images a wedge is exposed in: one per step, both ends
 * included, in either direction.
 * <p>
 * The count was computed in radians and truncated, so a 0 to 30 degree
 * wedge at 2 degrees -- 14.999999999999998 steps -- had 15 images, not 16.
 * A wedge running backwards counted sign * (int)(span / step + 1) of its
 * negative span, two images short, and its requested step was tested
 * against the negative span and so always replaced by the default.
 */
public class WedgeImageCountTest {

  /** A tiny crystal, so each run is quick. */
  private static final String INPUT =
        "Crystal\n"
      + "Type Cuboid\n"
      + "Dimensions 6 6 6\n"
      + "PixelsPerMicron 0.5\n"
      + "AbsCoefCalc RD3D\n"
      + "UnitCell 78.02 78.02 78.02\n"
      + "NumMonomers 24\n"
      + "NumResidues 51\n"
      + "\nBeam\n"
      + "Type Gaussian\n"
      + "Flux 2e12\n"
      + "FWHM 20 20\n"
      + "Energy 12.1\n"
      + "\nWedge START END\n"
      + "ExposureTime 10\n"
      + "RESOLUTION";

  /**
   * Runs a wedge and returns its images' angles, from DWDs.csv.
   *
   * @param start the wedge's start, degrees
   * @param end its end, degrees
   * @param resolution an AngularResolution line, or empty
   * @return the angles, degrees
   * @throws Exception if the run fails
   */
  private static double[] angles(final String start, final String end,
      final String resolution) throws Exception {
    WriterString dwds = new WriterString();
    WriterString voxelDoses = new WriterString();
    Map<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Output.OUTPUT_WRITER, dwds);
    Map<Object, Object> voxelProperties = new HashMap<Object, Object>();
    voxelProperties.put(Output.OUTPUT_WRITER, voxelDoses);
    Experiment experiment = new Experiment();
    experiment.addObserver(new OutputDWDs(properties));
    experiment.addObserver(new OutputVoxelDose(voxelProperties));
    PrintStream console = System.out;
    System.setOut(new PrintStream(new ByteArrayOutputStream(), true, "UTF-8"));
    try {
      experiment.process(new InputParserString(INPUT.replace("START", start)
          .replace("END", end).replace("RESOLUTION", resolution)));
      experiment.close();
    } finally {
      System.setOut(console);
    }
    String[] lines = dwds.getDataString().trim().split("\n");
    // VoxelDose.csv has a row per image after its header, and counted its
    // images separately: it used to keep the old count after the exposure
    // had the new one.
    assertEquals(lines.length,
        voxelDoses.getDataString().trim().split("\n").length,
        "VoxelDose.csv images against DWDs.csv images");
    double[] out = new double[lines.length - 1];
    for (int i = 1; i < lines.length; i++) {
      out[i - 1] = Double.parseDouble(lines[i].split(",")[2].trim());
    }
    return out;
  }

  /**
   * Images and the first and last angles, forwards and backwards.
   *
   * @param start wedge start, degrees
   * @param end wedge end, degrees
   * @param resolution AngularResolution line, or empty for the default 2
   * @param images expected number of images
   */
  @ParameterizedTest
  @CsvSource({
      "0, 30, '', 16",
      "0, 90, 'AngularResolution 3\n', 31",
      "90, 0, 'AngularResolution 3\n', 31",
      "30, 0, '', 16",
  })
  public void oneImagePerStepBothEndsIncluded(final String start,
      final String end, final String resolution, final int images)
      throws Exception {
    double[] angles = angles(start, end, resolution);
    assertEquals(images, angles.length, "images");
    assertEquals(Double.parseDouble(start), angles[0], 1e-9, "first angle");
    assertEquals(Double.parseDouble(end), angles[angles.length - 1], 1e-9,
        "last angle");
  }
}
