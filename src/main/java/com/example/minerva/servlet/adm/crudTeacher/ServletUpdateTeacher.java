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

@WebServlet(urlPatterns = "/admin/UpdateTeacher")
public class ServletUpdateTeacher extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        boolean update = Boolean.parseBoolean(request.getParameter("action"));

        int id = Integer.parseInt(request.getParameter("id"));

        UserDAO userRepository = new UserDAO();
        TeacherDAO teacherRepository = new TeacherDAO();

        if(update){
            String name = request.getParameter("nameInput");
            String email = request.getParameter("emailInput");
            String password = String.valueOf(new HashSenha(request.getParameter("passwordInput")));
            String wand = request.getParameter("wandInput");
            boolean headHouse = Boolean.parseBoolean(request.getParameter("headHouseInput"));
            String pastExperiences = request.getParameter("pastExperiencesInput");
            String wizardTitle = request.getParameter("wizardTitleInput");

            User currentUser = new User(name, password, email);
            Teacher currentTeacher = new Teacher(wand, headHouse, pastExperiences, wizardTitle);

            request.setAttribute("msg", userRepository.update(teacherRepository.getUserIdById(id), currentUser) > 0 && teacherRepository.update(id, currentTeacher) > 0 ?"Professor atualizado com sucesso": "Erro ao atualizar professor!");
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);

        }else{
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
        }
    }
}
