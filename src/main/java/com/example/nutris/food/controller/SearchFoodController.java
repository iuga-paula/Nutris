package com.example.nutris.food.controller;

import com.example.nutris.errorMessage.ErrorMessage;
import com.example.nutris.food.Food;
import com.example.nutris.food.FoodConstants;
import com.example.nutris.food.service.SearchFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/search_food", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchFoodController {
    private final SearchFoodService searchFoodService;

    @Autowired

    public SearchFoodController(SearchFoodService searchFoodService) {
        this.searchFoodService = searchFoodService;
    }

    @GetMapping()
    public ResponseEntity<?> searchFood(@RequestParam Map<String, String> params) {
        Optional<String> validationMessage = this.validateSearchParams(params);
        if (validationMessage.isPresent()) {
            return new ResponseEntity<>(new ErrorMessage(validationMessage.get()).getMessage(), HttpStatus.BAD_REQUEST);
        }

        List<Food> result = this.searchFoodService.searchFood(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private Optional<String> validateSearchParams(Map<String, String> params) {
        if (params.isEmpty()) {
            return Optional.of("No search parameter provided");
        }
        List<String> availableParams = FoodConstants.searchFoodParams;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            if (!availableParams.contains(name)) {
                return Optional.of(String.format("wrong parameter provided - %s", name));
            }
            // validate parameter type
            if (Objects.equals(name, "limit")) {
                //  must be int value
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException ex) {
                    return Optional.of("Wrong value supplied for limit parameter");
                }
            }
            if (!FoodConstants.searchTextValues.contains(name)) {
                // must be float value
                try {
                    if (value.charAt(0) == '>' || value.charAt(0) == '<') {
                        value = value.substring(1);
                    }
                    Float.valueOf(value);

                } catch (NumberFormatException ex) {
                    return Optional.of(String.format("Wrong value supplied for parameter %s, must be float", name));
                }
            }
        }

        return Optional.empty();
    }


}
