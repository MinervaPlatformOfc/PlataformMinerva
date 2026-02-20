package com.example.minerva.servlet.teacher;

import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dto.StudentGradeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/students")
public class ServletStudents extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        request.setAttribute("teacherId", teacherId);
        String houseName = request.getParameter("houseName");
        request.setAttribute("houseName", houseName);

        int year = Integer.parseInt(request.getParameter("year"));
        String subject = request.getParameter("subject");

        String showGradesParam = request.getParameter("showGrades");
        boolean showGrades = "true".equals(showGradesParam);
        request.setAttribute("showGrades", showGrades);

        TeacherDAO dao = new TeacherDAO();
        List<StudentGradeDTO> students = dao.getStudentsByTeacherYearAndSubject(teacherId, year, subject);

        request.setAttribute("students", students);
        request.setAttribute("year", year);
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("/teacher/students.jsp").forward(request, response);
    }
}
