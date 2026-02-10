package com.example.minerva.dto;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;

public class SubjectDTO {
    private int subjectId;
    private String subjectName;
    private ArrayList<CommentDTO> comments;

    public SubjectDTO(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public SubjectDTO(int subjectId, String subjectName, ArrayList<CommentDTO> comments) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.comments = comments;
    }

    public String getSubjectName() { return subjectName; }

    public int getSubjectId() {
        return subjectId;
    }

    public ArrayList<CommentDTO> getComments() {
        return comments;
    }

    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

}
