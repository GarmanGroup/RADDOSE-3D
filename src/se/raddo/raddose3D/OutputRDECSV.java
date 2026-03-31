package se.raddo.raddose3D;

import java.util.Map;

public class OutputRDECSV implements Output{
  /** Where output should be directed to. */
  private final Writer         w;
 
  /** ExposureSummary object producing summary metrics from exposures. */
  private ExposureSummary      expSummary;

  /** 1-based index of the wedge just completed (incremented in publishWedge). */
  private int                  wedgeCounter;


  public OutputRDECSV(final Map<Object, Object> properties){
    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputRDECSV: ");
    a.checkIsClass(properties.get(Output.OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) properties.get(Output.OUTPUT_WRITER);
    
    w.write("Wedge, Image Number, Angle, Avg RDE, min RDE\n");
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
    double[][] arrayRDE = expSummary.getWeightedRDEArray();
    double[][] minArrayRDE = expSummary.getMinRDEArray();
    for (int i = 0; i < arrayRDE.length; i++) {
      int imageInWedge = i + 1;
      double angle = arrayRDE[i][0] * (180 / Math.PI);
      w.write(wedgeCounter + ",");
      w.write(imageInWedge + ",");
      w.write(angle + ",");
      w.write(arrayRDE[i][1] + ",");
      w.write(minArrayRDE[i][1] + "\n");
    }
  }

  @Override
  public void close() {
    expSummary = null;
    w.close();
  }

}
