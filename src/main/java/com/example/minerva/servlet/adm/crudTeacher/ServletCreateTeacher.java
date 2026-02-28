package com.example.minerva.servlet.adm.crudTeacher;

import com.cloudinary.Cloudinary;
import com.example.minerva.conexao.CloudinaryConfig;
import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.model.Teacher;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;
import com.example.minerva.utils.validacao.ValidacaoEmail;
import com.example.minerva.utils.validacao.ValidacaoSenha;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.example.minerva.utils.matricula.Matricula;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = "/admin/CreateTeacher", loadOnStartup = 1)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class ServletCreateTeacher extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Matricula matricula = new Matricula();
        UserDAO userDAO = new UserDAO();
        TeacherDAO teacherRepository = new TeacherDAO();

        String email = request.getParameter("emailInput");
        String password = request.getParameter("passwordInput");

        if (userDAO.findByEmail(email) != null) {
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=email_exists");
            return;
        } if (!ValidacaoEmail.validarEmail(email)){
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=email_invalid");
            return;
        } if (!ValidacaoSenha.validarSenha(password)){
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=password_invalid");
            return;
        }
        String name = (String)  request.getAttribute("nameInput");

        Part filePart = request.getPart("image");
        if (filePart == null) {
//            System.out.println("❌ ERRO: filePart é NULL");
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=file_null");
            return;
        }

        if (filePart.getSize() == 0) {
//            System.out.println("❌ ERRO: filePart está VAZIO (tamanho 0)");
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=file_empty");
            return;
        }

        // CONVERTER PARA BYTE
        byte[] imageBytes;
        try (InputStream input = filePart.getInputStream()) {
            imageBytes = input.readAllBytes();
        }

//        System.out.println("Bytes lidos: " + imageBytes.length);

        // Verificar se é uma imagem válida (magic numbers)
        if (imageBytes.length < 4) {
            throw new ServletException("Arquivo muito pequeno");
        }

        // Upload para Cloudinary com parâmetros explícitos
        Cloudinary cloudinary = CloudinaryConfig.getInstance();

        Map<String, Object> params = new HashMap<>();
        params.put("folder", "profile_images");
        params.put("public_id", "user_" + System.currentTimeMillis());
        params.put("overwrite", true);
        params.put("resource_type", "image");
        params.put("type", "upload");

        // uso do byte array diretamente
        Map uploadResult = cloudinary.uploader().upload(imageBytes, params);
        String imageUrl = (String) uploadResult.get("secure_url");

        User newUser = new User(name, email, new HashSenha(password).getHashSenha(), "TEACHER", imageUrl);

        Integer houseId = Integer.parseInt(request.getParameter("houseIdInput"));
        String wand = request.getParameter("wandInput");
        Boolean headHouse = Boolean.parseBoolean(request.getParameter("headHouseInput"));
        String pastExperiences = request.getParameter("pastExperiencesInput");
        String wizardTitle = request.getParameter("wizardTitleInput");
        String teacherRegistrationCode = matricula.generateAndSave("TEACHER", (String) request.getAttribute("email"));

        Teacher newTeacher = new Teacher(houseId, wand, headHouse, pastExperiences, wizardTitle, teacherRegistrationCode);

        request.setAttribute("msg", teacherRepository.save(newTeacher, newUser) ? "Professor inserido com sucesso!": "Erro ao inserir professor!");

        request.getRequestDispatcher("/admin/ViewTeachers").forward(request,response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }

}
