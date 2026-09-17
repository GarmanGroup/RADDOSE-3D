package se.raddo.raddose3D;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Numeric assertion helpers with readable failure messages.
 * <p>
 * This is the JUnit 5 port of the old {@code tests/Assertion.java}. It is
 * deliberately <em>not</em> called {@code Assertions}: that name is already
 * taken by {@link se.raddo.raddose3D.Assertions} in the production code, and
 * these tests live in the same package.
 */
public final class Tolerance {

  /**
   * Default tolerance for comparing doubles that should agree to within
   * floating point rounding.
   */
  public static final double DBL_ROUNDING = 1e-13;

  private Tolerance() {
    // Utility class.
  }

  /**
   * Asserts that {@code value} is within {@code tolerance} of {@code target},
   * handling nulls and infinities explicitly.
   */
  public static void equals(final Double value, final Double target,
      final String name, final double tolerance) {
    assertNotNull(value, name + " is null");

    if (target == Double.NEGATIVE_INFINITY) {
      assertTrue(value == Double.NEGATIVE_INFINITY,
          () -> name + " not set to -Inf (" + value + ")");

    } else if (target == Double.POSITIVE_INFINITY) {
      assertTrue(value == Double.POSITIVE_INFINITY,
          () -> name + " not set to +Inf (" + value + ")");

    } else {
      assertTrue(Math.abs(value - target) < tolerance,
          () -> name + " set incorrectly (" + value + " - deviation "
              + (target - value) + ")");
    }
  }

  /** Asserts equality to within {@link #DBL_ROUNDING}. */
  public static void equals(final Double value, final Double target,
      final String name) {
    equals(value, target, name, DBL_ROUNDING);
  }

  /** Asserts equality to within {@link #DBL_ROUNDING}. */
  public static void equals(final Double value, final Integer target,
      final String name) {
    equals(value, Double.valueOf(target), name);
  }

  /** Asserts that two integers are equal. */
  public static void equals(final Integer value, final Integer target,
      final String name) {
    assertNotNull(value, name + " is null");
    org.junit.jupiter.api.Assertions.assertEquals(target, value,
        name + " set incorrectly (" + value + ")");
  }

  /** Asserts that two strings are equal. */
  public static void equals(final String value, final String target,
      final String name) {
    assertNotNull(value, name + " is null");
    org.junit.jupiter.api.Assertions.assertEquals(target, value,
        name + " set incorrectly (" + value + ")");
  }

  /** Asserts that an object is not null. */
  public static void isNotNull(final Object object, final String name) {
    assertNotNull(object, name + " is null");
  }
}
