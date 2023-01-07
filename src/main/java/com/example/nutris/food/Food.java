package com.example.nutris.food;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity
@Table(name="food")
public class Food {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique=true)
    private String name;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float calories;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float carbohydrates;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float proteins;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float fats;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float sugar;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float fibre;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float water;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float cholesterol;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float potassium;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float vitaminC;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float calcium;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float iron;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float vitaminD;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float vitaminB6;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float cobalamin;
    @Positive
    @Column(columnDefinition = "float default 0.0")
    private Float magnesium;

    @Column(columnDefinition = "text")
    private String goodFor;

    @Column(columnDefinition = "text")
    private String badFor;

    public Food(Long id, String name, Float calories, Float carbohydrates, Float proteins, Float fats, Float sugar, Float fibre, Float water, Float cholesterol, Float potassium, Float vitaminC, Float calcium, Float iron, Float vitaminD, Float vitaminB6, Float cobalamin, Float magnesium, String goodFor, String badFor) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        this.fats = fats;
        this.sugar = sugar;
        this.fibre = fibre;
        this.water = water;
        this.cholesterol = cholesterol;
        this.potassium = potassium;
        this.vitaminC = vitaminC;
        this.calcium = calcium;
        this.iron = iron;
        this.vitaminD = vitaminD;
        this.vitaminB6 = vitaminB6;
        this.cobalamin = cobalamin;
        this.magnesium = magnesium;
        this.goodFor = goodFor;
        this.badFor = badFor;
    }

    public Food() {
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

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public Float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Float getProteins() {
        return proteins;
    }

    public void setProteins(Float proteins) {
        this.proteins = proteins;
    }

    public Float getFats() {
        return fats;
    }

    public void setFats(Float fats) {
        this.fats = fats;
    }

    public Float getSugar() {
        return sugar;
    }

    public void setSugar(Float sugar) {
        this.sugar = sugar;
    }

    public Float getFibre() {
        return fibre;
    }

    public void setFibre(Float fibre) {
        this.fibre = fibre;
    }

    public Float getWater() {
        return water;
    }

    public void setWater(Float water) {
        this.water = water;
    }

    public Float getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Float cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Float getPotassium() {
        return potassium;
    }

    public void setPotassium(Float potassium) {
        this.potassium = potassium;
    }

    public Float getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(Float vitaminC) {
        this.vitaminC = vitaminC;
    }

    public Float getCalcium() {
        return calcium;
    }

    public void setCalcium(Float calcium) {
        this.calcium = calcium;
    }

    public Float getIron() {
        return iron;
    }

    public void setIron(Float iron) {
        this.iron = iron;
    }

    public Float getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(Float vitaminD) {
        this.vitaminD = vitaminD;
    }

    public Float getVitaminB6() {
        return vitaminB6;
    }

    public void setVitaminB6(Float vitaminB6) {
        this.vitaminB6 = vitaminB6;
    }

    public Float getCobalamin() {
        return cobalamin;
    }

    public void setCobalamin(Float cobalamin) {
        this.cobalamin = cobalamin;
    }

    public Float getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Float magnesium) {
        this.magnesium = magnesium;
    }

    public String getGoodFor() {
        return goodFor;
    }

    public void setGoodFor(String goodFor) {
        this.goodFor = goodFor;
    }

    public String getBadFor() {
        return badFor;
    }

    public void setBadFor(String badFor) {
        this.badFor = badFor;
    }
}
