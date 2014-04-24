package se.raddo.raddose3D;

/**
 * Wedge Class contains all the information for the wedge.
 * It describes a single wedge of an experiment.
 */
public class Wedge {

  private final Double angRes,
                       startAng,
                       endAng,
                       totSec,
                       startX,
                       startY,
                       startZ,
                       transX,
                       transY,
                       transZ,
                       offAxisUm;

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
    /* Compulsory Variables */
    startAng = Math.toRadians(startAngle);
    endAng = Math.toRadians(endAngle);
    totSec = totalSecondsExposure;

    /* Default to 2 deg/step if null */
    angRes = Math
        .toRadians((angularResolution == null) ? 2 : angularResolution);
    /* Default to 0 */
    startX = (startXposition == null) ? 0 : startXposition;
    /* Default to 0 */
    startY = (startYposition == null) ? 0 : startYposition;
    /* Default to 0 */
    startZ = (startZposition == null) ? 0 : startZposition;
    /*
     * Since the variable stores 'translation per degree' the correct way
     * of translating this to 'translation per radian' is by multiplying
     * by 180 and dividing by pi, which is 1/toRadians, a.k.a. 'toDegrees'
     */
    /* Default to 0 */
    transX = (translationX == null) ? 0 : Math.toDegrees(translationX);
    /* Default to 0 */
    transY = (translationY == null) ? 0 : Math.toDegrees(translationY);
    /* Default to 0 */
    transZ = (translationZ == null) ? 0 : Math.toDegrees(translationZ);

    /* Default to 0 */
    offAxisUm = (offAxisRotationUm == null) ? 0 : offAxisRotationUm;
  }

  /**
   * Contains a description of the wedge: beam size and type, angular range,
   * offset and translation properties.
   * 
   * @return
   *         String describing the wedge.
   */
  public String wedgeProperties() {
    String st = String.format(
        "Collecting data for a total of %.1fs from phi = %.1f to %.1f deg.\n"
        , totSec, Math.toDegrees(startAng), Math.toDegrees(endAng));
    if (startX != 0 || startY != 0 || transX != 0 || transY != 0) {
      String st2 = String.format(
          "Start is offset by [%f, %f] um [x,y].\n"
              + " Helical scanning is at [%f, %f] um/deg in [x,y]\n",
          startX, startY, Math.toRadians(transX), Math.toRadians(transY));
      return st + st2;
    } else {
      return st;
    }
  }

  /**
   * Returns angular resolution.
   * 
   * @return
   *         Wedge angular resolution in radians.
   */
  public Double getAngRes() {
    return angRes;
  }

  /**
   * Returns starting angle.
   * 
   * @return
   *         Wedge starting angle in radians.
   */
  public Double getStartAng() {
    return startAng;
  }

  /**
   * Returns end angle.
   * 
   * @return
   *         Wedge end angle in radians.
   */
  public Double getEndAng() {
    return endAng;
  }

  public Double getTotSec() {
    return totSec;
  }

  public Double getStartX() {
    return startX;
  }

  public Double getStartY() {
    return startY;
  }

  public Double getStartZ() {
    return startZ;
  }

  public Double getTransX() {
    return transX;
  }

  public Double getTransY() {
    return transY;
  }

  public Double getTransZ() {
    return transZ;
  }

  public Double[] getStartVector() {
    Double[] startVector = new Double[3];
    startVector[0] = startX;
    startVector[1] = startY;
    startVector[2] = startZ;
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
   * @return
   */
  public Double getTransX(final double deltaPhi) {
    return getTransX() * (deltaPhi - getStartAng());
  }

  /**
   * get the y-translation of vector at a given rotation.
   * 
   * @param deltaPhi
   *          angle of rotation in radians
   * @return
   */
  public Double getTransY(final double deltaPhi) {
    return getTransY() * (deltaPhi - getStartAng());
  }

  /**
   * get the z-translation of vector at a given rotation.
   * 
   * @param deltaPhi
   *          angle of rotation in radians
   * @return
   */
  public Double getTransZ(final double deltaPhi) {
    return getTransZ() * (deltaPhi - getStartAng());
  }

  public Double getOffAxisUm() {
    return offAxisUm;
  }
}
