package se.raddo.raddose3D;

/**
 * Exception for errors in the input data.
 * 
 * @author Markus Gerstel
 **/

public class InputException extends Exception {

  /** Unique exception identifier. */
  private static final long serialVersionUID = -7693051764589056723L;

  /** Reason given for the exception. */
  private final String      reason;

  /**
   * creates an exception for handling errors during configuration acquisition.
   * This exception type can be created by Input classes when requested to pass
   * objects to an Initializer.
   * 
   * @param err
   *          A description of the error.
   */
  public InputException(final String err) {
    System.err.println("InputException: " + err);
    reason = err;
  }

  @Override
  public String toString() {
    return "InputException: " + reason;
  }
}
