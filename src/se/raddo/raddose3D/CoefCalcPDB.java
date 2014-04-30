package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import se.raddo.raddose3D.MuCalcConstantParser.Atom;

public class CoefCalcPDB extends CoefCalcCompute {

  public static class Residue {
    /** 3-letter code identifier found in PDB. */
    private String               identifier;
    /** Numbers of atoms per residue. */
    private int                  carbons, hydrogens, oxygens, nitrogens,
                                 sulphurs,
                                 phosphoruses, type;
    /** Static list which holds all residues */
    private static List<Residue> residueArray     = new ArrayList<Residue>();

    /** Alanine identifier. */
    private static final String  ALANINE_ID       = "ALA";
    /** Alanine no. of atoms per residue. */
    private static final int     ALANINE_C        = 3, ALANINE_H = 7,
                                                  ALANINE_N = 1, ALANINE_O = 2,
                                                  ALANINE_S = 0, ALANINE_P = 0;

    /** Arginine identifier. */
    private static final String  ARGININE_ID      = "ARG";
    /** Arginine no. of atoms per residue. */
    private static final int     ARGININE_C       = 6, ARGININE_H = 15,
                                                  ARGININE_N = 4,
                                                  ARGININE_O = 1,
                                                  ARGININE_S = 0,
                                                  ARGININE_P = 0;

    /** Asparagine identifier. */
    private static final String  ASPARAGINE_ID    = "ASN";
    /** Asparagine no. of atoms per residue. */
    private static final int     ASPARAGINE_C     = 4, ASPARAGINE_H = 8,
                                                  ASPARAGINE_N = 2,
                                                  ASPARAGINE_O = 2,
                                                  ASPARAGINE_S = 0,
                                                  ASPARAGINE_P = 0;

    /** Aspartate identifier. */
    private static final String  ASPARTATE_ID     = "ASP";
    /** Aspartate no. of atoms per residue. */
    private static final int     ASPARTATE_C      = 4, ASPARTATE_H = 5,
                                                  ASPARTATE_N = 1,
                                                  ASPARTATE_O = 3,
                                                  ASPARTATE_S = 0,
                                                  ASPARTATE_P = 0;

    /** Cysteine identifier. */
    private static final String  CYSTEINE_ID      = "CYS";
    /** Cysteine no. of atoms per residue. */
    private static final int     CYSTEINE_C       = 3, CYSTEINE_H = 5,
                                                  CYSTEINE_N = 1,
                                                  CYSTEINE_O = 1,
                                                  CYSTEINE_S = 1,
                                                  CYSTEINE_P = 0;

    /** Glutamine identifier. */
    private static final String  GLUTAMINE_ID     = "GLN";
    /** Glutamine no. of atoms per residue. */
    private static final int     GLUTAMINE_C      = 5, GLUTAMINE_H = 8,
                                                  GLUTAMINE_N = 2,
                                                  GLUTAMINE_O = 2,
                                                  GLUTAMINE_S = 0,
                                                  GLUTAMINE_P = 0;

    /** Glutamate identifier. */
    private static final String  GLUTAMATE_ID     = "GLU";
    /** Glutamate no. of atoms per residue. */
    private static final int     GLUTAMATE_C      = 5, GLUTAMATE_H = 7,
                                                  GLUTAMATE_N = 1,
                                                  GLUTAMATE_O = 3,
                                                  GLUTAMATE_S = 0,
                                                  GLUTAMATE_P = 0;

    /** Glycine identifier. */
    private static final String  GLYCINE_ID       = "GLY";
    /** Glycine no. of atoms per residue. */
    private static final int     GLYCINE_C        = 2, GLYCINE_H = 3,
                                                  GLYCINE_N = 1, GLYCINE_O = 1,
                                                  GLYCINE_S = 0, GLYCINE_P = 0;

    /** Histidine identifier. */
    private static final String  HISTIDINE_ID     = "HIS";
    /** Histidine no. of atoms per residue. */
    private static final int     HISTIDINE_C      = 6, HISTIDINE_H = 7,
                                                  HISTIDINE_N = 3,
                                                  HISTIDINE_O = 1,
                                                  HISTIDINE_S = 0,
                                                  HISTIDINE_P = 0;

    /** Isoleucine identifier. */
    private static final String  ISOLEUCINE_ID    = "ILE";
    /** Isoleucine no. of atoms per residue. */
    private static final int     ISOLEUCINE_C     = 6, ISOLEUCINE_H = 11,
                                                  ISOLEUCINE_N = 1,
                                                  ISOLEUCINE_O = 1,
                                                  ISOLEUCINE_S = 0,
                                                  ISOLEUCINE_P = 0;

    /** Leucine identifier. */
    private static final String  LEUCINE_ID       = "LEU";
    /** Leucine no. of atoms per residue. */
    private static final int     LEUCINE_C        = 6, LEUCINE_H = 11,
                                                  LEUCINE_N = 1, LEUCINE_O = 1,
                                                  LEUCINE_S = 0, LEUCINE_P = 0;

    /** Lysine identifier. */
    private static final String  LYSINE_ID        = "LYS";
    /** Lysine no. of atoms per residue. */
    private static final int     LYSINE_C         = 6, LYSINE_H = 12,
                                                  LYSINE_N = 2, LYSINE_O = 1,
                                                  LYSINE_S = 0, LYSINE_P = 0;

    /** Methionine identifier. */
    private static final String  METHIONINE_ID    = "MET";
    /** Methionine no. of atoms per residue. */
    private static final int     METHIONINE_C     = 5, METHIONINE_H = 9,
                                                  METHIONINE_N = 1,
                                                  METHIONINE_O = 1,
                                                  METHIONINE_S = 1,
                                                  METHIONINE_P = 0;

    /** Phenylalanine identifier. */
    private static final String  PHENYLALANINE_ID = "PHE";
    /** Phenylalanine no. of atoms per residue. */
    private static final int     PHENYLALANINE_C  = 9, PHENYLALANINE_H = 9,
                                                  PHENYLALANINE_N = 1,
                                                  PHENYLALANINE_O = 1,
                                                  PHENYLALANINE_S = 0,
                                                  PHENYLALANINE_P = 0;

    /** Proline identifier. */
    private static final String  PROLINE_ID       = "PRO";
    /** Proline no. of atoms per residue. */
    private static final int     PROLINE_C        = 5, PROLINE_H = 7,
                                                  PROLINE_N = 1, PROLINE_O = 1,
                                                  PROLINE_S = 0, PROLINE_P = 0;

    /** Serine identifier. */
    private static final String  SERINE_ID        = "SER";
    /** Serine no. of atoms per residue. */
    private static final int     SERINE_C         = 3, SERINE_H = 5,
                                                  SERINE_N = 1, SERINE_O = 2,
                                                  SERINE_S = 0, SERINE_P = 0;

    /** Threonine identifier. */
    private static final String  THREONINE_ID     = "THR";
    /** Threonine no. of atoms per residue. */
    private static final int     THREONINE_C      = 4, THREONINE_H = 7,
                                                  THREONINE_N = 1,
                                                  THREONINE_O = 2,
                                                  THREONINE_S = 0,
                                                  THREONINE_P = 0;

    /** tryptophan identifier. */
    private static final String  TRYPTOPHAN_ID    = "TRP";
    /** Tryptophan no. of atoms per residue. */
    private static final int     TRYPTOPHAN_C     = 11, TRYPTOPHAN_H = 10,
                                                  TRYPTOPHAN_N = 2,
                                                  TRYPTOPHAN_O = 1,
                                                  TRYPTOPHAN_S = 0,
                                                  TRYPTOPHAN_P = 0;

    /** tyrosine identifier. */
    private static final String  TYROSINE_ID      = "TYR";
    /** Tyrosine no. of atoms per residue. */
    private static final int     TYROSINE_C       = 9, TYROSINE_H = 9,
                                                  TYROSINE_N = 1,
                                                  TYROSINE_O = 2,
                                                  TYROSINE_S = 0,
                                                  TYROSINE_P = 0;

    /** valine identifier. */
    private static final String  VALINE_ID        = "VAL";
    /** Valine no. of atoms per residue. */
    private static final int     VALINE_C         = 5, VALINE_H = 9,
                                                  VALINE_N = 1, VALINE_O = 1,
                                                  VALINE_S = 0, VALINE_P = 0;

    /** AMP identifier. */
    private static final String  AMP_ID           = "  A";
    /** AMP no. of atoms per residue. */
    private static final int     AMP_C            = 10, AMP_H = 12, AMP_N = 5,
                                                  AMP_O = 6, AMP_S = 0,
                                                  AMP_P = 1;

    /** UMP identifier. */
    private static final String  UMP_ID           = "  U";
    /** UMP no. of atoms per residue. */
    private static final int     UMP_C            = 9, UMP_H = 11, UMP_N = 2,
                                                  UMP_O = 8, UMP_S = 0,
                                                  UMP_P = 1;

    /** GMP identifier. */
    private static final String  GMP_ID           = "  G";
    /** GMP no. of atoms per residue. */
    private static final int     GMP_C            = 10, GMP_H = 12, GMP_N = 5,
                                                  GMP_O = 7, GMP_S = 0,
                                                  GMP_P = 1;

    /** CMP identifier. */
    private static final String  CMP_ID           = "  C";
    /** CMP no. of atoms per residue. */
    private static final int     CMP_C            = 9, CMP_H = 12, CMP_N = 3,
                                                  CMP_O = 7, CMP_S = 0,
                                                  CMP_P = 1;

    /** dAMP identifier. */
    private static final String  DAMP_ID          = " DA";
    /** dAMP no. of atoms per residue. */
    private static final int     DAMP_C           = 10, DAMP_H = 12,
                                                  DAMP_N = 5, DAMP_O = 5,
                                                  DAMP_S = 0, DAMP_P = 1;

    /** dTMP identifier. */
    private static final String  DTMP_ID          = " DT";
    /** dTMP no. of atoms per residue. */
    private static final int     DTMP_C           = 10, DTMP_H = 11,
                                                  DTMP_N = 2, DTMP_O = 7,
                                                  DTMP_S = 0, DTMP_P = 1;

    /** dGMP identifier. */
    private static final String  DGMP_ID          = " DG";
    /** dGMP no. of atoms per residue. */
    private static final int     DGMP_C           = 10, DGMP_H = 12,
                                                  DGMP_N = 5, DGMP_O = 6,
                                                  DGMP_S = 0, DGMP_P = 1;

    /** dCMP identifier. */
    private static final String  DCMP_ID          = " DC";
    /** dCMP no. of atoms per residue. */
    private static final int     DCMP_C           = 9, DCMP_H = 12, DCMP_N = 3,
                                                  DCMP_O = 6, DCMP_S = 0,
                                                  DCMP_P = 1;

    /** identifiers for type of residue */
    private static final int     TYPE_PROTEIN     = 1, TYPE_RNA = 2,
        TYPE_DNA = 3;

    /**
     * Creates a residue with identifier and light atom constituents.
     * ID for RNA/DNA should have preceding spaces, e.g. " DA".
     * 
     * @param id PDB identifier
     * @param cs no. of carbons
     * @param hs no. of hydrogens
     * @param os no. of oxygens
     * @param ns no. of nitrogens
     * @param ss no. of sulphurs
     * @param ps no. of phosphoruses
     */
    public Residue(final String id, final int cs, final int hs, final int os,
        final int ns, final int ss, final int ps, final int typ) {
      identifier = id;
      carbons = cs;
      hydrogens = hs;
      oxygens = os;
      nitrogens = ns;
      sulphurs = ss;
      phosphoruses = ps;
      type = typ;

      residueArray.add(this);
    }

    /**
     * creates residue array which is added to the static library of residues
     * for future access.
     */
    public static void createResidueArray()
    {
      new Residue(ALANINE_ID, ALANINE_C, ALANINE_H, ALANINE_O, ALANINE_N,
          ALANINE_S, ALANINE_P, TYPE_PROTEIN);
      new Residue(ARGININE_ID, ARGININE_C, ARGININE_H, ARGININE_O, ARGININE_N,
          ARGININE_S, ARGININE_P, TYPE_PROTEIN);
      new Residue(ASPARAGINE_ID, ASPARAGINE_C, ASPARAGINE_H, ASPARAGINE_O,
          ASPARAGINE_N, ASPARAGINE_S, ASPARAGINE_P, TYPE_PROTEIN);
      new Residue(ASPARTATE_ID, ASPARTATE_C, ASPARTATE_H, ASPARTATE_O,
          ASPARTATE_N, ASPARTATE_S, ASPARTATE_P, TYPE_PROTEIN);
      new Residue(CYSTEINE_ID, CYSTEINE_C, CYSTEINE_H, CYSTEINE_O, CYSTEINE_N,
          CYSTEINE_S, CYSTEINE_P, TYPE_PROTEIN);
      new Residue(GLUTAMINE_ID, GLUTAMINE_C, GLUTAMINE_H, GLUTAMINE_O,
          GLUTAMINE_N, GLUTAMINE_S, GLUTAMINE_P, TYPE_PROTEIN);
      new Residue(GLUTAMATE_ID, GLUTAMATE_C, GLUTAMATE_H, GLUTAMATE_O,
          GLUTAMATE_N, GLUTAMATE_S, GLUTAMATE_P, TYPE_PROTEIN);
      new Residue(GLYCINE_ID, GLYCINE_C, GLYCINE_H, GLYCINE_O, GLYCINE_N,
          GLYCINE_S, GLYCINE_P, TYPE_PROTEIN);
      new Residue(HISTIDINE_ID, HISTIDINE_C, HISTIDINE_H, HISTIDINE_O,
          HISTIDINE_N, HISTIDINE_S, HISTIDINE_P, TYPE_PROTEIN);
      new Residue(ISOLEUCINE_ID, ISOLEUCINE_C, ISOLEUCINE_H, ISOLEUCINE_O,
          ISOLEUCINE_N, ISOLEUCINE_S, ISOLEUCINE_P, TYPE_PROTEIN);
      new Residue(LEUCINE_ID, LEUCINE_C, LEUCINE_H, LEUCINE_O, LEUCINE_N,
          LEUCINE_S, LEUCINE_P, TYPE_PROTEIN);
      new Residue(LYSINE_ID, LYSINE_C, LYSINE_H, LYSINE_O, LYSINE_N, LYSINE_S,
          LYSINE_P, TYPE_PROTEIN);
      new Residue(METHIONINE_ID, METHIONINE_C, METHIONINE_H, METHIONINE_O,
          METHIONINE_N, METHIONINE_S, METHIONINE_P, TYPE_PROTEIN);
      new Residue(PHENYLALANINE_ID, PHENYLALANINE_C, PHENYLALANINE_H,
          PHENYLALANINE_O, PHENYLALANINE_N, PHENYLALANINE_S, PHENYLALANINE_P,
          TYPE_PROTEIN);
      new Residue(PROLINE_ID, PROLINE_C, PROLINE_H, PROLINE_O, PROLINE_N,
          PROLINE_S, PROLINE_P, TYPE_PROTEIN);
      new Residue(SERINE_ID, SERINE_C, SERINE_H, SERINE_O, SERINE_N, SERINE_S,
          SERINE_P, TYPE_PROTEIN);
      new Residue(THREONINE_ID, THREONINE_C, THREONINE_H, THREONINE_O,
          THREONINE_N, THREONINE_S, THREONINE_P, TYPE_PROTEIN);
      new Residue(TRYPTOPHAN_ID, TRYPTOPHAN_C, TRYPTOPHAN_H, TRYPTOPHAN_O,
          TRYPTOPHAN_N, TRYPTOPHAN_S, TRYPTOPHAN_P, TYPE_PROTEIN);
      new Residue(TYROSINE_ID, TYROSINE_C, TYROSINE_H, TYROSINE_O, TYROSINE_N,
          TYROSINE_S, TYROSINE_P, TYPE_PROTEIN);
      new Residue(VALINE_ID, VALINE_C, VALINE_H, VALINE_O, VALINE_N, VALINE_S,
          VALINE_P, TYPE_PROTEIN);

      new Residue(AMP_ID, AMP_C, AMP_H, AMP_O, AMP_N, AMP_S, AMP_P, TYPE_RNA);
      new Residue(UMP_ID, UMP_C, UMP_H, UMP_O, UMP_N, UMP_S, UMP_P, TYPE_RNA);
      new Residue(GMP_ID, GMP_C, GMP_H, GMP_O, GMP_N, GMP_S, GMP_P, TYPE_RNA);
      new Residue(CMP_ID, CMP_C, CMP_H, CMP_O, CMP_N, CMP_S, CMP_P, TYPE_RNA);

      new Residue(DAMP_ID, DAMP_C, DAMP_H, DAMP_O, DAMP_N, DAMP_S, DAMP_P,
          TYPE_DNA);
      new Residue(DTMP_ID, DTMP_C, DTMP_H, DTMP_O, DTMP_N, DTMP_S, DTMP_P,
          TYPE_DNA);
      new Residue(DGMP_ID, DGMP_C, DGMP_H, DGMP_O, DGMP_N, DGMP_S, DGMP_P,
          TYPE_DNA);
      new Residue(DCMP_ID, DCMP_C, DCMP_H, DCMP_O, DCMP_N, DCMP_S, DCMP_P,
          TYPE_DNA);

    }

    /**
     * Static function which can take an ID and return the appropriate
     * residue from the holding array.
     * 
     * @param id identifier in PDB.
     * @return Residue object.
     */
    public static Residue residueWithName(final String id) {
      for (Residue r : residueArray) {
        if (r.identifier.equals(id)) {
          return r;
        }
      }

      return null;
    }
  }

  // these variables are important for PDB downloading and parsing.

  /**
   * PDB download link stem from pdb.org.
   */
  protected static final String PDB_DOWNLOAD_LINK         = "http://www.pdb.org/pdb/download/downloadFile.do?"
                                                              + "fileFormat=pdb&compression=NO&structureId=";

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

  /** Beginning of SEQRES residue names */

  protected static final int    SEQRES_START              = 19;

  /**
   * This parser extracts the unit cell details from the CRYST1 line.
   * 
   * @param inputLine PDB line
   */
  public void parseCryst1Line(String inputLine) {
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

      System.out.println("Number of monomers: " + numMonomers);
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
    double occupancy_num = 1;

    if (occupancy.length() == 0 && occupancyWarning == false) {
      System.out.println("Warning: occupancy for atom missing, "
          + "assuming occupancy of 1.0 (message only displayed once)");
      occupancyWarning = true;
    } else {
      try {
        occupancy_num = Double.parseDouble(occupancy);
      } catch (NumberFormatException e)
      {
        System.out.println("Warning: occupancy column "
            + "does not contain a valid number");
      }
    }

    if (elementSymbol.length() == 0) {
      System.out.println("Warning: element symbol for atom "
          + "is not present (displayed per atom)");
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
   * @param inputLine line from pdb
   */

  public void parseMatrixLine(final String inputLine) {

    String presentAlready = inputLine.substring(59, 60);

    if (presentAlready.equals("1")) {
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

    String symtry = inputLine.substring(13, 19);

    if (symtry.equals("SMTRY1")) {
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
    String occupancy = inputLine.substring(54, 60);
    String elementSymbol = inputLine.substring(76, 78);

    occupancy = occupancy.trim();
    elementSymbol = elementSymbol.trim();
    elementSymbol = elementSymbol.toUpperCase();
    String residueName = inputLine.substring(17, 20).trim().toUpperCase();

    if (residueName.equals("HOH")) {
      return;
    }

    double occupancyNum = this.checkOccupancyAndElementName(occupancy,
        elementSymbol, inputLine);

    Atom proteinAtom = parser.findAtomWithName(elementSymbol);
    proteinAtom.setMacromolecularOccurrence(proteinAtom
        .getMacromolecularOccurrence() + occupancyNum);
    proteinAtom.setHetatmOccurrence(proteinAtom.getHetatmOccurrence()
        + occupancyNum);
  }

  /**
   * Takes the SeqRes sequence and turns them into C/H/O/N/S
   * using the Residue nested class info. Adds them to the
   * parser's atom database.
   * 
   * @param inputLine line from PDB
   */
  public void parseSeqResLine(final String inputLine) {
    String sequenceOnly = inputLine.substring(19, inputLine.length());

    while (sequenceOnly.length() > 3) {
      String threeLetters = sequenceOnly.substring(0, 3);

      if (threeLetters.equals("   ")) {
        sequenceOnly = sequenceOnly.substring(4, sequenceOnly.length());
        continue;
      }

      Residue residue = Residue.residueWithName(threeLetters);

      if (residue == null) {
        System.out
            .println("Warning: could not decipher PDB three letter code: "
                + threeLetters);
        sequenceOnly = sequenceOnly.substring(4, sequenceOnly.length());
        continue;
      }

      if (residue.type == Residue.TYPE_PROTEIN) {
        numAminoAcids++;
      } else if (residue.type == Residue.TYPE_RNA) {
        numRNA++;
      } else if (residue.type == Residue.TYPE_DNA) {
        numDNA++;
      }

      Atom hydrogen = parser.findAtomWithName("H");
      Atom oxygen = parser.findAtomWithName("O");
      Atom carbon = parser.findAtomWithName("C");
      Atom nitrogen = parser.findAtomWithName("N");
      Atom phosphorus = parser.findAtomWithName("P");
      Atom sulphurs = parser.findAtomWithName("S");

      hydrogen.setMacromolecularOccurrence(residue.hydrogens
          + hydrogen.getMacromolecularOccurrence());
      oxygen.setMacromolecularOccurrence(residue.oxygens
          + oxygen.getMacromolecularOccurrence());
      carbon.setMacromolecularOccurrence(residue.carbons
          + carbon.getMacromolecularOccurrence());
      nitrogen.setMacromolecularOccurrence(residue.nitrogens
          + nitrogen.getMacromolecularOccurrence());
      phosphorus.setMacromolecularOccurrence(residue.phosphoruses
          + phosphorus.getMacromolecularOccurrence());
      sulphurs.setMacromolecularOccurrence(residue.sulphurs
          + sulphurs.getMacromolecularOccurrence());

      sequenceOnly = sequenceOnly.substring(4, sequenceOnly.length());
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
    String directive = inputLine.substring(0, 6);

    if (directive.equals("CRYST1")) {
      parseCryst1Line(inputLine);
      foundCryst1 = true;
    }

    if (directive.equals("HETATM")) {
      parseHetAtomLine(inputLine);
    }

    if (directive.equals("SEQRES")) {
      parseSeqResLine(inputLine);
    }

    if (directive.equals("REMARK")) {
      parseRemarkLine(inputLine);
    }

    if (directive.equals("MTRIX1")) {
      parseMatrixLine(inputLine);
    }
  }

  /**
   * Take into account the number of molecules in the unit cell; equal to NCS
   * symmetry operators multiplied
   * by CS symmetry operators.
   * 
   * @param num number of molecules in unit cell
   */
  public void multiplyAtoms(final int num) {
    for (int i = 0; i < parser.getAtomCount(); i++) {
      parser.getAtoms()[i].setMacromolecularOccurrence(parser.getAtoms()[i]
          .getMacromolecularOccurrence() * num);
      parser.getAtoms()[i].setHetatmOccurrence(parser.getAtoms()[i].getHetatmOccurrence()
          * num);
    }
  }

  /**
   * Taking the number of protein residues, RNA and DNA residues (from the TER
   * parsing results) and multiplying
   * these by average no. of hydrogen atoms per residue, then adding them to the
   * macromolecular occurrence
   * of hydrogen.
   */

  public void calculateHydrogens() {
    int hydrogens = 0;

    hydrogens += numAminoAcids * HYDROGENS_PER_AMINO_ACID;
    hydrogens += numRNA * HYDROGENS_PER_RNA_NUCLEOTIDE;
    hydrogens += numDNA * HYDROGENS_PER_DNA_NUCLEOTIDE;

    parser.findAtomWithZ(1).setMacromolecularOccurrence(hydrogens);
  }

  /**
   * Downloads PDB from http://www.pdb.org/ and initiates parsing of PDB line by
   * line.
   * 
   * @param pdbName PDB four letter code
   * @throws Exception exception
   */
  public void downloadPDB(String pdbName) throws Exception {
    String urlString = String.format("%s%s", PDB_DOWNLOAD_LINK, pdbName);
    URL pdbURL = new URL(urlString);
    URLConnection pdbConnection = pdbURL.openConnection();

    BufferedReader in = null;

    try {
      InputStreamReader isr = new InputStreamReader(
          pdbConnection.getInputStream());
      in = new BufferedReader(isr);
    } catch (FileNotFoundException e) {
      System.out.println("Error: Could not find PDB file " + pdbName
          + " on pdb.org");

      throw new FileNotFoundException();
    }

    String inputLine;

    while ((inputLine = in.readLine()) != null) {
      parsePDBLine(inputLine);
    }

    in.close();

    if (!foundCryst1) {
      System.out
          .println("Could not find CRYST1 line "
              + "containing unit cell information.");
      throw new Exception();
    }

    System.out.println("Crystallographic symmetry operators: "
        + csSymmetryOperators);
    System.out.println("Non-crystallographic symmetry operators: "
        + ncsSymmetryOperators);

    numMonomers = csSymmetryOperators * ncsSymmetryOperators;

    if (!foundHydrogen) {
      calculateHydrogens();
    }

    multiplyAtoms(numMonomers);

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

  public CoefCalcPDB(final String pdbCode) {
    String pdbName = pdbCode.toUpperCase();
    parser = new MuCalcConstantParser();

    Residue.createResidueArray();

    try {
      downloadPDB(pdbName);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      System.out.println("Could not find PDB file");
    } catch (Exception e) {
      System.out.println("Caught read-write exception");
    }
  }

  /**
   * constructor class which takes a pdbName and initiates downloading of PDB
   * and determination of atom constituents in the unit cell.
   * 
   * @param pdbName four letter PDB code
   */
  public CoefCalcPDB(String pdbName, List<String> heavySolvConcNames,
      List<Double> heavySolvConcNums) {
    pdbName = pdbName.toUpperCase();
    parser = new MuCalcConstantParser();

    Residue.createResidueArray();

    this.addSolventConcentrations(heavySolvConcNames, heavySolvConcNums);

    try {
      downloadPDB(pdbName);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
