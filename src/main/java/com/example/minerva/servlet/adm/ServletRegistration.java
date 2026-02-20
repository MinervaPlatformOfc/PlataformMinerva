package com.example.minerva.servlet.adm;

import com.example.minerva.utils.email.Email;
import com.example.minerva.utils.matricula.Matricula;
import com.example.minerva.utils.validacao.ValidacaoEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/generateRegistration")
public class ServletRegistration extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if(ValidacaoEmail.validarEmail(email)){
            Matricula matriculaService = new Matricula();
            Email emailService = new Email();
            if (!emailService.sendRegistration(email, matriculaService.generateAndSave("STUDENT", email))) req.setAttribute("erro", "Ocorreu um erro durante o envio do código de matrícula!");
            req.getRequestDispatcher("/admin/home.jsp").forward(req, resp);
        }
    }
}
