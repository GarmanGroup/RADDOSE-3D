package se.raddo.raddose3D;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Generates R code for visualizing the final dose distribution using the RGL
 * library.
 * 
 * @author Markus Gerstel
 */
public class OutputFinalDoseStateR implements Output {

  /** Where output should be directed to. */
  private final Writer w;

  /**
   * Private reference to the last seen crystal which will be inspected after
   * all exposure events on close().
   */
  private Crystal      crystal;

  /**
   * Generic property constructor for OutputDoseStateR output class.
   * Extracts all required information from a Map data structure.
   * *
   * Used properties:
   * OUTPUT_WRITER - writer class to which output will be directed.
   * 
   * @param properties
   *          Map of type <Object, Object> that contains all output properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Output} class.
   */
  public OutputFinalDoseStateR(final Map<Object, Object> properties) {
    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputDoseStatePOV: ");
    a.checkIsClass(properties.get(Output.OUTPUT_WRITER), Writer.class,
        "no writer class given");
    w = (Writer) properties.get(Output.OUTPUT_WRITER);
  }

  @Override
  public void publishCrystal(final Crystal c) {
    crystal = c;
  }

  @Override
  public void publishWedge(final Wedge wdg) {
  }

  @Override
  public void publishBeam(final Beam b) {
  }

  @Override
  public void close() {
    if (crystal == null) {
      System.err
          .println("OutputDoseStateR: No crystal object has been seen.");
      w.write("# OutputDoseStateR: No crystal object has been seen.");
      w.close();
      return;
    }

    int[] csize = crystal.getCrystSizeVoxels();
    w.write("# Crystal dose state visualization using R\n");
    w.write("# http://www.r-project.org/\n#\n");
    w.write("# Code generated "
        + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())
        + "\n");
    w.write("# Crystal size: "
        + csize[0] + " x " + csize[1]
        + " x " + csize[2] + " voxels\n");
    w.write("#\n\n");

    w.write("contourlevels <- c(0.1, 20, 30) # MGy\n");
    w.write("contourcolours <- c('lightblue', 'darkblue', 'red')\n");
    w.write("contouropacity <- c(0.2, 0.5, 1)\n\n");

    w.write("require(\"rgl\")\n");
    w.write("require(\"misc3d\")\n\n");

    w.write("# Three dimensional dose array (MGy)\n");
    w.write(String.format("dose <- array(0, c(%d, %d, %d))\n",
        csize[0], csize[1], csize[2]));
    for (int k = 0; k < csize[2]; k++) {
      w.write(String.format("dose[,,%d]<-c(", (k + 1)));
      for (int j = 0; j < csize[1]; j++) {
        for (int i = 0; i < csize[0]; i++) {
          if ((i != 0) || (j != 0)) {
            w.write(",");
          }
          float dose = (float) crystal.getDose(i, j, k);
          if (dose <= Float.MIN_VALUE) {
            w.write("0");
          } else {
            w.write("" + dose);
          }
        }
      }
      w.write(")\n");
    }

    w.write("contour3d(dose, level=contourlevels, color=contourcolours, "
        + "alpha=contouropacity)\n");
    w.write("# axes3d()\n");
    w.write("wire3d(translate3d("
        + String.format("scale3d(cube3d(),%d/2,%d/2,%d/2),",
            csize[0], csize[1], csize[2])
        + String.format("%d/2,%d/2,%d/2),", csize[0], csize[1], csize[2])
        + "col = 'grey')");

    crystal = null;
    w.close();
  }
}
