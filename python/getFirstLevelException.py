import sys
if(len(sys.argv) == 1):
    print("usage: python thisFile.py exceptionFileName")

fileName = sys.argv[1];
fileObj = open(fileName,"r",encoding="utf-8")
beginSearch = False
goodLines = []
for line in fileObj.readlines():
    line = line.strip()
    if(line.startswith("[")):
        beginSearch = True
    if(beginSearch and not(line.startswith("["))):
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
sortedList = sorted(errorLines,key=lambda x:x[1],reverse=True)
for item in sortedList:
    print("count:" + str(item[1]) + "; " +item[0])
    