package com.ydm.j2ee.web.servlet;

import com.google.gson.Gson;
import com.ydm.j2ee.core.model.Loan;
import com.ydm.j2ee.core.model.User;
import com.ydm.j2ee.web.dto.LoanResponse_DTO;
import com.ydm.j2ee.web.dto.Response_DTO;
import com.ydm.j2ee.web.dto.UserResponse_DTO;
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

@WebServlet("/loanUsers")
public class LoanUsersServlet extends HttpServlet {
    @PersistenceContext
    private EntityManager em;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet calling");
        List<Loan> loans = em.createQuery(
                "SELECT l FROM Loan l JOIN FETCH l.account a JOIN FETCH a.user u",
                Loan.class
        ).getResultList();

        List<LoanResponse_DTO> loanDTOs = loans.stream()
                .map(LoanResponse_DTO::new)
                .collect(Collectors.toList());
        System.out.println(loanDTOs);
        Response_DTO response = new Response_DTO(true, loanDTOs);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new Gson().toJson(response));
    }
}
