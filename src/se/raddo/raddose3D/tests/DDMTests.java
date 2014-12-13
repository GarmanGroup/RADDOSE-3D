package se.raddo.raddose3D.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import se.raddo.raddose3D.DDMLeal;
import se.raddo.raddose3D.DDMLinear;
import se.raddo.raddose3D.DDMSimple;

public class DDMTests {

  @Test
  /** Tests that DDMSimple is equal to 1 at an arbitrary dose*/
  public void testDDMSimpleIsOne() {
    //Create a DDMSimple object and assign an arbitrary dose value
    //Create an array of arbitrary dose values
    //Create a boolean value to state whether decay values are equal to 1
    //Create a double to store the calculated dose value
    //Define a tolerance value
    DDMSimple mySimpleDDM = new DDMSimple();
    double[] arbitraryDoseValues = {0,1,5,10,30,43,50,100,1000};
    boolean equalToOne = true;
    double calculatedDecayValue;
    double zeroTolerance = 1e-6;

    //Calculate the decay for each dose value
    for (int i = 0; i < arbitraryDoseValues.length; i++) {
      calculatedDecayValue = mySimpleDDM.calcDecay(arbitraryDoseValues[i]);

      //If the decay value is not equal to 1 then set boolean value to false
      //and break out of loop.
      //If this condition is satisfied then the test should fail.
      if (calculatedDecayValue - 1.0 > zeroTolerance){
        equalToOne = false;
        break;
      }
    }

    //Check that the decay value is equal to 1
    Assert.assertTrue(equalToOne,"DDMSimple calculate decay");

    System.out.println("@Test - testDDMSimpleIsOne");
  }


  @Test
  /** Tests that the decay values of the Linear DDM class are between 0 and 1*/
  public void testDDMLinearIsDecayRange() {

    DDMLinear myLinearDDM = new DDMLinear();
    double[] doseValues = {0,1,5,10,30,43,50,100,1000};
    boolean withinRangeZeroToOne = true;
    double decayValue;

    //For each dose value calculate the decay value.
    //Then check if the decay value is outside of the range 0 < x < 1.
    //If it is then set boolean value to false and break out of the loop.
    //The test should fail if this is the case.
    for (int i = 0; i < doseValues.length; i++) {
      decayValue = myLinearDDM.calcDecay(doseValues[i]);

      if (decayValue > 1.0 || decayValue < 0.0){
        withinRangeZeroToOne = false;
        break;
      }
    }

    //Check that the decay value is equal to 1
    Assert.assertTrue(withinRangeZeroToOne,"DDMLinear decay range 0 < x < 1");

    System.out.println("@Test - testDDMLinearDecayRange");
  }


  @Test
  /** Tests that the DDMLeal relative integrated intensity values
   * are between 0 and 1*/
  public void testDDMLealDecayRange() {
    //Create a DDMLeal object with arbitrary decay parameters.
    //Create an array with arbitrary dose values.
    //Create a boolean variable which tests the range of the relative decay
    //Create a double to hold the Relative Diffraction efficiency (RDE) values
    DDMLeal myLealDDM = new DDMLeal(0.3, 10.0, 0.03);
    double[] doseValues = {0,1,5,10,30,43,50,100,1000};
    boolean withinRangeZeroToOne = true;
    double RDE;

    //Calculate the zero dose integrated intensity
    double zeroDoseIntegratedIntensity = myLealDDM.getIntegratedIntensity(0);

    //For each dose value calculate the relative diffraction Efficiency (RDE).
    //Then check if the RDE is outside of the range 0 < x < 1. If it is then
    //set boolean value to false and break out of the loop.
    //The test should fail if this is the case.
    for(int i = 0; i < doseValues.length; i++) {

      RDE = myLealDDM.getIntegratedIntensity(doseValues[i])
          / zeroDoseIntegratedIntensity;

      if (RDE > 1.0 || RDE < 0.0){
        withinRangeZeroToOne = false;
        break;
      }
    }

    //Check that the RDE is within range
    Assert.assertTrue(withinRangeZeroToOne, "DDMLeal should be within range 0 < x < 1");

    System.out.println("@Test - testDDMLealDecayRange");
  }


  @Test
  /** Tests that the decay values of the Linear DDM class monotonically decrease*/
  public void testDDMLinearMonotonicDecrease() {

    DDMLinear myLinearDDM = new DDMLinear();
    double[] doseValues = {0,1,5,10,30,43,50,100,1000};
    boolean monotonicDecrease = true;
    double decayValue;
    double previousDecayValue = 1.0;

    //For each dose value calculate the decay value.
    //Then check if the current decay value (at the higher dose) is
    //smaller than the previous decay value (at the smaller dose).
    //If it's bigger then the test should fail.
    //There is a case where the decay values don't get smaller and that's
    //when the decay value has already dropped to zero.
    for (int i = 0; i < doseValues.length; i++) {
      decayValue = myLinearDDM.calcDecay(doseValues[i]);

      if (i > 0){
        if (decayValue >= previousDecayValue && previousDecayValue != 0.0){
          monotonicDecrease = false;
          break;
        }
      }
      previousDecayValue = decayValue;
    }

    //Check that the decay value is equal to 1
    Assert.assertTrue(monotonicDecrease,"DDMLinear should monotonically decrease");

    System.out.println("@Test - testDDMLinearMonotonicDecrease");
  }


  @Test
  /** Tests that the DDMLeal relative integrated intensity values
   * are monotonically decreasing*/
  public void testDDMLealMonotonicDecrease() {
    //Create a DDMLeal object with arbitrary decay parameters.
    //Create an array with arbitrary dose values.
    //Create a boolean variable which tests the range of the relative decay
    //Create a double to hold the Relative Diffraction efficiency (RDE) values
    DDMLeal myLealDDM = new DDMLeal(0.3, 10.0, 0.03);
    double[] doseValues = {0,1,5,10,30,43,50,100,1000};
    boolean monotonicDecrease = true;
    double RDE;
    double previousRDE = 1.0;

    //Calculate the zero dose integrated intensity
    double zeroDoseIntegratedIntensity = myLealDDM.getIntegratedIntensity(0);

    //For each dose value calculate the RDE.
    //Then check if the RDE (at the higher dose) is
    //smaller than the previous RDE(at the smaller dose).
    //If it's bigger then the test should fail
    for(int i = 0; i < doseValues.length; i++) {

      RDE = myLealDDM.getIntegratedIntensity(doseValues[i])
          / zeroDoseIntegratedIntensity;

      if (i > 0){
        if (RDE > previousRDE){
          monotonicDecrease = false;
          break;
        }
      }

      previousRDE = RDE;

    }

    //Check that the RDE is within range
    Assert.assertTrue(monotonicDecrease, "DDMLeal should monotonically decrease");

    System.out.println("@Test - testDDMLealMonotonicDecrease");
  }


}
