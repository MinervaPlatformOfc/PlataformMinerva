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
}
