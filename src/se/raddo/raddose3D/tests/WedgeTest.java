package se.raddo.raddose3D.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import se.raddo.raddose3D.Wedge;

/**
 * Tests for the Wedge class.
 * Wedge is basically just a fancy data storage class with little internal
 * logic.
 */

public class WedgeTest {

  @SuppressWarnings("unused")
  @Test
  /** Instantiation should fail if required parameters are missing. */
  public void testInstantiationFail() {
    try {
      Wedge w = new Wedge(null, null, null, null, null, null, null, null, null,
          null, null);
    } catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("@Test - testInstantiationFail");
      return;
    }

    Assert.fail("Wedge instantiation accepted invalid input (should fail)");
  }

  @Test
  /** Instantiation should work with optional parameters missing. */
  public void testInstantiationDefaults() {
    Wedge w = new Wedge(null, 0d, 90d, 120d, null, null, null, null, null,
        null, null);
       
    Assertion.isNotNull(w.getAngRes(), "angular resolution");
    Assertion.equals(w.getStartAng(), 0d, "start angle");
    Assertion.equals(w.getEndAng(), 90d, "end angle");
    Assertion.equals(w.getTotSec(), 120d, "exposure time");
    Assertion.isNotNull(w.getStartX(), "start X coordinate");
    Assertion.isNotNull(w.getStartY(), "start Y coordinate");
    Assertion.isNotNull(w.getStartZ(), "start Z coordinate");
    Assertion.isNotNull(w.getTransX(), "X translation");
    Assertion.isNotNull(w.getTransY(), "Y translation");
    Assertion.isNotNull(w.getTransZ(), "Z translation");
    Assertion.isNotNull(w.getOffAxisUm(), "off axis information");

    System.out.println("@Test - testInstantiationDefaults");
  }

// TODO
//  @Test
//  /** Instantiation with all parameters. */
//  public void testInstantiation() {
//    
//    System.out.println("@Test - testInstantiation");
//  }
}
