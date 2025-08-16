package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

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
    return String.format("https://physics.nist.gov/PhysRefData/XrayMassCoef"
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
      Pattern tableRowPattern = Pattern.compile("<TR VALIGN=\"top\" ALIGN=\"right\">");
      Pattern tableCellPattern = Pattern.compile("<TD>([^<]+)</TD>");
      Pattern scientificNotation = Pattern.compile("([0-9]\\.[0-9]+E[+-][0-9]+)");
      
      // Debug flag - set to true to enable debug output
      boolean debug = false;

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
        // Set user agent to avoid 403 Forbidden errors from NIST server
        nistConnection.setRequestProperty("User-Agent", 
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        // Set additional headers that some servers expect
        nistConnection.setRequestProperty("Accept", 
            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        nistConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        nistConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
        nistConnection.setRequestProperty("Connection", "keep-alive");
        nistConnection.setRequestProperty("Upgrade-Insecure-Requests", "1");
      } catch (IOException e) {
        System.out.print("Cannot open URL: " + urlString);
        e.printStackTrace();
      }

      //Read the data from the NIST table webpage into a buffered reader
      try {
        InputStream inputStream = nistConnection.getInputStream();
        
        // Check if the response is gzip compressed
        String encoding = nistConnection.getContentEncoding();
        if (debug) {
          System.out.println("Content encoding: " + encoding);
        }
        if ("gzip".equalsIgnoreCase(encoding)) {
          if (debug) {
            System.out.println("Decompressing gzip content");
          }
          inputStream = new GZIPInputStream(inputStream);
        }
        
        br = new BufferedReader(new InputStreamReader(inputStream));
      } catch (IOException e) {
        System.out.println("Cannot read from URL: " + urlString);
        e.printStackTrace();
      }

      //Read and parse the data from the buffered reader
      StringBuilder htmlContent = new StringBuilder();
      try {
        //Read all content first
        while ((inputLine = br.readLine()) != null) {
          htmlContent.append(inputLine).append("\n");
        }
        
        // Now parse the complete HTML content
        String html = htmlContent.toString();
        if (debug) {
          System.out.println("Beam energy to find: " + beam.getPhotonEnergy() + " keV");
          System.out.println("HTML content length: " + html.length());
          if (html.length() > 0) {
            System.out.println("First 200 chars: " + html.substring(0, Math.min(200, html.length())));
            // Also check if we can find the expected HTML structure
            if (html.contains("<TD>")) {
              System.out.println("Found TD tags in HTML");
            } else {
              System.out.println("No TD tags found in HTML");
            }
          }
        }
        
        // Extract all TD cell values that contain scientific notation
        Pattern cellPattern = Pattern.compile("<TD>([0-9]\\.[0-9]+E[+-][0-9]+)</TD>", Pattern.CASE_INSENSITIVE);
        Matcher cellMatcher = cellPattern.matcher(html);
        
        List<String> scientificValues = new ArrayList<String>();
        while (cellMatcher.find()) {
          scientificValues.add(cellMatcher.group(1));
        }
        
        if (debug) {
          System.out.println("Found " + scientificValues.size() + " scientific notation values");
          for (int i = 0; i < Math.min(10, scientificValues.size()); i++) {
            System.out.println("  Value " + i + ": " + scientificValues.get(i));
          }
        }
        
        // Process values in groups of 3 (energy, mu/rho, mu_en/rho)
        int dataPointsFound = 0;
        for (int i = 0; i < scientificValues.size() - 2; i += 3) {
          String energyStr = scientificValues.get(i);
          String muRhoStr = scientificValues.get(i + 1);
          
          try {
            nistBeamEnergyInMeV = Double.parseDouble(energyStr);
            massAttenCoeff = Double.parseDouble(muRhoStr);
            
            //Convert the beam energy from MeV to KeV
            nistBeamEnergyInKeV = nistBeamEnergyInMeV * MEV_TO_KEV;
            
            dataPointsFound++;
            if (debug && dataPointsFound <= 5) {
              System.out.println("Found data point: " + nistBeamEnergyInKeV + " keV, " + massAttenCoeff + " cm²/g");
            }

            //Check if the beam energy on this line of the NIST table
            //is bigger than the beam energy of the input beam
            if (nistBeamEnergyInKeV > beam.getPhotonEnergy()) {
              if (debug) {
                System.out.println("Found energy " + nistBeamEnergyInKeV + " keV > beam energy " + beam.getPhotonEnergy() + " keV, stopping");
              }
              break;
            } else {
              nistBeamEnergyInKeVPrevious = nistBeamEnergyInKeV;
              massAttenCoeffPrevious = massAttenCoeff;
            }
          } catch (NumberFormatException e) {
            if (debug) {
              System.out.println("Could not parse: " + energyStr + " or " + muRhoStr);
            }
            continue;
          }
        }
        
        if (debug) {
          System.out.println("Total data points found: " + dataPointsFound);
          System.out.println("Previous energy: " + nistBeamEnergyInKeVPrevious + " keV, coeff: " + massAttenCoeffPrevious);
          System.out.println("Next energy: " + nistBeamEnergyInKeV + " keV, coeff: " + massAttenCoeff);
        }
        
      } catch (IOException e) {
        System.out.println("Error reading from URL: " + urlString);
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
