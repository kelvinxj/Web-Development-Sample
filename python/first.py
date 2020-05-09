#!/usr/bin/env python
# -*- coding: utf-8 -*-
#the first line is used only in unix when you want to make python file executable
#the second is used to declare a encoding other than utf-8. if no this line, the code file is treated as UTF-8
import re

#Print "hello, world"
print("Hello, world")

#use \ to escapse:
print ('It\'s a desk') #will print It's a desk

#use raw string:
print (r'It\'s a desk') #will print It\'s a desk

#print string in multiple lines
#if string end with "\", new line will not be printed
print ("""abc\
      , it\'s a long string""")
      
#without "\", new line will be printed
print("""this is line one,
        this is line two.""")

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
#Character to int
print(ord('\n')) #print 10
#int to Character
print(chr(97)) #print lower case 'a'
	
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

#index a char in string:
print (str1[0]) #print T

#index from the last char:
print (str1[-1])#print g

#index of string
print("Position of 4 in string is: " + str(str1.index('s'))) #return first index of 4, if 4 not exist in string, Excepiton thrown
print("Position of i in string is: " + str(str1.find('i')))#return first index of "i", if "i" not exist in string, return -1


#slicing a string
str1='0123456'

#print string from position 0 (included) to 2(excluded)
print (str1[0:2])

str1 = "    this is a string      "
#String match or not
#Find a substring

#StartWith:

#endWith


#String format
print("Hello, {0}, today is:{1}".format("xiejia","Sunday"))

#String trim:
print(len(str1))
print(len(str1.strip()))
print("String is: " + str1)
print("Trim left    side space: " + str1.lstrip())
print("Trim right side space: " + str1.rstrip())
print("Trim both side space: " + str1.strip())

#String replace
str1= str1.replace("string","orange")
print("Now the string is: " + str1)


#Regular expression:
#Define a regular variable
reg = "\.java:\d+"

#Basic regular methods:
#Match or not
re.search(reg,"RemoteTestRunner.java:382") == None #False

str1="abcdef"
match = re.search("abc",str1)
if(match):
    print(str1 + " match " + "abc")

#match ignore case:
match = re.search("ABC", str1, re.IGNORECASE)
if(match):
    print(str1 + " match " + "ABC(ignore case)")

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

#Delete an item from list
fruits.remove("apple") #['tomato', 'pear', 'peach', 'lychee']

#Check an item in list
print("apple" in fruits) #False

#Set:
#Define a Set
book={"book1","book2","book3"}
print(book)


#Access item Set
#Can't access a set by index. using for:
for myBook in book:
    print("I have this book:" + myBook)
    
#Add an item to Set
book.add("Scary book")
print(book)

#remove  item from set. Items should exist in set ,otherwise, exception will be thrown
book.remove("book1")
#remove  item from set no matter item exist in a set
book.discard("book8")

#Check an item in Set
print("book1" in book) #True

#Map:
#Define a Map
myMap = {}

#Add an item to Map
myMap["name"] = "name1"
myMap["value"] = "value1"

#Access item of Map
print("name:" + myMap["name"])
print("value:" + myMap["value"])

#Delete an item to Map
#Check an item in Map
print("name" in myMap.keys()) #print True
print("name1" in myMap.keys()) #print True
print("value1" in myMap.values()) #print True



#Tuple(if have)
#Define a tuple
#Access item of tuple


#Class

#object, it is a singleton class

#File I/O:
#Create file obj
#Readlines from file

#Language specific features

