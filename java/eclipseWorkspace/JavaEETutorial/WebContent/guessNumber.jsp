<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Guess the number</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath }/guessNumberServlet/">
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
<span>Please input a number between 0 and 100</span><br/>
<input type="text" name="num"></input>
<input type="submit" value="check number"></input>
<span><%=msg %></span><br/>
<span>Remaining times: <%=times %></span>
<br/>
<select name="carSelect">
  <option value ="volvo">Volvo</option>
  <option value ="saab">Saab</option>
  <option value="opel">Opel</option>
  <option value="audi">Audi</option>
</select>
<br/>
<span>The car you select is: <%=car %></span>
<br/>
<br/>
<input type="radio" id="rd1" name="youroption" value="type1" checked="checked"><label for="rd1">Type1</label></input>
<input type="radio" id="rd2" name="youroption" value="type2"><label for="rd2">Type2</label></input>
</br>
<span>The type you select is: <%=youroption %></span>
</form>
</body>
</html>