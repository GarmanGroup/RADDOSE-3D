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
 * 
 * @author magd3052
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

          double[] xyz = new double[3];

          for (int i = 0; i < 3; i++)
            xyz[i] = Double.parseDouble(components[i + 1]);

          Triad newVertex = new Triad(xyz);

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
      System.arraycopy(triads.get(i).getXyz(), 0, returnVertices[i], 0, 3);
    }

    return returnVertices;
  }

  @Override
  public int[][] getIndices() {
    List<Triad> triads = getTriadList("f "); // face

    int[][] returnIndices = new int[triads.size()][3];

    for (int i = 0; i < triads.size(); i++) {
      System.arraycopy(triads.get(i).getXyz(), 0, returnIndices[i], 0, 3);
    }

    return returnIndices;
  }

  /**
   * Small triad class which looks after xyz coordinates before
   * being sent back to the caller object.
   * 
   * @author magd3052
   */
  class Triad {
    /** xyz coordinates. */
    private double[] xyz = new double[3];

    /**
     * xyz setter.
     * 
     * @param newXyz new xyz coordinates
     */
    public void setXyz(final double[] newXyz) {
      System.arraycopy(newXyz, 0, xyz, 0, 3);
    }

    /**
     * xyz getter.
     * 
     * @return stored xyz coordinates
     */
    public double[] getXyz() {
      double[] returnXyz = new double[3];
      System.arraycopy(xyz, 0, returnXyz, 0, 3);
      return returnXyz;
    }

    /**
     * constructor with particular xyz.
     * 
     * @param newXyz new xyz coordinates
     */
    public Triad(final double[] newXyz) {
      System.arraycopy(newXyz, 0, xyz, 0, 3);
    }
  }
}
