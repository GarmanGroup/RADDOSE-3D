package se.raddo.raddose3D;

import java.util.Map;

public class OutputDWDs implements Output{
  /** Where output should be directed to. */
  private final Writer         w;
 
  /** ExposureSummary object producing summary metrics from exposures. */
  private ExposureSummary      expSummary;


  public OutputDWDs(final Map<Object, Object> properties){
    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputDWDs: ");
    a.checkIsClass(properties.get(Output.OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) properties.get(Output.OUTPUT_WRITER);
    
    w.write("RADDOSE Image Number, DWD Angle, DWD\n");
  }
  
  
  @Override
  public void publishCrystal(Crystal c) {
 // TODO Auto-generated method stub
    expSummary = c.getExposureSummary();
  }

  @Override
  public void publishBeam(Beam b) {
    // TODO Auto-generated method stub

  }

  @Override
  public void publishWedge(Wedge w) {
    // TODO Auto-generated method stub


  }

  @Override
  public void close() {
    double[] imageDWD = expSummary.getDWDs();
    double[] angleDWD = expSummary.getAngleDWDs();
    for(int i = 0; i < angleDWD.length; i++) {
      double image = i+1;
      double angle = angleDWD[i] * (180/Math.PI);
      w.write(image + ",");
      w.write(angle + ",");
      w.write(imageDWD[i] + "\n");
    }
    expSummary = null;
    w.close();
  }
}
