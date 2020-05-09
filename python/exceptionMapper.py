#!/usr/bin/env python
# -*- coding: utf-8 -*-
import sys
for line in sys.stdin:
    line = line.strip()
    fields = line.split("\t")
    print(fields[0] + "\t1")