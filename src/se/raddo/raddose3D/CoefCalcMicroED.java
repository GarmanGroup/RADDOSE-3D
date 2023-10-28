package se.raddo.raddose3D;

import java.util.List;

public class CoefCalcMicroED extends CoefCalcCompute{
  
  /**
   * Right angle.
   */
  protected static final double RIGHT_ANGLE = 90;
  
 /**
  * 
  * @param cellA
  * @param cellB
  * @param cellC
  * @param cellAlpha
  * @param cellBeta
  * @param cellGamma
  * @param numMonomers
  * @param numResidues
  * @param numRNA
  * @param numDNA
  * @param heavyProteinAtomNames
  * @param heavyProteinAtomNums
  * @param heavySolutionConcNames
  * @param heavySolutionConcNums
  * @param cryoSolutionMolecule
  * @param cryoSolutionConc
  * @param solventFraction
  * @param oilBased
  * @param calcSurrounding
  * @param numCarb
  * @param oilElementNames
  * @param oilElementsNums
  * @param oilDensity
  */
  public CoefCalcMicroED(final Double cellA, final Double cellB,
      final Double cellC,
      final Double cellAlpha, final Double cellBeta, final Double cellGamma,
      final int numMonomers, final int numResidues, final int numRNA,
      final int numDNA, 
      final List<String> heavyProteinAtomNames,
      final List<Double> heavyProteinAtomNums,
      final List<String> heavySolutionConcNames,
      final List<Double> heavySolutionConcNums,
      final List<String> cryoSolutionMolecule,
      final List<Double> cryoSolutionConc,
      final Double solventFraction, final String oilBased, final String calcSurrounding,
      final int numCarb,
      final List<String> oilElementNames, final List<Double> oilElementsNums, final double oilDensity)  {
    
    
    isEM = true;
    
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

    Double sf = -1.0;

    if (solventFraction != null) {
      sf = solventFraction;
    }

    cellVolume(cellA, cellB, cellC, alpha, beta, gamma);

    calculateAtomOccurrences(numMonomers, numResidues, numRNA, numDNA,
        sf, heavyProteinAtomNames, heavyProteinAtomNums,
        heavySolutionConcNames, heavySolutionConcNums, cryoSolutionMolecule, cryoSolutionConc, oilBased, calcSurrounding, numCarb,
        oilElementNames, oilElementsNums, oilDensity);
    
    super.calculateDensity();
  }
  
  /**
   * Calculate the macromolecular mass (etc.) and add the appropriate numbers of
   * atom occurrences to the parser's atom array.
   *
   * @param monomers number of monomers
   * @param numResidues number of amino acid residues
   * @param numRNAresidues number of RNA residues
   * @param numDNAresidues number of DNA residues
   * @param solventFraction solvent fraction
   * @param heavyProteinAtomNames heavy atom protein element symbols
   * @param heavyProteinAtomNums heavy atom protein occurrences
   * @param heavySolvConcNames heavy atom solvent element symbols
   * @param heavySolvConcNums heavy atom solvent concentrations in mM.
   */
  public void calculateAtomOccurrences(final int monomers,
      final int numResidues,
      final int numRNAresidues, final int numDNAresidues,
      final double solventFraction,
      final List<String> heavyProteinAtomNames,
      final List<Double> heavyProteinAtomNums,
      final List<String> heavySolvConcNames,
      final List<Double> heavySolvConcNums,
      final List<String> cryoSolutionAtoms,
      final List<Double> cryoSolutionConcs, final String oilBased,  String calcSurrounding,
      final int numCarbResidues,
      final List<String> oilElementNames, final List<Double> oilElementsNums, final double oilDensity) {

    // Start by dealing with heavy atom in the
    // protein and adding these to the unit cell.
    
    if (heavyProteinAtomNames != null) {
      for (int i = 0; i < heavyProteinAtomNames.size(); i++) {
        Element heavyAtom = getParser()
            .getElement(heavyProteinAtomNames.get(i));

        // note: heavy atoms are provided per monomer,
        // so multiply by number of monomers.
        incrementMacromolecularOccurrence(heavyAtom,
            heavyProteinAtomNums.get(i)
                * monomers);
        
        ElementEM heavyAtomEM = getParserEM()
            .getElement(heavyProteinAtomNames.get(i));
        

        // note: heavy atoms are provided per monomer,
        // so multiply by number of monomers.
        incrementMacromolecularOccurrenceEM(heavyAtomEM,
            heavyProteinAtomNums.get(i)
                * monomers);
      }
    }

    // Combine concentrations of heavy atoms in the
    // solvent and add these to the unit cell.
    if (heavySolvConcNames != null) {
      addSolventConcentrations(heavySolvConcNames, heavySolvConcNums);
    }
    
    //check whether a surrounding should be calculated 
    if (calcSurrounding != null) {
      calcSurrounding = calcSurrounding.toUpperCase();
    }
    boolean surrounding = ("TRUE".equals(calcSurrounding));

    if (surrounding == true) { 
      //populate the 'cryo unit cell' with these atoms 
      addCryoConcentrations(cryoSolutionAtoms, cryoSolutionConcs, oilBased, oilElementNames, oilElementsNums, oilDensity);
      super.calculateCryoDensity();
    }
    
    this.setNumMonomers(monomers);
    this.setNumAminoAcids(numResidues);
    this.setNumRNA(numRNAresidues);
    this.setNumDNA(numDNAresidues);
    this.setNumCarb(numCarbResidues);

    // If the solvent fraction has not been specified.
    double newSolventFraction = solventFraction;

    if (solventFraction <= 0) {
      newSolventFraction = calculateSolventFractionFromNums();
    }

    calculateSolventWater(newSolventFraction);

    // Atom preparation...

    Element hydrogen = getParser().getElement("H");
    Element oxygen = getParser().getElement("O");
    Element carbon = getParser().getElement("C");
    Element nitrogen = getParser().getElement("N");
    Element phosphorus = getParser().getElement("P");

    // Protein atoms: for every amino acid
    // add 5C + 1.35 N + 1.5 O + 8H
    incrementMacromolecularOccurrence(carbon, CARBONS_PER_AMINO_ACID
        * numResidues * getNumMonomers());
    incrementMacromolecularOccurrence(nitrogen, NITROGENS_PER_AMINO_ACID
        * numResidues * getNumMonomers());
    incrementMacromolecularOccurrence(oxygen, OXYGENS_PER_AMINO_ACID
        * numResidues * getNumMonomers());
    incrementMacromolecularOccurrence(hydrogen, HYDROGENS_PER_AMINO_ACID
        * numResidues * getNumMonomers());

    // RNA atoms: for every NTP
    // add 11.25 H + 9.5 C + 3.75 N + 7 O + 1 P.
    incrementMacromolecularOccurrence(carbon, CARBONS_PER_RNA_NUCLEOTIDE
        * getNumRNA() * getNumMonomers());
    incrementMacromolecularOccurrence(nitrogen, NITROGENS_PER_RNA_NUCLEOTIDE
        * getNumRNA() * getNumMonomers());
    incrementMacromolecularOccurrence(oxygen, OXYGENS_PER_RNA_NUCLEOTIDE
        * getNumRNA() * getNumMonomers());
    incrementMacromolecularOccurrence(hydrogen, HYDROGENS_PER_RNA_NUCLEOTIDE
        * getNumRNA() * getNumMonomers());
    incrementMacromolecularOccurrence(phosphorus,
        PHOSPHORI_PER_RNA_NUCLEOTIDE
            * this.getNumRNA() * getNumMonomers());

    // DNA atoms: for every NTP
    // add 11.75 H + 9.75 C + 4 N + 6 O + 1 P.
    incrementMacromolecularOccurrence(carbon, CARBONS_PER_DNA_NUCLEOTIDE
        * getNumDNA() * getNumMonomers());
    incrementMacromolecularOccurrence(nitrogen, NITROGENS_PER_DNA_NUCLEOTIDE
        * getNumDNA() * getNumMonomers());
    incrementMacromolecularOccurrence(oxygen, OXYGENS_PER_DNA_NUCLEOTIDE
        * getNumDNA() * getNumMonomers());
    incrementMacromolecularOccurrence(hydrogen, HYDROGENS_PER_DNA_NUCLEOTIDE
        * getNumDNA() * getNumMonomers());
    incrementMacromolecularOccurrence(phosphorus,
        PHOSPHORI_PER_DNA_NUCLEOTIDE
            * getNumDNA() * getNumMonomers());
    
    // Carbohydrate atoms: for every residue
    // add 11 H + 6 C + 5 O 
    incrementMacromolecularOccurrence(carbon, CARBONS_PER_CARBOHYDRATE
        * getNumCarb() * getNumMonomers());
    incrementMacromolecularOccurrence(oxygen, OXYGENS_PER_CARBOHYDRATE
        * getNumCarb() * getNumMonomers());
    incrementMacromolecularOccurrence(hydrogen, HYDROGENS_PER_CARBOHYDRATE
        * getNumCarb() * getNumMonomers());
  }
}