package com.example.minerva.model;


import java.util.Date;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private String role;
    private boolean firstAcess;
    private Date createdAt;

    private String imageUrl;

    public User(String name, String password, String email, String role, boolean firstAcess, Date createdAt){
        this.name = name;
        this. password = password;
        this.email = email;
        this.role = role;
        this.firstAcess = firstAcess;
        this.createdAt = createdAt;
    }

    public User(int id, String name, String password, String email, String role, String imageUrl){
        this.name = name;
        this. password = password;
        this.email = email;
        this.role = role;
        this.imageUrl = imageUrl;
        this.firstAcess = true;
        this.id = id;
    }

    public User(String name, String password, String email, String role, String imageUrl){
        this.name = name;
        this. password = password;
        this.email = email;
        this.role = role;
        this.imageUrl = imageUrl;
        this.firstAcess = true;
    }

    public User(String name, String email, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
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

    public int getId() {
        return id;
    }
}
