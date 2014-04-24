package se.raddo.raddose3D;

/**
 * static version information for RADDOSE-3D.
 */
public final class Version {

  /**
   * Declare constructor for this class private. Class only holds static version
   * strings, and thus does not need to be instantiated.
   */
  private Version() {
  }

  /**
   * A string containing the SVN revision of the current source code. This line
   * ('?' followed by '---' and '?') gets changed automatically during the
   * build/deploy process to the SVN revision number. In development builds the
   * version will be reported as 'head'.
   */
  private static final String SVN_REVISION = "?---?";

  /**
   * Can be called from outside to read version number. This function is used
   * during the build/deployment process.
   * 
   * @param args
   *          command line options (input ignored)
   */
  public static void main(final String[] args) {
    System.out.println(VERSION);
  }

  /** The major version number. */
  public static final long   VERSION_MAJOR = 1;

  /** The minor version number. */
  public static final long   VERSION_MINOR = 1;

  /** The build number (the SVN revision the build is based upon). */
  @SuppressWarnings("unused")
  public static final String VERSION_BUILD = (SVN_REVISION == "?-" + "-"
                                               + "-?") ? "head"
                                               : SVN_REVISION;

  /** A string built from version number plus build number. */
  public static final String VERSION       = VERSION_MAJOR + "."
                                               + VERSION_MINOR + "."
                                               + VERSION_BUILD;

  /** prints version information to STDOUT. */
  public static final void printVersionInformation() {
    System.out.println("Raddose 3D\nversion " + VERSION);
  }
}
