package com.example.minerva.servlet.student;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.HouseDAO;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dto.StudentHomeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/aluno/home")
public class ServletHome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getAttribute("email");
        String id = request.getParameter("id");
        HouseDAO houseDao = new HouseDAO();
        StudentDAO studentDAO = new StudentDAO();
        StudentHomeDTO homeDto;
        if (email!=null && !email.isEmpty()) homeDto = studentDAO.getStudentByEmail(email);
        else homeDto = studentDAO.getStudentById(Integer.parseInt(id));
        request.setAttribute("ranking", houseDao.viewRanking());
        request.setAttribute("homeDto", homeDto);
        request.getRequestDispatcher("/aluno/home.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
