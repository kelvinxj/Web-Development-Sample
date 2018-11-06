<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<form method="post" action="${pageContext.request.contextPath }/guessNumberServlet/">
<div>
<span>search by title:</span><input type="text" name="titleSearch"/><input type="submit" value="Submit"/>
</div>
<a href="${pageContext.request.contextPath}/book">Add Book</a>
<table class="listing">
	<tr>
		<th>Title
			<span class="sortLink">
				<a href="${pageContext.request.contextPath }/book/?order=asc&field=title">asc</a>|
				<a href="${pageContext.request.contextPath }/book/?order=desc&field=title">desc</a>
			</span>
		</th>
		<th>Description</th>
		<th>Price
			<span class="sortLink">
				<a href="${pageContext.request.contextPath }/book/?order=asc&field=price">asc</a>|
				<a href="${pageContext.request.contextPath }/book/?order=desc&field=title">desc</a>
			</span></th>
		<th>Publication Date
			<span class="sortLink">
				<a href="${pageContext.request.contextPath }/book/?order=asc&field=pubDate">asc</a>|
				<a href="${pageContext.request.contextPath }/book/?order=desc&field=pubDate">desc</a>
			</span></th>
		<th>Action</th>
	</tr>
	<c:forEach var="book1" items="${books }" varStatus="status">
		<tr class="${status.index%2==0?'alt':''}">
			<td><a href="${pageContext.request.contextPath }/book?id=${book1.id}">${book1.title}</a></td>
			<td>${book1.description };status:${status.index}</td>
			<td>
			<fmt:formatNumber value="${book1.price }" type="currency"/>
			</td>
			<td>
			<fmt:formatDate value="${book1.pubDate}" type="both" dateStyle="short" 
				timeStyle="short" pattern="yyyy-MM-dd"/>
			</td>
			<td>
				<a href="${pageContext.request.contextPath }/book/remove?id=${book1.id}">remove</a>
			</td>		
		</tr>
	</c:forEach>
</table>
</form>