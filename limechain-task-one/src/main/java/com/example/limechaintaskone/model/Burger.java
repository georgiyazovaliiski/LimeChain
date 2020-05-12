package com.example.limechaintaskone.model;

@javax.persistence.Entity
public class Burger extends Entity {
    private String name;

    public Burger() {
    }

    public Burger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
