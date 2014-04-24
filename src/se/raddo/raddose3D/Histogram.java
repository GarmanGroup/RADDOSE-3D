package se.raddo.raddose3D;

/**
 * Saves all encountered values in predefined bins and returns a histogram.
 */
public class Histogram {
  /** Lowest bin boundary. */
  private final double minValue;
  /** Highest bin boundary. */
  private final double maxValue;
  /** Bin width. */
  private final double bucketStep;
  /**
   * The number of buckets including one bucket for values smaller than minValue
   * and one bucket for values larger than maxValue.
   */
  private final int    bucketCount;

  //     _
  //    | |  _
  //    | |_| |
  //  -----------
  //   1 2 3 4 5
  //    |     \-- maxValue = 4.5
  //    |         bucketCount = 3
  //    \-------- minValue = 1.5
  //  results in 5 buckets: 1: x<1.5
  //                        2: 1.5<=x<2.5
  //                        3: 2.5<=x<3.5
  //                        4: 3.5<=x<4.5
  //                        5: x>=4.5

  private int[]        observations;
  private double[]     values;

  private int          observationCount;
  private double       observationPositionSum;
  private double       observationWeightedSum;

  /**
   * Keep the sum of the weights of all observations. Unweighted observations
   * are treated as observations with weight 1.
   */
  private double       observationWeightSum;

  /**
   * creates a new Histogram with a specified number of bins between
   * a minimum and a maximum value, plus two additional bins for observations
   * below the minimum and above the maximum value.
   * 
   * @param rangeMin
   *          lower boundary of the observation range
   * @param rangeMax
   *          upper boundary of the observation range
   * @param buckets
   *          number of buckets within the observation range
   */
  public Histogram(final double rangeMin, final double rangeMax,
      final int buckets) {
    if (rangeMax <= rangeMin) {
      throw new RuntimeException(
          "Histogram object could not be created. RangeMin (" + rangeMin
              + ") >= RangeMax (" + rangeMax + ")");
    }

    minValue = rangeMin;
    maxValue = rangeMax;

    if (buckets <= 0) {
      throw new RuntimeException(
          "Histogram object could not be created. Buckets (" + buckets
              + ") <= 0");
    }

    bucketStep = (rangeMax - rangeMin) / buckets;
    bucketCount = buckets + 2;
    reset();
  }

  /**
   * Clone an existing histogram object, including all observations.
   * 
   * @param histogramToCopy
   *          existing instance of Histogram
   */
  public Histogram(final Histogram histogramToCopy) {
    minValue = histogramToCopy.minValue;
    maxValue = histogramToCopy.maxValue;
    bucketStep = histogramToCopy.bucketStep;
    bucketCount = histogramToCopy.bucketCount;
    observations = histogramToCopy.observations.clone();
    values = histogramToCopy.values.clone();
    observationCount = histogramToCopy.observationCount;
    observationPositionSum = histogramToCopy.observationPositionSum;
    observationWeightedSum = histogramToCopy.observationWeightedSum;
    observationWeightSum = histogramToCopy.observationWeightSum;
  }

  // TODO implement constructor
  // public Histogram(double[] breakpoints) { }

  /**
   * Records a single observation.
   * 
   * @param position
   *          position of the observation
   */
  public void addObservation(final double position) {
    int bucket;
    if (position < minValue) {
      bucket = 0;
    } else if (position >= maxValue) {
      bucket = bucketCount - 1;
    } else {
      bucket = 1 + (int) ((position - minValue) / bucketStep);
    }

    ++observationCount;
    ++observations[bucket];
    ++values[bucket];

    observationPositionSum += position;
    observationWeightedSum += 1 * position;
    observationWeightSum++;
  }

  /**
   * Records a weighted observation at a given position.
   * 
   * @param position
   *          position of the observation
   * @param weight
   *          weight of the observation
   */
  public void addValue(final double position, final double weight) {
    int bucket;
    if (position < minValue) {
      bucket = 0;
    } else if (position >= maxValue) {
      bucket = bucketCount - 1;
    } else {
      bucket = 1 + (int) ((position - minValue) / bucketStep);
    }

    ++observationCount;
    ++observations[bucket];
    values[bucket] += weight;

    observationPositionSum += position;
    observationWeightedSum += weight * position;
    observationWeightSum += weight;
  }

  /**
   * Clears all observations and resets the histogram.
   */
  public void reset() {
    observationCount = 0;
    observationPositionSum = 0;
    observationWeightedSum = 0;
    observationWeightSum = 0;
    observations = new int[bucketCount];
    values = new double[bucketCount];
  }

  /**
   * Returns the number of recorded observations.
   * 
   * @return
   *         number of observations (weighted and unweighted) since
   *         instantiation or the most recent reset.
   */
  public int getObservationCount() {
    return observationCount;
  }

  /**
   * Returns the (unweighted) mean of the position of all recorded observations
   * (weighted and unweighted).
   * 
   * @return
   *         unweighted mean position of all observations.
   *         null is returned if there are no previous observations.
   */
  public Double getPositionMean() {
    if (observationCount == 0) {
      return null;
    }
    return observationPositionSum / observationCount;
  }

  /**
   * Returns the mean of the weights of all observations. Unweighted
   * observations are treated as observations with weight 1.
   * 
   * @return
   *         mean observation weight.
   *         null is returned if there are no previous observations.
   */
  public Double getWeightMean() {
    if (observationCount == 0) {
      return null;
    }
    return observationWeightSum / observationCount;
  }

  /**
   * Returns the sum of the weights of all observations. Unweighted
   * observations are treated as observations with weight 1.
   * 
   * @return
   *         sum of observation weights.
   *         0 is returned if there are no previous observations.
   */
  public double getWeightSum() {
    return observationWeightSum;
  }

  public Double getWeightedPositionMean() {
    if (observationCount == 0) {
      return null;
    }
    return observationWeightedSum / observationWeightSum;
  }

  public int[] getObservationHistogram() {
    int[] histarray = new int[bucketCount];
    for (int i = 0; i < bucketCount; i++) {
      histarray[i] = observations[i];
    }
    return histarray;
  }

  public Double[] getWeightHistogram() {
    Double[] histarray = new Double[bucketCount];
    for (int i = 0; i < bucketCount; i++) {
      histarray[i] = values[i];
    }
    return histarray;
  }

  public Double[] getHistogramBreaks() {
    Double[] histarray = new Double[bucketCount];
    histarray[0] = Double.NEGATIVE_INFINITY;
    for (int i = 1; i < bucketCount; i++) {
      histarray[i] = minValue + ((i - 1) * bucketStep);
    }
    return histarray;
  }

  public Double[] getNormalizedObservationHistogram() {
    Double[] histarray = new Double[bucketCount];
    int[] histObs = getObservationHistogram();
    for (int i = 0; i < bucketCount; i++) {
      histarray[i] = ((double) histObs[i]) / observationCount;
    }
    return histarray;
  }

  public Double[] getNormalizedWeightHistogram() {
    Double[] histarray = getWeightHistogram();
    for (int i = 0; i < bucketCount; i++) {
      histarray[i] /= observationWeightSum;
    }
    return histarray;
  }

  /**
   * Calculate the Gini coefficient, an inequality measure, of the current
   * histogram state.
   * *This code does not work.*
   * 
   * @return
   *         Gini coefficient of the recorded distribution.
   */
  @Deprecated
  public double getGiniCoefficient() {
    // Calculating the lorentz curve from the fineHist
    int nBins = bucketCount;
    double[][] lorentzCurve = new double[nBins][2];
    lorentzCurve[0][0] = 0;
    lorentzCurve[0][1] = 0;

    double[] cumulativeWeights = new double[bucketCount];
    double[] cumulativeWeightedSum = new double[bucketCount];

    cumulativeWeights[0] = 0;
    cumulativeWeightedSum[0] = 0;
    for (int n = 1; n < nBins; n++) {
      cumulativeWeights[n] = cumulativeWeights[n - 1] + observations[n]
          + values[n];
      //      lorentzCurve[position][0] = lorentzCurve[position - 1][0]
      //            + histogram[position - 1][1]; // Cumulative number of voxels

      cumulativeWeightedSum[n] = cumulativeWeightedSum[n - 1]
          + (observations[n] + values[n]) *
          (minValue + ((n - 1) * bucketStep));
      // lorentzCurve[position][1] = lorentzCurve[position - 1][1]
      //                             + histogram[position - 1][1]
      //      * histogram[position - 1][0];
      // Cumulative total dose (dose*number of voxels at that dose)
    }

    double lorentzArea = 0;
    for (int n = 1; n < nBins; n++) {
      lorentzArea += 0.5
          * (cumulativeWeightedSum[n] + cumulativeWeightedSum[n - 1])
          * (cumulativeWeights[n] - cumulativeWeights[n - 1]);

      //      lorentzArea += 0.5 * (lorentzCurve[n][1] + lorentzCurve[n - 1][1])
      //          * (lorentzCurve[n][0] - lorentzCurve[n - 1][0]);
      // the first bit is + because we're adding parallelograms, not triangles..
      // Draw it or see lab book!
    }

    double areaOfEquality =
        0.5 * cumulativeWeights[bucketCount - 1]
            * cumulativeWeightedSum[bucketCount - 1];
    //        0.5 * lorentzCurve[nBins - 1][0] * lorentzCurve[nBins - 1][1];

    double gini = (areaOfEquality - lorentzArea) / areaOfEquality;
    return gini;
  }
}
