<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE HTML>

<html>
<head>
<title>Book listing</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/book">Add Book</a>
<table>
	<tr>
		<th>Title</th>
		<th>Description</th>
		<th>Price</th>
		<th>Publication Date</th>
	</tr>
	
	<c:forEach var="book" items="${books}">
		<tr>
			<td><a href="${pageContext.request.contextPath}/book?id=${book.id}">${book.title}</a></td>
			<td>${book.description}</td>
			<td>${book.price}</td>
			<td>${book.pubDate}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>