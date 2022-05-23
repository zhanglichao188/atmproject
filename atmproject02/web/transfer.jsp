<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="jquery-1.11.1.js"></script>
    <script>
        $(function () {
            if (${transferResult}===false) {
                alert("输入的卡号不存在");
            }
        });
        function tijiao() {
            if ($("#atmCode").val()==""||$("#atmCode2").val()=="") {
                alert("卡号不能为空，请输入卡号");
                return false;
            }
            if ($("#atmCode").val()!=$("#atmCode2").val()) {
                alert("两次卡号不一致，请重输！");
                return false;
            }
            if ($("#jin").val()=="") {
                $("#transfer_span").html("<font color='red'>转账金额不能为空</font>");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
   <center>
       <h1>转账功能</h1>
       <form action="${pageContext.request.contextPath}/transferDemo.action" method="post" onsubmit="return tijiao();">
           <table>
               <tr>
                   <td>请输入对方卡号</td>
                   <td><input type="text" name="receive_atmCode" id="atmCode" value=""></td>
               </tr>
               <tr>
                   <td>请确认对方卡号</td>
                   <td><input type="text" name="receive_atmCode2" id="atmCode2" value=""></td>
               </tr>
               <tr>
                   <td>请输入转账金额</td>
                   <td><input type="text" name="transfer_money" id="jin" value=""></td>
                   <td><span id="transfer_span"></span></td>
               </tr>
           </table>
           <input type="submit" value="确认转账">
           <h1><font color="red">${result}</font></h1>
       </form>
       <h1><font color="blue">点击返回<a href="${pageContext.request.contextPath}/atmMain.jsp">main</a>页面</font></h1>
   </center>
</body>
</html>
