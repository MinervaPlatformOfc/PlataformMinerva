package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.StudentGradeDTO;
import com.example.minerva.dto.SubjectDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    private Conexao conexao;

    public GradeDAO() {
        this.conexao = new Conexao(); // usa a classe de conexão
    }

    public List<StudentGradeDTO> getStudentGrades(int studentId){
        String sql = "SELECT sub.name AS subject_name, ss.n1, ss.n2 " +
                "FROM subject_student ss " +
                "JOIN subject sub ON ss.subject_id = sub.id " +
                "WHERE ss.student_id = ?";

        List<StudentGradeDTO> grades = new ArrayList<>();
        Connection conn = null;

        try {
            conn = conexao.getConnection(); // pega conexão da classe Conexao
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
        } finally {
            // fecha conexão
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }

    }

    public List<SubjectDTO> getSubjects(int studentId){
        String sql = "SELECT  sub.id AS subject_id, sub.name AS subject_name " +
                "FROM subject_student ss " +
                "JOIN subject sub ON ss.subject_id = sub.id " +
                "WHERE ss.student_id = ?";

        List<SubjectDTO> subjects = new ArrayList<>();
        Connection conn = null;

        try {
            conn = conexao.getConnection(); // pega conexão da classe Conexao
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
        } finally {
            // fecha conexão
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }

    }

    public boolean updateGrades(int subjectId, int studentId, String n1Str, String n2Str) {
        String sql = "UPDATE subject_student SET n1 = ?, n2 = ? WHERE subject_id = ? AND student_id = ?";
        Connection conn = null;

        try {
            conn = conexao.getConnection();
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
        } finally {
            if (conn != null) conexao.closeConnection(conn);
        }
    }

    public Integer getSubjectId(String subject) {
        String sql = "select id from subject where name = ?";
        Connection conn = null;

        try {
            conn = conexao.getConnection();
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
        } finally {
            if (conn != null) conexao.closeConnection(conn);
        }
    }
}
