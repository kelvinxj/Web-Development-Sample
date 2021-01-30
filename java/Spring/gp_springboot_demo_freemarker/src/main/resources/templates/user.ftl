<html>
    <head>
        <title>用户信息</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <table border="1" align="center" width="50%">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
            </tr>
            <#list list as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.userName}</td>
                    <td>${user.age}</td>
                </tr>
            </#list>
        </table>
    </body>
</html>