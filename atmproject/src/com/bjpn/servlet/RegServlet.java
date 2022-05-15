package com.bjpn.servlet;

import com.bjpn.dao.ATMDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    ATMDao atmDao = new ATMDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决中文乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取用户名
        String atmName = request.getParameter("atmName");
        //获取密码
        String atmPwd = request.getParameter("atmPwd");
        //调用方法连接数据库，插入一条数据
        int count = atmDao.reg(atmName, atmPwd);
        if (count != -1) {
            //数据插入成功
            response.sendRedirect("atmLogin.html");
        } else {
            //数据插入失败
            response.sendRedirect("regError.html");
        }
    }
}
