package com.example.minerva.dao;

import com.example.minerva.model.User;
import com.example.minerva.conexao.Conexao;

import java.sql.*;
import java.util.Date;

public class UserDAO {

    private Conexao conexao;

    public UserDAO() {
        this.conexao = new Conexao(); // usa a classe de conexão
    }

    // Busca usuário pelo email
    public User findByEmail(String email) {

        String sql = "SELECT name, password, email, role, first_access, created_at FROM users WHERE email = ?";
        Connection conn = null;

        try {
            conn = conexao.getConnection(); // pega conexão da classe Conexao
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                String role = rs.getString("role");
                boolean firstAccess = rs.getBoolean("first_access");
                Date createdAt = rs.getTimestamp("created_at");

                return new User(name, password, email, role, firstAccess, createdAt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // fecha conexão
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }

        // Retorna null se não encontrar
        return null;
    }

}
