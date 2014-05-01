package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

/**
 * Wedge Class contains all the information for the wedge.
 * It describes a single wedge of an experiment.
 */
public class Wedge {

  private enum WedgeProperties {
    ANGLE_START, ANGLE_END, ANGULAR_RESOLUTION,
    EXPOSURE_TIME,
    START_POSITION_X, START_POSITION_Y, START_POSITION_Z,
    TRANSLATION_X, TRANSLATION_Y, TRANSLATION_Z,
    OFF_AXIS
  };

  private final Map<WedgeProperties, Double> properties;

  /**
   * Full constructor that can be used by the parser.
   * All angular arguments are in degrees.
   * 
   * @param angularResolution
   * @param startAngle
   * @param endAngle
   * @param totalSecondsExposure
   * @param startXposition
   * @param startYposition
   * @param translationX
   * @param translationY
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

    /* Compulsory variables */
    properties.put(WedgeProperties.ANGLE_START, Math.toRadians(startAngle));
    properties.put(WedgeProperties.ANGLE_END, Math.toRadians(endAngle));
    properties.put(WedgeProperties.EXPOSURE_TIME, totalSecondsExposure);

    /* Optional variables */
    if (angularResolution != null) {
      properties.put(WedgeProperties.ANGULAR_RESOLUTION,
          Math.toRadians(angularResolution));
    }
    if (startXposition != null) {
      properties.put(WedgeProperties.START_POSITION_X, startXposition);
    }
    if (startYposition != null) {
      properties.put(WedgeProperties.START_POSITION_Y, startYposition);
    }
    if (startZposition != null) {
      properties.put(WedgeProperties.START_POSITION_Z, startZposition);
    }

    /*
     * Since the variable stores 'translation per degree' the correct way
     * of translating this to 'translation per radian' is by multiplying
     * by 180 and dividing by pi, which is 1/toRadians, a.k.a. 'toDegrees'
     */
    if (translationX != null) {
      properties.put(WedgeProperties.TRANSLATION_X,
          Math.toDegrees(translationX));
    }
    if (translationY != null) {
      properties.put(WedgeProperties.TRANSLATION_Y,
          Math.toDegrees(translationY));
    }
    if (translationZ != null) {
      properties.put(WedgeProperties.TRANSLATION_Z,
          Math.toDegrees(translationZ));
    }

    if (offAxisRotationUm != null) {
      properties.put(WedgeProperties.OFF_AXIS,
          offAxisRotationUm);
    }
  }

  /**
   * Contains a description of the wedge: beam size and type, angular range,
   * offset and translation properties.
   * 
   * @return
   *         String describing the wedge.
   */
  public String wedgeProperties() {
    StringBuffer s = new StringBuffer();

    s.append(String.format(
        "Collecting data for a total of %.1fs from phi = %.1f to %.1f deg.%n",
        properties.get(WedgeProperties.EXPOSURE_TIME),
        properties.get(WedgeProperties.ANGLE_START),
        properties.get(WedgeProperties.ANGLE_END)));

    if ((properties.get(WedgeProperties.START_POSITION_X) != 0)
        || (properties.get(WedgeProperties.START_POSITION_Y) != 0)
        || (properties.get(WedgeProperties.TRANSLATION_X) != 0)
        || (properties.get(WedgeProperties.TRANSLATION_Y) != 0)) {
      s.append(String.format(
          "Start is offset by [%f, %f] um [x,y].%n"
              + " Helical scanning is at [%f, %f] um/deg in [x,y]%n",
          properties.get(WedgeProperties.START_POSITION_X),
          properties.get(WedgeProperties.START_POSITION_Y),
          Math.toRadians(properties.get(WedgeProperties.TRANSLATION_X)),
          Math.toRadians(properties.get(WedgeProperties.TRANSLATION_Y))));
    }

    return s.toString();
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
