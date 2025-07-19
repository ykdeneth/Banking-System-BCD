package com.ydm.j2ee.web.servlet;

import com.ydm.j2ee.core.service.UService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/userStateChange")
public class UserStateChange extends HttpServlet {

    @EJB
    private UService uService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("userId"));
        boolean b = uService.getUserById2(id);
    }
}
