package com.example.minerva.loader;

import com.example.minerva.dao.*;
import jakarta.servlet.ServletContext;
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

    public void rechargeForAdmin(ServletContext context){
        context.setAttribute("adminList", userRepository.getAllAdmins());
    }

    public void rechargeForStudent(ServletContext context){
        context.setAttribute("studentList", studentRepository.getAllStudents());
    }

    public void rechargeForSubject(ServletContext context){
        context.setAttribute("subjectList", gradeRepository.getAllSubjects());
    }

    public void rechargeForTeacher(ServletContext context){
        context.setAttribute("houseList", houseRepository.getAllHouses());
        context.setAttribute("subjectList", gradeRepository.getAllSubjects());
        context.setAttribute("teacherList", teacherRepository.getAllTeachers());
    }

    public void rechargeForUser(ServletContext context){
        context.setAttribute("userNotAdminList", userRepository.getNotAdmins());
        context.setAttribute("userStatistics", userRepository.getUserStatistics());
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String endPoint = request.getParameter("endpoint");

        ServletContext context = request.getServletContext();

        if(endPoint.equals( "/admin/ViewAdmins")){
            rechargeForAdmin(context);
        } else if (endPoint.equals("/admin/ViewStudents")) {
            rechargeForStudent(context);
        } else if (endPoint.equals("/admin/ViewSubjects")){
            rechargeForSubject(context);
        } else if (endPoint.equals("/admin/ViewTeachers")) {
            rechargeForTeacher(context);
        } else if (endPoint.equals("/admin/users")) {
            rechargeForUser(context);
        }

        String adminName = request.getParameter("name");
        String adminUrl = request.getParameter("url");
        if (adminName != null && !adminName.isEmpty()) {
            request.setAttribute("name", adminName);
        }
        if (adminUrl != null && !adminUrl.isEmpty()) {
            request.setAttribute("url", adminUrl);
        }
        request.getRequestDispatcher(endPoint).forward(request, response);
    }
}
