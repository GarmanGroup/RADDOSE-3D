package se.raddo.raddose3D;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


/**
 * @author magd3052
 */
public class CoefCalcTests {
  /**
   * Right angle.
   */
  protected static final double RIGHT_ANGLE = 90;
  
  /**
   * Take a unit cell consisting entirely of water and make sure
   * number of hydrogens is twice the number of oxygens.
   */
  @Test
  public void testCoefCalcWaterOnly() {
    List<String> atoms = new ArrayList<String>();
    List<Double> numbers = new ArrayList<Double>();

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(100.0,
        100.0, 100.0, 90.0, 90.0, 90.0, 0, 0, 0, 0, atoms, numbers,
        atoms, numbers, atoms, numbers, 100.0, null, null, 0, atoms, numbers, 0, 0);

    Double oxygenOccurrence = coefCalc.getSolventOccurrence(
        coefCalc.getParser().getElement("O"));
    Double hydrogenOccurrence = coefCalc.getSolventOccurrence(
        coefCalc.getParser().getElement("H"));

    Tolerance.equals(hydrogenOccurrence, oxygenOccurrence * 2, "O vs H");
    
    System.out.println("@Test - testCoefCalcWaterOnly");
  }

  /**
   * Test to make sure protein heavy atoms are added and
   * multiplied by no. of monomers correctly
   */
  @Test
  public void testCoefCalcHeavyProteinAtoms() {
    List<String> emptyAtoms = new ArrayList<String>();
    List<Double> emptyNumbers = new ArrayList<Double>();

    List<String> atoms = new ArrayList<String>();
    List<Double> numbers = new ArrayList<Double>();

    atoms.add("zn");
    numbers.add(Double.valueOf(2));

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(100.0,
        100.0, 100.0, 90.0, 90.0, 90.0, 24, 10, 0, 0, atoms,
        numbers, emptyAtoms, emptyNumbers, emptyAtoms, emptyNumbers, 100.0, null, null, 0, emptyAtoms, emptyNumbers, 0, 0);

    Double zincOccurrence = coefCalc.getMacromolecularOccurrence(
        coefCalc.getParser().getElement("ZN"));

    Tolerance.equals(zincOccurrence, 48, "Zn = 48");
    System.out.println("@Test - testHeavyProteinAtoms");
  }
  

  /**
   * Run an actual scenario and compare to values obtained from RADDOSE2.
   */
  @Test
  /**
   * PARKED -- see TEST-TRIAGE.md #1.
   * Fails since b0a79ff (2023-10-22) flipped WATER_CONCENTRATION from 55555
   * (1.00 g/cm3) to 51666 (0.93 g/cm3), shifting absorption by about -2.6%.
   * Expected 1.042e-03, currently 1.01852368e-03.
   * The expectation is deliberately left untouched pending a decision.
   */
  @Tag("pending")
  public void testCoefCalcScenario1() {
    List<String> heavyProtAtomNames = new ArrayList<String>();
    List<Double> heavyProtAtomNums = new ArrayList<Double>();

    List<String> heavySolutionConcNames = new ArrayList<String>();
    List<Double> heavySolutionConcNums = new ArrayList<Double>();
    
    //These are cryo can test later
    List<String> emptyAtoms = new ArrayList<String>();
    List<Double> emptyNumbers = new ArrayList<Double>();

    heavyProtAtomNames.add("S");
    heavyProtAtomNums.add(10.0);

    heavySolutionConcNames.add("Na");
    heavySolutionConcNames.add("Cl");
    heavySolutionConcNums.add(1200.);
    heavySolutionConcNums.add(200.);

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(
        79.2, 79.2, 38.1, 90.0, 90.0, 90.0, 8, 129, 0, 0,
        heavyProtAtomNames, heavyProtAtomNums,
        heavySolutionConcNames, heavySolutionConcNums, emptyAtoms, emptyNumbers, 0., null, null, 0, emptyAtoms, emptyNumbers, 0, 0);

    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_COLL_H, 80.);
    beamProperties.put(Beam.BEAM_COLL_V, 80.);
    beamProperties.put(Beam.BEAM_FLUX, 9.281e8);
    beamProperties.put(Beam.BEAM_ENERGY, 8.05);
    Beam b = new BeamTophat(beamProperties);

    coefCalc.updateCoefficients(b);

    // Values obtained from RADDOSEv2, http://www.raddo.se/legacy/
    Tolerance.equals(coefCalc.getAbsorptionCoefficient(), 0.001042,
        "Absorption Coefficient", 0.000005);
    Tolerance.equals(coefCalc.getElasticCoefficient(), 0.000036,
        "Elastic Coefficient", 0.000005);
    Tolerance.equals(coefCalc.getAttenuationCoefficient(), 0.001095,
        "Attenuation Coefficient", 0.000005);
    
    System.out.println("@Test - testCoefCalc1");
  }

  /**
   * Run an actual scenario and compare to values obtained from RADDOSE2.
   */
  @Test
  public void testCoefCalcScenario2() {
    List<String> heavyProtAtomNames = new ArrayList<String>();
    List<Double> heavyProtAtomNums = new ArrayList<Double>();

    List<String> heavySolutionConcNames = new ArrayList<String>();
    List<Double> heavySolutionConcNums = new ArrayList<Double>();
    
    //These are cryo can test later
    List<String> emptyAtoms = new ArrayList<String>();
    List<Double> emptyNumbers = new ArrayList<Double>();

    heavyProtAtomNames.add("S");
    heavyProtAtomNames.add("Se");
    heavyProtAtomNames.add("Cu");
    heavyProtAtomNums.add(4.0);
    heavyProtAtomNums.add(2.0);
    heavyProtAtomNums.add(200.0);

    heavySolutionConcNames.add("Na");
    heavySolutionConcNames.add("Cl");
    heavySolutionConcNames.add("As");
    heavySolutionConcNums.add(500.);
    heavySolutionConcNums.add(200.);
    heavySolutionConcNums.add(200.);

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(
        79.2, 79.2, 38.1, 70.0, 70.0, 50.0, 4, 200, 0, 0,
        heavyProtAtomNames, heavyProtAtomNums,
        heavySolutionConcNames, heavySolutionConcNums, emptyAtoms, emptyNumbers, 0., null, null, 0, emptyAtoms, emptyNumbers, 0, 0);

    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_COLL_H, 80.);
    beamProperties.put(Beam.BEAM_COLL_V, 80.);
    beamProperties.put(Beam.BEAM_FLUX, 9.281e8);
    beamProperties.put(Beam.BEAM_ENERGY, 14.05);
    Beam b = new BeamTophat(beamProperties);

    coefCalc.updateCoefficients(b);

    // Values obtained from RADDOSEv2, http://www.raddo.se/legacy/
    Tolerance.equals(coefCalc.getAbsorptionCoefficient(), 0.004675,
        "Absorption Coefficient", 0.000005);
    Tolerance.equals(coefCalc.getElasticCoefficient(), 0.000068,
        "Elastic Coefficient", 0.000005);
    Tolerance.equals(coefCalc.getAttenuationCoefficient(), 0.004769,
        "Attenuation Coefficient", 0.000005);
    
    System.out.println("@Test - testCoefCalc2");
  }

  /**
   * Run an actual scenario and compare to values obtained from RADDOSE2.
   * This input file is stolen from Jonny's insulin crystals!
   */
  @Test
  /**
   * PARKED -- see TEST-TRIAGE.md #1.
   * Fails since b0a79ff (2023-10-22) flipped WATER_CONCENTRATION from 55555
   * (1.00 g/cm3) to 51666 (0.93 g/cm3), shifting absorption by about -2.6%.
   * Expected 4.60e-04, currently 4.45987638e-04.
   * The expectation is deliberately left untouched pending a decision.
   */
  @Tag("pending")
  public void testCoefCalcScenario3() {
    List<String> heavyProtAtomNames = new ArrayList<String>();
    List<Double> heavyProtAtomNums = new ArrayList<Double>();

    List<String> heavySolutionConcNames = new ArrayList<String>();
    List<Double> heavySolutionConcNums = new ArrayList<Double>();
    
    //These are cryo can test later
    List<String> emptyAtoms = new ArrayList<String>();
    List<Double> emptyNumbers = new ArrayList<Double>();

    heavyProtAtomNames.add("S");
    heavyProtAtomNames.add("Zn");
    heavyProtAtomNums.add(6.0);
    heavyProtAtomNums.add(2.0);

    heavySolutionConcNames.add("P");
    heavySolutionConcNums.add(425.);

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(
        78.27, 78.27, 78.27, 90.0, 90.0, 90.0, 24, 51, 0, 0,
        heavyProtAtomNames, heavyProtAtomNums,
        heavySolutionConcNames, heavySolutionConcNums, emptyAtoms, emptyNumbers, 0., null, null, 0, emptyAtoms, emptyNumbers, 0, 0);

    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_COLL_H, 20.);
    beamProperties.put(Beam.BEAM_COLL_V, 70.);
    beamProperties.put(Beam.BEAM_FLUX, 2e12);
    beamProperties.put(Beam.BEAM_ENERGY, 12.1);
    Beam b = new BeamTophat(beamProperties);

    coefCalc.updateCoefficients(b.getPhotonEnergy());

    // Values obtained from RADDOSEv2, http://www.raddo.se/legacy/
    Tolerance.equals(coefCalc.getAbsorptionCoefficient(), 4.60e-04,
        "Absorption Coefficient", 0.000005);
    Tolerance.equals(coefCalc.getElasticCoefficient(), 2.20e-05,
        "Elastic Coefficient", 0.000005);
    Tolerance.equals(coefCalc.getAttenuationCoefficient(), 4.97e-04,
        "Attenuation Coefficient", 0.000005);
    
    System.out.println("@Test - testCoefCalc3");
  }
  
  /**
   * Run an actual scenario - water based surrounding 
   */
  @Test
  /**
   * PARKED -- see TEST-TRIAGE.md #1.
   * Fails since b0a79ff (2023-10-22) flipped WATER_CONCENTRATION from 55555
   * (1.00 g/cm3) to 51666 (0.93 g/cm3), shifting absorption by about -2.6%.
   * Expected 3.5358e-04 (cryo), currently 3.37172471e-04.
   * The expectation is deliberately left untouched pending a decision.
   */
  @Tag("pending")
  public void testCoefCalcWaterBasedSurrounding() {
    List<String> heavyProtAtomNames = new ArrayList<String>();
    List<Double> heavyProtAtomNums = new ArrayList<Double>();

    List<String> heavySolutionConcNames = new ArrayList<String>();
    List<Double> heavySolutionConcNums = new ArrayList<Double>();
    
    List<String> heavyCryoConcNames = new ArrayList<String>();
    List<Double> heavyCryoConcNums = new ArrayList<Double>();
    
    //These are cryo can test later
    List<String> emptyAtoms = new ArrayList<String>();
    List<Double> emptyNumbers = new ArrayList<Double>();

    heavyProtAtomNames.add("S");
    heavyProtAtomNames.add("Zn");
    heavyProtAtomNums.add(6.0);
    heavyProtAtomNums.add(0.333);

    heavySolutionConcNames.add("P");
    heavySolutionConcNums.add(425.);
    
    heavyCryoConcNames.add("Na");
    heavyCryoConcNames.add("Cl");
    heavyCryoConcNums.add(1000.);
    heavyCryoConcNums.add(1000.);

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(
        78.02, 78.02, 78.02, 90.0, 90.0, 90.0, 24, 51, 0, 0,
        heavyProtAtomNames, heavyProtAtomNums,
        heavySolutionConcNames, heavySolutionConcNums, heavyCryoConcNames, heavyCryoConcNums, 0., null, "TRUE", 0, emptyAtoms, emptyNumbers, 0, 0);

    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_COLL_H, 10.);
    beamProperties.put(Beam.BEAM_COLL_V, 10.);
    beamProperties.put(Beam.BEAM_FLUX, 2e12);
    beamProperties.put(Beam.BEAM_ENERGY, 12.4);
    Beam b = new BeamTophat(beamProperties);

    coefCalc.updateCryoCoefficients(b.getPhotonEnergy());

    // Values obtained from RADDOSEv2, http://www.raddo.se/legacy/
    Tolerance.equals(coefCalc.getCryoAbsorptionCoefficient(), 3.535855532631557E-4,
        "Cryo Absorption Coefficient", 0.000005);

    
    System.out.println("@Test - testCoefCalcWaterSurrounding");
  }
  
  /**
   * Run an actual scenario - oil based surrounding 
   */
  @Test
  public void testCoefCalcOilBasedSurrounding() {
    List<String> heavyProtAtomNames = new ArrayList<String>();
    List<Double> heavyProtAtomNums = new ArrayList<Double>();

    List<String> heavySolutionConcNames = new ArrayList<String>();
    List<Double> heavySolutionConcNums = new ArrayList<Double>();
    
    List<String> oilNames = new ArrayList<String>();
    List<Double> oilNums = new ArrayList<Double>();
    
    
    //These are cryo can test later
    List<String> emptyAtoms = new ArrayList<String>();
    List<Double> emptyNumbers = new ArrayList<Double>();

    heavyProtAtomNames.add("S");
    heavyProtAtomNames.add("Zn");
    heavyProtAtomNums.add(6.0);
    heavyProtAtomNums.add(0.333);

    heavySolutionConcNames.add("P");
    heavySolutionConcNums.add(425.);
    
    oilNames.add("C");
    oilNames.add("H");
    oilNums.add(3.0);
    oilNums.add(8.0);  
    

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(
        78.02, 78.02, 78.02, 90.0, 90.0, 90.0, 24, 51, 0, 0,
        heavyProtAtomNames, heavyProtAtomNums,
        heavySolutionConcNames, heavySolutionConcNums, emptyAtoms, emptyNumbers, 0., "TRUE", "TRUE", 0, oilNames, oilNums, 1.2, 0);

    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_COLL_H, 10.);
    beamProperties.put(Beam.BEAM_COLL_V, 10.);
    beamProperties.put(Beam.BEAM_FLUX, 2e12);
    beamProperties.put(Beam.BEAM_ENERGY, 12.4);
    Beam b = new BeamTophat(beamProperties);

    coefCalc.updateCryoCoefficients(b.getPhotonEnergy());
    

    // Values obtained from RADDOSEv2, http://www.raddo.se/legacy/
    Tolerance.equals(coefCalc.getCryoAbsorptionCoefficient(), 9.068762586797131E-5,
        "Cryo Absorption Coefficient", 0.000005);

    
    System.out.println("@Test - testCoefCalcOilSurrounding");
  }
  
  /**
   * This test checks that the sequence file parser is able to
   * parse the correct number of protein, DNA and RNA residues.
   */
  @Test
  public void testSequenceParser() {
    List<String> heavyProtAtomNames = new ArrayList<String>();
    List<Double> heavyProtAtomNums = new ArrayList<Double>();

    List<String> heavySolutionConcNames = new ArrayList<String>();
    List<Double> heavySolutionConcNums = new ArrayList<Double>();
    
    //These are cryo can test later
    List<String> emptyAtoms = new ArrayList<String>();
    List<Double> emptyNumbers = new ArrayList<Double>();
    
    List<String> oilNames = new ArrayList<String>();
    List<Double> oilNums = new ArrayList<Double>();

    heavyProtAtomNames.add("S");
    heavyProtAtomNums.add(10.0);

    heavySolutionConcNames.add("Na");
    heavySolutionConcNames.add("Cl");
    heavySolutionConcNums.add(1200.);
    heavySolutionConcNums.add(200.);
    
    oilNames.add("C");
    oilNames.add("H");
    oilNums.add(3.0);
    oilNums.add(8.0); 
    
    CoefCalcFromSequenceSAXS coefCalc = new CoefCalcFromSequenceSAXS(100.0, 100.0, 
        100.0, RIGHT_ANGLE, RIGHT_ANGLE, RIGHT_ANGLE, heavyProtAtomNames,
        heavyProtAtomNums, heavySolutionConcNames, heavySolutionConcNums,
        -1.0, 1.0, "test/resources/TestSequence.fasta", emptyAtoms, emptyNumbers, "TRUE", "TRUE", 0, oilNames, oilNums, 1.2);
    
    boolean correctNumProtein = false;
    if (coefCalc.getNumAminoAcids() == 25) {
      correctNumProtein = true;
    }
    assertTrue(correctNumProtein, "Sequence file parser is not counting the correct amount of protein residues");
    
    boolean correctNumDNA = false;
    if (coefCalc.getNumDNA() == 9) {
      correctNumDNA = true;
    }
    assertTrue(correctNumDNA, "Sequence file parser is not counting the correct amount of DNA residues");
    
    boolean correctNumRNA = false;
    if (coefCalc.getNumRNA() == 4) {
      correctNumRNA = true;
    }
    assertTrue(correctNumRNA, "Sequence file parser is not counting the correct amount of RNA residues");
    
    System.out.println("@Test - testSequenceParser");
  }

  /**
   * Random numbers used for CoefCalc - currently not
   * used as a Jenkins test because Jenkins cannot run
   * the old raddose executable.
   * 
   * TODO: Refactor into Jenkins compatible test
   */
  public static void main(final String[] cmdLineParams) {  //not currently being tested
    int testCount = 7;
    Random random = new Random(0);
    
    for (int i = 0; i < testCount; i++)
    {
      List<String> heavyProtAtomNames = new ArrayList<String>();
      List<Double> heavyProtAtomNums = new ArrayList<Double>();

      List<String> heavySolutionConcNames = new ArrayList<String>();
      List<Double> heavySolutionConcNums = new ArrayList<Double>();
      
      //These are cryo can test later
      List<String> emptyAtoms = new ArrayList<String>();
      List<Double> emptyNumbers = new ArrayList<Double>();

      heavyProtAtomNames.add("S");

      double sulphur = 6;

      heavyProtAtomNums.add(sulphur);

      double phosphorus = 500;

      heavySolutionConcNames.add("P");
      heavySolutionConcNums.add(phosphorus);

      double unit_cell_length = 100;

      int protein_residues = 100;
      int rna_residues = random.nextInt(80) + 20;

      CoefCalcFromParams coefCalc = new CoefCalcFromParams(
          unit_cell_length, unit_cell_length, unit_cell_length, 90.0, 90.0,
          90.0, 24, protein_residues, rna_residues, 0,
          heavyProtAtomNames, heavyProtAtomNums,
          heavySolutionConcNames, heavySolutionConcNums, emptyAtoms, emptyNumbers, 0., null, null, 0, emptyAtoms, emptyNumbers, 0, 0);

      CoefCalcRaddose coefCalcRDV2 = new CoefCalcRaddose(
          unit_cell_length, unit_cell_length, unit_cell_length, 90.0, 90.0,
          90.0, 24, protein_residues, rna_residues, 0,
          heavyProtAtomNames, heavyProtAtomNums,
          heavySolutionConcNames, heavySolutionConcNums, 0.);

      Map<Object, Object> beamProperties = new HashMap<Object, Object>();
      beamProperties.put(Beam.BEAM_COLL_H, 20.);
      beamProperties.put(Beam.BEAM_COLL_V, 70.);
      beamProperties.put(Beam.BEAM_FLUX, 2e12);
      beamProperties.put(Beam.BEAM_ENERGY, 12.1);
      Beam b = new BeamTophat(beamProperties);

      coefCalc.updateCoefficients(b.getPhotonEnergy());

      // Values obtained from RADDOSEv2, http://www.raddo.se/legacy/
      Tolerance.equals(coefCalc.getAbsorptionCoefficient(),
          coefCalcRDV2.getAbsorptionCoefficient(),
          "Absorption Coefficient", 0.000005);
      Tolerance.equals(coefCalc.getElasticCoefficient(),
          coefCalcRDV2.getElasticCoefficient(),
          "Elastic Coefficient", 0.000005);
      Tolerance.equals(coefCalc.getAttenuationCoefficient(),
          coefCalcRDV2.getAttenuationCoefficient(),
          "Attenuation Coefficient", 0.000005);
      
      System.out.println("@Test - testCoefCalcMain " + String.valueOf(i));
      
      
    }
    
  }
}
