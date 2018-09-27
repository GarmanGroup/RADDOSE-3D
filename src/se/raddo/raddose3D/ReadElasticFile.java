package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadElasticFile {
  
  public double openFile(final String fileName, final double eEnergy, final int Z) {
    BufferedReader br = null;
    String line = "";
    String splitBy = " ";
    String[] previousLine = null;
    String[] wholeLine = null;
    String[] lineToUse = null;
    double elastic_cross = 0.;
    final int numberElements = 9;
    int count = 0;
    
    try {
      
      InputStreamReader isr = locateConstantsFile(fileName);
       br = new BufferedReader(isr);
      
      while ((line = br.readLine()) != null) {
          count += 1;
          wholeLine = line.split(splitBy);
          if (Integer.parseInt(wholeLine[0]) == Z) {
            int index = ((int) (eEnergy/50)) + 1;   // Just rounds down to int for 100, 200 and 300 for now
            elastic_cross = Double.parseDouble(wholeLine[index]);
            break;
          }
            
          previousLine = wholeLine;
          if (count == numberElements) {
            break;
          }
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
    
    return elastic_cross;
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
  private InputStreamReader locateConstantsFile(final String fileName)
      throws UnsupportedEncodingException, FileNotFoundException {
    // Try to find it within class path;
    InputStream is = getClass().getResourceAsStream("/" + fileName);

    if (is == null) {
      // If it is not within the class path, try via the file system.
      is = new FileInputStream(fileName);
    }

    return new InputStreamReader(is, "US-ASCII");
  }

}
