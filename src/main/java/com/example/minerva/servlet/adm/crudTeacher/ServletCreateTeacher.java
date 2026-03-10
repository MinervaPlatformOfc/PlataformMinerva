package com.example.minerva.servlet.adm.crudTeacher;

import com.cloudinary.Cloudinary;
import com.example.minerva.conexao.CloudinaryConfig;
import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.GradeDAO;
import com.example.minerva.dao.HouseDAO;
import com.example.minerva.dao.TeacherDAO;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.loader.RechargeListener;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.minerva.utils.matricula.Matricula;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = "/admin/insertTeacher", loadOnStartup = 1)
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

        String email = request.getParameter("email");
        String password = request.getParameter("senha");

        if (userDAO.findByEmail(email) != null) {
            request.setAttribute("msg", "E-mail já cadastrado.");
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
            return;
        }
        if (!ValidacaoEmail.validarEmail(email)) {
            request.setAttribute("msg", "E-mail inválido.");
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
            return;
        }
        if (!ValidacaoSenha.validarSenha(password)) {
            request.setAttribute("msg", "Senha inválida.");
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
            return;
        }

        String name = request.getParameter("nome");

        Part filePart = request.getPart("foto");

        if (filePart == null) {
            request.setAttribute("msg", "Imagem não enviada.");
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
            return;
        }
        if (filePart.getSize() == 0) {
            request.setAttribute("msg", "Arquivo de imagem vazio.");
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
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
            request.setAttribute("msg", "Arquivo inválido.");
            request.getRequestDispatcher("/admin/ViewTeachers").forward(request, response);
            return;
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

        User newUser = new User(name, new HashSenha(password).getHashSenha(), email, "TEACHER", imageUrl);

        Integer houseId = Integer.parseInt(request.getParameter("houseIdInput"));
        String wand = request.getParameter("wood") + " - " +
                request.getParameter("core") + " - " +
                request.getParameter("flexibility");
        Boolean headHouse = request.getParameter("headHouse") != null;
        String pastExperiences = request.getParameter("pastExperiences");
        String wizardTitle = request.getParameter("wizardTitle");
        String teacherRegistrationCode = matricula.generateAndSave("TEACHER", email);

        String[] subjectIds = request.getParameterValues("newSubjects");
        List<Integer> subjects = new ArrayList<>();
        if (subjectIds != null) {
            for (String id : subjectIds) {
                subjects.add(Integer.parseInt(id));
            }
        }

        Teacher newTeacher = new Teacher(houseId, wand, headHouse, pastExperiences, wizardTitle, teacherRegistrationCode);

        request.setAttribute("msg", teacherRepository.save(newTeacher, newUser, subjects) ?
                "Professor inserido com sucesso!":
                "Erro ao inserir professor!");

        RechargeListener rechargeListener = new RechargeListener();
        rechargeListener.rechargeForTeacher(getServletContext());

        request.getRequestDispatcher("/admin/ViewTeachers").forward(request,response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }

}
