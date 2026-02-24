package com.example.minerva.dao;

import com.example.minerva.conexao.Conexao;
import com.example.minerva.dto.StudentGradeDTO;
import com.example.minerva.dto.TeacherDTO;
import com.example.minerva.dto.TeacherHomeDTO;
import com.example.minerva.dto.TeacherProfileDTO;
import com.example.minerva.model.Teacher;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;

import java.sql.*;
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
                                        rs.getInt("teacher_id"),
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

        public Integer getUserIdById(int id){
            String sql= "select user_id from teacher where id = ?";

            Connection conn = conexao.getConnection();

            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, id);

                ResultSet rs = pstmt.executeQuery();

                if(rs.next()){
                    return rs.getInt("user_id");
                }
            }catch (SQLException sqle){
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
                String sqlFindUserId = "select user_id from teacher where id = ?";

                Connection conn = conexao.getConnection();

                if(conn == null){
                        System.out.println("Erro de conexão (PostgreSQL)");
                        return false;
                }
                int lines = 0;

                try(PreparedStatement pstmt = conn.prepareStatement(sql); PreparedStatement stmt = conn.prepareStatement(sqlFindUserId)){
                        pstmt.setInt(1, id);
                        stmt.setInt(1, id);

                        UserDAO userRepository = new UserDAO();

                        ResultSet rs = stmt.executeQuery();
                        boolean userDeleted = userRepository.delete(rs.getInt("user_id"));

                        rs.next();

                        lines = pstmt.executeUpdate();

                        return lines > 0 && userDeleted;

                }catch (SQLException sqle){
                        sqle.printStackTrace();
                        return false;
                }finally{
                        conexao.closeConnection(conn);
                }
        }

        public TeacherHomeDTO getTeacherWithStudentsByEmail(String email) {
                String sql = "SELECT teacher_id, teacher_name, teacher_house_name, " +
                        "student_id, student_name, student_house_name, n1, n2, subject_name " +
                        "FROM teacher_students " +
                        "WHERE teacher_name = (SELECT name FROM users WHERE email = ?) " +
                        "ORDER BY student_name";

                Connection conn = null;
                try {
                        conn = conexao.getConnection();
                        if (conn == null) {
                                System.out.println("Erro ao conectar ao banco!");
                                return null;
                        }

                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, email);
                        ResultSet rs = stmt.executeQuery();

                        List<StudentGradeDTO> students = new ArrayList<>();
                        TeacherHomeDTO teacherDTO = null;

                        while (rs.next()) {
                                // só cria o DTO do professor na primeira linhaaaaaaaaaaaaa
                                if (teacherDTO == null) {
                                        int teacherId = rs.getInt("teacher_id");
                                        String teacherName = rs.getString("teacher_name");
                                        String teacherHouse = rs.getString("teacher_house_name");
                                        teacherDTO = new TeacherHomeDTO(teacherId, teacherName, teacherHouse, students);
                                }

                                // adiciona cada aluno no array
                                Integer studentId = rs.getObject("student_id") != null ? rs.getInt("student_id") : null;                String studentName = rs.getString("student_name");
                                String studentHouse = rs.getString("student_house_name");
                                Double n1 = rs.getObject("n1") != null ? rs.getDouble("n1") : null;
                                Double n2 =rs.getObject("n2") != null ? rs.getDouble("n2") : null;
                                String subjectName = rs.getString("subject_name");

                                if (studentId != null ) students.add(new StudentGradeDTO(studentId, studentName, studentHouse, n1, n2, subjectName));
                        }

                        return teacherDTO;

                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                } finally {
                        if (conn != null) {
                                conexao.closeConnection(conn);
                        }
                }
        }

        public TeacherProfileDTO getTeacherProfile(int teacherId) {
                String sqlTeacher = "SELECT t.*, u.name AS user_name, u.profile_image_url AS image_url, " +
                        "h.name AS house_name " +
                        "FROM teacher t " +
                        "JOIN users u ON t.user_id = u.id " +
                        "LEFT JOIN house h ON t.house_id = h.id " +
                        "WHERE t.id = ?";

                String sqlSubjects = "SELECT sub.name " +
                        "FROM subject sub " +
                        "JOIN subject_teacher st ON sub.id = st.subject_id " +
                        "WHERE st.teacher_id = ?";

                Connection conn = null;
                try {
                        conn = conexao.getConnection();
                        if (conn == null) {
                                System.out.println("Erro ao conectar ao banco!");
                                return null;
                        }

                        // Dados do professor
                        PreparedStatement stmt = conn.prepareStatement(sqlTeacher);
                        stmt.setInt(1, teacherId);
                        ResultSet rs = stmt.executeQuery();

                        if (!rs.next()) return null;

                        // Varinha
                        String wand = rs.getString("wand");
                        String wandWood = null, wandCore = null, wandFlexibility = null;
                        if (wand != null && wand.contains(" - ")) {
                                String[] parts = wand.split(" - ");
                                wandWood = parts[0];
                                wandCore = parts[1];
                                wandFlexibility = parts[2];
                        }

                        // Matérias
                        List<String> subjects = new ArrayList<>();
                        PreparedStatement stmtSubjects = conn.prepareStatement(sqlSubjects);
                        stmtSubjects.setInt(1, teacherId);
                        ResultSet rsSubjects = stmtSubjects.executeQuery();
                        while (rsSubjects.next()) {
                                subjects.add(rsSubjects.getString("name"));
                        }

                        return new TeacherProfileDTO(
                                teacherId,
                                rs.getString("image_url"),
                                rs.getString("user_name"),
                                rs.getString("wizard_title"),
                                rs.getBoolean("head_house"),
                                rs.getString("house_name"),
                                wandWood,
                                wandCore,
                                wandFlexibility,
                                rs.getString("past_experiences"),
                                rs.getString("teacher_registration_code"),
                                subjects
                        );

                } catch (SQLException e) {
                        e.printStackTrace();
                } finally {
                        if (conn != null) conexao.closeConnection(conn);
                }
                return null;
        }

        public TeacherHomeDTO getTeacherWithStudentsById(int teacherId) {
                String sql = "SELECT teacher_id, teacher_name, teacher_house_name, " +
                        "student_id, student_name, student_house_name, n1, n2, subject_name " +
                        "FROM teacher_students " +
                        "WHERE teacher_id = ? " +
                        "ORDER BY student_name";

                Connection conn = null;
                try {
                        conn = conexao.getConnection();
                        if (conn == null) {
                                System.out.println("Erro ao conectar ao banco!");
                                return null;
                        }

                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, teacherId);
                        ResultSet rs = stmt.executeQuery();

                        List<StudentGradeDTO> students = new ArrayList<>();
                        TeacherHomeDTO teacherDTO = null;

                        while (rs.next()) {
                                if (teacherDTO == null) {
                                        String teacherName = rs.getString("teacher_name");
                                        String teacherHouse = rs.getString("teacher_house_name");

                                        teacherDTO = new TeacherHomeDTO(teacherId, teacherName, teacherHouse, students);
                                }

                                Integer studentId = rs.getObject("student_id") != null ? rs.getInt("student_id") : null;
                                String studentName = rs.getString("student_name");
                                String studentHouse = rs.getString("student_house_name");
                                Double n1 = rs.getObject("n1") != null ? rs.getDouble("n1") : null;
                                Double n2 = rs.getObject("n2") != null ? rs.getDouble("n2") : null;
                                String subjectName = rs.getString("subject_name");

                                if (studentId != null ) students.add(new StudentGradeDTO(studentId, studentName, studentHouse, n1, n2, subjectName));
                        }

                        return teacherDTO;

                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                } finally {
                        if (conn != null) {
                                conexao.closeConnection(conn);
                        }
                }
        }

        public List<String> getYearsAndSubjectsByTeacherId(int teacherId) {
                String sql = "SELECT DISTINCT school_year, subject_name " +
                        "FROM teacher_students " +
                        "WHERE teacher_id = ? " +
                        "ORDER BY school_year, subject_name";

                Connection conn = null;
                List<String> result = new ArrayList<>();

                try {
                        conn = conexao.getConnection();
                        if (conn == null) {
                                System.out.println("Erro ao conectar ao banco!");
                                return null;
                        }

                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, teacherId);

                        ResultSet rs = stmt.executeQuery();

                        while (rs.next()) {
                                Integer year = rs.getObject("school_year") != null ? rs.getInt("school_year") : null;
                                String subject = rs.getString("subject_name");

                                if (year != null && subject != null) {
                                        result.add(year + "º Ano (" + subject + ")");
                                }
                        }

                } catch (SQLException e) {
                        e.printStackTrace();
                } finally {
                        if (conn != null) {
                                conexao.closeConnection(conn);
                        }
                }

                return result;
        }
        public List<StudentGradeDTO> getStudentsByTeacherYearAndSubject(int teacherId, int year, String subject) {
                String sql = "SELECT DISTINCT student_id, student_name, student_house_name, n1, n2 " +
                        "FROM teacher_students " +
                        "WHERE teacher_id = ? AND school_year = ? AND subject_name = ? " +
                        "ORDER BY student_name";

                Connection conn = null;
                List<StudentGradeDTO> students = new ArrayList<>();

                try {
                        conn = conexao.getConnection();
                        if (conn == null) {
                                System.out.println("Erro ao conectar ao banco!");
                                return null;
                        }

                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, teacherId);
                        stmt.setInt(2, year);
                        stmt.setString(3, subject);

                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                                int studentId = rs.getInt("student_id");
                                String studentName = rs.getString("student_name");
                                String studentHouse = rs.getString("student_house_name");
                                Double n1 = rs.getObject("n1") != null ? rs.getDouble("n1") : null;
                                Double n2 = rs.getObject("n2") != null ? rs.getDouble("n2") : null;

                                students.add(new StudentGradeDTO(studentId, studentName, studentHouse, n1, n2));
                        }

                } catch (SQLException e) {
                        e.printStackTrace();
                } finally {
                        if (conn != null) {
                                conexao.closeConnection(conn);
                        }
                }

                return students;
        }

    public boolean findByRegistration(String registration){
        String sql = "select 1 from teacher where teacher_registration_code = ?";
        Connection conn = null;

        try {
            conn = conexao.getConnection(); // pega conexão da classe Conexao
            if (conn == null) {
                System.out.println("Erro ao conectar ao banco!");
                return false;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, registration);
            return stmt.executeQuery().next();
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

