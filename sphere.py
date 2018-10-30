import csv

def loop():
    vertices = [["x", "y", "z"]]
    indices = [["x", "y", "z"]]
    f = open('sphere3.obj', 'r')
    for lines in f:
        line = lines.strip().split(' ')
        if line[0] == 'v':
            vertices.append(['{' + line[1] + ',', line[2] + ',', line[3] + '},'])

        if line[0] == 'f':
            indexx = line[1].split('//')[0]
            indexy = line[2].split('//')[0]
            indexz = line[3].split('//')[0]
            indices.append(['{' + indexx + ',', indexy + ',', indexz + '},'])
    f.close()

    f = open('./vertices.csv', 'wb')
    writer = csv.writer(f)
    writer.writerows(vertices)
    f.close()

    f = open('./indices.csv', 'wb')
    writer = csv.writer(f)
    writer.writerows(indices)
    f.close()

if __name__ == "__main__":
    loop()