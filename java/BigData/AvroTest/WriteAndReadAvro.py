#!/usr/bin/env python3

import avro.schema
from avro.datafile import DataFileReader, DataFileWriter
from avro.io import DatumReader, DatumWriter

schemaFile = "ufo.avsc"
dataFile = "ufoData.avso"

schema = avro.schema.Parse(open(schemaFile,"rb").read())

writer = DataFileWriter(open(dataFile,"wb"), DatumWriter(),schema)
#the property "where":"good" will not be display when read this file
writer.append({"sighting_date":"2013-11-11", "city":"Las Vegas","shape":"light","duration":3,"where":"good"})
writer.append({"sighting_date":"2011-11-11", "city":"Jehnkl","shape":"s1","duration":4.5})
writer.append({"sighting_date":"2012-09-11", "city":"Juno","shape":"evho","duration":6})
writer.close()

reader = DataFileReader(open(dataFile,"rb"), DatumReader())

for ufo in reader:
	print(ufo)
reader.close()
