import os
import csv

def loop(inputFile='testinput.txt', changeMe='$', metric='DWD', metric2 ='DiffractionEfficiency'):

    # set of values to change
   # vals = [1, 2, 5, 10, 20]
    vals = [100]
    beam_energy = [5.4, 8, 10, 12.4, 15, 17.5, 20, 22.2, 25, 30, 35, 40, 50, 60]
   # beam_energy = [7.5, 11, 14]
    overall = [["Beam Energy", "DWD", "DiffractionEfficiency"]]

    extractedMetrics = []
    beam_size = 0

    for v in vals: #every crystal size
       # ppm = 10/float(v)
        ppm = 20 * (1/float(v))
        for ratio in beam_energy:
            lines = []
            beam_size = float(v)
            # change input file for current value
            newInputFile = inputFile.replace('.txt', '-updated.txt')
            f = open(inputFile, 'r')
            g = open(newInputFile, 'w')
            for l in f.readlines():
                l = l.replace('$', str(v))
                l = l.replace('?', str(ppm))
                l = l.replace('@', str(ratio))
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


            # os.system('java -jar RD3DDone.jar -i ' + newerInputFile)
            os.system('java -jar raddose3d.jar -i ' + newInputFile)

            # parse the output file
            f = open('./output-Summary.csv', 'r')
            met = 0
            met2 = 0
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
            overall.append([ratio, met, met2])

    '''
    # write new csv file
    f = open('./loop-metrics.csv', 'w')
    f.write(','.join(map(str, extractedMetrics)))
    f.close()
    '''

    f = open('./roughbeam-energy.csv', 'wb')
    writer = csv.writer(f)
    writer.writerows(overall)
    f.close()

if __name__ == "__main__":
    loop()
