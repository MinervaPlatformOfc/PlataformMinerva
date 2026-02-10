package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.ProfileDTO;
import com.example.minerva.dto.StudentHomeDTO;
import com.example.minerva.model.Student;
import com.example.minerva.model.User;

import java.sql.*;
import java.text.SimpleDateFormat;

public class StudentDAO {
    private Conexao conexao;

    public StudentDAO() {
        this.conexao = new Conexao(); // usa a classe de conexão
    }

    public boolean save(Student student, User user){
        String sql = "call create_student(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = conexao.getConnection(); // pega conexão da classe Conexao
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return false;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setInt(4, student.getSchoolYear());
            stmt.setString(5, student.getWand());
            stmt.setBoolean(6, false);
            stmt.setString(7, student.getBlood());
            stmt.setDate(8, java.sql.Date.valueOf(student.getBirthDate()));
            stmt.setString(9, student.getRegistration());
            stmt.setString(10, student.getResidenceAddress());
            stmt.setString(11, student.getLegalGuardianName());
            stmt.setString(12, student.getPetType());
            stmt.setBoolean(13, student.getBasicKit());
            stmt.setBoolean(14, student.getGuardianPermission());


            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // fecha conexão
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }
    }

    public StudentHomeDTO getStudentByEmail(String email) {
        String sql = "SELECT s.id AS student_id, u.name AS user_name, h.name AS house_name " +
                "FROM student s " +
                "JOIN users u ON s.user_id = u.id " +
                "LEFT JOIN house h ON s.house_id = h.id " +
                "WHERE u.email = ?";

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
                int id = rs.getInt("student_id");
                String name = rs.getString("user_name");
                String houseName = rs.getString("house_name");
                return new StudentHomeDTO(id, name, houseName);
            } else {
                return null; // estudante não encontrado
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // fecha conexão
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }
    }

    public ProfileDTO getStudentProfile(int studentId) {
        String sql = "SELECT s.*, u.name AS user_name, h.name AS house_name " +
                "FROM student s " +
                "JOIN users u ON s.user_id = u.id " +
                "LEFT JOIN house h ON s.house_id = h.id " +
                "WHERE s.id = ?";

        Connection conn = null;

        try {
            conn = conexao.getConnection();
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Varinha
                String wand = rs.getString("wand");
                String wandWood = null, wandCore = null, wandFlexibility = null;
                if (wand != null && wand.contains(" - ")) {
                    String[] parts = wand.split(" - ");
                    wandWood = parts[0];
                    wandCore = parts[1];
                    wandFlexibility = parts[2];
                }

                return new ProfileDTO(
                        studentId,
                        rs.getString("user_name"),
                        rs.getDate("birth_date"),
                        rs.getString("blood"),
                        rs.getString("registration"),
                        rs.getString("residence_address"),
                        rs.getString("legal_guardian_name"),
                        rs.getString("school_year") + "º Ano",
                        rs.getString("house_name"),
                        wandWood,
                        wandCore,
                        wandFlexibility,
                        rs.getString("pet_type"),
                        rs.getBoolean("flight_fitness"),
                        rs.getBoolean("basic_kit"),
                        rs.getString("allergies"),
                        rs.getBoolean("guardian_permission")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }
        return null;
    }

    public boolean updateHouseIdByEmail(String email, String houseName) {

        String sql = " UPDATE student s SET house_id = (SELECT h.id FROM house h WHERE h.name = ?), first_access = false FROM users u WHERE s.user_id = u.id AND u.email = ?";
        Connection conn = null;

        try {
            conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, houseName);
            stmt.setString(2, email);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // fecha conexão
            if (conn != null) {
                conexao.closeConnection(conn);
            }
        }
    }
}
