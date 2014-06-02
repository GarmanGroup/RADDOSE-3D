package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

/**
 * Wedge Class contains all the information for the wedge.
 * It describes a single wedge of an experiment.
 */
public class Wedge {

  /** Possible Wedge properties, used as index in the properties map. */
  public enum WedgeProperties {
    /** Start and end angle of exposure in radians. */
    ANGLE_START, ANGLE_END,
    /** Angular exposure resolution in radians per image. */
    ANGULAR_RESOLUTION,
    /** Total exposure time of the wedge in seconds. */
    EXPOSURE_TIME,
    START_POSITION_X, START_POSITION_Y, START_POSITION_Z,
    /** Translation along the axis in distance per radian. */
    TRANSLATION_X, TRANSLATION_Y, TRANSLATION_Z,
    OFF_AXIS
  };

  /** Storage of all wedge properties. */
  private final Map<WedgeProperties, Double> properties;

  /**
   * Create a new Wedge class, storing experimental data collection strategy
   * parameters.
   * 
   * @param wedgeProperties
   *          A Map linking WedgeProperties to values. Some values may be
   *          initialized with default values if not set.
   */
  public Wedge(final Map<WedgeProperties, Double> wedgeProperties) {
    properties = new HashMap<WedgeProperties, Double>();

    /* Default values */
    properties.put(WedgeProperties.ANGULAR_RESOLUTION, Math.toRadians(2));
    properties.put(WedgeProperties.START_POSITION_X, 0d);
    properties.put(WedgeProperties.START_POSITION_Y, 0d);
    properties.put(WedgeProperties.START_POSITION_Z, 0d);
    properties.put(WedgeProperties.TRANSLATION_X, 0d);
    properties.put(WedgeProperties.TRANSLATION_Y, 0d);
    properties.put(WedgeProperties.TRANSLATION_Z, 0d);
    properties.put(WedgeProperties.OFF_AXIS, 0d);

    properties.putAll(wedgeProperties);
  }

  /**
   * Full constructor that can be used by the parser.
   * All angular arguments are in degrees.
   * 
   * @param angularResolution
   *          Angular exposure resolution in degrees.
   * @param startAngle
   *          Start angle of exposure in degrees.
   * @param endAngle
   *          End angle of exposure in degrees.
   * @param totalSecondsExposure
   * @param startXposition
   * @param startYposition
   * @param translationX
   *          translation on the X axis in distance per degree rotation.
   * @param translationY
   *          translation on the Y axis in distance per degree rotation.
   * @param translationZ
   *          translation on the Z axis in distance per degree rotation.
   * @param offAxisRotationUm
   */
  public Wedge(final Double angularResolution,
      final Double startAngle,
      final Double endAngle,
      final Double totalSecondsExposure,
      final Double startXposition,
      final Double startYposition,
      final Double startZposition,
      final Double translationX,
      final Double translationY,
      final Double translationZ,
      final Double offAxisRotationUm) {

    this(generateMapFromParameters(
        angularResolution, startAngle, endAngle, totalSecondsExposure,
        startXposition, startYposition, startZposition, translationX,
        translationY, translationZ, offAxisRotationUm));
  }

  /**
   * Function to convert Wedge parameters into a Map structure.
   * 
   * @param angularResolution
   *          Angular exposure resolution in degrees.
   * @param startAngle
   *          Start angle of exposure in degrees.
   * @param endAngle
   *          End angle of exposure in degrees.
   * @param translationX
   *          translation on the X axis in distance per degree rotation.
   * @param translationY
   *          translation on the Y axis in distance per degree rotation.
   * @param translationZ
   *          translation on the Z axis in distance per degree rotation.
   * @return
   *         Map structure containing the Wedge parameters.
   */
  private static Map<WedgeProperties, Double> generateMapFromParameters(
      final Double angularResolution, final Double startAngle,
      final Double endAngle, final Double totalSecondsExposure,
      final Double startXposition, final Double startYposition,
      final Double startZposition, final Double translationX,
      final Double translationY, final Double translationZ,
      final Double offAxisRotationUm) {
    Map<WedgeProperties, Double> wedgeProperties =
        new HashMap<WedgeProperties, Double>();

    /* Compulsory variables */
    // TODO: Setting the angles in degrees, but getting them in radians? Why?
    wedgeProperties
        .put(WedgeProperties.ANGLE_START, Math.toRadians(startAngle));
    wedgeProperties.put(WedgeProperties.ANGLE_END, Math.toRadians(endAngle));
    wedgeProperties.put(WedgeProperties.EXPOSURE_TIME, totalSecondsExposure);

    /* Optional variables */
    if (angularResolution != null) {
      wedgeProperties.put(WedgeProperties.ANGULAR_RESOLUTION,
          Math.toRadians(angularResolution));
    }
    if (startXposition != null) {
      wedgeProperties.put(WedgeProperties.START_POSITION_X, startXposition);
    }
    if (startYposition != null) {
      wedgeProperties.put(WedgeProperties.START_POSITION_Y, startYposition);
    }
    if (startZposition != null) {
      wedgeProperties.put(WedgeProperties.START_POSITION_Z, startZposition);
    }

    /*
     * Since the variable stores 'translation per degree' the correct way
     * of translating this to 'translation per radian' is by multiplying
     * by 180 and dividing by pi, which is 1/toRadians, a.k.a. 'toDegrees'
     */
    if (translationX != null) {
      wedgeProperties.put(WedgeProperties.TRANSLATION_X,
          Math.toDegrees(translationX));
    }
    if (translationY != null) {
      wedgeProperties.put(WedgeProperties.TRANSLATION_Y,
          Math.toDegrees(translationY));
    }
    if (translationZ != null) {
      wedgeProperties.put(WedgeProperties.TRANSLATION_Z,
          Math.toDegrees(translationZ));
    }

    if (offAxisRotationUm != null) {
      wedgeProperties.put(WedgeProperties.OFF_AXIS,
          offAxisRotationUm);
    }
    return wedgeProperties;
  }

  /**
   * Contains a description of the wedge: beam size and type, angular range,
   * offset and translation properties.
   * 
   * @return
   *         String describing the wedge.
   */
  public String wedgeProperties() {
    String s = String.format(
        "Collecting data for a total of %.1fs from phi = %.1f to %.1f deg.%n",
        properties.get(WedgeProperties.EXPOSURE_TIME),
        Math.toDegrees(properties.get(WedgeProperties.ANGLE_START)),
        Math.toDegrees(properties.get(WedgeProperties.ANGLE_END)));

    if ((properties.get(WedgeProperties.START_POSITION_X) != 0)
        || (properties.get(WedgeProperties.START_POSITION_Y) != 0)
        || (properties.get(WedgeProperties.TRANSLATION_X) != 0)
        || (properties.get(WedgeProperties.TRANSLATION_Y) != 0)) {
      s = s + String.format(
          "Start is offset by [%f, %f] um [x,y].%n"
              + " Helical scanning is at [%f, %f] um/deg in [x,y]%n",
          properties.get(WedgeProperties.START_POSITION_X),
          properties.get(WedgeProperties.START_POSITION_Y),
          Math.toRadians(properties.get(WedgeProperties.TRANSLATION_X)),
          Math.toRadians(properties.get(WedgeProperties.TRANSLATION_Y)));
    }

    return s;
  }

  /**
   * Retrieve a single value from the wedge information.
   * 
   * @param property
   *          The wedge property.
   * @return
   *         The value of the requested wedge property,
   *         or null if the value is not set.
   */
  public Double get(WedgeProperties property) {
    return properties.get(property);
  }

  /**
   * Returns angular resolution.
   * 
   * @return
   *         Wedge angular resolution in radians.
   */
  public Double getAngRes() {
    return properties.get(WedgeProperties.ANGULAR_RESOLUTION);
  }

  /**
   * Returns starting angle.
   * 
   * @return
   *         Wedge starting angle in radians.
   */
  public Double getStartAng() {
    return properties.get(WedgeProperties.ANGLE_START);
  }

  /**
   * Returns end angle.
   * 
   * @return
   *         Wedge end angle in radians.
   */
  public Double getEndAng() {
    return properties.get(WedgeProperties.ANGLE_END);
  }

  /**
   * Returns the total wedge exposure time.
   * 
   * @return
   *         Wedge exposure time in seconds.
   */
  public Double getTotSec() {
    return properties.get(WedgeProperties.EXPOSURE_TIME);
  }

  public Double getStartX() {
    return properties.get(WedgeProperties.START_POSITION_X);
  }

  public Double getStartY() {
    return properties.get(WedgeProperties.START_POSITION_Y);
  }

  public Double getStartZ() {
    return properties.get(WedgeProperties.START_POSITION_Z);
  }

  public Double getTransX() {
    return properties.get(WedgeProperties.TRANSLATION_X);
  }

  public Double getTransY() {
    return properties.get(WedgeProperties.TRANSLATION_Y);
  }

  public Double getTransZ() {
    return properties.get(WedgeProperties.TRANSLATION_Z);
  }

  public Double[] getStartVector() {
    Double[] startVector = new Double[3];
    startVector[0] = properties.get(WedgeProperties.START_POSITION_X);
    startVector[1] = properties.get(WedgeProperties.START_POSITION_Y);
    startVector[2] = properties.get(WedgeProperties.START_POSITION_Z);
    return startVector;
  }

  public Double[] getTranslationVector(final double deltaPhi) {
    Double[] translationVector = new Double[3];
    translationVector[0] = getTransX(deltaPhi);
    translationVector[1] = getTransY(deltaPhi);
    translationVector[2] = getTransZ(deltaPhi);
    return translationVector;
  }

  /**
   * get the x-translation of vector at a given rotation.
   * 
   * @param deltaPhi
   *          angle of rotation in radians
   */
  public Double getTransX(final double deltaPhi) {
    return getTransX() * (deltaPhi - getStartAng());
  }

  /**
   * get the y-translation of vector at a given rotation.
   * 
   * @param deltaPhi
   *          angle of rotation in radians
   */
  public Double getTransY(final double deltaPhi) {
    return getTransY() * (deltaPhi - getStartAng());
  }

  /**
   * get the z-translation of vector at a given rotation.
   * 
   * @param deltaPhi
   *          angle of rotation in radians
   */
  public Double getTransZ(final double deltaPhi) {
    return getTransZ() * (deltaPhi - getStartAng());
  }

  public Double getOffAxisUm() {
    return properties.get(WedgeProperties.OFF_AXIS);
  }
}
