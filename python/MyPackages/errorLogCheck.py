import os
import re
	
def putItemToDic(key, dic):
    if(dic.has_key(key)):
        dic[key]+=1
    else:
        dic[key]=1
        
def  getRetryErrorCodeAndRequestID(fileName, errorCode):
    errorDic = {} #reasonCode:requestID
    requestIDDic = {} #requestID: count
    fileObj = open(fileName)
    allLines = fileObj.readlines()
    currentTime = ""
    currentRequestID = ""
    reasonCode = ""
    for line in allLines:
        line = line.lstrip()
        if(line.startswith("20")):
            timeStr = line[:line.index(",")]
            if(currentTime != timeStr):
                currentTime = timeStr
                currentRequestID = ""
        elif(line.startswith("<requestID>")):
            requestID = line.strip().replace("<requestID>","").replace("</requestID>","")
            #if(currentRequestID == requestID):
            #    if(errorDic.has_key(reasonCode)):
            #        requestIDDic = errorDic[reasonCode]
            #        putItemToDic(requestID, requestIDDic)
            currentRequestID = requestID
            if(requestIDDic.has_key(currentRequestID)):
                putItemToDic(currentRequestID, requestIDDic)
        elif(line.startswith("<ReasonCode>")):
             reasonCode = line.strip().replace("<ReasonCode>","").replace("</ReasonCode>","")
             if(reasonCode == errorCode and currentRequestID != ""):
                 #print reasonCode + "; requestID: " + currentRequestID
                 errorDic[reasonCode] = {currentRequestID}
                 putItemToDic(currentRequestID,requestIDDic)
    
    keys = requestIDDic.keys()
    keys.sort()
    totalError = 0
    totalRequestIDCount = 0
    for key in keys:
        totalError += requestIDDic[key]
        totalRequestIDCount+=1
        print ("{0:20}, count :{1:3}".format(key, requestIDDic[key]))
    print ("File name: {0}".format(fileName))
    print ("total failed request count: " + str(totalRequestIDCount))
    print ("total error count:" + str(totalError))
    fileObj.close()

def checkFile(fileNameSuffix, errorCode):
    f="RequestResponseMessage.log."
    getRetryErrorCodeAndRequestID(f + fileNameSuffix, errorCode)

def findDistinctRequestIDByThrowableMessage(fileName, message, requestIDSet):
    fileObj = open(fileName)
    for line in fileObj.readlines():
        line = line.lstrip()
        if(line.startswith("<requestID>")):
            requestID = line.strip().replace("<requestID>","").replace("</requestID>","")
            currentRequestID = requestID
        elif(line.startswith("<Throwable>")):
             throwableMsg = line.strip()
             if(throwableMsg == message and currentRequestID != ""):
                 requestIDSet.add(currentRequestID)
    fileObj.close()
    
def findErrorMessagesByRequestString(fileName, requestString, printRequestID=False):
    fileObj = open(fileName,"r",encoding="utf-8")
    foundrequestString = False
    requestID = ""
    #string to be printed
    infoStr = ""
    for line in fileObj.readlines():
        line = line.lstrip()
        if(requestString in line):
            foundrequestString = True
        elif(line.startswith("<requestID>")):
            requestID = line.strip().replace("<requestID>","").replace("</requestID>","")
        elif(line.startswith("</TCRMService>")):
            foundrequestString = False
        elif(line.startswith("<ErrorMessage>")):
            if(foundrequestString):
                infoStr = line.strip().replace("<ErrorMessage>","").replace("</ErrorMessage>","")
                if(printRequestID):
                    infoStr = infoStr + ", RequestID " + requestID
                print(infoStr)
    fileObj.close()
 
def findRequestBodyByErrorMessage(fileName, errorMessage, searchReg, printFileName=False):
    fileObj = open(fileName, "r", encoding="utf-8")
    request=""
    info = ""
    timeStr = ""
    searchPart = ""
    
    if(printFileName):
        print(fileName)
    for line in fileObj.readlines():
        line = line.lstrip()
        if(line.startswith("20")):
            request = line
        elif(line.startswith("<ErrorMessage>")):
            if(line.strip().replace("<ErrorMessage>","").replace("</ErrorMessage>","") == errorMessage):
                timeStr = request[0:10]
                matchs = re.search(searchReg, request);
                #could also: if(matchs != None)
                if(matchs):
                    searchPart = matchs.group(1)
                    print(timeStr + ":     " + searchPart)
    fileObj.close()
    
#search key: <ReasonCode>109</ReasonCode>
#fieldsList:["Detail", "RequestID"]
def getErrorMessageAsTable(fileName,searchKey,fieldsList):
    fileObj = open(fileName, "r", encoding="utf-8")
    myTable = []
    errorMsgRow = {}
    needToCheckThisLine = False
    for line in fileObj.readlines():
        line = line.strip()
        if line.startswith("</TCRMService>"):
            #finished read exception xml, add error row to table
            if(needToCheckThisLine):
                myTable.append(errorMsgRow)
        elif(line.startswith("<TCRMService")):
            #new line begin
            needToCheckThisLine = False
            errorMsgRow = {}
            errorMsgRow["searchKey"] = searchKey
        else:
            #other line, check error message
            if(line.startswith(searchKey)):
                needToCheckThisLine = True
            else:
                for field in fieldsList:
                    #message in single line
                    if(line.startswith("<" + field + ">") and line.endswith("</" + field + ">")):
                        errorMsgRow[field] = line.replace("<" + field + ">","").replace("</" + field + ">","")
                    #message in multiple line(message start)
                    elif(line.startswith("<" + field + ">")):
                        errorMsgRow[field] = line.replace("<" + field + ">","")
                    #message in multiple line(message end)
                    elif(line.endswith("</" + field + ">")):
                        errorMsgRow[field] += (" " + line.replace("</" + field + ">",""))
    fileObj.close()
    return myTable

def showErrorMessageTable(errorMsgTable):
    allKeys = ""
    fieldSeparator = "\t"
    for key in errorMsgTable[0].keys():
        allKeys += key + fieldSeparator
    print(allKeys)
    #i = 1
    for item in errorMsgTable:    
        singleRow = ""
        for key in item.keys():
            singleRow += item[key] + fieldSeparator
        print(singleRow)
        #i += 1
    
#check log file in Log1023
#os.chdir(r"C:\Users\IBM_ADMIN\Documents\Work Document\TasksAndIssues\CurrentWorkWith\1597004LastUpdateDtNotMatch\Log1023")
#checkFile("5","130628")
#checkFile("6","130628")
#checkFile("7","130628")
#checkFile("8","130628")
#checkFile("9","130628")

#checkFile("5","109")
#checkFile("6","109")
#checkFile("7","109")
#checkFile("8","109")
#checkFile("9","109")


#check log file in Log1022
#os.chdir(r"C:\Users\IBM_ADMIN\Documents\Work Document\TasksAndIssues\CurrentWorkWith\1597004LastUpdateDtNotMatch\Log1022")
#checkFile("9","109")
#checkFile("10","109")
#checkFile("11","109")
#checkFile("12","109")

#allRequestID = set()
#throwableStr = "<Throwable>java.lang.RuntimeException: com.dwl.base.exception.LastUpdateDateException: class com.dwl.tcrm.coreParty.entityObject.EObjPersonName | 584748181126185841</Throwable>"
#os.chdir(r"C:\Users\IBM_ADMIN\Documents\Work Document\TasksAndIssues\CurrentWorkWith\1597004LastUpdateDtNotMatch\Log1023")
#findDistinctRequestIDByThrowableMessage("RequestResponseMessage.log.5",throwableStr,allRequestID)
#findDistinctRequestIDByThrowableMessage("RequestResponseMessage.log.6",throwableStr,allRequestID)
#findDistinctRequestIDByThrowableMessage("RequestResponseMessage.log.7",throwableStr,allRequestID)
#findDistinctRequestIDByThrowableMessage("RequestResponseMessage.log.8",throwableStr,allRequestID)
#findDistinctRequestIDByThrowableMessage("RequestResponseMessage.log.9",throwableStr,allRequestID)
#
#os.chdir(r"C:\Users\IBM_ADMIN\Documents\Work Document\TasksAndIssues\CurrentWorkWith\1597004LastUpdateDtNotMatch\Log1022")
#findDistinctRequestIDByThrowableMessage("RequestResponseMessage.log.9",throwableStr,allRequestID)
#findDistinctRequestIDByThrowableMessage("RequestResponseMessage.log.10",throwableStr,allRequestID)
#findDistinctRequestIDByThrowableMessage("RequestResponseMessage.log.11",throwableStr,allRequestID)
#findDistinctRequestIDByThrowableMessage("RequestResponseMessage.log.12",throwableStr,allRequestID)
#
#i=0
#for id in allRequestID:
#    i+=1
#    print id
#print "Total:" + str(i)




