package se.raddo.raddose3D.tests;

import org.testng.annotations.*;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.Experiment;
import se.raddo.raddose3D.ExperimentDummy;
import se.raddo.raddose3D.Output;
import se.raddo.raddose3D.Wedge;
import static org.mockito.Mockito.*;

public class ExperimentTest {
  private final Crystal c = mock(Crystal.class);
  private final Wedge   w = mock(Wedge.class);
  private final Beam    b = mock(Beam.class);
  
  @Test
  public void testExperimentWithCrystalAndNullValues() {
    Experiment e = new Experiment();
    Output testsubscriber = mock(Output.class);

    e.addObserver(testsubscriber);

    // No message sent yet
    verify(testsubscriber, never()).publishCrystal(any(Crystal.class));
    verify(testsubscriber, never()).publishBeam(any(Beam.class));
    verify(testsubscriber, never()).publishWedge(any(Wedge.class));

    e.setCrystal(c);
    // Null values should be handled gracefully and ignored
    e.setBeam(null);
    e.setCrystal(null);
    e.exposeWedge(null);
    
    // One object sent
    verify(testsubscriber, times(1)).publishCrystal(any(Crystal.class));
    verify(testsubscriber, times(1)).publishCrystal(c);
    verify(testsubscriber, never()).publishBeam(any(Beam.class));
    verify(testsubscriber, never()).publishWedge(any(Wedge.class));    
    verify(testsubscriber, never()).close();
    
    e.close();

    // And closed
    verify(testsubscriber, times(1)).publishCrystal(any(Crystal.class));
    verify(testsubscriber, never()).publishBeam(any(Beam.class));
    verify(testsubscriber, never()).publishWedge(any(Wedge.class));
    verify(testsubscriber, times(1)).close();
  }

  /* This may be a bit overspecified... */
  @Test
  public void testExperimentWithTwoSubscribers() {
    Output testsubscriberOne = mock(Output.class);
    Output testsubscriberTwo = mock(Output.class);
    Output testsubscriberThree = mock(Output.class);
    
    Experiment e = new ExperimentDummy();
    e.addObserver(testsubscriberOne);
    e.setCrystal(c);
    e.addObserver(testsubscriberTwo);

    // One object sent to first subscriber, none to second
    verify(testsubscriberOne, times(1)).publishCrystal(any(Crystal.class));
    verify(testsubscriberOne, times(1)).publishCrystal(c);
    verify(testsubscriberOne, never()).publishWedge(any(Wedge.class));
    verify(testsubscriberTwo, never()).publishWedge(any(Wedge.class));

    e.exposeWedge(w);
    // Null values should be handled gracefully and ignored
    e.setBeam(null);
    e.setCrystal(null);
    e.exposeWedge(null);

    // Two objects sent to first subscriber, one to second
    verify(testsubscriberOne, never()).publishBeam(any(Beam.class));
    verify(testsubscriberOne, times(1)).publishWedge(any(Wedge.class));
    verify(testsubscriberOne, times(1)).publishWedge(w);
    verify(testsubscriberTwo, never()).publishBeam(any(Beam.class));
    verify(testsubscriberTwo, times(1)).publishWedge(any(Wedge.class));
    verify(testsubscriberTwo, times(1)).publishWedge(w);

    // Subscribe third listener
    e.addObserver(testsubscriberThree);
    e.setBeam(b);

    // Check subscriber status
    verify(testsubscriberOne, times(1)).publishBeam(any(Beam.class));
    verify(testsubscriberOne, times(1)).publishBeam(b);
    verify(testsubscriberOne, times(1)).publishWedge(any(Wedge.class));
    verify(testsubscriberOne, never()).close();
    
    verify(testsubscriberTwo, times(1)).publishBeam(any(Beam.class));
    verify(testsubscriberTwo, times(1)).publishBeam(b);
    verify(testsubscriberTwo, times(1)).publishWedge(any(Wedge.class));
    verify(testsubscriberTwo, never()).close();

    verify(testsubscriberThree, times(1)).publishBeam(any(Beam.class));
    verify(testsubscriberThree, times(1)).publishBeam(b);
    verify(testsubscriberThree, never()).publishWedge(any(Wedge.class));
    verify(testsubscriberThree, never()).close();

    e.close();

    // Check subscriber status
    verify(testsubscriberOne, times(1)).publishCrystal(any(Crystal.class));
    verify(testsubscriberOne, times(1)).publishBeam(any(Beam.class));
    verify(testsubscriberOne, times(1)).publishWedge(any(Wedge.class));
    verify(testsubscriberOne, times(1)).close();
    
    verify(testsubscriberTwo, never()).publishCrystal(any(Crystal.class));
    verify(testsubscriberTwo, times(1)).publishBeam(any(Beam.class));
    verify(testsubscriberTwo, times(1)).publishWedge(any(Wedge.class));
    verify(testsubscriberTwo, times(1)).close();

    verify(testsubscriberThree, never()).publishCrystal(any(Crystal.class));
    verify(testsubscriberThree, times(1)).publishBeam(any(Beam.class));
    verify(testsubscriberThree, never()).publishWedge(any(Wedge.class));
    verify(testsubscriberThree, times(1)).close();
  }
}
