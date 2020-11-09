#!/bin/usr/env python
# -*- coding: utf-8 -*-
str3 = "中"

def fromBytesToHexString(theBytesArray):
    for i in range(0, len(theBytesArray)):
        intByteValue = theBytesArray[i]
        print(str(i) + ":" + hex(intByteValue))
        
def showUnicodeHexString(str1):
    for i in range(0, len(str1)):
        print(hex(ord(str1[i])))
        
        

#show unicode encoding:
print(str3 + " unicode: ")
showUnicodeHexString(str3)

#show utf-8 encoding:
print(str3 + " utf-8:")
fromBytesToHexString(str3.encode("utf-8"))


str4 = "a"
print(str4 + " unicode:")
showUnicodeHexString(str4)

print(str4 + " utf-8:")
fromBytesToHexString(str4.encode("utf-8"))