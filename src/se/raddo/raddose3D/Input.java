package se.raddo.raddose3D;

/**
 * generic interface for all experimental data providers.
 * 
 * @author Markus Gerstel
 */
public interface Input {

  /**
   * Causes the class to parse and send all available data
   * as an object stream to the given Initializer.
   * 
   * @param i
   *          Initializer that receives all sent objects.
   * @throws InputException
   *           For reporting problems during parsing or I/O.
   */
  public void sendData(Initializer i) throws InputException;

}
