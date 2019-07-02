#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re

goodLines = []
fileobj = open(r"C:\Users\IBM_ADMIN\Documents\Work Document\TasksAndIssues\CurrentWorkWith\MDMUpgrade\UnitTest\FailureList0627.txt","r")
for line in fileobj.readlines():
	#some actions
	line = line.strip()
	if (re.search("\.java:\d+",line) == None) and re.search("\(Native Method\)",line) == None and (len(line) > 0) and (re.search("\(Unknown Source\)",line) == None):
		goodLines.append(line)
fileobj.close()

#for line in goodLines:
#	print(line + "\n")

fileObj = open(r"C:\Users\IBM_ADMIN\Documents\Work Document\TasksAndIssues\CurrentWorkWith\MDMUpgrade\UnitTest\FailureList0627_Clean.txt","w")
for line in goodLines:
	fileObj.write(line + "\n")
fileObj.close()
	