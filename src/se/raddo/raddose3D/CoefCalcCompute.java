package se.raddo.raddose3D;

import java.util.List;
import static java.lang.Math.PI;

import se.raddo.raddose3D.MuCalcConstantParser.Atom;

/**
 * @author Helen Ginn
 */

public class CoefCalcCompute extends CoefCalc {
  /**
   * Identified coefficients and density from last program run. Final variables.
   */
  private double                 absCoeff, attCoeff, elasCoeff, density,
                                 cellVolume;

  /**
   * Right angle.
   */
  public static final double     RIGHT_ANGLE                     = 90;

  /**
   * Parallel angle.
   */

  public static final double     PARALLEL_ANGLE                  = 180;

  /**
   * Percentage conversion.
   */
  public static final double     PERCENTAGE_CONVERSION           = 100;

  /**
   * Protein density in g/ml.
   */
  protected static final double  PROTEIN_DENSITY                 = 1.35;

  /**
   * RNA density in g/ml.
   */
  protected static final double  RNA_DENSITY                     = 1.3;

  /**
   * DNA density in g/ml.
   */
  protected static final double  DNA_DENSITY                     = 1.35;

  /**
   * Density of heteroatoms.
   */
  protected static final double  HETATM_DENSITY                  = 1.35;

  /**
   * Atomic mass unit in grams.
   */
  protected static final double  ATOMIC_MASS_UNIT                = 1.66E-24;

  /**
   * Avogadro's number.
   */
  protected static final double  AVOGADRO_NUM                    = 6.022e+23;

  /**
   * Average weight of an amino acid.
   */
  protected static final double  AMINO_ACID_AVE_MASS             = 110.0;

  /**
   * Average weight of a DNA nucleotide.
   */
  protected static final double  DNA_NUCLEOTIDE_MASS             = 312.0;

  /**
   * Average weight of an RNA nucleotide.
   */
  protected static final double  RNA_NUCLEOTIDE_MASS             = 321.0;

  /**
   * Angstroms to ml conversion.
   */
  protected static final double  ANGSTROMS_TO_ML                 = 1E-24;

  /**
   * conversion factor to make a number bigger. 1E27.
   */

  protected static final double  MASS_TO_CELL_VOLUME             = 1E27;

  /**
   * Water concentration in mM.
   */
  protected static final double  WATER_CONCENTRATION             = 55555;

  /**
   * Units per milli-unit.
   */
  protected static final long    UNITSPERMILLIUNIT               = 1000L;

  /**
   * Units per deci-unit.
   */

  protected static final long    UNITSPERDECIUNIT                = 10L;

  /** hydrogens per amino acid. */
  protected static final double  HYDROGENS_PER_AMINO_ACID        = 8;
  /** carbons per amino acid. */
  protected static final double  CARBONS_PER_AMINO_ACID          = 5;
  /** nitrogens per amino acid. */
  protected static final double  NITROGENS_PER_AMINO_ACID        = 1.35;
  /** oxygens per amino acid. */
  protected static final double  OXYGENS_PER_AMINO_ACID          = 1.5;

  /** hydrogens per RNA nucleotide. */
  protected static final double  HYDROGENS_PER_RNA_NUCLEOTIDE    = 11.25;
  /** carbons per RNA nucleotide. */
  protected static final double  CARBONS_PER_RNA_NUCLEOTIDE      = 9.5;
  /** nitrogens per RNA nucleotide. */
  protected static final double  NITROGENS_PER_RNA_NUCLEOTIDE    = 3.75;
  /** oxygens per RNA nucleotide. */
  protected static final double  OXYGENS_PER_RNA_NUCLEOTIDE      = 7;
  /** phosphoruses per RNA nucleotide. */
  protected static final double  PHOSPHORUSES_PER_RNA_NUCLEOTIDE = 1;

  /** hydrogens per DNA nucleotide. */
  protected static final double  HYDROGENS_PER_DNA_NUCLEOTIDE    = 11.75;
  /** carbons per DNA nucleotide. */
  protected static final double  CARBONS_PER_DNA_NUCLEOTIDE      = 9.75;
  /** nitrogens per DNA nucleotide. */
  protected static final double  NITROGENS_PER_DNA_NUCLEOTIDE    = 4;
  /** oxygens per DNA nucleotide. */
  protected static final double  OXYGENS_PER_DNA_NUCLEOTIDE      = 6;
  /** phosphoruses per DNA nucleotide. */
  protected static final double  PHOSPHORUSES_PER_DNA_NUCLEOTIDE = 1;

  /**
   * Number of amino acids.
   */
  private double                 numAminoAcids                   = 0;

  /**
   * Number of RNA residues.
   */
  private double                 numRNA                          = 0;

  /**
   * Number of DNA residues.
   */
  private double                 numDNA                          = 0;

  /**
   * Number of monomers per unit cell.
   */
  private int                    numMonomers                     = 1;

  /**
   * Parser which is going to look after our atom objects.
   */
  protected MuCalcConstantParser parser;

  /**
   * Simple constructor.
   */
  public CoefCalcCompute() {
    parser = new MuCalcConstantParser();
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

    for (int i = 0; i < parser.getAtomCount(); i++) {
      double addition = parser.getAtoms()[i].totalMass();

      mass += addition;
    }

    density = mass * MASS_TO_CELL_VOLUME / (cellVolume * UNITSPERMILLIUNIT);

    double energy = b.getPhotonEnergy();

    double crossSectionPhotoElectric = 0;
    double crossSectionCoherent = 0;
    double crossSectionTotal = 0;

    // take cross section contributions from each individual atom
    // weighted by the cell volume

    for (int i = 0; i < parser.getAtomCount(); i++) {
      parser.getAtoms()[i].calculateMu(energy);

      crossSectionPhotoElectric += parser.getAtoms()[i].totalAtoms()
          * parser.getAtoms()[i].getPhotoelectricCrossSection() / cellVolume
          / UNITSPERDECIUNIT;
      crossSectionCoherent += parser.getAtoms()[i].totalAtoms()
          * parser.getAtoms()[i].getCoherentCrossSection() / cellVolume
          / UNITSPERDECIUNIT;
      crossSectionTotal += parser.getAtoms()[i].totalAtoms()
          * parser.getAtoms()[i].getTotalCrossSection() / cellVolume
          / UNITSPERDECIUNIT;
    }

    absCoeff = crossSectionPhotoElectric / UNITSPERMILLIUNIT;
    attCoeff = crossSectionTotal / UNITSPERMILLIUNIT;
    elasCoeff = crossSectionCoherent / UNITSPERMILLIUNIT;
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
  protected void setNumAminoAcids(double newnumAminoAcids) {
    this.numAminoAcids = newnumAminoAcids;
  }

  /**
   * @param increment the numAminoAcids to increment
   */
  protected void incrementNumAminoAcids(double increment) {
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
  protected void setNumRNA(double newnumRNA) {
    this.numRNA = newnumRNA;
  }

  /**
   * @param increment the NumRNA to increment
   */
  protected void incrementNumRNA(double increment) {
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
  protected void setNumDNA(double newnumDNA) {
    this.numDNA = newnumDNA;
  }

  /**
   * @param increment the numDNA to increment
   */
  protected void incrementNumDNA(double increment) {
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
  protected void setNumMonomers(int newnumMonomers) {
    this.numMonomers = newnumMonomers;
  }

  /**
   * @return the parser
   */
  protected MuCalcConstantParser getParser() {
    return parser;
  }

  /**
   * @param newparser the parser to set
   */
  protected void setParser(MuCalcConstantParser newparser) {
    this.parser = newparser;
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
   * Compute results and put them in local variables absCoeff, attCoeff,
   * elasCoeff and density.
   */
  public CoefCalcCompute(final Double cellA, final Double cellB,
      final Double cellC,
      final Double cellAlpha, final Double cellBeta, final Double cellGamma,
      final int numMonomers, final int numResidues, final int numRNA,
      final int numDNA,
      final List<String> heavyProteinAtomNames,
      final List<Double> heavyProteinAtomNums,
      final List<String> heavySolutionConcNames,
      final List<Double> heavySolutionConcNums,
      final Double solventFraction) {
    parser = new MuCalcConstantParser();

    Double alpha = cellAlpha;
    Double beta = cellBeta;
    Double gamma = cellGamma;

    if (alpha == null) {
      alpha = RIGHT_ANGLE;
    }
    if (beta == null) {
      beta = RIGHT_ANGLE;
    }
    if (gamma == null) {
      gamma = RIGHT_ANGLE;
    }

    cellVolume(cellA, cellB, cellC, alpha, beta, gamma);

    this.calculateAtomOccurrences(numMonomers, numResidues, numRNA, numDNA,
        solventFraction, heavyProteinAtomNames, heavyProteinAtomNums,
        heavySolutionConcNames, heavySolutionConcNums);

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

    for (int i = 0; i < 20; i++) {
      hetatmMass += ATOMIC_MASS_UNIT
          * parser.getAtoms()[i].getHetatmOccurrence()
          * parser.getAtoms()[i].getAtomicWeight();
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

    for (int i = 0; i < parser.getAtomCount(); i++) {
      double conc = parser.getAtoms()[i].getSolventConcentration();
      double atomCount = conc * (1 / UNITSPERMILLIUNIT) * AVOGADRO_NUM
          * cellVolume * (1 / MASS_TO_CELL_VOLUME)
          * solventFraction;
      parser.getAtoms()[i].incrementSolventOccurrence(atomCount);

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

    Atom hydrogen = parser.findAtomWithName("H");
    hydrogen.setSolventOccurrence(hydrogen.getSolventOccurrence()
        + waterMolecules * 2);

    Atom oxygen = parser.findAtomWithName("O");
    oxygen.setSolventOccurrence(oxygen.getSolventOccurrence() + waterMolecules);

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
      Atom heavyAtom = parser.findAtomWithName(heavySolvConcNames.get(i));

      heavyAtom.setSolventConcentration(heavySolvConcNums.get(i)
          + heavyAtom.getSolventConcentration());
    }
  }

  /**
   * Calculate the macromolecular mass (etc.) and add the appropriate numbers of
   * atom occurrences to the parser's atom array.
   * 
   * @param numMonomers number of monomers
   * @param numResidues number of amino acid residues
   * @param numRNAresidues number of RNA residues
   * @param numDNAresidues number of DNA residues
   * @param solventFraction solvent fraction
   * @param heavyProteinAtomNames heavy atom protein element symbols
   * @param heavyProteinAtomNums heavy atom protein occurrences
   * @param heavySolvConcNames heavy atom solvent element symbols
   * @param heavySolvConcNums heavy atom solvent concentrations in mM.
   */
  public void calculateAtomOccurrences(final int numMonomers,
      final int numResidues,
      final int numRNAresidues, final int numDNAresidues,
      final double solventFraction,
      final List<String> heavyProteinAtomNames,
      final List<Double> heavyProteinAtomNums,
      final List<String> heavySolvConcNames,
      final List<Double> heavySolvConcNums) {

    // Start by dealing with heavy atom in the
    // protein and adding these to the unit cell.

    for (int i = 0; i < heavyProteinAtomNames.size(); i++) {
      Atom heavyAtom = parser.findAtomWithName(heavyProteinAtomNames.get(i));

      // note: heavy atoms are provided per monomer,
      // so multiply by number of monomers.
      heavyAtom.incrementMacromolecularOccurrence(heavyProteinAtomNums.get(i)
          * numMonomers);
    }

    // Combine concentrations of heavy atoms in the
    // solvent and add these to the unit cell.

    addSolventConcentrations(heavySolvConcNames, heavySolvConcNums);

    this.numAminoAcids = numResidues;
    this.numRNA = numRNAresidues;
    this.numDNA = numDNAresidues;

    // If the solvent fraction has not been specified.
    double newSolventFraction = solventFraction;

    if (solventFraction <= 0) {
      newSolventFraction = calculateSolventFractionFromNums();
    }

    calculateSolventWater(newSolventFraction);

    // Atom preparation...

    Atom hydrogen = parser.findAtomWithName("H");
    Atom oxygen = parser.findAtomWithName("O");
    Atom carbon = parser.findAtomWithName("C");
    Atom nitrogen = parser.findAtomWithName("N");
    Atom phosphorus = parser.findAtomWithName("P");

    // Protein atoms: for every amino acid
    // add 5C + 1.35 N + 1.5 O + 8H

    carbon.incrementMacromolecularOccurrence(CARBONS_PER_AMINO_ACID
        * numResidues
        * numMonomers);
    nitrogen.incrementMacromolecularOccurrence(NITROGENS_PER_AMINO_ACID
        * numResidues
        * numMonomers);
    oxygen.incrementMacromolecularOccurrence(OXYGENS_PER_AMINO_ACID
        * numResidues
        * numMonomers);
    hydrogen.incrementMacromolecularOccurrence(HYDROGENS_PER_AMINO_ACID
        * numResidues
        * numMonomers);

    // RNA atoms: for every NTP
    // add 11.25 H + 9.5 C + 3.75 N + 7 O + 1 P.

    carbon.incrementMacromolecularOccurrence(CARBONS_PER_RNA_NUCLEOTIDE
        * numRNA
        * numMonomers);
    nitrogen.incrementMacromolecularOccurrence(NITROGENS_PER_RNA_NUCLEOTIDE
        * numRNA
        * numMonomers);
    oxygen.incrementMacromolecularOccurrence(OXYGENS_PER_RNA_NUCLEOTIDE
        * numRNA
        * numMonomers);
    hydrogen.incrementMacromolecularOccurrence(HYDROGENS_PER_RNA_NUCLEOTIDE
        * numRNA
        * numMonomers);
    phosphorus
        .incrementMacromolecularOccurrence(PHOSPHORUSES_PER_RNA_NUCLEOTIDE
            * numRNA * numMonomers);

    // DNA atoms: for every NTP
    // add 11.75 H + 9.75 C + 4 N + 6 O + 1 P.

    carbon.incrementMacromolecularOccurrence(CARBONS_PER_DNA_NUCLEOTIDE
        * numRNA
        * numMonomers);
    nitrogen.incrementMacromolecularOccurrence(NITROGENS_PER_DNA_NUCLEOTIDE
        * numRNA
        * numMonomers);
    oxygen.incrementMacromolecularOccurrence(OXYGENS_PER_DNA_NUCLEOTIDE
        * numRNA
        * numMonomers);
    hydrogen.incrementMacromolecularOccurrence(HYDROGENS_PER_DNA_NUCLEOTIDE
        * numRNA
        * numMonomers);
    phosphorus
        .incrementMacromolecularOccurrence(PHOSPHORUSES_PER_DNA_NUCLEOTIDE
            * numRNA * numMonomers);

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
}
