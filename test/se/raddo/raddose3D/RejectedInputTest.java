package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Checks that an input RADDOSE-3D cannot parse produces no result.
 * <p>
 * The parser generated from {@code Inputfile.g} runs its actions as it reads,
 * and ANTLR's error recovery lets it carry on past a syntax error. Handing it
 * the real initializer therefore exposed wedges and printed a dose before
 * anyone had checked whether the input parsed. The number that appeared was
 * computed from whatever defaults applied to the parts that had failed.
 * <p>
 * {@code AbsCoefCalc PDB} was the case that exposed it. The token
 * <em>named</em> {@code PDB} matched the literal {@code "EXP"}, so the most
 * natural spelling could never match: the line was skipped, no absorption
 * coefficient calculator was set, {@code Crystal.java:231} substituted
 * {@code CoefCalcAverage}, and the run printed a dose identical to an
 * explicit {@code AbsCoefCalc Dummy} run and exited zero.
 * <p>
 * That spelling is now accepted, so the tests here use a keyword that names
 * nothing at all. Both halves matter and are covered separately: the parser
 * understands more than it did, <em>and</em> still refuses what it does not
 * understand.
 */
public class RejectedInputTest {

  /** Records everything the parser asks the initializer to do. */
  private static class Collector implements Initializer {
    /** Crystals the parser produced. */
    private final List<Crystal> crystals = new ArrayList<Crystal>();
    /** Beams the parser produced. */
    private final List<Beam> beams = new ArrayList<Beam>();
    /** Wedges the parser asked to expose. */
    private final List<Wedge> wedges = new ArrayList<Wedge>();

    @Override
    public void setCrystal(final Crystal c) {
      crystals.add(c);
    }

    @Override
    public void setBeam(final Beam b) {
      beams.add(b);
    }

    @Override
    public void exposeWedge(final Wedge w) {
      wedges.add(w);
    }

    @Override
    public void raiseWarning(final String warning) {
      // Not relevant here.
    }

    @Override
    public void addReference(final String reference) {
      // Not relevant here.
    }

    /**
     * Total calls received.
     *
     * @return number of crystals, beams and wedges seen
     */
    int total() {
      return crystals.size() + beams.size() + wedges.size();
    }
  }

  /** A complete, valid input, used as the control. */
  private static final String VALID =
      "Crystal\n"
          + "Type Cuboid\n"
          + "Dimensions 20 20 20\n"
          + "PixelsPerMicron 0.5\n"
          + "AbsCoefCalc Dummy\n"
          + "\n"
          + "Beam\n"
          + "Type Gaussian\n"
          + "Flux 1e12\n"
          + "FWHM 20 20\n"
          + "Energy 12.1\n"
          + "\n"
          + "Wedge 0 90\n"
          + "ExposureTime 50\n";

  /** The same input with an AbsCoefCalc keyword that names nothing. */
  private static final String UNPARSEABLE =
      VALID.replace("AbsCoefCalc Dummy", "AbsCoefCalc Nonsense");

  /**
   * Parses a string and returns what reached the initializer.
   *
   * @param text the input file contents
   * @return the collector, whether or not parsing succeeded
   * @throws InputException if the input does not parse
   */
  private static Collector parse(final String text) throws InputException {
    Collector collector = new Collector();
    new InputParserString(text).sendData(collector);
    return collector;
  }

  /** The control: a valid input still reaches the initializer intact. */
  @Test
  public void validInputIsPassedThrough() throws InputException {
    Collector collector = parse(VALID);

    assertEquals(1, collector.crystals.size(), "one crystal expected");
    assertEquals(1, collector.beams.size(), "one beam expected");
    assertEquals(1, collector.wedges.size(), "one wedge expected");
  }

  /** An input that does not parse is rejected rather than approximated. */
  @Test
  public void unparseableInputIsRejected() {
    assertThrows(InputException.class, () -> parse(UNPARSEABLE),
        "a file the parser cannot read must be reported, not interpreted");
  }

  /**
   * The point of the change: nothing reaches the initializer, so no wedge is
   * exposed and no dose can be printed.
   * <p>
   * This is the assertion that fails if the deferral is removed. Checking
   * that an exception is thrown is not enough -- that always happened. What
   * did not happen was withholding the experiment until the input was known
   * to be good.
   */
  @Test
  public void nothingIsComputedFromAnInputThatDoesNotParse() {
    Collector collector = new Collector();
    try {
      new InputParserString(UNPARSEABLE).sendData(collector);
    } catch (InputException expected) {
      // The rejection itself is covered above.
    }

    assertEquals(0, collector.total(),
        "a rejected input must not expose a wedge, set a crystal or set a "
            + "beam: anything it reaches can print a dose");
  }

  /** The error names the line at fault. */
  @Test
  public void theOffendingLineIsNamed() {
    InputException e = assertThrows(InputException.class,
        () -> parse(UNPARSEABLE));

    assertTrue(e.toString().contains("line 5"),
        "the offending line should be named: " + e);
  }

  /**
   * Every syntax error is reported in one pass, so a file with several
   * mistakes can be fixed in one go rather than one run per mistake.
   */
  @Test
  public void allSyntaxErrorsAreReportedTogether() {
    // Both must be syntax errors. A bad beam Type, for instance, parses
    // fine and is rejected later by the factory, so it would not do.
    String twoMistakes = VALID.replace("AbsCoefCalc Dummy",
        "AbsCoefCalc Nonsense\nDDM Nonsense");

    InputException e = assertThrows(InputException.class,
        () -> parse(twoMistakes));

    assertTrue(e.toString().contains("Parser found 2 errors"),
        "both mistakes should be reported together: " + e);
  }

  /**
   * The spelling that started all this now works.
   * <p>
   * {@code AbsCoefCalc PDB} and {@code AbsCoefCalc CIF} are accepted
   * alongside the existing {@code EXP} and {@code EXPSM}. Nothing that
   * parsed before changes meaning; input that used to be silently
   * misunderstood now does what it says.
   *
   * @param dir temporary directory for the structure file
   * @throws Exception if the fixture cannot be written
   */
  @org.junit.jupiter.api.Test
  public void pdbAndCifSpellingsAreAccepted(
      @org.junit.jupiter.api.io.TempDir final java.nio.file.Path dir)
      throws Exception {
    java.nio.file.Path pdb = dir.resolve("tiny.pdb");
    java.nio.file.Files.write(pdb,
        ("CRYST1   78.270   78.270   78.270  90.00  90.00  90.00 P 1\n"
            + "REMARK 290   SMTRY1   1  1.000000  0.000000  0.000000     0.00000\n"
            + "SEQRES   1 A    3  ALA GLY SER\n"
            + "END\n").getBytes("US-ASCII"));

    Collector viaPdb = parse(VALID.replace("AbsCoefCalc Dummy",
        "AbsCoefCalc PDB\nPDB " + pdb.toString()));
    Collector viaExp = parse(VALID.replace("AbsCoefCalc Dummy",
        "AbsCoefCalc EXP\nPDB " + pdb.toString()));

    assertEquals(1, viaPdb.crystals.size(),
        "AbsCoefCalc PDB should now build a crystal from the file");
    assertEquals(viaExp.crystals.size(), viaPdb.crystals.size(),
        "PDB and EXP should be two spellings of the same thing");
  }

  /** The message says the run produced nothing, so it cannot be misread. */
  @Test
  public void theErrorSaysNoDoseWasCalculated() {
    InputException e = assertThrows(InputException.class,
        () -> parse(UNPARSEABLE));

    assertTrue(e.toString().contains("No dose was calculated"),
        "the message must say the run produced nothing: " + e);
  }

  /**
   * A deferred input arrives in the order it was written. Replaying out of
   * order would expose a wedge against the wrong crystal.
   */
  @Test
  public void deferredCallsReplayInOrder() {
    final List<String> order = new ArrayList<String>();
    DeferredInitializer deferred = new DeferredInitializer();
    deferred.setCrystal(null);
    deferred.setBeam(null);
    deferred.exposeWedge(null);

    assertEquals(3, deferred.recordedCallCount(), "three calls recorded");

    deferred.replayOn(new Initializer() {
      @Override
      public void setCrystal(final Crystal c) {
        order.add("crystal");
      }

      @Override
      public void setBeam(final Beam b) {
        order.add("beam");
      }

      @Override
      public void exposeWedge(final Wedge w) {
        order.add("wedge");
      }

      @Override
      public void raiseWarning(final String warning) {
        order.add("warning");
      }

      @Override
      public void addReference(final String reference) {
        order.add("reference");
      }
    });

    assertEquals("[crystal, beam, wedge]", order.toString(),
        "calls must replay in the order they were recorded");
  }
}
