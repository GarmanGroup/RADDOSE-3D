package se.raddo.raddose3D.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.*;

import se.raddo.raddose3D.Beam;
import se.raddo.raddose3D.BeamFactory;
import se.raddo.raddose3D.CoefCalcAverage;
import se.raddo.raddose3D.Crystal;
import se.raddo.raddose3D.CrystalFactory;
import se.raddo.raddose3D.DDMSimple;
import se.raddo.raddose3D.Initializer;
import se.raddo.raddose3D.InputException;
import se.raddo.raddose3D.InputParser;
import se.raddo.raddose3D.InputParserString;
import se.raddo.raddose3D.Wedge;

/**
 * Test cases for InputParser and package raddoseParser
 * 
 * @author Markus Gerstel
 */

public class InputParserTest {

  @Test(timeOut = 2000)
  public void InputParserReadInvalidFile() {
    InputParser parser = new InputParserString("Crystals\nare\nfun\n");
    Initializer i = new InputParserTestInit();

    Assert.assertNotNull(parser);

    try {
      parser.sendData(i);
    } catch (InputException e) {
      return;
    }

    Assert.fail("Parser accepted invalid input file (should fail)");
  }

  @Test(timeOut = 2000)
  public void InputParserReadEmptyFile() {
    InputParser parser = new InputParserString("");
    InputParserTestInit init = new InputParserTestInit();

    Assert.assertNotNull(parser);

    try {
      parser.sendData(init);
    } catch (InputException e) {
      Assert.fail("Parser failed on empty input file");
    }

    Assert.assertEquals(init.crystals.size(), 0);
    Assert.assertEquals(init.beams.size(), 0);
    Assert.assertEquals(init.wedges.size(), 0);
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

  @Test(timeOut = 3000)
  public void InputParserReadValidSimpleFile() {
    InputParser parser = new InputParserString(sampleFile_Simple());
    InputParserTestInit init = new InputParserTestInit();
    TestCrystalFactory testCF = new TestCrystalFactory();
    TestBeamFactory testBF = new TestBeamFactory();
    parser.setCrystalFactory(testCF);
    parser.setBeamFactory(testBF);

    try {
      parser.sendData(init);
    } catch (InputException e) {
      Assert.fail("Parser failed on valid simple input file\n" + e.toString());
    }

    Assert.assertEquals(init.crystals.size(), 1);
    Assert.assertEquals(init.beams.size(), 1);
    Assert.assertEquals(init.wedges.size(), 1);

    Assert
        .assertTrue(
            init.crystals.get(0) instanceof CrystalDummy,
            "Crystal not stored");
    Assert.assertEquals(testCF.createEvents, 1,
        "Seen more than one create event");
    Assert.assertEquals(testCF.lastSeenType, "Cuboid",
        "Crystal type set incorrectly (" + testCF.lastSeenType + ")");

    Map<Object, Object> properties;
    Iterator<Object> keys;

    properties = testCF.getSeenProperties();
    keys = properties.keySet().iterator();
    while (keys.hasNext()) {
      Boolean match = false;
      Object me = keys.next();
      if (me.equals(Crystal.CRYSTAL_DIM_X)) {
        Assertion.equals((Double) properties.get(me), 100, "Crystal size X");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_DIM_Y)) {
        Assertion.equals((Double) properties.get(me), 100, "Crystal size Y");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_DIM_Z)) {
        Assertion.equals((Double) properties.get(me), 100, "Crystal size Z");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_RESOLUTION)) {
        Assertion.equals((Double) properties.get(me), 0.73,
            "Crystal resolution");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_COEFCALC)) {
        Assert.assertTrue(properties.get(me) instanceof CoefCalcAverage,
            "CoefCalc not initialized with CoefCalcDummy");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_DDM)) {
        Assert.assertTrue(properties.get(me) instanceof DDMSimple,
            "DDM is of wrong type");
        match = true;
      }
      if (!match) {
        Assert.fail("Unexpected crystal property " + me + " set. ("
            + properties.get(me) + ")");
      }
    }

    Assert.assertTrue(
        init.beams.get(0) instanceof BeamDummy,
        "Beam not stored");
    Assert.assertEquals(testBF.createEvents, 1,
        "Seen more than one beam create event");
    Assertion.equals(testBF.lastSeenType, "Gaussian", "Beam type");

    properties = testBF.getSeenProperties();
    keys = properties.keySet().iterator();
    while (keys.hasNext()) {
      Boolean match = false;
      Object me = keys.next();
      if (me.equals(Beam.BEAM_FLUX)) {
        Assertion.equals((Double) properties.get(me), 200000000000d,
            "Beam flux");
        match = true;
      }
      if (me.equals(Beam.BEAM_FWHM_X)) {
        Assertion.equals((Double) properties.get(me), 70, "Beam x FWHM");
        match = true;
      }
      if (me.equals(Beam.BEAM_FWHM_Y)) {
        Assertion.equals((Double) properties.get(me), 20, "Beam y FWHM");
        match = true;
      }
      if (me.equals(Beam.BEAM_ENERGY)) {
        Assertion.equals((Double) properties.get(me), 12.1, "Beam energy");
        match = true;
      }
      if (me.equals(Beam.BEAM_COLL_H)) {
        Assertion.equals((Double) properties.get(me), 55,
            "Beam horizontal collimation");
        match = true;
      }
      if (me.equals(Beam.BEAM_COLL_V)) {
        Assertion.equals((Double) properties.get(me), 19,
            "Beam vertical collimation");
        match = true;
      }
      if (!match) {
        Assert.fail("Unexpected beam property " + me + " set. ("
            + properties.get(me) + ")");
      }
    }

    Wedge w = init.wedges.get(0);
    Assert.assertNotNull(w, "Wedge not stored");
    Assertion.equals(w.getAngRes(), Math.toRadians(2.6),
        "Wedge angular resolution");
    Assertion.equals(w.getStartAng(), Math.toRadians(0), "Wedge start angle");
    Assertion.equals(w.getEndAng(), Math.toRadians(360), "Wedge end angle");
    Assertion.equals(w.getTotSec(), 150, "Wedge total exposure time");
    Assertion.equals(w.getStartX(), 17.3, "Wedge X start offset");
    Assertion.equals(w.getStartY(), 24.1, "Wedge Y start offset");
    Assertion.equals(w.getStartZ(), 0d, "Wedge Z start offset");
    Assertion.equals(w.getTransX(), 1 / Math.toRadians(1 / 0.1),
        "Wedge X translation");
    Assertion.equals(w.getTransY(), 0.0, "Wedge Y translation");
    Assertion.equals(w.getTransZ(), 1 / Math.toRadians(1 / 0.3),
        "Wedge Z translation");
    Assertion.equals(w.getOffAxisUm(), 5, "Wedge rotational axis offset");
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

  @Test(timeOut = 3000)
  public void InputParserReadValidComplexFile1() {
    InputParser parser = new InputParserString(sampleFile_Complex_1());
    InputParserTestInit init = new InputParserTestInit();
    TestCrystalFactory testCF = new TestCrystalFactory();
    TestBeamFactory testBF = new TestBeamFactory();
    parser.setCrystalFactory(testCF);
    parser.setBeamFactory(testBF);

    try {
      parser.sendData(init);
    } catch (InputException e) {
      Assert.fail("Parser failed on valid simple input file\n" + e.toString());
    }

    Assert.assertEquals(init.crystals.size(), 1);
    Assert.assertEquals(init.beams.size(), 1);
    Assert.assertEquals(init.wedges.size(), 1);

    Assert
        .assertTrue(
            init.crystals.get(0) instanceof CrystalDummy,
            "Crystal not stored");
    Assert.assertEquals(testCF.createEvents, 1,
        "Seen more than one crystal create event");
    Assert.assertEquals(testCF.lastSeenType, "Cuboid", "Crystal type");

    Map<Object, Object> properties;
    Iterator<Object> keys;

    properties = testCF.getSeenProperties();
    keys = properties.keySet().iterator();
    while (keys.hasNext()) {
      Boolean match = false;
      Object me = keys.next();
      if (me.equals(Crystal.CRYSTAL_DIM_X)) {
        Assertion.equals((Double) properties.get(me), 75, "Crystal size X");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_DIM_Y)) {
        Assertion.equals((Double) properties.get(me), 70, "Crystal size Y");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_DIM_Z)) {
        Assertion.equals((Double) properties.get(me), 60, "Crystal size Z");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_RESOLUTION)) {
        Assertion.equals((Double) properties.get(me), 0.273,
            "Crystal resolution");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_ANGLE_P)) {
        Assertion.equals((Double) properties.get(me), 1, "Crystal angle P");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_ANGLE_L)) {
        Assertion.equals((Double) properties.get(me), 0.1, "Crystal angle L");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_COEFCALC)) {
        Assert.assertTrue(properties.get(me) instanceof CoefCalcAverage,
            "CoefCalc not initialized with CoefCalcDummy");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_DDM)) {
        Assert.assertTrue(properties.get(me) instanceof DDMSimple,
            "DDM is of wrong type");
        match = true;
      }
      if (!match) {
        Assert.fail("Unexpected crystal property " + me + " set. ("
            + properties.get(me) + ")");
      }
    }

    Assert.assertTrue(
        init.beams.get(0) instanceof BeamDummy,
        "Beam not stored");
    Assert.assertEquals(testBF.createEvents, 1,
        "Seen more than one beam create event");
    Assert.assertEquals(testBF.lastSeenType, "Gaussian",
        "Beam type set incorrectly (" + testBF.lastSeenType + ")");

    properties = testBF.getSeenProperties();
    keys = properties.keySet().iterator();
    while (keys.hasNext()) {
      Boolean match = false;
      Object me = keys.next();
      if (me.equals(Beam.BEAM_FLUX)) {
        Assertion.equals((Double) properties.get(me), 200000000000d,
            "Beam flux");
        match = true;
      }
      if (me.equals(Beam.BEAM_FWHM_X)) {
        Assertion.equals((Double) properties.get(me), 70, "Beam x FWHM");
        match = true;
      }
      if (me.equals(Beam.BEAM_FWHM_Y)) {
        Assertion.equals((Double) properties.get(me), 20, "Beam y FWHM");
        match = true;
      }
      if (me.equals(Beam.BEAM_ENERGY)) {
        Assertion.equals((Double) properties.get(me), 12.1, "Beam energy");
        match = true;
      }
      if (me.equals(Beam.BEAM_COLL_H)) {
        Assertion.equals((Double) properties.get(me), 41,
            "Beam horizontal collimation");
        match = true;
      }
      if (!match) {
        Assert.fail("Unexpected beam property " + me + " set. ("
            + properties.get(me) + ")");
      }
    }

    Wedge w = init.wedges.get(0);
    Assert.assertNotNull(w, "Wedge not stored");

    Assertion.equals(w.getAngRes(), Math.toRadians(2.6d),
        "Wedge angular resolution");
    Assertion.equals(w.getStartAng(), Math.toRadians(0d), "Wedge start angle");
    Assertion.equals(w.getEndAng(), Math.toRadians(360d), "Wedge end angle");
    Assertion.equals(w.getTotSec(), 150d, "Wedge total exposure time");
    Assertion.equals(w.getStartX(), 17.3d, "Wedge X start offset");
    Assertion.equals(w.getStartY(), 24.1d, "Wedge Y start offset");
    Assertion.equals(w.getStartZ(), 0.2d, "Wedge Z start offset");
    Assertion.equals(w.getTransX(), 1 / Math.toRadians(1 / 0.1d),
        "Wedge X translation");
    Assertion.equals(w.getTransY(), 0.0d, "Wedge Y translation");
    Assertion.equals(w.getTransZ(), 1 / Math.toRadians(1 / 0.21d),
        "Wedge Z translation");
    Assertion.equals(w.getOffAxisUm(), 5d, "Wedge rotational axis offset");
  }

  @Test(timeOut = 3000)
  public void InputParserReadValidComplexFile2() {
    InputParser parser = new InputParserString(sampleFile_Complex_2());
    InputParserTestInit init = new InputParserTestInit();
    TestCrystalFactory testCF = new TestCrystalFactory();
    TestBeamFactory testBF = new TestBeamFactory();
    parser.setCrystalFactory(testCF);
    parser.setBeamFactory(testBF);

    try {
      parser.sendData(init);
    } catch (InputException e) {
      Assert.fail("Parser failed on valid simple input file\n" + e.toString());
    }

    Assert.assertEquals(init.crystals.size(), 1);
    Assert.assertEquals(init.beams.size(), 1);
    Assert.assertEquals(init.wedges.size(), 1);

    Assert
        .assertTrue(
            init.crystals.get(0) instanceof CrystalDummy,
            "Crystal not stored");
    Assert.assertEquals(testCF.createEvents, 1,
        "Seen more than one create event");
    Assert.assertEquals(testCF.lastSeenType, "SpHeRiCaL",
        "Crystal type set incorrectly (" + testCF.lastSeenType + ")");
    Map<Object, Object> properties;
    Iterator<Object> keys;

    properties = testCF.getSeenProperties();
    keys = properties.keySet().iterator();
    while (keys.hasNext()) {
      Boolean match = false;
      Object me = keys.next();
      if (me.equals(Crystal.CRYSTAL_DIM_X)) {
        Assertion.equals((Double) properties.get(me), 42, "Crystal size X");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_RESOLUTION)) {
        Assertion
            .equals((Double) properties.get(me), 0.4, "Crystal resolution");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_COEFCALC)) {
        Assert.assertTrue(properties.get(me) instanceof CoefCalcAverage,
            "CoefCalc not initialized with CoefCalcDummy");
        match = true;
      }
      if (me.equals(Crystal.CRYSTAL_DDM)) {
        Assert.assertTrue(properties.get(me) instanceof DDMSimple,
            "DDM is of wrong type");
        match = true;
      }
      if (!match) {
        Assert.fail("Unexpected crystal property " + me + " set. ("
            + properties.get(me) + ")");
      }
    }

    Assert.assertTrue(
        init.beams.get(0) instanceof BeamDummy,
        "Beam not stored");
    Assert.assertEquals(testBF.createEvents, 1,
        "Seen more than one beam create event");
    Assertion.equals(testBF.lastSeenType, "Tophat", "Beam type");

    properties = testBF.getSeenProperties();
    keys = properties.keySet().iterator();
    while (keys.hasNext()) {
      Boolean match = false;
      Object me = keys.next();
      if (me.equals(Beam.BEAM_FLUX)) {
        Assertion
            .equals((Double) properties.get(me), 10000000000d, "Beam flux");
        match = true;
      }
      if (me.equals(Beam.BEAM_FWHM_X)) {
        Assertion.equals((Double) properties.get(me), 55, "Beam x FWHM");
        match = true;
      }
      if (me.equals(Beam.BEAM_FWHM_Y)) {
        Assertion.equals((Double) properties.get(me), 20, "Beam y FWHM");
        match = true;
      }
      if (me.equals(Beam.BEAM_ENERGY)) {
        Assertion.equals((Double) properties.get(me), 5, "Beam energy");
        match = true;
      }
      if (me.equals(Beam.BEAM_COLL_V)) {
        Assertion.equals((Double) properties.get(me), 15,
            "Beam vertical collimation");
        match = true;
      }
      if (!match) {
        Assert.fail("Unexpected beam property " + me + " set. ("
            + properties.get(me) + ")");
      }
    }

    Wedge w = init.wedges.get(0);
    Assert.assertNotNull(w, "Wedge not stored");

    Assertion.equals(w.getAngRes(), Math.toRadians(2.6),
        "Wedge angular resolution");
    Assertion.equals(w.getStartAng(), Math.toRadians(0), "Wedge start angle");
    Assertion.equals(w.getEndAng(), Math.toRadians(360), "Wedge end angle");
    Assertion.equals(w.getTotSec(), 150, "Wedge total exposure time");
    Assertion.equals(w.getStartX(), 17.3, "Wedge X start offset");
    Assertion.equals(w.getStartY(), 24.1, "Wedge Y start offset");
    Assertion.equals(w.getTransX(), 1 / Math.toRadians(1 / 0.1),
        "Wedge X translation");
    Assertion.equals(w.getTransY(), 0.0, "Wedge Y translation");
    Assertion.equals(w.getOffAxisUm(), 5, "Wedge rotational axis offset");
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

  private class TestCrystalFactory extends CrystalFactory {
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

  private class TestBeamFactory extends BeamFactory {
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

  private class InputParserTestInit implements Initializer {
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
    }

    @Override
    public void addReference(String reference) {
    }
  }
}
