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
    usingTimeRange = False
else:
    begin=sys.argv[1]
    end=sys.argv[2]
    usingTimeRange = True
tempArr = []
fileObj = open(r'C:\Users\xiejia-lihui\Desktop\nohup.out')
for line in fileObj.readlines():
    time = line.split(' ')[0]
    temp = float(line.split(' ')[1])/1000
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
