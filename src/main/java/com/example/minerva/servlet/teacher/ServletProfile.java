package com.example.minerva.servlet.teacher;
import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dto.TeacherProfileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/teacher/profile")
public class ServletProfile extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        request.setAttribute("teacherId", teacherId);
        String houseName = request.getParameter("houseName");
        request.setAttribute("houseName", houseName);

        TeacherDAO dao = new TeacherDAO();
        TeacherProfileDTO profile = dao.getTeacherProfile(teacherId);

        request.setAttribute("profile", profile);
        request.getRequestDispatcher("/professor/profile.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
