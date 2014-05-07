package se.raddo.raddose3D;

/**
 * Exception used by the ClassFactory for cases when the requested class could
 * not be instantiated.
 */
public class ClassFactoryException extends RuntimeException {
  /**
   * Unique exception serial.
   */
  private static final long serialVersionUID = -9115668539037904958L;

  /**
   * Basic exception constructor. Takes only a string.
   * 
   * @param string
   *          Reason why the exception was thrown.
   */
  public ClassFactoryException(final String string) {
    super(string);
  }

  /**
   * Exception constructor taking a string and another exception holding a
   * stack trace.
   * 
   * @param string
   *          Reason why the exception was thrown.
   * @param e
   *          Original exception holding the stack trace.
   */
  public ClassFactoryException(final String string, final Throwable e) {
    super(string, e);
  }
}
