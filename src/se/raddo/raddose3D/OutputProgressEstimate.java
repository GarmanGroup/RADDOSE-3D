package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO: Comment on what this does and where it is used
 * Underlying model: Time / TTick = xa + (CrystalVoxels + xb Sum_WedgeSlices).
 * This code reports CrystalVoxels and Sum_WedgeSlices.
 */
public class OutputProgressEstimate implements Output {
  /** Where output should be directed to. (optional) */
  private final Writer          w;

  /** The total number of voxels in the last seen crystal. */
  private Long                  crystalVoxels     = 0L;
  /** The total number of wedge exposures of the current crystal so far. */
  private Long                  sumWedgeSlices    = 0L;
  /** A list of numbers of voxels for all seen crystals. */
  private final List<Long> crystalVoxelList  = new ArrayList<Long>();
  /** A list of numbers of wedge exposures for all seen crystals. */
  private final List<Long> sumWedgeSliceList = new ArrayList<Long>();

  /**
   * Generic property constructor for OutputProgressEstimate output class.
   * Extracts all required information from a Map data structure.
   * *
   * Used properties:
   * OUTPUT_WRITER - writer class to which output will be directed. (optional)
   * 
   * @param properties
   *          Map of type <Object, Object> that contains all output properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Output} class.
   */
  public OutputProgressEstimate(final Map<Object, Object> properties) {
    if (properties.get(Output.OUTPUT_WRITER) instanceof Writer) {
      w = (Writer) properties.get(Output.OUTPUT_WRITER);
    } else {
      w = null;
    }
  }

  @Override
  public void publishCrystal(final Crystal c) {
    int[] crystDim = c.getCrystSizeVoxels();
    if ((crystalVoxels > 0) || (sumWedgeSlices > 0)) {
      addEstimate(crystalVoxels, sumWedgeSlices);
    }
    crystalVoxels = (long) (crystDim[0] * crystDim[1] * crystDim[2]);
    sumWedgeSlices = 0L;
  }

  @Override
  public void publishBeam(final Beam b) {
    // No implementation needed.
  }

  @Override
  public void publishWedge(final Wedge wdg) {
    Double startAngle = wdg.getStartAng();
    Double endAngle = wdg.getEndAng();
    Double angularResolution = wdg.getAngRes();

    if (Math.abs(startAngle - endAngle) < angularResolution) {
      sumWedgeSlices += 100;
    } else {
      sumWedgeSlices += (long)
          (Math.abs(endAngle - startAngle) / angularResolution + 1);
    }
  }

  /**
   * Record key properties of a wedge exposure.
   * 
   * @param voxels
   *          number of voxels in the crystal.
   * @param slices
   *          number of individual exposures.
   */
  private void addEstimate(final Long voxels, final Long slices) {
    if (w != null) {
      w.write(String.format(
          "Progress Estimate Information: CrystalVoxels=%d WedgeSlices=%d\n",
          voxels, slices));
    }
    crystalVoxelList.add(voxels);
    sumWedgeSliceList.add(slices);
  }

  @Override
  public void close() {
    addEstimate(crystalVoxels, sumWedgeSlices);
    if (w != null) {
      w.close();
    }
  }

  /**
   * Returns a list containing the numbers of voxels of each seen crystal.
   * 
   * @return
   *         Ordered list of numbers of voxels.
   */
  public List<Long> getCrystalVoxelList() {
    return crystalVoxelList;
  }

  /**
   * Returns a list containing the number of wedge exposures for each seen
   * crystal.
   * 
   * @return
   *         An ordered list containing the number of wedge exposures for each
   *         seen crystal.
   */
  public List<Long> getWedgeSliceList() {
    return sumWedgeSliceList;
  }
}
