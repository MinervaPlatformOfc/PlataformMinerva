package com.example.minerva.servlet.teacher;
import com.example.minerva.dao.CommentDAO;
import com.example.minerva.dto.CommentDTO;
import com.example.minerva.view.CommentView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/teacher/ListComment")
public class ServletListComments extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("entrou no servlet slk");

        int student_id = Integer.parseInt(request.getParameter("student_id"));
        //nn sei se faz sentido pq eu to fazendo com sono mas aqui lista os comentarios d
        // aluno e o id house é so para passar pro proximo servlet que seria o de insert
        int house_id = Integer.parseInt(request.getParameter("house_id"));
        //eu vou usar esse servlet para caminhos diferentes. quais? nao faço ideia agora, mas sei q ele é reultilizavel
        String path = (String)request.getParameter("path");

        HttpSession session = request.getSession();

        int teacher_id = (int) session.getAttribute("teacherId");
        int subject_id = (int) session.getAttribute("subjectId");

        CommentDAO commentDAO = new CommentDAO();
        List<CommentView> listComments = commentDAO.listAllByStudent(student_id);

        request.setAttribute("teacherId", teacher_id);
        request.setAttribute("subjectId", subject_id);
        request.setAttribute("studentId", student_id);
        request.setAttribute("listComments", listComments);
        //request.setAttribute("studentName", listComments.get(0).getStudent());
        request.setAttribute("house_id", house_id);


        request.getRequestDispatcher(path)
                .forward(request, response);
    }
}
