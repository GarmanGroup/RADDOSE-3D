package se.raddo.raddose3D;

import java.util.Map;
import java.util.HashMap;


public class PEsim extends CrystalPolyhedron{
  
  Map<Element, Double> elementAbsorptionProbs;
  Map<Element, double[]> ionisationProbs;
  private double[] angularEmissionProbs;
  private final int numberAngularEmissionBins = 50;

  public PEsim(Map<Object, Object> properties) {
    super(properties);
    // TODO Auto-generated constructor stub
  }
  
  public void setup(CoefCalc coefCalc, double photonEnergy) {
    elementAbsorptionProbs = coefCalc.getPhotoElectricProbsElement(photonEnergy);
    ionisationProbs = getRelativeShellProbs(photonEnergy);
    populateAngularEmissionProbs();   
    Element ionisedElement = getIonisedElement();
    int shell = getIonisedShell(ionisedElement);
    
  }
  
  public double getPEenergy(double photonEnergy, Element ionisedElement, int shellIndex) {
    return photonEnergy - getShellBindingEnergy(ionisedElement, shellIndex);
  }
  
  public void initialDirection(int shellIndex, final double angle) {
  //Convert angle to less than 360 if it is more
    int timesOver = (int) (angle/(2*Math.PI));
    double thisAngle = angle - (timesOver * 2 *Math.PI);
    
    //flip the angle to be opposite direction
    thisAngle = 2*Math.PI - thisAngle;
    double polarised = Math.random();
    double xNorminit = 0, xNorm = 0, yNorm = 0, zNorminit = 0, zNorm = 0, phi = 0, theta = 0;
    if (shellIndex == 0 && polarised >= 0.25) { //then I want to send out in a biased direction
      if (verticalGoniometer == true) {
        xNorminit = getCosAngleToX();
        //get yNorm and zNorm
        yNorm = PosOrNeg() * Math.random() * Math.pow(1-Math.pow(xNorminit, 2), 0.5);
      }
      else {
        yNorm = getCosAngleToX();
        //get yNorm and zNorm
        xNorminit = PosOrNeg() * Math.random() * Math.pow(1-Math.pow(yNorm, 2), 0.5);
      }
      zNorminit = PosOrNeg() * Math.pow(1 - Math.pow(xNorm, 2) - Math.pow(yNorm, 2), 0.5);
      
      //now apply the rotation matrix
      xNorm = xNorminit * Math.cos(thisAngle) + zNorminit * Math.sin(thisAngle);
      zNorm = -1 * xNorminit * Math.sin(thisAngle) + zNorminit * Math.cos(thisAngle);

      theta = Math.acos(zNorm);
      phi = Math.acos(xNorm / Math.sin(theta));
      
    }
    else { // send it out in a random direction
      theta = Math.random() * 2 * Math.PI;
      phi = Math.random() * 2 * Math.PI;
      xNorm = Math.sin(theta) * Math.cos(phi);
      yNorm = Math.sin(theta) * Math.sin(phi);
      zNorm = Math.cos(theta);
    }
  }
  
  
  private Element getIonisedElement() {
    double elementRND = Math.random();
    Element ionisedElement = null;
    for (Element e : elementAbsorptionProbs.keySet()) {
      double elementProb =  elementAbsorptionProbs.get(e);
      if (elementProb > elementRND) {
        ionisedElement = e;
        break;
      }
    }
    return ionisedElement;
  }
  
  private Map<Element, double[]> getRelativeShellProbs(double beamEnergy){
    Map<Element, double[]> ionisationProbs = new HashMap<Element, double[]>();
    for (Element e : elementAbsorptionProbs.keySet()) {
      e.EdgeRatio();
      double runningSumProb = 0;
      double kshellProb = 0, L1shellProb = 0, L2shellProb = 0, L3shellProb = 0, M1shellProb = 0, M2shellProb = 0, M3shellProb = 0, M4shellProb = 0, M5shellProb = 0;
      double[] shellProbs = new double[9];
  //    double shellProb = 0;
      if (beamEnergy > e.getKEdge() ) {
        kshellProb = e.getKShellIonisationProb();
        runningSumProb += kshellProb;
        shellProbs[0] = runningSumProb;
      }
      if (beamEnergy > e.getL1Edge() && e.getAtomicNumber() >= 12) {
        L1shellProb = e.getL1ShellIonisationProb() * (1-kshellProb);
        runningSumProb += L1shellProb;
        shellProbs[1] = runningSumProb;
      }
      if (beamEnergy > e.getL2Edge() && e.getAtomicNumber() >= 12) {
        L2shellProb = e.getL2ShellIonisationProb() * (1-kshellProb-L1shellProb);
        runningSumProb += L2shellProb;
        shellProbs[2] = runningSumProb;
      }
      if (beamEnergy > e.getL3Edge() && e.getAtomicNumber() >= 12) {
        L3shellProb = e.getL3ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb);
        runningSumProb += L3shellProb;
        shellProbs[3] = runningSumProb;
      }
      if (beamEnergy > e.getM1Edge() && e.getAtomicNumber() >= 73) { 
        M1shellProb = e.getM1ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb-L3shellProb);
        runningSumProb += M1shellProb;
        shellProbs[4] = runningSumProb;
      }
      if (beamEnergy > e.getM2Edge() && e.getAtomicNumber() >= 73) { 
        M2shellProb = e.getM2ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb-L3shellProb-M1shellProb);
        runningSumProb += M2shellProb;
        shellProbs[5] = runningSumProb;
      }
      if (beamEnergy > e.getM3Edge() && e.getAtomicNumber() >= 73) { 
        M3shellProb = e.getM3ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb-L3shellProb-M1shellProb-M2shellProb);
        runningSumProb += M3shellProb;
        shellProbs[6] = runningSumProb;
      }
      if (beamEnergy > e.getM4Edge() && e.getAtomicNumber() >= 73) { 
        M4shellProb = e.getM4ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb-L3shellProb-M1shellProb-M2shellProb-M3shellProb);
        runningSumProb += M4shellProb;
        shellProbs[7] = runningSumProb;
      }
      if (beamEnergy > e.getM5Edge() && e.getAtomicNumber() >= 73) { 
        M4shellProb = e.getM5ShellIonisationProb() * (1-kshellProb-L1shellProb-L2shellProb-L3shellProb-M1shellProb-M2shellProb-M3shellProb-M4shellProb);
        runningSumProb += M4shellProb;
        shellProbs[8] = runningSumProb;
      }
      ionisationProbs.put(e, shellProbs);
    }
    return ionisationProbs;
  }
  
  private int getIonisedShell(Element ionisedElement) {
    double[] shellProbs = ionisationProbs.get(ionisedElement);
    double shellRND = Math.random();
    int shellIndex = 0;
    for (int j = 0; j < shellProbs.length; j++) {
      if (shellProbs[j] > shellRND) {
        shellIndex = j;
        break;
      }
    }
    return shellIndex;
  }
  
  private double getShellBindingEnergy(Element collidedElement, int collidedShell) {
    double shellBindingEnergy = 0;
    switch (collidedShell) {
      case 0: shellBindingEnergy = collidedElement.getKEdge();
              break;
      case 1: shellBindingEnergy = collidedElement.getL1Edge();
              break;
      case 2: shellBindingEnergy = collidedElement.getL2Edge();
              break;
      case 3: shellBindingEnergy = collidedElement.getL3Edge();
              break;
      case 4: shellBindingEnergy = collidedElement.getM1Edge();
              break;
      case 5: shellBindingEnergy = collidedElement.getM2Edge();
              break;
      case 6: shellBindingEnergy = collidedElement.getM3Edge();
              break;
      case 7: shellBindingEnergy = collidedElement.getM4Edge();
              break;
      case 8: shellBindingEnergy = collidedElement.getM5Edge();
              break;
    }
    return shellBindingEnergy;
  }
  
  private double PosOrNeg() {
    double RND = Math.random();
    if (RND < 0.5) {
      return 1;
    }
    else {
      return -1;
    }
  }
  
  private double getCosAngleToX() {
    double RNDangle = Math.random();
    double lastProb = 0;
    double angle = 0;
    for (int i = 0; i < numberAngularEmissionBins; i++) {
      if (RNDangle < angularEmissionProbs[i]) { //then it's in this angle range
        //interpolate angle
        double angleStart = i * Math.PI/numberAngularEmissionBins;
        double angleEnd = (i+1) * (Math.PI/numberAngularEmissionBins);
        double proportionAlong = (RNDangle - lastProb) / (angularEmissionProbs[i] - lastProb);
        angle = angleStart + (proportionAlong * (angleEnd - angleStart));
        break;
      }
      lastProb = angularEmissionProbs[i];
    }
    return Math.cos(angle);
  }
  
  private void populateAngularEmissionProbs() {
    angularEmissionProbs = new double[numberAngularEmissionBins];
  //    double photoelectric = coefCalc.getElementAbsorptionCoef(beam.getPhotonEnergy(), e);
      //integrate under the whole curve
      double lastHeight = 0;
      double totalArea = 0;
      for (int i = 0; i <= 100; i++) {
        double angle = ((Math.PI)/100)*i;
        double height = solvePolarisationEquationForAngle(angle, 1, 2);
        if (i > 0) {
          double area = ((lastHeight + height)/2) * ((Math.PI)/100);
          totalArea += area;
        }
        lastHeight = height;
      }
      //now get the proportion of some of these
   //   double[] emissionProbs = new double[numberAngularEmissionBins];
      lastHeight = 0;
      double cumulativeProb = 0;
      for (int i = 0; i <= numberAngularEmissionBins; i++) {
        double angle = ((Math.PI)/numberAngularEmissionBins)*i;
        double height = solvePolarisationEquationForAngle(angle, 1, 2);
        if (i > 0) {
          double area = ((lastHeight + height)/2) * ((Math.PI)/numberAngularEmissionBins);
          cumulativeProb += area / totalArea;
          angularEmissionProbs[i-1] = cumulativeProb;
        }
        lastHeight = height;
      }
    //  angularEmissionProbs.put(e, emissionProbs);
  
  }
  
  private double solvePolarisationEquationForAngle(double phi, double photoElectric, double beta) {
    double height = (photoElectric / (4*Math.PI)) * (1+(beta*0.5*(3*Math.pow(Math.cos(phi), 2) - 1)));
    return height;
  }
  
  
  private void trackPhotoelectron(CoefCalc coefCalc, double startingTimeStamp, double startingEnergy,
      double previousX, double previousY, double previousZ,
      double xNorm, double yNorm, double zNorm, double theta, double phi, boolean surrounding,
      boolean primaryElectron, Beam beam) {
      //do full Monte Carlo simulation the same way as in MicroED, but with a time stamp and adding dose every step
     //just do stopping power for now dw about surrounding and aUger and fluorescence and stuff


    double energyLost = 0;
    double W = 0;
    double electronEnergy = startingEnergy;
    double timeStamp = startingTimeStamp;
    double startingStoppingPower = coefCalc.getStoppingPower(startingEnergy, surrounding);
    double stoppingPower = startingStoppingPower;
    
    double startingLambda_el = coefCalc.getElectronElasticMFPL(startingEnergy, surrounding);
    Map<ElementEM, Double> elasticProbs = coefCalc.getElasticProbs(surrounding);
    
    
    //Inner shell ionisation x section
    coefCalc.populateCrossSectionCoefficients();
    double startingInnerShellLambda = coefCalc.betheIonisationxSection(startingEnergy, surrounding);
    Map<Element, double[]> ionisationProbs = coefCalc.getAllShellProbs(surrounding); //Really need to make sure that these are in the same order
    
    double gosInelasticLambda = 0, gosInnerLambda = 0, gosOuterLambda = 0;
    Map<Element, double[]> gosIonisationProbs = null;
    Map<Element, Double> gosOuterIonisationProbs = null;
    double lambdaT = 0;
    double Pinner = 0;
 //   lambdaT = startingLambda_el;
 //   lambdaT = 1/ (1/startingLambda_el + 1/startingFSELambda);
    
    if (gosInelasticLambda > 0) {
      lambdaT = 1 / (1/startingLambda_el + 1/gosInelasticLambda);
    }
    else {
      lambdaT = startingLambda_el;
    }
    if (startingInnerShellLambda > 0) {
      Pinner = (gosInelasticLambda/startingInnerShellLambda);
    }
    
    double testRND = Math.random();
    double s = -lambdaT*Math.log(testRND);
    double Pinel = 1 - (lambdaT / startingLambda_el);
    
     
    
   // Pinel = 0; //quick way so it never activates the slow code
    
    double xn = previousX + s * xNorm;
    double yn = previousY + s * yNorm;
    double zn = previousZ + s * zNorm;
    
    double lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, surrounding);
    double FSELambda = 0, FSExSection = 0, innerShellLamda = 0;;
    
    
    boolean exited = false;
    boolean entered = false;
    
    
    double previousTheta = 0, previousPhi = 0;
    
    if (startingEnergy < 0.05) {
      int[] pixelCoord = convertToPixelCoordinates(previousX, previousY, previousZ);
      exited = true;
      //I need to add to the dose here
      if (isMicrocrystalAt(previousX, previousY, previousZ) == true) { 
          int doseTime = (int) (timeStamp/PULSE_BIN_LENGTH);
        if (primaryElectron == true) { //only doing dose from the primary
          dose[doseTime] += startingEnergy;
          electronDose[doseTime] += startingEnergy;
          gosElectronDose[doseTime] += startingEnergy;
          gosElectronDosevResolved[doseTime] += startingEnergy;
        //add to voxel
          
      //    voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += startingEnergy;
          voxelEnergyvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += startingEnergy;
          
        }
        //get avg energy and add on ionisation
        double avgEnergy = coefCalc.getAvgInelasticEnergy(startingEnergy);
        avgEnergy = coefCalc.getPlasmaEnergy(surrounding)/1000;
        if (avgEnergy > 0) {
          if (simpleMC == false) {
          int numIonisation = (int) (startingEnergy/avgEnergy);
          if (numIonisation > 0 && surrounding == false) {
            totalIonisationEvents[doseTime] += numIonisation;
            lowEnergyIonisations[doseTime] += numIonisation;
            //decide what elements to add this to
            Element hitElem = chooseLowEnElement(coefCalc, Pinner, gosOuterIonisationProbs, ionisationProbs);
            if (timeStamp <lastTimeVox[pixelCoord[2]]-(1*PULSE_BIN_LENGTH)) {
              totalIonisationEventsvResolved[doseTime] += numIonisation;
              voxelIonisationsvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += numIonisation;
              if (atomicIonisations.containsKey(hitElem)) {
                atomicIonisations.put(hitElem, atomicIonisations.get(hitElem)+numIonisation);
              }
              else {
                atomicIonisations.put(hitElem, (long)numIonisation);
              }
              if(testIfInsideExposedArea(previousX, previousY, beam) == true) {
                if (atomicIonisationsExposed.containsKey(hitElem)) {
                  atomicIonisationsExposed.put(hitElem, atomicIonisationsExposed.get(hitElem)+numIonisation);
                }
                else {
                  atomicIonisationsExposed.put(hitElem, (long)numIonisation);
                }
              }
            }
          }
        }
        }
      }
    }
    while (exited == false) {
      if (isMicrocrystalAt(xn, yn, zn) == true) { //photoelectron still in the crystal
        //here need to check if it has crossed a boundary to enter
        if (surrounding == true) {
          entered = true;
          surrounding = false;
          //need to check where it intersects
          double intersectionDistance = 1000*getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm); //nm 
          double[] intersectionPoint = getIntersectionPoint(intersectionDistance, previousX, previousY, previousZ, xNorm, yNorm, zNorm); 
          //set this to previous positions
          previousX = 1000*intersectionPoint[0];
          previousY = 1000*intersectionPoint[1];
          previousZ = 1000*intersectionPoint[2];
          //update timestamp
          timeStamp += getTimeToDistance(electronEnergy, intersectionDistance);
          //update energy to this point and coefficients
          if (simpleMC == true) {
            electronEnergy -= intersectionDistance * stoppingPower;
            //might need to sort it out if less than 0.05 here
          }
          stoppingPower = coefCalc.getStoppingPower(electronEnergy, surrounding);
          double newFSExSection = getFSEXSection(startingEnergy);
          double newFSELambda = coefCalc.getFSELambda(startingFSExSection, false);
          if (simpleMC == false) {
          startingInnerShellLambda = coefCalc.betheIonisationxSection(startingEnergy, surrounding);
          ionisationProbs = coefCalc.getAllShellProbs(surrounding);
          gosInelasticLambda = coefCalc.getGOSInel(surrounding, electronEnergy);
          gosInnerLambda = coefCalc.getGOSInnerLambda(surrounding);
          gosOuterLambda = coefCalc.getGOSOuterLambda(surrounding);
          gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(surrounding, gosOuterLambda); //note atm this does not work with plasma in this way
          if (startingInnerShellLambda > 0) {
            gosInelasticLambda = 1/(1/gosOuterLambda + 1/startingInnerShellLambda);
          }
          else {
            gosInelasticLambda = 1/gosOuterLambda;
          }
          gosIonisationProbs = coefCalc.getGOSShellProbs(surrounding, gosInelasticLambda);
          if (startingInnerShellLambda > 0) {
            Pinner = (gosInelasticLambda/startingInnerShellLambda);
          }
          else {
            Pinner = 0;
          }
          }
        //  lambdaT = 1/(1/coefCalc.getElectronElasticMFPL(electronEnergy, surrounding) + 1/newFSELambda);
          startingLambda_el = coefCalc.getElectronElasticMFPL(electronEnergy, surrounding);
          if (gosInelasticLambda > 0) {
            lambdaT = 1 / (1/startingLambda_el + 1/gosInelasticLambda);
          }
          else {
            lambdaT = startingLambda_el;
          }
          elasticProbs = coefCalc.getElasticProbs(surrounding);

          Pinel = 1 - (lambdaT / startingLambda_el);

          //get a new s and xn, yn, zn
          s = -lambdaT*Math.log(Math.random());
          xn = previousX + s*xNorm;
          yn = previousY + s*yNorm;
          zn = previousZ + s*zNorm;
        }
      }
      //Will need a second if microcrystal is at = to true here so that I can check the surrounding one again
      if (isMicrocrystalAt(xn, yn, zn) == true) {
        energyLost = s * stoppingPower;
        if (simpleMC == true) {
          lossSinceLastUpdate += energyLost;
        }
        //work out how long it took to travel this far 
        double timeToDistance = getTimeToDistance(electronEnergy, s);
        int doseTime = (int) ((timeStamp + (timeToDistance/2))/PULSE_BIN_LENGTH);
        timeStamp += timeToDistance;
        double energyToAdd = energyLost;
        if (doseTime < 0) {
          doseTime = 0;
        }
        /*
        if (energyToAdd < 0) {
          System.out.print("Test");
        }
        */
        if (primaryElectron == true) { //only doing dose from the primary
          dose[doseTime] += energyToAdd;  //still just adding keV
          if (entered == true) {
            electronDoseSurrounding[doseTime] += energyToAdd;
          }
          else {
            electronDose[doseTime] += energyToAdd;
          }
          //add to voxel
        //  int[] pixelCoord = convertToPixelCoordinates(xn, yn, zn);
        //  voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += energyToAdd;
        }
        
        //update position and angle
        //update position and angle
        previousTheta = theta;
        previousPhi = phi;
        previousX = xn;
        previousY = yn;
        previousZ = zn;

        //here would be where I check if elastic or inelastic collision
        double RNDinelastic = Math.random();
        if (RNDinelastic  < Pinel) {
          
          //generate a charge if possible //this below is the FSE model
          /*
          //determine which element was hit
          double shellBindingEnergy = 0;
          Element collidedElement = null;
          int collidedShell = -1;
          double elementRND = Math.random();
          for (Element e : ionisationProbs.keySet()) {
            collidedShell = findIfElementIonised(e, ionisationProbs, elementRND);
            if (collidedShell >= 0) {
              collidedElement = e;
              break;
            }
          } 
          shellBindingEnergy = getShellBindingEnergy(collidedElement, collidedShell);
          double epsilon = getFSEEnergy(electronEnergy, shellBindingEnergy);
          double FSEEnergy = epsilon * electronEnergy - shellBindingEnergy;
          double sinSquaredAlpha = 0, sinSquaredGamma = 0;
          double FSEtheta = 0, FSEphi = 0, FSEpreviousTheta = 0, FSEpreviousPhi = 0, FSExNorm = 0, FSEyNorm = 0, FSEzNorm = 0;
          FSEpreviousTheta = previousTheta;
          FSEpreviousPhi = previousPhi;
          double minTrackEnergy = 0.05;  //this needs to be tested 
      //    double minTrackEnergy = 0.00;  //this needs to be tested 
          if (FSEEnergy > 0) {
            ionisationsPerPhotoelectron += 1;
            totalIonisationEvents[doseTime] += 1;
            totalShellBindingEnergy += shellBindingEnergy;
            if (FSEEnergy > minTrackEnergy) { // track a secondary electron if sufficient energy, this full cascade will be expensive but necessary for charge!!!!
              double tPrimary = (electronEnergy-FSEEnergy)/511; //t is in rest mass units. Need to change to stopping power en
              double tFSE = FSEEnergy/511;
              //alpha = angle of primary electron
              sinSquaredAlpha = (2 * epsilon) / (2 + tPrimary - tPrimary*epsilon);
              //gamma - angle of secondary electron
              sinSquaredGamma = 2*(1-epsilon) / (2 + tFSE*epsilon); 
            
              FSEtheta = Math.asin(Math.pow(sinSquaredGamma, 0.5));
              FSEphi = 2 * Math.PI * Math.random();
            
            
              FSEtheta = FSEpreviousTheta + FSEtheta;
              if (FSEtheta >= (2 * Math.PI)) {
                FSEtheta -= 2*Math.PI;
              }
              FSEphi = FSEpreviousPhi + FSEphi;
              if (FSEphi >= (2 * Math.PI)) {
                FSEphi -= 2*Math.PI;
              }
              FSExNorm = Math.sin(FSEtheta) * Math.cos(FSEphi);
              FSEyNorm = Math.sin(FSEtheta) * Math.sin(FSEphi);
              FSEzNorm = Math.cos(FSEtheta);
              
              
              //recursive so slow!!!!!!!!!!!
              trackPhotoelectron(coefCalc, timeStamp, FSEEnergy, xn, yn, zn, FSExNorm, FSEyNorm, FSEzNorm, FSEtheta, FSEphi, surrounding);
              
           //   if (surrounding == false) {
           //     if (entered == true) {
           //       electronDoseSurrounding[doseTime] += FSEEnergy;
           //     }
           //     else {
           //       electronDose[doseTime] += FSEEnergy;
           //     }
           //   }
              
              
              
            
            }
            else { //0 to cutoff
            //  totalIonisationEvents[doseTime] += 1;
            }
            //produce an Auger electron as well - only if it was a K shell for now. 
            produceAugerElectron(coefCalc, timeStamp, collidedShell, collidedElement, xn, yn, zn, surrounding);
          }
          //update the angle deflection of the primary
          theta = Math.asin(Math.pow(sinSquaredAlpha, 0.5));
          theta = previousTheta + theta;
          if (theta >= (2 * Math.PI)) {
            theta -= 2*Math.PI;
          }
          
          */
         // theta = previousTheta;
          
          
          //here I am going to do the GOS model
          

          
          //determine which element and shell was hit
          int doseTimeGOS = (int) (timeStamp/PULSE_BIN_LENGTH);
          if (doseTimeGOS < 0) {
            doseTimeGOS = 0;
          }
          double shellBindingEnergy = 0;
          boolean plasmon = false;
          Element collidedElement = null;
          int collidedShell = -1;
          
          //I want to check if it's an inner shell ionisation and if it is use the proper inner shell cross sections
          //determine if the interaction was inner shell or outer shell
          double RNDInner = Math.random();
          double elementRND = Math.random();
          if (RNDInner < Pinner) {
            //then this hit an inner shell
            for (Element e : ionisationProbs.keySet()) {
              collidedShell = findIfElementIonised(e, ionisationProbs, elementRND);
              if (collidedShell >= 0) {
                collidedElement = e;
                break;
              }
            } 
          }
          else {
            //hit an outer shell
            for (Element e : gosOuterIonisationProbs.keySet()) {
              int[] electrons = coefCalc.getNumValenceElectronsSubshells(e);
              int numInnerShells = electrons[1];
              collidedShell = numInnerShells;
              if (findIfOuterShellIonised(e, gosOuterIonisationProbs, elementRND) == true){
                collidedElement = e;
                break;
              }
            } 
          }
          
          /*
          for (Element e : gosIonisationProbs.keySet()) {
            collidedShell = findIfElementIonised(e, gosIonisationProbs, elementRND); //will need to adjust this for if plasmon
            if (collidedShell >= 0) {
              collidedElement = e;
              break;
            }
          } 
          */
          
          if (collidedShell == -1) {
            //then this is a collision with the conduction band 
            plasmon = true;
          }
          else {
            //shellBindingEnergy = getShellBindingEnergyGOS(collidedElement, collidedShell);
            shellBindingEnergy = getShellBindingEnergy(collidedElement, collidedShell);
          }
          
          
          //get the type of collision
          int type = 0;
          if (plasmon == false) {
            type = getGOSInelasticType(coefCalc.getGOSVariable(surrounding).get(collidedElement), collidedShell);
          }
          else {
            type = getGOSInelasticTypePlasmon(coefCalc.getPlasmonVariable(surrounding));
          }
          
          //get energy loss
          double a = coefCalc.returnAdjustment();
          double Uk = 0;
         // double Uk = shellBindingEnergy*1000;
          double Wk = 0, Qak = 0, Q = 0;
          if (plasmon == false) {
            Uk = shellBindingEnergy*1000;
            Wk = coefCalc.getWkMolecule(a, collidedElement, collidedShell, surrounding);
            Qak = getQak(electronEnergy, Wk, Uk);
          }
          else {
            Uk = 0;
            Wk = coefCalc.getWcbAll(surrounding);
            Qak = Wk;
          }
          double Wak = WkToWak(electronEnergy, Wk, Uk); //eV
          double Wdis = 3*Wak - 2*Uk;
          if (type == 0 || type == 1) {
            //then this was a distant collision
             
            //get recoil energy
            if (plasmon == true) {
              W = Wk/1000;
            }
            else {
              W = getEnergyLossDistant(Wdis, Uk)/1000; //in keV  
            }
            Q = coefCalc.getRecoilEnergyDistant(electronEnergy, Wak, Qak); //J
            //get theta (new to add on to previous)
            if (type == 1) {
              //transverse
              theta = previousTheta;
            }
            else {
              //longitudinal
             theta = getGOSPrimaryThetaLong(electronEnergy, Q, Wak, previousTheta);
            }
          }
          else {
            //a close collision
            if (plasmon == true) {
              W = Wk/1000;
            }
            else {
         //     double k = samplek(electronEnergy, Qak);
              double k = samplek(electronEnergy, Uk);
              W = k*(electronEnergy+Uk/1000); //keV
            }
            theta = getGOSPrimaryThetaClose(electronEnergy, W, previousTheta);
          }
          //now I need to send out the secondary electron
          //get an angle and energy then recursively call ofc
          double minTrackEnergy = 0.05;
          double SEPreviousTheta = previousTheta;
          double SEPreviousPhi = previousPhi;
          double SEEnergy = W - Uk/1000;
          double SETheta = 0, SEPhi = 0, SExNorm = 0, SEyNorm = 0, SEzNorm = 0;
          int[] pixelCoord = convertToPixelCoordinates(xn, yn, zn);
          if (SEEnergy > 0) {
      //      if (timeStamp <lastTime-(1*PULSE_BIN_LENGTH) & surrounding == false) {
            if (surrounding == false) {
            totalIonisationEvents[doseTimeGOS] += 1;
            if (timeStamp <lastTimeVox[pixelCoord[2]]-(1*PULSE_BIN_LENGTH)) { 
              
              totalIonisationEventsvResolved[doseTimeGOS] += 1;
              voxelIonisationsvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTimeGOS] += 1;
              if (atomicIonisations.containsKey(collidedElement)) {
                atomicIonisations.put(collidedElement, atomicIonisations.get(collidedElement)+1);
              }
              else {
                atomicIonisations.put(collidedElement, (long)1);
              }
              if(testIfInsideExposedArea(xn, yn, beam) == true) {
                if (atomicIonisationsExposed.containsKey(collidedElement)) {
                  atomicIonisationsExposed.put(collidedElement, atomicIonisationsExposed.get(collidedElement)+1);
                }
                else {
                  atomicIonisationsExposed.put(collidedElement, (long)1);
                }
              }
            }
            }
            ionisationsPerPhotoelectron += 1;
            avgUk += Uk;
            avgUkNum += 1;
            if (plasmon == true) {
              avgUk += W*1000;
            }
          //  totalIonisationEvents[doseTime] += 1; //is this right??? need to sort this as well!!!
            //don't do this above if calling recursively as done at the top
            totalShellBindingEnergy += shellBindingEnergy;
            if (SEEnergy > minTrackEnergy) {
           //   gosElectronDose[doseTime] += Uk/1000;
              //get theta
              if (type == 0 || type == 1) { //distant
                SETheta = secondaryThetaDistant(electronEnergy, Wak, Q, previousTheta);
              }
              else { //close
                SETheta = secondaryThetaClose(electronEnergy, W, SEPreviousTheta);
              }
              //get phi
              SEPhi = 2 * Math.PI * Math.random();
              SEPhi = SEPreviousPhi + SEPhi;
              if (SEPhi >= (2 * Math.PI)) {
                SEPhi -= 2*Math.PI;
              }
              //now get normals
              SExNorm = Math.sin(SETheta) * Math.cos(SEPhi);
              SEyNorm = Math.sin(SETheta) * Math.sin(SEPhi);
              
              SEzNorm = Math.cos(SETheta);
              //send it out with the correct timestamp
              
              trackPhotoelectron(coefCalc, timeStamp, SEEnergy, xn, yn, zn, SExNorm, SEyNorm, SEzNorm, SETheta, SEPhi, surrounding, false, beam);
              if (primaryElectron == true) { //only doing dose from the primary
                gosElectronDose[doseTimeGOS] += W;
                avgW += W;
                avgWNum += 1;
                //add to voxel
           //     voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTimeGOS] += W;
              }
              
            }
            else { //too low energy to track - work out what exactly I'm doing with dose! - need an SP way and a W way
              if (primaryElectron == true) { //only doing dose from the primary
                gosElectronDose[doseTimeGOS] += W;
                //add to voxel
         //       voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTimeGOS] += W;
              }
              gosElectronDosevResolved[doseTimeGOS] += SEEnergy;
              voxelEnergyvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTimeGOS] += SEEnergy;
           //   totalIonisationEvents[doseTimeGOS] += 1;
              //sort out how many extra ionisations this will cause
              double avgEnergy = coefCalc.getAvgInelasticEnergy(SEEnergy);
              avgEnergy = coefCalc.getPlasmaEnergy(surrounding)/1000;
              if (avgEnergy > 0 && surrounding == false) {
                int numIonisation = (int) (SEEnergy/avgEnergy);
                if (numIonisation > 0) {
                  totalIonisationEvents[doseTimeGOS] += numIonisation;
                  lowEnergyIonisations[doseTimeGOS] += numIonisation;
                  //decide what elements to add this to
                  Element hitElem = chooseLowEnElement(coefCalc, Pinner, gosOuterIonisationProbs, ionisationProbs);
                  if (timeStamp <lastTimeVox[pixelCoord[2]]-(1*PULSE_BIN_LENGTH)) {
                    totalIonisationEventsvResolved[doseTimeGOS] += numIonisation;
                    voxelIonisationsvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTimeGOS] += numIonisation;
                    if (atomicIonisations.containsKey(hitElem)) {
                      atomicIonisations.put(hitElem, atomicIonisations.get(hitElem)+numIonisation);
                    }
                    else {
                      atomicIonisations.put(hitElem, (long)numIonisation);
                    }
                    if(testIfInsideExposedArea(xn, yn, beam)) {
                      if (atomicIonisationsExposed.containsKey(hitElem)) {
                        atomicIonisationsExposed.put(hitElem, atomicIonisationsExposed.get(hitElem)+numIonisation);
                      }
                      else {
                        atomicIonisationsExposed.put(hitElem, (long)numIonisation);
                      }
                    }
                  }
                }
              }
            }
          //  electronEnergy -= W; 
            lossSinceLastUpdate += W;
            //produce Auger electon should only be if it is from an inner shell of an element more than 2
            produceAugerElectron(coefCalc, timeStamp, collidedShell, collidedElement, xn, yn, zn, surrounding, beam);
          }
            
          //I still need to add a cutoff in here and do dose time and ionisations
        }
        else {
          //do elastic
          theta = getElectronElasticTheta(electronEnergy, elasticProbs, previousTheta);
          
          
          
        //  theta = previousTheta;
          
        }

        //update angle and stuff - for now it is always an elastic interaction

        phi = getElectronElasticPhi(previousPhi);
        
   //     phi = previousPhi;
      //now further update the primary
        
        xNorm = Math.sin(theta) * Math.cos(phi);
        yNorm = Math.sin(theta) * Math.sin(phi);
        zNorm = Math.cos(theta);
        
        //update the energy and stopping Power and stuff
     //   electronEnergy -= energyLost; 
     //   lossSinceLastUpdate += energyLost;
        
        //GOS lambda
        if (electronEnergy > 0.5) {  //this will make it not quite behave right at edges :/
          //need to change the 0.5 to if it crosses an ionisation boundary  or just higher than highest binding energy shell
          //put in a get maxUk thing in coefCalc
          if (lossSinceLastUpdate > energyLossToUpdate) {
            electronEnergy -= lossSinceLastUpdate;
            if (simpleMC == false) {
            gosInelasticLambda = coefCalc.getGOSInel(false, electronEnergy);
            innerShellLamda = coefCalc.betheIonisationxSection(electronEnergy, false);
            gosOuterLambda = coefCalc.getGOSOuterLambda(surrounding);
            gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(surrounding, gosOuterLambda);
            gosIonisationProbs = coefCalc.getGOSShellProbs(false, gosInelasticLambda);
            if (startingInnerShellLambda > 0) {
              gosInelasticLambda = 1/(1/gosOuterLambda + 1/startingInnerShellLambda);
            }
            else {
              gosInelasticLambda = 1/gosOuterLambda;
            }
            }
            lossSinceLastUpdate = 0;
          }
        }
        else {
          electronEnergy -= lossSinceLastUpdate;
          if (simpleMC == false) {
          gosInelasticLambda = coefCalc.getGOSInel(false, electronEnergy);
          innerShellLamda = coefCalc.betheIonisationxSection(electronEnergy, false);
          gosOuterLambda = coefCalc.getGOSOuterLambda(surrounding);
          gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(surrounding, gosOuterLambda);
          gosIonisationProbs = coefCalc.getGOSShellProbs(false, gosInelasticLambda);
          if (startingInnerShellLambda > 0) {
            gosInelasticLambda = 1/(1/gosOuterLambda + 1/startingInnerShellLambda);
          }
          else {
            gosInelasticLambda = gosOuterLambda;
          }
          }
          lossSinceLastUpdate = 0;
        }
        
        stoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
        //get new lambdaT
        lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, false);
        FSExSection = getFSEXSection(electronEnergy);
        FSELambda = coefCalc.getFSELambda(FSExSection, false);
        

        
      //  lambdaT = 1/(1/lambdaEl + 1/FSELambda);
        if (gosInelasticLambda > 0) {
          lambdaT = 1/(1/lambdaEl + 1/gosInelasticLambda);
        }
        else {
          lambdaT = 1/(1/lambdaEl);
        }
        s = -lambdaT*Math.log(Math.random());
        elasticProbs = coefCalc.getElasticProbs(false);
        ionisationProbs = coefCalc.getAllShellProbs(false);
        //GOS ionisation probs
        Pinel = 1 - (lambdaT / lambdaEl);
        if (innerShellLamda > 0) {
          Pinner = gosInelasticLambda / innerShellLamda;
        }
        //update to new position
        xn = previousX + s * xNorm;
        yn = previousY + s * yNorm;
        zn = previousZ + s * zNorm;
      }
      else { //it's left the crystal if surrounding = false
        if (surrounding == false) {
          surrounding = true;  //will need to try and see what happens when it comes back in the crystal in both models
          
          
          exited = true;
          if (simpleMC == true) {
          //get the energy deposited before it left the crystal. - when I slice need to also do timestamps 
          double escapeDist = 1000 * getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm); //nm
          double FSEStoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
          double energyToEdge = FSEStoppingPower * escapeDist;
          if (energyToEdge < electronEnergy){ //the FSE has escaped
            double energyLostStep = 0, totFSEenLostLastStep = 0;
            double newEnergy = electronEnergy;
            for (int j = 0; j < 10; j++) { //I will need to play around with the amount of slicing when I am writing up
              energyLostStep = (escapeDist/10) * FSEStoppingPower;
              //add dose to timeStamp
              double timeToDistance = getTimeToDistance(newEnergy, escapeDist/10);
              int doseTime = (int) ((timeStamp + (timeToDistance/2))/PULSE_BIN_LENGTH); // over 2 as adding it half way
              timeStamp += timeToDistance;
              if (doseTime < 0) {
                doseTime = 0;
              }
              if (energyLostStep < 0) {
                System.out.print("Test");
              }
              dose[doseTime] += energyLostStep;  //still just adding keV
              if (entered == false) {
              electronDose[doseTime] += energyLostStep;
              
              }
              else {
                electronDoseSurrounding[doseTime] += energyLostStep;
              }
              newEnergy -= energyLostStep;
              FSEStoppingPower = coefCalc.getStoppingPower(newEnergy, false);
              if (newEnergy < 0.05) {
                exited = true;
                if (newEnergy > 0) {
                  dose[doseTime] += newEnergy;
                  if (entered == false) {
                    electronDose[doseTime] += newEnergy;
                    
                    }
                    else {
                      electronDoseSurrounding[doseTime] += newEnergy;
                    }
                }
                break;
              }
            } 
            //calc escaped energy
            if (entered == false) {
              escapedEnergy += newEnergy;
            }
            
          }
          else {
            //didn't quite escape, add the electron energy to the dose
            double timeToDistance = getTimeToDistance(electronEnergy, s);
            int doseTime = (int) ((timeStamp + (timeToDistance/2))/PULSE_BIN_LENGTH);
            timeStamp += timeToDistance;
            if (doseTime < 0) {
              doseTime = 0;
            }
            if (electronEnergy < 0) {
              System.out.print("Test");
            }
            dose[doseTime] += electronEnergy;  //still just adding keV
            if (entered == false) {
            electronDose[doseTime] += electronEnergy;
            
            }
            else {
              electronDoseSurrounding[doseTime] += electronEnergy;
            }
          }
          }
        }
        else { //it's one I'm tracking from the surrounding
          //check if it's still worth tracking it form the surrounding anymore - i.e still in track range
          double maxDistanceNM = (electronEnergy/coefCalc.getStoppingPower(electronEnergy, surrounding)); 
          if (Math.abs(xn) > maxDistanceNM + XDimension/2 || Math.abs(yn) > maxDistanceNM + YDimension/2 || Math.abs(zn) > maxDistanceNM + ZDimension/2) {
            exited = true;
          }
          else {
             //if it is still worth tracking I need to do everything exactly the same as if it was in the crystal...
            //find energy lost
            energyLost = s * stoppingPower;
            //update timeStamp
            double timeToDistance = getTimeToDistance(electronEnergy, s);
            timeStamp += timeToDistance;
          //update position and angle
            //update position and angle
            previousTheta = theta;
            previousPhi = phi;
            previousX = xn;
            previousY = yn;
            previousZ = zn;
            
            //update angle and stuff - for now it is always an elastic interaction
            theta = getElectronElasticTheta(electronEnergy, elasticProbs, previousTheta);
            phi = getElectronElasticPhi(previousPhi);
          //now further update the primary
            
            xNorm = Math.sin(theta) * Math.cos(phi);
            yNorm = Math.sin(theta) * Math.sin(phi);
            zNorm = Math.cos(theta);
            
            //update the energy and stopping Power and stuff
            electronEnergy -= energyLost; 
            stoppingPower = coefCalc.getStoppingPower(electronEnergy, surrounding);
            //get new lambdaT
            lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, surrounding);
            
            lambdaT = lambdaEl;
            s = -lambdaT*Math.log(Math.random());
            elasticProbs = coefCalc.getElasticProbs(surrounding);
            
            //update to new position
            xn = previousX + s * xNorm;
            yn = previousY + s * yNorm;
            zn = previousZ + s * zNorm;
          }
        }
      }
      if (electronEnergy < 0.05) {
        exited = true;
        //add the dose if died in the crystal
        if (isMicrocrystalAt(previousX, previousY, previousZ) == true) {
          int doseTime = (int) (timeStamp/PULSE_BIN_LENGTH);
          if (doseTime < 0) {
            doseTime = 0;
          }
          if (primaryElectron == true) { //only doing dose from the primary
            dose[doseTime] += electronEnergy;
            gosElectronDose[doseTime] += electronEnergy;
            if (entered == false) {
              electronDose[doseTime] += electronEnergy;
            }
            else {
              electronDoseSurrounding[doseTime] += electronEnergy;
            }
            //add to voxel
            int[] pixelCoord = convertToPixelCoordinates(previousX, previousY, previousZ);
        //    voxelEnergy[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += electronEnergy;
          }
          if (simpleMC == false) {
          gosElectronDosevResolved[doseTime] += electronEnergy;
          int[] pixelCoord = convertToPixelCoordinates(previousX, previousY, previousZ);
          voxelEnergyvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += electronEnergy;
          double avgEnergy = coefCalc.getAvgInelasticEnergy(electronEnergy); //can make this plasmon energy
          avgEnergy = coefCalc.getPlasmaEnergy(surrounding)/1000;
          if (avgEnergy > 0) {
            int numIonisation = (int) (electronEnergy/avgEnergy);
            if (numIonisation > 0) {
              totalIonisationEvents[doseTime] += numIonisation;
              lowEnergyIonisations[doseTime] += numIonisation;
              //decide what elements to add this to
              Element hitElem = chooseLowEnElement(coefCalc, Pinner, gosOuterIonisationProbs, ionisationProbs);
              if (timeStamp <lastTimeVox[pixelCoord[2]]-(1*PULSE_BIN_LENGTH)) {
                totalIonisationEventsvResolved[doseTime] += numIonisation;
                voxelIonisationsvResolved[pixelCoord[0]][pixelCoord[1]][pixelCoord[2]][doseTime] += numIonisation;
                if (atomicIonisations.containsKey(hitElem)) {
                  atomicIonisations.put(hitElem, atomicIonisations.get(hitElem)+numIonisation);
                }
                else {
                  atomicIonisations.put(hitElem, (long)numIonisation);
                }
                if(testIfInsideExposedArea(xn, yn, beam) == true) {
                  if (atomicIonisationsExposed.containsKey(hitElem)) {
                    atomicIonisationsExposed.put(hitElem, atomicIonisationsExposed.get(hitElem)+numIonisation);
                  }
                  else {
                    atomicIonisationsExposed.put(hitElem, (long)numIonisation);
                  }
                }
              }
            } 
          }
        }
        }
      }
    }
    
  }
}
