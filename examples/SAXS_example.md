#### SAXS example

Below is an example in which we simulate a SAXS experiment with BSA. RADDOSE-3D can read in the sample composition from a [FASTA sequence formatted file](https://en.wikipedia.org/wiki/FASTA_format), so you can save a sequence file in the working directory (here we've used the sequence from the structure with PDB code: **4OR0**). We have assumed a protein concentration of 2 mg/ml.
Because SAXS samples are contained within a cylindrical quartz capillary (on BM29 at the ESRF) with inner diameter on 1.7mm and a wall thickness of 50 microns, we have to define a **Container** to describe this. The elemental composition of quartz is Si 1 O 2 and the density of quartz is 2.643 g/cm^3.
The other input parameters are defined as in the [*simple example*](https://github.com/GarmanGroup/RADDOSE-3D#simple-example) and a more detailed description of all parameters can be found in the [RADDOSE-3D documentation.](https://github.com/GarmanGroup/RADDOSE-3D/blob/master/doc/user-guide.pdf) For full details on the modifications of RADDOSE-3D for SAXS experiments see [this paper by Brooks-Bartlett *et al.* (2017) JSR.](https://doi.org/10.1107/S1600577516015083)
```
##############################################################################
#  Crystal Block ("Crystal" is still used but we are defining a SAXS sample) #
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
Save this in a file of your choice (here we'll call it "MyInput.txt"). We can then open up a terminal/command prompt, change directory to wherever the RADDOSE-3D executable jar file is located and run:
```
java -jar raddose3d.jar -i path/to/MyInput.txt
```
If you come across an error, namely: `Error accessing element database file constants/MuCalcConstants.txt`, this is likely because RADDOSE-3D hasn't found the `MuCalcConstants.txt` file which it needs to know information about the various elements. To solve this problem:
   - you need to create a folder in your working directory called `constants`.       
   - make a copy of the `MuCalcConstants.txt` file from [here](https://github.com/GarmanGroup/RADDOSE-3D/tree/master/constants) in that folder.    

   This error should only occur when the `CoefCalc` input values are either: `RD3D`, `EXP`, `SAXS`, `SEQUENCE` or `SAXSSEQ`.   
   See the  [Gotchas](https://github.com/GarmanGroup/RADDOSE-3D#gotchas-when-running-raddose-3d) section for notes on how to solve other problems that may arise when running RADDOSE-3D.
