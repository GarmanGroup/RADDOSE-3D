package se.raddo.raddose3D;

import se.raddo.raddose3D.parser.InputfileLexer;
import se.raddo.raddose3D.parser.InputfileParser;

import java.util.List;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

/**
 * A Parser that reads characters from a ANTLR CharStream. Objects are created
 * via the Initializer interface.
 * The character stream is passed to the lexer and parser for raddose syntax
 * (package raddoseParser) along with an initializer and Crystal/Beam-factories.
 * These can be overridden for testing. This class encapsulates all ANTLR
 * interaction from the rest of the project.
 */
public class InputParser implements Input {
  /** The ANTLR CommonTokenStream that the InputParser works on. */
  private final CommonTokenStream tokens;

  /** A class to create Crystal type objects. */
  private CrystalFactory          cf         = new CrystalFactory();
  /** A class to create Beam type objects. */
  private BeamFactory             bf         = new BeamFactory();

  /**
   * System-specific line ending characters.
   */
  protected static final String   LINEENDING = String.format("%n").intern();

  /**
   * create a new InputParser that can process an ANTLR CharStream.
   * Actual parsing only starts once the sendData-method is called.
   * 
   * @param stream
   *          ANTLR CharStream object
   */
  public InputParser(final CharStream stream) {
    InputfileLexer lex = new InputfileLexer(stream);
    tokens = new CommonTokenStream(lex);
  }

  /**
   * Override the default CrystalFactory class. A CrystalFactory is an
   * object that creates Crystal objects. This usually only needs overriding
   * for unit tests.
   * 
   * @param crystFact
   *          CrystalFactory that should be used.
   */
  public void setCrystalFactory(final CrystalFactory crystFact) {
    cf = crystFact;
  }

  /**
   * Override the default BeamFactory class. A BeamFactory is an
   * object that creates Beam objects. This usually only needs overriding
   * for unit tests.
   * 
   * @param beamFact
   *          BeamFactory that should be used.
   */
  public void setBeamFactory(final BeamFactory beamFact) {
    bf = beamFact;
  }

  @Override
  public void sendData(final Initializer i) throws InputException {
    InputfileParser parser = new InputfileParser(tokens);
    parser.setCrystalFactory(cf);
    parser.setBeamFactory(bf);

    /*
     * The parser runs its actions as it reads, and ANTLR's error recovery
     * lets it carry on past a syntax error. Handing it the real initializer
     * therefore exposed wedges and printed a dose before anyone had checked
     * whether the input parsed -- so a file with one mistyped keyword still
     * produced a plausible-looking number, computed from the defaults that
     * applied to the parts that had failed.
     *
     * Recording the calls and replaying them only after a clean parse keeps
     * reading the input separate from running the experiment, while still
     * collecting every syntax error rather than stopping at the first.
     */
    DeferredInitializer deferred = new DeferredInitializer();
    parser.setInitializer(deferred);

    try {
      parser.configfile();
    } catch (RecognitionException e) {
      throw new InputException(e.toString()); // NOPMD - discard stack trace
    }

    List<String> errors = parser.getErrors();
    if (!errors.isEmpty()) {
      StringBuffer errorString = new StringBuffer("Parser found "
          + errors.size() + " errors in input:\n");
      for (String e : errors) {
        errorString.append(LINEENDING).append(e);
      }
      errorString.append(LINEENDING).append(LINEENDING)
          .append("No dose was calculated. Fix the input and run again.");
      throw new InputException(errorString.toString());
    }

    deferred.replayOn(i);
  }
}
