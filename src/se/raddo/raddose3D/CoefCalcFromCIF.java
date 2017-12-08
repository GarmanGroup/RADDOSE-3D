package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;

public class CoefCalcFromCIF extends CoefCalcCompute{
  public CoefCalcFromCIF(final String cifFilePath) {
    getCIFFile(cifFilePath);
    super.calculateDensity(); //again, to fill the present elements
  }
  
  public void getCIFFile(final String cifFilePath) {
    BufferedReader in = null;
    InputStreamReader isr = null;
    try {
      File file = new File (cifFilePath); 
      isr = new InputStreamReader(new FileInputStream(file));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("Cannot read from specified path.");
    }
    in = new BufferedReader(isr);
    readCIFFile(in);
  }
  
  public void readCIFFile(BufferedReader in) {
    String inputLine;
    int counter = 0;
    try {
      while ((inputLine = in.readLine()) != "#END") { // there are blank lines so this needs to change
        
        if (inputLine != null) {
          counter = 0;
//        if (inputLine.trim().length() > 0) {
        parseCIFLine(inputLine);
  //      }
        }
        else {
          counter += 1;
        }
        
        if (counter > 1) {
          break;
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("Cannot read from URL.");
      e.printStackTrace();
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Line length error encounted in URL line");
      e.printStackTrace();
    }
  }
  
  public void parseCIFLine(final String inputLine) {
    int spaceIndex = inputLine.indexOf(" ");
    String directive = null;
    if (spaceIndex >= 0) {
      directive = inputLine.substring(0, spaceIndex).trim();
    }
    if (Objects.equals(directive, "_chemical_formula_sum")) { //remember to use the moiety if the sum never exists
      parseChemicalFormula(inputLine);
    }
    
    if (Objects.equals(directive, "_cell_volume")) {
      //function to get index of first digit
      String theValue = inputLine.substring(firstDigitIndex(inputLine));
      double theNumber;
      if (theValue.contains("(")){
        theNumber = Double.parseDouble(theValue.substring(0, theValue.indexOf("(")));
      }
      else {
        theNumber = Double.parseDouble(theValue.trim());
      }
      cellVolume = theNumber; //currently in A^2
    }
    
  }
  
  public void parseChemicalFormula(final String inputLine) {
    int apostropheIndexOne = inputLine.indexOf("'");
    int apostropheIndexTwo = inputLine.lastIndexOf("'");
    String elements = inputLine.substring(apostropheIndexOne + 1, apostropheIndexTwo);
    
    int countElements = countElements(elements);
    
    for (int i = 0; i < countElements; i++) {
      String elementName;
      double elementOccurence;
      
      int firstSpace = 0;
      if (i == countElements -1) {
        firstSpace = elements.length();
      }
      else {
        firstSpace = elements.indexOf(" ");
      }
      
      int elementLetterLength;
      if (Character.isLetter(elements.charAt(1))) {
        elementLetterLength = 2;
      }
      else {
        elementLetterLength = 1;
      }
    
        elementName = elements.substring(0, elementLetterLength);
      
        if (firstSpace != elementLetterLength) {
        elementOccurence = Double.parseDouble(elements.substring(elementLetterLength, firstSpace));
        }
        else {
          elementOccurence = 1;
        } 
        Element thisElement = this.getParser().getElement(elementName.toUpperCase());
        setMacromolecularOccurrence(thisElement, elementOccurence);
      
        //chop this element off elements string
        if (i != countElements -1) {
        elements = elements.substring(firstSpace + 1);
        }
      }
    }


  
  public int countElements(final String elements) {
    int count = 0;
    for (int i=0; i < elements.length(); i++)
    {
        if (elements.charAt(i) == ' ')
        {
             count++;
        }
    }
    count += 1; //  one more element than spaces
    return count;
  }
  
  
  public int firstDigitIndex(final String inputLine) {
    int index = 0;
    for (int i=0; i < inputLine.length(); i++) {
      if (Character.isDigit(inputLine.charAt(i))){
        index = i;
        break;
      }
    }
    return index;
  }
}
