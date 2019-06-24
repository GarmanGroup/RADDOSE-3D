package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Summary text output module for the PNAS 2013 release of the code. */
public class OutputSummaryText implements ExposeObserver, Output,
    ExperimentNotices {
  /** Where output should be directed to. */
  private final Writer         w;

  /** Default TAD to be reported is TAD-95. */
  private static final Double  ABSORBED_ENERGY_THRESHOLD = 0.95;

  /** How many % are in 100%. */
  private static final Integer PERCENT                   = 100;

  /** Custom set TAD. */
  private final Double         absEnThreshold;

  /** Simple dose histogram of exposed crystal regions. */
  private final Histogram      h;
  /** Default minimum dose for the simple dose histogram. */
  private static final Double  DEFAULT_HISTOGRAM_MIN     = 0.1d;
  /** Default maximum dose for the simple dose histogram. */
  private static final Double  DEFAULT_HISTOGRAM_MAX     = 30.0d;
  /** Default number of bins in the simple dose histogram. */
  private static final Integer DEFAULT_HISTOGRAM_BINS    = 9;

  /** Source of the coefficients used during exposure. */
  private CoefCalc             crystalCoefCalc;
  /** ExposureSummary object producing summary metrics from exposures. */
  private ExposureSummary      expSummary;

  /** Counter for seen wedges. */
  private Integer              wedgeNum                  = 0;

  /** List of references to display. */
  private List<String>         references                =
      new ArrayList<String>();

  /**
   * Generic property constructor for OutputSummaryText class.
   * Extracts all required information from a Map data structure.
   * *
   * Used properties:
   * OUTPUT_WRITER - writer class to which output will be directed.
   * OUTPUT_HISTBINS - number of bins for the fluence per dose histogram.
   * OUTPUT_HISTMIN - lower bound of the second bin of the resulting histogram.
   * OUTPUT_HISTMAX - upper bound of the penultimate bin of the resulting
   * * histogram.
   * OUTPUT_ABS_ENERGY_THRESH - custom TAD to be reported.
   *
   * @param properties
   *          Map of type <Object, Object> that contains all output properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Output} class.
   */
  public OutputSummaryText(final Map<Object, Object> properties) {
    // Check if optional values are initialized, otherwise set to defaults.
    Map<Object, Object> mProp = new HashMap<Object, Object>();
    mProp.put(OUTPUT_HISTBINS, DEFAULT_HISTOGRAM_BINS);
    mProp.put(OUTPUT_HISTMIN, DEFAULT_HISTOGRAM_MIN);
    mProp.put(OUTPUT_HISTMAX, DEFAULT_HISTOGRAM_MAX);
    mProp.put(OUTPUT_ABS_ENERGY_THRESH, ABSORBED_ENERGY_THRESHOLD);
    mProp.putAll(properties);

    // Check for valid parameters
    Assertions a = new Assertions("Could not create "
        + "OutputSummaryText: ");
    a.checkIsClass(mProp.get(OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) mProp.get(OUTPUT_WRITER);

    a.checkIsClass(mProp.get(OUTPUT_HISTBINS), Integer.class,
        "number of histograms bins is not an integer");
    a.checkIsClass(mProp.get(OUTPUT_HISTMIN), Double.class,
        "histogram minimum value is not a double");
    a.checkIsClass(mProp.get(OUTPUT_HISTMAX), Double.class,
        "histogram maximum value is not a double");
    h = new Histogram((Double) mProp.get(OUTPUT_HISTMIN),
        (Double) mProp.get(OUTPUT_HISTMAX),
        (Integer) mProp.get(OUTPUT_HISTBINS));

    a.checkIsClass(mProp.get(OUTPUT_ABS_ENERGY_THRESH), Double.class,
        "absorbed energy threshold is not a double");
    absEnThreshold = (Double) mProp.get(OUTPUT_ABS_ENERGY_THRESH);
  }

  @Override
  public void publishCrystal(final Crystal c) {
    w.write(c.crystalInfo() + "\n");
    w.write(c.getDDM().toString() + "\n");

    c.addObserver(this);

    expSummary = c.getExposureSummary();

    crystalCoefCalc = c.getCoefCalc();

    h.reset();
  }

  @Override
  public void publishBeam(final Beam b) {
    w.write(b.getDescription());
  }

  @Override
  public void exposureObservation(final int wedgeImage, final int i,
      final int j, final int k, final double addedDose, final double totalDose,
      final double fluence, final double doseDecay,
      final double absorbedEnergy, final double elastic) {
    // No implementation required
  }

  @Override
  public void imageComplete(final int image, final double angle, final double lastAngle, final double voxVol) {
    // No implementation required
  }

  @Override
  public void summaryObservation(final int i, final int j, final int k,
      final double totalDose, final double voxelMassKg) {
    if (totalDose > 0) {
      h.addObservation(totalDose);
    }
  }

  @Override
  public void publishWedge(final Wedge wdg) {
    // Wedge info
    wedgeNum++;
    w.write("Wedge " + wedgeNum + ":\n");
    w.write(wdg.wedgeProperties() + crystalCoefCalc.toString() + "\n");

    w.write(String.format("%-42s: %.6f MGy%n",
        "Average Diffraction Weighted Dose",
        expSummary.getAvgDiffractedDose()));
    w.write(String.format("%-42s: %.6f MGy%n",
        "Last Diffraction Weighted Dose",
        expSummary.getLastDWD()));
    w.write(String.format("%-42s: %.2e photons%n",
        "Elastic Yield",
        expSummary.getWedgeElastic()));
    w.write(String.format("%-42s: %.2e photons/MGy%n",
        "Diffraction Efficiency (Elastic Yield/DWD)",
        expSummary.getWedgeElastic() / expSummary.getAvgDiffractedDose()));

    w.write(String.format("%-42s: %.6f MGy%n",
        "Average Dose (Whole Crystal)",
        expSummary.getAvgDoseWholeCrystal()));
    w.write(String.format("%-42s: %.6f MGy%n",
        "Average Dose (Exposed Region)",
        expSummary.getAvgDoseExposedRegion()));
    w.write(String.format("%-42s: %.6f MGy%n",
        "Max Dose",
        expSummary.getMaxDose()));
    w.write(String.format("%-42s: %.6f MGy%n",
        String.format(
            "Average Dose (%.1f %% of total absorbed energy threshold"
                + " (%.2f MGy))",
            absEnThreshold * PERCENT,
            expSummary.getAbsDoseThreshold(absEnThreshold)),
        expSummary.getAvgDoseThreshold(absEnThreshold)));

    w.write(String.format("%-42s: %.2f%n",
        "Dose Contrast (Max/Threshold Av.)",
        expSummary.getDoseContrast(absEnThreshold)));
    w.write(String.format("%-42s: %.1f%%%n",
        "Used Volume",
        expSummary.getUsedVolumeFraction()));
    w.write(String.format("%-42s: %.2e J.%n",
        "Absorbed Energy (this Wedge)",
        expSummary.getAbsEnergyTotal()));
    w.write(String.format("%-42s: %.1f 1/g%n",
        "Dose Inefficiency (Max Dose/mJ Absorbed)",
        expSummary.getDoseInefficiency()));
    w.write(String.format("%-42s: %.1f 1/g%n",
        "Dose Inefficiency PE (Max Dose/mJ Deposited)",
        expSummary.getDoseInefficiencyPE()));
    
    /*
    //RDE warning
    if (expSummary.getAvgRDE() == true) {
      w.write(String.format("%-42s%n", "WARNING - THE AVG RELATIVE DIFFRACTION EFFICIENCY DROPS BELOW 0.5"));
    }
    if (expSummary.getWeightedRDE() == true) {
      w.write(String.format("%-42s%n", "WARNING - THE WEIGHTED RELATIVE DIFFRACTION EFFICIENCY DROPS BELOW 0.5"));
    }
    */

    StringBuffer b = new StringBuffer("Final Dose Histogram:\n");

    Double[] doseHistogram = h.getNormalizedObservationHistogram();
    Double[] doseHistogramBreaks = h.getHistogramBreaks();
    int bins = doseHistogram.length;

    b.append(String.format("Bin  1,  0.0 to %4.1f MGy: %4.1f %% %n",
        doseHistogramBreaks[1], doseHistogram[0] * PERCENT));
    for (int i = 1; i < bins - 1; i++) {
      b.append(String.format("Bin %2d, %4.1f to %4.1f MGy: %4.1f %% %n",
          i + 1, doseHistogramBreaks[i], doseHistogramBreaks[i + 1],
          doseHistogram[i] * PERCENT));
    }
    b.append(String.format("Bin %2d, %4.1f MGy upwards: %4.1f %%%n",
        bins, doseHistogramBreaks[bins - 1],
        doseHistogram[bins - 1] * PERCENT));
    w.write(b);
    h.reset();
  }

  @Override
  public void exposureStart(final int imageCount, Wedge wedge) {
    // No implementation required
  }

  @Override
  public void exposureComplete() {
    // No implementation required
  }

  @Override
  public void close() {
    expSummary = null;
    crystalCoefCalc = null;
    if ((references != null) && (!references.isEmpty())) {
      w.write("\nReferences:\n");
      for (String s : references) {
        w.write(s);
      }
    }
    references = null;
    w.close();
  }

  @Override
  public void register(final Crystal c) {
    // No implementation required
  }

  @Override
  public void raiseWarning(final String warning) {
    w.write("\n*** " + warning + "\n");
  }

  @Override
  public void addReference(final String reference) {
    references.add(reference);
  }
}
