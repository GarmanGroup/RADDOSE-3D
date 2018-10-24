package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

//import se.raddo.raddose3D.ElementDatabase.DatabaseFields;

/**
 * The ElementDatabase holds electron information for (nearly) all
 * elements of the periodic table
**/

public class ElementDatabaseEM {
  /**
   * Reference to the singleton instance of ElementDatabase.
   */
  private static ElementDatabaseEM     singleton;

  /**
   * Map of all Element objects in the database.
   */
  private final Map<Object, ElementEM> elements;

  /**
   * List of available fields in the database file.
   */
  
 // public static enum DatabaseFields {
    /** Valid energy ranges for low energy x sections */
  //  EminLow(2), EmaxLow(3),
    /** Constants for the lower energy K shell*/
  //  bKlow(4), cKlow(5), 
    /** Valid energy ranges for high energy x sections */
  //  EminHigh(6), 
    /** Constants for the higher energy K shell*/
  //  bKhigh(7), cKhigh(8), 
    /** ELSEPA elastic cross section */
  //  EL_50(9), EL_100(10), EL_150(11), EL_200(12), EL_250(13), EL_300(14),
    /**K edge */
  //  EDGE_K(15);
    
    /**
     * The position of each element in the database line. Index starting at 0.
     */
 //   private final int field;

    /**
     * Initialization of each enum type entry.
     * 
     * @param fieldnumber
     *          The position of this element in the database line. Index
     *          starting at 0.
     */
    /*
    DatabaseFields(final int fieldnumber) {
      field = fieldnumber;
    }
*/
    /**
     * Returns the position of this element in the database line.
     * 
     * @return
     *         Position of this element in the database line. Index starting at
     *         0.
     */
    /*
    private int fieldNumber() {
      return field;
    }
  }
  */

  /**
   * Location of MuCalcConstants library.
   */
  private static final String ELCALC_FILE   = "constants/full_elsepa.CSV";

  /** Position of the atomic number in the database file. */
  private static final int    ATOMIC_NUMBER = 0;
  /** Position of the element name in the database file. */
  private static final int    ELEMENT_NAME  = 1;

  /**
   * Protected constructor of ElementDatabase. This reads in and parses the
   * constant file and creates the element map.
   * To obtain an instance of the ElementDatabase class, call the getInstance()
   * function.
   * 
   * @throws IOException
   *           The constant file could not be found or accessed.
   */
  @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
  protected ElementDatabaseEM() throws IOException {
    elements = new HashMap<Object, ElementEM>();

    InputStreamReader isr = locateConstantsFile();
    BufferedReader br = new BufferedReader(isr);

    // Read in constants file, consider some kind of error checking
    String line;
    String[] components;

    TreeMap<Double, Double> elementInfo =
        new TreeMap<Double, Double>();

    while ((line = br.readLine()) != null) {
      // ignore commented out lines.
      if (Character.toString(line.charAt(0)).equals("#")) {
        continue;
      }

      // array containing all those numbers from the calculator file
      components = line.split(",", -1);

      // Setting all the properties of the new atom.
      // component[x] where the values of x are in order
      // as listed in the constants file.

      int atomicNumber = Integer.parseInt(components[ATOMIC_NUMBER]);
      double atomicWeight = Double.valueOf(components[2]);

      elementInfo.clear();
      for (int i = 3; i <= 321; i++) {
        double multiplyFactor = 0.05;
        int minusFactor = 2;
        if (i >= 23) {
          minusFactor = 21;
          multiplyFactor = 1;
        }
        double energy = ((double)(i - minusFactor)) * multiplyFactor;
        if ("".equals(components[i])) {
          elementInfo.put(energy, null);
        } else {
          elementInfo.put(energy, Double.valueOf(components[i]));
        }
      }

      ElementEM elem = new ElementEM(components[ELEMENT_NAME], atomicNumber, atomicWeight,
          elementInfo);   //really check this
      elements.put(components[ELEMENT_NAME].toLowerCase(), elem);
      elements.put(atomicNumber, elem);
    }

    br.close();
    isr.close();
  }

  /**
   * Try to locate MUCALC_FILE. This may be in the class path (ie. within a .jar
   * file), or in the file system.
   * 
   * @return
   *         InputStreamReader pointing to the correct resource.
   * @throws FileNotFoundException
   *           The file could not be found.
   * @throws UnsupportedEncodingException
   *           The file charset cannot be interpreted.
   */
  private InputStreamReader locateConstantsFile()
      throws UnsupportedEncodingException, FileNotFoundException {
    // Try to find it within class path;
    InputStream is = getClass().getResourceAsStream("/" + ELCALC_FILE);

    if (is == null) {
      // If it is not within the class path, try via the file system.
      is = new FileInputStream(ELCALC_FILE);
    }

    return new InputStreamReader(is, "US-ASCII");
  }

  /**
   * Returns an instance of the element database. The true constructor of
   * ElementDatabase is private, as ElementDatabase is a Singleton.
   * 
   * @return
   *         Instance of the element database.
   */
  @SuppressWarnings("PMD.AvoidSynchronizedAtMethodLevel")
  public static synchronized ElementDatabaseEM getInstance() {
    if (singleton == null) {
      try {
        singleton = new ElementDatabaseEM();
      } catch (IOException e) {
        throw new IllegalStateException("Error accessing element database file "
            + ELCALC_FILE, e);
      }
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
  public ElementEM getElement(final int z) {
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
  public ElementEM getElement(final String name) {
    return elements.get(name.toLowerCase());
  }
}
