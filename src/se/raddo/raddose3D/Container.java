package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *This class defines the absorption properties of the container
 * encasing the irradiated sample. This is used to add an additional
 * attenuation factor to the beam before it reaches the sample.
 */
public class Container {

  /**
   * Conversion of beam energy from MeV to KeV
   */
  private static final double MEV_TO_KEV = 1e3;

  /**
   * Conversion from microns to centimeters
   */
  private static final double MICRONS_TO_CENTIMETERS = 1e-4;

  /**
   * The material that the container is made from
   */
  private final String material;

  /**
   * The thickness of the container in microns
   */
  private final double thickness;

  /**
   * The density of the material in grams per centimetre cubed
   */
  private final double density;

  /**
   * The mass attenuation coefficient of the sample
   */
  private double massAttenuationCoefficient;

  /**
   * The mass thickness of the sample. On the NIST website the mass
   * thickness is defined as the mass per unit area.
   */
  private double massThickness;

  /**
   * The total fraction attenuation of the X-ray beam due to the container
   */
  private double containerAttenuationFraction;

  /**
   * Constructor for the Container class.
   * @param conMaterial
   *        String type argument giving the material of the container
   *        encasing the irradiated sample.
   * @param conThickness
   *        Double type argument giving the thickness of the
   *        container.
   */
  public Container(double conThickness, String conMaterial, double conDensity){
    /**
     * Initialise the instance variables
     */
    this.material = conMaterial;
    this.thickness = conThickness;
    this.density = conDensity;
  }

  /**
   * Calculate the fraction by which the beam is attenuated by the container
   * @param beam
   *        The beam object that is used to irradiate the sample
   */
  public void calculateContainerAttenuation(Beam beam){
    extractMassAttenuationCoef(beam);
    calculateMassThickness();

    this.containerAttenuationFraction = 1 - Math.exp(-this.massAttenuationCoefficient
        * this.massThickness);
  }

  /**
   * This method downloads the mass attenuation coefficients from NIST table
   * for the corresponding material and uses the beam energy to interpolate the
   * mass attenuation coefficient.
   * @param beam
   *        beam object describing the beam used.
   */
  public void extractMassAttenuationCoef(Beam beam){

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

    // Variables used in parsing
    boolean readLine = false;
    Pattern openTag = Pattern.compile("<PRE>");
    Pattern closeTag = Pattern.compile("</PRE>");
    Pattern scientificNotation = Pattern.compile("[0-9]E[-+][0-9]");
    Pattern existK = Pattern.compile("K");

    //Get the URL string
    String urlString = getNISTURL();

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
                  Matcher existKMatcher = existK.matcher(inputLine);
                  //Return the beam energy and mass attenuation coefficient values
                  if (existKMatcher.find()) {
                      nistBeamEnergyInMeV = Double.parseDouble(splitLine[3]);
                      massAttenCoeff = Double.parseDouble(splitLine[4]);
                  } else {
                      nistBeamEnergyInMeV = Double.parseDouble(splitLine[1]);
                      massAttenCoeff = Double.parseDouble(splitLine[2]);
                  }
                  //Convert the beam energy from MeV to KeV
                  nistBeamEnergyInKeV = nistBeamEnergyInMeV * MEV_TO_KEV;

                  //Check if the beam energy on this line of the NIST table
                  //is bigger than the beam energy of the input beam
                  if (nistBeamEnergyInKeV > beam.getPhotonEnergy()) {
                      break;
                  } else{
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
  double massAttenuationCoefficient = massAttenCoeffPrevious +
          (massAttenCoeff - massAttenCoeffPrevious) *
          ((beam.getPhotonEnergy() - nistBeamEnergyInKeVPrevious) /
          (nistBeamEnergyInKeV - nistBeamEnergyInKeVPrevious));

  this.massAttenuationCoefficient = massAttenuationCoefficient;

  }

  /**
   * Uses the container material given in the input file to generate the URL
   * to the NIST table with the corresponding mass attenuation coefficients
   *
   * @return
   *        String of the URL pointing to the location of the mass attenuation
   *        coefficients.
   */
  private String getNISTURL(){
    return String.format("http://physics.nist.gov/PhysRefData/XrayMassCoef"
        + "/ComTab/%s.html", this.material);
  }

  /**
   * Calculate the mass thickness of the container. The mass thickness is
   * defined as the mass per unit area.
   */
  private void calculateMassThickness(){
    //Convert container thickness units from microns to centimeters.
    double thicknessInCentimeters = MICRONS_TO_CENTIMETERS * this.thickness;
    this.massThickness = this.density * thicknessInCentimeters;
  }

  /**
   * Return the mass thickness of the container
   *
   * @return
   *        Mass thickness of the sample
   */
  public double getMassThickness(){
    return this.massThickness;
  }

  /**
   * Return the mass attenuation coefficient of the container
   *
   * @return
   *        Mass attenuation coefficient
   */
  public double getMassAttenuationCoefficient() {
    return this.massAttenuationCoefficient;
  }

  /**
   * Return the attenuation factor of the container
   *
   * @return
   *        Attenuation factor
   */
  public double getContainerAttenuationFraction(){
    return this.containerAttenuationFraction;
  }

  /**
   * Construct a string that prints details about the sample container.
   */
  public void containerInformation() {
    String s = String.format("The mass attenuation coefficient of the %s container "
        + "is %.2f centimetres^2 per gram.%n"
        + "The attentuation fraction of the beam due to the sample"
        + " container of thickness %.2f microns is: %.2f.%n"
        ,this.material, this.massAttenuationCoefficient
        ,this.thickness, this.containerAttenuationFraction);

    System.out.printf(s);
  }

}
