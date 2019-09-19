package se.raddo.raddose3D;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class OutputRDECSV implements Output{
  /** Where output should be directed to. */
  private final Writer         w;
 
  /** ExposureSummary object producing summary metrics from exposures. */
  private ExposureSummary      expSummary;


  public OutputRDECSV(final Map<Object, Object> properties){
    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputRDECSV: ");
    a.checkIsClass(properties.get(Output.OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) properties.get(Output.OUTPUT_WRITER);
    
    w.write("Image Number, Angle, Avg RDE, min RDE\n");
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
    double[][] arrayRDE = expSummary.getWeightedRDEArray();
    double[][] minArrayRDE = expSummary.getMinRDEArray();
    for(int i = 0; i < arrayRDE.length; i++) {
      double image = i+1;
      double angle = arrayRDE[i][0] * (180/Math.PI);
      w.write(image + ",");
      w.write(angle + ",");
      w.write(arrayRDE[i][1] + ",");
      w.write(minArrayRDE[i][1] + "\n");
    }
    expSummary = null;
    w.close();
  }

}
