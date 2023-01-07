package com.example.nutris.food.service;

import com.example.nutris.food.Food;
import com.example.nutris.food.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getFood() {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long foodId) {
        return foodRepository.findById(foodId);
    }

    public Optional<Food> getFoodByName(String foodName) {
        return foodRepository.findByName(foodName);
    }
}
