package se.raddo.raddose3D.server;

import java.io.FileInputStream;

import se.raddo.raddose3D.server.DatabaseConnector.OutputType;

/**
 * WriterSQLBLOB can write a binary large object to an SQL record.
 * It is not a Writer implementation, as it does not deal with Strings, but
 * only with a FileInputStream.
 */
public class WriterSQLBLOB {
  /** Reference to initialized database connection. */
  private final DatabaseConnector sql;
  /** Unique ID referencing the job in the SQL database. */
  private final Long              job;
  /** Unique name referencing the output field in the SQL database. */
  private final String            filename;
  /** Type of the data that will eventually be written to the database. */
  private final OutputType        type;

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
  public WriterSQLBLOB(final DatabaseConnector databaseConnection,
      final Long jobID, final String outputFilename, final OutputType blobType)
  {
    sql = databaseConnection;
    filename = outputFilename;
    job = jobID;
    type = blobType;
  }

  /**
   * Copies binary stream data into database. Will overwrite previously stored
   * data. Does not require a close()-call.
   * 
   * @param fBLOB
   *          binary stream of data.
   * @param fBLOBLength
   *          Number of bytes to be copied from fBLOB.
   */
  public void write(final FileInputStream fBLOB, final Long fBLOBLength) {
    sql.writeBLOB(job, filename, fBLOB, fBLOBLength, type);
  }
}
