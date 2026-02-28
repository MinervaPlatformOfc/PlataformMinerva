package com.example.minerva.servlet.student;

import com.example.minerva.conexao.Conexao;
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

@WebServlet(urlPatterns = "/aluno/grades", loadOnStartup = 1)
public class ServletGrades extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id", id);
        String houseName = request.getParameter("houseName");
        request.setAttribute("houseName", houseName);
        GradeDAO dao = new GradeDAO();
        List<StudentGradeDTO> grades = dao.getStudentGrades(id);
        request.setAttribute("grades", grades);
        request.getRequestDispatcher("/aluno/grades.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
