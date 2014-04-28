package se.raddo.raddose3D.tests;

import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.BeamFactory;
import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.CrystalFactory;
import se.raddo.raddose3D.Initializer;
import se.raddo.raddose3D.Wedge;
import se.raddo.raddose3D.parser.InputfileLexer;
import se.raddo.raddose3D.parser.InputfileParser;

/**
 * Class for testing parser actions on an input file.
 * This is not a unit test file, but for manually debugging the parser.
 */
public class ParserTestClass {

  private ParserTestClass() {
    String testData =
        "Crystal\n"
            + "Type Cuboid\n"
            + "DiffractionDecayModel Simple # equivalent: DDM\n"
            + "Dimensions 100 1e2 10.0e1\n"
            + "PixelsPerMicron 0.73\n"
            + "AbsCoefCalc Dummy\n"
            + "UnitCell 127.5 141.5 80\n"
            + "SolventFraction 0.5\n"
            + "Beam\n"
            + "Type ExperimentalPGM test.pgm 30 27.1\n"
            + "Flux 2e11\n"
            + "FWHM 70 20\n"
            + "Energy 12.1\n"
            + "Collimation Rectangular 55 19\n"
            + "Wedge 0 360\n"
            + "ExposureTime 150\n"
            + "AngularResolution 2.6\n"
            + "StartOffset 17.3 24.1\n"
            + "TranslatePerDegree 0.1 0 .3\n"
            + "RotAxBeamOffset 5\n";

    CharStream stream = new ANTLRStringStream(testData);
    InputfileLexer lex = new InputfileLexer(stream);
    TokenStream tokens = new CommonTokenStream(lex);
    InputfileParser parser = new InputfileParser(tokens);
    Initializer init = new InputParserTestInit();

    parser.setInitializer(init);
    parser.setCrystalFactory(new TestCrystalFactory());
    parser.setBeamFactory(new TestBeamFactory());

    try {
      parser.configfile();
    } catch (RecognitionException e) {
      System.err.println(e);
    }

    List<String> errors = parser.getErrors();
    if (errors.size() > 0) {
      StringBuffer errorString = new StringBuffer();
      errorString.append("Parser found " + errors.size()
          + " errors in input:\n");
      for (String e : errors) {
        errorString.append("\n" + e);
      }
      System.err.println(errorString);
    }
  }

  public static void main(String[] args) {
    new ParserTestClass();
  }

  private static class TestCrystalFactory extends CrystalFactory {
    public TestCrystalFactory() {
    }

    @Override
    public Crystal createCrystal(final String crystalType,
        final Map<Object, Object> properties) {
      System.out.println("Crystal creation with type: " + crystalType);
      System.out.println("Crystal properties: " + properties);
      return null;
    }
  }

  private static class TestBeamFactory extends BeamFactory {
    public TestBeamFactory() {
    }

    @Override
    public Beam createBeam(final String beamType,
        final Map<Object, Object> properties) {
      System.out.println("Beam creation with type: " + beamType);
      System.out.println("Beam properties: " + properties);
      return null;
    }
  }

  private static class InputParserTestInit implements Initializer {
    public InputParserTestInit() {
    }

    @Override
    public void exposeWedge(Wedge w) {
      System.out.println("Wedge object created: " + w);
    }

    @Override
    public void setBeam(Beam b) {
      System.out.println("Beam object created: " + b);
    }

    @Override
    public void setCrystal(Crystal c) {
      System.out.println("Crystal object created: " + c);
    }

    @Override
    public void raiseWarning(String warning) {
    }

    @Override
    public void addReference(String reference) {
    }
  }
}
