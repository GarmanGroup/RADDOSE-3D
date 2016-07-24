package se.raddo.raddose3D.tests;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

import se.raddo.raddose3D.Element;
import se.raddo.raddose3D.ElementDatabase;

/**
 * Generate the element database and check if it contains sensible information
 */
public class ElementDatabaseTest {
  @Test
  public void checkElementDB() {
    ElementDatabase edb = ElementDatabase.getInstance();

    Element s = edb.getElement("S");
    Assertion.isNotNull(s, "Could not find sulphur by name");

    Element s16 = edb.getElement(16);
    Assertion.isNotNull(s16, "Could not find sulphur by element number");

    assertSame(s, s16);

    ElementTest et = new ElementTest();
    et.sulphurTests(s);

    Element o = edb.getElement("o");
    Assertion.isNotNull(o, "Could not find oxygen by name");

    Element o8 = edb.getElement(8);
    Assertion.isNotNull(o8, "Could not find oxygen by element number");

    assertSame(o, o8);

    for (int i = 1; i <= 83; i++) {
      Element e = edb.getElement(i);
      Assertion.isNotNull(e, "Could not find element no. " + i);
    }
    
    Element mg = edb.getElement("Mg");
    Assertion.isNotNull(mg, "Could not find manganese by name");
    
    et.manganeseTests(mg);
  }
}
