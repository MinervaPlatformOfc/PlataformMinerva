package com.example.minerva.model;

public class Teacher implements Model{
    private int id;
    private int house_id;
    private int user_id;

    public Teacher(int id, int house_id, int user_id) {
        this.id = id;
        this.house_id = house_id;
        this.user_id = user_id;
    }

    public Object getId() { return id; }

}
