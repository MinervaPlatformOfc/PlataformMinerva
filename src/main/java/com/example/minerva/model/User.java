package com.example.minerva.model;


import java.util.Date;

public class User {
    private String name;
    private String password;
    private String email;
    private String role;
    private boolean firstAcess;
    private Date createdAt;

    public User(String name, String password, String email, String role, boolean firstAcess, Date createdAt){
        this.name = name;
        this. password = password;
        this.email = email;
        this.role = role;
        this.firstAcess = firstAcess;
        this.createdAt = createdAt;
    }

    public User(String name, String password, String email, String role){
        this.name = name;
        this. password = password;
        this.email = email;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public boolean isFirstAcess() {
        return firstAcess;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
