package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * An {@link Output} subscriber that records every call it receives, in order.
 * <p>
 * This replaces the Mockito mocks the old {@code ExperimentTest} used. Those
 * tests only ever asserted <em>which</em> callbacks fired, in what order, and
 * with which argument &mdash; all of which a recording fake expresses directly,
 * without a mocking framework. That matters here because Mockito 1.9.5 (the
 * version this project vendored) cannot run on Java 9 or newer.
 */
public class RecordingOutput implements Output {

  /** Every call received, in order, as "method:argument". */
  private final List<String> calls = new ArrayList<String>();

  /** The actual objects passed, in order. */
  private final List<Object> arguments = new ArrayList<Object>();

  @Override
  public void publishCrystal(final Crystal c) {
    record("publishCrystal", c);
  }

  @Override
  public void publishBeam(final Beam b) {
    record("publishBeam", b);
  }

  @Override
  public void publishWedge(final Wedge w) {
    record("publishWedge", w);
  }

  @Override
  public void close() {
    record("close", null);
  }

  private void record(final String method, final Object argument) {
    calls.add(method);
    arguments.add(argument);
  }

  /** The sequence of method names received. */
  public List<String> getCalls() {
    return calls;
  }

  /** How many times {@code method} was called. */
  public int countOf(final String method) {
    int count = 0;
    for (String call : calls) {
      if (call.equals(method)) {
        count++;
      }
    }
    return count;
  }

  /** The argument passed to the n'th (0-based) call of {@code method}. */
  public Object argumentOf(final String method, final int occurrence) {
    int seen = 0;
    for (int i = 0; i < calls.size(); i++) {
      if (calls.get(i).equals(method)) {
        if (seen == occurrence) {
          return arguments.get(i);
        }
        seen++;
      }
    }
    throw new AssertionError("No call " + occurrence + " of " + method
        + "; recorded calls were " + calls);
  }

  /** Asserts the complete call sequence, in order. */
  public void assertCalls(final String... expected) {
    assertEquals(Arrays.asList(expected), calls, "recorded call sequence");
  }

  /** Asserts that {@code method} was called exactly {@code times} times. */
  public void assertCallCount(final String method, final int times) {
    assertEquals(times, countOf(method),
        method + " call count; recorded calls were " + calls);
  }

  /** Asserts that {@code method} was never called. */
  public void assertNeverCalled(final String method) {
    assertCallCount(method, 0);
  }
}
