package com.example.minerva.servlet.adm.crudTeacher;

import com.cloudinary.Cloudinary;
import com.example.minerva.conexao.CloudinaryConfig;
import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.model.Teacher;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;
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

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
@WebServlet(urlPatterns = "/admin/CreateTeacher", asyncSupported = true)
public class ServletCreateTeacher extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Matricula matricula = new Matricula();
        TeacherDAO teacherRepository = new TeacherDAO();

        String email = (String) request.getAttribute("emailInput");
        String password = String.valueOf(new HashSenha((String) request.getAttribute("passwordInput")));
        String name = (String)  request.getAttribute("nameInput");

        // ====== TRATAR IMAGEM ======
        Part filePart = request.getPart("imageInput");
        String imageUrl;

        if (filePart == null || filePart.getSize() == 0) {
            response.sendRedirect(request.getContextPath() + "/admin/CRUD/Teacher.jsp?error=validation_image_failed");
            return;
        } else {

            byte[] imageBytes;
            try (InputStream input = filePart.getInputStream()) {
                imageBytes = input.readAllBytes();
            }

            if (imageBytes.length < 4) {
                throw new ServletException("Arquivo muito pequeno");
            }

            Cloudinary cloudinary = CloudinaryConfig.getInstance();

            Map<String, Object> params = new HashMap<>();
            params.put("folder", "profile_images");
            params.put("public_id", "user_" + System.currentTimeMillis());
            params.put("overwrite", true);
            params.put("resource_type", "image");
            params.put("type", "upload");

            Map uploadResult = cloudinary.uploader().upload(imageBytes, params);

            imageUrl = (String) uploadResult.get("secure_url");
        }

        User newUser = new User(name, password, email, "TEACHER",imageUrl);

        Integer houseId = (Integer) request.getAttribute("houseIdInput");
        String wand = (String) request.getAttribute("wandInput");
        Boolean headHouse = (Boolean) request.getAttribute("headHouseInput");
        String pastExperiences = (String) request.getAttribute("pastExperiencesInput");
        String wizardTitle = (String) request.getAttribute("wizardTitleInput");
        String teacherRegistrationCode = matricula.generateAndSave("TEACHER", email);

        Teacher newTeacher = new Teacher(houseId, wand, headHouse, pastExperiences, wizardTitle, teacherRegistrationCode);

        teacherRepository.save(newTeacher, newUser);

        request.setAttribute("msg", "Professor inserido com sucesso!");

        request.getRequestDispatcher("/admin/ViewTeachers").forward(request,response);
    }

}
