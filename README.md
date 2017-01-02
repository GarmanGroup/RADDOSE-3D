RADDOSE-3D - time- and space-resolved modelling of dose in macromolecular crystallography.
==========================================================================================  

[![Build Status](https://travis-ci.org/GarmanGroup/RADDOSE-3D.svg?branch=master)](https://travis-ci.org/GarmanGroup/RADDOSE-3D)

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

Usage
------

For the full RADDOSE-3D documentation [see here.](https://github.com/GarmanGroup/RADDOSE-3D/blob/master/doc/user-guide.pdf) Below are a two examples showing how to run RADDOSE-3D for a simple MX experiment and for a SAXS experiment.

#### Simple example
This example shows how you can run RADDOSE-3D to simulate a macromolecular crystallography experiment where a crystal of insulin is exposed to a Gaussian profile X-ray beam for 50 seconds with a 90⁰ rotation. This example can be run directly using the [RADDOSE-3D web server interface](http://www.raddo.se/).   
The following text can be added to any plain text file.
```
##############################################################################
#                                 Crystal Block                              #
##############################################################################

Crystal

Type Cuboid             # Cuboid
Dimensions 100 100 100  # Dimensions of the crystal in X,Y,Z in µm.
PixelsPerMicron 0.1     # The computational resolution
AbsCoefCalc  RDV2       # Absorption Coefficients Calculated using
                        # RADDOSE v2 (Paithankar et al. 2009)

# Example case for insulin:
UnitCell   78.02  78.02  78.02  # unit cell size: a, b, c
                                # alpha, beta and gamma angles default to 90°
NumMonomers  24                 # number of monomers in unit cell
NumResidues  51                 # number of residues per monomer
ProteinHeavyAtoms Zn 2 S 6      # heavy atoms added to protein part of the
                                # monomer, i.e. S, coordinated metals,
                                # Se in Se-Met
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
FWHM 20 70                # in µm, vertical by horizontal for a Gaussian beam
Energy 12.1               # in keV

Collimation Rectangular 100 100 # Vertical/Horizontal collimation of the beam
                                # For 'uncollimated' Gaussians, 3xFWHM
                                # recommended


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

#### SAXS example
Below is an example in which we simulate a SAXS experiment with BSA. RADDOSE-3D can read in the sample composition from a FASTA sequence formatted file, so you can save a sequence file in the working directory (here we've used the sequence from the structure with PDB code: **4OR0**). We have assumed a protein concentration of 2 mg/ml.    
Because SAXS samples are container within a cylindrical quartz capillary (on BM29 at the ESRF) with inner diameter on 1.7mm and a wall thickness of 50 microns, we have to define **Container** to describe this. The elemental composition of quartz is Si 1 O 2 and the density of quartz is 2.643 g/cm^3.    
The other input parameters are defined as in the *simple example* above and a more detailed description of all parameters can be found in the [RADDOSE-3D documentation.](https://github.com/GarmanGroup/RADDOSE-3D/blob/master/doc/user-guide.pdf)
```
##############################################################################
#                                 Crystal Block                              #
##############################################################################
Crystal
Type Cylinder           
Dimensions 1700 1000
PixelsPerMicron 0.01    
CoefCalc  SAXSseq           
SeqFile 4OR0.fasta  # PDB code 4OR0
ProteinConc 2	      
ContainerMaterialType elemental
MaterialElements Si 1 O 2
ContainerThickness 50
ContainerDensity 2.648

##############################################################################
#                                  Beam Block                                #
##############################################################################
Beam
Type Gaussian            
Flux 2e12                
FWHM 700 700                
Energy 12.1               
Collimation Rectangular 1000 1000

##############################################################################
#                                  Wedge Block                               #
##############################################################################

Wedge 0 0                                   
ExposureTime 50           
# AngularResolution 2
```
As with the example above, save this in a file of your choice (here we'll call it "MyInput.txt"). We can then open up a terminal/command prompt, change directory to wherever the RADDOSE-3D executable jar file is located and run:
```
java -jar raddose3d.jar -i path/to/MyInput.txt
```

#### Considerations when running RADDOSE-3D for some special cases.

- If you have a **small crystal** (< 20μm³ for example) then the default `Pixels per micron` value (0.5) will be too small and this will result in inaccurate dose values. To avoid this, the `PixelsPerMicron` keyword needs to be set in the `Crystal` block and needs to be increased from 0.5. This increases the resolution and hence will increase the amount of time taken for RADDOSE-3D to run.
- If the crystal is not rotated at all then specify `Wedge 0 0` in the input file and **don't** specify the `AngularResolution`. RADDOSE-3D will automatically account for this.
- If the crystal is rotated over a small, non-zero angular range, (e.g. < 20⁰) then the `AngularResolution` value should be set to value at least 10 times smaller than the overall angular range. For example if the crystal is rotated by 5⁰ then the `AngularResolution` should be set to a maximum value of 0.5.
- If you don't specify the `SolventHeavyConc` then RADDOSE-3D crashes with an error ([see this issue](https://github.com/GarmanGroup/RADDOSE-3D/issues/1)). To avoid this run the solvent heavy concentration can be specified as `SolventHeavyConc S 0`. This is a known bug which will be fixed for the next RADDOSE-3D release.

Contributors:
-------------

* Oliver Zeldin
* Markus Gerstel
* Jonny Brooks-Bartlett
* Helen Ginn

**Please cite**
Zeldin, Gerstel, Garman. (2013). J. Appl. Cryst. 46, 1225-1230.
http://dx.doi.org/10.1107/S0021889813011461
