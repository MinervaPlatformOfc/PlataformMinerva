package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.model.Model;
import org.postgresql.core.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public abstract class DAO<T extends Model> {
    private Conexao conexao;

    public void save(T entidade) {
        String sql = "INSERT INTO " + getNomeTabela() +
                " (" + getNomesColunas() + ") VALUES (" + getPlaceholders() + ")";
        try(Connection conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            System.out.println(sql);
            prepareStatementForSave(stmt, entidade);
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                System.out.println("Erro ao salvar");
            }
        } catch (SQLException e)  {
            e.printStackTrace();
        }
    }

    public T findByAttribute(String coluna, Object valor){
        String sql = "SELECT * FROM " + getNomeTabela() +
                " WHERE " + coluna + " = ?";
        try(Connection conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setObject(1, valor);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return mapResultSet(rs);
                } else {
                    return null;
                }
            } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }


    protected String getPlaceholders() {
        int quantidade = getNomesColunas().split( "\\s*,\\s*").length;
        return String.join(", ", Collections.nCopies(quantidade, "?"));
    }


    //ultilitarios
    protected abstract void prepareStatementForSave(PreparedStatement stmt, T entidade) throws SQLException; // prepareStatement pra método save
    public abstract String getNomeTabela();
    protected abstract String getNomesColunas();
    protected abstract T mapResultSet(ResultSet rs) throws SQLException;

}
