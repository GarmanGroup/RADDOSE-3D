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
 */
public class ClassFactory {

  /**
   * Creates and returns different objects of a defined type by name.
   * 
   * @param <T>
   *          the type of the produced class. Does not need to be specified by
   *          the caller, but will be automatically set by the producedClass
   *          parameter.
   * @param producedClass
   *          the type of class requested. e.g. Crystal.class
   *          This is required for type-checking and the final cast of the newly
   *          created object.
   * @param name
   *          the name of the class requested.
   * @param properties
   *          a Map containing the complete list of object properties.
   *          Different object types may require a different set of specified
   *          properties. Keys of the Map structure are usually determined by
   *          the constants defined in the relevant class (e.g. {@link Beam} or
   *          {@link Crystal}), but external implementations may have their own
   *          key set. Check the corresponding classes for details.
   *          E.g. crystal type classes will certainly require dimension and
   *          resolution etc.
   *          The newly created object should not keep any references to this
   *          Map after object creation.
   * @return
   *         the requested object of type producedClass
   * @throws IllegalArgumentException
   *           the passed parameters are invalid
   * @throws ClassFactoryException
   *           the requested class could not be initialized
   */
  public <T> T createObject(final Class<T> producedClass,
      final String name, final Map<Object, Object> properties)
      throws IllegalArgumentException, ClassFactoryException {

    // 1. Do some sanity checks on the passed parameters
    parameterChecks(producedClass, name, properties);

    // 2. Construct the full class name of the requested object
    String objectClassName = name;
    if (objectClassName.indexOf('.') == -1) {
      // Only short name was specified.
      // Add the full name of the general class as prefix
      objectClassName = producedClass.getName().concat(objectClassName);
    } // otherwise: The full object path is assumed.

    // 3. Try to find the class
    Class<?> objectClass;
    try {
      objectClass = Class.forName(objectClassName);
    } catch (ClassNotFoundException e) {
      throw new ClassFactoryException(
          "Could not initialize object of type "
              + producedClass.getName() + ": Class " + objectClassName
              + " not found.", e);
    }

    // 4. A class has been found.
    // Check that it is a subclass of the requested type.
    if (!producedClass.isAssignableFrom(objectClass)) {
      throw new ClassFactoryException("Could not initialize object of type "
          + producedClass.getName() + ": Class " + objectClassName
          + " is not a subclass of " + producedClass.getName() + ".");
    }

    // 5. Find the constructor that accepts the property Map data structure.
    Constructor<?> objectConstructor;
    try {
      objectConstructor = objectClass.getConstructor(Map.class);
    } catch (NoSuchMethodException e) {
      throw new ClassFactoryException("Error initializing object of type "
          + producedClass.getName() + ": Class " + objectClassName
          + " does not have a property constructor.", e);
    }

    // 6. Invoke the constructor and create the object. Voila.
    try {
      return producedClass.cast(objectConstructor.newInstance(properties));
    } catch (InstantiationException e) {
      throw new ClassFactoryException(
          "Error during crystal instantiation of "
              + objectClassName + ": " + e.getCause().getMessage(), e);
    } catch (IllegalAccessException e) {
      throw new ClassFactoryException("Error during creation of "
          + objectClassName + ": Illegal access exception", e);
    } catch (InvocationTargetException e) {
      throw new ClassFactoryException("Error during invocation of "
          + objectClassName + ": " + e.getCause().getMessage(), e);
    }
  }

  /**
   * Run simple checks on the passed parameters.
   * 
   * @param factoryClass
   *          the type of class requested.
   * @param name
   *          the name of the class requested.
   * @param properties
   *          a Map containing the complete list of object properties.
   * @throws IllegalArgumentException
   *           the passed parameters are invalid
   */
  private void parameterChecks(final Class<?> factoryClass,
      final String name, final Map<Object, Object> properties)
      throws IllegalArgumentException {

    if (factoryClass == null) {
      throw new IllegalArgumentException(
          "ClassFactory: target class not defined");
    }

    if (name == null) {
      throw new IllegalArgumentException(
          "ClassFactory: object name missing");
    }

    if (properties == null) {
      throw new IllegalArgumentException(
          "ClassFactory: properties set to null");
    }

    if ("".equals(name)) {
      throw new IllegalArgumentException(
          "ClassFactory: object name not set");
    }

    if (!name.equals(name.trim())) {
      throw new IllegalArgumentException(
          "ClassFactory: object name contains leading/trailing spaces");
    }
  }
}
