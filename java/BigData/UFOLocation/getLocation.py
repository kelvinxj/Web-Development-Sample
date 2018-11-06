#! /usr/bin/env python

import sys
import re

for line in sys.stdin:
    line = line.strip();
    parts = line.split('\t')
    if len(parts) == 6:
        if parts[2] != "":
            location = parts[2]
            match  = re.search('([a-zA-Z]{2})[^a-zA-Z]*$',location)
            if match:
                print(match.group(1).upper() + ":" + location)
        