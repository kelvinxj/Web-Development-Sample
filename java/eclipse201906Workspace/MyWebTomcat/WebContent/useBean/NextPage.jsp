<!DOCTYPE html>
<jsp:useBean id="user" class="com.kelvin.web.bean.UserData" scope="application"/>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
You entered<br/>
Name:<%=user.getUsername() %>
Email: <%= user.getEmail() %><BR>
Age: <%= user.getAge() %><BR>
</body>
</html>