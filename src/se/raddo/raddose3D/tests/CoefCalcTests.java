package se.raddo.raddose3D.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.*;

import se.raddo.raddose3D.Assertions;
import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.BeamTophat;
import se.raddo.raddose3D.CoefCalcFromParams;

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

    Double oxygenOccurrence = Double.valueOf(coefCalc
        .getSolventOccurrence(coefCalc.getParser()
            .getElement("O")));
    Double hydrogenOccurrence = Double.valueOf(coefCalc
        .getSolventOccurrence(coefCalc.getParser()
            .getElement("H")));

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
   * Run an actual scenario and compare to values obtained by RADDOSE2
   */
  @Test
  public void testCoefCalcScenario() {
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

    coefCalc.updateCoefficients(null, b);

    Assertion.equals(coefCalc.getAbsorptionCoefficient(), 1.04,
        "Absorption Coefficient");
    Assertion.equals(coefCalc.getAttenuationCoefficient(), 1.095,
        "Attenuation Coefficient");
  }
}
