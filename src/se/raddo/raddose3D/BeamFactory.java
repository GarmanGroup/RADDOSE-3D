package se.raddo.raddose3D;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * The BeamFactory class sits between the parser and the actual beam classes. It
 * allows easy testing of the parser and extensibility for new beam types.
 */
public class BeamFactory {

  /**
   * creates and returns different Beam type objects.
   * 
   * @param beamName
   *          the name of the beam class requested.
   * @param properties
   *          a Map containing the complete list of beam properties. Each beam
   *          type may require a different set of specified properties, but
   *          flux and energy etc. will certainly be required. Keys of the Map
   *          structure are usually determined by the constants defined in
   *          {@link Beam}, but third party Beam implementations may have their
   *          own key set. Check the corresponding beam class for details.
   *          The newly created object should not keep any references to this
   *          Map after object creation.
   * @return
   *         the requested Beam type object
   * @throws IllegalArgumentException
   *           the passed parameters are invalid
   * @throws BeamFactoryException
   *           the requested beam class could not be initialized
   */
  @SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.NPathComplexity" })
  public Beam createBeam(final String beamName,
      final Map<Object, Object> properties)
      throws IllegalArgumentException, BeamFactoryException {

    // 1. Do some sanity checks on the passed parameters

    if (beamName == null) {
      throw new IllegalArgumentException("BeamFactory: beamName set to null");
    }
    if (properties == null) {
      throw new IllegalArgumentException("BeamFactory: properties set to null");
    }

    String trimmedBeamName = beamName.trim();
    if ("".equals(trimmedBeamName)) {
      throw new IllegalArgumentException("BeamFactory: beamName is empty");
    }

    // 2. Construct the class name of the requested beam type

    String beamClassName, alternativeBeamClassName;

    if (trimmedBeamName.indexOf('.') == -1) {
      beamClassName = "Beam"
          .concat(trimmedBeamName.substring(0, 1).toUpperCase())
          .concat(trimmedBeamName.substring(1).toLowerCase());

      alternativeBeamClassName = "Beam"
          .concat(trimmedBeamName);

      beamClassName = "se.raddo.raddose3D."
          .concat(beamClassName);
      alternativeBeamClassName = "se.raddo.raddose3D."
          .concat(alternativeBeamClassName);
    } else {
      beamClassName = trimmedBeamName;
      alternativeBeamClassName = trimmedBeamName;
    }

    // 3. Try to find that class either by the preferred or
    //    - alternatively - by the original capitalization

    Class<?> beamClass;
    try {
      beamClass = Class.forName(beamClassName);
    } catch (ClassNotFoundException e1) {
      try {
        beamClass = Class.forName(alternativeBeamClassName);
        beamClassName = alternativeBeamClassName;
      } catch (ClassNotFoundException e2) {
        throw new BeamFactoryException("Could not initialize beam of type "
            + beamName + ": Class " + beamClassName
            + " not found.", e1); // NOPMD - Stack trace is dropped on purpose
      }
    }

    // 4. A class has been found. Check that it is an implementation of Beam.

    if (!Beam.class.isAssignableFrom(beamClass)) {
      throw new BeamFactoryException("Could not initialize beam of type "
          + beamName + ": Class " + beamClassName
          + " is not an implementation of Beam.");
    }

    // 5. Find the constructor that accepts the property Map data structure.

    Constructor<?> beamConstructor;
    try {
      beamConstructor = beamClass.getConstructor(Map.class);
    } catch (NoSuchMethodException e) {
      throw new BeamFactoryException("Error initializing beam of type "
          + beamName + ": Class " + beamClassName
          + " does not have a property constructor.", e);
    }

    // 6. Invoke the constructor and create the beam object. Voila.

    try {
      return (Beam) beamConstructor.newInstance(properties);
    } catch (InstantiationException e) {
      throw new BeamFactoryException("Error during beam instantiation of "
          + beamClassName + ": " + e.getCause().getMessage(), e);
    } catch (IllegalAccessException e) {
      throw new BeamFactoryException("Error during beam creation of "
          + beamClassName + ": Illegal access exception", e);
    } catch (InvocationTargetException e) {
      throw new BeamFactoryException("Error during beam invocation of "
          + beamClassName + ": " + e.getCause().getMessage(), e);
    }
  }

  /**
   * Exception for when the requested Beam type class could not be instantiated.
   */
  private static class BeamFactoryException extends RuntimeException {
    /**
     * Unique exception serial.
     */
    private static final long serialVersionUID = 6831150255787484147L;

    /**
     * Basic exception constructor. Takes only a string.
     * 
     * @param string
     *          Reason why the exception was thrown.
     */
    public BeamFactoryException(final String string) {
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
    public BeamFactoryException(final String string, final Throwable e) {
      super(string, e);
    }
  }
}
