package se.raddo.raddose3D.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import se.raddo.raddose3D.DDMSimple;

public class DDMTests {

  @Test
  /** Tests that DDM Simple is equal to 1 at an arbitrary dose*/
  public void testDDMSimpleIsOne() {
    //Create a DDMSimple object and assign an arbitrary dose value
    DDMSimple mySimpleDDM = new DDMSimple();
    double arbitraryDose = 10;

    //Using the dose value calculate the decay
    double calculatedDecayValue = mySimpleDDM.calcDecay(arbitraryDose);

    //Check that the decay value is equal to 1
    Assert.assertEquals(calculatedDecayValue, 1.0, "DDMSimple calculate decay");

  }
}
