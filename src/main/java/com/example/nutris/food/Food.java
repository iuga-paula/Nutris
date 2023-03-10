package com.example.nutris.food;

import com.example.nutris.diet.Diet;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float calories;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float carbohydrates;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float proteins;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float fats;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float sugar;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float fibre;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float water;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float cholesterol;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float potassium;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float vitaminC;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float calcium;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float iron;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float vitaminD;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float vitaminB6;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float cobalamin;
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Column(columnDefinition = "float default 0.0")
    private Float magnesium;

    @Column(columnDefinition = "text")
    private String goodFor;

    @Column(columnDefinition = "text")
    private String badFor;

    @OneToMany(mappedBy = "food")
    private Set<Diet> diets;


    public Food(String name, Float calories, Float carbohydrates, Float proteins, Float fats, Float sugar, Float fibre, Float water, Float cholesterol, Float potassium, Float vitaminC, Float calcium, Float iron, Float vitaminD, Float vitaminB6, Float cobalamin, Float magnesium, String goodFor, String badFor) {
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

    public Food(FoodDTO food) {
        this.name = food.getName();
        this.calories = this.getOrDefault(food.getCalories(), 0.0F);
        this.carbohydrates = this.getOrDefault(food.getCarbohydrates(), 0.0F);
        this.proteins = this.getOrDefault(food.getProteins(), 0.0F);
        this.fats = this.getOrDefault(food.getFats(), 0.0F);
        this.sugar = this.getOrDefault(food.getSugar(), 0.0F);
        this.fibre = this.getOrDefault(food.getFibre(), 0.0F);
        this.water = this.getOrDefault(food.getWater(), 0.0F);
        this.cholesterol = this.getOrDefault(food.getCholesterol(), 0.0F);
        this.potassium = this.getOrDefault(food.getPotassium(), 0.0F);
        this.vitaminC = this.getOrDefault(food.getVitaminC(), 0.0F);
        this.calcium = this.getOrDefault(food.getCalcium(), 0.0F);
        this.iron = this.getOrDefault(food.getIron(), 0.0F);
        this.vitaminD = this.getOrDefault(food.getVitaminD(), 0.0F);
        this.vitaminB6 = this.getOrDefault(food.getVitaminB6(), 0.0F);
        this.cobalamin = this.getOrDefault(food.getCobalamin(), 0.0F);
        this.magnesium = this.getOrDefault(food.getMagnesium(), 0.0F);
        this.goodFor = this.getOrDefault(food.getGoodFor(), "");
        this.badFor = this.getOrDefault(food.getBadFor(), "");
    }

    private <T> T getOrDefault(T field, T defaultValue) {
        if (field == null) {
            return defaultValue;
        }
        return field;
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

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", carbohydrates=" + carbohydrates +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", sugar=" + sugar +
                ", fibre=" + fibre +
                ", water=" + water +
                ", cholesterol=" + cholesterol +
                ", potassium=" + potassium +
                ", vitaminC=" + vitaminC +
                ", calcium=" + calcium +
                ", iron=" + iron +
                ", vitaminD=" + vitaminD +
                ", vitaminB6=" + vitaminB6 +
                ", cobalamin=" + cobalamin +
                ", magnesium=" + magnesium +
                ", goodFor='" + goodFor + '\'' +
                ", badFor='" + badFor + '\'' +
                '}';
    }
}
