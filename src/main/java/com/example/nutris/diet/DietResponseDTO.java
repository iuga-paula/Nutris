package com.example.nutris.diet;

import com.example.nutris.food.Food;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class DietResponseDTO {
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    Food food;

    @NotNull
    Long userId;

    public DietResponseDTO() {

    }

    public DietResponseDTO(Diet entity) {
        this.date=entity.getDate();
        this.id = entity.getId();
        this.food = entity.getFood();
        this.userId = entity.getUser().getId();
    }
    public DietResponseDTO(Date date, Food food, Long userId) {
        this.date = date;
        this.food = food;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
