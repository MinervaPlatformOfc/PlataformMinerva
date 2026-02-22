package com.example.minerva.servlet.student;

import com.example.minerva.dao.StudentDAO;
import com.example.minerva.view.StudentForTeacherView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/listByYear")
public class ServletListByYear extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int schoolYear = Integer.parseInt(request.getParameter("schoolYear"));
        StudentDAO studentDAO = new StudentDAO();
        List<StudentForTeacherView> list = studentDAO.listStudentBySchoolYear(schoolYear);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/professor/student_comment.jsp").forward(request, response);

    }
}
