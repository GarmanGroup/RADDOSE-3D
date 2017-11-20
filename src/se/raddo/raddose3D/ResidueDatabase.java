package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;
/**
 * The Residue Database looks after the 20 amino acids, 4 RNA bases and
 * 4 DNA bases and provides their C/H/N/O/S/P content.
 * This class is a Singleton. Its constructor is therefore not publicly
 * accessible. To obtain an instance of this class, call the getInstance()
 * function.
 */
public class ResidueDatabase {

  /** Reference to the singleton instance of ResidueDatabase. */
  private static ResidueDatabase     singleton           = null;

  /** Map of all Residue objects in the database. */
  private final Map<String, Residue> residues;

  /** Alanine identifier. */
  private static final String        ALANINE_ID          = "ALA";
  /** Alanine no. of atoms per residue. */
  private static final int           ALANINE_C           = 3, ALANINE_H = 7,
                                                         ALANINE_N = 1,
                                                         ALANINE_O = 2,
                                                         ALANINE_S = 0,
      ALANINE_P = 0;
  /** Alanine molecular weight (g/mol). */
  private static final double           ALANINE_MW           = 89.09;

  /** Arginine identifier. */
  private static final String        ARGININE_ID         = "ARG";
  /** Arginine no. of atoms per residue. */
  private static final int           ARGININE_C          = 6, ARGININE_H = 15,
                                                         ARGININE_N = 4,
                                                         ARGININE_O = 1,
                                                         ARGININE_S = 0,
                                                         ARGININE_P = 0;
  /** Arginine molecular weight (g/mol). */
  private static final double           ARGININE_MW           = 174.20;

  /** Asparagine identifier. */
  private static final String        ASPARAGINE_ID       = "ASN";
  /** Asparagine no. of atoms per residue. */
  private static final int           ASPARAGINE_C        = 4, ASPARAGINE_H = 8,
                                                         ASPARAGINE_N = 2,
                                                         ASPARAGINE_O = 2,
                                                         ASPARAGINE_S = 0,
                                                         ASPARAGINE_P = 0;
  /** Asparagine molecular weight (g/mol). */
  private static final double           ASPARAGINE_MW           = 132.12;

  /** Aspartate identifier. */
  private static final String        ASPARTATE_ID        = "ASP";
  /** Aspartate no. of atoms per residue. */
  private static final int           ASPARTATE_C         = 4, ASPARTATE_H = 5,
                                                         ASPARTATE_N = 1,
                                                         ASPARTATE_O = 3,
                                                         ASPARTATE_S = 0,
                                                         ASPARTATE_P = 0;
  /** Aspartate molecular weight (g/mol). */
  private static final double           ASPARTATE_MW           = 133.10;

  /** Cysteine identifier. */
  private static final String        CYSTEINE_ID         = "CYS";
  /** Cysteine no. of atoms per residue. */
  private static final int           CYSTEINE_C          = 3, CYSTEINE_H = 5,
                                                         CYSTEINE_N = 1,
                                                         CYSTEINE_O = 1,
                                                         CYSTEINE_S = 1,
                                                         CYSTEINE_P = 0;
  /** Cysteine molecular weight (g/mol). */
  private static final double           CYSTEINE_MW           = 121.16;

  /** Glutamine identifier. */
  private static final String        GLUTAMINE_ID        = "GLN";
  /** Glutamine no. of atoms per residue. */
  private static final int           GLUTAMINE_C         = 5, GLUTAMINE_H = 8,
                                                         GLUTAMINE_N = 2,
                                                         GLUTAMINE_O = 2,
                                                         GLUTAMINE_S = 0,
                                                         GLUTAMINE_P = 0;
  /** Glutamine molecular weight (g/mol). */
  private static final double           GLUTAMINE_MW           = 146.15;

  /** Glutamate identifier. */
  private static final String        GLUTAMATE_ID        = "GLU";
  /** Glutamate no. of atoms per residue. */
  private static final int           GLUTAMATE_C         = 5, GLUTAMATE_H = 7,
                                                         GLUTAMATE_N = 1,
                                                         GLUTAMATE_O = 3,
                                                         GLUTAMATE_S = 0,
                                                         GLUTAMATE_P = 0;
  /** Glutamate molecular weight (g/mol). */
  private static final double           GLUTAMATE_MW           = 147.13;

  /** Glycine identifier. */
  private static final String        GLYCINE_ID          = "GLY";
  /** Glycine no. of atoms per residue. */
  private static final int           GLYCINE_C           = 2, GLYCINE_H = 3,
                                                         GLYCINE_N = 1,
                                                         GLYCINE_O = 1,
                                                         GLYCINE_S = 0,
                                                         GLYCINE_P = 0;
  /** Glycine molecular weight (g/mol). */
  private static final double           GLYCINE_MW           = 75.07;

  /** Histidine identifier. */
  private static final String        HISTIDINE_ID        = "HIS";
  /** Histidine no. of atoms per residue. */
  private static final int           HISTIDINE_C         = 6, HISTIDINE_H = 7,
                                                         HISTIDINE_N = 3,
                                                         HISTIDINE_O = 1,
                                                         HISTIDINE_S = 0,
                                                         HISTIDINE_P = 0;
  /** Histidine molecular weight (g/mol). */
  private static final double           HISTIDINE_MW           = 155.16;

  /** Isoleucine identifier. */
  private static final String        ISOLEUCINE_ID       = "ILE";
  /** Isoleucine no. of atoms per residue. */
  private static final int           ISOLEUCINE_C        = 6,
                                                        ISOLEUCINE_H = 11,
                                                        ISOLEUCINE_N = 1,
                                                        ISOLEUCINE_O = 1,
                                                        ISOLEUCINE_S = 0,
                                                        ISOLEUCINE_P = 0;
  /** Isoleucine molecular weight (g/mol). */
  private static final double           ISOLEUCINE_MW           = 131.17;

  /** Leucine identifier. */
  private static final String        LEUCINE_ID          = "LEU";
  /** Leucine no. of atoms per residue. */
  private static final int           LEUCINE_C           = 6, LEUCINE_H = 11,
                                                         LEUCINE_N = 1,
                                                         LEUCINE_O = 1,
                                                         LEUCINE_S = 0,
                                                         LEUCINE_P = 0;
  /** Leucine molecular weight (g/mol). */
  private static final double           LEUCINE_MW           = 131.17;

  /** Lysine identifier. */
  private static final String        LYSINE_ID           = "LYS";
  /** Lysine no. of atoms per residue. */
  private static final int           LYSINE_C            = 6, LYSINE_H = 12,
                                                         LYSINE_N = 2,
                                                         LYSINE_O = 1,
                                                         LYSINE_S = 0,
                                                         LYSINE_P = 0;
  /** Lysine molecular weight (g/mol). */
  private static final double           LYSINE_MW           = 146.19;

  /** Methionine identifier. */
  private static final String        METHIONINE_ID       = "MET";
  /** Methionine no. of atoms per residue. */
  private static final int           METHIONINE_C        = 5, METHIONINE_H = 9,
                                                         METHIONINE_N = 1,
                                                         METHIONINE_O = 1,
                                                         METHIONINE_S = 1,
                                                         METHIONINE_P = 0;
  /** Methionine molecular weight (g/mol). */
  private static final double           METHIONINE_MW           = 149.21;

  /** Selenomethionine identifier. */
  private static final String        SELENOMETHIONINE_ID = "MSE";
  /** Methionine no. of atoms per residue. */
  private static final int           SELENOMETHIONINE_C  = 5, SELENOMETHIONINE_H = 9,
                                                        SELENOMETHIONINE_N = 1,
                                                        SELENOMETHIONINE_O = 1,
                                                        SELENOMETHIONINE_S = 0,
                                                        SELENOMETHIONINE_SE = 1,
                                                        SELENOMETHIONINE_P = 0;
  /** Selenomethionine molecular weight (g/mol). */
  private static final double           SELENOMETHIONINE_MW           = 196.11;

  /** Phenylalanine identifier. */
  private static final String        PHENYLALANINE_ID    = "PHE";
  /** Phenylalanine no. of atoms per residue. */
  private static final int           PHENYLALANINE_C     = 9, PHENYLALANINE_H = 9,
                                                        PHENYLALANINE_N = 1,
                                                        PHENYLALANINE_O = 1,
                                                        PHENYLALANINE_S = 0,
                                                        PHENYLALANINE_P = 0;
  /** Phenylalanine molecular weight (g/mol). */
  private static final double           PHENYLALANINE_MW           = 165.19;

  /** Proline identifier. */
  private static final String        PROLINE_ID          = "PRO";
  /** Proline no. of atoms per residue. */
  private static final int           PROLINE_C           = 5, PROLINE_H = 7,
                                                         PROLINE_N = 1,
                                                         PROLINE_O = 1,
                                                         PROLINE_S = 0,
                                                         PROLINE_P = 0;
  /** Proline molecular weight (g/mol). */
  private static final double           PROLINE_MW           = 115.13;

  /** Serine identifier. */
  private static final String        SERINE_ID           = "SER";
  /** Serine no. of atoms per residue. */
  private static final int           SERINE_C            = 3, SERINE_H = 5,
                                                         SERINE_N = 1,
                                                         SERINE_O = 2,
                                                         SERINE_S = 0,
                                                         SERINE_P = 0;
  /** Serine molecular weight (g/mol). */
  private static final double           SERINE_MW           = 105.09;

  /** Threonine identifier. */
  private static final String        THREONINE_ID        = "THR";
  /** Threonine no. of atoms per residue. */
  private static final int           THREONINE_C         = 4, THREONINE_H = 7,
                                                         THREONINE_N = 1,
                                                         THREONINE_O = 2,
                                                         THREONINE_S = 0,
                                                         THREONINE_P = 0;
  /** Threonine molecular weight (g/mol). */
  private static final double           THREONINE_MW           = 119.12;

  /** Tryptophan identifier. */
  private static final String        TRYPTOPHAN_ID       = "TRP";
  /** Tryptophan no. of atoms per residue. */
  private static final int           TRYPTOPHAN_C        = 11, TRYPTOPHAN_H = 10,
                                                          TRYPTOPHAN_N = 2,
                                                          TRYPTOPHAN_O = 1,
                                                          TRYPTOPHAN_S = 0,
                                                          TRYPTOPHAN_P = 0;
  /** Tryptophan molecular weight (g/mol). */
  private static final double           TRYPTOPHAN_MW           = 204.23;

  /** tyrosine identifier. */
  private static final String        TYROSINE_ID         = "TYR";
  /** Tyrosine no. of atoms per residue. */
  private static final int           TYROSINE_C          = 9, TYROSINE_H = 9,
                                                         TYROSINE_N = 1,
                                                         TYROSINE_O = 2,
                                                         TYROSINE_S = 0,
                                                         TYROSINE_P = 0;
  /** Tyrosine molecular weight (g/mol). */
  private static final double           TYROSINE_MW           = 181.19;

  /** Valine identifier. */
  private static final String        VALINE_ID           = "VAL";
  /** Valine no. of atoms per residue. */
  private static final int           VALINE_C            = 5, VALINE_H = 9,
                                                         VALINE_N = 1,
                                                         VALINE_O = 1,
                                                         VALINE_S = 0,
                                                         VALINE_P = 0;
  /** Valine molecular weight (g/mol). */
  private static final double           VALINE_MW           = 117.15;

  /** AMP identifier. */
  private static final String        AMP_ID              = "  A";
  /** AMP no. of atoms per residue. */
  private static final int           AMP_C               = 10, AMP_H = 12,
                                                         AMP_N = 5,
                                                         AMP_O = 6, AMP_S = 0,
                                                         AMP_P = 1;
  /** AMP molecular weight (g/mol). */
  private static final double           AMP_MW           = 347.2;

  /** UMP identifier. */
  private static final String        UMP_ID              = "  U";
  /** UMP no. of atoms per residue. */
  private static final int           UMP_C               = 9, UMP_H = 11,
                                                         UMP_N = 2,
                                                         UMP_O = 8, UMP_S = 0,
                                                         UMP_P = 1;
  /** UMP molecular weight (g/mol). */
  private static final double           UMP_MW           = 324.2;

  /** GMP identifier. */
  private static final String        GMP_ID              = "  G";
  /** GMP no. of atoms per residue. */
  private static final int           GMP_C               = 10, GMP_H = 12,
                                                         GMP_N = 5,
                                                         GMP_O = 7, GMP_S = 0,
                                                         GMP_P = 1;
  /** GMP molecular weight (g/mol). */
  private static final double           GMP_MW           = 363.2;

  /** CMP identifier. */
  private static final String        CMP_ID              = "  C";
  /** CMP no. of atoms per residue. */
  private static final int           CMP_C               = 9, CMP_H = 12,
                                                         CMP_N = 3,
                                                         CMP_O = 7, CMP_S = 0,
                                                         CMP_P = 1;
  /** CMP molecular weight (g/mol). */
  private static final double           CMP_MW           = 323.2;

  /** dAMP identifier. */
  private static final String        DAMP_ID             = " DA";
  /** dAMP no. of atoms per residue. */
  private static final int           DAMP_C              = 10, DAMP_H = 12,
                                                         DAMP_N = 5,
      DAMP_O = 5,
      DAMP_S = 0, DAMP_P = 1;
  /** dAMP molecular weight (g/mol). */
  private static final double           DAMP_MW           = 331.2;

  /** dTMP identifier. */
  private static final String        DTMP_ID             = " DT";
  /** dTMP no. of atoms per residue. */
  private static final int           DTMP_C              = 10, DTMP_H = 11,
                                                         DTMP_N = 2,
      DTMP_O = 7,
      DTMP_S = 0, DTMP_P = 1;
  /** dTMP molecular weight (g/mol). */
  private static final double           DTMP_MW           = 322.2;

  /** dGMP identifier. */
  private static final String        DGMP_ID             = " DG";
  /** dGMP no. of atoms per residue. */
  private static final int           DGMP_C              = 10, DGMP_H = 12,
                                                         DGMP_N = 5,
      DGMP_O = 6,
      DGMP_S = 0, DGMP_P = 1;
  /** dGMP molecular weight (g/mol). */
  private static final double           DGMP_MW           = 347.2;

  /** dCMP identifier. */
  private static final String        DCMP_ID             = " DC";
  /** dCMP no. of atoms per residue. */
  private static final int           DCMP_C              = 9, DCMP_H = 12,
                                                         DCMP_N = 3,
                                                         DCMP_O = 6,
      DCMP_S = 0,
      DCMP_P = 1;
  /** dCMP molecular weight (g/mol). */
  private static final double           DCMP_MW           = 307.2;

  /** identifiers for type of residue. */
  protected static final int         TYPE_PROTEIN        = 1, TYPE_RNA = 2,
      TYPE_DNA = 3;

  /**
   * This class creates the Residue object for each type of residue and stores
   * the object] is a map object with a String identifier (key).
   * 
   * @return Map object containing the residue identifier string as
   *         the key and the corresponding residue as the value.
   */
  public static HashMap<String, Residue> createResidueMap() {
    //Create residues.
    Residue alanine = new Residue(ALANINE_ID, ALANINE_C, ALANINE_H, ALANINE_O,
        ALANINE_N,
        ALANINE_S, ALANINE_P, TYPE_PROTEIN, 0, ALANINE_MW);
    Residue arginine = new Residue(ARGININE_ID, ARGININE_C, ARGININE_H,
        ARGININE_O, ARGININE_N,
        ARGININE_S, ARGININE_P, TYPE_PROTEIN, 0, ARGININE_MW);
    Residue asparagine = new Residue(ASPARAGINE_ID, ASPARAGINE_C, ASPARAGINE_H,
        ASPARAGINE_O,
        ASPARAGINE_N, ASPARAGINE_S, ASPARAGINE_P, TYPE_PROTEIN, 0, ASPARAGINE_MW);
    Residue aspartate = new Residue(ASPARTATE_ID, ASPARTATE_C, ASPARTATE_H,
        ASPARTATE_O,
        ASPARTATE_N, ASPARTATE_S, ASPARTATE_P, TYPE_PROTEIN, 0, ASPARTATE_MW);
    Residue cysteine = new Residue(CYSTEINE_ID, CYSTEINE_C, CYSTEINE_H,
        CYSTEINE_O, CYSTEINE_N,
        CYSTEINE_S, CYSTEINE_P, TYPE_PROTEIN, 0, CYSTEINE_MW);
    Residue glutamine = new Residue(GLUTAMINE_ID, GLUTAMINE_C, GLUTAMINE_H,
        GLUTAMINE_O,
        GLUTAMINE_N, GLUTAMINE_S, GLUTAMINE_P, TYPE_PROTEIN, 0, GLUTAMINE_MW);
    Residue glutamate = new Residue(GLUTAMATE_ID, GLUTAMATE_C, GLUTAMATE_H,
        GLUTAMATE_O,
        GLUTAMATE_N, GLUTAMATE_S, GLUTAMATE_P, TYPE_PROTEIN, 0, GLUTAMATE_MW);
    Residue glycine = new Residue(GLYCINE_ID, GLYCINE_C, GLYCINE_H, GLYCINE_O,
        GLYCINE_N,
        GLYCINE_S, GLYCINE_P, TYPE_PROTEIN, 0, GLYCINE_MW);
    Residue histidine = new Residue(HISTIDINE_ID, HISTIDINE_C, HISTIDINE_H,
        HISTIDINE_O,
        HISTIDINE_N, HISTIDINE_S, HISTIDINE_P, TYPE_PROTEIN, 0, HISTIDINE_MW);
    Residue isoleucine = new Residue(ISOLEUCINE_ID, ISOLEUCINE_C, ISOLEUCINE_H,
        ISOLEUCINE_O,
        ISOLEUCINE_N, ISOLEUCINE_S, ISOLEUCINE_P, TYPE_PROTEIN, 0, ISOLEUCINE_MW);
    Residue leucine = new Residue(LEUCINE_ID, LEUCINE_C, LEUCINE_H, LEUCINE_O,
        LEUCINE_N,
        LEUCINE_S, LEUCINE_P, TYPE_PROTEIN, 0, LEUCINE_MW);
    Residue lysine = new Residue(LYSINE_ID, LYSINE_C, LYSINE_H, LYSINE_O,
        LYSINE_N, LYSINE_S,
        LYSINE_P, TYPE_PROTEIN, 0, LYSINE_MW);
    Residue methionine = new Residue(METHIONINE_ID, METHIONINE_C, METHIONINE_H,
        METHIONINE_O,
        METHIONINE_N, METHIONINE_S, METHIONINE_P, TYPE_PROTEIN, 0, METHIONINE_MW);
    Residue selenomethionine = new Residue(SELENOMETHIONINE_ID,
        SELENOMETHIONINE_C, SELENOMETHIONINE_H, SELENOMETHIONINE_O,
        SELENOMETHIONINE_N, SELENOMETHIONINE_S, SELENOMETHIONINE_P,
        TYPE_PROTEIN, SELENOMETHIONINE_SE, SELENOMETHIONINE_MW);
    Residue phenylalanine = new Residue(PHENYLALANINE_ID, PHENYLALANINE_C,
        PHENYLALANINE_H,
        PHENYLALANINE_O, PHENYLALANINE_N, PHENYLALANINE_S, PHENYLALANINE_P,
        TYPE_PROTEIN, 0, PHENYLALANINE_MW);
    Residue proline = new Residue(PROLINE_ID, PROLINE_C, PROLINE_H, PROLINE_O,
        PROLINE_N,
        PROLINE_S, PROLINE_P, TYPE_PROTEIN, 0, PROLINE_MW);
    Residue serine = new Residue(SERINE_ID, SERINE_C, SERINE_H, SERINE_O,
        SERINE_N, SERINE_S,
        SERINE_P, TYPE_PROTEIN, 0, SERINE_MW);
    Residue threonine = new Residue(THREONINE_ID, THREONINE_C, THREONINE_H,
        THREONINE_O,
        THREONINE_N, THREONINE_S, THREONINE_P, TYPE_PROTEIN, 0, THREONINE_MW);
    Residue tryptophan = new Residue(TRYPTOPHAN_ID, TRYPTOPHAN_C, TRYPTOPHAN_H,
        TRYPTOPHAN_O,
        TRYPTOPHAN_N, TRYPTOPHAN_S, TRYPTOPHAN_P, TYPE_PROTEIN, 0, TRYPTOPHAN_MW);
    Residue tyrosine = new Residue(TYROSINE_ID, TYROSINE_C, TYROSINE_H,
        TYROSINE_O, TYROSINE_N,
        TYROSINE_S, TYROSINE_P, TYPE_PROTEIN, 0, TYROSINE_MW);
    Residue valine = new Residue(VALINE_ID, VALINE_C, VALINE_H, VALINE_O,
        VALINE_N, VALINE_S,
        VALINE_P, TYPE_PROTEIN, 0, VALINE_MW);

    Residue adenineRNA = new Residue(AMP_ID, AMP_C, AMP_H, AMP_O, AMP_N, AMP_S,
        AMP_P, TYPE_RNA, 0, AMP_MW);
    Residue uracilRNA = new Residue(UMP_ID, UMP_C, UMP_H, UMP_O, UMP_N, UMP_S,
        UMP_P, TYPE_RNA, 0, UMP_MW);
    Residue guanineRNA = new Residue(GMP_ID, GMP_C, GMP_H, GMP_O, GMP_N, GMP_S,
        GMP_P, TYPE_RNA, 0, GMP_MW);
    Residue cytosineRNA = new Residue(CMP_ID, CMP_C, CMP_H, CMP_O, CMP_N,
        CMP_S, CMP_P, TYPE_RNA, 0, CMP_MW);

    Residue adenineDNA = new Residue(DAMP_ID, DAMP_C, DAMP_H, DAMP_O, DAMP_N,
        DAMP_S, DAMP_P,
        TYPE_DNA, 0, DAMP_MW);
    Residue thymineDNA = new Residue(DTMP_ID, DTMP_C, DTMP_H, DTMP_O, DTMP_N,
        DTMP_S, DTMP_P,
        TYPE_DNA, 0, DTMP_MW);
    Residue guanineDNA = new Residue(DGMP_ID, DGMP_C, DGMP_H, DGMP_O, DGMP_N,
        DGMP_S, DGMP_P,
        TYPE_DNA, 0, DGMP_MW);
    Residue cytosineDNA = new Residue(DCMP_ID, DCMP_C, DCMP_H, DCMP_O, DCMP_N,
        DCMP_S, DCMP_P,
        TYPE_DNA, 0, DCMP_MW);

    //Create map
    HashMap<String, Residue> residueMap = new HashMap<String, Residue>();
    residueMap.put(ALANINE_ID, alanine);
    residueMap.put(ARGININE_ID, arginine);
    residueMap.put(ASPARAGINE_ID, asparagine);
    residueMap.put(ASPARTATE_ID, aspartate);
    residueMap.put(CYSTEINE_ID, cysteine);
    residueMap.put(GLUTAMINE_ID, glutamine);
    residueMap.put(GLUTAMATE_ID, glutamate);
    residueMap.put(GLYCINE_ID, glycine);
    residueMap.put(HISTIDINE_ID, histidine);
    residueMap.put(ISOLEUCINE_ID, isoleucine);
    residueMap.put(LEUCINE_ID, leucine);
    residueMap.put(LYSINE_ID, lysine);
    residueMap.put(METHIONINE_ID, methionine);
    residueMap.put(SELENOMETHIONINE_ID, selenomethionine);
    residueMap.put(PHENYLALANINE_ID, phenylalanine);
    residueMap.put(PROLINE_ID, proline);
    residueMap.put(SERINE_ID, serine);
    residueMap.put(THREONINE_ID, threonine);
    residueMap.put(TRYPTOPHAN_ID, tryptophan);
    residueMap.put(TYROSINE_ID, tyrosine);
    residueMap.put(VALINE_ID, valine);

    residueMap.put(AMP_ID, adenineRNA);
    residueMap.put(UMP_ID, uracilRNA);
    residueMap.put(GMP_ID, guanineRNA);
    residueMap.put(CMP_ID, cytosineRNA);

    residueMap.put(DAMP_ID, adenineDNA);
    residueMap.put(DTMP_ID, thymineDNA);
    residueMap.put(DGMP_ID, guanineDNA);
    residueMap.put(DCMP_ID, cytosineDNA);

    return residueMap;
  }

  /**
   * Constructor for the Residue Database. This constructor calls the
   * createResidueMap()
   * method to create the map containing the residues and their key values.
   */
  protected ResidueDatabase() {
    this.residues = createResidueMap();
  }

  /**
   * Returns an instance of the residue database. The true constructor of
   * ResidueDatabase is private, as ResidueDatabase is a Singleton.
   * 
   * @return Instance of the residue database.
   */
  public static ResidueDatabase getInstance() {
    if (singleton == null) {
      singleton = new ResidueDatabase();
    }
    return singleton;
  }

  /**
   * Return residue given the residue ID, a three character sequence
   * (including whitespace) identifying the residue.
   * 
   * @param residueID
   *          Three character sequence (including whitespace) identifying
   *          the residue.
   * @return A single residue object.
   */
  public Residue getResidue(final String residueID) {
    return residues.get(residueID);
  }

  /**
   * Return residue given the residue ID, a single character identifying the
   * residue and the residue type, i.e. whether the residue is protein, DNA or
   * RNA.
   * 
   * @param residueID
   *          A single character identifying the residue.
   * @param residueType
   *          An integer, either 1, 2 or 3 depending on whether the residue
   *          is protein, RNA or DNA respectively.
   * @return A single residue object.
   */
  public Residue getResidue(final String residueID, final int residueType) {
    String threeCharacterID;
    if (residueID.length() == 1) {
      threeCharacterID = idLetterConversion(residueID, residueType);
    } else {
      threeCharacterID = residueID;
    }
    return residues.get(threeCharacterID);
  }

  /**
   * Convert the single character residue identifier to the three character
   * residue identifier (key), required to access the corresponding residue
   * from the residue map.
   * 
   * @param oneLetterID
   *          A single character identifying the residue.
   * @param residueType
   *          An integer, either 1, 2 or 3 depending on whether the residue
   *          is protein, RNA or DNA respectively.
   * @return A string giving the three character residue identifier (key).
   */
  public String idLetterConversion(String oneLetterID, int residueType) {
    String id = oneLetterID.toUpperCase();

    if (residueType == TYPE_PROTEIN) {
      if (id.equals("A")) {
        return ALANINE_ID;
      } else if (id.equals("B")) {
        return ASPARAGINE_ID;
      } else if (id.equals("C")) {
        return CYSTEINE_ID;
      } else if (id.equals("D")) {
        return ASPARTATE_ID;
      } else if (id.equals("E")) {
        return GLUTAMATE_ID;
      } else if (id.equals("F")) {
        return PHENYLALANINE_ID;
      } else if (id.equals("G")) {
        return GLYCINE_ID;
      } else if (id.equals("H")) {
        return HISTIDINE_ID;
      } else if (id.equals("I")) {
        return ISOLEUCINE_ID;
      } else if (id.equals("J")) {
        return LEUCINE_ID;
      } else if (id.equals("K")) {
        return LYSINE_ID;
      } else if (id.equals("L")) {
        return LEUCINE_ID;
      } else if (id.equals("M")) {
        return METHIONINE_ID;
      } else if (id.equals("N")) {
        return ASPARAGINE_ID;
      } else if (id.equals("O")) {
        System.out.println("**************WARNING**************");
        System.out.println("Amino acid residue id: \"" + id
            + "\" unrecognised!");
        System.out
            .println("If you are hoping this will be interpreted as Pyrrolysine"
                + "Please contact us:");
        System.out.println("E-mail: elspeth.garman@bioch.ox.ac.uk");
        return null;
      } else if (id.equals("P")) {
        return PROLINE_ID;
      } else if (id.equals("Q")) {
        return GLUTAMINE_ID;
      } else if (id.equals("R")) {
        return ARGININE_ID;
      } else if (id.equals("S")) {
        return SERINE_ID;
      } else if (id.equals("T")) {
        return THREONINE_ID;
      } else if (id.equals("U")) {
        System.out.println("**************WARNING**************");
        System.out.println("Amino acid residue id: \"" + id
            + "\" unrecognised!");
        System.out
            .println("If you are hoping this will be interpreted as Selenocysteine"
                + "Please contact us:");
        System.out.println("E-mail: elspeth.garman@bioch.ox.ac.uk");
        return null;
      } else if (id.equals("V")) {
        return VALINE_ID;
      } else if (id.equals("W")) {
        return TRYPTOPHAN_ID;
      } else if (id.equals("X")) {
        return ALANINE_ID;
      } else if (id.equals("Y")) {
        return TYROSINE_ID;
      } else if (id.equals("Z")) {
        return GLUTAMATE_ID;
      } else if (id.equals("-")) {
        System.out.println("**************WARNING**************");
        System.out.println("Amino acid residue id: \"" + id
            + "\" Does not exist");
        return null;
      } else if (id.equals("*")) {
        System.out.println("**************WARNING**************");
        System.out.println("Amino acid residue id: \"" + id
            + "\" Does not exist");
        return null;
      }
    } else if (residueType == TYPE_DNA) {
      if (id.equals("A")) {
        return DAMP_ID;
      } else if (id.equals("B")) {
        return DTMP_ID;
      } else if (id.equals("C")) {
        return DCMP_ID;
      } else if (id.equals("D")) {
        return DGMP_ID;
      } else if (id.equals("E")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("F")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("G")) {
        return DGMP_ID;
      } else if (id.equals("H")) {
        return DTMP_ID;
      } else if (id.equals("I")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("J")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("K")) {
        return DGMP_ID;
      } else if (id.equals("L")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("M")) {
        return DAMP_ID;
      } else if (id.equals("N")) {
        return DCMP_ID;
      } else if (id.equals("O")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("P")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("Q")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("R")) {
        return DGMP_ID;
      } else if (id.equals("S")) {
        return DCMP_ID;
      } else if (id.equals("T")) {
        return DTMP_ID;
      } else if (id.equals("U")) {
        System.out.println("**************WARNING**************");
        System.out.println("DNA residue id: \"" + id + "\" Does not exist");
        return null;
      } else if (id.equals("V")) {
        return DAMP_ID;
      } else if (id.equals("W")) {
        return DTMP_ID;
      } else if (id.equals("X")) {
        System.out.println("**************WARNING**************");
        System.out.println("RNA residue id: \"" + id + "\" Does not exist");
        return null;
      } else if (id.equals("Y")) {
        return DCMP_ID;
      } else if (id.equals("Z")) {
        System.out.println("**************WARNING**************");
        System.out.println("RNA residue id: \"" + id + "\" Does not exist");
        return null;
      } else if (id.equals("-")) {
        System.out.println("**************WARNING**************");
        System.out.println("RNA residue id: \"" + id + "\" Does not exist");
        return null;
      } else if (id.equals("*")) {
        System.out.println("**************WARNING**************");
        System.out.println("RNA residue id: \"" + id + "\" Does not exist");
        return null;
      }
    } else if (residueType == TYPE_RNA) {
      if (id.equals("A")) {
        return AMP_ID;
      } else if (id.equals("B")) {
        return UMP_ID;
      } else if (id.equals("C")) {
        return CMP_ID;
      } else if (id.equals("D")) {
        return GMP_ID;
      } else if (id.equals("E")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("F")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("G")) {
        return GMP_ID;
      } else if (id.equals("H")) {
        return UMP_ID;
      } else if (id.equals("I")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("J")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("K")) {
        return GMP_ID;
      } else if (id.equals("L")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("M")) {
        return AMP_ID;
      } else if (id.equals("N")) {
        return CMP_ID;
      } else if (id.equals("O")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("P")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("Q")) {
        System.out.println("**************WARNING**************");
        System.out.println("Nucleic acid residue id: \"" + id
            + "\" unrecognised!");
        return null;
      } else if (id.equals("R")) {
        return GMP_ID;
      } else if (id.equals("S")) {
        return CMP_ID;
      } else if (id.equals("T")) {
        System.out.println("**************WARNING**************");
        System.out.println("RNA residue id: \"" + id + "\" Does not exist");
        return null;
      } else if (id.equals("U")) {
        return UMP_ID;
      } else if (id.equals("V")) {
        return AMP_ID;
      } else if (id.equals("W")) {
        return UMP_ID;
      } else if (id.equals("X")) {
        System.out.println("**************WARNING**************");
        System.out.println("RNA residue id: \"" + id + "\" Does not exist");
        return null;
      } else if (id.equals("Y")) {
        return CMP_ID;
      } else if (id.equals("Z")) {
        System.out.println("**************WARNING**************");
        System.out.println("RNA residue id: \"" + id + "\" Does not exist");
        return null;
      } else if (id.equals("-")) {
        System.out.println("**************WARNING**************");
        System.out.println("RNA residue id: \"" + id + "\" Does not exist");
        return null;
      } else if (id.equals("*")) {
        System.out.println("**************WARNING**************");
        System.out.println("RNA residue id: \"" + id + "\" Does not exist");
        return null;
      }

    } else {
      System.out.println("**************WARNING**************");
      System.out.println("Residue type unrecognised!");
      return null;
    }
    return null;
  }

}
