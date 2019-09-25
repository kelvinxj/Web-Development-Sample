#!/bin/bash
#first line should be this. to tell which shell should be used.

#defining variables.
#note: there is no space between variable name and "="
aStr="Hello, world"

#using a variable.
echo $aStr

#using variable in another string, should use ${paramNumber}.
#need to use double quote to print variable value
echo "The string you set is:\"${aStr}\" and this is how to use a variable in string."

#running a shell command and get the result in string
currentDir=`pwd`
echo "current dir is:${currentDir}"

#using the input parameters and value assignment.
echo "input parameter count is:$#"
if [ $# -gt 1 ]; then
	val1=$1
	val2=$2
	echo "#1 parameter is: $1, is the same as ${val1}"
	echo "#2 parameter is: $2, is the same as ${val2}"
fi

#comparing between integer, using -eq, -lt , -gt for equal, less than, greater than.
curVal=5

#logical operator AND, for "or", use || or -o
#when use &&, need to use "[[]]"
echo "----------------------------------------The usage of logical operator \
------------------------------------------"
if [[ $curVal > 4 ]] && [[ $curVal < 6 ]]; then
	echo "$curVal is 5"
else
	echo "Can not use equal sign"
fi

if [ $curVal -gt 4  -a  $curVal -lt 6 ]; then
	echo "$curVal is 5"
else
	echo "Can not use equal sign"
fi

val1=3
val2=4
#AND
if [ $val1 -gt 1 -a $val2 -gt 1 ]; then
	echo "$val1 and $val2 are all greater than 1"
fi

#OR
if [ $val1 -gt 3 -o $val2 -gt 3 ]; then
	echo "$val1 or $val2 is greater than 3"
fi

#NOT
if [ ! $val1 -gt 4 ]; then
	echo "$val1 is not greater than 4"
fi
echo "----------------------------------------End the usage of logical operator \
------------------------------------------"

#comparing operator
#equal sign. 
echo "----------------------------------------The usage of comparing operator \
---------------------------"
if [ $curVal -eq 8 ]; then
	echo "${curVal} is 8"
fi

#for string, use =
astr="a string"
if [[ $astr = "a string" ]]; then
	echo "$astr is a string"
else
	echo "two strings is not identical"
fi

#-gt,-lt only for integer.
curVal=10
if [ $curVal -eq 8 ]; then
	echo "$curVal is 8"
elif [ $curVal -lt 8 ]; then
	echo "${curVal} is less than 8"
elif [ $curVal -gt 8 ]; then
	echo "$curVal is greater than 8"
else
	echo "${curVal} is greater than 8"
fi
echo "----------------------------------------End the usage of comparing operator \
---------------------------"

#String processing
#comparing between string
echo "----------------------------------------The usage of strinng processing \
---------------------------"
currentProcess=`ps -e`
if [ -z "$currentProcess" ]; then
	#-z means string variable is empty.
	echo "No process running"
else
	echo "current process:"
	echo "${currentProcess}"
fi

str1="Hello,world"
if [[ "$str1" =~ "llo,wor" ]] || [[ $str1 =~ "abc" ]]; then
	echo "${str1} contains wor"
else
	echo "${str1} don't contain wor"
fi

#regular expression to string.
if [ $str1=^Hel ]; then
	echo "$str1 begin with Hel"
fi

if [ $str1=ld$ ]; then
	echo "$str1 ends with ld"
fi

if [ $str1=lo*or ]; then
	echo "$str1 contains lo...or"
fi

echo "----------------------------------------End the usage of string processing \
---------------------------"

#file attributes testing
echo "----------------------------------------File atrributes check \
---------------------------"
MyFileName="Data-`date +%s`.txt"
echo "some data" > ${MyFileName}

if [ -e ${MyFileName} ]; then
	echo "${MyFileName} exists"
fi

if [ -f "${MyFileName}" ]; then
	echo "${MyFileName} is a regular file"
fi

if [ -s ${MyFileName} ]; then
	echo "${MyFileName} exists and contain one character at least."
fi

MyFolderName="testFolder"
if [ -d ${MyFolderName} ]; then
	echo " ${MyFolderName} exists"
else
	echo " ${MyFolderName} not exists"
fi

rm $MyFileName
echo "----------------------------------------End of file atrributes check \
---------------------------"

#calculation
echo "----------------------------------------Caculation sample \
---------------------------"
seconds=120
minutes=$((seconds/60))
echo "${seconds} seconds is ${minutes} minutes"

let seconds=seconds+60
echo "${seconds} seconds is $((seconds/60)) minutes"

let seconds=seconds*2
echo "${seconds} seconds is $((seconds/60)) minutes"

let seconds=seconds-60
echo "${seconds} seconds is $((seconds/60)) minutes"

num1=78655
echo "(${num1}/100)*100 is:"
#num1=$((num1/100))
#num1=$((num1*100))
let num1=(num1/100)*100
echo $num1
echo "----------------------------------------End of Caculation sample \
---------------------------"
#looping.
waitTime=1

while(true)
do
	if [ $waitTime -lt 4 ]; then
		echo "wait for 1 second"
		let waitTime=waitTime+1
		#sleep one second.
		#sleep 1
	else
		echo "Waiting time is over."
		break
	fi
done
#useing condition in while:
#while([ $waitTime -lt 4 ])

#file IO
fileName="MyFile`date +%s`.txt"
#using ">>" to append content to a file
echo "input some data to file" >> $fileName

#show file content:
echo "file content:"
absoluteFileName="`pwd`/${fileName}"
cat "${absoluteFileName}"

#delete file
#how to write a long command in multiple lines.
rm \
"${absoluteFileName}"

#how to write a long string in multiple lines.
#Note: Although write in multiple lines, this string is still one line.
#line 1 should have a whitespace between "\", otherwise it will be a new line.
longStr=\
"line 1 \
line2 line3 \
line4"

echo "a long string:$longStr"

str1="a string"
if [ ! -z "$str1" ]; then
	echo "str1 is not empty"
else
	echo "str1 is empty"
fi