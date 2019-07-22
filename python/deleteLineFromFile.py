#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re

goodLines = []
fileobj = open(r"C:\Users\IBM_ADMIN\Documents\Work Document\TasksAndIssues\CurrentWorkWith\MDMUpgrade\UnitTest\FailureList0717.txt","r")
for line in fileobj.readlines():
	#some actions
	line = line.strip()
	if (re.search("^com.ibm.idm.unit.rel|^com.ibm.idm.unit.testSuites|^com.ibm.idm.unit.sprint",line) == None) and (re.search("java.io.IOException: public test case not supported",line) == None) and (re.search("\.java:\d+",line) == None) and re.search("\(Native Method\)",line) == None and (len(line) > 0) and (re.search("\(Unknown Source\)",line) == None):
		goodLines.append(line)
fileobj.close()

#for line in goodLines:
#	print(line + "\n")

fileObj = open(r"C:\Users\IBM_ADMIN\Documents\Work Document\TasksAndIssues\CurrentWorkWith\MDMUpgrade\UnitTest\FailureList0717_Clean.txt","w")
for line in goodLines:
	fileObj.write(line + "\n")
fileObj.close()
	