<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date, java.util.Random, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>test jstl</title>
</head>
<body>
<%
Random rd = new Random();
int n = rd.nextInt(30);
ArrayList<Integer> intList = new ArrayList<Integer>();
for(int i = 1; i <= n;i++){
	intList.add(i);
}
%>
<c:set var="time1" scope="session"><%=new Date() %></c:set>
<c:set var="theNum" scope="session"><%=n %></c:set>
The number is: <c:out value="${theNum}"/>
<br/>
Will draw a table with <c:out value="${theNum}"/>rows:<br/>
<table border="1" width="100">
	<c:forEach var="i" begin="1" end="${theNum }">
		<tr>
			<td>Row <c:out value="${i }"/></td>
			<td><c:out value="${i }"/></td>
		</tr>
	</c:forEach>
</table>
<a href="testJSTL2.jsp">check this number</a>
</body>
</html>