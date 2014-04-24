package se.raddo.raddose3D;

/**
 * Class that contains a number of validation functions. Objects can be tested
 * against null, objects can be tested whether they are instances of other
 * classes, etc.
 * If the validation fails the function will throw a RuntimeException with a
 * (hopefully) helpful error message.
 * 
 * @author Markus Gerstel
 */
public class Assertions {

  /**
   * Prefix used for any raised RuntimeExceptions.
   */
  private final String sourceMessage;

  /**
   * Default constructor with no custom exception prefix.
   */
  public Assertions() {
    sourceMessage = "";
  }

  /**
   * Constructor with a custom exception prefix.
   * 
   * @param exceptionPrefix
   *          A String that will be prefixed to any exceptions. May contain a
   *          helpful message to the user indicating where something went wrong.
   */
  public Assertions(final String exceptionPrefix) {
    sourceMessage = exceptionPrefix;
  }

  /**
   * Throw a runtime exception if a variable is set to null.
   * 
   * @param variable
   *          The variable to test.
   * @param errorMessage
   *          A helpful message that will be included in the runtime exception.
   */
  public void checkIsNotNull(final Object variable,
      final String errorMessage) {
    if (variable == null) {
      throw new RuntimeException(sourceMessage.concat(errorMessage));
    }
  }

  /**
   * Throw a runtime exception if a variable is not of a given type, or is set
   * to null.
   * 
   * @param variable
   *          The variable to test.
   * @param classToTest
   *          class that variable should be an instance of. (e.g: Double.class)
   * @param errorMessage
   *          A helpful message that will be included in the runtime exception.
   */
  public void checkIsClass(final Object variable, final Class<?> classToTest,
      final String errorMessage) {
    if ((variable == null)
        || (classToTest == null)
        || (!classToTest.isAssignableFrom(variable.getClass()))) {
      throw new RuntimeException(sourceMessage.concat(errorMessage));
    }
  }
}
