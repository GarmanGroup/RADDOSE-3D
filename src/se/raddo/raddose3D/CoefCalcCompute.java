package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.raddo.raddose3D.Element.CrossSection;
import static java.lang.Math.PI;

/**
 * @author Helen Ginn
 */

public class CoefCalcCompute extends CoefCalc {
  /**
   * Identified coefficients and density from last program run. Final variables.
   */
  private double                     absCoeff, attCoeff, elasCoeff, density,
                                     cellVolume;

  /**
   * Right angle.
   */
  protected static final double      RIGHT_ANGLE                     = 90;

  /**
   * Parallel angle.
   */
  protected static final double      PARALLEL_ANGLE                  = 180;

  /**
   * Percentage conversion.
   */
  protected static final double      PERCENTAGE_CONVERSION           = 100;

  /**
   * Protein density in g/ml.
   */
  protected static final double      PROTEIN_DENSITY                 = 1.35;

  /**
   * RNA density in g/ml.
   */
  protected static final double      RNA_DENSITY                     = 1.3;

  /**
   * DNA density in g/ml.
   */
  protected static final double      DNA_DENSITY                     = 1.35;

  /**
   * Density of heteroatoms.
   */
  protected static final double      HETATM_DENSITY                  = 1.35;

  /**
   * Atomic mass unit in grams.
   */
  protected static final double      ATOMIC_MASS_UNIT                = 1.66E-24;

  /**
   * Avogadro's number.
   */
  protected static final double      AVOGADRO_NUM                    = 6.022e+23;

  /**
   * Average weight of an amino acid.
   */
  protected static final double      AMINO_ACID_AVE_MASS             = 110.0;

  /**
   * Average weight of a DNA nucleotide.
   */
  protected static final double      DNA_NUCLEOTIDE_MASS             = 312.0;

  /**
   * Average weight of an RNA nucleotide.
   */
  protected static final double      RNA_NUCLEOTIDE_MASS             = 321.0;

  /**
   * Angstroms to ml conversion.
   */
  protected static final double      ANGSTROMS_TO_ML                 = 1E-24;

  /**
   * conversion factor to make a number bigger. 1E27.
   */
  protected static final double      MASS_TO_CELL_VOLUME             = 1E27;

  /**
   * Water concentration in mM.
   */
  protected static final double      WATER_CONCENTRATION             = 55555;

  /**
   * Units per milli-unit.
   */
  protected static final long        UNITSPERMILLIUNIT               = 1000L;

  /**
   * Units per deci-unit.
   */
  protected static final long        UNITSPERDECIUNIT                = 10L;

  /** hydrogens per amino acid. */
  protected static final double      HYDROGENS_PER_AMINO_ACID        = 8;
  /** carbons per amino acid. */
  protected static final double      CARBONS_PER_AMINO_ACID          = 5;
  /** nitrogens per amino acid. */
  protected static final double      NITROGENS_PER_AMINO_ACID        = 1.35;
  /** oxygens per amino acid. */
  protected static final double      OXYGENS_PER_AMINO_ACID          = 1.5;

  /** hydrogens per RNA nucleotide. */
  protected static final double      HYDROGENS_PER_RNA_NUCLEOTIDE    = 11.25;
  /** carbons per RNA nucleotide. */
  protected static final double      CARBONS_PER_RNA_NUCLEOTIDE      = 9.5;
  /** nitrogens per RNA nucleotide. */
  protected static final double      NITROGENS_PER_RNA_NUCLEOTIDE    = 3.75;
  /** oxygens per RNA nucleotide. */
  protected static final double      OXYGENS_PER_RNA_NUCLEOTIDE      = 7;
  /** phosphoruses per RNA nucleotide. */
  protected static final double      PHOSPHORUSES_PER_RNA_NUCLEOTIDE = 1;

  /** hydrogens per DNA nucleotide. */
  protected static final double      HYDROGENS_PER_DNA_NUCLEOTIDE    = 11.75;
  /** carbons per DNA nucleotide. */
  protected static final double      CARBONS_PER_DNA_NUCLEOTIDE      = 9.75;
  /** nitrogens per DNA nucleotide. */
  protected static final double      NITROGENS_PER_DNA_NUCLEOTIDE    = 4;
  /** oxygens per DNA nucleotide. */
  protected static final double      OXYGENS_PER_DNA_NUCLEOTIDE      = 6;
  /** phosphoruses per DNA nucleotide. */
  protected static final double      PHOSPHORUSES_PER_DNA_NUCLEOTIDE = 1;

  /**
   * Number of amino acids.
   */
  private double                     numAminoAcids;

  /**
   * Number of RNA residues.
   */
  private double                     numRNA;

  /**
   * Number of DNA residues.
   */
  private double                     numDNA;

  /**
   * Number of monomers per unit cell.
   */
  private int                        numMonomers                     = 1;

  /**
   * Element database keeping the coefficients of all elements.
   */
  private final ElementDatabase      elementDB;

  private final Map<Element, Double> heteroAtomOccurrence;
  private final Map<Element, Double> macromolecularOccurrence;
  private final Map<Element, Double> solventOccurrence;
  private final Map<Element, Double> solventConcentration;

  /**
   * Simple constructor.
   */
  public CoefCalcCompute() {
    elementDB = new ElementDatabase();
    macromolecularOccurrence = new HashMap<Element, Double>();
    heteroAtomOccurrence = new HashMap<Element, Double>();
    solventOccurrence = new HashMap<Element, Double>();
    solventConcentration = new HashMap<Element, Double>();
  }

  /**
   * Calculate cross-sections from the associated parser's atom array.
   * 
   * @param w Wedge object
   * @param b Beam object
   */
  @Override
  public void updateCoefficients(final Wedge w, final Beam b) {
    // density is easy. Loop through all atoms and calculate total mass.
    // then express as g / cm-3.
    double mass = 0;

    for (int i = 0; i < elementDB.getAtomCount(); i++) {
      Element element = elementDB.getAtoms()[i];
      double addition = totalAtoms(element)
          * element.getAtomicWeightInGrams();

      mass += addition;
    }

    density = mass * MASS_TO_CELL_VOLUME / (cellVolume * UNITSPERMILLIUNIT) / 2;

    double energy = b.getPhotonEnergy();

    double crossSectionPhotoElectric = 0;
    double crossSectionCoherent = 0;
    double crossSectionTotal = 0;

    // take cross section contributions from each individual atom
    // weighted by the cell volume

    for (int i = 0; i < elementDB.getAtomCount(); i++) {
      Map<Element.CrossSection, Double> cs = elementDB.getAtoms()[i]
          .calculateMu(energy);

      crossSectionPhotoElectric += totalAtoms(elementDB.getAtoms()[i])
          * cs.get(CrossSection.PHOTOELECTRIC) / cellVolume
          / UNITSPERDECIUNIT;
      crossSectionCoherent += totalAtoms(elementDB.getAtoms()[i])
          * cs.get(CrossSection.COHERENT) / cellVolume
          / UNITSPERDECIUNIT;
      crossSectionTotal += totalAtoms(elementDB.getAtoms()[i])
          * cs.get(CrossSection.TOTAL) / cellVolume
          / UNITSPERDECIUNIT;
    }

    absCoeff = crossSectionPhotoElectric / UNITSPERMILLIUNIT / 2;
    attCoeff = crossSectionTotal / UNITSPERMILLIUNIT / 2;
    elasCoeff = crossSectionCoherent / UNITSPERMILLIUNIT / 2;
  }

  /**
   * Returns absorption coefficient.
   * 
   * @return absorption coefficient
   */
  @Override
  public double getAbsorptionCoefficient() {
    return absCoeff;
  }

  /**
   * Returns attenuation coefficient.
   * 
   * @return attenuation coefficient
   */
  @Override
  public double getAttenuationCoefficient() {
    return attCoeff;
  }

  /**
   * Returns elastic coefficient.
   * 
   * @return elastic coefficient
   */
  @Override
  public double getElasCoef() {
    return elasCoeff;
  }

  /**
   * Returns density coefficient.
   * 
   * @return density coefficient
   */
  @Override
  public double getDensity() {
    return density;
  }

  /**
   * @return the numAminoAcids
   */
  protected double getNumAminoAcids() {
    return numAminoAcids;
  }

  /**
   * @param newnumAminoAcids the numAminoAcids to set
   */
  protected void setNumAminoAcids(final double newnumAminoAcids) {
    this.numAminoAcids = newnumAminoAcids;
  }

  /**
   * @param increment the numAminoAcids to increment
   */
  protected void incrementNumAminoAcids(final double increment) {
    this.numAminoAcids += increment;
  }

  /**
   * @return the numRNA
   */
  protected double getNumRNA() {
    return numRNA;
  }

  /**
   * @param newnumRNA the numRNA to set
   */
  protected void setNumRNA(final double newnumRNA) {
    this.numRNA = newnumRNA;
  }

  /**
   * @param increment the NumRNA to increment
   */
  protected void incrementNumRNA(final double increment) {
    this.numRNA += increment;
  }

  /**
   * @return the numDNA
   */
  protected double getNumDNA() {
    return numDNA;
  }

  /**
   * @param newnumDNA the numDNA to set
   */
  protected void setNumDNA(final double newnumDNA) {
    this.numDNA = newnumDNA;
  }

  /**
   * @param increment the numDNA to increment
   */
  protected void incrementNumDNA(final double increment) {
    this.numDNA += increment;
  }

  /**
   * @return the numMonomers
   */
  protected int getNumMonomers() {
    return numMonomers;
  }

  /**
   * @param newnumMonomers the numMonomers to set
   */
  protected void setNumMonomers(final int newnumMonomers) {
    this.numMonomers = newnumMonomers;
  }

  /**
   * @return the parser
   */
  public ElementDatabase getParser() {
    return elementDB;
  }

  /**
   * Returns string containing absorption, attenuation, elastic
   * coefficient and density.
   * 
   * @return descriptive string
   */
  @Override
  public String toString() {
    return String.format(
        "Crystal coefficients calculated with Raddose-3D "
            + "(Paithankar et al., 2009). %n"
            + "Absorption Coefficient: %.2e /um.%n"
            + "Attenuation Coefficient: %.2e /um.%n"
            + "Elastic Coefficient: %.2e /um.%n"
            + "Density: %.2f g/ml.%n",
        absCoeff, attCoeff, elasCoeff, density);
  }

  /**
   * Calculating solvent fraction from numbers of amino acids, RNA residues and
   * DNA residues in the unit cell.
   * Also takes into account any hetatms from a PDB entry. These are assumed to
   * have a density of protein
   * but this might be worth changing... would not apply correctly to heavy
   * metals.
   * 
   * @return solvent fraction of crystal
   */
  public double calculateSolventFractionFromNums() {
    // Protein, RNA, DNA masses are calculated and
    // then weighted to fit the unit cell.

    double proteinMass = ATOMIC_MASS_UNIT * AMINO_ACID_AVE_MASS
        * numAminoAcids * numMonomers;
    proteinMass /= cellVolume * PROTEIN_DENSITY * ANGSTROMS_TO_ML;

    double rnaMass = ATOMIC_MASS_UNIT * RNA_NUCLEOTIDE_MASS * numRNA
        * numMonomers;
    rnaMass /= cellVolume * RNA_DENSITY * ANGSTROMS_TO_ML;

    double dnaMass = ATOMIC_MASS_UNIT * DNA_NUCLEOTIDE_MASS * numDNA
        * numMonomers;
    dnaMass /= cellVolume * DNA_DENSITY * ANGSTROMS_TO_ML;

    // heteroatom mass only used in PDBs, otherwise this value is 0 anyway.

    double hetatmMass = 0;

    // we only care about low atomic weight atoms for hetatms
    // otherwise heavy atoms would make a very large impact
    // on reduction of solvent accessible space.

    for (int i = 0; i < Element.LIGHT_ATOM_MAX_NUM; i++) {
      hetatmMass += ATOMIC_MASS_UNIT
          * getHetatmOccurrence(elementDB.getAtoms()[i])
          * elementDB.getAtoms()[i].getAtomicWeight();
    }

    hetatmMass /= cellVolume * HETATM_DENSITY * ANGSTROMS_TO_ML;

    // We estimate the solvent fraction from the
    // remaining mass to be found in the crystal. Magic!

    double solventFraction = 1 - proteinMass - rnaMass - dnaMass
        - hetatmMass;

    // sanity check
    if (solventFraction < 0) {
      System.out
          .println("Warning: Solvent mass calculated as a negative number...");
    }

    System.out.println("Solvent fraction determined as " + solventFraction
        * PERCENTAGE_CONVERSION + "%.");

    return solventFraction;
  }

  /**
   * Convert solvent concentrations in unit cell to solvent no. of atoms (loop
   * round all atoms)
   * Also need to know number of non-water atoms in the solvent in order to
   * calculate a displacement.
   * 1 Angstrom = 1E-27 litres.
   * 
   * @param solventFraction solvent fraction
   */
  public void calculateSolventWater(final double solventFraction) {

    double nonWaterAtoms = 0;

    for (int i = 0; i < elementDB.getAtomCount(); i++) {
      double conc = getSolventConcentration(elementDB.getAtoms()[i]);
      double atomCount = conc * AVOGADRO_NUM * cellVolume * solventFraction
          * 1E-3 * 1E-27;
      incrementSolventOccurrence(elementDB.getAtoms()[i], atomCount);

      nonWaterAtoms += atomCount;
    }

    // Calculating number of water molecules.
    // NOTE: using updated value for concentration of water,
    // 55.555M instead of the 55M in Fortran.

    double waterMolecules = WATER_CONCENTRATION * AVOGADRO_NUM
        / UNITSPERMILLIUNIT
        * cellVolume * (1 / MASS_TO_CELL_VOLUME) * solventFraction
        - nonWaterAtoms;

    // Add water molecules to hydrogen and oxygen.

    Element hydrogen = elementDB.getElement("H");
    setSolventOccurrence(hydrogen, getSolventOccurrence(hydrogen)
        + waterMolecules * 2);

    Element oxygen = elementDB.getElement("O");
    setSolventOccurrence(oxygen, getSolventOccurrence(oxygen) + waterMolecules);

  }

  /**
   * Combine concentrations of heavy atoms in the solvent and add these to the
   * unit cell.
   * 
   * @param heavySolvConcNames heavy solvent concentration atom names
   * @param heavySolvConcNums heavy solvent concentrations in mM.
   */
  public void addSolventConcentrations(final List<String> heavySolvConcNames,
      final List<Double> heavySolvConcNums) {

    for (int i = 0; i < heavySolvConcNames.size(); i++) {
      Element heavyAtom = elementDB.getElement(heavySolvConcNames.get(i));

      setSolventConcentration(heavyAtom, heavySolvConcNums.get(i)
          + getSolventConcentration(heavyAtom));
    }
  }

  /**
   * Calculate cell volume from cell dimensions and unit cell angles.
   * 
   * @param cellA unit cell dimension a
   * @param cellB unit cell dimension b
   * @param cellC unit cell dimension c
   * @param cellAlpha unit cell angle alpha
   * @param cellBeta unit cell angle beta
   * @param cellGamma unit cell angle gamma
   * @return cell volume in Angstroms cubed.
   */

  public double cellVolume(final double cellA, final double cellB,
      final double cellC,
      final double cellAlpha, final double cellBeta, final double cellGamma) {
    double alpha = cellAlpha * PI / PARALLEL_ANGLE;
    double beta = cellBeta * PI / PARALLEL_ANGLE;
    double gamma = cellGamma * PI / PARALLEL_ANGLE;

    double ult = 1.0 + 2.0 * Math.cos(alpha) * Math.cos(beta) * Math.cos(gamma)
        - Math.pow(Math.cos(alpha), 2.0) - Math.pow(Math.cos(beta), 2.0)
        - Math.pow(Math.cos(gamma), 2.0);

    if (ult < 0.0) {
      System.out
          .println("Warning: error calculating unit cell "
              + "volume - please check inputs.");
    }

    double cellVol = cellA * cellB * cellC * Math.sqrt(ult);

    // This result below is what Fortran thought
    // of a 78.27 x 78.27 x 78.27 (cubic) unit cell
    // instead of our value now of 479497.1 Angstroms cubed
    // resulting in an error between the calculations.
    //  double cellVol = 460286.7; Angstrom cubed

    cellVolume = cellVol;

    System.out.println("Cell volume: " + cellVolume + " Angstroms cubed");

    return cellVol;
  }

  public void setSolventConcentration(final Element element,
      final Double newsolventConcentration) {
    solventConcentration.put(element, newsolventConcentration);
  }

  public double getSolventConcentration(final Element element) {
    if (solventConcentration.containsKey(element)) {
      return solventConcentration.get(element);
    } else {
      return 0.;
    }
  }

  public Double getSolventOccurrence(Element element) {
    if (solventOccurrence.containsKey(element)) {
      return solventOccurrence.get(element);
    } else {
      return 0.;
    }
  }

  public void incrementSolventOccurrence(final Element element,
      final Double increment) {
    if (solventOccurrence.containsKey(element)) {
      solventOccurrence.put(element, increment +
          solventOccurrence.get(element));
    } else {
      solventOccurrence.put(element, increment);
    }
  }

  public void setSolventOccurrence(final Element element,
      final Double newsolventOccurrence) {
    solventOccurrence.put(element, newsolventOccurrence);
  }

  public Double getHetatmOccurrence(final Element element) {
    if (heteroAtomOccurrence.containsKey(element)) {
      return heteroAtomOccurrence.get(element);
    } else {
      return 0.;
    }
  }

  public void incrementHetatmOccurrence(final Element element,
      final Double increment) {
    if (heteroAtomOccurrence.containsKey(element)) {
      heteroAtomOccurrence.put(element, increment +
          heteroAtomOccurrence.get(element));
    } else {
      heteroAtomOccurrence.put(element, increment);
    }
  }

  public void setHetatmOccurrence(final Element element, final Double haOcc) {
    heteroAtomOccurrence.put(element, haOcc);
  }

  public Double getMacromolecularOccurrence(final Element element) {
    if (macromolecularOccurrence.containsKey(element)) {
      return macromolecularOccurrence.get(element);
    } else {
      return 0.;
    }
  }

  public void incrementMacromolecularOccurrence(final Element element,
      final Double increment) {
    if (macromolecularOccurrence.containsKey(element)) {
      macromolecularOccurrence.put(element, increment +
          macromolecularOccurrence.get(element));
    } else {
      macromolecularOccurrence.put(element, increment);
    }
  }

  public void setMacromolecularOccurrence(final Element element,
      final Double mmOcc) {
    macromolecularOccurrence.put(element, mmOcc);
  }

  public double totalAtoms(final Element element) {
    return getSolventOccurrence(element) + getMacromolecularOccurrence(element);
  }
}
