package com.example.minerva.dao;

import com.example.minerva.dto.AdminDTO;
import com.example.minerva.dto.UserAnalysisDTO;
import com.example.minerva.model.User;
import com.example.minerva.conexao.Conexao;
import com.example.minerva.utils.criptografia.HashSenha;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO {

    private final Connection conn = Conexao.getConnection();

    public boolean saveAdmin(User user){
        String sql = "call create_admin(?, ?, ?, ?)";

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
        }
    }

    // Busca usuário pelo email
    public User findByEmail(String email) {

        String sql = "SELECT name, password, email, role, first_access, created_at FROM users WHERE email = ?";

        try {
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
        }

        // Retorna null se não encontrar
        return null;
    }


    public User findById(int id) {

        String sql = "SELECT name, password, email, role, first_access, created_at FROM users WHERE id = ?";

        try {

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
        }

        // Retorna null se não encontrar
        return null;
    }

    public UserAnalysisDTO getUserAnalysis(){
        String sql = "select (select count(id) from users) as \"total_users\",\n" +
                "(select count(id) from users where role = 'admin') as \"total_admins\",\n" +
                "(select count(id) from users where role = 'teacher') as \"total_teachers\",\n" +
                "(select count(id) from users where role = 'student') as \"total_students\";";


        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                return new UserAnalysisDTO(
                        rs.getInt("total_users"),
                        rs.getInt("total_admins"),
                        rs.getInt("total_students"),
                        rs.getInt("total_teachers")
                );
            }else{
                return null;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }


    public List<AdminDTO> getAllAdmins(){
        String sql = "SELECT id, email, password, name, profile_image_url FROM users where role = \'admin\'";


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
        }
    }

    public List<User> getNotAdmins(){
        String sql = "SELECT id, role, email, password, name, profile_image_url FROM users where role != 'admin'";

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
        }
    }


    public boolean delete(int id){
        String sql = "delete from users where id = ?";

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
        }
    }

    public  boolean update(int id, User user) {

        String sql = "UPDATE users SET name = ?, email = ?, password = ?, profile_image_url = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        try (PreparedStatement stmt = conn.prepareStatement(sql);
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
