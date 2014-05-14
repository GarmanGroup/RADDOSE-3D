package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds X-ray cross section information for all elements of the periodic table.
 */
public class ElementDatabase {
  /**
   * Location of MuCalcConstants library.
   */
  private static final String        MUCALC_FILE = "constants/MuCalcConstants.txt";

  /**
   * Array of Atom objects containing all elements listed in Constants file.
   */
  @Deprecated
  private Element[]                  atoms;

  /**
   * Map of all Element objects in the database.
   */
  private final Map<Object, Element> elements;

  /**
   * Atom count - number of atoms in this array.
   */
  @Deprecated
  private int                        atomCount;

  /**
   * Total atoms in period table.
   */
  @Deprecated
  private static final int           TOTAL_ATOMS = 111;

  /**
   * List of available fields in the database file.
   */
  public static enum DatabaseFields {
    /** K edge in Angstroms. */
    EDGE_K(2),
    /** L edge in Angstroms. */
    EDGE_L(3),
    /** M edge in Angstroms. */
    EDGE_M(4),
    /** K coefficients in polynomial expansion. */
    K_COEFF_0(5), K_COEFF_1(6), K_COEFF_2(7), K_COEFF_3(8),
    /** L coefficients in polynomial expansion. */
    L_COEFF_0(9), L_COEFF_1(10), L_COEFF_2(11), L_COEFF_3(12),
    /** M coefficients in polynomial expansion. */
    M_COEFF_0(13), M_COEFF_1(14), M_COEFF_2(15), M_COEFF_3(16),
    /** N coefficients for polynomial expansion. */
    N_COEFF_0(17), N_COEFF_1(18), N_COEFF_2(19), N_COEFF_3(20),

    /** Atomic weight. */
    ATOMIC_WEIGHT(23),

    /** Coherent coefficients for polynomial expansion. */
    COHERENT_COEFF_0(24), COHERENT_COEFF_1(25),
    /** Coherent coefficients for polynomial expansion. */
    COHERENT_COEFF_2(26), COHERENT_COEFF_3(27),

    /** Incoherent coefficients for polynomial expansion. */
    INCOHERENT_COEFF_0(28), INCOHERENT_COEFF_1(29),
    /** Incoherent coefficients for polynomial expansion. */
    INCOHERENT_COEFF_2(30), INCOHERENT_COEFF_3(31),

    /** L2. */
    L2(36),

    /** L3. */
    L3(37);

    /**
     * The position of each element in the database line. Index starting at 0.
     */
    private final int field;

    /**
     * Initialization of each enum type entry.
     * 
     * @param fieldnumber
     *          The position of this element in the database line. Index
     *          starting at 0.
     */
    DatabaseFields(final int fieldnumber) {
      field = fieldnumber;
    }

    /**
     * Returns the position of this element in the database line.
     * 
     * @return
     *         Position of this element in the database line. Index starting at
     *         0.
     */
    private int fieldNumber() {
      return field;
    }
  }

  /** Position of the atomic number in the database file. */
  private static final int ATOMIC_NUMBER = 0;
  /** Position of the element name in the database file. */
  private static final int ELEMENT_NAME  = 1;

  /**
   * Constructor - reads in constant file & populates atom array.
   */
  public ElementDatabase() {
    atoms = new Element[TOTAL_ATOMS];
    elements = new HashMap<Object, Element>();

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
          Map<DatabaseFields, Double> einfo =
              new HashMap<DatabaseFields, Double>();
          for (DatabaseFields df : DatabaseFields.values()) {
            einfo.put(df, new Double(components[df.fieldNumber()]));
          }

          Integer atomicNumber = new Integer(components[ATOMIC_NUMBER]);
          String elementName = components[ELEMENT_NAME];

          Element el = new Element(elementName, atomicNumber, einfo);
          elements.put(elementName.toLowerCase(), el);
          elements.put(atomicNumber, el);

          /*
           * el.setAbsorptionEdges(Double.parseDouble(components[EDGE_K]),
           * Double.parseDouble(components[EDGE_L]),
           * Double.parseDouble(components[EDGE_M]));
           * el.setAbsorptionKEdgeCoeffs(
           * Double.parseDouble(components[K_COEFF_0]),
           * Double.parseDouble(components[K_COEFF_1]),
           * Double.parseDouble(components[K_COEFF_2]),
           * Double.parseDouble(components[K_COEFF_3]));
           * el.setAbsorptionLEdgeCoeffs(
           * Double.parseDouble(components[L_COEFF_0]),
           * Double.parseDouble(components[L_COEFF_1]),
           * Double.parseDouble(components[L_COEFF_2]),
           * Double.parseDouble(components[L_COEFF_3]));
           * el.setAbsorptionMEdgeCoeffs(
           * Double.parseDouble(components[M_COEFF_0]),
           * Double.parseDouble(components[M_COEFF_1]),
           * Double.parseDouble(components[M_COEFF_2]),
           * Double.parseDouble(components[M_COEFF_3]));
           * el.setAbsorptionNEdgeCoeffs(
           * Double.parseDouble(components[N_COEFF_0]),
           * Double.parseDouble(components[N_COEFF_1]),
           * Double.parseDouble(components[N_COEFF_2]),
           * Double.parseDouble(components[N_COEFF_3]));
           * el.setAtomicConstants(Double
           * .parseDouble(components[ATOMIC_WEIGHT]));
           * el.setCoherentScatteringCoeffs(
           * Double.parseDouble(components[COHERENT_COEFF_0]),
           * Double.parseDouble(components[COHERENT_COEFF_1]),
           * Double.parseDouble(components[COHERENT_COEFF_2]),
           * Double.parseDouble(components[COHERENT_COEFF_3]));
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
  @Deprecated
  public Element findAtomWithZ(final double z) {
    return getElement((int) z);
    /**
     * int lower = 0;
     * int higher = atomCount - 1;
     * int newBound = (higher + lower) / 2;
     * if (z < atoms[lower].atomicNumber || z > atoms[higher].atomicNumber) {
     * System.out
     * .println("Warning: Atomic number asked for which is out of range.");
     * return null;
     * }
     * while (atoms[newBound].atomicNumber != z) {
     * if (higher == lower + 1) {
     * System.out
     * .println("Warning: Atomic number within range but"
     * + "no data available for particular Z.");
     * return null;
     * }
     * if (atoms[newBound].atomicNumber > z) {
     * higher = newBound;
     * } else if (atoms[newBound].atomicNumber < z) {
     * lower = newBound;
     * }
     * newBound = (higher + lower) / 2;
     * }
     * return atoms[newBound];
     */
  }

  /**
   * A bit of a slower algorithm to find atom with a given name. If you have the
   * atomic number, use findAtomWithZ instead.
   * Your job is to check for a NULL return.
   * 
   * @param atomName element name
   * @return Atom object
   */
  @Deprecated
  public Element findAtomWithName(final String atomName) {
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
  @Deprecated
  public Element[] getAtoms() {
    return atoms;
  }

  /**
   * Returns the Element object associated with the chemical element with z
   * protons.
   * 
   * @param z
   *          atomic number
   * @return
   *         associated Element object
   */
  public Element getElement(final int z) {
    return elements.get(z);
  }

  /**
   * Returns the Element object associated with the specified chemical element.
   * 
   * @param name
   *          name of a chemical element
   * @return
   *         associated Element object
   */
  public Element getElement(final String name) {
    return elements.get(name.toLowerCase());
  }

  /**
   * @param newAtoms the atoms to set
   */
  public void setAtoms(final Element[] newAtoms) {
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
