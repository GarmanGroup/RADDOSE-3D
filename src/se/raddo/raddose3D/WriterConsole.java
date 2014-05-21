package se.raddo.raddose3D;

/**
 * WriterConsole writes all received data to STDOUT.
 */
public class WriterConsole implements Writer {
  /** Text to be written upon close()-call. */
  private final String closingMsg;

  /**
   * Empty constructor, no closing message.
   */
  public WriterConsole() {
    this("");
  }

  /**
   * Standard constructor with a specified closing message.
   * 
   * @param closingMessage
   *          This string will be written when the close()-method is called.
   */
  public WriterConsole(final String closingMessage) {
    closingMsg = closingMessage;
  }

  @Override
  public void write(final String s) {
    System.out.print(s);
  }

  @Override
  public void write(final StringBuffer b) {
    System.out.print(b);
  }

  @Override
  public void close() {
    System.out.print(closingMsg);
  }
}
