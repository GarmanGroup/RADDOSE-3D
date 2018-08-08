package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class ReadEnergyCSV {
  
  public double[] openCSV(final String csvFileName, final double peEnergy) {
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
    String[] previousLine = null;
    String[] wholeLine = null;
    String[] lineToUse = null;
    double[] coefficients = new double[7];
    
    double usedEnergy = 0;
    if (peEnergy <= 1.0) {
      usedEnergy = 1.01;
    }
    else if (peEnergy >= 100) {
      usedEnergy = 99.99; //sort these out
    }
    else {
      usedEnergy = peEnergy;
    }
      
    try {
      
      InputStreamReader isr = locateConstantsFile(csvFileName);
       br = new BufferedReader(isr);
      
   //   br = new BufferedReader(new FileReader(csvFileName)); //this is the issue
      while ((line = br.readLine()) != null) {

          wholeLine = line.split(cvsSplitBy);
          if (Double.parseDouble(wholeLine[0]) > Math.round(usedEnergy*100.0)/100.0) {
            double interpolation = (usedEnergy - Double.parseDouble(previousLine[0])) / 
                                   (Double.parseDouble(wholeLine[0]) - Double.parseDouble(previousLine[0])); //check how far in between values it is
            if (interpolation <= 0.5) {
              lineToUse = previousLine;
            }
            else {
              lineToUse = wholeLine;
            }
            int counter = 7;
            for (int i = 1; i < 8; i++) {
              counter -=1;
              coefficients[counter] = Double.parseDouble(lineToUse[i]);
            }
            break;

          }
          previousLine = wholeLine;
      }
      
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
  } catch (IOException e) {
      e.printStackTrace();
  } finally {
      if (br != null) {
          try {
              br.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }
    
    return coefficients;
  }
  
  /**
   * Try to locate ENERGY_FILE. This may be in the class path (ie. within a .jar
   * file), or in the file system.
   * 
   * @return
   *         InputStreamReader pointing to the correct resource.
   * @throws FileNotFoundException
   *           The file could not be found.
   * @throws UnsupportedEncodingException
   *           The file charset cannot be interpreted.
   */
  private InputStreamReader locateConstantsFile(final String csvFileName)
      throws UnsupportedEncodingException, FileNotFoundException {
    // Try to find it within class path;
    InputStream is = getClass().getResourceAsStream("/" + csvFileName);

    if (is == null) {
      // If it is not within the class path, try via the file system.
      is = new FileInputStream(csvFileName);
    }

    return new InputStreamReader(is, "US-ASCII");
  }
  
  /*
  public double interpolateCSV(final int i, final double interpolation, final String[] wholeLine, final String[] previousLine ) {
    double coefficient = 0;
    coefficient = Double.parseDouble(previousLine[i]) + (interpolation * (Double.parseDouble(wholeLine[i]) - Double.parseDouble(previousLine[i])));
    return coefficient;
  }
  */
}
