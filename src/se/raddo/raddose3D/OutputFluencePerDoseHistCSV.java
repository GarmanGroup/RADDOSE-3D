package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

/**
 * This Output module produces a fluence per dose histogram.
 * - not currently in use. Names need recasting since it now outputs a Elastic
 * per dose
 * histogram - 07-03-2012.
 * 
 * @author Oliver Zeldin
 */
// This file is not part of the RD3D release

public class OutputFluencePerDoseHistCSV implements Output, ExposeObserver {
  /** Default number of bins for the fluence per dose histogram. */
  private static final Integer DEFAULT_HISTOGRAM_BINS = 199;
  /**
   * Default value for the lower bound of the second bin of the resulting
   * fluence per dose histogram.
   */
  private static final Double  DEFAULT_HISTOGRAM_MIN  = 0.1;
  /**
   * Default value for the upper bound of the penultimate bin of the resulting
   * fluence per dose histogram. All observations greater than this value are
   * grouped together.
   */
  private static final Double  DEFAULT_HISTOGRAM_MAX  = 100.0;

  /** Where output should be directed to. */
  private final Writer         w;

  /** Number of the current wedge. */
  private int                  wedgeCounter           = 1;

  /** A reference to the current Crystal object. */
  private Crystal              crystal;

  private final Histogram      h;

  private double               diffractedIntensityNum;
  private double               diffractedIntensityDenom;

  /**
   * Generic property constructor for OutputFluencePerDoseHistCSV output class.
   * Extracts all required information from a Map data structure.
   * *
   * Used properties:
   * OUTPUT_WRITER - writer class to which output will be directed.
   * OUTPUT_HISTBINS - number of bins for the fluence per dose histogram.
   * OUTPUT_HISTMIN - lower bound of the second bin of the resulting histogram.
   * OUTPUT_HISTMAX - upper bound of the penultimate bin of the resulting
   * * histogram.
   * 
   * @param properties
   *          Map of type <Object, Object> that contains all output properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Output} class.
   */
  public OutputFluencePerDoseHistCSV(final Map<Object, Object> properties) {
    // Check if optional values are initialized, otherwise set to defaults.
    Map<Object, Object> mProp = new HashMap<Object, Object>();
    mProp.put(OUTPUT_HISTBINS, DEFAULT_HISTOGRAM_BINS);
    mProp.put(OUTPUT_HISTMIN, DEFAULT_HISTOGRAM_MIN);
    mProp.put(OUTPUT_HISTMAX, DEFAULT_HISTOGRAM_MAX);
    mProp.putAll(properties);

    // Check for valid parameters
    Assertions a = new Assertions("Could not create "
        + "OutputFluencePerDoseHistCSV: ");
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
  }

  /**
   * Constructor that allows control over the histogram range and bin size.
   * 
   * @param out
   *          Writer which will receive the CSV file.
   * @param histMin
   *          lower bound of the second bin of the resulting histogram.
   * @param histMax
   *          upper bound of the penultimate bin of the resulting histogram.
   * @param histBuckets
   *          number of bins for the fluence per dose histogram.
   */
  @Deprecated
  public OutputFluencePerDoseHistCSV(final Writer out,
      final double histMin, final double histMax, final int histBuckets) {
    w = out;
    h = new Histogram(histMin, histMax, histBuckets);
  }

  @Override
  public void publishCrystal(final Crystal c) {
    diffractedIntensityNum = 0;
    diffractedIntensityDenom = 0;
    crystal = c;

    crystal.addObserver(this);

    h.reset();
    Double[] histogram = h.getHistogramBreaks();
    w.write(String.format(
        "Wedge Number, Angular position, Average Diffracted Dose, < %g",
        histogram[1]));
    for (int i = 1; i < histogram.length - 1; i++) {
      w.write(String.format(", %g", histogram[i]));
    }
    w.write(String.format(", > %g%n", histogram[histogram.length - 1]));
  }

  @Override
  public void publishBeam(final Beam b) {
    // Implementation not required.
  }

  @Override
  public void exposureObservation(final int wedgeImage, final int i,
      final int j, final int k, final double addedDose, final double totalDose,
      final double fluence, final double doseDecay,
      final double absorbedEnergy, final double elastic, final double anglecount) {

    Double halfDose = totalDose + (addedDose / 2d);
    Double decay = crystal.getDDM().calcDecay(halfDose);

    h.addValue(halfDose, elastic * decay);

    // updating the diffracted intensity for this image/iteration equation
    diffractedIntensityNum += halfDose * elastic * decay;
    diffractedIntensityDenom += elastic * decay;
  }

  @Override
  public void imageComplete(final int image, final double angle) {
    double avgDiffDose;
    if (diffractedIntensityDenom == 0) {
      avgDiffDose = 0;
    } else {
      avgDiffDose = diffractedIntensityNum / diffractedIntensityDenom;
    }

    w.write(String.format("%d, %g, %g", wedgeCounter, Math.toDegrees(angle),
        avgDiffDose));

    Double[] histogram = h.getWeightHistogram();

    for (int i = 0; i < histogram.length; i++) {
      w.write(String.format(", %f", histogram[i]));
    }
    w.write("\n");
    h.reset();
    diffractedIntensityNum = 0;
    diffractedIntensityDenom = 0;
  }

  @Override
  public void summaryObservation(final int i, final int j, final int k,
      final double totalDose, final double voxelMassKg) {
    // Implementation not required.
  }

  @Override
  public void exposureStart(final int imageCount) {
    // Implementation not required.
  }

  @Override
  public void exposureComplete() {
    // Implementation not required.
  }

  @Override
  public void publishWedge(final Wedge wdg) {
    wedgeCounter++;
    h.reset();
    diffractedIntensityNum = 0;
    diffractedIntensityDenom = 0;
  }

  @Override
  public void close() {
    w.close();
  }

  @Override
  public void register(final Crystal c) {
    // Implementation not required.
  }
}
