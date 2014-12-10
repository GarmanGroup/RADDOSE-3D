package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Returns vertices and indices for use by CrystalPolyhedron.
 * Vertices have not been rotated by the initial rotation angles
 * and this must be executed by the Crystal class. This simply
 * imports data from a text file of OBJ file type.
 */
public class ImportWireframeObj implements ImportWireframe {
  /** Import file name of OBJ file type. */
  private final String wireframeFileName;

  /**
   * Sets up a wireframe object and sets file name for later
   * importing.
   * 
   * @param filename .obj filename.
   */
  public ImportWireframeObj(final String filename) {
    if (filename == null) {
      System.out.println("Filename not found, please set ModelFile");
      throw new RuntimeException();
    }

    wireframeFileName = filename;
  }

  /**
   * Opens file and takes any line from the file beginning
   * with the identifier token (e.g. "v " or "f ") and splits
   * into the component numbers, and returns a double ListArray
   * containing these values.
   * 
   * @param identifier at beginning of lines to be interested in, two
   *          characters.
   * @return List<Triad> array containing triplets of doubles.
   */
  public List<Triad> getTriadList(final String identifier) {
    BufferedReader br = null;
    InputStreamReader isr = null;

    List<Triad> triads = new ArrayList<Triad>();

    try {
      FileInputStream is = new FileInputStream(wireframeFileName);
      isr = new InputStreamReader(is);
      br = new BufferedReader(isr);
    } catch (FileNotFoundException e) {
      // give up
      System.out.println("Cannot find import file for wireframe.");
    }

    String line;

    try {
      while ((line = br.readLine()) != null) {
        // only interested in lines beginning with "v "
        if (identifier.equals(line.substring(0, 2))) {
          String[] components = line.split(" ", -1);

          // if components are not 4, then this was a false positive
          if (components.length < 4)
            continue;

          // components should be: "v", x, y, z.

          // TODO: there could be a localization bug here
          Triad newVertex = new Triad(
              Double.parseDouble(components[1]),
              Double.parseDouble(components[2]),
              Double.parseDouble(components[3]));

          triads.add(newVertex);
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (isr != null) {
      try {
        isr.close();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    return triads;
  }

  @Override
  public double[][] getVertices() {
    List<Triad> triads = getTriadList("v "); // vertex

    double[][] returnVertices = new double[triads.size()][3];

    for (int i = 0; i < triads.size(); i++) {
      System.arraycopy(triads.get(i).getXYZ(), 0, returnVertices[i], 0, 3);
    }

    return returnVertices;
  }

  @Override
  public int[][] getIndices() {
    List<Triad> triads = getTriadList("f "); // face

    int[][] returnIndices = new int[triads.size()][3];

    for (int i = 0; i < triads.size(); i++) {
      double[] xyz = triads.get(i).getXYZ();
      returnIndices[i][0] = (int) xyz[0];
      returnIndices[i][1] = (int) xyz[1];
      returnIndices[i][2] = (int) xyz[2];
    }

    return returnIndices;
  }

  /**
   * Small triad class which looks after xyz coordinates before
   * being sent back to the caller object.
   */
  class Triad {
    /** xyz coordinates. */
    private double[] xyz = new double[3];

    /**
     * constructor with particular xyz.
     * 
     * @param newXYZ new xyz coordinates
     */
    public Triad(final double[] newXYZ) {
      System.arraycopy(newXYZ, 0, xyz, 0, 3);
    }

    /**
     * constructor with particular xyz.
     * 
     * @param x x coordinate.
     * @param y y coordinate.
     * @param z z coordinate.
     */
    public Triad(final double x, final double y, final double z) {
      xyz[0] = x;
      xyz[1] = y;
      xyz[2] = z;
    }

    /**
     * xyz setter.
     * 
     * @param newXYZ new xyz coordinates.
     */
    @Deprecated
    public void setXyz(final double[] newXYZ) {
      System.arraycopy(newXYZ, 0, xyz, 0, 3);
    }

    /**
     * xyz getter.
     * 
     * @return stored xyz coordinates
     */
    public double[] getXYZ() {
      double[] returnXYZ = new double[3];
      System.arraycopy(xyz, 0, returnXYZ, 0, 3);
      return returnXYZ;
    }
  }
}
