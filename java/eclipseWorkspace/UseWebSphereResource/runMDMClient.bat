@set WAS_HOME=C:\IBM\WebSphere\AppServer
@set JAVA_HOME=%WAS_HOME%\java\8.0
@set profileRoot=%WAS_HOME%\profiles\AppSrv01
@set wasuser=wasadmin
@set wasPassword=Lihui2020
@set serverUrl=corbaloc::127.0.0.1:2809

@set param=-Dcom.ibm.CORBA.loginSource=properties -Dcom.ibm.CORBA.loginUserid=%wasuser% -Dcom.ibm.CORBA.loginPassword=%wasPassword% -Dcom.ibm.CORBA.securityEnabled=true -Dcom.ibm.CORBA.authenticationRetryEnabled=true -Dcom.ibm.CORBA.authenticationRetryCount=3 -Dcom.ibm.CORBA.validateBasicAuth=true -Dcom.ibm.CORBA.securityServerHost= -Dcom.ibm.CORBA.securityServerPort= -Dcom.ibm.CORBA.loginTimeout=300 -Dcom.ibm.CORBA.requestTimeout=180 -Dcom.ibm.SSL.ConfigURL=file:\\\%profileRoot%\properties\ssl.client.props -Dcom.ibm.CORBA.authenticationTarget=BasicAuth 
@rem -Dcom.ibm.CSI.performStateful=true -Dcom.ibm.CSI.performClientAuthenticationRequired=false 
@rem -Dcom.ibm.CSI.performClientAuthenticationSupported=true -Dcom.ibm.CSI.performTLClientAuthenticationRequired=false -Dcom.ibm.CSI.performTLClientAuthenticationSupported=false -Dcom.ibm.CSI.performTransportAssocSSLTLSRequired=false -Dcom.ibm.CSI.performTransportAssocSSLTLSSupported=true -Dcom.ibm.CSI.performMessageIntegrityRequired=true -Dcom.ibm.CSI.performMessageIntegritySupported=true -Dcom.ibm.CSI.performMessageConfidentialityRequired=false -Dcom.ibm.CSI.performMessageConfidentialitySupported=true 
@rem -Dcom.ibm.ssl.alias=DefaultSSLSettings

@set libpath=libs
@set classpath=%libpath%\*
@rem @set classpath="mdmclient.jar;com.ibm.ws.ejb.thinclient_9.0.jar;j2ee.jar;com.ibm.mdm.server.dwlcommonservices_11.6.0.8-201812151241.jar"
@%JAVA_HOME%\bin\java.exe %param% -classpath %classpath% -DserverUrl=%serverUrl% -DrequestFile=requestOneLine.xml com.ibm.mdm.testtool.MySimpleClient