package com.example.nutris.food.controller;

import com.example.nutris.errorMessage.ResponseMessage;
import com.example.nutris.food.Food;
import com.example.nutris.food.FoodDTO;
import com.example.nutris.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/food", produces = MediaType.APPLICATION_JSON_VALUE)
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping()
    public ResponseEntity<?> getFood(@RequestParam(required = false, name = "id") Long foodId,
                                     @RequestParam(required = false, name = "name") String foodName) {
        if (foodId == null && foodName == null) {
            return new ResponseEntity<>(new ResponseMessage("You should search food by id of name").getMessage(), HttpStatus.BAD_REQUEST);
        }
        Optional<Food> food = Optional.empty();
        if (foodId != null) {
            food = foodService.getFoodById(foodId);
        }
        if (foodName != null) {
            food = foodService.getFoodByName(foodName);
        }
        if (food.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> postFood(@RequestBody @Valid FoodDTO food) {
        try {
           Food newFood =  foodService.addFood(food);
            return new ResponseEntity<>(food, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()).getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
