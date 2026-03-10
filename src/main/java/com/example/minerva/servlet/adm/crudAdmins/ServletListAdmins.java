package com.example.minerva.servlet.adm.crudAdmins;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.dto.AdminDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/ViewAdmins", loadOnStartup = 1)
public class ServletListAdmins extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        List<AdminDTO> admins = (List<AdminDTO>) getServletContext().getAttribute("adminList");
        request.setAttribute("adminList", admins);
        String admName = null;
        String url = null;

        // Primeiro tenta dos atributos
        Object nameAttr = request.getAttribute("name");
        if (nameAttr != null) {
            admName = nameAttr.toString();
        }

        Object urlAttr = request.getAttribute("url");
        if (urlAttr != null) {
            url = urlAttr.toString();
        }

// Se não achou nos atributos, tenta dos parâmetros
        if (admName == null) {
            admName = request.getParameter("name");
        }

        if (url == null) {
            url = request.getParameter("url");
        }

// Seta os atributos para o JSP
        if (admName != null) request.setAttribute("name", admName);
        if (url != null) request.setAttribute("url", url);

        request.getRequestDispatcher("/admin/CRUD/Admin.jsp").forward(request, response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }


    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
