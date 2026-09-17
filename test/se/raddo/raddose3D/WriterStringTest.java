package se.raddo.raddose3D;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


public class WriterStringTest {

  @Test
  public void testWriterString() {
    Writer w = new WriterString();

    w.write("asdf");
    w.write("");
    w.write(new StringBuffer("\n"));
    w.write("bla");
    w.close();

    assertEquals("asdf\nbla", ((WriterString) w).getDataString());
    System.out.println("@Test - testWriterString");
  }

  @Test
  public void testWritingAfterClosingShouldFailWithString() {
    assertThrows(RuntimeException.class, () -> {
      Writer w = new WriterString();
      w.write("asdf");
      w.close();
      w.write("asdf");
    });
  }

  @Test
  public void testWritingAfterClosingShouldFailWithStringBuffer() {
    assertThrows(RuntimeException.class, () -> {
      Writer w = new WriterString();
      w.write("asdf");
      w.close();
      w.write(new StringBuffer("asdf"));
    });
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

    assertEquals("asdf\nbla", w.getDataString());
    System.out.println("@Test - testWriterStringAsOutputStream");
  }

}
