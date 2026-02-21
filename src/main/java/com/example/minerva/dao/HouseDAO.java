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

    public boolean delete(int id){
        String sql = "delete from house where id = ?";

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
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }finally{
            conexao.closeConnection(conn);
        }
    }
}
