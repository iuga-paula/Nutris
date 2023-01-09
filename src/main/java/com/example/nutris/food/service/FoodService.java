package com.example.nutris.food.service;

import com.example.nutris.food.Food;
import com.example.nutris.food.FoodDTO;
import com.example.nutris.food.FoodMapper;
import com.example.nutris.food.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    private final FoodMapper mapper;

    @Autowired
    public FoodService(FoodRepository foodRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository; this.mapper = foodMapper;
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

    public Food addFood(FoodDTO food) throws Exception {
        FoodNameExists(food.getName());
        return foodRepository.saveAndFlush(new Food(food));
    }

    private void FoodNameExists(String name) throws Exception {
        Optional<Food> testFood = foodRepository.findByName(name);
        if(testFood.isPresent()) {
            throw new Exception("There is already an item with that name");
        }
    }

    public Food updateFood(Long id, FoodDTO food) throws Exception {
        Optional<Food> foodToUpdate = foodRepository.findById(id);
        if(foodToUpdate.isEmpty()) {
            throw new Exception("There is no item with provided id");
        }
        Food newFood = foodToUpdate.get();
        FoodNameExists(food.getName());
        mapper.updateFoodFromDto(food, newFood);
        foodRepository.save(newFood);

        return newFood;
    }
}
