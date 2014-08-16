package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Calculate absorption and attenuation coefficients using a previous version of
 * RADDOSE.
 *
 * @author Oliver Zeldin
 */
public class CoefCalcRaddose extends CoefCalc {
  /** Constant for unit conversion. */
  private static final long UNITSPERMILLIUNIT = 1000L;

  /**
   * Suggested location of RADDOSE executable.
   */
  private static String     raddosePathOverride;

  /**
   * Actual location of RADDOSE executable.
   * Will try to find it if not set manually.
   */
  private static String     raddosePath;

  /**
   * Description of the experiment.
   */
  private final String      raddoseInput;

  /**
   * Identified coefficients and density from last program run.
   */
  private double            absCoeff, attCoeff, elasCoeff, density;

  public CoefCalcRaddose(final Double cellA, final Double cellB,
      final Double cellC,
      final Double cellAlpha, final Double cellBeta, final Double cellGamma,
      final int numMonomers, final int numResidues, final int numRNA,
      final int numDNA,
      final List<String> heavyProteinAtomNames,
      final List<Double> heavyProteinAtomNums,
      final List<String> heavySolutionConcNames,
      final List<Double> heavySolutionConcNums,
      final Double solventFraction) {

    String cell;
    if (cellAlpha == null || cellBeta == null || cellGamma == null) {
      cell = "CELL " + cellA + " " + cellB + " " + cellC + "\n";
    } else {
      cell = "CELL " + cellA + " " + cellB + " " + cellC + " "
          + cellAlpha + " " + cellBeta + " " + cellGamma + "\n";
    }

    String nMon = "NMON " + numMonomers + "\n";
    String nRes = "NRES " + numResidues + "\n";

    String nDNA;
    if (numDNA > 0) {
      nDNA = "NDNA " + numDNA + "\n";
    } else {
      nDNA = "";
    }
    String nRNA;
    if (numRNA > 0) {
      nRNA = "NRNA " + numRNA + "\n";
    } else {
      nRNA = "";
    }

    String pAtm;
    if ((heavyProteinAtomNames == null)
        || heavyProteinAtomNames.isEmpty()) {
      pAtm = "";
    } else {
      StringBuffer p = new StringBuffer("PATM");
      for (int i = 0; i < heavyProteinAtomNames.size(); i++) {
        p.append(" " + heavyProteinAtomNames.get(i) + " "
            + heavyProteinAtomNums.get(i));
      }
      pAtm = new String(p) + "\n";
    }

    String sAtm;
    if ((heavySolutionConcNames == null)
        || heavySolutionConcNames.isEmpty()) {
      sAtm = "";
    } else {
      StringBuffer p = new StringBuffer("SATM");
      for (int i = 0; i < heavySolutionConcNames.size(); i++) {
        p.append(" " + heavySolutionConcNames.get(i) + " "
            + heavySolutionConcNums.get(i));
      }
      sAtm = new String(p) + "\n";
    }

    String solvent;
    if (solventFraction == null) {
      solvent = "";
    } else {
      solvent = "SOLVENT " + solventFraction + "\n";
    }

    // Just some defaults, since this does not affect coefficient calculations.
    String crystal = "CRYSTAL 100 100 100" + "\n";
    String beam = String.format("BEAM 100 100") + "\n";
    String images = "IMAGES 1" + "\n";

    raddoseInput = cell + nMon + nRes
        + nDNA + nRNA + pAtm + sAtm
        + solvent + crystal + beam + images;
  }

  /**
   * Start an instance of legacy RADDOSE. A number of locations will be tried.
   * If RADDOSE can not be found or run a RuntimeException will be thrown.
   * If an instance of RADDOSE is found then its location is cached for
   * subsequent calls.
   *
   * @return
   *         object referring to the running RADDOSE instance.
   */
  private static Process runRaddose() {
    Runtime rt = Runtime.getRuntime();
    Process raddose = null;

    // If RADDOSE has been found before then try at the same location.
    if (raddosePath != null) {
      try {
        raddose = rt.exec(raddosePath);
        return raddose;
      } catch (IOException e) {
        System.out.println("Found RADDOSE at " + raddosePath
            + " but could not execute. Trying to find another instance...");
      }
    }

    // Try to find RADDOSE executable
    List<String> raddoseCandidates = new ArrayList<String>();
    if ((raddosePathOverride != null) && (!raddosePathOverride.equals(""))) {
      raddoseCandidates.add(raddosePathOverride);
      if (raddosePathOverride.indexOf('/') < 0) {
        raddoseCandidates.add("./" + raddosePathOverride);
      }
    }
    raddoseCandidates.add("raddose");
    raddoseCandidates.add("raddose.exe");
    raddoseCandidates.add("./raddose");
    raddoseCandidates.add("./raddose.exe");
    raddoseCandidates.add("../raddose");
    raddoseCandidates.add("../raddose.exe");
    raddoseCandidates.add("/data/jenkins/jobs/Raddose3D master/workspace/raddose");
    
    String command = null;
    File fileCandidate;
    for (String raddoseCandidate : raddoseCandidates) {
      fileCandidate = new File(raddoseCandidate);
      if (fileCandidate.exists()) {
        // This is a good sign
        if (fileCandidate.canExecute()) {
          // This is even better
          command = raddoseCandidate;
          try {
            raddose = rt.exec(command);
          } catch (IOException e) {
            System.out.println("Found RADDOSE at " + raddoseCandidate
                + " but could not execute");
          }
          if (raddose != null) {
            // Success! Cache path.
            raddosePath = command;
            return raddose;
          }
        } else {
          System.out.println("Found RADDOSE at " + raddoseCandidate
              + " but is not executable");
        }
      }
    }

    if (command == null) {
      System.err
          .println("RADDOSE program could not be found. "
              + "Please specify path to RADDOSE manually "
              + "using the -r command line argument");
      throw new RuntimeException("Could not find RADDOSE executable.");
    }

    System.err
        .println("RADDOSE program could not be run. "
            + "Please specify path to RADDOSE manually "
            + "using the -r command line argument");
    throw new RuntimeException("Could not run RADDOSE executable.");
  }

  @Override
  @SuppressWarnings("PMD.PrematureDeclaration")
  public void updateCoefficients(final Beam b) {
    String energy = String.format("ENERGY %g%n", b.getPhotonEnergy());
    String phoSec = String.format("PHOSEC %g%n", b.getPhotonsPerSec());
    String exposure = String.format("EXPOSURE %d%n", 10);
    String debug = "DEBUG\n";

    Process oldRD = runRaddose();
    try {
      // Now run the old RADDOSE and get coefficients from output stream.

      BufferedWriter raddoseV2input = new BufferedWriter(
          new OutputStreamWriter(oldRD.getOutputStream()));

      String testRaddoseInput = raddoseInput
          + energy + phoSec + exposure + debug + "END\n";
      // DEBUG System.out.println(testRaddoseInput);
      raddoseV2input.write(testRaddoseInput);
      raddoseV2input.flush();

      BufferedReader raddoseV2output = new BufferedReader(
          new InputStreamReader(oldRD.getInputStream()));
      BufferedReader raddoseV2errorStream = new BufferedReader(
          new InputStreamReader(oldRD.getErrorStream()));

      Scanner scanIN = new Scanner(raddoseV2output);
      scanIN.useLocale(Locale.US);
      while (scanIN.hasNext()) {
        String word = scanIN.next();

        // DEBUG    System.out.print(word + " ");
        if ("Absorption".equals(word) && scanIN.hasNext("Coefficient")) {
          scanIN.next();
          scanIN.next();
          if (scanIN.hasNextDouble()) {
            absCoeff = scanIN.nextDouble() / UNITSPERMILLIUNIT;
          } else {
            scanIN.close();
            throw new RuntimeException(
                "Error in Absorption calculation using RDV2");
          }
        }
        if ("(Rayleigh)".equals(word)) {
          scanIN.next();
          scanIN.next();
          if (scanIN.hasNextDouble()) {
            elasCoeff = scanIN.nextDouble() / UNITSPERMILLIUNIT;
          } else {
            scanIN.close();
            throw new RuntimeException(
                "Error in Elastic calculation using RDV2");
          }
        }
        if ("Attenuation".equals(word)) {
          scanIN.next();
          scanIN.next();
          if (scanIN.hasNextDouble()) {
            attCoeff = scanIN.nextDouble() / UNITSPERMILLIUNIT;
          } else {
            scanIN.close();
            throw new RuntimeException(
                "Error in Attenuation calculation using RDV2");
          }
        }
        if ("Density".equals(word)) {
          scanIN.next();
          if (scanIN.hasNextDouble()) {
            density = scanIN.nextDouble();
          } else {
            scanIN.close();
            throw new RuntimeException(
                "Error in density calculation using RDV2");
          }
        }
      }
      scanIN.close();
      raddoseV2output.close();

      String line;
      while ((line = raddoseV2errorStream.readLine()) != null) {
        System.err.println("RADDOSE error during line: " + line);
      }
      raddoseV2errorStream.close();
      oldRD.waitFor();

    } catch (IOException t) {
      t.printStackTrace();
    } catch (InterruptedException t) {
      t.printStackTrace();
    }
  }

  @Override
  public double getAbsorptionCoefficient() {
    return absCoeff;
  }

  @Override
  public double getAttenuationCoefficient() {
    return attCoeff;
  }

  @Override
  public double getElasticCoefficient() {
    return elasCoeff;
  }

  @Override
  public double getDensity() {
    return density;
  }

  @Override
  public String toString() {
     return String.format(
        "Crystal coefficients calculated with RADDOSE V2 "
            + "(Paithankar et al., 2009). %n"
            + "Absorption Coefficient: %.2e /um.%n"
            + "Attenuation Coefficient: %.2e /um.%n"
            + "Elastic Coefficient: %.2e /um.%n"
            + "Density: %.2f g/ml.%n",
        absCoeff, attCoeff, elasCoeff, density);
  }

  /**
   * Override the default search locations of legacy RADDOSE executable.
   *
   * @param pathToExecutable
   *          Complete path to the executable of a legacy RADDOSE version.
   */
  public static final void setRADDOSEExecutable(final String pathToExecutable) {
    raddosePathOverride = pathToExecutable;
  }
}
