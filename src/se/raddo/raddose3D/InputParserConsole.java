package se.raddo.raddose3D;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * A parser that reads from STDIN.
 * 
 * @author Markus Gerstel
 */
public class InputParserConsole extends InputParserString {

  /**
   * Read input from console and pass it to InputParserString constructor.
   * 
   * @throws IOException
   *           thrown when there are errors opening STDIN reader
   */
  public InputParserConsole() throws IOException {
    super(getFileFromConsole());
  }

  /**
   * Read from STDIN. This needs to be outside the constructor, so that the
   * superclass (InputParserString) constructor can be used.
   * 
   * @return
   *         String containing input from STDIN.
   * @throws IOException
   *           thrown when there are errors opening STDIN reader
   */
  private static String getFileFromConsole() throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuffer sb = new StringBuffer();

    String s;
    while ((s = in.readLine()) != null) {
      sb.append(s);
      sb.append("\n");
    }
    // Ctrl-Z terminates input

    return new String(sb);
  }
}
