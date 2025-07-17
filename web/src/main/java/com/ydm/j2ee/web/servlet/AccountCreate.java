package com.ydm.j2ee.web.servlet;

import com.google.gson.Gson;
import com.ydm.j2ee.core.mail.VerificationMail;
import com.ydm.j2ee.core.model.User;
import com.ydm.j2ee.core.provider.MailServiceProvider;
import com.ydm.j2ee.core.service.AccountService;
import com.ydm.j2ee.core.service.UService;
import com.ydm.j2ee.web.dto.Response_DTO;
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

            writeJsonResponse(resp, HttpServletResponse.SC_NOT_FOUND,
                    new Response_DTO(false, "User not found"), req.getContextPath() + "/index.jsp");
        } else {
//           boolean b = accountService.createAccount(u,0.00);
//            System.out.println(b);

            try {
                boolean b = accountService.createAccount(u, 50000.00);
//                System.out.println(b);
                if (b) {
                writeJsonResponse(resp, HttpServletResponse.SC_OK,
                        new Response_DTO(b, "Account Created Success"), req.getContextPath() + "/index.jsp");
                }else {
                    writeJsonResponse(resp, HttpServletResponse.SC_OK,
                            new Response_DTO(false, "Account Create Fail"), req.getContextPath() + "/index.jsp");
                }
            } catch (Exception e) {
                // Handle unauthorized access gracefully
                // 401

                writeJsonResponse(resp, HttpServletResponse.SC_UNAUTHORIZED,
                        new Response_DTO(false, "Unauthorized Request"), req.getContextPath() + "/index.jsp");

                // Serialize with Gson or JSON-B


                return; // stop further processing
            }
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
