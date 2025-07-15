package com.ydm.j2ee.web.servlet;

import com.ydm.j2ee.core.mail.VerificationMail;
import com.ydm.j2ee.core.model.User;
import com.ydm.j2ee.core.provider.MailServiceProvider;
import com.ydm.j2ee.core.service.AccountService;
import com.ydm.j2ee.core.service.UService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/createAcc")
public class AccountCreate extends HttpServlet {

    @EJB
    private AccountService accountService;

    @EJB
    private UService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter("email"));
       User u = userService.getUserByEmail(req.getParameter("email"));

        if (u == null) {
            System.out.println("no such account for this email");
            resp.getWriter().write("User not found" );
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }else {
//           boolean b = accountService.createAccount(u,0.00);
//            System.out.println(b);

            try {
                boolean b = accountService.createAccount(u, 50000.00);
                System.out.println(b);
            } catch (Exception e) {
                // Handle unauthorized access gracefully
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
                resp.getWriter().println("Unauthorized request");
                return; // stop further processing
            }
        }

    }
}
