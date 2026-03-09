package com.example.minerva.servlet.adm.crudAdmins;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.loader.RechargeListener;
import com.example.minerva.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/removeAdmin", loadOnStartup = 1)
public class ServletDeleteAdmin extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        int id = Integer.parseInt(request.getParameter("id"));

        UserDAO userRepository = new UserDAO();

        HttpSession session = request.getSession();

        User currentUser = (User) session.getAttribute("user");

           request.setAttribute("msg",
                   currentUser.getEmail().equals(userRepository.findById(id).getEmail())?"Não é possível se auto deletar":
                           userRepository.delete(id)?"Administrador removido com sucesso":
                                   "Falha ao remover administrador");
        RechargeListener rechargeListener = new RechargeListener();
        rechargeListener.rechargeForAdmin(getServletContext());
        request.getRequestDispatcher("/admin/ViewAdmins").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
