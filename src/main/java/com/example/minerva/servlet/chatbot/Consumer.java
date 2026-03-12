package com.example.minerva.servlet.chatbot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.example.minerva.dto.ChatbotRequest;
import com.example.minerva.dto.ChatbotResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Consumer {

    public static ChatbotResponse send(ChatbotRequest request) throws IOException, InterruptedException{


        HttpClient client = HttpClient.newHttpClient();

        ObjectMapper mapper = new ObjectMapper();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://chatbotapi-gei3.onrender.com/"))
                .headers(
                        "Content-Type", "application/json",
                        "CHATBOT_API_KEY","9e4c1e9c3a5b2c64c91a8c9b7e3d3c7a5a4c2c7e5f6b1a9d4f3c2e1b0a8d7c6"
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

