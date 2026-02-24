package com.example.minerva.servlet.login;

import com.example.minerva.dao.*;
import com.example.minerva.dto.StudentHomeDTO;
import com.example.minerva.model.Teacher;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;

import com.example.minerva.view.StudentForTeacherView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class ServletLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("ENTROU NO SERVLET LOGIN");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UserDAO dao = new UserDAO();
        User user = dao.findByEmail(email);

        HashSenha hash = new HashSenha(senha);

        if(user != null && hash.getHashSenha().equals(user.getPassword())) {

            HttpSession session = request.getSession();

            session.setAttribute("user", user);

            String role = user.getRole();

            switch(role) {

                case "student":

                    if (user.isFirstAcess()) {
                        request.setAttribute("email", email);
                        request.getRequestDispatcher("/aluno/quiz/quiz.jsp")
                                .forward(request, response);

                    } else {
                        HouseDAO houseDao = new HouseDAO();
                        StudentDAO studentDAO = new StudentDAO();

                        StudentHomeDTO homeDto = studentDAO.getStudentByEmail(email);

                        request.setAttribute("ranking", houseDao.viewRanking());
                        request.setAttribute("homeDto", homeDto);

                        request.getRequestDispatcher("/aluno/home.jsp")
                                .forward(request, response);
                    }

                    break;

                case "teacher":
                    TeacherDAO teacherDAO = new TeacherDAO();
                    Teacher teacher = teacherDAO.findTeacherByUserId(user.getId());

                    Subject_teacherDAO subject_teacherDAO = new Subject_teacherDAO();
                    int subjectId = subject_teacherDAO.findSubjectByTeacher(teacher.getId());

                    StudentDAO studentDAO = new StudentDAO();
                    List<StudentForTeacherView> list = studentDAO.listStudentsForSubject(subjectId);
                    //pega as diferente materias do novo modelo
                    List<String> subjects = subject_teacherDAO.listSubjectsByTeacher(teacher.getId());

                    session.setAttribute("subjects", subjects);

                    session.setAttribute("teacher", teacher);
                    session.setAttribute("teacherId", teacher.getId());
                    session.setAttribute("subjectId", subjectId);
                    session.setAttribute("user", user);
                    session.setAttribute("studentList", list);
                    session.setAttribute("subjects", subjects);


                    response.sendRedirect(request.getContextPath() + "/professor/home.jsp");
                    break;

                case "admin":

                    session.setAttribute("adminId", user.getId());

                    response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
                    break;

                default:
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    break;
            }

        } else {
            System.out.println("LOGIN INVALIDO");
            System.out.println(hash.getHashSenha());
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
