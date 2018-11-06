#! /usr/bin/env python

import sys
import re
value = 0
unit = ""
for line in sys.stdin:
    line = line.strip()
    parts = line.split('\t')
    if len(parts) == 6 and parts[3] != '' and parts[4] != '':
        match = re.search('(\d*)?\s*((min)|(sec))',parts[4])
        if match and len(match.groups()) >= 3:
            if match.group(1).strip() != '':
                value = int(match.group(1))
                unit = match.group(2)
                if "min" in unit.lower():
                    value = value * 60
                print("%s\t%s" % (parts[3], value))
                    