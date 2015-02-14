package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *This class defines the absorption properties of the container
 * encasing the irradiated sample. This is used to add an additional
 * attenuation factor to the beam before it reaches the sample.
 */
public class Container {

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
   * Constructor for the Container class.
   * @param conMaterial
   *        String type argument giving the material of the container
   *        encasing the irradiated sample.
   * @param conThickness
   *        Double type argument giving the thickness of the
   *        container.
   */
  public Container(Double conThickness, String conMaterial, Double conDensity){
    
    if (conThickness == null || conMaterial == null || conDensity == null){
      this.material = null;
      this.thickness = 0;
      this.density = 0;
    } else {
    /**
     * Initialise the instance variables
     */
    this.material = conMaterial;
    this.thickness = conThickness;
    this.density = conDensity;
    }
    
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
        while ((inputLine = br.readLine()) != null) {
            System.out.println(inputLine);
        }
    } catch (IOException e) {
        System.out.println("Cannot read from URL: " + urlString);
        e.printStackTrace();
    }
    
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
        + "/ComTab/pyrex.html", this.material);
  }



}
