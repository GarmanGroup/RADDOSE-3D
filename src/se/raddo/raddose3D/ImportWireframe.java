package se.raddo.raddose3D;

/**
 * Interface for importing wireframe from various types of file
 * formats containing vertex/index information.
 */
public interface ImportWireframe {
  /**
   * Returns array of three-dimensional vectors containing vertex
   * information in crystal coordinates (dimensions in um).
   * 
   * @return array of vertices
   */
  public double[][] getVertices();

  /**
   * Returns connectivity of vertices starting from index 1 (not 0)
   * in groups of three indices for each triangle.
   * 
   * @return array of indices
   */
  public int[][] getIndices();

}
