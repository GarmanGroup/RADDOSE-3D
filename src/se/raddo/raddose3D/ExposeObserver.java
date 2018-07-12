package se.raddo.raddose3D;

/**
 * Interface for listening in on exposure activity on a crystal. Once registered
 * with an instance of {@link Crystal} the method exposureObservation will be
 * called for each voxel exposure, and after exposure, the method
 * summaryObservation will be called for each voxel.
 */
public interface ExposeObserver {

  /**
   * Called immediately upon registering onto a Crystal.
   * 
   * @param c
   *          Crystal object that the Observer is registered to.
   */
  public void register(Crystal c);

  /**
   * Called before exposure takes place to initialise ExposeObserver
   * implementation.
   * 
   * @param wedgeImages
   *          Expected number of upcoming images
   */
  public void exposureStart(int wedgeImages);

  /**
   * Single voxel exposure event. Called for every voxel, in every angular step.
   * 
   * @param wedgeImage
   *          Running image number
   * @param i
   *          voxel x index: perpendicular to rotation axis
   * @param j
   *          voxel y index: rotation axis
   * @param k
   *          voxel z index: beam axis
   * @param addedDose
   *          dose in MGy for this exposure event
   * @param totalDose
   *          total dose in voxel after exposure event
   * @param fluence
   *          fluence added for this exposure event
   * @param relativeDiffractionEfficiency
   *          result of DDM for this voxel
   * @param absorbedEnergy
   *          energy absorbed by the voxel for this exposure event in J.
   * @param elastic
   *          TODO: Oli needs to explain what this is!
   */
  public void exposureObservation(int wedgeImage, int i, int j, int k,
      double addedDose, double totalDose, double fluence,
      double relativeDiffractionEfficiency,
      double absorbedEnergy, double elastic);

  /**
   * Completion of an exposure at a particular angle.
   * Called once for every angular step after the exposure of every voxel at
   * that angle.
   * 
   * @param image
   *          a running number for each angular exposure. Starts at 0.
   * @param angrad
   *          phi angle of the exposure, in radians.
   */
  public void imageComplete(int image, double angrad);

  /**
   * Voxel exposure summary. Called for every occupied voxel after each
   * completed Wedge exposure.
   * 
   * @param i
   *          voxel x index: perpendicular to rotation axis
   * @param j
   *          voxel y index: rotation axis
   * @param k
   *          voxel z index: beam axis
   * @param totalDose
   *          total dose in voxel after wedge exposure
   */
  public void summaryObservation(int i, int j, int k, double totalDose, double voxelMassKg);

  /**
   * Called once at the end of each completed Wedge exposure.
   */
  public void exposureComplete();
}
