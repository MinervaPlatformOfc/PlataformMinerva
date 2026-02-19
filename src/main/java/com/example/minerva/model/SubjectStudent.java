package com.example.minerva.model;

public class SubjectStudent {

    private int subjectId;
    private double n1;
    private double n2;
    private int student_id;


    // Construtor com parâmetros
    public SubjectStudent(int subjectId, double n1, double n2, int student_id) {
        this.subjectId = subjectId;
        this.n1 = n1;
        this.n2 = n2;
        this.student_id = student_id;
    }

    // Getters e Setters
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    // toString
    @Override
    public String toString() {
        return "SubjectStudent{" +
                "subjectId=" + subjectId +
                ", n1=" + n1 +
                ", n2=" + n2 +
                ", student_id=" + student_id +
                '}';
    }
}

