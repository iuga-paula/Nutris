package com.example.nutris.physicalActivity;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhysicalActivityResponseDTO {
    // don't show user details in response

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String date;
    @NotNull
    private Integer duration;

    private Float caloriesBurnt;

    @NotNull
    private Long user_id;

    public PhysicalActivityResponseDTO() {
    }

    public PhysicalActivityResponseDTO(PhysicalActivity entity) {
        this.name = entity.getName();
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(entity.getDate());
        this.duration = entity.getDuration();
        this.caloriesBurnt = entity.getCaloriesBurnt();
        this.user_id = entity.getUser().getId();
        this.id = entity.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Float getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(Float caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
