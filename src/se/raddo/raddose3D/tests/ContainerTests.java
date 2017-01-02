package se.raddo.raddose3D.tests;

import se.raddo.raddose3D.ContainerMixture;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.BeamTophat;
import se.raddo.raddose3D.ContainerElemental;

public class ContainerTests {
  /**
   * Check that when the container thickness is zero, the X-ray beam is not 
   * attenuated at all i.e. the container attenuation fraction is zero.
   */
  public void testTransparency() {
    ContainerMixture containerMix = new ContainerMixture(0.0, 2.23, "pyrex");
    
    List<String> containerElementNames = new ArrayList<String>();
    List<Double> containerElementNums = new ArrayList<Double>();
    
    containerElementNames.add("S");
    containerElementNames.add("O");
    containerElementNums.add(1.0);
    containerElementNums.add(2.0);
    ContainerElemental containerElem = new ContainerElemental(0.0, 2.65, 
        containerElementNames, containerElementNums);
    
    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_COLL_H, 80.);
    beamProperties.put(Beam.BEAM_COLL_V, 80.);
    beamProperties.put(Beam.BEAM_FLUX, 9.281e8);
    beamProperties.put(Beam.BEAM_ENERGY, 8.05);
    Beam b = new BeamTophat(beamProperties);
    
    containerMix.calculateContainerAttenuation(b);
    containerElem.calculateContainerAttenuation(b);
    
    boolean zeroContainerAttenuationFractionMix = false;
    if (containerMix.getContainerAttenuationFraction() < 1e-6) {
      zeroContainerAttenuationFractionMix = true;
    }
    assertTrue(zeroContainerAttenuationFractionMix, "Zero mixture thickness results in non-negligible container attenuation");
    
    boolean zeroContainerAttenuationFractionElem = false;
    if (containerElem.getContainerAttenuationFraction() < 1e-6) {
      zeroContainerAttenuationFractionElem = true;
    }
    assertTrue(zeroContainerAttenuationFractionElem, "Zero elemental thickness results in non-negligible container attenuation");
  }
  
  /**
   * Check that the mass attenuation coefficient of alanine is the same when
   * calculated using the constituent elemental composition and when you
   * use the mixture definition directly from the NIST tables.
   */
  public void testElementalMatchesMixture() {
    ContainerMixture containerMix = new ContainerMixture(50.0, 1.424, "alanine");
    
    List<String> containerElementNames = new ArrayList<String>();
    List<Double> containerElementNums = new ArrayList<Double>();
    
    containerElementNames.add("H");
    containerElementNames.add("C");
    containerElementNames.add("N");
    containerElementNames.add("O");
    containerElementNums.add(7.0);
    containerElementNums.add(3.0);
    containerElementNums.add(1.0);
    containerElementNums.add(2.0);
    ContainerElemental containerElem = new ContainerElemental(50.0, 1.424, 
        containerElementNames, containerElementNums);
    
    Map<Object, Object> beamProperties = new HashMap<Object, Object>();
    beamProperties.put(Beam.BEAM_COLL_H, 80.);
    beamProperties.put(Beam.BEAM_COLL_V, 80.);
    beamProperties.put(Beam.BEAM_FLUX, 9.281e8);
    beamProperties.put(Beam.BEAM_ENERGY, 8.05);
    Beam b = new BeamTophat(beamProperties);
    
    containerMix.calculateContainerAttenuation(b);
    containerElem.calculateContainerAttenuation(b);
    
    boolean equalMassAttenuation = false; 
    if (containerMix.getMassAttenuationCoefficient() - containerElem.getMassAttenuationCoefficient() < 1e-2) {
      equalMassAttenuation = true;
    }
    assertTrue(equalMassAttenuation,"Mass attenuation of mixture and elemental components don't agree.");
  }
}
