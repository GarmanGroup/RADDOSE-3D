package se.raddo.raddose3D;

import java.io.IOException;
import org.antlr.runtime.ANTLRFileStream;

/**
 * A parser that reads from a single file.
 * 
 * @author Markus Gerstel
 */
public class InputParserFile extends InputParser {

  /**
   * creates a parser that reads from a file.
   * 
   * @param filename
   *          name of the file to be parsed
   * @throws IOException
   *           thrown when there is an error opening the file
   */
  public InputParserFile(final String filename) throws IOException {
    super(new ANTLRFileStream(filename));
  }

  /**
   * creates a parser that reads from a file with a custom encoding.
   * 
   * @param filename
   *          name of the file to be parsed
   * @param encoding
   *          file encoding, e.g. "UTF8"
   * @throws IOException
   *           thrown when there is an error opening the file
   */
  public InputParserFile(final String filename, final String encoding)
      throws IOException {
    super(new ANTLRFileStream(filename, encoding));
  }

}
