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
   * Uses the container material given in the input file to generate the URL
   * to the NIST table with the corresponding mass attenuation coefficients
   *
   * @return
   *         String of the URL pointing to the location of the mass attenuation
   *         coefficients.
   */
  private String getNISTURL() {
    return String.format("http://physics.nist.gov/PhysRefData/XrayMassCoef"
        + "/ComTab/%s.html", this.material);
  }

  /**
   * This method downloads the mass attenuation coefficients from NIST table
   * for the corresponding material and uses the beam energy to interpolate the
   * mass attenuation coefficient.
   *
   * @param beam
   *          beam object describing the beam used.
   */
  @Override
  public void extractMassAttenuationCoef(Beam beam) {

    if (this.material != null) {
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
      int indexOfEnergyCol = -1;

      // Regular expressions used in parsing
      boolean readLine = false;
      Pattern openTag = Pattern.compile("<PRE>");
      Pattern closeTag = Pattern.compile("</PRE>");
      Pattern scientificNotation = Pattern.compile("[0-9]E[-+][0-9]");

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
      double massAttenuationCoefficient = massAttenCoeffPrevious +
          (massAttenCoeff - massAttenCoeffPrevious) *
          ((beam.getPhotonEnergy() - nistBeamEnergyInKeVPrevious) /
          (nistBeamEnergyInKeV - nistBeamEnergyInKeVPrevious));
      
      this.massAttenuationCoefficient = massAttenuationCoefficient;
    }
  }
}
