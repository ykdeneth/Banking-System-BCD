package com.ydm.j2ee.web.filter;

import com.google.gson.Gson;
import com.ydm.j2ee.web.dto.Response_DTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/transfer","/createAcc","/timerSchedul","/transaction_history","/dailyTransaction", "/profile","/applyLoan","/getallusers"})
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute("loggeduser") != null) {
            filterChain.doFilter(request, servletResponse);
        }else {
            System.out.println("loggeduser is null therefore Login First");
//            response.sendRedirect("index.jsp");
            writeJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    new Response_DTO(false, "loggeduser is null therefore Login First"), request.getContextPath() + "/index.jsp");
        }
    }
    private void writeJsonResponse(HttpServletResponse resp, int status, Response_DTO responseDto, String path) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(responseDto);
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
//        resp.sendRedirect(path);
    }
}
