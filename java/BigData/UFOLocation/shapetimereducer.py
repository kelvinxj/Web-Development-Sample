#! /usr/bin/env python

import sys
current_word = None
min = 0
max = 0
mean = 0
count = 0
total = 0

#caculate timing of each ufo shape:
#max, min, mean,
for line in sys.stdin:
    line = line.strip()
    if line == "":
        continue
    parts = line.split('\t')
    time = int(parts[1])
    if parts[0] == current_word:
        #still the same word, caculate max/min/mean
        count += 1
        total += time
        if time >= max:
            max = time
        if time <= min:
            min = time
        
    else:
        #new words, print last word's max/min/mean
        if current_word:
            print(current_word + "\t" + str(max) +" " + str(min) + " " + str(total/count))
        #begin count current word
        current_word = parts[0]
        total = time
        max = time
        min = time
        count = 1
        

print(current_word + "\t" + str(max) +" " + str(min) + " " + str(total/count))    