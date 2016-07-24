package se.raddo.raddose3D.tests;

import static org.testng.Assert.*;

public class Assertion {
  final public static double dblRoundingTolerance = 1e-13;

  /**
   * Tests value against target with custom tolerance.
   * Includes testing for null, tolerance, and nice error messages
   */
  public static void equals(Double value, Double target, String name,
      double tolerance) {
    assertNotNull(value, name + " is null");

    if (target == Double.NEGATIVE_INFINITY) {
      assertTrue(value == Double.NEGATIVE_INFINITY,
          name + " not set to -Inf (" + value + ")");

    } else if (target == Double.POSITIVE_INFINITY) {
      assertTrue(value == Double.POSITIVE_INFINITY,
          name + " not set to +Inf (" + value + ")");

    } else {
      assertTrue(Math.abs(value - target) < tolerance,
          name + " set incorrectly (" + value + " - deviation "
              + (target - value) + ")");
    }
  }

  /**
   * Tests value against target.
   * Includes testing for null, tolerance, and nice error messages
   */
  public static void equals(Double value, Double target, String name) {
    equals(value, target, name, dblRoundingTolerance);
  }

  /**
   * Tests value against target.
   * Includes testing for null, tolerance, and nice error messages
   */
  public static void equals(Double value, Integer target, String name) {
    equals(value, new Double(target), name);
  }

  /**
   * Tests value against target.
   * Includes testing for null and nice error messages
   */
  public static void equals(Integer value, Integer target, String name) {
    assertNotNull(value, name + " is null");
    assertEquals(value, target, name + " set incorrectly (" + value + ")");
  }

  /**
   * Tests value against target.
   * Includes testing for null and nice error messages
   */
  public static void equals(String value, String target, String name) {
    assertNotNull(value, name + " is null");
    assertEquals(value, target, name + " set incorrectly (" + value + ")");
  }

  /**
   * Tests object for null with a nice error message.
   */
  public static void isNotNull(Object object, String name) {
    assertNotNull(object, name + " is null");
  }

}
