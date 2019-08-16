package se.raddo.raddose3D;

import java.util.Map;

public class OutputVoxelFluences implements Output{
  /** Where output should be directed to. */
  private final Writer w;

  /**
   * Private reference to the last seen crystal which will be inspected after
   * all exposure events on close().
   */
  private Crystal      crystal;
  private Wedge        wedge;
  private ExposureSummary      expSummary;

  /**
   * Generic property constructor for OutputFinalDoseStateCSV output class.
   * Extracts all required information from a Map data structure.
   * *
   * Used properties:
   * OUTPUT_WRITER - writer class to which output will be directed.
   * 
   * @param properties
   *          Map of type <Object, Object> that contains all output properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Output} class.
   */
  public OutputVoxelFluences(final Map<Object, Object> properties) {
    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputVoxelStateCSV: ");
    a.checkIsClass(properties.get(Output.OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) properties.get(Output.OUTPUT_WRITER);
  }

  @Override
  public void publishCrystal(final Crystal c) {
    crystal = c;
    expSummary = c.getExposureSummary();
  }

  @Override
  public void publishWedge(final Wedge wdg) {
    wedge = wdg;
  }

  @Override
  public void publishBeam(final Beam b) {
    // No implementation needed.
  }

  @Override
  public void close() {
    if (crystal == null) {
      System.err
          .println("OutputVoxelFluences: No crystal object has been seen.");
      w.write("OutputVoxelFluences: No crystal object has been seen.");
      w.close();
      return;
    }
    
    //write first row
   // double totFluence = 0;
    
    //write the names
    for (int i = 0; i < crystal.getCrystSizeVoxels()[0]; i++) {
      for (int j = 0; j < crystal.getCrystSizeVoxels()[1]; j++) {
        for (int k = 0; k < crystal.getCrystSizeVoxels()[2]; k++) {
          if (crystal.isCrystalAt(i, j, k)) {
            w.write(((float) crystal.getCrystCoord(i, j, k)[0]) + "_"
                + ((float) crystal.getCrystCoord(i, j, k)[1]) + "_"
                + ((float) crystal.getCrystCoord(i, j, k)[2]) + ",");
          }
         // totFluence += (float) crystal.getFluence(i, j, k);
        }
      }
    }
    w.write("\n");
    double[][][][] voxFluences = expSummary.getVoxelFluences();
    for (int l = 0; l < crystal.getNumImages(wedge);l++) {
      for (int i = 0; i < crystal.getCrystSizeVoxels()[0]; i++) {
        for (int j = 0; j < crystal.getCrystSizeVoxels()[1]; j++) {
          for (int k = 0; k < crystal.getCrystSizeVoxels()[2]; k++) {
            if (crystal.isCrystalAt(i, j, k)) {
            w.write(((float) voxFluences[i][j][k][l]) + ",");
            }
          }
        }
      }
      w.write("\n");
    }
    
    
    crystal = null;
    wedge = null;
    w.close();
  }
}
