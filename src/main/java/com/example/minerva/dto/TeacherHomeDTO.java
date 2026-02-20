package com.example.minerva.dto;

import java.util.List;

public class TeacherHomeDTO {
    private int teacherId;
    private String teacherName;
    private String teacherHouseName;
    private List<StudentGradeDTO> students;

    public TeacherHomeDTO(int teacherId, String teacherName, String teacherHouseName, List<StudentGradeDTO> students) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherHouseName = teacherHouseName;
        this.students = students;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getTeacherHouseName() {
        return teacherHouseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public List<StudentGradeDTO> getStudents() {
        return students;
    }
}
