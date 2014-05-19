package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    InputStreamReader isr = locateConstantsFile();
    BufferedReader br = new BufferedReader(isr);

    // Read in constants file, consider some kind of error checking
    try {
      String line;
      String[] components;

      Map<DatabaseFields, Double> elementInfo =
          new HashMap<DatabaseFields, Double>();

      while ((line = br.readLine()) != null) {
        // ignore commented out lines.
        if (Character.toString(line.charAt(0)).equals("#")) {
          continue;
        }

        // array containing all those numbers from the calculator file
        components = line.split("\t", -1);

        // Setting all the properties of the new atom.
        // component[x] where the values of x are in order
        // as listed in the constants file.

        int atomicNumber = Integer.valueOf(components[ATOMIC_NUMBER]);

        elementInfo.clear();
        for (DatabaseFields df : DatabaseFields.values()) {

          if ("".equals(components[df.fieldNumber()])) {
            elementInfo.put(df, null);
          } else {
            elementInfo.put(df, Double.valueOf(components[df.fieldNumber()]));
          }
        }

        Element elem = new Element(components[ELEMENT_NAME], atomicNumber,
            elementInfo);
        elements.put(components[ELEMENT_NAME].toLowerCase(), elem);
        elements.put(atomicNumber, elem);
      }

      br.close();
      isr.close();
    } catch (IOException e) {
      throw new RuntimeException("Error accessing element database file "
          + MUCALC_FILE, e);
    }
  }

  /**
   * Try to locate MUCALC_FILE. This may be in the class path (ie. within a .jar
   * file), or in the file system.
   * 
   * @return
   *         InputStreamReader pointing to the correct resource.
   * @throws RuntimeException
   *           If the file could not be found.
   */
  private InputStreamReader locateConstantsFile() throws RuntimeException {
    // Try to find it within class path;
    InputStream is = getClass().getResourceAsStream("/" + MUCALC_FILE);

    if (is == null) {
      // If it is not within the class path, try via the file system.
      try {
        is = new FileInputStream(MUCALC_FILE);
      } catch (FileNotFoundException e) {
        // give up
        throw new RuntimeException("Cannot find element database file "
            + MUCALC_FILE, e);
      }
    }

    return new InputStreamReader(is);
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
}
