package com.example.minerva.servlet.teacher;

import com.example.minerva.dao.SubjectStudentDAO;
import com.example.minerva.model.SubjectStudent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;


import java.io.IOException;
@WebServlet("/teacher/subject")
public class ServletInsertNotes extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ENTROU NO SERVLET");

        double n1 = Double.parseDouble(request.getParameter("n1"));
        double n2 = Double.parseDouble(request.getParameter("n2"));
        int subject_id = Integer.parseInt(request.getParameter("subject_id"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));

        SubjectStudentDAO subjectStudentDAO = new SubjectStudentDAO();
        SubjectStudent subjectStudent = subjectStudentDAO.insert(subject_id, n1, n2, student_id);
        if(subjectStudent != null){
            System.out.println("Salvo com sucesso");
        }else{
            System.out.println("Erro ao salvar");
        }


        System.out.println("salvo no banco com sucesso");

        request.getRequestDispatcher("/professor/home.jsp").forward(request, response);
    }
}

