package com.example.minerva.servlet.recoverPassword;

import com.example.minerva.dao.UserDAO;
import com.example.minerva.utils.criptografia.HashSenha;
import com.example.minerva.utils.validacao.ValidacaoSenha;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/recoverPassword/updatePassword")
public class ServletUpdatePassword extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        if (!ValidacaoSenha.validarSenha(password)){
            resp.sendRedirect(req.getContextPath() + "/recoverPassword/update.jsp?error=password_invalid");
            return;
        }

        HashSenha hash = new HashSenha(password);

        UserDAO dao = new UserDAO();
        dao.updatePassword(email, hash.getHashSenha());
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
