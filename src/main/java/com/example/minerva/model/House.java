package com.example.minerva.model;

public class House {
    private String name;
    private int points;

    public House(String name, int points){
        this.name = name;
        this.points = points;
    }
    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
