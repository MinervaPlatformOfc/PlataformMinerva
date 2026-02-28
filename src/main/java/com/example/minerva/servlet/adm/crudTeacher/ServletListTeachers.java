package com.example.minerva.servlet.adm.crudTeacher;

import com.example.minerva.dao.GradeDAO;
import com.example.minerva.dao.HouseDAO;
import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dto.SubjectDTO;
import com.example.minerva.dto.TeacherDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/ViewTeachers", loadOnStartup = 1)

public class ServletListTeachers extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TeacherDAO teacherRepository = new TeacherDAO();
        GradeDAO subjectRepository = new GradeDAO();

        List<TeacherDTO> teachers = teacherRepository.getAllTeachers();
        List<SubjectDTO> subjects = subjectRepository.getAllSubjects();
        HouseDAO houseDAO = new HouseDAO();

        request.setAttribute("house", houseDAO.getAllHouses());
        request.setAttribute("teacherList", teachers);
        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("/admin/CRUD/Teacher.jsp").forward(request, response);

    }
}
