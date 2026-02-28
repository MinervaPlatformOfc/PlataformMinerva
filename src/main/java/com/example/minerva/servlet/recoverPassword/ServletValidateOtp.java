package com.example.minerva.servlet.recoverPassword;

import com.example.minerva.utils.otp.Otp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/recoverPassword/validateOtp")
public class ServletValidateOtp extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        req.setAttribute("email", email);
        String otp = req.getParameter("otp");

        Otp otpService = new Otp();
        if (otpService.validate(email, otp)) req.getRequestDispatcher("/recoverPassword/update.jsp").forward(req, resp);
        else req.getRequestDispatcher("/recoverPassword/validate.jsp?error=code_invalid").forward(req, resp);
    }
}
