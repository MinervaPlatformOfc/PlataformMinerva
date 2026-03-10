package com.example.minerva.servlet.adm.crudUser;

import com.cloudinary.Cloudinary;
import com.example.minerva.conexao.CloudinaryConfig;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.loader.RechargeListener;
import com.example.minerva.model.User;
import com.example.minerva.utils.validacao.ValidacaoEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/admin/updateUser", loadOnStartup = 1)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class ServletUpdateUsers extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDAO userDAO = new UserDAO();

        String nameInput = request.getParameter("nome");
        String email = request.getParameter("email");
        String emailOriginal = request.getParameter("emailOriginal");

        if (!emailOriginal.equals(email)) {
            User existingUser = userDAO.findByEmail(email);
            if (existingUser != null) {
                request.setAttribute("msg", "Email já existente");
                request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
                return;
            }

            if (!ValidacaoEmail.validarEmail(email)) {
                request.setAttribute("msg", "Email inválido");
                request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
                return;
            }
        }

        Part filePart = request.getPart("foto");
        String imageUrl;

        if (filePart == null || filePart.getSize() == 0) {
            imageUrl = request.getParameter("currentImageUrl");
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

            try {
                Map uploadResult = cloudinary.uploader().upload(imageBytes, params);
                imageUrl = (String) uploadResult.get("secure_url");
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("Erro ao fazer upload da imagem", e);
            }
        }

        User user = new User(nameInput, email, imageUrl);
        boolean userUpdated = userDAO.update(id, user);

        if (userUpdated) {
            request.setAttribute("msg", "Usuário atualizado com sucesso!");
        } else {
            request.setAttribute("msg", "Erro ao atualizar usuário!");
        }

        RechargeListener rechargeListener = new RechargeListener();
        rechargeListener.rechargeForUser(getServletContext());

        request.getRequestDispatcher("/admin/users").forward(request, response);
    }
}