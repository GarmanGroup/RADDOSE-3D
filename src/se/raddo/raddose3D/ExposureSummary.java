package se.raddo.raddose3D;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Generate generic summary statistics for an single wedge exposure of a
 * crystal.
 */
public class ExposureSummary implements ExposeObserver {
  /** How many % are in 100%. */
  private static final Integer                PERCENT = 100;

  /**
   * A red-black tree for dose observations to identify threshold boundaries.
   */
  private final NavigableMap<Double, Integer> voxelDoses;

  // per voxel exposure variables exposureObservation()
  private Double                              totalAbsorbedEnergy;
  private Double                              diffNum;
  private Double                              diffDenom;
  private Double                              wedgeElastic;

  // per image exposure variables imageComplete()
  private Double                              runningSumDiffDose;
  /** Internal counter of individual exposures. */
  private int                                 images;

  /** Total absorbed dose over all crystal voxels in MGy. */
  private Double                              totalDose;
  /** Total number of exposed voxels in the crystal (voxel dose > 0). */
  private int                                 exposedVoxels;
  /** Total number of occupied (= non-empty) voxels in the crystal. */
  private int                                 occupiedVoxels;

  // exposure summary variables exposureComplete()
  /** Diffraction weighted dose (Zeldin et al, 2013, PNAS). */
  private Double                              avgDiffractedDose;
  /** Average dose throughout the whole crystal. */
  private Double                              avgDoseWholeCrystal;
  /** Average dose of the exposed crystal regions. */
  private Double                              avgDoseExposedRegion;
  /** Fraction of the crystal volume that has been illuminated. */
  private Double                              usedVolumeFraction;
  private Double                              doseInefficiency;

  /**
   * Last requested dose quantile.
   * For caching of dose quantile dependent summary statistics.
   */
  private Double                              cachedDoseQuantile;
  /**
   * Last calculated absolute dose threshold.
   * For caching of dose quantile dependent summary statistics.
   */
  private Double                              cachedAbsDoseThreshold;
  /**
   * The number of voxels found above the absolute dose threshold.
   * For caching of dose quantile dependent summary statistics.
   */
  private Integer                             cachedVoxelsAboveThreshold;
  /**
   * Last calculated average dose within dose quantile volume.
   * For caching of dose quantile dependent summary statistics.
   */
  private Double                              cachedAvgDoseThreshold;
  /**
   * Last calculated dose contrast.
   * For caching of dose quantile dependent summary statistics.
   */
  private Double                              cachedDoseContrast;

  /**
   * Create an observer object for a crystal that records simple summary
   * statistics.
   */
  public ExposureSummary() {
    voxelDoses = new TreeMap<Double, Integer>();
  }

  @Override
  public void register(final Crystal c) {
    // No implementation required.
  }

  @Override
  public void exposureStart(final int imageCount) {
    // Resets the global crystal metrics for each new exposure.

    // per voxel exposure variables exposureObservation()
    totalAbsorbedEnergy = 0d;
    diffNum = 0d;
    diffDenom = 0d;
    wedgeElastic = 0d;

    // per image exposure variables imageComplete()
    runningSumDiffDose = 0d;
    images = 0;

    // exposure summary per voxel variables summaryObservation()
    totalDose = 0d;
    exposedVoxels = 0;
    occupiedVoxels = 0;

    // Clear the observation tree
    voxelDoses.clear();
    // record a single 0-dose observation, so getMaxDose() is defined
    voxelDoses.put(0d, 1);

    // reset cached values
    cachedDoseQuantile = null;
    cachedAbsDoseThreshold = null;
    cachedVoxelsAboveThreshold = 0;
    cachedAvgDoseThreshold = null;
    cachedDoseContrast = null;
  }

  @Override
  public void exposureObservation(final int wedgeImage,
      final int i, final int j, final int k,
      final double addedDose, final double totalVoxDose, final double fluence,
      final double doseDecay, final double absorbedEnergy,
      final double elastic) {

    // updating the diffracted intensity for this image/iteration equation
    diffNum += (totalVoxDose + addedDose / 2) * fluence * doseDecay;
    diffDenom += fluence * doseDecay;

    totalAbsorbedEnergy += absorbedEnergy;
    wedgeElastic += elastic;

  }

  @Override
  public void imageComplete(final int image, final double angle) {
    if (diffDenom != 0) {
      runningSumDiffDose += diffNum / diffDenom;
    }
    diffNum = 0d;
    diffDenom = 0d;
    images++;
  }

  @Override
  public void summaryObservation(final int i, final int j, final int k,
      final double voxelDose) {
    occupiedVoxels++;

    if (voxelDose > 0) {
      // Record dose observation in voxelDoses, so that dose thresholds
      // can be found
      Integer voxelDoseSeenCount = voxelDoses.get(voxelDose);
      if (voxelDoseSeenCount == null) {
        voxelDoses.put(voxelDose, 1);
      } else {
        voxelDoses.put(voxelDose, 1 + voxelDoseSeenCount);
      }

      // Data for avDose
      totalDose += voxelDose;
      exposedVoxels++;
    }
  }

  @Override
  public void exposureComplete() {
    avgDiffractedDose = runningSumDiffDose / images;

    // Calculating average dose, dose contrast, used volume, thresholded dose,
    // and average diffracted dose:
    avgDoseExposedRegion = totalDose / exposedVoxels;

    usedVolumeFraction = PERCENT * (double) exposedVoxels / occupiedVoxels;

    doseInefficiency = getMaxDose() / (1000 * totalAbsorbedEnergy);

    avgDoseWholeCrystal = totalDose / occupiedVoxels;
  }

  public Double getAvgDiffractedDose() {
    return avgDiffractedDose;
  }

  public Double getAvgDoseWholeCrystal() {
    return avgDoseWholeCrystal;
  }

  public Double getAvgDoseExposedRegion() {
    return avgDoseExposedRegion;
  }

  /**
   * Obtain absorbed dose threshold for a specified dose quantile.
   * 
   * @param doseQuantile
   *          Fraction of total dose that should lie above the returned
   *          threshold. Valid values lie between 0 and 1.
   * @return
   *         Dose value of the iso-surface encompassing doseQuantile * 100% of
   *         the total dose within the crystal.
   */
  public Double getAbsDoseThreshold(final Double doseQuantile) {
    if (doseQuantile.equals(cachedDoseQuantile)) {
      return cachedAbsDoseThreshold;
    }

    cachedDoseQuantile = doseQuantile;
    cachedAvgDoseThreshold = null;
    cachedDoseContrast = null;
    cachedAbsDoseThreshold = Double.POSITIVE_INFINITY;
    cachedVoxelsAboveThreshold = 0;

    Double doseCutoff = (1 - doseQuantile) * totalDose;
    Double doseSeen = 0.;

    Boolean thresholdFound = false;

    for (Map.Entry<Double, Integer> e : voxelDoses.entrySet()) {
      if (thresholdFound) {
        cachedVoxelsAboveThreshold += e.getValue();
      } else {
        if (doseSeen < doseCutoff) {
          doseSeen += e.getKey() * e.getValue();
        }
        if (doseSeen >= doseCutoff) {
          cachedAbsDoseThreshold = e.getKey();
          thresholdFound = true;
        }
      }
    }

    return cachedAbsDoseThreshold;
  }

  public Double getAvgDoseThreshold(final Double doseQuantile) {
    if (!doseQuantile.equals(cachedDoseQuantile)) {
      getAbsDoseThreshold(doseQuantile);
    }
    if (cachedAvgDoseThreshold == null) {
      cachedAvgDoseThreshold = doseQuantile * totalDose
          / cachedVoxelsAboveThreshold;
    }
    return cachedAvgDoseThreshold;
  }

  /**
   * Returns the maximum encountered dose at any crystal voxel.
   * 
   * @return
   *         dose value in MGy.
   */
  public Double getMaxDose() {
    return voxelDoses.lastKey();
  }

  /**
   * Returns the total absorbed dose over all crystal voxels.
   * 
   * @return
   *         dose value in MGy.
   */
  public Double getTotalDose() {
    return totalDose;
  }

  public Double getDoseContrast(final Double doseQuantile) {
    if (doseQuantile.equals(cachedDoseQuantile)
        && (cachedDoseContrast != null)) {
      return cachedDoseContrast;
    }
    cachedDoseContrast = getMaxDose() / getAvgDoseThreshold(doseQuantile);
    return cachedDoseContrast;
  }

  /**
   * Returns the fraction of the crystal volume that has been illuminated.
   *
   * @return
   *         fraction between 0 and 1.
   */
  public Double getUsedVolumeFraction() {
    return usedVolumeFraction;
  }

  public Double getAbsEnergyTotal() {
    return totalAbsorbedEnergy;
  }

  public Double getWedgeElastic() {
    return wedgeElastic;
  }

  public Double getDoseInefficiency() {
    return doseInefficiency;
  }
}
