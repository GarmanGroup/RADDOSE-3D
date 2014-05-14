package se.raddo.raddose3D;

import java.util.Map;

/**
 * The CrystalFactory class sits between the parser and the actual crystal
 * classes. It allows easy testing of the parser and extensibility for new
 * crystal types.
 */
public class CrystalFactory extends ClassFactory {
  /**
   * creates and returns different Crystal type objects.
   * 
   * @param crystalName
   *          the name of the crystal class requested.
   * @param properties
   *          a Map containing the complete list of crystal properties.
   *          Different crystal types may require a different set of specified
   *          properties, but dimension and resolution etc. will certainly be
   *          required. Keys of the Map structure are usually determined by the
   *          constants defined in {@link Crystal}, but third party Crystal
   *          implementations may have their own key set.
   *          Check the corresponding crystal class for details.
   *          The newly created object should not keep any references to this
   *          Map after object creation.
   * @return
   *         the requested Crystal type object
   * @throws IllegalArgumentException
   *           the passed parameters are invalid
   * @throws ClassFactoryException
   *           the requested crystal class could not be initialized
   */
  public Crystal createCrystal(final String crystalName,
      final Map<Object, Object> properties)
      throws IllegalArgumentException, ClassFactoryException {

    String revisedCrystalName;

    if ("cuboid".equalsIgnoreCase(crystalName)) {
      revisedCrystalName = "se.raddo.raddose3D.CrystalCuboid";
    } else if ("spherical".equalsIgnoreCase(crystalName)) {
      revisedCrystalName = "se.raddo.raddose3D.CrystalSpherical";
    } else if ("polyhedron".equalsIgnoreCase(crystalName)) {
      revisedCrystalName = "se.raddo.raddose3D.CrystalPolyhedron";
    } else {
      revisedCrystalName = crystalName;
    }

    return createObject(Crystal.class, revisedCrystalName, properties);
  }
}
