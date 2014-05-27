package se.raddo.raddose3D;

import java.util.Map;

/**
 * Debugging output module that only writes event notifications to its
 * {@link Writer}.
 */

public class OutputEventObserver implements Output {
  /** Writer to write notifications to. */
  private final Writer w;
  /** Closing message to be written upon close()-call. */
  private final String eof;

  /**
   * Generic property constructor for EventObserver output class.
   * Extracts all required information from a Map data structure.
   * *
   * Used properties:
   * OUTPUT_WRITER - writer class to which output will be directed.
   * OUTPUT_TEXT - message that should be written upon close()-call.
   * 
   * @param properties
   *          Map of type <Object, Object> that contains all output properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Output} class.
   */
  public OutputEventObserver(final Map<Object, Object> properties) {
    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputEventObserver: ");
    a.checkIsClass(properties.get(Output.OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) properties.get(Output.OUTPUT_WRITER);

    if (properties.get(Output.OUTPUT_TEXT) instanceof String) {
      eof = (String) properties.get(Output.OUTPUT_TEXT);
    } else {
      eof = "";
    }
  }

  @Override
  public void publishCrystal(final Crystal c) {
    w.write("Observed new Crystal (" + c + ").\n");
  }

  @Override
  public void publishBeam(final Beam b) {
    w.write("Observed new Beam (" + b + ").\n");
  }

  @Override
  public void publishWedge(final Wedge wdg) {
    w.write("Observed new Wedge (" + wdg + ").\n");
  }

  @Override
  public void close() {
    w.write(eof);
    w.close();
  }
}
