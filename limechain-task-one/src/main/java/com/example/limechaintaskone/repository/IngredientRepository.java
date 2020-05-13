package com.example.limechaintaskone.repository;

import com.example.limechaintaskone.model.Burger;
import com.example.limechaintaskone.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {
    Optional<Ingredient> getIngredientByName(String name);
}
