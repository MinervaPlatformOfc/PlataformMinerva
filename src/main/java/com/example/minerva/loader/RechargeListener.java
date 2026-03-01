package com.example.minerva.loader;

import com.example.minerva.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/recharge")
public class RechargeListener extends HttpServlet {

    HouseDAO houseRepository = new HouseDAO();
    GradeDAO gradeRepository = new GradeDAO();
    StudentDAO studentRepository = new StudentDAO();
    TeacherDAO teacherRepository = new TeacherDAO();
    UserDAO userRepository = new UserDAO();

    public void rechargeForAdmin(){
        getServletContext().setAttribute("adminList", userRepository.getAllAdmins());
    }

    public void rechargeForStudent(){
        getServletContext().setAttribute("studentList", studentRepository.getAllStudents());
    }

    public void rechargeForSubject(){
        getServletContext().setAttribute("subjectList", gradeRepository.getAllSubjects());
    }

    public void rechargeForTeacher(){
        getServletContext().setAttribute("houseList", houseRepository.getAllHouses());
        getServletContext().setAttribute("subjectList", gradeRepository.getAllSubjects());
        getServletContext().setAttribute("teacherList", teacherRepository.getAllTeachers());
    }

    public void rechargeForUser(){
        getServletContext().setAttribute("userNotAdminList", userRepository.getNotAdmins());
        getServletContext().setAttribute("userStatistics", userRepository.getUserStatistics());
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String endPoint = request.getParameter("endpoint");

        if(endPoint == "/admin/ViewAdmins"){
            rechargeForAdmin();
        } else if (endPoint == "/admin/ViewStudents") {
            rechargeForStudent();
        } else if (endPoint == "/admin/ViewSubjects"){
            rechargeForSubject();
        } else if (endPoint == "/admin/ViewTeachers") {
            rechargeForTeacher();
        } else if (endPoint == "/admin/users") {
            rechargeForUser();
        }

        request.getRequestDispatcher(endPoint).forward(request, response);
    }
}
