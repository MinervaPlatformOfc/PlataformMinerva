package com.example.minerva.view;

public class StudentForTeacherView {
        private int id_user;
        private int id_student;
        private int id_teacher;
        private int id_subject;
        private int id_house;
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
        public StudentForTeacherView(int id_user, int id_student, String name, String email, String registration, String schoolYear,
                       String legalGuardianName, String wand, String petType,
                       String allergies, String blood, String basicKit, int id_house) {
            this.id_student = id_student;
            this.id_user = id_user;
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
            this.id_house = id_house;
        }

        // Getters and Setters


    public int getId_house() {
        return id_house;
    }

    public int getId_subject() {
        return id_subject;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public int getId_student() {
        return id_student;
    }

    public int getId_user() {
        return id_user;
    }

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


