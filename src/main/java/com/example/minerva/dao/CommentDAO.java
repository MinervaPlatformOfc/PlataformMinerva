package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.CommentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private final Connection conn = Conexao.getConnection();

    public ArrayList<CommentDTO> getCommentsBySubjectAndUser(int studentId, int subjectId) {

        ArrayList<CommentDTO> comments = new ArrayList<>();

        String sql = "SELECT content, score, date_time FROM comment WHERE subject_id = ? AND student_id = ? ORDER BY date_time DESC";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, subjectId);
            stmt.setInt(2, studentId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CommentDTO comment = new CommentDTO(
                        rs.getString("content"),
                        rs.getInt("score"),
                        rs.getTimestamp("date_time").toLocalDateTime()
                );
                comments.add(comment);
            }
            return comments;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean insertComment(String content, int score, int teacherId, int subjectId, int studentId) {
        String sql = "insert into comment " +
                "(content, teacher_id, subject_id, student_id, score)" +
                " values (?, ?, ?, ?, ?)";

        String updateHouseSQL = "UPDATE house SET points = points + ? WHERE id = (SELECT house_id FROM student s WHERE s.id = ?)";

        try{
            conn.setAutoCommit(false);

            PreparedStatement stmtComment = conn.prepareStatement(sql);
            stmtComment.setString(1, content);
            stmtComment.setInt(2, teacherId);
            stmtComment.setInt(3, subjectId);
            stmtComment.setInt(4, studentId);
            stmtComment.setInt(5, score);
            int commentResult = stmtComment.executeUpdate();


            PreparedStatement stmtHouse = conn.prepareStatement(updateHouseSQL);
            // Atualizar pontos da casa ae
            stmtHouse.setInt(1, score);
            stmtHouse.setInt(2, studentId);
            int houseResult = stmtHouse.executeUpdate();


            if (commentResult > 0 && houseResult > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<CommentDTO> listAllByTeacher(int id){
        String sql = "select content, score, date_time from commentView " +
                "where id_teacher = ?;";

        List<CommentDTO> comments = new ArrayList<>();

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                CommentDTO temp = new CommentDTO(
                        rs.getString("content"),
                        rs.getInt("score"),
                        rs.getTimestamp("date_time").toLocalDateTime()
                );

                comments.add(temp);
            }
            return comments;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean delete(int id){
        String sql = "delete from comment where id = ?";

        if(conn == null){
            System.out.println("Erro de conexão (PostgreSQL)");
            return false;
        }
        int lines = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);

            lines = pstmt.executeUpdate();

            return lines > 0;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }

    public List<CommentDTO> findBySubjectStudentTeacher(int subjectId, int studentId, int teacherId) {

        String sql = "SELECT id, content, score, date_time " +
                "FROM comment " +
                "WHERE subject_id = ? AND student_id = ? AND teacher_id = ? " +
                "ORDER BY id";

        List<CommentDTO> comments = new ArrayList<>();

        try {
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
        }

        return comments;
    }
}
