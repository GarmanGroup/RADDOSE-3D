package se.raddo.raddose3D;


/**
 * This class defines the absorption properties of the container
 * encasing the irradiated sample. In this class the container material
 * is defined as a mixture rather than by its component elements.
 * The container introduces an additional attenuation factor to the
 * beam before it reaches the sample.
 */
public class ContainerMixture extends ContainerSemiTransparent{

  /**
   * Constructor for the ContainerMixture class.
   *
   * @param conThickness
   *          Double type argument giving the thickness of the
   *          container in microns.
   * @param conDensity
   *          Double type argument giving the density of the
   *          container in grams per centimetre cubed.
   * @param conMaterial
   *          String type argument giving the material of the container
   *          encasing the irradiated sample.
   */
  public ContainerMixture(Double conThickness, Double conDensity, String conMaterial) {
    super(conThickness, conDensity, conMaterial);
  }
  
  /**
   * What is wrong with a container of this material, if anything: a name
   * NIST has no table for. Read when the input is, so that such an input is
   * refused before any dose; the download it replaced failed with a
   * NullPointerException.
   *
   * @param conMaterial the material, as ContainerMaterialMixture gives it
   * @return a message, or null if NIST has a table for it
   */
  public static String problem(final String conMaterial) {
    if (conMaterial == null || NistTables.mixture(conMaterial) != null) {
      return null;
    }
    StringBuilder names = new StringBuilder();
    for (String name : NistTables.mixtureNames()) {
      names.append(names.length() == 0 ? "" : ", ").append(name);
    }
    return "ContainerMaterialMixture: NIST has no table for " + conMaterial
        + "; it has " + names
        + ". ContainerMaterialType Elemental takes any composition.";
  }

  /**
   * The container's mass attenuation coefficient, interpolated at the beam's
   * energy from NIST's table for the material. The tables ship in
   * constants/; they were downloaded on every run, which failed without the
   * network and on Java 8.
   *
   * @param beam
   *          beam object describing the beam used.
   */
  @Override
  public void extractMassAttenuationCoef(Beam beam) {
    if (this.material != null) {
      this.massAttenuationCoefficient = interpolate(
          NistTables.mixture(this.material), beam.getPhotonEnergy());
    }
  }
}
