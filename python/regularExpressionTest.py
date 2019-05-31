#!/usr/bin/env python
# -*- coding: utf-8 -*-
#the first line is used only in unix when you want to make python file executable
#the second is used to declare a encoding other than utf-8. if no this line, the code file is treated as UTF-8

import re

#result.txt has such lines:
#./src/com/ibm/idm/unit/addr/extension/AddressExtensionPositiveTest.java:6
#./src/com/ibm/idm/unit/adminContequivExtension/AdminContequivExtensionNegative.java:10

f = open(r"c:\result.txt")
sum = 0
for line in f.readlines():
	matchGroups = re.search(":(\d+)",line)
	numStr = matchGroups.groups(1)[0]
	print(numStr)
	sum = sum + int(numStr)
f.close()
print("Total test count: " + str(sum))