package com.example.minerva.servlet.teacher;

import com.example.minerva.dao.GradeDAO;
import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dto.StudentGradeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;


import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/studentGrade")
public class ServletInsertGrade extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subject = request.getParameter("subject");
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String n1 = request.getParameter("n1");
        String n2 = request.getParameter("n2");
        if (!request.getParameter("n1Original").equals(n1) || !request.getParameter("n1Original").equals(n2)){
            GradeDAO dao = new GradeDAO();
            dao.updateGrades(dao.getSubjectId(subject),studentId,n1,n2);
        }

        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        request.setAttribute("teacherId", teacherId);
        String houseName = request.getParameter("houseName");
        request.setAttribute("houseName", houseName);
        int year = Integer.parseInt(request.getParameter("year"));
        request.setAttribute("showGrades", true);
        request.setAttribute("year", year);
        request.setAttribute("subject", subject);

        request.getRequestDispatcher("/teacher/students").forward(request, response);
    }
}

