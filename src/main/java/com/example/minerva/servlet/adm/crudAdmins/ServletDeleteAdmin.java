package com.example.minerva.servlet.adm.crudAdmins;

import com.example.minerva.dao.UserDAO;
import com.example.minerva.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/removeAdmin", asyncSupported = true)
public class ServletDeleteAdmin extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        boolean delete = (boolean) request.getAttribute("action");

        int id = (int) request.getAttribute("id");

        UserDAO userRepository = new UserDAO();

        HttpSession session = request.getSession();


        User currentUser = (User) session.getAttribute("user");



        if(delete){
           if(currentUser.getEmail().equals(userRepository.findById(id).getEmail())){
               request.setAttribute("msg", "Não é possível se auto deletar");
               request.getRequestDispatcher("/admin/ViewAdmins");
           }else{
               userRepository.delete(id);
               request.setAttribute("msg", "Admin removido com sucesso");
               request.getRequestDispatcher("/admin/ViewAdmins").forward(request, response);
           }
        }else{
            request.getRequestDispatcher("/admin/ViewAdmins").forward(request, response);
        }


    }
}
