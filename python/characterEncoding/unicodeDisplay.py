#!/bin/usr/env python
# -*- coding: utf-8 -*-
import sys
import io
import locale

str1 = "El Niño"
str2 = "αβγδ"
str3 = "中文"
        
print(sys.getfilesystemencoding()) #print utf-8 in git bash and windows console
print(locale.getpreferredencoding()) #print cp936 in git bash and windows console
#sys.stdout = io.TextIOWrapper(sys.stdout.buffer,encoding='utf-8')
#get error in git bash:
#UnicodeEncodeError: 'gbk' codec can't encode character '\xf1' in position 5: illegal multibyte sequence
print(str1)
print(str2)
print(str3.encode("utf-8"))
print(str3)
print(sys.stdout.encoding) #print cp936 in git bash; print utf-8 in windows console