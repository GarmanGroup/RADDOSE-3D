package se.raddo.raddose3D.server;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Provides high level functions for access to a database to store, organize,
 * queue and retrieve RADDOSE-3D jobs.
 */
public class DatabaseConnector {
  /**
   * Constants for declaring SQL statement parameters.
   */
  private static final int SQL_1          = 1, SQL_2 = 2, SQL_3 = 3,
                                          SQL_4 = 4, SQL_5 = 5, SQL_6 = 6;

  /**
   * Lock to ensure only one thread can access a single connection. More
   * flexible than 'synchronized'.
   */
  private final Lock       lock           = new ReentrantLock();
  /** Connection handler. */
  private Connection       conn;

  /** Milliseconds to wait when trying to reconnect to the database. */
  private static final int RECONNECT_WAIT = 30000;

  /** Connection credentials. */
  private String           connUser;
  /** Connection credentials. */
  private String           connPass;

  /**
   * Version ID of currently running RADDOSE-3D instance. Use via
   * getVersionNumber()-getter.
   */
  private Long             version;

  /** Types for BLOB output. */
  public enum OutputType {
    /** Binary data. The user will have the option to download it. */
    BINARY,
    /**
     * Plain text data. The user will see at least parts of it, and be able
     * to download the entire file.
     */
    TEXT,
    /** An image. The user will see the image itself. */
    IMAGE,
    /** A preview image. A smaller version of the image with the same name. */
    IMAGEPREVIEW,
    /**
     * R code. The user will neither see this, nor be able to download it.
     * It will be executed in the background and may generate further images and
     * image previews.
     */
    R
  }

  /** Lightweight MySQL Connector/J ping. */
  private static final String PING_MARKER = "/* ping */";

  /**
   * Connect to MySQL database via Connector/J.
   * 
   * @param username
   *          Username for MySQL connection.
   * @param password
   *          Password for MySQL connection.
   * @throws SQLException
   *           MySQL database name or credentials incorrect.
   */
  public void connect(final String username, final String password)
      throws SQLException {
    if (conn == null) {
      lock.lock();

      try {
        // The newInstance() call is a work around for some
        // broken Java implementations
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connUser = username;
        connPass = password;
        reconnect();
      } catch (final ClassNotFoundException e) {
        throw new SQLException(
            "Probable cause of error: MySQL Connector/J not found", e);
      } catch (final InstantiationException e) {
        throw new SQLException(
            "Probable cause of error: MySQL Connector/J may not be the "
                + "correct version for the local architecture.", e);
      } catch (final IllegalAccessException e) {
        throw new SQLException(
            "Probable cause of error: MySQL database name or "
                + "credentials incorrect.", e);
      } finally {
        lock.unlock();
      }
    }
  }

  /**
   * Reconnect an already connected session.
   * 
   * @throws SQLException
   *           Could not reconnect. MySQL database name or credentials may be
   *           incorrect.
   */
  private void reconnect() throws SQLException {
    lock.lock();

    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        // Ignore any closing errors
      }
      conn = null;
    }

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/raddose?"
          + "user=" + connUser + "&password=" + connPass);
    } catch (SQLException s) {
      // Catch one exception, try backing off 30 seconds
      try {
        Thread.sleep(RECONNECT_WAIT);
      } catch (InterruptedException i) {
        // ignore
      }
      conn = DriverManager.getConnection("jdbc:mysql://localhost/raddose?"
          + "user=" + connUser + "&password=" + connPass);
    }
    lock.unlock();

    if (conn == null) {
      throw new SQLException();
    }
  }

  /**
   * Verify database connection. Try reestablishing database connection if it
   * failed.
   */
  private void ensureConnectionPresent() {
    lock.lock();
    if (conn == null) {
      throw new RuntimeException("Connection not present!");
    }

    Statement st = null;
    try {
      st = conn.createStatement();
      st.execute(PING_MARKER + " SELECT 1");
      return;
    } catch (SQLException ex) {
      System.out.println("SQL connection lost. Trying to reconnect...");
      try {
        reconnect();
        System.out.println("Reconnect may have succeded.");
      } catch (SQLException exint) {
        System.out.println("Reconnecting failed.");
        reportSQLException(exint);
      }
    } finally {
      try {
        if (st != null) {
          st.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      lock.unlock();
    }
  }

  /**
   * Returns the highest priority of all jobs queued in the database.
   * 
   * @return
   *         job priority, or 0 if the job queue is empty.
   */
  public Integer getHighestPriority() {
    lock.lock();
    ensureConnectionPresent();
    Statement st = null;
    ResultSet rs = null;

    try {
      st = conn.createStatement();
      rs = st
          .executeQuery("SELECT MAX(Queue) FROM queue WHERE Status = 'Queued'");
      if (rs.next()) {
        return rs.getInt(SQL_1);
      }
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      try {
        if (st != null) {
          st.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      lock.unlock();
    }
    return 0;
  }

  /**
   * Central function for reporting SQL errors.
   * Currently only writes SQL exceptions to STDERR.
   * 
   * @param e
   *          SQLException to be reported.
   */
  private void reportSQLException(final SQLException e) {
    System.err.println("SQL Exception: " + e.getMessage());
    StackTraceElement[] trace = e.getStackTrace();
    for (StackTraceElement s : trace) {
      System.err.println(s.toString());
    }
    System.err.println();
  }

  /**
   * Find the next available job with a given minimum priority.
   * 
   * @param priority
   *          Minimum priority of job to be returned.
   * @return
   *         ID of a job with priority >= $priority.
   */
  public Long getJobIDWithPriorityGreaterOrEqual(final Integer priority) {
    lock.lock();
    ensureConnectionPresent();

    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
      pst = conn.prepareStatement("SELECT JobID, "
          + "(RuntimeEst + Penalty - "
          + "(UNIX_TIMESTAMP() - UNIX_TIMESTAMP(Age))) AS Priority "
          + "FROM queue "
          + "WHERE Status = 'Queued' AND Queue >= ? "
          + "ORDER BY Priority ASC LIMIT 1");
      pst.setInt(SQL_1, priority);
      rs = pst.executeQuery();
      if (rs.next()) {
        return rs.getLong(SQL_1);
      }
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      lock.unlock();
    }
    return null;
  }

  /**
   * Obtain an exclusive lock on a job stored in the database.
   * 
   * @param jobid
   *          Unique job identifier.
   * @return
   *         TRUE if the lock could be obtained,
   *         FALSE if the lock failed (eg. job already locked).
   */
  public Boolean lockQueueJob(final Long jobid) {
    lock.lock();
    ensureConnectionPresent();

    PreparedStatement pst = null;
    Boolean success = false;

    try {
      pst = conn.prepareStatement("UPDATE queue "
          + "SET Status = 'Running' "
          + "WHERE Status = 'Queued' AND JobID = ?");
      pst.setLong(SQL_1, jobid);
      int changedRows = pst.executeUpdate();
      success = (changedRows == 1);
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
    }

    try {
      pst = conn.prepareStatement("UPDATE jobs "
          + "SET TimeStarted = NOW() "
          + "WHERE ID = ?");
      pst.setLong(SQL_1, jobid);
      pst.executeUpdate(); // ignore outcome
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
    }

    lock.unlock();

    return success;
  }

  /**
   * Retrieve the input file for a single job from the database.
   * 
   * @param id
   *          job identifier
   * @return
   *         the input file of the given job as a single String. Null if the job
   *         does not exist in the database.
   */
  public String getJob(final Long id) {
    lock.lock();

    ensureConnectionPresent();

    PreparedStatement pst = null;
    ResultSet rs = null;
    try {
      pst = conn.prepareStatement("SELECT Commands FROM jobs WHERE ID = ?");
      pst.setLong(SQL_1, id);
      rs = pst.executeQuery();
      if (rs.next()) {
        return rs.getString(SQL_1);
      }
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      lock.unlock();
    }
    return null;
  }

  /**
   * Create a virtual file in the database from a binary stream.
   * 
   * @param jobID
   *          The job number that this file is associated with.
   * @param fileName
   *          The name of the virtual file. If the file already exists, it will
   *          be overwritten.
   * @param blob
   *          The content of the virtual file as a binary FileInputStream.
   * @param blobLength
   *          The number of bytes in the file stream.
   * @param type
   *          The associated file type.
   */
  public void writeBLOB(final Long jobID, final String fileName,
      final FileInputStream blob, final Long blobLength, final OutputType type)
  {
    lock.lock();
    ensureConnectionPresent();

    PreparedStatement pst = null;
    try {
      pst = conn
          .prepareStatement("REPLACE INTO output (JobID, Name, Type, Content) "
              + "VALUES (?, ?, ?, ?)");
      pst.setLong(SQL_1, jobID);
      pst.setString(SQL_2, fileName);
      pst.setString(SQL_3, typeEnumToString(type));
      pst.setBinaryStream(SQL_4, blob, blobLength);
      pst.executeUpdate();
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      lock.unlock();
    }
  }

  /**
   * Create a virtual file in the database from a string.
   * 
   * @param jobID
   *          The job number that this file is associated with.
   * @param fileName
   *          The name of the virtual file. If the file already exists, it will
   *          be overwritten.
   * @param fileContent
   *          The content of the virtual file as a single string.
   * @param type
   *          The associated file type.
   */
  public void writeOutputFile(final Long jobID, final String fileName,
      final String fileContent, final OutputType type) {
    lock.lock();
    ensureConnectionPresent();

    PreparedStatement pst = null;
    try {
      pst = conn
          .prepareStatement("REPLACE INTO output (JobID, Name, Type, Content) "
              + "VALUES (?, ?, ?, ?)");
      pst.setLong(SQL_1, jobID);
      pst.setString(SQL_2, fileName);
      pst.setString(SQL_3, typeEnumToString(type));
      pst.setString(SQL_4, fileContent);
      pst.executeUpdate();
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      lock.unlock();
    }
  }

  /**
   * Append a String to a file of a particular job, stored in the database.
   * This can be used to work around database query length limitations.
   * 
   * @param jobID
   *          Number of the job the file belongs to.
   * @param fileName
   *          Name of the file.
   * @param stringToAppend
   *          String to be appended.
   */
  public void appendOutputFile(final Long jobID, final String fileName,
      final String stringToAppend) {
    lock.lock();
    ensureConnectionPresent();

    PreparedStatement pst = null;
    try {
      pst = conn
          .prepareStatement("UPDATE output SET Content = CONCAT(Content, ?) "
              + "WHERE JobID = ? AND Name = ?");
      pst.setString(SQL_1, stringToAppend);
      pst.setLong(SQL_2, jobID);
      pst.setString(SQL_3, fileName);
      pst.executeUpdate();
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      lock.unlock();
    }
  }

  /**
   * Declare the job finished and remove it from the job queue.
   * 
   * @param jobID
   *          The unique job identifier.
   */
  public void finalizeJob(final Long jobID) {
    lock.lock();
    ensureConnectionPresent();

    PreparedStatement pst = null;
    try {
      pst = conn
          .prepareStatement("UPDATE jobs "
              + "SET Finished = 'Y', Version = ?, TimeCompleted = NOW() "
              + "WHERE ID = ?");
      pst.setLong(SQL_1, getVersionNumber());
      pst.setLong(SQL_2, jobID);
      pst.executeUpdate();
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
    }

    try {
      pst = conn.prepareStatement("DELETE FROM queue WHERE JobID = ?");
      pst.setLong(SQL_1, jobID);
      pst.executeUpdate();
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
    }

    lock.unlock();
  }

  /**
   * Retrieve the database internal representation (an integer number) of a
   * given RADDOSE3D instance version string.
   * 
   * @param versionString
   *          A string containing a RADDOSE3D version (e.g. "1.1.1000").
   * @return
   *         A number representing the version of the given RADDOSE3D instance
   *         string or null if the version string has not been defined in the
   *         database.
   */
  private Long selectVersionNumber(final String versionString) {
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
      pst = conn.prepareStatement("SELECT ID FROM versions WHERE Version = ?");
      pst.setString(SQL_1, versionString);
      rs = pst.executeQuery();
      if (rs.next()) {
        return rs.getLong(SQL_1);
      }
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
    }

    return null;
  }

  /**
   * Retrieve the database internal representation (an integer number) of the
   * current RADDOSE3D instance version. If the current RADDOSE3D instance
   * version is unknown to the database, then register it.
   * 
   * @return
   *         A number representing the version of the currently running
   *         RADDOSE3D instance.
   */
  public Long getVersionNumber() {
    if (version == null) {
      lock.lock();
      ensureConnectionPresent();

      version = selectVersionNumber(se.raddo.raddose3D.Version.VERSION_STRING);

      if (version == null) {
        // version not yet registered
        PreparedStatement pst = null;

        try {
          pst = conn
              .prepareStatement("INSERT INTO versions "
                  + "(jar, Version, Compilation) "
                  + "VALUES (NULL, ?, NOW())");
          pst.setString(SQL_1, se.raddo.raddose3D.Version.VERSION_STRING);
          pst.executeUpdate();
        } catch (SQLException ex) {
          reportSQLException(ex);
        } finally {
          try {
            if (pst != null) {
              pst.close();
            }
          } catch (SQLException ex) {
            reportSQLException(ex);
          }
        }

        version = selectVersionNumber(
            se.raddo.raddose3D.Version.VERSION_STRING);

        if (version == null) {
          throw new RuntimeException("Could not determine version ID.");
        }
      }

      lock.unlock();
    }
    return version;
  }

  /**
   * Convert output type ENUM to the appropriate string representation used in
   * the database.
   * 
   * @param type
   *          Java ENUM.
   * @return
   *         Database ENUM string.
   */
  private String typeEnumToString(final OutputType type) {
    switch (type) {
      case BINARY:
        return "binary";
      case IMAGE:
        return "image";
      case IMAGEPREVIEW:
        return "preview";
      case R:
        return "r";
      case TEXT:
        return "text";
      default:
        throw new RuntimeException("Using undefined OutputType " + type);
    }
  }

  public void saveTimingData(final Long jobID, final Long x1, final Long x2,
      final Double realTime, final Double userTime) {
    lock.lock();
    ensureConnectionPresent();

    PreparedStatement pst = null;
    try {
      pst = conn
          .prepareStatement("REPLACE INTO runtimeestimate "
              + "(JobID, Version, X1, X2, realtime, usertime) "
              + "VALUES (?, ?, ?, ?, ?, ?)");
      pst.setLong(SQL_1, jobID);
      pst.setLong(SQL_2, getVersionNumber());
      pst.setLong(SQL_3, x1);
      pst.setLong(SQL_4, x2);
      pst.setDouble(SQL_5, realTime);
      pst.setDouble(SQL_6, userTime);
      pst.executeUpdate();
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
    }

    lock.unlock();
  }

  public Boolean markThreadAsCrashed(final Long jobID, final String message,
      final Throwable reason) {
    lock.lock();
    ensureConnectionPresent();

    Boolean success = false;

    PreparedStatement pst = null;
    try {
      pst = conn
          .prepareStatement("REPLACE INTO crashes (JobID, StackTrace) "
              + "VALUES (?, ?)");
      pst.setLong(SQL_1, jobID);

      String crashmessage = message.concat("Crash due to "
          + reason.getMessage() + "\n");
      StackTraceElement[] trace = reason.getStackTrace();
      for (StackTraceElement s : trace) {
        crashmessage = crashmessage.concat(s.toString() + "\n");
      }

      pst.setString(SQL_2, crashmessage);
      pst.executeUpdate();
      success = true;
    } catch (SQLException ex) {
      reportSQLException(ex);
    } finally {
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        reportSQLException(ex);
      }
      lock.unlock();
    }
    return success;
  }
}
