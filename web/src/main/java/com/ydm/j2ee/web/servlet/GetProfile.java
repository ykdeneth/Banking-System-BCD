package com.ydm.j2ee.web.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ydm.j2ee.core.model.User;
import com.ydm.j2ee.core.service.UService;
import com.ydm.j2ee.web.dto.Response_DTO;
import com.ydm.j2ee.web.dto.TransferDetailsDTO;
import com.ydm.j2ee.web.dto.UserResponse_DTO;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/profile")
public class GetProfile extends HttpServlet {

    @EJB
    private UService uService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        System.out.println(email);
        if (req.getSession().getAttribute("loggeduser") != null && req.getSession().getAttribute("loggeduser").equals(email)) {
            System.out.println("loggeduser is " + req.getSession().getAttribute("loggeduser"));
//            User u = uService.getUserByEmail(email);
            List<User> l = uService.getUserByEmail2(email);
            List<UserResponse_DTO> dtos = l.stream()
                    .map(UserResponse_DTO::new)
                    .collect(Collectors.toList());


            Response_DTO responseDto = new Response_DTO(true, dtos);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(responseDto);

            System.out.println(json);

            resp.setContentType("application/json");
            resp.getWriter().write(json);

        }
    }
}
