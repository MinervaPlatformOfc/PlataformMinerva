package com.example.minerva.dto;

public class StudentHomeDTO {
    private int id;
    private String name;
    private String houseName;

    // Construtor
    public StudentHomeDTO(int id, String name, String houseName) {
        this.id = id;
        this.name = name;
        this.houseName = houseName;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getHouseName() { return houseName; }

    // Setters (opcional, se precisar modificar depois)
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setHouseName(String houseName) { this.houseName = houseName; }
}
