package se.raddo.raddose3D;

import java.util.List;

/**
 * This class defines the absorption properties of the container
 * encasing the irradiated sample. In this class the container material
 * is defined as a list of its component elements.
 * The container introduces an additional attenuation factor to the
 * beam before it reaches the sample.
 */
public class ContainerElemental extends ContainerSemiTransparent{

  /**
   * A list of the elements from which the container is composed
   */
  private final List<String>  elementNamesList;

  /**
   * A list of the corresponding element occurrences
   */
  private final List<Double>  elementNumsList;

  /**
   * Element database keeping the coefficients of all elements.
   */
  private final ElementDatabase      elementDB;

  /**
   * Constructor for the ContainerMixture class.
   *
   * @param conThickness
   *          Double type argument giving the thickness of the
   *          container in microns.
   * @param conDensity
   *          Double type argument giving the density of the
   *          container in grams per centimetre cubed.
   */
  public ContainerElemental(Double conThickness, Double conDensity,
      List<String> containerElementNames,
      List<Double> containerElementNums) {
    super(conThickness, conDensity, 
        determineContainerMaterial(containerElementNames, 
            containerElementNums));
    this.elementNamesList = containerElementNames;
    this.elementNumsList = containerElementNums;
    this.elementDB = ElementDatabase.getInstance();
  }
  
  /**
   * The container's name: its elements, each followed by its count where
   * that is more than one, as C22H10N2O5.
   *
   * @param containerElementNames the elements' symbols
   * @param containerElementNums how many of each
   * @return the name
   */
  private static String determineContainerMaterial(List<String> containerElementNames,
      List<Double> containerElementNums) {
    String material = "";
    //Loop through elements in the element list and return the full compound as a
    //String
    for (int i = 0; i < containerElementNames.size(); i++) {
      material += containerElementNames.get(i);
      int elementAtomNum = (int) Math.round(containerElementNums.get(i));
      if (elementAtomNum > 1) {
        material += String.valueOf(elementAtomNum);
      }
    }
    return material;
  }

  /**
   * What is wrong with a container of these elements, if anything: an
   * element NIST has no table for, or one RADDOSE-3D does not know. Read
   * when the input is, so that such an input is refused before any dose.
   *
   * @param containerElementNames the elements' symbols
   * @return a message, or null if every element has a table
   */
  public static String problem(final List<String> containerElementNames) {
    if (containerElementNames == null) {
      return null;
    }
    for (String name : containerElementNames) {
      Element element = ElementDatabase.getInstance().getElement(name);
      if (element == null) {
        return "ContainerMaterialElements: " + name + " is not an element";
      }
      if (NistTables.element(element.getAtomicNumber()) == null) {
        return "ContainerMaterialElements: NIST's tables of mass attenuation"
            + " coefficients have no " + name + "; they go up to uranium";
      }
    }
    return null;
  }

  /**
   * @return the parser
   */
  public ElementDatabase getParser() {
    return elementDB;
  }

  /**
   * The container's mass attenuation coefficient: each element's,
   * interpolated at the beam's energy from NIST's table, weighted by the
   * share of the container's mass it is. This is NIST's own mixture rule,
   * http://physics.nist.gov/PhysRefData/XrayMassCoef/chap2.html. The tables
   * ship in constants/; they were downloaded on every run, which failed
   * without the network and on Java 8.
   *
   * @param beam
   *          beam object describing the beam used.
   */
  @Override
  public void extractMassAttenuationCoef(Beam beam) {
    if (this.material != null) {
      double totalWeight = 0;
      double[] elementMassAttCoeffs = new double[this.elementNamesList.size()];
      double[] elementAtomicWeights = new double[this.elementNamesList.size()];
      for (int i = 0; i < this.elementNamesList.size(); i++) {
        Element containerAtom =
            getParser().getElement(this.elementNamesList.get(i));
        elementMassAttCoeffs[i] = interpolate(
            NistTables.element(containerAtom.getAtomicNumber()),
            beam.getPhotonEnergy());
        elementAtomicWeights[i] = containerAtom.getAtomicWeight();
        totalWeight += containerAtom.getAtomicWeight()
            * this.elementNumsList.get(i);
      }

      // The weighted average of the elements' coefficients, each weighted by
      // its share of the container's mass.
      this.massAttenuationCoefficient = 0;
      for (int k = 0; k < this.elementNamesList.size(); k++) {
        double relativeAtomicWeight = (this.elementNumsList.get(k)
            * elementAtomicWeights[k]) / totalWeight;
        this.massAttenuationCoefficient += (relativeAtomicWeight
            * elementMassAttCoeffs[k]);
      }
    }
  }
}
