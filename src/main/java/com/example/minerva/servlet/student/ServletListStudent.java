package com.example.minerva.servlet.student;

import com.example.minerva.dao.StudentDAO;
import com.example.minerva.view.StudentForTeacherView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/student/listStudent")
public class ServletListStudent {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (String) request.getAttribute("path");
        StudentDAO  studentDAO = new StudentDAO();
        List<StudentForTeacherView> list = studentDAO.listStudents();
        request.setAttribute("list", list);

        request.getRequestDispatcher(path).forward(request, response);
    }
}
