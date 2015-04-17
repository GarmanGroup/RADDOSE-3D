package se.raddo.raddose3D.tests;

import static org.testng.Assert.*;
import org.testng.annotations.*;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.Experiment;
import se.raddo.raddose3D.ExperimentDummy;
import se.raddo.raddose3D.Output;
import se.raddo.raddose3D.Wedge;

public class ExperimentTest {
  Crystal c = new CrystalDummy();
  Wedge   w = new Wedge(0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d);
  Beam    b = new BeamDummy();

  @Test
  public void testExperimentSimple() {
    Experiment e = new Experiment();
    OutputTestSubscriber testsubscriber = new OutputTestSubscriber();

    e.addObserver(testsubscriber);

    // No message sent yet
    assertNull(testsubscriber.lastseenobject);
    assertNull(testsubscriber.lastseencrystal);
    assertNull(testsubscriber.lastseenbeam);
    assertNull(testsubscriber.lastseenwedge);
    assertEquals(testsubscriber.seenobjects, 0);
    assertEquals(testsubscriber.seenclose, 0);

    e.setCrystal(c);

    // One object sent
    assertEquals(testsubscriber.lastseenobject, c);
    assertEquals(testsubscriber.lastseencrystal, c);
    assertNull(testsubscriber.lastseenbeam);
    assertNull(testsubscriber.lastseenwedge);
    assertEquals(testsubscriber.seenobjects, 1);
    assertEquals(testsubscriber.seenclose, 0);

    // Null values should be handled gracefully and ignored
    e.setBeam(null);
    e.setCrystal(null);
    e.exposeWedge(null);

    // One object sent
    assertEquals(testsubscriber.lastseenobject, c);
    assertEquals(testsubscriber.lastseencrystal, c);
    assertNull(testsubscriber.lastseenbeam);
    assertNull(testsubscriber.lastseenwedge);
    assertEquals(testsubscriber.seenobjects, 1);
    assertEquals(testsubscriber.seenclose, 0);

    e.close();

    System.out.println("@Test - testExperimentSimple");
  }

  @Test
  public void testExperimentComplex() {
    Experiment e = new ExperimentDummy();
    OutputTestSubscriber testsubscriber = new OutputTestSubscriber();

    e.addObserver(testsubscriber);

    // No message sent yet
    assertNull(testsubscriber.lastseenobject);
    assertNull(testsubscriber.lastseencrystal);
    assertNull(testsubscriber.lastseenbeam);
    assertNull(testsubscriber.lastseenwedge);
    assertEquals(testsubscriber.seenobjects, 0);
    assertEquals(testsubscriber.seenclose, 0);

    e.setCrystal(c);

    // One object sent
    assertEquals(testsubscriber.lastseenobject, c);
    assertEquals(testsubscriber.lastseencrystal, c);
    assertNull(testsubscriber.lastseenbeam);
    assertNull(testsubscriber.lastseenwedge);
    assertEquals(testsubscriber.seenobjects, 1);
    assertEquals(testsubscriber.seenclose, 0);

    // Subscribe second listener
    e.addObserver(testsubscriber);

    e.exposeWedge(w);

    // Three objects sent (1 + 2x1)
    assertEquals(testsubscriber.lastseenobject, w);
    assertEquals(testsubscriber.lastseencrystal, c);
    assertNull(testsubscriber.lastseenbeam);
    assertEquals(testsubscriber.lastseenwedge, w);
    assertEquals(testsubscriber.seenobjects, 3);
    assertEquals(testsubscriber.seenclose, 0);

    // Null values should be handled gracefully and ignored
    e.setBeam(null);
    e.setCrystal(null);
    e.exposeWedge(null);

    // Three objects sent (1 + 2x1)
    assertEquals(testsubscriber.lastseenobject, w);
    assertEquals(testsubscriber.lastseencrystal, c);
    assertNull(testsubscriber.lastseenbeam);
    assertEquals(testsubscriber.lastseenwedge, w);
    assertEquals(testsubscriber.seenobjects, 3);
    assertEquals(testsubscriber.seenclose, 0);

    // Subscribe third listener
    e.addObserver(testsubscriber);

    e.setBeam(b);

    // Six objects sent (1 + 2x1 + 3x1)
    assertEquals(testsubscriber.lastseenobject, b);
    assertEquals(testsubscriber.lastseencrystal, c);
    assertEquals(testsubscriber.lastseenbeam, b);
    assertEquals(testsubscriber.lastseenwedge, w);
    assertEquals(testsubscriber.seenobjects, 6);
    assertEquals(testsubscriber.seenclose, 0);

    e.close();

    // Output flushed (3x 1)
    assertEquals(testsubscriber.lastseenobject, b);
    assertEquals(testsubscriber.lastseencrystal, c);
    assertEquals(testsubscriber.lastseenbeam, b);
    assertEquals(testsubscriber.lastseenwedge, w);
    assertEquals(testsubscriber.seenobjects, 6);
    assertEquals(testsubscriber.seenclose, 3);

    System.out.println("@Test - testExperimentComplex");
  }

  private static class OutputTestSubscriber implements Output {
    public long    seenobjects     = 0;
    public long    seenclose       = 0;
    public Object  lastseenobject  = null;
    public Crystal lastseencrystal = null;
    public Beam    lastseenbeam    = null;
    public Wedge   lastseenwedge   = null;

    @Override
    public void publishCrystal(Crystal c) {
      seenobjects = seenobjects + 1;
      lastseenobject = c;
      lastseencrystal = c;
    }

    @Override
    public void publishBeam(Beam b) {
      seenobjects = seenobjects + 1;
      lastseenobject = b;
      lastseenbeam = b;
    }

    @Override
    public void publishWedge(Wedge w) {
      seenobjects = seenobjects + 1;
      lastseenobject = w;
      lastseenwedge = w;
    }

    @Override
    public void close() {
      seenclose = seenclose + 1;
    }
  }
}
