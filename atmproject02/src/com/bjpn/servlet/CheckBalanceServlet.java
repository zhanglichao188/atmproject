package com.bjpn.servlet;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckBalanceServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ATM atm = (ATM) session.getAttribute("user");
        String atmCode = atm.getAtmCode();
        ATMDao atmDao = new ATMDao();
        String path = request.getContextPath();
        double balance = atmDao.checkMoney(atmCode);
        if (balance != 0) {
            session.setAttribute("balance", balance);
            response.sendRedirect(path + "/balance.jsp");
        } else {
            response.sendRedirect(path+"/balanceFail.jsp");
        }
    }
}
