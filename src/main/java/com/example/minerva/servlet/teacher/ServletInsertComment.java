package com.example.minerva.servlet.teacher;

import com.example.minerva.dao.CommentDAO;
import com.example.minerva.dao.HouseDAO;
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
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        int house_id = Integer.parseInt(request.getParameter("house_id"));
        String path = request.getParameter("path");

        HttpSession session = request.getSession();
        int teacherId = (int) session.getAttribute("teacherId");
        int subjectId = (int) session.getAttribute("subjectId");

        HouseDAO houseDAO = new HouseDAO();
        houseDAO.updatePointsHouse(house_id, score);

        CommentDAO  commentDAO = new CommentDAO();
        commentDAO.insertComment(content,score,teacherId,subjectId,student_id);

        request.setAttribute("path","comment.jsp");

        response.sendRedirect(
                request.getContextPath()
                        + "/teacher/ListComment?student_id=" + student_id
                        + "&house_id=" + house_id
                        + "&path=/professor/comment.jsp"
        );

    }
}
