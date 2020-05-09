#!/usr/bin/env python
# -*- coding: utf-8 -*-
import sys
import re
import datetime

argCount = len(sys.argv)
if argCount == 1:
    print("usage: makeExceptionFileOneLine.py fileName")
    exit(1)
else:
    print("args count good...")

fileName = sys.argv[1]
print("process file:" + fileName)

fieldSeparator= "\t"
messageFormat = "{0}{4}{1}{4}{2}{4}{3}"
datetimeFormat = '%Y-%m-%d %H:%M:%S,%f'
datetimeFormatShort = '%Y-%m-%d_%H-%M-%S'
timeStart=""
timeEnd=""
requestTimeStr = ""
requestId = ""
errorRequestId = ""
requestXML = ""
responseXML = ""
fileObj = open(fileName,"r",encoding="utf-8")
regExpTime = "(^\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2},\d{3})"
regExpRequestId = "requestID>(\d+)<"
regExpResponeBegin = "<\?xml"
regExpResponeEnd = "</TCRMService>"
regExpRequestXML = "(<\?xml.*TCRMService>)"
minDate = ""
maxDate = ""
currentDate = ""
goodLines = []

for line in fileObj.readlines():
    line = line.strip()
    matchWithTime = re.search(regExpTime,line)
    matchWithResponseBegin = re.search(regExpResponeBegin,line)
    matchWithResponseEnd = re.search(regExpResponeEnd,line)
    
    if matchWithTime:
        requestTimeStr = matchWithTime.group(1)
        matchWithRequestXML = re.search(regExpRequestXML,line)
        if matchWithRequestXML:
            requestXML = re.search(regExpRequestXML,line).group(1)
            requestId = re.search(regExpRequestId,line).group(1)
        else:
            requestXML = "null"
            requestId = "null"
        if minDate == "":
            minDate = datetime.datetime.strptime(requestTimeStr, datetimeFormat)
            maxDate = datetime.datetime.strptime(requestTimeStr, datetimeFormat)
        else:
            currentDate = datetime.datetime.strptime(requestTimeStr, datetimeFormat)
            minDate = currentDate if currentDate < minDate else minDate
            maxDate = currentDate if currentDate > maxDate else maxDate
        goodLines.append(messageFormat.format(requestTimeStr,requestId,"reuqest", requestXML,fieldSeparator))
        responseXML = ""
    elif matchWithResponseBegin:
        responseBegin=True
        responseXML = responseXML + line
    elif matchWithResponseEnd:
        responseXML = responseXML + line
        goodLines.append(messageFormat.format(requestTimeStr,errorRequestId, "response", responseXML,fieldSeparator))
        responseBegin=False
        responseXML = ""
        errorRequestId = "null"
    else:
        if responseBegin:
            responseXML = responseXML + line
            matchWithRequestId = re.search(regExpRequestId,line);
            if matchWithRequestId:
                errorRequestId = matchWithRequestId.group(1)
        
fileObj.close()
#print(minDate.strftime(datetimeFormat) + ":" + maxDate.strftime(datetimeFormat))
newFileName="exception" + timeStart+"To"+timeEnd
newFileName = "exception_{0}_To_{1}".format(minDate.strftime(datetimeFormatShort),maxDate.strftime(datetimeFormatShort))
newFileObj = open(newFileName,"w",encoding="utf-8")
for line in goodLines:
    newFileObj.write(line + "\n")
    #newFileObj.write("\r\n")
newFileObj.close()