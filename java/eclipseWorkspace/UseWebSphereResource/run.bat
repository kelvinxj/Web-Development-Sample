@set currentPath=%cd%
@set WAS_HOME=C:\IBM\WebSphere\AppServer
@set JAVA_HOME=%WAS_HOME%\java\8.0
@set profileRoot=%WAS_HOME%\profiles\AppSrv01
@set wasuser=wasadmin
@set wasPassword=Lihui2020
@set workingPath=%~dp0
@cd /d %workingPath%

@set param=-Dcom.ibm.CORBA.loginSource=properties -Dcom.ibm.CORBA.loginUserid=%wasuser% -Dcom.ibm.CORBA.loginPassword=%wasPassword% -Dcom.ibm.CORBA.securityEnabled=true -Dcom.ibm.CORBA.authenticationRetryEnabled=true -Dcom.ibm.CORBA.authenticationRetryCount=3 -Dcom.ibm.CORBA.validateBasicAuth=true -Dcom.ibm.CORBA.securityServerHost= -Dcom.ibm.CORBA.securityServerPort= -Dcom.ibm.CORBA.loginTimeout=300 -Dcom.ibm.CORBA.requestTimeout=180 -Dcom.ibm.SSL.ConfigURL=file:\\\%profileRoot%\properties\ssl.client.props -Dcom.ibm.CORBA.authenticationTarget=BasicAuth 

@set libPath=libs
@set classpath=%libPath%\*
@%JAVA_HOME%\bin\java.exe %param% -classpath %classpath% -DpropertyFile=kafkaprops.properties com.ibm.mdm.testtool.KafkaProcessor

cd %currentPath%