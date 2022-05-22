package com.bjpn.servlet;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TransferServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String receive_atmCode = request.getParameter("receive_atmCode");
        String transfer_money = request.getParameter("transfer_money");
        ATMDao atmDao = new ATMDao();
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        ATM atm = (ATM)session.getAttribute("user");
        double balance = atmDao.checkMoney(atm.getAtmCode());
        if (balance<Double.valueOf(transfer_money)) {
            session.setAttribute("balanceResult", "你的余额不足");
            response.sendRedirect(path+"/transferFail.jsp");
            return;
        }
        boolean b = atmDao.checkCode(receive_atmCode);
        if (b) {
            ATM atm1 = new ATM();
            atm1.setAtmMoney(Double.valueOf(transfer_money));
            atm1.setAtmCode(receive_atmCode);
            boolean result = atmDao.transferMoney(atm, atm1);
            if (result) {
                session.setAttribute("transfer_money", transfer_money);
                response.sendRedirect(path + "/transferSuccess.jsp");
            } else {
                response.sendRedirect(path + "/transferFail.jsp");
            }
        } else {
            session.setAttribute("transferResult", "这个卡号不存在");
            response.sendRedirect(path+"/transferFail.jsp");
        }


    }
}
