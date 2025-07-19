package com.ydm.j2ee.web.servlet;

import com.ydm.j2ee.core.model.TransactionType;
import com.ydm.j2ee.core.service.UserTimerSchedul;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/timerSchedul")
public class UserSchedulServlet extends HttpServlet {

    @EJB
    UserTimerSchedul userSchedulTimerBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet timerSchedul");
//            userSchedulTimerBean.createTimer(12,52,10);
        String sourceAccountNo = req.getParameter("sourceAccountNo");
        String destinationAccountNo = req.getParameter("destinationAccountNo");
        double amount = Double.parseDouble(req.getParameter("amount"));
        String reason = req.getParameter("reason");
//        String type = req.getParameter("type");

        int year = Integer.parseInt(req.getParameter("year"));
        int month = Integer.parseInt(req.getParameter("month"));
        int day = Integer.parseInt(req.getParameter("day"));
        System.out.println(year + "-" + month + "-" + day);
        userSchedulTimerBean.createTransferTimer(year, month, day, sourceAccountNo, destinationAccountNo, amount, reason, String.valueOf(TransactionType.SCHEDULED));

        resp.getWriter().write("Transfer scheduled successfully");
    }
}
