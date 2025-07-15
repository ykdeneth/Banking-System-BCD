package com.ydm.j2ee.web.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ydm.j2ee.core.model.TransferDetails;
import com.ydm.j2ee.core.service.AccountService;
import com.ydm.j2ee.web.dto.Response_DTO;
import com.ydm.j2ee.web.dto.TransferDetailsDTO;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/transaction_history")
public class TransactionHistory extends HttpServlet {
    @EJB
    AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        List<TransferDetails> transactions = accountService.getTransactionsByUserEmail(email);

        List<TransferDetailsDTO> dtos = transactions.stream()
                .map(TransferDetailsDTO::new)
                .collect(Collectors.toList());

        Response_DTO responseDto = new Response_DTO(true, dtos);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(responseDto);

        System.out.println(transactions.size());
        System.out.println(transactions.toString());
        System.out.println(email);
        System.out.println(json);

        resp.setContentType("application/json");
        resp.getWriter().write(json);

    }
}
