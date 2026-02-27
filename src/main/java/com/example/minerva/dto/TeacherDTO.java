package com.example.minerva.dto;



import java.util.ArrayList;
import java.util.List;

public class TeacherDTO {

    //Attrubutes
    private int id;
    private String email,
            password,
            name,
            house,
            wand,
            pastExperiences,
            wizardTitle,
            imageUrl;

    private List<CommentDTO> comments;

    private List<String> subjects;

    //Constructor
    public TeacherDTO(String email, String password, String name, String house, String wand, String pastExperiences, String wizardTitle, List<CommentDTO> comments){
        this.email = email;
        this.password = password;
        this.name = name;
        this.house = house;
        this.wand = wand;
        this.pastExperiences = pastExperiences;
        this.wizardTitle = wizardTitle;
        this.comments = comments;
    }
    public TeacherDTO(int id, String email, String password, String name, String house, String wand, String pastExperiences, String wizardTitle, String imageUrl, List<CommentDTO> comments, List<String> subjects){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.house = house;
        this.wand = wand;
        this.pastExperiences = pastExperiences;
        this.wizardTitle = wizardTitle;
        this.imageUrl = imageUrl;
        this.comments = comments;

        List<String> list = new ArrayList<>();
        for (String s: subjects){
            int inicio = s.indexOf("(") + 1;
            int fim = s.indexOf(")");

            list.add(s.substring(inicio, fim));
        }
        this.subjects = list;
    }


    //Getters methods
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getHouse(){ return  this.house; }

    public String getWand(){
        return this.wand;
    }

    public String getPastExperiences(){
        return this.pastExperiences;
    }

    public String getWizardTitle(){
        return this.wizardTitle;
    }

    public List<CommentDTO> getComments(){ return this.comments; }

    public List<String> getSubjects() {return subjects;}

    public String toString(){
        return this.email + " | " + this.password + " | " + this.name+ " | " +this.house+ " | " +this.wand + " | " + this.pastExperiences + " | " + this.wizardTitle + " | Comments: " + this.comments + "\n";
    }
}
