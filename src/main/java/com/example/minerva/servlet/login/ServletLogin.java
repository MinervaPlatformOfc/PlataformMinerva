package com.example.minerva.servlet.login;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.*;
import com.example.minerva.dto.StudentHomeDTO;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class ServletLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        System.out.println("ENTROU NO SERVLET LOGIN");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UserDAO dao = new UserDAO();
        User user = dao.findByEmail(email);

        HashSenha hash = new HashSenha(senha);
//        System.out.println(hash.getHashSenha());

        if(user != null && hash.getHashSenha().equals(user.getPassword())) {

            HttpSession session = request.getSession();

            session.setAttribute("user", user);

            String role = user.getRole();

            switch(role) {

                case "student":
                    request.setAttribute("email", email);
                    if (user.isFirstAcess()) {request.getRequestDispatcher("/aluno/quiz/quiz.jsp").forward(request, response);}
                    else {request.getRequestDispatcher("/aluno/home").forward(request, response);}
                    break;

                case "teacher":
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("/professor/home")
                            .forward(request, response);
                    break;

                case "admin":
                    response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
                    break;

                default:
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    break;
            }

        } else {
//            System.out.println("LOGIN INVALIDO");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
