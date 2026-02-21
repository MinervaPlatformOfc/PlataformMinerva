package com.example.minerva.dto;

public class AdminDTO {
    private int id;
    private String email,
            password,
            name;

    public AdminDTO(int id, String email, String password, String name){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public int getId(){return this.id;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public String getName(){return this.name;}

}
