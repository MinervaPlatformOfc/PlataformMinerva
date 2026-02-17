package com.example.minerva.servlet.student;

import com.example.minerva.dao.HouseDAO;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.dto.StudentHomeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/aluno/quiz")
public class ServletQuiz extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDAO dao = new StudentDAO();
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        dao.updateHouseIdByEmail(email, req.getParameter("houseName"));
        HouseDAO houseDao = new HouseDAO();
        StudentDAO studentDAO = new StudentDAO();
        StudentHomeDTO homeDto = studentDAO.getStudentByEmail(email);
        UserDAO userDAO = new UserDAO();
        req.setAttribute("ranking", houseDao.viewRanking());
        req.setAttribute("homeDto", homeDto);
        HttpSession session = req.getSession();
        session.setAttribute("user", userDAO.findByEmail(email));
        req.getRequestDispatcher("/aluno/home.jsp").forward(req, resp);
    }
}
