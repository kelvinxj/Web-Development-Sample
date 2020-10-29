import matplotlib
import sys
#matplotlib.use("Agg")
import matplotlib.pyplot as plt
#plt.plot([1,2,3,4])
#plt.ylabel('some numbers')
#plt.show()
#usage: python chart.py
#usage: python chart.py 11:00 12:00
begin=""
end=""
usingTimeRange = True
if(len(sys.argv) == 1):
    print("usage: python chart.py dataFileName")
    sys.exit(1)
elif(len(sys.argv) == 2):
    usingTimeRange = False
else:
    begin=sys.argv[2]
    end=sys.argv[3]
    usingTimeRange = True
tempArr = []
fileName = sys.argv[1]
fileObj = open(fileName)
for line in fileObj.readlines():
    arr = line.split(' ')
    if(len(arr) == 2):
        time = arr[0]
        temp = float(arr[1])/1000
        #print(temp)
        if not(usingTimeRange):
            tempArr.append(temp)
        else:
            if time >= begin and time <= end:
                tempArr.append(temp)
fileObj.close()
plt.plot(tempArr)
plt.ylabel('CPU temperature')
plt.show()
#plt.savefig('test.png')
