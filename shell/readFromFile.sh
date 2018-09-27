#!/bin/bash

fileName=multipleLineFile.txt #file should contain a empty line to make sure shell can read all content
lineNumber=0

while read line
do
	let lineNumber=lineNumber+1
	echo "Line" $lineNumber":"$line
done <$fileName

fileName=singleLineFile.txt
allContent=`cat $fileName`
echo "single file content: "$allContent