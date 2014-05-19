package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

import se.raddo.raddose3D.ElementDatabase.DatabaseFields;

/**
 * The Element class contains physical constants of an element associated with
 * x-ray cross sections.
 */
public class Element {
  /**
   * Element name.
   */
  private final String                                      elementName;

  /**
   * Atomic number.
   */
  private final int                                         atomicNumber;

  /**
   * Stored information about the chemical element.
   */
  private final Map<ElementDatabase.DatabaseFields, Double> elementData;

  /**
   * Stored absorption edge coefficients.
   */
  private final Map<AbsorptionEdge, Double[]>               coefficients;

  /**
   * List of absorption edges.
   */
  private enum AbsorptionEdge {
    /** innermost electron shell, 1 shell */
    K,
    /** 2 shell */
    L,
    /** 3 shell */
    M,
    /** 4 shell */
    N,
    /** Coherent scattering polynomial coefficients */
    C,
    /** Incoherent scattering polynomial coefficients */
    I
  }

  /** Atomic mass unit in grams. */
  private static final double ATOMIC_MASS_UNIT          = 1.66E-24;

  /** LJ_1 variable from Fortran, used to correct atomic elements < 29 Z. */
  private static final double LJ_1                      = 1.160;

  /** LJ_2 variable from Fortran, used to correct atomic elements < 29 Z. */
  private static final double LJ_2                      = 1.41;

  /** Light/heavy element threshold. (l<=x<h) */
  public static final int     LIGHT_ATOM_MAX_NUM        = 29;

  /** Absorption edge room for error. */
  private static final double ABSORPTION_EDGE_TOLERANCE = 0.001;

  /** Number of expansions of the polynomial. */
  private static final int    POLYNOMIAL_EXPANSION      = 4;

  /** Different types of calculated cross-sections. */
  public enum CrossSection {
    /**
     * Cross-section for the photoelectric effect. This does not contribute to
     * scattering.
     */
    PHOTOELECTRIC,
    COHERENT, TOTAL
  }

  /**
   * Create a new element with name, atomic number and associated information.
   * 
   * @param element
   *          element name
   * @param protons
   *          atomic number
   * @param elementInformation
   *          Map containing the associated element information
   */
  public Element(final String element, final int protons,
      final Map<ElementDatabase.DatabaseFields, Double> elementInformation) {
    elementName = element;
    atomicNumber = protons;
    elementData = elementInformation;
    coefficients = edgeCoefficients(elementInformation);
  }

  /**
   * Converts the edge coefficients into easier-to-handle arrays.
   * 
   * @param elementInformation
   *          The database fields of the current element
   * @return
   *         Map containing all edge coefficients as Double[] arrays.
   */
  private Map<AbsorptionEdge, Double[]> edgeCoefficients(
      final Map<ElementDatabase.DatabaseFields, Double> elementInformation) {

    Map<AbsorptionEdge, Double[]> coeffMap =
        new HashMap<AbsorptionEdge, Double[]>();

    Double[] coeff;
    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.K_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.K_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.K_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.K_COEFF_3);
    coeffMap.put(AbsorptionEdge.K, coeff);

    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.L_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.L_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.L_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.L_COEFF_3);
    coeffMap.put(AbsorptionEdge.L, coeff);

    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.M_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.M_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.M_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.M_COEFF_3);
    coeffMap.put(AbsorptionEdge.M, coeff);

    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.N_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.N_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.N_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.N_COEFF_3);
    coeffMap.put(AbsorptionEdge.N, coeff);

    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_3);
    coeffMap.put(AbsorptionEdge.C, coeff);

    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_3);
    coeffMap.put(AbsorptionEdge.I, coeff);

    return coeffMap;
  }

  /**
   * Calculation of "bax" for corresponding edge and energy.
   * 
   * @param energy
   *          beam energy in keV
   * @param edge
   *          Selected edge coefficient (K, L, M, N, C, I).
   * @return
   *         value of bax
   */
  private double baxForEdge(final double energy, final AbsorptionEdge edge) {
    // calculation from logarithmic coefficients in McMaster tables.

    double sum = 0;
    Double[] coeffs = coefficients.get(edge);

    for (int i = 0; i < POLYNOMIAL_EXPANSION; i++) {
      if (energy == 1) {
        sum += coeffs[i]; // TODO: Is this actually correct?
      } else {
        sum += coeffs[i] * Math.pow(Math.log(energy), i);
      }
    }

    return Math.exp(sum);
  }

  /**
   * energy is between two edges; this function finds the corresponding edge
   * and
   * calculates bax for this edge. Corrects bax if atomic number is below 29,
   * and then uses this to calculate the cross-sections.
   * 
   * @param energy
   *          X-ray photon energy in keV
   */
  public Map<CrossSection, Double> calculateMu(final double energy) {
    Double absorptionEdgeK =
        elementData.get(ElementDatabase.DatabaseFields.EDGE_K);
    Double absorptionEdgeL =
        elementData.get(ElementDatabase.DatabaseFields.EDGE_L);
    Double absorptionEdgeM =
        elementData.get(ElementDatabase.DatabaseFields.EDGE_M);

    if (energy < absorptionEdgeK
        && energy > absorptionEdgeK - ABSORPTION_EDGE_TOLERANCE) {
      throw new RuntimeException(
          "Warning: using an energy close to middle of K edge of "
              + elementName);
      // TODO: How does Fortran deal with this?
    }
    if (energy < absorptionEdgeL
        && energy > absorptionEdgeL - ABSORPTION_EDGE_TOLERANCE) {
      throw new RuntimeException(
          "Warning: using an energy close to middle of L edge of "
              + elementName);
      // TODO: How does Fortran deal with this?
    }
    if (energy < absorptionEdgeM
        && energy > absorptionEdgeM - ABSORPTION_EDGE_TOLERANCE) {
      throw new RuntimeException(
          "Warning: using an energy close to middle of M edge of "
              + elementName);
      // TODO: How does Fortran deal with this?
    }

    double bax = 0;

    if (energy > absorptionEdgeK) {
      bax = baxForEdge(energy, AbsorptionEdge.K);
    } else if (energy < absorptionEdgeK && energy > absorptionEdgeL) {
      bax = baxForEdge(energy, AbsorptionEdge.L);
    } else if (energy < absorptionEdgeL && energy > absorptionEdgeM) {
      bax = baxForEdge(energy, AbsorptionEdge.M);
    } else if (energy < absorptionEdgeM) {
      bax = baxForEdge(energy, AbsorptionEdge.N);
    }

    // Fortran says...
    // correct for L-edges since McMaster uses L1 edge.
    // Use edge jumps for correct X-sections.

    if (atomicNumber <= LIGHT_ATOM_MAX_NUM) {
      if (energy > elementData.get(ElementDatabase.DatabaseFields.L3)
          && energy < elementData.get(ElementDatabase.DatabaseFields.L2)) {
        bax /= (LJ_1 * LJ_2);
      }

      if (energy > elementData.get(ElementDatabase.DatabaseFields.L2)
          && energy < absorptionEdgeL) {
        bax /= LJ_1;
      }
    }

    double bcox = 0;
    double binx = 0;

    if (elementData.get(ElementDatabase.DatabaseFields.COHERENT_COEFF_0) != 0) {
      bcox = baxForEdge(energy, AbsorptionEdge.C);
    }

    if (elementData.get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_0) != 0)
    {
      binx = baxForEdge(energy, AbsorptionEdge.I);
    }

    double btox = bax + bcox + binx;

    Map<CrossSection, Double> results = new HashMap<CrossSection, Double>();
    results.put(CrossSection.COHERENT, bcox); // elastic
    results.put(CrossSection.PHOTOELECTRIC, bax); // mu, abs coefficient
    results.put(CrossSection.TOTAL, btox); // attenuation
    return results;
  }

  /**
   * @return the elementName
   */
  public String getElementName() {
    return elementName;
  }

  /**
   * @return the atomicNumber
   */
  public int getAtomicNumber() {
    return atomicNumber;
  }

  /**
   * Return the atomic weight of this element in unified atomic mass (u).
   * 
   * @return
   *         the atomic weight in u
   */
  public Double getAtomicWeight() {
    return elementData.get(DatabaseFields.ATOMIC_WEIGHT);
  }

  /**
   * Return the atomic weight of this element in grams.
   * 
   * @return
   *         the atomic weight in grams
   */
  public Double getAtomicWeightInGrams() {
    return getAtomicWeight() * ATOMIC_MASS_UNIT;
  }
}
