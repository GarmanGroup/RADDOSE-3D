package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

// import se.raddo.raddose3D.ElementDatabaseEM.DatabaseFields;

public class ElementEM {
  /**
   * Element name.
   */
  private final String                                      elementNameEM;

  /**
   * Atomic number.
   */
  private final int                                         atomicNumberEM;

  /**
   * Atomic weight.
   */
  private final double                                         atomicWeightEM;
  
  /**
   * Stored information about the chemical element.
   */
  private final TreeMap<Double, Double> elementDataEM;


  /**
   * Create a new element with name, atomic number and associated information.
   * 
   * @param element
   *          element name
   * @param protons
   *          atomic number
   * @param elementInformation
   *          Map containing the associated element information
   */
  public ElementEM(final String element, final int protons, final double mass,
      final TreeMap<Double, Double> elementInformation) {
    elementNameEM = element;
    atomicNumberEM = protons;
    atomicWeightEM = mass;
    elementDataEM = new TreeMap<Double, Double>(
        elementInformation);
  }
  
  public double getElasticCoefficient(double electronEnergy) {
    double crossSection = 0;
    double beforeKey = 0, afterKey = 0;
    //get keys above and below
    if (electronEnergy >= 0.05 && electronEnergy <= 300) {
      beforeKey = elementDataEM.floorKey(electronEnergy);
      afterKey =  elementDataEM.ceilingKey(electronEnergy);
      beforeKey = (beforeKey == 0) ? afterKey: beforeKey;
      afterKey = (afterKey == 0) ? beforeKey: afterKey;
      //find distance across
      double distanceAcross = 0;
      if (afterKey != beforeKey) {
        distanceAcross = (electronEnergy - beforeKey) / (afterKey - beforeKey);
      }
      double lowVal = elementDataEM.get(beforeKey);
      double highVal = elementDataEM.get(afterKey);
      crossSection = lowVal + 
          ((highVal - lowVal) * distanceAcross);
    }
    return crossSection;
  }

  /**
   * @return the elementName
   */
  public String getElementName() {
    return elementNameEM;
  }

  /**
   * @return the atomicNumber
   */
  public int getAtomicNumber() {
    return atomicNumberEM;
  }
  
  /**
   * @return the atomicWeight
   */
  public double getAtomicWeight() {
    return atomicWeightEM;
  }
}
