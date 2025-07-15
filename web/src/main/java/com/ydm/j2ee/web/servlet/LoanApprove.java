package com.ydm.j2ee.web.servlet;

import com.google.gson.GsonBuilder;
import com.ydm.j2ee.core.model.Loan;
import com.ydm.j2ee.core.model.LoanStatus;
import com.ydm.j2ee.core.service.LoanApprovalService;
import com.ydm.j2ee.web.dto.Response_DTO;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/loanapprove")
public class LoanApprove extends HttpServlet {

    @EJB
    private LoanApprovalService loanApprovalService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loanIdStr = req.getParameter("loanId");
        String statusStr = req.getParameter("status");

        Response_DTO responseDto;

        try {
            Integer loanId = Integer.parseInt(loanIdStr);
            LoanStatus newStatus = LoanStatus.valueOf(statusStr.toUpperCase());

            Loan loan = loanApprovalService.updateLoanStatus(loanId, newStatus);

            responseDto = new Response_DTO(true, "Loan status updated to " + loan.getStatus());
        } catch (Exception e) {
            responseDto = new Response_DTO(false, "Error updating loan status: " + e.getMessage());
        }

        String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create().toJson(responseDto);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}

