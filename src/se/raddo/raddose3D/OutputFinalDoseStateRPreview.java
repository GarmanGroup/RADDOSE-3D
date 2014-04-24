package se.raddo.raddose3D;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Generates R code for visualizing the final dose distribution in a series of
 * static images for the web service implementation.
 * A maximum grid size is set to limit the potential impact of a single job
 * on a shared system.
 * 
 * @author Markus Gerstel
 */
public class OutputFinalDoseStateRPreview implements Output {

  /** Where output should be directed to. */
  private final Writer     w;

  /**
   * Private reference to the last seen crystal which will be inspected after
   * all exposure events on close().
   */
  private Crystal          crystal;

  /** Maximum grid length output. */
  private static final int RESOLUTION_LIMIT = 91;

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
  public OutputFinalDoseStateRPreview(final Map<Object, Object> properties) {
    // Check for valid parameters
    Assertions a = new Assertions("Could not create OutputDoseStateRPreview: ");
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
          .println("OutputDoseStateRPreview: No crystal object has been seen.");
      w.write("# OutputDoseStateRPreview: No crystal object has been seen.");
      w.close();
      return;
    }

    int[] csize = crystal.getCrystSizeVoxels();
    w.write("# Crystal dose state visualization using R\n");
    w.write("# http://www.r-project.org/\n#\n");
    w.write("# Code generated "
        + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())
        + "\n");
    w.write(String.format("# Crystal size: %dx%dx%d voxels\n",
        csize[0], csize[1], csize[2]));

    // Limit to voxel numbers to keep resource requirements under control
    int[] csizel = new int[3];
    csizel[0] = csize[0];
    csizel[1] = csize[1];
    csizel[2] = csize[2];

    if (csize[0] > RESOLUTION_LIMIT) {
      csizel[0] = RESOLUTION_LIMIT;
    }
    if (csize[1] > RESOLUTION_LIMIT) {
      csizel[1] = RESOLUTION_LIMIT;
    }
    if (csize[2] > RESOLUTION_LIMIT) {
      csizel[2] = RESOLUTION_LIMIT;
    }

    w.write(String.format("# Reduced grid size: %dx%dx%d voxels\n",
        csizel[0], csizel[1], csizel[2]));
    w.write("#\n\n");

    w.write("contourlevels <- c(0.1, 20, 30) # MGy\n");
    w.write("contourcolours <- c('lightblue', 'darkblue', 'red')\n");
    w.write("contouropacity <- c(0.2, 0.5, 1)\n\n");

    w.write("require(\"misc3d\")\n\n");

    w.write("# Three dimensional dose array (MGy)\n");
    w.write(String.format("dose <- array(0, c(%d, %d, %d))\n",
        csizel[0], csizel[1], csizel[2]));
    for (int k = 0; k < csizel[2]; k++) {
      w.write(String.format("dose[,,%d]<-c(", (k + 1)));
      for (int j = 0; j < csizel[1]; j++) {
        for (int i = 0; i < csizel[0]; i++) {
          if ((i != 0) || (j != 0)) {
            w.write(",");
          }

          // Could put fancy interpolation algorithm here.
          // Or just trilinear interpolation.
          // For now nearest neighbour will do.

          float trueCoordinateX = i * csize[0] / csizel[0];
          float trueCoordinateY = j * csize[1] / csizel[1];
          float trueCoordinateZ = k * csize[2] / csizel[2];

          double dose = crystal.getDose(
              Math.round(trueCoordinateX),
              Math.round(trueCoordinateY),
              Math.round(trueCoordinateZ));

          if (dose <= Double.MIN_VALUE) {
            w.write("0");
          } else {
            w.write(String.format("%.4e", dose));
          }
        }
      }
      w.write(")\n");
    }

    w.write("\n");
    generateBoxFunctions();

    w.write("boundingBox <- cuboidFrame(1,1,1,"
        + "dim(dose)[1],dim(dose)[2],dim(dose)[3],"
        + "lwd=(max(dim(dose))-1)/1000, alpha=0.07)\n");
    w.write("doseMap <- contour3d(dose, level=contourlevels,"
        + "color=contourcolours, alpha=contouropacity, engine='none')\n");

    w.write("doseMap <- tryCatch({"
        + "contour3d(dose, level=contourlevels,color=contourcolours, "
        + "alpha=contouropacity, engine='none')"
        + "}, error = function(e) list())\n");
    w.write("if (class(doseMap)=='Triangles3D') { doseMap<-list(doseMap) }\n");

    w.write("mergedScene <- c(boundingBox, doseMap)\n");

    w.write("render <- function(image) {\n"
        + "par(mar=c(0,0,0,0))\n"
        + "drawScene(mergedScene, screen=list(z=40+image, x=-60),"
        + String.format("aspect=c(%d/%d,%d/%d)",
            csize[1], csize[0], csize[2], csize[0])
        + ")\n"
        + "}\n");

    //    w.write("wire3d(translate3d("
    //        + String.format("scale3d(cube3d(),%d/2,%d/2,%d/2),",
    //            csize[0], csize[1], csize[2])
    //        + String.format("%d/2,%d/2,%d/2),", csize[0], csize[1], csize[2])
    //        + "col = 'grey')");

    crystal = null;
    w.close();
  }

  /**
   * Generates R code for drawing wireframes of 3d boxes using triangles.
   */
  private void generateBoxFunctions() {
    w.write("cuboidTrianglesFaces <- array(0, c(3, 12))\n");
    w.write("cuboidTrianglesFaces[, 1] <- c(1, 2, 3)\n");
    w.write("cuboidTrianglesFaces[, 2] <- c(1, 3, 4)\n");
    w.write("cuboidTrianglesFaces[, 3] <- c(2, 6, 7)\n");
    w.write("cuboidTrianglesFaces[, 4] <- c(2, 3, 7)\n");
    w.write("cuboidTrianglesFaces[, 5] <- c(4, 3, 7)\n");
    w.write("cuboidTrianglesFaces[, 6] <- c(4, 7, 8)\n");
    w.write("cuboidTrianglesFaces[, 7] <- c(2, 5, 6)\n");
    w.write("cuboidTrianglesFaces[, 8] <- c(1, 2, 5)\n");
    w.write("cuboidTrianglesFaces[, 9] <- c(1, 4, 5)\n");
    w.write("cuboidTrianglesFaces[,10] <- c(4, 5, 8)\n");
    w.write("cuboidTrianglesFaces[,11] <- c(5, 6, 8)\n");
    w.write("cuboidTrianglesFaces[,12] <- c(6, 7, 8)\n");

    w.write("cuboidTriangles <- function(x1,y1,z1,x2,y2,z2,"
        + "color='black', alpha=1) {\n");
    w.write(" vertices <- array(0, c(3, 8))\n");
    w.write(" vertices[,1] <- c(x1, y1, z1)\n");
    w.write(" vertices[,2] <- c(x2, y1, z1)\n");
    w.write(" vertices[,3] <- c(x2, y2, z1)\n");
    w.write(" vertices[,4] <- c(x1, y2, z1)\n");
    w.write(" vertices[,5] <- c(x1, y1, z2)\n");
    w.write(" vertices[,6] <- c(x2, y1, z2)\n");
    w.write(" vertices[,7] <- c(x2, y2, z2)\n");
    w.write(" vertices[,8] <- c(x1, y2, z2)\n");
    w.write(" makeTriangles(vertices, cuboidTrianglesFaces,"
        + "color=color, material='dull', alpha=alpha)\n");
    w.write("}\n\n");

    w.write("cuboidFrame <- function(x1,y1,z1,x2,y2,z2,lwd=0.1,...) {\n");
    w.write(" l01 <- cuboidTriangles(x1-lwd,y1-lwd,z1-lwd,"
        + "x2+lwd,y1+lwd,z1+lwd,...)\n");
    w.write(" l02 <- cuboidTriangles(x2-lwd,y1-lwd,z1-lwd,"
        + "x2+lwd,y2+lwd,z1+lwd,...)\n");
    w.write(" l03 <- cuboidTriangles(x1-lwd,y2-lwd,z1-lwd,"
        + "x2+lwd,y2+lwd,z1+lwd,...)\n");
    w.write(" l04 <- cuboidTriangles(x1-lwd,y1-lwd,z1-lwd,"
        + "x1+lwd,y2+lwd,z1+lwd,...)\n");
    w.write(" l05 <- cuboidTriangles(x2-lwd,y1-lwd,z1-lwd,"
        + "x2+lwd,y1+lwd,z2+lwd,...)\n");
    w.write(" l06 <- cuboidTriangles(x2-lwd,y1-lwd,z2-lwd,"
        + "x2+lwd,y2+lwd,z2+lwd,...)\n");
    w.write(" l07 <- cuboidTriangles(x2-lwd,y2-lwd,z1-lwd,"
        + "x2+lwd,y2+lwd,z2+lwd,...)\n");
    w.write(" l08 <- cuboidTriangles(x1-lwd,y2-lwd,z2-lwd,"
        + "x2+lwd,y2+lwd,z2+lwd,...)\n");
    w.write(" l09 <- cuboidTriangles(x1-lwd,y2-lwd,z1-lwd,"
        + "x1+lwd,y2+lwd,z2+lwd,...)\n");
    w.write(" l10 <- cuboidTriangles(x1-lwd,y1-lwd,z1-lwd,"
        + "x1+lwd,y1+lwd,z2+lwd,...)\n");
    w.write(" l11 <- cuboidTriangles(x1-lwd,y1-lwd,z2-lwd,"
        + "x2+lwd,y1+lwd,z2+lwd,...)\n");
    w.write(" l12 <- cuboidTriangles(x1-lwd,y1-lwd,z2-lwd,"
        + "x1+lwd,y2+lwd,z2+lwd,...)\n");
    w.write(" triangles <- list(l01, l02, l03, l04, l05, l06,"
        + " l07, l08, l09, l10, l11, l12)\n");
    w.write("}\n\n");
  }
}
