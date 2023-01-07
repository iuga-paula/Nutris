package com.example.nutris.food;

import com.example.nutris.errorMessage.ErrorMessage;
import net.bytebuddy.pool.TypePool;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResponseEntity<>(new ErrorMessage("You should search food by id of name").getMessage(), HttpStatus.BAD_REQUEST);
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
}
