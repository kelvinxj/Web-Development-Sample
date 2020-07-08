import sys
import io

if(len(sys.argv) != 3):
    print("usage: mergeTwoList.py file1 file2")
    sys.exit(1)

fileName1 = sys.argv[1]
fileName2 = sys.argv[2]
sys.stdout = io.TextIOWrapper(sys.stdout.buffer,encoding='utf8')

#print("file1: "+ fileName1 + "; file2:" + fileName2)
keyFieldIndex = 1
key1 = set()
fileObj = open(fileName1,"r",encoding="utf-8")
for line in fileObj.readlines():
    print(line,end="")
    fields = line.split("\t")
    key1.add(fields[keyFieldIndex])
fileObj.close()

fileObj = open(fileName2,"r",encoding="utf-8")
for line in fileObj.readlines():    
    fields = line.split("\t")
    key = fields[keyFieldIndex]
    if(key not in key1):
        print(line,end="")
fileObj.close()