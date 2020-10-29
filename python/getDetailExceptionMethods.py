import sys
if(len(sys.argv) == 1):
    print("usage: python thisFile.py exceptionFileName")

fileName = sys.argv[1];
fileObj = open(fileName,"r",encoding="utf-8")
beginSearch = False
goodLines = []
for line in fileObj.readlines():
    line = line.strip()
    if(line == "at java.net.SocketInputStream.socketRead0(Native Method)"):
       beginSearch = True
    elif (line.startswith("[")):
        beginSearch = False
        
    if(beginSearch):
        if(line.startswith("at com.ibm.idm.mdm")):
            goodLines.append(line)
            beginSearch = False
        elif(line.startswith("at com.dwl.tcrm")):
            goodLines.append(line)
            beginSearch = False
            
goodLines.sort()
#count:
currentLine = ""
currentKindCount = 0
errorLines = []
for line in goodLines:
    if(currentLine == ""):
        currentKindCount = currentKindCount + 1
        currentLine = line
    elif (currentLine == line):
        currentKindCount = currentKindCount + 1
    elif(currentLine != line):
        errorLines.append((currentLine,currentKindCount))
        currentKindCount = 1
        currentLine = line
errorLines.append((currentLine,currentKindCount))

for item in sorted(errorLines,key=lambda x:x[1],reverse=True):
    print("count:" + str(item[1]) + "; " + item[0])
