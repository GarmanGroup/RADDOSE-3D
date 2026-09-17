package se.raddo.raddose3D;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * Generate the element database and check if it contains sensible information
 */
public class ElementDatabaseTest {
  @Test
  public void checkElementDB() {
    ElementDatabase edb = ElementDatabase.getInstance();

    Element s = edb.getElement("S");
    Tolerance.isNotNull(s, "Could not find sulphur by name");

    Element s16 = edb.getElement(16);
    Tolerance.isNotNull(s16, "Could not find sulphur by element number");

    assertSame(s, s16);

    ElementTest et = new ElementTest();
    et.sulphurTests(s);

    Element o = edb.getElement("o");
    Tolerance.isNotNull(o, "Could not find oxygen by name");

    Element o8 = edb.getElement(8);
    Tolerance.isNotNull(o8, "Could not find oxygen by element number");

    assertSame(o, o8);

    for (int i = 1; i <= 83; i++) {
      Element e = edb.getElement(i);
      Tolerance.isNotNull(e, "Could not find element no. " + i);
    }
    
    Element mg = edb.getElement("Mg");
    Tolerance.isNotNull(mg, "Could not find manganese by name");
    
    et.manganeseTests(mg);
  }
}
