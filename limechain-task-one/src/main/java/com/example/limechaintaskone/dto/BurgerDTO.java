package com.example.limechaintaskone.dto;

import java.util.ArrayList;
import java.util.List;

public class BurgerDTO {
    private int id;
    private String name;
    private List<IngredientDTO> ingredients;

    public BurgerDTO() {
    }

    public BurgerDTO(int id, String name, List<IngredientDTO> ingredients) {
        this.id = id;
        this.name = name;
        if(ingredients!=null)
            this.ingredients = new ArrayList<>(ingredients);
        else this.ingredients = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
