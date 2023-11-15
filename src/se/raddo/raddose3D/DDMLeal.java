package se.raddo.raddose3D;

/**
 * This is a Dose Decay Model class that calculates the Relative
 * Diffraction Efficiency (RDE) according to the model from the
 * Leal et al. (2012) paper. The paper describes the loss of
 * scattering power of a crystal as a product of the expected
 * intensity, the Debye-waller factor and an empirically derived
 * scale factor.
 */
public class DDMLeal implements DDM {

  /**
   * Decay parameters used in Leal et al. (2012) (eqn 4).
   */

  /**
   * Decay Parameter beta.
   */
  private final double            beta;

  /**
   * Decay Parameter b0.
   */
  private final double            b0;

  /**
   * Decay Parameter gamma.
   */
  private final double            gamma;

  /**
   * Array that stores the BEST intensity data
   * column 1 - h^2 values. Where h = 1/d and d is
   * the spacing between successive Bragg planes (i.e. resolution)
   * column 2 - J values. These are expected intensity values
   */
  private static final double[][] BEST_DATA = new double[][] {
                                            { 0.009000, 117970.000000 },
                                            { 0.013100, 100512.023438 },
                                            { 0.017200, 80882.992188 },
                                            { 0.021300, 62948.515625 },
                                            { 0.025400, 57507.757813 },
                                            { 0.029500, 61357.429688 },
                                            { 0.033500, 72234.062500 },
                                            { 0.037600, 89858.945313 },
                                            { 0.041700, 109460.929688 },
                                            { 0.045800, 126917.039063 },
                                            { 0.049900, 137405.062500 },
                                            { 0.054000, 139655.375000 },
                                            { 0.058100, 137483.218750 },
                                            { 0.062200, 133394.875000 },
                                            { 0.066300, 129394.328125 },
                                            { 0.070400, 125762.617188 },
                                            { 0.074500, 121035.289063 },
                                            { 0.078600, 116051.804688 },
                                            { 0.082600, 110836.078125 },
                                            { 0.086700, 104613.296875 },
                                            { 0.090800, 97322.054688 },
                                            { 0.094900, 89836.304688 },
                                            { 0.099000, 83216.187500 },
                                            { 0.103100, 78146.273438 },
                                            { 0.107200, 73459.671875 },
                                            { 0.111300, 69471.023438 },
                                            { 0.115400, 65299.644531 },
                                            { 0.119500, 61581.441406 },
                                            { 0.123600, 58510.613281 },
                                            { 0.127700, 55865.179688 },
                                            { 0.131800, 53658.789063 },
                                            { 0.135800, 52101.019531 },
                                            { 0.139900, 51070.417969 },
                                            { 0.144000, 50092.042969 },
                                            { 0.148100, 49350.722656 },
                                            { 0.152200, 48151.910156 },
                                            { 0.156300, 47058.906250 },
                                            { 0.160400, 46675.406250 },
                                            { 0.164500, 46597.675781 },
                                            { 0.168600, 45924.046875 },
                                            { 0.172700, 46080.671875 },
                                            { 0.176800, 45937.621094 },
                                            { 0.180900, 46096.023438 },
                                            { 0.184900, 45896.964844 },
                                            { 0.189000, 45990.093750 },
                                            { 0.193100, 46123.292969 },
                                            { 0.197200, 46343.515625 },
                                            { 0.201300, 45936.539063 },
                                            { 0.205400, 45715.695313 },
                                            { 0.209500, 45109.164063 },
                                            { 0.213600, 44549.132813 },
                                            { 0.217700, 43634.820313 },
                                            { 0.221800, 43566.085938 },
                                            { 0.225900, 43451.015625 },
                                            { 0.230000, 42696.292969 },
                                            { 0.234100, 41173.980469 },
                                            { 0.238100, 39972.753906 },
                                            { 0.242200, 39166.628906 },
                                            { 0.246300, 38020.367188 },
                                            { 0.250400, 36810.992188 },
                                            { 0.254500, 35497.308594 },
                                            { 0.258600, 34194.906250 },
                                            { 0.262700, 32992.742188 },
                                            { 0.266800, 31585.996094 },
                                            { 0.270900, 30211.492188 },
                                            { 0.275000, 29119.816406 },
                                            { 0.279100, 28151.564453 },
                                            { 0.283200, 27386.414063 },
                                            { 0.287300, 26232.775391 },
                                            { 0.291300, 25235.693359 },
                                            { 0.295400, 24318.244141 },
                                            { 0.299500, 23707.949219 },
                                            { 0.303600, 22821.910156 },
                                            { 0.307700, 22182.095703 },
                                            { 0.311800, 21694.740234 },
                                            { 0.315900, 21236.888672 },
                                            { 0.320000, 20733.123047 },
                                            { 0.324100, 20323.289063 },
                                            { 0.328200, 20073.404297 },
                                            { 0.332300, 19932.156250 },
                                            { 0.336400, 19631.480469 },
                                            { 0.340400, 19223.189453 },
                                            { 0.344500, 18920.273438 },
                                            { 0.348600, 18557.662109 },
                                            { 0.352700, 18134.789063 },
                                            { 0.356800, 17926.917969 },
                                            { 0.360900, 17909.144531 },
                                            { 0.365000, 17908.371094 },
                                            { 0.369100, 17781.652344 },
                                            { 0.373200, 17634.251953 },
                                            { 0.377300, 17607.757813 },
                                            { 0.381400, 17273.970703 },
                                            { 0.385500, 17132.121094 },
                                            { 0.389600, 16953.238281 },
                                            { 0.393600, 16883.560547 },
                                            { 0.397700, 16615.091797 },
                                            { 0.401800, 16435.376953 },
                                            { 0.405900, 16423.140625 },
                                            { 0.410000, 16351.833008 },
                                            { 0.414100, 16278.805664 },
                                            { 0.418200, 15998.300781 },
                                            { 0.422300, 15795.753906 },
                                            { 0.426400, 15589.185547 },
                                            { 0.430500, 15561.383789 },
                                            { 0.434600, 15467.072266 },
                                            { 0.438700, 15476.588867 },
                                            { 0.442800, 15331.998047 },
                                            { 0.446800, 15028.963867 },
                                            { 0.450900, 14745.987305 },
                                            { 0.455000, 14509.141602 },
                                            { 0.459100, 14445.925781 },
                                            { 0.463200, 14254.642578 },
                                            { 0.467300, 14111.920898 },
                                            { 0.471400, 13900.478516 },
                                            { 0.475500, 13785.526367 },
                                            { 0.479600, 13686.092773 },
                                            { 0.483700, 13464.845703 },
                                            { 0.487800, 13304.157227 },
                                            { 0.491900, 13084.092773 },
                                            { 0.495900, 13114.880859 },
                                            { 0.500000, 13089.595703 },
                                            { 0.504100, 13244.094727 },
                                            { 0.508200, 13117.398438 },
                                            { 0.512300, 13140.625977 },
                                            { 0.516400, 13031.726563 },
                                            { 0.520500, 12999.481445 },
                                            { 0.524600, 12835.458008 },
                                            { 0.528700, 12954.440430 },
                                            { 0.532800, 12937.747070 },
                                            { 0.536900, 12936.303711 },
                                            { 0.541000, 12825.827148 },
                                            { 0.545100, 12995.077148 },
                                            { 0.549100, 12994.031250 },
                                            { 0.553200, 13036.256836 },
                                            { 0.557300, 13006.765625 },
                                            { 0.561400, 13057.585938 },
                                            { 0.565500, 13010.015625 },
                                            { 0.569600, 12891.707031 },
                                            { 0.573700, 12966.081055 },
                                            { 0.577800, 13114.422852 },
                                            { 0.581900, 13119.473633 },
                                            { 0.586000, 13065.753906 },
                                            { 0.590100, 13052.747070 },
                                            { 0.594200, 13214.619141 },
                                            { 0.598200, 13376.884766 },
                                            { 0.602300, 13386.037109 },
                                            { 0.606400, 13244.183594 },
                                            { 0.610500, 13225.625000 },
                                            { 0.614600, 13203.177734 },
                                            { 0.618700, 13157.918945 },
                                            { 0.622800, 13058.344727 },
                                            { 0.626900, 13089.546875 },
                                            { 0.631000, 13236.269531 },
                                            { 0.635100, 13356.927734 },
                                            { 0.639200, 13294.084961 },
                                            { 0.643300, 13322.505859 },
                                            { 0.647400, 13356.877930 },
                                            { 0.651400, 13574.700195 },
                                            { 0.655500, 13741.788086 },
                                            { 0.659600, 13988.012695 },
                                            { 0.663700, 14126.933594 },
                                            { 0.667800, 14226.778320 },
                                            { 0.671900, 14096.913086 },
                                            { 0.676000, 14083.927734 },
                                            { 0.680100, 14170.342773 },
                                            { 0.684200, 14351.646484 },
                                            { 0.688300, 14494.584961 },
                                            { 0.692400, 14485.082031 },
                                            { 0.696500, 14514.433594 },
                                            { 0.700600, 14622.690430 },
                                            { 0.704600, 14725.596680 },
                                            { 0.708700, 14840.912109 },
                                            { 0.712800, 14869.136719 },
                                            { 0.716900, 14947.928711 },
                                            { 0.721000, 15039.328125 },
                                            { 0.725100, 15069.899414 },
                                            { 0.729200, 15058.230469 },
                                            { 0.733300, 14892.115234 },
                                            { 0.737400, 14829.183594 },
                                            { 0.741500, 14854.609375 },
                                            { 0.745600, 14911.042969 },
                                            { 0.749700, 14950.721680 },
                                            { 0.753700, 15113.783203 },
                                            { 0.757800, 15211.773438 },
                                            { 0.761900, 15205.695313 },
                                            { 0.766000, 15024.023438 },
                                            { 0.770100, 14926.859375 },
                                            { 0.774200, 14948.205078 },
                                            { 0.778300, 14968.500000 },
                                            { 0.782400, 14961.653320 },
                                            { 0.786500, 14880.744141 },
                                            { 0.790600, 14853.396484 },
                                            { 0.794700, 14715.400391 },
                                            { 0.798800, 14625.747070 },
                                            { 0.802900, 14476.197266 },
                                            { 0.806900, 14315.362305 },
                                            { 0.811000, 14115.835938 },
                                            { 0.815100, 14177.434570 },
                                            { 0.819200, 14214.168945 },
                                            { 0.823300, 13756.127930 },
                                            { 0.827400, 13478.938477 },
                                            { 0.831500, 13409.521484 },
                                            { 0.835600, 13313.304688 },
                                            { 0.839700, 13191.076172 },
                                            { 0.843800, 13068.227539 },
                                            { 0.847900, 13143.240234 },
                                            { 0.852000, 13034.021484 },
                                            { 0.856100, 12844.786133 },
                                            { 0.860100, 12565.625977 },
                                            { 0.864200, 12494.125977 },
                                            { 0.868300, 12431.333008 },
                                            { 0.872400, 12224.258789 },
                                            { 0.876500, 12045.228516 },
                                            { 0.880600, 11934.916992 },
                                            { 0.884700, 11999.309570 },
                                            { 0.888800, 12092.721680 },
                                            { 0.892900, 12073.926758 },
                                            { 0.897000, 12000.385742 },
                                            { 0.901100, 11492.284180 },
                                            { 0.905200, 11340.666016 },
                                            { 0.909200, 11261.278320 },
                                            { 0.913300, 11170.411133 },
                                            { 0.917400, 11033.553711 },
                                            { 0.921500, 10920.555664 },
                                            { 0.925600, 10805.260742 },
                                            { 0.929700, 10749.541992 },
                                            { 0.933800, 10633.936523 },
                                            { 0.937900, 10553.670898 },
                                            { 0.942000, 10396.851563 },
                                            { 0.946100, 10345.898438 },
                                            { 0.950200, 10439.532227 },
                                            { 0.954300, 10444.083984 },
                                            { 0.958400, 10338.727539 },
                                            { 0.962400, 10137.357422 },
                                            { 0.966500, 10024.374023 },
                                            { 0.970600, 9960.443359 },
                                            { 0.974700, 9843.068359 },
                                            { 0.978800, 9813.852539 },
                                            { 0.982900, 9774.963867 },
                                            { 0.987000, 9722.901367 },
                                            { 0.991100, 9668.754883 },
                                            { 0.995200, 9489.758789 },
                                            { 0.999300, 9437.469727 },
                                            { 1.003400, 9337.846680 },
                                            { 1.007500, 9232.355469 },
                                            { 1.011500, 9143.000977 },
                                            { 1.015600, 8946.202148 },
                                            { 1.019700, 9061.576172 },
                                            { 1.023800, 8927.707031 },
                                            { 1.027900, 8833.817383 },
                                            { 1.032000, 8559.502930 },
                                            { 1.036100, 8737.791016 },
                                            { 1.040200, 8741.252930 },
                                            { 1.044300, 8734.716797 },
                                            { 1.048400, 8730.012695 },
                                            { 1.052500, 8553.071289 },
                                            { 1.056600, 8567.203125 },
                                            { 1.060700, 8448.906250 },
                                            { 1.064700, 8348.450195 },
                                            { 1.068800, 8372.744141 },
                                            { 1.072900, 8420.621094 },
                                            { 1.077000, 8534.404297 },
                                            { 1.081100, 8515.739258 },
                                            { 1.085200, 8391.372070 },
                                            { 1.089300, 8376.128906 },
                                            { 1.093400, 8364.005859 },
                                            { 1.097500, 8370.607422 },
                                            { 1.101600, 8053.081055 },
                                            { 1.105700, 7885.546875 },
                                            { 1.109800, 7949.569824 },
                                            { 1.113900, 8098.683105 },
                                            { 1.117900, 8009.884766 },
                                            { 1.122000, 7884.853027 },
                                            { 1.126100, 7912.110840 },
                                            { 1.130200, 7977.089844 },
                                            { 1.134300, 8038.597168 },
                                            { 1.138400, 7984.880859 },
                                            { 1.142500, 7943.616211 },
                                            { 1.146600, 8002.785156 },
                                            { 1.150700, 7840.146973 },
                                            { 1.154800, 7771.714844 },
                                            { 1.158900, 7704.839844 },
                                            { 1.163000, 7606.397949 },
                                            { 1.167000, 7499.033203 },
                                            { 1.171100, 7380.200195 },
                                            { 1.175200, 7353.042969 },
                                            { 1.179300, 7373.826172 },
                                            { 1.183400, 7386.295898 },
                                            { 1.187500, 7445.311035 },
                                            { 1.191600, 7298.761230 },
                                            { 1.195700, 7163.548828 },
                                            { 1.199800, 6936.292969 },
                                            { 1.203900, 6920.410156 },
                                            { 1.208000, 6888.470215 },
                                            { 1.212100, 7020.129883 },
                                            { 1.216200, 6991.485840 },
                                            { 1.220200, 6970.270020 },
                                            { 1.224300, 6894.088867 },
                                            { 1.228400, 6915.407227 },
                                            { 1.232500, 6934.170898 }
                                            };

  /**
   * Interpolated values between BEST data.
   */
  private final double[][]        interpolatedValues;

  /**
   * Logarithm of the CONSTANT product within the integral
   * from Leal et al. 2012, equation 4. This should make the
   * code run quicker if this is defined in the constructor.
   */
  private final double[]          logIntegralProduct;

  /**
   * CONSTANT product within the exponential term (the coefficient
   * of the dose variable) in Leal et al. 2012, equation 4. Again
   * this is calculated in the constructor for speed.
   */
  private final double[]          exponentialProduct;

  /**
   * The total integrated intensity at zero dose.
   */
  private final double            zeroDoseIntegratedIntensity;

  /**
   * Constructor for the DDMLeal class that takes in the three decay
   * parameters (defined in Leal et al. 2012, equation 4).
   *
   * @param gamma The is the parameter that used to describe the extent
   *          of the Gaussian type decay of the scale factor (Leal et al. 2012,
   *          equation 3)
   * @param b0 This is the intercept of a plot of B factor against dose
   *          (Leal et al. 2012, equation 2)
   * @param beta This is the gradient of a plot of B factor against dose
   *          (Leal et al. 2012, equation 2)
   */
  public DDMLeal(final Double gamma, final Double b0, final Double beta) {

    //If not all decay parameters have been given in the input file then set their values to zeros
    //Otherwise set them to the values given in the input file.
    if (gamma == null || b0 == null || beta == null) {
      this.beta = 0;
      this.b0 = 0;
      this.gamma = 0;
      System.out.print("No decay parameter values given. ");
      System.out.println("All decay parameters set to 0.");
    } else {
      this.beta = beta;
      this.b0 = b0;
      this.gamma = gamma;

    }

    /**
     * interpolatedValues[i][0] are the midpoint h^2 values from the BEST data
     * interpolatedValues[i][1] are the midpoint J values from the BEST data
     * interpolatedValues[i][2] are the dh values, i.e. the differences between
     * each resolution (h^2) from the BEST data.
     */
    interpolatedValues = new double[BEST_DATA.length - 1][3];
    this.logIntegralProduct = new double[BEST_DATA.length - 1];
    this.exponentialProduct = new double[BEST_DATA.length - 1];

    for (int i = 0; i < BEST_DATA.length - 1; i++) {
      interpolatedValues[i][0] = (BEST_DATA[i][0] + BEST_DATA[i + 1][0]) / 2;
      interpolatedValues[i][1] = (BEST_DATA[i][1] + BEST_DATA[i + 1][1]) / 2;
      interpolatedValues[i][2] = Math.sqrt(BEST_DATA[i + 1][0])
          - Math.sqrt(BEST_DATA[i][0]);

      this.logIntegralProduct[i] = Math.log(interpolatedValues[i][0]
          * interpolatedValues[i][1]
          * interpolatedValues[i][2]
          * Math.exp(-0.5 * this.b0 * interpolatedValues[i][0]));
      this.exponentialProduct[i] = this.beta * -0.5 * interpolatedValues[i][0];
    }

    this.zeroDoseIntegratedIntensity = getIntegratedIntensity(0);
  }

  /**
   * Print string to tell user the type of dose decay model being used.
   *
   * @return Informative string about the dose decay model being used.
   */
  @Override
  public String toString() {
    String stringInfo = String.format("Dose Decay Model from Leal et al. 2012 is "
        + "being used.%n"
        + "Using decay parameter values:%n"
        + "%5s%2s%7.3f %s%n"
        + "%5s%2s%7.3f %s%n"
        + "%5s%2s%7.3f %s"
        ,"Gamma","=",this.gamma,"MGy^{-1}","B0","=",this.b0,"Angstroms^2","Beta"
        ,"=",this.beta,"Angstroms^2 MGy^{-1}");

        return stringInfo;
  }

  /**
   * Method to calculate the Relative Diffraction Efficiency (RDE).
   * The model used is from the Leal et al. 2012 paper that describes
   * the loss of scattering power of a crystal as a product of the
   * expected intensity, the Debye-waller factor and an empirically
   * derived scale factor.
   *
   * @param dose
   *          This is the absorbed dose within the crystal voxel
   * @return The Relative Diffraction Efficiency
   */
  @Override
  public double calcDecay(final double dose) {

    /**
     * Relative intensity is the integrated intensity calculated
     * using the current dose divided by the integrated intensity
     * at dose = 0 MGy.
     * However to up-weight damaged voxels we return 1 minus the
     * relative diffraction efficiency.
     */
    //double relativeWeight = 1 - (getIntegratedIntensity(dose)
    //    / this.zeroDoseIntegratedIntensity);
    double relativeWeight = getIntegratedIntensity(dose) / this.zeroDoseIntegratedIntensity;
    return relativeWeight;
  }

  /**
   * Method to calculate the expected integrated intensity.
   * The integrated intensity can be found in the Leal et al. 2012
   * paper equation 4.
   *
   * @param dose
   *          This is the absorbed dose within the crystal voxel
   * @return The integrated intensity
   */
  public double getIntegratedIntensity(final double dose) {
    // TODO Write a 'check' to make sure there is an argument.

    /**
     * Calculate integral of eqn 4 leal et al. 2012
     */
    double integralSum = 0;
    for (int j = 0; j < interpolatedValues.length; j++) {
      integralSum += Math.exp(this.exponentialProduct[j] * dose
          + this.logIntegralProduct[j]);
    }

    /**
     * Calculate the integrated intensity of eqn 4 leal et al. 2012
     */
    double integratedIntensity = Math.exp(-Math.pow(this.gamma * dose, 2))
        * integralSum;

    return integratedIntensity;
  }

}
