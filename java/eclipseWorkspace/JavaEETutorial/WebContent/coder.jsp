<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Coder Example</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath }/coderServlet/">
<%
String msg = "123";
String times = "10";
Date dt = new Date();
if(request.getAttribute("success") != null)
	msg = request.getAttribute("success").toString();
if(request.getAttribute("times") != null)
	times = request.getAttribute("times").toString();
String car = "";
if(request.getAttribute("carSelect") != null)
	car = request.getAttribute("carSelect").toString();
String youroption = "";
if(request.getParameter("youroption") != null)
	youroption = request.getParameter("youroption");
%>
<span>Please input your name</span><br/>
<input type="text" name="yourname"></input>
<br/>
<input type="submit" value="Encode"></input>
</form>
</body>
</html>