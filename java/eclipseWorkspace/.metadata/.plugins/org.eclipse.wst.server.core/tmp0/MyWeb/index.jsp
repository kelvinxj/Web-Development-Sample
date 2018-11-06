<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.util.Date, java.util.Properties, java.util.Set" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java server pages</title>
</head>
<body>
JSP Page on Resin!<br/>
<%Date myDate = new Date();%>
<span>current time is=<%=myDate %></span>
<%out.println("I'll show you a table contain all the system properties."); %>
<table id="mainTable">
<tr>
	<td>Property Name</td>
	<td>Property Value</td>
</tr>
<%
String str = System.getProperty("user.dir");
out.println("System property user.dir is:" + str);
Properties p = System.getProperties();
Set<Object> keys = p.keySet();
String keyNameStr = "";
for(Object keyName : keys){
	keyNameStr = keyName.toString();
%>
<tr>
	<td><%=keyNameStr %></td>
	<td><%=p.getProperty(keyNameStr)%></td>
</tr>
<%} %>
</body>
</html>