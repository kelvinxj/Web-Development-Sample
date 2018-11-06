<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String name = request.getParameter("username");
	session.setAttribute("theName", name);
	out.println(session.getAttribute("theName"));
 %>
 <a href="NextPage.jsp">Continue</a>
</body>
</html>