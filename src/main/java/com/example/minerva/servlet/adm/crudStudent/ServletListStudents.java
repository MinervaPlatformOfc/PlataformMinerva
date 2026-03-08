package com.example.minerva.servlet.adm.crudStudent;

import com.example.minerva.conexao.Conexao;
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Student> students = (List<Student>) getServletContext().getAttribute("studentList");

        String admName = request.getParameter("name");
        String url = request.getParameter("url");
        if (admName != null) {
            request.setAttribute("name", admName);
        }
        if (url != null) {
            request.setAttribute("url", url);
        }
        request.setAttribute("studentList", students);

        request.getRequestDispatcher("/admin/CRUD/Student.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }

}
