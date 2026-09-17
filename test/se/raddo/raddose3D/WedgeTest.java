package se.raddo.raddose3D;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * Tests for the Wedge class.
 * Wedge is basically just a fancy data storage class with little internal
 * logic.
 */

public class WedgeTest {

  /** Instantiation should fail if required parameters are missing. */
  @Test
  public void testInstantiationShouldFailWithoutRequiredParameters() {
    assertThrows(RuntimeException.class, () -> {
      new Wedge(null, null, null, null, null, null, null, null, null, null, null, null);
    });
  }

  @Test
  /** Instantiation should work with optional parameters missing. */
  public void testInstantiationDefaults() {
    Wedge w = new Wedge(null, 0d, 90d, 120d, null, null, null, null, null,
        null, null, null);

    Tolerance.isNotNull(w.getAngRes(), "angular resolution");
    Tolerance.equals(w.getStartAng(), Math.toRadians(0), "start angle");
    Tolerance.equals(w.getEndAng(), Math.toRadians(90), "end angle");
    Tolerance.equals(w.getTotSec(), 120d, "exposure time");
    Tolerance.isNotNull(w.getStartX(), "start X coordinate");
    Tolerance.isNotNull(w.getStartY(), "start Y coordinate");
    Tolerance.isNotNull(w.getStartZ(), "start Z coordinate");
    Tolerance.isNotNull(w.getTransX(), "X translation");
    Tolerance.isNotNull(w.getTransY(), "Y translation");
    Tolerance.isNotNull(w.getTransZ(), "Z translation");
    Tolerance.isNotNull(w.getOffAxisUm(), "off axis information");

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
