package com.example.limechaintaskone.repository;

import com.example.limechaintaskone.model.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BurgerRepository extends PagingAndSortingRepository<Burger,Integer> {
    Optional<Burger> getBurgerByName(String name);
    @Query(value = "SELECT * FROM burger ORDER BY RAND() LIMIT 1",
            nativeQuery = true)
    Optional<Burger> getRandomBurger();
}
