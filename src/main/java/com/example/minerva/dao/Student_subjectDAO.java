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
        return "subject_student";
    }

    @Override
    protected String getNomesColunas() {
        return "n1, n2, student_id,  subject_id";
    }

    @Override
    protected Subject_student mapResultSet(ResultSet rs) throws SQLException {
        return new Subject_student(
        rs.getDouble("n1"),
        rs.getDouble("n2"),
        rs.getInt("student_id"),
        rs.getInt("subject_id")
        );
    }

}
