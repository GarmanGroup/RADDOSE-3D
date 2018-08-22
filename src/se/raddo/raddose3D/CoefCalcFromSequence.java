package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CoefCalcFromSequence extends CoefCalcCompute{

  /**
   * Right angle.
   */
  protected static final double RIGHT_ANGLE = 90;
  
  /**
   * Total molecular weight of the molecule in the sequence file.
   */
  protected double totalMolecularWeight;
  
  /**
   * An identifier for the residue type that is being read in the current line
   * if the sequence file.
   */
  int residueType;
  
  /**
   * Simple Constructor that does nothing.
   * Created for subclasses
   */
  public CoefCalcFromSequence(){}
  
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
   * @param heavyProteinAtomNames heavy atom protein element symbols
   * @param heavyProteinAtomNums heavy atom protein occurrences
   * @param heavySolutionConcNames heavy atom solvent element symbols
   * @param heavySolutionConcNums heavy atom solvent concentrations in mM.
   * @param solventFraction solvent fraction
   * @param sequenceFile name of the sequence file for the molecule.
   */
  public CoefCalcFromSequence(Double cellA, Double cellB, Double cellC,
      Double cellAlpha, Double cellBeta, Double cellGamma, int numMonomers,
      List<String> heavyProteinAtomNames, List<Double> heavyProteinAtomNums,
      List<String> heavySolutionConcNames, List<Double> heavySolutionConcNums,
      Double solventFraction, String sequenceFile,
      final List<String> cryoSolutionMolecule,
      final List<Double> cryoSolutionConc, final String oilBased, final String calcSurrounding,
      final int numCarb,
      final List<String> oilElementNames, final List<Double> oilElementsNums, final double oilDensity) {
    
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
    
    if (numMonomers == 0) {
      System.out.println("*************** WARNING ***************");
      System.out.println("The number of monomers is zero. No protein in crystal!!!");
    }
    this.setNumMonomers(numMonomers);

    cellVolume(cellA, cellB, cellC, alpha, beta, gamma);

    calculateAtomOccurrences(numMonomers, sf, heavyProteinAtomNames, 
        heavyProteinAtomNums, heavySolutionConcNames, heavySolutionConcNums,
        sequenceFile, cryoSolutionMolecule, cryoSolutionConc, oilBased, calcSurrounding, numCarb,
        oilElementNames, oilElementsNums, oilDensity);
    
    
    multiplyAtoms(this.getNumMonomers());
    
    super.calculateDensity(); //Density was never being calculated
    
  }
  
  /**
   * Calculate the macromolecular mass (etc.) and add the appropriate numbers of
   * atom occurrences to the parser's atom array.
   *
   * @param monomers number of monomers
   * @param solventFraction solvent fraction
   * @param heavyProteinAtomNames heavy atom protein element symbols
   * @param heavyProteinAtomNums heavy atom protein occurrences
   * @param heavySolvConcNames heavy atom solvent element symbols
   * @param heavySolvConcNums heavy atom solvent concentrations in mM.
   * @param seqFile name of the sequence file
   */
  public void calculateAtomOccurrences(int monomers, Double solventFraction,
      List<String> heavyProteinAtomNames, List<Double> heavyProteinAtomNums,
      List<String> heavySolvConcNames, List<Double> heavySolvConcNums,
      String seqFile,
      final List<String> cryoSolutionMolecule,
      final List<Double> cryoSolutionConc, final String oilBased, String calcSurrounding,
      final int numCarb,
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
            heavyProteinAtomNums.get(i) );
     //           * monomers);   //Multiply all atoms by number of monomers later on
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
      addCryoConcentrations(cryoSolutionMolecule, cryoSolutionConc, oilBased, oilElementNames, oilElementsNums, oilDensity);
      super.calculateCryoDensity();
    }
    
    //Parse the given sequence file
    System.out.println("Parsing sequence file: " + seqFile);
    parseSequenceFile(seqFile);
    System.out.println("Residue occurrences determined from sequence file:");
    System.out.printf("Number of Amino Acids: %.0f%n" , this.getNumAminoAcids());
    if (this.getNumDNA() > 0){
      System.out.printf("Number of DNA Residues: %.0f%n", this.getNumDNA());
    }
    if (this.getNumRNA() > 0) {
      System.out.printf("Number of RNA Residues: %.0f%n", this.getNumRNA());
    }

    // If the solvent fraction has not been specified.
    double newSolventFraction = solventFraction;

    if (solventFraction <= 0) {
      newSolventFraction = calculateSolventFractionFromNums();
    }

    calculateSolventWater(newSolventFraction);
    
    addCarbs(numCarb);
  }
  
  public void addCarbs(final int numCarb) {
    //add in carbs 
    this.setNumCarb(numCarb);
    Element hydrogen = getParser().getElement("H");
    Element oxygen = getParser().getElement("O");
    Element carbon = getParser().getElement("C");
    
    // Carbohydrate atoms: for every residue
    // add 12 H + 6 C + 6 O 
    incrementMacromolecularOccurrence(carbon, CARBONS_PER_CARBOHYDRATE
        * getNumCarb());
    incrementMacromolecularOccurrence(oxygen, OXYGENS_PER_CARBOHYDRATE
        * getNumCarb());
    incrementMacromolecularOccurrence(hydrogen, HYDROGENS_PER_CARBOHYDRATE
        * getNumCarb());
  }
  
  /**
   * This method opens and parses the sequence file
   *
   * @param sequenceFile A string containing the name of the sequence file
   */
  public void parseSequenceFile(String sequenceFile) {
    String sequenceLine;
    //Try to read the file
    try {
      FileReader fr = new FileReader(sequenceFile);
      BufferedReader textReader = new BufferedReader(fr);
      //Try to read the contents of the file
      try {
        //Read each line of the file.
        while ((sequenceLine = textReader.readLine()) != null) {
          parseLine(sequenceLine);
        }
      } catch (IOException e) {
        System.out.println("Could not read sequence file.");
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      System.out.println("Could not read sequence file.");
      e.printStackTrace();
    }
  }

  /**
   * This method looks at the first character of the line to determine
   * whether the current line is a comment line or a sequence line.
   * 
   * @param line String containing the current line of the sequence file.
   */
  public void parseLine(String line) {
    //first check that the line is not empty
    if (line.trim().length() > 0) {
      String firstCharacter = line.substring(0, 1);
      if (firstCharacter.equals(">")) {
        parseCommentLine(line);
      } else {
        parseSequenceLine(line);
      }
    }
  }

  /**
   * Parses the comment line. Explicitly it searches for substrings in the
   * comment lines "ISDNA" and "ISRNA" to determine the residue type for the
   * subsequent sequence line.
   * 
   * @param line String containing the current line of the sequence file.
   */
  public void parseCommentLine(String line) {
    if (line.toUpperCase().contains("ISDNA")) {
      this.residueType = ResidueDatabase.TYPE_DNA;
    } else if (line.toUpperCase().contains("ISRNA")) {
      this.residueType = ResidueDatabase.TYPE_RNA;
    } else {
      this.residueType = ResidueDatabase.TYPE_PROTEIN;
    }

  }

  /**
   * Parses the sequence line. Explicitly it loops through every character in
   * the line to determine each residue in the sequence and the atomic
   * occurrences. It also sums the molecular weights of each residue
   * 
   * @param line String containing the current line of the sequence file.
   */
  public void parseSequenceLine(String line) {
    String resID;
    for (int i = 0; i < line.length(); i++) {
      resID = line.substring(i, i + 1);
      Residue residue = ResidueDatabase.getInstance().getResidue(resID,
          this.residueType);

      this.totalMolecularWeight = this.totalMolecularWeight
          + residue.getMolecularWeight();
      
      updateAtomicAndMacromolecularOccurrences(residue);
    }
  }
  
  /**
   * Update the macromolecular and atomic occurrences. Given a residue it will update
   * the number of residues of that type and will update the number of component
   * atoms present in that residue.
   * 
   * @param residue Amino acid, RNA or DNA residue object.
   */
  public void updateAtomicAndMacromolecularOccurrences(Residue residue) {
    
    if (residue == null) {
      System.out.println("Warning: could not identify the sequence in the file");
    }

    if (residue.getResidueType() == ResidueDatabase.TYPE_PROTEIN) {
      this.incrementNumAminoAcids(1);
    } else if (residue.getResidueType() == ResidueDatabase.TYPE_RNA) {
      this.incrementNumRNA(1);
    } else if (residue.getResidueType() == ResidueDatabase.TYPE_DNA) {
      this.incrementNumDNA(1);
    }

 // Atom preparation...
    Element hydrogen = this.getParser().getElement("H");
    Element oxygen = this.getParser().getElement("O");
    Element carbon = this.getParser().getElement("C");
    Element nitrogen = this.getParser().getElement("N");
    Element phosphorus = this.getParser().getElement("P");
    Element sulphurs = this.getParser().getElement("S");
    Element seleniums = this.getParser().getElement("SE");     
    
    setMacromolecularOccurrence(hydrogen, residue.getHydrogens()
        + getMacromolecularOccurrence(hydrogen));
    setMacromolecularOccurrence(oxygen, residue.getOxygens()
        + getMacromolecularOccurrence(oxygen));
    setMacromolecularOccurrence(carbon, residue.getCarbons()
        + getMacromolecularOccurrence(carbon));
    setMacromolecularOccurrence(nitrogen, residue.getNitrogens()
        + getMacromolecularOccurrence(nitrogen));
    setMacromolecularOccurrence(phosphorus, residue.getPhosphoruses()
        + getMacromolecularOccurrence(phosphorus));
    setMacromolecularOccurrence(sulphurs, residue.getSulphurs()
        + getMacromolecularOccurrence(sulphurs));
    setMacromolecularOccurrence(seleniums, residue.getSeleniums()
        + getMacromolecularOccurrence(seleniums));
    
  }
}
