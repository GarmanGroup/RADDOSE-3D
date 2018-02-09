package se.raddo.raddose3D;

import java.util.Map;

/**
 * Dummy Experiment class for fast what-if simulation.
 * Used for runtime estimation.
 */
public class ExperimentDummy extends Experiment {

  /**
   * Cause given Input object to send its object stream to this Experiment.
   * Try avoiding complex object instantiation wherever possible.
   *
   * @param i
   *          Single Input type object.
   * @throws InputException
   *           Any problems during input processing are sent up to the caller.
   */
  @Override
  public void process(final Input i) throws InputException {
    if (i instanceof InputParser) {
      ((InputParser) i).setBeamFactory(new DummyBeamFactory());
      ((InputParser) i).setCrystalFactory(new DummyCrystalFactory());
    }

    i.sendData(this);
  }

  /**
   * This dummy version of exposeWedge does NOT actually expose the wedge.
   * Notifies all subscribers.
   *
   * @param w
   *          Wedge object for exposure
   */
  @Override
  public void exposeWedge(final Wedge w) {
    if (w != null) {
      notifyObserver(w);
    }
  }

  /**
   * An internal implementation of a BeamFactory that only creates dummy Beams.
   */
  private static class DummyBeamFactory extends BeamFactory {
    @Override
    public Beam createBeam(final String beamType,
        final Map<Object, Object> properties) {
      return new DummyBeam();
    }

    /**
     * An internal minimal implementation of a Beam interface.
     */
    private static class DummyBeam implements Beam {
      @Override
      public double beamIntensity(final double coordX, final double coordY,
          final double offAxisUM) {
        return 0;
      }

      @Override
      public String getDescription() {
        return "";
      }

      @Override
      public double getPhotonsPerSec() {
        return 0;
      }

      @Override
      public double getPhotonEnergy() {
        return 0;
      }

      @Override
      public void applyContainerAttenuation(Container sampleContainer){
      }

      @Override
      public void generateBeamArray() {        
      }
    }
  }

  /**
   * An internal implementation of a CrystalFactory that only creates dummy
   * Crystals.
   */
  private static class DummyCrystalFactory extends CrystalFactory {
    @Override
    public Crystal createCrystal(final String crystalType,
        final Map<Object, Object> properties) {
      return new DummyCrystal(properties);
    }

    /**
     * An internal implementation of a dummy Crystal, that only computes the
     * size of the bounding box.
     */
    private static class DummyCrystal extends Crystal {
      /** 3 element array defining dimensions of bounding box in voxels. */
      private final int[] crystSizeVoxels;

      /**
       * Generic property constructor for dummy crystals. Extracts all required
       * information from a Map data structure.
       * *
       * Used properties:
       * CRYSTAL_DIM_X
       * CRYSTAL_DIM_Y (optional. Default: CRYSTAL_DIM_X)
       * CRYSTAL_DIM_Z (optional. Default: CRYSTAL_DIM_X)
       * CRYSTAL_RESOLUTION (optional. Default: 0.5)
       *
       * @param properties
       *          Map of type <Object, Object> that contains all crystal
       *          properties. The keys of the Map are defined by the constants
       *          in the {@link Crystal} class.
       */
      public DummyCrystal(final Map<Object, Object> properties) {
        super(properties);

        Double res = (Double) properties.get(Crystal.CRYSTAL_RESOLUTION);
        if (res == null) {
          res = 1d / 2;
        }

        Assertions a = new Assertions("Could not create DummyCrystal: ");
        a.checkIsClass(properties.get(Crystal.CRYSTAL_DIM_X), Double.class,
            "no crystal dimension specified");

        Double xDim = (Double) properties.get(Crystal.CRYSTAL_DIM_X);
        Double yDim = (Double) properties.get(Crystal.CRYSTAL_DIM_Y);
        Double zDim = (Double) properties.get(Crystal.CRYSTAL_DIM_Z);

        int nx, ny, nz;
        nx = (int) StrictMath.round(xDim * res) + 1;
        if (yDim == null) {
          ny = nx;
        } else {
          ny = (int) StrictMath.round(yDim * res) + 1;
        }
        if (zDim == null) {
          nz = nx;
        } else {
          nz = (int) StrictMath.round(zDim * res) + 1;
        }
        int[] tempCrystSize = { nx, ny, nz };
        crystSizeVoxels = tempCrystSize; // Final Value
      }

      @Override
      public double findDepth(final double[] voxCoord, final double deltaPhi,
          final Wedge myWedge) {
        return 0;
      }

      @SuppressWarnings("PMD.ReturnEmptyArrayRatherThanNull")
      @Override
      public double[] getCrystCoord(final int i, final int j, final int k) {
        return null;
      }

      @Override
      public boolean isCrystalAt(final int i, final int j, final int k) {
        return false;
      }

      @Override
      public void addDose(final int i, final int j, final int k,
          final double doseVox) {
        // No implementation required
      }

      @Override
      public void addFluence(final int i, final int j, final int k,
          final double fluenceVox) {
        // No implementation required
      }

      @Override
      public String crystalInfo() {
        return "";
      }

      @Override
      @SuppressWarnings("PMD.MethodReturnsInternalArray")
      public int[] getCrystSizeVoxels() {
        return crystSizeVoxels;
      }

      @Override
      @SuppressWarnings("PMD.ReturnEmptyArrayRatherThanNull")
      public double[] getCrystSizeUM() {
        return null;
      }

      @Override
      public double getDose(final int i, final int j, final int k) {
        return 0;
      }

      @Override
      public double getFluence(final int i, final int j, final int k) {
        return 0;
      }

      @Override
      public double getCrystalPixPerUM() {
        return 0;
      }

      @Override
      public void setupDepthFinding(final double angle, final Wedge wedge) {
        // No implementation required
      }

      @Override
      public void addElastic(final int i, final int j, final int k,
          final double elasticIncrease) {
        // No implementation required
      }

      @Override
      public double getElastic(final int i, final int j, final int k) {
        return 0;
      }

      @Override
      public double getEscapeFactor(int i, int j, int k) {
        return 1.0;
      }

      @Override
      public double addDoseAfterPE(int i, int j, int k, double doseIncreasePE){
        return 0;
      }

      @Override
      public void setPEparamsForCurrentBeam(double beamEnergy, CoefCalc coefCalc, double[][] feFactors) {        
      }
      
      @Override
      public void setFLparamsForCurrentBeam(final double[][] feFactors) {
      }

      @Override
      public double addDoseAfterFL(int i, int j, int k, double doseIncreaseFL) {
        // TODO Auto-generated method stub
        return 0;
      }

      @Override
      public double[] getCryoCrystCoord(int i, int j, int k) {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public int[] getCryoCrystSizeVoxels() {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public int getExtraVoxels(int maxPEDistance, double pixelsPerMicron) {
        // TODO Auto-generated method stub
        return 0;
      }
      
      @Override
      public void setCryoPEparamsForCurrentBeam(double beamEnergy, CoefCalc coefCalc, double[][] feFactors) {    
      }
      
      @Override
      public double addDoseAfterPECryo(int i, int j, int k, double doseIncreasePE) {
        return 0;
      }
      
      @Override
      public void findVoxelsReachedByPE(boolean cryo, CoefCalc coefCalc, final double energy, double[][] feFactors, final double angle) {
        
      }
    }
  }
}
