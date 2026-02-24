package com.example.minerva.servlet.teacher;

import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dto.TeacherHomeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/professor/home")
public class ServletHome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getAttribute("email");
        String teacherId = req.getParameter("teacherId");
        TeacherDAO teacherDAO = new TeacherDAO();
        TeacherHomeDTO teacherHomeDto;
        if (email!=null && !email.isEmpty()) teacherHomeDto = teacherDAO.getTeacherWithStudentsByEmail(email);
        else teacherHomeDto = teacherDAO.getTeacherWithStudentsById(Integer.parseInt(teacherId));
        req.setAttribute("teacherHomeDto", teacherHomeDto);
        req.getRequestDispatcher("/professor/home.jsp")
                .forward(req, resp);
    }
}
