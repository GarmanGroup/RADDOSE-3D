package se.raddo.raddose3D.tests;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.*;

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

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(Double.valueOf(100.0),
        Double.valueOf(100.0), Double.valueOf(100.0), Double.valueOf(90.0),
        Double.valueOf(90.0), Double.valueOf(90.0), 0, 0, 0, 0, atoms, numbers,
        atoms, numbers, Double.valueOf(100.0));

    Double oxygenOccurrence = Double.valueOf(coefCalc.getParser()
        .findAtomWithName("O").getSolventOccurrence());
    Double hydrogenOccurrence = Double.valueOf(coefCalc.getParser()
        .findAtomWithName("H").getSolventOccurrence());

    Assertion.equals(hydrogenOccurrence, oxygenOccurrence * 2, "O vs H");
  }
}
