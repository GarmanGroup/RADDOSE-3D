package se.raddo.raddose3D.tests;
/** Performs basic tests on Experimental Beam classes. Includes:
 *  
 *  @author Oliver Zeldin       
 * */

import org.testng.Assert;
import org.testng.annotations.*;

import se.raddo.raddose3D.BeamExperimental;

public class BeamExperimentalTest {

  private double tolerance = 1e-10;

 // private Double[][] oddBeamVerticalChange   = new Double[5][5];
 // private Double[][] oddBeamHorizontalChange = new Double[5][5];
 // private Double[][] oddBeamBothChange       = new Double[5][5];

  private Double[][] evenBeamVerticalChange   = new Double[4][4];
  private Double[][] evenBeamHorizontalChange = new Double[4][4];
  private Double[][] evenBeamBothChange       = new Double[4][4];
  
  private Double sumVert = 0.0, sumHoriz = 0.0, sumBoth = 0.0;
  
  
  private Double defaultE =  10.0;
  private Double defaultFlux = 1e12;
  private Double defaultVertSize = 5.0;
  private Double defaultHorizSize = 5.0;

  public BeamExperimentalTest() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        evenBeamVerticalChange[i][j]   = (double) i + 1;
        sumVert += (double) i + 1;
        evenBeamHorizontalChange[i][j] = (double) j + 1;
        sumHoriz += (double) j + 1;
        evenBeamBothChange[i][j]       = (double) i + j + 2;
        sumBoth += (double) i + j + 2;
      }
    }
//  //  Don't think I need these tests 08/01/13

//    for (int i = 0 ; i < 5; i++){
//      for (int j = 0; j<5; j++){
//        oddBeamVerticalChange[i][j]   = (double) i + 1;
//        oddBeamHorizontalChange[i][j] = (double) j + 1;
//        oddBeamBothChange[i][j]       = (double) i + j + 2;
//      }
//    }
//    BeamExperimental oddVert = new BeamExperimental(oddBeamVerticalChange, 
//        defaultFlux, defaultE, defaultHorizSize, defaultVertSize);
//    BeamExperimental oddHoriz = new BeamExperimental(oddBeamHorizontalChange, 
//        defaultFlux, defaultE, defaultHorizSize, defaultVertSize);
//    BeamExperimental oddBoth = new BeamExperimental(oddBeamBothChange, 
//        defaultFlux, defaultE, defaultHorizSize, defaultVertSize);

  }


  @Test
  /** Checks that the halfway points between nodes are correct */
  public void testHorizontal(){
    BeamExperimental evenVert = new BeamExperimental(evenBeamVerticalChange, 
        defaultFlux, defaultE, defaultHorizSize, defaultVertSize);
    // Intensity profile should be constant across rows, 
    // change from 0 to 4 across columns

    double normF = defaultFlux / (sumHoriz*defaultHorizSize*defaultVertSize);
    
    // Check along X
    Assert.assertTrue(evenVert.beamIntensity(0,0,0)- 2.5*normF < tolerance);
    Assert.assertTrue(evenVert.beamIntensity(5,8,0)- 3.5*normF < tolerance);
    Assert.assertTrue(evenVert.beamIntensity(0,-10,0)- 1.25*normF < tolerance);
    // Check along Y
    Assert.assertTrue(evenVert.beamIntensity(5,0,0)- 3.5*normF < tolerance);
    Assert.assertTrue(evenVert.beamIntensity(-2.5,5,0) - 2*normF < tolerance);
  }

  @Test
  /** Checks that the halfway points between nodes are correct */
  public void testVertical(){
    BeamExperimental evenHoriz = new BeamExperimental(evenBeamHorizontalChange, 
        defaultFlux, defaultE, defaultHorizSize, defaultVertSize);
    // Intensity profile should be constant across rows, 
    // change from 0 to 4 across columns
    
    double normF = defaultFlux / (sumVert*defaultHorizSize*defaultVertSize);

    
    // Check along X
    Assert.assertTrue(evenHoriz.beamIntensity(0,0,0)- 2.5*normF < tolerance);
    Assert.assertTrue(evenHoriz.beamIntensity(8,5,0)- 3.5*normF < tolerance);
    Assert.assertTrue(evenHoriz.beamIntensity(-10,0,0)- 1.25*normF < tolerance);
    // Check along Y
    Assert.assertTrue(evenHoriz.beamIntensity(0,5,0)- 3.5*normF < tolerance);
    Assert.assertTrue(evenHoriz.beamIntensity(5,-2.5,0) - 2*normF < tolerance);
 }
  
  @Test
  /** Checks that the halfway points between nodes are correct */
  public void testBoth(){
    BeamExperimental evenBoth = new BeamExperimental(evenBeamBothChange, 
        defaultFlux, defaultE, defaultHorizSize, defaultVertSize);    // Intensity profile should be constant across rows, 
    
    double normF = defaultFlux / (sumBoth*defaultHorizSize*defaultVertSize);

    
    // Check along X
    Assert.assertTrue(evenBoth.beamIntensity(0,0,0)- 5*normF < tolerance);
    Assert.assertTrue(evenBoth.beamIntensity(5,0,0)- 6*normF < tolerance);
    Assert.assertTrue(evenBoth.beamIntensity(2.5,0,0)- 5.5*normF < tolerance);
    
    // Check along Y
    Assert.assertTrue(evenBoth.beamIntensity(0,-5,0)- 4*normF < tolerance);
    Assert.assertTrue(evenBoth.beamIntensity(0,-2.5,0) - 4.5*normF < tolerance);
    
    // Check along both
    Assert.assertTrue(evenBoth.beamIntensity(5,-5,0)- 5.5*normF < tolerance);
    Assert.assertTrue(evenBoth.beamIntensity(7.5,-2.5,0) - 6*normF < tolerance);    
    
    System.out.print("ok");
  }
}
