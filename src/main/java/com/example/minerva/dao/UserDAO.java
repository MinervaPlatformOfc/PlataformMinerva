package com.example.minerva.dao;

import com.example.minerva.dto.AdminDTO;
import com.example.minerva.model.User;
import com.example.minerva.conexao.Conexao;
import com.example.minerva.utils.criptografia.HashSenha;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public User findById(int id) {

        String sql = "SELECT name, password, email, role, first_access, created_at FROM users WHERE id = ?";
        Connection conn = null;

        try {
            conn = conexao.getConnection(); // pega conexão da classe Conexao
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getBoolean("first_access"),
                        rs.getDate("created_at")
                );
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


    public List<AdminDTO> getAllAdmins(){
        String sql = "SELECT id, email, password,name FROM users where role = \'admin\'";

        Connection conn = conexao.getConnection();

        List<AdminDTO> users = new ArrayList<>();

        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                AdminDTO temp = new AdminDTO(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name")
                );
                users.add(temp);
            }
            return users;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return new ArrayList<>();
        }finally {
            conexao.closeConnection(conn);
        }
    }


    public int update(int id, User newUser) {

        String sqlName = "update users set name = ? where id = ?";
        String sqlEmail = "update users set email = ? where id = ?";
        String sqlPassword = "update users set password = ? where id = ?";

        Connection conn = conexao.getConnection();

        if (conn == null) {
            System.out.println("Erro de conexão (PostgreSQL)");
            return 0;
        }

        int lines = 0;

        User user = findById(id);
        if (user == null) return 0;

        String newHashedPassword = String.valueOf(new HashSenha(newUser.getPassword()));

        try (
                PreparedStatement pstmtName = conn.prepareStatement(sqlName);
                PreparedStatement pstmtEmail = conn.prepareStatement(sqlEmail);
                PreparedStatement pstmtPassword = conn.prepareStatement(sqlPassword)
        ) {

            if (!newUser.getName().equals(user.getName())) {
                pstmtName.setString(1, newUser.getName());
                pstmtName.setInt(2, id);
                lines += pstmtName.executeUpdate();
            }

            if (!newUser.getEmail().equals(user.getEmail())) {
                pstmtEmail.setString(1, newUser.getEmail());
                pstmtEmail.setInt(2, id);
                lines += pstmtEmail.executeUpdate();
            }

            if (!newHashedPassword.equals(user.getPassword())) {
                pstmtPassword.setString(1, newHashedPassword);
                pstmtPassword.setInt(2, id);
                lines += pstmtPassword.executeUpdate();
            }

            return lines;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 0;
        } finally {
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
