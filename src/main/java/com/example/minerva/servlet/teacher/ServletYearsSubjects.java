package com.example.minerva.servlet.teacher;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.TeacherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/yearsSubjects")
public class ServletYearsSubjects extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        request.setAttribute("teacherId", teacherId);
        String houseName = request.getParameter("houseName");
        request.setAttribute("houseName", houseName);

        String showGradesParam = request.getParameter("showGrades");
        boolean showGrades = "true".equals(showGradesParam);
        request.setAttribute("showGrades", showGrades);

        TeacherDAO dao = new TeacherDAO();
        List<String> yearsSubjects = dao.getYearsAndSubjectsByTeacherId(teacherId);

        request.setAttribute("yearsSubjects", yearsSubjects);
        request.getRequestDispatcher("/professor/yearsSubjects.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
