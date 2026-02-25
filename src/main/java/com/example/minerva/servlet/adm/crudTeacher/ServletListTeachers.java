package com.example.minerva.servlet.adm.crudTeacher;

import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dto.TeacherDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/ViewTeachers")
public class ServletListTeachers extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TeacherDAO teacherRepository = new TeacherDAO();

        List<TeacherDTO> teachers = teacherRepository.getAllTeachers();

        request.setAttribute("teacherList", teachers);

        request.getRequestDispatcher("/admin/CRUD/Teacher.jsp").forward(request, response);

    }
}
