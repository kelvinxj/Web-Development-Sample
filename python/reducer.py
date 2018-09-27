#!/usr/bin/evn python
# -*- coding: utf-8 -*-

import sys
current = ""
for line in sys.stdin:
    line = line.split(";")
    str1 = line[0].strip()
    print(str1)
