package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO {
    private Conexao conexao;
    public TeacherDAO() {
        this.conexao = new Conexao();
    }

    public Teacher findTeacherByUserId(int userId) {

        String sql = "SELECT * FROM teacher t WHERE t.user_id = ?";
        Connection conn = null;

        try {
            conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Teacher(
                            rs.getInt("id"),
                            rs.getInt("house_id"),
                            rs.getInt("user_id"),
                            rs.getString("wand"),
                            rs.getBoolean("head_house"),
                            rs.getString("past_experiences"),
                            rs.getString("wizard_title"),
                            rs.getString("teacher_registration_code")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
