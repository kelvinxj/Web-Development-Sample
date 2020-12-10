#!/bin/bash
currentPath=`pwd`
workpath=`dirname $0`
WAS_HOME=/c/IBM/WebSphere/AppServer
JAVA_HOME=$WAS_HOME/java/8.0
profileRoot=%WAS_HOME%/profiles/AppSrv01
wasuser=wasadmin
wasPassword=Lihui2020


cd $workpath

libPath=libs
classpath=$libPath/**
$JAVA_HOME/bin/java -classpath $classpath -DpropertyFile=kafkaprops.properties com.ibm.mdm.testtool.KafkaProcessor


cd "$currentPath"
