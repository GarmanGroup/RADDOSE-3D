"""
Created on Tue Nov 28 19:26:52 2023

@author: Patrick McCubbin

Script to generate a modelled beam profile to input to RADDOSE-3D as a .pgm file
(creates a .png file that can be straightforwardly converted to .pgm by external software)

computePseudoVoigt() could be changed to a different function depending on the desired model for the beam
"""
import sys
import numpy as np
import matplotlib.pyplot as plt
from PIL import Image as im

def populateArray(FWHMx, FWHMy, h_gauss, image_width, image_height, pixel_width, pixel_height):
	x = np.arange(-image_width/2, image_width/2, pixel_width)
	y = np.arange(-image_height/2, image_height/2, pixel_height)
	x, y = np.meshgrid(x, y)
	z = computePseudoVoigt(x, y, FWHMx, FWHMy, h_gauss)
	return z

def computePseudoVoigt(x, y, FWHMx, FWHMy, h_gauss):
	h_lorentz = 1 - h_gauss
	calc1 = 4*((x/FWHMx)**2+(y/FWHMy)**2)
	calc2 = (h_gauss*(2**(-calc1)))+(h_lorentz/(1+calc1)) # should be 0 < value < 1
	return calc2

def displayHeatmap(array):
	plt.imshow(array, cmap='hot', interpolation='nearest')
	plt.show()
	
def displayGrayscale(array):
	plt.imshow(array, cmap='gray', interpolation='nearest')
	plt.show()
	
def pngwrite(array, filename):
	array = (array * 65535).astype(np.uint16) # 65535 because this is the max value for 16 bit unsigned int
	profile = im.fromarray(array)
	#profile.show()
	# Issue with writing 16 bit pgm using pillow, so export as 16 bit png, then convert with external software
	filename = str(filename) + '.png'
	profile.save(filename)

def main():
	result = populateArray(3.0, 1.5, 0.86, 15, 15, 0.05, 0.05)
	#displayHeatmap(result)
	#displayGrayscale(result)
	pngwrite(result, 'pseudoVoigtProfile')
	
if __name__ == '__main__':
	sys.exit(main())

