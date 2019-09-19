import os
import csv

def loop(inputFile='MyInput.txt', changeMe='$', metric='DWD', metric2 ='MaxDose'):

    # set of values to change
    vals = [2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22.5, 25, 27.5, 30]

    extractedMetrics = []
    overall = [["Beam Energy", "DWD", "MaxDose"]]

    for v in vals:
       # ppm = 10/float(v)
        # ppm = 0.2
        # change input file for current value
        newInputFile = inputFile.replace('.txt', '-updated.txt')
        f = open(inputFile, 'r')
        g = open(newInputFile, 'w')
        for l in f.readlines():
            g.write(l.replace(changeMe, str(v)))
        f.close()
        g.close()
        '''
        newerInputFile = newInputFile.replace('.txt', '-updated.txt')
        f = open(newInputFile, 'r')
        g = open(newerInputFile, 'w')
        for l in f.readlines():
            g.write(l.replace(alsoChangeMe, str(ppm)))
        f.close()
        g.close()
        '''

        # run new RD3D file
       # os.system('java -jar RD3DDone.jar -i ' + newerInputFile)
        os.system('java -jar raddose3d-minpol.jar -i ' + newInputFile)

       # parse the output file
        f = open('./output-Summary.csv', 'r')
        for i, l in enumerate(f.readlines()):
            l = l.replace(' ', '')
            if i == 0:
                # get metric place in csv file
                ind = l.split(',').index(metric)
                ind2 = l.split(',').index(metric2)
            else:
                # extract the metric value
                met = float(l.split(',')[ind])
                met2 = float(l.split(',')[ind2])
                extractedMetrics.append(met)
                break
        f.close()
        overall.append([v, met, met2])


    '''
    # write new csv file
    f = open('./loop-metrics.csv', 'w')
    f.write(','.join(map(str, extractedMetrics)))
    f.close()
    '''

    f = open('./fluorescence.csv', 'wb')
    writer = csv.writer(f)
    writer.writerows(overall)
    f.close()

if __name__ == "__main__":
    loop()
