package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import org.postgresql.core.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Subject_teacherDAO {
    private Conexao conexao = new Conexao();
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

    public List<String> listSubjectsByTeacher(int teacherId){

        List<String> subjects = new ArrayList<>();
        Connection conn = null;
        String sql = """
        SELECT s.name
        FROM subject s
        JOIN subject_teacher st
        ON st.subject_id = s.id
        WHERE st.teacher_id = ?
    """;

        try{
            conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, teacherId);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                subjects.add(rs.getString("name"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return subjects;
    }

}
