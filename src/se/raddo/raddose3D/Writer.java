package se.raddo.raddose3D;

/**
 * Writer is an interface allowing Strings and StringBuffers to be passed to
 * implementing classes which may then write these data to the console,
 * different files, strings or other targets. Implementing classes should expect
 * any number of write-calls followed by a single call of the close() method.
 * 
 * TODO: Make Writer an abstract class extending OutputStream.
 */
public interface Writer {
  /**
   * Write a String.
   * 
   * @param s
   *          String to be written
   */
  public void write(String s);

  /**
   * Write a StringBuffer.
   * 
   * @param b
   *          Stringbuffer to be written
   */
  public void write(StringBuffer b);

  /**
   * Close the output. Open files will be flushed and closed. After close() is
   * called, the behaviour of further write() calls is undefined.
   * Implementing classes should drop references to stored objects at this
   * point.
   */
  public void close();
}
