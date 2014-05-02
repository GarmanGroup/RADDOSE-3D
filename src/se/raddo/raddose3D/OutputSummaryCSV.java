package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

/** Output CSV for PNAS release. */
public class OutputSummaryCSV implements Output {
  /** Where output should be directed to. */
  private final Writer         w;

  /** Default TAD to be reported is TAD-95. */
  private static final Double  ABSORBED_ENERGY_THRESHOLD = 0.95;

  /** How many % are in 100%. */
  private static final Integer PERCENT                   = 100;

  /** Custom set TAD. */
  private final double         absEnThreshold;

  /** ExposureSummary object producing summary metrics from exposures. */
  private ExposureSummary      expSummary;

  /** Number of seen wedges. */
  private int                  wedgeCounter;

  /**
   * Generic property constructor for OutputSummaryCSV class.
   * Extracts all required information from a Map data structure.
   * *
   * Used properties:
   * OUTPUT_WRITER - writer class to which output will be directed.
   * OUTPUT_ABS_ENERGY_THRESH - custom TAD to be reported.
   * 
   * @param properties
   *          Map of type <Object, Object> that contains all output properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Output} class.
   */
  public OutputSummaryCSV(final Map<Object, Object> properties) {
    // Check if optional values are initialized, otherwise set to defaults.
    Map<Object, Object> mProp = new HashMap<Object, Object>();
    mProp.put(OUTPUT_ABS_ENERGY_THRESH, ABSORBED_ENERGY_THRESHOLD);
    mProp.putAll(properties);

    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputSummaryCSV: ");
    a.checkIsClass(mProp.get(Output.OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) mProp.get(Output.OUTPUT_WRITER);

    a.checkIsClass(mProp.get(OUTPUT_ABS_ENERGY_THRESH), Double.class,
        "absorbed energy threshold is not a double");
    absEnThreshold = (Double) mProp.get(Output.OUTPUT_ABS_ENERGY_THRESH);

    w.write("Wedge Number, DWD, Elastic Yield (wedge), Diffraction Efficiency, "
        + "AD-WC, AD-ExpRegion, Max Dose, Dose Threshold, Abs En Threshold, "
        + "TAD, Dose Contrast, Used Volume, Wedge Absorbed Energy, "
        + "Dose Inefficiency\n");

  }

  @Override
  public void publishCrystal(final Crystal c) {
    expSummary = c.getExposureSummary();
  }

  @Override
  public void publishBeam(final Beam b) {
    // No implementation required.
  }

  @Override
  public void publishWedge(final Wedge wdg) {
    wedgeCounter++;

    w.write(String.format(
        "%d, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f%n",
        wedgeCounter,
        expSummary.getAvgDiffractedDose(),
        expSummary.getWedgeElastic(),
        expSummary.getWedgeElastic() / expSummary.getAvgDiffractedDose(),
        expSummary.getAvgDoseWholeCrystal(),
        expSummary.getAvgDoseExposedRegion(),
        expSummary.getMaxDose(),
        expSummary.getAbsDoseThreshold(absEnThreshold),
        absEnThreshold * PERCENT,
        expSummary.getAvgDoseThreshold(absEnThreshold),
        expSummary.getDoseContrast(absEnThreshold),
        expSummary.getUsedVolumeFraction(),
        expSummary.getAbsEnergyTotal(),
        expSummary.getDoseInefficiency()));
  }

  @Override
  public void close() {
    expSummary = null;
    w.close();
  }
}
