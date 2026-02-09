package com.example.minerva.control;

import com.example.minerva.model.Subject_student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "InsertNotesServlet", value = "/notes/insert")
public class InsertNotesServlet extends HttpServlet {
   protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       double n1 =  Integer.parseInt(request.getParameter("n1"));
       double n2 = Integer.parseInt(request.getParameter("n2"));
       int student_id = Integer.parseInt(request.getParameter("id"));
       int subject_id = Integer.parseInt(request.getParameter("subject_id"));

       Subject_student model = new Subject_student(n1, n2, student_id ,subject_id);


   }
}
