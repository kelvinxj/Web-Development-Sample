import sys

if(len(sys.argv) == 1):
    print("usage: python getThreadHungStackTrace.py fileName")
    sys.exit(0)

linesOfLog = 0
if(len(sys.argv) == 3):
    linesOfLog = int(sys.argv[2])
    

fileName = sys.argv[1]
#print("you will check file" + fileName)

hungBegin = False
timeBegin = False
logs = []

fileObj = open(fileName,"r",encoding="utf-8")
linesToLog = 0
for line in fileObj.readlines():
    line = line.strip()
    if(line.startswith("[")):
        timeBegin = True        
        if("has been active" in line):
            hungBegin = True
            linesToLog = 1
        else:
            hungBegin = False
    else:
        timeBegin = False
        
    if hungBegin:
        if(linesOfLog == 0):
            if not(timeBegin):
                logs.append("\t" + line)
            else:
                logs.append(line)
        else:
            if linesToLog<= linesOfLog:
                if not(timeBegin):
                    logs.append("\t" + line)
                else:
                    logs.append(line)
                linesToLog = linesToLog + 1

for line in logs:
    print(line)
    