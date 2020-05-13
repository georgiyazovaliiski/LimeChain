package com.example.limechaintaskone.essentials;

import com.example.limechaintaskone.mapping.BurgerMap;
import com.example.limechaintaskone.mapping.IngredientMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // DTO to Entity

        // Entity to DTO
        mapper.addMappings(new IngredientMap());
        mapper.addMappings(new BurgerMap());


        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PROTECTED);
        return mapper;
    }
}