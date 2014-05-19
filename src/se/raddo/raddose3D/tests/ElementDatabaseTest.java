package se.raddo.raddose3D.tests;

import org.junit.Assert;
import org.testng.annotations.Test;

import se.raddo.raddose3D.Element;
import se.raddo.raddose3D.ElementDatabase;

/**
 * Generate the element database and check if it contains sensible information
 */
public class ElementDatabaseTest {
  @Test
  public void checkElementDB() {
    ElementDatabase edb = new ElementDatabase();

    Element s = edb.getElement("S");
    Assertion.isNotNull(s, "Could not find sulphur by name");

    Element s16 = edb.getElement(16);
    Assertion.isNotNull(s16, "Could not find sulphur by element number");

    Assert.assertSame(s, s16);

    ElementTest et = new ElementTest();
    et.sulphurTests(s);

    for (int i = 1; i <= 83; i++) {
      Element e = edb.getElement(i);
      Assertion.isNotNull(e, "Could not find element no. " + i);
    }
  }
}
