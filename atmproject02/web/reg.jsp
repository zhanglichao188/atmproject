<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/16
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<center>
    <h1>欢迎注册ATM系统</h1>
    <form action="${pageContext.request.contextPath}/regDemo.action" method="post">
        <table>
            <tr>
                <td>姓名</td>
                <td><input type="text" name="atmName" value=""></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="atmPwd" value=""></td>
            </tr>
        </table>
        <input type="submit" value="注册">
    </form>
    </center>
</body>
</html>
