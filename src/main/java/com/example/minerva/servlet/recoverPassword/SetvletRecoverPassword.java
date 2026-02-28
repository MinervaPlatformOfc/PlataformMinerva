package com.example.minerva.servlet.recoverPassword;

import com.example.minerva.dao.UserDAO;
import com.example.minerva.utils.email.Email;
import com.example.minerva.utils.otp.Otp;
import com.example.minerva.utils.validacao.ValidacaoEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/recoverPassword")
public class SetvletRecoverPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        Email emailService = new Email();
        UserDAO dao = new UserDAO();
        Otp otp = new Otp();
        if (dao.findByEmail(email)!=null) emailService.sendRegistration(email, otp.generateAndSave(email));
        else {
            req.getRequestDispatcher("/recoverPassword/reciveEmail.jsp?error=user_not_exists").forward(req, resp);
            return;
        }

        req.setAttribute("email", email);
        req.getRequestDispatcher("/recoverPassword/validate.jsp").forward(req, resp);
    }
}
