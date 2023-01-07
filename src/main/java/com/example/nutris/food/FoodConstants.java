package com.example.nutris.food;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public final class FoodConstants {

    public final static List<String> searchTextValues = List.of( "goodFor", "badFor");
    public final static List<String> searchFoodParams = List.of("calories",
                                                                "carbohydrates",
                                                                "proteins",
                                                                "fats",
                                                                "sugar",
                                                                "fibre",
                                                                "water",
                                                                "cholesterol",
                                                                "potassium",
                                                                "vitaminC",
                                                                "calcium",
                                                                "iron",
                                                                "vitaminD",
                                                                "vitaminB6",
                                                                "cobalamin",
                                                                "magnesium",
                                                                "goodFor",
                                                                "badFor",
                                                                "limit");


}
