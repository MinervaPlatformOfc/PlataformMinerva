package com.example.minerva.servlet.teacher;

import com.example.minerva.dao.CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/teacher/insertComment")
public class ServletInsertComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content  = request.getParameter("comment");
        int score =  Integer.parseInt(request.getParameter("score"));
        HttpSession session = request.getSession();
        int teacherId = 1;//(int) session.getAttribute("teacherId");
        int subjectId = 3;//(int) session.getAttribute("subjectId");
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        int pontos = 0;
        if (score == 0){
            pontos = -50;
        } else {
            pontos = 50;
        }

        CommentDAO  commentDAO = new CommentDAO();
        commentDAO.insertComment(content,pontos,teacherId,subjectId,student_id);
        response.sendRedirect(
                request.getContextPath() + "/teacher/ListComment?student_id=" + student_id
        );

    }
}
