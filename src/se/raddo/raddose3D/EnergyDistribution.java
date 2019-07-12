package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.distribution.NormalDistribution;

//Might be preferable to put class NormalEnergyDistribution and class SampleNormalEnergyDistribution elsewhere
class NormalEnergyDistribution  {
 // Fields with defaults - defining distribution parameters
 
 private NormalDistribution gE;
 private double meanEnergy;
 private double energyFwhm;
 
 private double sigmaE;
 private double deltaEOverE; 

 public double FWHM_TO_SIGMA = 1/(2*Math.sqrt(2*Math.log(2)));   
 
 // Method to compute parameters
  public void computeValues() {
     sigmaE = FWHM_TO_SIGMA*energyFwhm;
     deltaEOverE = energyFwhm / meanEnergy; // need to check that this is how delta E /E is defined in Meents 2017, so have not used this as input
  }
  
  public void setUpDistribution() {
      gE = new NormalDistribution(meanEnergy, sigmaE);
  }
  
 // Constructors
 NormalEnergyDistribution() {meanEnergy = 0; energyFwhm = 1; computeValues(); setUpDistribution();}
 NormalEnergyDistribution(double mean, double fwhm) {meanEnergy = mean; energyFwhm = fwhm; computeValues(); setUpDistribution();}
 
 
 // Methods - altering distributions parameters
 public void   setMean(double mean)  {meanEnergy = mean;}
 public double getMean()             {return meanEnergy;}
 public void   setWidth(double width) {energyFwhm = width;} //sets FWHM
 public double getWidth()             {return energyFwhm;}
 public void   setSigma(double sigma) {sigmaE = sigma;} //sets sd
 public double getSigma()             {return sigmaE;}
 public String describeDistribution() 
 {return "\nNormal Distribution of Photon Energies: Parameters\nMean = " + meanEnergy + "\nFWHM = " + energyFwhm + "\nStandard Deviation = " + sigmaE + "\ndelta E / E = " + deltaEOverE + "\n";}
 
 //Method - the pdf
 public double calcProbDensity(double value) {double probabilityDensity = gE.density(value); return probabilityDensity;}
 
 // Method - the cdf
 public double calcCumulativeProb(double value) {double cumulativeProbability = gE.cumulativeProbability(value); return cumulativeProbability;}
     
 // Method - the inverse cdf
 public double calcInverseCumulativeProb(double value) {double energy = gE.inverseCumulativeProbability(value); return energy;}
         
 // Method - selecting values to neglect because normal distribution is truncated
 public boolean testIfNeglect(double value) {
     double cutAt = 40*sigmaE; //need to check if this is suitable value, but unless sample size very large (number?) all it does is omit the infinities
     double lowerEnergyBound = meanEnergy - cutAt;
     double upperEnergyBound = meanEnergy + cutAt;
     boolean result = true;
     if((value < lowerEnergyBound) || (value > upperEnergyBound)) {result = true;}
     else if((value >= lowerEnergyBound) & (value <= upperEnergyBound)) {result = false;}
     return result;}
 
}   


class SampleNormalEnergyDistribution {

private double[] sample;


//Constructor does all the work 
SampleNormalEnergyDistribution(double mean, double fwhm, long numberOfPhotonsToSample) {    // what about the pulse energy?????

// Create distribution objects to sample
NormalEnergyDistribution normalPinkBeam = new NormalEnergyDistribution(mean, fwhm); // Mean energy + FWHM +      Units are keV.

// Systematically sample along distribution

// Sample normal, but truncated (note this means it will not be normalised - need to correct to use a 'truncated normal distribution'):
// Find number of values remaining after truncation
int sampleSize = (int) numberOfPhotonsToSample;
int lengthBeforeNeglections = sampleSize;
int lengthAfterNeglections = 0;
double[] arrayReg;
int targetLength = sampleSize;


while(lengthAfterNeglections != targetLength) {

   arrayReg = new double[lengthBeforeNeglections];
   for(int i = 0; i < arrayReg.length; i++) {
       double value = Double.valueOf(i)/Double.valueOf(lengthBeforeNeglections-1); 
       arrayReg[i] = value;
       }
   lengthAfterNeglections = 0;
   for(int i=0; i < lengthBeforeNeglections; ++i) {
       double cumulativeProb = arrayReg[i];
       double photonEnergy = normalPinkBeam.calcInverseCumulativeProb(cumulativeProb);
       boolean test = normalPinkBeam.testIfNeglect(photonEnergy);
       if(test == false) {lengthAfterNeglections += 1;}
       else if(test == true) {;}
       }
   lengthBeforeNeglections += 1;    
}
   lengthBeforeNeglections -= 1;
   
// Actually calculate the energies
double[] normalPhotonEnergies = new double[sampleSize];
int newIndex = 0;

arrayReg = new double[lengthBeforeNeglections]; 
for(int i = 0; i < arrayReg.length; i++) {
   double value = Double.valueOf(i)/Double.valueOf(lengthBeforeNeglections-1); 
   arrayReg[i] = value;
   }


for(int i=0; i < lengthBeforeNeglections; i++) {
   double cumulativeProb = arrayReg[i];
   double photonEnergy = normalPinkBeam.calcInverseCumulativeProb(cumulativeProb);
   boolean test = normalPinkBeam.testIfNeglect(photonEnergy);
   if(test == false) {
       normalPhotonEnergies[newIndex] = photonEnergy; 
       newIndex += 1;
       }
   else if(test == true) {;}
   
}   

// Shuffle up the energies so not ordered, to avoid introducing bias as we iterate simultaneously
List<Double> normalPhotonEnergiesList = new ArrayList();
for (int i = 0; i < normalPhotonEnergies.length; i++) {
   normalPhotonEnergiesList.add(normalPhotonEnergies[i]);}
Collections.shuffle(normalPhotonEnergiesList);
int length = normalPhotonEnergiesList.size();
double[] normalPhotonEnergiesShuffled = new double[length];
for(int i=0;i<length;i++){
   normalPhotonEnergiesShuffled[i] = normalPhotonEnergiesList.get(i);}

// Store the array of energies so accessible
this.sample = normalPhotonEnergiesShuffled;
}

// Method to pull out the sampled energies
public double[] getSampledEnergies() {return sample;}

}
