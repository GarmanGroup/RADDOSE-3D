package se.raddo.raddose3D;

/**
 * Classes implementing both this and the {@link Output) interface can subscribe
 * to {@link Experiment} and receive out-of-band notices, such as warnings and
 * citation information.
 */
public interface ExperimentNotices {
  /**
   * Passes a warning notice to output modules.
   * Output modules may interpret these differently depending on their function
   * (i.e. red warnings in an HTML output, no warnings in a CSV output, etc.)
   * 
   * @param warning
   *          Warning message
   */
  public void raiseWarning(String warning);

  /**
   * Registers a citation for the current experiment.
   * Output modules are free to aggregate citations to report them in a
   * (e.g. deduplicated) list or to ignore them completely
   * 
   * @param reference
   *          A string pointing to a relevant publication.
   */
  public void addReference(String reference);
}
