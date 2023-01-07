package com.example.nutris.food.repository;

import com.example.nutris.food.Food;

import java.util.List;
import java.util.Map;

public interface CustomFoodRepository {
    public List<Food> searchFood(Map<String, String> params);
}
