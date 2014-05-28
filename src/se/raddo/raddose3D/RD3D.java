package se.raddo.raddose3D;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * RADDOSE-3D main class for command line invocation.
 */
public final class RD3D {
  /** Conversion factor for ns/s. */
  private static final long NANOSECONDSPERSECOND = 1000000000L;
  /** List of prepared input modules for the simulation. */
  private List<Input>       inputs               = new ArrayList<Input>();
  /** List of prepared output modules for the simulation. */
  private List<Output>      outputs              = new ArrayList<Output>();
  /**
   * Experiment class for the simulation.
   * If set to null then no simulation is run.
   */
  private Experiment        exp                  = new Experiment();
  /** Common prefix for output files. */
  private String            prefix               = "output-";

  /**
   * Private class constructor. Only the class itself needs to instantiate it.
   * 
   * @param args
   *          Command line parameters to be parsed.
   */
  private RD3D(final String[] args) {
    // Display help and exit if called without command line arguments
    if (args.length < 1) {
      exp = null;
      printCommandlineHelp();
    } else {
      // Check for high priority command line parameters.
      // This may change the default prefix.
      if (parseHighPriorityParameters(args)) {
        // Now parse all command line options.
        parseCommandLineParameters(args);

        // Did the user select specific output modules? If not, set defaults.
        if (outputs.isEmpty()) {
          setDefaultObservers();
        }

      } else {
        exp = null;
      }
    }
  }

  /**
   * static main function, called externally.
   * 
   * @param cmdLineParams
   *          Array of strings containing command line parameters.
   */
  public static void main(final String[] cmdLineParams) {
    RD3D raddose = new RD3D(cmdLineParams);

    if (raddose.runExperiment()) {
      System.out.println(String.format(
          "RADDOSE-3D terminated after %.1f seconds",
          (double) ManagementFactory.getThreadMXBean()
              .getCurrentThreadUserTime() / NANOSECONDSPERSECOND));
    }
  }

  /**
   * Passes inputs and outputs to the prepared Experiment class and runs the
   * experiment.
   * Closes down and destroys all references afterwards.
   * 
   * @return
   *         Returns true if there was a defined experiment, false if there was
   *         no experiment defined (ie. not enough input to create an
   *         experiment).
   */
  private boolean runExperiment() {
    if (exp == null) {
      return false;
    }

    // Add outputs to experiment.
    for (Output o : outputs) {
      exp.addObserver(o);
    }
    outputs = null;

    // Process all inputs.
    for (Input i : inputs) {
      try {
        exp.process(i);
      } catch (InputException e) {
        System.err.println("Unhandled input exception " + e);
      }
    }
    inputs = null;

    // Clean up.
    exp.close();
    exp = null;

    return true;
  }

  /**
   * Check command line for high priority parameters.
   * Parameters such as -V, -? or -p affect all others, and must therefore be
   * parsed first.
   * 
   * @param cmdLineParams
   *          Array of command line parameters.
   */
  @SuppressWarnings("PMD.CyclomaticComplexity")
  private boolean parseHighPriorityParameters(final String[] cmdLineParams) {
    for (int i = 0; i < cmdLineParams.length; i++) {

      // Comparisons here are case sensitive
      String command = cmdLineParams[i];

      if ("-V".equals(command) || "--version".equalsIgnoreCase(command)) {
        Version.printVersionInformation();
        return false;

      } else if ("-?".equals(command) || "/?".equals(command)
          || "--help".equalsIgnoreCase(command)) {
        printCommandlineHelp();
        return false;

      } else if ("-p".equalsIgnoreCase(command)
          || "--prefix".equalsIgnoreCase(command)) {
        if ((i + 1) >= cmdLineParams.length) {
          System.err.println("No output prefix given");
        } else {
          prefix = cmdLineParams[++i];
          System.out.println("Output file prefix set to " + prefix);
        }
      }
    }
    return true;
  }

  /**
   * Parse all regular command line parameters.
   * Populates the two lists inputs and outputs.
   * 
   * @param cmdLineParams
   *          Array of command line parameters.
   */
  @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
  private void parseCommandLineParameters(final String[] cmdLineParams) {
    OutputFactory of = new OutputFactory();

    String command;
    for (int i = 0; i < cmdLineParams.length; i++) {
      command = cmdLineParams[i].toLowerCase();

      if ("-i".equals(command) || "--in".equals(command)) {
        if ((i + 1) >= cmdLineParams.length) {
          System.err.println("No input filename given");
        } else {
          i++;
          if (cmdLineParams[i].equals("-")) {
            System.out.println("Read from console");
            try {
              inputs.add(new InputParserConsole());
            } catch (IOException e) {
              System.err.println("Could not read from console");
            }
          } else {
            System.out.println("Load File: " + cmdLineParams[i]);
            try {
              inputs.add(new InputParserFile(cmdLineParams[i]));
            } catch (IOException e) {
              System.err.println("Could not read from file '"
                  + cmdLineParams[i] + "'");
            }
          }
        }

      } else if ("-p".equals(command) || "--prefix".equals(command)) {
        if ((i + 1) < cmdLineParams.length) {
          i++;
          // prefix is assigned as a priority parameter,
          // so that it can affect earlier -o commands
        }

      } else if ("-t".equals(command) || "--test".equals(command)) {
        System.out.println("Test run. No actual calculations will take place.");
        exp = new ExperimentDummy();

      } else if ("-r".equals(command) || "--raddose".equals(command)) {
        if ((i + 1) >= cmdLineParams.length) {
          System.err.println("No path to raddose executable given");
        } else {
          i++;
          CoefCalcRaddose.setRADDOSEExecutable(cmdLineParams[i]);
          System.out.println("raddose executable set to "
              + cmdLineParams[i]);
        }

      } else if ("-o".equals(command) || "--out".equals(command)) {
        //        module[:parameters]:dest[:dest[..]]
        if ((i + 1) >= cmdLineParams.length) {
          System.err.println("No custom output specification given");
        } else {
          i++;

          String[] specification = cmdLineParams[i].split(":");
          if (specification.length < 2) {
            System.err.println("Invalid output specification: "
                + cmdLineParams[i]);
          } else {
            Writer w;
            w = parseOutputDestinations(specification[specification.length - 1]
                .split(","));
            if (w == null) {
              System.err.println("No valid output specified in: "
                  + cmdLineParams[i]);
            } else {

              String module = specification[0];
              /* Unused so far: */
              // String[] parameters = Arrays.copyOfRange(specification, 1,
              //    specification.length - 1);

              HashMap<Object, Object> properties =
                  new HashMap<Object, Object>();
              properties.put(Output.OUTPUT_WRITER, w);

              Output observer = of.createOutput(module, properties);
              outputs.add(observer);
            }
          }
        }

      } else {
        System.err.println("Unparsed command line argument: "
            + cmdLineParams[i]);
      }
    }
  }

  /**
   * Set default output modules.
   */
  private void setDefaultObservers() {
    System.out.println("No output specifications given. Using defaults.");

    OutputFactory of = new OutputFactory();

    try {
      Writer w = new WriterFile(prefix + "Summary.csv");
      outputs.add(of.createOutputSimple("SummaryCSV", w));
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Could not initialize OutputSummaryCSV");
    }

    try {
      Writer w = new WriterMultiple(Arrays.asList(
          new WriterConsole(), new WriterFile(prefix + "Summary.txt")));
      outputs.add(of.createOutputSimple("SummaryText", w));
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Could not initialize OutputSummaryText");
    }

    try {
      Writer w = new WriterFile(prefix + "DoseState.csv");
      outputs.add(of.createOutputSimple("FinalDoseStateCSV", w));
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Could not initialize OutputFinalDoseStateCSV");
    }

    try {
      Writer w = new WriterFile(prefix + "DoseState.R");
      outputs.add(of.createOutputSimple("FinalDoseStateR", w));
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Could not initialize OutputDoseStateR");
    }

    Writer w = new WriterConsole();
    outputs.add(of.createOutputSimple("ProgressIndicator", w));
  }

  /**
   * writes basic command line usage instructions to STDOUT.
   */
  private static void printCommandlineHelp() {
    System.out.println("raddose   --   command line options  ("
        + Version.VERSION_STRING + ")");
    System.out
        .println("==========================================================");
    System.out.println(" -?   or --help         show command line help (this)");
    System.out.println(" -V   or --version      show version information");
    System.out.println();
    System.out.println(" -i   or --in filename  read instructions from file");
    System.out.println(" -i - or --in -         read instructions from STDIN");
    System.out.println();
    System.out.println(" -p   or --prefix name  prefix for output files");
    System.out.println(" -r   or --raddose path path to RaddoseV3 executable");
    System.out.println(" -t   or --test         test run with no simulation");
    System.out.println();
    System.out.println(" -o   or --out <output> for user-defined output");
    // System.out.println("      see manual or use -o? for complete syntax");
    //        module[:param[:param[..]]]:dest[,dest[..]]
    //        param can be anything (not containing :)
    //        dest can be anything (not containing : or ,)
    //        dest = '-'  => STDOUT
    System.out.println("     Default output corresponds to");
    System.out.println("       -o SummaryCSV:SummaryCSV.csv");
    System.out.println("       -o Summary:Summary.txt,-");
    System.out.println("       -o DoseStateCSV:DoseState.csv");

    System.out.println();
    System.out
        .println("==========================================================");
    System.out.println("Please cite:");
    System.out.println(" Zeldin, Gerstel, Garman. (2013). J. Appl. Cryst. 46,"
        + " 1225-1230.");
    System.out.println(" http://dx.doi.org/10.1107/S0021889813011461");
  }

  /**
   * Creates an instance of Writer that writes to specified destinations.
   * Destinations can be '-' for STDOUT or filenames.
   * 
   * @param destinations
   *          Array of output specifications.
   * @return
   *         A single Writer that writes to all valid destinations, or null if
   *         no valid destination was given.
   */
  @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
  private Writer parseOutputDestinations(final String[] destinations) {
    ArrayList<Writer> writers = new ArrayList<Writer>();

    for (String destination : destinations) {
      if ("-".equalsIgnoreCase(destination)) {
        writers.add(new WriterConsole());
      } else {
        String filename = prefix.concat(destination);
        try {
          writers.add(new WriterFile(filename));
        } catch (IOException e) {
          System.err.println("Could not open file " + filename
              + " for writing.");
        }
      }
    }

    if (writers.size() > 1) {
      return new WriterMultiple(writers);
    }
    if (writers.size() == 1) {
      return writers.get(0);
    }
    return null;
  }
}
