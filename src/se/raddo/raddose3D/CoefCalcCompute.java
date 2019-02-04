package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
  
  private double solFraction;
  
 
  
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
  
  public double sturnheimerAdjustment;
  public TreeMap<Double, Double> dsimgaOverdW;
  public final int Wbins = 1000;
  public final double fcbCutoff = 0.0;
  public final double Wcc = 0.0;
  public Map<Element, double[][]> GOSinelastic; //double is shell : 0 = longitudinal, 1 - transverse, 2 = close
  public double[] cbInel;
  //surrounding
  public Map<Element, double[][]> GOSinelasticSurrounding; //double is shell : 0 = longitudinal, 1 - transverse, 2 = close
  public double[] cbInelSurrounding;
  
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
    
    if (element.getAtomicNumber() == 16) {
  //    double test = cs.get(CrossSection.PHOTOELECTRIC);
  //    System.out.println(test);
    }
    return absCoeffs;
  }
  
  @Override
  public Map<Element, Double> getPhotoElectricProbsElement(double beamEnergy) {
    Map<Element, Double> elementAbsorptionProbs = new HashMap<Element, Double>(); 
    double runnningSumProb = 0;
    for (Element e : this.presentElements) {
      Map<String, Double> absCoeffs = calculateCoefficientsElement(beamEnergy, e);
      double absorptionProb = absCoeffs.get(PHOTOELECTRIC)/absCoeffphoto;
      runnningSumProb += absorptionProb;
      elementAbsorptionProbs.put(e, runnningSumProb);
    }
    return elementAbsorptionProbs;
  }
  
  @Override
  public Map<Element, Double> getPhotoElectricProbsElementSurrounding(double beamEnergy) {
    Map<Element, Double> elementAbsorptionProbs = new HashMap<Element, Double>(); 
    double runnningSumProb = 0;
    for (Element e : cryoElements) {
      Map<String, Double> absCoeffs = calculateCoefficientsCryoElement(beamEnergy, e);
      double absorptionProb = absCoeffs.get(PHOTOELECTRIC)/cryoAbsCoeffPhoto;
      runnningSumProb += absorptionProb;
      elementAbsorptionProbs.put(e, runnningSumProb);
    }
    return elementAbsorptionProbs;
  }
  
  @Override
  public double getElementAbsorptionCoef(double beamEnergy, Element e) {
    Map<String, Double> absCoeffs = calculateCoefficientsElement(beamEnergy, e);
    return absCoeffs.get(PHOTOELECTRIC);
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
  
  @Override
  public double getCryoInelasticCoefficient() {
    return cryoAbsCoeffComp;
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
    
    solFraction = solventFraction;

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
  
  @Override
  public double getSolventFraction() {
    return solFraction;
  }

  public double totalAtoms(final Element element) {
    return getSolventOccurrence(element) + getMacromolecularOccurrence(element);
  }
  
  @Override
  public double getTotalAtomsInCrystal(double crystalVolume) {
    double totalAtoms = 0;
    for (Element e : this.presentElements) { //get total Atoms per unit cell
      totalAtoms += totalAtoms(e);
    }
    //crystal volume is in cm^3, cell volume is in A^3
    totalAtoms *= (crystalVolume*1E24)/cellVolume;
    return totalAtoms;
  }
  
  @Override
  public  double getTotalAtomsInCrystalElement(double crystalVolume, Element elem) {
    double totalAtoms = totalAtoms(elem);
    totalAtoms *= (crystalVolume*1E24)/cellVolume;
    return totalAtoms;   
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
  //    Z = 7.22;
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
    
      
    //  double NperVol2 = molWeightFraction*(AVOGADRO_NUM * (density/1E21))/A; //Atoms per nm^3
      xSectionTotPerElement += elasticElement[counter] * NperVol; //nm^-1
      if (surrounding == false) {
        elasticXSections.put(e, elasticElement[counter] * NperVol);
      }
      else {
        elasticXSectionsSurrounding.put(e, elasticElement[counter] * NperVol);
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
    
    
  //  double test = getPimblottElastic(electronEnergy);
    
    return partLambda; //nm^2
  }
  
  //I am testing the Pimblott method for the elastic scattering cross section of water
  private double getPimblottElastic(double avgEnergy) {
    double elasticEl = 0;
    double Z = 10;
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double e4 = (Math.pow(elementaryCharge, 4) / 1E6) / (1E12);
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = avgEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double gamma = 1 / Math.pow(1-betaSquared, 0.5);
    double vSquared = betaSquared*csquared;
    double p = gamma * m * Math.pow(vSquared, 0.5);  
    
    double T = (gamma-1);                                     //kinetic energy in units mc^2
    double nc = 1.64 - 0.0825 * Math.log(T*m*csquared);
    nc = 1.13 + 3.76*Math.pow(Z/137*Math.pow(betaSquared, 0.5), 2);
    double n = (Math.pow(Z,2/3)*nc)/(T*(T+2));
    
    //do the thing
    int bins = 100;
    int count = 0;
    double step = Math.PI / bins;
    double theta = 0;
    double thisY = 0, previousY = 0, sum = 0;
    while (theta <= Math.PI) {
      double sigma = ((Math.pow(Z, 2) + Z)* e4) / (Math.pow(p, 2) * vSquared * Math.pow(1-Math.cos(theta)+2*n, 2));
      thisY = sigma * Math.sin(theta);
      if (count > 0) {
        //then integrate this trapezium
        sum += step * ((thisY + previousY)/2);
      }
      count += 1;
      previousY = thisY;
      theta += step;
    }
    sum *= 2*Math.PI; //m^2
    sum = sum * 1E18; //nm^2
    return sum;
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
    double weirdLetter = (0.02*Beam.KEVTOJOULES)/(betaSquared*(Vo + m*csquared)); //assuming 0.02 keV per inelastic plasmon event  
    //this is a big assumption no??? 
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
    /*
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
    double weirdLetter = (0.025*Beam.KEVTOJOULES)/(betaSquared*(Vo + m*csquared)); //assuming 0.02 keV per inellasticevent  
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
    
    double test = reimerElectronInelastic(surrounding);
    return lambda;
  }
  
  //Reimer method for the inelastic cross section
  public double reimerElectronInelastic(boolean surrounding) {
    double inelasticMFPL = 0;
    double inelastic_x_section = 0;
    Set<ElementEM> elementList = presentElementsEM;
    if (surrounding == true) {
      elementList = cryoElementsEM;
    }
    for (ElementEM e : elementList) { 
      int Z = e.getAtomicNumber();
      double v = 20/Z;
      double element_inel = v * elasticXSections.get(e);
      inelastic_x_section += element_inel;
      
    }
    inelasticMFPL = 1/inelastic_x_section;
    return inelasticMFPL;
    */
    
    
    //Reimer way
    double inelasticMFPL = 0;
    double inelastic_x_section = 0;
    Set<ElementEM> elementList = presentElementsEM;
    if (surrounding == true) {
      elementList = cryoElementsEM;
    }
    for (ElementEM e : elementList) { 
      /*
      double totalAtoms = totalAtoms(e);
      if (surrounding == true) {
        totalAtoms = getCryoOccurrence(e);
      }
      */
      int Z = e.getAtomicNumber();
      double v = 20/Z;
      double element_inel = 0;
      if (surrounding == false) {
        element_inel = v * elasticXSections.get(e);
      }
      else {
        element_inel = elasticXSectionsSurrounding.get(e);
      }
          
      inelastic_x_section += element_inel;
      
    }
    inelasticMFPL = 1/inelastic_x_section;
    return inelasticMFPL;
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
    
    if (avgEnergy < 0.1) {
      double test = 0.0;
    }
    
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
        
        
        //Remove hydrogen from the equation for now
        if (Z == 1) {
          elXSection = 0;
          shellSigma[i] = 0;
        }
        
        
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
    /*
    int sumZ = 0;  //sumA = molecularWeight
    for (Element e : this.presentElements) {
      sumZ += getNumValenceElectrons(e)[0] * totalAtoms(e);
    }
    double plasmaFrequency = 28.816 * Math.pow(density*(sumZ/molecularWeight), 0.5); //equals (h/2pi)*omegap //this is in eV
    return plasmaFrequency; //in eV
    */
    
    double hbarSqaured = Math.pow(6.62607004E-34/(2*Math.PI), 2); // m^4 kg^2  s^-2
    double m = 9.10938356E-31; //kg
    double eSquared = Math.pow(4.80320425E-10,2)/1000; //units = esu = Kg^1 cm^3 s^-2
    double plasmaEnergy = 0, sumZ = 0, sumfcb = 0, NZ = 0;
    for (Element e: this.presentElements) {
      NZ += (e.getAtomicNumber()*totalAtoms(e)) / (cellVolume/1E24) ; //electrons. cm^-3
      sumZ += e.getAtomicNumber() * totalAtoms(e);
      sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
    }
      plasmaEnergy =  Math.pow(4*Math.PI*hbarSqaured*NZ*eSquared/m,0.5); //J;
    
    plasmaEnergy = (plasmaEnergy/Beam.KEVTOJOULES)*1000;
    double Wcb = Math.pow(sumfcb/sumZ, 0.5) * plasmaEnergy;
    return Wcb; //eV
    
  }
  
  public int[] getNumValenceElectrons(Element e) {
    int Z = e.getAtomicNumber();
    int valence = Z;
    int numInnerShells = 0;
    if (Z > 2 && Z <= 10 ) {
      valence -= 2;
      numInnerShells = 1; //just K
    }
    else if (Z > 10 && Z <= 28) {
      valence -= 10;
      numInnerShells = 2;
    }
    else if (Z > 28 && Z <= 60) {
      valence -= 28;
      numInnerShells = 3;
    }
    else if (Z > 60) {
      valence -= 60;
      numInnerShells = 4;
    }
    int[] numElectrons = {valence, numInnerShells};
    return numElectrons;
  }
  public void setNumberSimulatedElectrons(long numSim) {
    numSimulatedElectrons = numSim;
  }
  
  @Override
  public long getNumberSimulatedElectrons() {
    return numSimulatedElectrons;
  }
  
  
  
  
  
  
  @Override
  public void getDifferentialInlasticxSection(double electronEnergy){
 // public void getDifferentialInlasticxSection(double electronEnergy){
  //  double[] test = checkMeanI(electronEnergy);
    
    
    
    double E = electronEnergy * 1000; //in eV
    
    double Wcb = getWcbAll();
    double Wmax = E/2;
    int sumfcb = 0, sumAtoms = 0;
    for (Element e: this.presentElements) {
      sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
      sumAtoms += totalAtoms(e);
    }
    
    int[] shells = {2, 8, 18, 32};
    double total_x_section = 0, totalSP = 0, totalVar = 0, otherX = 0, otherSP = 0, otherVar = 0, integratedSigmai = 0;
    // do the plasma stuff first
    double a = 1.65;
    double b = 1;
    double step = (Wmax - Wcb) / Wbins;
    dsimgaOverdW = new TreeMap<Double, Double>(); //clearing it
    double globalStep = (E/2)/Wbins;
    for (int i = 0; i<= Wbins; i++ ) {
      double energy = globalStep*i;
      dsimgaOverdW.put(energy, 0.0);
    }
    double[] integratedValues = getIntegratedSigmai(Wcb, step, E, sumfcb);
    integratedSigmai = integratedValues[0];
    //multiply it up
    //way 1
    double shell_x_section = ((1/cellVolume)*1E30) * integratedSigmai; // m^-1
    double shellSP = ((1/cellVolume)*1E30) * integratedValues[1]; // J.m^-1
    double shellVar = ((1/cellVolume)*1E30) * integratedValues[2]; // J^2.m^-1
    total_x_section += shell_x_section/1E9; //nm^-1
    totalSP += (shellSP/Beam.KEVTOJOULES)/1E9; //keV/nm
    totalVar += ((shellVar/Math.pow(Beam.ELEMENTARYCHARGE,2))/1E6)/1E9; //keV^2/nm
    //way 2
    double[] integrals = integrateSigma();
    double element_x_section = ((1/cellVolume)*1E30) * integrals[0];
    double elementSP = ((1/cellVolume)*1E30) * integrals[1];
    double elementVar = ((1/cellVolume)*1E30) * integrals[2];
    otherX += element_x_section/1E9;
    otherSP += (elementSP/Beam.KEVTOJOULES)/1E9; //keV/nm
    otherVar += ((elementVar/Math.pow(Beam.ELEMENTARYCHARGE,2))/1E6)/1E9; //keV^2/nm
    

    for (Element e : this.presentElements) {
      dsimgaOverdW = new TreeMap<Double, Double>(); //clearing it
      //populate TreeMap
    //  double globalStep = (E/2)/Wbins;
      globalStep = (E/2)/Wbins;
      for (int i = 0; i<= Wbins; i++ ) {
        double energy = globalStep*i;
        dsimgaOverdW.put(energy, 0.0);
      }
      if (totalAtoms(e) > 0) {
      //get fcb
        int Z = e.getAtomicNumber();
        int[] electrons = getNumValenceElectrons(e);
        int fcb = electrons[0];
    //    sumfcb += electrons[0];
        //get first Wcb estimate with b = 1
        int numInnerShells = electrons[1];
        double[] Wi = new double[numInnerShells];
        if (numInnerShells > 3){
          numInnerShells = 3; //does not work for elements Z above 60 a the moment as no N edge in MuCalc!!!! 
        }
        
        
        double sumfilnU = 0;
        for (int j = 0; j < numInnerShells; j++) { 
          double Ui = getShellBinding(j, e)*1000; //in eV
          sumfilnU += shells[j] * Math.log(Ui);
        }
        double plasmaEnergy = getElementPlasmaFrequency(e);
  //      double a = 0;
  //      double b = 1;
   //     double Wcb = b * plasmaEnergy * Math.pow((double)(fcb)/Z, 0.5);
        if (numInnerShells > 0) {
          if (fcb > 0) {
            double thisStep = 20;
            int sign = 1, previousSign = 1, count = 0;
       //     while (a < 1.5 || a > 3) { 
              Wcb = b * plasmaEnergy * Math.pow((double)(fcb)/Z, 0.5);
              //get Wi
              //get a for the element
              a = getAdjustmentFactor(e, fcb, Wcb, sumfilnU);
              /*
              //adjust b until 1.5 < a < 3 //way I'm doing it I get a high a :/ maybe try for a proper a!!!!!
              if (a > 3) {
                b += thisStep;
                sign = 1;
              }
              else if (a < 1.5) {
                b -= thisStep;
                sign = -1;
              }
              // if it gets stuck in between need to drop the step
              if (count >= 1) { //so not on the first go
                if (sign != previousSign) { //sign has flipped
                  thisStep -= thisStep/2;
                }
              }
              count += 1;
              previousSign = sign;
              */
        //    }
          }
          else {
            a = getAdjustmentFactor(e, fcb, Wcb, sumfilnU);
          }
        }
        
        
        //get Wi for each transition
        for (int i = 0; i < numInnerShells; i++) { 
          Wi[i] = a * getShellBinding(i, e)*1000;
        }
        //return ZlnI and the sum to see if they match
        double[] match = checkWiValues(Wi, numInnerShells, e, fcb, Wcb);
        
        //get dsigma/dW
        double dsigmaiOverdW = 0, previousdsigmaiOverdW = 0;
   //     double integratedSigmai = 0;
        
  //      double Wmax = E/2;
        for (int i = 0; i < numInnerShells; i++) { 
          //need to start at Wi go up to Wmax
   //      double step = (Wmax - Wi[i]) / Wbins;
   //      double[] integratedValues = getIntegratedSigmai(Wi[i], step, E, shells[i]);
          step = (Wmax - Wi[i]) / Wbins;
          integratedValues = getIntegratedSigmai(Wi[i], step, E, shells[i]);
          integratedSigmai = integratedValues[0];
          //now get the inelastic cross section for this shell
   //       double shell_x_section = ((totalAtoms(e)/cellVolume)*1E30) * integratedSigmai; // m^-1
   //       double shellSP = ((totalAtoms(e)/cellVolume)*1E30) * integratedValues[1]; // J.m^-1
   //       double shellVar = ((totalAtoms(e)/cellVolume)*1E30) * integratedValues[2]; // J^2.m^-1
          shell_x_section = ((totalAtoms(e)/cellVolume)*1E30) * integratedSigmai; // m^-1
          shellSP = ((totalAtoms(e)/cellVolume)*1E30) * integratedValues[1]; // J.m^-1
          shellVar = ((totalAtoms(e)/cellVolume)*1E30) * integratedValues[2]; // J^2.m^-1
          total_x_section += shell_x_section/1E9; //nm^-1
          totalSP += (shellSP/Beam.KEVTOJOULES)/1E9; //keV/nm
          totalVar += ((shellVar/Math.pow(Beam.ELEMENTARYCHARGE,2))/1E6)/1E9; //keV^2/nm
        }
        //also need to do Wcb 
        //change to do this for everything
        /*
        double step = (Wmax - Wcb) / Wbins;
        double[] integratedValues = getIntegratedSigmai(Wcb, step, E, fcb);
        integratedSigmai = integratedValues[0];
        double shell_x_section = ((totalAtoms(e)/cellVolume)*1E30) * integratedSigmai; // m^-1
        double shellSP = ((totalAtoms(e)/cellVolume)*1E30) * integratedValues[1]; // J.m^-1
        double shellVar = ((totalAtoms(e)/cellVolume)*1E30) * integratedValues[2]; // J^2.m^-1
        total_x_section += shell_x_section/1E9; //nm^-1
        totalSP += (shellSP/Beam.KEVTOJOULES)/1E9; //keV/nm
        totalVar += ((shellVar/Math.pow(Beam.ELEMENTARYCHARGE,2))/1E6)/1E9; //keV^2/nm
        */
        //try doing it with the sums
     //   double[] integrals = integrateSigma();
     //   double element_x_section = ((totalAtoms(e)/cellVolume)*1E30) * integrals[0];
     //   double elementSP = ((totalAtoms(e)/cellVolume)*1E30) * integrals[1];
    //    double elementVar = ((totalAtoms(e)/cellVolume)*1E30) * integrals[2];
        integrals = integrateSigma();
        element_x_section = ((totalAtoms(e)/cellVolume)*1E30) * integrals[0];
        elementSP = ((totalAtoms(e)/cellVolume)*1E30) * integrals[1];
        elementVar = ((totalAtoms(e)/cellVolume)*1E30) * integrals[2];
        otherX += element_x_section/1E9;
        otherSP += (elementSP/Beam.KEVTOJOULES)/1E9; //keV/nm
        otherVar += ((elementVar/Math.pow(Beam.ELEMENTARYCHARGE,2))/1E6)/1E9; //keV^2/nm
      } 
    }
    
    double lambda = 1/total_x_section;
    double otherLambda = 1/otherX;
 //   System.out.println("test");
    
    
    if (isCryo()) {
      //repeat for surrounding as well
    }
  }
  public double getElementPlasmaFrequency(Element e) {
    //J = Kg m^2 s^-2
    double hbarSqaured = Math.pow(6.62607004E-34/(2*Math.PI), 2); // m^4 kg^2  s^-2
    double NZ = (e.getAtomicNumber()*totalAtoms(e)) / (cellVolume/1E24) ; //electrons. cm^-3
    double eSquared = Math.pow(4.80320425E-10,2)/1000; //units = esu = Kg^1 cm^3 s^-2
    double m = 9.10938356E-31; //kg
    double plasmaEnergy = Math.pow(4*Math.PI*hbarSqaured*NZ*eSquared/m,0.5); //J
    plasmaEnergy = (plasmaEnergy/Beam.KEVTOJOULES)*1000;
    return plasmaEnergy; //eV
  }
  
  @Override
  public double getWcbAll() {
    double hbarSqaured = Math.pow(6.62607004E-34/(2*Math.PI), 2); // m^4 kg^2  s^-2
    double m = 9.10938356E-31; //kg
    double eSquared = Math.pow(4.80320425E-10,2)/1000; //units = esu = Kg^1 cm^3 s^-2
    double plasmaEnergy = 0, sumZ = 0, sumfcb = 0, NZ = 0;
    for (Element e: this.presentElements) {
      NZ += (e.getAtomicNumber()*totalAtoms(e)) / (cellVolume/1E24) ; //electrons. cm^-3
      sumZ += e.getAtomicNumber() * totalAtoms(e);
      if (e.getAtomicNumber() != 8) {
   //     sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
      }
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      for (int i = 0; i <= numInnerShells; i++) {
        double Uk = getShellBinding(i, e)*1000;
        if (Uk < fcbCutoff) {
          sumfcb += electrons[0] * totalAtoms(e);
        }
      }
      
    }
      plasmaEnergy =  Math.pow(4*Math.PI*hbarSqaured*NZ*eSquared/m,0.5); //J;
     // plasmaEnergy = getPlasmaFrequency();
    plasmaEnergy = (plasmaEnergy/Beam.KEVTOJOULES)*1000;
    double Wcb = Math.pow(sumfcb/sumZ, 0.5) * plasmaEnergy;
    return Wcb; //eV
  }
  
  public double getPlasmaEnergyAll() {
    double hbarSqaured = Math.pow(6.62607004E-34/(2*Math.PI), 2); // m^4 kg^2  s^-2
    double m = 9.10938356E-31; //kg
    double eSquared = Math.pow(4.80320425E-10,2)/1000; //units = esu = Kg^1 cm^3 s^-2
    double plasmaEnergy = 0, sumZ = 0, sumfcb = 0, NZ = 0;
    for (Element e: this.presentElements) {
      NZ += (e.getAtomicNumber()*totalAtoms(e)) / (cellVolume/1E24) ; //electrons. cm^-3
      sumZ += e.getAtomicNumber() * totalAtoms(e);
      sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
    }
    plasmaEnergy =  Math.pow(4*Math.PI*hbarSqaured*NZ*eSquared/m,0.5); //J;
    plasmaEnergy = (plasmaEnergy/Beam.KEVTOJOULES)*1000;
    return plasmaEnergy;
  }
  
  public double getShellBinding(int shellIndex, Element e) {
    if (shellIndex == 0) {
      return e.getKEdge();
    }
    else if (shellIndex == 1){
      return e.getL1Edge();
    }
    else if (shellIndex == 2) {
      return e.getM1Edge();
    }
    else {
      return 0;
    }
  }
  public double getAdjustmentFactor(Element e, int fcb, double Wcb, double sumfilnU) {
    int Z = e.getAtomicNumber();
    double I = e.getI();
    if ((Z != 1) && (Z != 6) && (Z != 7) && (Z != 8) && (Z!= 9) && (Z != 17)) { //already modified in table
      I *= 1.13; //modified from gas to liquid/solid phase
    }
    double a = Math.exp((Z*Math.log(I) - fcb*Math.log(Wcb) - sumfilnU)/(Z - fcb));
    return a;    
  }
  
  public double[] checkWiValues(double[] Wi, int numInnerShells, Element e, int fcb, double Wcb) {
    int[] shells = {2, 8, 18, 32};
    //get sum
    double sumfilnWi = 0;
    for (int j = 0; j < numInnerShells; j++) { 
      sumfilnWi += shells[j] * Math.log(Wi[j]);
    }
    //add on the Wcb as well
    if (Wcb > 0) {
      sumfilnWi += fcb * Math.log(Wcb);
    }
    //get ZlnI
    int Z = e.getAtomicNumber();
    double I = e.getI();
    if ((Z != 1) && (Z != 6) && (Z != 7) && (Z != 8) && (Z!= 9) && (Z != 17)) { //already modified in table
      I *= 1.13; //modified from gas to liquid/solid phase
    }
    double ZlnI = Z*Math.log(I);
    double[] both = {sumfilnWi, ZlnI}; 
    return both;
  }
  
  private int heavisideStepFunction(double x) {
    if (x >= 0) {
      return 1;
    }
    else {
      return 0;
    }
  }
  
  private int diracDeltaFunction(double x) {
    //so far this is always going to miss it i need to set a to summin
    if (x == 0) {
      return 1;
    }
    else {
      return 0;
    }
  }
  
  private double getFMinus(double E, double W) {
    double kappa = W/E;
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = (E/1000) * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double gamma = 1/Math.pow((1-betaSquared), 0.5);
    double FMinus = 1 + Math.pow(kappa/(1-kappa), 2) - (kappa/(1-kappa))
                    + (Math.pow((gamma-1)/gamma, 2)*(Math.pow(kappa, 2) + (kappa/(1-kappa))));
    return FMinus;
  }
  
  private double getdci(double Wi, double W, double E) { //E must be passed in eV
    double Wmax = E/2;
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = (E/1000) * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double vSquared = betaSquared * csquared;
    double dci = (2*Math.PI*(Math.pow(elementaryCharge, 4)/1E18)/(m*vSquared))*Math.pow((W/1000)*Beam.KEVTOJOULES, -2)*getFMinus(E, W)
                   * heavisideStepFunction(W-Wi)* heavisideStepFunction(Wmax-W);
    return dci; //units Kg^-1 s^2
  }
  
  private double getDelta() {
    return 0;
  }
  
  private double getQMinus(double W, double E, boolean plus) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = (E/1000) * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double gamma = 1/Math.pow((1-betaSquared), 0.5);
    double p = Math.pow(betaSquared,0.5)*gamma*m*c;
    double Edash = ((E-W)/1000)*Beam.KEVTOJOULES;
    double betaSquareddash = 1- Math.pow(m*csquared/(Edash + m*csquared), 2); 
    double gammadash = 1/Math.pow((1-betaSquareddash), 0.5);
    double pdash = Math.pow(betaSquareddash,0.5)*gammadash*m*c;
    if (plus == false) {
      double Qminus = 0;
      if (W/E < 100) {
        Qminus = Math.pow(Beam.KEVTOJOULES*(W/1000), 2) / (2*betaSquared*m*csquared);
      }
      else {
        Qminus = Math.pow(csquared*(p-pdash) + Math.pow(m*csquared, 2), 0.5) - m*csquared;
      }
      return Qminus;
    }
    else {
      double Qplus = Math.pow(csquared*(p+pdash) + Math.pow(m*csquared, 2), 0.5) - m*csquared;
      return Qplus;
    }
  }
  
  private double getddi(double Wi, double W, double E) {
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = (E/1000) * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double vSquared = betaSquared * csquared;
    double Qminus = getQMinus(Wi, E, false);
 //   double Qminus = getQMinus(Wi, E);
    double WiJoules = (Wi/1000)*Beam.KEVTOJOULES;
    
    double ddi = (2*Math.PI*(Math.pow(elementaryCharge, 4)/1E18)/(m*vSquared))*(1/WiJoules) * 
                  (Math.log((WiJoules/Qminus)*((Qminus+2*m*csquared)/(WiJoules+2*m*csquared))) 
                   + Math.log(1/(1-betaSquared)) - betaSquared - getDelta()) * diracDeltaFunction(W-Wi);
    return ddi;
  }
  
  private double[] getIntegratedSigmai(double Wi, double step, double E, int fi) {
    double dsigmaiOverdW = 0, previousdsigmaiOverdW = 0, wtimes = 0, previousWtimes = 0, w2times = 0, previousW2times = 0;
    double integratedSigmai = 0, integratedSPi = 0, integratedVari = 0;
    double globalStep = (E/2)/Wbins;
      for (int j = 0; j <= Wbins; j++) {
        double W = Wi + step*j;
        //get the nearest energy in the TreeMap
        double energyKey = 0;
        double toFloorKey = Math.abs(W-dsimgaOverdW.floorKey(W));
        double toCeilingKey = Math.abs(W-dsimgaOverdW.floorKey(W));
        if (toFloorKey <= toCeilingKey) {
          energyKey = dsimgaOverdW.floorKey(W);
        }
        else {
          energyKey = dsimgaOverdW.ceilingKey(W);
        }
        
        double Wjoules = (W/1000)*Beam.KEVTOJOULES;
        dsigmaiOverdW += fi*(getdci(Wi, W, E) + getddi(Wi, W, E));  //Kg^-1 s^2
        //Add this to the right place
        double currentValue = dsimgaOverdW.get(energyKey);
        double newValue = currentValue + dsigmaiOverdW;
        dsimgaOverdW.replace(energyKey, newValue);
        
        wtimes = Wjoules*fi*(getdci(Wi, W, E) + getddi(Wi, W, E));  //J.Kg^-1 s^2
        w2times = Wjoules*Wjoules*fi*(getdci(Wi, W, E) + getddi(Wi, W, E));  //J^2Kg^-1 s^2
        if (j > 0) {
          //integrate
          integratedSigmai += ((dsigmaiOverdW+previousdsigmaiOverdW)/2)*((step/1000)*Beam.KEVTOJOULES); //m^2
          integratedSPi += ((wtimes+previousWtimes)/2)*((step/1000)*Beam.KEVTOJOULES); //J.m^2
          integratedVari += ((w2times+previousW2times)/2)*((step/1000)*Beam.KEVTOJOULES); //J^2.m^2
        }
        previousdsigmaiOverdW = dsigmaiOverdW;
        previousWtimes = wtimes;
        previousW2times = w2times;
      }  
    double[] integratedValues = {integratedSigmai, integratedSPi, integratedVari};
    return integratedValues;
  }
  public double[] integrateSigma() {
    double count = 0;
    double lastKey = 0, lastValue = 0, value = 0, key = 0, integral = 0, integralW = 0, integralW2 = 0;
    for (Map.Entry<Double, Double> entry : dsimgaOverdW.entrySet()) {
      if (count > 0) {
        value = entry.getValue();
        key = entry.getKey();
        integral += ((value+lastValue)/2)*(((key-lastKey)/1000)*Beam.KEVTOJOULES); //m^2
        double W = ((lastKey + (key-lastKey)/2)/1000)*Beam.KEVTOJOULES; //J
        integralW += ((W*value+W*lastValue)/2)*(((key-lastKey)/1000)*Beam.KEVTOJOULES); //J.m^2
        integralW2 += ((W*W*value+W*W*lastValue)/2)*(((key-lastKey)/1000)*Beam.KEVTOJOULES); //J.m^2
      }
      lastKey = key;
      lastValue = value;
      count +=1;
    }
    double[] integratedValues = {integral, integralW, integralW2};
    return integratedValues;
  }
  
  //going to work out the GOS and do that model below
  /*
  public void doGOSStuff(double electronEnergy) {
 // public void getDifferentialInlasticxSection(double electronEnergy){
    double integratedSigmai = 0;
    double Wmax = E/2;
    double Wcb = getWcbAll();
    double plasmaEnergy = getPlasmaEnergyAll();
    double sumfcb = 0;
    for (Element e: this.presentElements) {
      sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
    }
    double E = electronEnergy * 1000; //in eV
    int[] shells = {2, 8, 18, 32};
    double total_x_section = 0, totalSP = 0, totalVar = 0, otherX = 0, otherSP = 0, otherVar = 0;
    // do the plasma stuff first
    double a = 1.65;
    double b = 1; //trying these just for now
    //for Wcb
    double step = (Wmax - Wcb) / Wbins;
    double[] integratedValues = getIntegratedSigmai(Wcb, step, E, sumfcb);
    integratedSigmai = integratedValues[0];
    double shell_x_section = ((totalAtoms(e)/cellVolume)*1E30) * integratedSigmai; // m^-1
    double shellSP = ((totalAtoms(e)/cellVolume)*1E30) * integratedValues[1]; // J.m^-1
    double shellVar = ((totalAtoms(e)/cellVolume)*1E30) * integratedValues[2]; // J^2.m^-1
    total_x_section += shell_x_section/1E9; //nm^-1
    totalSP += (shellSP/Beam.KEVTOJOULES)/1E9; //keV/nm
    totalVar += ((shellVar/Math.pow(Beam.ELEMENTARYCHARGE,2))/1E6)/1E9; //keV^2/nm
    
    // get Wi for each shell of each element
    for (Element e : this.presentElements) {
      dsimgaOverdW = new TreeMap<Double, Double>(); //clearing it
      int Z = e.getAtomicNumber();
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      double[] Wi = new double[numInnerShells];
      if (numInnerShells > 3){
        numInnerShells = 3; //does not work for elements Z above 60 a the moment as no N edge in MuCalc!!!! 
      }
      //get Wi
      if (numInnerShells > 0) {
        for (int i = 0; i < numInnerShells; i++) { 
          Wi[i] = Math.pow(Math.pow(a * getShellBinding(i, e)*1000,2) + (2/3)*(shells[i]/Z)*Math.pow(plasmaEnergy, 2), 0.5);
        }
      }
      
      for (int i = 0; i < numInnerShells; i++) { 
        //need to start at Wi go up to Wmax
        double step = (Wmax - Wi[i]) / Wbins;
        double[] integratedValues = getIntegratedSigmai(Wi[i], step, E, shells[i]);
        integratedSigmai = integratedValues[0];
        //now get the inelastic cross section for this shell
        double shell_x_section = ((totalAtoms(e)/cellVolume)*1E30) * integratedSigmai; // m^-1
        double shellSP = ((totalAtoms(e)/cellVolume)*1E30) * integratedValues[1]; // J.m^-1
        double shellVar = ((totalAtoms(e)/cellVolume)*1E30) * integratedValues[2]; // J^2.m^-1
        total_x_section += shell_x_section/1E9; //nm^-1
        totalSP += (shellSP/Beam.KEVTOJOULES)/1E9; //keV/nm
        totalVar += ((shellVar/Math.pow(Beam.ELEMENTARYCHARGE,2))/1E6)/1E9; //keV^2/nm
      }
    }
    
  }
  */
  
  
  
  
  
  
  
  
  
  
  
  
  
  //start again!!!!!
  //I'll need to go through adjusting all the units and putting stuff in joules I think!!!
  
  public double FkQW(double Q, double W, double Wk, double Qk) {
    return diracDeltaFunction(W-Wk)*heavisideStepFunction(Qk-Q) + diracDeltaFunction(W-Q)*heavisideStepFunction(Q-Wk);
  }
  
  public double getGOS(double W, double Q, Element e, double Wk, double Qk) {
    double GOS = 0;
    int[] shells = {2, 8, 18, 32};
    //equation 3.58 gives the GOS of an atom 
    int Z = e.getAtomicNumber();
    int[] electrons = getNumValenceElectrons(e);
    int numInnerShells = electrons[1];
    for (int i = 0; i < numInnerShells; i++) {
      int fk = shells[i];
      GOS += fk*FkQW(Q, W, Wk, Qk);
    }
    return GOS;
  }
  
  public double getOOSElement(double W, Element e) {
    double OOS = 0;
    double a = 1.65;
    int[] shells = {2, 8, 18, 32};
    //equation 3.58 gives the GOS of an atom 
    int Z = e.getAtomicNumber();
    int[] electrons = getNumValenceElectrons(e);
    int numInnerShells = electrons[1];
    for (int i = 0; i < numInnerShells; i++) {
      double Wk = getWkMolecule(a, e, i);
      int fk = shells[i];
      OOS += totalAtoms(e)*fk*diracDeltaFunction(W-Wk);
    }
    return OOS;
  }
  
  public double getOOSPlasmon(double W) {
    double OOS = 0;
    double Wcb = getWcbAll();
    double sumfcb = 0;
    for (Element e: this.presentElements) {
      sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
    }
    OOS = sumfcb*diracDeltaFunction(W-Wcb);
    return OOS;
  }
  
  @Override
  public double getWkMolecule(double a, Element e, int shellIndex) {
    int[] shells = {2, 8, 18, 32};
    int Z = e.getAtomicNumber();
    int sumZ = 0;
    for (Element elem: this.presentElements) {
        sumZ += elem.getAtomicNumber() * totalAtoms(elem);
    }
    int fk = shells[shellIndex];
  //  if (e.getAtomicNumber() == 8 && shellIndex == 1) {
   //   fk = 6;
   // }
    int[] electrons = getNumValenceElectrons(e);
    if (shellIndex == electrons[1]) { //if the shell is a valence shell
      fk = electrons[0];
    }
    double plasmaEnergy = getPlasmaEnergyAll();
    double Wk = Math.pow(Math.pow(a * getShellBinding(shellIndex, e)*1000,2) + (2/3)*((fk*totalAtoms(e))/(sumZ))*Math.pow(plasmaEnergy, 2), 0.5);
    //I am really guessing the Lorenz Lorentz sumFk sum Z but just a hunch
    return Wk;
  }
  
  public double[] checkMeanI(double electronEnergy, double a) {
    int[] shells = {2, 8, 18, 32};
    //just a start for a test
    double ZlnI = getZlnI(electronEnergy);
    double sumfcb = 0;
    //get sumfk*ln(Wk)
    double sumfklnWk = 0;
    for (Element e: this.presentElements) {
      if (e.getAtomicNumber() != 8) {
 //       sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
      }
      //get number of shells
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      if (e.getAtomicNumber() == 8) {
   //     numInnerShells = 2;
      }
   //   for (int i = 0; i < numInnerShells; i++) {
      for (int i = 0; i <= numInnerShells; i++) {
        int fk = shells[i];
        /*
        if (e.getAtomicNumber() == 8 && i == 1) {
          fk = 6;
        }
        */
        
        if (i == numInnerShells) { //then this is the valence shell
          double Uk = getShellBinding(i, e)*1000;
          if (Uk > fcbCutoff) {
            fk = electrons[0];
          }
          else {
            sumfcb += electrons[0]; //if below the cutoff energy then consider it in the conduction band
            fk = 0;
          }
        }
        double Wk = getWkMolecule(a, e, i);
        if (Wk > 0) {
          sumfklnWk += totalAtoms(e)*fk * Math.log(Wk); //total atoms as my unit cell is one molecule
        }
      }
    }
    //get fcb*ln(Wcb)
    double fcblnWcb = 0;
    double Wcb = getWcbAll();
    if (Wcb > 0.0) {
      fcblnWcb = sumfcb * Math.log(Wcb);
    }
    double RHS = fcblnWcb + sumfklnWk;
    double[] both = {ZlnI, RHS};
    return both;
    //so I may need to adjust a and b here to get these to match up like I did before, not a million miles away for now though
  }
  
  public double getZlnI(double electronEnergy) {
    double sumZ = 0, sumA = 0, meanJ = 0, meanlnI = 0;
    for (Element e : this.presentElements) { 
      //calculate meanJ (mean excitation energy) for this material
      //molWeight fraction
      double A = e.getAtomicWeight();
      double molWeightFraction = 0;
      int Z = e.getAtomicNumber();
      molWeightFraction = (totalAtoms(e) * A) / molecularWeight;
      sumZ += Z * totalAtoms(e);
      sumA += A * totalAtoms(e);
      double J = 0, Jstar = 0, k = 0;
      J = e.getI();
      if ((Z != 1) && (Z != 6) && (Z != 7) && (Z != 8) && (Z!= 9) && (Z != 17)) { //already modified in table
        J *= 1.13; //modified from gas to liquid/solid phase
      }
      k = 0.7344 * Math.pow(Z, 0.0367);
      Jstar = J / (1+ k*(J/(electronEnergy*1000)));
      
   //   Jstar = J;
      meanJ += (Jstar * molWeightFraction);  //eV
      meanlnI +=  molWeightFraction * (Z/A) * Math.log(Jstar);
    }
    meanlnI = meanlnI/(sumZ/sumA); 
    double I = Math.exp(meanlnI);
    return (sumZ * meanlnI);
  }
  
  public double longitudinalDCS(double W, double Q, double E, double a) {
    //Qk = Uk for bound shells, Qk = 0 for the plasma - Actually I think Qk = Qcb = Wcb;
    int[] shells = {2, 8, 18, 32};
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = (E/1000) * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double vSquared = betaSquared * csquared;
    double constant = 2*Math.PI*(Math.pow(elementaryCharge, 4)/1E18)/(m*vSquared);
    // do bound shells
    double sumfcb = 0, sumDCS = 0;
    for (Element e: this.presentElements) {
      sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
      //get number of shells
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      for (int i = 0; i < numInnerShells; i++) {
        int fk = shells[i];
        double Wk = getWkMolecule(a, e, i);
        double Uk = getShellBinding(i, e)*1000;
        double Qk = getQak(E, Wk, Uk);
        //the units below don't work
        sumDCS += totalAtoms(e)*fk * (1/W)*((2*m*csquared)/(Q*(Q+2*m*csquared)))*getpDisW(E, W, a, e, i)*heavisideStepFunction(Qk-Q);
      }
    }
    //and now the plasmon stuff
    double Qk = 0;
    double Wcb = getWcbAll();
    //units below don't match up
    sumDCS += sumfcb * (1/W)*((2*m*csquared)/(Q*(Q+2*m*csquared)))*diracDeltaFunction(W-Wcb)*heavisideStepFunction(Qk-Q);
    sumDCS *= constant;
    return sumDCS;
  }
  
  public double transverseDCS(double W, double Q, double E, double a) {
  //Qk = Uk for bound shells, Qk = 0 for the plasma
    int[] shells = {2, 8, 18, 32};
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = (E/1000) * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double vSquared = betaSquared * csquared;
    double constant = 2*Math.PI*(Math.pow(elementaryCharge, 4)/1E18)/(m*vSquared);
    double deltaF = getDeltaF(E, a);
    // do bound shells
    double sumfcb = 0, sumDCS = 0;
    for (Element e: this.presentElements) {
      sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
      //get number of shells
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      for (int i = 0; i < numInnerShells; i++) {
        int fk = shells[i];
        double Wk = getWkMolecule(a, e, i);
        double Uk = getShellBinding(i, e)*1000;
        double Qk = getQak(E, Wk, Uk);
        double Wak = getWak(E, Wk, Uk);
        //units don't work
        sumDCS += totalAtoms(e)*fk * (1/W)*(Math.log(1/(1-betaSquared))-betaSquared-deltaF)*getpDisW(E, W, a, e, i)*heavisideStepFunction(Qk-Q)*diracDeltaFunction(Q-getQminusModified(E, Wak));
      }
    }
    //and now the plasmon stuff
    double Qk = 0;
    double Wcb = getWcbAll();
    //units don't work
    sumDCS += sumfcb * (1/W)*(Math.log(1/(1-betaSquared))-betaSquared-deltaF)*diracDeltaFunction(W-Wcb)*heavisideStepFunction(Qk-Q)*diracDeltaFunction(Q-getQMinus(W, E, false));
    sumDCS *= constant;
    return sumDCS;
  }
  
  public double closeDCS(double W, double Q, double E, double a) {
    int[] shells = {2, 8, 18, 32};
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = (E/1000) * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double vSquared = betaSquared * csquared;
    double constant = 2*Math.PI*(Math.pow(elementaryCharge, 4)/1E18)/(m*vSquared);
    // do bound shells
    double sumfcb = 0, sumDCS = 0;
    for (Element e: this.presentElements) {
      sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
      //get number of shells
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      for (int i = 0; i < numInnerShells; i++) {
        int fk = shells[i];
        double Qk = getShellBinding(i, e)*1000;
        double Wk = getWkMolecule(a, e, i);
        double Uk = getShellBinding(i, e)*1000;
        double Qak = getQak(E, Wk, Uk);
        double Fminus = getFMinusClose(E, W, Uk);
        //units don't work
        sumDCS += totalAtoms(e)*fk * (1/Math.pow(W, 2))*Fminus*diracDeltaFunction(W-Q)*heavisideStepFunction(Q-Qak);
      }
    }
    //and now the plasmon stuff
    double Qk = 0;
    double Uk = 0;
    double Wcb = getWcbAll();
    double Fminus = getFMinusClose(E, Wcb, Uk);
    //units don't work
    sumDCS += sumfcb * (1/Math.pow(W, 2))*Math.pow(W, 2)*diracDeltaFunction(W-Q)*heavisideStepFunction(Q-Qk);
    sumDCS *= constant;
    return sumDCS;
  }
  

  
  public double getQak(double E, double Wk, double Uk) {
    if (E*1000 > 3*Wk - 2*Uk) {
      return Uk;
    }
    else {
      return Uk * (E*1000/(3*Wk-2*Uk));
    }
  }
  
  public double getWak(double E, double Wk, double Uk) {
    if (E < 0.1) {
      double test = 0.0;
    }
//    if (E*1000 < Uk) {
//      return 0;
//    }
    if (E*1000 > 3*Wk - 2*Uk) {
      return Wk;
    }
    else {
      return (E*1000+2*Uk)/3;
    }
  }
  
  public double getFnought(double E) {
    //get sumZ
    double sumZ = 0;
    for (Element e: this.presentElements) {
      sumZ += e.getAtomicNumber()*totalAtoms(e);
    }
    double plasmaEnergy = getPlasmaEnergyAll();
    //integrate
    double Wmax = E/2; //change
    double step = Wmax/Wbins;
    double previousY = 0, integralSum = 0;
    for (double i = 0; i <= Wmax; i += step) {
      double W = i;
      double OOS = getTotalOOSMolecule(W);
      double y = Math.pow(W, -2) * OOS;
      if (i > 0) {
        //then integrate
        integralSum += ((y+previousY)/2)*step;
      }
      previousY = y;
    }
    double Fnought = (1/sumZ)*Math.pow(plasmaEnergy, 2)* integralSum;
    return Fnought;
  }
  
  public double FnoughtTwo(double E, double a, double L) {
    //get sumZ
    int[] shells = {2, 8, 18, 32};
    double sumZ = 0;
    double plasmaEnergy = (getPlasmaEnergyAll()/1000) * Beam.KEVTOJOULES;
    
    double sumfcb = 0, sumFnought = 0;
    for (Element e: this.presentElements) {
      /*
      sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
      //get number of shells
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      for (int i = 0; i < numInnerShells; i++) {
        int fk = shells[i];
        double Wk = getWkMolecule(a, e, i);
        sumFnought += fk/Math.pow(Wk,  2);
      }
      */
      sumZ += e.getAtomicNumber() * totalAtoms(e);
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      for (int i = 0; i <= numInnerShells; i++) {
        int fk = shells[i];
        if (i == numInnerShells) { // this is a valence
          fk = electrons[0];
        }
        double Wk = getWkMolecule(a, e, i);
        if (Wk > 0) {
          double Uk = getShellBinding(i, e)*1000;
          if (Uk < fcbCutoff) {
            sumfcb += fk * totalAtoms(e);
            fk = 0;
          }
          sumFnought += fk / (Math.pow((Wk/1000)*Beam.KEVTOJOULES,  2) + Math.pow(L, 2));
        }
      }
      
    }
    double Wcb = getWcbAll();
    if (Wcb > 0) {
      sumFnought += sumfcb / (Math.pow((Wcb/1000)*Beam.KEVTOJOULES,  2) + Math.pow(L, 2));
    }
    
    double Fnought2 = (1/sumZ)*Math.pow(plasmaEnergy, 2)*sumFnought;
    return Fnought2;
  }
  
  public double getTotalOOSMolecule(double W) {
    //get the OOS for the whole molecule for this W
    double sumOOS = 0;
    for (Element e: this.presentElements) {
      sumOOS += getOOSElement(W, e);
    }
    //add on the plasmon for this OOS
    sumOOS += getOOSPlasmon(W);
    return sumOOS;
  }
  
  public double getDeltaF(double electronEnergy, double a) {
    double deltaF = 0;
    double E = electronEnergy*1000;
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = electronEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double L = 0;
    
    //add in the high B approximation
    if (betaSquared > 0.95) {
      //use high energy approximation
      double sumZ = 0;
      for (Element e: this.presentElements) {
        sumZ += e.getAtomicNumber()*totalAtoms(e);
      }
      double plasmaEnergy = getPlasmaEnergyAll();
      double I = Math.exp(getZlnI(electronEnergy)/sumZ);
      deltaF = Math.log(Math.pow(plasmaEnergy,2)/((1-betaSquared)*Math.pow(I, 2)))-1;
    }
    else {
      double LHS = 1-betaSquared;
      double RHS = FnoughtTwo(E, a, 0);
      if (LHS < RHS) {
        //get L
        L = Math.pow(1-betaSquared, 0.5); 
        // get L properly
        //not too critical for now as it's always 0 in this range
        /*
        deltaF = calcDeltaF(L, betaSquared, E);
        deltaF = calcDeltaF2(L, betaSquared, E, a);
        */
        deltaF = 0;
      }
      else {
        deltaF = 0;
      }
    }
    return deltaF;
  }
  
  public double calcDeltaF(double L, double betaSquared, double E) {
    //get sumZ
    double sumZ = 0;
    for (Element e: this.presentElements) {
      sumZ += e.getAtomicNumber()*totalAtoms(e);
    }
    double plasmaEnergy = getPlasmaEnergyAll();
    
    double Wmax = E/2; // need to change Wmax
    //get the integral
    double step = Wmax/Wbins;
    double previousY = 0, integralSum = 0;
    for (double i = 0; i <= Wmax; i += step) {
      double W = i;
      double OOS = getTotalOOSMolecule(W);
      double y = OOS * Math.log(1 + Math.pow(L, 2)/Math.pow(W, 2));
      if (i > 0) {
        //then integrate
        integralSum += ((y+previousY)/2)*step;
      }
      previousY = y;
    }
    double deltaF = (1/sumZ)*integralSum - ((Math.pow(L, 2)/Math.pow(plasmaEnergy, 2))*(1-betaSquared));
    return deltaF;
  }
  
  public double calcDeltaF2(double L, double betaSquared, double E, double a) {
    double deltaF = 0, sumfcb = 0, sumZ = 0, sumDeltaF = 0;
    int[] shells = {2, 8, 18, 32};
    for (Element e: this.presentElements) {
      sumZ += e.getAtomicNumber() * totalAtoms(e);
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      for (int i = 0; i <= numInnerShells; i++) {
        int fk = shells[i];
        if (i == numInnerShells) { // this is a valence
          fk = electrons[0];
        }
        double Wk = getWkMolecule(a, e, i);
        if (Wk > 0) {
          double Uk = getShellBinding(i, e)*1000;
          if (Uk < fcbCutoff) {
            sumfcb += fk * totalAtoms(e);
            fk = 0;
          }
          sumDeltaF += fk * Math.log(1+Math.pow(L, 2)/Math.pow((Wk/1000)*Beam.KEVTOJOULES, 2));
        }
      }
    }
    double Wcb = getWcbAll();
    if (Wcb > 0) {
      sumDeltaF += sumfcb * Math.log(1+Math.pow(L, 2)/Math.pow((Wcb/1000)*Beam.KEVTOJOULES, 2));
    }
    sumDeltaF *= 1/sumZ;
    double plasmaEnergy = (getPlasmaEnergyAll()/1000) * Beam.KEVTOJOULES;
    deltaF = sumDeltaF - (Math.pow(L, 2)/Math.pow(plasmaEnergy, 2)) * (1-betaSquared);
    return deltaF;
  }
  
  public double getQminusModified(double EkeV, double WakeV) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    //sort out units here
    double E = EkeV*Beam.KEVTOJOULES;
    double Wak = (WakeV/1000)*Beam.KEVTOJOULES;
    double Qminus = Math.pow(Math.pow(Math.pow(E*(E+2*m*csquared), 0.5)-Math.pow((E-Wak)*(E-Wak+2*m*csquared), 0.5), 2)+Math.pow(m*csquared, 2), 0.5)-m*csquared;  
    if (Wak > E) {
      Qminus = 0;
    }
    return Qminus;
  }
  
  public double getEdash(double E, double Uk) {
    return E+Uk/1000; //get units right here
  }
  
  public double getFMinusClose(double E, double W, double Uk) {
    double Edash = getEdash(E, Uk);
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = (E/1000) * Beam.KEVTOJOULES;
    double a = Math.pow(Vo/(Vo+m*csquared), 2);
    double Fminus = 1+ Math.pow(W/(Edash-W), 2) - W/(Edash-W) + a*(W/(Edash-W) + Math.pow(W, 2)/Math.pow(E, 2));
    return Fminus;
  }
  
  public double integrateSigmaLongn(double E, int n, double a) {
    int[] shells = {2, 8, 18, 32};
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = E * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double vSquared = betaSquared * csquared;
    double constant = 2*Math.PI*(Math.pow(elementaryCharge, 4)/1E18)/(m*vSquared); //m^2
    
    
    double sumfcb = 0, sumDCS = 0;
    for (Element e: this.presentElements) {
      if (e.getAtomicNumber() != 8) {
    //    sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
      }
      //get number of shells
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      if (e.getAtomicNumber() == 8) {
     //   numInnerShells = 2;
      }
     // for (int i = 0; i < numInnerShells; i++) {
      for (int i = 0; i <= numInnerShells; i++) {
        int fk = shells[i];
        if (e.getAtomicNumber() == 1 && i == 1) {
      //    fk = 6;
        }
        if (i == numInnerShells) { // this is a valence
          fk = electrons[0];
          
        }
        double Wk = getWkMolecule(a, e, i);
        if (Wk > 0) {
          double Uk = getShellBinding(i, e)*1000;
          if (Uk < fcbCutoff) {
            sumfcb += fk * totalAtoms(e);
            fk = 0;
          }
          double Wak = getWak(E, Wk, Uk);
          double Qak = getQak(E, Wk, Uk);
          double Qminus = getQminusModified(E, Wak);
          //do the integral here
          double integral = integrateDist(E, Uk, n, i, e, a);
       //   fk = 0;
          double DCS = 0;
          if (Qminus > 0 && Qak > 0) {
            DCS = totalAtoms(e)*fk * Math.log(((Qak/1000)*Beam.KEVTOJOULES/Qminus)*((Qminus+2*m*csquared)/((Qak/1000)*Beam.KEVTOJOULES+2*m*csquared))) * integral;
          }
          if (E*1000 > Uk) {
            sumDCS += DCS;
          }
        }
      }
    }
    //and now the plasmon stuff
 //   double Qak = 0, Uk = 0;
    double Wcb = getWcbAll();
    double Qcb = Wcb;
    double Qminus = getQminusModified(E, Wcb);
   // double Wak = getWak(E, Wcb, Uk);
    double integral = integrateDistPlasmon(E, n, Wcb);
    //units below don't match up
    if (Wcb > 0 && E*1000 > Wcb) {
      sumDCS += sumfcb *  Math.log(((Qcb/1000)*Beam.KEVTOJOULES/Qminus)*((Qminus+2*m*csquared)/((Qcb/1000)*Beam.KEVTOJOULES+2*m*csquared))) * integral;
    }
    sumDCS *= constant;
    return sumDCS;
  }
  
  public double integrateSigmaTransn(double E, int n, double a) {
    int[] shells = {2, 8, 18, 32};
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = E * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double vSquared = betaSquared * csquared;
    double constant = 2*Math.PI*(Math.pow(elementaryCharge, 4)/1E18)/(m*vSquared); //m^2
    double deltaF = getDeltaF(E, a);
    
    double sumfcb = 0, sumDCS = 0;
    for (Element e: this.presentElements) {
         if (e.getAtomicNumber() != 8) {
   //   sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
    }
    //get number of shells
    int[] electrons = getNumValenceElectrons(e);
    int numInnerShells = electrons[1];
    if (e.getAtomicNumber() == 8) {
   //   numInnerShells = 2;
    }
  //  for (int i = 0; i < numInnerShells; i++) {
    for (int i = 0; i <= numInnerShells; i++) {
      int fk = shells[i];
      if (e.getAtomicNumber() == 1 && i == 1) {
 //       fk = 6;
      }
      if (i == numInnerShells) { // this is a valence
        fk = electrons[0];
      }
      double Wk = getWkMolecule(a, e, i);
      if (Wk > 0) {
        double Uk = getShellBinding(i, e)*1000;
        if (Uk < fcbCutoff) {
          sumfcb += fk * totalAtoms(e);
          fk = 0;
        }
        double Wak = getWak(E, Wk, Uk);
        //do the integral here
        double integral = integrateDist(E, Uk, n, i, e, a);
    //    fk = 0;
        deltaF = 0;
        if (E*1000 > Uk) {
          sumDCS += totalAtoms(e)* fk * (Math.log(1/(1-betaSquared))-betaSquared-deltaF) * integral;
        }
      }
    }
    }
    //and now the plasmon stuff
 //   double Qak = 0, Uk = 0;
    double Wcb = getWcbAll();
   // double Wak = getWak(E, Wcb, Uk);
    double integral = integrateDistPlasmon(E, n, Wcb);
    //units below don't match up
    if (Wcb > 0 && E* 1000 > Wcb) {
      sumDCS += sumfcb *  (Math.log(1/(1-betaSquared))-betaSquared-deltaF) * integral;
    }
    sumDCS *= constant;
    return sumDCS;
  }
  
  public double integrateSigmaClosen(double E, int n, double a) {
    int[] shells = {2, 8, 18, 32};
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = E * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double vSquared = betaSquared * csquared;
    double constant = 2*Math.PI*(Math.pow(elementaryCharge, 4)/1E18)/(m*vSquared); //Kg m^4 s^-2
    
    double sumfcb = 0, sumDCS = 0;
    for (Element e: this.presentElements) {
         if (e.getAtomicNumber() != 8) {
    //  sumfcb += getNumValenceElectrons(e)[0] * totalAtoms(e);
    }
    //get number of shells
    int[] electrons = getNumValenceElectrons(e);
    int numInnerShells = electrons[1];
    if (e.getAtomicNumber() == 8) {
    //  numInnerShells = 2;
    }
//    for (int i = 0; i < numInnerShells; i++) {
    for (int i = 0; i <= numInnerShells; i++) {
      int fk = shells[i];
      if (e.getAtomicNumber() == 1 && i == 1) {
   //     fk = 6;
      }
      if (i == numInnerShells) { // this is a valence
        fk = electrons[0];
      }
        double Wk = getWkMolecule(a, e, i);
      if (Wk > 0) {
        double Uk = getShellBinding(i, e)*1000;
        if (Uk < fcbCutoff) {
          sumfcb += fk * totalAtoms(e);
          fk = 0;
        }
        double Wak = getWak(E, Wk, Uk);
        double Qak = getQak(E, Wk, Uk);
        //do the integral here
        double integral = doCloseIntegral(E, n, Uk, Qak); // change this
     //   fk = 0;
        if (E*1000 > Uk) {
          sumDCS += totalAtoms(e)*fk * integral;
        }
      }
    }
    }
    //and now the plasmon stuff
 //   double Qak = 0, Uk = 0;
    double Wcb = getWcbAll();
    double Qcb = Wcb;
    double Qminus = getQminusModified(E, Wcb);
   // double Wak = getWak(E, Wcb, Uk);
    double integral = doCloseIntegral(E, n, 0, Qcb); //do this properly 
    if (Wcb > 0 && E*1000 > Wcb) {
      sumDCS += sumfcb * integral;
    }
    sumDCS *= constant;
    return sumDCS;
  }
  
  public double integrateDist(double E, double Uk, int n, int i, Element e, double a) {
    double Wmax = (getEdash(E, Uk)/2)*Beam.KEVTOJOULES;
 //   int bins = 100;
    double step = Wmax/(Wbins*10);
    double W = 0, previousY = 0, thisY = 0, sumIntegral = 0;
    int count = 0;
    while (W <= Wmax) {
      if (W == 0) {
        thisY = 0;
      }
      else {
      thisY = Math.pow(W, n-1)*getpDisW(E, W, a, e, i);
      }
      if (count > 0) {
        sumIntegral += step*((thisY+previousY)/2);
      }
      count += 1;
      W += step;
      previousY = thisY; 
    }
    
    return sumIntegral;
  }
  
  public double getpDisW(double E, double W, double a, Element e, int i) {
    double Uk = getShellBinding(i, e)*1000;
    //get Wk
    double Wk = getWkMolecule(a, e, i);
    //modify Wk
    double Wak = getWak(E, Wk, Uk);
    double Wdis = 3*Wak - 2*Uk;
    //change values to J here
    Uk = (Uk/1000)*Beam.KEVTOJOULES;
    Wdis = (Wdis/1000)*Beam.KEVTOJOULES;
   // double W = (Win/1000)*Beam.KEVTOJOULES;
    if (W >= Uk && W < Wdis) {
      return (2/Math.pow(Wdis-Uk, 2)) * (Wdis-W);
    }
    else {
      return 0;
    }
  }
  
  public double integrateDistPlasmon(double E, int n, double Wcb) {
    double Uk = 0;
    double Wmax = (getEdash(E, Uk)/2)*Beam.KEVTOJOULES;
 //   int bins = 100;
 //   double step = Wmax/Wbins;
    double previousY = 0, thisY = 0, sumIntegral = 0;
 //   int count = 0;
    double W = (Wcb/1000) * Beam.KEVTOJOULES;
    /*
    while (W <= Wmax) {
      if (W == 0) {
        thisY = 0;
      }
      else {
        thisY = Math.pow(W, n-1)*getpDisWPlasmon(E, W, Wcb); //this should be Dirac delta function bit I'm going to keep this for now
      }
      if (count > 0) {
        sumIntegral += step*((thisY+previousY)/2);
      }
      count += 1;
      W += step;
      previousY = thisY; 
    }
    */
    if (W > 0 && W < Wmax) {
      sumIntegral = Math.pow(W, n-1);
    }
    else {
      sumIntegral = 0;
    }
    
    return sumIntegral;
  }
  
  public double getpDisWPlasmon(double E,  double W, double Wcb) {
    double Uk = 0;
    double Wak = getWak(E, Wcb, Uk);
    double Wdis = 3*Wak - 2*Uk;
    Wdis = (Wdis/1000)*Beam.KEVTOJOULES;
    if (W >= Uk && W < Wdis) {
      return 2/Math.pow(Wdis-Uk, 2);
    }
    else {
      return 0;
    }
  }
  
  public double doCloseIntegral(double E, int n, double Uk, double Qak) {
    double Wmax = 1000*getEdash(E, Uk)/2;
    double a = getClosea(E);
    double sumIntegral = 0;
    sumIntegral = solveCloseAnalytical(Wmax, n, E, a) - solveCloseAnalytical(Qak, n, E, a);
    return sumIntegral;
  }
  
  public double solveCloseAnalytical(double Win, int n, double Energy, double a) {
    double E = Energy*Beam.KEVTOJOULES;
    double W = (Win/1000) * Beam.KEVTOJOULES;
    double answer = 0;
    if (n == 0) {
      answer = -1/W + 1/(E-W) + (((1-a)/E)*Math.log((E-W)/W)) + a*W/Math.pow(E, 2);
    }
    else if (n == 1) {
      answer = Math.log(W) + E/(E-W) + ((2-a)*Math.log(E-W)) + (a*Math.pow(W,2))/(2*Math.pow(E, 2));
    }
    else if (n == 2) {
      answer = (2-a)*W + (2*Math.pow(E, 2)-Math.pow(W, 2))/(E-W) + ((3-a)*E*Math.log(E-W)) + (a*Math.pow(W,3))/(3*Math.pow(E, 2));
    }
    if (E < W) {
      answer = 0;
    }
    return answer;
  }
  
  public double getClosea(double E) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = (E) * Beam.KEVTOJOULES;
    double a = Math.pow(Vo/(Vo+m*csquared), 2);
    return a;
  }
  
  public double getSturnheimera(double E) {
    double a = 1.5;
    boolean tooHigh = false;
    if (E < 0.1) {
      double test = 0.0;
    }
    double[] checkEqu = checkMeanI(E, a);
    double[] lastcheckEqu = checkMeanI(E, a);
    if (checkEqu[1] > checkEqu[0]) {
      a = 1.5; //actually I'm going to go down!!!
      while (checkEqu[1] > checkEqu[0]) {
        if (a == 0.1) {
          tooHigh = true;
          break;
        }
        lastcheckEqu = checkEqu;
        a -= 0.1;
        checkEqu = checkMeanI(E, a);
      }
    }
    else {
      //loop until it is higher
      while (checkEqu[1] < checkEqu[0]) {
        if (a >= 3) {
          tooHigh = true;
          break;
        }
        lastcheckEqu = checkEqu;
        a += 0.1;
        checkEqu = checkMeanI(E, a);
      }
    }
    if (tooHigh == false && a > 1.5) {
      //interpolate
      double fractionBack = (checkEqu[1] - checkEqu[0]) / (checkEqu[1] - lastcheckEqu[1]);
      a -= 0.1*fractionBack;
    }
    return a;
  }
  
  @Override
  public double getGOSInel(boolean surrounding, double E) {

    //so this is going to return the inelastic cross section calculated by the GOS model
 //   double E = 100; //keV
    double a = 1.5;  //this should be adjusted properly
    //adjust a value
 //   double[] test = checkMeanI(E, a);
    a = getSturnheimera(E);
    sturnheimerAdjustment = a;
    double lambda = 0;
  //  if (surrounding == false) {
      lambda = populateGOSInel(E, 0, a, surrounding);
   // }
   // else {
      
   // }
   // double getSturnheimera
    /*
    int n = 0;
    double sigmaTrans = integrateSigmaTransn(E, n, a);
    double sigmaLong = integrateSigmaLongn(E, n, a);
   // sigmaLong= 0;
    double sigmaClose = integrateSigmaClosen(E, n, a);
    double sigmaTot = (sigmaTrans + sigmaLong + sigmaClose)*1E18; //nm^2
    double sigmaInel = sigmaTot / (cellVolume/1000); //nm^-1
 //   double sigmaInel = (sigmaTot * (density/1E21) * AVOGADRO_NUM) / molecularWeight;
    double lambda = 1/sigmaInel; //nm
    */
    
    //then test stopping power
      if (E < 0.1) {
        double test = 0.0;
      }
      /*
      if (E < 0.1) { 
    int n = 1;
    double longIntegral = integrateSigmaLongn(E, n, a);
    double transIntegral = integrateSigmaTransn(E, n, a);
    double closeintegral = integrateSigmaClosen(E, n, a);
    double stoppingPower = (longIntegral + transIntegral + closeintegral)*1E18; // units J nm^2
    stoppingPower = stoppingPower / (cellVolume/1000); //J/nm
    stoppingPower = stoppingPower / Beam.KEVTOJOULES; //keV/nm
    //and now straggling parameter
    n = 2;
    double stragglingParam = (integrateSigmaLongn(E, n, a) + integrateSigmaTransn(E, n, a) + integrateSigmaClosen(E, n, a))*1E18; // units J^2 nm^2
    stragglingParam /= (cellVolume/1000); //J^2/nm
    stragglingParam = stragglingParam / Math.pow(Beam.KEVTOJOULES, 2); //keV^2/nm
        
    //also get average energy per deposition event 
    double avgEnergy = (lambda* stoppingPower) * 1000; //eV  - tis high but stopping power agrees so no plasmon cchosen for now
    
    
      double test = 0.0;
      double test2 = test;
    }
   */
    return lambda;
  }
  
  @Override
  public double getAvgInelasticEnergy(double E) {
    //this isn't working at the low energies...maybe cos a is way off
    /*
    double a = 1.5;
    a = getSturnheimera(E);
    int n = 0;
    double sigmaTrans = integrateSigmaTransn(E, n, a);
    double sigmaLong = integrateSigmaLongn(E, n, a);
   // sigmaLong= 0;
    double sigmaClose = integrateSigmaClosen(E, n, a);
    double sigmaTot = (sigmaTrans + sigmaLong + sigmaClose)*1E18; //nm^2
    double sigmaInel = sigmaTot / (cellVolume/1000); //nm^-1
 //   double sigmaInel = (sigmaTot * (density/1E21) * AVOGADRO_NUM) / molecularWeight;
    double lambda = 1/sigmaInel; //nm
    n = 1;
    double stoppingPower = (integrateSigmaLongn(E, n, a) + integrateSigmaTransn(E, n, a) + integrateSigmaClosen(E, n, a))*1E18; // units J nm^2
    stoppingPower = stoppingPower / (cellVolume/1000); //J/nm
    stoppingPower = stoppingPower / Beam.KEVTOJOULES; //keV/nm
    double avgEnergy = (lambda* stoppingPower); //keV
    */
    double avgEnergy = 21.0/1000;  //very rough could change to test just akes things slow
    return avgEnergy;
  }
  
  public double populateGOSInel(double E, int n, double a, boolean surrounding) {
    GOSinelastic = new HashMap<Element, double[][]>();
    GOSinelasticSurrounding = new HashMap<Element, double[][]>();
    int[] shells = {2, 8, 18, 32};
    double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = E * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double vSquared = betaSquared * csquared;
    double constant = 2*Math.PI*(Math.pow(elementaryCharge, 4)/1E18)/(m*vSquared); //m^2
    double deltaF = 0;
    double sumfcb = 0, checkSum = 0;
    int maxShells = 9;
    
    Set<Element> elementList = presentElements;
    if (surrounding == true) {
      elementList = cryoElements;
    }
    for (Element e: elementList) {
      if (totalAtoms(e) > 0) {
      double[][] inelasticShell = new double[maxShells][4];
      //get number of shells
      int[] electrons = getNumValenceElectrons(e);
      int numInnerShells = electrons[1];
      for (int i = 0; i <= numInnerShells; i++) {
        int fk = shells[i];
        if (i == numInnerShells) { // this is a valence
          fk = electrons[0];
          
        }
        double Wk = getWkMolecule(a, e, i);
        if (Wk > 0) {
          double Uk = getShellBinding(i, e)*1000;
          if (Uk < fcbCutoff) {
            sumfcb += fk * totalAtoms(e);
            fk = 0;
          }
          double Wak = getWak(E, Wk, Uk);
          double Qak = getQak(E, Wk, Uk); //actually Qak should only be used fgor inner shell not outer shell!!!
      //    if (i == numInnerShells) { //valence shell
      //      Qak = Uk;
      //    }
          double Qminus = getQminusModified(E, Wak);
          //do the integral here
          double integral = integrateDist(E, Uk, n, i, e, a);
       //   fk = 0;
          if(E*1000 > Uk && Uk >= fcbCutoff) {
            if (Qak >0 && Qminus > 0) {
              inelasticShell[i][0] = (1E18 * constant *(totalAtoms(e)*fk * Math.log(((Qak/1000)*Beam.KEVTOJOULES/Qminus)*((Qminus+2*m*csquared)/((Qak/1000)*Beam.KEVTOJOULES+2*m*csquared))) * integral))
                                  /(cellVolume/1000); //nm^-1  //long
            }
            inelasticShell[i][1] = (1E18 * constant * (totalAtoms(e)* fk * (Math.log(1/(1-betaSquared))-betaSquared-deltaF) * integral))
                                 /(cellVolume/1000); //nm^-1;  //trans
            integral = doCloseIntegral(E, n, Uk, Qak); 
            inelasticShell[i][2] = (1E18 * constant * (totalAtoms(e)*fk * integral)) / (cellVolume/1000); //nm^-1; //close
            inelasticShell[i][3] = inelasticShell[i][0]+inelasticShell[i][1]+inelasticShell[i][2]; //tot
            checkSum += inelasticShell[i][3];
          }
          else {
            if (E < 0.1) {
              double test = 0.0;
            }
          }
          //could be summing total inel here as well but okay for now
        }
      }
      if (surrounding == false) {
        GOSinelastic.put(e, inelasticShell);
      }
      else {
        GOSinelasticSurrounding.put(e, inelasticShell);
      }
      }
    }
    //and now the plasmon stuff
    cbInel = new double[4];
    cbInelSurrounding = new double[4];
 //   double Qak = 0, Uk = 0;
    double Wcb = getWcbAll();
    double Qcb = Wcb;
    double Qminus = getQminusModified(E, Wcb);
  //  Qminus = getQMinus(Wcb, E*1000, false);
   // double Wak = getWak(E, Wcb, Uk);
    double integral = integrateDistPlasmon(E, n, Wcb);
    //units below don't match up
    if (Wcb > 0 && E*1000 > Wcb) {
      if (surrounding == false) {
        cbInel[0] = (1E18 * constant * (sumfcb *  Math.log(((Qcb/1000)*Beam.KEVTOJOULES/Qminus)*((Qminus+2*m*csquared)/((Qcb/1000)*Beam.KEVTOJOULES+2*m*csquared))) * integral))
                     /(cellVolume/1000); //nm^-1  //long
        cbInel[1] =  (1E18 * constant * (sumfcb *  (Math.log(1/(1-betaSquared))-betaSquared-deltaF) * integral))
                      /(cellVolume/1000); //nm^-1  //trans
        integral = doCloseIntegral(E, n, 0, Qcb);
        cbInel[2] = (1E18 * constant * (sumfcb*integral)) /(cellVolume/1000);  //nm^-1 //close
        cbInel[3] = cbInel[0] + cbInel[1] + cbInel[2];
        checkSum += cbInel[3];
      }
      else {
        cbInelSurrounding[0] = (1E18 * constant * (sumfcb *  Math.log(((Qcb/1000)*Beam.KEVTOJOULES/Qminus)*((Qminus+2*m*csquared)/((Qcb/1000)*Beam.KEVTOJOULES+2*m*csquared))) * integral))
                     /(cellVolume/1000); //nm^-1  //long
        cbInelSurrounding[1] = (1E18 * constant * (sumfcb *  (Math.log(1/(1-betaSquared))-betaSquared-deltaF) * integral))
                     /(cellVolume/1000); //nm^-1  //trans
        integral = doCloseIntegral(E, n, 0, Qcb);
        cbInelSurrounding[2] = (1E18 * constant * (sumfcb*integral)) /(cellVolume/1000);  //nm^-1 //close
        cbInelSurrounding[3] = cbInelSurrounding[0] + cbInelSurrounding[1] + cbInelSurrounding[2];
        checkSum += cbInelSurrounding[3];
      }
    }
  double testLamda = 1/checkSum;
  return testLamda;
  }
  
  public double getEnergyLossDistant(double Wdis, double Uk){ 
    double RND = Math.random();
    double W = Wdis - Math.pow(RND*Math.pow(Wdis-Uk, 2), 0.5);
    return W;
  }
  
  @Override
  public double getRecoilEnergyDistant(double EkeV, double WakeV, double Qak) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    
    double Qminus = getQminusModified(EkeV, WakeV);
    double Qs = Qminus / (1+Qminus/(2*m*csquared));
    double RND = Math.random();
    //gotta make sure all these units are changed to Joules to be correct when I do it properly
    double Q = Qs * 1/(Math.pow((Qs/((Qak/1000)*Beam.KEVTOJOULES))*(1+(((Qak/1000)*Beam.KEVTOJOULES)/(2*m*csquared))), RND) - (Qs/(2*m*csquared)));
    return Q;
  }
  
  public double getPrimaryThetaLong(double E, double Q, double Wak) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double theta = 0;
    //again make sure I sort out units in here
    double numerator = E*(E+2*m*csquared) + (E-Wak)*(E-Wak+2*m*csquared) - Q*(Q+2*m*csquared);
    double denominator = 2*Math.pow(E*(E+2*m*csquared)*(E-Wak)*(E-Wak+2*m*csquared), 0.5);
    double cosTheta = numerator/denominator;
    theta = Math.acos(cosTheta);
    return theta;
  }
  
  public double getPDFk(double E, double k, double Qk) {
    double kc = Math.max(Qk, Wcc) / (E*1000);  //get units right ofc
    double a = getClosea(E);   //assume this is the gamma one not sturnheimer one
    double PDF = (Math.pow(k, -2) + Math.pow(1-k, -2) - 1/(k*(1-k)) + a*(1+1/(k*(1-k))))
                  * heavisideStepFunction(k-kc) * heavisideStepFunction(0.5-k);
    return PDF;
  }
  
  public double getRandomk(double E, double Qk) { //E in keV and Qk in eV
    double kc = Math.max(Qk, Wcc) / (E*1000);  //get units right ofc
    double k = 0;
    double a = getClosea(E);
    double RND = Math.random();
    double zeta = RND * (1+5*a*kc/2);
    if (zeta < 1) {
      k = kc / (1-zeta*(1-2*kc));
    }
    else {
      k = kc + (zeta-1)*(1-2*kc)/(5*a*kc);
    }
    return k; //dimensionless
  }
  
  //now do the rejection algorithm
  public double samplek(double E, double Qk) {
    double a = getClosea(E);
    boolean exit = false;
    double k = 0;
    int count = 0;
    while (exit == false) {
      k = getRandomk(E, Qk);
      double RND = Math.random();
      double LHS = RND * (1 + 5*a*Math.pow(k, 2));
      double RHS = Math.pow(k, 2) * getPDFk(E, k, Qk);
      if (LHS < RHS) {
        exit = true;
      }
      // testing clause
      count += 1;
      if (count > 100000) {
        System.out.println("the random sampling of k is always being rejected");
        break;
      }
    }
    return k;
  }
  
  public double getPrimaryThetaClose(double E, double Uk, double Qk) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    
    double k = samplek(E, Qk);
    double W = k*(E+Uk);
    double cosTheta = Math.pow(((E-W)/E) * ((E+ 2*m*csquared)/ (E-W+2*m*csquared)),0.5);
    double theta = Math.acos(cosTheta);
    return theta;
  }
  
  //next I have to do secondary emission of electrons
  public double secondaryThetaDistant(double E, double Wak, double Q) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    double Vo = E * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    
    double cosTheta = Math.pow(((Math.pow(Wak, 2)/betaSquared)/(Q*(Q+2*m*csquared)))
                      *Math.pow(1+(Q*(Q+2*m*csquared)-Math.pow(Wak, 2))/(2*Wak*(E+m*csquared)), 2),0.5);
    double theta = Math.acos(cosTheta);
    return theta;
  }
  
  public double seondaryThetaClose(double E, double W) {
    double m = 9.10938356E-31; // in Kg
    double c = 299792458;
    double csquared = c*c;  // (m/s)^2
    
    double cosTheta = Math.pow((W/E)*((E+2*m*csquared)/(W+2*m*csquared)), 0.5);
    double theta = Math.acos(cosTheta);
    return theta;
  }
  
  //Then properly integrate the Monte carlo and cross section sampling stuff
  @Override
  public Map<Element, double[]> getGOSShellProbs(boolean surrounding, double totLambda){
    double totInel = 1/totLambda;  //nm^-1
    Map<Element, double[]> ionisationProbs = new HashMap<Element, double[]>();
    double runningSumFraction = 0;  //should equal 1 by the end
    if (surrounding == false) {
      for (Element e : GOSinelastic.keySet()) {
        double[][] elementShells = GOSinelastic.get(e);
   //     double[] shellProbs = new double[4];
        double[] shellProbs = new double[9];
     //   for (int i = 0; i < elementShells.length; i++) {]
     //   for (int i = 0; i < 4; i++) {
        for (int i = 0; i < 9; i++) {
          double lambdaFraction = elementShells[i][3]/totInel;
          runningSumFraction += lambdaFraction;
          shellProbs[i] = runningSumFraction;
        }
        ionisationProbs.put(e, shellProbs);
      }
    }
    else {
      for (Element e : GOSinelasticSurrounding.keySet()) {
        double[][] elementShells = GOSinelasticSurrounding.get(e);
   //     double[] shellProbs = new double[4];
        double[] shellProbs = new double[9];
     //   for (int i = 0; i < elementShells.length; i++) {]
     //   for (int i = 0; i < 4; i++) {
        for (int i = 0; i < 9; i++) {
          double lambdaFraction = elementShells[i][3]/totInel;
          runningSumFraction += lambdaFraction;
          shellProbs[i] = runningSumFraction;
        }
        ionisationProbs.put(e, shellProbs);
      }
    }
    
    return ionisationProbs;
  }
  
  @Override
  public  Map<Element, double[][]> getGOSVariable(boolean surrounding){
    if (surrounding == false) {
      return GOSinelastic;
    }
    else {
      return GOSinelasticSurrounding;
    }
  }
  
  @Override
  public double[] getPlasmonVariable (boolean surrounding) {
    if (surrounding == false) {
      return cbInel;
    }
    else {
      return cbInelSurrounding;
    }
  }
  
  @Override
  public double returnAdjustment() {
    return sturnheimerAdjustment;
  }
  
  //inner shell cross sections I could sum into a sigma and then compare with the actual rigorous one and multiply this
  //up or down somehow to get actual number of ionisations. 
  
  
  //be careful with my recoil energies to take into account the binding energy of the shell.  PENELOPE does this strangely 
  //start with Q=W and then remember to test changing it later and seeing what happens 
  
}
