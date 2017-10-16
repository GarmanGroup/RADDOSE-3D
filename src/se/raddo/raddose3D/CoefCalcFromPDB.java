package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * CoefCalcPDB class takes PDB information and converts
 * it into atom occurrence information which the superclass,
 * CoefCalcCompute, uses to calculate the absorption
 * coefficient.
 */
public class CoefCalcFromPDB extends CoefCalcCompute {

  /**
   * Found cryst1 line containing unit cell information?
   */
  private boolean            foundCryst1;

  /**
   * Found hetatm line containing hetatm information?
   */
  private boolean            foundHetatm;
  
  /**
   * Found seqres line containing residue sequence information?
   */
  private boolean            foundSeqres;
  
  /**
   * Found remark line containing symmetry operators for space group?
   */
  private boolean            foundRemark;
  
  /**
   * Found mtrix1 line containing non-crystallographic symmetry information?
   */
  private boolean            foundMtrix1;
  
  /**
   * Have we already warned the user that the occupancy column is empty?
   */
  private boolean            occupancyWarning;

  /**
   * How many crystallographic symmetry operators have we found?
   */
  private int                csSymmetryOperators;

  /**
   * How many non-crystallographic symmetry operators have we found?
   */
  private int                ncsSymmetryOperators      = 1;

  /**
   * Crystal unit cell dimension A - position in PDB file.
   */
  protected static final int CRYST1_A_POS              = 6;

  /**
   * Crystal unit cell dimension B - position in PDB file.
   */
  protected static final int CRYST1_B_POS              = 15;

  /**
   * Crystal unit cell dimension C - position in PDB file.
   */
  protected static final int CRYST1_C_POS              = 24;

  /**
   * Crystal unit cell angle alpha - position in PDB file.
   */
  protected static final int CRYST1_ALPHA_POS          = 33;

  /**
   * Crystal unit cell angle beta - position in PDB file.
   */
  protected static final int CRYST1_BETA_POS           = 40;

  /**
   * Crystal unit cell angle gamma - position in PDB file.
   */
  protected static final int CRYST1_GAMMA_POS          = 47;

  /**
   * Crystal unit cell definition end character.
   */
  protected static final int CRYST1_END_POS            = 54;

  /**
   * Atom residue name - position in PDB file.
   */
  protected static final int ATOM_RESIDUE_NAME_POS     = 17;
  /**
   * End of atom residue name - position in PDB file.
   */
  protected static final int ATOM_RESIDUE_NAME_END_POS = 20;
  /**
   * Atom residue number - position in PDB file.
   */
  protected static final int ATOM_RESIDUE_NUM_POS      = 22;

  /**
   * End of atom residue number - position in PDB file.
   */
  protected static final int ATOM_RESIDUE_NUM_END_POS  = 26;

  /** MATRX1 line - position of coordinates-included flag. */
  protected static final int MATRX_FLAG_POS            = 59;
  /** MATRX1 line - end position of coordinates-included flag. */
  protected static final int MATRX_FLAG_END_POS        = 60;

  /** SMTRY1 line position of SMTRY1 keyword. */
  protected static final int SMTRY1_POS                = 13;
  /** SMTRY1 line end position of SMTRY1 keyword. */
  protected static final int SMTRY1_END_POS            = 19;

  /** Start of the Occupancy field in PDB line. */
  protected static final int OCCUPANCY_POS             = 54;
  /** End of the Occupancy field in PDB line. */
  protected static final int OCCUPANCY_END_POS         = 60;

  /** Start of the Element symbol field in PDB line. */
  protected static final int ELEMENT_SYMBOL_POS        = 76;
  /** End of the Element symbol field in PDB line. */
  protected static final int ELEMENT_SYMBOL_END_POS    = 78;

  /** Beginning of SEQRES residue names. */
  protected static final int SEQRES_START              = 19;
  /** Length of SEQRES residue name. */
  protected static final int SEQRES_RESI_LENGTH        = 3;

  /** Directive first six characters - end pos. */
  protected static final int DIRECTIVE_END_POS         = 6;
  
  /**
   * Residue database keeping the atomic composition of all residues.
   */
  private ResidueDatabase      residueDB;

  // these variables are important for PDB downloading and parsing.

  /**
   * This parser extracts the unit cell details from the CRYST1 line.
   * 
   * @param inputLine PDB line
   */
  public void parseCryst1Line(final String inputLine) {
    String aString = inputLine.substring(CRYST1_A_POS, CRYST1_B_POS);
    String bString = inputLine.substring(CRYST1_B_POS, CRYST1_C_POS);
    String cString = inputLine.substring(CRYST1_C_POS, CRYST1_ALPHA_POS);
    String alphaString = inputLine
        .substring(CRYST1_ALPHA_POS, CRYST1_BETA_POS);
    String betaString = inputLine.substring(CRYST1_BETA_POS, CRYST1_GAMMA_POS);
    String gammaString = inputLine.substring(CRYST1_GAMMA_POS, CRYST1_END_POS);

    foundCryst1 = true;

    try {
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
      
      cellVolume(a, b, c, alpha, beta, gamma);
    } catch (NumberFormatException e) {
      System.out
          .println("Error: CRYST1 line could not"
              + " be parsed, cannot calculate"
              + " cell volume or find Z value.%nCheck CRYST1"
              + " line follows standard input format.");
      foundCryst1 = false;
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
      final String elementSymbol, final String inputLine) {
    double occupancyNum = 1;

    if (occupancy.length() == 0 && !occupancyWarning) {
      System.out.println("Warning: occupancy for atom missing, "
          + "assuming occupancy of 1.0 (message only displayed once)");
      occupancyWarning = true;
    } else {
      try {
        occupancyNum = Double.parseDouble(occupancy);
      } catch (NumberFormatException e) {
        System.out.println("Warning: occupancy column "
            + "does not contain a valid number");
      }
    }

    if (elementSymbol.length() == 0) {
      System.out.println("Warning: element symbol for atom "
          + "is not present (displayed per atom)");
      System.out.println("For line: " + inputLine);
    }

    return occupancyNum;
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
   * @param inputLine line from pdb
   */

  public void parseMatrixLine(final String inputLine) {

    String presentAlready = inputLine.substring(MATRX_FLAG_POS,
        MATRX_FLAG_END_POS);

    if ("1".equals(presentAlready)) {
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
   * @param inputLine line from pdb
   */

  public void parseRemarkLine(final String inputLine) {

    String symtry = inputLine.substring(SMTRY1_POS, SMTRY1_END_POS);

    if ("SMTRY1".equals(symtry)) {
      csSymmetryOperators++;
    }
  }

  /**
   * Heteroatoms which are NOT water are added to the macromolecule. Any HOH
   * heteroatom is ignored.
   * 
   * @param inputLine line from pdb
   */

  public void parseHetAtomLine(final String inputLine) {
    String occupancy = inputLine.substring(OCCUPANCY_POS, OCCUPANCY_END_POS);
    String elementSymbol = inputLine.substring(ELEMENT_SYMBOL_POS,
        ELEMENT_SYMBOL_END_POS);

    occupancy = occupancy.trim();
    elementSymbol = elementSymbol.trim();
    elementSymbol = elementSymbol.toUpperCase();
    String residueName = inputLine
        .substring(ATOM_RESIDUE_NAME_POS, ATOM_RESIDUE_NAME_END_POS).trim()
        .toUpperCase();

    if ("HOH".equals(residueName)) {
      return;
    }

    double occupancyNum = this.checkOccupancyAndElementName(occupancy,
        elementSymbol, inputLine);

    Element proteinAtom = this.getParser().getElement(elementSymbol);
    incrementMacromolecularOccurrence(proteinAtom, occupancyNum);
    incrementHetatmOccurrence(proteinAtom, occupancyNum);

  }

  /**
   * Takes the SeqRes sequence and turns them into C/H/O/N/S
   * using the Residue nested class info. Adds them to the
   * parser's atom database.
   * 
   * @param inputLine line from PDB
   */
  @SuppressWarnings("static-access")
  public void parseSeqResLine(final String inputLine) {
    String sequenceOnly = inputLine.substring(SEQRES_START, inputLine.length());

    while (sequenceOnly.length() > 3) {
      String threeLetters = sequenceOnly.substring(0, SEQRES_RESI_LENGTH);

      if (threeLetters.equals("   ")) {
        sequenceOnly = sequenceOnly.substring(SEQRES_RESI_LENGTH + 1,
            sequenceOnly.length());
        continue;
      }
      
      residueDB = ResidueDatabase.getInstance();
      Residue residue = residueDB.getResidue(threeLetters);

      if (residue == null) {
        System.out
            .println("Warning: could not decipher PDB three letter code: "
                + threeLetters);
        sequenceOnly = sequenceOnly.substring(SEQRES_RESI_LENGTH + 1,
            sequenceOnly.length());
        continue;
      }

      if (residue.getResidueType() == ResidueDatabase.TYPE_PROTEIN) {
        this.incrementNumAminoAcids(1);
      } else if (residue.getResidueType() == ResidueDatabase.TYPE_RNA) {
        this.incrementNumRNA(1);
      } else if (residue.getResidueType() == ResidueDatabase.TYPE_DNA) {
        this.incrementNumDNA(1);
      }

      Element hydrogen = this.getParser().getElement("H");
      Element oxygen = this.getParser().getElement("O");
      Element carbon = this.getParser().getElement("C");
      Element nitrogen = this.getParser().getElement("N");
      Element phosphorus = this.getParser().getElement("P");
      Element sulphurs = this.getParser().getElement("S");
      Element seleniums = this.getParser().getElement("SE");     
      
      setMacromolecularOccurrence(hydrogen, residue.getHydrogens()
          + getMacromolecularOccurrence(hydrogen));
      setMacromolecularOccurrence(oxygen, residue.getOxygens()
          + getMacromolecularOccurrence(oxygen));
      setMacromolecularOccurrence(carbon, residue.getCarbons()
          + getMacromolecularOccurrence(carbon));
      setMacromolecularOccurrence(nitrogen, residue.getNitrogens()
          + getMacromolecularOccurrence(nitrogen));
      setMacromolecularOccurrence(phosphorus, residue.getPhosphoruses()
          + getMacromolecularOccurrence(phosphorus));
      setMacromolecularOccurrence(sulphurs, residue.getSulphurs()
          + getMacromolecularOccurrence(sulphurs));
      setMacromolecularOccurrence(seleniums, residue.getSeleniums()
          + getMacromolecularOccurrence(seleniums));
      
      sequenceOnly = sequenceOnly.substring(SEQRES_RESI_LENGTH + 1,
          sequenceOnly.length());
    }
  }

  /**
   * Parent function which takes each line, examines first six characters and
   * sends the line to
   * the appropriate parsing function accordingly.
   * 
   * @param inputLine line from pdb
   */
  public void parsePDBLine(final String inputLine) {
    String directive = inputLine.substring(0, DIRECTIVE_END_POS);
    // TODO: a check that the parsing was successful in lines other than CRYST1?

    if ("CRYST1".equals(directive)) {
      parseCryst1Line(inputLine);
      foundCryst1 = true;
    }

    if ("HETATM".equals(directive)) {
      parseHetAtomLine(inputLine);
      foundHetatm = true;
    } else {
      foundHetatm = false;
    }

    if ("SEQRES".equals(directive)) {
      parseSeqResLine(inputLine);
      foundSeqres = true;
    } else {
      foundSeqres = false;
    }

    if ("REMARK".equals(directive)) {
      parseRemarkLine(inputLine);
      foundRemark = true;
    } else {
      foundRemark = false;
    }

    if ("MTRIX1".equals(directive)) {
      parseMatrixLine(inputLine);
      foundMtrix1 = true;
    }
    else {
      foundMtrix1 = false;
    }
  }

  /**
   * Downloads PDB from http://www.pdb.org/ and initiates parsing of PDB line by
   * line.
   * 
   * @param pdbName PDB four letter code
   */
  public void downloadPDB(final String pdbName) {
    String urlString = getPDBURL(pdbName);

    URL pdbURL = null;
    URLConnection pdbConnection = null;

    try {
      pdbURL = new URL(urlString);
    } catch (MalformedURLException e) {
      System.out.println("URL " + urlString + " is malformed.");
    }
    try {
      pdbConnection = pdbURL.openConnection();
    } catch (IOException e) {
      System.out.println("Cannot read from URL.");
    }

    BufferedReader in = null;

    InputStreamReader isr = null;
    try {
      isr = new InputStreamReader(
          pdbConnection.getInputStream());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("Cannot read from URL.");
    }
    in = new BufferedReader(isr);

    String inputLine;

    try {
      while ((inputLine = in.readLine()) != null) {
        parsePDBLine(inputLine);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("Cannot read from URL.");
      e.printStackTrace();
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Line length error encounted in URL line");
      e.printStackTrace();
    }
    
    try {
      in.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (!foundCryst1) {
      System.out
          .println("Could not find CRYST1 line "
              + "containing unit cell information.");
    }
    
    if (!foundCryst1 && !foundHetatm && !foundSeqres && 
        !foundRemark && !foundMtrix1) {
      System.out
          .println("No neccessary information found in URL - "
              + "There seems to be something wrong with URL.");
    }
      
    System.out.println("Crystallographic symmetry operators: "
        + csSymmetryOperators);
    System.out.println("Non-crystallographic symmetry operators: "
        + ncsSymmetryOperators);

    this.setNumMonomers(csSymmetryOperators * ncsSymmetryOperators);

    multiplyAtoms(this.getNumMonomers());
    System.out.println("Number of monomers: " + this.getNumMonomers());

    double solventFraction = calculateSolventFractionFromNums();
    calculateSolventWater(solventFraction);
  }

  /**
   * constructor class which takes a pdbName and initiates downloading of PDB
   * and determination
   * of atom constituents in the unit cell.
   * 
   * @param pdbCode four letter PDB code
   */
  public CoefCalcFromPDB(final String pdbCode) {
    String pdbName = pdbCode.toUpperCase();

    downloadPDB(pdbName);
  }

  /**
   * constructor class which takes a pdbName and initiates downloading of PDB
   * and determination of atom constituents in the unit cell.
   * 
   * @param pdbName four letter PDB code
   * @param heavySolvConcNames solvent element names
   * @param heavySolvConcNums solvent concentrations in mM.
   */
  public CoefCalcFromPDB(final String pdbName,
      final List<String> heavySolvConcNames,
      final List<Double> heavySolvConcNums) {
    String pdbNameUpperCase = pdbName.toUpperCase();

    this.addSolventConcentrations(heavySolvConcNames, heavySolvConcNums);

    try {
      downloadPDB(pdbNameUpperCase);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Generate a URL to a given PDB structure file at pdb.org.
   * 
   * @param pdbName
   *          The 4-character PDB entry name
   * @return
   *         A URL pointing to the PDB text file of the given PDB entry.
   */
  private String getPDBURL(final String pdbName) {
    return String.format("http://www.pdb.org/pdb/download/downloadFile.do?"
        + "fileFormat=pdb&compression=NO&structureId=%s", pdbName);
  }
}
