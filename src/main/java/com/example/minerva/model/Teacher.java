package com.example.minerva.model;

public class Teacher {
    //Attributes
    private int id,
            houseId,
            userId;
    private boolean headHouse;
    private String wand,
            pastExperiences,
            wizardTitle,
            teacherRegistrationCode;

    //Constructor
    public Teacher(
            int houseId,
            String wand,
            boolean headHouse,
            String pastExperiences,
            String wizardTitle,
            String teacherRegistrationCode
    ){
        this.houseId = houseId;
        this.wand = wand;
        this.headHouse = headHouse;
        this.pastExperiences = pastExperiences;
        this.wizardTitle = wizardTitle;
        this.teacherRegistrationCode = teacherRegistrationCode;
    }
    public Teacher(
            String wand,
            boolean headHouse,
            String pastExperiences,
            String wizardTitle
    ){
        this.wand = wand;
        this.headHouse = headHouse;
        this.pastExperiences = pastExperiences;
        this.wizardTitle = wizardTitle;
    }

    //Getters methods
    public int getHouseId(){
        return this.houseId;
    }

    public String getWand(){
        return this.wand;
    }

    public boolean getHeadHouse(){
        return this.headHouse;
    }

    public String getPastExperiences(){
        return this.pastExperiences;
    }

    public String getWizardTitle(){
        return this.wizardTitle;
    }

    public String getTeacherRegistrationCode(){
        return this.teacherRegistrationCode;
    }

    //Setters methods
    public void setHouseId(int houseId){
        this.houseId = houseId;
    }

    public void setWand(String wand){
        this.wand = wand;
    }

    public void setHeadHouse(boolean headHouse){
        this.headHouse = headHouse;
    }

    public void setPastExperiences(String pastExperiences){
        this.pastExperiences = pastExperiences;
    }

    public void setWizardTitle(String wizardTitle){
        this.wizardTitle = wizardTitle;
    }

    public void setTeacherRegistrationCode(String teacherRegistrationCode){
        this.teacherRegistrationCode = teacherRegistrationCode;
    }
}
