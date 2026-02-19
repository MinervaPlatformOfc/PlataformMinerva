package com.example.minerva.view;

import java.time.LocalDateTime;

public class CommentView {

    private String content;

    private String student;
    private int idStudent;

    private String teacher;
    private int idTeacher;

    private String subjectName;
    private int subjectId;

    private double score;

    private LocalDateTime dateTime;

    public CommentView() {
    }

    public CommentView(String content,
                       String student,
                       int idStudent,
                       String teacher,
                       int idTeacher,
                       String subjectName,
                       int subjectId,
                       double score,
                       LocalDateTime dateTime) {

        this.content = content;
        this.student = student;
        this.idStudent = idStudent;
        this.teacher = teacher;
        this.idTeacher = idTeacher;
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.score = score;
        this.dateTime = dateTime;
    }

    // Getters e Setters

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
