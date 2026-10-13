package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  /**
   * The subprograms write their own files and leave the wedge's exposure
   * summary empty. Publishing the wedge made OutputSummaryText read it and
   * throw NullPointerException at the end of every MicroED, XFEL or Monte
   * Carlo run.
   */
  @Test
  public void testSubprogramWedgeIsNotPublished() {
    for (String program : new String[] {"EMED", "emsp", "XFEL", "MonteCarlo", "GOS"}) {
      Crystal crystal = crystalRunning(program);
      assertTrue(crystal.runsSubprogram(), program);

      Experiment e = new Experiment();
      RecordingOutput subscriber = new RecordingOutput();
      e.addObserver(subscriber);
      e.setCrystal(crystal);
      e.setBeam(b);
      e.exposeWedge(w);
      e.close();

      subscriber.assertCalls("publishCrystal", "publishBeam", "close");
    }
  }

  /**
   * Every writer RD3D turns on by default closes cleanly after a subprogram.
   * OutputVoxelDose and OutputVoxelFluences counted the images of the last
   * wedge they were sent, and threw NullPointerException when there was
   * none.
   */
  @Test
  public void testDefaultWritersCloseAfterASubprogram() throws Exception {
    Map<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, 2d);
    properties.put(Crystal.CRYSTAL_DIM_Y, 2d);
    properties.put(Crystal.CRYSTAL_DIM_Z, 1d);
    properties.put(Crystal.CRYSTAL_RESOLUTION, 1d);
    properties.put(Crystal.CRYSTAL_PROGRAM, "EMED");
    // MicroED itself writes to the working directory; this is about what
    // happens around it.
    Crystal crystal = new CrystalCuboid(properties) {
      @Override
      public void startMicroED(double xDim, double yDim, double zDim,
          Beam beam, Wedge wedge, CoefCalc coefCalc, String crystalType) {
      }
    };
    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_ENERGY, 300d);
    beamProperties.put(Beam.BEAM_COLL_H, 4d);
    beamProperties.put(Beam.BEAM_COLL_V, 4d);
    beamProperties.put(Beam.BEAM_EXPOSURE, 1d);

    Experiment e = new Experiment();
    OutputFactory factory = new OutputFactory();
    for (String module : new String[] {"SummaryCSV", "SummaryText",
        "FinalDoseStateCSV", "FinalDoseStateR", "RDECSV", "DWDs", "VoxelDose",
        "VoxelFluences", "ProgressIndicator"}) {
      e.addObserver(factory.createOutputSimple(module, new WriterString()));
    }
    e.setCrystal(crystal);
    e.setBeam(new BeamTophat(beamProperties));
    e.exposeWedge(w);
    e.close();
  }

  @Test
  public void testMXWedgeIsStillPublished() {
    assertFalse(c.runsSubprogram());
    assertFalse(crystalRunning("rd3d").runsSubprogram());
  }

  private static Crystal crystalRunning(final String program) {
    Map<Object, Object> properties = new HashMap<Object, Object>();
    properties.put(Crystal.CRYSTAL_DIM_X, 0d);
    properties.put(Crystal.CRYSTAL_DIM_Y, 0d);
    properties.put(Crystal.CRYSTAL_DIM_Z, 0d);
    properties.put(Crystal.CRYSTAL_PROGRAM, program);
    return new CrystalDummy(properties);
  }
}
