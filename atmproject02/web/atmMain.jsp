<%@ page import="com.bjpn.bean.ATM" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/16
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ATM主页面</title>
</head>
<body>
    <center>
        <h1>登入成功</h1>
        <h1>欢迎<font color="#8a2be2">${user.atmName}</font>使用ATM系统</h1>
        <ul>
            <li><a href="${pageContext.request.contextPath}/saveMoney.jsp">存款</a></li>
            <li><a href="${pageContext.request.contextPath}/drawMoney.jsp">取款</a></li>
            <li><a href="${pageContext.request.contextPath}/transfer.jsp">转账</a></li>
            <li><a href="${pageContext.request.contextPath}/checkBalanceDemo.action">查看余额</a></li>
            <li><a href="${pageContext.request.contextPath}/login.jsp">退出</a></li>
        </ul>
    </center>
</body>
</html>
