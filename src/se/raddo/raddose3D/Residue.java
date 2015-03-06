package se.raddo.raddose3D;

/**
 * The residue class contains information about the atomic components of a
 * residue including which atoms are present and how many of each type.
 */
public class Residue {

  /** Identifier for the residue. */
  private String identifier;

  /** Numbers of atoms per residue. */
  private int    carbons, hydrogens, oxygens, nitrogens,
                 sulphurs, seleniums,
                 phosphoruses, type;

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
   * @param typ TYPE_PROTEIN, TYPE_RNA or TYPE_DNA.
   */
  public Residue(final String id, final int cs, final int hs, final int os,
      final int ns, final int ss, final int ps, final int typ,
      final int ses) {
    identifier = id;
    carbons = cs;
    hydrogens = hs;
    oxygens = os;
    nitrogens = ns;
    sulphurs = ss;
    phosphoruses = ps;
    seleniums = ses;
    type = typ;
  }

  /**
   * Get the number of carbons for the residue
   *
   * @return integer giving the number of carbons
   */
  public int getCarbons() {
    return this.carbons;
  }

  /**
   * Get the number of hydrogens for the residue
   *
   * @return integer giving the number of hydrogens
   */
  public int getHydrogens() {
    return this.hydrogens;
  }

  /**
   * Get the number of oxygens for the residue
   *
   * @return integer giving the number of oxygens
   */
  public int getOxygens() {
    return this.oxygens;
  }

  /**
   * Get the number of nitrogens for the residue
   *
   * @return integer giving the number of nitrogens
   */
  public int getNitrogens() {
    return this.nitrogens;
  }

  /**
   * Get the number of sulphurs for the residue
   *
   * @return integer giving the number of sulphurs
   */
  public int getSulphurs() {
    return this.sulphurs;
  }

  /**
   * Get the number of seleniums for the residue
   *
   * @return integer giving the number of seleniums
   */
  public int getSeleniums() {
    return this.seleniums;
  }

  /**
   * Get the number of phosphoruses for the residue
   *
   * @return integer giving the number of phosphoruses
   */
  public int getPhosphoruses() {
    return this.phosphoruses;
  }

  /**
   * Get the type of the residue
   *
   * @return integer indicating the type of the residue.
   *         1 = protein
   *         2 = RNA
   *         3 = DNA
   */
  public int getResidueType() {
    return this.type;
  }

  /**
   * Get the three character sequence that uniquely identifies the residue.
   *
   * @return string giving the 3 character residue identifier
   */
  public String getIdentifier() {
    return this.identifier;
  }

}
