package se.raddo.raddose3D;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks that escape modelling moves energy rather than losing it.
 * <p>
 * An absorbed photon's energy goes to the photoelectron, less the binding
 * energy, and the atom releases the binding energy as fluorescence and Auger
 * electrons. With photoelectron escape alone, only the K-shell Auger part of
 * the binding energy used to be deposited: the K-shell fluorescence and all L
 * and M relaxation were dropped. For a SeMet crystal just above the Se K edge
 * that was 15% of the absorbed energy.
 * <p>
 * In a crystal of 100 um with 10 um voxels, almost no photoelectron reaches
 * another voxel, let alone leaves the crystal, so the dose with photoelectron
 * escape must match the dose without it.
 */
public class EscapeEnergyTest {

  /** Fixed so the escape directions replay identically. */
  private static final long SEED = 20260917L;

  /** A SeMet crystal with one Se per monomer, exposed above the Se K edge. */
  private static final String SEMET =
        "Crystal\n"
      + "Type Cuboid\n"
      + "Dimensions 100 100 100\n"
      + "PixelsPerMicron 0.1\n"
      + "AbsCoefCalc RD3D\n"
      + "UnitCell 78.02 78.02 78.02\n"
      + "NumMonomers 24\n"
      + "NumResidues 51\n"
      + "ProteinHeavyAtoms Se 1 S 5\n"
      + "SolventHeavyConc P 425\n"
      + "SolventFraction 0.64\n"
      + "ESCAPE\n"
      + "\nBeam\n"
      + "Type Gaussian\n"
      + "Flux 2e12\n"
      + "FWHM 100 100\n"
      + "Energy 12.7\n"
      + "\nWedge 0 10\n"
      + "ExposureTime 10\n";

  /** Column of the whole-crystal average dose in the summary CSV. */
  private static final int AVERAGE_DOSE_WHOLE_CRYSTAL = 5;

  /**
   * Runs one experiment and returns its whole-crystal average dose.
   *
   * @param escape the escape directives, or an empty string for none
   * @return the dose in MGy
   * @throws Exception if the run fails
   */
  private static double averageDose(final String escape) throws Exception {
    RandomSource.setSeed(SEED);
    WriterString writer = new WriterString();
    Map<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Output.OUTPUT_WRITER, writer);
    Experiment experiment = new Experiment();
    experiment.addObserver(new OutputSummaryCSV(properties));

    PrintStream console = System.out;
    System.setOut(new PrintStream(new ByteArrayOutputStream(), true, "UTF-8"));
    try {
      experiment.process(
          new InputParserString(SEMET.replace("ESCAPE\n", escape)));
      experiment.close();
    } finally {
      System.setOut(console);
    }
    String[] lines = writer.getDataString().trim().split("\n");
    String[] fields = lines[lines.length - 1].split(",");
    return Double.parseDouble(fields[AVERAGE_DOSE_WHOLE_CRYSTAL].trim());
  }

  /** Photoelectron escape alone keeps the atom's relaxation energy. */
  @Test
  public void photoelectronEscapeAloneConservesEnergy() throws Exception {
    double plain = averageDose("");
    double escaping = averageDose("CALCULATEPEESCAPE TRUE\n");

    assertEquals(plain, escaping, 2e-3 * plain,
        "with photoelectron escape alone the dose fell from " + plain + " to "
            + escaping + " MGy");
  }
}
