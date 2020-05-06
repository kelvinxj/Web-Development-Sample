#!/bin/bash

if [ $# -eq 0 ]; then
	echo 'usage: analyze.sh 202005'
	exit
fi
datepattern=$1
#basepath=./logs/
basepath=./
for fileName in `ls -t ${basepath}log_${datepattern}*.txt`
do
	
	echo `basename $fileName`:`tail -n 2 $fileName|head -n 1`|sed 's/log_\([0-9]\{8\}\).*Read\s\([0-9]*,[0-9]\+\).*/date\t\1\tcount\t\2/'
done