package com.ydm.j2ee.web.servlet;

import com.google.gson.Gson;
import com.ydm.j2ee.core.model.User;
import com.ydm.j2ee.core.service.UService;
import com.ydm.j2ee.web.dto.Response_DTO;
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

@WebServlet("/getallusers")
public class GetAllUsers extends HttpServlet {

    @EJB
    private UService uService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loggeduser") != null){
            List<User> l = uService.allUsers();
            List<UserResponse_DTO> dtos = l.stream()
                    .map(UserResponse_DTO::new)
                    .collect(Collectors.toList());

            writeJsonResponse(resp, HttpServletResponse.SC_OK,
                    new Response_DTO(true, dtos), req.getContextPath() + "/admin/index.jsp");
        }else {
            writeJsonResponse(resp, HttpServletResponse.SC_UNAUTHORIZED,
                    new Response_DTO(true, "Unauthorized Request. Login First"), req.getContextPath() + "/index.jsp");
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
