package se.raddo.raddose3D;

import java.util.List;

public class CoefCalcSmallMolecules extends CoefCalcCompute {
  /**
   * Right angle.
   */
  protected static final double RIGHT_ANGLE = 90;


  /**
   * Simple Constructor that does nothing.
   * Created for subclasses
   */
  public CoefCalcSmallMolecules(){

  }

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
   * @param smallMoleAtomNames symbols of each atom type per monomer
   * @param smallMoleAtomNums number of each atom type per monomer
   * @param heavySolutionConcNames heavy atom solvent element symbols
   * @param heavySolutionConcNums heavy atom solvent concentrations in mM.
   * @param solventFraction solvent fraction
   */
  public CoefCalcSmallMolecules(final Double cellA, final Double cellB,
      final Double cellC,
      final Double cellAlpha, final Double cellBeta, final Double cellGamma,
       int numMonomers,
      final List<String> smallMoleAtomNames,
      final List<Double> smallMoleAtomNums,
      final List<String> heavySolutionConcNames,
      final List<Double> heavySolutionConcNums,
      final Double solventFraction,
      final List<String> cryoSolutionMolecule,
      final List<Double> cryoSolutionConc,
      final String oilBased, final String calcSurrounding) {

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

    if (numMonomers == 0) {
      numMonomers = 1;
    }
    
    calculateAtomOccurrences(numMonomers,
        sf, smallMoleAtomNames, smallMoleAtomNums,
        heavySolutionConcNames, heavySolutionConcNums, cryoSolutionMolecule, cryoSolutionConc, oilBased, calcSurrounding);
    
    super.calculateDensity();  //Calculate density if where presentElements is filled in coefCalcCompute so this must be called
  }

  /**
   * Calculate the macromolecular mass (etc.) and add the appropriate numbers of
   * atom occurrences to the parser's atom array.
   *
   * @param monomers number of monomers
   * @param solventFraction solvent fraction
   * @param smallMoleAtomNames symbols of each atom type per monomer
   * @param smallMoleAtomNums number of each atom type per monomer
   * @param heavySolvConcNames heavy atom solvent element symbols
   * @param heavySolvConcNums heavy atom solvent concentrations in mM.
   */
  public void calculateAtomOccurrences(final int monomers,
      final double solventFraction,
      final List<String> smallMoleAtomNames,
      final List<Double> smallMoleAtomNums,
      final List<String> heavySolvConcNames,
      final List<Double> heavySolvConcNums,
      final List<String> cryoSolutionAtoms,
      final List<Double> cryoSolutionConcs,
      final String oilBased, String calcSurrounding) {

    // Start by dealing with the atoms in each small molecule
    // and adding these to the unit cell.    
    if (smallMoleAtomNames != null) {
      for (int i = 0; i < smallMoleAtomNames.size(); i++) {
        Element smallMoleAtom = getParser()
            .getElement(smallMoleAtomNames.get(i));

        // note: small molecule atoms are provided per monomer,
        // so multiply by number of monomers.
        incrementMacromolecularOccurrence(smallMoleAtom,
            smallMoleAtomNums.get(i)
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
      addCryoConcentrations(cryoSolutionAtoms, cryoSolutionConcs, oilBased);
      super.calculateCryoDensity();
    }
   
    
    this.setNumMonomers(monomers);

    boolean fillRestWithWater = false; //There are vacuums in small molecule crystals
    
  if (fillRestWithWater) {
    // If the solvent fraction has not been specified.
    double newSolventFraction = solventFraction;

    if (solventFraction <= 0) {
      newSolventFraction = calculateSolventFractionFromNums();
    }

    calculateSolventWater(newSolventFraction);
  }
  
  
  }
}
