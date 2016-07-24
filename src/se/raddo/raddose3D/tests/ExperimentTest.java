package se.raddo.raddose3D.tests;

import org.mockito.InOrder;
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
    // arrange
    Experiment e = new Experiment();
    Output testsubscriber = mock(Output.class);

    // act
    e.addObserver(testsubscriber);
    e.setCrystal(c);
    e.setBeam(null); // Null values should be handled gracefully and ignored
    e.setCrystal(null);
    e.exposeWedge(null);
    e.close();

    // assert
    InOrder inOrder = inOrder(testsubscriber);
    inOrder.verify(testsubscriber).publishCrystal(c);
    inOrder.verify(testsubscriber).close();
    verify(testsubscriber, times(1)).publishCrystal(any(Crystal.class));
    verify(testsubscriber, never()).publishBeam(any(Beam.class));
    verify(testsubscriber, never()).publishWedge(any(Wedge.class));
    verify(testsubscriber, times(1)).close();
  }

  @Test
  public void testExperimentWithMultipleSubscribers() {
    // arrange
    Experiment e = new ExperimentDummy();
    Output testsubscriberOne = mock(Output.class);
    Output testsubscriberTwo = mock(Output.class);
    Output testsubscriberThree = mock(Output.class);
    
    // act
    e.addObserver(testsubscriberOne);
    e.setCrystal(c);
    e.addObserver(testsubscriberTwo);
    e.exposeWedge(w);
    e.setBeam(null); // Null values should be handled gracefully and ignored
    e.setCrystal(null);
    e.exposeWedge(null);
    e.addObserver(testsubscriberThree);
    e.setBeam(b);
    e.close();

    // assert
    InOrder inOrder = inOrder(testsubscriberOne);
    inOrder.verify(testsubscriberOne).publishCrystal(c);
    inOrder.verify(testsubscriberOne).publishWedge(w);
    inOrder.verify(testsubscriberOne).publishBeam(b);
    inOrder.verify(testsubscriberOne).close();
    
    inOrder = inOrder(testsubscriberTwo);
    inOrder.verify(testsubscriberTwo).publishWedge(w);
    inOrder.verify(testsubscriberTwo).publishBeam(b);
    inOrder.verify(testsubscriberTwo).close();
    
    inOrder = inOrder(testsubscriberThree);
    inOrder.verify(testsubscriberThree).publishBeam(b);
    inOrder.verify(testsubscriberThree).close();
    
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
