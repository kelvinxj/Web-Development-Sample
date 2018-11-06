#! /usr/bin/env python

import sys
for line in sys.stdin:
    line = line.strip()
    words = line.split()
    #all characters should be lower cased
    for word in words:
        print ("%s\t%s" % (word.lower(),1))