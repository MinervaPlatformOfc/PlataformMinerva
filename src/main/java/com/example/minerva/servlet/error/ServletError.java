package com.example.minerva.servlet.error;

import com.example.minerva.dto.ErrorDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ServletError extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        if (status == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int statusCode = (int) status;

        System.out.println("Ocorreu um erro!\nStatus: " + statusCode);

        ErrorDTO error = new ErrorDTO(
                statusCode,
                (String) request.getAttribute("jakarta.servlet.error.message"),
                (Throwable) request.getAttribute("jakarta.servlet.error.exception"),
                (String) request.getAttribute("jakarta.servlet.error.request_uri")
        );

        request.setAttribute("error", error);

        request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);


    }
}
