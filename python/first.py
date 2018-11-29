#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re

#the first line is used only in unix when you want to make python file executable
#the second is used to declare a encoding other than utf-8. if no this line, the code file is treated as UTF-8
#Now this source file is updated to use python 3(3.6.4)

#define a string in python, can use single quote and double quotes
a="Hello,world"
b = ',someone'
print (a)
print (b)

#use \ to escapse:
print ('It\'s a desk') #will print It's a desk

#use raw string:
print (r'It\'s a desk') #will print It\'s a desk

#concatenate two string:
c = a+b
print ("Combine two string:" + c)

#or use following way:
c='Hello, world' ',Someone'
print ("Combine two string:"+c)

#Concatenating string and number
a=1
string = "The number is: "
#convert number to string
print(string + str(a))

#index a char in string:
print (c[0])

#index from the last char:
print (c[-1])

#slicing a string
str1='0123456'

#print string from position 0 (included) to 2(excluded)
print (str1[0:2])

#Manipulations of string
#legnth of a string
print (len(str1))

#index of string
print("Position of 4 in string is: " + str(str1.index('4'))) #return first index of 4, if 4 not exist in string, Excepiton thrown
print("Position of i in string is: " + str(str1.find('i')))#return first index of "i", if "i" not exist in string, return -1

#string strip:
str1="            abc            "
print("String is: " + str1)
print("Trim left    side space: " + str1.lstrip())
print("Trim right side space: " + str1.rstrip())
print("Trim both side space: " + str1.strip())
str1=str1.strip()
str1= str1.replace("abc","def")
print("Now the string is: " + str1)

#Basic regular  expression usage:
str1="abcdef"
match = re.search("abc",str1)
if(match):
    print(str1 + " match " + "abc")
    
#search ignore case:
match = re.search("ABC", str1, re.IGNORECASE)
if(match):
    print(str1 + " match " + "ABC(ignore case)")
    
#get matching groups by Regular expression
str1="somestring<name>MyName</name>"
match = re.search("<name>([^>]*)</name>",str1)

#replace by regular expression:
print("Before replacement: " + str1)
str1 = re.sub("<name>([^>]*)</name>","<name>YourName</name>",str1)
print("After replacement: " + str1)

print("Total matched group count: ")
print(len(match.groups()))

print("first group: " + match.group(0)) #whole match
print("second group: " + match.group(1))#match in ()


#String split
str1 = "first,second,thrid,fourth,,sixth"
print("string is: " + str1)
list1 = str1.split(",")
print(list1)


#print a Fibonacci array:
print ("a Fibonacci array")
a,b=0,1 #mutiple assignment
while b<10:
    print (b,",")
    a,b=b,a+b

#Flow control
#x = int(raw_input("Please enter an integer:"))
x = 3
if x<0:
    print ('Negative number')
elif x == 0:
    print ('Zero')
elif x ==1:
    print ("single")
else:
    print ("more")

#for:
words = ['cat','window','defenestrate']
for w in words:
    print (w,len(w))

for num in range(2,10):
    if num %2 == 0:
        print ('Found an even number', num)
    else:
        print ('Found a number',num)
        
#define functions
def fib(n):
    a,b=0,1
    print ("display a fibonacci array:")
    while a<n:
        print (a,",")
        a,b=b,a+b

#call a function
fib(10)

#print string in multiple lines
#if string end with "\", new line will not be printed
print ("""abc\
      , it\'s a long string""")
      
#without "\", new line will be printed
print("""this is line one,
        this is line two.""")
        
#Type info
map={1:'map',2:'ChinaMap'}
type(map) #<class 'dict'>
type(map.keys()) #<class 'dict_keys'>
type(map.values()) #<class 'dict_keys'>
type(1)#<class 'int'>
type('abc')#<class 'str'>
type([1,2,3])#<class 'list'>
type((1,2,3))#<class 'tuple'>
type({1,2,3})#<class 'set'>    
        
#Collections
#list
#initialize an empty list
list1=[]

#add item to list
list1.append(1)
list1.append(2)
list1.append(3)

print("The list is")
print(list1)

#length of a list
print(len(list1))

#remove first item whose value is X
list1.remove(2)
print(list1)

#some functions of List
squares=[1,4,9,16,25]
print (squares)
print (len(squares))
print ('first element of this list:')
print (squares[0])

#Set
#Initialization
book={"book1","book2","book3"}
print(book)

#check if a item is in set
print("book1" in book)

#add item to set
book.add("Scary book")
print(book)

#remove  item from set. Items should exist in set ,otherwise, exception will be thrown
book.remove("book1")

#remove  item from set no matter item exist in a set
book.discard("book8")

print(book)

#Dictionary(Map)
#Initialization
tel={'jack': 4098, 'sape': 4139}
print(tel)

#get value of an item
print(tel['jack'])
print(tel.get("jack"))

#whether a key in dictionary
print('jack' in tel)
print('yourname' in tel)

#add new item to dic
tel['Jim'] = 4444
print(tel)

#return all keys
print(tel.keys())

#return all values
print(tel.values())
