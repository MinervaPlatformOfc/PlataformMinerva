package com.example.minerva.servlet.adm.crudStudent;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/RemoveStudent", loadOnStartup = 1)
public class ServletDeleteStudent extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        boolean delete = Boolean.parseBoolean(request.getParameter("action"));

        int id = Integer.parseInt(request.getParameter("id"));

        StudentDAO studentRepository = new StudentDAO();

        if(delete){
            request.setAttribute("msg", studentRepository.delete(id) ? "Estudante removido com sucesso":"Falha ao remover estudante");
        }
        response.sendRedirect("/admin/ViewStudents");
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
