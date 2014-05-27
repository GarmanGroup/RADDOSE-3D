package se.raddo.raddose3D;

import org.antlr.runtime.ANTLRStringStream;

/**
 * A parser that reads from a string.
 */
public class InputParserString extends InputParser {

  /**
   * creates a parser that reads from a String.
   * 
   * @param configstring
   *          String object to be parsed
   */
  public InputParserString(final String configstring) {
    super(new ANTLRStringStream(configstring));
  }
}
