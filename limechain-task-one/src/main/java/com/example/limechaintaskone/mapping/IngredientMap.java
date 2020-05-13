package com.example.limechaintaskone.mapping;

import com.example.limechaintaskone.dto.IngredientDTO;
import com.example.limechaintaskone.model.Ingredient;
import org.modelmapper.PropertyMap;

public class IngredientMap extends PropertyMap<Ingredient, IngredientDTO> {
    @Override
    protected void configure() {
        map().setName(source.getName());
    }
}
