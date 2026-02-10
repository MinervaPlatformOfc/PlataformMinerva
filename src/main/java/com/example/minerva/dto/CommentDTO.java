package com.example.minerva.dto;

import java.time.LocalDateTime;

public class CommentDTO {
    private String content;
    private int score;
    private LocalDateTime createdAt;

    public CommentDTO(String content, Integer score, LocalDateTime createdAt) {
        this.content = content;
        this.score = score;
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public Integer getScore() {
        return score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
