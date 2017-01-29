# Using a deposited PDB

#### PDB example
If the structure of a protein has already been determined and has a deposited entry in the [protein data bank](http://www.rcsb.org/pdb/home/home.do) then RADDOSE-3D can read the atomic composition straight from the web and you don't need to specify the composition of the sample (you'll need to make sure that you have an internet connection). This makes the running of RADDOSE-3D much simpler. Here is an example input file:
```
Crystal
Type Cuboid
Dimensions 100 100 100
AbsCoefCalc EXP             # This tells RADDOSE-3D that it should expect a PDB file
Pdb 1dwa                    # PDB Code
SolventHeavyConc S 2700     # Specifying heavy atoms in the solvent
PixelsPerMicron 0.2

Beam
Type TopHat                 # Tophat beam profile
Energy 12.40
Collimation Rectangular 160 160
Flux 6.00e12

Wedge 0 90
exposureTime 100
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
