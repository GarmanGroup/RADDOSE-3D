package se.raddo.raddose3D;

import java.util.Map;

public class OutputDWDs implements Output{
  /** Where output should be directed to. */
  private final Writer         w;
 
  /** ExposureSummary object producing summary metrics from exposures. */
  private ExposureSummary      expSummary;

  /** 1-based index of the wedge just completed (incremented in publishWedge). */
  private int                  wedgeCounter;


  public OutputDWDs(final Map<Object, Object> properties){
    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputDWDs: ");
    a.checkIsClass(properties.get(Output.OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) properties.get(Output.OUTPUT_WRITER);
    
    w.write("Wedge, RADDOSE Image Number, DWD Angle, DWD, Vol, 1A RDE, 2A RDE, 3A RDE, 4A RDE, max res RDE\n");
  }
  
  
  @Override
  public void publishCrystal(Crystal c) {
    expSummary = c.getExposureSummary();
    wedgeCounter = 0;
  }

  @Override
  public void publishBeam(Beam b) {
    // No implementation required.
  }

  @Override
  public void publishWedge(Wedge wdg) {
    wedgeCounter++;
    double[] imageDWD = expSummary.getDWDs();
    double[] angleDWD = expSummary.getAngleDWDs();
    double[] imageVol = expSummary.getImageVol();
    double[][] imageRDE = expSummary.getRDEs();
    for (int i = 0; i < angleDWD.length; i++) {
      int imageInWedge = i + 1;
      double angle = angleDWD[i] * (180 / Math.PI);
      w.write(wedgeCounter + ",");
      w.write(imageInWedge + ",");
      w.write(angle + ",");
      w.write(imageDWD[i] + ",");
      w.write(imageVol[i] + ",");
      w.write(imageRDE[i][1] + ",");
      w.write(imageRDE[i][2] + ",");
      w.write(imageRDE[i][3] + ",");
      w.write(imageRDE[i][4] + ",");
      w.write(imageRDE[i][0] + "\n");
    }
  }

  @Override
  public void close() {
    expSummary = null;
    w.close();
  }
}
