#!/bin/bash
#str="Hello"
#echo $str
#it is better that file ended with newLine character.
fileName="rowCount.txt"
count=1
num=0
num=`awk -F' ' 'NR==1{print $1}' rowCount.txt`
echo "num is"$num