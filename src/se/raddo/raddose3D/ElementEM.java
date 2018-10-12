package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

import se.raddo.raddose3D.ElementDatabaseEM.DatabaseFields;

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
   * Stored information about the chemical element.
   */
  private final Map<ElementDatabaseEM.DatabaseFields, Double> elementDataEM;


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
  public ElementEM(final String element, final int protons,
      final Map<ElementDatabaseEM.DatabaseFields, Double> elementInformation) {
    elementNameEM = element;
    atomicNumberEM = protons;
    elementDataEM = new HashMap<ElementDatabaseEM.DatabaseFields, Double>(
        elementInformation);
  }
  
  public double[] getELSEPACoefficients() {
    double[] crossSection = new double[6];
    for (int i = 1; i < 7; i++) {
      String keV = String.valueOf((double)i * 50);
      String field = "EL_" + keV;
      crossSection[i-1] = elementDataEM.get(ElementDatabaseEM.DatabaseFields.valueOf(field));
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
   * @return the minimum energy for low ionisation coefficients
   */
  public Double getEminLow() {
    return elementDataEM.get(DatabaseFields.EminLow);
  }
  
  /**
   * @return the maximum energy for low ionisation coefficients
   */
  public Double getEmaxLow() {
    return elementDataEM.get(DatabaseFields.EmaxLow);
  }
  
  /**
   * @return the bK constant for low energy
   */
  public Double getbKLow() {
    return elementDataEM.get(DatabaseFields.bKlow);
  }
  
  /**
   * @return the cK constant for low energy
   */
  public Double getcKLow() {
    return elementDataEM.get(DatabaseFields.cKlow);
  }
  
  /**
   * @return the minimum energy for high energy ionisation coefficients
   */
  public Double getEminHigh() {
    return elementDataEM.get(DatabaseFields.EminHigh);
  }
  
  /**
   * @return the bK constant for high energy
   */
  public Double getbKHigh() {
    return elementDataEM.get(DatabaseFields.bKhigh);
  }
  
  /**
   * @return the cK constant for high energy
   */
  public Double getcKHigh() {
    return elementDataEM.get(DatabaseFields.cKhigh);
  }
  
  /**
   * Return the K edge energy in keV of the element
   * 
   * @return
   *         the K edge energy in keV
   */
  public Double getKEdge() {
    return elementDataEM.get(DatabaseFields.EDGE_K);
  }
  
}
