package com.example.minerva.servlet.login;

import com.example.minerva.dao.HouseDAO;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.dto.StudentHomeDTO;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO dao;
        User user;
        HttpSession session;
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        dao = new UserDAO();
        user = dao.findByEmail(email);

        HashSenha hash = new HashSenha(senha);

        if(user != null && hash.getHashSenha().equals(user.getPassword())) {

            session = request.getSession();
            session.setAttribute("user", user);

            String role = user.getRole();

            switch(role) {
                case "student":
                    if (user.isFirstAcess()){
                        request.setAttribute("email", email);
                        request.getRequestDispatcher("/aluno/quiz/quiz.jsp").forward(request, response);
                    }
                    else {
                        HouseDAO houseDao = new HouseDAO();
                        StudentDAO studentDAO = new StudentDAO();
                        StudentHomeDTO homeDto = studentDAO.getStudentByEmail(email);
                        request.setAttribute("ranking", houseDao.viewRanking());
                        request.setAttribute("homeDto", homeDto);
                        request.getRequestDispatcher("/aluno/home.jsp").forward(request, response);
                    }
                    break;
                case "teacher":
                    response.sendRedirect(request.getContextPath() + "/professor/home.jsp");
                    break;
                case "admin":
                    response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    break;
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }


}
