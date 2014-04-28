package se.raddo.raddose3D;

import java.io.*;

/**
 * @author Helen Ginn
 */

public class MuCalcConstantParser {

  
  /**
   * Location of MuCalcConstants library.
   */

  protected static final String MUCALC_FILE        = "constants/MuCalcConstants.txt";

  /**
   * Array of Atom objects containing all elements listed in Constants file
   * Atom count - number of atoms in this array
   */
  public Atom[]                 atoms;
  public int                    atomCount;
  private static int            TOTAL_ATOMS         = 111;

  /**
   * Positions of variables in constant file
   */

  private static final int            ELEMENT_NAME       = 0;
  private static final int            ATOMIC_NUMBER      = 1;
  private static final int            EDGE_K             = 2;
  private static final int            EDGE_L             = 3;
  private static final int            EDGE_M             = 4;

  private static final int            K_COEFF_0          = 5;
  private static final int            K_COEFF_1          = 6;
  private static final int            K_COEFF_2          = 7;
  private static final int            K_COEFF_3          = 8;

  private static final int            L_COEFF_0          = 9;
  private static final int            L_COEFF_1          = 10;
  private static final int            L_COEFF_2          = 11;
  private static final int            L_COEFF_3          = 12;

  private static final int            M_COEFF_0          = 13;
  private static final int            M_COEFF_1          = 14;
  private static final int            M_COEFF_2          = 15;
  private static final int            M_COEFF_3          = 16;

  private static final int            N_COEFF_0          = 17;
  private static final int            N_COEFF_1          = 18;
  private static final int            N_COEFF_2          = 19;
  private static final int            N_COEFF_3          = 20;

  private static final int            ATOMIC_WEIGHT      = 23;

  private static final int            COHERENT_COEFF_0   = 24;
  private static final int            COHERENT_COEFF_1   = 25;
  private static final int            COHERENT_COEFF_2   = 26;
  private static final int            COHERENT_COEFF_3   = 27;

  private static final int            INCOHERENT_COEFF_0 = 28;
  private static final int            INCOHERENT_COEFF_1 = 29;
  private static final int            INCOHERENT_COEFF_2 = 30;
  private static final int            INCOHERENT_COEFF_3 = 31;

  private static final int            L2                 = 36;
  private static final int            L3                 = 37;

  /**
   * Constructor - reads in constant file & populates atom array.
   */
  public MuCalcConstantParser()
  {
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
      while ((line = br.readLine()) != null)
      {
        totalLines++;
        // ignore commented out lines.
        if (Character.toString(line.charAt(0)).equals("#"))
        {
          continue;
        }

        // array containing all those numbers from the calculator file
        String[] components = line.split("\t", -1);

        for (int j = 0; j < components.length; j++)
        {
          // set components to -1 if they're empty, because
          // otherwise Java gets upset.
          String component = components[j];
          if (component.equals(""))
          {
            components[j] = "-1";
          }
        }

        // Setting all the properties of the new atom.
        // component[x] where the values of x are in order
        // as listed in the constants file.

        try
        {
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
        } catch (NumberFormatException e)
        {
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

    if (isr != null)
    {
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
  public Atom findAtomWithZ(double z)
  {
    int lower = 0;
    int higher = atomCount - 1;
    int newBound = (higher + lower) / 2;

    if (z < atoms[lower].atomicNumber || z > atoms[higher].atomicNumber)
    {
      System.out
          .println("Warning: Atomic number asked for which is out of range.");
      return null;
    }

    while (atoms[newBound].atomicNumber != z)
    {
      if (higher == lower + 1)
      {
        System.out
            .println("Warning: Atomic number within range but no data available for particular Z.");
        return null;
      }

      if (atoms[newBound].atomicNumber > z)
        higher = newBound;
      else if (atoms[newBound].atomicNumber < z)
        lower = newBound;

      newBound = (higher + lower) / 2;
    }

    return atoms[newBound];
  }

  /**
   * A bit of a slower algorithm to find atom with a given name. If you have the
   * atomic number, use findAtomWithZ instead.
   * Your job is to check for a NULL return.
   * 
   * @param atomName
   * @return Atom object
   */
  public Atom findAtomWithName(String atomName)
  {
    for (int i = 0; i < atoms.length; i++)
    {
      if (atoms[i].elementName.equals(atomName.toUpperCase()))
        return atoms[i];
    }

    System.out.println("Warning: Atom with name " + atomName
        + " cannot be found in atom dictionary.");
    return null;
  }
}
