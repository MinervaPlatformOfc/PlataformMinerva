package com.example.minerva.servlet.student;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.GradeDAO;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dto.ProfileDTO;
import com.example.minerva.dto.SubjectDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.security.auth.Subject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/aluno/subjects")
public class ServletSubjects extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id", id);
        String houseName = request.getParameter("houseName");
        request.setAttribute("houseName", houseName);
        GradeDAO dao = new GradeDAO();
        List<SubjectDTO> subjects = dao.getSubjects(id);
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("/aluno/subjects.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
