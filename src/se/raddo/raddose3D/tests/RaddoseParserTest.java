package se.raddo.raddose3D.tests;

import org.antlr.runtime.*;
import org.testng.annotations.*;

import se.raddo.raddose3D.parser.InputfileLexer;
import se.raddo.raddose3D.parser.InputfileParser;

/** Extremly basic instantiation tests of the InputfileParser.
 * @author Markus Gerstel
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
