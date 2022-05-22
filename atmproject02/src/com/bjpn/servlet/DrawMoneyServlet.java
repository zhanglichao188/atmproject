package com.bjpn.servlet;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DrawMoneyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String money = request.getParameter("money");
        HttpSession session = request.getSession();
        ATM atm = (ATM) session.getAttribute("user");
        String atmCode = atm.getAtmCode();
        ATMDao atmDao = new ATMDao();
        String path = request.getContextPath();
        double balance = atmDao.checkMoney(atmCode);
        if (balance>=Double.parseDouble(money)) {
            boolean result = atmDao.drawMoney(money, atmCode);
            if (result) {
                session.setAttribute("money", money);
                response.sendRedirect(path + "/drawMoneySuccess.jsp");
            } else {
                response.sendRedirect(path + "/drawMoneyFail.jsp");
            }
        } else {
            session.setAttribute("error", "抱歉余额不足！");
            response.sendRedirect(path+"/drawMoney.jsp");
        }
    }
}
