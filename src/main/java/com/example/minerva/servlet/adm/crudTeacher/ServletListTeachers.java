package com.example.minerva.servlet.adm.crudTeacher;


import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dto.TeacherDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@WebServlet("/adm/ListTeachers")
public class ServletListTeachers extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        TeacherDAO teacherRepository = new TeacherDAO();

        List<TeacherDTO> teachers = teacherRepository.getAllTeachers();

        request.setAttribute("teachers", teachers);
    }
}
