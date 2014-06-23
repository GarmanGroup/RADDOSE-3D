package se.raddo.raddose3D.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import se.raddo.raddose3D.Element;
import se.raddo.raddose3D.Element.CrossSection;
import se.raddo.raddose3D.ElementDatabase.DatabaseFields;

/**
 * Generate an element and compare results with results obtained from
 * http://cars9.uchicago.edu/mcbook/
 */
public class ElementTest {

  @Test
  public void checkElement() {
    Element s = new Element("S", 16, getSulphur());

    Assertion.equals(s.getAtomicNumber(), 16, "atomic number");
    Assertion.equals(s.getAtomicWeight(), 3.20660e+01, "atomic weight");
    Assertion.equals(s.getAtomicWeightInGrams(), 5.3246841e-23,
        "atomic weight (g)");
    Assertion.equals(s.getElementName(), "S", "element name");

    sulphurTests(s);
  }

  // Theoretical values obtained from e.g.
  // http://csrri.iit.edu/cgi-bin/period-form?ener=1.1&name=S
  public void sulphurTests(final Element s) {
    /** Conversion factor [Barns/Atom] = C * [cm^2/g]. */
    final double C = 53.2400017;

    compareResults(s, C, 1.1, 1978.71497, 2.62428641, 1981.35059);
    compareResults(s, C, 1.2, 1568.09387, 2.63965917, 1570.74646);
    compareResults(s, C, 1.7, 609.046936, 2.56492448, 611.633484);
    compareResults(s, C, 2.1, 339.237946, 2.42386913, 341.690186);
    compareResults(s, C, 2.3, 262.987366, 2.34494638, 265.363983);
    compareResults(s, C, 2.4, 233.32811, 2.30482531, 235.666229);
    compareResults(s, C, 2.4719, 214.701355, 2.27591038, 217.011719);
    compareResults(s, C, 2.4721,
        2217.96924 + 0.001, 2.27582908, 2220.2793 + 0.001);
    // Our calculation has higher precision than the website values
    compareResults(s, C, 2.5, 2156.88818, 2.26461291, 2159.18774);
    compareResults(s, C, 5, 349.58316, 1.44702506, 351.098572);
    compareResults(s, C, 10, 48.4815712, 0.716733396, 49.3056908);
  }

  // Theoretical values obtained from e.g.
  // http://csrri.iit.edu/cgi-bin/period-form?ener=1.1&name=Mg
  public void manganeseTests(final Element mg) {
    /** Conversion factor [Barns/Atom] = C * [cm^2/g]. */
    final double C = 40.3800011;

    compareResults(mg, C, 1.1, 753.605774, 2.02505422, 755.646667);
    compareResults(mg, C, 1.2, 588.960938, 2.03198099, 591.010681);
    compareResults(mg, C, 1.30, 470.938721, 2.02889442, 472.987274);
    compareResults(mg, C, 1.31,
        5354.30518 - 0.007, 2.0281291, 5356.35303 - 0.007);
    // Our calculation has higher precision than the website values
    compareResults(mg, C, 1.7, 2900.64331, 1.95550072, 2902.62573);
    compareResults(mg, C, 2.1,
        1719.7334 - 0.001, 1.83737242, 1721.60461 - 0.001);
    // Our calculation has higher precision than the website values
    compareResults(mg, C, 2.3, 1364.00793, 1.77321875, 1365.81836);
    compareResults(mg, C, 2.5, 1099.26038, 1.70869017, 1101.0094);
    compareResults(mg, C, 5, 163.331894, 1.07319105, 164.477081);
    compareResults(mg, C, 10, 20.6076527, 0.52411586, 21.2394829);
    compareResults(mg, C, 0.05,
        41337100. - 33.815, 0.0305607822, 41337100. - 33.785);
    // Our calculation has higher precision than the website values
    compareResults(mg, C, 0.1, 
        2718515.75 + 4.713, 0.169477433, 2718516. + 4.633);
    // Our calculation has higher precision than the website values
  }

  private static void compareResults(final Element e,
      final double conversionfactor, final double energy,
      final double photoelectric, final double coherent, final double total) {
    Map<CrossSection, Double> xs = e.calcAbsCoefficients(energy);
    Assertion.equals(xs.get(Element.CrossSection.PHOTOELECTRIC)
        / conversionfactor, photoelectric, "Photoelectric cross-section at "
        + energy + " keV", 0.001);
    Assertion.equals(xs.get(Element.CrossSection.COHERENT) / conversionfactor,
        coherent, "Coherent cross-section at " + energy + " keV", 0.001);
    Assertion.equals(xs.get(Element.CrossSection.TOTAL) / conversionfactor,
        total, "Total cross-section at " + energy + " keV", 0.001);
  }

  private Map<DatabaseFields, Double> getSulphur() {
    Map<DatabaseFields, Double> sul = new HashMap<DatabaseFields, Double>();
    sul.put(DatabaseFields.EDGE_K, 2.47200e+00);
    sul.put(DatabaseFields.EDGE_L, 1.93000e-01);
    sul.put(DatabaseFields.EDGE_M, 1.70000e-02);

    sul.put(DatabaseFields.K_COEFF_0, 1.37394e+01);
    sul.put(DatabaseFields.K_COEFF_1, -2.04786e+00);
    sul.put(DatabaseFields.K_COEFF_2, -2.73259e-01);
    sul.put(DatabaseFields.K_COEFF_3, 2.29976e-02);

    sul.put(DatabaseFields.L_COEFF_0, 1.18181e+01);
    sul.put(DatabaseFields.L_COEFF_1, -2.64618e+00);
    sul.put(DatabaseFields.L_COEFF_2, -9.68049e-02);
    sul.put(DatabaseFields.L_COEFF_3, 0.00000e+00);

    sul.put(DatabaseFields.M_COEFF_0, 0.00000e+00);
    sul.put(DatabaseFields.M_COEFF_1, 0.00000e+00);
    sul.put(DatabaseFields.M_COEFF_2, 0.00000e+00);
    sul.put(DatabaseFields.M_COEFF_3, 0.00000e+00);

    sul.put(DatabaseFields.N_COEFF_0, 0.00000e+00);
    sul.put(DatabaseFields.N_COEFF_1, 0.00000e+00);
    sul.put(DatabaseFields.N_COEFF_2, 0.00000e+00);
    sul.put(DatabaseFields.N_COEFF_3, 0.00000e+00);

    // values 21, 22: ignore  // 2.00000e+00 5.32400e+01

    sul.put(DatabaseFields.ATOMIC_WEIGHT, 3.20660e+01);

    sul.put(DatabaseFields.COHERENT_COEFF_0, 4.92707e+00);
    sul.put(DatabaseFields.COHERENT_COEFF_1, 1.65746e-01);
    sul.put(DatabaseFields.COHERENT_COEFF_2, -3.59424e-01);
    sul.put(DatabaseFields.COHERENT_COEFF_3, 1.95505e-02);

    sul.put(DatabaseFields.INCOHERENT_COEFF_0, -6.56419e-01);
    sul.put(DatabaseFields.INCOHERENT_COEFF_1, 1.65408e+00);
    sul.put(DatabaseFields.INCOHERENT_COEFF_2, -2.98623e-01);
    sul.put(DatabaseFields.INCOHERENT_COEFF_3, 1.42979e-02);

    // values 32-35: ignore // 2.30800e+00 2.46400e+00 0.00000e+00 0.00000e+00

    sul.put(DatabaseFields.L2, 1.63600e-01);
    sul.put(DatabaseFields.L3, 1.62500e-01);

    // values 38-: ignore // 0.00000e+00 7.80000e-02 7.40000e-05 2.60000e-04 2.60000e-04

    return sul;
  }
}
