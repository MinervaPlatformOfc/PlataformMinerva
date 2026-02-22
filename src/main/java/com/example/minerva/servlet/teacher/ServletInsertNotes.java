package com.example.minerva.servlet.teacher;

import com.example.minerva.dao.SubjectStudentDAO;
import com.example.minerva.model.SubjectStudent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;


import java.io.IOException;
@WebServlet("/teacher/insertNotes")
public class ServletInsertNotes extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ENTROU NO SERVLET");
        int schoolYear = Integer.parseInt(request.getParameter("school_year"));

        String n1Param = request.getParameter("n1");
        String n2Param = request.getParameter("n2");

        double n1 = (n1Param == null || n1Param.isEmpty()) ? 0 : Double.parseDouble(n1Param);
        double n2 = (n2Param == null || n2Param.isEmpty()) ? 0 : Double.parseDouble(n2Param);
        int subject_id = Integer.parseInt(request.getParameter("subject_id"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));

        SubjectStudentDAO subjectStudentDAO = new SubjectStudentDAO();
        SubjectStudent subjectStudent = subjectStudentDAO.save(subject_id, n1, n2, student_id);
        if(subjectStudent != null){
            System.out.println("Salvo com sucesso");
        }else{
            System.out.println("Erro ao salvar");
        }


        System.out.println("salvo no banco com sucesso");
        response.sendRedirect(
                request.getContextPath() + "/teacher/listStudentByNotes?schoolYear=" + schoolYear
        );
    }
}

