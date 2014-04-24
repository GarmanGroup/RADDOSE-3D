package se.raddo.raddose3D;

/**
 * Forwards all received input to multiple {@link Writer} instances.
 * 
 * @author Markus Gerstel
 */
public class WriterMultiple implements Writer {
  /** List of Writer instances. */
  private final Iterable<Writer> children;

  /**
   * Constructor, takes a list of Writer instances.
   * 
   * @param instances
   *          e.g. a List or Vector of Writers. Can also be constructed by
   *          Arrays.asList(...)
   */
  public WriterMultiple(final Iterable<Writer> instances) {
    children = instances;
  }

  @Override
  public void write(final String s) {
    for (Writer w : children) {
      w.write(s);
    }
  }

  @Override
  public void write(final StringBuffer b) {
    for (Writer w : children) {
      w.write(b);
    }
  }

  @Override
  public void close() {
    for (Writer w : children) {
      w.close();
    }
  }
}
