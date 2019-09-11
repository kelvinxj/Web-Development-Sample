<HTML> 
<HEAD> 
<TITLE>My App Home</TITLE> 
<META http-equiv=Content-Typecontent="text/html; charset=iso-8859-1"> 
<% 
String javaVersion = System.getProperty("java.version"); 
%> 
</HEAD> 
<BODY> 
<p>Current JRE version: <font color="#FF0000"><%=javaVersion%></font></p> 
<p> 
<a href="/servlet/myfirstservlet?userName=userA"> 
Click to test seervlet 
</a> 
</BODY> 
</HTML>