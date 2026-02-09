package com.example.minerva.model;

import java.io.Serializable;

public class Subject_student implements Model {
    private double n1;
    private double n2;
    private int student_id;
    private int subject_id;
    private int id;

    public Subject_student(double n1, double n2, int student_id, int subject_id) {
        this.n1 = n1;
        this.n2 = n2;
        this.student_id = student_id;
        this.subject_id = subject_id;
    }

    public double getN1() {
        return n1;
    }

    public double getN2() {
        return n2;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    @Override
    public Object getId() { return id; }

}
