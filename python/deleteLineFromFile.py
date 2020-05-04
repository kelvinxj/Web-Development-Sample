#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re
import sys

goodLines = []
argCount = len(sys.argv)
if(argCount == 1):
    print("usage: deleteLineFromFile.py file")
    sys.exit(1)
else:
    print("good argcount")
    
resultFileName = str(sys.argv[1])
print("begin to process...")
#resultFileName = r"C:\Users\JiaXie\CrashPlan\My document\Work document\TasksAndIssues\CurrentWorkWith\Sprint21\RegressionTest\LocalResult0417.txt"
fileobj = open(resultFileName,"r",encoding="utf-8")
for line in fileobj.readlines():
	#some actions
	line = line.strip()
	if (re.search("^com.ibm.idm.unit.rel|^com.ibm.idm.unit.testSuites|^com.ibm.idm.unit.sprint",line) == None) \
		and (re.search("java.io.IOException: public test case not supported",line) == None) \
		and (re.search("\.java:\d+",line) == None) \
		and re.search("\(Native Method\)",line) == None \
		and (len(line) > 0) \
		and (re.search("\(Unknown Source\)",line) == None):
		goodLines.append(line)
fileobj.close()

#for line in goodLines:
#	print(line + "\n")

fileObj = open(resultFileName + ".clean","w",encoding="utf-8")
for line in goodLines:
	fileObj.write(line + "\n")
fileObj.close()
	
