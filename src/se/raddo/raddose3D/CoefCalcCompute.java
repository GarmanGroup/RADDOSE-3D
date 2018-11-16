package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import se.raddo.raddose3D.Element.CrossSection;

public class CoefCalcCompute extends CoefCalc {
  /**
   * Identified coefficients and density from last program run. Final variables.
   */
  private double                     absCoeffcomp, absCoeffphoto, attCoeff, elasCoeff, density, molecularWeight, molecularWeightSurrounding;
  
  private long numSimulatedElectrons;
  
  public double cellVolume;

  private double cryoAbsCoeffComp, cryoAbsCoeffPhoto, cryoAttCoeff, cryoElasCoeff, cryoDensity;
  
  /**
   * Parameters used to calculate the ionisation cross section for a specific shell of an element
   * This array is for when the overvoltage is less than 16
   * the array is [Atomic number][shell number][coefficient]
   * For shell number [K=0, L1=1, L2=2, L3=3, M1=4, M2=5, M3=6, M4=7, M5=8
   * For coefficient; 0=binding energy, 1 = a1, 2=a2, 3=a3, 4=a4, 5=a5
   */
  private double[][][] lowOvervoltages; 
  
  /**
   * Parameters used to calculate the ionisation cross section for a specific shell of an element
   * This array is for when the overvoltage is less than 16
   * the array is [Atomic number][shell number][coefficient]
   * For shell number K=0, L1=1, L2=2, L3=3, M1=4, M2=5, M3=6, M4=7, M5=8
   * For coefficient; 0=b-, 1=b+, 2=Anlj, 3=g1, 4=g2, 5=g3, 6=g4
   */
  private double[][][] highOvervoltages;
  
  /**
   * Set of the unique elements present in the crystal (including solvent
   * and macromolecular)
   */
  private Set<Element>               presentElements;
  
  /**
   * Set of the unique elements present in the crystal (including solvent
   * and macromolecular)
   */
  private Set<ElementEM>               presentElementsEM;

  /**
   * Set of the unique elements present in the cryo-solution
   */
  private Set<Element>               cryoElements;
  
  /**
   * Set of the unique elements present in the cryo-solution
   */
  private Set<ElementEM>               cryoElementsEM;
  
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
   * Carbohydrate density in g/ml.
   */
  protected static final double      CARBOHYDRATE_DENSITY                  = 1.54;

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
   * Average weight of a carbohydrate residue.
   */
  protected static final double      CARBOHYDRATE_AVE_MASS          = 162.0;

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
  protected static final double      WATER_CONCENTRATION          = 55555;    // Density of 1 g/cm^3

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
  
  /** hydrogens per amino acid. */
  protected static final double      HYDROGENS_PER_CARBOHYDRATE     = 11;
  /** carbons per amino acid. */
  protected static final double      CARBONS_PER_CARBOHYDRATE       = 6;
  /** oxygens per amino acid. */
  protected static final double      OXYGENS_PER_CARBOHYDRATE      = 5;
  
  /**
   * Number of X-ray Fluorescent escape factors
   */
  private static final int NUM_FLUOR_ESCAPE_FACTORS  = 27;

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
   * Number of carbohydrate residues.
   */
  private double                     numCarb;

  /**
   * Number of monomers per unit cell.
   */
  private int                        numMonomers                  = 1;

  /**
   * Element database keeping the coefficients of all elements.
   */
  private final ElementDatabase      elementDB;
  
  /**
   * Element database keeping the coefficients of all elements for EM.
   */
  private final ElementDatabaseEM      elementDBEM;
  
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
  
  /** Number of atoms (all) in cryoSolution per unit cell volume. */
  private final Map<Element, Double> cryoOccurrence;
  
  private final Map<Element, Double> cryoConcentration;
  
  /** Number of atoms (all) per monomer. */
  private final Map<ElementEM, Double> macromolecularOccurrenceEM;
  private final Map<ElementEM, Double> solventOccurrenceEM;
  private final Map<ElementEM, Double> heteroAtomOccurrenceEM;
  private final Map<ElementEM, Double> solventConcentrationEM;
  
  private final Map<ElementEM, Double> cryoOccurrenceEM;
  private final Map<ElementEM, Double> cryoConcentrationEM;
  
  public  Map<String, Double> fractionElementEM;
  
  
  public Map<Element, Double> betheXSections;
  
  public Map<Element, double[]> shellXSections;
  public double BethexSectionTotPerElement;
  public Map<Element, double[]> shellXSectionsSurrounding;
  public double BethexSectionTotPerElementSurrounding;
  
  public Map<ElementEM, Double> elasticXSections;
  public double ElasticxSectionTotPerElement;
  public Map<ElementEM, Double> elasticXSectionsSurrounding;
  public double ElasticxSectionTotPerElementSurrounding;
  
  
  public boolean isEM;

  /**
   * Simple constructor.
   */
  public CoefCalcCompute() {
    elementDB = ElementDatabase.getInstance();
    elementDBEM = ElementDatabaseEM.getInstance();
    macromolecularOccurrence = new HashMap<Element, Double>();
    heteroAtomOccurrence = new HashMap<Element, Double>();
    solventOccurrence = new HashMap<Element, Double>();
    solventConcentration = new HashMap<Element, Double>();
    cryoOccurrence = new HashMap<Element, Double>();
    cryoConcentration = new HashMap<Element, Double>();
    
    macromolecularOccurrenceEM = new HashMap<ElementEM, Double>();
    solventOccurrenceEM = new HashMap<ElementEM, Double>();
    heteroAtomOccurrenceEM = new HashMap<ElementEM, Double>();
    solventConcentrationEM = new HashMap<ElementEM, Double>();
    
    cryoOccurrenceEM = new HashMap<ElementEM, Double>();
    cryoConcentrationEM = new HashMap<ElementEM, Double>();
    
    fractionElementEM = new HashMap<String, Double>();
    
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
    
    presentElementsEM = new HashSet<ElementEM>();
    presentElementsEM.addAll(solventOccurrenceEM.keySet());
    presentElementsEM.addAll(macromolecularOccurrenceEM.keySet());

    for (Element e : presentElements) {
      mass += totalAtoms(e) * e.getAtomicWeightInGrams();
      molecularWeight += totalAtoms(e) * e.getAtomicWeight();
      //to test
 //     System.out.println(e.getAtomicNumber() + " " + totalAtoms(e));
    }
    
    density = mass * MASS_TO_CELL_VOLUME / (cellVolume * UNITSPERMILLIUNIT);
 //   System.out.println("Test");
    
    
  }
  
  /**
   * Calculate the density of the cryo-solution from its composition.
   */
  protected void calculateCryoDensity() {
    // density is easy. Loop through all atoms and calculate total mass.
    // then express as g / cm-3.
    double mass = 0;

    for (Element e : cryoElements) {
      mass += getCryoOccurrence(e) * e.getAtomicWeightInGrams();
      molecularWeightSurrounding += getCryoOccurrence(e) * e.getAtomicWeight();
    }
    
    cryoDensity = mass * MASS_TO_CELL_VOLUME / (cellVolume * UNITSPERMILLIUNIT);
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
    Map<String, Double> absCoefficients = calculateCoefficientsAll(b.getPhotonEnergy());
    attCoeff = absCoefficients.get(TOTAL);
    elasCoeff = absCoefficients.get(ELASTIC);
    absCoeffcomp = absCoefficients.get(COMPTON);
    absCoeffphoto = absCoefficients.get(PHOTOELECTRIC);
  }
  
  @Override
  public void updateCryoCoefficients(final Beam b) { 
    Map<String, Double> absCoefficients = calculateCryoCoefficientsAll(b.getPhotonEnergy());
    cryoAttCoeff = absCoefficients.get(TOTAL);
    cryoElasCoeff = absCoefficients.get(ELASTIC);
    cryoAbsCoeffComp = absCoefficients.get(COMPTON);
    cryoAbsCoeffPhoto = absCoefficients.get(PHOTOELECTRIC);
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
  private Map<String, Double> calculateCoefficientsAll(final double energy) {
    
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
  private Map<String, Double> calculateCoefficientsElement(final double energy, 
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

  /**
   * Calculates the absorption, attenuation and elastic coefficients for
   * the entire cryo solution.
   * 
   * @param energy
   *          The energy in KeV of the incident photons.
   * @return
   *         Map containing the calculated coefficient values.
   */
  private Map<String, Double> calculateCryoCoefficientsAll(final double energy) {
    
    Map<String, Double> absCoeffs = new HashMap<String, Double>();
    double crossSectionPhotoElectric = 0;
    double crossSectionCoherent = 0;
    double crossSectionTotal = 0;
    double crossSectionComptonAttenuation = 0;

    // take cross section contributions from each individual atom
    // weighted by the cell volume
    Map<Element.CrossSection, Double> cs;
    for (Element e : this.cryoElements) {
      cs = e.getAbsCoefficients(energy);
      crossSectionPhotoElectric += getCryoOccurrence(e)
          * cs.get(CrossSection.PHOTOELECTRIC) / cellVolume
          / UNITSPERDECIUNIT;
      crossSectionCoherent += getCryoOccurrence(e)
          * cs.get(CrossSection.COHERENT) / cellVolume
          / UNITSPERDECIUNIT;
      crossSectionTotal += getCryoOccurrence(e)
          * cs.get(CrossSection.TOTAL) / cellVolume
          / UNITSPERDECIUNIT;
      crossSectionComptonAttenuation += getCryoOccurrence(e) 
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
   * the given set of elements in the surrounding.
   * @param energy
   * @param element
   * @return
   */
  private Map<String, Double> calculateCoefficientsCryoElement(final double energy, 
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
    crossSectionPhotoElectric += getCryoOccurrence(element)
        * cs.get(CrossSection.PHOTOELECTRIC) / cellVolume
        / UNITSPERDECIUNIT;
    crossSectionCoherent += getCryoOccurrence(element)
        * cs.get(CrossSection.COHERENT) / cellVolume
        / UNITSPERDECIUNIT;
    crossSectionTotal += getCryoOccurrence(element)
        * cs.get(CrossSection.TOTAL) / cellVolume
        / UNITSPERDECIUNIT;
    crossSectionComptonAttenuation += getCryoOccurrence(element)  
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
    
    double m1ShellEnergy, m2ShellEnergy, m3ShellEnergy, m4ShellEnergy, m5ShellEnergy,
           m1FactorA, m2FactorA, m3FactorA, m4FactorA, m5FactorA;
    
    Map<String, Double> photonMuAbsL1;
    Map<String, Double> photonMuAbsL2;
    Map<String, Double> photonMuAbsL3;
    
    Map<String, Double> elAbsCoeffs;
    
    for (Element e : this.presentElements) {
      elAbsCoeffs = calculateCoefficientsElement(beam.getPhotonEnergy(), e);
      e.EdgeRatio();
      if (beam.getPhotonEnergy() > e.getKEdge() ) {// &&   //if beam energy below 1.072keV then this is never true and the program messes up when flcalc is true
        //   e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_K_SHELL_IONISATION)  {
        //K shell energy : checked from element database class
        kShellEnergy = e.getKEdge();
        //Probability of K shell ionization: checked worked out in element class
        kFactorA = e.getKShellIonisationProb(); //this is probability of K relative to other shells              
        //K shell fluorescent yield: checked from element database class
        kFactorB = e.getKShellFluorescenceYield();
        //This gives difference between the edge energies needed for fluorescent escape probability.

        photonMuAbsK = calculateCoefficientsAll(e.getKFluorescenceAverage());

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
          //   e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_L_SHELL_IONISATION) {
          e.getAtomicNumber() >= 12) {
        l1ShellEnergy = e.getL1Edge();
        l1FactorA = (e.getL1ShellIonisationProb()) * (1-kFactorA);
      //  l1FactorB = e.getL1ShellFluorescenceYield();
               
      //  photonMuAbsL1 = calculateCoefficientsAll(e.getLFluorescenceAverage());       
      //  escapeMuAbsL1 = photonMuAbsL1.get(PHOTOELECTRIC);       
      } 
      else {
        l1ShellEnergy = 0.0;
        l1FactorA = 0.0;
        l1FactorB = 0.0;
        escapeMuAbsL1 = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getL2Edge() &&
          //   e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_L_SHELL_IONISATION) {
          e.getAtomicNumber() >= 12) {
        l2ShellEnergy = e.getL2Edge();
        l2FactorA = e.getL2ShellIonisationProb() * (1-kFactorA - l1FactorA);
      //  l2FactorB = e.getL2ShellFluorescenceYield();

      //  photonMuAbsL2 = calculateCoefficientsAll(e.getLFluorescenceAverage());       
      //  escapeMuAbsL2 = photonMuAbsL2.get(PHOTOELECTRIC);
      } 
      else {
        l2ShellEnergy = 0.0;
        l2FactorA = 0.0;
        l2FactorB = 0.0;
        escapeMuAbsL2 = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getL3Edge() &&
          //   e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_L_SHELL_IONISATION) {
          e.getAtomicNumber() >= 12) {
        l3ShellEnergy = e.getL3Edge();
        l3FactorA = e.getL3ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA);
      //  l3FactorB = e.getL3ShellFluorescenceYield();

      //  photonMuAbsL3 = calculateCoefficientsAll(e.getLFluorescenceAverage());       
      //  escapeMuAbsL3 = photonMuAbsL3.get(PHOTOELECTRIC);
      } 
      else {
        l3ShellEnergy = 0.0;
        l3FactorA = 0.0;
        l3FactorB = 0.0;
        escapeMuAbsL3 = 0.0;
      }
      
      //All the M shells
      
      if (beam.getPhotonEnergy() > e.getM1Edge() && e.getAtomicNumber() >= 73) { 
        m1ShellEnergy = e.getM1Edge();
        m1FactorA = e.getM1ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA - l3FactorA);
      }
      else {
        m1ShellEnergy = 0.0;
        m1FactorA = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getM2Edge() && e.getAtomicNumber() >= 73) { // if it equals uranium for now, set a cut off later
        m2ShellEnergy = e.getM2Edge();
        m2FactorA = e.getM2ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA - l3FactorA - m1FactorA);
      }
      else {
        m2ShellEnergy = 0.0;
        m2FactorA = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getM3Edge() && e.getAtomicNumber() >= 73) { // if it equals uranium for now, set a cut off later
        m3ShellEnergy = e.getM3Edge();
        m3FactorA = e.getM3ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA - l3FactorA - m1FactorA - m2FactorA);
      }
      else {
        m3ShellEnergy = 0.0;
        m3FactorA = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getM4Edge() && e.getAtomicNumber() >= 73) { // if it equals uranium for now, set a cut off later
        m4ShellEnergy = e.getM4Edge();
        m4FactorA = e.getM4ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA - l3FactorA - m1FactorA - m2FactorA - m3FactorA);
      }
      else {
        m4ShellEnergy = 0.0;
        m4FactorA = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getM5Edge() && e.getAtomicNumber() >= 73) { // if it equals uranium for now, set a cut off later
        m5ShellEnergy = e.getM5Edge();
        m5FactorA = e.getM5ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA - l3FactorA - m1FactorA - m2FactorA - m3FactorA - m4FactorA);
      }
      else {
        m5ShellEnergy = 0.0;
        m5FactorA = 0.0;
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
     // fluorEscapeFactors[element_counter][7] = l1FactorB;
     // fluorEscapeFactors[element_counter][8] = escapeMuAbsL1;
      fluorEscapeFactors[element_counter][9] = l2ShellEnergy;
      fluorEscapeFactors[element_counter][10] = l2FactorA;
    //  fluorEscapeFactors[element_counter][11] = l2FactorB;
    //  fluorEscapeFactors[element_counter][12] = escapeMuAbsL2;
      fluorEscapeFactors[element_counter][13] = l3ShellEnergy;
      fluorEscapeFactors[element_counter][14] = l3FactorA;
    //  fluorEscapeFactors[element_counter][15] = l3FactorB;
    //  fluorEscapeFactors[element_counter][16] = escapeMuAbsL3;
      
      fluorEscapeFactors[element_counter][17] = m1ShellEnergy;
      fluorEscapeFactors[element_counter][18] = m1FactorA;
      fluorEscapeFactors[element_counter][19] = m2ShellEnergy;
      fluorEscapeFactors[element_counter][20] = m2FactorA;
      fluorEscapeFactors[element_counter][21] = m3ShellEnergy;
      fluorEscapeFactors[element_counter][22] = m3FactorA;
      fluorEscapeFactors[element_counter][23] = m4ShellEnergy;
      fluorEscapeFactors[element_counter][24] = m4FactorA;
      fluorEscapeFactors[element_counter][25] = m5ShellEnergy;
      fluorEscapeFactors[element_counter][26] = m5FactorA;

      element_counter += 1;
    }    
    return fluorEscapeFactors;
  }

@Override
  public double[][] getCryoFluorescentEscapeFactors(Beam beam) {
    double[][] fluorEscapeFactors = new double[cryoElements.size()][NUM_FLUOR_ESCAPE_FACTORS];
    int element_counter = 0;
    double kShellEnergy,kFactorA,kFactorB,escapeMuAbsK,l1ShellEnergy,l1FactorA,l2ShellEnergy,l2FactorA,
    l3ShellEnergy,l3FactorA;
    Map<String, Double> photonMuAbsK;
    
    double m1ShellEnergy, m2ShellEnergy, m3ShellEnergy, m4ShellEnergy, m5ShellEnergy,
           m1FactorA, m2FactorA, m3FactorA, m4FactorA, m5FactorA;
    
    Map<String, Double> elAbsCoeffs;
    
    for (Element e : this.cryoElements) {
      elAbsCoeffs = calculateCoefficientsCryoElement(beam.getPhotonEnergy(), e);
      e.EdgeRatio();
      if (beam.getPhotonEnergy() > e.getKEdge() ) {// &&   //if beam energy below 1.072keV then this is never true and the program messes up when flcalc is true
       //   e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_K_SHELL_IONISATION)  {
        //K shell energy : checked from element database class
        kShellEnergy = e.getKEdge();
        //Probability of K shell ionization: checked worked out in element class
        kFactorA = e.getKShellIonisationProb();               
        //K shell fluorescent yield: checked from element database class
        kFactorB = e.getKShellFluorescenceYield();
        //This gives difference between the edge energies needed for fluorescent escape probability.

        photonMuAbsK = calculateCoefficientsAll(e.getKFluorescenceAverage());
        
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
       //   e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_L_SHELL_IONISATION) {
          e.getAtomicNumber() >= 12) {
        l1ShellEnergy = e.getL1Edge();
        l1FactorA = (e.getL1ShellIonisationProb()) * (1-kFactorA);
      //  l1FactorB = e.getL1ShellFluorescenceYield();

      //  photonMuAbsL1 = calculateCoefficientsAll(e.getLFluorescenceAverage());       
      //  escapeMuAbsL1 = photonMuAbsL1.get(PHOTOELECTRIC);  
      } 
      else {
        l1ShellEnergy = 0.0;
        l1FactorA = 0.0;

      }
      
      if (beam.getPhotonEnergy() > e.getL2Edge() &&
//        e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_L_SHELL_IONISATION) {
          e.getAtomicNumber() >= 12) {
        l2ShellEnergy = e.getL2Edge();
        l2FactorA = e.getL2ShellIonisationProb() * (1-kFactorA - l1FactorA);
      //  l2FactorB = e.getL2ShellFluorescenceYield();

      //  photonMuAbsL2 = calculateCoefficientsAll(e.getLFluorescenceAverage());     
      //  escapeMuAbsL2 = photonMuAbsL2.get(PHOTOELECTRIC);
      } 
      else {
        l2ShellEnergy = 0.0;
        l2FactorA = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getL3Edge() &&
//        e.getAtomicNumber() >= MIN_ATOMIC_NUM_FOR_L_SHELL_IONISATION) {
          e.getAtomicNumber() >= 12) {
        l3ShellEnergy = e.getL3Edge();
        l3FactorA = e.getL3ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA);
      //  l3FactorB = e.getL3ShellFluorescenceYield();

      //  photonMuAbsL3 = calculateCoefficientsAll(e.getLFluorescenceAverage());    
      //  escapeMuAbsL3 = photonMuAbsL3.get(PHOTOELECTRIC);
      } 
      else {
        l3ShellEnergy = 0.0;
        l3FactorA = 0.0;
      }
      
      //All the M shells
      
      if (beam.getPhotonEnergy() > e.getM1Edge() && e.getAtomicNumber() >= 73) { 
        m1ShellEnergy = e.getM1Edge();
        m1FactorA = e.getM1ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA - l3FactorA);
      }
      else {
        m1ShellEnergy = 0.0;
        m1FactorA = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getM2Edge() && e.getAtomicNumber() >= 73) { // if it equals uranium for now, set a cut off later
        m2ShellEnergy = e.getM2Edge();
        m2FactorA = e.getM2ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA - l3FactorA - m1FactorA);
      }
      else {
        m2ShellEnergy = 0.0;
        m2FactorA = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getM3Edge() && e.getAtomicNumber() >= 73) { // if it equals uranium for now, set a cut off later
        m3ShellEnergy = e.getM3Edge();
        m3FactorA = e.getM3ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA - l3FactorA - m1FactorA - m2FactorA);
      }
      else {
        m3ShellEnergy = 0.0;
        m3FactorA = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getM4Edge() && e.getAtomicNumber() >= 73) { // if it equals uranium for now, set a cut off later
        m4ShellEnergy = e.getM4Edge();
        m4FactorA = e.getM4ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA - l3FactorA - m1FactorA - m2FactorA - m3FactorA);
      }
      else {
        m4ShellEnergy = 0.0;
        m4FactorA = 0.0;
      }
      
      if (beam.getPhotonEnergy() > e.getM5Edge() && e.getAtomicNumber() >= 73) { // if it equals uranium for now, set a cut off later
        m5ShellEnergy = e.getM5Edge();
        m5FactorA = e.getM5ShellIonisationProb() * (1-kFactorA - l1FactorA - l2FactorA - l3FactorA - m1FactorA - m2FactorA - m3FactorA - m4FactorA);
      }
      else {
        m5ShellEnergy = 0.0;
        m5FactorA = 0.0;
      }

      double muAbsFrac = elAbsCoeffs.get(PHOTOELECTRIC) / cryoAbsCoeffPhoto;
      
      //TODO change these to be something more readable, like a map structure
      fluorEscapeFactors[element_counter][0] = muAbsFrac;
      fluorEscapeFactors[element_counter][1] = kShellEnergy; 
      fluorEscapeFactors[element_counter][2] = kFactorA; // ionisation
      fluorEscapeFactors[element_counter][3] = kFactorB; //yield
      fluorEscapeFactors[element_counter][4] = escapeMuAbsK;
      
      fluorEscapeFactors[element_counter][5] = l1ShellEnergy;
      fluorEscapeFactors[element_counter][6] = l1FactorA;
     // fluorEscapeFactors[element_counter][7] = l1FactorB;
     // fluorEscapeFactors[element_counter][8] = escapeMuAbsL1;
      fluorEscapeFactors[element_counter][9] = l2ShellEnergy;
      fluorEscapeFactors[element_counter][10] = l2FactorA;
    //  fluorEscapeFactors[element_counter][11] = l2FactorB;
    //  fluorEscapeFactors[element_counter][12] = escapeMuAbsL2;
      fluorEscapeFactors[element_counter][13] = l3ShellEnergy;
      fluorEscapeFactors[element_counter][14] = l3FactorA;
    //  fluorEscapeFactors[element_counter][15] = l3FactorB;
    //  fluorEscapeFactors[element_counter][16] = escapeMuAbsL3;
      
      fluorEscapeFactors[element_counter][17] = m1ShellEnergy;
      fluorEscapeFactors[element_counter][18] = m1FactorA;
      fluorEscapeFactors[element_counter][19] = m2ShellEnergy;
      fluorEscapeFactors[element_counter][20] = m2FactorA;
      fluorEscapeFactors[element_counter][21] = m3ShellEnergy;
      fluorEscapeFactors[element_counter][22] = m3FactorA;
      fluorEscapeFactors[element_counter][23] = m4ShellEnergy;
      fluorEscapeFactors[element_counter][24] = m4FactorA;
      fluorEscapeFactors[element_counter][25] = m5ShellEnergy;
      fluorEscapeFactors[element_counter][26] = m5FactorA;
        
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
  
  @Override
  public double getCryoAbsorptionCoefficient() {
    return cryoAbsCoeffPhoto;
  }
  
  @Override
  public double getCryoDensity() {
    return cryoDensity;
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
   * @return the numCarb
   */
  public double getNumCarb() {
    return numCarb;
  }

  /**
   * @param newnumDNA the numDNA to set
   */
  protected void setNumCarb(final double newnumCarb) {
    this.numCarb = newnumCarb;
  }

  /**
   * @param increment the numCarb to increment
   */
  protected void incrementNumCarb(final double increment) {
    this.numCarb += increment;
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
  
  
  public ElementDatabaseEM getParserEM() {
    return elementDBEM;
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

    //change protein density to that calculated by Fischer et al 2004
    double proteinDensity = 1.35;
 //   double mW = (AMINO_ACID_AVE_MASS * numAminoAcids)/1000;
 //   proteinDensity = 1.410 + 0.145 * Math.exp(-mW/13);
  
    double proteinMass = ATOMIC_MASS_UNIT * AMINO_ACID_AVE_MASS
        * numAminoAcids * numMonomers;
    proteinMass /= cellVolume * proteinDensity * ANGSTROMS_TO_ML;

    double rnaMass = ATOMIC_MASS_UNIT * RNA_NUCLEOTIDE_MASS * numRNA
        * numMonomers;
    rnaMass /= cellVolume * RNA_DENSITY * ANGSTROMS_TO_ML;

    double dnaMass = ATOMIC_MASS_UNIT * DNA_NUCLEOTIDE_MASS * numDNA
        * numMonomers;
    dnaMass /= cellVolume * DNA_DENSITY * ANGSTROMS_TO_ML;
    
    double carbMass = ATOMIC_MASS_UNIT * CARBOHYDRATE_AVE_MASS * numCarb
        * numMonomers;
    carbMass /= cellVolume * CARBOHYDRATE_DENSITY * ANGSTROMS_TO_ML;

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
        - hetatmMass - carbMass;

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
    //same for EM
    for (ElementEM eM : solventConcentrationEM.keySet()) {
      double conc = solventConcentrationEM.get(eM);
      double atomCount = conc * AVOGADRO_NUM * cellVolume * solventFraction
          * 1E-3 * 1E-27;
      incrementSolventOccurrenceEM(eM, atomCount);
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
    
    ElementEM hydrogenEM = elementDBEM.getElement("H");
    setSolventOccurrenceEM(hydrogenEM, getSolventOccurrenceEM(hydrogenEM)
        + waterMolecules * 2);

    ElementEM oxygenEM = elementDBEM.getElement("O");
    setSolventOccurrenceEM(oxygenEM, getSolventOccurrenceEM(oxygenEM) + waterMolecules);

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
      
      ElementEM heavyAtomEM = elementDBEM.getElement(heavySolvConcNames.get(i));
      setSolventConcentrationEM(heavyAtomEM, heavySolvConcNums.get(i)
          + getSolventConcentrationEM(heavyAtomEM));
    }
  }
  
  /**
   * Combine concentrations of heavy atoms in the surrounding and add elements to the voxels 
   * @param cryoSolutionAtoms
   * @param cryoSolutionConcs
   * @param oilBased
   */
  public void addCryoConcentrations(final List<String> cryoSolutionAtoms,
      final List<Double> cryoSolutionConcs, final String oilBased, 
      final List<String> oilElementNames, final List<Double> oilElementsNums, final double oilDensity) {
    double nonWaterAtoms = 0;
    
    //check if oil based
    boolean calcWater = true;
    if (oilBased != null) {
      String oilBase = oilBased.toUpperCase().trim();
      if ("TRUE".equals(oilBase)) {
        calcWater = false;
      }
    }
    
    // add in the concentrations of the cryo-solution atoms
    //need to only do if not null when change
    if (cryoSolutionAtoms != null) {
      for (int i = 0; i < cryoSolutionAtoms.size(); i++) {
        Element cryoAtom = elementDB.getElement(cryoSolutionAtoms.get(i));
        setCryoConcentration(cryoAtom, cryoSolutionConcs.get(i)
            + getCryoConcentration(cryoAtom));
        
        ElementEM cryoAtomEM = elementDBEM.getElement(cryoSolutionAtoms.get(i));
        setCryoConcentrationEM(cryoAtomEM, cryoSolutionConcs.get(i)
            + getCryoConcentrationEM(cryoAtomEM));
      }
    }
      
      // use oil density if oilbased is true 
    if (calcWater == false) {
      double gPerMol = 0;
      if (oilElementNames != null) {
      for (int i = 0; i < oilElementNames.size(); i++) {
        Element cryoAtom = elementDB.getElement(oilElementNames.get(i));
        //calculate the g/mol
        gPerMol += cryoAtom.getAtomicWeight() *  oilElementsNums.get(i);
      }
      double oilConc = (oilDensity/gPerMol) * 1E6;
      for (int i = 0; i < oilElementNames.size(); i++) {
        Element cryoAtom = elementDB.getElement(oilElementNames.get(i));
        double elementConc = oilConc * oilElementsNums.get(i);
        setCryoConcentration(cryoAtom, elementConc
            + getCryoConcentration(cryoAtom));
        
        ElementEM cryoAtomEM = elementDBEM.getElement(oilElementNames.get(i));
        setCryoConcentrationEM(cryoAtomEM, elementConc
            + getCryoConcentrationEM(cryoAtomEM));
        
      }
      }
    }
       
    for (Element e : cryoConcentration.keySet()) {
        double conc = cryoConcentration.get(e);
        double atomCount = conc * AVOGADRO_NUM * cellVolume * 1E-3 * 1E-27;
        incrementCryoOccurrence(e, atomCount);
        nonWaterAtoms += atomCount;
    }
    for (ElementEM e : cryoConcentrationEM.keySet()) {
      double conc = cryoConcentrationEM.get(e);
      double atomCount = conc * AVOGADRO_NUM * cellVolume * 1E-3 * 1E-27;
      incrementCryoOccurrenceEM(e, atomCount);
    }
    
   
    

    
    if (calcWater == true){
      //Add in water
      double waterMolecules = WATER_CONCENTRATION * AVOGADRO_NUM
          / UNITSPERMILLIUNIT
          * cellVolume * (1 / MASS_TO_CELL_VOLUME) - nonWaterAtoms;
    
      // Add water molecules to hydrogen and oxygen.

      Element hydrogen = elementDB.getElement("H");
      setCryoOccurrence(hydrogen, getCryoOccurrence(hydrogen)
          + waterMolecules * 2);

      Element oxygen = elementDB.getElement("O");
      setCryoOccurrence(oxygen, getCryoOccurrence(oxygen) + waterMolecules);
      
      ElementEM hydrogenEM = elementDBEM.getElement("H");
      setCryoOccurrenceEM(hydrogenEM, getCryoOccurrenceEM(hydrogenEM)
          + waterMolecules * 2);

      ElementEM oxygenEM = elementDBEM.getElement("O");
      setCryoOccurrenceEM(oxygenEM, getCryoOccurrenceEM(oxygenEM) + waterMolecules);
    }
    
    cryoElements = new HashSet<Element>();
    cryoElements.addAll(cryoOccurrence.keySet());
    
    cryoElementsEM = new HashSet<ElementEM>();
    cryoElementsEM.addAll(cryoOccurrenceEM.keySet());
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
  
  public void setSolventConcentrationEM(final ElementEM element,
      final Double newsolventConcentration) {
    solventConcentrationEM.put(element, newsolventConcentration);
  }
  
  

  public double getSolventConcentration(final Element element) {
    if (solventConcentration.containsKey(element)) {
      return solventConcentration.get(element);
    } else {
      return 0.;
    }
  }
  
  public double getSolventConcentrationEM(final ElementEM element) {
    if (solventConcentrationEM.containsKey(element)) {
      return solventConcentrationEM.get(element);
    } else {
      return 0.;
    }
  }
  
  public void setCryoConcentration(final Element element,
      final Double newsolventConcentration) {
    cryoConcentration.put(element, newsolventConcentration);
  }
  
  public void setCryoConcentrationEM(final ElementEM element,
      final Double newsolventConcentration) {
    cryoConcentrationEM.put(element, newsolventConcentration);
  }
  
  public double getCryoConcentration(final Element element) {
    if (cryoConcentration.containsKey(element)) {
      return cryoConcentration.get(element);
    } else {
      return 0.;
    }
  }
  
  public double getCryoConcentrationEM(final ElementEM element) {
    if (cryoConcentrationEM.containsKey(element)) {
      return cryoConcentrationEM.get(element);
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
  
  public Double getSolventOccurrenceEM(final ElementEM element) {
    if (solventOccurrenceEM.containsKey(element)) {
      return solventOccurrenceEM.get(element);
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
  
  public void incrementSolventOccurrenceEM(final ElementEM element,
      final Double increment) {
    if (solventOccurrenceEM.containsKey(element)) {
      solventOccurrenceEM.put(element,
          increment + solventOccurrenceEM.get(element));
    } else {
      solventOccurrenceEM.put(element, increment);
    }
  }

  public void setSolventOccurrenceEM(final ElementEM element,
      final Double newsolventOccurrence) {
    solventOccurrenceEM.put(element, newsolventOccurrence);
  }

  
  public Double getCryoOccurrence(final Element element) {
    if (cryoOccurrence.containsKey(element)) {
      return cryoOccurrence.get(element);
    } else {
      return 0.;
    }
  }

  public void incrementCryoOccurrence(final Element element,
      final Double increment) {
    if (cryoOccurrence.containsKey(element)) {
      cryoOccurrence.put(element,
          increment + cryoOccurrence.get(element));
    } else {
      cryoOccurrence.put(element, increment);
    }
  }

  public void setCryoOccurrence(final Element element,
      final Double newsolventOccurrence) {
    cryoOccurrence.put(element, newsolventOccurrence);
  }

  public Double getCryoOccurrenceEM(final ElementEM element) {
    if (cryoOccurrenceEM.containsKey(element)) {
      return cryoOccurrenceEM.get(element);
    } else {
      return 0.;
    }
  }

  public void incrementCryoOccurrenceEM(final ElementEM element,
      final Double increment) {
    if (cryoOccurrenceEM.containsKey(element)) {
      cryoOccurrenceEM.put(element,
          increment + cryoOccurrenceEM.get(element));
    } else {
      cryoOccurrenceEM.put(element, increment);
    }
  }

  public void setCryoOccurrenceEM(final ElementEM element,
      final Double newsolventOccurrence) {
    cryoOccurrenceEM.put(element, newsolventOccurrence);
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
  
  
  public Double getHetatmOccurrenceEM(final ElementEM element) {
    if (heteroAtomOccurrenceEM.containsKey(element)) {
      return heteroAtomOccurrenceEM.get(element);
    } else {
      return 0.;
    }
  }

  public void incrementHetatmOccurrenceEM(final ElementEM element,
      final Double increment) {
    if (heteroAtomOccurrenceEM.containsKey(element)) {
      heteroAtomOccurrenceEM.put(element,
          increment + heteroAtomOccurrenceEM.get(element));
    } else {
      heteroAtomOccurrenceEM.put(element, increment);
    }
  }

  public void setHetatmOccurrenceEM(final ElementEM element, final Double haOcc) {
    heteroAtomOccurrenceEM.put(element, haOcc);
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
 //   System.out.println("test");
  }

  public void setMacromolecularOccurrence(final Element element,
      final Double mmOcc) {
    macromolecularOccurrence.put(element, mmOcc);
  }
  
  public Double getMacromolecularOccurrenceEM(final ElementEM element) {
    if (macromolecularOccurrenceEM.containsKey(element)) {
      return macromolecularOccurrenceEM.get(element);
    } else {
      return 0.;
    }
  }

  public void incrementMacromolecularOccurrenceEM(final ElementEM element,
      final Double increment) {
    if (macromolecularOccurrenceEM.containsKey(element)) {
      macromolecularOccurrenceEM.put(element,
          increment + macromolecularOccurrenceEM.get(element));
    } else {
      macromolecularOccurrenceEM.put(element, increment);
    }
 //   System.out.println("test");
  }

  public void setMacromolecularOccurrenceEM(final ElementEM element,
      final Double mmOcc) {
    macromolecularOccurrenceEM.put(element, mmOcc);
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
  
  public void multiplyAtomsEM(final int num) {
    for (ElementEM e : macromolecularOccurrenceEM.keySet()) {
      setMacromolecularOccurrenceEM(e,
          getMacromolecularOccurrenceEM(e) * num);
    }

    for (ElementEM e : heteroAtomOccurrenceEM.keySet()) {
      setHetatmOccurrenceEM(e,
          getHetatmOccurrenceEM(e) * num);
    }
  }
  

  public double totalAtoms(final Element element) {
    return getSolventOccurrence(element) + getMacromolecularOccurrence(element);
  }
  
  public double totalAtomsEM(final ElementEM element) {
    return getSolventOccurrenceEM(element) + getMacromolecularOccurrenceEM(element);
  }
  
  
  @Override
  public boolean isCryo() {
    if (cryoElements != null) { // will this need to change?
      return true;
    }
    else {
      return false;
    }
  }
  
  @Override
  public Set<Element> getPresentElements(boolean cryo){
    if (cryo == false) {
      return presentElements;
    }
    else {
      return cryoElements;
    }
  }
  
  
  
  @Override 
  public double getElectronElastic(double avgEnergy) { //need to think about how to incorporate the thingy about cutting off most in here
    //Individual atom cross sections
    double[] elasticElement = new double[presentElements.size()];
    double elasticMolecule = 0;
    double m = 9.10938356E-31; // in Kg
    double csquared = 3E8*3E8;  // (m/s)^2   //update this to be precise
    double Vo = avgEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
   
    double molWeight = 0;
    int counter = 0;
    for (Element e : this.presentElements) {
      elasticElement[counter] =  ((1.4E-6 * Math.pow(e.getAtomicNumber(), 1.5))/betaSquared)*
                       ((1-(0.26*e.getAtomicNumber())/(137*Math.pow(betaSquared, 0.5))));
                   //    *1E06; // convert nm^2 to pm^2
      
      //do by ELSEPA as more accurate if in the table
      
      /*
      if (avgEnergy <= 300) {
        ReadElasticFile rdEl = new ReadElasticFile();
        double x_section = rdEl.openFile("constants/electron_elastic.txt", beam.getPhotonEnergy(), e.getAtomicNumber()); 
        if (x_section > 0.0) {
          elasticElement[counter] = x_section;
        }
      }
      */

      double numEl = totalAtoms(e);
      elasticMolecule += elasticElement[counter] * numEl;
      molWeight += numEl * e.getAtomicWeight();
      counter += 1;
    }

    double massScatteringCoefficient = elasticMolecule / molWeight;
    double PoverT = 602 * massScatteringCoefficient  * density; //thickness in nm ; //* (EMThickness/10);
    
    return PoverT;
  }
  /**
   * Return the electron elastic cross section of the material in nm^2
   * @param electronEnergy
   * @return
   */
  private double getElectronElasticCrossSection(double electronEnergy, boolean surrounding) {
    //set stuff for surrounding or not
    Set<ElementEM> elementList = this.presentElementsEM;
    if (surrounding == true) {
      elementList = cryoElementsEM;
    }
    
    double[] elasticElement = new double[elementList.size()];
    double elasticMolecule = 0, partLambda =0, numerator = 0, denominator = 0;
    double m = 9.10938356E-31; // in Kg
    double csquared = 3E8*3E8;  // (m/s)^2   //update this to be precise
    double Vo = electronEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    int counter = 0;
    double sumZ = 0, sumA = 0;
    double xSectionTotPerElement = 0;
    ElasticxSectionTotPerElement = 0;
    ElasticxSectionTotPerElementSurrounding = 0;
    elasticXSections = new HashMap<ElementEM, Double>();
    elasticXSectionsSurrounding = new HashMap<ElementEM, Double>();

    
    for (ElementEM e : elementList) {
      double Z = e.getAtomicNumber();
      elasticElement[counter] =  ((1.4E-6 * Math.pow(Z, 1.5))/betaSquared)*
                       ((1-(0.26*Z)/(137*Math.pow(betaSquared, 0.5))));
                   //    *1E06; // nm^2/atom
      double A = e.getAtomicWeight();
      double molWeightFraction = 0;
 //     double atomicFraction = totalAtomsEM(e) / totAtoms;
      //do by ELSEPA as more accurate if in the table
      
      //test for this atom
      double NperVol = 0; //Atoms per nm^3
      
      if (surrounding == false) {
        molWeightFraction = (totalAtomsEM(e) * A) / molecularWeight;
        NperVol = totalAtomsEM(e)/(cellVolume /1000); //Atoms per nm^3
      }
      else {
        molWeightFraction = (getCryoOccurrenceEM(e) * A) / molecularWeightSurrounding;
        NperVol = getCryoOccurrenceEM(e)/(cellVolume /1000); //Atoms per nm^3
      }
      
    //  double NperVol2 = molWeightFraction*(AVOGADRO_NUM * (density/1E21))/A; //Atoms per nm^3
      xSectionTotPerElement += elasticElement[counter] * NperVol; //nm^-1
      if (surrounding == false) {
        elasticXSections.put(e, elasticElement[counter] * NperVol);
      }
      else {
        elasticXSectionsSurrounding.put(e, elasticElement[counter] * NperVol);
      }
      
      
      //Do ELSEPA
      if ((electronEnergy <= 300) && (electronEnergy >= 0.05)) {
        /*
        ReadElasticFile rdEl = new ReadElasticFile();
        double x_section = rdEl.openFile("constants/electron_elastic.txt", electronEnergy, e.getAtomicNumber()); 
        if (x_section > 0.0) {
          elasticElement[counter] = x_section;
        }
        */
        double x_section = e.getElasticCoefficient(electronEnergy);
        if (x_section > 0.0) {
          elasticElement[counter] = x_section;
        }
      }
      
      //needs to just read the ELSEPA file once and store in an array or summin like that - CASINO interpolates
      //This is now incorporated in element database 

   //   double numEl = totalAtomsEM(e);
  //   elasticElement[counter] *= numEl * atomicFraction;
      elasticMolecule += elasticElement[counter];
      partLambda += (molWeightFraction * elasticElement[counter])/A;
   //   partLambda += elasticElement[counter]/A;
      //other part Lambda
   //   numerator += (molWeightFraction * A / density);
   //   denominator += atomicFraction * elasticElement[counter];
      
      counter += 1;

    }
    // not summing the elastic cross section in the same way as Drouin et al 2007 CASINO 2.42 to get 
    // lambda so need to have a look at this by comparisons to CASINOv3
   // molecularWeight = molWeight;
    
    //testing the other way
  //  partLambda = numerator/denominator;
    double lambda = 1/ xSectionTotPerElement;
    
    if (surrounding == false) {
      ElasticxSectionTotPerElement = xSectionTotPerElement;
    }
    else {
      ElasticxSectionTotPerElementSurrounding = xSectionTotPerElement;
    }
    
    return partLambda; //nm^2
  }
  
  @Override
  public double getElectronElasticMFPL(double electronEnergy, boolean surrounding) {
    double partLambda = getElectronElasticCrossSection(electronEnergy, surrounding);
    double densityCalc = 0;
    if (surrounding == false) {
      densityCalc = density;
    }
    else {
      densityCalc = cryoDensity;
    }
 //   double lambda = molecularWeight / ((density/1E21) * AVOGADRO_NUM * elasticXSection);
    double lambda = 1 / (AVOGADRO_NUM * (densityCalc/1E21) * partLambda);
    //other way
 //   double lambda = (partLambda * 1E21) / AVOGADRO_NUM;
    //test
   // lambda = 515;
    return lambda; //nm
  }
  
  @Override
  public Map<ElementEM, Double> getElasticProbs(boolean surrounding){
    Map<ElementEM, Double> elasticProbs = new HashMap<ElementEM, Double>();
    double runningSumFraction = 0;  //should equal 1 by the end
    if (surrounding == false) {
      for (ElementEM e : elasticXSections.keySet()) {
        double lambdaFraction = elasticXSections.get(e)/ElasticxSectionTotPerElement;
        runningSumFraction += lambdaFraction;
        elasticProbs.put(e, runningSumFraction);
      }
    }
    else {
      for (ElementEM e : elasticXSectionsSurrounding.keySet()) {
        double lambdaFraction = elasticXSectionsSurrounding.get(e)/ElasticxSectionTotPerElementSurrounding;
        runningSumFraction += lambdaFraction;
        elasticProbs.put(e, runningSumFraction);
      }
    }
    return elasticProbs;
  }
  
  @Override
  public  double getElectronInelastic(double avgEnergy, double exposedVolume) {
    //Individual atom cross sections
    double[] inelasticElement = new double[presentElements.size()];
    double inelasticMolecule = 0;
    double m = 9.10938356E-31; // in Kg
    double csquared = 3E8*3E8;  // (m/s)^2
    double Vo = avgEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double weirdLetter = (0.02*Beam.KEVTOJOULES)/(betaSquared*(Vo + m*csquared)); //assuming 0.02 keV per inellastic plasmon event  
    double molWeight = 0;
    double inelasticAll = 0;

    int counter = 0;
    for (Element e : this.presentElements) { 
      if (e.getAtomicNumber() == 1) { //Correct as formula doesn't work for hydrogen
        inelasticElement[counter] = 6.4E-5;
      }
      else {
      inelasticElement[counter] =  ((1.5E-6 * Math.pow(e.getAtomicNumber(), 0.5))/betaSquared)*
                       (Math.log(2/weirdLetter));  //nm^2/atom
                     //  1E06; // 
      }
      double numEl = totalAtoms(e);     
      inelasticMolecule += inelasticElement[counter] * numEl;
      molWeight += numEl * e.getAtomicWeight();
      counter += 1;
    }
    
    double massScatteringCoefficient = inelasticMolecule / molWeight;
    double PoverT = 602 * massScatteringCoefficient  * density;        //* (EMThickness/10);
    
    return PoverT;
  }
  
  @Override
  public double getElectronInelasticMFPL(double electronEnergy, boolean surrounding) {
    //Individual atom cross sections
    Set<Element> elementList = presentElements;
    if (surrounding == true) {
      elementList = cryoElements;
    }
    
    double[] inelasticElement = new double[elementList.size()];
    double inelasticMolecule = 0;
    double m = 9.10938356E-31; // in Kg
    double csquared = 3E8*3E8;  // (m/s)^2
    double Vo = electronEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double weirdLetter = (0.02*Beam.KEVTOJOULES)/(betaSquared*(Vo + m*csquared)); //assuming 0.02 keV per inellastic plasmon event  
    double molWeight = 0;
    double inelasticAll = 0;

    int counter = 0;

    
    for (Element e : elementList) { 
      double totalAtoms = totalAtoms(e);
      if (surrounding == true) {
        totalAtoms = getCryoOccurrence(e);
      }
      if (e.getAtomicNumber() == 1) { //Correct as formula doesn't work for hydrogen
        inelasticElement[counter] = 6.4E-5;
      }
      else {
      inelasticElement[counter] =  ((1.5E-6 * Math.pow(e.getAtomicNumber(), 0.5))/betaSquared)*
                       (Math.log(2/weirdLetter));  //nm^2/atom
                     //  1E06; // 
      }
  //    System.out.println(e.getAtomicNumber() + " " + inelasticElement[counter]);
      double NperVol = totalAtoms/(cellVolume /1000);  //atoms/nm^3
      inelasticAll += NperVol * inelasticElement[counter];
      counter +=1;
    }
    double lambda = 1/inelasticAll;
    return lambda;
  }
  
  /**
   * Return the stopping power of the material in keV/nm
   */
  @Override
  public double getStoppingPower(double avgEnergy, boolean surrounding) {
    double stoppingPower = 0;
    if (surrounding == false) {
      stoppingPower = calcStoppingPower(avgEnergy, presentElements, density, surrounding);
    }
    else {
      stoppingPower = calcStoppingPower(avgEnergy, cryoElements, cryoDensity, surrounding);
    }

//    stoppingPower = stoppingPower * 1000 * density /1E7;
    //test
  //  stoppingPower = 2.07E-04;
    /*
    meanJ = 0.0904;
    
    stoppingPower = (78500 * sumZ * density/(beam.getPhotonEnergy()* sumA)) 
        * Math.log(1.166 * beam.getPhotonEnergy()/(meanJ))
        /1E7;  // keV/nm
    */
    //lets try again
      /*
    double m = 9.10938356E-31; // in Kg
    double csquared = 3E8*3E8;  // (m/s)^2
    double Vo = beam.getPhotonEnergy() * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    */
   
    //note no density effect corrections, shell corrections or Bremstrahlung (can justify why)
    
 /*   
 //   double K = 0.31;
 //   double gamma = Math.pow(1/(1-betaSquared), 0.5);
    meanJ = 0.0773;
    double test = sumZ/sumA;
 //   meanJ = meanJ/1000;
    meanJ = meanJ * Beam.KEVTOJOULES;
    meanZoverA = 0.56;
    stoppingPower = K * (meanZoverA)* (1/betaSquared) * 
                    (Math.log((csquared*betaSquared*m*Math.pow(gamma-1, 0.5))/(meanJ * Math.pow(2, 0.5)))+ 
                        0.5*(1-betaSquared)-((2*gamma-1)/(2*Math.pow(gamma, 2))) + (1/16)*Math.pow((gamma-1)/gamma, 2));
    stoppingPower = stoppingPower * 1000 * density /1E7;
    
    //without corrections
    meanJ = 0.0753 * Beam.KEVTOJOULES;
    stoppingPower = K * (meanZoverA)* (1/betaSquared) * 
        (Math.log((csquared*betaSquared*m*gamma)/(meanJ * 2))-betaSquared);
stoppingPower = stoppingPower * 1000 * density /1E7;
    
//    stoppingPower = 2.107602E-04;
 
 */
    return stoppingPower;  //keV/nm
  }
  
  private double calcStoppingPower(double avgEnergy, Set<Element> elements, double passedDensity, boolean surrounding) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = avgEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double K = 0.31;
    double gamma = 1/Math.pow((1-betaSquared), 0.5);
    double KE = (gamma - 1) * m * csquared;
    
    double meanJ = 0, meanlnI = 0;
    double stoppingPower = 0;
    double sumA = 0, meanZoverA = 0;
    int sumZ = 0;
    for (Element e : elements) { 
      //calculate meanJ (mean excitation energy) for this material
      //molWeight fraction
      double A = e.getAtomicWeight();
      double molWeightFraction = 0;
      int Z = e.getAtomicNumber();
      if (surrounding == false) {
        molWeightFraction = (totalAtoms(e) * A) / molecularWeight;
        sumZ += Z * totalAtoms(e);
        sumA += A * totalAtoms(e);
      }
      else {
        molWeightFraction = (getCryoOccurrence(e) * A) / molecularWeightSurrounding;
        sumZ += Z * getCryoOccurrence(e);
        sumA += A * getCryoOccurrence(e);
      }
      fractionElementEM.put(ElementNameLower(e.getElementName()), molWeightFraction);
      double J = 0, Jstar = 0, k = 0;
 //     double energy = beam.getPhotonEnergy();
      meanZoverA += molWeightFraction * (Z/A);

      /*
      if (Z <= 12) {
        J = Z * 11.5;    //eV
      }
      else {
        J = 9.76 * Z + (58.5/Math.pow(Z, 0.19));  //eV
      }
      */
      J = e.getI();
      if ((Z != 1) && (Z != 6) && (Z != 7) && (Z != 8) && (Z!= 9) && (Z != 17)) { //already modified in table
        J *= 1.13; //modified from gas to liquid/solid phase
      }
      k = 0.7344 * Math.pow(Z, 0.0367);
      Jstar = J / (1+ k*(J/(avgEnergy*1000)));
      meanJ += (Jstar * molWeightFraction);  //eV
     
      
      meanlnI +=  molWeightFraction * (Z/A) * Math.log(Jstar);
      
      
      stoppingPower += molWeightFraction * ((78500 * Z /(avgEnergy*A)) 
          * Math.log(1.166 * avgEnergy/(J/1000)));  // keV/nm

      
    }
      stoppingPower *= passedDensity /1E7;

      /*
      //Big point - the adjustment factor should be done only once and then stored as it is not dependetn on energy
      //chnage this once it is in
      //get density effect modification
    //  double h = 6.626070040E-34;
      double plasmaFrequency = 28.816 * Math.pow(density*(Z/A), 0.5); //equals (h/2pi)*omegap
    //  Get number of shells and number of electrons in each shell - when looping through shells
      int[] electronsPerShell = {2, 8, 18, 32, 50};
      double minMu = 10;
      double minDifference = 9E20;
      double nMax = 0;
      for(double mu = 1.0; mu <= 3.0; mu += 0.05) {
        int runningElectronSum = 0;
        boolean exitLoop = false;
        double lnISum = 0;
        for (int i = 1; i <= 5; i++) { // for every shell
          int electronsInShell = 0;
          double fn = 0;
          runningElectronSum += electronsPerShell[i-1];
          if (Z > runningElectronSum) { //if there are electrons in a higher shell
            electronsInShell = electronsPerShell[i-1];
          }
          else {
            electronsInShell = Z - (runningElectronSum - electronsPerShell[i-1]);
            exitLoop = true;
            nMax = i;  //need to sort out what happens when there is a full shell
            
          }
          fn = electronsInShell / Z;
          lnISum += fn * Math.log(Math.pow(Math.pow(mu, 2) * Math.pow(i, 2) + fn * Math.pow(plasmaFrequency, 2), 0.5));
          if (exitLoop == true) {
            break;
          }
        }
        double lnI = Math.log(J);
        double difference = Math.pow(lnI-lnISum, 2);
        if (difference < minDifference) {
          minDifference = difference;
          minMu = mu;
        }
      }
      double delta = 0;
      for (int j = 1; j <= nMax; j++) {
        //redo electrons in shell stuff
        double EnL = Math.pow(Math.pow(minMu, 2)*Math.pow(j, 2) + fn * Math.pow(plasmaFrequency, 2), 0.5, 0.5);
        
        delta += 
      }
      */
      
      
      //density should be of every element...
      //or use mean density, mean Z/A and mean J
      //Z might need to be canged to Zeff
   
   meanlnI = meanlnI/(sumZ/sumA); 
      double meanI = Math.exp(meanlnI);  //This is now right!!!
    double Fbeta = 0;
    meanJ = meanI; //eV  
    //modify meanJ for lower energy by Joy and Luo method - essential for FSEs
   // double k = 0.85; //test
   // meanJ = meanJ / (1 + 0.85*meanJ/(avgEnergy*1000));
    
    meanJ = (meanJ/1000) * Beam.KEVTOJOULES;
    double energy = avgEnergy * Beam.KEVTOJOULES;
    Fbeta = Math.log((m*csquared*(energy)* betaSquared) / (2*(1-betaSquared))) 
            - (2*Math.pow((1-betaSquared),0.5) - 1 + betaSquared)
            * Math.log(2) + 1 - betaSquared + (1/8)*(1-Math.pow(1-betaSquared,0.5));
    stoppingPower = (0.153536/betaSquared)*(sumZ/sumA)*(Fbeta - 2*Math.log(meanJ));
    stoppingPower = stoppingPower * 1000 * passedDensity /1E7;
    
    return stoppingPower;
  }
  
  @Override
  public double getEta() {
    double eta = 0;
    for (Element e : this.presentElements) { 
      double A = e.getAtomicWeight();
      double molWeightFraction = (totalAtoms(e) * A) / molecularWeight;
      int Z = e.getAtomicNumber();
      //backscattering coefficient
      eta += molWeightFraction* (0.025 + 0.016*Z - 1.86E-4 * Math.pow(Z, 2) + 8.3E-7*Math.pow(Z, 3));
      
    }
    return eta;
  }
  
  private String ElementNameLower(String elementName) { //Elements currently in caps, ESTAR needs the second letter lower case
    String lowerCaseName = null;
    if (elementName.length() == 1) {
      lowerCaseName = elementName;
    }
    else {
      char firstLetter = elementName.charAt(0);
      char secondLetter = elementName.charAt(1);
      secondLetter = Character.toLowerCase(secondLetter);
      lowerCaseName = Character.toString(firstLetter) + Character.toString(secondLetter);
    }
    return lowerCaseName;
  }
  
  @Override
  public Map<String, Double> getFractionElementEM(){
    return fractionElementEM;
  }

  @Override
  public double getRutherfordScreening(double electronEnergy) { //this isn't weighted right, do for each element instead
    double alpha = 0;
    for (Element e : this.presentElements) { 
      double A = e.getAtomicWeight();
      double molWeightFraction = (totalAtoms(e) * A) / molecularWeight;
      alpha += molWeightFraction * (3.4E-3 * (Math.pow(e.getAtomicNumber(), 0.67)/electronEnergy));
    }
    return alpha;
  }
  
  @Override
  public double getFSELambda(double FSExSection, boolean surrounding) {  //FSExSection is in cm^2/electron
    double lambda = 0;
    double numEl = 0; //num electrons in the unit cell  
    if (surrounding == false) {
      for (Element e : this.presentElements) { 
        numEl += e.getAtomicNumber() * totalAtoms(e);
      }
    }
    else {
      for (Element e : cryoElements) { 
        numEl += e.getAtomicNumber() * getCryoOccurrence(e);
      }
    }
    double elPerVolume = numEl/(cellVolume/1E24); //electrons/cm^3
    double xSection = elPerVolume * FSExSection;  //cm^-1
    lambda = 1/xSection;
    /*
    for (Element e : this.presentElements) { 
      double A = e.getAtomicWeight();
      double molWeightFraction = (totalAtoms(e) * A) / molecularWeight;
      lambda += molWeightFraction*(A / (e.getAtomicNumber() * AVOGADRO_NUM * (density/1E21) * FSExSection));
    }
    */
    lambda *= 1E7;  //convert cm to nm
  
    
    return lambda;
  }
  

  
  @Override
  public double betheIonisationxSection(double electronEnergy, boolean surrounding) {  //still need to do L and M edges as well
    //need to sort this out for when energy less than shell binding energy
    double a0 = 5.2917721067E-2; //bohr radius in nm
    double m = 9.10938356E-31; // in Kg
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = electronEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
//    double betaSquared2 = (Vo * (Vo + 2*m*csquared))/Math.pow(Vo + m*csquared, 2);
    double vsquared = betaSquared * csquared;
    double elXSection = 0, partLambda = 0;
    double bi = 0, ci = 0;
    boolean calculate = true;
    betheXSections= new HashMap<Element, Double>();
    shellXSections= new HashMap<Element, double[]>();
    BethexSectionTotPerElement = 0;
    shellXSectionsSurrounding= new HashMap<Element, double[]>();
    BethexSectionTotPerElementSurrounding = 0;
  //  double xSectionTotPerElement = 0;
    double molWeight = molecularWeight;
    Set<Element> elementList = presentElements;
    double thisDensity = density;
    if (surrounding == true) {
      molWeight = molecularWeightSurrounding;
      elementList = cryoElements;
      thisDensity = cryoDensity;
    }
    
    for (Element e : elementList) {
      double totAtoms = totalAtoms(e);
      if (surrounding == true) {
        totAtoms = getCryoOccurrence(e);
      }
      //look at doing this using the table
      //get number of shells with cross sections
      int Z = e.getAtomicNumber();
      int numShells = getNumberOfShells(Z);
  //    double[] shellSigma = new double[numShells];
  //    double[] shellSigma = new double[4];
      double[] shellSigma = new double[9];
      elXSection = 0;
      
      double A = e.getAtomicWeight();
      double molWeightFraction = (totAtoms * A) / molWeight;
      double NperVol = totAtoms/(cellVolume /1000); //Atoms per nm^3
      
      //keep to just L shells
      /*
      if (numShells > 4) {
        numShells = 4;    //for now only going to consider ionisation of K and L shells
      }
      */
      for (int i = 0; i < numShells; i++) { //for every shell in this element
        //work out overvoltage
        double bindingEnergy = lowOvervoltages[Z][i][0]/1000; //binding energy in keV
        double U = electronEnergy / bindingEnergy;
        if (U < 16) {
          //low overVoltage calculation
          shellSigma[i] = 4*Math.PI*Math.pow(a0, 2)*((U-1)/Math.pow(U, 2)) *
                         Math.pow(lowOvervoltages[Z][i][1] + U*lowOvervoltages[Z][i][2] + 
                          lowOvervoltages[Z][i][3]/(1+U) + lowOvervoltages[Z][i][4]/Math.pow(1+U, 3) + 
                          lowOvervoltages[Z][i][5]/Math.pow(1+U, 5), 2) ; //nm^2
        }
        else {
          //high overVoltage calculation
          double X = Math.pow(Vo * (Vo + 2*m*csquared), 0.5)/(m*csquared);
          double sigmaPWBA = 4*Math.PI*Math.pow(a0, 2)*(highOvervoltages[Z][i][2]/betaSquared) * 
                             ((Math.log(Math.pow(X, 2)) - betaSquared)*(1+(1/X)*highOvervoltages[Z][i][3]) +
                             highOvervoltages[Z][i][4] + highOvervoltages[Z][i][5]*Math.pow(1-betaSquared, 0.25) + 
                             highOvervoltages[Z][i][6]*(1/X) );
          shellSigma[i] = sigmaPWBA * (U/(U+highOvervoltages[Z][i][0]));
        }
        elXSection += shellSigma[i]; 
        shellSigma[i] *= NperVol; //converts to total cross section of elemental shell per nm
        
        /*
        //Remove hydrogen from the equation for now
        if (Z == 1) {
          elXSection = 0;
          shellSigma[i] = 0;
        }
        */
        
      }
      
      
      
      
      
      /*
      
      int numEl = 2;  //true for K shells
      double A = e.getAtomicWeight();
      double molWeightFraction = (totalAtoms(e) * A) / molecularWeight;
      double shellBindingEnergy = e.getKEdge();   //ofc I need to look this up properly
      
      if (shellBindingEnergy > electronEnergy) {
        calculate = false;
      }
      
      if ((e.getEminLow() == 0) || (e.getEminLow() == null)) { //if there is no entry
        calculate = false;
        elXSection = 0;      //just set the x section of that element to 0 for now, will add in later if it works
      }
      else {
        calculate = true;
        if (electronEnergy < e.getEmaxLow()) {   //I know these don't fit properly but sort our later if I get the chance
          bi = e.getbKLow();
          ci = e.getcKLow();
        }
        else {
          bi = e.getbKHigh();
          ci = e.getcKHigh();
        }
      }
      //this didn't work so using the one from Joy 1995 with relativistic correction

      if (calculate == true){
        //relativistic
        elXSection = ((2*Math.PI * Math.pow(elementaryCharge, 4) * numEl * bi) / (((m*1000)*(vsquared*10000)) * (shellBindingEnergy*Beam.KEVTOJOULES*1000*10000)))
            * ((Math.log(betaSquared/(1-betaSquared)) - betaSquared) + Math.log(ci*m*csquared/(2*shellBindingEnergy*Beam.KEVTOJOULES)));
        //non-relativistic
    //    elXSection = ((Math.PI * Math.pow(elementaryCharge, 4) * numEl * bi) / ((electronEnergy*Beam.KEVTOJOULES * 1000*10000) * (shellBindingEnergy*Beam.KEVTOJOULES*1000*10000)))
    //        * (Math.log(ci*electronEnergy/(shellBindingEnergy)));
        
        
        //Tests
    //    elXSection = ((2*Math.PI * Math.pow(elementaryCharge, 4) * numEl * bi) / ((2*electronEnergy*Beam.KEVTOJOULES * 1000*10000) * (shellBindingEnergy*Beam.KEVTOJOULES*1000*10000)))
    //        * ((Math.log(betaSquared/(1-betaSquared)) - betaSquared) + Math.log(ci*m*csquared/(2*shellBindingEnergy*Beam.KEVTOJOULES)));
        
     //   elXSection = (6.51E-20)*((numEl*bi)/(electronEnergy*shellBindingEnergy))
     //                 * ((Math.log(betaSquared/(1-betaSquared)) - betaSquared) + Math.log(ci*m*csquared/(2*e.getKEdge()*Beam.KEVTOJOULES)));
        //cm^2
        elXSection = elXSection * 1E14; //convert to nm^2
      //  betheXSections.put(e, elXSection);   //nm^2/atom 
        //Will need to combine this in the same why I did the elastic stuff
        partLambda += (molWeightFraction * elXSection)/A;
        
        double NperVol = totalAtoms(e)/(cellVolume /1000); //Atoms per nm^3
        double NperVol2 = molWeightFraction*(AVOGADRO_NUM * (density/1E21))/A; //Atoms per nm^3
        BethexSectionTotPerElement += elXSection * NperVol; //nm^-1
        betheXSections.put(e, elXSection * NperVol);
        
      }
      */
      
  //    System.out.println(Z + " " + elXSection);
      partLambda += (molWeightFraction * elXSection)/A;
      if (surrounding == false) {
        BethexSectionTotPerElement += elXSection * NperVol; //nm^-1
        shellXSections.put(e, shellSigma); //nm^2
      }
      else {
        BethexSectionTotPerElementSurrounding += elXSection * NperVol; //nm^-1
        shellXSectionsSurrounding.put(e, shellSigma); //nm^2
      }
    }
    double lambda = 0;
    
    if (partLambda > 0) {
      lambda = 1 / (AVOGADRO_NUM * (thisDensity/1E21) * partLambda);
    }
    if (surrounding == false) {
      if (BethexSectionTotPerElement > 0) {
        lambda = 1/BethexSectionTotPerElement;  //should both give the same result 
      }
    }
    else {
      if (BethexSectionTotPerElementSurrounding > 0) {
        lambda = 1/BethexSectionTotPerElementSurrounding;  //should both give the same result 
      }
    }
    return lambda; //nm
  }
  /*
  @Override
  public Map<Element, Double> getInnerShellProbs(){
    Map<Element, Double> ionisationProbs = new HashMap<Element, Double>();
    
//    double totalLambda = 0;
//    for (Element e : betheXSections.keySet()) {
//      double A = e.getAtomicWeight();
//      double molWeightFraction = (totalAtoms(e) * A) / molecularWeight;
//      totalLambda += molWeightFraction * (betheXSections.get(e) / A);
//    }
    
    double runningSumFraction = 0;  //should equal 1 by the end
    for (Element e : betheXSections.keySet()) {
      
   //   double A = e.getAtomicWeight();
   //   double molWeightFraction = (totalAtoms(e) * A) / molecularWeight;
      
    //  double lambdaFraction = (molWeightFraction * (betheXSections.get(e) / A))/totalLambda;
      double lambdaFraction = betheXSections.get(e)/BethexSectionTotPerElement;
      runningSumFraction += lambdaFraction;
      ionisationProbs.put(e, runningSumFraction);
    }
    return ionisationProbs;
  }
  */
  
  @Override
  public Map<Element, double[]> getAllShellProbs(boolean surrounding){
    Map<Element, double[]> ionisationProbs = new HashMap<Element, double[]>();
    double runningSumFraction = 0;  //should equal 1 by the end
    if (surrounding == false) {
      for (Element e : shellXSections.keySet()) {
        double[] elementShells = shellXSections.get(e);
   //     double[] shellProbs = new double[4];
        double[] shellProbs = new double[9];
     //   for (int i = 0; i < elementShells.length; i++) {]
     //   for (int i = 0; i < 4; i++) {
        for (int i = 0; i < 9; i++) {
          double lambdaFraction = elementShells[i]/BethexSectionTotPerElement;
          runningSumFraction += lambdaFraction;
          shellProbs[i] = runningSumFraction;
        }
        ionisationProbs.put(e, shellProbs);
      }
    }
    else {
      for (Element e : shellXSectionsSurrounding.keySet()) {
        double[] elementShells = shellXSectionsSurrounding.get(e);
   //     double[] shellProbs = new double[4];
        double[] shellProbs = new double[9];
     //   for (int i = 0; i < elementShells.length; i++) {]
     //   for (int i = 0; i < 4; i++) {
        for (int i = 0; i < 9; i++) {
          double lambdaFraction = elementShells[i]/BethexSectionTotPerElementSurrounding;
          runningSumFraction += lambdaFraction;
          shellProbs[i] = runningSumFraction;
        }
        ionisationProbs.put(e, shellProbs);
      }
    }
    
    return ionisationProbs;
  }
  
  
  @Override
  public double getEMFlAbsCoef(double flEnergy) {
    double escapeMuAbsK = 0;
    Map<String, Double> absCoefficients = calculateCoefficientsAll(flEnergy);
    escapeMuAbsK = absCoefficients.get(PHOTOELECTRIC);
    return escapeMuAbsK;
  }
  
  @Override
  public void calculateSterheimerFactor() {
    
  }
  
  @Override
  public double getZav() {
    double Zav = 0;
    double atomTot = 0, Ztot = 0;
    for (Element e : presentElements) {
      atomTot += totalAtoms(e);
      Ztot += totalAtoms(e) * e.getAtomicNumber();
    }
    Zav = Ztot/atomTot;
    return Zav;
  }
  
  @Override
  public void populateCrossSectionCoefficients(){
    try {
      populateLowOvervoltageCoefficeints();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      populateHighOvervoltageCoefficeints();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  private void populateLowOvervoltageCoefficeints() throws IOException{
    lowOvervoltages = new double[95][9][6];
    InputStreamReader isr = locateConstantsFile("constants/low_overvoltage.csv");
    BufferedReader br = new BufferedReader(isr);
    String line;
    String[] components;
    
    while ((line = br.readLine()) != null) {
      components = line.split(",");
      int atomicNumber = Integer.parseInt(components[0]);
      int numberIterations = (int)(components.length - 1) / 7;
      
      for (int i = 0; i < numberIterations; i++) {
        /*
        int shell = i;
        double bindingEnergy = Double.valueOf(components[(i*7)+2]);
        double a1 =  Double.valueOf(components[(i*7)+3]);
        double a2 = Double.valueOf(components[(i*7)+4]);
        double a3 =  Double.valueOf(components[(i*7)+5]);
        double a4 = Double.valueOf(components[(i*7)+6]);
        double a5 = Double.valueOf(components[(i*7)+7]);
        */
        for (int j = 0; j < 6; j++) {
          lowOvervoltages[atomicNumber][i][j] = Double.valueOf(components[(i*7)+j+2]);
        }
      }
    }
    br.close();
    isr.close();
  }
  
  private void populateHighOvervoltageCoefficeints() throws IOException{
    highOvervoltages = new double[95][9][7];
    InputStreamReader isr = locateConstantsFile("constants/high_overvoltage.csv");
    BufferedReader br = new BufferedReader(isr);
    String line;
    String[] components;
    
    while ((line = br.readLine()) != null) {
      components = line.split(",");
      int atomicNumber = Integer.parseInt(components[0]);
      int numberIterations = (int)(components.length - 1) / 8;
      
      for (int i = 0; i < numberIterations; i++) {
        /*
        int shell = i;
        double b- =  Double.valueOf(components[(i*8)+2]);
        double b+ =  Double.valueOf(components[(i*8)+3]);
        double A = Double.valueOf(components[(i*8)+4]);
        double g1 =  Double.valueOf(components[(i*8)+5]);
        double g2 = Double.valueOf(components[(i*8)+6]);
        double g3 = Double.valueOf(components[(i*8)+7]);
        double g4 = Double.valueOf(components[(i*8)+8]);
        */
        for (int j = 0; j < 7; j++) {
          highOvervoltages[atomicNumber][i][j] = Double.valueOf(components[(i*8)+j+2]);
        }
      }
    }
    br.close();
    isr.close();
  }
  
  private InputStreamReader locateConstantsFile(String fileName)
      throws UnsupportedEncodingException, FileNotFoundException {
    // Try to find it within class path;
    InputStream is = getClass().getResourceAsStream("/" + fileName);

    if (is == null) {
      // If it is not within the class path, try via the file system.
      is = new FileInputStream(fileName);
    }

    return new InputStreamReader(is, "US-ASCII");
  }
  
  private int getNumberOfShells(int Z) {
    int numShells = 0;
    if (Z <= 10) {
      numShells = 1;
    }
    else if (Z == 11) {
      numShells = 2;
    }
    else if (Z >= 12 && Z <= 19) {
      numShells = 4;
    }
    else if (Z >= 20 && Z <= 22) {
      numShells = 5;
    }
    else if (Z == 23) {
      numShells = 6;
    }
    else if (Z >= 24 && Z <= 32) {
      numShells = 7;
    }
    else {
      numShells = 9;
    }
    return numShells;
  }
  
  @Override
  public double getPlasmaMFPL(double electronEnergy) {  //non-relativistic at the moment
    double lambda = 0;
    double m = 9.10938356E-31;
    double h = 6.626070040E-34; //m^2 Kg/s
    double hbar = h/(2*Math.PI);
    double a0 = 5.291772106E-2; //nm
    int sumZ = 0;  //sumA = molecularWeight
    for (Element e : this.presentElements) {
      sumZ += e.getAtomicNumber() * totalAtoms(e);
    }
    double plasmaFrequency = getPlasmaFrequency();
    double fermiEnergy = 0.294 * Math.pow(plasmaFrequency, 4/3); //eV
    double kf = Math.pow(((fermiEnergy/1000)*Beam.KEVTOJOULES)*2*m/Math.pow(hbar, 2), 0.5);  //m^-1
    double kMinus = Math.pow(2*m, 0.5) *
                    (Math.pow(electronEnergy*Beam.KEVTOJOULES, 0.5)- Math.pow((electronEnergy - plasmaFrequency/1000)*Beam.KEVTOJOULES, 0.5));
                    //m Kg s^-1
    double kPlusOne = Math.pow(2*m, 0.5) *
                      (Math.pow(electronEnergy*Beam.KEVTOJOULES, 0.5) + Math.pow((electronEnergy-plasmaFrequency/1000)*Beam.KEVTOJOULES, 0.5));
                    //m Kg s^-1
    double kPlusTwo = Math.pow(2*m, 0.5) * Math.pow(((fermiEnergy+plasmaFrequency)/1000)*Beam.KEVTOJOULES, 0.5) - (hbar*kf); //m Kg s^-1
    double kPlus = Math.min(kPlusOne, kPlusTwo); // / hbar; //m^-1
    lambda = 1/((plasmaFrequency / (2*a0*(electronEnergy*1000)))*Math.log(kPlus/kMinus));
    
    return lambda;
  }
  
  @Override
  public double getPlasmaFrequency() {
    int sumZ = 0;  //sumA = molecularWeight
    for (Element e : this.presentElements) {
      sumZ += e.getAtomicNumber() * totalAtoms(e);
    }
    double plasmaFrequency = 28.816 * Math.pow(density*(sumZ/molecularWeight), 0.5); //equals (h/2pi)*omegap //this is in eV
    return plasmaFrequency; //in eV
  }
  
  public void setNumberSimulatedElectrons(long numSim) {
    numSimulatedElectrons = numSim;
  }
  
  @Override
  public long getNumberSimulatedElectrons() {
    return numSimulatedElectrons;
  }
}
