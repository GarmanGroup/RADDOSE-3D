package se.raddo.raddose3D;

import java.util.List;

public class CoefCalcFromSequenceSAXS extends CoefCalcCompute {
  /**
   * Right angle.
   */
  protected static final double RIGHT_ANGLE = 90;
  
  /**
   * Default unit cell dimension
   */
  private static final double   UNIT_CELL_LENGTH = 1000;

  /**
   * Average molecular mass of an amino acid (daltons = grams/mole)
   */
  private static final double   AVG_RESIDUE_MASS = 110;

  /**
   * Conversion factor to convert Angstroms^3 to litres
   */
  private static final double   ANGSTROM_TO_LITRE_VOLUME_CONVERSION = 1e-27;

  public CoefCalcFromSequenceSAXS(Double cellA, Double cellB, Double cellC,
      Double cellAlpha, Double cellBeta, Double cellGamma, 
      List<String> heavyProteinAtomNames, List<Double> heavyProteinAtomNums, 
      List<String> heavySolutionConcNames, List<Double> heavySolutionConcNums, 
      Double solFrac, Double solventFraction, String sequenceFile) {
    
    /**
     * Create local variables for the unit cell parameters.
     * These don't have to be given in the input file for this module.
     * If these values are null then give suitable defaults.
     */
    Double a = cellA;
    Double b = cellB;
    Double c = cellC;
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
    if (a == null) {
      a = UNIT_CELL_LENGTH;
    }
    if (b == null) {
      b = UNIT_CELL_LENGTH;
    }
    if (c == null) {
      c = UNIT_CELL_LENGTH;
    }

    //Set local variable solvent fraction to minus 1
    Double sf = -1.0;

    //If actual solvent fraction is given then set the local variable
    //sf equal to the actual solvent fraction
    if (solventFraction != null) {
      sf = solventFraction;
    }

    //Calculate the Unit Cell Volume
    //This method exists in CoefCalcCompute Class
    double unitCellVolume = cellVolume(a, b, c, alpha, beta, gamma);

  }

}
