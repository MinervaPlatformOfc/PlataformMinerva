package com.example.minerva.dao;

import java.sql.*;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.TeacherDTO;
import com.example.minerva.model.Teacher;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {

    private final Conexao conexao = new Conexao();


    //Metodo de listagem de todos os professores
    public boolean save(Teacher teacher, User user){
        String sql = "call create_teacher(?,?,?,?,?,?,?,?,?)";

        Connection conn = conexao.getConnection();

        int lines = 0;

        if(conn == null){
            System.out.println("Erro de conexão (PostgreSQL)");
            return false;
        }

        try(CallableStatement pstmt = conn.prepareCall(sql)){

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, String.valueOf(new HashSenha(user.getPassword())));
            pstmt.setString(3, user.getName());
            pstmt.setInt(4, teacher.getHouseId());
            pstmt.setString(5, teacher.getWand());
            pstmt.setBoolean(6, teacher.getHeadHouse());
            pstmt.setString(7, teacher.getPastExperiences());
            pstmt.setString(8, teacher.getWizardTitle());
            pstmt.setString(9, teacher.getTeacherRegistrationCode());

            lines = pstmt.executeUpdate();

            return lines > 0;
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }finally {
            conexao.closeConnection(conn);
        }
    }


    public List<TeacherDTO> getAllTeachers(){
        String sql = "select t.id as \"teacher_id\",u.email, u.password, u.name as \"user\", h.name as \"house\", t.wand, t.past_experiences, t.wizard_title " +
                "from teacher t " +
                "join users u on t.user_id = u.id " +
                "join house h on t.house_id = h.id " +
                "order by u.id";

        Connection conn = conexao.getConnection();

        List<TeacherDTO> teachers = new ArrayList<>();

        CommentDAO commentRepository = new CommentDAO();

        if(conn == null){
            System.out.println("Erro de conexão (PostgreSQL)");
            return new ArrayList<>();
        }
        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                TeacherDTO temp = new TeacherDTO(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("user"),
                        rs.getString("house"),
                        rs.getString("wand"),
                        rs.getString("past_experiences"),
                        rs.getString("wizard_title"),
                        commentRepository.listAllByTeacher(rs.getInt("teacher_id"))

                );
                teachers.add(temp);
            }
            return teachers;
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return new ArrayList<TeacherDTO>();
        }finally {
            conexao.closeConnection(conn);
        }
    }

    public TeacherDTO findById(int id){
        String sql = "select t.id as teacher_id, u.email, u.password, u.name as \"user\", h.name as \"house\", t.wand, t.past_experiences, t.wizard_title\n" +
                "from teacher t\n" +
                "join users u on t.user_id = u.id\n" +
                "join house h on t.house_id = h.id\n" +
                "where t.id = ?\n" +
                "order by u.id;";

        Connection conn = conexao.getConnection();

        CommentDAO commentRepository = new CommentDAO();

        if(conn == null){
            System.out.println("Erro na conexão (PostgreSQL)");
            return null;
        }

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return new TeacherDTO(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("user"),
                        rs.getString("house"),
                        rs.getString("wand"),
                        rs.getString("past_experiences"),
                        rs.getString("wizard_title"),
                        commentRepository.listAllByTeacher(rs.getInt("teacher_id"))
                );
            }else{
                return null;
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.closeConnection(conn);
        }
    }

    public int update(int id, Teacher newTeacher){
        String sqlWand = "update teacher set wand = ? where id = ?";
        String sqlPastExperiences = "update teacher set past_experiences = ? where id = ?";
        String sqlWizardTitle = "update teacher set wizard_title = ? where id = ?";

        Connection conn = conexao.getConnection();

        if (conn == null) {
            System.out.println("Erro de conexão (PostgreSQL)");
            return 0;
        }

        int lines = 0;

        TeacherDTO teacher = findById(id);
        if (teacher == null) return 0;

        try (
                PreparedStatement pstmtWand = conn.prepareStatement(sqlWand);
                PreparedStatement pstmtPastExperiences = conn.prepareStatement(sqlPastExperiences);
                PreparedStatement pstmtWizardTitle = conn.prepareStatement(sqlWizardTitle)
        ) {

            if(!teacher.getWand().equals(newTeacher.getWand())){
                pstmtWand.setString(1, newTeacher.getWand());
                pstmtWand.setInt(2, id);
                lines += pstmtWand.executeUpdate();
            }

            if(!teacher.getPastExperiences().equals(newTeacher.getPastExperiences())){
                pstmtPastExperiences.setString(1, newTeacher.getPastExperiences());
                pstmtPastExperiences.setInt(2, id);
                lines += pstmtPastExperiences.executeUpdate();
            }

            if(!teacher.getWizardTitle().equals(newTeacher.getWizardTitle())){
                pstmtWizardTitle.setString(1, newTeacher.getWizardTitle());
                pstmtPastExperiences.setInt(2, id);
                lines += pstmtWizardTitle.executeUpdate();
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
        String sql = "delete from teacher where id = ?";

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
