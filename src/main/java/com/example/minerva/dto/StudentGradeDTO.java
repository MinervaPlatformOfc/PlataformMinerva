package com.example.minerva.dto;

public class StudentGradeDTO {
    private int studentId;
    private String studentName;
    private String studentHouseName;
    private String subjectName;
        private Double n1;    // pode ser null
        private Double n2;    // pode ser null

        public StudentGradeDTO(String subjectName, Double n1, Double n2) {
            this.subjectName = subjectName;
            this.n1 = n1;
            this.n2 = n2;
        }
    public StudentGradeDTO(int studentId, String studentName, String studentHouseName, Double n1, Double n2, String subjectName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentHouseName = studentHouseName;
        this.n1 = n1;
        this.n2 = n2;
        this.subjectName = subjectName;
    }

    public StudentGradeDTO(int studentId, String studentName, String studentHouseName, Double n1, Double n2) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentHouseName = studentHouseName;
        this.n1 = n1;
        this.n2 = n2;
    }

    public String getStudentHouseName() {
        return studentHouseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSubjectName() { return subjectName; }
        public Double getN1() { return n1; }
        public Double getN2() { return n2; }
        public Double getMedia() {
            int count = 0;
            double sum = 0;

            if (n1 != null) {
                sum += n1;
                count++;
            }
            if (n2 != null) {
                sum += n2;
                count++;
            } return count > 0 ? sum / count : null; // retorna null se não houver nota
        };

    public int getStudentId() {
        return studentId;
    }

    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

        public void setN1(Double n1) {
            this.n1 = n1;
        }
        public void setN2(Double n2) {
            this.n2 = n2;
        }
    }
