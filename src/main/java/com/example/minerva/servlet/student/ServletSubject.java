package com.example.minerva.servlet.student;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.CommentDAO;
import com.example.minerva.dao.GradeDAO;
import com.example.minerva.dto.StudentGradeDTO;
import com.example.minerva.dto.SubjectDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/aluno/subject")
public class ServletSubject extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id", id);
        String houseName = request.getParameter("houseName");
        request.setAttribute("houseName", houseName);
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        String subjectName = request.getParameter("subjectName");

        CommentDAO dao = new CommentDAO();
        SubjectDTO subject = new SubjectDTO(subjectId, subjectName, dao.getCommentsBySubjectAndUser(id, subjectId));
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("/aluno/subject.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
