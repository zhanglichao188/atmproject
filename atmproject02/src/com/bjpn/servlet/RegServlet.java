package com.bjpn.servlet;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

public class RegServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String atmName = request.getParameter("atmName");
        String atmPwd = request.getParameter("atmPwd");
        String atmCode = "888";
        ATMDao atmDao = new ATMDao();
        while (true) {
            atmCode=atmCode+new Random().nextInt(1000);
            boolean b = atmDao.checkCode(atmCode);
            if (!b) {
                break;
            }
        }
        ATM atm = new ATM();
        atm.setAtmCode(atmCode);
        atm.setAtmName(atmName);
        atm.setAtmPwd(atmPwd);
        atm.setAtmMoney(0);
        boolean result = atmDao.atmReg(atm);
        String path = request.getContextPath();
        if (result) {
            HttpSession session = request.getSession();
            session.setAttribute("atmCode", atmCode);
            response.sendRedirect(path+"/regSuccess.jsp");
        } else {
            response.sendRedirect(path+"/regFail.jsp");
        }
    }
}
