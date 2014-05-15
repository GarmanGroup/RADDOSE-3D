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
   * List of absorption edges.
   */
  private enum AbsorptionEdge {
    K, L, M, N, C, I
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

  /**
   * Hetatms - number of times this atom is found in the protein, should also
   * be included in macromolecular occurrence.
   */
  @Deprecated
  private double              hetatmOccurrence          = 0;

  /**
   * Concentration of this atom in the solvent.
   */
  @Deprecated
  private double              solventConcentration;

  /**
   * Number of atoms of this type in the solvent,
   * calculated from solvent concentration.
   */
  @Deprecated
  private double              solventOccurrence;

  /** Different types of calculated cross-sections. */
  public enum CrossSection {
    PHOTOELECTRIC, COHERENT, TOTAL
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
  }

  /**
   * Total atoms combining solvent occurrence and macromolecular occurrence.
   * 
   * @return total atoms in unit cell
   */
  @Deprecated
  public double totalAtoms(Double macromolecularOccurrence) {
    double totalAtoms = solventOccurrence + macromolecularOccurrence;
    return totalAtoms;
  }

  /**
   * Returns the edge coefficients depending on the edge specified.
   * 
   * @param edge String indicating which edge coefficients (K, L, M, N, C, I).
   * @return corresponding edge coefficients.
   */
  private Double[] edgeCoefficients(final AbsorptionEdge edge) {
    Double[] coefficients = new Double[4];

    switch (edge) {
      case K:
        coefficients[0] = elementData
            .get(ElementDatabase.DatabaseFields.K_COEFF_0);
        coefficients[1] = elementData
            .get(ElementDatabase.DatabaseFields.K_COEFF_1);
        coefficients[2] = elementData
            .get(ElementDatabase.DatabaseFields.K_COEFF_2);
        coefficients[3] = elementData
            .get(ElementDatabase.DatabaseFields.K_COEFF_3);
        break;
      case L:
        coefficients[0] = elementData
            .get(ElementDatabase.DatabaseFields.L_COEFF_0);
        coefficients[1] = elementData
            .get(ElementDatabase.DatabaseFields.L_COEFF_1);
        coefficients[2] = elementData
            .get(ElementDatabase.DatabaseFields.L_COEFF_2);
        coefficients[3] = elementData
            .get(ElementDatabase.DatabaseFields.L_COEFF_3);
        break;
      case M:
        coefficients[0] = elementData
            .get(ElementDatabase.DatabaseFields.M_COEFF_0);
        coefficients[1] = elementData
            .get(ElementDatabase.DatabaseFields.M_COEFF_1);
        coefficients[2] = elementData
            .get(ElementDatabase.DatabaseFields.M_COEFF_2);
        coefficients[3] = elementData
            .get(ElementDatabase.DatabaseFields.M_COEFF_3);
        break;
      case N:
        coefficients[0] = elementData
            .get(ElementDatabase.DatabaseFields.N_COEFF_0);
        coefficients[1] = elementData
            .get(ElementDatabase.DatabaseFields.N_COEFF_1);
        coefficients[2] = elementData
            .get(ElementDatabase.DatabaseFields.N_COEFF_2);
        coefficients[3] = elementData
            .get(ElementDatabase.DatabaseFields.N_COEFF_3);
        break;
      case C:
        coefficients[0] = elementData
            .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_0);
        coefficients[1] = elementData
            .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_1);
        coefficients[2] = elementData
            .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_2);
        coefficients[3] = elementData
            .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_3);
        break;
      case I:
        coefficients[0] = elementData
            .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_0);
        coefficients[1] = elementData
            .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_1);
        coefficients[2] = elementData
            .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_2);
        coefficients[3] = elementData
            .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_3);
        break;
      default:
        throw new RuntimeException(
            "ERROR: Something's gone horribly wrong in the code");
    }
    return coefficients;
  }

  /**
   * Calculation of "bax" for corresponding edge and energy in Angstroms.
   * 
   * @param energy
   *          energy of beam in Angstroms
   * @param edge
   *          String indicating which edge coefficient (K, L, M, N, C, I).
   * @return
   *         value of bax
   */
  public double baxForEdge(final double energy, final AbsorptionEdge edge) {
    // calculation from logarithmic coefficients in McMaster tables.

    double sum = 0;
    Double[] coefficients = edgeCoefficients(edge);

    for (int i = 0; i < POLYNOMIAL_EXPANSION; i++) {
      if (coefficients[i] == -1) {
        sum = 0; //TODO: Confirm whether this is actually needed, or just a 'safeguard'? ie. is this from Fortran?
      } else if (energy == 1) {
        sum += coefficients[i];
      } else {
        sum += coefficients[i] * Math.pow(Math.log(energy), i);
      }
    }

    double bax = Math.exp(sum);

    return bax;
  }

  /**
   * energy is between two edges; this function finds the corresponding edge
   * and
   * calculates bax for this edge. Corrects bax if atomic number is below 29,
   * and then uses this to calculate the cross-sections.
   * 
   * @param energy wavelength in Angstroms
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
  @Deprecated
  public String getElementName() {
    return elementName;
  }

  /**
   * @return the atomicNumber
   */
  @Deprecated
  public int getAtomicNumber() {
    return atomicNumber;
  }

  /**
   * @return the atomic weight in u
   */
  public Double getAtomicWeight() {
    return elementData.get(DatabaseFields.ATOMIC_WEIGHT);
  }

  /**
   * @return the atomic weight in grams
   */
  public Double getAtomicWeightInGrams() {
    return elementData.get(DatabaseFields.ATOMIC_WEIGHT) * ATOMIC_MASS_UNIT;
  }

  /**
   * @return the hetatmOccurrence
   */
  @Deprecated
  public double getHetatmOccurrence() {
    return new Double(hetatmOccurrence);
  }

  /**
   * @param newhetatmOccurrence the hetatmOccurrence to set
   */
  @Deprecated
  public void setHetatmOccurrence(final double newhetatmOccurrence) {
    this.hetatmOccurrence = newhetatmOccurrence;
  }

  /**
   * @return the solventConcentration
   */
  @Deprecated
  public double getSolventConcentration() {
    return solventConcentration;
  }

  /**
   * @param newsolventConcentration the solventConcentration to set
   */
  @Deprecated
  public void setSolventConcentration(final double newsolventConcentration) {
    this.solventConcentration = newsolventConcentration;
  }

  /**
   * @param increment the solventConcentration to increment
   */
  @Deprecated
  public void incrementSolventConcentration(final double increment) {
    this.solventConcentration += increment;
  }

  /**
   * @return the solventOccurrence
   */
  @Deprecated
  public double getSolventOccurrence() {
    return solventOccurrence;
  }

  /**
   * @param newsolventOccurrence the solventOccurrence to set
   */
  @Deprecated
  public void setSolventOccurrence(final double newsolventOccurrence) {
    this.solventOccurrence = newsolventOccurrence;
  }

  /**
   * @param increment the solventOccurrence to increment
   */
  @Deprecated
  public void incrementSolventOccurrence(final double increment) {
    this.solventOccurrence += increment;
  }

}
