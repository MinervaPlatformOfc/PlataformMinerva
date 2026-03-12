package com.example.minerva.servlet.chatbot;

import com.example.minerva.dto.ChatbotRequest;
import com.example.minerva.dto.ChatbotResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/ia/chatbot", asyncSupported = true)
public class ChatbotServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession currentSession = request.getSession();

        String msg = request.getParameter("msg");
        String sessionId = currentSession.getId();

        ChatbotRequest chatbotRequest = new ChatbotRequest(msg, sessionId);
        try{
            ChatbotResponse chatbotResponse = Consumer.send(chatbotRequest);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            mapper.writeValue(response.getWriter(), chatbotResponse);
        } catch (InterruptedException e) {
            throw new ServletException(e);
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

}
