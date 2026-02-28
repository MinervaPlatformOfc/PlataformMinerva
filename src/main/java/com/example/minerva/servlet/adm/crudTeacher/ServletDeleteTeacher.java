package com.example.minerva.servlet.adm.crudTeacher;

import com.example.minerva.dao.TeacherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/RemoveTeacher")
public class ServletDeleteTeacher extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        boolean delete = Boolean.parseBoolean(request.getParameter("action"));

        int id = Integer.parseInt(request.getParameter("id"));

        TeacherDAO teacherRepository = new TeacherDAO();

        if(delete){
            request.setAttribute("msg", teacherRepository.delete(id) ? "Professor removido com sucesso": "Erro ao remover professor!");
        }

        response.sendRedirect("/admin/ViewTeachers");
    }
}
