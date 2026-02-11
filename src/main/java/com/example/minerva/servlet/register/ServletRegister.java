package com.example.minerva.servlet.register;

import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.model.Student;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;
import com.example.minerva.utils.matricula.Matricula;
import com.example.minerva.utils.validacao.ValidacaoEmail;
import com.example.minerva.utils.validacao.ValidacaoSenha;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/register")
public class ServletRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest  request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String pet = request.getParameter("pet");
        String allergies = request.getParameter("allergies");
        String blood = request.getParameter("blood");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String wand = request.getParameter("wood")+" - "+request.getParameter("core")+" - "+request.getParameter("flexibility");
        int schoolYear = Integer.parseInt(request.getParameter("schoolYear"));
        String legalGuardianName = request.getParameter("legalGuardianName");
        String residenceAdress = request.getParameter("residenceAdress");
        boolean guardianPermission =  Boolean.parseBoolean(request.getParameter("guardianPermission"));
        boolean basicKit = Boolean.parseBoolean(request.getParameter("BasicKit"));

        String matricula = Matricula.gerarMatricula("STUDENT");
        UserDAO userDao = new UserDAO();

//      Validações
        if (!ValidacaoSenha.validarSenha(password) || !ValidacaoEmail.validarEmail(email) || userDao.findByEmail(email)!=null) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }
        String hash = new HashSenha(request.getParameter("password")).getHashSenha();

        StudentDAO studentDao = new StudentDAO();
        Student student =  new Student(birthDate, schoolYear, legalGuardianName, residenceAdress, wand, pet, allergies, blood, basicKit, guardianPermission, matricula);
        User user = new User(name, email, hash, "student");
        studentDao.save(student, user);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        request.setAttribute("email", email);

//      Redirect para o descobrir a qual casa o aluno pertence
        request.getRequestDispatcher("/aluno/quiz/quiz.jsp").forward(request, response);
    }
}
