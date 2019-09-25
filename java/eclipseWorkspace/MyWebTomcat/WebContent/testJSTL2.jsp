<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Time:
<c:out value="${sessionScope.time1 }"></c:out>
<br/>
Number:
<c:out value="${sessionScope.theNum }"/>
<br/>
Number is odd or not:
<br/>
<c:set var="num" value="${sessionScope.theNum }"/>
<c:if test="${num % 2 == 0}">it is an even number</c:if>
<c:if test="${num % 2 != 0}">it is an odd number</c:if>
<br/>
<span>using "choose" tag:</span>
<br/>
<c:choose>
	<c:when test="${num <= 10}">the number is less than 10</c:when>
	<c:when test="${num <= 20 && num > 10 }">the number is greater than 10, but less than 20</c:when>
	<c:otherwise>the number is greater than 20, but less than 30</c:otherwise>
</c:choose>
<a href="testJSTL.jsp">Back</a>
</body>
</html>