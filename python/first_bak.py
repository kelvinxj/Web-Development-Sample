#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re

#Now this source file is updated to use python 3(3.6.4)

#use \ to escapse:
print ('It\'s a desk') #will print It's a desk

#use raw string:
print (r'It\'s a desk') #will print It\'s a desk


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



        


#print string in multiple lines
#if string end with "\", new line will not be printed
print ("""abc\
      , it\'s a long string""")
      
#without "\", new line will be printed
print("""this is line one,
        this is line two.""")
        
 
        
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
