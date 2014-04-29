package se.raddo.raddose3D;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * The CrystalFactory class sits between the parser and the actual crystal
 * classes. It allows easy testing of the parser and extensibility for new
 * crystal types.
 * TODO: Merge BeamFactory, CrystalFactory, OutputFactory and CoefCalcFactory
 */
public class CrystalFactory {

  /**
   * creates and returns different Crystal type objects.
   * 
   * @param crystalName
   *          the name of the crystal class requested.
   * @param properties
   *          a Map containing the complete list of crystal properties.
   *          Different crystal types may require a different set of specified
   *          properties, but dimension and resolution etc. will certainly be
   *          required. Keys of the Map structure are usually determined by the
   *          constants defined in {@link Crystal}, but third party Crystal
   *          implementations may have their own key set.
   *          Check the corresponding crystal class for details.
   *          The newly created object should not keep any references to this
   *          Map after object creation.
   * @return
   *         the requested Crystal type object
   * @throws IllegalArgumentException
   *           the passed parameters are invalid
   * @throws CrystalFactoryException
   *           the requested crystal class could not be initialized
   */
  @SuppressWarnings({
      "PMD.CyclomaticComplexity",
      "PMD.NPathComplexity",
      "PMD.PreserveStackTrace" })
  public Crystal createCrystal(final String crystalName,
      final Map<Object, Object> properties)
      throws IllegalArgumentException, CrystalFactoryException {

    // 1. Do some sanity checks on the passed parameters

    if (crystalName == null) {
      throw new IllegalArgumentException(
          "CrystalFactory: crystalName set to null");
    }
    if (properties == null) {
      throw new IllegalArgumentException(
          "CrystalFactory: properties set to null");
    }

    String trimmedCrystalName = crystalName.trim();
    if (trimmedCrystalName.equals("")) {
      throw new IllegalArgumentException(
          "CrystalFactory: crystalName is empty");
    }

    // 2. Construct the class name of the requested crystal type

    String crystalClassName, alternativeCrystalClassName;

    if (trimmedCrystalName.indexOf('.') == -1) {
      crystalClassName = "Crystal"
          .concat(trimmedCrystalName.substring(0, 1).toUpperCase())
          .concat(trimmedCrystalName.substring(1).toLowerCase());

      alternativeCrystalClassName = "Crystal"
          .concat(trimmedCrystalName);

      crystalClassName = "se.raddo.raddose3D."
          .concat(crystalClassName);
      alternativeCrystalClassName = "se.raddo.raddose3D."
          .concat(alternativeCrystalClassName);
    } else {
      crystalClassName = trimmedCrystalName;
      alternativeCrystalClassName = trimmedCrystalName;
    }

    // 3. Try to find that class either by the preferred or
    //    - alternatively - by the original capitalization

    Class<?> crystalClass;
    try {
      crystalClass = Class.forName(crystalClassName);
    } catch (ClassNotFoundException e1) {
      try {
        crystalClass = Class.forName(alternativeCrystalClassName);
        crystalClassName = alternativeCrystalClassName;
      } catch (ClassNotFoundException e2) {
        throw new CrystalFactoryException(
            "Could not initialize crystal of type "
                + crystalName + ": Class " + crystalClassName
                + " not found.", e1);
      }
    }

    // 4. A class has been found. Check that it is a subclass of Crystal.

    if (!Crystal.class.isAssignableFrom(crystalClass)) {
      throw new CrystalFactoryException("Could not initialize crystal of type "
          + crystalName + ": Class " + crystalClassName
          + " is not a subclass of Crystal.");
    }

    // 5. Find the constructor that accepts the property Map data structure.

    Constructor<?> crystalConstructor;
    try {
      crystalConstructor = crystalClass.getConstructor(Map.class);
    } catch (NoSuchMethodException e) {
      throw new CrystalFactoryException("Error initializing crystal of type "
          + crystalName + ": Class " + crystalClassName
          + " does not have a property constructor.", e);
    }

    // 6. Invoke the constructor and create the crystal object. Voila.

    try {
      return (Crystal) crystalConstructor.newInstance(properties);
    } catch (InstantiationException e) {
      throw new CrystalFactoryException("Error during crystal instantiation of "
          + crystalClassName + ": " + e.getCause().getMessage(), e.getCause());
    } catch (IllegalAccessException e) {
      throw new CrystalFactoryException("Error during crystal creation of "
          + crystalClassName + ": Illegal access exception", e);
    } catch (InvocationTargetException e) {
      throw new CrystalFactoryException("Error during crystal invocation of "
          + crystalClassName + ": " + e.getCause().getMessage(), e.getCause());
    }
  }

  /**
   * Exception for when the requested Crystal type class could not be
   * instantiated.
   */
  private static class CrystalFactoryException extends RuntimeException {
    /**
     * Unique exception serial.
     */
    private static final long serialVersionUID = -8223644774855228211L;

    /**
     * Basic exception constructor. Takes only a string.
     * 
     * @param string
     *          Reason why the exception was thrown.
     */
    public CrystalFactoryException(final String string) {
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
    public CrystalFactoryException(final String string, final Throwable e) {
      super(string, e);
    }
  }
}
