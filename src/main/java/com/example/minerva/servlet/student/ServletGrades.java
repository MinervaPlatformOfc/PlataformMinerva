package com.example.minerva.servlet.student;

import com.example.minerva.dao.GradeDAO;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dto.StudentGradeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/aluno/grades")
public class ServletGrades extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        int argument = Integer.parseInt(request.getParameter("argument"));
        GradeDAO dao = new GradeDAO();
        List<StudentGradeDTO> grades = dao.getStudentGrades(id);
        request.setAttribute("grades", grades);
        if (argument == 1) {
            request.getRequestDispatcher("/aluno/grades.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/aluno/grades.jsp").forward(request, response);
        }
    }
}
