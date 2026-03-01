package com.example.minerva.servlet.adm.crudSubject;

import com.example.minerva.dao.GradeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/updateSubject", loadOnStartup = 1)
public class ServletUpdateSubject extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subjectName = req.getParameter("subjectName");
        int id = Integer.parseInt(req.getParameter("id"));

        GradeDAO dao = new GradeDAO();

        dao.update(id, subjectName);
        req.getRequestDispatcher("/admin/ViewSubjects").forward(req, resp);
    }
}
