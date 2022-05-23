<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登入页面</title>
</head>
<body>
<center>
    <h1>欢迎登录ATM系统</h1>
    <form action="${pageContext.request.contextPath}/loginDemo.action" method="post">
        <table>
            <tr>
                <td>账号</td>
                <td><input type="text" name="atmCode" value="${cookie.atmCode.value}"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="atmPwd" value="${cookie.atmPwd.value}"></td>
            </tr>
        </table>
        <input type="checkbox" name="flag" checked="checked">记住密码
        <input type="submit" value="登录">
        <a href="reg.jsp"><input type="button" value="注册"></a>
        <h1><font color="red">${result}</font></h1>
    </form>
</center>
</body>
</html>
