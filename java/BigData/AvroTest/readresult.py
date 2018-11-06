#!/usr/bin/env python3
import avro
from avro.datafile import DataFileReader
from avro.io import DatumReader
reader = DataFileReader(open("result.avro","rb"), DatumReader())

for ufo in reader:
	print("Shape: " + ufo.get("shape") + "; Count: " + str(ufo.get("count")))
reader.close()
