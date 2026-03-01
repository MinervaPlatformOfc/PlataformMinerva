package com.example.minerva.loader;

import com.example.minerva.dao.*;
import jakarta.servlet.http.HttpServlet;

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


}
