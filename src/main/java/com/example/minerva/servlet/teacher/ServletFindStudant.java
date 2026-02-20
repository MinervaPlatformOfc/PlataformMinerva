package com.example.minerva.servlet.teacher;

import com.example.minerva.dao.StudentDAO;
import com.example.minerva.view.StudentForTeacherView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/teacher/findStudent")
public class ServletFindStudant extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("ID recebido: " + id);
        StudentDAO dao = new StudentDAO();
        StudentForTeacherView studentForTeacherView = dao.findStudentByRegistration(id);
        request.setAttribute("student", studentForTeacherView);
        request.getRequestDispatcher("/professor/teste.jsp").forward(request, response);

    }

}
