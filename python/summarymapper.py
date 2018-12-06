#! /usr/bin/env python

import sys
for line in sys.stdin:
    line = line.strip()
    print("%s\t%s" % ("Total",1))
    parts = line.split("\t")
    if len(parts) != 6:
        print("%s\t%s" % ("Bad line",1))
    else:
        #print sighted, recorded, location, shape, duration, description
        if parts[0] != '':
            print("%s\t%s" % ("Sighted",1))
        if parts[1] != '':
            print("%s\t%s" % ("Recorded",1))
        if parts[2] != '':
            print("%s\t%s" % ("Location",1))
        if parts[3] != '':
            print("%s\t%s" % ("Shape",1))
        if parts[4] != '':
            print("%s\t%s" % ("Duration",1))
        if parts[5] != '':
            print("%s\t%s" % ("Description",1))
