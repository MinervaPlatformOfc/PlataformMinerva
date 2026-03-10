package com.example.minerva.servlet.adm.crudTeacher;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.loader.RechargeListener;
import com.example.minerva.model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/admin/updateTeacher", loadOnStartup = 1)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class ServletUpdateTeacher extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));

        TeacherDAO teacherRepository = new TeacherDAO();

        // Pegar os três campos da varinha
        String wood = request.getParameter("wood");
        String core = request.getParameter("core");
        String flexibility = request.getParameter("flexibility");
        // Montar a wand no formato "madeira - nucleo - flexibilidade"
        String wand = wood + " - " + core + " - " + flexibility;

        // Pegar os demais campos
        boolean headHouse = "on".equals(request.getParameter("headHouse")); // checkbox retorna "on" quando marcado
        String pastExperiences = request.getParameter("pastExperiences");
        String wizardTitle = request.getParameter("wizardTitle");

        Teacher currentTeacher = new Teacher(wand, headHouse, pastExperiences, wizardTitle);

        // Processar matérias
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
        }else{
            request.setAttribute("msg", "Erro ao atualizar professor");
        }

        RechargeListener rechargeListener = new RechargeListener();
        rechargeListener.rechargeForTeacher(getServletContext());

        request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}