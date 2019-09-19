package se.raddo.raddose3D;

/**
 * static version information for RADDOSE-3D.
 */
public final class Version {

  /**
   * A string containing a revision number of the current source code. This line
   * ('?' followed by '---' and '?') is modified automatically during the
   * build/deploy process. In development builds the version will be reported as
   * 'head'.
   */
  private static final String REVISION       = "?---?";

  /** The major version number. */
  public static final long    VERSION_MAJOR  = 4;

  /** The minor version number. */
  public static final long    VERSION_MINOR  = 0;

  /** The true build number (depending on REVISION). */
  @SuppressWarnings("unused")
  public static final String  VERSION_BUILD  = (REVISION == "?-" + "-"
                                                 + "-?") ? "head"
                                                 : REVISION;

  /** A string built from version number plus build number. */
  public static final String  VERSION_STRING = VERSION_MAJOR + "."
                                                 + VERSION_MINOR + "."
                                                 + VERSION_BUILD;

  /**
   * Declare constructor for this class private. Class only holds static version
   * strings, and thus does not need to be instantiated.
   */
  private Version() {
  }

  /**
   * Can be called from outside to read version number. This function is used
   * during the build/deployment process.
   * 
   * @param args
   *          command line options (input ignored)
   */
  public static void main(final String[] args) {
    System.out.println(VERSION_STRING);
  }

  /** Prints version information to STDOUT. */
  public static void printVersionInformation() {
    System.out.println("Raddose 3D\nversion " + VERSION_STRING);
  }
}
