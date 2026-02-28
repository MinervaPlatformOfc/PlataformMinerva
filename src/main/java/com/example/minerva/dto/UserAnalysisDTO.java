package com.example.minerva.dto;


public class UserAnalysisDTO {
    private int totalUsers;
    private double adminPercent,
            studentPercent,
            teacherPercent;


    public UserAnalysisDTO(int totalUsers, int totalAdmins, int totalStudents, int totalTeachers){
        this.totalUsers = totalUsers;
        this.adminPercent = (totalAdmins/totalUsers) * 100;
        this.studentPercent = (totalStudents/totalUsers) * 100;
        this.teacherPercent = (totalTeachers/totalUsers) * 100;
    }

    public Integer getTotalUsers(){return this.totalUsers;}
    public Double getAdminPercent(){return this.adminPercent;}
    public Double getStudentPercent(){return this.studentPercent;}
    public Double getTeacherPercent(){return this.teacherPercent;}
}
