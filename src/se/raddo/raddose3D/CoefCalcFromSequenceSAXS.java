package se.raddo.raddose3D;

import java.util.List;

public class CoefCalcFromSequenceSAXS extends CoefCalcFromSequence {
  /**
   * Right angle.
   */
  protected static final double RIGHT_ANGLE = 90;
  
  /**
   * Default unit cell dimension
   */
  private static final double   UNIT_CELL_LENGTH = 1000;

  /**
   * Conversion factor to convert Angstroms^3 to litres
   */
  private static final double   ANGSTROM_TO_LITRE_VOLUME_CONVERSION = 1e-27;

  public CoefCalcFromSequenceSAXS(Double cellA, Double cellB, Double cellC,
      Double cellAlpha, Double cellBeta, Double cellGamma, 
      List<String> heavyProteinAtomNames, List<Double> heavyProteinAtomNums, 
      List<String> heavySolutionConcNames, List<Double> heavySolutionConcNums, 
      Double solventFraction, Double proteinConc, String seqFile,
      final int numCarb) {
    
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
    
    //Parse the given sequence file
    System.out.println("Parsing sequence file: " + seqFile);
    parseSequenceFile(seqFile);
    System.out.println("Residue occurrences determined from sequence file:");
    System.out.printf("Number of Amino Acids: %.0f%n", this.getNumAminoAcids());
    if (this.getNumDNA() > 0) {
      System.out.printf("Number of DNA Residues: %.0f%n", this.getNumDNA());
    }
    if (this.getNumRNA() > 0) {
      System.out.printf("Number of RNA Residues: %.0f%n", this.getNumRNA());
    }
    System.out.printf("The total molecular weight is: %.2f g/mol%n",
        this.totalMolecularWeight);

    //Calculate the number of monomers
    int numMonomers = calculateNumMonomers(proteinConc, unitCellVolume);
    this.setNumMonomers(numMonomers);
    
    calculateHeavyAtomOccurrences(numMonomers, sf, heavyProteinAtomNames,
        heavyProteinAtomNums, heavySolutionConcNames, heavySolutionConcNums, numCarb);
    
    multiplyAtoms(this.getNumMonomers()); //this is multiplying by the monomers
    
    
    
    super.calculateDensity(); 
  }

  public void calculateHeavyAtomOccurrences(int monomers, Double solventFraction,
      List<String> heavyProteinAtomNames, List<Double> heavyProteinAtomNums,
      List<String> heavySolvConcNames, List<Double> heavySolvConcNums, final int numCarb) {

    // Start by dealing with heavy atom in the
    // protein and adding these to the unit cell.
    if (heavyProteinAtomNames != null) {
      for (int i = 0; i < heavyProteinAtomNames.size(); i++) {
        Element heavyAtom = getParser()
            .getElement(heavyProteinAtomNames.get(i));

        // note: heavy atoms are provided per monomer,
        // so multiply by number of monomers.
        incrementMacromolecularOccurrence(heavyAtom,
            heavyProteinAtomNums.get(i) );
  //              * monomers);  //Atoms multiplied by number of monomers later on
      }
    }

    // Combine concentrations of heavy atoms in the
    // solvent and add these to the unit cell.
    if (heavySolvConcNames != null) {
      addSolventConcentrations(heavySolvConcNames, heavySolvConcNums);
    }

    // If the solvent fraction has not been specified.
    double newSolventFraction = solventFraction;

    if (solventFraction <= 0) {
      newSolventFraction = calculateSolventFractionFromNums();
    }

    calculateSolventWater(newSolventFraction);
    
    //add in carbs 
    this.addCarbs(numCarb);
  }
  
  /**
   * Method that calculates the number of protein molecules in a given volume
   * in a solution for a SAXS experiment.
   *
   * @param numberOfResidues Number of residues per molecule unit (monomer)
   * @param proteinConc Concentration of the protein in the SAXS experiment
   * @param volume Given volume considered for calculation
   * @return Number of monomers of the molecule in the given volume
   */
  private int calculateNumMonomers(final double proteinConcentration, final double volumeAngstromsCubed) {

    //Calculate molarity of solution as concentration divided by the total
    //molecular mass.
    double molarity = proteinConcentration / (this.totalMolecularWeight);

    // Calculate volume in litres
    double volumeLitres = ANGSTROM_TO_LITRE_VOLUME_CONVERSION * volumeAngstromsCubed;

    //Calculate the number of monomers
    double numOfMon = Math.round(molarity * volumeLitres * AVOGADRO_NUM);

    //Check that the number of molecules is not smaller than 1.
    if (numOfMon < 1) {
      System.out.println("");
      System.out.println("*************** WARNING ***************");
      System.out.println("The number of monomers calculated to be in the unit cell"
          + " volume given is less than 1");
      System.out.println("You should manually increase the unit cell dimensions in the"
          + " input file.");
      System.out.println("");
      //Set number of monomers in the cell volume to 1
      numOfMon = 1;
    }

    //Cast the result to an integer
    int numOfMonomers = (int)(numOfMon);

    System.out.println("Calculated number of monomers in cell volume: " + numOfMonomers);

    //return the result i.e. the number of monomers in the given volume.
    return numOfMonomers;
  }

}
