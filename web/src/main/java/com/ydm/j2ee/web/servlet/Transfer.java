package com.ydm.j2ee.web.servlet;

import com.ydm.j2ee.core.service.AccountService;
import com.ydm.j2ee.core.service.UService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ydm.j2ee.core.service.TransferService;

import java.io.IOException;

@WebServlet("/transfer")
public class Transfer extends HttpServlet {

    @EJB
    private TransferService transferService;

    @EJB
    private UService uService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sourceAccountNumber = request.getParameter("sourceAccount");
        String destinationAccountNumber = request.getParameter("destinationAccountNo");
        String amount = request.getParameter("amount");
        String reason = request.getParameter("reason");
        String type = request.getParameter("type");
        System.out.println("sourceAccountNumber: " + sourceAccountNumber + " destinationAccountNumber: " + destinationAccountNumber + " amount: " + amount + " reason: " + reason + " type: " + type);

        if(request.getSession().getAttribute("loggeduser") == null) {
            response.getWriter().write("You are not logged in");
        }

        boolean validacc = uService.validAccount((String) request.getSession().getAttribute("loggeduser"), sourceAccountNumber);
        System.out.println("is has a valid account: " + validacc);
        if(validacc){
            transferService.
                    transferAmount(sourceAccountNumber,destinationAccountNumber,Double.parseDouble(amount), reason, type);
//            System.out.println("transfer success");
//            response.sendRedirect("index.jsp");
        }else {
            System.out.println("Invalid account number");
            response.getWriter().println("Invalid account number");
        }



    }
}
