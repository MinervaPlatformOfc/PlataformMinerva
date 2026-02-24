package com.example.minerva.servlet.adm.crudTeacher;

import com.example.minerva.dao.TeacherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/RemoveTeacher", asyncSupported = true)
public class ServletDeleteTeacher extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        boolean delete = (boolean) request.getAttribute("action");

        int id = (int) request.getAttribute("id");

        TeacherDAO teacherRepository = new TeacherDAO();

        if(delete){
            teacherRepository.delete(id);
            request.setAttribute("msg", "Professor removido com sucesso");
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
        }else{
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
        }
    }
}
