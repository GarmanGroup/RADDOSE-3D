RADDOSE-3D - time- and space-resolved modelling of dose in macromolecular crystallography.
==========================================================================================  

[![Build Status](https://travis-ci.org/GarmanGroup/RADDOSE-3D.svg?branch=master)](https://travis-ci.org/GarmanGroup/RADDOSE-3D)
[![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.228172.svg)](https://doi.org/10.5281/zenodo.228172)

RADDOSE-3D allows the macroscopic modelling of an X-ray diffraction
experiment for the purpose of better predicting radiation-damage progression.
The distribution of dose within the crystal volume is calculated for a number
of iterations in small angular steps across one or more data collection
wedges, providing a time-resolved picture of the dose state of the crystal.
The code is highly modular so that future contributions from the community
can be easily integrated into it, in particular to incorporate online methods
for determining the shape of macromolecular crystals and better protocols for
imaging real experimental X-ray beam profiles.

Brought to you by the Garman Lab at the University of Oxford.
http://www.bioch.ox.ac.uk/garmangroup

If you have any questions on how to run RADDOSE-3D or would like advice on what inputs to use please email j_dickerson80@yahoo.com.

Usage
------

For the full RADDOSE-3D documentation [see here.](https://github.com/GarmanGroup/RADDOSE-3D/blob/master/doc/user-guide.pdf) Examples of running RADDOSE-3D for different sample types are included in the [examples folder](https://github.com/GarmanGroup/RADDOSE-3D/tree/master/examples). Below is an example showing how to run RADDOSE-3D for a simple macromolecular crystallography (MX) experiment.

#### Simple example
This example shows how you can run RADDOSE-3D to simulate a macromolecular crystallography experiment where a crystal of insulin is exposed to a Gaussian profile X-ray beam for 50 seconds with a 90⁰ rotation. This example can be run directly using the [RADDOSE-3D web server interface](http://www.raddo.se/).   
The following text can be added to any plain text file.   
**Note: that anything after a "#" or a "!" are regarded as comments and are ignored by RADDOSE-3D**
```
##############################################################################
#                                 Crystal Block                              #
##############################################################################

Crystal

Type Cuboid             # Cuboid
Dimensions 100 100 100  # Dimensions of the crystal in X,Y,Z in µm.
PixelsPerMicron 0.1     # The computational resolution
AbsCoefCalc  RD3D       # Tells RADDOSE-3D how to calculate the
                        # Absorption coefficients

# Example case for insulin:
UnitCell   78.02  78.02  78.02  # unit cell size: a, b, c
                                # alpha, beta and gamma angles default to 90°
NumMonomers  24                 # number of monomers in unit cell
NumResidues  51                 # number of residues per monomer
ProteinHeavyAtoms Zn 0.333 S 6  # heavy atoms added to protein part of the
                                # monomer, i.e. S, coordinated metals,
                                # Se in Se-Met
                                # Note: If a sequence file is used S does not 
                                # need to be added
                                
SolventHeavyConc P 425          # concentration of elements in the solvent
                                # in mmol/l. Oxygen and lighter elements
                                # should not be specified
SolventFraction 0.64            # fraction of the unit cell occupied by solvent


##############################################################################
#                                  Beam Block                                #
##############################################################################

Beam

Type Gaussian             # Gaussian profile beam
Flux 2e12                 # in photons per second (2e12 = 2 * 10^12)
FWHM 20 70                # in µm, X and Y for a Gaussian beam
						  # X=vertical and Y = horizontal for a 
						  # horizontal goniometer
						  # Opposite for a vertical goniometer

Energy 12.1               # in keV

Collimation Rectangular 100 100 # X/Y collimation of the beam in µm
								# X = vertical and Y = horizontal for a
								# horizontal goniometer
								# Opposite for a vertical goniometer



##############################################################################
#                                  Wedge Block                               #
##############################################################################

Wedge 0 90                # Start and End rotational angle of the crystal
                          # Start < End
ExposureTime 50           # Total time for entire angular range in seconds

# AngularResolution 2     # Only change from the defaults when using very
                          # small wedges, e.g 5°.

```
Save this in a file of your choice (here we'll call it "MyInput.txt"). We can then open up a terminal/command prompt, change directory to wherever the RADDOSE-3D executable jar file is located and run:
```
java -jar raddose3d.jar -i path/to/MyInput.txt
```
and this should produce the required output.

#### Considerations when running RADDOSE-3D for some special cases.

- If you have a **small crystal** (< 20μm³ for example) then the default `PixelsPerMicron` value (0.5) will be too small and this will result in inaccurate dose values. To avoid this, the `PixelsPerMicron` keyword needs to be set in the `Crystal` block and needs to be increased from 0.5. This increases the resolution and hence will increase the amount of time taken for RADDOSE-3D to run.
- If the crystal is not rotated at all then specify `Wedge 0 0` in the input file and **don't** specify the `AngularResolution`. RADDOSE-3D will automatically account for this.
- If the crystal is rotated over a small, non-zero angular range, (e.g. < 20⁰) then the `AngularResolution` value should be set to value at least 100 times smaller than the overall angular range. For example if the crystal is rotated by 5⁰ then the `AngularResolution` should be set to a maximum value of 0.05. Thank you to **Leigh Carter** at [Global Phasing](https://www.globalphasing.com/) for performing the systematic analysis to obtain a suitable value for this case.
- If you have a **big crystal/sample** then RADDOSE-3D may take quite a while to run using the default `PixelsPerMicron` value of 0.5. To reduce the computation time you can reduce that value. However reducing the `PixelsPerMicron` will decrease the accuracy of the calculation so it is important to determine a suitable accuracy threshold. Try running RADDOSE-3D with a few different values to determine a when the dose value approaches convergence of the dose value. This is likely to be the case when running a simulation for a SAXS experiment. If the sample is too big then you'll come across an error: `Error during invocation of se.raddo.raddose3D.CrystalCylinder: Java heap space`. This means you'll have to further reduce the `PixelsPerMicron` value.
- If running RADDOSE-3D with photoelectron escape from the crystal RADDOSE-3D will take significantly longer to run. This is especially true if the surroundng solution is also simulated. When running photoelectron escape, it is even more important that the `PixelsPerMicron` is high enough to ensure accuracy.

### "Gotchas" when running RADDOSE-3D
- If you have a **very big crystal** or the `PixelsPerMicron` value is set **too big** then Java will fail with the error: `Error during invocation of se.raddo.raddose3D.CrystalCylinder: Java heap space`. To rectify this problem:
   - you need to reduce the value of the `PixelsPerMicron` value.

   *This problem is highly likely when running with SAXS samples* because they are typically contained in capillaries with are much bigger than crystals in MX, which RADDOSE-3D was initially written for.

- If you run RADDOSE-3D with the `CoefCalc` input parameter value as `RD`, `RDV2` or `RDV3` then this tells RADDOSE-3D to run the RADDOSE V2 executable file (an external program) and extract the absorption values from there. This requires you to have another executable file in addition to the raddose3d.jar (namely the raddose.exe file). If you don't have it then you'll encounter the following error:
`
Found RADDOSE at ../raddose but could not execute`      
`RADDOSE program could not be run. Please specify path to RADDOSE manually using the -r command line argument`. This problem should be solved by changing the `CoefCalc` input value to `RD3D`.

- If you run RADDOSE-3D and come across the error: `Error accessing element database file constants/MuCalcConstants.txt` this is likely because RADDOSE-3D hasn't found the `MuCalcConstants.txt` file which it needs to know information about the various elements.  This error should only occur when the `CoefCalc` input values are either: `RD3D`, `EXP`, `SAXS`, `SEQUENCE` or `SAXSSEQ`. To solve this problem:
   - you need to create a folder in your working directory called `constants`.       
   - make a copy of the `MuCalcConstants.txt` file from [here](https://github.com/GarmanGroup/RADDOSE-3D/tree/master/constants) in that folder.    
   - if you are running RADDOSE-XFEL or RADDOSE-3D Monte Carlo simulations, all files in the constants folder will need to be present to avoid an error.




- The `flux` value that is provided in the `Beam` block in the input file represents the **flux at the sample position after collimation.** This can lead to ostensibly surprising results. For example if you run 1 simulation and get a dose value, we'll say dose 1, and then run a second simulation with the same inputs except the collimation has been reduced, then the second dose value, dose 2, will be larger than dose 1. This happens because the same flux in both simulations is spread over a smaller area in the second simulation. So the dose distribution in the crystal is spread over a smaller volume but it has received the same number of photons in that volume as in the first simulation.

- The input file must have no spaces in the file name/path or it will not be recognised

### Interpreting the output
A discussion of the output is given in the original RADDOSE-3D paper - [Zeldin, Gerstel, Garman. (2013). J. Appl. Cryst. 46, 1225-1230.](#Contributers)   
The `Max dose` given by RADDOSE-3D is the dose value that is comparable to the dose output by the predecessor program [RADDOSE V2](https://doi.org/10.1107/S0909049508040430). However the dose value that is the most informative and representative of the damage state of the crystal is the **Average Diffraction Weighted Dose**. The details of this quantity can be found in [this paper by Zeldin *et al.* (2013) PNAS](https://doi.org/10.1073/pnas.1315879110).

Contributors:
-------------

* Oliver Zeldin
* Markus Gerstel
* Jonny Brooks-Bartlett
* Helen Ginn
* Charlie Bury
* Steve Walsh
* Josh Dickerson
* Patrick McCubbin



### Please cite us!
The original RADDOSE-3D publication is:
- Zeldin, O. B., Gerstel, M., & Garman, E. F. (2013). RADDOSE-3D : time- and space-resolved modelling of dose in macromolecular crystallography. Journal of Applied Crystallography, 46, 1225–1230. https://doi.org/10.1107/S0021889813011461

RADDOSE-3D version 5 now includes a new program to estimate doses for electron diffraction experiments, RADDOSE-ED. We also now include dose decay models and a new GUI:
- Dickerson, J. L., McCubbin P. T. N, Brooks-Bartlett J. C., Garman E. F.. Doses for X-ray and electron diffraction: New features in RADDOSE-3D including intensity decay models. Protein Science. 2024;33(7):e5005. https://doi.org/10.1002/pro.5005

We now have a new publication on updates to RADDOSE-3D in the period 2014 - 2017:
- Bury, C. S., Brooks‐Bartlett, J. C., Walsh, S. P. and Garman, E. F. (2018), Estimate your dose: RADDOSE‐3D. Protein Science, 27, 217-228. https://doi.org/10.1002/pro.3302

For the application to SAXS, also check out:
- Brooks-Bartlett, J. C., Batters, R. A., Bury, C. S., Lowe, E. D., Ginn, H. M., Round, A., & Garman, E. F. (2017). Development of tools to automate quantitative analysis of radiation damage in SAXS experiments. Journal of Synchrotron Radiation, 24, 1–10. https://doi.org/10.1107/S1600577516015083

For RADDOSE-XFEL please cite:
- Dickerson, J. L., McCubbin, P. T. N. & Garman, E. F. (2020). RADDOSE-XFEL: femtosecond time-resolved dose estimates for macromolecular X-ray free-electron laser experiments. J. Appl. Cryst. 53, 549-560. https://doi.org/10.1107/S1600576720000643

RADDOSE-3D can now also be used with small molecules:
- Christensen, J., Horton, P. N., Bury, C. S., Dickerson, J. L., Taberman, H., Garman, E. F. & Coles, S. J. (2019). Radiation damage in small-molecule crystallography: fact not fiction. IUCrJ, 6, 703-713. https://doi.org/10.1107/S2052252519006948

And if using microcrystals or microbeams, we recommend using the Monte Carlo simulations outlined in:
- Dickerson JL, Garman EF. Doses for experiments with microbeams and microcrystals: Monte Carlo simulations in RADDOSE-3D. Protein Science. 2021; 30: 8–19. https://doi.org/10.1002/pro.3922

If there are heavy atoms present in the sample, it may be more accurate to simulate fluorescent escape, with the implementation detailed in:
- Fernando, N. K., Cairns, A. B., Murray, C. A., Thompson, A. L., Dickerson, J. L., Garman, E. F., … Regoutz, A. (2021). Structural and Electronic Effects of X-ray Irradiation on Prototypical [M(COD)Cl]2 Catalysts. The Journal of Physical Chemistry A, 125(34), 7473–7488. doi:10.1021/acs.jpca.1c05759




