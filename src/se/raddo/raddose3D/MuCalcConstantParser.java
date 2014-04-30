package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Helen Ginn
 */

public class MuCalcConstantParser {
  /**
   * Atom class looking after physical constants associated with
   * x-ray cross sections.
   * 
   * @author magd3052
   */
  public static class Atom {
    /**
     * Element name.
     */
    private String              elementName;
    /**
     * Atomic number.
     */
    private int                 atomicNumber;

    /**
     * Absorption edge K, L, M in Angstroms.
     */
    private double              absorptionEdgeK, absorptionEdgeL,
                                absorptionEdgeM;

    /**
     * Array (of four) for K, L, M, N edges.
     */
    private double[]            absorptionEdgeKCoeff, absorptionEdgeLCoeff,
                                absorptionEdgeMCoeff, absorptionEdgeNCoeff;

    /**
     * Atomic weight.
     */
    private double              atomicWeight;

    /**
     * Array of four for coherent and incoherent scattering.
     */
    private double[]            coherentScatteringCoeff,
                                incoherentScatteringCoeff;

    /**
     * L2 and L3 variables.
     */
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
    public static final int    LIGHT_ATOM_MAX_NUM        = 29;
    /** Absorption edge room for error. */
    private static final double ABSORPTION_EDGE_TOLERANCE = 0.001;

    /**
     * Number of expansions of the polynomial.
     */
    private static final int    POLYNOMIAL_EXPANSION      = 4;

    /**
     * Occurrences - number of times this atom is found in the protein.
     */
    private double              macromolecularOccurrence;

    /**
     * Hetatms - number of times this atom is found in the protein, should also
     * be included in macromolecular occurrence.
     */
    private double              hetatmOccurrence          = 0;

    /**
     * Concentration of this atom in the solvent.
     */
    private double              solventConcentration;

    /**
     * Number of atoms of this type in the solvent,
     * calculated from solvent concentration.
     */
    private double              solventOccurrence;

    /**
     * calculated cross-sections.
     */

    private double              photoelectricCrossSection, totalCrossSection,
                                coherentCrossSection;

    /**
     * Create new atom with element name & atomic number.
     * 
     * @param name element name
     * @param number atomic number
     */
    public Atom(final String name, final int number) {
      elementName = name;
      atomicNumber = number;
      macromolecularOccurrence = 0;
      solventOccurrence = 0;
      solventConcentration = 0;
      photoelectricCrossSection = 0;
      totalCrossSection = 0;
      coherentCrossSection = 0;
    }

    /**
     * Set element name and atomic number.
     * 
     * @param name element name
     * @param number atomic number
     */
    public void setCoreParameters(final String name, final int number) {
      this.elementName = name;
      this.atomicNumber = number;
    }

    /**
     * Set values of absorption edges in Angstroms.
     * 
     * @param edgeK K edge energy
     * @param edgeL L edge energy
     * @param edgeM M edge energy
     */
    public void setAbsorptionEdges(final double edgeK, final double edgeL,
        final double edgeM) {
      this.absorptionEdgeK = edgeK;
      this.absorptionEdgeL = edgeL;
      this.absorptionEdgeM = edgeM;
    }

    /**
     * Sets atomic weight of atom.
     * 
     * @param atweight atomic weight
     */
    public void setAtomicConstants(final double atweight) {
      this.atomicWeight = atweight;
    }

    /**
     * sets logarithmic coefficients of K edge for calculation of cross-section.
     * 
     * @param k0 k0
     * @param k1 k1
     * @param k2 k2
     * @param k3 k3
     */
    public void setAbsorptionKEdgeCoeffs(final double k0, final double k1,
        final double k2,
        final double k3) {
      this.absorptionEdgeKCoeff = new double[POLYNOMIAL_EXPANSION];

      absorptionEdgeKCoeff[0] = k0;
      absorptionEdgeKCoeff[1] = k1;
      absorptionEdgeKCoeff[2] = k2;
      absorptionEdgeKCoeff[3] = k3;
    }

    /**
     * sets logarithmic coefficients of L edge for calculation of cross-section.
     * follows polynomial fit
     * 
     * @param l0 l0
     * @param l1 l1
     * @param newl2 l2
     * @param newl3 l3
     */
    public void setAbsorptionLEdgeCoeffs(final double l0, final double l1,
        final double newl2,
        final double newl3) {
      this.absorptionEdgeLCoeff = new double[POLYNOMIAL_EXPANSION];

      absorptionEdgeLCoeff[0] = l0;
      absorptionEdgeLCoeff[1] = l1;
      absorptionEdgeLCoeff[2] = newl2;
      absorptionEdgeLCoeff[3] = newl3;
    }

    /**
     * sets logarithmic coefficients of M edge for calculation of cross-section.
     * follows polynomial fit
     * 
     * @param m0 m0
     * @param m1 m1
     * @param m2 m2
     * @param m3 m3
     */
    public void setAbsorptionMEdgeCoeffs(final double m0, final double m1,
        final double m2,
        final double m3) {
      this.absorptionEdgeMCoeff = new double[POLYNOMIAL_EXPANSION];

      absorptionEdgeMCoeff[0] = m0;
      absorptionEdgeMCoeff[1] = m1;
      absorptionEdgeMCoeff[2] = m2;
      absorptionEdgeMCoeff[3] = m3;
    }

    /**
     * sets logarithmic coefficients of K edge for calculation of cross-section.
     * follows polynomial fit
     * 
     * @param n0 n0
     * @param n1 n1
     * @param n2 n2
     * @param n3 n3
     */
    public void setAbsorptionNEdgeCoeffs(final double n0, final double n1,
        final double n2,
        final double n3) {
      this.absorptionEdgeNCoeff = new double[POLYNOMIAL_EXPANSION];

      absorptionEdgeNCoeff[0] = n0;
      absorptionEdgeNCoeff[1] = n1;
      absorptionEdgeNCoeff[2] = n2;
      absorptionEdgeNCoeff[3] = n3;
    }

    /**
     * sets logarithmic coefficients of coherent scattering for calculation of
     * cross-section.
     * follows polynomial fit
     * 
     * @param coh0 coh0
     * @param coh1 coh1
     * @param coh2 coh2
     * @param coh3 coh3
     */
    public void setCoherentScatteringCoeffs(final double coh0,
        final double coh1,
        final double coh2, final double coh3) {
      this.coherentScatteringCoeff = new double[POLYNOMIAL_EXPANSION];

      coherentScatteringCoeff[0] = coh0;
      coherentScatteringCoeff[1] = coh1;
      coherentScatteringCoeff[2] = coh2;
      coherentScatteringCoeff[3] = coh3;
    }

    /**
     * sets logarithmic coefficients of incoherent scattering for calculation of
     * cross-section.
     * follows polynomial fit
     * 
     * @param incoh0 incoh0
     * @param incoh1 incoh1
     * @param incoh2 incoh2
     * @param incoh3 incoh3
     */
    public void setIncoherentScatteringCoeffs(final double incoh0,
        final double incoh1,
        final double incoh2, final double incoh3) {
      this.incoherentScatteringCoeff = new double[POLYNOMIAL_EXPANSION];

      incoherentScatteringCoeff[0] = incoh0;
      incoherentScatteringCoeff[1] = incoh1;
      incoherentScatteringCoeff[2] = incoh2;
      incoherentScatteringCoeff[3] = incoh3;
    }

    /**
     * Sets L2 and L3 which were found in the Fortran code.
     * 
     * @param ltwo L2 to be set
     * @param lthree L3 to be set
     */
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
    public double edgeCoefficient(final int num, final String edge) {
      switch (edge.toCharArray()[0]) {
        case 'K':
          return this.absorptionEdgeKCoeff[num];
        case 'L':
          return this.absorptionEdgeLCoeff[num];
        case 'M':
          return this.absorptionEdgeMCoeff[num];
        case 'N':
          return this.absorptionEdgeNCoeff[num];
        case 'C':
          return this.coherentScatteringCoeff[num];
        case 'I':
          return this.incoherentScatteringCoeff[num];
        default:
          System.out
              .println("ERROR: Something's gone horribly wrong in the code");
          return -1;
      }
    }

    /**
     * Calculation of "bax" for corresponding edge and energy in Angstroms.
     * 
     * @param energy energy of beam in Angstroms
     * @param edge String indicating which edge coefficient (K, L, M, N, C, I).
     * @return value of bax
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

        if (energy > this.l2 && energy < this.absorptionEdgeL) {
          bax /= LJ_1;
        }
      }

      double bcox = 0;
      double binx = 0;

      if (!(this.coherentScatteringCoeff[0] == -1
      || this.coherentScatteringCoeff[0] == 0)) {
        bcox = baxForEdge(energy, "C");
      }

      if (!(this.coherentScatteringCoeff[0] == -1
      || this.coherentScatteringCoeff[0] == 0)) {
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
    public String getElementName() {
      return elementName;
    }

    /**
     * @param elementNameNew the elementName to set
     */
    public void setElementName(final String elementNameNew) {
      this.elementName = elementNameNew;
    }

    /**
     * @return the atomicNumber
     */
    public int getAtomicNumber() {
      return atomicNumber;
    }

    /**
     * @param newatomicNumber the atomicNumber to set
     */
    public void setAtomicNumber(final int newatomicNumber) {
      this.atomicNumber = newatomicNumber;
    }

    /**
     * @return the absorptionEdgeK
     */
    public double getAbsorptionEdgeK() {
      return absorptionEdgeK;
    }

    /**
     * @param newabsorptionEdgeK the absorptionEdgeK to set
     */
    public void setAbsorptionEdgeK(final double newabsorptionEdgeK) {
      this.absorptionEdgeK = newabsorptionEdgeK;
    }

    /**
     * @return the absorptionEdgeL
     */
    public double getAbsorptionEdgeL() {
      return absorptionEdgeL;
    }

    /**
     * @param newabsorptionEdgeL the absorptionEdgeL to set
     */
    public void setAbsorptionEdgeL(final double newabsorptionEdgeL) {
      this.absorptionEdgeL = newabsorptionEdgeL;
    }

    /**
     * @return the absorptionEdgeM
     */
    public double getAbsorptionEdgeM() {
      return absorptionEdgeM;
    }

    /**
     * @param newabsorptionEdgeM the absorptionEdgeM to set
     */
    public void setAbsorptionEdgeM(final double newabsorptionEdgeM) {
      this.absorptionEdgeM = newabsorptionEdgeM;
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
    public void setAtomicWeight(final double newatomicWeight) {
      this.atomicWeight = newatomicWeight;
    }

    /**
     * @return the l2
     */
    public double getL2() {
      return l2;
    }

    /**
     * @param newl2 the l2 to set
     */
    public void setL2(final double newl2) {
      this.l2 = newl2;
    }

    /**
     * @return the l3
     */
    public double getL3() {
      return l3;
    }

    /**
     * @param newl3 the l3 to set
     */
    public void setL3(final double newl3) {
      this.l3 = newl3;
    }

    /**
     * @return the macromolecularOccurrence
     */
    public double getMacromolecularOccurrence() {
      return macromolecularOccurrence;
    }

    /**
     * @param newmacromolecularOccurrence the macromolecularOccurrence to set
     */
    public void setMacromolecularOccurrence(
        final double newmacromolecularOccurrence) {
      this.macromolecularOccurrence = newmacromolecularOccurrence;
    }

    /**
     * @param increment the macromolecularOccurrence increment
     */
    public void incrementMacromolecularOccurrence(final double increment) {
      this.macromolecularOccurrence += increment;
    }

    /**
     * @return the hetatmOccurrence
     */
    public double getHetatmOccurrence() {
      return new Double(hetatmOccurrence);
    }

    /**
     * @param newhetatmOccurrence the hetatmOccurrence to set
     */
    public void setHetatmOccurrence(final double newhetatmOccurrence) {
      this.hetatmOccurrence = newhetatmOccurrence;
    }

    /**
     * @return the solventConcentration
     */
    public double getSolventConcentration() {
      return solventConcentration;
    }

    /**
     * @param newsolventConcentration the solventConcentration to set
     */
    public void setSolventConcentration(final double newsolventConcentration) {
      this.solventConcentration = newsolventConcentration;
    }

    /**
     * @param increment the solventConcentration to increment
     */
    public void incrementSolventConcentration(final double increment) {
      this.solventConcentration += increment;
    }

    /**
     * @return the solventOccurrence
     */
    public double getSolventOccurrence() {
      return solventOccurrence;
    }

    /**
     * @param newsolventOccurrence the solventOccurrence to set
     */
    public void setSolventOccurrence(final double newsolventOccurrence) {
      this.solventOccurrence = newsolventOccurrence;
    }

    /**
     * @param increment the solventOccurrence to increment
     */
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
     * @param newphotoelectricCrossSection the photoelectricCrossSection to set
     */
    public void setPhotoelectricCrossSection(
        final double newphotoelectricCrossSection) {
      this.photoelectricCrossSection = newphotoelectricCrossSection;
    }

    /**
     * @return the totalCrossSection
     */
    public double getTotalCrossSection() {
      return totalCrossSection;
    }

    /**
     * @param newtotalCrossSection the totalCrossSection to set
     */
    public void setTotalCrossSection(final double newtotalCrossSection) {
      this.totalCrossSection = newtotalCrossSection;
    }

    /**
     * @return the coherentCrossSection
     */
    public double getCoherentCrossSection() {
      return coherentCrossSection;
    }

    /**
     * @param newcoherentCrossSection the coherentCrossSection to set
     */
    public void setCoherentCrossSection(final double newcoherentCrossSection) {
      this.coherentCrossSection = newcoherentCrossSection;
    }
  }

  /**
   * Location of MuCalcConstants library.
   */

  protected static final String MUCALC_FILE        = "constants/MuCalcConstants.txt";

  /**
   * Array of Atom objects containing all elements listed in Constants file.
   */
  private Atom[]                atoms;

  /**
   * Atom count - number of atoms in this array.
   */
  private int                   atomCount;

  /**
   * Total atoms in period table.
   */
  private static final int      TOTAL_ATOMS        = 111;

  /**
   * element name.
   */
  private static final int      ELEMENT_NAME       = 1;
  /**
   * atomic number.
   */
  private static final int      ATOMIC_NUMBER      = 0;
  /**
   * K edge in Angstroms.
   */
  private static final int      EDGE_K             = 2;
  /**
   * L edge in Angstroms.
   */
  private static final int      EDGE_L             = 3;
  /**
   * M edge in Angstroms.
   */
  private static final int      EDGE_M             = 4;

  /**
   * K coefficient 0 in polynomial expansion.
   */
  private static final int      K_COEFF_0          = 5;
  /**
   * K coefficient 1.
   */
  private static final int      K_COEFF_1          = 6;
  /**
   * K coefficient 2.
   */
  private static final int      K_COEFF_2          = 7;
  /**
   * K coefficient 3.
   */
  private static final int      K_COEFF_3          = 8;

  /**
   * L coefficient 0 in polynomial expansion.
   */
  private static final int      L_COEFF_0          = 9;
  /**
   * L coefficient 1.
   */
  private static final int      L_COEFF_1          = 10;
  /**
   * L coefficient 2.
   */
  private static final int      L_COEFF_2          = 11;
  /**
   * L coefficient 3.
   */
  private static final int      L_COEFF_3          = 12;

  /**
   * M coefficient 0 in polynomial expansion.
   */
  private static final int      M_COEFF_0          = 13;
  /**
   * M coefficient 1.
   */
  private static final int      M_COEFF_1          = 14;
  /**
   * M coefficient 2.
   */
  private static final int      M_COEFF_2          = 15;
  /**
   * M coefficient 3.
   */
  private static final int      M_COEFF_3          = 16;
  /**
   * N coefficient 0 for polynomial expansion.
   */
  private static final int      N_COEFF_0          = 17;
  /**
   * N coefficient 1.
   */
  private static final int      N_COEFF_1          = 18;
  /**
   * N coefficient 2.
   */
  private static final int      N_COEFF_2          = 19;
  /**
   * N coefficient 3.
   */
  private static final int      N_COEFF_3          = 20;
  /**
   * N coefficient 4.
   */
  private static final int      ATOMIC_WEIGHT      = 23;

  /**
   * Coherent coefficient 0 for polynomial expansion.
   */
  private static final int      COHERENT_COEFF_0   = 24;
  /**
   * Coherent coefficient 1.
   */
  private static final int      COHERENT_COEFF_1   = 25;
  /**
   * Coherent coefficient 2.
   */
  private static final int      COHERENT_COEFF_2   = 26;
  /**
   * Coherent coefficient 3.
   */
  private static final int      COHERENT_COEFF_3   = 27;

  /**
   * Incoherent coefficient 0 for polynomial expansion.
   */
  private static final int      INCOHERENT_COEFF_0 = 28;
  /**
   * Incoherent coefficient 1.
   */
  private static final int      INCOHERENT_COEFF_1 = 29;
  /**
   * Incoherent coefficient 2.
   */
  private static final int      INCOHERENT_COEFF_2 = 30;
  /**
   * Incoherent coefficient 3.
   */
  private static final int      INCOHERENT_COEFF_3 = 31;
  /**
   * L2.
   */
  private static final int      L2                 = 36;
  /**
   * L3.
   */
  private static final int      L3                 = 37;

  /**
   * Constructor - reads in constant file & populates atom array.
   */
  public MuCalcConstantParser() {
    atoms = new Atom[TOTAL_ATOMS];

    BufferedReader br = null;
    InputStreamReader isr = null;

    try {
      FileInputStream is = new FileInputStream(MUCALC_FILE);
      isr = new InputStreamReader(is);
      br = new BufferedReader(isr);
    } catch (FileNotFoundException e) {
      // give up
      System.out.println("Cannot find atom library file. Have you deleted it?");

      e.printStackTrace();
      return;
    } // Read in constants file, consider some kind of error checking

    String line;
    int i = 0;
    int totalLines = 0;
    try {
      while ((line = br.readLine()) != null) {
        totalLines++;
        // ignore commented out lines.
        if (Character.toString(line.charAt(0)).equals("#")) {
          continue;
        }

        // array containing all those numbers from the calculator file
        String[] components = line.split("\t", -1);

        for (int j = 0; j < components.length; j++) {
          // set components to -1 if they're empty, because
          // otherwise Java gets upset.
          String component = components[j];
          if (component.equals("")) {
            components[j] = "-1";
          }
        }

        // Setting all the properties of the new atom.
        // component[x] where the values of x are in order
        // as listed in the constants file.

        try {
          atoms[i] = new Atom(components[ELEMENT_NAME],
              Integer.parseInt(components[ATOMIC_NUMBER]));
          atoms[i].setAbsorptionEdges(Double.parseDouble(components[EDGE_K]),
              Double.parseDouble(components[EDGE_L]),
              Double.parseDouble(components[EDGE_M]));
          atoms[i].setAbsorptionKEdgeCoeffs(
              Double.parseDouble(components[K_COEFF_0]),
              Double.parseDouble(components[K_COEFF_1]),
              Double.parseDouble(components[K_COEFF_2]),
              Double.parseDouble(components[K_COEFF_3]));
          atoms[i].setAbsorptionLEdgeCoeffs(
              Double.parseDouble(components[L_COEFF_0]),
              Double.parseDouble(components[L_COEFF_1]),
              Double.parseDouble(components[L_COEFF_2]),
              Double.parseDouble(components[L_COEFF_3]));
          atoms[i].setAbsorptionMEdgeCoeffs(
              Double.parseDouble(components[M_COEFF_0]),
              Double.parseDouble(components[M_COEFF_1]),
              Double.parseDouble(components[M_COEFF_2]),
              Double.parseDouble(components[M_COEFF_3]));
          atoms[i].setAbsorptionNEdgeCoeffs(
              Double.parseDouble(components[N_COEFF_0]),
              Double.parseDouble(components[N_COEFF_1]),
              Double.parseDouble(components[N_COEFF_2]),
              Double.parseDouble(components[N_COEFF_3]));
          atoms[i].setAtomicConstants(Double
              .parseDouble(components[ATOMIC_WEIGHT]));
          atoms[i].setCoherentScatteringCoeffs(
              Double.parseDouble(components[COHERENT_COEFF_0]),
              Double.parseDouble(components[COHERENT_COEFF_1]),
              Double.parseDouble(components[COHERENT_COEFF_2]),
              Double.parseDouble(components[COHERENT_COEFF_3]));
          atoms[i].setIncoherentScatteringCoeffs(
              Double.parseDouble(components[INCOHERENT_COEFF_0]),
              Double.parseDouble(components[INCOHERENT_COEFF_1]),
              Double.parseDouble(components[INCOHERENT_COEFF_2]),
              Double.parseDouble(components[INCOHERENT_COEFF_3]));
          atoms[i].setLs(Double.parseDouble(components[L2]),
              Double.parseDouble(components[L3]));
        } catch (NumberFormatException e) {
          System.out.println("Could not parse line " + totalLines);
          e.printStackTrace();
        }

        i++;
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (isr != null) {
      try {
        isr.close();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    atomCount = i;
  }

  /**
   * Q chop algorithm to quickly find an atom with a given atomic number Z.
   * * Your job is to check for a NULL return.
   * 
   * @param z atomic number
   * @return associated Atom object
   */
  public Atom findAtomWithZ(final double z) {
    int lower = 0;
    int higher = atomCount - 1;
    int newBound = (higher + lower) / 2;

    if (z < atoms[lower].atomicNumber || z > atoms[higher].atomicNumber) {
      System.out
          .println("Warning: Atomic number asked for which is out of range.");
      return null;
    }

    while (atoms[newBound].atomicNumber != z) {
      if (higher == lower + 1) {
        System.out
            .println("Warning: Atomic number within range but"
                + "no data available for particular Z.");
        return null;
      }

      if (atoms[newBound].atomicNumber > z) {
        higher = newBound;
      } else if (atoms[newBound].atomicNumber < z) {
        lower = newBound;
      }

      newBound = (higher + lower) / 2;
    }

    return atoms[newBound];
  }

  /**
   * A bit of a slower algorithm to find atom with a given name. If you have the
   * atomic number, use findAtomWithZ instead.
   * Your job is to check for a NULL return.
   * 
   * @param atomName element name
   * @return Atom object
   */
  public Atom findAtomWithName(final String atomName) {
    for (int i = 0; i < atoms.length; i++) {
      if (atoms[i].elementName.equals(atomName.toUpperCase())) {
        return atoms[i];
      }
    }

    System.out.println("Warning: Atom with name " + atomName
        + " cannot be found in atom dictionary.");
    return null;
  }

  /**
   * @return the atoms
   */
  public Atom[] getAtoms() {
    return atoms;
  }

  /**
   * @param newAtoms the atoms to set
   */
  public void setAtoms(final Atom[] newAtoms) {
    this.atoms = newAtoms;
  }

  /**
   * @return the atomCount
   */
  public int getAtomCount() {
    return atomCount;
  }

  /**
   * @param newAtomCount the atomCount to set
   */
  public void setAtomCount(final int newAtomCount) {
    this.atomCount = newAtomCount;
  }
}
