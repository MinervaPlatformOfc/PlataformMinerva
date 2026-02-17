package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.CommentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
}
