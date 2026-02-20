package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.CommentDTO;
import com.example.minerva.dto.TeacherProfileDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private Conexao conexao;

    public CommentDAO() {
        this.conexao = new Conexao(); // usa a classe de conexão
    }

    public ArrayList<CommentDTO> getCommentsBySubjectAndUser(int userId, int subjectId) {

        ArrayList<CommentDTO> comments = new ArrayList<>();

        String sql = "SELECT content, score, created_at FROM comment WHERE subject_id = ? AND user_id = ? ORDER BY created_at DESC";
        Connection conn = null;

        try{
            conn = conexao.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, subjectId);
            stmt.setInt(2, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CommentDTO comment = new CommentDTO(
                        rs.getString("content"),
                        rs.getInt("score"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                comments.add(comment);
            }
            return comments;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // fecha conexão
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }
    }

    public boolean insertComment(String content, int score, int teacher_id, int subject_id, int student_id) {
        String sql = "insert into comment " +
                "(content, teacher_id, subject_id, student_id, score)" +
                " values (?, ?, ?, ?, ?)";
        Connection conn = null;
        try{
            conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, content);
            stmt.setInt(2, teacher_id);
            stmt.setInt(3, subject_id);
            stmt.setInt(4, student_id);
            stmt.setInt(5, score);

            return stmt.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            // fecha conexão
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }
    }

    public List<CommentDTO> findBySubjectStudentTeacher(int subjectId, int studentId, int teacherId) {

        String sql = "SELECT id, content, score, date_time " +
                "FROM comment " +
                "WHERE subject_id = ? AND student_id = ? AND teacher_id = ? " +
                "ORDER BY id";

        List<CommentDTO> comments = new ArrayList<>();
        Connection conn = null;

        try {
            conn = conexao.getConnection();
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return comments;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, subjectId);
            stmt.setInt(2, studentId);
            stmt.setInt(3, teacherId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CommentDTO comment = new CommentDTO(rs.getString("content"), rs.getInt("score"), rs.getTimestamp("date_time").toLocalDateTime());
                comments.add(comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conexao.closeConnection(conn);
        }

        return comments;
    }

}
