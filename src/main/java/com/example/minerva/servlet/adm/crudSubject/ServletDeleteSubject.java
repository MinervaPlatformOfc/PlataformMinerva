package com.example.minerva.servlet.adm.crudSubject;

import com.example.minerva.dao.GradeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/deleteSubject", loadOnStartup = 1)
public class ServletDeleteSubject extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        int id = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("name", name);
        req.setAttribute("url", url);
        GradeDAO dao = new GradeDAO();

        dao.delete(id);
        req.getRequestDispatcher("/admin/ViewSubjects");
    }
}
