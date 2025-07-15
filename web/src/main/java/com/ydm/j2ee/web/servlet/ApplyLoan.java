package com.ydm.j2ee.web.servlet;

import com.google.gson.GsonBuilder;
import com.ydm.j2ee.core.model.Loan;
import com.ydm.j2ee.core.service.LoanService;
import com.ydm.j2ee.web.dto.Response_DTO;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/applyLoan")
public class ApplyLoan extends HttpServlet {
    @EJB
    private LoanService loanService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountNo = request.getParameter("accountNo"); // must send accountNo param
        String amountStr = request.getParameter("amount");

        Response_DTO dto;

        try {
            double amount = Double.parseDouble(amountStr);
            Loan loan = loanService.requestLoan(accountNo, amount);

            Map<String, Object> content = new HashMap<>();
            content.put("id", loan.getId());

            dto = new Response_DTO(true,  content);
        } catch (Exception e) {
            dto = new Response_DTO(false, "Loan request failed: " + e.getMessage());
        }

        String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create().toJson(dto);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}

