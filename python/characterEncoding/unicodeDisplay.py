#!/bin/usr/env python
# -*- coding: utf-8 -*-
import sys
import io
import locale

str1 = "El Niño"
str2 = "αβγδ"
str3 = "中文"
        
#print(sys.getfilesystemencoding()) #print utf-8 in git bash and windows console
#print(locale.getpreferredencoding()) #print cp936 in git bash and windows console
##sys.stdout = io.TextIOWrapper(sys.stdout.buffer,encoding='utf-8')
##get error in git bash:
##UnicodeEncodeError: 'gbk' codec can't encode character '\xf1' in position 5: illegal multibyte sequence
#print(str1)
#print(str2)
#print(str3.encode("utf-8"))
#print(str3)
#print(sys.stdout.encoding) #print cp936 in git bash; print utf-8 in windows console


#show string:
#unicode hex
#unicode decimal
#utf-8 hex
#utf-8 decimal
#gbk hex
#gbk decimal
#sys.stdout = io.TextIOWrapper(sys.stdout.buffer,encoding='utf-8')
def showCharEncode(str1):
	str1 = str1[0]
	uDecimal = ord(str1)
	print(str1 + " unicode hex:" + str(hex(uDecimal)))
	print(str1 + " unicode decimal:" + str(int(uDecimal)))
	
	#utf-8
	bytesUtf8 = str1.encode("utf-8")
	if(len(bytesUtf8) > 1):
		#double byte character
		print(str1 + " utf-8 hex:" + str(bytesUtf8))
	else:
		#single byte character
		print(str1 + " utf-8 hex (single byte character):" + str(hex(int(bytesUtf8[0]))))
		
	
	#gbk
	bytesGbk = str1.encode("gbk")
	if(len(bytesGbk) > 1):
		#double byte character
		print(str1 + " gbk hex:" + str(bytesGbk))
	else:
		#single byte character
		print(str1 + " gbk hex (single byte character):" + str(hex(int(bytesGbk[0]))))
		
showCharEncode("中")
showCharEncode("a")