<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Book Form</title>
</head>
<body>
	<h1>Book Form</h1>
	<form method="post" action="${pageContext.request.contextPath}/book">
		<fieldset>
			<legend>
				<c:choose>
					<c:when test="${not empty book.id }">
						Updating Book
					</c:when>
					<c:otherwise>
						Adding Book
					</c:otherwise>
				</c:choose>
			</legend>
			<div>
				<label for="title">Title</label>
				<input type="text" name="title" id="title" value="${book.title }"/>
			</div>
			<div>
				<label for="description">Description</label>
				<textarea name="description" id="description" rows="2" cols="60">${book.description }</textarea>
			</div>
			<div>
				<label for="price">Price</label>
				<input name="price" id="price" value="${book.price }"/>
			</div>
			<div>
				<label for="pubDate">pubDate</label>
				<input name="pubDate" id="pubDate" value="${pubDate }"/>
			</div>
			<c:if test="${not empty book.id}">
				<input type="hidden" name="id" value="${book.id}"/>
			</c:if>
		</fieldset>
		<div class="button-row">
			<a href="${pageContext.request.contextPath}/book/">Cancel</a> or <input type="submit" value="Submit" />
		</div>
	</form>
</body>
</html>