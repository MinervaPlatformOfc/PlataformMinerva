package com.example.minerva.dto;


public class StudentByNotesDTO {

    private double n1;
    private double n2;
    private String studentName;
    private String subjectName;
    private int school_year;
    private int id_subject;
    private int id_student;

    public StudentByNotesDTO(double n1, double n2, String studentName, String subjectName ,int school_year, int id_subject, int id_student) {
        this.n1 = n1;
        this.n2 = n2;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.school_year = school_year;
        this.id_subject = id_subject;
        this.id_student = id_student;

    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public int getSchool_year() {
        return school_year;
    }

    public void setSchool_year(int school_year) {
        this.school_year = school_year;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}