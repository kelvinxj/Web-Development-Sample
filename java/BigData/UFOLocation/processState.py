#! /usr/bin/env python

import sys
for line in sys.stdin:
    line = line.strip()
    parts = line.split('\t')
    print(parts[2].strip() + "\t" + parts[0].strip())