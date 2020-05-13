package com.example.limechaintaskone.model;


import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;

@javax.persistence.Entity
public class Ingredient extends com.example.limechaintaskone.model.Entity {
    @Column(unique=true)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private List<Burger> containingIn;

    public Ingredient() {
    }

    public Ingredient(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 25;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }

        if(obj.getClass() == this.getClass()){
            Ingredient comparing = (Ingredient) obj;
            if(this.name.equals(comparing.name)) return true;
            else return false;
        }
        return false;
    }
}
