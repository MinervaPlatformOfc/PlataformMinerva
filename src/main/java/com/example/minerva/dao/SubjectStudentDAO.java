package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.model.SubjectStudent;
import com.example.minerva.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SubjectStudentDAO {

    private Conexao conexao;
    public SubjectStudentDAO() {
        this.conexao = new Conexao();
    }

    public SubjectStudent insert (int subjectId, double n1, double n2, int student_id){

        String sql = "INSERT INTO subject_student (subject_id, n1, n2, student_id) VALUES (?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = conexao.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, subjectId);
            stmt.setDouble(2, n1);
            stmt.setDouble(3, n2);
            stmt.setInt(4, student_id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                return new SubjectStudent(subjectId, n1, n2, student_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conexao.closeConnection(conn);
        }

        return null;
    }

//    public List<SubjectStudent> listNotes(int student_id){
//        String sql = "SELECT ss.n1, ss.n2, s.name FROM subject_student ss where student_id = ? " +
//                "join subject s on ss.subject_id = s.id";
//        Connection conn = null;
//        try {
//            conn = conexao.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, student_id);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    SubjectStudent subject = new SubjectStudent(
//                            rs.getInt("subjectId"),
//                            rs.getDouble("n1"),
//                            rs.getDouble("n2"),
//                            rs.getInt("student_id")
//                    );
//                } else {
//                    System.out.println("nao foi adicionado");
//                }
//            }
//        }
//    }

}

