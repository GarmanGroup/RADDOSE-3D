package se.raddo.raddose3D;

/**
 * static version information for RADDOSE-3D.
 */
public final class Version {

  /**
   * A string containing a revision number of the current source code, being
   * the repository commit count at the time of release.
   * <p>
   * Stamped by the {@code versionize} Ant target, which replaces the
   * placeholder below during a build and puts it back afterwards, so the
   * working tree is not left modified. A build that is interrupted between
   * those two steps leaves a literal number here; restoring the placeholder
   * is the fix.
   * <p>
   * The placeholder had been replaced by a literal at some point, leaving
   * nothing for the substitution to match, and the target used GNU
   * {@code sed -i}, which fails on BSD and macOS. Running with
   * {@code failonerror="false"}, it reported nothing and did nothing for
   * years; the number was maintained by hand and went stale. The
   * substitution is now a pure Ant {@code replaceregexp}.
   */
  private static final String REVISION       = "?---?";

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
