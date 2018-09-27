#open file with encoding(should specify encoding for non-english file)
f=open("text.txt","r",encoding="utf-8");
#looping all lines from a file:
for line in f:
    print(line,end="")
f.close()

print()
f=open("text.txt","r",encoding="utf-8");
#print all contents of file
s=f.read()
print("all contents of this file:",s)
f.close()
