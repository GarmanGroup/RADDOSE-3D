package se.raddo.raddose3D;

import java.io.IOException;

/**
 * WriterCompress applies ZIP compression to any output, and passes it on to
 * another Writer instance.
 */
public class WriterCompress extends Writer {
  /** The Writer class that output should be directed to. */
  private final Writer output;

  /**
   * Compress any output and send it on to another Writer instance.
   * 
   * @param w
   *          Writer instance, where the compressed output stream should end up.
   */
  public WriterCompress(final Writer w) {
    output = w;
  }

  @Override
  public void write(final String s) {
    // TODO Auto-generated method stub
    output.write(s);
  }

  @Override
  public void write(final StringBuffer b) {
    // TODO Auto-generated method stub
    output.write(b);
  }

  @Override
  public void flush() throws IOException {
    output.flush();
  }

  @Override
  public void close() {
    // TODO Auto-generated method stub
    output.close();
  }
}
