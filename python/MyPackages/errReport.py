#!/usr/bin/evn python

# -*- coding: utf-8 -*-


import time
import os
import sys

def countErrorInFolder(folderName, jobStartTimeLocalString="", jobStartTimeGMTString="", jobEndTimeGMTString="", errorReportDic={}):
    os.chdir(folderName)
    jobStartLocalTime = time.mktime(time.strptime(jobStartTimeLocalString,"%Y-%m-%d %H:%M:%S"))
    for roots,dirs,fileNames in os.walk(folderName):
        for name in fileNames:
            if name.startswith("BulkProcessingFailedResponse.log"):
                if jobStartLocalTime<= os.path.getmtime(name):
                    str = " After"
                else:
                    str = " Before"
                #print name + ", Modified time is:" + time.ctime(os.path.getmtime(name)) + str + jobStartTimeLocalString
                if str == " After":
                    print name + ", count error of this file"
                    countErrorOnSingleFile(jobStartTimeGMTString,jobEndTimeGMTString,name,errorReportDic)
                else:
                    pass
        

def countErrorOnSingleFile(jobStartTimeGMTString, jobEndTimeGMTString, logFileName, errRpt):
    #jobStartTime and jobEndTime should be set a gmt time string.
    jobStartTime=time.strptime(jobStartTimeGMTString,"%Y-%m-%d %H:%M:%S")
    jobEndTime=time.strptime(jobEndTimeGMTString,"%Y-%m-%d %H:%M:%S")
    filename = logFileName
    fileObj = open(filename)
    allines = fileObj.readlines()

    #for line in allines:
    #test several lines in files:
    #totalLineCount=173616
    #totalErrors = 100000
    currentErrors = 0
    #for i in range(0,totalLineCount):
    for line in allines:
        if line.startswith("20"):
                    #time = gettime from line
            timeStr=line[:line.index(",")]
            timeGmt=time.strptime(timeStr,"%Y-%m-%d %H:%M:%S")
            if timeGmt>=jobStartTime and timeGmt<=jobEndTime:
                checkError=True
            else:
                checkError=False
        elif "<ErrorMessage>" in line:
            errorStr = line.replace("<ErrorMessage>","").replace("</ErrorMessage>","").strip()
            if checkError:
                currentErrors += 1
                #if currentErrors<=totalErrors:
                #do something to count error
                #print "Count Error: "+errorStr
                if not errRpt.has_key(errorStr):
                    errRpt[errorStr] = 1
                else:
                    errRpt[errorStr] +=1
            else:
                #print errorStr + " ;Not Count Error"
                continue
        else:
            continue
			
    fileObj.close()
    '''
    '''
        
num = len(sys.argv)
if num <> 5:
    print "Usage: errReport.py startTimeLocal startTimeGMT endTimeGMT folderName"
    exit()
    
jobStartTimeLocal="2016-06-17 15:41:00"
jobStartTimeGMT = "2016-06-17 07:41:00"
jobEndTimeGMT = "2016-06-18 00:00:00"
folderName=r"C:\Users\IBM_ADMIN\Box Sync\Work Document\TasksAndIssues\CurrentWorkWith\evergreening\0617-0741"

jobStartTimeLocal=sys.argv[1]
jobStartTimeGMT = sys.argv[2]
jobEndTimeGMT = sys.argv[3]
folderName=sys.argv[4]
errRpt = {}
#countErrorOnSingleFile(jobStartTime,fileName, errRpt)

os.chdir(folderName)
countErrorInFolder(folderName,jobStartTimeLocal,jobStartTimeGMT,jobEndTimeGMT, errRpt)
#print error report:
totalErrors = 0
errMsg = ""
for key in errRpt.keys():
    totalErrors += errRpt[key]
    errMsg += key + ":" + str(errRpt[key]) + "\n"

print "Total Errors:" + str(totalErrors)
print errMsg
