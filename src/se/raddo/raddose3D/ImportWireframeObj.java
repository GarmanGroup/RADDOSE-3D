package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * TODO: This whole class appears to be overly complicated. The subclass seems
 * unnecessary and the int->double->int conversion is probably not helpful.
 */
/**
 * Returns vertices and indices for use by CrystalPolyhedron.
 * Vertices have not been rotated by the initial rotation angles
 * and this must be executed by the Crystal class. This simply
 * imports data from a text file of OBJ file type.
 */
public class ImportWireframeObj implements ImportWireframe {
  /** Import file name of OBJ file type. */
  private final String      wireframeFileName;

  /** List of vertices from OBJ file. */
  private final List<Triad> vertexTriads;
  /** List of faces from OBJ file. */
  private final List<Triad> faceTriads;

  /**
   * Sets up a wireframe object and sets file name for later
   * importing.
   * 
   * @param filename .obj filename.
   */
  public ImportWireframeObj(final String filename) {
    if (filename == null) {
      throw new IllegalArgumentException(
          "Filename not found, please set ModelFile");
    }

    wireframeFileName = filename;
    vertexTriads = new ArrayList<Triad>();
    faceTriads = new ArrayList<Triad>();
    try {
      loadWireframeFile();
    } catch (IOException e) {
      throw new IllegalArgumentException(
          "Could not read wireframe file ".concat(filename), e);
    }
  }

  /**
   * Opens file and takes any line from the file beginning
   * with the identifier token (e.g. "v " or "f ") and splits
   * into the component numbers, and stores the values.
   * 
   * @throws IOException
   *           Error occured when reading from file.
   */
  private void loadWireframeFile() throws IOException {
    FileInputStream is = new FileInputStream(wireframeFileName);
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);

    String line;
    String[] components;

    while ((line = br.readLine()) != null) {
      // vertex lines begin with 'v '
      if ("v ".equals(line.substring(0, 2))) {
        components = line.split(" ", -1);
        // if components are not 4, then this was a false positive
        if (components.length < 4) {
          continue;
        }

        // components should be: "v", x, y, z.

        // TODO: there could be a localization bug here
        Triad newVertex = new Triad(
            Double.parseDouble(components[1]),
            Double.parseDouble(components[2]),
            Double.parseDouble(components[3]));

        vertexTriads.add(newVertex);
      }
      // face lines begin with 'v '
      if ("f ".equals(line.substring(0, 2))) {
        components = line.split(" ", -1);
        // if components are not 4, then this was a false positive
        if (components.length < 4) {
          continue;
        }

        // components should be: "f", x, y, z.

        // TODO: there could be a localization bug here
        Triad newVertex = new Triad(
            Double.parseDouble(components[1]),
            Double.parseDouble(components[2]),
            Double.parseDouble(components[3]));

        faceTriads.add(newVertex);
      }
    }
    br.close();
    isr.close();
  }

  @Override
  public double[][] getVertices() {
    double[][] returnVertices = new double[vertexTriads.size()][3];

    for (int i = 0; i < vertexTriads.size(); i++) {
      System
          .arraycopy(vertexTriads.get(i).getXYZ(), 0, returnVertices[i], 0, 3);
    }

    return returnVertices;
  }

  @Override
  public int[][] getIndices() {
    int[][] returnIndices = new int[faceTriads.size()][3];

    for (int i = 0; i < faceTriads.size(); i++) {
      double[] xyz = faceTriads.get(i).getXYZ();
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
  private static class Triad {
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
