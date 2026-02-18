package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

}
