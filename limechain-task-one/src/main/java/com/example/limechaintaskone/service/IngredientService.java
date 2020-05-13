package com.example.limechaintaskone.service;

import com.example.limechaintaskone.model.Ingredient;

import java.util.Optional;

public interface IngredientService {
    Optional<Ingredient> getIngredientByName(String name);
}
