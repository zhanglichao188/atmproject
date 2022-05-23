package com.bjpn.filter;

import com.bjpn.bean.ATM;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        String path = request.getServletPath();
        System.out.println("当前请求为:"+path);
        if (path.equals("/login.jsp") || path.equals("/loginDemo.action") || path.equals("/reg.jsp") || path.equals("/regDemo.action")) {
            filterChain.doFilter(request, servletResponse);
        } else {
            ATM atm = (ATM)request.getSession().getAttribute("user");
            if (atm != null) {
                filterChain.doFilter(request, servletResponse);
            } else {
                HttpServletResponse response=(HttpServletResponse)servletResponse;
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }
}
