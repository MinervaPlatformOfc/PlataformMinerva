package com.example.minerva.servlet.adm.crudStudent;

import com.example.minerva.dao.StudentDAO;
import com.example.minerva.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/ViewStudents", loadOnStartup = 1)
public class ServletListStudents extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        StudentDAO studentRepository = new StudentDAO();

        List<Student> students = studentRepository.getAllStudents();

        request.setAttribute("studentList", students);

        request.getRequestDispatcher("/admin/CRUD/Student.jsp");


    }

}
