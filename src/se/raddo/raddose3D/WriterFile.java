package se.raddo.raddose3D;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * WriterFile writes all received data to a predefined file.
 */
public class WriterFile extends Writer {
  /** Reference to the output file. */
  private final BufferedWriter outFile;

  /** Name of the output file, used for meaningful error messages. */
  private final String         outFileName;

  /**
   * Constructor that opens a specified file for writing.
   * 
   * @param filename
   *          Name of the file, may include absolute or relative path.
   * @throws IOException
   *           thrown, if file cannot be opened for writing.
   */
  public WriterFile(final String filename) throws IOException {
    outFileName = filename;
    outFile = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(filename), "UTF-8"));
  }

  @Override
  public void write(final String s) {
    try {
      outFile.write(s);
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("WriterFile: Could not write to file " + outFileName);
    }
  }

  @Override
  public void write(final StringBuffer b) {
    write(new String(b));
  }

  @Override
  public void flush() {
    try {
      outFile.flush();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("WriterFile: Could not flush file " + outFileName);
    }
  }

  @Override
  public void close() {
    try {
      outFile.close();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("WriterFile: Could not close file " + outFileName);
    }
  }

}
