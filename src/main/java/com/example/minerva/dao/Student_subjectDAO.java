package com.example.minerva.dao;

import com.example.minerva.model.Subject_student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student_subjectDAO extends DAO<Subject_student> {

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Subject_student entidade)
            throws java.sql.SQLException {

        stmt.setDouble(1, entidade.getN1());
        stmt.setDouble(2, entidade.getN2());
        stmt.setInt(3, entidade.getStudent_id());
        stmt.setInt(4, entidade.getSubject_id());
    }

    @Override
    public String getNomeTabela() {
        return "Student_subject";
    }

    @Override
    protected String getNomesColunas() {
        return "";
    }

    @Override
    protected Subject_student mapResultSet(ResultSet rs) throws SQLException {
        return null;
    }

}
