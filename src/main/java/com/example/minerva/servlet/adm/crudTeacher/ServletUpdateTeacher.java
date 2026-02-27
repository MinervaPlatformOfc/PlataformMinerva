package com.example.minerva.servlet.adm.crudTeacher;

import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        TeacherDAO teacherRepository = new TeacherDAO();

        if(update){
            String wand = (String) request.getAttribute("wandInput");
            boolean headHouse = (boolean) request.getAttribute("headHouseInput");
            String pastExperiences = (String) request.getAttribute("pastExperiencesInput");
            String wizardTitle = (String) request.getAttribute("wizardTitleInput");

            Teacher currentTeacher = new Teacher(wand, headHouse, pastExperiences, wizardTitle);

            String[] originalSubjectsParam = request.getParameterValues("originalSubjects");
            List<Integer> originalSubjects = new ArrayList<>();
            if (originalSubjectsParam != null) {
                for (String s : originalSubjectsParam) {
                    originalSubjects.add(Integer.parseInt(s));
                }
            }

            String[] newSubjectsParam = request.getParameterValues("newSubjects");
            List<Integer> newSubjects = new ArrayList<>();
            if (newSubjectsParam != null) {
                for (String s : newSubjectsParam) {
                    newSubjects.add(Integer.parseInt(s));
                }
            }

            // Matérias removidas: estavam na original, mas não estão na nova
            List<Integer> subjectsToRemove = new ArrayList<>(originalSubjects);
            subjectsToRemove.removeAll(newSubjects);

            // Matérias adicionadas: estão na nova, mas não estavam na original
            List<Integer> subjectsToAdd = new ArrayList<>(newSubjects);
            subjectsToAdd.removeAll(originalSubjects);

            // Atualizar o banco
            for (Integer subjectId : subjectsToRemove) {
                teacherRepository.removeSubjectFromTeacher(id, subjectId);
            }

            for (Integer subjectId : subjectsToAdd) {
                teacherRepository.addSubjectToTeacher(id, subjectId);
            }

            if(teacherRepository.update(id, currentTeacher) > 0){
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
