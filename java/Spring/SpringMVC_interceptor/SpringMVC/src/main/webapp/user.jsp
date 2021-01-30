<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <script type="text/javascript" src="jquery-3.5.1.min.js" ></script>
</head>
<body>
<input type="button" value="post" onclick="func1();"/>
<script type="text/javascript">
    function func1(){
        $.ajax(
            {
                type:"POST",
                url: "user/save",
                contentType: "application/json",
                data: JSON.stringify({"userName":"sam","age":"12"}),
                dataType: "text",
                success: function(data){alert("success!" + data)},
                error: function(data){alert("error!" + data)}
            }
        );
    }
</script>
</body>
</html>
