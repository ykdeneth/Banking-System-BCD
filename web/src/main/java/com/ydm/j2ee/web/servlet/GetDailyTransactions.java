package com.ydm.j2ee.web.servlet;

import com.google.gson.Gson;
import com.ydm.j2ee.core.model.TransferDetails;
import com.ydm.j2ee.core.service.TodayTransaction;
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

@WebServlet("/dailyTransaction")
public class GetDailyTransactions extends HttpServlet {

    @EJB
    private TodayTransaction dailyTransactionProcessor;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TransferDetails> todaysTransactions = dailyTransactionProcessor.getTodaysTransactions();

        // Wrap in your Response_DTO (assuming it's imported or fully qualified)
        List<TransferDetailsDTO> dtos = todaysTransactions.stream()
                .map(TransferDetailsDTO::new)
                .collect(Collectors.toList());

        Response_DTO responseDto = new Response_DTO(true, dtos);

        // Serialize with Gson or JSON-B
        Gson gson = new Gson();
        String json = gson.toJson(responseDto);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
