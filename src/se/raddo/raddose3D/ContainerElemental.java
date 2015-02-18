package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class defines the absorption properties of the container
 * encasing the irradiated sample. In this class the container material
 * is defined as a list of its component elements.
 * The container introduces an additional attenuation factor to the
 * beam before it reaches the sample.
 */
public class ContainerElemental extends Container{

  /**
   * Conversion of beam energy from MeV to KeV
   */
  private static final double MEV_TO_KEV             = 1e3;

  /**
   * Conversion from microns to centimeters
   */
  private static final double MICRONS_TO_CENTIMETERS = 1e-4;

  /**
   * The material that the container is made from
   */
  private String              material;

  /**
   * The thickness of the container in microns
   */
  private final double        thickness;

  /**
   * The density of the material in grams per centimetre cubed
   */
  private final double        density;

  /**
   * The mass attenuation coefficient of the sample
   */
  private double              massAttenuationCoefficient;

  /**
   * The mass thickness of the sample. On the NIST website the mass
   * thickness is defined as the mass per unit area.
   */
  private double              massThickness;

  /**
   * The total fraction attenuation of the X-ray beam due to the container
   */
  private double              containerAttenuationFraction;

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

    /**
     * Initialise the instance variables
     */
    this.thickness = 0;
    this.density = 0;
    this.elementNamesList = containerElementNames;
    this.elementNumsList = containerElementNums;
    this.elementDB = ElementDatabase.getInstance();

    //Loop through elements in the element list and return the full compound as a
    //String
    this.material = "";
    for (int i = 0; i < containerElementNames.size(); i++) {
      this.material += containerElementNames.get(i);
      int elementAtomNum = (int) Math.round(containerElementNums.get(i));
      if (elementAtomNum > 1) {
        this.material += String.valueOf(elementAtomNum);
      }
    }
  }

  /**
   * Calculate the fraction by which the beam is attenuated by the container
   *
   * @param beam
   *          The beam object that is used to irradiate the sample
   */
  @Override
  public void calculateContainerAttenuation(Beam beam) {
    extractMassAttenuationCoef(beam);
    calculateMassThickness();

    this.containerAttenuationFraction = 1 - Math
        .exp(-this.massAttenuationCoefficient
            * this.massThickness);
  }

  /**
   * This method downloads the mass attenuation coefficients from NIST table
   * for the corresponding elements and uses the beam energy to interpolate the
   * mass attenuation coefficient for each one.
   * The weighted average of the mass attenuation coefficients is given as the
   * overall mass attenuation coefficient. This approach is the exact approach
   * used by NIST explained at the bottom of the following webpage:
   * http://physics.nist.gov/PhysRefData/XrayMassCoef/chap2.html
   *
   * @param beam
   *          beam object describing the beam used.
   */
  public void extractMassAttenuationCoef(Beam beam) {

    if (this.material == null) {
      this.massAttenuationCoefficient = 0;
    } else {
      //Define/Initialise the local variables
      URL nistURL = null;
      URLConnection nistConnection = null;
      BufferedReader br = null;
      String inputLine;
      double nistBeamEnergyInMeV = 0;
      double nistBeamEnergyInKeV = 0;
      double massAttenCoeff = 0;
      double nistBeamEnergyInKeVPrevious = 0;
      double massAttenCoeffPrevious = 0;
      double totalWeight = 0;

      //Create arrays to store the mass attenuation coeffcients and the atomic weights
      //of each element in the container material.
      double[] elementMassAttCoeffs = new double[this.elementNamesList.size()];
      double[] elementAtomicWeights = new double[this.elementNamesList.size()];
      double[] relativeAtomicWeights = new double[this.elementNamesList.size()];

      // Regular expressions used in parsing
      Pattern openTag = Pattern.compile("<PRE>");
      Pattern closeTag = Pattern.compile("</PRE>");
      Pattern scientificNotation = Pattern.compile("[0-9]E[-+][0-9]");

      for (int i = 0; i < this.elementNamesList.size(); i++) {
        boolean readLine = false;
        int indexOfEnergyCol = -1;
        //create an element object from the element name using information in the
        //ElementDatabase.
        Element containerAtom = getParser().getElement(this.elementNamesList.get(i));

        //Get the URL string
        String urlString = getNISTURL(containerAtom.getAtomicNumber());

        //Create a URL object for the relevant NIST table
        try {
          nistURL = new URL(urlString);
        } catch (MalformedURLException e) {
          System.out.println("URL " + urlString + " is malformed");
          e.printStackTrace();
        }

        //Open a URL connection
        try {
          nistConnection = nistURL.openConnection();
        } catch (IOException e) {
          System.out.print("Cannot read from URL: " + urlString);
          e.printStackTrace();
        }

        //Read the data from the NIST table webpage into a buffered reader
        try {
          br = new BufferedReader(
              new InputStreamReader(nistConnection.getInputStream()));
        } catch (IOException e) {
          System.out.println("Cannot read from URL: " + urlString);
          e.printStackTrace();
        }

        //Read and parse the data from the buffered reader
        try {
          //While we haven't reached the end of the file read each line
          while ((inputLine = br.readLine()) != null) {
            //Check if the line contains the "<PRE>" and "<\PRE>" tags
            Matcher openTagMatcher = openTag.matcher(inputLine);
            Matcher closeTagMatcher = closeTag.matcher(inputLine);
            if (openTagMatcher.find()) {
              readLine = true;
            } else if (closeTagMatcher.find()) {
              readLine = false;
            }

            //If we are in inside the "<PRE>" tag block then check for
            //mass coefficient table
            if (readLine) {
              //Look for scientific notation in the current line
              Matcher scientificNotationotationMatcher =
                  scientificNotation.matcher(inputLine);
              if (scientificNotationotationMatcher.find()) {
                //Split the string by whitespace
                String[] splitLine = inputLine.split("\\s+");
                for (int j = 0; j < splitLine.length; j++) {
                  if(splitLine[j].indexOf("E") != -1) {
                    indexOfEnergyCol = j;
                    break;
                  }
                }
                //Extract the beam energy and the mass attenuation coefficient from
                //the current line.
                nistBeamEnergyInMeV = Double.parseDouble(splitLine[indexOfEnergyCol]);
                massAttenCoeff = Double.parseDouble(splitLine[indexOfEnergyCol + 1]);
                //Convert the beam energy from MeV to KeV
                nistBeamEnergyInKeV = nistBeamEnergyInMeV * MEV_TO_KEV;

                //Check if the beam energy on this line of the NIST table
                //is bigger than the beam energy of the input beam
                if (nistBeamEnergyInKeV > beam.getPhotonEnergy()) {
                  break;
                } else {
                  nistBeamEnergyInKeVPrevious = nistBeamEnergyInKeV;
                  massAttenCoeffPrevious = massAttenCoeff;
                }
              }
            }
          }
        } catch (IOException e) {
          System.out.println("Cannot read from URL: " + urlString);
          e.printStackTrace();
        }

        //Get the mass attenuation coefficient given by a linear interpolation
        //between the values in the NIST table.
        double elementMassAttenuationCoefficient = massAttenCoeffPrevious +
            (massAttenCoeff - massAttenCoeffPrevious) *
            ((beam.getPhotonEnergy() - nistBeamEnergyInKeVPrevious) /
            (nistBeamEnergyInKeV - nistBeamEnergyInKeVPrevious));

        //Store the interpolated value and the atomic weights.
        elementMassAttCoeffs[i] = elementMassAttenuationCoefficient;
        elementAtomicWeights[i] = containerAtom.getAtomicWeight();
        //Add this element's contribution to the total weight
        totalWeight += containerAtom.getAtomicWeight() *
            this.elementNumsList.get(i);
      }

      //Calculate the relative atomic weights and use the weighted average
      //to give the overall mass attenuation coefficient.
      for (int k = 0; k < this.elementNamesList.size(); k++) {
        relativeAtomicWeights[k] = (this.elementNumsList.get(k) *
            elementAtomicWeights[k]) / totalWeight;
        this.massAttenuationCoefficient += (relativeAtomicWeights[k] *
            elementMassAttCoeffs[k]);
      }
    }
  }

  /**
   * Uses the container atoms (explicitly it uses their atomic numbers) to
   * generate the URL to the NIST table with the corresponding mass
   * attenuation coefficients.
   *
   * @param atomicNumber
   *            The atomic number of the element.
   *
   * @return
   *         String of the URL pointing to the location of the mass attenuation
   *         coefficients.
   */
  private String getNISTURL(int atomicNumber) {
    return String.format("http://physics.nist.gov/PhysRefData/XrayMassCoef"
        + "/ElemTab/z%s.html", String.format("%02d", atomicNumber));
  }

  /**
   * Calculate the mass thickness of the container. The mass thickness is
   * defined as the mass per unit area.
   */
  private void calculateMassThickness() {
    //Convert container thickness units from microns to centimeters.
    double thicknessInCentimeters = MICRONS_TO_CENTIMETERS * this.thickness;
    this.massThickness = this.density * thicknessInCentimeters;
  }

  /**
   * Return the mass thickness of the container
   *
   * @return
   *         Mass thickness of the sample
   */
  public double getMassThickness() {
    return this.massThickness;
  }

  /**
   * Return the mass attenuation coefficient of the container
   *
   * @return
   *         Mass attenuation coefficient
   */
  public double getMassAttenuationCoefficient() {
    return this.massAttenuationCoefficient;
  }

  /**
   * Return the attenuation factor of the container
   *
   * @return
   *         Attenuation factor
   */
  @Override
  public double getContainerAttenuationFraction() {
    return this.containerAttenuationFraction;
  }

  /**
   * Return the material from which the container is made
   *
   * @return
   *        Container material
   */
  @Override
  public String getContainerMaterial() {
    return this.material;
  }

  /**
   * @return the parser
   */
  public ElementDatabase getParser() {
    return elementDB;
  }

  /**
   * Construct a string that prints details about the sample container.
   */
  @Override
  public void containerInformation() {
    if (this.material != null) {
      String s = String.format(
          "The mass attenuation coefficient of the %s container "
              + "is %.2f centimetres^2 per gram.%n"
              + "The attenuation fraction of the beam due to the sample"
              + " container of thickness %.2f microns is: %.2f.%n"
          , this.material, this.massAttenuationCoefficient
          , this.thickness, this.containerAttenuationFraction);

      System.out.printf(s);
    }
  }

}
