package se.raddo.raddose3D;

/**
 * WriterString writes all received data to a string.
 */
public class WriterString extends Writer {
  /** All received data is kept in a Stringbuffer. */
  private final StringBuffer data     = new StringBuffer();
  /** After close() is called further data results in RuntimeExceptions. */
  private Boolean            readonly = false;

  @Override
  public void write(final String s) {
    if (readonly) {
      throw new IllegalStateException("Writer has been closed");
    }
    data.append(s);
  }

  @Override
  public void write(final StringBuffer b) {
    if (readonly) {
      throw new IllegalStateException("Writer has been closed");
    }
    data.append(b);
  }

  @Override
  public void close() {
    readonly = true;
  }

  /**
   * Retrieve all encountered data.
   * 
   * @return
   *         one String containing all encountered data
   */
  public String getDataString() {
    return new String(data);
  }

  /**
   * Retrieve all encountered data.
   * 
   * @return
   *         StringBuffer object containing all encountered data
   */
  public StringBuffer getDataStringBuffer() {
    return data;
  }
}
