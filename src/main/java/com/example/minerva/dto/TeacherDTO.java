package com.example.minerva.dto;



import java.util.List;

public class TeacherDTO {

    //Attrubutes
    private String email,
            password,
            name,
            house,
            wand,
            pastExperiences,
            wizardTitle;

    private List<CommentDTO> comments;

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

    //Getters methods
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
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


    public String toString(){
        return this.email + " | " + this.password + " | " + this.name+ " | " +this.house+ " | " +this.wand + " | " + this.pastExperiences + " | " + this.wizardTitle + " | Comments: " + this.comments + "\n";
    }
}
