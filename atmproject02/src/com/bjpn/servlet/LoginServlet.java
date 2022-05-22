package com.bjpn.servlet;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String atmCode = request.getParameter("atmCode");
        String atmPwd = request.getParameter("atmPwd");
        String flag = request.getParameter("flag");
        ATMDao atmDao = new ATMDao();
        ATM atm = atmDao.atmLogin(atmCode, atmPwd);
        System.out.println(atm);
        if (atm != null) {
            if (flag != null) {
                Cookie cookie_atmCode = new Cookie("atmCode", atmCode);
                Cookie cookie_atmPwd = new Cookie("atmPwd", atmPwd);
                cookie_atmCode.setMaxAge(60 * 60 * 24 * 7);
                cookie_atmPwd.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie_atmCode);
                response.addCookie(cookie_atmPwd);
            } else {
                Cookie cookie_atmCode = new Cookie("atmCode", atmCode);
                Cookie cookie_atmPwd = new Cookie("atmPwd", atmPwd);
                cookie_atmCode.setMaxAge(0);
                cookie_atmPwd.setMaxAge(0);
                response.addCookie(cookie_atmCode);
                response.addCookie(cookie_atmPwd);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", atm);
            response.sendRedirect("atmMain.jsp");
        } else {
            request.setAttribute("result", "账号或密码错误");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
