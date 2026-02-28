package com.example.minerva.dto;

public class AdminDTO {
    private int id;
    private String email,
            password,
            name,
            imageUrl;

    public AdminDTO(int id, String email, String password, String name, String imageUrl){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId(){return this.id;}

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public String getName(){return this.name;}

}
