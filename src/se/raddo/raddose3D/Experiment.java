package se.raddo.raddose3D;

import java.util.Vector;

/**
 * The Experiment class is a central coordinating class in a simulation.
 * It receives {@link Crystal}, {@link Beam} and {@link Wedge} classes,
 * initiates the exposure simulation and notifies subscribed {@link Output}
 * classes on any of these events. It also relays 'out-of-band' information such
 * as warnings or citation information to any subscribers.
 */
public class Experiment implements Initializer {
  /** Reference to the currently used crystal object. */
  private Crystal              currentCrystal;

  /** Reference to the currently defined beam object. */
  private Beam                 currentBeam;

  /**
   * List of currently subscribed event listeners. Subclasses of Experiment can
   * gain access to this object via the notifyObserver() methods and the close()
   * method.
   */
  private final Vector<Output> observers = new Vector<Output>();

  /**
   * Cause given Input object to send its object stream to this Experiment.
   * Basically this is where the parser is called on the input stream, and
   * through the Initializer methods, this object is populated. Basically: send
   * me the crystal, beam, and wedge objects.
   * 
   * @param i
   *          Single Input type object.
   * @throws InputException
   *           Any problems during input processing are sent up to the caller.
   */
  public void process(final Input i) throws InputException {
    i.sendData(this);
  }

  /**
   * Adds Output event listener to the list of subscribers.
   * If the event listener also supports the {@link ExperimentNotices} interface
   * it will also receive warnings and citation information.
   * 
   * @param o
   *          class implementing Output to be added to subscriber list
   */
  public void addObserver(final Output o) {
    observers.add(o);
  }

  /**
   * Passes a Wedge object to all observers.
   * 
   * @param w
   *          The Wedge object describing the exposure.
   */
  protected void notifyObserver(final Wedge w) {
    for (Output o : observers) {
      o.publishWedge(w);
    }
  }

  /**
   * Passes a Crystal object to all observers.
   * 
   * @param c
   *          The Crystal object.
   */
  protected void notifyObserver(final Crystal c) {
    for (Output o : observers) {
      o.publishCrystal(c);
    }
  }

  /**
   * Passes a Beam object to all observers.
   * 
   * @param b
   *          The Beam object.
   */
  protected void notifyObserver(final Beam b) {
    for (Output o : observers) {
      o.publishBeam(b);
    }
  }

  /**
   * Exposes wedge of crystal to the beam.
   * Notifies all subscribers after wedge exposure.
   * 
   * @param w
   *          Wedge object for exposure
   */
  @Override
  public void exposeWedge(final Wedge w) {
    if (w != null) {
      currentCrystal.expose(currentBeam, w);
      notifyObserver(w);
    }
  }

  /**
   * Sets Crystal object and notifies all subscribers.
   * This method is called after Crystal initialization.
   * 
   * @param c
   *          Crystal object to be set and passed to subscribers
   */
  @Override
  public void setCrystal(final Crystal c) {
    if (c != null) {
      currentCrystal = c;
      notifyObserver(currentCrystal);
    }
  }

  /**
   * Sets the beam to be used for the experiment, and notifies all subscribers.
   * 
   * @param b
   *          Beam object to be set
   */
  @Override
  public void setBeam(final Beam b) {
    if (b != null) {
      currentBeam = b;
      notifyObserver(currentBeam);
    }
  }

  /**
   * Ends the experiment.
   * Tells all subscribers to shut down, clears the list of subscribers and
   * resets the class.
   */
  public void close() {
    for (Output o : observers) {
      o.close();
    }

    // Ensure not to send updates to subscribers after closing.
    observers.clear();

    // Delete all references to other objects.
    currentBeam = null;
    currentCrystal = null;
  }

  /**
   * Custom object destructor to ensure that Experiment is closed properly.
   * This includes closing any downstream writers such as open files, etc.
   */
  @Override
  protected void finalize() {
    if ((currentBeam != null) || (currentCrystal != null)
        || (!observers.isEmpty())) {
      System.err.println("Experiment has not been closed properly.");
      System.err.println("ExClose: " + (currentBeam == null) + ", "
          + (currentCrystal == null) + ", " + observers.isEmpty() + ".");
      close();
      System.err.println("ExClose: " + (currentBeam == null) + ", "
          + (currentCrystal == null) + ", " + observers.isEmpty() + ".");
    }
  }

  @Override
  public void raiseWarning(final String warning) {
    for (Output o : observers) {
      if (o instanceof ExperimentNotices) {
        ((ExperimentNotices) o).raiseWarning(warning);
      }
    }
  }

  @Override
  public void addReference(final String reference) {
    for (Output o : observers) {
      if (o instanceof ExperimentNotices) {
        ((ExperimentNotices) o).addReference(reference);
      }
    }
  }
}
