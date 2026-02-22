package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.model.House;

import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

public class HouseDAO {

    private Conexao conexao;

    public HouseDAO() {
        this.conexao = new Conexao(); // usa a classe de conexão
    }

    public Queue<House> viewRanking() {
        Connection conn = null;

        try {
            conn = conexao.getConnection(); // pega conexão da classe Conexao
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return null;
            }

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM house_ranking";
            ResultSet rs = stmt.executeQuery(sql);
            Queue<House> ranking = new LinkedList<>();

            while (rs.next()) {
                String name = rs.getString("house_name");
                int points = rs.getInt("points");
                ranking.add(new House(name, points));
            }
            return ranking;
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

    public void updatePointsHouse(int house_id, int points) {
        Connection conn = null;
        String sql = "Update house set points = points + ? where id = ?";
        try {
            conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, points);
            stmt.setInt(2, house_id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("Erro ao atualizar house de " + house_id);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
