package se.raddo.raddose3D;

import java.util.List;

public class CoefCalcEM extends CoefCalcCompute{
  
/**
 * Constructor
 * 
 * @param numMon
 * @param numRes
 * @param heavyProteinAtomNames
 * @param heavyProteinAtomNums
 * @param proteinConc
 * @param molecularWeight
 */
  public CoefCalcEM(final int numMon, final int numRes, final List<String> heavyProteinAtomNames,
                    final List<Double> heavyProteinAtomNums, final Double proteinConc,
                    final Double molecularWeight, 
                    final Double cellA, final Double cellB, final Double cellC,
                    List<String> heavySolutionConcNames, List<Double> heavySolutionConcNums) {
    isEM = true;
 //   calculateSA(cellA, cellB);
    EMConc = proteinConc;
  //  EMThickness = cellC;
 //   double totVolume = (cellA * cellB * cellC) * 1E-27; //convert A^3 to dm^3
 //   EMMass = (proteinConc * totVolume) / 1000; //in Kg
    
    calculateAtomOccurrences(numMon, numRes, heavyProteinAtomNames, heavyProteinAtomNums, 
                             heavySolutionConcNames, heavySolutionConcNums);

    ///change
  //  super.calculateDensity(); //going to need a calculate density EM for exposed volume but keep now as adds to present elements
  }
  
  public void calculateAtomOccurrences(final int numMon, final int numRes, final List<String> heavyProteinAtomNames,
                                       final List<Double> heavyProteinAtomNums, List<String> heavySolvConcNames, 
                                       List<Double> heavySolvConcNums) {
    
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
                * numMon);
      }
    }
    
    // Combine concentrations of heavy atoms in the
    // solvent and add these to the unit cell.
    if (heavySolvConcNames != null) {
      addSolventConcentrations(heavySolvConcNames, heavySolvConcNums);
    }
    
    
    this.setNumMonomers(numMon);
    this.setNumAminoAcids(numRes);
  //  this.setNumRNA(numRNAresidues);
   // this.setNumDNA(numDNAresidues);
    
    // If the solvent fraction has not been specified.
  //  double newSolventFraction = solventFraction;
  //  double newSolventFraction;
    
  //  if (solventFraction <= 0) {
  //    newSolventFraction = calculateSolventFractionEM();
  //  }

      //To test that this is the same as I did 
 //     newSolventFraction = 1;
      
      //change
  //  calculateSolventWater(newSolventFraction); //Going to have to be calculateEM for exposed volume
    
    
    Element hydrogen = getParser().getElement("H");
    Element oxygen = getParser().getElement("O");
    Element carbon = getParser().getElement("C");
    Element nitrogen = getParser().getElement("N");
    Element phosphorus = getParser().getElement("P");

    // Protein atoms: for every amino acid
    // add 5C + 1.35 N + 1.5 O + 8H
    incrementMacromolecularOccurrence(carbon, CARBONS_PER_AMINO_ACID
        * numRes * getNumMonomers());
    incrementMacromolecularOccurrence(nitrogen, NITROGENS_PER_AMINO_ACID
        * numRes * getNumMonomers());
    incrementMacromolecularOccurrence(oxygen, OXYGENS_PER_AMINO_ACID
        * numRes * getNumMonomers());
    incrementMacromolecularOccurrence(hydrogen, HYDROGENS_PER_AMINO_ACID
        * numRes * getNumMonomers());
    
    /*
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
*/
  }
}
