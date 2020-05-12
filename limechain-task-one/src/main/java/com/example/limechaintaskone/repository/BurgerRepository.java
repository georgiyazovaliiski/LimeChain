package com.example.limechaintaskone.repository;

import com.example.limechaintaskone.model.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerRepository extends JpaRepository<Burger,Integer> {

}
