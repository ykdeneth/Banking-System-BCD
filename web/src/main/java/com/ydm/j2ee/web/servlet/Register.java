package com.ydm.j2ee.web.servlet;

import com.google.gson.Gson;
import com.ydm.j2ee.core.model.*;
import com.ydm.j2ee.web.dto.Response_DTO;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ydm.j2ee.core.mail.VerificationMail;
import com.ydm.j2ee.core.provider.MailServiceProvider;
import com.ydm.j2ee.core.service.UService;
import com.ydm.j2ee.core.util.Encryption;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@WebServlet("/register")
public class Register extends HttpServlet {

    @EJB
    private UService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        String accType = request.getParameter("accType");

        String encryptedPassword = Encryption.encrypt(password);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setPassword(encryptedPassword);
        String verificationCode = UUID.randomUUID().toString();
        user.setVerificationCode(verificationCode);

        if (userType.equals("admin")) {
            user.setUserType(UserType.ADMIN);
            user.setStatus(Status.ACTIVE);
            user.setVerifyState(VerifyState.VERIFIED_ACCOUNT);
        } else if (userType.equals("user")) {
            user.setUserType(UserType.USER);
        } else {
            user.setUserType(UserType.OFFICER);
        }
        if(accType.equals("saving")) {
            user.setAccType(AccType.SAVING_ACCOUNT);
        }else if(accType.equals("current")) {
            user.setAccType(AccType.CURRENT_ACCOUNT);
        }else if(accType.equals("")) {
            System.out.println("Invalid accType or accType is Empty");
            writeJsonResponse(response, HttpServletResponse.SC_NOT_FOUND,
                    new Response_DTO(false, "Invalid accType or accType is Empty"), request.getContextPath() + "/index.jsp");
        }

        System.out.println(name + " " + email + " " + contact + " " + encryptedPassword);

        User u = userService.getUserByEmail(email);
        if (u == null) {
            System.out.println(name + " " + email + " " + contact + " " + encryptedPassword);
            userService.addUser(user);
            VerificationMail mail = new VerificationMail(email, verificationCode);
            MailServiceProvider.getInstance().sendMail(mail);

            writeJsonResponse(response, HttpServletResponse.SC_OK,
                    new Response_DTO(true, "User Created"), request.getContextPath() + "/index.jsp");

//            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else {
            System.out.println(u);
//            response.getWriter().write("User with email " + email + " already exists");
            writeJsonResponse(response, HttpServletResponse.SC_FOUND,
                    new Response_DTO(false, "User with email " + email + " already exists"), request.getContextPath() + "/index.jsp");

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
