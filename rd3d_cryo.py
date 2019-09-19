import os
import csv

def loop(inputFile='inputCryo.txt', changeMe='$', metric='DWD', metric2='DiffractionEfficiency'):

    # set of values to change
    vals = [50]
    beam_rat = [0.0, 50.0, 100.0, 150.0, 200.0, 250.0, 300.0, 350.0, 400.0, 450.0, 500.0, 550.0, 600.0, 650.0, 700.0, 750.0, 800.0, 850.0, 900.0, 950.0, 1000.0 ]
    overall = [["Crystal Size", "ppm", "Beam size", "Concentration", "DWD", "DE"]]
    extractedMetrics = []
    beam_size = 0

    for v in vals: #every crystal size
       # ppm = 10/float(v)
        ppm = 20 * (1/float(v))
        for ratio in beam_rat:
            lines = []
            beam_size = v * 2
            carbon = ratio * 2
            hydrogen = ratio * 4
            # change input file for current value
            newInputFile = inputFile.replace('.txt', '-updated.txt')
            f = open(inputFile, 'r')
            g = open(newInputFile, 'w')
            for l in f.readlines():
                l = l.replace('$', str(v))
                l = l.replace('?', str(ppm))
                l = l.replace('@', str(beam_size))
                l = l.replace('%', str(ratio))
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
            os.system('java -jar raddose3d-ppmforce.jar -i ' + newInputFile)

            # parse the output file
            f = open('./output-Summary.csv', 'r')
            met = 0.
            met2 = 0.
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
            overall.append([v, ppm, beam_size, ratio, met, met2])

    '''
    # write new csv file
    f = open('./loop-metrics.csv', 'w')
    f.write(','.join(map(str, extractedMetrics)))
    f.close()
    '''

    f = open('./cryo.csv', 'wb')
    writer = csv.writer(f)
    writer.writerows(overall)
    f.close()

if __name__ == "__main__":
    loop()
