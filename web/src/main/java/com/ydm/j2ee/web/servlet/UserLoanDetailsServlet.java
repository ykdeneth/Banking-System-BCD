package com.ydm.j2ee.web.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ydm.j2ee.core.model.Loan;
import com.ydm.j2ee.web.dto.LoanDetailsResponse_DTO;
import com.ydm.j2ee.web.dto.Response_DTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/userLoanDetails")
public class UserLoanDetailsServlet extends HttpServlet {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loggedEmail = (String) req.getSession().getAttribute("loggeduser");
        if (loggedEmail == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("{\"success\":false,\"content\":\"Not logged in.\"}");
            return;
        }

        // Get all loans where the account's user matches the logged-in email
        List<Loan> loans = em.createQuery(
                        "SELECT l FROM Loan l JOIN l.account a JOIN a.user u WHERE u.email = :email", Loan.class)
                .setParameter("email", loggedEmail)
                .getResultList();

        List<LoanDetailsResponse_DTO> dtos = loans.stream()
                .map(LoanDetailsResponse_DTO::new)
                .collect(Collectors.toList());

        Response_DTO responseDto = new Response_DTO(true, dtos);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(responseDto);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
