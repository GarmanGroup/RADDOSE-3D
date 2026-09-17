package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


/**
 * Test cases for InputParser and package raddoseParser
 */

public class InputParserTest {

  @Test
  @Timeout(2)
  public void InputParserShouldFailOnInvalidFile() throws Exception {
    InputParser parser = new InputParserString("Crystals\nare\nfun\n");
    Initializer i = new InputParserTestInit();

    assertNotNull(parser);

    assertThrows(InputException.class, () -> parser.sendData(i));
  }

  @Test
  @Timeout(2)
  public void InputParserReadEmptyFile() throws Exception {
    InputParser parser = new InputParserString("");
    InputParserTestInit init = new InputParserTestInit();

    assertNotNull(parser);

    parser.sendData(init);

    assertEquals(0, init.crystals.size());
    assertEquals(0, init.beams.size());
    assertEquals(0, init.wedges.size());
  }

  private String sampleFile_Simple() {
    return "Crystal\n" + "Type Cuboid\n"
        + "DiffractionDecayModel Simple # equivalent: DDM\n"
        + "Dimensions 100 1e2 10.0e1\n" + "PixelsPerMicron 0.73\n"
        + "AbsCoefCalc Dummy\n"
        + "UnitCell 127.5 141.5 80\n" + "SolventFraction 0.5\n"

        + "Beam\n"
        + "Type Gaussian\n" + "Flux 2e11\n" + "FWHM 70 20\n" + "Energy 12.1\n"
        + "Collimation Rectangular 55 19\n"

        + "Wedge 0 360\n"
        + "ExposureTime 150\n" + "AngularResolution 2.6\n"
        + "StartOffset 17.3 24.1\n" + "TranslatePerDegree 0.1 0 .3\n"
        + "RotAxBeamOffset 5\n";
  }

  @Test
  @Timeout(3)
  public void InputParserReadValidSimpleFile() throws Exception {
    InputParser parser = new InputParserString(sampleFile_Simple());
    InputParserTestInit init = new InputParserTestInit();
    TestCrystalFactory testCF = new TestCrystalFactory();
    TestBeamFactory testBF = new TestBeamFactory();
    parser.setCrystalFactory(testCF);
    parser.setBeamFactory(testBF);

    parser.sendData(init);

    assertEquals(1, init.crystals.size());
    assertEquals(1, init.beams.size());
    assertEquals(1, init.wedges.size());

    assertTrue(
            init.crystals.get(0) instanceof CrystalDummy,
            "Crystal not stored");
    assertEquals(1, testCF.createEvents, "Seen more than one create event");
    assertEquals("Cuboid", testCF.lastSeenType,
        "Crystal type set incorrectly (" + testCF.lastSeenType + ")");

    Map<Object, Object> properties;
    Iterator<Entry<Object, Object>> iter;

    properties = testCF.getSeenProperties();
    iter = properties.entrySet().iterator();
    while (iter.hasNext()) {
      Entry<Object, Object> me = iter.next();

      Boolean match = false;
      if (me.getKey().equals(Crystal.CRYSTAL_DIM_X)) {
        Tolerance.equals((Double) me.getValue(), 100, "Crystal size X");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_DIM_Y)) {
        Tolerance.equals((Double) me.getValue(), 100, "Crystal size Y");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_DIM_Z)) {
        Tolerance.equals((Double) me.getValue(), 100, "Crystal size Z");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_RESOLUTION)) {
        Tolerance.equals((Double) me.getValue(), 0.73,
            "Crystal resolution");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_COEFCALC)) {
        assertTrue(me.getValue() instanceof CoefCalcAverage,
            "CoefCalc not initialized with CoefCalcDummy");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_DDM)) {
        assertTrue(me.getValue() instanceof DDMSimple,
            "DDM is of wrong type");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_CONTAINER)) {
        assertNull(me.getValue(),
            "Crystal container is of wrong type");
        match = true;
      }
      if (!match) {
        fail("Unexpected crystal property " + me.getKey() + " set. ("
            + me.getValue() + ")");
      }
    }

    assertTrue(
        init.beams.get(0) instanceof BeamDummy,
        "Beam not stored");
    assertEquals(1, testBF.createEvents,
        "Seen more than one beam create event");
    Tolerance.equals(testBF.lastSeenType, "Gaussian", "Beam type");

    properties = testBF.getSeenProperties();
    iter = properties.entrySet().iterator();
    while (iter.hasNext()) {
      Boolean match = false;

      Entry<Object, Object> me = iter.next();
      if (me.getKey().equals(Beam.BEAM_FLUX)) {
        Tolerance.equals((Double) me.getValue(), 200000000000d,
            "Beam flux");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_FWHM_X)) {
        Tolerance.equals((Double) me.getValue(), 70, "Beam x FWHM");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_FWHM_Y)) {
        Tolerance.equals((Double) me.getValue(), 20, "Beam y FWHM");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_ENERGY)) {
        Tolerance.equals((Double) me.getValue(), 12.1, "Beam energy");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_COLL_H)) {
        Tolerance.equals((Double) me.getValue(), 55,
            "Beam horizontal collimation");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_COLL_V)) {
        Tolerance.equals((Double) me.getValue(), 19,
            "Beam vertical collimation");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_CONTAINER)) {
        assertNull(me.getValue(),
            "Crystal container is of wrong type");
        match = true;
      }
      if (!match) {
        fail("Unexpected beam property " + me.getKey() + " set. ("
            + me.getValue() + ")");
      }
    }

    Wedge w = init.wedges.get(0);
    assertNotNull(w, "Wedge not stored");
    Tolerance.equals(w.getAngRes(), Math.toRadians(2.6),
        "Wedge angular resolution");
    Tolerance.equals(w.getStartAng(), Math.toRadians(0), "Wedge start angle");
    Tolerance.equals(w.getEndAng(), Math.toRadians(360), "Wedge end angle");
    Tolerance.equals(w.getTotSec(), 150, "Wedge total exposure time");
    Tolerance.equals(w.getStartX(), 17.3, "Wedge X start offset");
    Tolerance.equals(w.getStartY(), 24.1, "Wedge Y start offset");
    Tolerance.equals(w.getStartZ(), 0d, "Wedge Z start offset");
    Tolerance.equals(w.getTransX(), 1 / Math.toRadians(1 / 0.1),
        "Wedge X translation");
    Tolerance.equals(w.getTransY(), 0.0, "Wedge Y translation");
    Tolerance.equals(w.getTransZ(), 1 / Math.toRadians(1 / 0.3),
        "Wedge Z translation");
    Tolerance.equals(w.getOffAxisUm(), 5, "Wedge rotational axis offset");
  }

  private String sampleFile_Complex_1() {
    return "Crystal\n"
        + "Type Cuboid\n"
        + "DiffractionDecayModel Simple # equivalent: DDM\n"
        + "Dimensions 75 0.7e2 6.0e1\n"
        + "PixelsPerMicron 0.273\n"
        + "AbsCoefCalc Dummy\n"
        + "// comment because we like java\n"
        + "! comment for historic raddose-reasons\n"
        + "AngleP 1.\n"
        + "AngleL .1\n"
        + "\n"
        + "UnitCell 127.5 141.5 80 60 60 60\n"
        + "NumMonomers 5\n"
        + "NumResidues 80\n"
        + "NumRNA 0\n"
        + "NumDNA 0\n"
        + "ProteinHeavyAtoms S 20 Se 50\n"
        + "SolventHeavyConc Na 1000 Cl 1200\n"
        + "SolventFraction 0.5\n"
        + "\n"
        + "Beam\n"
        + "Type Gaussian\n"
        + "Flux 2e11 # photons per second\n"
        + "FWHM 70 20 # micrometers\n"
        + "Energy 12.1 keV\n"
        + "Collimation Horizontal 41\n"
        + "#Collimation Circular 50  # this stays undocumented\n"
        + "#Collimation Horizontal 70 # and people should only use one collimation at a time\n"
        + "#Collimation Vertical 20\n" + "\n" + "Wedge 0 360\n"
        + "ExposureTime 150\n" + "AngularResolution 2.6\n"
        + "StartOffset 17.3 24.1 0.2\n" + "TranslatePerDegree 0.1 0 0.21\n"
        + "RotAxBeamOffset 5\n";
  }

  @Test
  @Timeout(3)
  public void InputParserReadValidComplexFile1() throws Exception {
    InputParser parser = new InputParserString(sampleFile_Complex_1());
    InputParserTestInit init = new InputParserTestInit();
    TestCrystalFactory testCF = new TestCrystalFactory();
    TestBeamFactory testBF = new TestBeamFactory();
    parser.setCrystalFactory(testCF);
    parser.setBeamFactory(testBF);

    parser.sendData(init);
    
    assertEquals(1, init.crystals.size());
    assertEquals(1, init.beams.size());
    assertEquals(1, init.wedges.size());

    assertTrue(
            init.crystals.get(0) instanceof CrystalDummy,
            "Crystal not stored");
    assertEquals(1, testCF.createEvents,
        "Seen more than one crystal create event");
    assertEquals("Cuboid", testCF.lastSeenType, "Crystal type");

    Map<Object, Object> properties;
    Iterator<Map.Entry<Object, Object>> iter;

    properties = testCF.getSeenProperties();
    iter = properties.entrySet().iterator();
    while (iter.hasNext()) {
      Boolean match = false;
      Map.Entry<Object, Object> me = iter.next();
      if (me.getKey().equals(Crystal.CRYSTAL_DIM_X)) {
        Tolerance.equals((Double) me.getValue(), 75, "Crystal size X");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_DIM_Y)) {
        Tolerance.equals((Double) me.getValue(), 70, "Crystal size Y");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_DIM_Z)) {
        Tolerance.equals((Double) me.getValue(), 60, "Crystal size Z");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_RESOLUTION)) {
        Tolerance.equals((Double) me.getValue(), 0.273,
            "Crystal resolution");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_ANGLE_P)) {
        Tolerance.equals((Double) me.getValue(), 1, "Crystal angle P");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_ANGLE_L)) {
        Tolerance.equals((Double) me.getValue(), 0.1, "Crystal angle L");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_COEFCALC)) {
        assertTrue(me.getValue() instanceof CoefCalcAverage,
            "CoefCalc not initialized with CoefCalcDummy");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_DDM)) {
        assertTrue(me.getValue() instanceof DDMSimple,
            "DDM is of wrong type");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_CONTAINER)) {
        assertNull(me.getValue(),
            "Crystal container is of wrong type");
        match = true;
      }
      if (!match) {
        fail("Unexpected crystal property " + me.getKey() + " set. ("
            + me.getValue() + ")");
      }
    }

    assertTrue(
        init.beams.get(0) instanceof BeamDummy,
        "Beam not stored");
    assertEquals(1, testBF.createEvents,
        "Seen more than one beam create event");
    assertEquals("Gaussian", testBF.lastSeenType,
        "Beam type set incorrectly (" + testBF.lastSeenType + ")");

    properties = testBF.getSeenProperties();
    iter = properties.entrySet().iterator();
    while (iter.hasNext()) {
      Boolean match = false;
      Map.Entry<Object, Object> me = iter.next();
      if (me.getKey().equals(Beam.BEAM_FLUX)) {
        Tolerance.equals((Double) me.getValue(), 200000000000d,
            "Beam flux");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_FWHM_X)) {
        Tolerance.equals((Double) me.getValue(), 70, "Beam x FWHM");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_FWHM_Y)) {
        Tolerance.equals((Double) me.getValue(), 20, "Beam y FWHM");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_ENERGY)) {
        Tolerance.equals((Double) me.getValue(), 12.1, "Beam energy");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_COLL_H)) {
        Tolerance.equals((Double) me.getValue(), 41,
            "Beam horizontal collimation");
        match = true;
      }
      if (!match) {
        fail("Unexpected beam property " + me.getKey() + " set. ("
            + me.getValue() + ")");
      }
    }

    Wedge w = init.wedges.get(0);
    assertNotNull(w, "Wedge not stored");

    Tolerance.equals(w.getAngRes(), Math.toRadians(2.6d),
        "Wedge angular resolution");
    Tolerance.equals(w.getStartAng(), Math.toRadians(0d), "Wedge start angle");
    Tolerance.equals(w.getEndAng(), Math.toRadians(360d), "Wedge end angle");
    Tolerance.equals(w.getTotSec(), 150d, "Wedge total exposure time");
    Tolerance.equals(w.getStartX(), 17.3d, "Wedge X start offset");
    Tolerance.equals(w.getStartY(), 24.1d, "Wedge Y start offset");
    Tolerance.equals(w.getStartZ(), 0.2d, "Wedge Z start offset");
    Tolerance.equals(w.getTransX(), 1 / Math.toRadians(1 / 0.1d),
        "Wedge X translation");
    Tolerance.equals(w.getTransY(), 0.0d, "Wedge Y translation");
    Tolerance.equals(w.getTransZ(), 1 / Math.toRadians(1 / 0.21d),
        "Wedge Z translation");
    Tolerance.equals(w.getOffAxisUm(), 5d, "Wedge rotational axis offset");
  }

  @Test
  @Timeout(3)
  public void InputParserReadValidComplexFile2() throws Exception {
    InputParser parser = new InputParserString(sampleFile_Complex_2());
    InputParserTestInit init = new InputParserTestInit();
    TestCrystalFactory testCF = new TestCrystalFactory();
    TestBeamFactory testBF = new TestBeamFactory();
    parser.setCrystalFactory(testCF);
    parser.setBeamFactory(testBF);

    parser.sendData(init);

    assertEquals(1, init.crystals.size());
    assertEquals(1, init.beams.size());
    assertEquals(1, init.wedges.size());

    assertTrue(
            init.crystals.get(0) instanceof CrystalDummy,
            "Crystal not stored");
    assertEquals(1, testCF.createEvents,
        "Seen more than one create event");
    assertEquals("SpHeRiCaL", testCF.lastSeenType,
        "Crystal type set incorrectly (" + testCF.lastSeenType + ")");
    Map<Object, Object> properties;

    properties = testCF.getSeenProperties();
    Iterator<Map.Entry<Object, Object>> entries =
        properties.entrySet().iterator();
    while (entries.hasNext()) {
      Boolean match = false;
      Map.Entry<Object, Object> me = entries.next();
      if (me.getKey().equals(Crystal.CRYSTAL_DIM_X)) {
        Tolerance.equals((Double) me.getValue(), 42, "Crystal size X");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_RESOLUTION)) {
        Tolerance
            .equals((Double) me.getValue(), 0.4, "Crystal resolution");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_COEFCALC)) {
        assertTrue(me.getValue() instanceof CoefCalcAverage,
            "CoefCalc not initialized with CoefCalcDummy");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_DDM)) {
        assertTrue(me.getValue() instanceof DDMSimple,
            "DDM is of wrong type");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_CONTAINER)) {
        assertNull(me.getValue(),
            "Crystal container is of wrong type");
        match = true;
      }
      if (!match) {
        fail("Unexpected crystal property " + me.getKey() + " set. ("
            + me.getValue() + ")");
      }
    }

    assertTrue(
        init.beams.get(0) instanceof BeamDummy,
        "Beam not stored");
    assertEquals(1, testBF.createEvents,
        "Seen more than one beam create event");
    Tolerance.equals(testBF.lastSeenType, "Tophat", "Beam type");

    properties = testBF.getSeenProperties();
    entries = properties.entrySet().iterator();
    while (entries.hasNext()) {
      Boolean match = false;
      Map.Entry<Object, Object> me = entries.next();
      if (me.getKey().equals(Beam.BEAM_FLUX)) {
        Tolerance
            .equals((Double) me.getValue(), 10000000000d, "Beam flux");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_FWHM_X)) {
        Tolerance.equals((Double) me.getValue(), 55, "Beam x FWHM");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_FWHM_Y)) {
        Tolerance.equals((Double) me.getValue(), 20, "Beam y FWHM");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_ENERGY)) {
        Tolerance.equals((Double) me.getValue(), 5, "Beam energy");
        match = true;
      }
      if (me.getKey().equals(Beam.BEAM_COLL_V)) {
        Tolerance.equals((Double) me.getValue(), 15,
            "Beam vertical collimation");
        match = true;
      }
      if (me.getKey().equals(Crystal.CRYSTAL_CONTAINER)) {
        assertNull(me.getValue(),
            "Crystal container is of wrong type");
        match = true;
      }
      if (!match) {
        fail("Unexpected beam property " + me.getKey() + " set. ("
            + me.getValue() + ")");
      }
    }

    Wedge w = init.wedges.get(0);
    assertNotNull(w, "Wedge not stored");

    Tolerance.equals(w.getAngRes(), Math.toRadians(2.6),
        "Wedge angular resolution");
    Tolerance.equals(w.getStartAng(), Math.toRadians(0), "Wedge start angle");
    Tolerance.equals(w.getEndAng(), Math.toRadians(360), "Wedge end angle");
    Tolerance.equals(w.getTotSec(), 150, "Wedge total exposure time");
    Tolerance.equals(w.getStartX(), 17.3, "Wedge X start offset");
    Tolerance.equals(w.getStartY(), 24.1, "Wedge Y start offset");
    Tolerance.equals(w.getTransX(), 1 / Math.toRadians(1 / 0.1),
        "Wedge X translation");
    Tolerance.equals(w.getTransY(), 0.0, "Wedge Y translation");
    Tolerance.equals(w.getOffAxisUm(), 5, "Wedge rotational axis offset");
  }

  private String sampleFile_Complex_2() {
    return "Crystal\n"
        + "Type SpHeRiCaL\n"
        + "ddm Simple " // newline not necessary
        + "Dimensions 42\n"
        + "PixelsPerMicron 0.4\n"
        + "AbsCoefCalc Dummy\n"
        + "\n"
        + "UnitCell 127.5 141.5 80 60 60 60\n"
        + "NumMonomers 5\n"
        + "NumResidues 80\n"
        + "NumRNA 0\n"
        + "NumDNA 0\n"
        + "ProteinHeavyAtoms S 20 Se 50\n"
        + "SolventHeavyConc Na 1000 Cl 1200\n"
        + "SolventFraction 0.5\n"
        + "\n"
        + "Beam\n"
        + "Type Tophat\n"
        + "Flux 1e10 # photons per second\n"
        + "FWHM 55 20 # micrometers\n"
        + "Energy 5.0 keV\n"
        + "Collimation Vertical 15\n"
        + "#Collimation Circular 50  # this stays undocumented\n"
        + "#Collimation Horizontal 70 # and people should only use one collimation at a time\n"
        + "#Collimation Vertical 20\n" + "\n" + "Wedge 0 360\n"
        + "ExposureTime 150\n" + "AngularResolution 2.6\n"
        + "StartOffset 17.3 24.1\n" + "TranslatePerDegree 0.1 0\n"
        + "RotAxBeamOffset 5\n";
  }

  private static class TestCrystalFactory extends CrystalFactory {
    public int                  createEvents       = 0;
    public String               lastSeenType       = null;
    private Map<Object, Object> lastSeenProperties = null;

    @Override
    public Crystal createCrystal(final String crystalType,
        final Map<Object, Object> properties) {
      createEvents++;
      lastSeenType = crystalType;
      lastSeenProperties = properties;
      return new CrystalDummy();
    }

    public Map<Object, Object> getSeenProperties() {
      return lastSeenProperties;
    }
  }

  private static class TestBeamFactory extends BeamFactory {
    public int                  createEvents = 0;
    public String               lastSeenType;
    private Map<Object, Object> lastSeenProperties;

    @Override
    public Beam createBeam(String beamType,
        final Map<Object, Object> properties) {
      createEvents++;
      lastSeenType = beamType;
      lastSeenProperties = properties;
      return new BeamDummy();
    }

    public Map<Object, Object> getSeenProperties() {
      return lastSeenProperties;
    }
  }

  private static class InputParserTestInit implements Initializer {
    @SuppressWarnings("unused")
    public long               seenEvents = 0;
    public ArrayList<Wedge>   wedges     = new ArrayList<Wedge>();
    public ArrayList<Beam>    beams      = new ArrayList<Beam>();
    public ArrayList<Crystal> crystals   = new ArrayList<Crystal>();

    @Override
    public void exposeWedge(Wedge w) {
      seenEvents++;
      wedges.add(w);
    }

    @Override
    public void setBeam(Beam b) {
      seenEvents++;
      beams.add(b);
    }

    @Override
    public void setCrystal(Crystal c) {
      seenEvents++;
      crystals.add(c);
    }

    @Override
    public void raiseWarning(String warning) {
      // No implementation required.
    }

    @Override
    public void addReference(String reference) {
      // No implementation required.
    }
  }
}
