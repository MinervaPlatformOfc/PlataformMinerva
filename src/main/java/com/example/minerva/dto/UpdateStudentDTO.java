package com.example.minerva.dto;

public class UpdateStudentDTO {
    String residenceAddress,
            petType,
            allergies;
    boolean basicKit,
            flightFitness;
    int schoolYear;

    public UpdateStudentDTO(
            String residenceAddress,
            String petType,
            String allergies,
            boolean basicKit,
            boolean flightFitness,
            int schoolYear
    ){
        this.residenceAddress = residenceAddress;
        this.petType = petType;
        this.allergies = allergies;
        this.basicKit = basicKit;
        this.flightFitness = flightFitness;
        this.schoolYear = schoolYear;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getPetType() {
        return petType;
    }

    public boolean getBasicKit(){
        return this.basicKit;
    }

    public boolean getFlightFitness(){
        return this.flightFitness;
    }
}
