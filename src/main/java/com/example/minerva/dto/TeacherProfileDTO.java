package com.example.minerva.dto;
import java.time.LocalDateTime;
import java.util.List;

public class TeacherProfileDTO {

    private int id;
    private String imageUrl;
    private String name;
    private String wizardTitle;
    private boolean headHouse;
    private String houseName;
    private String wandWood;
    private String wandCore;
    private String wandFlexibility;
    private String pastExperiences;
    private String teacherRegistrationCode;

    private List<String> subjects; // matériasS

    public TeacherProfileDTO(int id, String imageUrl, String name, String wizardTitle, boolean headHouse,
                             String houseName, String wandWood, String wandCore, String wandFlexibility,
                             String pastExperiences, String teacherRegistrationCode, List<String> subjects) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.wizardTitle = wizardTitle;
        this.headHouse = headHouse;
        this.houseName = houseName;
        this.wandWood = wandWood;
        this.wandCore = wandCore;
        this.wandFlexibility = wandFlexibility;
        this.pastExperiences = pastExperiences;
        this.teacherRegistrationCode = teacherRegistrationCode;
        this.subjects = subjects;
    }

    // getters
    public int getId() { return id; }
    public String getImageUrl() { return imageUrl; }
    public String getName() { return name; }
    public String getWizardTitle() { return wizardTitle; }
    public boolean isHeadHouse() { return headHouse; }
    public String getHouseName() { return houseName; }
    public String getWandWood() { return wandWood; }
    public String getWandCore() { return wandCore; }
    public String getWandFlexibility() { return wandFlexibility; }
    public String getPastExperiences() { return pastExperiences; }
    public String getTeacherRegistrationCode() { return teacherRegistrationCode; }
    public List<String> getSubjects() { return subjects; }
}

