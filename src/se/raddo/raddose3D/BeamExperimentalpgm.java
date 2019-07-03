package se.raddo.raddose3D;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

// The javadoc comment that should go here should point out that this
// class is called BeamExperimentalPGM, but due to the BeamFactory lookup
// algorithm PGM is written in lower case.

/**
 * Class to input PGM files taken with an in-line camera facing the beam.
 */
public class BeamExperimentalpgm extends BeamExperimental {

  /**
   * Name of the file containing the PGM image of the beam.
   */
  private final String file;

  /**
   * Generic property constructor for ExperimentalPGM beams. Extracts all
   * required
   * information from a Map data structure.
   * 
   * @param properties
   *          Map of type <Object, Object> that contains all beam properties.
   *          The keys of the Map are defined by the constants in the
   *          {@link Beam} class.
   */
  public BeamExperimentalpgm(final Map<Object, Object> properties) {
    // Call to the super constructor must be the first instruction.
    // Thus all file operations must go into a static function.
    // only one pixel size since PGM has implicit square pixel geometry

    super(getXYIntensityList((String) properties.get(Beam.BEAM_EXTFILE)),
        (Double) properties.get(Beam.BEAM_FLUX),
        (Double) properties.get(Beam.BEAM_ENERGY),
        (Double) properties.get(Beam.BEAM_PIXSIZE_X),
        (Double) properties.get(Beam.BEAM_PIXSIZE_Y),
        (Double) properties.get(Beam.PULSE_ENERGY),
        (Double) properties.get(Beam.ENERGY_FWHM));
    file = (String) properties.get(Beam.BEAM_EXTFILE);
    
    if (properties.get(Beam.BEAM_CIRCULAR) == "TRUE") {
      isCircular = true;
    }
    else {
      isCircular = false;
    }
  }

  @Override
  public String getDescription() {
    return String
        .format(
            "Experimental beam profile taken from %s. Beam has"
                + " %.1e photons per second at %.2f keV.%n",
            file, getPhotonsPerSec(), getPhotonEnergy());
  }

  /**
   * Parses a PGM file to an array, assuming a single comment line.
   * CCD face normal is anti-parallel to the Z axis.
   * .pgm files must have magic number P2, a single comment line, two integers
   * describing the X and Y size of the image in the lab frame*, then an integer
   * describing the dynamic range of the .pgm file followed by the data in
   * row-column order.
   * *lab frame:
   * X increase left-right antiparallel to goniometer axis (RD3D y-axis)
   * Y increases top to bottom of the .gpm antiparallel to the RD3D x axis
   * 
   * @param filename
   *          path to input PGM file.
   * @return
   *         array of PGM intensities.
   */
  private static Double[][] getXYIntensityList(final String filename) {
    if ((filename == null) || (filename.equals(""))) {
      throw new IllegalArgumentException("No filename given");
    }

    FileInputStream fileInputStream;
    try {
      fileInputStream = new FileInputStream(filename);
    } catch (IOException e) {
      throw new IllegalArgumentException("File " + filename
          + " could not be opened.", e);
    }
    //        return new BeamExperimentalPGM(properties);

    Scanner scan = new Scanner(fileInputStream);
    // Discard the magic number
    scan.nextLine();

    // Discard the comment line
    scan.nextLine(); //TODO make a test to see if this is there
    // Read pic width, height and max value
    int picWidth = scan.nextInt();
    int picHeight = scan.nextInt();
    // skip maxValue line
    scan.nextInt();

    Double[][] pgmData = new Double[picWidth][picHeight];

    //because the image scans from bottom up
    for (int row = picHeight - 1; row >= 0; row--) {
      // for (int col = 0; col < picWidth; col++) {
      for (int col = picWidth - 1; col >= 0; col--) {
        pgmData[col][row] = scan.nextDouble();
        //System.out.print(pgmData[row][col] + " ");
      }
    }
    scan.close();

    try {
      fileInputStream.close();
    } catch (IOException e) {
      throw new IllegalStateException("File " + filename
          + " could not be closed.", e);
    }

    return pgmData;
  }

}
