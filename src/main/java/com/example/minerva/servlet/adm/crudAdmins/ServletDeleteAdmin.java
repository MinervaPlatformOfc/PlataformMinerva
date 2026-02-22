package com.example.minerva.servlet.adm.crudAdmins;

import com.example.minerva.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/removeAdmin", asyncSupported = true)
public class ServletDeleteAdmin extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        boolean delete = (boolean) request.getAttribute("action");

        int id = (int) request.getAttribute("id");

        UserDAO userRepository = new UserDAO();

        if(delete){
            userRepository.delete(id);
            request.setAttribute("msg", "Admin removido com sucesso");
            request.getRequestDispatcher("/admin/ViewAdmins").forward(request, response);
        }else{
            request.getRequestDispatcher("/admin/ViewAdmins").forward(request, response);
        }


    }
}
