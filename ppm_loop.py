import os
import csv

def loop(inputFile="input.txt", changeMe='$', metric='DWD'):
    # set of values to change

    same_DWD = False
    DWD = []
    lines = []
    ppm = 0.0
    counter = -1;
    while  same_DWD == False:
        ppm += 0.1
        counter += 1
        reps = {'$':str(ppm)}
        newInputFile = inputFile.replace('.txt', '-updated.txt')
        f = open(inputFile, 'r')
        g = open(newInputFile, 'w')
        '''
        for l in f.readlines():
            for i, j in reps.iteritems():
               # g.write(l.replace(i, j))
               l = l.replace(i, j)
               g.write(l)
           #  g.write(l.replace(changeMe, str(ppm)))
        '''
        for l in f.readlines():
            l = l.replace(changeMe, str(ppm))
            l = l.replace('@', '30')
            lines.append(l)
        for value in lines:
            g.write(value)
        f.close()
        g.close()

        # run new RD3D file
        os.system('java -jar raddose3d.jar -i ' + newInputFile)

        # parse the output file
        f = open('./output-Summary.csv', 'r')
        for i, l in enumerate(f.readlines()):
            l = l.replace(' ', '')
            if i == 0:
                # get metric place in csv file
                ind = l.split(',').index(metric)
            else:
                # extract the metric value
                met = float(l.split(',')[ind])
                DWD.append(met)
                break
        f.close()
        # now need to compare previous to exit the loop
        if (counter > 1) and (DWD[counter - 2] > 0.0):
            if (abs((DWD[counter] - DWD[counter - 1])/DWD[counter - 1]) < 0.01) and (abs((DWD[counter] - DWD[counter - 2])/DWD[ counter - 2]) < 0.01) \
                and (abs((DWD[counter - 1] - DWD[counter - 2])/DWD[counter - 2]) < 0.01):
                same_DWD = True

    #extract all of the inputs
    f = open(newInputFile, 'r')
    xtal_x = ''
    xtal_y = ''
    xstal_z = ''
    xstal_area = 0.
    xtal_vol = 0.
    beam_x = ''
    beam_y = ''
    beam_area = 0.
    beam_energy = ''
    area_ratio = 0.
    pe_escape = ''

    for l in f.readlines():
        if l != '\n':
            words = l.strip().split()
            if words[0] == 'Dimensions':
                xstal_x = words[1]
                xstal_y = words[2]
                xstal_z = words[3]
            if words[0] == 'Energy':
                beam_energy = words[1]
            if words[0] == 'Collimation':
                beam_x = words[2]
                beam_y = words[3]
            if words[0] == 'CALCULATEPEESCAPE':
                pe_escape = words[1]

    beam_area = float(beam_x) * float(beam_y)
    xstal_area = float(xstal_x) * float(xstal_y)
    xstal_volume = xstal_area * float(xstal_z)
    area_ratio = xstal_area / beam_area
    f.close()
    actual_ppm = ppm - 0.2


    # write new csv file

    with open('./ppm.csv', 'wb') as csvfile:
        ppmwriter = csv.writer(csvfile)
        ppmwriter.writerow(['pe_escape','xstal x', 'xstal y', 'xstal z', 'xstal area', 'xstal volume', 'beam energy', 'beam x', 'beam y', 'beam area', 'area ratio', 'ppm'])
        ppmwriter.writerow([pe_escape, xstal_x, xstal_y, xstal_z, xstal_area, xstal_volume, beam_energy, beam_x, beam_y, beam_area, area_ratio, actual_ppm])
    '''
    #append to existing file
    with open('./ppm.csv', 'ab') as csvfile:
        ppmwriter = csv.writer(csvfile)
        ppmwriter.writerow(
            [pe_escape, xstal_x, xstal_y, xstal_z, xstal_area, xstal_volume, beam_energy, beam_x, beam_y, beam_area,
             area_ratio, actual_ppm])
    '''

if __name__ == "__main__":
    loop()