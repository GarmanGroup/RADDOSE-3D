package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link Initializer} that records what it is told and replays it later.
 * <p>
 * The parser generated from {@code Inputfile.g} runs its actions as it reads,
 * and ANTLR's error recovery lets it keep reading past a syntax error. The
 * consequence was that a file with a mistyped keyword still exposed its
 * wedges, printed a dose, and only afterwards reported that it had not
 * understood the input -- so the number on screen was computed from whatever
 * defaults applied to the parts that failed to parse.
 * <p>
 * Recording the calls and replaying them only once the whole file has parsed
 * cleanly separates "read the input" from "run the experiment", which is the
 * distinction the original code lacked. All syntax errors are still collected
 * and reported together, rather than stopping at the first.
 * <p>
 * Note that crystals and beams are still <em>constructed</em> while parsing,
 * because the grammar builds them inside its actions; some of those
 * constructors print. Only the experiment itself is deferred.
 */
public class DeferredInitializer implements Initializer {

  /** One recorded call, replayed against the real initializer later. */
  private interface Action {
    /**
     * Replays this call.
     *
     * @param target the initializer to replay onto
     */
    void replayOn(Initializer target);
  }

  /** Recorded calls, in the order they were made. */
  private final List<Action> actions = new ArrayList<Action>();

  @Override
  public void setCrystal(final Crystal c) {
    actions.add(new Action() {
      @Override
      public void replayOn(final Initializer target) {
        target.setCrystal(c);
      }
    });
  }

  @Override
  public void setBeam(final Beam b) {
    actions.add(new Action() {
      @Override
      public void replayOn(final Initializer target) {
        target.setBeam(b);
      }
    });
  }

  @Override
  public void exposeWedge(final Wedge w) {
    actions.add(new Action() {
      @Override
      public void replayOn(final Initializer target) {
        target.exposeWedge(w);
      }
    });
  }

  @Override
  public void raiseWarning(final String warning) {
    actions.add(new Action() {
      @Override
      public void replayOn(final Initializer target) {
        target.raiseWarning(warning);
      }
    });
  }

  @Override
  public void addReference(final String reference) {
    actions.add(new Action() {
      @Override
      public void replayOn(final Initializer target) {
        target.addReference(reference);
      }
    });
  }

  /**
   * Replays every recorded call onto the given initializer, in order.
   *
   * @param target
   *          the initializer that should receive the calls
   */
  public void replayOn(final Initializer target) {
    for (Action a : actions) {
      a.replayOn(target);
    }
  }

  /**
   * Number of calls recorded. Used by tests to check that a rejected input
   * really did reach the initializer with nothing.
   *
   * @return
   *         count of recorded calls
   */
  public int recordedCallCount() {
    return actions.size();
  }
}
