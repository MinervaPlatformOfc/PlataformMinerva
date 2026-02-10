package com.example.minerva.servlet.student;

import com.example.minerva.dao.GradeDAO;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dto.ProfileDTO;
import com.example.minerva.dto.StudentGradeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/aluno/profile")
public class ServletProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        StudentDAO dao = new StudentDAO();
        ProfileDTO profile = dao.getStudentProfile(id);
        request.setAttribute("profile", profile);
        request.getRequestDispatcher("/aluno/profile.jsp").forward(request, response);
    }
}
