#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys
current = ""
word = ""
sum = 0
for line in sys.stdin:
	line = line.split("\t")
	word = line[0].strip()
	value = line[1].strip()
	if(word == current):
		sum = sum+int(value)
	else:
		if(current != ""):
			print(current + ":" + str(sum))
		sum=0
		sum = sum+int(value)
		current = word
