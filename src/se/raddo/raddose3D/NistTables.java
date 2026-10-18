package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * NIST's X-ray mass attenuation coefficients, which the sample containers
 * read: J. H. Hubbell and S. M. Seltzer, Tables of X-Ray Mass Attenuation
 * Coefficients and Mass Energy-Absorption Coefficients, NIST Standard
 * Reference Database 126, doi:10.18434/T4D01F.
 * <p>
 * The containers downloaded these from physics.nist.gov on every run, one
 * page per element or compound. A run without the network failed, and so did
 * every run on Java 8, whose requests NIST refuses: in both, with a
 * NullPointerException. The tables now ship in constants/, extracted from the
 * same pages as each container read them, row for row, so the coefficients
 * are the same.
 */
final class NistTables {

  /** One table per element, keyed by atomic number. */
  static final String ELEMENTS_FILE = "constants/nist_elements.tsv";

  /** One table per compound or mixture, keyed by NIST's page name. */
  static final String MIXTURES_FILE = "constants/nist_mixtures.tsv";

  private static Map<String, List<double[]>> elements;
  private static Map<String, List<double[]>> mixtures;

  private NistTables() {
  }

  /**
   * An element's table.
   *
   * @param atomicNumber the element's atomic number
   * @return rows of {energy in MeV, mass attenuation coefficient in cm^2/g},
   *         in NIST's order, an absorption edge as two rows at one energy;
   *         null if NIST has no table for it
   */
  static synchronized List<double[]> element(final int atomicNumber) {
    if (elements == null) {
      elements = read(ELEMENTS_FILE);
    }
    return elements.get(String.valueOf(atomicNumber));
  }

  /**
   * A compound's or mixture's table, by NIST's name for its page: "pyrex",
   * "water", "alanine". Any case.
   *
   * @param name the name
   * @return rows as for {@link #element}; null if NIST has no table by that
   *         name
   */
  static synchronized List<double[]> mixture(final String name) {
    if (mixtures == null) {
      mixtures = read(MIXTURES_FILE);
    }
    return name == null ? null : mixtures.get(name.toLowerCase(Locale.ROOT));
  }

  /**
   * @return NIST's names for the compounds and mixtures it has tables for
   */
  static synchronized Set<String> mixtureNames() {
    mixture("");
    return Collections.unmodifiableSet(mixtures.keySet());
  }

  /**
   * Reads a table file: lines beginning # are comments, the first other line
   * is the header, and each row is a key, an energy and a coefficient,
   * separated by tabs.
   */
  private static Map<String, List<double[]>> read(final String fileName) {
    Map<String, List<double[]>> tables =
        new LinkedHashMap<String, List<double[]>>();
    InputStream is = NistTables.class.getResourceAsStream("/" + fileName);
    try {
      if (is == null) {
        is = new FileInputStream(fileName);
      }
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(is, StandardCharsets.UTF_8));
      try {
        boolean header = true;
        String line;
        while ((line = reader.readLine()) != null) {
          if (line.startsWith("#") || line.trim().isEmpty()) {
            continue;
          }
          if (header) {
            header = false;
            continue;
          }
          String[] fields = line.split("\t");
          List<double[]> rows = tables.get(fields[0]);
          if (rows == null) {
            rows = new ArrayList<double[]>();
            tables.put(fields[0], rows);
          }
          rows.add(new double[] {Double.parseDouble(fields[1]),
              Double.parseDouble(fields[2])});
        }
      } finally {
        reader.close();
      }
    } catch (IOException e) {
      throw new IllegalStateException("Cannot read " + fileName, e);
    }
    return tables;
  }
}
