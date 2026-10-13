package se.raddo.raddose3D;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The electron calculations (MicroED, and the Monte Carlo's elastic
 * scattering) must see the same atoms as the X-ray calculation.
 * <p>
 * They used to read a second, electron-only set of composition maps that
 * each route filled alongside its own. The routes did not fill them alike:
 * the PDB route never multiplied them by the number of molecules in the cell
 * and left out the HETATM atoms, and the sequence and CIF routes never filled
 * them at all, so MicroED saw the solvent and nothing else. Only the
 * unit-cell route agreed.
 */
public class ElectronCompositionTest {

  private static final List<String> NO_NAMES = new ArrayList<String>();
  private static final List<Double> NO_AMOUNTS = new ArrayList<Double>();

  private static Path write(final Path path, final String text)
      throws IOException {
    Files.write(path, text.getBytes(StandardCharsets.UTF_8));
    return path;
  }

  /** Every element, counted the same way for electrons as for X-rays. */
  private static void assertElectronsSeeEveryAtom(
      final CoefCalcCompute coefCalc, final String... expected) {
    Set<Element> present = coefCalc.getPresentElements(false);
    for (String symbol : expected) {
      Element e = coefCalc.getParser().getElement(symbol);
      assertTrue(present.contains(e) && coefCalc.totalAtoms(e) > 0,
          symbol + " should be in the crystal");
    }
    for (Element e : present) {
      ElementEM em = coefCalc.getParserEM().getElement(e.getAtomicNumber());
      String name = e.getElementName();
      assertEquals(coefCalc.totalAtoms(e), coefCalc.totalAtomsEM(em), 0.0,
          name + " per unit cell");
      assertEquals(coefCalc.getMacromolecularOccurrence(e),
          coefCalc.getProteinAtoms(em), 0.0, name + " in the macromolecule");
      assertEquals(coefCalc.getSolventOccurrence(e),
          coefCalc.getSolventAtoms(em), 0.0, name + " in the solvent");
    }
  }

  @Test
  public void unitCell() {
    assertElectronsSeeEveryAtom(new CoefCalcFromParams(78.02, 78.02, 78.02,
        90.0, 90.0, 90.0, 24, 51, 0, 0, Arrays.asList("ZN", "S"),
        Arrays.asList(0.333, 6.0), Arrays.asList("P"), Arrays.asList(425.0),
        NO_NAMES, NO_AMOUNTS, 0.64, null, null, 0, NO_NAMES, NO_AMOUNTS, 0, 0),
        "C", "N", "O", "H", "S", "ZN", "P");
  }

  /** Two molecules in the cell, and a zinc that only HETATM supplies. */
  @Test
  public void pdb(@TempDir final Path dir) throws IOException {
    Path pdb = write(dir.resolve("two.pdb"),
        "CRYST1   78.270   78.270   78.270  90.00  90.00  90.00 P 1\n"
        + "REMARK 290   SMTRY1   1  1.000000  0.000000  0.000000        0.00000\n"
        + "REMARK 290   SMTRY1   2 -1.000000  0.000000  0.000000        0.00000\n"
        + "SEQRES   1 A    3  ALA CYS SER\n"
        + "HETATM    1  ZN   ZN A 201      10.000  10.000  10.000  1.00 20.00"
        + "          ZN\n"
        + "END\n");
    CoefCalcFromPDB coefCalc = new CoefCalcFromPDB(pdb.toString(), NO_NAMES,
        NO_AMOUNTS, null, null, NO_NAMES, NO_AMOUNTS, 0.0, 0L);

    assertEquals(2, coefCalc.getNumMonomers());
    assertElectronsSeeEveryAtom(coefCalc, "C", "N", "O", "H", "S", "ZN");
  }

  @Test
  public void sequence() {
    assertElectronsSeeEveryAtom(new CoefCalcFromSequence(78.27, 78.27, 78.27,
        90.0, 90.0, 90.0, 8, NO_NAMES, NO_AMOUNTS, NO_NAMES, NO_AMOUNTS, null,
        "test/resources/TestSequence.fasta", NO_NAMES, NO_AMOUNTS, null, null,
        0, NO_NAMES, NO_AMOUNTS, 0.0), "C", "N", "O", "H", "S", "P");
  }

  @Test
  public void cif(@TempDir final Path dir) throws IOException {
    Path cif = write(dir.resolve("urea.cif"), "data_urea\n"
        + "_chemical_formula_sum            'C H4 N2 O'\n"
        + "_cell_volume                     145.9\n"
        + "_cell_formula_units_Z            2\n");
    assertElectronsSeeEveryAtom(new CoefCalcFromCIF(cif.toString()),
        "C", "N", "O", "H");
  }
}
