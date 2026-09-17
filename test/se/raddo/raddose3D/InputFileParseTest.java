package se.raddo.raddose3D;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Checks that the shipped example inputs still parse, and that the input
 * directives added since the original parser tests were written are accepted.
 * <p>
 * Commit bc40d89, "fix: correct invalid SMX example", shipped an example that
 * did not parse. Nothing tested the examples, so nothing caught it.
 * {@code InputParserTest} covers only an empty file, an invalid file and three
 * hardcoded samples, none of which use any directive added after about 2015.
 */
public class InputFileParseTest {

  /** Collects what the parser produces, and records any warnings. */
  private static class Collector implements Initializer {
    final List<Crystal> crystals = new ArrayList<Crystal>();
    final List<Beam> beams = new ArrayList<Beam>();
    final List<Wedge> wedges = new ArrayList<Wedge>();
    final List<String> warnings = new ArrayList<String>();

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
      warnings.add(warning);
    }

    @Override
    public void addReference(final String reference) {
      // Not relevant here.
    }
  }

  // ------------------------------------------------------- shipped examples

  /** Every example input the project ships must parse. */
  static Stream<Arguments> shippedExamples() {
    File dir = new File("examples");
    File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
    if (files == null || files.length == 0) {
      return Stream.empty();
    }
    List<Arguments> out = new ArrayList<Arguments>();
    for (File f : files) {
      // SMXray2 now parses (its AbsCoefCalc keyword was corrected from CIF to
      // EXPSM), but it cannot be built: it names a CIF file, Fe3O4, that the
      // project does not ship, and CoefCalcFromCIF reads from a local path
      // only -- its download code is commented out. See TEST-TRIAGE.md #8.
      if (f.getName().equals("SMXray2_example_input.txt")) {
        continue;
      }
      out.add(Arguments.of(f.getPath()));
    }
    return out.stream();
  }

  @ParameterizedTest
  @MethodSource("shippedExamples")
  @Timeout(10)
  public void shippedExamplesParse(String path) throws Exception {
    InputParser parser = new InputParserFile(path);
    Collector init = new Collector();

    assertDoesNotThrow(() -> parser.sendData(init),
        path + " does not parse");

    assertFalse(init.crystals.isEmpty(), path + " produced no crystal");
    assertFalse(init.beams.isEmpty(), path + " produced no beam");
    assertFalse(init.wedges.isEmpty(), path + " produced no wedge");
  }

  /**
   * SMXray2 is excluded from the sweep above because it references an
   * unshipped CIF file, but its grammar must still be valid -- that was the
   * actual defect (AbsCoefCalc CIF, which can never parse, since the grammar
   * token named CIF matches the literal "EXPSM").
   */
  @Test
  @Timeout(10)
  public void smxray2ExampleUsesAValidAbsCoefCalcKeyword() throws Exception {
    String text = new String(java.nio.file.Files.readAllBytes(
        new File("examples/SMXray2_example_input.txt").toPath()),
        java.nio.charset.Charset.forName("UTF-8"));

    assertFalse(text.matches("(?s).*(?i)AbsCoefCalc\\s+CIF\\s.*"),
        "AbsCoefCalc CIF can never parse; the keyword for a CIF-based "
            + "calculation is EXPSM");
    assertTrue(text.matches("(?s).*(?i)AbsCoefCalc\\s+EXPSM\\s.*"),
        "expected AbsCoefCalc EXPSM in the SMXray2 example");
  }

  /** Guards against the examples directory quietly emptying. */
  @Test
  public void thereAreExamplesToCheck() {
    assertTrue(shippedExamples().count() > 0,
        "no example .txt inputs found -- has examples/ moved?");
  }

  // ------------------------------------------------- newer input directives

  /** A minimal but complete input, to which one directive is added. */
  private static String inputWith(String crystalExtra, String beamExtra) {
    return "Crystal\n"
        + "Type Cuboid\n"
        + "Dimensions 20 20 20\n"
        + "PixelsPerMicron 0.5\n"
        + "AbsCoefCalc Dummy\n"
        + "UnitCell 78.02 78.02 78.02\n"
        + "NumMonomers 24\n"
        + "NumResidues 51\n"
        + "SolventFraction 0.64\n"
        + crystalExtra
        + "\nBeam\n"
        + "Type Gaussian\n"
        + "Flux 2e12\n"
        + "FWHM 30 30\n"
        + "Energy 12.1\n"
        + "Collimation Rectangular 60 60\n"
        + beamExtra
        + "\nWedge 0 90\n"
        + "ExposureTime 10\n"
        + "AngularResolution 10\n";
  }

  private static void parses(String input, String label) {
    InputParser parser = new InputParserString(input);
    Collector init = new Collector();
    try {
      parser.sendData(init);
    } catch (InputException e) {
      fail(label + " failed to parse: " + e.getMessage());
    }
    assertFalse(init.crystals.isEmpty(), label + " produced no crystal");
  }

  /**
   * Crystal directives added after the original parser tests. None of these
   * appear in InputParserTest's three sample files.
   */
  @ParameterizedTest
  @ValueSource(strings = {
      "CALCULATEPEESCAPE TRUE\n",
      "CALCULATEFLESCAPE TRUE\n",
      "CALCSURROUNDING TRUE\n",
      "FLRESOLUTION 8\n",
      "PERESOLUTION 4\n",
      "POLARISATIONDIRECTION 0\n",
      "GONIOMETERAXIS 0\n",
      "RUNS 1\n",
      "NUMCARB 0\n",
      "SIMELECTRONS 100\n",
      "AngleP 45\n",
      "AngleL 45\n",
      "DiffractionDecayModel Linear\n",
  })
  @Timeout(10)
  public void crystalDirectivesParse(String directive) {
    parses(inputWith(directive, ""), "crystal directive '" + directive.trim() + "'");
  }

  /** Beam directives added after the original parser tests. */
  @ParameterizedTest
  @ValueSource(strings = {
      "SEMIANGLE 0.1\n",
      "APERTURERADIUS 50\n",
      "PULSEENERGY 1.0\n",
      "ENERGYFWHM 0.1\n",
  })
  @Timeout(10)
  public void beamDirectivesParse(String directive) {
    parses(inputWith("", directive), "beam directive '" + directive.trim() + "'");
  }

  /**
   * Circular collimation, which reaches the BEAM_CIRCULAR property that
   * BeamGaussian compares by reference. This is the path that
   * BeamGaussianCircularTest exercises from the other end.
   */
  @Test
  @Timeout(10)
  public void circularCollimationParsesAndProducesACircularBeam() {
    String input = inputWith("", "").replace(
        "Collimation Rectangular 60 60\n", "Collimation Circular 60 60\n");

    InputParser parser = new InputParserString(input);
    Collector init = new Collector();
    try {
      parser.sendData(init);
    } catch (InputException e) {
      fail("circular collimation failed to parse: " + e.getMessage());
    }
    assertFalse(init.beams.isEmpty(), "circular collimation produced no beam");
    assertTrue(init.beams.get(0).getIsCircular(),
        "Collimation Circular should produce a circular beam");
  }

  /** The baseline input itself must parse, or the tests above prove nothing. */
  @Test
  @Timeout(10)
  public void theBaselineInputParses() {
    parses(inputWith("", ""), "baseline input");
  }
}
