package com.example.minerva.dao;

import com.example.minerva.model.Teacher;
import com.example.minerva.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO {
    protected Teacher mapResultSet(ResultSet rs) throws SQLException {
        return new Teacher(
            rs.getInt("id"),
            rs.getInt("Id_house"),
            rs.getInt("id_user")
        );
    }
    public String getNomeTabela(){
        return "User";
    }

}
