package com.example.limechaintaskone.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class Burger extends com.example.limechaintaskone.model.Entity {
    @Transient
    public static String defaultImageUrl = "https://vignette.wikia.nocookie.net/new-world-union/images/d/dd/Unknown.png/revision/latest?cb=20160713053648";


    @Column(unique = true)
    private String name;
    private String burgerImageURL;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<com.example.limechaintaskone.model.Ingredient> ingredients;

    public Burger() {
    }

    public Burger(String name) {
        this(name,Burger.defaultImageUrl);
    }

    public Burger(String name, String burgerImageURL){
        this(name,new ArrayList<Ingredient>(),burgerImageURL);
    }

    public Burger(String name, List<Ingredient> ingredients){
        this(name,ingredients,Burger.defaultImageUrl);
    }

    public Burger(String name, List<Ingredient> ingredients, String burgerImageURL){
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.burgerImageURL = burgerImageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getBurgerImageURL() {
        return burgerImageURL;
    }

    public void setBurgerImageURL(String burgerImageURL) {
        this.burgerImageURL = burgerImageURL;
    }
}
