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

  public void sulphurTests(final Element s) {
    compareResults(s, 1.1, 1978.71497, 2.62428641, 1981.35059);
    compareResults(s, 1.2, 1568.09387, 2.63965917, 1570.74646);
    compareResults(s, 1.7, 609.046936, 2.56492448, 611.633484);
    compareResults(s, 2.1, 339.237946, 2.42386913, 341.690186);
    compareResults(s, 2.3, 262.987366, 2.34494638, 265.363983);
    compareResults(s, 2.4, 233.32811, 2.30482531, 235.666229);
    compareResults(s, 2.4719, 214.701355, 2.27591038, 217.011719);
    compareResults(s, 2.4721, 2217.96924, 2.27582908, 2220.2793);
    compareResults(s, 2.5, 2156.88818, 2.26461291, 2159.18774);
    compareResults(s, 5, 349.58316, 1.44702506, 351.098572);
    compareResults(s, 10, 48.4815712, 0.716733396, 49.3056908);
  }

  private static void compareResults(final Element e, final double energy,
      final double photoelectric, final double coherent, final double total) {
    Map<CrossSection, Double> xs = e.calculateMu(energy);
    Assertion.equals(xs.get(Element.CrossSection.PHOTOELECTRIC) / Element.C,
        photoelectric, "Photoelectric cross-section at " + energy + " keV",
        0.001);
    Assertion.equals(xs.get(Element.CrossSection.COHERENT) / Element.C,
        coherent, "Coherent cross-section at " + energy + " keV", 0.001);
    Assertion.equals(xs.get(Element.CrossSection.TOTAL) / Element.C,
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
