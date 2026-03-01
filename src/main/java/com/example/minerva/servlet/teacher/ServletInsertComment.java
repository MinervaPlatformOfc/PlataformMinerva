package com.example.minerva.servlet.teacher;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/teacher/insertComment", loadOnStartup = 1)
public class ServletInsertComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String content  = request.getParameter("comment");
        int score =  Integer.parseInt(request.getParameter("score"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        request.setAttribute("teacherId", teacherId);

        int year = Integer.parseInt(request.getParameter("year"));
        request.setAttribute("year", year);
        String houseName = request.getParameter("houseName");
        request.setAttribute("houseName", houseName);
        String subject = request.getParameter("subject");
        request.setAttribute("subject", subject);


        request.setAttribute("studentId", studentId);

        CommentDAO  commentDAO = new CommentDAO();
        commentDAO.insertComment(content,score,teacherId,subjectId,studentId);

        request.getRequestDispatcher("/teacher/studentComments").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
