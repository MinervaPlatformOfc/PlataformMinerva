package com.example.minerva.conexao;
/// Classe criada com objetivo da criação e destruição da conexão com o banco de dados

//Importações
import java.sql.*;

import com.example.minerva.exception.FailedConnectionException;
import io.github.cdimascio.dotenv.Dotenv;


//Abertura da classe
public class Conexao {

    private static Connection conn;

    public static Connection getConnection(){
        try{
            if(conn == null || conn.isClosed()){
                PostgresqlConfig postgresql = new PostgresqlConfig();

                conn = postgresql.getConnection();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new FailedConnectionException("Erro na conexão! (PostgreSQL)");
        }
        return conn;
    }

    public static void closeConnection(){
        try{
            if(!(conn == null || conn.isClosed())){
                PostgresqlConfig postgresql = new PostgresqlConfig();

                postgresql.closeConnection(conn);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }finally {
            conn = null;
        }
    }

}
