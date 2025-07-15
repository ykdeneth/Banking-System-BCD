package com.ydm.j2ee.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/transfer","/createAcc","/timerSchedul","/transaction_history","/dailyTransaction", "/profile","/applyLoan"})
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute("loggeduser") != null) {
            filterChain.doFilter(request, servletResponse);
        }else {
            System.out.println("loggeduser is null therefore Login First");
            response.sendRedirect("index.jsp");
        }
    }
}
