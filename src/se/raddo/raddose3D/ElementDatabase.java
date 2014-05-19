package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds X-ray cross section information for all elements of the periodic table.
 * The ElementDatabase class is a Singleton. Its constructor is not publically
 * accessible.
 * To obtain an instance of the ElementDatabase class, call the getInstance()
 * function.
 */
public class ElementDatabase {
  /**
   * Reference to the singleton instance of ElementDatabase.
   */
  private static ElementDatabase     singleton;

  /**
   * Map of all Element objects in the database.
   */
  private final Map<Object, Element> elements;

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

  /**
   * Location of MuCalcConstants library.
   */
  private static final String MUCALC_FILE   = "constants/MuCalcConstants.txt";

  /** Position of the atomic number in the database file. */
  private static final int    ATOMIC_NUMBER = 0;
  /** Position of the element name in the database file. */
  private static final int    ELEMENT_NAME  = 1;

  /**
   * Protected constructor of ElementDatabase. This reads in and parses the
   * constant file and creates the element map.
   * To obtain an instance of the ElementDatabase class, call the getInstance()
   * function.
   */
  protected ElementDatabase() {
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
    int totalLines = 0;
    Map<DatabaseFields, Double> einfo;
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
          if ("".equals(component)) {
            components[j] = "-1";
          }
        }

        // Setting all the properties of the new atom.
        // component[x] where the values of x are in order
        // as listed in the constants file.

        try {
          einfo = new HashMap<DatabaseFields, Double>();
          for (DatabaseFields df : DatabaseFields.values()) {
            einfo.put(df, new Double(components[df.fieldNumber()]));
          }

          int atomicNumber = Integer.valueOf(components[ATOMIC_NUMBER]);

          Element el =
              new Element(components[ELEMENT_NAME], atomicNumber, einfo);
          elements.put(components[ELEMENT_NAME].toLowerCase(), el);
          elements.put(atomicNumber, el);
        } catch (NumberFormatException e) {
          System.out.println("Could not parse line " + totalLines);
          e.printStackTrace();
        }
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
  }

  /**
   * Returns an instance of the element database. The true constructor of
   * ElementDatabase is private, as ElementDatabase is a Singleton.
   * 
   * @return
   *         Instance of the element database.
   */
  @SuppressWarnings("PMD.AvoidSynchronizedAtMethodLevel")
  public static synchronized ElementDatabase getInstance() {
    if (singleton == null) {
      singleton = new ElementDatabase();
    }
    return singleton;
  }

  /**
   * @return the atoms
   */
  @Deprecated
  public Element[] getAtoms() {
    Collection<Element> collection = elements.values();
    return collection.toArray(new Element[collection.size()]);
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
   * @return the atomCount
   */
  @Deprecated
  public int getAtomCount() {
    return elements.size();
  }
}
