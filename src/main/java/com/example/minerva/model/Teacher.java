package com.example.minerva.model;

public class Teacher {

    private int id;
    private int houseId;
    private int userId;
    private String wand;
    private boolean headHouse;
    private String pastExperiences;
    private String wizardTitle;
    private String teacherRegistrationCode;

    // Construtor vazio
    public Teacher() {
    }

    // Construtor completo
    public Teacher(int id, int houseId, int userId, String wand, boolean headHouse,
                   String pastExperiences, String wizardTitle, String teacherRegistrationCode) {
        this.id = id;
        this.houseId = houseId;
        this.userId = userId;
        this.wand = wand;
        this.headHouse = headHouse;
        this.pastExperiences = pastExperiences;
        this.wizardTitle = wizardTitle;
        this.teacherRegistrationCode = teacherRegistrationCode;
    }

    // Getters e Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWand() {
        return wand;
    }

    public void setWand(String wand) {
        this.wand = wand;
    }

    public boolean isHeadHouse() {
        return headHouse;
    }

    public void setHeadHouse(boolean headHouse) {
        this.headHouse = headHouse;
    }

    public String getPastExperiences() {
        return pastExperiences;
    }

    public void setPastExperiences(String pastExperiences) {
        this.pastExperiences = pastExperiences;
    }

    public String getWizardTitle() {
        return wizardTitle;
    }

    public void setWizardTitle(String wizardTitle) {
        this.wizardTitle = wizardTitle;
    }

    public String getTeacherRegistrationCode() {
        return teacherRegistrationCode;
    }

    public void setTeacherRegistrationCode(String teacherRegistrationCode) {
        this.teacherRegistrationCode = teacherRegistrationCode;
    }

}