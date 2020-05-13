package com.example.limechaintaskone.service;

import com.example.limechaintaskone.dto.BurgerDTO;
import com.example.limechaintaskone.model.Burger;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BurgerService {
    Optional<BurgerDTO> addBurger(String name, String ...ingredients);
    Optional<BurgerDTO> addBurger(String name, String imageUrl, String ...ingredients);
    Optional<BurgerDTO> getBurger(Integer id);
    Optional<BurgerDTO> getRandom();
    Optional<BurgerDTO> getBurger(String name);
    List<BurgerDTO> getBurgers();
    List<BurgerDTO> getBurgers(Integer pageNo, Integer pageSize, String sortBy);
}
