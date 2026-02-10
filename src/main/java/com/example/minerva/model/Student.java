package com.example.minerva.model;

import java.time.LocalDate;

public class Student {
    private int id;
    private int userId;
    private int houseId;
    private int schoolYear;
    private boolean flightFitness;
    private String blood;
    private LocalDate birthDate;
    private String wand;
    private String petType;
    private String allergies;
    private  boolean guardianPermission;
    private boolean basicKit;
    private  String legalGuardianName;
    private  String residenceAddress;
    private String registration;

    public Student( LocalDate birthDate, Integer schoolYear, String legalGuardianName, String residenceAdress, String wand, String pet, String allergies, String blood, boolean basicKit, boolean guardianPermission, String registration){
        this.birthDate = birthDate;
        this.schoolYear = schoolYear;
        this.legalGuardianName = legalGuardianName;
        this.residenceAddress = residenceAdress;
        this.wand = wand;
        this.petType = pet;
        this.allergies = allergies;
        this.blood = blood;
        this.basicKit = basicKit;
        this.guardianPermission = guardianPermission;
        this.registration = registration;
    }

    public int getId() {
        return id;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public int getHouseId() {
        return houseId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getUserId() {
        return userId;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getLegalGuardianName() {
        return legalGuardianName;
    }

    public String getBlood() {
        return blood;
    }

    public String getRegistration() {
        return registration;
    }

    public String getPetType() {
        return petType;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public String getWand() {
        return wand;
    }

    public boolean getBasicKit() {
        return basicKit;
    }

    public boolean getGuardianPermission() {
        return guardianPermission;
    }
}
