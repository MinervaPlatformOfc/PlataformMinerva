package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.StudentByNotesDTO;
import com.example.minerva.model.SubjectStudent;
import com.example.minerva.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubjectStudentDAO {

    private Conexao conexao;
    public SubjectStudentDAO() {
        this.conexao = new Conexao();
    }

    public SubjectStudent save(int subjectId, double n1, double n2, int student_id){

        Connection conn = null;

        try {

            conn = conexao.getConnection();


            String updateSql = "UPDATE subject_student SET n1 = ?, n2 = ? WHERE subject_id = ? AND student_id = ?";

            PreparedStatement updateStmt = conn.prepareStatement(updateSql);

            updateStmt.setDouble(1, n1);
            updateStmt.setDouble(2, n2);
            updateStmt.setInt(3, subjectId);
            updateStmt.setInt(4, student_id);

            int rowsAffected = updateStmt.executeUpdate();

            if(rowsAffected == 0){

                String insertSql = "INSERT INTO subject_student (subject_id, n1, n2, student_id) VALUES (?, ?, ?, ?)";

                PreparedStatement insertStmt = conn.prepareStatement(insertSql);

                insertStmt.setInt(1, subjectId);
                insertStmt.setDouble(2, n1);
                insertStmt.setDouble(3, n2);
                insertStmt.setInt(4, student_id);

                insertStmt.executeUpdate();
            }

            return new SubjectStudent(subjectId, n1, n2, student_id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conexao.closeConnection(conn);
        }

        return null;
    }

    public List<StudentByNotesDTO> listNotesByYear(int school_year, int student_id){
        String sql = "SELECT * FROM studentByNotesView where  school_year = ? and id_subject = ? " +
                "ORDER BY student_name ASC";
        Connection conn = null;
        List<StudentByNotesDTO> list = new ArrayList<>();
        try {
            conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, school_year);
            stmt.setInt(2, student_id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StudentByNotesDTO student = new StudentByNotesDTO(
                            rs.getDouble("n1"),
                            rs.getDouble("n2"),
                            rs.getString("student_name"),
                            rs.getString("subject_name"),
                            rs.getInt("school_year"),
                            rs.getInt("id_subject"),
                            rs.getInt("id_student")
                    );
                    list.add(student);
                }
                return list;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}

