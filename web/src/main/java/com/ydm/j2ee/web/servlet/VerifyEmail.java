package com.ydm.j2ee.web.servlet;

import com.ydm.j2ee.core.model.VerifyState;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ydm.j2ee.core.model.Status;
import com.ydm.j2ee.core.model.User;
import com.ydm.j2ee.core.service.UService;

import java.io.IOException;
import java.util.Base64;

@WebServlet("/verify")
public class VerifyEmail extends HttpServlet {

    @EJB
    private UService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String vc = request.getParameter("vc");

        byte[] bytes = Base64.getDecoder().decode(id);
        String email = new String(bytes);

        User user = userService.getUserByEmail(email);
        if (user != null && user.getVerificationCode().equals(vc)) {
            user.setVerifyState(VerifyState.VERIFIED_ACCOUNT);
            userService.updateUser(user);

        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");


    }
}
