package com.example.minerva.servlet.adm.crudStudent;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.dto.UpdateStudentDTO;
import com.example.minerva.loader.RechargeListener;
import com.example.minerva.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/updateStudent", loadOnStartup = 1)
public class ServletUpdateStudent extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            StudentDAO studentRepository = new StudentDAO();

            // Pegando os parâmetros do formulário (verifique se os nomes estão corretos)
            String residenceAddress = request.getParameter("residenceAddress");
            String petType = request.getParameter("pet");
            String allergies = request.getParameter("allergies");

            // Checkboxes: retornam null se não marcados
            Boolean basicKit = request.getParameter("basicKit") != null;
            Boolean guardianPermission = request.getParameter("guardianPermission") != null;

            Boolean flightFitness = request.getParameter("flightFitness") != null;

            // Tratamento para schoolYear (pode vir vazio)
            Integer schoolYear = null;
            String schoolYearParam = request.getParameter("schoolYear");
            if (schoolYearParam != null && !schoolYearParam.isEmpty()) {
                schoolYear = Integer.parseInt(schoolYearParam);
            }

            String legalGuardianName = request.getParameter("legalGuardianName");

            UpdateStudentDTO student = new UpdateStudentDTO(
                    residenceAddress,
                    petType,
                    allergies,
                    basicKit,
                    flightFitness,
                    schoolYear,
                    legalGuardianName,
                    guardianPermission
            );

            int result = studentRepository.update(id, student);

            if (result > 0) {
                request.setAttribute("msg", "Estudante atualizado com sucesso!");
            } else {
                request.setAttribute("msg", "Erro ao atualizar estudante!");
            }

            RechargeListener rechargeListener = new RechargeListener();
            rechargeListener.rechargeForStudent(getServletContext());

            request.getRequestDispatcher("/admin/ViewStudents").forward(request,response);

        } catch (NumberFormatException e) {
            request.setAttribute("msg", "Erro: ID ou ano escolar inválido!");
            request.getRequestDispatcher("/admin/ViewStudents").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Erro ao atualizar estudante: " + e.getMessage());
            request.getRequestDispatcher("/admin/ViewStudents").forward(request,response);
        }
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}