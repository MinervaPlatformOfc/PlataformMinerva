package com.example.minerva.servlet.adm.crudAdmins;

import com.cloudinary.Cloudinary;
import com.example.minerva.conexao.CloudinaryConfig;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.loader.RechargeListener;
import com.example.minerva.model.User;
import com.example.minerva.utils.validacao.ValidacaoEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/admin/updateAdmin", loadOnStartup = 1)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)

public class ServletUpdateAdmin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String originalEmail = request.getParameter("emailOriginal");

        UserDAO userDAO = new UserDAO();

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        boolean currentAdm = currentUser.getEmail().equals(originalEmail);

// ====== PEGAR DADOS ======
        String nameInput = request.getParameter("nameInput");
        String email = request.getParameter("emailInput");


// ====== VALIDAÇÕES ======
        if (!originalEmail.equals(email)){
            if (userDAO.findByEmail(email) != null) {
                request.setAttribute("msg", "Email já existente");
                request.getRequestDispatcher("/admin/CRUD/Admin.jsp").forward(request, response);
                return;
            }

            if (!ValidacaoEmail.validarEmail(email)) {
                request.setAttribute("msg", "Email inválido");
                request.getRequestDispatcher("/admin/CRUD/Admin.jsp").forward(request, response);
                return;
            }
        }

// ====== TRATAR IMAGEM ======
        Part filePart = request.getPart("imageInput");
        String imageUrl;

        if (filePart == null || filePart.getSize() == 0) {
            // Mantém a imagem antiga
            imageUrl = request.getParameter("fotoOriginal");
        } else {

            byte[] imageBytes;
            try (InputStream input = filePart.getInputStream()) {
                imageBytes = input.readAllBytes();
            }

            if (imageBytes.length < 4) {
                request.setAttribute("msg", "Arquivo inválido.");
                request.getRequestDispatcher("/admin/ViewAdmins").forward(request, response);
                return;
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

// ====== CRIAR OBJETO ======
        User user = new User(nameInput, email, imageUrl, "admin");

// ====== ATUALIZAR ======
        boolean userUpdated = userDAO.updateAdmin(id, user);

        if (userUpdated) {
            request.setAttribute("msg", "Usuário atualizado com sucesso");
        } else {
            request.setAttribute("msg", "Erro ao atualizar usuário");
        }

        if (currentAdm){
            session.setAttribute("user", user);
            request.setAttribute("name", nameInput);
            request.setAttribute("url", imageUrl);
        }

        RechargeListener rechargeListener = new RechargeListener();
        rechargeListener.rechargeForAdmin(getServletContext());


        request.getRequestDispatcher("/admin/ViewAdmins").forward(request, response);
    }
}
