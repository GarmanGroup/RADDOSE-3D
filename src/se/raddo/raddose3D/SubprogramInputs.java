package se.raddo.raddose3D;

import java.util.Set;

/**
 * What the Monte Carlo and XFEL subprograms need of their input, checked
 * before a run starts rather than met as an exception partway through one.
 */
final class SubprogramInputs {

  /** Up to this energy, in keV, ELSEPA's angular tables cover every element. */
  static final double ANGULAR_TABLES_ALL_ELEMENTS_KEV = 20;

  /** Above it, they stop at this atomic number. */
  static final int ANGULAR_TABLES_MAX_Z = 52;

  /** A Gaussian XFEL pulse's window, in FWHMs, when none is given. */
  static final double DEFAULT_PULSE_WINDOW = 2;

  private SubprogramInputs() {
  }

  /**
   * The window an XFEL pulse's photons arrive in, in FWHMs of a Gaussian
   * pulse, centred on its peak.
   *
   * @param beam the beam, with its PulseShape and PulseWindow
   * @return the window; null for a top-hat pulse, the default
   */
  static Double pulseWindow(final Beam beam) {
    String shape = beam.getPulseShape();
    Double window = beam.getPulseWindow();
    boolean gaussian = "Gaussian".equalsIgnoreCase(shape);
    if (shape != null && !gaussian && !"TopHat".equalsIgnoreCase(shape)) {
      throw new IllegalArgumentException("PulseShape is TopHat or Gaussian, "
          + "not " + shape + ".");
    }
    if (!gaussian) {
      if (window != null) {
        throw new IllegalArgumentException("PulseWindow is the window a "
            + "Gaussian pulse's photons arrive in, in FWHMs; a top-hat pulse "
            + "lasts the exposure time.");
      }
      return null;
    }
    if (window == null) {
      return DEFAULT_PULSE_WINDOW;
    }
    if (!(window > 0)) {
      throw new IllegalArgumentException("PulseWindow, in FWHMs, must be more "
          + "than 0, not " + window + ".");
    }
    return window;
  }

  /**
   * Refuses input the subprograms cannot run.
   *
   * @param subprogram its name, for the messages
   * @param beam the beam
   * @param coefCalc the crystal's composition
   * @param photonEnergies every photon energy the run will use, in keV
   */
  static void check(final String subprogram, final Beam beam,
      final CoefCalc coefCalc, final double[] photonEnergies) {
    String type = beam.getType();
    if (!"Tophat".equals(type) && !"Gaussian".equals(type)) {
      // An experimental beam gave every photon the position (0, 0), an
      // exposed area of zero, and a NullPointerException at the end.
      throw new IllegalArgumentException("The " + subprogram
          + " subprogram needs a TopHat or Gaussian beam, and this beam is "
          + type + ".");
    }
    if (!"XFEL".equals(subprogram)
        && (beam.getPulseShape() != null || beam.getPulseWindow() != null)) {
      throw new IllegalArgumentException("PulseShape and PulseWindow "
          + "describe an XFEL pulse, which the " + subprogram
          + " subprogram does not have.");
    }
    if (!(coefCalc instanceof CoefCalcCompute)) {
      throw new IllegalArgumentException("The " + subprogram
          + " subprogram needs the crystal's elements, which AbsCoefCalc "
          + "AVERAGE and the original RADDOSE (RDV2, RDV3) do not give.");
    }
    double maxEnergy = 0;
    for (double energy : photonEnergies) {
      maxEnergy = Math.max(maxEnergy, energy);
    }
    if (maxEnergy > ANGULAR_TABLES_ALL_ELEMENTS_KEV) {
      // An electron above 20 keV scattering off such an element found no
      // table, and a NullPointerException ended the run.
      CoefCalcCompute composition = (CoefCalcCompute) coefCalc;
      for (boolean surrounding : new boolean[] {false, true}) {
        Set<Element> elements = composition.getPresentElements(surrounding);
        if (elements == null) {
          continue;
        }
        for (Element e : elements) {
          if (e.getAtomicNumber() > ANGULAR_TABLES_MAX_Z) {
            throw new IllegalArgumentException(String.format(
                java.util.Locale.ROOT, "The %s subprogram has electron "
                + "scattering tables above %.0f keV only up to Z = %d, and "
                + "the %s contains %s (Z = %d) in a beam reaching %.3f keV.",
                subprogram, ANGULAR_TABLES_ALL_ELEMENTS_KEV,
                ANGULAR_TABLES_MAX_Z, surrounding ? "surrounding" : "crystal",
                e.getElementName(), e.getAtomicNumber(), maxEnergy));
          }
        }
      }
    }
  }
}
