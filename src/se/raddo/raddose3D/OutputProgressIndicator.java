package se.raddo.raddose3D;

import java.util.Map;

/**
 * A simple progress indicator for lengthy simulations.
 */
public class OutputProgressIndicator implements Output, ExposeObserver {
  /** Where output should be directed to. */
  private final Writer w;

  /** Progress indicator constant. */
  private static final int PERCENT_100 = 100,
                                       PERCENT_80 = 80,
                                       PERCENT_60 = 60,
                                       PERCENT_40 = 40,
                                       PERCENT_20 = 20;
  /** Percent steps between dot outputs. */
  private static final int DOTINTERVAL = 4;

  /** Total number of images in the current wedge. */
  private int              imageCount;
  /** Currently indicated progress of the exposure. */
  private int              wedgeProgress;

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
  public OutputProgressIndicator(final Map<Object, Object> properties) {
    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputProgressIndicator: ");
    a.checkIsClass(properties.get(Output.OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) properties.get(Output.OUTPUT_WRITER);
  }

  @Override
  public void publishCrystal(final Crystal c) {
    c.addObserver(this);
  }

  @Override
  public void publishBeam(final Beam b) {
    // No implementation required
  }

  @Override
  public void publishWedge(final Wedge wdg) {
    // No implementation required
  }

  @Override
  public void close() {
    // No implementation required
  }

  @Override
  public void register(final Crystal c) {
    // No implementation required
  }

  @Override
  public void exposureStart(final int upcomingImageCount) {
    w.write("Exposing wedge: [ 0%");
    imageCount = upcomingImageCount;
    wedgeProgress = 0;
  }

  @Override
  public void exposureObservation(final int wedgeImage, final int i,
      final int j, final int k,
      final double addedDose, final double totalDose, final double fluence,
      final double relativeDiffractionEfficiency, final double absorbedEnergy,
      final double elastic, final double anglecount) {
    // No implementation required
  }

  @Override
  public void imageComplete(final int image, final double angle) {
    while (PERCENT_100 * (image + 1) / imageCount > wedgeProgress) {
      wedgeProgress++;

      if (wedgeProgress % DOTINTERVAL == 0) {
        w.write(".");
      }
      switch (wedgeProgress) {
        case PERCENT_20:
          w.write("20%");
          break;
        case PERCENT_40:
          w.write("40%");
          break;
        case PERCENT_60:
          w.write("60%");
          break;
        case PERCENT_80:
          w.write("80%");
          break;
        case PERCENT_100:
          w.write("100%");
          break;
        default:
          break;
      }
    }
  }

  @Override
  public void summaryObservation(final int i, final int j, final int k,
      final double totalDose, final double voxelMassKg) {
    // No implementation required
  }

  @Override
  public void exposureComplete() {
    w.write(" ]\n");
  }
}
