#open file with encoding(should specify encoding for non-english file)
f=open("text.txt","r",encoding="utf-8");
#looping all lines from a file:
print("method1:")
for line in f:
	#end="" means print a string which a new line not automatically append to it
    print(line,end="")
f.close()

#or:
print()
print("method2:")
f=open("text.txt","r",encoding="utf-8");
for line in f.readlines():
	print(line,end="")
f.close()

#or:
print()
print("method3:")
f=open("text.txt","r",encoding="utf-8");
s=f.read()
print("all contents of this file:")
print(s)
f.close()

#read file in binary mode:
f = open(r"C:\Users\IBM_ADMIN\Documents\MyGit\MySource\python\text.txt","rb")
i = 0
byteChar = f.read(1)
while byteChar:
	i = i+1
	print("byte " + str(i) + ":" + str(byteChar) + "(" + str(ord(byteChar)) + ")" + ";hex:(" + byteChar.hex() + ")")
	byteChar = f.read(1)
f.close()