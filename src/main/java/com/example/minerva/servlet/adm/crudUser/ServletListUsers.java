package com.example.minerva.servlet.adm.crudUser;

import com.example.minerva.dao.UserDAO;
import com.example.minerva.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/users")
public class ServletListUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        double[] stats = dao.getUserStatistics();
        req.setAttribute("stats", stats);

        String email = req.getParameter("email");

        if(email==null || email.isEmpty()){
            User user = dao.findByEmail(email);
            req.setAttribute("name", user.getName());
            req.setAttribute("url", user.getImageUrl());
        }

        req.setAttribute("users", dao.getNotAdmins());
        req.getRequestDispatcher("/admin/home.jsp");
    }
}
