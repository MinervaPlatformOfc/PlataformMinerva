package com.example.minerva.view;

public class StudentForTeacherView {

        private String name;
        private String email;
        private String registration;
        private String schoolYear;
        private String legalGuardianName;
        private String wand;
        private String petType;
        private String allergies;
        private String blood;
        private String basicKit;

        // Constructor
        public StudentForTeacherView(String name, String email, String registration, String schoolYear,
                       String legalGuardianName, String wand, String petType,
                       String allergies, String blood, String basicKit) {

            this.name = name;
            this.email = email;
            this.registration = registration;
            this.schoolYear = schoolYear;
            this.legalGuardianName = legalGuardianName;
            this.wand = wand;
            this.petType = petType;
            this.allergies = allergies;
            this.blood = blood;
            this.basicKit = basicKit;
        }

        // Getters and Setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRegistration() {
            return registration;
        }

        public void setRegistration(String registration) {
            this.registration = registration;
        }

        public String getSchoolYear() {
            return schoolYear;
        }

        public void setSchoolYear(String schoolYear) {
            this.schoolYear = schoolYear;
        }

        public String getLegalGuardianName() {
            return legalGuardianName;
        }

        public void setLegalGuardianName(String legalGuardianName) {
            this.legalGuardianName = legalGuardianName;
        }

        public String getWand() {
            return wand;
        }

        public void setWand(String wand) {
            this.wand = wand;
        }

        public String getPetType() {
            return petType;
        }

        public void setPetType(String petType) {
            this.petType = petType;
        }

        public String getAllergies() {
            return allergies;
        }

        public void setAllergies(String allergies) {
            this.allergies = allergies;
        }

        public String getBlood() {
            return blood;
        }

        public void setBlood(String blood) {
            this.blood = blood;
        }

        public String getBasicKit() {
            return basicKit;
        }

        public void setBasicKit(String basicKit) {
            this.basicKit = basicKit;
        }
    }


