package se.raddo.raddose3D.server;

import se.raddo.raddose3D.Writer;
import se.raddo.raddose3D.server.DatabaseConnector.OutputType;

/**
 * WriterSQL writes all received data to an SQL record on a close() call.
 */
public class WriterSQL extends Writer {
  /** Reference to initialized database connection. */
  private final DatabaseConnector sql;
  /** Unique ID referencing the job in the SQL database. */
  private final Long              job;
  /** Unique name referencing the output field in the SQL database. */
  private final String            filename;

  /** Internal output buffer. */
  private StringBuffer            buffer          = new StringBuffer();
  /** Maximum buffer length. Causes flush if exceeded. */
  private static final int        BUFFERMAXLENGTH = 10000000;

  /**
   * Write output to a field in an SQL database.
   * 
   * @param databaseConnection
   *          Reference to an active database connection.
   * @param jobID
   *          Unique identifier referencing the job in the database.
   * @param outputFilename
   *          Unique name for the output data.
   * @param blobType
   *          Type of the data to be written.
   */
  public WriterSQL(final DatabaseConnector databaseConnection,
      final Long jobID, final String outputFilename, final OutputType blobType)
  {
    sql = databaseConnection;
    filename = outputFilename;
    job = jobID;
    sql.writeOutputFile(job, filename, "", blobType);
  }

  @Override
  public void write(final String s) {
    buffer.append(s);
    checkbuffersize();
  }

  @Override
  public void write(final StringBuffer b) {
    buffer.append(b);
    checkbuffersize();
  }

  /**
   * Check if the buffer length exceeds BUFFERMAXLENGTH. If it does a string of
   * size BUFFERMAXLENGTH is flushed to the database.
   */
  private void checkbuffersize() {
    while (buffer.length() > BUFFERMAXLENGTH) {
      sql.appendOutputFile(job, filename, buffer.substring(0, BUFFERMAXLENGTH));
      buffer.delete(0, BUFFERMAXLENGTH);
    }
  }

  @Override
  public void close() {
    sql.appendOutputFile(job, filename, buffer.toString());
    buffer = null;
  }
}
