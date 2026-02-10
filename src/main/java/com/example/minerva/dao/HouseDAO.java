package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.model.House;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                String name = rs.getString("name");
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
}
