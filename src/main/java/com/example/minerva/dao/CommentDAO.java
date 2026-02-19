package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.CommentDTO;
import com.example.minerva.view.CommentView;

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

    public List<CommentView> listAllByStudent(int id) {
        String sql = "select * from commentView where id_student = ?";
        Connection conn = null;
        List<CommentView> commentsList = new ArrayList<>();
        try{
            conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            try  (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()){
                    CommentView comment = new CommentView(
                            rs.getString("content"),
                            rs.getString("student"),
                            rs.getInt("id_student"),
                            rs.getString("teacher"),
                            rs.getInt("id_teacher"),
                            rs.getString("subject"),
                            rs.getInt("id_subject"),
                            rs.getDouble("score"),
                            rs.getTimestamp("date_time").toLocalDateTime()
                    );
                    commentsList.add(comment);
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return commentsList;
    }

    public void insertComment(String content, int score, int teacher_id, int subject_id, int student_id) {
        String sql = "insert into comment " +
                "(content, teacher_id, subject_id, student_id, score, date_time)" +
                " values (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        try{
            conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, content);
            stmt.setInt(2, teacher_id);
            stmt.setInt(3, subject_id);
            stmt.setInt(4, student_id);
            stmt.setInt(5, score);
            stmt.setTimestamp(6, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));

            int rows = stmt.executeUpdate();
            if(rows == 0) {
                System.out.println("nao foi colocado nadaaa");
            } else {
                System.out.println("inseridooo");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
