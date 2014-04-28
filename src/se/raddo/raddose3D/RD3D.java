package se.raddo.raddose3D;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * RADDOSE-3D main class for command line invocation.
 * 
 * @author Markus Gerstel
 */
public final class RD3D {
  /** Conversion factor for ns/s. */
  private static final long NANOSECONDSPERSECOND = 1000000000L;
  /** Array of command line parameters. */
  private final String[]    commandLineParams;
  /** List of prepared input modules for the simulation. */
  private ArrayList<Input>  inputs               = new ArrayList<Input>();
  /** List of prepared output modules for the simulation. */
  private ArrayList<Output> outputs              = new ArrayList<Output>();
  /** Experiment class for the simulation. */
  private Experiment        exp                  = new Experiment();
  /** Common prefix for output files. */
  private String            prefix;

  /**
   * Private class constructor. Only the class itself needs to instantiate it.
   * 
   * @param args
   *          Command line parameters to be parsed.
   */
  private RD3D(final String[] args) {
    commandLineParams = args;
  }

  /**
   * static main function, called externally.
   * 
   * @param args
   *          Array of strings containing command line parameters.
   */
  public static void main(final String[] args) {
    // Display help and exit if called without command line arguments
    if (args.length < 1) {
      printCommandlineHelp();
      System.exit(0);
    }

    RD3D raddose = new RD3D(args);

    // Check for high priority command line parameters.
    raddose.parseHighPriorityParameters();

    // Unless a custom prefix has been set, set default prefix.
    if (raddose.prefix == null) {
      System.out.println("Output files are prefixed with 'output-'");
      raddose.prefix = "output-";
    }

    // Now parse all command line options.
    raddose.parseCommandLineParameters();

    // Did the user select specific output modules? If not, set defaults.
    if (raddose.outputs.isEmpty()) {
      raddose.setDefaultObservers();
    }

    raddose.runExperiment();

    System.out.println("RADDOSE-3D terminated after "
        + String.format("%.2f", (double) ManagementFactory.getThreadMXBean()
            .getCurrentThreadUserTime() / NANOSECONDSPERSECOND)
        + " seconds");
  }

  /**
   * Passes inputs and outputs to the prepared Experiment class and runs the
   * experiment.
   * Closes down and destroys all references afterwards.
   */
  private void runExperiment() {
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
  }

  /**
   * Check command line for high priority parameters.
   * Parameters such as -V, -? or -p affect all others, and must therefore be
   * parsed first.
   */
  private void parseHighPriorityParameters() {
    for (int i = 0; i < commandLineParams.length; i++) {

      // Comparisons here are case sensitive
      String command = commandLineParams[i];

      if ("-V".equals(command) || "--version".equalsIgnoreCase(command)) {
        Version.printVersionInformation();
        System.exit(0);

      } else if ("-?".equals(command) || "/?".equals(command)
          || "--help".equalsIgnoreCase(command)) {
        printCommandlineHelp();
        System.exit(0);

      } else if ("-p".equalsIgnoreCase(command)
          || "--prefix".equalsIgnoreCase(command)) {
        if ((i + 1) >= commandLineParams.length) {
          System.err.println("No output prefix given");
        } else {
          prefix = commandLineParams[++i];
          System.out.println("Output file prefix set to " + prefix);
        }
      }
    }
  }

  /**
   * Parse all regular command line parameters.
   * Populates the two Vectors inputs and outputs.
   */
  private void parseCommandLineParameters() {
    OutputFactory of = new OutputFactory();

    for (int i = 0; i < commandLineParams.length; i++) {
      String command = commandLineParams[i].toLowerCase();

      if (command.equals("-i") || command.equals("--in")) {
        if ((i + 1) >= commandLineParams.length) {
          System.err.println("No input filename given");
        } else {
          i++;
          if (commandLineParams[i].equals("-")) {
            System.out.println("Read from console");
            try {
              inputs.add(new InputParserConsole());
            } catch (IOException e) {
              System.err.println("Could not read from console");
            }
          } else {
            System.out.println("Load File: " + commandLineParams[i]);
            try {
              inputs.add(new InputParserFile(commandLineParams[i]));
            } catch (IOException e) {
              System.err.println("Could not read from file '"
                  + commandLineParams[i] + "'");
            }
          }
        }

      } else if (command.equals("-p") || command.equals("--prefix")) {
        if ((i + 1) < commandLineParams.length) {
          i++;
          // prefix is assigned as a priority parameter,
          // so that it can affect earlier -o commands
        }

      } else if (command.equals("-t") || command.equals("--test")) {
        System.out.println("Test run. No actual calculations will take place.");
        exp = new ExperimentDummy();

      } else if (command.equals("-r") || command.equals("--raddose")) {
        if ((i + 1) >= commandLineParams.length) {
          System.err.println("No path to raddose executable given");
        } else {
          i++;
          CoefCalcRaddose.setRADDOSEExecutable(commandLineParams[i]);
          System.out.println("raddose executable set to "
              + commandLineParams[i]);
        }

      } else if (command.equals("-o") || command.equals("--out")) {
        //        module[:parameters]:dest[:dest[..]]
        if ((i + 1) >= commandLineParams.length) {
          System.err.println("No custom output specification given");
        } else {
          i++;

          String[] specification = commandLineParams[i].split(":");
          if (specification.length < 2) {
            System.err.println("Invalid output specification: "
                + commandLineParams[i]);
          } else {
            Writer w;
            w = parseOutputDestinations(specification[specification.length - 1]
                .split(","));
            if (w == null) {
              System.err.println("No valid output specified in: "
                  + commandLineParams[i]);
            } else {

              String module = specification[0];
              /* Unused so far: */
              // String[] parameters = Arrays.copyOfRange(specification, 1,
              //    specification.length - 1);

              HashMap<Object, Object> properties =
                  new HashMap<Object, Object>();
              properties.put(Output.OUTPUT_WRITER, w);

              try {
                Output observer = of.createOutput(module, properties);
                outputs.add(observer);
              } catch (RuntimeException r) {
                System.err.println("Unknown output specification "
                    + specification[0] + ":\n " + r.getMessage());
              }
            }
          }
        }

      } else {
        System.err.println("Unparsed command line argument: "
            + commandLineParams[i]);
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
      Writer w = new WriterFile(prefix + "FluencePerDoseHistCSV.csv");
      outputs.add(of.createOutputSimple("FluencePerDoseHistCSV", w));
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Could not initialize OutputFluencePerDoseHistCSV");
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
        + Version.VERSION + ")");
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
    System.out.println("       -o fluencehist:FluencePerDoseHistCSV.csv");

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
