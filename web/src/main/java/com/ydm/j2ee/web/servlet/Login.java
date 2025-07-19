package com.ydm.j2ee.web.servlet;

import com.ydm.j2ee.core.model.User;
import com.ydm.j2ee.core.model.UserType;
import com.ydm.j2ee.core.service.UService;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ydm.j2ee.core.exception.LoginFailedException;
import com.ydm.j2ee.core.util.Encryption;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    @EJB
    UService uService;

    @Inject
    private SecurityContext securityContext;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email + " " + password);

        //String encryptedPassword = Encryption.encrypt(password);

        AuthenticationParameters parameters = AuthenticationParameters.withParams()
                        .credential(new UsernamePasswordCredential(email, Encryption.encrypt(password)));
        if(request.getSession().getAttribute("loggeduser") != null) {
            System.out.println("loggeduser already logged in");
            request.getSession().invalidate();
        }
        System.out.println("parameters :" + parameters);
        AuthenticationStatus status = securityContext.authenticate(request, response, parameters);
        System.out.println("status:" + status);

        if (status == AuthenticationStatus.SUCCESS) {
            System.out.println("Authentication successful");
            HttpSession session = request.getSession();
            session.setAttribute("loggeduser", email);
            User u = uService.getUserByEmail(email);
            System.out.println(session.getAttribute("loggeduser"));
            if (u.getUserType().equals(UserType.ADMIN)) {
                response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
            }else {
                response.sendRedirect(request.getContextPath() + "/user/index.jsp");
            }

        } else {
            System.out.println("Authentication failed");
            throw new LoginFailedException("Invalid username or password");
        }

    }
}
