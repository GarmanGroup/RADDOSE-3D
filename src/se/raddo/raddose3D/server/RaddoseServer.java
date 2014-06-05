package se.raddo.raddose3D.server;

import java.sql.SQLException;
// Notice, do not import com.mysql.jdbc.*
// or you will have problems!
import java.util.Iterator;
import java.util.Vector;

/**
 * Server for batch processing of RADDOSE-3D jobs.
 */
public final class RaddoseServer {
  /** Maximum time to wait for remaining jobs in seconds. */
  private static final int     SHUTDOWNTIME       = 60;

  /** limit for the maximum number of parallel jobs to be run. */
  private static final Integer MAXPROCESSES       = 2;

  /**
   * Maximum time in milliseconds to wait in main loop.
   * Events will interrupt.
   */
  private static final long    TICKWAIT           = 5000;

  /** Number of milliseconds per second. */
  private static final int     SECONDSINMS        = 1000;

  /**
   * If set to true, then the server will not start processing any new jobs.
   * Once all running jobs are completed, the server will exit.
   */
  private Boolean              shutdownInProgress = false;

  /**
   * Main method for RADDOSE-3D server.
   * 
   * @param args
   *          Command line parameters.
   */
  public static void main(final String[] args) {
    System.out.println("RADDOSE-3D Server starting up.");

    RaddoseServer rds = new RaddoseServer();
    rds.run(MAXPROCESSES);
  }

  /**
   * Main loop of the RADDOSE-3D server.
   * 
   * @param maxProcesses
   *          maximum number of parallel jobs to be run.
   */
  private void run(final Integer maxProcesses) {
    createShutdownHook();

    DatabaseConnector sql = new DatabaseConnector();
    try {
      sql.connect("root", "raddose");
    } catch (SQLException e) {
      System.err.println("Exception while connecting to SQL database: " + e);
      return;
    }

    se.raddo.raddose3D.CoefCalcRaddose
        .setRADDOSEExecutable("/raddose/workdir/raddose");

    System.out.println("RADDOSE-3D version: " + getCurrentVersion());
    System.out.println("Database version: " + sql.getVersionNumber());

    //    GetPerformanceIndicators();
    // SELECT X1, X2, AVG(realtime), AVG(usertime)
    // FROM runtimeestimate
    // WHERE Version = (SELECT MAX(Version)
    // FROM runtimeestimate) AND (X1 > 0) AND (X2 > 0)
    // GROUP BY X1, X2

    // T = a * X1 + b * X2
    // => X1 = cv,  X2 = cv * ws
    // und realtime / usertime

    Vector<Raddose3DWorker> workers = new Vector<Raddose3DWorker>();
    Iterator<Raddose3DWorker> workerIterator;

    System.out.println("RADDOSE-3D Server ready.");

    while ((!shutdownInProgress) || (!workers.isEmpty())) {

      // CheckIfThreadsAreAlive
      // If any thread finished or died
      //  update timing information

      workerIterator = workers.iterator();
      System.out.print("Thread status:");

      while (workerIterator.hasNext()) {
        Raddose3DWorker w = workerIterator.next();
        System.out.print(" #" + w.getJobID() + ":" + w.getState());
        if (w.getState() == Thread.State.TERMINATED) {

          if (w.getCrashReason() != null) {
            System.out.print("  ... crashed");
            if (!w.getCrashMarker()) {
              System.out.print("  ... BUT NOT MARKED AS CRASHED IN DATABASE");
              System.err.println("Thread died without marking crash:");
              System.err.println(w.getCrashReason());
            }
          }

          System.out.println("  ... removing (CPU User time: "
              + w.getUserTime() + "ms)");
          workerIterator.remove();
        }
      }
      System.out.println();

      if (!shutdownInProgress
          && (workers.size() < maxProcesses)
          && (sql.getHighestPriority() > workers.size())) {
        Long nextJob = sql
            .getJobIDWithPriorityGreaterOrEqual(workers.size() + 1);
        if (nextJob != null) {
          for (Raddose3DWorker r : workers) {
            if (r.getJobID().equals(nextJob)) {
              nextJob = null;
            }
          }
          if (nextJob != null) {
            Raddose3DWorker newJob = new Raddose3DWorker(
                sql.getJobIDWithPriorityGreaterOrEqual(workers.size() + 1),
                sql, this);
            workers.add(newJob);
            newJob.start();
          }
        }
      }

      // Sleep for 5 seconds or interrupt
      synchronized (this) {
        try {
          this.wait(TICKWAIT);
        } catch (InterruptedException e) {
          // Ignore Exception
        }
      }
    }

    System.out.println("RADDOSE-3D Server terminated.");
  }

  /**
   * Declare constructor private.
   */
  private RaddoseServer() {
  }

  /**
   * Retrieve RADDOSE-3D version string.
   * 
   * @return
   *         Version number of underlying RADDOSE-3D build.
   */
  private String getCurrentVersion() {
    return se.raddo.raddose3D.Version.VERSION_BUILD;
  }

  /**
   * Cleanly shut down the server instance.
   */
  public void shutdown() {
    shutdownInProgress = true;
  }

  /**
   * Install a shutdown hook to intercept CTRL+C and other shutdown signals.
   */
  private void createShutdownHook() {
    // Keep final reference to this RaddoseServer instance...
    final RaddoseServer rds = this;
    final Thread mainThread = Thread.currentThread();

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        rds.shutdown(); // ...to allow variable capture and thus shutdown.
        System.out.println(String.format("Server shutting down. "
            + "This may take up to %d seconds", SHUTDOWNTIME));
        try {
          mainThread.join(SHUTDOWNTIME * SECONDSINMS);
        } catch (InterruptedException e) {
          // Do nothing.
        }
      }
    });
  }
}
