package se.raddo.raddose3D.server;

import se.raddo.raddose3D.Writer;

/**
 * Dummy writer implementation. Takes all input an discards it.
 * (/dev/null)
 */
public class WriterDummy implements Writer {

  @Override
  public void write(final String s) {
    // Dummy writer does not write.
  }

  @Override
  public void write(final StringBuffer b) {
    // Dummy writer does not write.
  }

  @Override
  public void close() {
    // Dummy writer does not close.
  }
}
