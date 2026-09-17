package se.raddo.raddose3D;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import se.raddo.raddose3D.CrystalPolyhedron.Vector;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link CrystalPolyhedron.Vector}, the 3D vector maths underpinning
 * all polyhedron ray tracing.
 * <p>
 * These nine static methods had no coverage at all, despite
 * {@code findDepth}, {@code calculateCrystalOccupancy} and the surface-normal
 * machinery all being built on them.
 */
public class VectorMathTest {

  /** Ray tracing routinely works with coordinates in the tens of microns. */
  private static final double EPS = 1e-12;

  @ParameterizedTest
  @CsvSource({
      // x,   y,   z,   expected magnitude
      "  0,   0,   0,   0",
      "  1,   0,   0,   1",
      "  0,  -1,   0,   1",
      "  3,   4,   0,   5",
      "  2,   3,   6,   7",
      " -3,  -4,   0,   5",
      "  1,   1,   1,   1.7320508075688772",
  })
  public void vectorMagnitude(double x, double y, double z, double expected) {
    assertEquals(expected,
        Vector.vectorMagnitude(new double[] {x, y, z}), EPS);
  }

  @Test
  public void vectorBetweenPointsIsToMinusFrom() {
    double[] from = {1, 2, 3};
    double[] to = {10, -5, 0.5};
    assertArrayEquals(new double[] {9, -7, -2.5},
        Vector.vectorBetweenPoints(from, to), EPS);
  }

  @Test
  public void vectorBetweenPointsDoesNotMutateItsArguments() {
    double[] from = {1, 2, 3};
    double[] to = {4, 5, 6};
    Vector.vectorBetweenPoints(from, to);
    assertArrayEquals(new double[] {1, 2, 3}, from, 0.0, "from was mutated");
    assertArrayEquals(new double[] {4, 5, 6}, to, 0.0, "to was mutated");
  }

  @Test
  public void crossProductFollowsRightHandRule() {
    double[] x = {1, 0, 0};
    double[] y = {0, 1, 0};
    assertArrayEquals(new double[] {0, 0, 1}, Vector.crossProduct(x, y), EPS);
    // and is anti-commutative
    assertArrayEquals(new double[] {0, 0, -1}, Vector.crossProduct(y, x), EPS);
  }

  @Test
  public void crossProductOfParallelVectorsIsZero() {
    double[] a = {2, -3, 4};
    double[] b = {4, -6, 8};
    assertArrayEquals(new double[] {0, 0, 0}, Vector.crossProduct(a, b), EPS);
  }

  @Test
  public void crossProductIsPerpendicularToBothInputs() {
    double[] a = {3, -1, 7};
    double[] b = {-2, 5, 1};
    double[] c = Vector.crossProduct(a, b);
    assertEquals(0.0, Vector.dotProduct(a, c), 1e-10, "not perpendicular to a");
    assertEquals(0.0, Vector.dotProduct(b, c), 1e-10, "not perpendicular to b");
  }

  @Test
  public void normalisedCrossProductHasUnitMagnitude() {
    double[] a = {3, -1, 7};
    double[] b = {-2, 5, 1};
    double[] n = Vector.normalisedCrossProduct(a, b);
    assertEquals(1.0, Vector.vectorMagnitude(n), 1e-12);
    // same direction as the unnormalised cross product
    double[] raw = Vector.crossProduct(a, b);
    double scale = Vector.vectorMagnitude(raw);
    assertArrayEquals(new double[] {raw[0] / scale, raw[1] / scale, raw[2] / scale},
        n, EPS);
  }

  @ParameterizedTest
  @CsvSource({
      // a(x,y,z)      b(x,y,z)      expected dot
      " 1, 0, 0,    0, 1, 0,     0",
      " 1, 0, 0,    1, 0, 0,     1",
      " 1, 0, 0,   -1, 0, 0,    -1",
      " 1, 2, 3,    4, 5, 6,     32",
      " 2,-3, 4,   -1, 5, 2,    -9",
  })
  public void dotProduct(double ax, double ay, double az,
      double bx, double by, double bz, double expected) {
    assertEquals(expected,
        Vector.dotProduct(new double[] {ax, ay, az}, new double[] {bx, by, bz}),
        EPS);
  }

  @Test
  public void dotProductIsCommutative() {
    double[] a = {1.5, -2.25, 3.125};
    double[] b = {-4, 0.5, 6};
    assertEquals(Vector.dotProduct(a, b), Vector.dotProduct(b, a), EPS);
  }

  /**
   * The plane z = 5 has unit normal (0,0,1) and, in the convention used here
   * (dot(origin, n) + planeDistance == 0 on the plane), planeDistance = -5.
   * A ray from the origin along +z must therefore travel exactly 5.
   */
  @Test
  public void rayTraceDistanceToAxisAlignedPlane() {
    double[] normal = {0, 0, 1};
    double[] direction = {0, 0, 1};
    double[] origin = {0, 0, 0};
    assertEquals(5.0, Vector.rayTraceDistance(normal, direction, origin, -5), EPS);
  }

  @Test
  public void rayTraceDistanceIsSignedForPlanesBehindTheOrigin() {
    double[] normal = {0, 0, 1};
    double[] direction = {0, 0, 1};
    double[] origin = {0, 0, 0};
    assertEquals(-5.0, Vector.rayTraceDistance(normal, direction, origin, 5), EPS,
        "a plane behind the ray should give a negative distance");
  }

  @Test
  public void rayTraceDistanceScalesInverselyWithDirectionMagnitude() {
    double[] normal = {0, 0, 1};
    double[] origin = {0, 0, 0};
    // t is expressed in units of the direction vector, so doubling the
    // direction vector halves t.
    assertEquals(5.0, Vector.rayTraceDistance(normal, new double[] {0, 0, 1},
        origin, -5), EPS);
    assertEquals(2.5, Vector.rayTraceDistance(normal, new double[] {0, 0, 2},
        origin, -5), EPS);
  }

  @Test
  public void rayTraceToPointLandsOnThePlane() {
    double[] normal = {0, 0, 1};
    double[] direction = {1, 2, 1};
    double[] origin = {3, -4, 0};
    double[] hit = Vector.rayTraceToPoint(normal, direction, origin, -5);
    assertEquals(5.0, hit[2], EPS, "intersection is not on the plane z = 5");
    // travelling t = 5 along (1,2,1) from (3,-4,0)
    assertArrayEquals(new double[] {8, 6, 5}, hit, EPS);
  }

  @Test
  public void rayTraceToPointAgreesWithRayTraceToPointWithDistance() {
    double[] normal = {0, 1, 0};
    double[] direction = {0.5, 1, -0.25};
    double[] origin = {1, 2, 3};
    double t = Vector.rayTraceDistance(normal, direction, origin, -9);
    assertArrayEquals(
        Vector.rayTraceToPoint(normal, direction, origin, -9),
        Vector.rayTraceToPointWithDistance(direction, origin, t), EPS);
  }

  @Test
  public void rayTraceToPointWithDistanceIsLinearInT() {
    double[] direction = {1, 0, 0};
    double[] origin = {0, 0, 0};
    assertArrayEquals(new double[] {0, 0, 0},
        Vector.rayTraceToPointWithDistance(direction, origin, 0), EPS);
    assertArrayEquals(new double[] {7, 0, 0},
        Vector.rayTraceToPointWithDistance(direction, origin, 7), EPS);
    assertArrayEquals(new double[] {-7, 0, 0},
        Vector.rayTraceToPointWithDistance(direction, origin, -7), EPS);
  }

  /** A 10x10 square in the xy plane, corners at (0,0) and (10,10). */
  private static final double[][] SQUARE = {
      {0, 0, 0}, {10, 0, 0}, {10, 10, 0}, {0, 10, 0}};

  @ParameterizedTest
  @CsvSource({
      //   x,     y,   inside?
      "   5,      5,   true",
      "   0.01,   0.01, true",
      "   9.99,   9.99, true",
      "  -1,      5,   false",
      "  11,      5,   false",
      "   5,     -1,   false",
      "   5,     11,   false",
      " 100,    100,   false",
  })
  public void polygonInclusionTest(double x, double y, boolean inside) {
    assertEquals(inside,
        Vector.polygonInclusionTest(SQUARE, new double[] {x, y, 0}),
        "point (" + x + ", " + y + ")");
  }

  @Test
  public void polygonInclusionTestHandlesAConcavePolygon() {
    // An L-shape occupying the bottom and left of a 10x10 box: the notch at
    // (8,8) is outside even though it is within the bounding box.
    double[][] lShape = {
        {0, 0, 0}, {10, 0, 0}, {10, 4, 0}, {4, 4, 0}, {4, 10, 0}, {0, 10, 0}};
    assertTrue(Vector.polygonInclusionTest(lShape, new double[] {2, 2, 0}),
        "corner of the L should be inside");
    assertTrue(Vector.polygonInclusionTest(lShape, new double[] {8, 2, 0}),
        "foot of the L should be inside");
    assertTrue(Vector.polygonInclusionTest(lShape, new double[] {2, 8, 0}),
        "upright of the L should be inside");
    assertFalse(Vector.polygonInclusionTest(lShape, new double[] {8, 8, 0}),
        "the notch should be outside");
  }

  @Test
  public void polygonInclusionTestIgnoresZ() {
    // Documented behaviour: "Ignores the z axis at the moment."
    assertTrue(Vector.polygonInclusionTest(SQUARE, new double[] {5, 5, 1000}));
  }
}
