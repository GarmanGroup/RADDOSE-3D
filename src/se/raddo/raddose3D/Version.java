package se.raddo.raddose3D;

/**
 * static version information for RADDOSE-3D.
 */
public final class Version {

  /**
   * A string containing a revision number of the current source code, being
   * the repository commit count at the time of release.
   * <p>
   * This was once stamped automatically by the {@code versionize} Ant target,
   * which substituted a '?---?' placeholder here. That has not worked for
   * years: the placeholder was replaced by a literal value at some point, so
   * there is nothing left for the substitution to match, and the target also
   * relies on GNU {@code sed -i}, which fails on BSD/macOS. The target runs
   * with {@code failonerror="false"} and so has been silently doing nothing.
   * <p>
   * Until that is repaired, bump this by hand as part of a release. It is
   * necessarily approximate, since the commit that sets it changes the count.
   */
  private static final String REVISION       = "1090";

  /** The major version number. */
  public static final long    VERSION_MAJOR  = 5;

  /** The minor version number. */
  public static final long    VERSION_MINOR  = 0;

  /** The true build number (depending on REVISION). */
  @SuppressWarnings("unused")
  public static final String  VERSION_BUILD  = ("?-" + "-" + "-?")
                                                 .equals(REVISION) ? "head"
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
