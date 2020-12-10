#!/bin/bash
WAS_HOME=/home/mdmqausr/IBM/WebSphere/AppServer
JAVA_HOME=$WAS_HOME/java/8.0
profileRoot=$WAS_HOME/profiles/AppSrv01
wasuser=wasadmin
wasPassword=xH2inhmvW2pyus
serverUrl=corbaloc::127.0.0.1:2809

param="-Dcom.ibm.CORBA.loginSource=properties -Dcom.ibm.CORBA.loginUserid=$wasuser -Dcom.ibm.CORBA.loginPassword=$wasPassword -Dcom.ibm.CORBA.securityEnabled=true -Dcom.ibm.CORBA.authenticationTarget=BasicAuth -Dcom.ibm.CORBA.authenticationRetryEnabled=true -Dcom.ibm.CORBA.authenticationRetryCount=3 -Dcom.ibm.CORBA.validateBasicAuth=true -Dcom.ibm.CORBA.securityServerHost= -Dcom.ibm.CORBA.securityServerPort= -Dcom.ibm.CORBA.loginTimeout=300 -Dcom.ibm.CORBA.requestTimeout=180 -Dcom.ibm.SSL.ConfigURL=file:$profileRoot/properties/ssl.client.props  "
#param=$param -Dcom.ibm.CSI.performStateful=true -Dcom.ibm.CSI.performClientAuthenticationRequired=false 
#param=$param -Dcom.ibm.CSI.performClientAuthenticationSupported=true -Dcom.ibm.CSI.performTLClientAuthenticationRequired=false -Dcom.ibm.CSI.performTLClientAuthenticationSupported=false -Dcom.ibm.CSI.performTransportAssocSSLTLSRequired=false -Dcom.ibm.CSI.performTransportAssocSSLTLSSupported=true -Dcom.ibm.CSI.performMessageIntegrityRequired=true -Dcom.ibm.CSI.performMessageIntegritySupported=true -Dcom.ibm.CSI.performMessageConfidentialityRequired=false -Dcom.ibm.CSI.performMessageConfidentialitySupported=true 
#param=$param -Dcom.ibm.ssl.alias=DefaultSSLSettings

classpath="mdmclient.jar:com.ibm.ws.ejb.thinclient_9.0.jar:j2ee.jar:com.ibm.mdm.server.dwlcommonservices_11.6.0.8-201812151241.jar"
$JAVA_HOME/bin/java $param -classpath $classpath -DserverUrl=$serverUrl -DrequestFile=request.xml com.ibm.mdm.testtool.MySimpleClient