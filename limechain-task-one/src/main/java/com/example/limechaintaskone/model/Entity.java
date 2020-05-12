package com.example.limechaintaskone.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entity {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    public Entity() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
