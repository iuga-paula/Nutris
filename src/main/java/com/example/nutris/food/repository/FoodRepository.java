package com.example.nutris.food.repository;

import com.example.nutris.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FoodRepository extends JpaRepository<Food,Long>, CustomFoodRepository {
    Optional<Food> findByName(String name);

}

