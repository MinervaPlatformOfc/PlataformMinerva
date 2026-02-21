package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Subject_teacherDAO {
    private Conexao conexao;

    public int findSubjectByTeacher(int teacherId) {

        String sql = "SELECT subject_id FROM subject_teacher WHERE teacher_id = ?";

        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return rs.getInt("subject_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean delete(int id){
        String sql = "delete from subject_teacher where id = ?";

        Connection conn = conexao.getConnection();

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
        }finally{
            conexao.closeConnection(conn);
        }
    }

}
