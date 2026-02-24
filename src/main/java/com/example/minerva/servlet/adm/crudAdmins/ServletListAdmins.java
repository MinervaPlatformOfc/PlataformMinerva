package com.example.minerva.servlet.adm.crudAdmins;

import com.example.minerva.dao.UserDAO;
import com.example.minerva.dto.AdminDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/ViewAdmins", asyncSupported = true)
public class ServletListAdmins extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        UserDAO userRepository = new UserDAO();
        List<AdminDTO> admins = userRepository.getAllAdmins();
        request.setAttribute("adminList", admins);
        request.getRequestDispatcher("/admin/CRUD/Admin.jsp").forward(request, response);
    }
}
