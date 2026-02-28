package com.example.minerva.servlet.adm.crudStudent;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.dto.UpdateStudentDTO;
import com.example.minerva.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/UpdateStudent", loadOnStartup = 1)
public class ServletUpdateStudent extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        boolean update = Boolean.parseBoolean(request.getParameter("action"));

        int id = Integer.parseInt(request.getParameter("id"));

        StudentDAO studentRepository = new StudentDAO();

        if(update){
            String residenceAddress = request.getParameter("residenceAddressInput");
            String petType = request.getParameter("petTypeInput");
            String allergies = request.getParameter("allergiesInput");
            Boolean basicKit = Boolean.parseBoolean(request.getParameter("basicKit"));
            Boolean flightFitness = Boolean.parseBoolean(request.getParameter("flightFitnessInput"));
            Integer schoolYear = Integer.parseInt(request.getParameter("schoolYearInput"));

            UpdateStudentDTO student = new UpdateStudentDTO(
                    residenceAddress,
                    petType,
                    allergies,
                    basicKit,
                    flightFitness,
                    schoolYear
            );

            request.setAttribute("msg", studentRepository.update(id, student) > 0 ?
                    "Estudante atualizado com sucesso!" :
                    "Erro ao atualizar estudante!");

            response.sendRedirect("/admin/ViewStudents");

            return;
        }

        response.sendRedirect("/admin/ViewStudents");
        return;

    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
