package com.example.minerva.servlet.register;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.minerva.conexao.CloudinaryConfig;
import com.example.minerva.conexao.Conexao;
import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dao.UserDAO;
import com.example.minerva.model.Student;
import com.example.minerva.model.User;
import com.example.minerva.utils.criptografia.HashSenha;
import com.example.minerva.utils.email.Email;
import com.example.minerva.utils.matricula.Matricula;
import com.example.minerva.utils.validacao.ValidacaoEmail;
import com.example.minerva.utils.validacao.ValidacaoSenha;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/register", asyncSupported = true)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 5,   // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class ServletRegister extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        AsyncContext async = request.startAsync();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String pet = request.getParameter("pet");
        String allergies = request.getParameter("allergies");
        String blood = request.getParameter("blood");
        LocalDate birthDate = request.getParameter("birthDate") != null
                ? LocalDate.parse(request.getParameter("birthDate"))
                : null;

        String wand = request.getParameter("wood") + " - " +
                request.getParameter("core") + " - " +
                request.getParameter("flexibility");

        int schoolYear = request.getParameter("schoolYear") != null
                ? Integer.parseInt(request.getParameter("schoolYear"))
                : 0;

        String legalGuardianName = request.getParameter("legalGuardianName");
        String residenceAdress = request.getParameter("residenceAdress");
        boolean guardianPermission = "true".equalsIgnoreCase(request.getParameter("guardianPermission"));
        boolean basicKit = "true".equalsIgnoreCase(request.getParameter("BasicKit"));
        String registration = request.getParameter("registration");

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


        Matricula matricula = new Matricula();
        Email emailUtils = new Email();

        boolean matriculaValida = matricula.validate(email, registration);

        if (!matriculaValida) {
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=invalid_registration");
            return;
        }
//        System.out.println("✅ Matrícula validada com sucesso!");

// Validações do usuário
//        System.out.println("=== VALIDAÇÕES DO USUÁRIO ===");
        UserDAO userDao = new UserDAO();
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();
//        System.out.println("Data de nascimento: " + birthDate);
//        System.out.println("Idade calculada: " + age);
//        System.out.println("Data atual: " + today);

// Validar senha
        boolean senhaValida = ValidacaoSenha.validarSenha(password);
//        System.out.println("Senha válida: " + (senhaValida ? "✅ SIM" : "❌ NÃO"));

// Validar email existente
        User usuarioExistente = userDao.findByEmail(email);
//        System.out.println("Email já cadastrado: " + (usuarioExistente != null ? "❌ SIM" : "✅ NÃO"));

// Validar idade
        boolean idadeValida = age >= 11 && age <= 13;
//        System.out.println("Idade dentro do permitido (11-13): " + (idadeValida ? "✅ SIM" : "❌ NÃO"));

// Validar data de nascimento
        boolean dataNascimentoValida = !birthDate.isAfter(today);
//        System.out.println("Data de nascimento não é futura: " + (dataNascimentoValida ? "✅ SIM" : "❌ NÃO"));

        if (!senhaValida || usuarioExistente != null || !idadeValida || !dataNascimentoValida) {
//            System.out.println("❌ REDIRECIONANDO: validações de usuário falharam");
//            System.out.println("   Resumo das falhas:");
//            if (!senhaValida) System.out.println("   - Senha inválida");
//            if (usuarioExistente != null) System.out.println("   - Email já cadastrado");
//            if (!idadeValida) System.out.println("   - Idade fora do permitido");
//            if (!dataNascimentoValida) System.out.println("   - Data de nascimento inválida");
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=validation_failed");
            return;
        }

//        System.out.println("✅ TODAS AS VALIDAÇÕES PASSSARAM!");
//        System.out.println("=== CONTINUANDO COM O CADASTRO ===");

        String hash = new HashSenha(password).getHashSenha();

        // Logs de debug
//        System.out.println("=== DEBUG UPLOAD ===");
//        System.out.println("Arquivo recebido: " + filePart.getSubmittedFileName());
//        System.out.println("Tamanho: " + filePart.getSize() + " bytes");
//        System.out.println("Content Type: " + filePart.getContentType());

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
//        System.out.println("Upload sucesso! URL: " + imageUrl);

        Student student = new Student(birthDate, schoolYear, legalGuardianName,
                residenceAdress, wand, pet, allergies, blood, basicKit, guardianPermission, registration);

        User user = new User(name, hash, email, "student", imageUrl);

        StudentDAO studentDao = new StudentDAO();
        studentDao.save(student, user);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        request.setAttribute("email", email);
        request.getRequestDispatcher("/aluno/quiz/quiz.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Conexao.closeConnection();
    }
}
