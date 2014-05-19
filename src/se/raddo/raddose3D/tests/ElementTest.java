package se.raddo.raddose3D.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import se.raddo.raddose3D.Element;
import se.raddo.raddose3D.Element.CrossSection;
import se.raddo.raddose3D.ElementDatabase.DatabaseFields;

/**
 * Generate an element and compare results with results calculated from
 * http://cars9.uchicago.edu/mcbook/
 */
public class ElementTest {

  @Test
  public void createElement() {
    Element s = new Element("S", 16, getSulphur());

    Assertion.equals(s.getAtomicNumber(), 16, "atomic number");
    Assertion.equals(s.getAtomicWeight(), 3.20660e+01, "atomic weight");
    Assertion.equals(s.getAtomicWeightInGrams(), 5.3246841e-23,
        "atomic weight (g)");
    Assertion.equals(s.getElementName(), "S", "element name");

    compareResults(s, 1.1, 1978.71497, 2.62428641, 1981.35059);
  }

  private void compareResults(final Element e, final double energy,
      final double photoelectric, final double coherent, final double total) {
    Map<CrossSection, Double> xs = e.calculateMu(energy);
    Assertion.equals(xs.get(Element.CrossSection.PHOTOELECTRIC), photoelectric,
        "Photoelectric cross-section at " + energy + " keV", 0.001);
    Assertion.equals(xs.get(Element.CrossSection.COHERENT), photoelectric,
        "Coherent cross-section at " + energy + " keV", 0.001);
    Assertion.equals(xs.get(Element.CrossSection.TOTAL), photoelectric,
        "Total cross-section at " + energy + " keV", 0.001);
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
