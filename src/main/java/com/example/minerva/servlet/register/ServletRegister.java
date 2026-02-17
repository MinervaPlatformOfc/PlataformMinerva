package com.example.minerva.servlet.register;

import com.example.minerva.conexao.CloudinaryConfig;
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
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Map;

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
        Part filePart = request.getPart("image");


        String registration = request.getParameter("registration");
        Matricula matricula = new Matricula();
        if (!matricula.validate(email, registration)){
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }
        UserDAO userDao = new UserDAO();

//      Validações
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();

        if (!ValidacaoSenha.validarSenha(password) || userDao.findByEmail(email)!=null || age < 11 || age>13 || birthDate.isAfter(today)) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }
        String hash = new HashSenha(request.getParameter("password")).getHashSenha();

        //Armazenar imagem no Cloudinary
        InputStream inputStream = filePart.getInputStream();
        Cloudinary cloudinary = CloudinaryConfig.getInstance();
        Map uploadResult = cloudinary.uploader().upload(
                inputStream,
                ObjectUtils.asMap(
                        "folder", "profile_images"
                )
        );
        String imageUrl = uploadResult.get("secure_url").toString();

        StudentDAO studentDao = new StudentDAO();
        Student student =  new Student(birthDate, schoolYear, legalGuardianName, residenceAdress, wand, pet, allergies, blood, basicKit, guardianPermission, registration);
        User user = new User(name, hash, email, "student", imageUrl);
        studentDao.save(student, user);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        request.setAttribute("email", email);

//      Redirect para o descobrir a qual casa o aluno pertence
        request.getRequestDispatcher("/aluno/quiz/quiz.jsp").forward(request, response);
    }
}
