package com.example.minerva.dto;

public class StudentGradeDTO {
        private String subjectName;
        private Double n1;    // pode ser null
        private Double n2;    // pode ser null
        private Double total; // soma de n1 + n2, considerando null

        public StudentGradeDTO(String subjectName, Double n1, Double n2) {
            this.subjectName = subjectName;
            this.n1 = n1;
            this.n2 = n2;
            this.total = (n1 != null ? n1 : 0) + (n2 != null ? n2 : 0);
        }

        public String getSubjectName() { return subjectName; }
        public Double getN1() { return n1; }
        public Double getN2() { return n2; }
        public Double getTotal() { return total; }

        public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

        public void setN1(Double n1) {
            this.n1 = n1;
        }
        public void setN2(Double n2) {
            this.n2 = n2;
        }
    }
