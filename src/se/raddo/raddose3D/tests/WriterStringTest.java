package se.raddo.raddose3D.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import se.raddo.raddose3D.Writer;
import se.raddo.raddose3D.WriterString;

public class WriterStringTest {

  @Test
  public void testWriterString() {
    Writer w = new WriterString();

    w.write("asdf");
    w.write("");
    w.write(new StringBuffer("\n"));
    w.write("bla");
    w.close();

    Assert.assertEquals(((WriterString) w).getDataString(), "asdf\nbla");
    System.out.println("@Test - testWriterString");
  }

  @Test
  public void testWriterStringAfterClose() {
    Writer w = new WriterString();
    Boolean exceptionEncountered = false;

    w.write("asdf");
    w.close();

    try {
      w.write("asdf");
    } catch (RuntimeException e) {
      exceptionEncountered = true;
    }
    Assert.assertTrue(exceptionEncountered);

    exceptionEncountered = false;
    try {
      w.write(new StringBuffer("asdf"));
    } catch (RuntimeException e) {
      exceptionEncountered = true;
    }
    Assert.assertTrue(exceptionEncountered);

    System.out.println("@Test - testWriterStringAfterClose");
  }

}
