package com.example.limechaintaskone.repository;

import com.example.limechaintaskone.model.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BurgerRepository extends JpaRepository<Burger,Integer> {
    Optional<Burger> getBurgerByName(String name);
}
