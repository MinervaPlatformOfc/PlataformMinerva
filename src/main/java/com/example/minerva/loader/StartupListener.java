package com.example.minerva.loader;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.*;
import com.example.minerva.dto.AdminDTO;
import com.example.minerva.dto.StudentGradeDTO;
import com.example.minerva.dto.SubjectDTO;
import com.example.minerva.dto.TeacherDTO;
import com.example.minerva.model.House;
import com.example.minerva.model.Student;
import com.example.minerva.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.List;

@WebListener
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce){
        System.out.println("Sistema inicializando...");

        try{
            ServletContext context = sce.getServletContext();

            System.out.println("Sistema carregando objetos");
            HouseDAO houseRepository = new HouseDAO();
            GradeDAO gradeRepository = new GradeDAO();
            StudentDAO studentRepository = new StudentDAO();
            TeacherDAO teacherRepository = new TeacherDAO();
            UserDAO userRepository = new UserDAO();

            System.out.println("Sistema carregando dados");
            List<AdminDTO> admins = userRepository.getAllAdmins();
            List<Student> students = studentRepository.getAllStudents();
            List<SubjectDTO> subjects = gradeRepository.getAllSubjects();
            List<TeacherDTO> teachers = teacherRepository.getAllTeachers();
            List<User> usersNotAdmin = userRepository.getNotAdmins();
            HashMap<String, Integer> houses = houseRepository.getAllHouses();
            double[] statistics = userRepository.getUserStatistics();

            System.out.println("Sistema setando dados");
            context.setAttribute("adminList", admins);
            context.setAttribute("studentList", students);
            context.setAttribute("subjectList", subjects);
            context.setAttribute("teacherList", teachers);
            context.setAttribute("userNotAdminList", usersNotAdmin);
            context.setAttribute("houseList", houses);
            context.setAttribute("userStatistics", statistics);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Conexao.closeConnection();
        }
    }
}
