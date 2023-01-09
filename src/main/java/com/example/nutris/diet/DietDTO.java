package com.example.nutris.diet;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class DietDTO {

    @NotNull
    private Long foodId;

    @NotNull
    private String date;

    public DietDTO() {

    }

    public DietDTO(Long foodId, String date) {
        this.foodId = foodId;
        this.date = date;
    }

    public Long getFoodId() {
        return this.foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
