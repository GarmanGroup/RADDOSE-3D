import math,0
import csv,0
import numpy,0
import os,0
,0
,0
def loop():,0
for filename in os.listdir(os.getcwd()):,0
,0
f = open(filename, 'r'),0
overall = [],0
for line in f:,0
values = line.strip().split(','),0
#get the drop shell,0
shellIndex = getDropShell(values[0]),0
values.append(str(shellIndex)),0
overall.append(values),0
f.close(),0
g = open(filename, 'wb'),0
writer = csv.writer(g),0
writer.writerows(overall),0
g.close(),0
,0
,0
,0
,0
def getDropShell(transitionString):,0
dropShellString = '',0
if len(transitionString) > 0:,0
if transitionString[0] == 'K':,0
dropShellString = transitionString[1:3],0
else:,0
dropShellString = transitionString[2:4],0
,0
dropShell = 0,0
if dropShellString == 'L1':,0
dropShell = 1,0
elif dropShellString == 'L2':,0
dropShell = 2,0
elif dropShellString == 'L3':,0
dropShell = 3,0
elif dropShellString == 'M1':,0
dropShell = 4,0
elif dropShellString == 'M2':,0
dropShell = 5,0
elif dropShellString == 'M3':,0
dropShell = 6,0
elif dropShellString == 'M4':,0
dropShell = 7,0
elif dropShellString == 'M5':,0
dropShell = 8,0
elif dropShellString == 'N1':,0
dropShell = 9,0
elif dropShellString == 'N2':,0
dropShell = 10,0
elif dropShellString == 'N3':,0
dropShell = 11,0
elif dropShellString == 'N4':,0
dropShell = 12,0
,0
return dropShell,0
,0
,0
,0
,0
"if __name__ == ""__main__"":",0
loop(),0
,0
