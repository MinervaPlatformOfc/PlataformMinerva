package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private Conexao conexao;

    public TeacherDAO() {
        this.conexao = new Conexao(); // usa a classe de conexão
    }

    public TeacherHomeDTO getTeacherWithStudentsByEmail(String email) {
        String sql = "SELECT teacher_id, teacher_name, teacher_house_name, " +
                "student_id, student_name, student_house_name, n1, n2, subject_name " +
                "FROM teacher_students " +
                "WHERE teacher_name = (SELECT name FROM users WHERE email = ?) " +
                "ORDER BY student_name";

        Connection conn = null;
        try {
            conn = conexao.getConnection();
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            List<StudentGradeDTO> students = new ArrayList<>();
            TeacherHomeDTO teacherDTO = null;

            while (rs.next()) {
                // só cria o DTO do professor na primeira linhaaaaaaaaaaaaa
                if (teacherDTO == null) {
                    int teacherId = rs.getInt("teacher_id");
                    String teacherName = rs.getString("teacher_name");
                    String teacherHouse = rs.getString("teacher_house_name");
                    teacherDTO = new TeacherHomeDTO(teacherId, teacherName, teacherHouse, students);
                }

                // adiciona cada aluno no array
                Integer studentId = rs.getObject("student_id") != null ? rs.getInt("student_id") : null;                String studentName = rs.getString("student_name");
                String studentHouse = rs.getString("student_house_name");
                Double n1 = rs.getObject("n1") != null ? rs.getDouble("n1") : null;
                Double n2 =rs.getObject("n2") != null ? rs.getDouble("n2") : null;
                String subjectName = rs.getString("subject_name");

                if (studentId != null ) students.add(new StudentGradeDTO(studentId, studentName, studentHouse, n1, n2, subjectName));
            }

            return teacherDTO;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }
    }

    public TeacherProfileDTO getTeacherProfile(int teacherId) {
        String sqlTeacher = "SELECT t.*, u.name AS user_name, u.profile_image_url AS image_url, " +
                "h.name AS house_name " +
                "FROM teacher t " +
                "JOIN users u ON t.user_id = u.id " +
                "LEFT JOIN house h ON t.house_id = h.id " +
                "WHERE t.id = ?";

        String sqlSubjects = "SELECT sub.name " +
                "FROM subject sub " +
                "JOIN subject_teacher st ON sub.id = st.subject_id " +
                "WHERE st.teacher_id = ?";

        Connection conn = null;
        try {
            conn = conexao.getConnection();
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            // Dados do professor
            PreparedStatement stmt = conn.prepareStatement(sqlTeacher);
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            // Varinha
            String wand = rs.getString("wand");
            String wandWood = null, wandCore = null, wandFlexibility = null;
            if (wand != null && wand.contains(" - ")) {
                String[] parts = wand.split(" - ");
                wandWood = parts[0];
                wandCore = parts[1];
                wandFlexibility = parts[2];
            }

            // Matérias
            List<String> subjects = new ArrayList<>();
            PreparedStatement stmtSubjects = conn.prepareStatement(sqlSubjects);
            stmtSubjects.setInt(1, teacherId);
            ResultSet rsSubjects = stmtSubjects.executeQuery();
            while (rsSubjects.next()) {
                subjects.add(rsSubjects.getString("name"));
            }

            return new TeacherProfileDTO(
                    teacherId,
                    rs.getString("image_url"),
                    rs.getString("user_name"),
                    rs.getString("wizard_title"),
                    rs.getBoolean("head_house"),
                    rs.getString("house_name"),
                    wandWood,
                    wandCore,
                    wandFlexibility,
                    rs.getString("past_experiences"),
                    rs.getString("teacher_registration_code"),
                    subjects
            );

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conexao.closeConnection(conn);
        }
        return null;
    }

    public TeacherHomeDTO getTeacherWithStudentsById(int teacherId) {
        String sql = "SELECT teacher_id, teacher_name, teacher_house_name, " +
                "student_id, student_name, student_house_name, n1, n2, subject_name " +
                "FROM teacher_students " +
                "WHERE teacher_id = ? " +
                "ORDER BY student_name";

        Connection conn = null;
        try {
            conn = conexao.getConnection();
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            List<StudentGradeDTO> students = new ArrayList<>();
            TeacherHomeDTO teacherDTO = null;

            while (rs.next()) {
                if (teacherDTO == null) {
                    String teacherName = rs.getString("teacher_name");
                    String teacherHouse = rs.getString("teacher_house_name");

                    teacherDTO = new TeacherHomeDTO(teacherId, teacherName, teacherHouse, students);
                }

                Integer studentId = rs.getObject("student_id") != null ? rs.getInt("student_id") : null;
                String studentName = rs.getString("student_name");
                String studentHouse = rs.getString("student_house_name");
                Double n1 = rs.getObject("n1") != null ? rs.getDouble("n1") : null;
                Double n2 = rs.getObject("n2") != null ? rs.getDouble("n2") : null;
                String subjectName = rs.getString("subject_name");

                if (studentId != null ) students.add(new StudentGradeDTO(studentId, studentName, studentHouse, n1, n2, subjectName));
            }

            return teacherDTO;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }
    }

    public List<String> getYearsAndSubjectsByTeacherId(int teacherId) {
        String sql = "SELECT DISTINCT school_year, subject_name " +
                "FROM teacher_students " +
                "WHERE teacher_id = ? " +
                "ORDER BY school_year, subject_name";

        Connection conn = null;
        List<String> result = new ArrayList<>();

        try {
            conn = conexao.getConnection();
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, teacherId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer year = rs.getObject("school_year") != null ? rs.getInt("school_year") : null;
                String subject = rs.getString("subject_name");

                if (year != null && subject != null) {
                    result.add(year + "º Ano (" + subject + ")");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }

        return result;
    }

    public List<StudentGradeDTO> getStudentsByTeacherYearAndSubject(int teacherId, int year, String subject) {
        String sql = "SELECT DISTINCT student_id, student_name, student_house_name, n1, n2 " +
                "FROM teacher_students " +
                "WHERE teacher_id = ? AND school_year = ? AND subject_name = ? " +
                "ORDER BY student_name";

        Connection conn = null;
        List<StudentGradeDTO> students = new ArrayList<>();

        try {
            conn = conexao.getConnection();
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, teacherId);
            stmt.setInt(2, year);
            stmt.setString(3, subject);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String studentHouse = rs.getString("student_house_name");
                Double n1 = rs.getObject("n1") != null ? rs.getDouble("n1") : null;
                Double n2 = rs.getObject("n2") != null ? rs.getDouble("n2") : null;

                students.add(new StudentGradeDTO(studentId, studentName, studentHouse, n1, n2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }

        return students;
    }

    public boolean findByRegistration(String registration){
        String sql = "select 1 from teacher where teacher_registration_code = ?";
        Connection conn = null;

        try {
            conn = conexao.getConnection(); // pega conexão da classe Conexao
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return false;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, registration);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // fecha conexão
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }
    }
}
