<!DOCTYPE html>
<jsp:useBean id="user" class="com.kelvin.web.bean.UserData" scope="application"/>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
You entered<br/>
Name:<%=user.getnme8() %>
Email: <%= user.getEmail() %><BR>
Age: <%= user.getAge() %><BR>
<%
if(user.getnme8().equalsIgnoreCase("name1")){
	%><a href="page1.jsp">Go to Page1</a><%;
}
else
	%><a href="page2.jsp">Go to Page2</a><%;
%>
</body>
</html>