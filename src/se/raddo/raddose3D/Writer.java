package se.raddo.raddose3D;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Writer is an interface allowing Strings and StringBuffers to be passed to
 * implementing classes which may then write these data to the console,
 * different files, strings or other targets. Implementing classes should expect
 * any number of write-calls followed by a single call of the close() method.
 */
public abstract class Writer extends OutputStream {
  /**
   * Write a String.
   * 
   * @param s
   *          String to be written
   */
  public abstract void write(String s);

  /**
   * Write a StringBuffer.
   * 
   * @param b
   *          StringBuffer to be written
   */
  public abstract void write(StringBuffer b);

  /**
   * Close the output. Open files will be flushed and closed. After close() is
   * called, the behaviour of further write() calls is undefined.
   * Implementing classes should drop references to stored objects at this
   * point.
   */
  @Override
  public abstract void close();

  @Override
  public void write(final int i) {
    write(String.valueOf(Character.toChars(i)));
  }

  @Override
  public void write(final byte[] b) {
    // Use the string based write method instead of individual write(int) calls
    try {
      write(new String(b, "UTF-8"));
    } catch (UnsupportedEncodingException ex) {
      throw new IllegalArgumentException(
          "Could not convert byte-array to string for output", ex);
    }
  }
}
