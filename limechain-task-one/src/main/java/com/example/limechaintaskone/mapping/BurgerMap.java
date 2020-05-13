package com.example.limechaintaskone.mapping;

import com.example.limechaintaskone.dto.BurgerDTO;
import com.example.limechaintaskone.dto.IngredientDTO;
import com.example.limechaintaskone.model.Burger;
import com.example.limechaintaskone.model.Ingredient;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BurgerMap extends PropertyMap<Burger, BurgerDTO> {
    @Override
    protected void configure() {
        map().setName(source.getName());
        map().setIngredients(convertToDTO(source.getIngredients()));
    }

    private List<IngredientDTO> convertToDTO(List<Ingredient> ingredients) {
        if(ingredients == null) return null;
        ModelMapper mapper = new ModelMapper();
        List<IngredientDTO> ingredientDTOS = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            ingredientDTOS.add(mapper.map(ingredient,IngredientDTO.class));
        }

        return ingredientDTOS;
    }
}
