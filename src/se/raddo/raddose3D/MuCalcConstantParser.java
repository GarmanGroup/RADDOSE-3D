package se.raddo.raddose3D;

import java.io.*;

/**
 * @author Helen Ginn
 */

public class MuCalcConstantParser {
  
  // Two constants LJ_1, LJ_2 involved in correcting for L-edges since McMaster uses the L1-edge.
  
  // Location of MuCalcConstants library.
  
  protected static final String     MUCALC_FILE          = "constants/MuCalcConstants.txt";
  
  public Atom[]                    atoms;
  public int                       atomCount;
  
  public MuCalcConstantParser()
  {
    atoms = new Atom[100]; // TODO: allocate according to number of lines.
    
    BufferedReader br = null; InputStreamReader isr = null;
    
    try {
      FileInputStream is = new FileInputStream(MUCALC_FILE);
      isr = new InputStreamReader(is);
      br = new BufferedReader(isr);
    } catch (FileNotFoundException e) {
      // give up
      System.out.println("Cannot find atom library file. Have you deleted it?");
      
      e.printStackTrace();
      return;
    }        // Read in constants file, consider some kind of error checking
    
    String line;
    int i=0;
    int totalLines = 0;
    try {
      while ((line = br.readLine()) != null)
      {
        totalLines++;
        // ignore commented out lines.
        if (Character.toString(line.charAt(0)).equals("#"))
          continue;
        
  // array containing all those numbers from the calculator file
        String[] components = line.split("\t", -1);

        for (int j=0; j < components.length; j++)
        {
          // set components to -1 if they're empty, because otherwise Java gets upset.
          String component = components[j];
          if (component.equals(""))
            components[j] = "-1";
        }
        
        // Setting all the properties of the new atom.
        // component[x] where the values of x are in order as listed in the constants file.
        
        try
        {
          atoms[i] = new Atom(components[1], Integer.parseInt(components[0]));
          atoms[i].setAbsorptionEdges(Double.parseDouble(components[2]), Double.parseDouble(components[3]), Double.parseDouble(components[4]));
          atoms[i].setAbsorptionKEdgeCoeffs(Double.parseDouble(components[5]), Double.parseDouble(components[6]), Double.parseDouble(components[7]), Double.parseDouble(components[8]));
          atoms[i].setAbsorptionLEdgeCoeffs(Double.parseDouble(components[9]), Double.parseDouble(components[10]), Double.parseDouble(components[11]), Double.parseDouble(components[12]));
          atoms[i].setAbsorptionMEdgeCoeffs(Double.parseDouble(components[13]), Double.parseDouble(components[14]), Double.parseDouble(components[15]), Double.parseDouble(components[16]));
          atoms[i].setAbsorptionNEdgeCoeffs(Double.parseDouble(components[17]), Double.parseDouble(components[18]), Double.parseDouble(components[19]), Double.parseDouble(components[20]));
          atoms[i].setAtomicConstants(Double.parseDouble(components[21]), Double.parseDouble(components[22]), Double.parseDouble(components[23]));
          atoms[i].setCoherentScatteringCoeffs(Double.parseDouble(components[24]), Double.parseDouble(components[25]), Double.parseDouble(components[26]), Double.parseDouble(components[27]));
          atoms[i].setIncoherentScatteringCoeffs(Double.parseDouble(components[28]), Double.parseDouble(components[29]), Double.parseDouble(components[30]), Double.parseDouble(components[31]));
          atoms[i].setAlphaBetaEdges(Double.parseDouble(components[32]), Double.parseDouble(components[33]), Double.parseDouble(components[34]), Double.parseDouble(components[35]));
          atoms[i].setLsLJsFEKsFELs(Double.parseDouble(components[36]), Double.parseDouble(components[37]), Double.parseDouble(components[38]), Double.parseDouble(components[39]), Double.parseDouble(components[40]));
        }
        catch (NumberFormatException e)
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
      try {
        isr.close();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    
    atomCount = i;
  }
  
  /**
   * Q chop algorithm to quickly find an atom with a given atomic number Z.
   * * Your job is to check for a NULL return.
   */
  public Atom findAtomWithZ(double z)
  {
    int lower = 0;
    int higher = atomCount - 1;
    int new_bound = (higher + lower) / 2;
    
    if (z < atoms[lower].atomicNumber || z > atoms[higher].atomicNumber)
    {
      System.out.println("Warning: Atomic number asked for which is out of range.");
      return null;
    }
    
    while (atoms[new_bound].atomicNumber != z)
    {
      if (higher == lower + 1)
      {
        System.out.println("Warning: Atomic number within range but no data available for particular Z.");
        return null;
      }
      
      if (atoms[new_bound].atomicNumber > z)
        higher = new_bound;
      else if (atoms[new_bound].atomicNumber < z)
        lower = new_bound;
      
      new_bound = (higher + lower) / 2;
    }
    
    return atoms[new_bound];
  }
  
  /**
   * A bit of a slower algorithm to find atom with a given name. If you have the atomic number, use findAtomWithZ instead.
   * Your job is to check for a NULL return.
   * @param atomName
   * @return Atom object
   */
  public Atom findAtomWithName(String atomName)
  {
    for (int i=0; i < atoms.length; i++)
    {
      if (atoms[i].elementName.equals(atomName.toUpperCase()))
        return atoms[i];
    }
    
    System.out.println("Warning: Atom with name " + atomName + " cannot be found in atom dictionary.");
    return null;
  }
}
