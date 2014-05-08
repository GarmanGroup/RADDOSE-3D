package se.raddo.raddose3D;

import java.util.Map;

/**
 * The BeamFactory class sits between the parser and the actual beam classes. It
 * allows easy testing of the parser and extensibility for new beam types.
 */
public class BeamFactory extends ClassFactory {
  /**
   * creates and returns different Beam type objects.
   * 
   * @param beamName
   *          the name of the beam class requested.
   * @param properties
   *          a Map containing the complete list of beam properties. Each beam
   *          type may require a different set of specified properties, but
   *          flux and energy etc. will certainly be required. Keys of the Map
   *          structure are usually determined by the constants defined in
   *          {@link Beam}, but third party Beam implementations may have their
   *          own key set. Check the corresponding beam class for details.
   *          The newly created object should not keep any references to this
   *          Map after object creation.
   * @return
   *         the requested Beam type object
   * @throws IllegalArgumentException
   *           the passed parameters are invalid
   * @throws ClassFactoryException
   *           the requested beam class could not be initialized
   */
  public Beam createBeam(final String beamName,
      final Map<Object, Object> properties)
      throws IllegalArgumentException, ClassFactoryException {

    String revisedBeamName;

    if ("experimental".equalsIgnoreCase(beamName)) {
      revisedBeamName = "se.raddo.raddose3D.BeamExperimental";
    } else if ("experimentalpgm".equalsIgnoreCase(beamName)) {
      revisedBeamName = "se.raddo.raddose3D.BeamExperimentalpgm";
    } else if ("gaussian".equalsIgnoreCase(beamName)) {
      revisedBeamName = "se.raddo.raddose3D.BeamGaussian";
    } else if ("tophat".equalsIgnoreCase(beamName)) {
      revisedBeamName = "se.raddo.raddose3D.BeamTophat";
    } else {
      revisedBeamName = beamName;
    }

    return createObject(Beam.class, revisedBeamName, properties);
  }
}
