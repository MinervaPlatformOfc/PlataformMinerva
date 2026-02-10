package com.example.minerva.servlet.student;

import com.example.minerva.dao.HouseDAO;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dto.StudentHomeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/aluno/quiz")
public class ServletQuiz extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDAO dao = new StudentDAO();
        String email = req.getParameter("email");
        dao.updateHouseIdByEmail(email, req.getParameter("houseName"));

        HouseDAO houseDao = new HouseDAO();
        StudentDAO studentDAO = new StudentDAO();
        StudentHomeDTO homeDto = studentDAO.getStudentByEmail(email);
        req.setAttribute("ranking", houseDao.viewRanking());
        req.setAttribute("homeDto", homeDto);
        req.getRequestDispatcher("/aluno/home.jsp").forward(req, resp);
    }
}
