package se.raddo.raddose3D.tests;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import static org.testng.Assert.*;
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

    assertEquals(((WriterString) w).getDataString(), "asdf\nbla");
    System.out.println("@Test - testWriterString");
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testWritingAfterClosingShouldFailWithString() {
    Writer w = new WriterString();
    w.write("asdf");
    w.close();
    w.write("asdf");
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testWritingAfterClosingShouldFailWithStringBuffer() {
    Writer w = new WriterString();
    w.write("asdf");
    w.close();
    w.write(new StringBuffer("asdf"));
  }

  @Test
  public void testWriterStringAsOutputStream()
      throws UnsupportedEncodingException {
    WriterString w = new WriterString();
    PrintWriter p = new PrintWriter(new OutputStreamWriter(w, "UTF-8"));

    p.write("asdf");
    p.write("");
    p.write("\n");
    p.write("bla");
    p.close();

    assertEquals(w.getDataString(), "asdf\nbla");
    System.out.println("@Test - testWriterStringAsOutputStream");
  }

}
