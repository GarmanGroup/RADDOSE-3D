package se.raddo.raddose3D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Tests that {@link Experiment} fans events out to its subscribers in the right
 * order, and that null values are ignored rather than propagated.
 * <p>
 * The original version of these tests used Mockito 1.9.5, which cannot run on
 * Java 9 or newer. They assert only which callbacks fired, in what order, and
 * with which argument, so {@link RecordingOutput} expresses them directly.
 */
public class ExperimentTest {

  private final Crystal c = new CrystalDummy();
  private final Wedge   w = new Wedge(0d, 90d, 0d, 100d,
                                      null, null, null, null,
                                      null, null, null, null);
  private final Beam    b = new BeamDummy();

  @Test
  public void testExperimentWithCrystalAndNullValues() {
    // arrange
    Experiment e = new Experiment();
    RecordingOutput subscriber = new RecordingOutput();

    // act
    e.addObserver(subscriber);
    e.setCrystal(c);
    e.setBeam(null); // Null values should be handled gracefully and ignored
    e.setCrystal(null);
    e.exposeWedge(null);
    e.close();

    // assert
    subscriber.assertCalls("publishCrystal", "close");
    assertSame(c, subscriber.argumentOf("publishCrystal", 0),
        "the registered crystal should be the one published");
    subscriber.assertCallCount("publishCrystal", 1);
    subscriber.assertNeverCalled("publishBeam");
    subscriber.assertNeverCalled("publishWedge");
    subscriber.assertCallCount("close", 1);
  }

  @Test
  public void testExperimentWithMultipleSubscribers() {
    // arrange
    Experiment e = new ExperimentDummy();
    RecordingOutput one   = new RecordingOutput();
    RecordingOutput two   = new RecordingOutput();
    RecordingOutput three = new RecordingOutput();

    // act
    e.addObserver(one);
    e.setCrystal(c);
    e.addObserver(two);
    e.exposeWedge(w);
    e.setBeam(null); // Null values should be handled gracefully and ignored
    e.setCrystal(null);
    e.exposeWedge(null);
    e.addObserver(three);
    e.setBeam(b);
    e.close();

    // assert: a subscriber only sees events published after it subscribed
    one.assertCalls("publishCrystal", "publishWedge", "publishBeam", "close");
    two.assertCalls("publishWedge", "publishBeam", "close");
    three.assertCalls("publishBeam", "close");

    assertSame(c, one.argumentOf("publishCrystal", 0), "crystal published to first subscriber");
    assertSame(w, one.argumentOf("publishWedge", 0), "wedge published to first subscriber");
    assertSame(b, one.argumentOf("publishBeam", 0), "beam published to first subscriber");

    one.assertCallCount("publishCrystal", 1);
    one.assertCallCount("publishBeam", 1);
    one.assertCallCount("publishWedge", 1);
    one.assertCallCount("close", 1);

    two.assertNeverCalled("publishCrystal");
    two.assertCallCount("publishBeam", 1);
    two.assertCallCount("publishWedge", 1);
    two.assertCallCount("close", 1);

    three.assertNeverCalled("publishCrystal");
    three.assertCallCount("publishBeam", 1);
    three.assertNeverCalled("publishWedge");
    three.assertCallCount("close", 1);
  }
}
