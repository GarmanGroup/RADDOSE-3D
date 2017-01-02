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
   * @return the parser
   */
  public ElementDatabase getParser() {
    return elementDB;
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
      double totalWeight = 0;

      //Create arrays to store the mass attenuation coefficients and the atomic weights
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
      this.massAttenuationCoefficient = 0;
      for (int k = 0; k < this.elementNamesList.size(); k++) {
        relativeAtomicWeights[k] = (this.elementNumsList.get(k) *
            elementAtomicWeights[k]) / totalWeight;
        this.massAttenuationCoefficient += (relativeAtomicWeights[k] *
            elementMassAttCoeffs[k]);
      }
    }
  }
}
