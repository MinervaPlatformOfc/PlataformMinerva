package com.example.minerva.servlet.adm.crudTeacher;

import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.model.Teacher;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/UpdateTeacher", asyncSupported = true)
public class ServletUpdateTeacher extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        boolean update = (boolean) request.getAttribute("action");

        int id = (int) request.getAttribute("id");

        UserDAO userRepository = new UserDAO();
        TeacherDAO teacherRepository = new TeacherDAO();

        if(update){
            String name = (String) request.getAttribute("nameInput");
            String email = (String) request.getAttribute("emailInput");
            String password = String.valueOf(new HashSenha((String) request.getAttribute("passwordInput")));
            String wand = (String) request.getAttribute("wandInput");
            boolean headHouse = (boolean) request.getAttribute("headHouseInput");
            String pastExperiences = (String) request.getAttribute("pastExperiencesInput");
            String wizardTitle = (String) request.getAttribute("wizardTitleInput");

            User currentUser = new User(name, password, email);
            Teacher currentTeacher = new Teacher(wand, headHouse, pastExperiences, wizardTitle);

            if(userRepository.update(teacherRepository.getUserIdById(id), currentUser) > 0 && teacherRepository.update(id, currentTeacher) > 0){
                request.setAttribute("msg", "Professor atualizado com sucesso");
                request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
            }else{
                request.setAttribute("msg", "Erro ao atualizar professor");
                request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
            }

        }else{
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
        }
    }
}
