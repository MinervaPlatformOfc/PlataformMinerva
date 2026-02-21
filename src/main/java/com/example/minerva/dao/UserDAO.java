package com.example.minerva.dao;

import com.example.minerva.model.User;
import com.example.minerva.conexao.Conexao;
import com.example.minerva.utils.criptografia.HashSenha;

import java.sql.*;
import java.util.Date;

public class UserDAO {

    private final Conexao conexao = new Conexao();

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


    public boolean updateName(int id, String newName){
        String sql = "update users set name = ? where id = ?";

        Connection conn = conexao.getConnection();

        if(conn == null){
            System.out.println("Erro de conexão (PostgreSQL)");
            return false;
        }

        int lines = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);

            lines = pstmt.executeUpdate();

            return lines > 0;
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }finally {
            conexao.closeConnection(conn);
        }

    }

    public boolean updateEmail(int id, String newEmail){
        String sql = "update users set email = ? where id = ?";

        Connection conn = conexao.getConnection();

        if(conn == null){
            System.out.println("Erro de conexão (PostgreSQL)");
            return false;
        }
        int lines = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, newEmail);
            pstmt.setInt(2, id);

            lines = pstmt.executeUpdate();

            return lines > 0;
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }finally {
            conexao.closeConnection(conn);
        }

    }

    public boolean updatePassword(int id, String newPassword){
        String sql = "update users set password = ? where id = ?";

        Connection conn = conexao.getConnection();

        if(conn == null){
            System.out.println("Erro de conexão (PostgreSQL)");
            return false;
        }
        int lines = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, String.valueOf(new HashSenha(newPassword)));
            pstmt.setInt(2, id);

            lines = pstmt.executeUpdate();

            return lines > 0;
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }finally {
            conexao.closeConnection(conn);
        }

    }

    public boolean delete(int id){
        String sql = "delete from users where id = ?";

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
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }finally {
            conexao.closeConnection(conn);
        }
    }


}
