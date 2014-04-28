package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class CoefCalcPDB extends CoefCalcCompute
{

  // these variables are important for PDB downloading and parsing.

  /**
   * PDB download link stem from pdb.org
   */
  protected static final String PDB_DOWNLOAD_LINK         = "http://www.pdb.org/pdb/download/downloadFile.do?fileFormat=pdb&compression=NO&structureId=";

  /**
   * Found cryst1 line containing unit cell information?
   */
  private boolean               foundCryst1               = false;

  /**
   * Have we already warned the user that the occupancy column is empty?
   */
  private boolean               occupancyWarning          = false;

  /**
   * Have we found hydrogens in the atomic coordinates? (will be deleted later
   * when using SEQRES lines)
   */
  private boolean               foundHydrogen             = false;

  /**
   * Was the last thing we found a TER line? (will be deleted later when using
   * SEQRES lines)
   */
  private boolean               foundTer                  = true;

  /**
   * What was the starting residue of the first ATOM line? (will be deleted
   * later when using SEQRES lines)
   */
  private int                   startResidue              = -1;

  /**
   * How many crystallographic symmetry operators have we found?
   */
  private int                   csSymmetryOperators       = 0;

  /**
   * How many non-crystallographic symmetry operators have we found?
   */
  private int                   ncsSymmetryOperators      = 1;

  /**
   * Crystal unit cell dimension A - position in PDB file.
   */
  protected static final int    CRYST1_A_POS              = 6;

  /**
   * Crystal unit cell dimension B - position in PDB file.
   */
  protected static final int    CRYST1_B_POS              = 15;

  /**
   * Crystal unit cell dimension C - position in PDB file.
   */
  protected static final int    CRYST1_C_POS              = 24;

  /**
   * Crystal unit cell angle alpha - position in PDB file.
   */
  protected static final int    CRYST1_ALPHA_POS          = 33;

  /**
   * Crystal unit cell angle beta - position in PDB file.
   */
  protected static final int    CRYST1_BETA_POS           = 40;

  /**
   * Crystal unit cell angle gamma - position in PDB file.
   */
  protected static final int    CRYST1_GAMMA_POS          = 47;

  /**
   * Crystal unit cell definition end character.
   */
  protected static final int    CRYST1_END_POS            = 54;

  /**
   * Atom residue name - position in PDB file.
   */
  protected static final int    ATOM_RESIDUE_NAME_POS     = 17;
  /**
   * End of atom residue name - position in PDB file.
   */
  protected static final int    ATOM_RESIDUE_NAME_END_POS = 20;
  /**
   * Atom residue number - position in PDB file.
   */
  protected static final int    ATOM_RESIDUE_NUM_POS      = 22;

  /**
   * End of atom residue number - position in PDB file.
   */
  protected static final int    ATOM_RESIDUE_NUM_END_POS  = 26;

  /**
   * This parser extracts the unit cell details from the CRYST1 line.
   * 
   * @param inputLine PDB line
   */
  public void parseCryst1Line(String inputLine)
  {
    String aString = inputLine.substring(CRYST1_A_POS, CRYST1_B_POS);
    String bString = inputLine.substring(CRYST1_B_POS, CRYST1_C_POS);
    String cString = inputLine.substring(CRYST1_C_POS, CRYST1_ALPHA_POS);
    String alphaString = inputLine
        .substring(CRYST1_ALPHA_POS, CRYST1_BETA_POS);
    String betaString = inputLine.substring(CRYST1_BETA_POS, CRYST1_GAMMA_POS);
    String gammaString = inputLine.substring(CRYST1_GAMMA_POS, CRYST1_END_POS);

    foundCryst1 = true;

    try
    {
      double a = Double.parseDouble(aString);
      double b = Double.parseDouble(bString);
      double c = Double.parseDouble(cString);
      double alpha = Double.parseDouble(alphaString);
      double beta = Double.parseDouble(betaString);
      double gamma = Double.parseDouble(gammaString);

      // Z value is often mis-calculated in the PDB so we're
      // going to have to ignore these lines. Tends to be mis-calculated
      // when there is non-crystallographic symmetry involved
      // Instead this will be parsed by the REMARK 290 and MTRIX lines.

      //   numMonomers = Integer.parseInt(z_value);
      //   numMonomers = 9;

      System.out.println("PDB file unit cell: " + a + " " + b + " "
          + c + " " + alpha + " " + beta + " " + gamma);

      System.out.println("Number of monomers: " + numMonomers);
      cellVolume(a, b, c, alpha, beta, gamma);
    } catch (NumberFormatException e)
    {
      System.out
          .println("Error: CRYST1 line could not"
              + " be parsed, cannot calculate"
              + " cell volume or find Z value.%nCheck CRYST1"
              + " line follows standard input format.");
      foundCryst1 = false;
    }
  }

  /**
   * after finding a Ter, program calculates length of chain
   * from ending and starting chain numbers. This includes disordered residues
   * which, of course, are still present, just not visible
   * and will contribute to absorption coefficients.
   * RNA residues are A U G C, DNA residues are DA DT DG DC,
   * which are used to identify the type of macromolecule.
   * The number of residues are then added to one of the three bins accordingly.
   * These numbers are used to generate hydrogens if hydrogens
   * have not been found in the ATOM lines.
   * 
   * @param inputLine
   */
  public void parseTerLine(final String inputLine)
  {
    foundTer = true; // so ATOM line knows to start 
                     // recording first residue number again on the next line.

    String residueName = inputLine.substring(ATOM_RESIDUE_NAME_POS,
        ATOM_RESIDUE_NAME_END_POS);

    String residueNumString = inputLine.substring(ATOM_RESIDUE_NUM_POS,
        ATOM_RESIDUE_NUM_END_POS);

    residueName = residueName.trim().toUpperCase();

    residueNumString = residueNumString.trim();

    int residueNumber = -1;

    try
    {
      residueNumber = Integer.parseInt(residueNumString);
    } catch (NumberFormatException e)
    {
      System.out.println("Warning: TER column does not contain a"
          + "valid residue number, will throw calculation off");
    }

    int totalResidues = residueNumber - startResidue + 1;

    if (totalResidues < 0)
    {
      System.out.println("Warning: Calculated a negative number"
          + "of residues, will throw calculation off");
    }

    if (residueName.equals("G") || residueName.equals("A")
        || residueName.equals("C") || residueName.equals("U"))
    {
      // this means it is RNA

      numRNA += totalResidues;

    }
    else if (residueName.equals("DG") || residueName.equals("DA")
        || residueName.equals("DC") || residueName.equals("DT"))
    {
      // this means it is DNA

      numDNA += totalResidues;

    }
    else
    {
      // this means it is protein

      numAminoAcids += totalResidues;
    }
  }

  /**
   * Sanity check on occupancies & element name.
   * 
   * @param occupancy occupancy as passed as string from PDB
   * @param elementSymbol elementSymbol as passed as string from PDB
   * @param inputLine line from PDB
   * @return occupancy of atom
   */
  public double checkOccupancyAndElementName(final String occupancy,
      final String elementSymbol, final String inputLine)
  {
    double occupancy_num = 1;

    if (occupancy.length() == 0 && occupancyWarning == false)
    {
      System.out
          .println("Warning: occupancy for atom missing, assuming occupancy of 1.0 (message only displayed once)");
      occupancyWarning = true;
    }
    else
    {
      try
      {
        occupancy_num = Double.parseDouble(occupancy);
      } catch (NumberFormatException e)
      {
        System.out
            .println("Warning: occupancy column does not contain a valid number");
      }
    }

    if (elementSymbol.length() == 0)
    {
      System.out
          .println("Warning: element symbol for atom is not present (displayed per atom)");
      System.out.println("For line: " + inputLine);
    }

    return occupancy_num;
  }

  /**
   * NCS operators are at the end of the header, but sometimes the coordinates
   * of these extra molecules
   * are already present in the coordinate file. So we need to check the
   * property of this flag and add
   * an NCS operator if the flag is not set.
   * Default value of NCS operator is 1 so the identity matrix is already
   * covered.
   * 
   * @param inputLine
   */

  public void parseMatrixLine(String inputLine)
  {

    String presentAlready = inputLine.substring(59, 60);

    if (presentAlready.equals("1"))
    {
      System.out.println("Ignoring NCS entry");
      return;
    }

    ncsSymmetryOperators++;
  }

  /**
   * need to pay special attention to SMTRY1 as these will contain symmetry
   * operators for the space group.
   * these are then multiplied by the non-crystallographic symmetry (as picked
   * up by the MTRIX1 lines)
   * in order to calculate number of asymmetric units in the unit cell.
   * 
   * @param inputLine
   */

  public void parseRemarkLine(String inputLine)
  {

    String symtry = inputLine.substring(13, 19);

    if (symtry.equals("SMTRY1"))
      csSymmetryOperators++;
  }

  /**
   * Heteroatoms which are NOT water are added to the macromolecule. Any HOH
   * heteroatom is ignored.
   * 
   * @param inputLine
   */

  public void parseHetAtomLine(String inputLine)
  {
    String occupancy = inputLine.substring(54, 60);
    String elementSymbol = inputLine.substring(76, 78);

    occupancy = occupancy.trim();
    elementSymbol = elementSymbol.trim();
    elementSymbol = elementSymbol.toUpperCase();
    String residueName = inputLine.substring(17, 20).trim().toUpperCase();

    if (residueName.equals("HOH"))
      return;

    double occupancy_num = this.checkOccupancyAndElementName(occupancy,
        elementSymbol, inputLine);

    Atom proteinAtom = parser.findAtomWithName(elementSymbol);
    proteinAtom.macromolecularOccurrence += occupancy_num;
    proteinAtom.hetatmOccurrence += occupancy_num;
  }

  /**
   * each atom from the ATOM (protein) section is checked for element name
   * (final column)
   * and added to the protein heavy atom list as necessary.
   * if any hydrogen atom is found in this section, foundHydrogen is set to true
   * and
   * the algorithm will not add extra hydrogens. Otherwise the number of
   * hydrogens will
   * be estimated from the total number of protein residues, RNA residues and
   * DNA residues later.
   * 
   * @param inputLine
   */

  public void parseAtomLine(String inputLine)
  {

    String occupancy = inputLine.substring(54, 60);
    String elementSymbol = inputLine.substring(76, 78);

    occupancy = occupancy.trim();
    elementSymbol = elementSymbol.trim();
    elementSymbol = elementSymbol.toUpperCase();

    double occupancy_num = this.checkOccupancyAndElementName(occupancy,
        elementSymbol, inputLine);

    if (elementSymbol.equals("H") && !foundHydrogen)
    {
      foundHydrogen = true;
      System.out
          .println("Hydrogens have been found under ATOM labels in the PDB file, so hydrogens will not be added separately.");
    }

    if (foundTer)
    {
      foundTer = false;
      String residueNumberString = inputLine.substring(22, 26);
      residueNumberString = residueNumberString.trim();

      int residueNumber = -1;

      try
      {
        residueNumber = Integer.parseInt(residueNumberString);
        startResidue = residueNumber;
      } catch (NumberFormatException e)
      {
        System.out
            .println("Warning: starting residue number for macromolecular chain could not be found");
      }
    }

    Atom proteinAtom = parser.findAtomWithName(elementSymbol);
    proteinAtom.macromolecularOccurrence += occupancy_num;
  }

  /**
   * Parent function which takes each line, examines first six characters and
   * sends the line to
   * the appropriate parsing function accordingly.
   * 
   * @param inputLine
   */
  public void parsePDBLine(String inputLine)
  {
    String directive = inputLine.substring(0, 6);

    if (directive.equals("CRYST1"))
    {
      parseCryst1Line(inputLine);
      foundCryst1 = true;
    }

    if (directive.equals("ATOM  "))
      parseAtomLine(inputLine);

    if (directive.equals("HETATM"))
      parseHetAtomLine(inputLine);

    if (directive.equals("TER   "))
      parseTerLine(inputLine);

    if (directive.equals("REMARK"))
      parseRemarkLine(inputLine);

    if (directive.equals("MTRIX1"))
      parseMatrixLine(inputLine);
  }

  /**
   * Take into account the number of molecules in the unit cell; equal to NCS
   * symmetry operators multiplied
   * by CS symmetry operators.
   * 
   * @param num - number of molecules in unit cell
   */
  public void multiplyAtoms(int num)
  {
    for (int i = 0; i < parser.atomCount; i++)
    {
      parser.atoms[i].macromolecularOccurrence *= num;
      parser.atoms[i].hetatmOccurrence *= num;
    }
  }

  /**
   * Taking the number of protein residues, RNA and DNA residues (from the TER
   * parsing results) and multiplying
   * these by average no. of hydrogen atoms per residue, then adding them to the
   * macromolecular occurrence
   * of hydrogen.
   */

  public void calculateHydrogens()
  {
    int hydrogens = 0;

    hydrogens += numAminoAcids * 8;
    hydrogens += numRNA * 11.25;
    hydrogens += numDNA * 11.75;

    parser.findAtomWithZ(1).macromolecularOccurrence = hydrogens;
  }

  /**
   * Downloads PDB from http://www.pdb.org/ and initiates parsing of PDB line by
   * line.
   * 
   * @param pdbName
   * @throws Exception
   */
  public void downloadPDB(String pdbName) throws Exception
  {
    String URLString = String.format("%s%s", PDB_DOWNLOAD_LINK, pdbName);
    URL pdbURL = new URL(URLString);
    URLConnection pdbConnection = pdbURL.openConnection();

    BufferedReader in = null;

    try
    {
      InputStreamReader isr = new InputStreamReader(
          pdbConnection.getInputStream());
      in = new BufferedReader(isr);
    } catch (FileNotFoundException e)
    {
      System.out.println("Error: Could not find PDB file " + pdbName
          + " on pdb.org");

      throw new FileNotFoundException();
    }

    String inputLine;

    while ((inputLine = in.readLine()) != null)
    {
      parsePDBLine(inputLine);
    }

    in.close();

    if (!foundCryst1)
    {
      System.out
          .println("Could not find CRYST1 line containing unit cell information.");
      throw new Exception();
    }

    System.out.println("Crystallographic symmetry operators: "
        + csSymmetryOperators);
    System.out.println("Non-crystallographic symmetry operators: "
        + ncsSymmetryOperators);

    numMonomers = csSymmetryOperators * ncsSymmetryOperators;

    if (!foundHydrogen)
      calculateHydrogens();

    multiplyAtoms(numMonomers);

    double solventFraction = calculateSolventFractionFromNums();
    calculateSolventWater(solventFraction);
  }

  /**
   * constructor class which takes a pdbName and initiates downloading of PDB
   * and determination
   * of atom constituents in the unit cell.
   * 
   * @param pdbName
   */

  public CoefCalcPDB(String pdbName)
  {
    pdbName = pdbName.toUpperCase();
    parser = new MuCalcConstantParser();

    try {
      downloadPDB(pdbName);
    } catch (FileNotFoundException e)
    {
      // TODO Auto-generated catch block

    } catch (Exception e)
    {

    }
  }

  /**
   * constructor class which takes a pdbName and initiates downloading of PDB
   * and determination of atom constituents in the unit cell.
   * 
   * @param pdbName
   */
  public CoefCalcPDB(String pdbName, List<String> heavySolvConcNames,
      List<Double> heavySolvConcNums)
  {
    pdbName = pdbName.toUpperCase();
    parser = new MuCalcConstantParser();

    this.addSolventConcentrations(heavySolvConcNames, heavySolvConcNums);

    try {
      downloadPDB(pdbName);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
