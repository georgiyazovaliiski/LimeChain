package com.example.limechaintaskone.service;

import com.example.limechaintaskone.model.Burger;
import com.example.limechaintaskone.repository.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BurgerServiceImpl implements BurgerService {
    private BurgerRepository burgerRepository;

    @Autowired
    public BurgerServiceImpl(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    @Override
    public Optional<Burger> addBurger(String name) {
        Burger burger = new Burger(name);

        return Optional.ofNullable(this.burgerRepository.save(burger));
    }

    @Override
    public Optional<Burger> getBurger(Integer id) {
        return this.burgerRepository.findById(id);
    }

    @Override
    public Optional<Burger> getRandom() {
        List<Burger> burgers = this.burgerRepository.findAll();
        if(burgers.size() >= 1) {
            int randomBurgerPos = (int) (Math.random() * (burgers.size()));
            return Optional.ofNullable(burgers.get(randomBurgerPos));
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<Burger> getBurger(String name) {
        return this.burgerRepository.getBurgerByName(name);
    }

    @Override
    public List<Burger> getBurgers() {
        return this.burgerRepository.findAll();
    }
}
