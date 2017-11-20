package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.raddo.raddose3D.Element.CrossSection;

public class CoefCalcCompute extends CoefCalc {
  /**
   * Identified coefficients and density from last program run. Final variables.
   */
  private double                     absCoeffcomp, absCoeffphoto, attCoeff, elasCoeff, density, cellVolume;
  
  /**
   * Set of the unique elements present in the crystal (including solvent
   * and macromolecular)
   */
  private Set<Element>               presentElements;

  /**
   * Percentage conversion.
   */
  protected static final double      PERCENTAGE_CONVERSION        = 100;

  /**
   * Protein density in g/ml.
   */
  protected static final double      PROTEIN_DENSITY              = 1.35;

  /**
   * RNA density in g/ml.
   */
  protected static final double      RNA_DENSITY                  = 1.3;

  /**
   * DNA density in g/ml.
   */
  protected static final double      DNA_DENSITY                  = 1.35;

  /**
   * Density of heteroatoms.
   */
  protected static final double      HETATM_DENSITY               = 1.35;

  /**
   * Atomic mass unit in grams.
   */
  protected static final double      ATOMIC_MASS_UNIT             = 1.66E-24;

  /**
   * Avogadro's number.
   */
  protected static final double      AVOGADRO_NUM                 = 6.022e+23;

  /**
   * Average weight of an amino acid.
   */
  protected static final double      AMINO_ACID_AVE_MASS          = 110.0;

  /**
   * Average weight of a DNA nucleotide.
   */
  protected static final double      DNA_NUCLEOTIDE_MASS          = 312.0;

  /**
   * Average weight of an RNA nucleotide.
   */
  protected static final double      RNA_NUCLEOTIDE_MASS          = 321.0;

  /**
   * Angstroms to ml conversion.
   */
  protected static final double      ANGSTROMS_TO_ML              = 1E-24;

  /**
   * conversion factor to make a number bigger. 1E27.
   */
  protected static final double      MASS_TO_CELL_VOLUME          = 1E27;

  /**
   * Water concentration in mM.
   */
  protected static final double      WATER_CONCENTRATION          = 55555;

  /**
   * Units per milli-unit.
   */
  protected static final long        UNITSPERMILLIUNIT            = 1000L;

  /**
   * Units per deci-unit.
   */
  protected static final long        UNITSPERDECIUNIT             = 10L;

  /** hydrogens per amino acid. */
  protected static final double      HYDROGENS_PER_AMINO_ACID     = 8;
  /** carbons per amino acid. */
  protected static final double      CARBONS_PER_AMINO_ACID       = 5;
  /** nitrogens per amino acid. */
  protected static final double      NITROGENS_PER_AMINO_ACID     = 1.35;
  /** oxygens per amino acid. */
  protected static final double      OXYGENS_PER_AMINO_ACID       = 1.5;

  /** hydrogens per RNA nucleotide. */
  protected static final double      HYDROGENS_PER_RNA_NUCLEOTIDE = 11.25;
  /** carbons per RNA nucleotide. */
  protected static final double      CARBONS_PER_RNA_NUCLEOTIDE   = 9.5;
  /** nitrogens per RNA nucleotide. */
  protected static final double      NITROGENS_PER_RNA_NUCLEOTIDE = 3.75;
  /** oxygens per RNA nucleotide. */
  protected static final double      OXYGENS_PER_RNA_NUCLEOTIDE   = 7;
  /** phosphoruses per RNA nucleotide. */
  protected static final double      PHOSPHORI_PER_RNA_NUCLEOTIDE = 1;

  /** hydrogens per DNA nucleotide. */
  protected static final double      HYDROGENS_PER_DNA_NUCLEOTIDE = 11.75;
  /** carbons per DNA nucleotide. */
  protected static final double      CARBONS_PER_DNA_NUCLEOTIDE   = 9.75;
  /** nitrogens per DNA nucleotide. */
  protected static final double      NITROGENS_PER_DNA_NUCLEOTIDE = 4;
  /** oxygens per DNA nucleotide. */
  protected static final double      OXYGENS_PER_DNA_NUCLEOTIDE   = 6;
  /** phosphoruses per DNA nucleotide. */
  protected static final double      PHOSPHORI_PER_DNA_NUCLEOTIDE = 1;
  
  /**
   * Number of X-ray Fluorescent escape factors
   */
  private static final int NUM_FLUOR_ESCAPE_FACTORS  = 18;

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
  private int                        numMonomers                  = 1;

  /**
   * Element database keeping the coefficients of all elements.
   */
  private final ElementDatabase      elementDB;
  
  private static final String        PHOTOELECTRIC                = "Photoelectric";
  private static final String        ELASTIC                      = "Elastic";
  private static final String        TOTAL                        = "Total";
  private static final String        COMPTON                      = "Compton Attenuation";
  private static final double        MIN_ATOMIC_NUM_FOR_K_SHELL_IONISATION = 11;
  private static final double        MIN_ATOMIC_NUM_FOR_L_SHELL_IONISATION = 16;

  /**
   * Number of atoms (only those that are not part of the protein), per
   * monomer.
   * TODO: maybe move to PDB class
   */
  private final Map<Element, Double> heteroAtomOccurrence;
  /** Number of atoms (all) per monomer. */
  private final Map<Element, Double> macromolecularOccurrence;
  /** Concentration of heavy atoms in the solvent in mMol. */
  private final Map<Element, Double> solventConcentration;
  /**
   * Number of atoms in the solvent per unit cell, derived from
   * solventContentration.
   */
  private final Map<Element, Double> solventOccurrence;

  /**
   * Simple constructor.
   */
  public CoefCalcCompute() {
    elementDB = ElementDatabase.getInstance();
    macromolecularOccurrence = new HashMap<Element, Double>();
    heteroAtomOccurrence = new HashMap<Element, Double>();
    solventOccurrence = new HashMap<Element, Double>();
    solventConcentration = new HashMap<Element, Double>();
  }
  
  /**
   * Calculate the density of the crystal from its composition.
   */
  protected void calculateDensity() {
    // density is easy. Loop through all atoms and calculate total mass.
    // then express as g / cm-3.
    double mass = 0;

    presentElements = new HashSet<Element>();
    presentElements.addAll(solventOccurrence.keySet());
    presentElements.addAll(macromolecularOccurrence.keySet());

    for (Element e : presentElements) {
      mass += totalAtoms(e) * e.getAtomicWeightInGrams();
    }
    
    density = mass * MASS_TO_CELL_VOLUME / (cellVolume * UNITSPERMILLIUNIT);
  }
  
  /**
   * Calculates the absorption, attenuation and elastic coefficients for the 
   * crystal and updates the corresponding instance properties
   * 
   *  @param b
   *    Beam object which contains the properties that describe the incident beam.
   */
  @Override
  public void updateCoefficients(final Beam b) { 
    Map<String, Double> absCoefficients = calculateCoefficients(b.getPhotonEnergy());
    attCoeff = absCoefficients.get(TOTAL);
    elasCoeff = absCoefficients.get(ELASTIC);
    absCoeffcomp = absCoefficients.get(COMPTON);
    absCoeffphoto = absCoefficients.get(PHOTOELECTRIC);
  }
  
  /**
   * Calculates the absorption, attenuation and elastic coefficients for
   * the entire crystal.
   * 
   * @param energy
   *          The energy in KeV of the incident photons.
   * @return
   *         Map containing the calculated coefficient values.
   */
  private Map<String, Double> calculateCoefficients(final double energy) {
    
    Map<String, Double> absCoeffs = new HashMap<String, Double>();
    double crossSectionPhotoElectric = 0;
    double crossSectionCoherent = 0;
    double crossSectionTotal = 0;
    double crossSectionComptonAttenuation = 0;

    // take cross section contributions from each individual atom
    // weighted by the cell volume
    Map<Element.CrossSection, Double> cs;
    for (Element e : this.presentElements) {
      cs = e.getAbsCoefficients(energy);
      crossSectionPhotoElectric += totalAtoms(e)
          * cs.get(CrossSection.PHOTOELECTRIC) / cellVolume
          / UNITSPERDECIUNIT;
      crossSectionCoherent += totalAtoms(e)
          * cs.get(CrossSection.COHERENT) / cellVolume
          / UNITSPERDECIUNIT;
      crossSectionTotal += totalAtoms(e)
          * cs.get(CrossSection.TOTAL) / cellVolume
          / UNITSPERDECIUNIT;
      crossSectionComptonAttenuation += totalAtoms(e) 
          * cs.get(CrossSection.COMPTON) / cellVolume
          / UNITSPERDECIUNIT;  
    }
    crossSectionPhotoElectric = crossSectionPhotoElectric / UNITSPERMILLIUNIT;
    crossSectionTotal = crossSectionTotal / UNITSPERMILLIUNIT;
    crossSectionCoherent = crossSectionCoherent / UNITSPERMILLIUNIT;
    crossSectionComptonAttenuation = crossSectionComptonAttenuation/ UNITSPERMILLIUNIT;
    
    absCoeffs.put(PHOTOELECTRIC, crossSectionPhotoElectric);
    absCoeffs.put(ELASTIC, crossSectionCoherent);
    absCoeffs.put(COMPTON, crossSectionComptonAttenuation);
    absCoeffs.put(TOTAL, crossSectionTotal);

    return absCoeffs;
  }
  
  /**
   * Calculates the absorption, attenuation and elastic coefficients for
   * the given set of elements.
   * 
   * @param energy
   *          The energy in KeV of the incident photons.
   * @param elementSet
   *          A set of elements for which the the coefficients are 
   *          calculated from.
   *          
   * @return
   *         Map containing the calculated coefficient values.
   */
  private Map<String, Double> calculateCoefficients(final double energy, 
      final Element element) {
    
    Map<String, Double> absCoeffs = new HashMap<String, Double>();
    double crossSectionPhotoElectric = 0;
    double crossSectionCoherent = 0;
    double crossSectionTotal = 0;
    double crossSectionComptonAttenuation = 0;

    // take cross section contributions from each individual atom
    // weighted by the cell volume
    Map<Element.CrossSection, Double> cs;
    cs = element.getAbsCoefficients(energy);
    crossSectionPhotoElectric += totalAtoms(element)
        * cs.get(CrossSection.PHOTOELECTRIC) / cellVolume
        / UNITSPERDECIUNIT;
    crossSectionCoherent += totalAtoms(element)
        * cs.get(CrossSection.COHERENT) / cellVolume
        / UNITSPERDECIUNIT;
    crossSectionTotal += totalAtoms(element)
        * cs.get(CrossSection.TOTAL) / cellVolume
        / UNITSPERDECIUNIT;
    crossSectionComptonAttenuation += totalAtoms(element)  
        * cs.get(CrossSection.COMPTON) / cellVolume
        / UNITSPERDECIUNIT;    
    
    crossSectionPhotoElectric = crossSectionPhotoElectric / UNITSPERMILLIUNIT;
    crossSectionTotal = crossSectionTotal / UNITSPERMILLIUNIT;
    crossSectionCoherent = crossSectionCoherent / UNITSPERMILLIUNIT;
    crossSectionComptonAttenuation = crossSectionComptonAttenuation/ UNITSPERMILLIUNIT;
    
    absCoeffs.put(PHOTOELECTRIC, crossSectionPhotoElectric);
    absCoeffs.put(ELASTIC, crossSectionCoherent);
    absCoeffs.put(COMPTON, crossSectionComptonAttenuation);
    absCoeffs.put(TOTAL, crossSectionTotal);

    return absCoeffs;
  }

  @Override
  public double[][] getFluorescentEscapeFactors(Beam beam) {
    double[][] fluorEscapeFactors = new double[presentElements.size()][NUM_FLUOR_ESCAPE_FACTORS];
    int element_counter = 0;
    double kShellEnergy,kFactorA,kFactorB,escapeMuAbsK,l1ShellEnergy,l1FactorA,l1FactorB,escapeMuAbsL1,l2ShellEnergy,l2FactorA,
    l2FactorB,escapeMuAbsL2,l3ShellEnergy,l3FactorA,l3FactorB,escapeMuAbsL3;
    Map<String, Double> photonMuAbsK;
    Map<String, Double> photonMuAbsL1;
    Map<String, Double> photonMuAbsL2;
    Map<String, Double> photonMuAbsL3;
    Map<String, Double> elAbsCoeffs;
    
    for (Element e : this.presentElements) {
      elAbsCoeffs = calculateCoefficients(beam.getPhotonEnergy(), e);
      e.EdgeRatio();
      if (beam.getPhotonEnergy() > e.getKEdge() &&
          e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_K_SHELL_IONISATION) {
        //K shell energy : checked from element database class
        kShellEnergy = e.getKEdge();
        //Probability of K shell ionization: checked worked out in element class
        kFactorA = e.getKShellIonisationProb();               
        //K shell fluorescent yield: checked from element database class
        kFactorB = e.getKShellFluorescenceYield();
        //This gives difference between the edge energies needed for fluorescent escape probability.
        // Only L1 shell as this is most likely and gives a relevant result
        photonMuAbsK = calculateCoefficients(e.getKEdge() - e.getL1Edge());
        //Fluorescent escape probability. This takes muabs as mupe. 
        //Fluorescence too low energy to consider compton in muabs
        escapeMuAbsK = photonMuAbsK.get(PHOTOELECTRIC);
      } 
      else {
        kShellEnergy = 0.0;
        kFactorA = 0.0;
        kFactorB = 0.0;
        escapeMuAbsK = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getL1Edge() &&
          e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_L_SHELL_IONISATION) {
        l1ShellEnergy = e.getL1Edge();
        l1FactorA = e.getL1ShellIonisationProb();
        l1FactorB = e.getL1ShellFluorescenceYield();
        photonMuAbsL1 = calculateCoefficients(e.getL1Edge() - e.getM1Edge());
        escapeMuAbsL1 = photonMuAbsL1.get(PHOTOELECTRIC);
      } 
      else {
        l1ShellEnergy = 0.0;
        l1FactorA = 0.0;
        l1FactorB = 0.0;
        escapeMuAbsL1 = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getL2Edge() &&
          e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_L_SHELL_IONISATION) {
        l2ShellEnergy = e.getL2Edge();
        l2FactorA = e.getL2ShellIonisationProb();
        l2FactorB = e.getL2ShellFluorescenceYield();
        photonMuAbsL2 = calculateCoefficients(e.getL2Edge() - e.getM1Edge());
        escapeMuAbsL2 = photonMuAbsL2.get(PHOTOELECTRIC);
      } 
      else {
        l2ShellEnergy = 0.0;
        l2FactorA = 0.0;
        l2FactorB = 0.0;
        escapeMuAbsL2 = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getL3Edge() &&
          e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_L_SHELL_IONISATION) {
        l3ShellEnergy = e.getL3Edge();
        l3FactorA = e.getL3ShellIonisationProb();
        l3FactorB = e.getL3ShellFluorescenceYield();
        photonMuAbsL3 = calculateCoefficients(e.getL3Edge() - e.getM1Edge());
        escapeMuAbsL3 = photonMuAbsL3.get(PHOTOELECTRIC);
      } 
      else {
        l3ShellEnergy = 0.0;
        l3FactorA = 0.0;
        l3FactorB = 0.0;
        escapeMuAbsL3 = 0.0;
      }
      
      double muAbsFrac = elAbsCoeffs.get(PHOTOELECTRIC) / absCoeffphoto;
      
      //TODO change these to be something more readable, like a map structure
      fluorEscapeFactors[element_counter][0] = muAbsFrac;
      fluorEscapeFactors[element_counter][1] = kShellEnergy;
      fluorEscapeFactors[element_counter][2] = kFactorA;
      fluorEscapeFactors[element_counter][3] = kFactorB;
      fluorEscapeFactors[element_counter][4] = escapeMuAbsK;
      fluorEscapeFactors[element_counter][5] = l1ShellEnergy;
      fluorEscapeFactors[element_counter][6] = l1FactorA;
      fluorEscapeFactors[element_counter][7] = l1FactorB;
      fluorEscapeFactors[element_counter][8] = escapeMuAbsL1;
      fluorEscapeFactors[element_counter][9] = l2ShellEnergy;
      fluorEscapeFactors[element_counter][10] = l2FactorA;
      fluorEscapeFactors[element_counter][11] = l2FactorB;
      fluorEscapeFactors[element_counter][12] = escapeMuAbsL2;
      fluorEscapeFactors[element_counter][13] = l3ShellEnergy;
      fluorEscapeFactors[element_counter][14] = l3FactorA;
      fluorEscapeFactors[element_counter][15] = l3FactorB;
      fluorEscapeFactors[element_counter][16] = escapeMuAbsL3;
      
      
      element_counter += 1;
    }
    return fluorEscapeFactors;
  }

  @Override
  public double getAbsorptionCoefficient() {
    return absCoeffphoto;
  }
  
  @Override
  public double getInelasticCoefficient() {
    return absCoeffcomp;
  }

  @Override
  public double getAttenuationCoefficient() {
    return attCoeff;
  }

  @Override
  public double getElasticCoefficient() {
    return elasCoeff;
  }

  @Override
  public double getDensity() {
    return density;
  }
  
  /**
   * @return the numAminoAcids
   */
  public double getNumAminoAcids() {
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
  public double getNumRNA() {
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
  public double getNumDNA() {
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
        "%n"
            + "Crystal coefficients calculated with RADDOSE-3D. %n"
            + "Photelectric Coefficient: %.2e /um.%n"
            + "Inelastic Coefficient: %.2e /um.%n"
            + "Elastic Coefficient: %.2e /um.%n"
            + "Attenuation Coefficient: %.2e /um.%n"
            + "Density: %.2f g/ml.%n",
        absCoeffphoto, absCoeffcomp, elasCoeff, attCoeff, density);
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

    for (Element e : heteroAtomOccurrence.keySet()) {
      if (e.getAtomicNumber() < Element.LIGHT_ATOM_MAX_NUM) {
        // TODO: Is this < or <= ?!

        hetatmMass += heteroAtomOccurrence.get(e)
            * e.getAtomicWeightInGrams();
      }
    }

    hetatmMass /= cellVolume * HETATM_DENSITY * ANGSTROMS_TO_ML;

    // We estimate the solvent fraction from the
    // remaining mass to be found in the crystal. Magic!

    double solventFraction = 1 - proteinMass - rnaMass - dnaMass
        - hetatmMass;

    // sanity check
    // TODO: Print to STDERR and/or crash out.
    if (solventFraction < 0) {
      System.out
          .println("Warning: Solvent mass calculated as a negative number...");
    }

    System.out.println(String.format("Solvent fraction determined as %.2f%%.",
        solventFraction * PERCENTAGE_CONVERSION));

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

    for (Element e : solventConcentration.keySet()) {
      double conc = solventConcentration.get(e);
      double atomCount = conc * AVOGADRO_NUM * cellVolume * solventFraction
          * 1E-3 * 1E-27;
      incrementSolventOccurrence(e, atomCount);
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
   * @param cellAlpha unit cell angle alpha in degrees
   * @param cellBeta unit cell angle beta in degrees
   * @param cellGamma unit cell angle gamma in degrees
   * @return cell volume in Angstroms cubed.
   */

  public double cellVolume(final double cellA, final double cellB,
      final double cellC,
      final double cellAlpha, final double cellBeta, final double cellGamma) {
    double alpha = Math.toRadians(cellAlpha);
    double beta = Math.toRadians(cellBeta);
    double gamma = Math.toRadians(cellGamma);

    double ult = 1.0 + 2.0 * Math.cos(alpha) * Math.cos(beta) * Math.cos(gamma)
        - Math.pow(Math.cos(alpha), 2.0) - Math.pow(Math.cos(beta), 2.0)
        - Math.pow(Math.cos(gamma), 2.0);

    if (ult < 0.0) {
      // TODO: Either print warning or crash out.
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

    System.out.printf("Cell volume: %.2f Angstroms cubed.%n",cellVolume);

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

  public Double getSolventOccurrence(final Element element) {
    if (solventOccurrence.containsKey(element)) {
      return solventOccurrence.get(element);
    } else {
      return 0.;
    }
  }

  public void incrementSolventOccurrence(final Element element,
      final Double increment) {
    if (solventOccurrence.containsKey(element)) {
      solventOccurrence.put(element,
          increment + solventOccurrence.get(element));
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
      heteroAtomOccurrence.put(element,
          increment + heteroAtomOccurrence.get(element));
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
      macromolecularOccurrence.put(element,
          increment + macromolecularOccurrence.get(element));
    } else {
      macromolecularOccurrence.put(element, increment);
    }
  }

  public void setMacromolecularOccurrence(final Element element,
      final Double mmOcc) {
    macromolecularOccurrence.put(element, mmOcc);
  }

  /**
   * Take into account the number of molecules in the unit cell; equal to NCS
   * symmetry operators multiplied
   * by CS symmetry operators.
   *
   * @param num
   *          number of molecules in unit cell
   */
  public void multiplyAtoms(final int num) {
    for (Element e : macromolecularOccurrence.keySet()) {
      setMacromolecularOccurrence(e,
          getMacromolecularOccurrence(e) * num);
    }

    for (Element e : heteroAtomOccurrence.keySet()) {
      setHetatmOccurrence(e,
          getHetatmOccurrence(e) * num);
    }
  }

  public double totalAtoms(final Element element) {
    return getSolventOccurrence(element) + getMacromolecularOccurrence(element);
  }
}
