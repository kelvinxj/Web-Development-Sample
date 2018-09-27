#!/usr/bin/evn python

# -*- coding: utf-8 -*-

import sys

print sys.path
print sys.path[0]

#fh is File object
fh=open(r"C:\Users\IBM_ADMIN\Box Sync\MySource\python\err.log")

#read a line from file obj.
#print fh.readline()

#get all lines from a file
alllines = fh.readlines()
print len(alllines)
print "first line:" + alllines[0]
print "last line:" + alllines[len(alllines) -1]

#count the number of "collapseparty"
count = 0
strErr = ''
for line in alllines:
    if '<ErrorMessage>' in line:
        count = count+1
        strErr = line.replace('<ErrorMessage>','').replace('</ErrorMessage>','')
        print strErr
print "Total count of CollapsePartiesWithRules:" + str(count)

fh.close()
