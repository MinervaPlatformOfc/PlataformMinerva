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

    public boolean saveAdmin(User user){
        String sql = "call create_admin(?, ?, ?, ?)";

        Connection conn = conexao.getConnection();

        int lines = 0;

        try(CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setString(1, user.getEmail());
            stmt.setString(2, String.valueOf(new HashSenha(user.getPassword())));
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getImageUrl());

            lines = stmt.executeUpdate();

            return lines > 0;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }finally{
            conexao.closeConnection(conn);
        }
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
        String sql = "SELECT id, email, password, name, profile_image_url FROM users where role = \'admin\'";

        Connection conn = conexao.getConnection();

        List<AdminDTO> users = new ArrayList<>();

        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                AdminDTO temp = new AdminDTO(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("profile_image_url")
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

    public List<User> getNotAdmins(){
        String sql = "SELECT id, role, email, password, name, profile_image_url FROM users where role != 'admin'";

        Connection conn = conexao.getConnection();

        List<User> users = new ArrayList<>();

        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                User temp = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("profile_image_url")
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

    public  boolean update(int id, User user) {

        String sql = "UPDATE users SET name = ?, email = ?, password = ?, profile_image_url = ? WHERE id = ?";

        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getImageUrl());
            stmt.setInt(5, id);

            return stmt.executeUpdate()>0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public  boolean updateAdmin(int id, User user) {

        String sql = "UPDATE users SET name = ?, email = ?, profile_image_url = ? WHERE id = ?";

        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getImageUrl());
            stmt.setInt(4, id);

            return stmt.executeUpdate()>0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public  boolean updatePassword(String email, String password) {

        String sql = "UPDATE users SET password = ? WHERE email = ?";

        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, password);
            stmt.setString(2, email);

            return stmt.executeUpdate()>0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public double[] getUserStatistics() {
        String sql = "SELECT total_users, pct_admins, pct_teachers, pct_students FROM user_statistics";

        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                double totalUsers = rs.getDouble("total_users");
                double pctAdmins = rs.getDouble("pct_admins");
                double pctTeachers = rs.getDouble("pct_teachers");
                double pctStudents = rs.getDouble("pct_students");

                return new double[]{totalUsers, pctAdmins, pctTeachers, pctStudents};
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retorna zeros em tds valores caso dê algum problema
        return new double[]{0, 0, 0, 0};
    }
}
