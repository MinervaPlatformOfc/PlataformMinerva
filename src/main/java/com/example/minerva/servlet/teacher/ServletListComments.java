package com.example.minerva.servlet.teacher;
import com.example.minerva.dao.CommentDAO;
import com.example.minerva.dao.GradeDAO;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dto.CommentDTO;
import com.example.minerva.dto.ProfileDTO;
import com.example.minerva.dto.SubjectDTO;
import com.example.minerva.dto.TeacherProfileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/studentComments")
public class ServletListComments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GradeDAO dao = new GradeDAO();
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String subject = request.getParameter("subject");
        int subjectId = dao.getSubjectId(subject);

        CommentDAO commentDAO = new CommentDAO();
        List<CommentDTO> listComments = commentDAO.findBySubjectStudentTeacher(subjectId,studentId,teacherId);

        request.setAttribute("teacherId", teacherId);
        request.setAttribute("subjectId", subjectId);
        request.setAttribute("studentId", studentId);
        request.setAttribute("subject", subject);

        String houseName = request.getParameter("houseName");
        request.setAttribute("houseName", houseName);

        int year = Integer.parseInt(request.getParameter("year"));
        request.setAttribute("year", year);

        StudentDAO studentDAO = new StudentDAO();
        ProfileDTO profile = studentDAO.getStudentProfile(studentId);
        request.setAttribute("profile", profile);
        request.setAttribute("listComments", listComments);

        request.getRequestDispatcher("/professor/comment.jsp").forward(request, response);
    }
}
