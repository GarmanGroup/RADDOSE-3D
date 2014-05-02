package se.raddo.raddose3D;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * The ClassFactory provides the ability to build up the initial network of
 * interacting experimental classes. It allows the creation of Beam, Crystal,
 * Output, etc. classes by name. The exact class name does not have to be known
 * at compile time.
 * The class constructor has to be known however. All classes created by
 * ClassFactory must offer a constructor taking a single Map<Object, Object>
 * parameter.
 * 
 * @param <E>
 *          The type of class to be generated. E can be a class or an interface.
 */
public class ClassFactory<E> {

  /**
   * http://stackoverflow.com/questions/6633317/checking-generic-type
   */
  private final Class<E> realType;

  /**
   * new MyClass<MyObject>(MyObject.class)
   * 
   * @param realType
   */
  public ClassFactory(Class<E> realType) {
    this.realType = realType;
    System.out.println(realType.getName());
  }

  /**
   * creates and returns different objects of type E by name
   * 
   * @param name
   *          the name of the class requested.
   * @param properties
   *          a Map containing the complete list of crystal properties.
   *          Different crystal types may require a different set of specified
   *          properties, but dimension and resolution etc. will certainly be
   *          required. Keys of the Map structure are usually determined by the
   *          constants defined in the class E (e.g. {@link Beam} or
   *          {@link Crystal}), but external implementations may have their own
   *          key set. Check the corresponding classes for details.
   *          The newly created object should not keep any references to this
   *          Map after object creation.
   * @return
   *         the requested object of type E
   * @throws IllegalArgumentException
   *           the passed parameters are invalid
   * @throws ClassFactoryException
   *           the requested class could not be initialized
   */
  @SuppressWarnings({
      "PMD.CyclomaticComplexity",
      "PMD.NPathComplexity",
      "PMD.PreserveStackTrace" })
  public E createObject(final String name,
      final Map<Object, Object> properties)
      throws IllegalArgumentException, ClassFactoryException {

    // 1. Do some sanity checks on the passed parameters

    if (name == null) {
      throw new IllegalArgumentException(
          "ClassFactory: object name missing");
    }
    if (properties == null) {
      throw new IllegalArgumentException(
          "ClassFactory: properties set to null");
    }

    String trimmedCrystalName = name.trim();
    if (trimmedCrystalName.equals("")) {
      throw new IllegalArgumentException(
          "ClassFactory: crystalName is empty");
    }

    // 2. Construct the class name of the requested crystal type

    String objectClassName, alternativeObjectClassName;

    if (trimmedCrystalName.indexOf('.') == -1) {
      objectClassName = "Crystal"
          .concat(trimmedCrystalName.substring(0, 1).toUpperCase())
          .concat(trimmedCrystalName.substring(1).toLowerCase());

      alternativeObjectClassName = "Crystal"
          .concat(trimmedCrystalName);

      objectClassName = "se.raddo.raddose3D."
          .concat(objectClassName);
      alternativeObjectClassName = "se.raddo.raddose3D."
          .concat(alternativeObjectClassName);
    } else {
      objectClassName = trimmedCrystalName;
      alternativeObjectClassName = trimmedCrystalName;
    }

    // 3. Try to find that class either by the preferred or
    //    - alternatively - by the original capitalization

    Class<?> crystalClass;
    try {
      crystalClass = Class.forName(objectClassName);
    } catch (ClassNotFoundException e1) {
      try {
        crystalClass = Class.forName(alternativeObjectClassName);
        objectClassName = alternativeObjectClassName;
      } catch (ClassNotFoundException e2) {
        throw new ClassFactoryException(
            "Could not initialize crystal of type "
                + name + ": Class " + objectClassName
                + " not found.", e1);
      }
    }

    // 4. A class has been found. Check that it is a subclass of Crystal.

    if (!realType.isAssignableFrom(crystalClass)) {
      throw new ClassFactoryException("Could not initialize crystal of type "
          + name + ": Class " + objectClassName
          + " is not a subclass of Crystal.");
    }

    // 5. Find the constructor that accepts the property Map data structure.

    Constructor<?> objectConstructor;
    try {
      objectConstructor = crystalClass.getConstructor(Map.class);
    } catch (NoSuchMethodException e) {
      throw new ClassFactoryException("Error initializing crystal of type "
          + name + ": Class " + objectClassName
          + " does not have a property constructor.", e);
    }

    // 6. Invoke the constructor and create the crystal object. Voila.

    try {
      return (E) realType.cast(objectConstructor.newInstance(properties));
    } catch (InstantiationException e) {
      throw new ClassFactoryException(
          "Error during crystal instantiation of "
              + objectClassName + ": " + e.getCause().getMessage(),
          e.getCause());
    } catch (IllegalAccessException e) {
      throw new ClassFactoryException("Error during crystal creation of "
          + objectClassName + ": Illegal access exception", e);
    } catch (InvocationTargetException e) {
      throw new ClassFactoryException("Error during crystal invocation of "
          + objectClassName + ": " + e.getCause().getMessage(), e.getCause());
    }
  }

  /**
   * Exception for when the requested class could not be instantiated.
   */
  private static class ClassFactoryException extends RuntimeException {
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
}
