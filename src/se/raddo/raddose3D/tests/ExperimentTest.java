package se.raddo.raddose3D.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.Experiment;
import se.raddo.raddose3D.ExperimentDummy;
import se.raddo.raddose3D.Output;
import se.raddo.raddose3D.Wedge;

public class ExperimentTest {
  Crystal c      = new CrystalDummy();
  Wedge   w      = new Wedge(0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d);
  Beam    b      = new BeamDummy();

  @Test
  public void testExperimentSimple() {
    Experiment e = new Experiment();
    OutputTestSubscriber testsubscriber = new OutputTestSubscriber();

    e.addObserver(testsubscriber);

    // No message sent yet
    Assert.assertNull(testsubscriber.lastseenobject);
    Assert.assertNull(testsubscriber.lastseencrystal);
    Assert.assertNull(testsubscriber.lastseenbeam);
    Assert.assertNull(testsubscriber.lastseenwedge);
    Assert.assertEquals(testsubscriber.seenobjects, 0);
    Assert.assertEquals(testsubscriber.seenclose, 0);

    e.setCrystal(c);

    // One object sent
    Assert.assertEquals(testsubscriber.lastseenobject, c);
    Assert.assertEquals(testsubscriber.lastseencrystal, c);
    Assert.assertNull(testsubscriber.lastseenbeam);
    Assert.assertNull(testsubscriber.lastseenwedge);
    Assert.assertEquals(testsubscriber.seenobjects, 1);
    Assert.assertEquals(testsubscriber.seenclose, 0);

    // Null values should be handled gracefully and ignored
    e.setBeam(null);
    e.setCrystal(null);
    e.exposeWedge(null);

    // One object sent
    Assert.assertEquals(testsubscriber.lastseenobject, c);
    Assert.assertEquals(testsubscriber.lastseencrystal, c);
    Assert.assertNull(testsubscriber.lastseenbeam);
    Assert.assertNull(testsubscriber.lastseenwedge);
    Assert.assertEquals(testsubscriber.seenobjects, 1);
    Assert.assertEquals(testsubscriber.seenclose, 0);

    System.out.println("@Test - testExperimentSimple");
  }

  @Test
  public void testExperimentComplex() {
    Experiment e = new ExperimentDummy();
    OutputTestSubscriber testsubscriber = new OutputTestSubscriber();

    e.addObserver(testsubscriber);

    // No message sent yet
    Assert.assertNull(testsubscriber.lastseenobject);
    Assert.assertNull(testsubscriber.lastseencrystal);
    Assert.assertNull(testsubscriber.lastseenbeam);
    Assert.assertNull(testsubscriber.lastseenwedge);
    Assert.assertEquals(testsubscriber.seenobjects, 0);
    Assert.assertEquals(testsubscriber.seenclose, 0);

    e.setCrystal(c);

    // One object sent
    Assert.assertEquals(testsubscriber.lastseenobject, c);
    Assert.assertEquals(testsubscriber.lastseencrystal, c);
    Assert.assertNull(testsubscriber.lastseenbeam);
    Assert.assertNull(testsubscriber.lastseenwedge);
    Assert.assertEquals(testsubscriber.seenobjects, 1);
    Assert.assertEquals(testsubscriber.seenclose, 0);

    // Subscribe second listener
    e.addObserver(testsubscriber);

    e.exposeWedge(w);

    // Three objects sent (1 + 2x1)
    Assert.assertEquals(testsubscriber.lastseenobject, w);
    Assert.assertEquals(testsubscriber.lastseencrystal, c);
    Assert.assertNull(testsubscriber.lastseenbeam);
    Assert.assertEquals(testsubscriber.lastseenwedge, w);
    Assert.assertEquals(testsubscriber.seenobjects, 3);
    Assert.assertEquals(testsubscriber.seenclose, 0);

    // Null values should be handled gracefully and ignored
    e.setBeam(null);
    e.setCrystal(null);
    e.exposeWedge(null);

    // Three objects sent (1 + 2x1)
    Assert.assertEquals(testsubscriber.lastseenobject, w);
    Assert.assertEquals(testsubscriber.lastseencrystal, c);
    Assert.assertNull(testsubscriber.lastseenbeam);
    Assert.assertEquals(testsubscriber.lastseenwedge, w);
    Assert.assertEquals(testsubscriber.seenobjects, 3);
    Assert.assertEquals(testsubscriber.seenclose, 0);

    // Subscribe third listener
    e.addObserver(testsubscriber);

    e.setBeam(b);

    // Six objects sent (1 + 2x1 + 3x1)
    Assert.assertEquals(testsubscriber.lastseenobject, b);
    Assert.assertEquals(testsubscriber.lastseencrystal, c);
    Assert.assertEquals(testsubscriber.lastseenbeam, b);
    Assert.assertEquals(testsubscriber.lastseenwedge, w);
    Assert.assertEquals(testsubscriber.seenobjects, 6);
    Assert.assertEquals(testsubscriber.seenclose, 0);

    e.close();

    // Output flushed (3x 1)
    Assert.assertEquals(testsubscriber.lastseenobject, b);
    Assert.assertEquals(testsubscriber.lastseencrystal, c);
    Assert.assertEquals(testsubscriber.lastseenbeam, b);
    Assert.assertEquals(testsubscriber.lastseenwedge, w);
    Assert.assertEquals(testsubscriber.seenobjects, 6);
    Assert.assertEquals(testsubscriber.seenclose, 3);

    System.out.println("@Test - testExperimentComplex");
  }

  private class OutputTestSubscriber implements Output {
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
