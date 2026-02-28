package com.example.minerva.servlet.adm;

import com.example.minerva.utils.email.Email;
import com.example.minerva.utils.matricula.Matricula;
import com.example.minerva.utils.validacao.ValidacaoEmail;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet(urlPatterns = "/admin/generateRegistration")
public class ServletRegistration extends HttpServlet {

    private static final ExecutorService executor =
            Executors.newFixedThreadPool(2);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String role = req.getParameter("role");

        if (ValidacaoEmail.validarEmail(email)) {

            Matricula matriculaService = new Matricula();
            Email emailService = new Email();

            String registration =
                    matriculaService.generateAndSave(role, email);

            executor.submit(() -> {
                try {
                    emailService.sendRegistration(email, registration);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            req.setAttribute("msg", "Email enviado com matrícula");
            req.getRequestDispatcher("/admin/home.jsp").forward(req, resp);
        }
    }
}
