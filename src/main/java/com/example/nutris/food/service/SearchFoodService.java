package com.example.nutris.food.service;

import com.example.nutris.food.Food;
import com.example.nutris.food.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchFoodService {
    @Autowired
    private FoodRepository foodRepository;


    public List<Food> searchFood(Map<String, String> params) {
        return this.foodRepository.searchFood(params);
    }
}
