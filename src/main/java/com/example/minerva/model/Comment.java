package com.example.minerva.model;

import java.time.LocalDateTime;

public class Comment {

    private int id;
    private String content;

    private int teacherId;
    private int studentId;
    private int subjectId;

    private LocalDateTime dateTime;
    private int score;

    public Comment() {}

    public Comment(int id, String content, int teacherId, int studentId,
                   int subjectId, LocalDateTime dateTime, int score) {
        this.id = id;
        this.content = content;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.dateTime = dateTime;
        this.score = score;
    }

    // Getters e Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getTeacherId() { return teacherId; }
    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
