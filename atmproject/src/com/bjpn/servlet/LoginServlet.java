package com.bjpn.servlet;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    ATMDao atmDao = new ATMDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决中文乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //接收参数
        String atmCoode = request.getParameter("atmCode");
        String atmPwd = request.getParameter("atmPwd");
        //调用方法访问数据库，查询用户是否存在
        ATM atm = atmDao.login(atmCoode, atmPwd);
        if (atm!= null) {
            //登入成功
            response.sendRedirect("atmMain.html");
        } else {
            //登入失败
            response.sendRedirect("loginError.html");
        }
    }
}
