package com.bjpn.servlet;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveMoneyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String money = request.getParameter("money");
        HttpSession session = request.getSession();
        ATM atm = (ATM)session.getAttribute("user");
        atm.setAtmMoney(Double.valueOf(money));
        ATMDao atmDao = new ATMDao();
        boolean result = atmDao.saveMoney(atm);
        String path = request.getContextPath();
        if (result) {
            response.sendRedirect(path + "/saveMoneySuccess.jsp");
        } else {
            response.sendRedirect(path+"/saveMoneyFail.jsp");
        }
    }
}
