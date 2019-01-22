package se.raddo.raddose3D.server;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.ExposeObserver;
import se.raddo.raddose3D.Output;
import se.raddo.raddose3D.Wedge;

/**
 * Class that forces thread switching during experiments.
 */
public class ExperimentYielder implements Output {
  @Override
  public void publishCrystal(final Crystal c) {
    c.addObserver(new ExposeYielder());
    Thread.yield();
  }

  @Override
  public void publishBeam(final Beam b) {
    Thread.yield();
  }

  @Override
  public void publishWedge(final Wedge w) {
    Thread.yield();
  }

  @Override
  public void close() {
    // Nothing to close.
  }

  /**
   * Class that forces thread switching during exposure.
   */
  private static class ExposeYielder implements ExposeObserver {
    @Override
    public void register(final Crystal c) {
      // Nothing to register.
    }

    @Override
    public void exposureStart(final int imageCount) {
      Thread.yield();
    }

    @Override
    public void exposureObservation(final int wedgeImage, final int i,
        final int j, final int k, final double addedDose,
        final double totalDose, final double fluence,
        final double relativeDiffractionEfficiency,
        final double absorbedEnergy, final double elastic, final double anglecount) {
      // Nothing to observe.
    }

    @Override
    public void imageComplete(final int image, final double angle) {
      Thread.yield();
    }

    @Override
    public void summaryObservation(final int i, final int j, final int k,
        final double totalDose, final double voxelMassKg) {
      // Nothing to summarize.
    }

    @Override
    public void exposureComplete() {
      Thread.yield();
    }
  }
}
