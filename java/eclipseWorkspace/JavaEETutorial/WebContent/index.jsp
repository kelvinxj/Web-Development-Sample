<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Welcome to see the Jave EE 7 Demo</title>
</head>
<body>
<%Date dt = new Date();

String url = response.encodeURL("guessNumber.jsp");
%>
<span>Current time is:<%=dt%></span><br/>
<a href="greetings/">Context and Dependency Injection</a>
<br/><a href="guessNumber.jsp/">Guess a number</a>
<a href="<%=url%>">Guess a number (encoded URL)</a>
</body>
</html>