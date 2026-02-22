package com.example.minerva.servlet.student;

import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dao.SubjectStudentDAO;
import com.example.minerva.dto.StudentByNotesDTO;
import com.example.minerva.view.StudentForTeacherView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
@WebServlet("/teacher/listStudentByNotes")
public class ServletListStudentByNotes extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ENTROU NO POST");
        HttpSession session = request.getSession();
        int schoolYear = Integer.parseInt(request.getParameter("schoolYear"));
        int subjectId = (int) session.getAttribute("subjectId");
        SubjectStudentDAO  subjectStudentDAO = new SubjectStudentDAO();
        List<StudentByNotesDTO> list = subjectStudentDAO.listNotesByYear(schoolYear, subjectId);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/professor/student_notes.jsp").forward(request, response);

    }
}
