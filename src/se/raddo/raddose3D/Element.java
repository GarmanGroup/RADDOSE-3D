package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

/**
 * The Element class contains physical constants of an element associated with
 * x-ray cross sections.
 */
public class Element {
  /**
   * Element name.
   */
  @Deprecated
  public String                                             elementName;
  /**
   * Atomic number.
   */
  @Deprecated
  public int                                                atomicNumber;

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

  /**
   * Atomic weight.
   */
  @Deprecated
  private double              atomicWeight;

  /**
   * L2 and L3 variables.
   */
  @Deprecated
  private double              l2, l3;

  /**
   * Atomic mass unit in grams.
   */
  private static final double ATOMIC_MASS_UNIT          = 1.66E-24;
  /**
   * LJ_1 variable from Fortran, used to correct atomic elements < 29 Z.
   */
  private static final double LJ_1                      = 1.160;
  /**
   * LJ_2 variable from Fortran, used to correct atomic elements < 29 Z.
   */
  private static final double LJ_2                      = 1.41;

  /** Light atom/heavy atom threshold. */
  public static final int     LIGHT_ATOM_MAX_NUM        = 29;
  /** Absorption edge room for error. */
  private static final double ABSORPTION_EDGE_TOLERANCE = 0.001;

  /**
   * Number of expansions of the polynomial.
   */
  private static final int    POLYNOMIAL_EXPANSION      = 4;

  /**
   * Occurrences - number of times this atom is found in the protein.
   */
  @Deprecated
  private double              macromolecularOccurrence;

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

  /**
   * calculated cross-sections.
   */
  private double              photoelectricCrossSection,
                              totalCrossSection,
                              coherentCrossSection;

  /**
   * Create new atom with element name & atomic number.
   * 
   * @param name element name
   * @param number atomic number
   */
  @Deprecated
  public Element(final String name, final int number) {
    elementName = name;
    atomicNumber = number;
    macromolecularOccurrence = 0;
    solventOccurrence = 0;
    solventConcentration = 0;
    photoelectricCrossSection = 0;
    totalCrossSection = 0;
    coherentCrossSection = 0;
    elementData = new HashMap<ElementDatabase.DatabaseFields, Double>();
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

  /*
   * el.setAtomicConstants(Double
   * .parseDouble(components[ATOMIC_WEIGHT]));
   * 
   * el.setIncoherentScatteringCoeffs(
   * Double.parseDouble(components[INCOHERENT_COEFF_0]),
   * Double.parseDouble(components[INCOHERENT_COEFF_1]),
   * Double.parseDouble(components[INCOHERENT_COEFF_2]),
   * Double.parseDouble(components[INCOHERENT_COEFF_3]));
   * el.setLs(Double.parseDouble(components[L2]),
   * Double.parseDouble(components[L3]));
   * 
   * elements.put(components[ELEMENT_NAME].toLowerCase(), el);
   * elements.put(Integer.parseInt(components[ATOMIC_NUMBER]), el);
   */

  /**
   * Set element name and atomic number.
   * 
   * @param name element name
   * @param number atomic number
   */
  @Deprecated
  public void setCoreParameters(final String name, final int number) {
    elementName = name;
    atomicNumber = number;
  }

  /**
   * Sets atomic weight of atom.
   * 
   * @param atweight atomic weight
   */
  @Deprecated
  public void setAtomicConstants(final double atweight) {
    atomicWeight = atweight;
  }

  /**
   * Sets L2 and L3 which were found in the Fortran code.
   * 
   * @param ltwo L2 to be set
   * @param lthree L3 to be set
   */
  @Deprecated
  public void setLs(final double ltwo, final double lthree) {
    this.l2 = ltwo;
    this.l3 = lthree;
  }

  /**
   * Total atoms combining solvent occurrence and macromolecular occurrence.
   * 
   * @return total atoms in unit cell
   */
  public double totalAtoms() {
    double totalAtoms = this.solventOccurrence
        + this.macromolecularOccurrence;

    return totalAtoms;
  }

  /**
   * multiplies total atoms in unit cell by atomic weight.
   * 
   * @return total weight of atoms in unit cell
   */
  public double totalMass() {
    double totalAtoms = this.totalAtoms();
    double mass = atomicWeight * totalAtoms * ATOMIC_MASS_UNIT;

    return mass;
  }

  /**
   * Returns the correct edge coefficient depending on the coefficient number
   * and the edge specified.
   * 
   * @param num number coefficient (0, 1, 2, 3) to access
   * @param edge String indicating which edge coefficient (K, L, M, N, C, I).
   * @return corresponding edge coefficient.
   */
  private double edgeCoefficient(final int num, final String edge) {
    switch (edge.toCharArray()[0]) {
      case 'K':
        switch (num) {
          case 0:
            return elementData.get(ElementDatabase.DatabaseFields.K_COEFF_0);
          case 1:
            return elementData.get(ElementDatabase.DatabaseFields.K_COEFF_1);
          case 2:
            return elementData.get(ElementDatabase.DatabaseFields.K_COEFF_2);
          case 3:
            return elementData.get(ElementDatabase.DatabaseFields.K_COEFF_3);
        }
      case 'L':
        switch (num) {
          case 0:
            return elementData.get(ElementDatabase.DatabaseFields.L_COEFF_0);
          case 1:
            return elementData.get(ElementDatabase.DatabaseFields.L_COEFF_1);
          case 2:
            return elementData.get(ElementDatabase.DatabaseFields.L_COEFF_2);
          case 3:
            return elementData.get(ElementDatabase.DatabaseFields.L_COEFF_3);
        }
      case 'M':
        switch (num) {
          case 0:
            return elementData.get(ElementDatabase.DatabaseFields.M_COEFF_0);
          case 1:
            return elementData.get(ElementDatabase.DatabaseFields.M_COEFF_1);
          case 2:
            return elementData.get(ElementDatabase.DatabaseFields.M_COEFF_2);
          case 3:
            return elementData.get(ElementDatabase.DatabaseFields.M_COEFF_3);
        }
      case 'N':
        switch (num) {
          case 0:
            return elementData.get(ElementDatabase.DatabaseFields.N_COEFF_0);
          case 1:
            return elementData.get(ElementDatabase.DatabaseFields.N_COEFF_1);
          case 2:
            return elementData.get(ElementDatabase.DatabaseFields.N_COEFF_2);
          case 3:
            return elementData.get(ElementDatabase.DatabaseFields.N_COEFF_3);
        }
      case 'C':
        switch (num) {
          case 0:
            return elementData
                .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_0);
          case 1:
            return elementData
                .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_1);
          case 2:
            return elementData
                .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_2);
          case 3:
            return elementData
                .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_3);
        }
      case 'I':
        switch (num) {
          case 0:
            return elementData
                .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_0);
          case 1:
            return elementData
                .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_1);
          case 2:
            return elementData
                .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_2);
          case 3:
            return elementData
                .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_3);
        }
      default:
        System.out
            .println("ERROR: Something's gone horribly wrong in the code");
        return -1;
    }
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
  public double baxForEdge(final double energy, final String edge) {
    // calculation from logarithmic coefficients in McMaster tables.

    double sum = 0;

    for (int i = 0; i < POLYNOMIAL_EXPANSION; i++) {
      double coefficient = edgeCoefficient(i, edge);

      if (coefficient == -1) {
        sum = 0;
      } else if (energy == 1) {
        sum += coefficient;
      } else {
        sum += coefficient * Math.pow(Math.log(energy), i);
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
  public void calculateMu(final double energy) {
    Double absorptionEdgeK =
        elementData.get(ElementDatabase.DatabaseFields.EDGE_K);
    Double absorptionEdgeL =
        elementData.get(ElementDatabase.DatabaseFields.EDGE_L);
    Double absorptionEdgeM =
        elementData.get(ElementDatabase.DatabaseFields.EDGE_M);

    if (energy < absorptionEdgeK
        && energy > absorptionEdgeK - ABSORPTION_EDGE_TOLERANCE) {
      System.out
          .println("Warning: using an energy close to middle of K edge of "
              + elementName);
      return;
    }
    if (energy < absorptionEdgeL
        && energy > absorptionEdgeL - ABSORPTION_EDGE_TOLERANCE) {
      System.out
          .println("Warning: using an energy close to middle of L edge of "
              + elementName);
      return;
    }
    if (energy < absorptionEdgeM
        && energy > absorptionEdgeM - ABSORPTION_EDGE_TOLERANCE) {
      System.out
          .println("Warning: using an energy close to middle of M edge of "
              + elementName);
      return;
    }

    double bax = 0;

    if (energy > absorptionEdgeK) {
      bax = baxForEdge(energy, "K");
    } else if (energy < absorptionEdgeK && energy > absorptionEdgeL) {
      bax = baxForEdge(energy, "L");
    } else if (energy < absorptionEdgeL && energy > absorptionEdgeM) {
      bax = baxForEdge(energy, "M");
    } else if (energy < absorptionEdgeM) {
      bax = baxForEdge(energy, "N");
    }

    // Fortran says...
    // correct for L-edges since McMaster uses L1 edge.
    // Use edge jumps for correct X-sections.

    if (atomicNumber <= LIGHT_ATOM_MAX_NUM) {
      if (energy > this.l3 && energy < this.l2) {
        bax /= (LJ_1 * LJ_2);
      }

      if (energy > this.l2 && energy < absorptionEdgeL) {
        bax /= LJ_1;
      }
    }

    double bcox = 0;
    double binx = 0;

    if (elementData.get(ElementDatabase.DatabaseFields.COHERENT_COEFF_0) != 0) {
      bcox = baxForEdge(energy, "C");
    }

    if (elementData.get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_0) != 0)
    {
      binx = baxForEdge(energy, "I");
    }

    double btox = bax + bcox + binx;

    photoelectricCrossSection = bax; // mu, abs coefficient
    totalCrossSection = btox; // attenuation
    coherentCrossSection = bcox; // elastic
  }

  /**
   * @return the elementName
   */
  @Deprecated
  public String getElementName() {
    return elementName;
  }

  /**
   * @param elementNameNew the elementName to set
   */
  @Deprecated
  public void setElementName(final String elementNameNew) {
    this.elementName = elementNameNew;
  }

  /**
   * @return the atomicNumber
   */
  @Deprecated
  public int getAtomicNumber() {
    return atomicNumber;
  }

  /**
   * @param newatomicNumber the atomicNumber to set
   */
  @Deprecated
  public void setAtomicNumber(final int newatomicNumber) {
    this.atomicNumber = newatomicNumber;
  }

  /**
   * @return the atomicWeight
   */
  public double getAtomicWeight() {
    return atomicWeight;
  }

  /**
   * @param newatomicWeight the atomicWeight to set
   */
  @Deprecated
  public void setAtomicWeight(final double newatomicWeight) {
    this.atomicWeight = newatomicWeight;
  }

  /**
   * @return the macromolecularOccurrence
   */
  @Deprecated
  public double getMacromolecularOccurrence() {
    return macromolecularOccurrence;
  }

  /**
   * @param newmacromolecularOccurrence the macromolecularOccurrence to set
   */
  @Deprecated
  public void setMacromolecularOccurrence(
      final double newmacromolecularOccurrence) {
    this.macromolecularOccurrence = newmacromolecularOccurrence;
  }

  /**
   * @param increment the macromolecularOccurrence increment
   */
  @Deprecated
  public void incrementMacromolecularOccurrence(final double increment) {
    this.macromolecularOccurrence += increment;
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

  /**
   * @return the photoelectricCrossSection
   */
  public double getPhotoelectricCrossSection() {
    return photoelectricCrossSection;
  }

  /**
   * @return the totalCrossSection
   */
  public double getTotalCrossSection() {
    return totalCrossSection;
  }

  /**
   * @return the coherentCrossSection
   */
  public double getCoherentCrossSection() {
    return coherentCrossSection;
  }
}
