package se.raddo.raddose3D.server;

import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Vector;

import se.raddo.raddose3D.Experiment;
import se.raddo.raddose3D.InputException;
import se.raddo.raddose3D.InputParserString;
import se.raddo.raddose3D.OutputFactory;
import se.raddo.raddose3D.OutputProgressEstimate;
import se.raddo.raddose3D.Writer;
import se.raddo.raddose3D.WriterString;

/**
 * Runs a RADDOSE 3D job in a separate thread. This will allow parallel
 * execution of independent jobs, isolate any errors and provide job statistics.
 */
public class Raddose3DWorker
    extends Thread
    implements UncaughtExceptionHandler {
  /** Connection object to the database. */
  private final DatabaseConnector db;

  /** Management object which allows reading out used CPU time. */
  private final ThreadMXBean      managementBean;

  /** Object that should be notified when the thread finishes. */
  private final Object            exitNotification;

  /** If the thread crashes the reason is stored here. */
  private Throwable               crashRecord                = null;
  /** If the thread crash is recorded properly this is set to true. */
  private Boolean                 markedAsCrashed            = false;

  /** The database ID of the job assigned to this thread. */
  private final Long              jobID;

  /** The user time (actual CPU time, not: real time) spent in this thread. */
  private Long                    threadUserTime             = 0L;
  /** Conversion factor for recorded time to useful output. */
  private static final Integer    NANOSECONDSPERMILLISECONDS = 1000000;
  /** Conversion factor for recorded time to useful output. */
  private static final Integer    MILLISECONDSPERSECONDS     = 1000;

  /**
   * Prepares a new Raddose3DWorker (Thread) object that can run a specified job
   * from the database. Actual processing only starts via run() method.
   * 
   * @param databaseJobID
   *          database identifier of the job to be run.
   * @param database
   *          database connection object.
   * @param exitNotificationObject
   *          any object, on which Notify() will be called when the thread
   *          finishes.
   */
  public Raddose3DWorker(final Long databaseJobID,
      final DatabaseConnector database, final Object exitNotificationObject) {
    jobID = databaseJobID;
    exitNotification = exitNotificationObject;
    db = database;
    managementBean = ManagementFactory.getThreadMXBean();

    // Calculation threads are less important than the main loop.
    setPriority(NORM_PRIORITY - 1);

    // Object can handle crashes itself.
    setUncaughtExceptionHandler(this);

    System.out.println("Acquiring lock");
    if (!db.lockQueueJob(jobID)) {
      System.err.println("Could not acquire lock!");
      throw new RuntimeException("Could not acquire lock!");
    }

  }

  @Override
  public void run() {
    long startTime = System.currentTimeMillis();

    System.out.println("Worker " + jobID + " started.");

    String jobData = db.getJob(jobID);
    System.out.println("Job commands: " + jobData);

    final Experiment experiment = new Experiment();

    // add ExperimentYielder to facilitate parallel processing
    experiment.addObserver(new ExperimentYielder());

    // authoritative timing information via OutputProgressEstimate
    HashMap<Object, Object> properties = new HashMap<Object, Object>();
    final OutputProgressEstimate progressEstimate =
        new OutputProgressEstimate(properties);
    experiment.addObserver(progressEstimate);

    // attach default output modules
    Writer summaryCSV =
        new WriterSQL(db, jobID, "Summary.csv",
            DatabaseConnector.OutputType.TEXT);
    Writer summaryTXT = new WriterSQL(db, jobID, "Summary.txt",
        DatabaseConnector.OutputType.TEXT);
    Writer doseStateCSV = new WriterSQL(db, jobID, "DoseState.csv",
        DatabaseConnector.OutputType.TEXT);
    Writer doseStateR = new WriterSQL(db, jobID, "DoseState.r",
        DatabaseConnector.OutputType.TEXT);
    WriterString doseStateRPreview = new WriterString();

    OutputFactory of = new OutputFactory();
    experiment.addObserver(of.createOutputSimple("SummaryCSV", summaryCSV));
    experiment.addObserver(of.createOutputSimple("SummaryText", summaryTXT));
    experiment.addObserver(of.createOutputSimple("FinalDoseStateCSV",
        doseStateCSV));
    experiment.addObserver(of.createOutputSimple("FinalDoseStateR",
        doseStateR));
    experiment.addObserver(of.createOutputSimple("FinalDoseStateRPreview",
        doseStateRPreview));

    // experiment.addObserver(of.createOutputSimple("FluencePerDoseHistCSV",
    // new WriterSQL(db, jobID, "FluencePerDoseHist.csv")));

    try {
      experiment.process(new InputParserString(jobData));
    } catch (InputException e) {
      throw new RuntimeException("Input malformed", e);
    }

    experiment.close();

    // Prepare R job
    Writer doseStateRPreviewDB = new WriterSQL(db, jobID, "imagegenerator",
        DatabaseConnector.OutputType.R);
    doseStateRPreviewDB.write(doseStateRPreview.getDataStringBuffer());

    final int fullCircle = 360;
    final int numberOfImages = 6;
    for (int imageNo = 0; imageNo <= numberOfImages - 1; imageNo++) {
      String fileName = String.format("FinalDoseState%d", imageNo);

      doseStateRPreviewDB.write("\n"
          + String.format("graph.out('%s',\n",
              fileName)
          + " function() {\n"
          + String.format("  render(%d)\n",
              (int) (imageNo * fullCircle / numberOfImages))
          + "})\n");
    }
    doseStateRPreviewDB.close();

    db.finalizeJob(jobID);
    System.out.println("Worker " + jobID + ": Done.");

    // Update performance metrics.
    Long x1 = 0L;
    Long x2 = 0L;

    Vector<Long> cvs = progressEstimate.getCrystalVoxelList();
    Vector<Long> wss = progressEstimate.getWedgeSliceList();

    if (cvs.size() != wss.size()) {
      throw new RuntimeException("Progress Estimate information malformed.");
    }

    for (int i = 0; i < cvs.size(); i++) {
      x1 += cvs.get(i);
      x2 += cvs.get(i) * wss.get(i);
    }

    long realTime = System.currentTimeMillis() - startTime;

    updateTimingInformation();

    db.saveTimingData(jobID, x1, x2,
        new Double(realTime) / MILLISECONDSPERSECONDS,
        new Double(getUserTime()) / MILLISECONDSPERSECONDS);

    if (exitNotification != null) {
      synchronized (exitNotification) {
        exitNotification.notifyAll();
      }
    }
  }

  /**
   * Get user time in milliseconds.
   * 
   * @return
   *         user time of this thread object.
   */
  public Long getUserTime() {
    return threadUserTime / NANOSECONDSPERMILLISECONDS;
  }

  /**
   * Get database ID of the job assigned to this thread.
   * 
   * @return
   *         ID of the assigned job.
   */
  public Long getJobID() {
    return jobID;
  }

  /**
   * Update the internally stored user time. This is not the actual wall clock
   * running time of the thread, but the real CPU time used by the currently
   * running thread.
   */
  private void updateTimingInformation() {
    if (managementBean.isCurrentThreadCpuTimeSupported()) {
      threadUserTime = managementBean.getCurrentThreadUserTime();
    } else {
      threadUserTime = 0L;
    }
  }

  @Override
  public void uncaughtException(final Thread crashThread,
      final Throwable crashCause) {
    crashRecord = crashCause;

    updateTimingInformation();

    markedAsCrashed = db.markThreadAsCrashed(jobID, "Thread died after "
        + getUserTime() + " ms.\n\n", crashCause);
    if (markedAsCrashed) {
      db.finalizeJob(jobID);
    }
  }

  /**
   * Returns the Exception or Error that ended the thread execution, or null if
   * the thread completed successfully.
   * 
   * @return
   *         reason for thread crash or null if thread did not crash.
   */
  public Throwable getCrashReason() {
    return crashRecord;
  }

  /**
   * If the thread crashed: Was the crash recorded in the database?
   * 
   * @return
   *         True if the thread crashed and the crash was successfully written
   *         to the database.
   */
  public Boolean getCrashMarker() {
    return markedAsCrashed;
  }
}
