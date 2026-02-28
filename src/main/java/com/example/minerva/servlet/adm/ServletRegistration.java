package com.example.minerva.servlet.adm;

import com.example.minerva.conexao.Conexao;
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

@WebServlet(urlPatterns = "/admin/generateRegistration", asyncSupported = true)
public class ServletRegistration extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AsyncContext async = req.startAsync();


        String email = req.getParameter("email");
        String role = req.getParameter("role");

        if (ValidacaoEmail.validarEmail(email)) {

            Matricula matriculaService = new Matricula();
            Email emailService = new Email();

            String registration =
                    matriculaService.generateAndSave(role, email);

            async.start(() -> {
                try {
                    emailService.sendRegistration(email, registration);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    async.complete();
                }
            });


            req.setAttribute("msg", "Email enviado com matrícula");
            req.getRequestDispatcher("/admin/home.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
