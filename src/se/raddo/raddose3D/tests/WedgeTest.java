package se.raddo.raddose3D.tests;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

import se.raddo.raddose3D.Wedge;

/**
 * Tests for the Wedge class.
 * Wedge is basically just a fancy data storage class with little internal
 * logic.
 */

public class WedgeTest {

  @Test(expectedExceptions = RuntimeException.class)
  /** Instantiation should fail if required parameters are missing. */
  public void testInstantiationShouldFailWithoutRequiredParameters() {
    new Wedge(null, null, null, null, null, null, null, null, null, null, null);
  }

  @Test
  /** Instantiation should work with optional parameters missing. */
  public void testInstantiationDefaults() {
    Wedge w = new Wedge(null, 0d, 90d, 120d, null, null, null, null, null,
        null, null);

    Assertion.isNotNull(w.getAngRes(), "angular resolution");
    Assertion.equals(w.getStartAng(), Math.toRadians(0), "start angle");
    Assertion.equals(w.getEndAng(), Math.toRadians(90), "end angle");
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
