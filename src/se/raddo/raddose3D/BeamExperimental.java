package se.raddo.raddose3D;

/**
 * Takes an experimental grid of beam intensities, and interpolates for
 * the correct intensity using Bilinear interpolation.
 */
public class BeamExperimental implements Beam {
  private final Double     pixXSize,
                           beamXSize,
                           pixYSize,
                           beamYSize,
                           totalFlux,
                           beamEnergy,
                           pulseEnergy,
                           energyFWHM;
  
  private double           beamSum,
                           attenuatedFlux;


  private double[][] beamArray;
  double[][] beamArrayWithBorders;
  private final Double[][] dataStructure;
  
  public boolean isCircular;

  /**
   * The aperture's full widths along x and y, in micrometres, or null for
   * none. The aperture clips the measured profile, and the flux -- which the
   * user guide defines as the flux after collimation -- is spread over the
   * part of the profile it lets through.
   */
  private Double collXum, collYum;
  

  /**
   * This takes the arguments, and generates the beamArray double[][] which
   * will be used for interpolation. This array must be in the RADDOSE-3D
   * coordinate system. It also adds a row or 0's around the
   * original array to allow for correct interpolation at the edges.
   *
   * @param totalFlux
   *          total flux of the beam in photons per second.
   * @param datastructure
   *          a 2D grid (image) of the beam. Must be 'true',
   *          i.e. any deconvolution of point spread functions or other
   *          pre-processing must be done upstream.
   * @param beamEnergy
   *          photon energy in keV
   * @param pixelSizeX
   *          the horizontal size of the pixels in micrometres
   * @param pixelSizeY
   *          the vertical size of the pixels in micrometres
   */
  public BeamExperimental(final Double[][] datastructure,
      final Double totalFlux, final Double beamEnergy, 
      final Double pixelSizeX,
      final Double pixelSizeY,final Double pulseEnergy,
      final Double energyFWHM) {

    
    this.dataStructure = datastructure;
    this.beamXSize = (datastructure[0].length + 2) * pixelSizeX;
    this.beamYSize = (datastructure.length + 2) * pixelSizeY;
    this.pixXSize = pixelSizeX;
    this.pixYSize = pixelSizeY;
    this.totalFlux = totalFlux;
    this.beamEnergy = beamEnergy;

    this.pulseEnergy = pulseEnergy;

    this.energyFWHM = energyFWHM;
    
  }

  /**
   * Sets the aperture in front of the measured profile.
   * <p>
   * It used to be ignored: Collimation Circular clipped the profile to the
   * ellipse inscribed in the whole image whatever sizes it gave, and any
   * other collimation did nothing.
   *
   * @param collX full width along x in micrometres, or null for none
   * @param collY full width along y in micrometres, or null for none
   * @param circular whether the aperture is elliptical
   */
  public void setCollimation(final Double collX, final Double collY,
      final boolean circular) {
    if ((collX == null) != (collY == null)) {
      throw new IllegalArgumentException(
          "An experimental beam's collimation needs both sizes.");
    }
    collXum = collX;
    collYum = collY;
    isCircular = circular;
  }

  /**
   * Whether a point, relative to the beam axis, passes the aperture.
   *
   * @param x micrometres along x
   * @param y micrometres along y
   * @return true if there is no aperture or the point is inside it
   */
  private boolean insideAperture(final double x, final double y) {
    if (collXum == null) {
      return true;
    }
    if (isCircular) {
      return (Math.pow(x, 2) / Math.pow(collXum / 2, 2))
          + (Math.pow(y, 2) / Math.pow(collYum / 2, 2)) <= 1;
    }
    return Math.abs(x) <= collXum / 2 && Math.abs(y) <= collYum / 2;
  }

  /**
   * Generate the beam array from the other instance variables
   */
  @Override
  public void generateBeamArray() {     //This has been added as a new method so is no longer automatically called by BeamExperimental
    //set beam sum to zero
    beamSum = 0;
    // add a zero border
    int sizeHoriz = dataStructure[0].length;
    int sizeVert = dataStructure.length;
    beamArrayWithBorders = new double[sizeVert + 2][sizeHoriz + 2];

    for (int i = 0; i < sizeVert + 2; i++) {
      for (int j = 0; j < sizeHoriz + 2; j++) {
        if (i == 0 || j == 0 || i == sizeVert + 1 || j == sizeHoriz + 1) {
          beamArrayWithBorders[i][j] = 0;
        } else {
          beamArrayWithBorders[i][j] = dataStructure[i - 1][j - 1];
          // Only the pixels the aperture lets through carry the flux.
          double x = (j + 0.5) * pixXSize - beamXSize / 2;
          double y = (i + 0.5) * pixYSize - beamYSize / 2;
          if (insideAperture(x, y)) {
            beamSum += dataStructure[i - 1][j - 1];
          }
        }
      }
    }
    if (beamSum <= 0) {
      throw new IllegalArgumentException(
          "The experimental beam's collimation lets none of its image "
              + "through.");
    }

    // Dividing by beamSum gives normalised flux/pixelsize
    for (int i = 0; i < sizeVert + 2; i++) {
      for (int j = 0; j < sizeHoriz + 2; j++) {
        beamArrayWithBorders[i][j] = beamArrayWithBorders[i][j]
            * KEVTOJOULES * this.beamEnergy
            * this.attenuatedFlux
            / (beamSum * pixXSize * pixYSize);
      }
    }
    this.beamArray = beamArrayWithBorders;
  }

  /**
   * This uses bilinear interpolation to return beam intensity at
   * coorX, coordY.
   */
  @Override
  public double beamIntensity(final double coordX, final double coordY,
      final double offAxisUM) {
    beamArray = beamArrayWithBorders;
    if (Math.abs(coordX - offAxisUM) <= beamXSize / 2 - pixXSize
        && Math.abs(coordY) <= beamYSize / 2 - pixYSize
        && insideAperture(coordX - offAxisUM, coordY)) {
      return bilinearInterpolation(coordX, coordY, offAxisUM);
    }
    return 0d;
  }
  
  private double bilinearInterpolation(final double coordX, final double coordY,
      final double offAxisUM) {
    double realX = (coordX - offAxisUM + beamXSize / 2);
    double realY = (coordY + beamYSize / 2);
    int voxelHorizontal = (int) Math.floor(realX / pixXSize - 0.5);
    int voxelVertical = (int) Math.floor(realY / pixYSize - 0.5);
    if (voxelHorizontal < beamArray[0].length     //null pointer exception here
        && voxelVertical < beamArray.length) {
      float fracX = (float) (realX / pixXSize - (voxelHorizontal + 0.5));
      float fracY = (float) (realY / pixYSize - (voxelVertical + 0.5));

      return bilinearInterpolate(beamArray[voxelVertical][voxelHorizontal],
          beamArray[voxelVertical][voxelHorizontal + 1],
          beamArray[voxelVertical + 1][voxelHorizontal],
          beamArray[voxelVertical + 1][voxelHorizontal + 1], fracX,
          fracY);

    } else if (voxelHorizontal == beamArray[0].length
        || voxelVertical == beamArray.length) {
      return beamArray[voxelVertical][voxelHorizontal];

    } else {
      throw new IllegalArgumentException(
          "Image voxel request out of experimental profile");
    }
  }
  

  /**
   * Bilinear interpolation routine.
   *
   * @param v00
   *          Value at x=0, y=0.
   * @param v10
   *          Value at x=1, y=0.
   * @param v01
   *          Value at x=0, y=1.
   * @param v11
   *          Value at x=1, y=1.
   * @param x
   *          x position between 0 and 1.
   * @param y
   *          y position between 0 and 1.
   * @return
   *         Bilinearly interpolated value for coordinates x, y.
   */
  public static double bilinearInterpolate(final double v00, final double v10,
      final double v01, final double v11, final double x, final double y) {
    return v00 * (1 - x) * (1 - y)
        + v10 * (x) * (1 - y)
        + v01 * (1 - x) * (y)
        + v11 * (x) * (y);
  }

  @Override
  public String getDescription() {
    return "Experimental beam profile used";
  }

  @Override
  public double getPhotonsPerSec() {
    return totalFlux;
  }

  @Override
  public double getPhotonEnergy() {
    return beamEnergy;
  }
  
  @Override
  public Double getEnergyFWHM() {
    return energyFWHM;
  }

  
  @Override
  public void applyContainerAttenuation(Container sampleContainer){
    this.attenuatedFlux = (1 - sampleContainer.getContainerAttenuationFraction())
        * this.totalFlux;

    if (sampleContainer.getContainerMaterial() != null) {
      String s = String.format("Beam photons per second after container "
          + "attenuation is %.2e photons per second", this.attenuatedFlux);

      System.out.println(s);
    }
  }
  
  @Override
  public double beamMinumumDimension() {
    return Math.min(beamXSize, beamYSize);
  }

  @Override
  public double getBeamArea() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getExposure() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Double getBeamX() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Double getBeamY() {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public String getType() {
    return "Experimental";
  }

  @Override
  public boolean getIsCircular() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public double getSemiAngle() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getApertureRadius() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getImageX() {
    // TODO Auto-generated method stub
    return 0;
  }
  @Override
  public double getImageY() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getPulseEnergy() {
    return pulseEnergy;
  }

  @Override
  public void setPhotonsPerfs(double photonsPerfs) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public double getSx() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getSy() {
    // TODO Auto-generated method stub
    return 0;
  }
}
