package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.StudentGradeDTO;
import com.example.minerva.dto.SubjectDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    private final Connection conn = Conexao.getConnection();

    public List<StudentGradeDTO> getStudentGrades(int studentId){
        String sql = "SELECT sub.name AS subject_name, ss.n1, ss.n2 " +
                "FROM subject_student ss " +
                "JOIN subject sub ON ss.subject_id = sub.id " +
                "WHERE ss.student_id = ?";

        List<StudentGradeDTO> grades = new ArrayList<>();

        try {
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                Double n1 = rs.getObject("n1") != null ? rs.getDouble("n1") : null;
                Double n2 = rs.getObject("n2") != null ? rs.getDouble("n2") : null;

                StudentGradeDTO grade = new StudentGradeDTO(subjectName, n1, n2);
                grades.add(grade);
            }

            return grades;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<SubjectDTO>  getSubjects(int studentId){
        String sql = "SELECT  sub.id AS subject_id, sub.name AS subject_name " +
                "FROM subject_student ss " +
                "JOIN subject sub ON ss.subject_id = sub.id " +
                "WHERE ss.student_id = ?";

        List<SubjectDTO> subjects = new ArrayList<>();

        try {
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int subjectId = rs.getInt("subject_id");
                String subjectName = rs.getString("subject_name");
                SubjectDTO subject = new SubjectDTO(subjectId, subjectName);
                subjects.add(subject);
            }

            return subjects;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean updateGrades(int subjectId, int studentId, String n1Str, String n2Str) {
        String sql = "UPDATE subject_student SET n1 = ?, n2 = ? WHERE subject_id = ? AND student_id = ?";
        try {
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return false;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);

            // converte em null pra ver se tem valor ou nn e depois para Double(se tiver numero)
            Double n1 = (n1Str == null || n1Str.trim().isEmpty()) ? null : Double.valueOf(n1Str);
            Double n2 = (n2Str == null || n2Str.trim().isEmpty()) ? null : Double.valueOf(n2Str);

            stmt.setObject(1, n1);
            stmt.setObject(2, n2);
            stmt.setInt(3, subjectId);
            stmt.setInt(4, studentId);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer getSubjectId(String subject) {
        String sql = "select id from subject where name = ?";

        try {
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, subject);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SubjectDTO> getAllSubjects(){
        String sql = "select id, name from subject";

        try {
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<SubjectDTO> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new SubjectDTO(rs.getInt("id"), rs.getString("name")));
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean save(String name, boolean addStudents){
        String sqlSubject = "INSERT INTO subject(name) VALUES(?)";

        try {
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return false;
            }

            // Inicia transação pra garantir que todas s ações vao ser feitas
            conn.setAutoCommit(false);

            // Insere a matéria
            PreparedStatement stmtSubject = conn.prepareStatement(sqlSubject, Statement.RETURN_GENERATED_KEYS);
            stmtSubject.setString(1, name);
            int affectedRows = stmtSubject.executeUpdate();

            if (affectedRows == 0) {
                conn.rollback();
                return false;
            }

            // Pega o ID gerado da matéria
            ResultSet generatedKeys = stmtSubject.getGeneratedKeys();
            int subjectId = 0;

            if (generatedKeys.next()) {
                subjectId = generatedKeys.getInt(1);
            } else {
                conn.rollback();
                return false;
            }

            // Se for para adicionar a nova matéria para todos os alunos
            if (addStudents) {
                String sqlGetStudents = "SELECT id FROM student";
                String sqlInsertSubjectStudent = "INSERT INTO subject_student(subject_id, student_id) VALUES(?, ?)";

                PreparedStatement stmtStudents = conn.prepareStatement(sqlGetStudents);
                ResultSet rsStudents = stmtStudents.executeQuery();

                PreparedStatement stmtInsert = conn.prepareStatement(sqlInsertSubjectStudent);

                while (rsStudents.next()) {
                    int studentId = rsStudents.getInt("id");

                    stmtInsert.setInt(1, subjectId);
                    stmtInsert.setInt(2, studentId);
                    stmtInsert.addBatch();
                }

                stmtInsert.executeBatch();
            }

            // Confirma tudo e retorna q deu certo
            conn.commit();
            return true;

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        }
    }

    public boolean update(int id, String name){
        String sql = "update subject set name=? where id = ?";

        try {
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return false;
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, id);
            int rs = stmt.executeUpdate();
            return rs>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        PreparedStatement stmtComment = null;
        PreparedStatement stmtSubjectStudent = null;
        PreparedStatement stmtSubjectTeacher = null;
        PreparedStatement stmtSubject = null;

        try {
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return false;
            }

            // DESATIVA AUTO-COMMIT (inicia transação pra garantir)
            conn.setAutoCommit(false);

            // 1️- Deleta comentários
            stmtComment = conn.prepareStatement(
                    "DELETE FROM comment WHERE subject_id = ?");
            stmtComment.setInt(1, id);
            stmtComment.executeUpdate();

            // 2- Deleta boletim
            stmtSubjectStudent = conn.prepareStatement(
                    "DELETE FROM subject_student WHERE subject_id = ?");
            stmtSubjectStudent.setInt(1, id);
            stmtSubjectStudent.executeUpdate();

            // 3️- Deleta relação professor-matéria
            stmtSubjectTeacher = conn.prepareStatement(
                    "DELETE FROM subject_teacher WHERE subject_id = ?");
            stmtSubjectTeacher.setInt(1, id);
            stmtSubjectTeacher.executeUpdate();

            // 4- Deleta matéria
            stmtSubject = conn.prepareStatement(
                    "DELETE FROM subject WHERE id = ?");
            stmtSubject.setInt(1, id);
            int rows = stmtSubject.executeUpdate();

            // Se der tudo certo, confirma o commit
            conn.commit();

            return rows > 0;

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); // se der errado, desfaz
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
            return false;

        } finally {
            try {
                if (stmtComment != null) stmtComment.close();
                if (stmtSubjectStudent != null) stmtSubjectStudent.close();
                if (stmtSubjectTeacher != null) stmtSubjectTeacher.close();
                if (stmtSubject != null) stmtSubject.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
