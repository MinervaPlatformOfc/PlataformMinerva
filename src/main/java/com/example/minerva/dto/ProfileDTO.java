package com.example.minerva.dto;

import com.example.minerva.dao.GradeDAO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ProfileDTO {
        private final int id;
        private final String name;
        private final Date birthDate;
        private final String bloodStatus;
        private final String registration;
        private final String residenceAddress;
        private final String guardianName;
        private final String schoolYear;
        private final String houseName;

        // Varinha
        private final String wandWood;
        private final String wandCore;
        private final String wandFlexibility;

        private final String petType;
        private final boolean flightFitness;
        private final boolean basicKit;
        private final String allergies;
        private final boolean guardianPermission;

        private final List<SubjectDTO> grades;

        public ProfileDTO(int id, String name, Date birthDate, String bloodStatus, String registration,
                          String residenceAddress, String guardianName, String schoolYear, String houseName,
                          String wandWood, String wandCore, String wandFlexibility,
                          String petType, boolean flightFitness, boolean basicKit, String allergies, boolean guardianPermission) {
            this.id = id;
            this.name = name;
            this.birthDate = birthDate;
            this.bloodStatus = bloodStatus;
            this.registration = registration;
            this.residenceAddress = residenceAddress;
            this.guardianName = guardianName;
            this.schoolYear = schoolYear;
            this.houseName = houseName;
            this.wandWood = wandWood;
            this.wandCore = wandCore;
            this.wandFlexibility = wandFlexibility;
            this.petType = petType;
            this.flightFitness = flightFitness;
            this.basicKit = basicKit;
            this.allergies = allergies;
            this.guardianPermission = guardianPermission;
            this.grades = new GradeDAO().getSubjects(id);
        }

        // Getters
        public String getName() { return name; }
        public Date getBirthDate() { return birthDate; }
        public String getBloodStatus() { return bloodStatus; }
        public String getRegistration() { return registration; }
        public String getResidenceAddress() { return residenceAddress; }
        public String getGuardianName() { return guardianName; }
        public String getSchoolYear() { return schoolYear; }
        public String getHouseName() { return houseName; }
        public String getWandWood() { return wandWood; }
        public String getWandCore() { return wandCore; }
        public String getWandFlexibility() { return wandFlexibility; }
        public String getPetType() { return petType; }
        public boolean isFlightFitness() { return flightFitness; }
        public boolean isBasicKit() { return basicKit; }
        public String getAllergies() { return allergies; }
        public boolean isGuardianPermission() { return guardianPermission; }

    public List<SubjectDTO> getGrades() {
        return grades;
    }
}
