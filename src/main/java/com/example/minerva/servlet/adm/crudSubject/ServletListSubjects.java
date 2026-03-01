package com.example.minerva.servlet.adm.crudSubject;

import com.example.minerva.dao.GradeDAO;
import com.example.minerva.dto.SubjectDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/ViewSubjects", loadOnStartup = 1)
public class ServletListSubjects extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        req.setAttribute("name", name);
        req.setAttribute("url", url);
        req.setAttribute("subjects", (List<SubjectDTO>) getServletContext().getAttribute("subjectList"));
        req.getRequestDispatcher("/admin/CRUD/Subjects.jsp").forward(req, resp);
    }
}
