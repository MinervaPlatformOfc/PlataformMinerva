package com.example.minerva.servlet.chatbot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.example.minerva.dto.ChatbotRequest;
import com.example.minerva.dto.ChatbotResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;


public class Consumer {
    private static Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private static String API_KEY = dotenv.get("CHATBOT_API_KEY", System.getenv("CHATBOT_API_KEY"));

    public static ChatbotResponse send(ChatbotRequest request) throws IOException, InterruptedException{



        HttpClient client = HttpClient.newHttpClient();

        ObjectMapper mapper = new ObjectMapper();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://chatbotapi-gei3.onrender.com/"))
                .headers(
                        "Content-Type", "application/json",
                        "CHATBOT_API_KEY", API_KEY
                        )
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(request)))
                .build();



        HttpResponse<String> httpResponse = client.send(
                httpRequest,
                HttpResponse.BodyHandlers.ofString()
        );


        ChatbotResponse response =
                mapper.readValue(httpResponse.body(), ChatbotResponse.class);

        return response;
    }
}

