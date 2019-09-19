import os
import matplotlib.pyplot as plt
from decimal import Decimal

def PlotRDE(inputFile='MyInput.txt'):
    # run new RD3D file
    os.system('java -jar raddose3d.jar -i ' + inputFile)
	
    # parse the output file
	
    RDEs = []
    angles = []
    half = []
    tooLow = False
    f = open('./output-RDE.csv', 'r')
    for i, l in enumerate(f.readlines()):
        metrics = l.split(',')
        if i > 0:
            angles.append(round(Decimal(metrics[1]),2))
            RDEs.append(round(Decimal(metrics[2]),3))
            half.append(0.5)
            if round(Decimal(metrics[2]),3) < 0.5:
                tooLow = True
    f.close()
	
    plt.plot(angles, RDEs)
    if tooLow == True:
        plt.plot(angles, half, 'r--')
    plt.xlabel('Angles in degrees')
    plt.ylabel('RDE')
    plt.title('Relative Diffraction Efficiency')
    plt.show()
	

if __name__ == "__main__":
    PlotRDE()