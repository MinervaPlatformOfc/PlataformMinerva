package com.example.minerva.servlet.adm.crudTeacher;

import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.model.Teacher;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.minerva.utils.matricula.Matricula;

@WebServlet(urlPatterns = "/admin/CreateTeacher", asyncSupported = true)
public class ServletCreateTeacher extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Matricula matricula = new Matricula();
        TeacherDAO teacherRepository = new TeacherDAO();

        String email = (String) request.getAttribute("emailInput");
        String password = String.valueOf(new HashSenha((String) request.getAttribute("passwordInput")));
        String name = (String)  request.getAttribute("nameInput");

        User newUser = new User(name, email, password);

        Integer houseId = (Integer) request.getAttribute("houseIdInput");
        String wand = (String) request.getAttribute("wandInput");
        Boolean headHouse = (Boolean) request.getAttribute("headHouseInput");
        String pastExperiences = (String) request.getAttribute("pastExperiencesInput");
        String wizardTitle = (String) request.getAttribute("wizardTitleInput");
        String teacherRegistrationCode = matricula.generateAndSave("TEACHER", (String) request.getAttribute("email"));

        Teacher newTeacher = new Teacher(houseId, wand, headHouse, pastExperiences, wizardTitle, teacherRegistrationCode);

        teacherRepository.save(newTeacher, newUser);

        request.setAttribute("msg", "Professor inserido com sucesso!");

        request.getRequestDispatcher("/admin/ViewTeachers").forward(request,response);
    }

}
