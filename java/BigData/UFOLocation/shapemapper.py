#! /usr/bin/env python

import sys
for line in sys.stdin:
    line = line.strip()
    parts = line.split("\t")
    if len(parts) == 6 and parts[3] != '':
        print("%s\t%s" % (parts[3], 1))