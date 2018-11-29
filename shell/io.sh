#!/bin/bash

#method1:
echo "method1:"
while IFS=  read -r f 0<&3;
do
	#echo $IFS
	echo "found file name: $f"
done 3< filename.txt

echo
echo "method2:"
#method2:
while IFS= read -r line; 
do
	echo $line
done <filename.txt