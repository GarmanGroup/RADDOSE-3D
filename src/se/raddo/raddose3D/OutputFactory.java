package se.raddo.raddose3D;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * The OutputFactory class can be used to instantiate Output type classes.
 * 
 * @author Markus Gerstel
 */
public class OutputFactory {

  /**
   * creates and returns an Output type object.
   * 
   * @param outputName
   *          the name of the output class requested.
   * @param properties
   *          a Map containing the complete list of output properties.
   *          Different output types may require a different set of specified
   *          properties, but at least one Writer will certainly be
   *          required. Keys of the Map structure are usually determined by the
   *          constants defined in {@link Output}, but third party Output
   *          implementations may have their own key set.
   *          Check the corresponding output class for details.
   *          The newly created object should not keep any references to this
   *          Map after object creation.
   * @return
   *         the requested Output type object
   */
  public Output createOutput(final String outputName,
      final Map<Object, Object> properties) {

    // 1. Do some sanity checks on the passed parameters

    if (outputName == null) {
      throw new RuntimeException("OutputFactory: outputName set to null");
    }
    if (properties == null) {
      throw new RuntimeException("OutputFactory: properties set to null");
    }

    String trimmedOutputName = outputName.trim();
    if (trimmedOutputName.equals("")) {
      throw new RuntimeException("OutputFactory: outputName is empty");
    }

    // 2. Construct the class name of the requested crystal type

    String outputClassName, alternativeOutputClassName;

    if (trimmedOutputName.indexOf(".") == -1) {
      outputClassName = "Output"
          .concat(trimmedOutputName.substring(0, 1).toUpperCase())
          .concat(trimmedOutputName.substring(1).toLowerCase());

      alternativeOutputClassName = "Output"
          .concat(trimmedOutputName);

      outputClassName = "se.raddo.raddose3D."
          .concat(outputClassName);
      alternativeOutputClassName = "se.raddo.raddose3D."
          .concat(alternativeOutputClassName);
    } else {
      outputClassName = trimmedOutputName;
      alternativeOutputClassName = trimmedOutputName;
    }

    // 3. Try to find that class either by the preferred or
    //    - alternatively - by the original capitalization

    Class<?> outputClass;
    try {
      outputClass = Class.forName(outputClassName);
    } catch (NoClassDefFoundError e1) {
      // Class not available although it has been seen at compile time
      // might be caused by different capitalization. Try alternative name:
      try {
        outputClass = Class.forName(alternativeOutputClassName);
        outputClassName = alternativeOutputClassName;
      } catch (ClassNotFoundException e2) {
        throw new RuntimeException("Could not initialize output of type "
            + outputName + ": Class " + outputClassName
            + " not found.", e1);
      }
    } catch (ClassNotFoundException e1) {
      // Class not available and has not been seen at compile time.
      // Try alternative name:
      try {
        outputClass = Class.forName(alternativeOutputClassName);
        outputClassName = alternativeOutputClassName;
      } catch (ClassNotFoundException e2) {
        throw new RuntimeException("Could not initialize output of type "
            + outputName + ": Class " + outputClassName
            + " not found.", e1);
      }
    }

    // 4. A class has been found. Check that it is a subclass of Output.

    if (!Output.class.isAssignableFrom(outputClass)) {
      throw new RuntimeException("Could not initialize output of type "
          + outputName + ": Class " + outputClassName
          + " is not an implementation of Output.");
    }

    // 5. Find the constructor that accepts the property Map data structure.

    Constructor<?> outputConstructor;
    try {
      outputConstructor = outputClass.getConstructor(Map.class);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException("Error initializing output of type "
          + outputName + ": Class " + outputClassName
          + " does not have a property constructor.", e);
    }

    // 6. Invoke the constructor and create the crystal object. Voila.

    try {
      return (Output) outputConstructor.newInstance(properties);
    } catch (InstantiationException e) {
      throw new RuntimeException("Error during output instantiation of "
          + outputClassName + ": " + e.getCause().getMessage(), e.getCause());
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Error during output creation of "
          + outputClassName + ": Illegal access exception", e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException("Error during output invocation of "
          + outputClassName + ": " + e.getCause().getMessage(), e.getCause());
    }
  }

  /**
   * creates and returns an Output type object with only a single {@link Writer}
   * parameter.
   * 
   * @param outputName
   *          the name of the output class requested.
   * @param writerObject
   *          a Writer object to which all output will be directed.
   *          Will be passed to Output object constructor as OUTPUT_WRITER
   *          property.
   * @return
   *         the requested Output type object
   */
  public Output createOutputSimple(final String outputName,
      final Writer writerObject) {
    HashMap<Object, Object> properties =
        new HashMap<Object, Object>();
    properties.put(Output.OUTPUT_WRITER, writerObject);

    return createOutput(outputName, properties);
  }
}
