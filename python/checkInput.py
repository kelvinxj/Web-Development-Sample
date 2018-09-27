#!/usr/bin/evn python
# -*- coding: utf-8 -*-
import sys
sumComplete = 0
sumIncomplete = 0
for line in sys.stdin:
    line = line.strip()
    words = line.split("\t")
    if(len(words) != 6):
        sumIncomplete += 1
    else:
        sumComplete += 1
print(("Complete records: %s; Incomplete records:%s") % (sumComplete, sumIncomplete))