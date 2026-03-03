package com.example.minerva.servlet.adm.crudSubject;

import com.example.minerva.dao.GradeDAO;
import com.example.minerva.loader.RechargeListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/createSubject", loadOnStartup = 1)
public class ServletCreateSubject extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subjectName = req.getParameter("subjectName");
        boolean addStudents = Boolean.parseBoolean(req.getParameter("addStudents"));

        GradeDAO dao = new GradeDAO();



        req.setAttribute("msg", dao.save(subjectName, addStudents)?
                "Disciplina inserida com sucesso!":
                "Erro ao inserir disciplina!");

        RechargeListener rechargeListener = new RechargeListener();
        rechargeListener.rechargeForSubject();



        req.getRequestDispatcher("/admin/ViewSubjects").forward(req, resp);
    }
}
