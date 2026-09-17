package se.raddo.raddose3D;

import org.antlr.runtime.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import se.raddo.raddose3D.parser.InputfileLexer;
import se.raddo.raddose3D.parser.InputfileParser;

/** Extremly basic instantiation tests of the InputfileParser.
 */
public class RaddoseParserTest {

  @Test
  public void testInstantiateLexer() {
    new InputfileLexer();
    System.out.println("@Test - testInstantiateLexer");
  }

  @Test
  public void testInstantiateParser() {
    new InputfileParser(new CommonTokenStream());
    System.out.println("@Test - testInstantiateParser");
  }

}
