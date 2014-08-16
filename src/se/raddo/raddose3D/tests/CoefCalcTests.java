package se.raddo.raddose3D.tests;
import java.io.File;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.*;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.BeamTophat;
import se.raddo.raddose3D.CoefCalcFromParams;
import se.raddo.raddose3D.CoefCalcRaddose;

/**
 * @author magd3052
 */
public class CoefCalcTests {
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
        atoms, numbers, 100.0);

    Double oxygenOccurrence = coefCalc.getSolventOccurrence(
        coefCalc.getParser().getElement("O"));
    Double hydrogenOccurrence = coefCalc.getSolventOccurrence(
        coefCalc.getParser().getElement("H"));

    Assertion.equals(hydrogenOccurrence, oxygenOccurrence * 2, "O vs H");
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
        numbers, emptyAtoms, emptyNumbers, 100.0);

    Double zincOccurrence = coefCalc.getMacromolecularOccurrence(
        coefCalc.getParser().getElement("ZN"));

    Assertion.equals(zincOccurrence, 48, "Zn = 48");
  }

  /**
   * Run an actual scenario and compare to values obtained from RADDOSE2.
   */
  @Test
  public void testCoefCalcScenario1() {
    List<String> heavyProtAtomNames = new ArrayList<String>();
    List<Double> heavyProtAtomNums = new ArrayList<Double>();

    List<String> heavySolutionConcNames = new ArrayList<String>();
    List<Double> heavySolutionConcNums = new ArrayList<Double>();

    heavyProtAtomNames.add("S");
    heavyProtAtomNums.add(10.0);

    heavySolutionConcNames.add("Na");
    heavySolutionConcNames.add("Cl");
    heavySolutionConcNums.add(1200.);
    heavySolutionConcNums.add(200.);

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(
        79.2, 79.2, 38.1, 90.0, 90.0, 90.0, 8, 129, 0, 0,
        heavyProtAtomNames, heavyProtAtomNums,
        heavySolutionConcNames, heavySolutionConcNums, 0.);

    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_COLL_H, 80.);
    beamProperties.put(Beam.BEAM_COLL_V, 80.);
    beamProperties.put(Beam.BEAM_FLUX, 9.281e8);
    beamProperties.put(Beam.BEAM_ENERGY, 8.05);
    Beam b = new BeamTophat(beamProperties);

    coefCalc.updateCoefficients(b);

    // Values obtained from RADDOSEv2, http://www.raddo.se/legacy/
    Assertion.equals(coefCalc.getAbsorptionCoefficient(), 0.001042,
        "Absorption Coefficient", 0.000005);
    Assertion.equals(coefCalc.getElasticCoefficient(), 0.000036,
        "Elastic Coefficient", 0.000005);
    Assertion.equals(coefCalc.getAttenuationCoefficient(), 0.001095,
        "Attenuation Coefficient", 0.000005);
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
        heavySolutionConcNames, heavySolutionConcNums, 0.);

    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_COLL_H, 80.);
    beamProperties.put(Beam.BEAM_COLL_V, 80.);
    beamProperties.put(Beam.BEAM_FLUX, 9.281e8);
    beamProperties.put(Beam.BEAM_ENERGY, 14.05);
    Beam b = new BeamTophat(beamProperties);

    coefCalc.updateCoefficients(b);

    // Values obtained from RADDOSEv2, http://www.raddo.se/legacy/
    Assertion.equals(coefCalc.getAbsorptionCoefficient(), 0.004675,
        "Absorption Coefficient", 0.000005);
    Assertion.equals(coefCalc.getElasticCoefficient(), 0.000068,
        "Elastic Coefficient", 0.000005);
    Assertion.equals(coefCalc.getAttenuationCoefficient(), 0.004769,
        "Attenuation Coefficient", 0.000005);
  }

  /**
   * Run an actual scenario and compare to values obtained from RADDOSE2.
   * This input file is stolen from Jonny's insulin crystals!
   */
  @Test
  public void testCoefCalcScenario3() {
    List<String> heavyProtAtomNames = new ArrayList<String>();
    List<Double> heavyProtAtomNums = new ArrayList<Double>();

    List<String> heavySolutionConcNames = new ArrayList<String>();
    List<Double> heavySolutionConcNums = new ArrayList<Double>();

    heavyProtAtomNames.add("S");
    heavyProtAtomNames.add("Zn");
    heavyProtAtomNums.add(6.0);
    heavyProtAtomNums.add(2.0);

    heavySolutionConcNames.add("P");
    heavySolutionConcNums.add(425.);

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(
        78.27, 78.27, 78.27, 90.0, 90.0, 90.0, 24, 51, 0, 0,
        heavyProtAtomNames, heavyProtAtomNums,
        heavySolutionConcNames, heavySolutionConcNums, 0.);

    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_COLL_H, 20.);
    beamProperties.put(Beam.BEAM_COLL_V, 70.);
    beamProperties.put(Beam.BEAM_FLUX, 2e12);
    beamProperties.put(Beam.BEAM_ENERGY, 12.1);
    Beam b = new BeamTophat(beamProperties);

    coefCalc.updateCoefficients(b);

    // Values obtained from RADDOSEv2, http://www.raddo.se/legacy/
    Assertion.equals(coefCalc.getAbsorptionCoefficient(), 4.60e-04,
        "Absorption Coefficient", 0.000005);
    Assertion.equals(coefCalc.getElasticCoefficient(), 2.20e-05,
        "Elastic Coefficient", 0.000005);
    Assertion.equals(coefCalc.getAttenuationCoefficient(), 4.97e-04,
        "Attenuation Coefficient", 0.000005);
  }

  /**
   * Random numbers used for CoefCalc
   */
  @Test
  public void testCoefCalcScenario4() {
    int testCount = 5;
    
    Random random = new Random(0);

    File path = new File(System.getProperty("user.dir"));
    File files[] = path.listFiles();
    
    String fileList = "";
    for (File subFile : files)
    {
      fileList = fileList.concat(subFile.toString() + " ");
    }
    
    Assertion.equals(1, 2, fileList);
    
    for (int i=0; i < testCount; i++)
    {
      List<String> heavyProtAtomNames = new ArrayList<String>();
      List<Double> heavyProtAtomNums = new ArrayList<Double>();

      List<String> heavySolutionConcNames = new ArrayList<String>();
      List<Double> heavySolutionConcNums = new ArrayList<Double>();

      heavyProtAtomNames.add("S");
      heavyProtAtomNames.add("Fe");
      
      double sulphur = random.nextInt() % 20;
      double iron = random.nextInt() % 10;
      
      heavyProtAtomNums.add(sulphur);
      heavyProtAtomNums.add(iron);

      double phosphorus = random.nextInt() % 1000;
      
      heavySolutionConcNames.add("P");
      heavySolutionConcNums.add(phosphorus);
      
      double unit_cell_length = random.nextInt() % 180 + 20;
 
      int protein_residues = random.nextInt() % 80 + 20;
      
      CoefCalcFromParams coefCalc = new CoefCalcFromParams(
          unit_cell_length, unit_cell_length, unit_cell_length, 90.0, 90.0, 90.0, 24, protein_residues, 0, 0,
          heavyProtAtomNames, heavyProtAtomNums,
          heavySolutionConcNames, heavySolutionConcNums, 0.);
      
      CoefCalcRaddose coefCalcRDV2 = new CoefCalcRaddose(
          unit_cell_length, unit_cell_length, unit_cell_length, 90.0, 90.0, 90.0, 24, protein_residues, 0, 0,
          heavyProtAtomNames, heavyProtAtomNums,
          heavySolutionConcNames, heavySolutionConcNums, 0.);

      Map<Object, Object> beamProperties = new HashMap<Object, Object>();
      beamProperties.put(Beam.BEAM_COLL_H, 20.);
      beamProperties.put(Beam.BEAM_COLL_V, 70.);
      beamProperties.put(Beam.BEAM_FLUX, 2e12);
      beamProperties.put(Beam.BEAM_ENERGY, 12.1);
      Beam b = new BeamTophat(beamProperties);

      coefCalc.updateCoefficients(b);
      coefCalcRDV2.updateCoefficients(b);

      // Values obtained from RADDOSEv2, http://www.raddo.se/legacy/
      Assertion.equals(coefCalc.getAbsorptionCoefficient(), coefCalcRDV2.getAbsorptionCoefficient(),
          "Absorption Coefficient", 0.000005);
      Assertion.equals(coefCalc.getElasticCoefficient(),coefCalcRDV2.getElasticCoefficient(),
          "Elastic Coefficient", 0.000005);
      Assertion.equals(coefCalc.getAttenuationCoefficient(), coefCalcRDV2.getAttenuationCoefficient(),
          "Attenuation Coefficient", 0.000005);
    }
  }
}
