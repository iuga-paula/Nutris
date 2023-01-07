package com.example.nutris.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchFoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public SearchFoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> searchFood(Map<String, String> params) {
        return this.foodRepository.searchFood(params);
    }
}
