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
                    final Double cellA, final Double cellB, final Double cellC) {
    isEM = true;
 //   calculateSA(cellA, cellB);
    EMConc = proteinConc;
  //  EMThickness = cellC;
 //   double totVolume = (cellA * cellB * cellC) * 1E-27; //convert A^3 to dm^3
 //   EMMass = (proteinConc * totVolume) / 1000; //in Kg
    
    calculateAtomOccurrences(numMon, numRes, heavyProteinAtomNames, heavyProteinAtomNums);

    super.calculateDensity();
  }
  
  public void calculateAtomOccurrences(final int numMon, final int numRes, final List<String> heavyProteinAtomNames,
      final List<Double> heavyProteinAtomNums) {
    
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
    
    this.setNumMonomers(numMon);
    this.setNumAminoAcids(numRes);
    
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

  }
}
