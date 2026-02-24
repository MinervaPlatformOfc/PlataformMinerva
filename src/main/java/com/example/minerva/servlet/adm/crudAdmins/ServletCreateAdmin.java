package com.example.minerva.servlet.adm.crudAdmins;

import com.example.minerva.dao.UserDAO;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/createAdmin", asyncSupported = true)
public class ServletCreateAdmin extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        UserDAO userRepository = new UserDAO();

        String email = (String) request.getAttribute("emailInput");
        String password = String.valueOf(new HashSenha((String) request.getAttribute("passwordInput")));
        String name = (String)  request.getAttribute("nameInput");

        User newUser = new User(name, email, password);

        userRepository.saveAdmin(newUser);

        request.setAttribute("msg", "Administrador inserido com sucesso!");

        request.getRequestDispatcher("/admin/ViewAdmins").forward(request, response);
    }
}
