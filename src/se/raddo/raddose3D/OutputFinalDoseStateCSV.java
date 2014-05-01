package se.raddo.raddose3D;

import java.util.Map;

public class OutputFinalDoseStateCSV implements Output {
  /** Where output should be directed to. */
  private final Writer w;

  /**
   * Private reference to the last seen crystal which will be inspected after
   * all exposure events on close().
   */
  private Crystal      crystal;

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
  public OutputFinalDoseStateCSV(final Map<Object, Object> properties) {
    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputFinalDoseStateCSV: ");
    a.checkIsClass(properties.get(Output.OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) properties.get(Output.OUTPUT_WRITER);
  }

  @Override
  public void publishCrystal(final Crystal c) {
    crystal = c;
  }

  @Override
  public void publishWedge(final Wedge wdg) {
    // No implementation needed.
  }

  @Override
  public void publishBeam(final Beam b) {
    // No implementation needed.
  }

  @Override
  public void close() {
    if (crystal == null) {
      System.err
          .println("OutputFinalDoseStateCSV: No crystal object has been seen.");
      w.write("OutputFinalDoseStateCSV: No crystal object has been seen.");
      w.close();
      return;
    }

    for (int i = 0; i < crystal.getCrystSizeVoxels()[0]; i++) {
      for (int j = 0; j < crystal.getCrystSizeVoxels()[1]; j++) {
        for (int k = 0; k < crystal.getCrystSizeVoxels()[2]; k++) {
          w.write(((float) crystal.getCrystCoord(i, j, k)[0]) + ","
              + ((float) crystal.getCrystCoord(i, j, k)[1]) + ","
              + ((float) crystal.getCrystCoord(i, j, k)[2]) + ",");

          float dose = (float) crystal.getDose(i, j, k);
          if (dose <= Float.MIN_VALUE) {
            w.write("0,");
          } else {
            w.write(dose + ",");
          }

          float fluence = (float) crystal.getFluence(i, j, k);
          if (fluence <= Float.MIN_VALUE) {
            w.write("0,");
          } else {
            w.write(fluence + ",");
          }

          float elastic = (float) crystal.getElastic(i, j, k);
          if (elastic <= Float.MIN_VALUE) {
            w.write("0\n");
          } else {
            w.write(elastic + "\n");
          }
        }
      }
    }

    crystal = null;
    w.close();
  }
}
