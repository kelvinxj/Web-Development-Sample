#!/usr/bin/env python
# -*- coding: utf-8 -*-
import sys
import datetime
minDate = ""
maxDate = ""
currentDate = ""
timeFormat = '%Y-%m-%d %H:%M:%S,%f'
for line in sys.stdin:
    line = line.strip()
    fields = line.split("\t")
    if(minDate == ""):
        minDate = datetime.datetime.strptime(fields[0], timeFormat)
        maxDate = datetime.datetime.strptime(fields[0], timeFormat)
        currentDate = minDate
    else:
        currentDate = datetime.datetime.strptime(fields[0], timeFormat)
        minDate = minDate if currentDate > minDate else currentDate
        maxDate = maxDate if currentDate < maxDate else currentDate
        
print("minDate: " + minDate.strftime(timeFormat) + "; maxDate: " + maxDate.strftime(timeFormat))