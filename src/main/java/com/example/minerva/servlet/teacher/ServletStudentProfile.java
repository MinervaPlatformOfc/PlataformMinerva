package com.example.minerva.servlet.teacher;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dto.ProfileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/teacher/studentProfile")
public class ServletStudentProfile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherId = request.getParameter("teacherId");
        request.setAttribute("teacherId", Integer.parseInt(teacherId));
        int id = Integer.parseInt(request.getParameter("studentId"));
        String houseName = request.getParameter("houseName");
        request.setAttribute("houseName", houseName);
        StudentDAO dao = new StudentDAO();
        ProfileDTO profile = dao.getStudentProfile(id);
        request.setAttribute("profile", profile);
        request.getRequestDispatcher("/professor/studentProfile.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
