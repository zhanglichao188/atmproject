<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <form action="${pageContext.request.contextPath}/saveMoneyDemo.action" method="post">
            <h1>存款功能</h1>
            <table>
                <tr>
                    <td>请输入存款金额:<input type="text" name="money"></td>
                </tr>
            </table>
            <input type="submit" value="点击确认">
        </form>
        <h1><font color="blue">点击返回<a href="${pageContext.request.contextPath}/atmMain.jsp">main</a>页面</font></h1>
    </center>
</body>
</html>
