import os
import csv

def loop(inputFile='MyInput.txt', changeMe='$', metric='DWD', metric2 ='DiffractionEfficiency'):

    # set of values to change
    vals = [1.0, 1.25, 1.5, 1.75, 2]

    extractedMetrics = []
    overall = []

    for v in vals:
        lines = []
        beam_x = float(v) * 10
        beam_y = float(v) * 100
        # change input file for current value
        newInputFile = inputFile.replace('.txt', '-updated.txt')
        f = open(inputFile, 'r')
        g = open(newInputFile, 'w')
        for l in f.readlines():
            l = l.replace('$', str(beam_x))
            l = l.replace('@', str(beam_y))
            lines.append(l)
        for value in lines:
            g.write(value)
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
        os.system('java -jar raddose3d.jar -i ' + newInputFile)

       # parse the output file
        f = open('./output-Summary.csv', 'r')
        for i, l in enumerate(f.readlines()):
            l = l.replace(' ', '')
            met = l.split(',')
            overall.append(met)
        f.close()


    '''
    # write new csv file
    f = open('./loop-metrics.csv', 'w')
    f.write(','.join(map(str, extractedMetrics)))
    f.close()
    '''

    f = open('./translate.csv', 'wb')
    writer = csv.writer(f)
    writer.writerows(overall)
    f.close()

if __name__ == "__main__":
    loop()
