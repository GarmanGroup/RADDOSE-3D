package se.raddo.raddose3D;

import java.util.List;

public class CoefCalcSAXS extends CoefCalcFromParams {

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
   * @param numResidues number of amino acids
   * @param numRNA number of RNA residues
   * @param numDNA number of DNA residues
   * @param heavyProteinAtomNames heavy atom protein element symbols
   * @param heavyProteinAtomNums heavy atom protein occurrences
   * @param heavySolutionConcNames heavy atom solvent element symbols
   * @param heavySolutionConcNums heavy atom solvent concentrations in mM.
   * @param solventFraction solvent fraction
   */
  public CoefCalcSAXS(final Double cellA, final Double cellB,
      final Double cellC,
      final Double cellAlpha, final Double cellBeta, final Double cellGamma,
      final int numResidues, final int numRNA,
      final int numDNA,
      final List<String> heavyProteinAtomNames,
      final List<Double> heavyProteinAtomNums,
      final List<String> heavySolutionConcNames,
      final List<Double> heavySolutionConcNums,
      final Double solventFraction,
      final Double proteinConcentration) {

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
        heavySolutionConcNames, heavySolutionConcNums);
  }

}
