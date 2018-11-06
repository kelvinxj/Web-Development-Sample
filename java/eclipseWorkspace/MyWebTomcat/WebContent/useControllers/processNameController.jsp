<!DOCTYPE html>
<jsp:useBean id="user" class="com.kelvin.web.bean.UserData" scope="application"/>
<jsp:setProperty name="user" property="*"/>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String tgtPage = null;
if(user.getUsername().equalsIgnoreCase("name1")){
	tgtPage = "page1.jsp";
}
else
	tgtPage = "page2.jsp";
	
response.sendRedirect(tgtPage);
//request.getRequestDispatcher(tgtPage).forward(request, response);

 %>
</body>
</html>