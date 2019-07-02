#!/usr/bin/env python
# -*- coding: utf-8 -*-
#the first line is used only in unix when you want to make python file executable
#the second is used to declare a encoding other than utf-8. if no this line, the code file is treated as UTF-8

#Print "hello, world"
print("Hello, world")

#Define variables: string, int, array,Array accessing
myName="this is a string"
myAge=22
print(myName)
print(myAge)

#Array is not standard type in python, you can use list

#If…else if...else struct
#in python, the condition can be with or without brackets
if(myAge > 30):
	print("Your age is older than 30")
elif myAge > 20:
	print("Your age is older than 20")
else:
	print("you are young")
	
#ogical operators(and, or, ==)
print(True and False) #False
print(True or False) #True
print("True or False is: " + str(True or False)) #print True
print("True and False is: " + str(True and False)) #print False
print("True is False?" + str(True == False))#print False

	
#For/looping/while/do while/foreach struct
#for:
words = ['cat','window','defenestrate']
for w in words:
    print (w,len(w))

for num in range(2,10):
    if num %2 == 0:
        print ('Found an even number', num)
    else:
        print ('Found a number',num)
		

#While:
#print a Fibonacci array:
print ("a Fibonacci array")
a,b=0,1 #mutiple assignment
while b<10:
	print (b,",")
	a,b=b,a+b

#flow control in loop: break, continue
for num in range(2,10):
	if num < 5:
		continue
	else:
		print("Find 5 in range 2 to 10")
		break


#Writing code in multiple lines

#Comparators: ==, <,>,<=,>=,!=

#Define function/methods
#Method without return value
def fib(n):
    a,b=0,1
    print ("display a fibonacci array:")
    while a<n:
        print (a,",")
        a,b=b,a+b

#call a function
fib(10)


#Method with return value


#Method, default arguments

#Type convert:
#Int to string
print("This is a num:" + str(1))
#String to int
print(4+int("1"))
	
#Get type of object
map={1:'map',2:'ChinaMap'}
print(type(map)) #<class 'dict'>
print(type(map.keys())) #<class 'dict_keys'>
print(type(map.values()))#<class 'dict_keys'>
print(type(1))#<class 'int'>
print(type('abc'))#<class 'str'>
print(type([1,2,3]))#<class 'list'>
print(type((1,2,3)))#<class 'tuple'>
print(type({1,2,3}))#<class 'set'>   

#String:
#Define a 
str1 = "This is a string"

#Basic string methods:
#Length
print (len(str1))

#concatenate two string:
print ("Hello, " + "world!")

#String contains:
print("a" in "abc") #True
print("a" not in "abc") #False

#String match or not
#Find a substring

#StartWith:

#endWith

#String replace

#String format

#String trim:
str1 = "    this is a string      "
print(len(str1))
print(len(str1.strip())


#Regular expression:
#Define a regular variable
reg = "\.java:\d+"

#Basic regular methods:
#Match or not
re.search(reg,"RemoteTestRunner.java:382") == None #False

#Get subGroups
matchs = re.search("\.java:(\d+)","RemoteTestRunner.java:382")
#first group is matched part
matchs.group(0)#.java:382
#second group is part in "()"
matchs.group(1)# 382

#Collection:
#List:
#Define a list
fruits = ["apple","pear","peach"]

#Access item of list
print(fruits[0]) # apple

#Add an item to the end of list
fruits.append("lychee") #['apple', 'pear', 'peach', 'lychee']

#Add an item to beginning of list
fruits.insert(0,"tomato") #['tomato', 'apple', 'pear', 'peach', 'lychee']

#Delete an item to list
#Check an item in list


#Set:
#Define a Set


#Access item Set
#Add an item to Set
#Delete an item to Set
#Check an item in Set


#Map:
#Define a Map


#Access item of Map


#Add an item to Map


#Delete an item to Map
#Check an item in Map


#Tuple(if have)
#Define a tuple
#Access item of tuple


#Class

#object, it is a singleton class

#File I/O:
#Create file obj
#Readlines from file

#Language specific features

