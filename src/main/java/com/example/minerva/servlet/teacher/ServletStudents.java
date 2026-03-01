package com.example.minerva.servlet.teacher;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dto.StudentGradeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/teacher/students", loadOnStartup = 1)
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

        Boolean showGrades = null;

        Object attribute = request.getAttribute("showGrades");
        if (attribute instanceof Boolean) {
            showGrades = (Boolean) attribute;
        }

        if (showGrades == null) {
            String param = request.getParameter("showGrades");
            showGrades = "true".equals(param); // default false se param for null ou diferente de "true" :)
        }

        request.setAttribute("showGrades", showGrades);

        TeacherDAO dao = new TeacherDAO();
        List<StudentGradeDTO> students = dao.getStudentsByTeacherYearAndSubject(teacherId, year, subject);

        request.setAttribute("students", students);
        request.setAttribute("year", year);
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("/professor/students.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
