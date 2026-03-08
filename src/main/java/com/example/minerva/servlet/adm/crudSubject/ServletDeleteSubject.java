package com.example.minerva.servlet.adm.crudSubject;

import com.example.minerva.dao.GradeDAO;
import com.example.minerva.loader.RechargeListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/deleteSubject", loadOnStartup = 1)
public class ServletDeleteSubject extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        GradeDAO dao = new GradeDAO();

        req.setAttribute("msg", dao.delete(id)?
                "Disciplina deletada com sucesso":
                "Erro ao deletar disciplina");

        RechargeListener rechargeListener = new RechargeListener();
        rechargeListener.rechargeForSubject();

        req.getRequestDispatcher("/admin/ViewSubjects").forward(req, resp);
    }
}
