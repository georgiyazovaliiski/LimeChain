package com.example.limechaintaskone.service;

import com.example.limechaintaskone.model.Burger;

import java.util.List;
import java.util.Optional;

public interface BurgerService {
    Optional<Burger> addBurger(String name);
    Optional<Burger> getBurger(Integer id);
    Optional<Burger> getRandom();
    Optional<Burger> getBurger(String name);
    List<Burger> getBurgers();
}
