package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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

      br = new BufferedReader(new FileReader(csvFileName));
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
  
  /*
  public double interpolateCSV(final int i, final double interpolation, final String[] wholeLine, final String[] previousLine ) {
    double coefficient = 0;
    coefficient = Double.parseDouble(previousLine[i]) + (interpolation * (Double.parseDouble(wholeLine[i]) - Double.parseDouble(previousLine[i])));
    return coefficient;
  }
  */
}
