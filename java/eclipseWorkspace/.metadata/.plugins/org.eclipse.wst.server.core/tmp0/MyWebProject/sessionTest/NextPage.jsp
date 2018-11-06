<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show you name page</title>
</head>
<body>
Hello,
<%= session.getAttribute("theName").toString() %>
</body>
</html>