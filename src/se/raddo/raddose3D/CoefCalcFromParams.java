/**
 *
 */
package se.raddo.raddose3D;

import java.util.List;

/**
 * @author magd3052
 */
public class CoefCalcFromParams extends CoefCalcCompute {
  /**
   * Right angle.
   */
  private static final double RIGHT_ANGLE = 90;

  /**
   * Compute results and put them in local variables absCoeff, attCoeff,
   * elasCoeff and density.
   * 
   * @param cellA cell dimension a
   * @param cellB cell dimension b
   * @param cellC cell dimension c
   * @param cellAlpha cell angle alpha
   * @param cellBeta cell angle beta
   * @param cellGamma cell angle gamma
   * @param numMonomers number of monomers
   * @param numResidues number of amino acids
   * @param numRNA number of RNA residues
   * @param numDNA number of DNA residues
   * @param heavyProteinAtomNames heavy atom protein element symbols
   * @param heavyProteinAtomNums heavy atom protein occurrences
   * @param heavySolutionConcNames heavy atom solvent element symbols
   * @param heavySolutionConcNums heavy atom solvent concentrations in mM.
   * @param solventFraction solvent fraction
   */
  public CoefCalcFromParams(final Double cellA, final Double cellB,
      final Double cellC,
      final Double cellAlpha, final Double cellBeta, final Double cellGamma,
      final int numMonomers, final int numResidues, final int numRNA,
      final int numDNA,
      final List<String> heavyProteinAtomNames,
      final List<Double> heavyProteinAtomNums,
      final List<String> heavySolutionConcNames,
      final List<Double> heavySolutionConcNums,
      final Double solventFraction) {

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

    this.calculateAtomOccurrences(numMonomers, numResidues, numRNA, numDNA,
        sf, heavyProteinAtomNames, heavyProteinAtomNums,
        heavySolutionConcNames, heavySolutionConcNums);

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
      final List<Double> heavySolvConcNums) {

    // Start by dealing with heavy atom in the
    // protein and adding these to the unit cell.

    for (int i = 0; i < heavyProteinAtomNames.size(); i++) {
      Element heavyAtom = getParser().getElement(heavyProteinAtomNames.get(i));

      // note: heavy atoms are provided per monomer,
      // so multiply by number of monomers.
      incrementMacromolecularOccurrence(heavyAtom, heavyProteinAtomNums.get(i)
          * monomers);
    }

    // Combine concentrations of heavy atoms in the
    // solvent and add these to the unit cell.

    addSolventConcentrations(heavySolvConcNames, heavySolvConcNums);

    this.setNumMonomers(monomers);
    this.setNumAminoAcids(numResidues);
    this.setNumRNA(numRNAresidues);
    this.setNumDNA(numDNAresidues);

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
        * numResidues
        * getNumMonomers());
    incrementMacromolecularOccurrence(nitrogen, NITROGENS_PER_AMINO_ACID
        * numResidues
        * getNumMonomers());
    incrementMacromolecularOccurrence(oxygen, OXYGENS_PER_AMINO_ACID
        * numResidues
        * getNumMonomers());
    incrementMacromolecularOccurrence(hydrogen, HYDROGENS_PER_AMINO_ACID
        * numResidues
        * getNumMonomers());

    // RNA atoms: for every NTP
    // add 11.25 H + 9.5 C + 3.75 N + 7 O + 1 P.

    incrementMacromolecularOccurrence(carbon, CARBONS_PER_RNA_NUCLEOTIDE
        * this.getNumRNA()
        * getNumMonomers());
    incrementMacromolecularOccurrence(nitrogen, NITROGENS_PER_RNA_NUCLEOTIDE
        * this.getNumRNA()
        * getNumMonomers());
    incrementMacromolecularOccurrence(oxygen, OXYGENS_PER_RNA_NUCLEOTIDE
        * this.getNumRNA()
        * getNumMonomers());
    incrementMacromolecularOccurrence(hydrogen, HYDROGENS_PER_RNA_NUCLEOTIDE
        * this.getNumRNA()
        * getNumMonomers());
    incrementMacromolecularOccurrence(phosphorus,
        PHOSPHORUSES_PER_RNA_NUCLEOTIDE
            * this.getNumRNA() * getNumMonomers());

    // DNA atoms: for every NTP
    // add 11.75 H + 9.75 C + 4 N + 6 O + 1 P.

    incrementMacromolecularOccurrence(carbon, CARBONS_PER_DNA_NUCLEOTIDE
        * getNumDNA()
        * getNumMonomers());
    incrementMacromolecularOccurrence(nitrogen, NITROGENS_PER_DNA_NUCLEOTIDE
        * getNumDNA()
        * getNumMonomers());
    incrementMacromolecularOccurrence(oxygen, OXYGENS_PER_DNA_NUCLEOTIDE
        * getNumDNA()
        * getNumMonomers());
    incrementMacromolecularOccurrence(hydrogen, HYDROGENS_PER_DNA_NUCLEOTIDE
        * getNumDNA()
        * getNumMonomers());
    incrementMacromolecularOccurrence(phosphorus,
        PHOSPHORUSES_PER_DNA_NUCLEOTIDE
            * getNumDNA() * getNumMonomers());

  }
}
